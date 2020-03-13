package com.chinacreator.dzjc_ruleEngine.ruleEngine.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.BeforeHuanJieEngineServiceImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.HuanJieEngineServiceImpl;

public class HuanJieHandEngineServlet extends HttpServlet {

	/**
	 * 规则引擎servlet启动类
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("开始执行环节监察前处理");
		StartupRuleEngineServiceIfc beforeRuleEngineServiceIfc = new BeforeHuanJieEngineServiceImpl();
		beforeRuleEngineServiceIfc.doStartup();
		System.out.println("结束执行环节监察前处理");
		
		System.out.println("开始执行环节监察规则引擎");
		System.err.println("环节监察规则引擎servlet处理开始......"+fat.format(new Date()));
		StartupRuleEngineServiceIfc startupRuleEngineServiceIfc = new HuanJieEngineServiceImpl();
		
		startupRuleEngineServiceIfc.doStartup();
		
		System.err.println("环节监察规则引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("环节监察执行完成");
	}
	
	public void doPost(HttpServletRequest  request, HttpServletResponse response){
		doGet(request, response);
	}
	
}
