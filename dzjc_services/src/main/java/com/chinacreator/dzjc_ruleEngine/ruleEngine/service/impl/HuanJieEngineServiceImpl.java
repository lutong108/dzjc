package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.SuperviseHuanJieTemp;
import com.chinacreator.dzjc_ruleEngine.SuperviseTemp;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.HuanJieResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_config;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.FunctionSuperviseIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RunHuanJieEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.StartupRuleEngineServiceIfc;
import com.chinacreator.util.ThreadPoolUtils;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.HttpInterfaceUtil;
import com.chinacreator.util.StringUtil;

/**
 * 
 * @author liaolong
 * @date 2019-1-14 
 */
public class HuanJieEngineServiceImpl implements
		StartupRuleEngineServiceIfc {

	// 允许的最大线程数。
	private static String MAX_POOL_SIZE = ConfigManager.getInstance().getConfig("max_supper_size");
	
	// 每次处理条数。
	private static String SUPERVISE_SIZE = ConfigManager.getInstance().getConfig("supervise_size");
	
	private int poolCount;
	private int superviseCount;
	private int lastCount;
	
	@Override
	public void doStartup() {
		getMemInfo("开始执行环节监察");
		//本次定时任务的批次ID
		String batchId = StringUtil.getUUID2();
		//初始化线程配置
		initThreadConfig();
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//先将所有没有开始时间，以及环节时限的数据修改监察标识
		batchUpdateCannotSupervise();
		//插入一条总数记录
		insertTotalFunction(batchId);
		
		//删除所有的监察要素结果
		deleteAllResultElement();
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//ServletContext application = request.getServletContext();
		ServletContext application = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		//调用接口获取益政访问令牌
		HttpInterfaceUtil.getYiZhengAccessToken(application);
		
		boolean flag = true;
		int index = 1;
		int startIndex = 0;
		int endIndex = 0;
		String startTimeStr = "";
		String endTimeStr = "";
		Map<String,Integer> paramMap = null;
		List<ResultOpinion> list = null;
		ResultOpinion resultOpinion = null;
		int total = 0;
		
		while(flag){
			getMemInfo("环节监察发牌开始"+index);
			Sp_status stauts = new Sp_status();
			startTimeStr = fat.format(new Date());
			stauts.setSuperviseStartTime(DateUtil.strToDate(startTimeStr));
			startIndex = (index-1)*superviseCount;
			endIndex = index*superviseCount;
			paramMap = new HashMap<String,Integer>();
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", endIndex);
			list = getHuanHieBusinessList(paramMap);
			System.out.println("startIndex="+startIndex+";endIndex="+endIndex+";条数："+list.size());
			paramMap = null;
			index++;
			total = total + list.size();
			
			if(list.size()>0 && list.size() <= poolCount){
				//如果总的业务数据低于线程数，执行单线程
				runHuanJieEngine(list,1,batchId,application);
			}else if(list.size()>poolCount){
				//如果总的业务数据大于线程数，执行MAX_POOL_SIZE个线程
				//runSingleAreaRuleEngine();
				int count=list.size()/poolCount;//每个线程的数据量
				
				for(int i=0;i<poolCount;i++){
					List<ResultOpinion> list1 = new ArrayList<ResultOpinion>();
					//第一步 获取当前线程的数据
					if(i == poolCount-1){
						for(int j=count*i;j<list.size();j++){
							resultOpinion = list.get(j);
							list1.add(resultOpinion);
						}
					}else{
						for(int j=count*i;j<count*(i+1);j++){
							resultOpinion = list.get(j);
							list1.add(resultOpinion);
						}
					}
					DownLoadRunnable dr = new DownLoadRunnable(list1,i,batchId,application);
					ThreadPoolUtils.execute(dr);
				}
			}
			else {
				//如果list没有数据了，则将flag设为false，不再循环查询
				flag = false;
			}
			try {
				FunctionSuperviseIfc functionsuperviseService = new FunctionSuperviseImpl();
				stauts.setFunctionName("环节监察查询引擎"+index);
				stauts.setStatus("Y");
				stauts.setProcessQty(new Double(list.size()));
				stauts.setType("12");
				stauts.setBatchId(batchId);
				endTimeStr = fat.format(new Date());
				stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
				stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
				functionsuperviseService.insertFunctionSupervise(stauts);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			list = null;
			getMemInfo("环节监察结束"+(index-1));
		}
		getMemInfo("环节监察全部发牌结束");
		System.out.println("环节监察发牌总处理条数："+total);
	}

	private class DownLoadRunnable implements Runnable {

		private List<ResultOpinion> list;
		private int i=1;
		private String batchId;
		private ServletContext application;
		
		public DownLoadRunnable(List<ResultOpinion> list,int i, String batchId,ServletContext application){
			this.list = list;
			this.i=i;
			this.batchId = batchId;
			this.application = application;
		}
		
		public void run() {
			// TO 要做的事情，实现在此接口中的run方法中
			runHuanJieEngine(list,i,batchId,application);
			list = null;
			i = 0;
			batchId = null;
		}

	}

	private void runHuanJieEngine(List<ResultOpinion> list,int i, String batchId
			,ServletContext application) {
		RunHuanJieEngineServiceIfc ruleEngineServiceIfc = new RunHuanJieEngineServiceImpl();
		FunctionSuperviseIfc functionsuperviseService = new FunctionSuperviseImpl();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = sdf.format(new Date());
		String endTimeStr = "";
		Sp_status stauts = new Sp_status();
		stauts.setFunctionName("环节监察引擎"+i);
		String exceptions = "";
		try {
			stauts.setSuperviseStartTime(DateUtil.strToDate(startTimeStr));
			//计算环节所用时长
			exceptions = ruleEngineServiceIfc.ruleCaculate(list,batchId);
			//处理发牌
			ruleEngineServiceIfc.ruleRun(list,application);
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
				stauts.setType("13");
				endTimeStr = sdf.format(new Date());
				stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
				stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
				functionsuperviseService.insertFunctionSupervise(stauts);
			} catch (Exception e) {
				System.err.println("环节监察写入Sp_status失败："+e.getMessage());
				e.printStackTrace();
			}
			writeBackData(batchId);
		} catch (Exception e) {
			try {
				stauts.setStatus("N");
				stauts.setProcessQty(new Double(list.size()));
				stauts.setException(e.toString());
				stauts.setType("13");
				stauts.setBatchId(batchId);
				endTimeStr = sdf.format(new Date());
				stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
				stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
				functionsuperviseService.insertFunctionSupervise(stauts);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.err.println("环节监察引擎启动失败......"+i);
			e.printStackTrace();
			
			writeBackData(batchId);
		}
	}
	
	private void writeBackData(String batchId){
		try {
			//查询线程执行结果表里，本次执行的总数量
			Sp_status total = DaoFactory.create(Sp_status.class).getSession()
					.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectHuanJieTotal");
			if(null != total 
					&& StringUtils.isNotBlank(total.getBatchId()) 
					&& null != total.getProcessQty() 
					&& total.getProcessQty() > 0){
				//查询本次任务所有线程的处理数量总和
				Sp_status param = new Sp_status();
				param.setBatchId(total.getBatchId());
				param.setType("13");
				int sum = DaoFactory.create(Sp_status.class).getSession()
						.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectSum",param);
				//如果所有线程处理总数量》=本次需要处理的总数量，则说明所有线程已经执行完成，可以将临时表里需要反写的数据更新到业务表
				if((sum+lastCount) >= total.getProcessQty().intValue()){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					boolean flag = true;
					int index = 1;
					int startIndex = 0;
					int endIndex = 0;
					Map<String,Integer> paramMap = null;
					List<SuperviseHuanJieTemp> tempList = null;
					List<ResultOpinion> infoList = new ArrayList<ResultOpinion>();
					ResultOpinion info = null;
					getMemInfo("环节监察反写开始"+index);
					while(flag){
						startIndex = (index-1)*superviseCount;
						endIndex = index*superviseCount;
						paramMap = new HashMap<String,Integer>();
						paramMap.put("startIndex", startIndex);
						paramMap.put("endIndex", endIndex);
						index++;
						//分批查询临时表里的数据
						tempList = DaoFactory.create(SuperviseTemp.class).getSession()
								.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseHuanJieTempMapper.selectAll",paramMap);
						if(tempList != null && tempList.size() >0){
							for(SuperviseHuanJieTemp temp : tempList){
								info = new ResultOpinion();
								info.setOpinionId(temp.getBusinessId());
								info.setIsNeedSupervise(temp.getIsNeedSupervise());
								info.setLastSupervise(temp.getLastSupervise());
								infoList.add(info);
								info = null;
							}
							if(infoList.size() > 0){
								DaoFactory.create(InstanceInfo.class).getSession()
									.update("com.chinacreator.dzjc_ruleEngine.ResultOpinionMapper.batchUpdateSupervise",infoList);
								infoList = new ArrayList<ResultOpinion>();
							}
							tempList = null;
						}
						else{
							flag = false;
							System.out.println("环节监察发牌反写处理结束===="+sdf.format(new Date()));
						}
					}
					getMemInfo("环节监察全部反写结束");
					//删除临时表数据
					DaoFactory.create(SuperviseTemp.class).getSession()
					.delete("com.chinacreator.dzjc_ruleEngine.SuperviseHuanJieTempMapper.deleteAll");
				}
			}
			total = null;
		} catch (Exception e) {
			System.err.println("环节监察反写数据时失败："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private List<ResultOpinion> getHuanHieBusinessList(Map<String,Integer> paramMap){
		List<ResultOpinion> list = new ArrayList<ResultOpinion>();
		try {
			list = DaoFactory.create(ResultOpinion.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ResultOpinionMapper.selectSuperviseData",paramMap);
		} catch (Exception e) {
			System.err.println("环节监察查询数据失败："+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private Integer insertTotalFunction(String batchId){
		//查询本次需要处理的数据的总数
		Sp_status stauts = new Sp_status();
		stauts.setFunctionName("环节监察总数引擎");
		stauts.setType("11");
		stauts.setBatchId(batchId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer total;
		String startTimeStr = sdf.format(new Date());
		FunctionSuperviseIfc functionsuperviseService = new FunctionSuperviseImpl();
		try {
			stauts.setSuperviseStartTime(DateUtil.strToDate(startTimeStr));
			total = DaoFactory.create(ResultOpinion.class).getSession()
					.selectOne("com.chinacreator.dzjc_ruleEngine.ResultOpinionMapper.selectSuperviseTotal");
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
	
	private void deleteAllResultElement(){
		DaoFactory.create(HuanJieResultElement.class).getSession()
		.delete("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.HuanJieResultElementMapper.deleteAll");
	}
	
	private void batchUpdateCannotSupervise(){
		DaoFactory.create(HuanJieResultElement.class).getSession()
		.update("com.chinacreator.dzjc_ruleEngine.ResultOpinionMapper.batchUpdateCannotSupervise");
	}
	
	private void initThreadConfig(){
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
	
}
