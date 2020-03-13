package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao;

import java.util.List;

import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAll;

/**
 * @author created by liaolong
 * 2019-1-14 
 */
public interface HuanJieInfoDao {

	List<RuleBaseinfoBeanAll> getRuleBaseinfoBeanAllList(List<ResultOpinion> list);

}
