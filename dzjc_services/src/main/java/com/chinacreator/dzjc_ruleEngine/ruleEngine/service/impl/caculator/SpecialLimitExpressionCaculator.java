package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.caculator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineCaculatorServiceIfc;
import com.chinacreator.util.HolidayDateUtil;


/**
 * <p>Title: 电子监察系统</p>
 * <p>Description:表达式计算(特别程序流程实际办理时长是否超过了时限)实现类</p>
 * <p>Company:湖南科创</p>
 * @author 廖龙
 * @version 1.0
 * @date 2018-07-18
 */

public class SpecialLimitExpressionCaculator implements RuleEngineCaculatorServiceIfc {

	//private static final Logger LOG = Logger.getLogger(SpecialLimitExpressionCaculator.class.getName());

	/**
	 * 特殊程序时限监察规则计算
	 * 算法：
	 * 如果特别程序流程实际办理时长超过了时限，则计算结果为true。
	 * @param elemId 
	 * @return
	 * @throws Exception
	 */
	public String doCaculator(String elementId,String batchId,List<JcAllbussinesSum> list1) throws Exception {
		StringBuilder exceptions = new StringBuilder("");
		
		try {
			List<TbcxsqInfoBean> list = new ArrayList<TbcxsqInfoBean>();
			List<String> businessIdList = new ArrayList<String>();
			for (JcAllbussinesSum jcAllbussinesSum : list1) {
				String bussinessType = jcAllbussinesSum.getBussinessType();
				if("4".equals(bussinessType)){
					businessIdList.add(jcAllbussinesSum.getBussinessId());
				}
			}
			if(businessIdList.size() > 0){
				list = DaoFactory.create(TbcxsqInfoBean.class).getSession()
						.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBeanMapper.selectAllById",businessIdList);
				businessIdList = null;
			}
			List<ResultElement> resultElementList = new ArrayList<ResultElement>();
			ResultElement resultElement = null;
			Date date = null;
			Date startTime = null;
			Date endTime = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(TbcxsqInfoBean info : list){
				date = new Date();
				int timeLimit = info.getSpecialTimelimit();
				startTime = info.getStartDate();
				endTime = info.getEndDate();
				//将当前时间和到期时间转换格式
				String dateString = sdf.format(date);
				String startTimeString = sdf.format(startTime);
				String endTimeString = "";
				if (endTime == null) {
					endTimeString = dateString;
				} else {
					endTimeString = sdf.format(endTime);
				}
				long dateInv = 0l;
				long standTimeInv = 0l;
				long diffTime = 0l;
				long remainInv = 0l;
				long[] invTimes = {0,0};
				
				try {
					//计算两个日期值相差天数及秒长
					invTimes = HolidayDateUtil.getInvTimesWithTime(startTimeString,endTimeString,
							info.getWorkTime(),info.getHolidayCount());
					//获取1个工作日标准秒长
					standTimeInv = HolidayDateUtil.getStandardRemainDiff(startTimeString.substring(0,10),
							info.getWorkTime());
				} catch (Exception e) {
					exceptions.append("businessType：4，").append(info.getBusinessId()).append("：")
						.append(e.getMessage()).append("；");
					System.err.println(exceptions.toString());
					e.printStackTrace();
				}
				//实际耗时与特别程序时限差值
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
				String rltMemo = "特别程序流程实际办理时长超过了时限，超过" + dateInv + "天";
				//将处理后的结果集封装
				resultElement = new ResultElement();
				resultElement.setAreaCode("");
				resultElement.setBusinessId(info.getBusinessId());
				resultElement.setElemId(elementId);
				resultElement.setIsTimesupervise("Y");
				resultElement.setRltValue(String.valueOf(dateInv));
				resultElement.setRltMemo(rltMemo);
				resultElement.setBusinessType("4");
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
			System.err.println("特别程序发牌规则执行失败," + e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions.toString())){
				exceptions.append("#");
			}
			exceptions.append(e.getMessage());
		}
		return exceptions.toString();
	}
}
