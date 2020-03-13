package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao;

import java.util.Map;


/**
 *<p>Title:</p>
 *<p>Description:监察消息发送数据接口</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public interface MessageInfoDao {
	
	
	/**
	 * 根据事项ID获取消息通知用户列表
	 * @param approveId
	 * @return
	 * @throws Exception
	 */
	//public List findMessageUserByApprove(String approveId) throws Exception ;
	
	/**
	 * 根据监察级别和事项配置加载用户
	 * 1、如果是预警，则只加载预警配置用户
	 * 2、如果是黄牌，则加载黄牌配置用户与预警配置用户
	 * 3、如果是红牌，则加载预警、黄牌和红牌配置用户
	 * @param superviseLevel
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	//public List<MessageUserBean> findCfgUserList(String superviseLevel, String businessId) throws Exception ;
	
	/**
	 * 发送消息
	 * 参数：
	 * businessId：业务ID  根据业务ID获取消息通知配置信息
	 * templateId：模板ID  根据模板ID与业务ID获取消息内容
	 * superviseId：监察信息ID 根据监察信息ID将信息更新到监察信息表
	 * superviseLevel：预警级别  为红牌时要同时加载黄牌、预警通知人员，为黄牌时同时加载预警通知人员
	 * @param paraMap
	 * @return msgContent
	 * @throws Exception
	 */
	public String sendMessage(Map paraMap) throws Exception;
}
