package com.chinacreator.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.HolidayQuery;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.WorkDateQuery;
/**
 * <p>Title: 电子监察系统</p>
 * <p>Description:节假日工作日计算工具类</p>
 * <p>创建日期:2011-07-30</p>
 * @author xianlu.lu
 * @version 1.0
 * <p>科创</p>
 */
public class HolidayDateUtil {
	
	//FIXME yin.liu 需要做 testcase

	/**
	 * 求两个日期相差天数 
	 * 日期格式：yyyy-MM-dd hh:mm:ss
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @throws Exception
	 */
	public static long getInvDaysWithTime(String dateStart, String dateEnd) throws Exception {
		long dateInv = 0l;
		String dateStartTime = null;
		String dateEndTime = null;
		dateStartTime = dateStart.substring(0,10);
		dateEndTime = dateEnd.substring(0,10);
		dateInv = HolidayDateUtil.getInvDaysEspHolidy(dateStartTime,dateEndTime);
		dateInv = dateInv - 2; //只取位于中间的天数
		long standTimeInv = HolidayDateUtil.getStandardRemainDiff(dateStartTime);
		long timeInv = HolidayDateUtil.getRemainDiffByWorkday(dateStart) + HolidayDateUtil.getUsedDiffByWorkday(dateEnd);
		if((timeInv > standTimeInv) && (timeInv <= 2*standTimeInv)){//所耗小时数加起来超过1个工作日
			dateInv = dateInv + 2;
		}else if(timeInv <= standTimeInv){ //所耗小时数加起来不超过1个工作日，则算1工作日
			dateInv = dateInv + 1;
		}
		return dateInv;
	}


	/**
	 * 求两个日期相差天数与秒钟数 
	 * 日期格式：yyyy-MM-dd hh:mm:ss
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 * @throws Exception
	 */
	public static long[] getInvTimesWithTime(String dateStart, String dateEnd) throws Exception {
		long[] invTimes = new long[2];
		String dateStartTime = null;
		String dateEndTime = null;
		dateStartTime = dateStart.substring(0,10);
		dateEndTime = dateEnd.substring(0,10);
		long dateInv = HolidayDateUtil.getInvDaysEspHolidy(dateStartTime,dateEndTime);
		dateInv = dateInv - 2; //只取位于中间的天数
		long timeInv = HolidayDateUtil.getRemainDiffByWorkday(dateStart) + HolidayDateUtil.getUsedDiffByWorkday(dateEnd);
		invTimes[0] = dateInv;
		invTimes[1] = timeInv;
		return invTimes;
	}

	public static long[] getInvTimesWithTime(String dateStart, String dateEnd, 
			String workTime, int holidayCount) throws Exception {
		long[] invTimes = new long[2];
		String dateStartTime = null;
		String dateEndTime = null;
		dateStartTime = dateStart.substring(0,10);
		dateEndTime = dateEnd.substring(0,10);
		long dateInv = HolidayDateUtil.getInvDaysEspHolidy(dateStartTime,dateEndTime,holidayCount);
		dateInv = dateInv - 2; //只取位于中间的天数
		long timeInv = HolidayDateUtil.getRemainDiffByWorkday(dateStart,workTime) + HolidayDateUtil.getUsedDiffByWorkday(dateEnd,workTime);
		invTimes[0] = dateInv;
		invTimes[1] = timeInv;
		return invTimes;
	}

	/**
	 * 求两个日期相差天数 
	 * 去掉节假日
	 * @param dateStart 起始日期，格式yyyy-MM-dd
	 * @param dateEnd 终止日期，格式yyyy-MM-dd
	 * @return 两个日期相差天数
	 */
	public static long getInvDaysEspHolidy(String dateStart, String dateEnd) throws Exception {
		long interval = 0l;
		long absInterval = DateUtil.getIntervalDays(dateStart,dateEnd);
		long holidays = getHolidays(dateStart,dateEnd);
//		for(int i=0; i<absInterval; i++){
//			if(!isHoliday(dateStart,i)){
//				interval++;
//			}
//		}
		interval = absInterval - holidays;
		return interval;
	}
	
	public static long getInvDaysEspHolidy(String dateStart, String dateEnd, int holidayCount) throws Exception {
		long interval = 0l;
		long absInterval = DateUtil.getIntervalDays(dateStart,dateEnd);
		long holidays = new Long(holidayCount);
		interval = absInterval - holidays;
		return interval;
	}

	/**
	 * 获取两个日期间节假日的天数
	 * @param dateStart，dateEnd
	 * @return holidays
	 * @throws Exception
	 */
	public static long getHolidays(String dateStart, String dateEnd) throws Exception {
		HolidayQuery queryParams = new HolidayQuery();
		queryParams.setStartDate(dateStart);
		queryParams.setEndDate(dateEnd);
		long holidays = DaoFactory.create(HolidayQuery.class).count(queryParams);
		return holidays;
	}
	/**
	 * 判断给定日期是否是节假日
	 * @param sdate
	 * @return
	 * @throws Exception
	 */
	public static boolean isHoliday(String sdate) throws Exception {
		boolean isHol = false;
		HolidayQuery queryParams = new HolidayQuery();
		queryParams.setHoliday(sdate);
		long holidays = DaoFactory.create(HolidayQuery.class).count(queryParams);
		if(holidays > 0) isHol =true;
		return isHol;
	}

	/**
	 * 获取从指定日期后指定工作日的时间
	 * @param feedbackLimit 反馈时限
	 * @throws Exception
	 */
	public static String getDate(int feedbackLimit) throws Exception {
		String result = null;
		int i = 1;
		int j = 1;
		boolean flag = true;
		while (flag) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			Calendar c = Calendar.getInstance();
	        c.setTime(today);
	        c.add(Calendar.DAY_OF_MONTH, i);// 今天+i天
	        Date iday = c.getTime();
	        result = sdf.format(iday);
	        if (!isHoliday(result.substring(0,10))) {
				if (j == feedbackLimit) {
					flag = false;
				} else {
					j++;
				}
			}
			i++;
		}
		return result;
	}


	/**
	 * 判断给定日期、位移
	 * 判断是否是节假日
	 * @param sdate
	 * @return
	 * @throws Exception
	 */
	public static boolean isHoliday(String sdate, int interval) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, interval);
        String result = format.format(c.getTime());

		return isHoliday(result);
	}


	/**
	 * 取得给定日期的工作时间区间设置
	 * @param sdate
	 * @return
	 */
	public static String[] getWorkDateSet(String sdate) throws Exception {
		WorkDateQuery queryParams = new WorkDateQuery();
		queryParams.setQueryDate(sdate);
		List<WorkDateQuery> workDatelist = DaoFactory.create(WorkDateQuery.class).select(queryParams);
		String[] workDateGroup = null;
		if(workDatelist.size() <= 0){
			throw new Exception("工作时间没有设置，请检查！");
		}
		WorkDateQuery workDate = workDatelist.get(0);
		workDateGroup = new String[]{workDate.getAmBeginTime(),workDate.getAmEndTime(),workDate.getPmBeginTime(),workDate.getPmEndTime()};
		return workDateGroup;
	}


	/**
	 * 获取给定日期的工作设置时间差
	 * @param date
	 * @return
	 */
	public static long getStandardRemainDiff(String sdate) throws Exception {
		long stanInv = 0l;
		String[] timeSet = getWorkDateSet(sdate);
		String amBegin = DateUtil.getFormartDateStrHM(sdate,timeSet[0]);
		String amEnd = DateUtil.getFormartDateStrHM(sdate,timeSet[1]);
		String bmBegin = DateUtil.getFormartDateStrHM(sdate,timeSet[2]);
		String bmEnd = DateUtil.getFormartDateStrHM(sdate,timeSet[3]);
		long amInv = DateUtil.getDiff(amBegin, amEnd);
		long bmInv = DateUtil.getDiff(bmBegin, bmEnd);
		stanInv = amInv + bmInv;
		return stanInv;
	}

	/**
	 * 获取给定日期的工作设置时间差
	 * @param date
	 * @return
	 */
	public static long getStandardRemainDiff(String sdate,String workTime) throws Exception {
		long stanInv = 0l;
		if(StringUtil.isBlank(workTime)){
			throw new Exception("工作时间没有设置，请检查！");
		}
		String[] timeSet = workTime.split("##");
		if(timeSet.length < 4){
			throw new Exception("工作时间设置不正确，请检查！");
		}
		String amBegin = DateUtil.getFormartDateStrHM(sdate,timeSet[0]);
		String amEnd = DateUtil.getFormartDateStrHM(sdate,timeSet[1]);
		String bmBegin = DateUtil.getFormartDateStrHM(sdate,timeSet[2]);
		String bmEnd = DateUtil.getFormartDateStrHM(sdate,timeSet[3]);
		long amInv = DateUtil.getDiff(amBegin, amEnd);
		long bmInv = DateUtil.getDiff(bmBegin, bmEnd);
		stanInv = amInv + bmInv;
		return stanInv;
	}

	/** 
	 * 获取时间串离下班时间的差值，单位为秒
	 * 这里只考虑给定时间是在上下班时间范围内的情况，超出上下班时间点的，在进入此方法时就应做适当处理
	 * @param sdate 待处理时间 yyyy-MM-dd HH:mm:ss
	 * @return 给定时间离下班时间差值(秒)
	 */
	public static long getRemainDiffByWorkday(String sdate) throws Exception {
		long remainInterval = 0l;
		String sd = sdate.substring(0,10);
		String[] timeSet = getWorkDateSet(sd);
		String amBegin = DateUtil.getFormartDateStrHM(sd,timeSet[0]);
		String amEnd = DateUtil.getFormartDateStrHM(sd,timeSet[1]);
		String bmBegin = DateUtil.getFormartDateStrHM(sd,timeSet[2]);
		String bmEnd = DateUtil.getFormartDateStrHM(sd,timeSet[3]);

		if(DateUtil.isDateBefore(sdate, amBegin)){ //上午上班之前就办理的任务(1标准工作日)
			long amInterval = DateUtil.getDiff(amBegin, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, bmEnd);
			remainInterval = amInterval + bmInterval;
		}else if(DateUtil.isDateBefore(sdate, amEnd)){ //上午处理的任务（上午加下午）
			long amInterval = DateUtil.getDiff(sdate, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, bmEnd);
			remainInterval = amInterval + bmInterval;
		}else if(DateUtil.isDateBefore(sdate, bmBegin)){//中午休息时间（下午）
			remainInterval = DateUtil.getDiff(bmBegin, bmEnd);
		}else if(DateUtil.isDateBefore(sdate, bmEnd)){//下午处理的任务（下午）
			remainInterval = DateUtil.getDiff(sdate, bmEnd);
		}else if(DateUtil.isDateBefore(bmEnd, sdate)){//下班以后加班办理，不计时
			remainInterval = 0l;
		}
		return remainInterval;
	}

	/** 
	 * 获取时间串离下班时间的差值，单位为秒
	 * 这里只考虑给定时间是在上下班时间范围内的情况，超出上下班时间点的，在进入此方法时就应做适当处理
	 * @param sdate 待处理时间 yyyy-MM-dd HH:mm:ss
	 * @return 给定时间离下班时间差值(秒)
	 */
	public static long getRemainDiffByWorkday(String sdate, String workTime) throws Exception {
		long remainInterval = 0l;
		String sd = sdate.substring(0,10);
		if(StringUtil.isBlank(workTime)){
			throw new Exception("工作时间没有设置，请检查！");
		}
		String[] timeSet = workTime.split("##");
		if(timeSet.length < 4){
			throw new Exception("工作时间设置不正确，请检查！");
		}
		String amBegin = DateUtil.getFormartDateStrHM(sd,timeSet[0]);
		String amEnd = DateUtil.getFormartDateStrHM(sd,timeSet[1]);
		String bmBegin = DateUtil.getFormartDateStrHM(sd,timeSet[2]);
		String bmEnd = DateUtil.getFormartDateStrHM(sd,timeSet[3]);

		if(DateUtil.isDateBefore(sdate, amBegin)){ //上午上班之前就办理的任务(1标准工作日)
			long amInterval = DateUtil.getDiff(amBegin, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, bmEnd);
			remainInterval = amInterval + bmInterval;
		}else if(DateUtil.isDateBefore(sdate, amEnd)){ //上午处理的任务（上午加下午）
			long amInterval = DateUtil.getDiff(sdate, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, bmEnd);
			remainInterval = amInterval + bmInterval;
		}else if(DateUtil.isDateBefore(sdate, bmBegin)){//中午休息时间（下午）
			remainInterval = DateUtil.getDiff(bmBegin, bmEnd);
		}else if(DateUtil.isDateBefore(sdate, bmEnd)){//下午处理的任务（下午）
			remainInterval = DateUtil.getDiff(sdate, bmEnd);
		}else if(DateUtil.isDateBefore(bmEnd, sdate)){//下班以后加班办理，不计时
			remainInterval = 0l;
		}
		return remainInterval;
	}

	/** 
	 * 获取办理时间离开班时间的差值，单位为秒
	 * 这里只考虑给定时间是在上下班时间范围内的情况，超出上下班时间点的，在进入此方法时就应做适当处理
	 * @param sdate 待处理时间 yyyy-MM-dd HH:mm:ss
	 * @return 给定时间离下班时间差值(秒)
	 */
	public static long getUsedDiffByWorkday(String sdate) throws Exception {
		long usedInterval = 0l;
		String sd = sdate.substring(0,10);
		String[] timeSet = getWorkDateSet(sd);
		String amBegin = DateUtil.getFormartDateStrHM(sd,timeSet[0]);
		String amEnd = DateUtil.getFormartDateStrHM(sd,timeSet[1]);
		String bmBegin = DateUtil.getFormartDateStrHM(sd,timeSet[2]);
		String bmEnd = DateUtil.getFormartDateStrHM(sd,timeSet[3]);
		if(DateUtil.isDateBefore(sdate, amBegin)){ //上午上班之前就办理的任务
			usedInterval = 0l;
		}else if(DateUtil.isDateBefore(sdate, amEnd)){ //上午处理的任务
			usedInterval = DateUtil.getDiff(amBegin, sdate);
		}else if(DateUtil.isDateBefore(sdate, bmBegin)){//中午休息时间
			usedInterval = DateUtil.getDiff(amBegin, amEnd);
		}else if(DateUtil.isDateBefore(sdate, bmEnd)){//下午处理的任务
			long amInterval = DateUtil.getDiff(amBegin, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, sdate);
			usedInterval = amInterval + bmInterval;
		}else if(DateUtil.isDateBefore(bmEnd, sdate)){//下班以后加班办理（1标准工作日）
			long amInterval = DateUtil.getDiff(amBegin, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, bmEnd);
			usedInterval = amInterval + bmInterval;
		}
		return usedInterval;
	}
	
	/** 
	 * 获取办理时间离开班时间的差值，单位为秒
	 * 这里只考虑给定时间是在上下班时间范围内的情况，超出上下班时间点的，在进入此方法时就应做适当处理
	 * @param sdate 待处理时间 yyyy-MM-dd HH:mm:ss
	 * @return 给定时间离下班时间差值(秒)
	 */
	public static long getUsedDiffByWorkday(String sdate,String workTime) throws Exception {
		long usedInterval = 0l;
		String sd = sdate.substring(0,10);
		if(StringUtil.isBlank(workTime)){
			throw new Exception("工作时间没有设置，请检查！");
		}
		String[] timeSet = workTime.split("##");
		if(timeSet.length < 4){
			throw new Exception("工作时间设置不正确，请检查！");
		}
		String amBegin = DateUtil.getFormartDateStrHM(sd,timeSet[0]);
		String amEnd = DateUtil.getFormartDateStrHM(sd,timeSet[1]);
		String bmBegin = DateUtil.getFormartDateStrHM(sd,timeSet[2]);
		String bmEnd = DateUtil.getFormartDateStrHM(sd,timeSet[3]);
		if(DateUtil.isDateBefore(sdate, amBegin)){ //上午上班之前就办理的任务
			usedInterval = 0l;
		}else if(DateUtil.isDateBefore(sdate, amEnd)){ //上午处理的任务
			usedInterval = DateUtil.getDiff(amBegin, sdate);
		}else if(DateUtil.isDateBefore(sdate, bmBegin)){//中午休息时间
			usedInterval = DateUtil.getDiff(amBegin, amEnd);
		}else if(DateUtil.isDateBefore(sdate, bmEnd)){//下午处理的任务
			long amInterval = DateUtil.getDiff(amBegin, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, sdate);
			usedInterval = amInterval + bmInterval;
		}else if(DateUtil.isDateBefore(bmEnd, sdate)){//下班以后加班办理（1标准工作日）
			long amInterval = DateUtil.getDiff(amBegin, amEnd);
			long bmInterval = DateUtil.getDiff(bmBegin, bmEnd);
			usedInterval = amInterval + bmInterval;
		}
		return usedInterval;
	}
}
