package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.caculator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.ElementInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.ElementInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.HuanJieResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.HuanJieEngineCaculatorServiceIfc;
import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;
import com.chinacreator.util.HolidayDateUtil;

/**
 * <p>
 * Title: 电子监察系统
 * </p>
 * <p>
 * Description:表达式计算（环节时限监察）实现类
 * </p>
 * <p>
 * Company:湖南科创
 * </p>
 * 
 * @author liaolong
 * @version 1.0
 * @date 2019-1-15
 */
public class HuanJieExpressionCaculator implements HuanJieEngineCaculatorServiceIfc {

	/**
	 * 计算环节实际处理时长
	 * 算法：
	 * 1、先计算最后时间与受理时间差异的天数（去掉节假日）。
	 * 2、再计算受理当天耗时数加上最后时间耗时数。
	 * 3、计算特别程序所耗时间。
	 * 4、用1+2-3计算实际耗时，小数进1
	 * 5、将计算结果更新到环节表
	 */
	@Override
	public String doCaculator(String elementId,String batchId,List<ResultOpinion> list) throws Exception {
		StringBuilder exceptions = new StringBuilder("");
		
		ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
		List<HuanJieResultElement> resultElementList = new ArrayList<HuanJieResultElement>();
		HuanJieResultElement resultElement = null;
		
		//特别程序的计算时长
		String tbcxTimes = "";
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			List<TbcxsqInfoBean> tbcxList = null;
			//获取办件业务的业务ID
			List<String> listHuanJieId = new ArrayList<String>();
			for (ResultOpinion resultOpinion : list) {
				listHuanJieId.add(resultOpinion.getOpinionId());
			}
			if(listHuanJieId.size() > 0){
				//批量查询出特别程序列表
				tbcxList = elementInfoDao.queryHuanJieTbcxsqlist(listHuanJieId);
			}
			java.sql.Date superviseTime = com.chinacreator.util.DateUtil.dateToDate(new Date());
			int supSize = list == null ? 0 : list.size();
			Double huanjieLimit = null;
			for(int i=0; i<supSize; i++){
				String huanJieId = list.get(i).getOpinionId();
				String acceptTime = ft.format(list.get(i).getBeginTime());
				String finishTime = "";
				huanjieLimit = list.get(i).getLinkTime();
				if(list.get(i).getOpinionTime() == null){
					finishTime = ft.format(new Date());
				}else{
					finishTime = ft.format(list.get(i).getOpinionTime());
				}
				long dateInv = 0l;
				long timeInv = 0l;
				long remainInv = 0l;
				long standTimeInv = 0l;
				int outDays = 0;
				long[] invTimes = {0,0};
				long[] splTimes = {0,0};
				
				try {
					invTimes = HolidayDateUtil.getInvTimesWithTime(acceptTime,finishTime,
							list.get(i).getWorkTime(), list.get(i).getHolidayCount());//计算第1步与第2步
					standTimeInv = HolidayDateUtil.getStandardRemainDiff(acceptTime.substring(0,10),
							list.get(i).getWorkTime());//获取1个工作日标准秒长
					splTimes = procSpecialTime(huanJieId,tbcxList,
							standTimeInv); //计算第3步
				} catch (Exception e) {
					exceptions.append("businessType：7，").append(huanJieId).append("：")
						.append(e.getMessage()).append("；");
					System.err.println(exceptions.toString());
					e.printStackTrace();
				}
				tbcxTimes = splTimes[0]+"天"+splTimes[1]+"秒";
				timeInv = ((invTimes[0]*standTimeInv)+invTimes[1]) 
							- ((splTimes[0]*standTimeInv)+splTimes[1]);//计算第4步
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
				list.get(i).setTimeUse(new Double(dateInv));
				list.get(i).setSuperviseTime(superviseTime);
				//计算超期时间
				outDays = new Double(new Double(dateInv) - huanjieLimit).intValue();
				String rltMemo = "环节办理超过环节时限，超过" + outDays + "天";
				//将处理后的结果集封装
				resultElement = new HuanJieResultElement();
				resultElement.setAreaCode("");
				resultElement.setBusinessId(list.get(i).getOpinionId());
				resultElement.setElemId(elementId);
				resultElement.setIsTimesupervise("Y");
				resultElement.setRltValue(String.valueOf(outDays));
				resultElement.setRltMemo(rltMemo);
				resultElement.setBusinessType("7");
				resultElement.setBatchId(batchId);
				resultElement.setTbcxTimes(tbcxTimes);
				resultElementList.add(resultElement);
			}
			if(supSize > 0){
				//批量修改环节的所用时长以及监察时间
				DaoFactory.create(ResultElement.class).getSession()
				.update("com.chinacreator.dzjc_ruleEngine.ResultOpinionMapper.batchUpdateTimeUse",
								list);
				list = null;
			}
			if (resultElementList.size() > 0) {
				DaoFactory.create(ResultElement.class).getSession()
				.insert("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.HuanJieResultElementMapper.insertBatch",
								resultElementList);
			}
			list = null;
			resultElementList = null;
		}catch(Exception e){
			System.err.println("环节发牌规则执行失败," + e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions.toString())){
				exceptions.append("#");
			}
			exceptions.append(e.getMessage());
		}
		return exceptions.toString();
	}

	/**
	 * 处理特别程序时间
	 * 注意：同一办件可能有多次特别程序申请
	 * 每次特别程序申请与特别程序结果都是对应的，但是如果还没有产生结果，说明特别程序还没有处理完，则取当前时间减特别程序申请时间为实际耗时
	 * @param instanceId
	 * @throws Exception
	 */
	public long[] procSpecialTime(String huanJieId,List<TbcxsqInfoBean> tbcxList, 
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
				if(huanJieId.equals(tbcxList.get(i).getBusinessId())){
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
	public static void main(String[] arsg){
		long dateInv = 5;
		double huanjieLimit = 8;
		int outDays = new Double(new Double(dateInv) - huanjieLimit).intValue();
		System.out.println(outDays);
	}
}
