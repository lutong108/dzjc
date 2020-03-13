package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 *<p>Title:</p>
 *<p>Description:监察信息业务处理</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public interface HuanJieSuperviseInfoServiceIfc {

	/**
	 * 预警处理
	 * 参数：
	 * businessId：业务ID  根据业务ID获取消息通知配置信息
	 * ruleBean：规则基本信息 包括预警级别、监察类型、发牌方式、消息模板等
	 * @param paraMap
	 * @return superviseId
	 * @throws Exception
	 */
	public String doSupervise(List<Map<String,Object>> list,ServletContext application) throws Exception ;
}
