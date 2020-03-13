package com.chinacreator.dzjc_complain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinacreator.util.FtpUtils;

public class FtpUploadProcess implements Runnable {

	/** 日志对象 **/
    private static final Logger LOGGER = LoggerFactory.getLogger(FtpUploadProcess.class);
	
	private String dzjcPath;
	private String savePath;
	private String today;
	private List<String> srcList;
	@Override
	public void run() {
		boolean result = false;
		String dateStr = "";
		String fileName = "";
		for(String src : srcList){
			dateStr = src.substring(src.indexOf(dzjcPath)+dzjcPath.length(), src.indexOf(dzjcPath)+dzjcPath.length()+8);
			fileName = src.substring(src.indexOf(dateStr)+dateStr.length()+1, src.length());
			//上传到ftp服务器
			result = FtpUtils.uploadLocalFile(today, savePath+"/"+dateStr+"/"+fileName, fileName);
			if(result){
				LOGGER.error(today+"/"+fileName+"上传到FTP服务器成功");
			}
			else {
				LOGGER.error(today+"/"+fileName+"上传到FTP服务器失败");
			}
		}
	}
	public String getDzjcPath() {
		return dzjcPath;
	}
	public void setDzjcPath(String dzjcPath) {
		this.dzjcPath = dzjcPath;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public List<String> getSrcList() {
		return srcList;
	}
	public void setSrcList(List<String> srcList) {
		this.srcList = srcList;
	}
}
