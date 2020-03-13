package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.caculator;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineCaculatorServiceIfc;

public class InternetRoomTimeExpressionCaculator implements RuleEngineCaculatorServiceIfc{
	
	/**
	 * 网厅时限监察
	 * 算法：
	 * 1、对于在办办件，以系统当前时间减受理时间，计算实际耗时
	 * 2、针对正常办结了的办件（一般办结、正常办结、上报办结、转报办结），以办结时间减去受理时间，计算实际耗时
	 * 3、计算实际耗时时，必须去掉节假日、特别程序时间
	 * @param elemId 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String doCaculator(String elemId,String batchId,List<JcAllbussinesSum> list1) throws Exception {
		StringBuilder exceptions = new StringBuilder("");
		
		for (JcAllbussinesSum jcAllbussinesSum : list1) {
			Map<String,String> map=new HashedMap();
			map.put("elemId", elemId);
			map.put("batchId", batchId);
			String instanceId=jcAllbussinesSum.getBussinessId();
			String bussinessType=jcAllbussinesSum.getBussinessType();
			map.put("instanceId", instanceId);
			if("1".equals(bussinessType)&&instanceId!=null && !"".equals(instanceId)&&!"null".equals(instanceId)){
				DaoFactory.create(ResultElement.class).getSession().insert("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.insertInternetRoomTime",map);
			}
		}
		//DaoFactory.create(ResultElement.class).getSession().insert("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.insertInternetRoomTime",elemId);
		return exceptions.toString();
	} 

}
