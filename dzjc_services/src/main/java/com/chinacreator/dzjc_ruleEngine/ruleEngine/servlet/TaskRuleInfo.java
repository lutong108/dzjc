package com.chinacreator.dzjc_ruleEngine.ruleEngine.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.StartupRuleEngineServiceImpl;

@Component
public class TaskRuleInfo extends HttpServlet{
	
	
	
    public void testJob(){
        System.out.println("开始执行发牌规则引擎");
        SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println("规则引擎servlet处理开始......"+fat.format(new Date()));
		StartupRuleEngineServiceIfc startupRuleEngineServiceIfc = new StartupRuleEngineServiceImpl();
		
		startupRuleEngineServiceIfc.doStartup();
		
		
		System.err.println("规则引擎servlet处理结束......"+fat.format(new Date()));
        System.out.println("执行完成");
    }
    
    
    public void insertCountSupervise(){
    	System.out.println("执行存储过程");
    	try {
    		DaoFactory
    		.create(JCSumSuperviseInfoAll.class)
    		.getSession()
    		.insert("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.INSERTTAJCCOUNTSUPERVISEINFO");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    public void insertSumSupervise(){
    	System.out.println("执行存储过程");
    	try {
    		DaoFactory
    		.create(JCSumSuperviseInfoAll.class)
    		.getSession()
    		.insert("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.INSERTTAJCSUMSUPERVISEINFO");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

}
