package com.chinacreator.dzjc_ruleEngine.ruleEngine.service;

import java.util.List;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;

/**
 * @author created by 李浪
 * 2018-3-30 上午10:39:37
 */
public interface RuleInfoServiceIfc {

	List<EnginecfgBean> getCfgValueList();

	RuleWithBusinessBean getRuleWithBusinessBeanList(List<JcAllbussinesSum> list);

	RuleWithBusinessBean getRuleWithBusinessConsultList(List<JcAllbussinesSum> list);
}
