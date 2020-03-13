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
import com.chinacreator.dzjc_ruleEngine.web.rest.InstanceSuperviseStatisticsController;
import com.chinacreator.dzjc_ruleEngine.web.rest.JCSumSuperviseInfoAllController;
import com.chinacreator.dzjc_tongji.web.rest.TongJiController;
import com.chinacreator.util.FilesUtil;

public class JCSumSuperviseInfoAllServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = -7508288159644266389L;

	// 存放文件夹所在路径
	private String srcPath = this.getClass().getResource("/").getPath();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String org_xzqm = request.getParameter("org_xzqm");
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		String parentCode=request.getParameter("parentCode");
		String viewFlag=request.getParameter("viewFlag");
		String type = request.getParameter("type");
		String org_id=request.getParameter("org_id");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		String path = null;
		if ("1".equals(type)) {//1是word

			// 全省发牌统计生成word文件，并返回文件名
			String titleName = new JCSumSuperviseInfoAllController().wordExport(org_xzqm,beginDate,endDate,parentCode,viewFlag);
			path = srcPath + titleName + ".doc";

		} else if("2".equals(type)) {//Excel

			// 全省发牌统计生成excel文件，并返回文件名
			String titleName = new JCSumSuperviseInfoAllController().excelExport(org_xzqm,beginDate,endDate,parentCode,viewFlag);
			path = srcPath + titleName + ".xls";
		} else if("3".equals(type)){
			// 发牌统计生成excel文件，并返回文件名
			String titleName = new JCSumSuperviseInfoAllController().excelExport1(org_xzqm,beginDate,endDate,parentCode,viewFlag);
			path = srcPath + titleName + ".xls";
			
		} else if("4".equals(type)){
			// 发牌统计生成excel文件，并返回文件名
			String titleName = new JCSumSuperviseInfoAllController().wordExport1(org_xzqm,beginDate,endDate,parentCode,viewFlag);
			path = srcPath + titleName + ".doc";
		} else if ("5".equals(type)) {
			String titleName = new InstanceSuperviseStatisticsController().wordExport(org_xzqm, beginDate, endDate, parentCode, viewFlag);
			path = srcPath + titleName + ".doc";
		} else if ("6".equals(type)) {
			String titleName = new InstanceSuperviseStatisticsController().excelExport(org_xzqm, beginDate, endDate, parentCode, viewFlag);
			path = srcPath + titleName + ".xls";
			//新办件统计导出
		}else if("7".equals(type) || "8".equals(type) ){
			String titleName= new TongJiController().export(type, beginDate, endDate, org_id, year, month, response, request);
			path =  titleName;
		} else if ("9".equals(type) || "10".equals(type)){
			//省三级监察导出
			String titleName = new TongJiController().exportProvinceThreeLevelSupervise(type, beginDate, endDate, org_id, year, month, response, request);
			path = titleName;
		} else if ("11".equals(type) || "12".equals(type)) {
			String titleName= new TongJiController().exportBusiness(type, beginDate, endDate, org_id, year, month, response, request);
			path =  titleName;
		}else if ("13".equals(type) || "14".equals(type)) {
			String titleName= new TongJiController().exportBusiness(type, beginDate, endDate, org_id, year, month, response, request);
			path =  titleName;
		}
		
		if (!"".equals(path) && path != null) {
			// 用户下载
			DownLoadUtil.download(path, response,request);
			// 删除临时文件
			FilesUtil.deleteFile(path);
		}
		
	}
}
