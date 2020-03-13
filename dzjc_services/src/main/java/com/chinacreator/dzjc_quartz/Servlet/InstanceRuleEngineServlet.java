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

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.InstancesRuleEngineServiceImpl;

public class InstanceRuleEngineServlet extends HttpServlet implements Job{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4945580008269948794L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("开始计算办件所用时长");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("servlet处理开始......"+fat.format(new Date()));
		StartupRuleEngineServiceIfc ir = new InstancesRuleEngineServiceImpl();
		ir.doStartup();
		System.err.println("servlet处理结束......"+fat.format(new Date()));
        System.out.println("执行完成");
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("开始计算办件所用时长");
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("servlet处理开始......" + fat.format(new Date()));
		StartupRuleEngineServiceIfc ir = new InstancesRuleEngineServiceImpl();
		ir.doStartup();
		System.err.println("servlet处理结束......" + fat.format(new Date()));
		System.out.println("执行完成");
	}

}
