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

import org.apache.commons.lang.StringUtils;

import bsh.Interpreter;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.Constant;
import com.chinacreator.dzjc_ruleEngine.CheckSupervise;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.ElementInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.ElementInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_config;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.CheckSuperviseEngineServiceIfc;
import com.chinacreator.util.ThreadPoolUtils;
import com.chinacreator.util.DateUtil;

/**
 * 
 * @author liaolong
 * @date 2018-11-05 
 */
public class CheckSuperviseEngineServiceImpl implements
	CheckSuperviseEngineServiceIfc {

	// 允许的最大线程数。
	private static String MAX_POOL_SIZE = ConfigManager.getInstance().getConfig("max_supper_size");
		
	// 每次处理条数。
	private static String SUPERVISE_SIZE = ConfigManager.getInstance().getConfig("supervise_size");
	
	private int poolCount;
	private int superviseCount;
	
	@Override
	public void doStartup() {
		getMemInfo("开始执行发牌巡检定时任务");
		//初始化线程配置
		initThreadConfig();
		boolean flag = true;
		int index = 1;
		int startIndex = 0;
		int endIndex = 0;
		Map<String,Integer> paramMap = null;
		List<CheckSupervise> list = null;
		CheckSupervise checkSuperviseBean = null;
		int total = 0;
		//SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(flag){
			getMemInfo("发牌巡检开始"+index);
			startIndex = (index-1)*superviseCount;
			endIndex = index*superviseCount;
			paramMap = new HashMap<String,Integer>();
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", endIndex);
			list = getCheckSuperviseBeanList(paramMap);
			paramMap = null;
			index++;
			total = total + list.size();
			if(list.size()>0 && list.size() <= poolCount){
				//如果总的业务数据低于线程数，执行单线程
				runCheckSuperviseEngine(list,1);
			}else if(list.size()>poolCount){
				//如果总的业务数据大于线程数，执行MAX_POOL_SIZE个线程
				//runSingleAreaRuleEngine();
				int count=list.size()/poolCount;//每个线程的数据量
				
				for(int i=0;i<poolCount;i++){
					List<CheckSupervise> list1=new ArrayList<CheckSupervise>();
					//第一步 获取当前线程的数据
					if(i==poolCount-1){
						for(int j=count*i;j<list.size();j++){
							checkSuperviseBean = list.get(j);
							list1.add(checkSuperviseBean);
						}
					}else{
						for(int j=count*i;j<count*(i+1);j++){
							checkSuperviseBean = list.get(j);
							list1.add(checkSuperviseBean);
						}
					}
					DownLoadRunnable dr = new DownLoadRunnable(list1,i);
					ThreadPoolUtils.execute(dr);
				}
			}
			else {
				//如果list没有数据了，则将flag设为false，不再循环查询
				flag = false;
				//反写巡检状态
				writeBackData();
			}
			list = null;
			
			getMemInfo("发牌巡检结束"+(index-1));
		}
		getMemInfo("全部发牌巡检结束");
		System.out.println("发牌巡检总处理条数："+total);
	}

	private class DownLoadRunnable implements Runnable {

		private List<CheckSupervise> list;
		private int i=1;
		
		public DownLoadRunnable(List<CheckSupervise> list,int i){
			this.list = list;
			this.i=i;
		}
		
		public void run() {
			// TO 要做的事情，实现在此接口中的run方法中
			runCheckSuperviseEngine(list,i);
			list = null;
			i = 0;
		}

	}

	private void runCheckSuperviseEngine(List<CheckSupervise> list,int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			InstanceUsedTimeCaculator caculate = new InstanceUsedTimeCaculator();
			double outDays = 0.0;
			List<SuperviserInfo> superviseList = new ArrayList<SuperviserInfo>();
			SuperviserInfo supervise = null;
			//查询出这一批数据的特别程序申请列表
			List<TbcxsqInfoBean> tbcxList = null;
			List<String> businessIdList = new ArrayList<String>();
			ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
			for(CheckSupervise check : list){
				if(StringUtils.isNotBlank(check.getBusinessId())){
					businessIdList.add(check.getBusinessId());
				}
			}
			if(businessIdList.size() > 0){
				//批量查询出特别程序列表
				tbcxList = elementInfoDao.queryTbcxsqlist(businessIdList);
			}
			for(CheckSupervise check : list){
				//计算办件所用时长
				try {
					check = caculate.caculateUsedTime(check,tbcxList);
				} catch (Exception e) {
					System.err.println(check.getBusinessId()+"： "+e.getMessage());
					e.printStackTrace();
					continue;
				}
				//如果是预警或黄牌，用承诺时限的超期天数去判断
				if("1".equals(check.getSuperviseResultNo()) || 
						"2".equals(check.getSuperviseResultNo())){
					outDays = check.getUseTime() - check.getCommitmentLimit();
				}
				//如果是黄牌，则用法定时限的超期天数去判断
				else {
					outDays = check.getUseTime() - check.getApproveLimit();
				}
				//构造表达式
				String expStr = ""+outDays + check.getCompa() + check.getCfgValue();
				expStr = expStr.replace(" ", "");
				supervise = new SuperviserInfo();
				supervise.setSuperviseInfoId(check.getSuperviseInfoId());
				supervise.setCheckTime(DateUtil.strToDate(sdf.format(new Date())));
				supervise.setCheckEndTime(check.getEndTime());
				//计算表达式
				//如果超期了，则将巡检状态修改为Y：正常发牌
				if(excuteExp(expStr)){
					supervise.setCheckStatusTemp(Constant.YES);
				}
				//如果没有超期，则将巡检状态修改为N：异常发牌
				else {
					supervise.setCheckStatusTemp(Constant.NO);
				}
				superviseList.add(supervise);
			}
			//批量修改发牌表里的巡检状态临时字段，
			//等全部巡检完成后，再统一将临时字段里面的值写入到巡检状态字段
			DaoFactory.create(SuperviserInfo.class).updateBatch(superviseList);
		} catch (Exception e) {
			System.err.println("发牌巡检规则引擎启动失败......"+i+":"+sdf.format(new Date()));
			e.printStackTrace();
		}
	}
	
	private boolean excuteExp(String expStr) {
		boolean bRlst = false;
		//这个地方调用BeanShell运算
		/*BeanShell执行代码*/
		Interpreter i = new Interpreter();
		i.setStrictJava(true);//严格使用java类型 ;
		try{
			bRlst =(Boolean)i.eval(expStr); 
		}catch(Exception e){
			//LOG.error(e.getMessage());
		}
		catch(Throwable t){
			//LOG.info("BeanShell运行异常："+t.getClass().getName());
			//LOG.error(t.getMessage());
		}
		return bRlst;
	}
	
	private void writeBackData(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("发牌巡检反写状态开始："+sdf.format(new Date()));
		DaoFactory.create(CheckSupervise.class).getSession().update("com.chinacreator.dzjc_ruleEngine.CheckSuperviseMapper.updateCheckStatus");	
		System.out.println("发牌巡检反写状态结束："+sdf.format(new Date()));
	}
	
	
	private List<CheckSupervise> getCheckSuperviseBeanList(Map<String,Integer> paramMap){
		List<CheckSupervise> list = new ArrayList<CheckSupervise>();
		list = DaoFactory.create(CheckSupervise.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.CheckSuperviseMapper.selectCheckSuperviseList",paramMap);
		return list;
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
	}
	
	public static void getMemInfo(String title){
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	    MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
	    System.out.println(title+"，初始化内存=="+(memoryUsage.getInit()/1024/1024)+"，"
	    		+"最大可用内存=="+(memoryUsage.getMax()/1024/1024)+"，"
	    		+"已用内存=="+(memoryUsage.getUsed()/1024/1024));
    }
	
}
