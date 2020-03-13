package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleEngineInitAndTearDownBean;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author jun.zhang
 *@version 1.0
 *@date 2011-8-31
 */
public interface RuleEngineInitAndTearDownServiceIfc {
	
	/**
	 * 规则引擎init时需要执行的接口
	 * @param ruleEngineInitAndTearDownBean
	 */
	public String start(RuleEngineInitAndTearDownBean ruleEngineInitAndTearDownBean,List<JcAllbussinesSum> list1) throws Exception;
	
	/**
	 * 规则引擎完成计算后，需要执行的后续方法
	 * @param ruleEngineInitAndTearDownBean
	 */
	public void clear(RuleEngineInitAndTearDownBean ruleEngineInitAndTearDownBean) throws Exception;
}


