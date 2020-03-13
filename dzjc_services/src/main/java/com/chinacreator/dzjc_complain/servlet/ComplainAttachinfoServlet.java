package com.chinacreator.dzjc_complain.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.fs.DownResult;
import com.chinacreator.dzjc_complain.TaJcComplainAttachinfo;
import com.chinacreator.util.S3StorageServices;

public class ComplainAttachinfoServlet extends HttpServlet {

	private static final long serialVersionUID = 5736627560796325230L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String attachId = request.getParameter("attachId");
		String fileName = request.getParameter("fileName");

		try {

			fileName = URLDecoder.decode(fileName, "utf-8");// 转码

			TaJcComplainAttachinfo attachinfo = new TaJcComplainAttachinfo();
			attachinfo.setAttachId(attachId);

			// 查询材料信息
			List<TaJcComplainAttachinfo> list = DaoFactory.create(TaJcComplainAttachinfo.class).select(attachinfo);

			// 读取文件
			if (list.size() > 0) {
				for (TaJcComplainAttachinfo entity : list) {

					if (entity != null && entity.getAttachName().equals(fileName)) {

						String url = entity.getAttachName();
						DownResult result = new S3StorageServices().downloadFile(url);

						if (result == null) {
							throw new Exception("文件已删除或文件不存在");
						}

						fileName = url.substring(url.lastIndexOf("$") + 1);

						response.setCharacterEncoding("utf-8");
						response.setHeader("Content-Disposition",
								"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

						InputStream inputStream = result.getInputStream();
						byte[] buf = FileCopyUtils.copyToByteArray(inputStream);
						BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(buf));
						OutputStream outs;
						outs = response.getOutputStream();
						int len;
						while ((len = is.read(buf)) != -1) {
							outs.write(buf, 0, len);
							outs.flush();
						}
						is.close();
						outs.flush();
						outs.close();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.doGet(request, response);
	}
}
