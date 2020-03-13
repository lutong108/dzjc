package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.HuanJieInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAll;

/**
 * @author created by liaolong 2019-1-14 
 */
public class HuanJieInfoDaoImpl implements HuanJieInfoDao {


	@Override
	public List<RuleBaseinfoBeanAll> getRuleBaseinfoBeanAllList(List<ResultOpinion> list) {
		List<RuleBaseinfoBeanAll> beanList = new ArrayList<RuleBaseinfoBeanAll>();
		//环节的opinionIdList
		List<String> opinionIdList = new ArrayList<String>();
		for (ResultOpinion resultOpinion : list) {
			String bussinessId = resultOpinion.getOpinionId();
			if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
				opinionIdList.add(bussinessId);
			}
		}
		//查询环节信息
		if(opinionIdList.size() > 0){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectByHuanJieID", opinionIdList);
		}
		return beanList;
	}

}
