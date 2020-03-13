package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.TaJcComplainBaseinfo;
import com.chinacreator.dzjc_ruleEngine.SuperviseTemp;
import com.chinacreator.dzjc_ruleEngine.SuperviseTimeLog;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_config;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.FunctionSuperviseIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RunRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.support.RuleEngineConstants;
import com.chinacreator.util.HolidayDateUtil;
import com.chinacreator.util.ThreadPoolUtils;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.StringUtil;

/**
 * 
 * @author lilang
 * @date 2018-3-28 下午8:14:50
 */
public class StartupRuleEngineServiceImpl implements
		StartupRuleEngineServiceIfc {

	// 允许的最大线程数。
	private static String MAX_POOL_SIZE = ConfigManager.getInstance().getConfig("max_supper_size");
	
	// 每次处理条数。
	private static String SUPERVISE_SIZE = ConfigManager.getInstance().getConfig("supervise_size");
	
	// 每次反写处理条数。
	private static String WRITEBACK_SIZE = ConfigManager.getInstance().getConfig("writeback_size");
	
	private int poolCount;
	private int superviseCount;
	private int lastCount;
	private int writebackCount;
	private int totalCount;
	private SuperviseTimeLog timeLog = null;
	private String superviseStartTimeStr = null;
	private String superviseEndTimeStr = null;
	private String threadStartTimeStr = null;
	private String feedbackDate = "";
	private boolean isStartBack = false;
	@Override
	public void doStartup() {
		getMemInfo("开始执行发牌规则");
		//本次定时任务的批次ID
		String batchId = StringUtil.getUUID2();
		//初始化线程配置
		initThreadConfig();
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeLog = new SuperviseTimeLog();
		timeLog.setSuperviseTimeId(StringUtil.getUUID2());
		timeLog.setBatchId(batchId);
		superviseStartTimeStr = fat.format(new Date());
		timeLog.setSuperviseStartTime(DateUtil.strToDate(superviseStartTimeStr));
		//插入一条总数记录
		Integer superviseBusinessQty = insertTotalFunction(batchId);
		timeLog.setSuperviseBusinessQty(new Double(superviseBusinessQty));
		//查询办件总数
		timeLog.setStartBusinessQty(new Double(queryBusinessCount()));
		//删除所有的监察要素结果
		deleteAllResultElement();
		boolean flag = true;
		int index = 1;
		int startIndex = 0;
		int endIndex = 0;
		Map<String,Integer> paramMap = null;
		List<JcAllbussinesSum> list = null;
		JcAllbussinesSum jcAllbussinesSum = null;
		int total = 0;
		String startTimeStr = "";
		String endTimeStr = "";
		String queryStartTimeStr = fat.format(new Date());
		timeLog.setQueryStartTime(DateUtil.strToDate(queryStartTimeStr));
		//插入监察时间日志
		insertSuperviseTimeLog(timeLog);
		//获取发牌反馈终止日期，30个工作日后，避免在每条发牌数据里都去获取
		try {
			feedbackDate = HolidayDateUtil.getDate(30);
		} catch (Exception e2) {
			System.out.println("获取反馈终止日期失败");
			e2.printStackTrace();
		}
		while(flag){
			getMemInfo("发牌规则开始"+index);
			Sp_status stauts = new Sp_status();
			startTimeStr = fat.format(new Date());
			try {
				stauts.setSuperviseStartTime(DateUtil.strToDate(startTimeStr));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			startIndex = (index-1)*superviseCount;
			endIndex = index*superviseCount;
			paramMap = new HashMap<String,Integer>();
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", endIndex);
			list = getJcAllbussinesSumList(paramMap);
			System.out.println("startIndex="+startIndex+";endIndex="+endIndex+";条数："+list.size());
			paramMap = null;
			if(index == 1){
				threadStartTimeStr = fat.format(new Date());
				timeLog.setThreadStartTime(DateUtil.strToDate(threadStartTimeStr));
			}
			index++;
			total = total + list.size();
			
			
			if(list.size()>0 && list.size() <= poolCount){
				//如果总的业务数据低于线程数，执行单线程
				runSingleAreaRuleEngine(list,1,batchId);
			}else if(list.size()>poolCount){
				//如果总的业务数据大于线程数，执行MAX_POOL_SIZE个线程
				//runSingleAreaRuleEngine();
				int count=list.size()/poolCount;//每个线程的数据量
				
				for(int i=0;i<poolCount;i++){
					List<JcAllbussinesSum> list1=new ArrayList<JcAllbussinesSum>();
					//第一步 获取当前线程的数据
					if(i==poolCount-1){
						for(int j=count*i;j<list.size();j++){
							jcAllbussinesSum = list.get(j);
							list1.add(jcAllbussinesSum);
						}
					}else{
						for(int j=count*i;j<count*(i+1);j++){
							jcAllbussinesSum = list.get(j);
							list1.add(jcAllbussinesSum);
						}
					}
					DownLoadRunnable dr = new DownLoadRunnable(list1,i,batchId);
					ThreadPoolUtils.execute(dr);
				}
			}
			else {
				//如果list没有数据了，则将flag设为false，不再循环查询
				flag = false;
			}
			try {
				FunctionSuperviseIfc functionsuperviseService = new FunctionSuperviseImpl();
				stauts.setFunctionName("发牌查询引擎"+index);
				stauts.setStatus("Y");
				stauts.setProcessQty(new Double(list.size()));
				stauts.setType("2");
				stauts.setBatchId(batchId);
				endTimeStr = fat.format(new Date());
				stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
				stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
				functionsuperviseService.insertFunctionSupervise(stauts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			list = null;
			
			getMemInfo("发牌规则结束"+(index-1));
			//System.out.println("第"+(index-1)+"次查询，目前已处理："+total+"条数据;"+fat.format(new Date()));
		}
		String queryEndTimeStr = fat.format(new Date());
		timeLog.setQueryEndTime(DateUtil.strToDate(queryEndTimeStr));
		timeLog.setQueryTotalTimes(new Double(DateUtil.getDiff(queryStartTimeStr, queryEndTimeStr)));
		//根据batchId修改
		updateSuperviseTimeLog(timeLog);
		getMemInfo("全部发牌结束");
		System.out.println("发牌总处理条数："+total);
	}

	private class DownLoadRunnable implements Runnable {

		private List<JcAllbussinesSum> list;
		private int i=1;
		private String batchId;
		
		public DownLoadRunnable(List<JcAllbussinesSum> list,int i, String batchId){
			this.list = list;
			this.i=i;
			this.batchId = batchId;
		}
		
		public void run() {
			// TO 要做的事情，实现在此接口中的run方法中
			runSingleAreaRuleEngine(list,i,batchId);
			list = null;
			i = 0;
			batchId = null;
		}

	}

	private void runSingleAreaRuleEngine(List<JcAllbussinesSum> list,int i, String batchId) {

		RunRuleEngineServiceIfc ruleEngineServiceIfc = new RunRuleEngineServiceImpl();
		
		FunctionSuperviseIfc functionsuperviseService = new FunctionSuperviseImpl();

		Map<String, List<EnginecfgBean>> map = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(new Date());
		String endTimeStr = "";
		Sp_status stauts = new Sp_status();
		stauts.setFunctionName("发牌规则引擎"+i);
		String exceptions = "";
		System.out.println("发牌规则引擎"+i+"......"+sdf.format(new Date()));
		try {
			stauts.setSuperviseStartTime(DateUtil.strToDate(startTimeStr));
			
			map = ruleEngineServiceIfc.loadConfigInfo();
			
			if (map != null && map.size() != 0) {
				//获取办件的数据
				exceptions = ruleEngineServiceIfc.ruleInit(list,map.get(RuleEngineConstants.INIT));
			}
			if(StringUtils.isNotBlank(exceptions)){
				exceptions += "#";
			}
			exceptions += ruleEngineServiceIfc.ruleCaculate(list,batchId);
			ruleEngineServiceIfc.ruleRun(list,feedbackDate);
			if(map != null && map.size() != 0){
				//ruleEngineServiceIfc.ruleTearDown(currentTime, map.get(RuleEngineConstants.END));
			}
			
			try {
				if(StringUtils.isNotBlank(exceptions)){
					stauts.setStatus("N");
					stauts.setException(exceptions);
				}
				else {
					stauts.setStatus("Y");
				}
				stauts.setProcessQty(new Double(list.size()));
				stauts.setBatchId(batchId);
				stauts.setType("3");
				endTimeStr = sdf.format(new Date());
				stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
				stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
				functionsuperviseService.insertFunctionSupervise(stauts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writeBackDataNew(batchId);

		} catch (Exception e) {
			try {
				stauts.setStatus("N");
				stauts.setProcessQty(new Double(list.size()));
				stauts.setException(e.toString());
				stauts.setType("3");
				stauts.setBatchId(batchId);
				endTimeStr = sdf.format(new Date());
				stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
				stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
				functionsuperviseService.insertFunctionSupervise(stauts);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.err.println("发牌规则引擎启动失败......"+i);
			
			writeBackDataNew(batchId);
		}finally {
			if (map != null) {
				map.clear();
			}
		}

	}
	//是否已经开始反写，必须要设置成同步，否则会有多个线程同时反写
	private synchronized boolean isNeedBack(String batchId){
		//如果已经开始反写，返回false，不再需要反写
		if(isStartBack){
			return false;
		}
		//查询本次任务所有线程的处理数量总和
		Sp_status param = new Sp_status();
		param.setBatchId(batchId);
		param.setType("3");
		int sum = DaoFactory.create(Sp_status.class).getSession()
				.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectSum",param);
		//如果所有线程处理总数量》=本次需要处理的总数量，则说明所有线程已经执行完成，可以将临时表里需要反写的数据更新到业务表
		if((sum+lastCount) >= totalCount){
			isStartBack = true;
			return true;
		}
		return false;
	}
	
	private void writeBackDataNew(String batchId){
		try {
			//如果不需要反写了，就直接退出
			if(!isNeedBack(batchId)){
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String writeStartTimeStr = sdf.format(new Date());
			timeLog.setThreadEndTime(DateUtil.strToDate(writeStartTimeStr));
			timeLog.setThreadTotalTimes(new Double(DateUtil.getDiff(threadStartTimeStr, writeStartTimeStr)));
			timeLog.setWriteStartTime(DateUtil.strToDate(writeStartTimeStr));
			//根据batchId修改
			updateSuperviseTimeLog(timeLog);
			boolean flag = true;
			int index = 1;
			int startIndex = 0;
			int endIndex = 0;
			Map<String,Integer> paramMap = null;
			List<Map<String,String>> countList = null;
			int instanceCount = 0;
			int complainCount = 0;
			int consultCount = 0;
			int suspendCount = 0;
			int internetCount = 0;
			int dzzzCount = 0;
			getMemInfo("全部发牌反写开始"+sdf.format(new Date()));
			while(flag){
				getMemInfo("发牌反写开始"+index+":"+sdf.format(new Date()));
				startIndex = (index-1)*writebackCount;
				endIndex = index*writebackCount;
				paramMap = new HashMap<String,Integer>();
				paramMap.put("startIndex", startIndex);
				paramMap.put("endIndex", endIndex);
				index++;
				//查询反写临时表里这一批有多少数量
				int backCount = DaoFactory.create(SuperviseTemp.class).getSession()
						.selectOne("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.selectCount",paramMap);
				System.err.println("本次反写数量="+backCount+":"+sdf.format(new Date()));
				if(backCount >0){
					//将这一批数据再插入到一张临时表，方便merge into操作
					DaoFactory.create(SuperviseTemp.class).getSession()
						.insert("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.insertIntoBackTemp",paramMap);
					countList = DaoFactory.create(SuperviseTemp.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.selectTempCount");
					if(countList != null && countList.size() > 0){
						instanceCount = 0;
						complainCount = 0;
						consultCount = 0;
						suspendCount = 0;
						internetCount = 0;
						dzzzCount = 0;
						for(Map<String,String> countMap : countList){
							if(countMap.get("BUSINESSTYPE").equals("1")){
								instanceCount = Integer.valueOf(String.valueOf(countMap.get("BUSINESSCOUNT")));
							}
							else if(countMap.get("BUSINESSTYPE").equals("2")){
								complainCount = Integer.valueOf(String.valueOf(countMap.get("BUSINESSCOUNT")));
							}
							else if(countMap.get("BUSINESSTYPE").equals("3")){
								consultCount = Integer.valueOf(String.valueOf(countMap.get("BUSINESSCOUNT")));
							}
							else if(countMap.get("BUSINESSTYPE").equals("4")){
								suspendCount = Integer.valueOf(String.valueOf(countMap.get("BUSINESSCOUNT")));
							}
							else if(countMap.get("BUSINESSTYPE").equals("5")){
								internetCount = Integer.valueOf(String.valueOf(countMap.get("BUSINESSCOUNT")));
							}
							else if(countMap.get("BUSINESSTYPE").equals("6")){
								dzzzCount = Integer.valueOf(String.valueOf(countMap.get("BUSINESSCOUNT")));
							}
						}
						//merge into处理办件反写数据
						if(instanceCount > 0){
							DaoFactory.create(SuperviseTemp.class).getSession()
							.update("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.mergeIntoInstance");
						}
						//merge into处理投诉反写数据
						if(complainCount > 0){
							DaoFactory.create(SuperviseTemp.class).getSession()
							.update("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.mergeIntoComplain");
						}
						//merge into处理咨询反写数据
						if(consultCount > 0){
							DaoFactory.create(SuperviseTemp.class).getSession()
							.update("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.mergeIntoConsult");
						}
						//merge into处理特别程序反写数据
						if(suspendCount > 0){
							DaoFactory.create(SuperviseTemp.class).getSession()
							.update("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.mergeIntoSuspend");
						}
						//merge into处理网办反写数据
						if(internetCount > 0){
							DaoFactory.create(SuperviseTemp.class).getSession()
							.update("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.mergeIntoInternet");
						}
						//merge into处理电子证照反写数据
						if(dzzzCount > 0){
							DaoFactory.create(SuperviseTemp.class).getSession()
							.update("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.mergeIntoDzzz");
						}
					}
					//清空反写临时表
					DaoFactory.create(SuperviseTemp.class).getSession()
						.delete("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.truncateBackTemp");
					getMemInfo("发牌反写结束"+(index-1)+":"+sdf.format(new Date()));
				}
				else{
					flag = false;
				}
			}
			getMemInfo("全部反写结束"+sdf.format(new Date()));
			//删除临时表数据
			DaoFactory.create(SuperviseTemp.class).getSession()
				.delete("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.deleteAll");
			//删除监察日志中成功的记录
			DaoFactory.create(Sp_status.class).getSession()
				.delete("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.deleteSuccess");
			try {
				superviseEndTimeStr = sdf.format(new Date());
				timeLog.setWriteEndTime(DateUtil.strToDate(superviseEndTimeStr));
				timeLog.setWriteTotalTimes(new Double(DateUtil.getDiff(writeStartTimeStr, superviseEndTimeStr)));
				timeLog.setSuperviseEndTime(DateUtil.strToDate(superviseEndTimeStr));
				timeLog.setSuperviseTotalTimes(new Double(DateUtil.getDiff(superviseStartTimeStr, superviseEndTimeStr)));
				timeLog.setEndBusinessQty(new Double(queryBusinessCount()));
				//查询本次各种发牌数量以及当前发牌结果表总数量
				Map<String,BigDecimal> countMap = querySuperviseCount(batchId);
				if(countMap != null){
					timeLog.setBlueQty(countMap.get("BLUEQTY").doubleValue());
					timeLog.setYellowQty(countMap.get("YELLOWQTY").doubleValue());
					timeLog.setRedQty(countMap.get("REDQTY").doubleValue());
					timeLog.setSuperviseTotalQty(countMap.get("TOTALQTY").doubleValue());
				}
				//根据batchId修改
				updateSuperviseTimeLog(timeLog);
			} catch (Exception e) {
				System.err.println("监察规则写入执行日志时异常："+e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("监察规则反写数据时异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void writeBackData(String batchId){
		try {
			//查询线程执行结果表里，本次执行的总数量
			Sp_status total = DaoFactory.create(Sp_status.class).getSession()
					.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectTotal");
			if(null != total 
					&& StringUtils.isNotBlank(total.getBatchId()) 
					&& null != total.getProcessQty() 
					&& total.getProcessQty() > 0){
				//查询本次任务所有线程的处理数量总和
				Sp_status param = new Sp_status();
				param.setBatchId(total.getBatchId());
				param.setType("3");
				int sum = DaoFactory.create(Sp_status.class).getSession()
						.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectSum",param);
				//如果所有线程处理总数量》=本次需要处理的总数量，则说明所有线程已经执行完成，可以将临时表里需要反写的数据更新到业务表
				if((sum+lastCount) >= total.getProcessQty().intValue()){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String writeStartTimeStr = sdf.format(new Date());
					timeLog.setThreadEndTime(DateUtil.strToDate(writeStartTimeStr));
					timeLog.setThreadTotalTimes(new Double(DateUtil.getDiff(threadStartTimeStr, writeStartTimeStr)));
					timeLog.setWriteStartTime(DateUtil.strToDate(writeStartTimeStr));
					//根据batchId修改
					updateSuperviseTimeLog(timeLog);
					int superviseCount = 1000;
					if(!StringUtils.isBlank(SUPERVISE_SIZE)){
						superviseCount = Integer.parseInt(SUPERVISE_SIZE);
					}
					boolean flag = true;
					int index = 1;
					int startIndex = 0;
					int endIndex = 0;
					Map<String,Integer> paramMap = null;
					List<SuperviseTemp> tempList = null;
					List<InstanceInfo> instanceList = new ArrayList<InstanceInfo>();
					InstanceInfo instance = null;
					List<TaJcComplainBaseinfo> complainList = new ArrayList<TaJcComplainBaseinfo>();
					TaJcComplainBaseinfo complain = null;
					List<ConsultInfo> consultList = new ArrayList<ConsultInfo>();
					ConsultInfo consult = null;
					List<TbcxsqInfoBean> suspendList = new ArrayList<TbcxsqInfoBean>();
					TbcxsqInfoBean suspend = null;
					List<InstanceInfo> internetList = new ArrayList<InstanceInfo>();
					InstanceInfo internet = null;
					List<InstanceInfo> dzzzList = new ArrayList<InstanceInfo>();
					InstanceInfo dzzz = null;
					getMemInfo("发牌反写开始"+index);
					while(flag){
						startIndex = (index-1)*superviseCount;
						endIndex = index*superviseCount;
						paramMap = new HashMap<String,Integer>();
						paramMap.put("startIndex", startIndex);
						paramMap.put("endIndex", endIndex);
						index++;
						//查询临时表里的所有数据
						tempList = DaoFactory.create(SuperviseTemp.class).getSession()
								.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.selectAll",paramMap);
						if(tempList != null && tempList.size() >0){
							for(SuperviseTemp temp : tempList){
								if(temp.getBusinessType().equals("1")){
									instance = new InstanceInfo();
									instance.setInstanceId(temp.getBusinessId());
									instance.setIsNeedSupervise(temp.getIsNeedSupervise());
									instance.setLastSupervise(temp.getLastSupervise());
									instanceList.add(instance);
									instance = null;
								}
								if(temp.getBusinessType().equals("2")){
									complain = new TaJcComplainBaseinfo();
									complain.setComplainId(temp.getBusinessId());
									complain.setIsNeedSupervise(temp.getIsNeedSupervise());
									complain.setLastSupervise(temp.getLastSupervise());
									complainList.add(complain);
									complain = null;
								}
								if(temp.getBusinessType().equals("3")){
									consult = new ConsultInfo();
									consult.setConsultId(temp.getBusinessId());
									consult.setIsNeedSupervise(temp.getIsNeedSupervise());
									consult.setLastSupervise(temp.getLastSupervise());
									consultList.add(consult);
									consult = null;
								}
								if(temp.getBusinessType().equals("4")){
									suspend = new TbcxsqInfoBean();
									suspend.setSuspendId(temp.getBusinessId());
									suspend.setIsNeedSupervise(temp.getIsNeedSupervise());
									suspend.setLastSupervise(temp.getLastSupervise());
									suspendList.add(suspend);
									suspend = null;
								}
								if(temp.getBusinessType().equals("5")){
									internet = new InstanceInfo();
									internet.setInstanceId(temp.getBusinessId());
									internet.setIsNeedSupervise2(temp.getIsNeedSupervise());
									internet.setLastSupervise2(temp.getLastSupervise());
									internetList.add(internet);
									internet = null;
								}
								if(temp.getBusinessType().equals("6")){
									dzzz = new InstanceInfo();
									dzzz.setInstanceId(temp.getBusinessId());
									dzzz.setIsNeedSupervise3(temp.getIsNeedSupervise());
									dzzz.setLastSupervise3(temp.getLastSupervise());
									dzzzList.add(dzzz);
									dzzz = null;
								}
							}
							if(instanceList.size() > 0){
								DaoFactory.create(InstanceInfo.class).getSession()
									.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.batchUpdateSupervise",instanceList);
								instanceList = new ArrayList<InstanceInfo>();
							}
							if(complainList.size() > 0){
								DaoFactory.create(TaJcComplainBaseinfo.class).getSession()
									.update("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.batchUpdateSupervise",complainList);
								complainList = new ArrayList<TaJcComplainBaseinfo>();
							}
							if(consultList.size() > 0){
								DaoFactory.create(ConsultInfo.class).getSession()
									.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfoMapper.batchUpdateSupervise",consultList);
								consultList = new ArrayList<ConsultInfo>();
							}
							if(suspendList.size() > 0){
								DaoFactory.create(TbcxsqInfoBean.class).getSession()
									.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBeanMapper.batchUpdateSupervise",suspendList);
								suspendList = new ArrayList<TbcxsqInfoBean>();
							}
							if(internetList.size() > 0){
								DaoFactory.create(InstanceInfo.class).getSession()
									.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.batchUpdateSupervise2",internetList);
								internetList = new ArrayList<InstanceInfo>();
							}
							if(dzzzList.size() > 0){
								DaoFactory.create(InstanceInfo.class).getSession()
									.update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.batchUpdateSupervise3",dzzzList);
								dzzzList = new ArrayList<InstanceInfo>();
							}
							tempList = null;
						}
						else{
							flag = false;
							System.out.println("发牌反写处理结束===="+sdf.format(new Date()));
						}
						//getMemInfo("反写结束"+index);
					}
					getMemInfo("全部反写结束");
					//删除临时表数据
					DaoFactory.create(SuperviseTemp.class).getSession()
					.delete("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.deleteAll");
					//删除监察日志中成功的记录
					DaoFactory.create(Sp_status.class).getSession()
					.delete("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.deleteSuccess");
					try {
						superviseEndTimeStr = sdf.format(new Date());
						timeLog.setWriteEndTime(DateUtil.strToDate(superviseEndTimeStr));
						timeLog.setWriteTotalTimes(new Double(DateUtil.getDiff(writeStartTimeStr, superviseEndTimeStr)));
						timeLog.setSuperviseEndTime(DateUtil.strToDate(superviseEndTimeStr));
						timeLog.setSuperviseTotalTimes(new Double(DateUtil.getDiff(superviseStartTimeStr, superviseEndTimeStr)));
						timeLog.setEndBusinessQty(new Double(queryBusinessCount()));
						//查询本次各种发牌数量以及当前发牌结果表总数量
						Map<String,BigDecimal> countMap = querySuperviseCount(batchId);
						if(countMap != null){
							timeLog.setBlueQty(countMap.get("BLUEQTY").doubleValue());
							timeLog.setYellowQty(countMap.get("YELLOWQTY").doubleValue());
							timeLog.setRedQty(countMap.get("REDQTY").doubleValue());
							timeLog.setSuperviseTotalQty(countMap.get("TOTALQTY").doubleValue());
						}
						//根据batchId修改
						updateSuperviseTimeLog(timeLog);
					} catch (Exception e) {
						System.err.println("监察规则写入执行日志时异常："+e.getMessage());
						e.printStackTrace();
					}
				}
			}
			total = null;
		} catch (Exception e) {
			System.err.println("监察规则反写数据时异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private List<JcAllbussinesSum> getJcAllbussinesSumList(Map<String,Integer> paramMap){
		List<JcAllbussinesSum> list = new ArrayList<JcAllbussinesSum>();
		try {
			list = DaoFactory.create(JcAllbussinesSum.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSumMapper.selectAll",paramMap);
		} catch (Exception e) {
			System.err.println("监察规则查询数据时异常："+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private Integer insertTotalFunction(String batchId){
		//查询本次需要处理的数据的总数
		Sp_status stauts = new Sp_status();
		stauts.setFunctionName("发牌总数引擎");
		stauts.setType("1");
		stauts.setBatchId(batchId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer total;
		String startTimeStr = sdf.format(new Date());
		FunctionSuperviseIfc functionsuperviseService = new FunctionSuperviseImpl();
		try {
			stauts.setSuperviseStartTime(DateUtil.strToDate(startTimeStr));
			total = DaoFactory.create(JcAllbussinesSum.class).getSession()
					.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSumMapper.selectCount");
			totalCount = total;
			stauts.setStatus("Y");
			stauts.setProcessQty(new Double(total));
			String endTimeStr = sdf.format(new Date());
			stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
			stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
			functionsuperviseService.insertFunctionSupervise(stauts);
		} catch (Exception e) {
			e.printStackTrace();
			stauts.setStatus("N");
			stauts.setProcessQty(new Double(0));
			String endTimeStr = sdf.format(new Date());
			stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
			stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
			functionsuperviseService.insertFunctionSupervise(stauts);
			return 0;
		}
		return total;
	}
	
	private void insertSuperviseTimeLog(SuperviseTimeLog timeLog){
		try {
			DaoFactory.create(SuperviseTimeLog.class).insert(timeLog);
		} catch (Exception e) {
			System.err.println("监察规则在插入执行日志时异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void updateSuperviseTimeLog(SuperviseTimeLog timeLog){
		try {
			DaoFactory.create(SuperviseTimeLog.class).getSession()
				.update("com.chinacreator.dzjc_ruleEngine.SuperviseTimeLogMapper.updateByBatchId", timeLog);
		} catch (Exception e) {
			System.err.println("监察规则在修改执行日志时异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Map<String,BigDecimal> querySuperviseCount(String batchId){
		return DaoFactory.create(SuperviseTimeLog.class).getSession()
			.selectOne("com.chinacreator.dzjc_ruleEngine.SuperviserInfoMapper.selectSuperviseCount",batchId);
	}
	
	private Integer queryBusinessCount() {
		Integer total = null;
		try {
			total = DaoFactory.create(InstanceInfo.class).getSession()
				.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectTotalCount");
		} catch (Exception e) {
			System.err.println("监察规则在查询本次监察数量时异常："+e.getMessage());
			e.printStackTrace();
		}
		return total;
	}
	
	private void deleteAllResultElement(){
		try {
			DaoFactory.create(ResultElement.class).getSession()
			.delete("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.deleteAll");
		} catch (Exception e) {
			System.err.println("监察规则删除上次执行的要素结果时异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void initThreadConfig(){
		writebackCount = 10000;
		if(!StringUtils.isBlank(WRITEBACK_SIZE)){
			writebackCount = Integer.parseInt(WRITEBACK_SIZE);
		}
		poolCount = 30;
		if(!StringUtils.isBlank(SUPERVISE_SIZE)){
			poolCount = Integer.parseInt(MAX_POOL_SIZE);
		}
		superviseCount = 1000;
		if(!StringUtils.isBlank(SUPERVISE_SIZE)){
			superviseCount = Integer.parseInt(SUPERVISE_SIZE);
		}
		//查询线程配置表是否配置了数据
		List<Thread_config> configList = DaoFactory.create(JcAllbussinesSum.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_configMapper.selectAll");
		if(configList != null && configList.size() > 0){
			superviseCount = configList.get(0).getQuerySize();
			poolCount = configList.get(0).getThreadSize();
		}
		try {
			lastCount = superviseCount/poolCount-1;
		} catch (Exception e) {
			e.printStackTrace();
			lastCount = 32;
		}
	}
	
	public static void getMemInfo(String title){
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	    MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
	    System.out.println(title+"，初始化内存=="+(memoryUsage.getInit()/1024/1024)+"，"
	    		+"最大可用内存=="+(memoryUsage.getMax()/1024/1024)+"，"
	    		+"已用内存=="+(memoryUsage.getUsed()/1024/1024));
    }
	public static void main(String[] args){
		int a = 1000;
		int b = 30;
		System.out.println(a/b-1);
	}
}
