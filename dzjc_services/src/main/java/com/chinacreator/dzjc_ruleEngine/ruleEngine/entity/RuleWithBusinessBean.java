package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import java.util.List;
import java.util.Map;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author jun.zhang
 *@version 1.0
 *@date 2011-8-31
 */
public class RuleWithBusinessBean {
	
	//TODO 注释请添加  元素自定义
	
	//规则信息
	 Map<String,RuleBean> ruleIdAndRuleBeanMap ;

	//Map<规则信息Bean,务ID>
	private Map<String,List<String>> ruleBeanAndBusinessIdList ;
	
	//表达式信息（Map<String[:rule_id],List<ExpressionBean>>）
	private Map<String,List<ExpressionBean>> ruleIdAndExpressionBeanList;
	
	//表达式片段信息（Map<String[:exp_id],List<ExpressionLetBean>>）
	private Map<String,List<ExpressionLetBean>> expIdAndExpressionLetBeanList;
	
	//规则定义信息（Map<String[:exp_id],SuperviseDefinitionBean>）
	private Map<String,SuperviseDefinitionBean> expIdAndSuperviseDefinitionBean;
	
	private Map<String, String> bussinessTypeMap;
	
	private Map<String, String> orgIdMap;
	
	private Map<String, Map<String, Object>> businessLogMap;

	public Map<String, RuleBean> getRuleIdAndRuleBeanMap() {
		return ruleIdAndRuleBeanMap;
	}

	public void setRuleIdAndRuleBeanMap(Map<String, RuleBean> ruleIdAndRuleBeanMap) {
		this.ruleIdAndRuleBeanMap = ruleIdAndRuleBeanMap;
	}

	public Map<String, List<String>> getRuleBeanAndBusinessIdList() {
		return ruleBeanAndBusinessIdList;
	}

	public void setRuleBeanAndBusinessIdList(
			Map<String, List<String>> ruleBeanAndBusinessIdList) {
		this.ruleBeanAndBusinessIdList = ruleBeanAndBusinessIdList;
	}

	public Map<String, List<ExpressionBean>> getRuleIdAndExpressionBeanList() {
		return ruleIdAndExpressionBeanList;
	}

	public void setRuleIdAndExpressionBeanList(
			Map<String, List<ExpressionBean>> ruleIdAndExpressionBeanList) {
		this.ruleIdAndExpressionBeanList = ruleIdAndExpressionBeanList;
	}

	public Map<String, List<ExpressionLetBean>> getExpIdAndExpressionLetBeanList() {
		return expIdAndExpressionLetBeanList;
	}

	public void setExpIdAndExpressionLetBeanList(
			Map<String, List<ExpressionLetBean>> expIdAndExpressionLetBeanList) {
		this.expIdAndExpressionLetBeanList = expIdAndExpressionLetBeanList;
	}

	public Map<String, SuperviseDefinitionBean> getExpIdAndSuperviseDefinitionBean() {
		return expIdAndSuperviseDefinitionBean;
	}

	public void setExpIdAndSuperviseDefinitionBean(
			Map<String, SuperviseDefinitionBean> expIdAndSuperviseDefinitionBean) {
		this.expIdAndSuperviseDefinitionBean = expIdAndSuperviseDefinitionBean;
	}

	public Map<String, String> getBussinessTypeMap() {
		return bussinessTypeMap;
	}

	public void setBussinessTypeMap(Map<String, String> bussinessTypeMap) {
		this.bussinessTypeMap = bussinessTypeMap;
	}

	public Map<String, String> getOrgIdMap() {
		return orgIdMap;
	}

	public void setOrgIdMap(Map<String, String> orgIdMap) {
		this.orgIdMap = orgIdMap;
	}

	public Map<String, Map<String, Object>> getBusinessLogMap() {
		return businessLogMap;
	}

	public void setBusinessLogMap(Map<String, Map<String, Object>> businessLogMap) {
		this.businessLogMap = businessLogMap;
	}
	
}


