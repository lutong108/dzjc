package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.caculator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.ElementInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.ElementInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineCaculatorServiceIfc;
import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;
import com.chinacreator.util.HolidayDateUtil;

/**
 * <p>
 * Title: 电子监察系统
 * </p>
 * <p>
 * Description:表达式计算（咨询时限监察）实现类
 * </p>
 * <p>
 * Company:湖南科创
 * </p>
 * 
 * @author 符永胜
 * @version 1.0
 * @date 2018-4-10
 */
public class ConsultTimeExpressionCaculator implements RuleEngineCaculatorServiceIfc {

	/**
	 * 咨询时限监察 算法： 1、对于咨询事件，以系统当前时间减到期时间，计算超出时间 2、计算超出时间时，必须去掉节假日、特别程序时间
	 * 
	 * @param elemId
	 * @throws Exception
	 */
	@Override
	public String doCaculator(String elementId,String batchId,List<JcAllbussinesSum> list1) throws Exception {
		StringBuilder exceptions = new StringBuilder("");
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<ConsultInfo> list = new ArrayList<ConsultInfo>();
			List<String> consultIdList = new ArrayList<String>();
			for (JcAllbussinesSum jcAllbussinesSum : list1) {
				String bussinessType = jcAllbussinesSum.getBussinessType();
				if("3".equals(bussinessType)){
					consultIdList.add(jcAllbussinesSum.getBussinessId());
				}
			}
			if(consultIdList.size() > 0){
				list = DaoFactory.create(ConsultInfo.class).getSession()
						.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfoMapper.selectAllById",consultIdList);
				consultIdList = null;
			}
			//1.获取咨询信息，条件为回复时间为空以及当前时间大于到期时间
			//List<ConsultInfo> list = DaoFactory.create(ConsultInfo.class).getSession()
			//		.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfoMapper.selectAll");
			List<ResultElement> resultElementList = new ArrayList<ResultElement>();
			ResultElement resultElement = null;
			Date date = null;
			Date consultTime = null;
			Date replyTime = null;
			Double consultLimit = null;
			for (ConsultInfo consultInfo : list) {
				date = new Date();
				//获取咨询时间
				consultTime = consultInfo.getConsultTime();
				consultLimit = consultInfo.getConsultLimit();
				replyTime = consultInfo.getReplyTime();
				//将当前时间和到期时间转换格式
				String dateString = sdf.format(date);
				String consultTimeString = sdf.format(consultTime);
				String replyTimeString = "";
				if (replyTime == null) {
					replyTimeString = dateString;
				} else {
					replyTimeString = sdf.format(replyTime);
				}

				long dateInv = 0l;
				long timeInv = 0l;
				long remainInv = 0l;
				long standTimeInv = 0l;
				long[] invTimes = {0,0};

				try {
					invTimes = HolidayDateUtil.getInvTimesWithTime(consultTimeString, replyTimeString,
							consultInfo.getWorkTime(),consultInfo.getHolidayCount());//计算第1步与第2步
					standTimeInv = HolidayDateUtil.getStandardRemainDiff(consultTimeString.substring(0, 10),
							consultInfo.getWorkTime());//获取1个工作日标准秒长
				} catch (Exception e) {
					exceptions.append("businessType：3，").append(consultInfo.getConsultId())
						.append("：").append(e.getMessage()).append("；");
					System.err.println(exceptions.toString());
					e.printStackTrace();
				}
				timeInv = ((invTimes[0] * standTimeInv) + invTimes[1]) - consultLimit.longValue()*standTimeInv;//计算第4步
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
				String rltMemo = "办理时限超过咨询时限，超过" + dateInv + "天";
				//将处理后的结果集封装
				resultElement = new ResultElement();
				resultElement.setAreaCode("");
				resultElement.setBusinessId(consultInfo.getConsultId());
				resultElement.setElemId(elementId);
				resultElement.setIsTimesupervise("Y");
				resultElement.setRltValue(String.valueOf(dateInv));
				resultElement.setRltMemo(rltMemo);
				resultElement.setBusinessType("3");
				resultElement.setBatchId(batchId);
				resultElementList.add(resultElement);
			}

			if (resultElementList.size() > 0) {
				DaoFactory.create(ResultElement.class).getSession().insert("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.insertConsultTime",
								resultElementList);
			}
			list = null;
			resultElementList = null;
		} catch (Exception e) {
			System.err.println("咨询发牌规则执行失败："+e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions.toString())){
				exceptions.append("#");
			}
			exceptions.append(e.getMessage());
		}
		return exceptions.toString();
	}

	/**
	 * 处理特别程序时间 注意：同一办件可能有多次特别程序申请
	 * 每次特别程序申请与特别程序结果都是对应的，但是如果还没有产生结果，说明特别程序还没有处理完，则取当前时间减特别程序申请时间为实际耗时
	 * 
	 * @param instanceId
	 * @throws Exception
	 */
	public long[] procSpecialTime(String instanceId) throws Exception {
		ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
		long[] specialTime = new long[2];
		long splDate = 0l;
		long splTime = 0l;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<TbcxsqInfoBean> tbcxsqList = elementInfoDao.getTbcxsqInfo(instanceId);

			for (int i = 0; i < tbcxsqList.size(); i++) {
				//String tscxsqId = tbcxsqList.get(i).getSuspendId();
				String startDate = ft.format(tbcxsqList.get(i).getStartDate());
				String endDate = tbcxsqList.get(i).getEndDate() == null ? "" : ft.format(tbcxsqList.get(i).getEndDate());
				if ("".equals(endDate) || endDate == null) {
					endDate = DateUtil.getCurrentDateTime();
				}
				long[] invTimes = HolidayDateUtil.getInvTimesWithTime(startDate, endDate);
				splDate = splDate + invTimes[0];
				splTime = splTime + invTimes[1];
			}
			specialTime[0] = splDate;
			specialTime[1] = splTime;
		} catch (Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return specialTime;
	}

}
