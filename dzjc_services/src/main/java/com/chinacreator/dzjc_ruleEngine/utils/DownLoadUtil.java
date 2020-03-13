package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;


public class DownLoadUtil {

	/**
	 * 单据下载到本地
	 * 
	 * @param path 路径
	 * @param response
	 */
	public static void download(String path, HttpServletResponse response, HttpServletRequest request) {

		try {
			// path是指下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = "";
			if(request != null){
				filename = processFileName(request,file.getName());
			}else{
				filename = URLEncoder.encode(file.getName(), "UTF-8");
			}
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
	/**
	 * 单据下载到本地
	 * 
	 * @param path 路径
	 * @param response
	 */
	public static void download(String path, HttpServletResponse response) {
		download(path,response,null);
	}
	/**
	 * 解决设置名称时的乱码
	 * @param request
	 * @param fileNames
	 * @return
	 */
	public static String processFileName(HttpServletRequest request, String filename) {
		String codedfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("Firefox")) {
				//浏览器为火狐
				codedfilename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			}else{
				codedfilename = URLEncoder.encode(filename, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codedfilename;
	}
}
