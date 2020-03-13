package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;
import java.util.Map;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;


/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author jun.zhang
 *@version 1.0
 *@date 2011-8-31
 */
public interface RunRuleEngineServiceIfc {
	
	
	/**
	 * 步骤一
	 * 读取规则引擎启动使用的配置信息
	 * 具体为  ta_rule_enginecfg 表的数据
	 * key 值为 init  或者 end 
	 * @return
	 */
	public Map<String, List<EnginecfgBean>> loadConfigInfo();
	/**
	 * 步骤二
	 * 规则引擎启动后执行的第一个步骤，开始数据的初始化
	 * 具体针对时限已用时限的数据进行更新
	 * 逻辑过程为：
	 * 1.
	 * @param areaCode
	 * @return
	 */
	public String ruleInit(List<JcAllbussinesSum> list1,List<EnginecfgBean> list) throws Exception;
	
	/**
	 * 步骤三
	 * 针对 @see RunRuleEngineServiceIfc.ruleInit 方法中完成初始化的数据 ，对需要进行监察运行的数据进行准备
	 * 逻辑过程为:
	 * 
	 * @param areaCode
	 */
	public String ruleCaculate(List<JcAllbussinesSum> list,String batchId) throws Exception;
	
	/**
	 * 步骤三
	 * 针对 @see RunRuleEngineServiceIfc.ruleInit 方法中完成初始化的数据 ，对需要进行监察运行的数据进行准备
	 * 逻辑过程为:
	 * 
	 * @param areaCode
	 */
	public String ruleCaculateConsult(List<JcAllbussinesSum> list,String batchId) throws Exception;
	
	
	/**
	 * 步骤四
	 * 具体规则对 ruleCaculate 中准备的规则数据  进行逻辑运行 ，得出具体的发牌数据
	 * 逻辑过程为:
	 * 
	 * @param areaCode
	 */
	public void ruleRun(List<JcAllbussinesSum> list, String feedbackDate) throws Exception;
	
	/**
	 * 步骤四
	 * 具体规则对 ruleCaculate 中准备的规则数据  进行逻辑运行 ，得出具体的发牌数据
	 * 逻辑过程为:
	 * 
	 * @param areaCode
	 */
	public void ruleRunConsult(List<JcAllbussinesSum> list, String feedbackDate) throws Exception;
	
	/**
	 * 步骤五
	 * 完成步骤四之后，后续的数据处理过程
	 * @param areaCode
	 */
	public void ruleTearDown(String currentTime, List<EnginecfgBean> list) throws Exception;
}


