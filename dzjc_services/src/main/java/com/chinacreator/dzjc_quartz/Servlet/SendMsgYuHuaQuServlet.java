package com.chinacreator.dzjc_quartz.Servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SendMsgServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.SendMsgYuHuaQuServiceImpl;

public class SendMsgYuHuaQuServlet implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("开始执行雨花区短信引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("雨花区短信引擎servlet处理开始......"+fat.format(new Date()));
		SendMsgServiceIfc sendMsgServiceIfc = new SendMsgYuHuaQuServiceImpl();
		
		sendMsgServiceIfc.doStartup();
		
		System.err.println("雨花区短信引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("雨花区短信执行完成");
		
	}

}
