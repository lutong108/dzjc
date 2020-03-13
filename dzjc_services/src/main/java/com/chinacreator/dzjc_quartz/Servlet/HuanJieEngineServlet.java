package com.chinacreator.dzjc_quartz.Servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.BeforeHuanJieEngineServiceImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.HuanJieEngineServiceImpl;

public class HuanJieEngineServlet implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
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

}
