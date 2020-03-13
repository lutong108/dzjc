package com.chinacreator.dzjc_quartz.Servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.StartupConsultEngineServiceImpl;

public class StartupConsultEngineServlet implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("开始执行咨询发牌规则引擎");
		System.err.println("咨询规则引擎servlet处理开始......"+fat.format(new Date()));
		//执行发牌
		StartupRuleEngineServiceIfc startupRuleEngineServiceIfc = new StartupConsultEngineServiceImpl();
		startupRuleEngineServiceIfc.doStartup();
		
		System.err.println("咨询规则引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("咨询发牌规则引擎执行完成");
	}

}
