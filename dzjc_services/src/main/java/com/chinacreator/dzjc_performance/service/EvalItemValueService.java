package com.chinacreator.dzjc_performance.service;

import org.springframework.stereotype.Service;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_performance.EvalItemValue;

/**
 * 测评成绩的分数service
 * @author zyz818
 *
 */

@Service
public class EvalItemValueService {
	
	/**
	 * 根据测评实例id，删除已经上报了的测评成绩
	 */
	public void deleteItemValue(String instanceId){
		DaoFactory.create(EvalItemValue.class).getSession()
			.delete("com.chinacreator.dzjc_performance.EvalItemValueMapper.deleteItemValue", instanceId);
	}
}
