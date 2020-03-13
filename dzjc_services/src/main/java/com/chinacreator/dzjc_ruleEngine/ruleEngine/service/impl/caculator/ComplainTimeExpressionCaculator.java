package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.caculator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.TaJcComplainBaseinfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineCaculatorServiceIfc;
import com.chinacreator.util.HolidayDateUtil;

/**
 * <p>
 * Title: 电子监察系统
 * </p>
 * <p>
 * Description:表达式计算（投诉办理时限监察）实现类
 * </p>
 * <p>
 * Company:湖南科创
 * </p>
 * 
 * @author 谌欣慰
 * @version 1.0
 * @date 2018-4-11
 */
public class ComplainTimeExpressionCaculator implements RuleEngineCaculatorServiceIfc {

	@Override
	public String doCaculator(String elementId,String batchId,List<JcAllbussinesSum> list1) throws Exception {
		StringBuilder exceptions = new StringBuilder("");
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<TaJcComplainBaseinfo> list = new ArrayList<TaJcComplainBaseinfo>();
			List<String> complainIdList = new ArrayList<String>();
			for (JcAllbussinesSum jcAllbussinesSum : list1) {
				String bussinessType = jcAllbussinesSum.getBussinessType();
				if("2".equals(bussinessType)){
					complainIdList.add(jcAllbussinesSum.getBussinessId());
				}
			}
			if(complainIdList.size() >0){
				//批量查询
				list = DaoFactory.create(TaJcComplainBaseinfo.class).getSession()
						.selectList("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectTSJC",complainIdList);
				complainIdList = null;
			}

			List<ResultElement> resultElementList = new ArrayList<ResultElement>();
			ResultElement resultElement = null;
			Date date = null;
			Date acceptTime = null;
			Date endTime = null;
			Double processLimit = null;
			
			for (TaJcComplainBaseinfo complain : list) {
				date = new Date();

				acceptTime = complain.getAcceptTime();//受理时间
				endTime = complain.getEndTime();//完成时间
				processLimit = complain.getProcessLimit();//办理时限
				
				//将当前时间和到期时间转换格式
				String dateString = sdf.format(date);
				String acceptTimeString = sdf.format(acceptTime);
				String endTimeString = "";

				//完成时间为空则引用当前时间
				if (endTime == null) {
					endTimeString = dateString;
				} else {
					endTimeString = sdf.format(endTime);
				}

				long dateInv = 0l;
				long timeInv = 0l;
				long remainInv = 0l;
				long standTimeInv = 0l;
				long[] invTimes = {0,0};

				try {
					invTimes = HolidayDateUtil.getInvTimesWithTime(acceptTimeString, endTimeString,
							complain.getWorkTime(),complain.getHolidayCount());//计算第1步与第2步
					standTimeInv = HolidayDateUtil.getStandardRemainDiff(acceptTimeString.substring(0, 10),
							complain.getWorkTime());//获取1个工作日标准秒长
				} catch (Exception e) {
					exceptions.append("businessType：2，").append(complain.getComplainId()).append("：")
						.append(e.getMessage()).append("；");
					System.err.println(exceptions.toString());
					e.printStackTrace();
				}
				timeInv = ((invTimes[0] * standTimeInv) + invTimes[1]) - processLimit.longValue()*standTimeInv;//计算第4步
				if(standTimeInv == 0){
					dateInv = 0;
					remainInv = 0;
				}
				else {
					dateInv = timeInv/standTimeInv; //计算第4步
					remainInv = timeInv%standTimeInv; //计算第4步
				}
				if (remainInv > 0l) { //计算第4步，如果还有余数，则差异天数加1
					dateInv = dateInv + 1;
				}
				String rltMemo = "办理时限超过投诉时限，超过" + dateInv + "天";

				//将处理后的结果集封装
				resultElement = new ResultElement();
				resultElement.setAreaCode("");
				resultElement.setBusinessId(complain.getComplainId());
				resultElement.setElemId(elementId);
				resultElement.setIsTimesupervise("Y");
				resultElement.setRltValue(String.valueOf(dateInv));
				resultElement.setRltMemo(rltMemo);
				resultElement.setBusinessType("2");
				resultElement.setBatchId(batchId);
				resultElementList.add(resultElement);
			}

			if (resultElementList.size() > 0) {
				DaoFactory.create(ResultElement.class).getSession()
						.insert("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.insertConsultInfoTime",
								resultElementList);
			}
			list = null;
			resultElementList = null;
		} catch (Exception e) {
			System.err.println("投诉发牌规则执行失败："+e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions.toString())){
				exceptions.append("#");
			}
			exceptions.append(e.getMessage());
		}
		return exceptions.toString();
	}
}
