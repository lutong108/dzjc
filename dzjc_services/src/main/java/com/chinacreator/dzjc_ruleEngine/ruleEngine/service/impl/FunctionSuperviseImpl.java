package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;


import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.FunctionSuperviseIfc;

/**
 * @author created by 李浪
 * 2018-3-30 上午11:17:16
 */
public class FunctionSuperviseImpl implements FunctionSuperviseIfc {

	@Override
	public void insertFunctionSupervise(Sp_status stauts) {
		
		DaoFactory.create(Sp_status.class).insert(stauts);
		
			
	}

}
