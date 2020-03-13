package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.caculator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.ElementInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.ElementInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineCaculatorServiceIfc;
import com.chinacreator.util.HolidayDateUtil;

/**
 * <p>
 * Title: 电子监察系统
 * </p>
 * <p>
 * Description:表达式计算（网上办事时限监察）实现类
 * </p>
 * <p>
 * Company:湖南科创
 * </p>
 * 
 * @author 廖龙
 * @version 1.0
 * @date 2018-8-15
 */
public class DzzzTimeExpressionCaculator implements RuleEngineCaculatorServiceIfc {

	// 电子证照默认时限。
	private static String dzzz_time_limit = ConfigManager.getInstance().getConfig("dzzz_time_limit");
	
	/**
	 * 电子证照时限监察 算法： 1、对于需要出证的办件，以出证时间减办结时间，计算超出时间 2、计算超出时间时，必须去掉节假日
	 * 
	 * @param elemId
	 * @throws Exception
	 */
	@Override
	public String doCaculator(String elementId,String batchId,List<JcAllbussinesSum> list1) throws Exception {
		StringBuilder exceptions = new StringBuilder("");
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<InstanceInfo> list = new ArrayList<InstanceInfo>();
			List<String> instanceIdList = new ArrayList<String>();
			for (JcAllbussinesSum jcAllbussinesSum : list1) {
				String bussinessType = jcAllbussinesSum.getBussinessType();
				if("6".equals(bussinessType)){
					instanceIdList.add(jcAllbussinesSum.getBussinessId());
				}
			}
			ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
			if(instanceIdList.size() > 0){
				list = elementInfoDao.getDzzzBegEndTimeByInstanceId(instanceIdList);
				instanceIdList = null;
			}
			List<ResultElement> resultElementList = new ArrayList<ResultElement>();
			ResultElement resultElement = null;
			int supSize = list == null ? 0 : list.size();
			int timeLimit = 3;
			if(!StringUtils.isBlank(dzzz_time_limit)){
				timeLimit = Integer.parseInt(dzzz_time_limit);
			}
			for(int i=0; i<supSize; i++){
				String instanceId = list.get(i).getInstanceId();
				String endTime = ft.format(list.get(i).getEndTime());
				String finishTime = "";
				if(list.get(i).getPretrialTime() == null){
					finishTime = ft.format(new Date());
				}else{
					finishTime = ft.format(list.get(i).getPretrialTime());
				}
				long dateInv = 0l;
				long remainInv = 0l;
				long diffTime = 0l;
				long standTimeInv = 0l;
				long[] invTimes = {0,0};
				try {
					//计算两个日期值相差天数及秒长
					invTimes = HolidayDateUtil.getInvTimesWithTime(endTime,finishTime,
							list.get(i).getWorkTime(),list.get(i).getHolidayCount());
					//获取1个工作日标准秒长
					standTimeInv = HolidayDateUtil.getStandardRemainDiff(endTime.substring(0,10),
							list.get(i).getWorkTime());
				} catch (Exception e) {
					exceptions.append("businessType：6，").append(instanceId).append("：")
						.append(e.getMessage()).append("；");
					System.err.println(exceptions.toString());
					e.printStackTrace();
				}
				//实际耗时与默认时限差值
				diffTime = ((invTimes[0]*standTimeInv)+invTimes[1]) - timeLimit*standTimeInv; 
				if(standTimeInv == 0){
					dateInv = 0;
					remainInv = 0;
				}
				else {
					dateInv = diffTime/standTimeInv; //计算第4步
					remainInv = diffTime%standTimeInv; //计算第4步
				}
				if(remainInv > 0l){ //如果还有余数，则天数加1
					dateInv = dateInv + 1;
				}
				String rltMemo = "办件出证时限超过承诺时限，超过" + dateInv + "天";
				//将处理后的结果集封装
				resultElement = new ResultElement();
				resultElement.setAreaCode("");
				resultElement.setBusinessId(instanceId);
				resultElement.setElemId(elementId);
				resultElement.setIsTimesupervise("Y");
				resultElement.setRltValue(String.valueOf(dateInv));
				resultElement.setRltMemo(rltMemo);
				resultElement.setBusinessType("6");
				resultElement.setBatchId(batchId);
				resultElementList.add(resultElement);
			}

			if (resultElementList.size() > 0) {
				DaoFactory.create(ResultElement.class).getSession().insert("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.insertConsultInfoTime",
								resultElementList);
			}
			list = null;
			resultElementList = null;
		} catch (Exception e) {
			System.err.println("电子证照发牌规则执行失败," + e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions.toString())){
				exceptions.append("#");
			}
			exceptions.append(e.getMessage());
		}
		return exceptions.toString();
	}

}
