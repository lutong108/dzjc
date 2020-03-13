package com.chinacreator.dzjc_ruleEngine.ruleEngine.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_quartz.Servlet.SumSuperviseServlet;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.StartupRuleEngineServiceImpl;

public class SumServlet extends HttpServlet {

	/**
	 * 规则引擎servlet启动类
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("统计servlet处理开始......"+fat.format(new Date()));
		SumSuperviseServlet t = new SumSuperviseServlet();
		try {
			t.execute(null);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
		
		
		System.err.println("统计servlet处理结束......"+fat.format(new Date()));
		
		
		
		
		
		
	}
	
	public void doPost(HttpServletRequest  request, HttpServletResponse response){
		doGet(request, response);
	}
	
}
