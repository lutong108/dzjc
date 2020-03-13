package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;

import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;

/**
 * @author created by liaolong
 * 2019-1-14 
 */
public interface HuanJieInfoServiceIfc {

	RuleWithBusinessBean getRuleWithBusinessBeanList(List<ResultOpinion> list);

}
