package com.chinacreator.dzjc_log.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_log.ConsoleLog;

/**@Title	: 保存日志方法
 * @Author	: zouhailin
 * @Date	: 2018-7-16
 */
public class ConsoleLogUtil {
	private static Logger log = Logger.getLogger(ConsoleLogUtil.class);
	
	public static void insertConsoleLog(String content){
		insertConsoleLog("",content);
	}
	public static void insertConsoleLog(String title,String content){
		insertConsoleLog(title, content, "");
	}
	public static void insertConsoleLog(String title,String content,String type){
		ConsoleLog consoleLog = new ConsoleLog();
		if(StringUtils.isNotBlank(title)){
			consoleLog.setLogTitle(title);
		}
		if(StringUtils.isNotBlank(content)){
			consoleLog.setLogContent(content);
		}
		if(StringUtils.isNotBlank(type)){
			consoleLog.setLogType(type);
		}
		try {
			DaoFactory.create(ConsoleLog.class).getSession().insert("com.chinacreator.dzjc_log.ConsoleLogMapper.insert",consoleLog);
		} catch (Exception e) {
			log.error("自定义日志错误");
		}
	}
}
