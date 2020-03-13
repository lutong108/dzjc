package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao;

import java.util.List;
import com.chinacreator.dzjc_ruleEngine.RuleElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;



/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-1
 */
public interface RuleElementResultDao {

	
	/**
	 * 将监察要素结果信息删除
	 * @throws Exception
	 */
	public void deleteResultElement(List<JcAllbussinesSum> list) throws Exception ;
	
	/**
	 * 查找所有有效的监察要素基本信息
	 * @return list
	 * @throws Exception
	 */
	public List<RuleElement> findElementInfo() throws Exception ;
	
	/**
	 * 查找所有有效的监察要素基本信息
	 * @return list
	 * @throws Exception
	 */
	public List<RuleElement> findElementConsult() throws Exception ;
}
