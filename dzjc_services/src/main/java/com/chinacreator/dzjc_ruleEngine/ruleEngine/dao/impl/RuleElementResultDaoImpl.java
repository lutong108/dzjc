package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl;

import java.util.List;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.RuleElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.RuleElementResultDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;

/**
 * @author created by 李浪
 * 2018-4-3 上午10:30:46
 */
public class RuleElementResultDaoImpl implements RuleElementResultDao {

	@Override
	public void deleteResultElement(List<JcAllbussinesSum> list) throws Exception {

		//删除所有的监察结果信息
		for (JcAllbussinesSum jcAllbussinesSum : list) {
			String businessId=jcAllbussinesSum.getBussinessId();
			if(businessId!=null && !"".equals(businessId) && !"null".equals(businessId)){
				DaoFactory.create(ResultElement.class).getSession().delete("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.deleteBybusinessId",businessId);
			}
		}
		
		
	}

	@Override
	public List<RuleElement> findElementInfo() throws Exception {
		return DaoFactory.create(RuleElement.class).selectAll();
	}

	@Override
	public List<RuleElement> findElementConsult() throws Exception {
		return DaoFactory.create(RuleElement.class).getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.RuleElementMapper.selectConsult");
	}
}
