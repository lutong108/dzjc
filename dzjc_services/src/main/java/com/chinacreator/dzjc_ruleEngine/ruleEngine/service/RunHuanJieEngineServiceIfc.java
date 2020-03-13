package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.chinacreator.dzjc_ruleEngine.ResultOpinion;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author liaolong
 *@version 1.0
 *@date 2019-1-14
 */
public interface RunHuanJieEngineServiceIfc {
	
	
	/**
	 * 计算环节所用时长
	 * 
	 */
	public String ruleCaculate(List<ResultOpinion> list,String batchId) throws Exception;
	
	
	/**
	 * 
	 * 环节发牌处理
	 */
	public void ruleRun(List<ResultOpinion> list,ServletContext application) throws Exception;
	
}


