package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao;

import java.util.List;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;


/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-1
 */
public interface ElementInfoDao {

	void updUsedTime(String instanceId, String valueOf) throws Exception;
	
	void batchUpdateUsedTime(List<InstanceInfo> list) throws Exception;

	List<InstanceInfo> getInstanceBegEndTime();
	
	List<InstanceInfo> getInstanceBegEndTimeByInstanceId(List<String> listInstanceId);

	List<TbcxsqInfoBean> getTbcxsqInfo(String instanceId);

	String getTbcxjgEndDate(String tscxsqId, String instanceId);

	List<InstanceInfo> getInternetBegEndTimeByInstanceId(List<String> listInstanceId);
	
	List<InstanceInfo> getDzzzBegEndTimeByInstanceId(List<String> listInstanceId);

	void batchUpdateCompletedTime(List<InstanceInfo> list) throws Exception;
	
	List<TbcxsqInfoBean> queryTbcxsqlist(List<String> listInstanceId);
	
	List<TbcxsqInfoBean> queryHuanJieTbcxsqlist(List<String> listHuanJieId);
}
