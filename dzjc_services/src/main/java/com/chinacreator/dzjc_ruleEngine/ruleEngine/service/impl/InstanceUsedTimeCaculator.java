package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.dzjc_ruleEngine.CheckSupervise;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.ElementInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.ElementInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleEngineInitAndTearDownBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineInitAndTearDownServiceIfc;
import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;
import com.chinacreator.util.HolidayDateUtil;



/**
 *<p>Title:</p>
 *<p>Description:计算审批项目的实例的实际处理时长</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-1
 */
public class InstanceUsedTimeCaculator implements RuleEngineInitAndTearDownServiceIfc {
	
	//private static final Logger LOG = Logger.getLogger(InstanceUsedTimeCaculator.class.getName());
	
	/**
	 * 计算审批项目实际处理时长
	 * 算法：
	 * 1、先计算最后时间与受理时间差异的天数（去掉节假日）。
	 * 2、再计算受理当天耗时数加上最后时间耗时数。
	 * 3、计算特别程序所耗时间。
	 * 4、用1+2-3计算实际耗时，小数进1
	 * 5、将计算结果更新到办件实例表
	 */

	public String start(RuleEngineInitAndTearDownBean ruleEngineInitAndTearDownBean,List<JcAllbussinesSum> list1) throws Exception{
		StringBuilder exceptions = new StringBuilder("");
		//获取办件业务的业务ID
		List<String> listInstanceId=new ArrayList<String>();
		for (JcAllbussinesSum jcAllbussinesSum : list1) {
			if("1".equals(jcAllbussinesSum.getBussinessType())){
				listInstanceId.add(jcAllbussinesSum.getBussinessId());
			}
		}
		
		ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
		//特别程序的计算时长
		String tbcxTimes = "";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String is_useTimeLimit = ruleEngineInitAndTearDownBean.getIsUseTimeLimit();
		//String areaCode = ruleEngineInitAndTearDownBean.getAreaCode();
		//PreparedDBUtil preDbUtil = new PreparedDBUtil();
		try{
			//查询指定区域所有审批实例的实例ID，开始受理时间和完成结束时间
			List<InstanceInfo> superviseInfoList = null;
			List<TbcxsqInfoBean> tbcxList = null;
			if(listInstanceId.size() > 0){
				superviseInfoList = elementInfoDao.getInstanceBegEndTimeByInstanceId(listInstanceId);
				//批量查询出特别程序列表
				tbcxList = elementInfoDao.queryTbcxsqlist(listInstanceId);
			}
			int supSize = superviseInfoList == null ? 0 : superviseInfoList.size();
			for(int i=0; i<supSize; i++){
				String instanceId = superviseInfoList.get(i).getInstanceId();
				String acceptTime = ft.format(superviseInfoList.get(i).getAcceptTime());
				String finishTime = "";
				if(superviseInfoList.get(i).getEndTime() == null){
					finishTime = ft.format(new Date());
				}else{
					finishTime = ft.format(superviseInfoList.get(i).getEndTime());
				}
				long dateInv = 0l;
				long timeInv = 0l;
				long remainInv = 0l;
				long standTimeInv = 0l;
				long[] invTimes = {0,0};
				long[] splTimes = {0,0};
				
				try {
					invTimes = HolidayDateUtil.getInvTimesWithTime(acceptTime,finishTime,
							superviseInfoList.get(i).getWorkTime(), superviseInfoList.get(i).getHolidayCount());//计算第1步与第2步
					standTimeInv = HolidayDateUtil.getStandardRemainDiff(acceptTime.substring(0,10),
							superviseInfoList.get(i).getWorkTime());//获取1个工作日标准秒长
					splTimes = procSpecialTime(instanceId,tbcxList,
							standTimeInv); //计算第3步
				} catch (Exception e) {
					exceptions.append("businessType：1，").append(instanceId).append("：")
						.append(e.getMessage()).append("；");
					System.err.println(exceptions.toString());
					e.printStackTrace();
				}
				tbcxTimes = splTimes[0]+"天"+splTimes[1]+"秒";
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
				//System.out.println("办件id :"+instanceId+"所用时长 :"+dateInv);
				//数据操作使用批处理
				//elementInfoDao.updUsedTime(instanceId,String.valueOf(dateInv));
				superviseInfoList.get(i).setTimeUse(new Double(dateInv));
				superviseInfoList.get(i).setTbcxTimes(tbcxTimes);
			}
			if(supSize > 0){
				//批量修改办件的所有时长
				elementInfoDao.batchUpdateUsedTime(superviseInfoList);
				superviseInfoList = null;
			}
		}catch(Exception e){
			System.err.println("办件发牌规则执行失败," + e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions.toString())){
				exceptions.append("#");
			}
			exceptions.append(e.getMessage());
		}
		return exceptions.toString();
	}
	public CheckSupervise caculateUsedTime(CheckSupervise check,List<TbcxsqInfoBean> tbcxList){
		long dateInv = 0l;
		long timeInv = 0l;
		long remainInv = 0l;
		long standTimeInv = 0l;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String acceptTime = ft.format(check.getAcceptTime());
			String finishTime = "";
			String instanceId = check.getBusinessId();
			if(check.getEndTime() == null){
				finishTime = ft.format(new Date());
			}else{
				finishTime = ft.format(check.getEndTime());
			}
			
			long[] invTimes = HolidayDateUtil.getInvTimesWithTime(acceptTime,finishTime,
					check.getWorkTime(),check.getHolidayCount());//计算第1步与第2步
			standTimeInv = HolidayDateUtil.getStandardRemainDiff(acceptTime.substring(0,10),
					check.getWorkTime());//获取1个工作日标准秒长
			long[] splTimes = procSpecialTime(instanceId,tbcxList,standTimeInv); //计算第3步
			timeInv = ((invTimes[0]*standTimeInv)+invTimes[1]) - ((splTimes[0]*standTimeInv)+splTimes[1]);//计算第4步
			dateInv = timeInv/standTimeInv; //计算第4步
			remainInv = timeInv%standTimeInv; //计算第4步
			if(remainInv > 0l){ //计算第4步，如果还有余数，则差异天数加1
				dateInv = dateInv + 1;
			}
			check.setUseTime(new Double(dateInv >= 0 ? dateInv : 0));
		} catch (Exception e) {
			check.setUseTime(new Double(0));
			e.printStackTrace();
		}
		return check;
	}
	/**
	 * 处理特别程序时间
	 * 注意：同一办件可能有多次特别程序申请
	 * 每次特别程序申请与特别程序结果都是对应的，但是如果还没有产生结果，说明特别程序还没有处理完，则取当前时间减特别程序申请时间为实际耗时
	 * @param instanceId
	 * @throws Exception
	 */
	public long[] procSpecialTime(String instanceId) throws Exception {
		ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
		long[] specialTime = new long[2];
		long splDate = 0l;
		long splTime = 0l;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
	    List <TbcxsqInfoBean> tbcxsqList = elementInfoDao.getTbcxsqInfo(instanceId);

		for(int i=0; i<tbcxsqList.size(); i++){
			//String tscxsqId = tbcxsqList.get(i).getSuspendId();
			String startDate = ft.format(tbcxsqList.get(i).getStartDate());
			//String endDate = elementInfoDao.getTbcxjgEndDate(tscxsqId, instanceId);
			String endDate = tbcxsqList.get(i).getEndDate() == null ? "" : ft.format(tbcxsqList.get(i).getEndDate());
			if(StringUtils.isBlank(endDate)) {
				endDate = DateUtil.getCurrentDateTime();
			}
			long[] invTimes = HolidayDateUtil.getInvTimesWithTime(startDate,endDate);
			splDate = splDate + invTimes[0];
			splTime = splTime + invTimes[1];
		}
		specialTime[0] = splDate;
		specialTime[1] = splTime;
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return specialTime;
	}

	/**
	 * 处理特别程序时间
	 * 注意：同一办件可能有多次特别程序申请
	 * 每次特别程序申请与特别程序结果都是对应的，但是如果还没有产生结果，说明特别程序还没有处理完，则取当前时间减特别程序申请时间为实际耗时
	 * @param instanceId
	 * @throws Exception
	 */
	public long[] procSpecialTime(String instanceId,List<TbcxsqInfoBean> tbcxList, 
			long standTimeInv) throws Exception {
		long[] specialTime = new long[2];
		long splDate = 0l;
		long splTime = 0l;
		long dateInv = 0l;
		long timeInv = 0l;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			if(tbcxList == null || tbcxList.size() == 0){
				specialTime[0] = splDate;
				specialTime[1] = splTime;
				return specialTime;
			}
			for(int i=0; i<tbcxList.size(); i++){
				if(instanceId.equals(tbcxList.get(i).getInstanceId())){
					String startDate = ft.format(tbcxList.get(i).getStartDate());
					String endDate = tbcxList.get(i).getEndDate() == null ? "" : ft.format(tbcxList.get(i).getEndDate());
					if(StringUtils.isBlank(endDate)) {
						endDate = DateUtil.getCurrentDateTime();
					}
					long[] invTimes = HolidayDateUtil.getInvTimesWithTime(startDate,endDate,
							tbcxList.get(i).getWorkTime(),tbcxList.get(i).getHolidayCount());
					dateInv = invTimes[0];
					timeInv = invTimes[1];
					//如果特别程序时间已经超过申请的天数，则以申请的天数计算
					if((dateInv + timeInv/standTimeInv) >= tbcxList.get(i).getSpecialTimelimit()){
						dateInv = tbcxList.get(i).getSpecialTimelimit();
						timeInv = 0l;
					}
					splDate = splDate + dateInv;
					splTime = splTime + timeInv;
				}
			}
			specialTime[0] = splDate;
			specialTime[1] = splTime;
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return specialTime;
	}

	public void clear(RuleEngineInitAndTearDownBean ruleEngineInitAndTearDownBean) throws Exception{
	
		
	}

}
