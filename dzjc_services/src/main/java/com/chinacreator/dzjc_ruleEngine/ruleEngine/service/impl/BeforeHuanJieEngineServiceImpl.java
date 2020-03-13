package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.HuanJieSuperviseInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;

/**
 * 
 * @author liaolong
 * @date 2019-1-22 
 */
public class BeforeHuanJieEngineServiceImpl implements
		StartupRuleEngineServiceIfc {

	@Override
	public void doStartup() {
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//将发牌时环节为待办状态，此时已办理完成的发牌中，发牌的单位是最终处理的单位的数据改成已办状态
		System.err.println("环节监察前处理，修改状态开始："+fat.format(new Date()));
		DaoFactory.create(HuanJieSuperviseInfo.class).getSession()
			.update("com.chinacreator.dzjc_ruleEngine.HuanJieSuperviseInfoMapper.batchUpdateStatus");
		System.err.println("环节监察前处理，修改状态结束："+fat.format(new Date()));
		
		//将发牌时环节为待办状态，此时已办理完成的发牌中，发牌的单位不是最终处理的单位的数据删除
		System.err.println("环节监察前处理，删除状态开始："+fat.format(new Date()));
		DaoFactory.create(HuanJieSuperviseInfo.class).getSession()
			.update("com.chinacreator.dzjc_ruleEngine.HuanJieSuperviseInfoMapper.batchDeleteStatus");
		System.err.println("环节监察前处理，删除状态结束："+fat.format(new Date()));
	}
}
