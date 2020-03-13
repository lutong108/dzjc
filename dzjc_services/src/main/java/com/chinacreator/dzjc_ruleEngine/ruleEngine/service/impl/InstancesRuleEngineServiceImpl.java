package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_config;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.FunctionSuperviseIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.util.HolidayDateUtil;
import com.chinacreator.util.StringUtil;

/**
 * 办件所用时长service实现类
 * @author zyz818
 * @date 2019-2-19
 */
public class InstancesRuleEngineServiceImpl implements StartupRuleEngineServiceIfc{
	
	/**
	 *  每次处理条数
	 */
	private int superviseCount;
	
	@Override
	public void doStartup() {
		initThreadConfig();
		//本次定时任务的批次ID
		String batchId = StringUtil.getUUID2();
		List<InstanceInfo> list = null;
		int index = 1;
		int total = 0;
		do {
			list = DaoFactory.create(InstanceInfo.class).getSession()
						.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectByAtOnceInstance", superviseCount);
			total += list.size();
			if (list.size() > 0) {
				runSingleAreaRuleEngine(list,index,batchId);
				index++;
			}
		} while (list.size() > 0);
		System.out.println("办件所用时长总数为：" + total);
	}
	
	/**
	 * 执行规则引擎
	 * @param list
	 * @param i
	 * @param batchId
	 */
	private void runSingleAreaRuleEngine(List<InstanceInfo> list, int i, String batchId) {
		FunctionSuperviseIfc functionSuperviseIfc = new FunctionSuperviseImpl();
		Sp_status stauts = new Sp_status();
		stauts.setFunctionName("规则引擎"+i);
		String exceptions = "";
		try {
			exceptions = ruleCount(list);
			if(StringUtils.isNotBlank(exceptions)){
				stauts.setStatus("N");
				stauts.setException(exceptions);
			}
			else {
				stauts.setStatus("Y");
			}
			stauts.setProcessQty(new Double(list.size()));
			stauts.setBatchId(batchId);
			stauts.setType("6");
			functionSuperviseIfc.insertFunctionSupervise(stauts);
		} catch (Exception e) {
			stauts.setStatus("N");
			stauts.setProcessQty(new Double(list.size()));
			stauts.setException(e.toString());
			stauts.setType("6");
			stauts.setBatchId(batchId);
			functionSuperviseIfc.insertFunctionSupervise(stauts);
		}
	}

	/**
	 * 计算办件时长
	 * @param list
	 * @throws Exception
	 */
	private String ruleCount(List<InstanceInfo> list) throws Exception {
		StringBuilder exceptions = new StringBuilder("");
		List<String> listInstanceId=new ArrayList<String>();
		for (InstanceInfo instanceInfo : list) {
			listInstanceId.add(instanceInfo.getInstanceId());
		}
		long dateInv = 0l;
		long timeInv = 0l;
		long remainInv = 0l;
		long standTimeInv = 0l;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			//查询指定区域所有审批实例的实例ID，开始受理时间和完成结束时间
			List<TbcxsqInfoBean> tbcxList = null;
			if(listInstanceId.size() > 0){
				//批量查询出特别程序列表
				tbcxList = DaoFactory.create(TbcxsqInfoBean.class).getSession()
						.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBeanMapper.selectBatchList", listInstanceId);
			}
			//查询指定区域所有审批实例的实例ID，开始受理时间和完成结束时间
			int supSize = list == null ? 0 : list.size();
			InstanceInfo instanceInfo = null;
			for(int i=0; i<supSize; i++){
				instanceInfo = list.get(i);
				String instanceId = instanceInfo.getInstanceId();
				String acceptTime = ft.format(instanceInfo.getAcceptTime());
				String finishTime = ft.format(instanceInfo.getEndTime());
				
				dateInv = 0l;
				timeInv = 0l;
				remainInv = 0l;
				standTimeInv = 0l;
				long[] invTimes = {0,0};
				long[] splTimes = {0,0};
				
				try {
					invTimes = HolidayDateUtil.getInvTimesWithTime(acceptTime,finishTime,
							instanceInfo.getWorkTime(), instanceInfo.getHolidayCount());//计算第1步与第2步
					standTimeInv = HolidayDateUtil.getStandardRemainDiff(acceptTime.substring(0,10),
							instanceInfo.getWorkTime());//获取1个工作日标准秒长
					splTimes = new InstanceUsedTimeCaculator().procSpecialTime(instanceId, tbcxList, standTimeInv);//procSpecialTime(instanceId,tbcxList); //计算第3步
				} catch (Exception e) {
					exceptions.append("businessType：8，").append(instanceId).append("：")
						.append(e.getMessage()).append("；");
					System.err.println(exceptions.toString());
					e.printStackTrace();
				}
				timeInv = ((invTimes[0]*standTimeInv)+invTimes[1]) - ((splTimes[0]*standTimeInv)+splTimes[1]);//计算第4步
				if(standTimeInv == 0){
					dateInv = 0;
					remainInv = 0;
				}
				else {
					dateInv = timeInv/standTimeInv; //计算第4步
					remainInv = timeInv%standTimeInv; //计算第4步
				}
				if(remainInv > 0l){ //计算第4步，如果还有余数，则差异天数加1
					dateInv = dateInv + 1;
				}
				if (dateInv < 0) {
					dateInv = 0;
				}
				list.get(i).setCompletedTime(new Double(dateInv));
			}
			if(supSize > 0){
				DaoFactory.create(InstanceInfo.class).getSession()
					.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.batchUpdateInstanceCompletedTime",list);
			}
		}catch(Exception e){
			System.err.println("计算办件时长执行失败," + e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions.toString())){
				exceptions.append("#");
			}
			exceptions.append(e.getMessage());
		}
		return exceptions.toString();
	}

	/**
	 * 初始化线程配置
	 */
	private void initThreadConfig(){
		//查询线程配置表是否配置了数据
		List<Thread_config> configList = DaoFactory.create(JcAllbussinesSum.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_configMapper.selectAll");
		if(configList != null && configList.size() > 0){
			superviseCount = configList.get(0).getQuerySize();
		} else {
			  String config = ConfigManager.getInstance().getConfig("supervise_size");
			  superviseCount = Integer.parseInt(config);
		}
	}
}
