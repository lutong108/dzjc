package com.chinacreator.dzjc_ruleEngine.ruleEngine.utils;


import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.HuanJieEngineCaculatorServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineCaculatorServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineInitAndTearDownServiceIfc;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author jun.zhang
 *@version 1.0
 *@date 2011-8-31
 */
public class RuleEngineInterfaceReflectUtil {
	
	
	/**
	 *  规则引擎监听加载
	 * @param className
	 * @return
	 */
	public RuleEngineInitAndTearDownServiceIfc getRuleEngineInitAndTearDownService(String className) throws Exception {
		//类的反射过程
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		if(!(obj instanceof RuleEngineInitAndTearDownServiceIfc)){
			System.err.println("规则引擎监听加载失败：类型错误");
			//LOG.info("规则引擎监听加载失败：类型错误");
			throw new Exception("规则引擎监听加载失败：类型错误");
		}
		return (RuleEngineInitAndTearDownServiceIfc)obj;
	}
	
	/**
	 * 监察要素计算逻辑加载
	 * @param className
	 * @return
	 */
	public RuleEngineCaculatorServiceIfc getRuleEngineCaculatorService(String className) throws Exception {
		//类的反射过程
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		if(!(obj instanceof RuleEngineCaculatorServiceIfc)){
			//LOG.error("[监察要素计算逻辑加载]className:"+className+" 不是RuleEngineCaculatorServiceIfc类型");
			System.err.println("[监察要素计算逻辑加载]className:"+className+" 不是RuleEngineCaculatorServiceIfc类型");
			throw new Exception("初始化失败：类型错误");
		}
		return (RuleEngineCaculatorServiceIfc)obj;
	}
	
	/**
	 * 监察要素计算逻辑加载
	 * @param className
	 * @return
	 */
	public HuanJieEngineCaculatorServiceIfc getHuanJieEngineCaculatorService(String className) throws Exception {
		//类的反射过程
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		if(!(obj instanceof HuanJieEngineCaculatorServiceIfc)){
			//LOG.error("[监察要素计算逻辑加载]className:"+className+" 不是RuleEngineCaculatorServiceIfc类型");
			System.err.println("[环节监察要素计算逻辑加载]className:"+className+" 不是HuanJieEngineCaculatorServiceIfc类型");
			throw new Exception("初始化失败：类型错误");
		}
		return (HuanJieEngineCaculatorServiceIfc)obj;
	}
}