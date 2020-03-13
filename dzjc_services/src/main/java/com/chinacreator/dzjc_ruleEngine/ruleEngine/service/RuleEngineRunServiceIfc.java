package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author jun.zhang
 *@version 1.0
 *@date 2011-8-31
 */
public interface RuleEngineRunServiceIfc {
	
	/**
	 * 传入的参数具体进行调整
	 */
	public void doRun(RuleBean ruleBean,List<String> businessIdList
			,RuleWithBusinessBean ruleWithBusinessBean,String feedbackDate) throws Exception;
	
	/**
	 * 传入的参数具体进行调整
	 */
	public void doRunConsult(RuleBean ruleBean,List<String> businessIdList
			,RuleWithBusinessBean ruleWithBusinessBean,String feedbackDate) throws Exception;
	
	public void doRun2(RuleBean ruleBean,List<String> businessIdList,
			RuleWithBusinessBean ruleWithBusinessBean,
			ServletContext application) throws Exception;
	
}


