package com.chinacreator.dzjc_quartz.Servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.CheckSuperviseEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.CheckSuperviseEngineServiceImpl;

public class CheckSuperviseEngineServlet implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("开始执行发牌巡检规则引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("发牌巡检规则引擎servlet处理开始......"+fat.format(new Date()));
		CheckSuperviseEngineServiceIfc checkSuperviseEngineServiceIfc = new CheckSuperviseEngineServiceImpl();
		
		checkSuperviseEngineServiceIfc.doStartup();
		
		System.err.println("发牌巡检规则引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("发牌巡检执行完成");
		
	}

}
