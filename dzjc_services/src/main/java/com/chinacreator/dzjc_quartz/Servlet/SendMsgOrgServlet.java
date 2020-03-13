package com.chinacreator.dzjc_quartz.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SendMsgServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.SendMsgOrgServiceImpl;

/**
 * 岳阳市电子监察增加短信提醒功能
 * @author chenlong.fan
 *
 */
public class SendMsgOrgServlet extends HttpServlet implements Job{
	
	private static final long serialVersionUID = -1L;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("开始执行岳阳短信引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("岳阳短信引擎servlet处理开始......"+fat.format(new Date()));
		SendMsgServiceIfc sendMsgServiceIfc = new SendMsgOrgServiceImpl();
		
		sendMsgServiceIfc.doStartup();
		
		System.err.println("岳阳短信引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("岳阳短信执行完成");
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("开始执行岳阳短信引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("岳阳短信引擎servlet处理开始......"+fat.format(new Date()));
		SendMsgServiceIfc sendMsgServiceIfc = new SendMsgOrgServiceImpl();
		
		sendMsgServiceIfc.doStartup();
		
		System.err.println("岳阳短信引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("岳阳短信执行完成");
	}

}
