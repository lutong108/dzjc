package com.chinacreator.dzjc_ruleEngine.servlet;

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

import com.chinacreator.dzjc_ruleEngine.utils.DownLoadUtil;
import com.chinacreator.dzjc_ruleEngine.web.rest.BussinessAllInfoController;
import com.chinacreator.util.FilesUtil;



public class ExcelImport extends HttpServlet{
	
	private static final long serialVersionUID = -1L;

	// 存放文件夹所在路径
	private String srcPath = this.getClass().getResource("/").getPath();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cond=request.getParameter("cond");
		String ids=request.getParameter("ids");
		
		String path = null;
		String titleName = new BussinessAllInfoController().excelExport(ids, cond);//excel
		path = srcPath + titleName + ".xls";
		// 用户下载
		DownLoadUtil.download(path, response, request);

		// 删除临时文件
		FilesUtil.deleteFile(path);

	}

}
