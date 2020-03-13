package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;

/**
 * 
 * @author liaolong
 * @date 2019-1-18 
 */
public class BeforeRuleEngineServiceImpl implements
		StartupRuleEngineServiceIfc {

	
	@Override
	public void doStartup() {
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		getMemInfo("开始执行发牌前处理："+fat.format(new Date()));
		//把从即办件修改成承诺件，并且事项表里是否监察标志=N，
		//的所有在事项的最近修改时间前受理的办件，修改成不需要监察
		DaoFactory.create(InstanceInfo.class).getSession()
			.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.updateBatchIsNeedSupervise");
		
		//将事项表里，即办件的事项的is_jc字段修改成N
		DaoFactory.create(InstanceInfo.class).getSession()
			.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.updateBatchIsJc1");
		
		//将事项表里，非即办件的事项的is_jc字段修改成Y
		DaoFactory.create(InstanceInfo.class).getSession()
			.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.updateBatchIsJc2");
		
		getMemInfo("发牌前处理结束："+fat.format(new Date()));
	}

	public static void getMemInfo(String title){
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	    MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
	    System.out.println(title+"，初始化内存=="+(memoryUsage.getInit()/1024/1024)+"，"
	    		+"最大可用内存=="+(memoryUsage.getMax()/1024/1024)+"，"
	    		+"已用内存=="+(memoryUsage.getUsed()/1024/1024));
    }
}
