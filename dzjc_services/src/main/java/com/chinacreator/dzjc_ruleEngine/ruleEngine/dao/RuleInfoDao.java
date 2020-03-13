package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao;

import java.util.List;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAll;

/**
 * @author created by 李浪
 * 2018-3-30 上午10:24:30
 */
public interface RuleInfoDao {

	List<EnginecfgBean> getCfgValueList();

	List<RuleBaseinfoBeanAll> getRuleBaseinfoBeanAllList(List<JcAllbussinesSum> list);

	List<RuleBaseinfoBeanAll> getRuleBaseinfoBeanConsultList(List<JcAllbussinesSum> list);
}
