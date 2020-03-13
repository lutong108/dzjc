package com.chinacreator.dzjc_tongji.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinacreator.dzjc_ruleEngine.utils.DownLoadUtil;
import com.chinacreator.dzjc_tongji.web.rest.RightsInfoController;
import com.chinacreator.util.FilesUtil;


public class RightsInfoServlet extends HttpServlet{

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
			String titleName = new RightsInfoController().exportExcel(cond,tableName,request,response);
			path = srcPath + titleName + ".xls";
		}
		// 用户下载
		DownLoadUtil.download(path, response, request);
		// 删除临时文件
		FilesUtil.deleteFile(path);
	}
}
