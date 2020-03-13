package com.chinacreator.dzjc_tongji.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

import com.chinacreator.dzjc_tongji.web.rest.HotInstanceSuperviseController;
import com.chinacreator.util.FilesUtil;


public class HotInstanceSuperviseServlet extends HttpServlet{

	private static final long serialVersionUID = 2424110233391559689L;
	
	// 存放文件夹所在路径
	private String srcPath = this.getClass().getResource("/").getPath();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("tableName");
		String type = request.getParameter("type");
		String cond = request.getParameter("cond");
		String path = null;
		if ("1".equals(type))
		{
			// Excel导出
			String titleName = new HotInstanceSuperviseController().exportExcel(cond,tableName,request,response);
			path = srcPath + titleName + ".xls";
		}
		// 用户下载
		download(path, response);
		// 删除临时文件
		FilesUtil.deleteFile(path);
	}

	/**
	 * 单据下载到本地
	 * 
	 * @param path 路径
	 * @param response
	 */
	public void download(String path, HttpServletResponse response) {

		try {
			// path是指下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = URLEncoder.encode(file.getName(), "UTF-8");
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = FileCopyUtils.copyToByteArray(fis);
			fis.close();
			BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(buffer));
			response.reset();// 清空response
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");

			OutputStream outs;
			outs = response.getOutputStream();
			int len;
			while ((len = is.read(buffer)) != -1) {
				outs.write(buffer, 0, len);
				outs.flush();
			}
			is.close();
			outs.flush();
			outs.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
