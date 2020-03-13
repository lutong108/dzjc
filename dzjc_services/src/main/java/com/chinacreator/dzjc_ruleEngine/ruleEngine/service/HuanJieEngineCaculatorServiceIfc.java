package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;

import com.chinacreator.dzjc_ruleEngine.ResultOpinion;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author jun.zhang
 *@version 1.0
 *@date 2011-8-31
 */
public interface HuanJieEngineCaculatorServiceIfc {
	
	/**
	 * 具体数据准备方法的接口
	 *TODO 请完善
	 * @param elementId
	 * @param areaCode
	 */
	public String doCaculator(String elementId,String batchId,List<ResultOpinion> list) throws Exception;
}


