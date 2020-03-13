package com.chinacreator.dzjc_quartz.Servlet;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_log.util.ConsoleLogUtil;
import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;
import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;

public class CountSuperviseServlet implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		System.out.println("执行INSERTTAJCCOUNTSUPERVISEINFO存储过程");
		Date beginDate = new Date();
		try {
			//ConsoleLogUtil.insertConsoleLog("执行count存储过程开始","执行时间："+DateUtil.getFormatDateTime(beginDate, "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	try {
    		DaoFactory
    		.create(JCSumSuperviseInfoAll.class)
    		.getSession()
    		.insert("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.INSERTTAJCCOUNTSUPERVISEINFO");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	Date endDate = new Date();
    	try {
    		String shichang = DateUtil.getDatePoor(endDate, beginDate);
    		//ConsoleLogUtil.insertConsoleLog("执行count存储过程结束","完成时间："+DateUtil.getFormatDateTime(endDate, "yyyy-MM-dd HH:mm:ss"+" 所用时长："+shichang));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
