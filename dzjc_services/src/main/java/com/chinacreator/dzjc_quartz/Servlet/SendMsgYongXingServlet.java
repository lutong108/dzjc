package com.chinacreator.dzjc_quartz.Servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.CheckSuperviseEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SendMsgServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.CheckSuperviseEngineServiceImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.SendMsgYongXingServiceImpl;

public class SendMsgYongXingServlet extends HttpServlet implements Job{
	//com.chinacreator.dzjc_quartz.Servlet.SendMsgYongXingServlet
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response){
		System.out.println("开始执行永兴短信引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("永兴短信引擎servlet处理开始......"+fat.format(new Date()));
		SendMsgServiceIfc sendMsgServiceIfc = new SendMsgYongXingServiceImpl();
		
		sendMsgServiceIfc.doStartup();
		
		System.err.println("永兴短信引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("永兴短信执行完成");
	}
	
	public void doPost(HttpServletRequest  request, HttpServletResponse response){
		doGet(request, response);
	}
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("开始执行永兴短信引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("永兴短信引擎servlet处理开始......"+fat.format(new Date()));
		SendMsgServiceIfc sendMsgServiceIfc = new SendMsgYongXingServiceImpl();
		
		sendMsgServiceIfc.doStartup();
		
		System.err.println("永兴短信引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("永兴短信执行完成");
	}
	
}
