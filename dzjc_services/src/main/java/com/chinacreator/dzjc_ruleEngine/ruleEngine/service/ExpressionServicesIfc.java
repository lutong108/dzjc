package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ExpressionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;

/**
 *<p>Title:</p>
 *<p>Description:表达式运算</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public interface ExpressionServicesIfc {

    /**
     * 表达式计算
     * @param expreesionBean 表达式Bean
     * @param businessId 业务ID
     * @param ruleWithBusinessBean 准备数据参数
     * @return
     * @throws Exception
     */
	public boolean execute(ExpressionBean expreesionBean,String businessId,RuleWithBusinessBean ruleWithBusinessBean) throws Exception ;
	
	/**
	 * 获取超期天数
	 * @param expreesionBean
	 * @param businessId
	 * @param ruleWithBusinessBean
	 * @return
	 * @throws Exception
	 */
	public String getOutDays(ExpressionBean expreesionBean,String businessId,RuleWithBusinessBean ruleWithBusinessBean) throws Exception;
}
