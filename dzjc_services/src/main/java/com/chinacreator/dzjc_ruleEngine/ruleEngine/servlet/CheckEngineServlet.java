package com.chinacreator.dzjc_ruleEngine.ruleEngine.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.CheckSuperviseEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.CheckSuperviseEngineServiceImpl;

public class CheckEngineServlet extends HttpServlet {

	/**
	 * 规则引擎servlet启动类
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		System.out.println("开始执行发牌巡检规则引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("发牌巡检规则引擎servlet处理开始......"+fat.format(new Date()));
		CheckSuperviseEngineServiceIfc checkSuperviseEngineServiceIfc = new CheckSuperviseEngineServiceImpl();
		
		checkSuperviseEngineServiceIfc.doStartup();
		
		System.err.println("发牌巡检规则引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("发牌巡检执行完成");
	}
	
	public void doPost(HttpServletRequest  request, HttpServletResponse response){
		doGet(request, response);
	}
	
}
