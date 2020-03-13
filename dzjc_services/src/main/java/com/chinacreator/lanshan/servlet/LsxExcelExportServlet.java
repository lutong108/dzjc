package com.chinacreator.lanshan.servlet;

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

import com.chinacreator.lanshan.web.rest.EvaluateController;
import com.chinacreator.lanshan.web.rest.JiFenController;
import com.chinacreator.lanshan.web.rest.IsHelpStatisticsController;
import com.chinacreator.util.FilesUtil;
/**
 * 蓝山帮你办导出Excel工具类
 * <p>Title: ExcelExportServlet</p> 
 * <p>Description: </p>  
 * @author ming.yi  
 * @date 2019年3月28日
 */
public class LsxExcelExportServlet extends HttpServlet{
	
	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;
	// 存放文件夹所在路径
	private String srcPath = this.getClass().getResource("/").getPath();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String beginDate=request.getParameter("beginDate");
		String endDate=request.getParameter("endDate");
		String type = request.getParameter("type");
		String cond = request.getParameter("cond");
		String path = null;
		if ("1".equals(type)) {
			String titleName = new IsHelpStatisticsController().expExcelIsHelpStatistics(cond);
			path = srcPath + titleName + ".xls";
		} else if("2".equals(type)) {
			String titleName = new IsHelpStatisticsController().expBnb_TongJiExcel(cond);
			path = srcPath + titleName + ".xls";
		}if ("3".equals(type)) {
			String titleName = new JiFenController().exportExcelJiFen(beginDate, endDate);
			path = srcPath + titleName + ".xls";
		} else if("4".equals(type)) {
			String titleName = new EvaluateController().exportExcelEvaluate(beginDate, endDate);
			path = srcPath + titleName + ".xls";
		}
		
		if (!"".equals(path) && path != null) {
			// 用户下载
			download(path, response);
			// 删除临时文件
			FilesUtil.deleteFile(path);
		}
		
	}

	/**
	 * 数据下载到本地
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
