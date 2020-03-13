package com.chinacreator.dzjc_quartz.web.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.BeforeRuleEngineServiceImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.StartupRuleEngineServiceImpl;

@Controller
@Path("quartzurl/")
public class QuartzUrlController {

	@Path("doSupervise")
	public void doSupervise(){
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//先处理即办件改成承诺件的数据
		StartupRuleEngineServiceIfc beforeRuleEngineServiceIfc = new BeforeRuleEngineServiceImpl();
		beforeRuleEngineServiceIfc.doStartup();
		
		System.out.println("开始执行发牌规则引擎");
		System.err.println("规则引擎servlet处理开始......"+fat.format(new Date()));
		//执行发牌
		StartupRuleEngineServiceIfc startupRuleEngineServiceIfc = new StartupRuleEngineServiceImpl();
		startupRuleEngineServiceIfc.doStartup();
		
		System.err.println("规则引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("发牌规则引擎执行完成");
	}
}
