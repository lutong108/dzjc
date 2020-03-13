package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao;

import java.util.Map;

import com.chinacreator.dzjc_ruleEngine.SuperviseInfo;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseDefinitionBean;


/**
 *<p>Title:</p>
 *<p>Description:监察信息处理</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public interface SuperviseInfoDao {

	/**
	 * 保存监察信息
	 * @param businessId
	 * @param ruleBean
	 * @throws Exception
	 */
	public SuperviserInfo insertSuperviseInfo(String businessId, String orgId, Map<String,Object> logMap, RuleBean ruleBean, SuperviseDefinitionBean superviseDefinitionBean) throws Exception ;
	/**
	 * 保存快照信息
	 * @param businessId
	 * @param superviseDefinitionBean
	 * @throws Exception
	 */
	public void insertKuaiZhao(String businessId,String superviseId, SuperviseDefinitionBean superviseDefinitionBean) throws Exception ;
	
	/**
	 * 更新快照办结时间信息
	 * @param 
	 * @throws Exception
	 */
	public void updateKuaiZhao() throws Exception ;
	
	/**
	 * 保存消息内容
	 * @param superviseId
	 * @param msgContent
	 * @return 
	 * @throws Exception
	 */
	public SuperviseInfo saveMsgContent(String superviseId, String msgContent) throws Exception ;
	
	/**
	 * 同一条业务，同一条规则，同一版本，不可监察2次
	 * @param businessId
	 * @param ruleId
	 * @param ruleVersion
	 * @return
	 */
	public boolean isSuperviseBefore(String businessId, String ruleId,String superviseTypeNo) throws Exception ;
	
	/**  
	 * 得到单位代码和单位名称
	 * @param businessId
	 * @param superviseTypeCode
	 * @throws Exception
	 */
	public String getOrgIdAndOrgName(String businessId,String superviseTypeCode) throws Exception ;
	
	/**  
	 * 判断规则引擎执行当天区域是否完成了交换
	 * @param area_code
	 * @throws Exception
	 */
	public boolean isChangeDate(String area_code) throws Exception ;
}
