package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;
/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author jun.zhang
 *@version 1.0
 *@date 2011-8-31
 */
public class RuleEngineInitAndTearDownBean {
	
	//TODO 属性值待添加
	
	private String isUseTimeLimit;
	
	private String areaCode;
	
	private String currentTime;

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getIsUseTimeLimit() {
		return isUseTimeLimit;
	}

	public void setIsUseTimeLimit(String isUseTimeLimit) {
		this.isUseTimeLimit = isUseTimeLimit;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	
}


