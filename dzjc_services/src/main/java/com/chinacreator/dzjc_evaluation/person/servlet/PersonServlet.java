package com.chinacreator.dzjc_evaluation.person.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

import com.chinacreator.dzjc_evaluation.person.web.rest.PersonController;
import com.chinacreator.util.FilesUtil;

/**
 * 
 * @author 谌欣慰
 */
public class PersonServlet extends HttpServlet {

	private static final long serialVersionUID = -5504359072758715478L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		String org_id = request.getParameter("org_id");
		String tableId = request.getParameter("tableId");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");

		String path = null;
		if ("1".equals(type)) {// 1是word

			// 生成word文件，并返回文件名
			//			String titleName = new DepartmentController().wordExport(org_id, dataStr);
			//			path = this.getClass().getResource("/").getPath() + titleName + ".doc";

		} else {// Excel

			// 生成excel文件，并返回文件名
			String titleName = new PersonController().PersonExcel(org_id, tableId, beginDate, endDate);
			path = this.getClass().getResource("/").getPath() + titleName + ".xls";

		}

		// 用户下载
		download(path, response);

		// 删除临时文件
		FilesUtil.deleteFile(path);
	}

	public void download(String path, HttpServletResponse response) {
		try {

			// path是指欲下载的文件的路径。
			File file = new File(path);

			// 取得文件名。
			String filename = URLEncoder.encode(file.getName(), "UTF-8");

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			// 清空response
			response.reset();

			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
