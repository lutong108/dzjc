package com.chinacreator.dzjc_ruleEngine.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {

	public static void main(String[] args) {
	}

	/**
	 * 格式化日期显示格式
	 * 
	 * @param sdate
	 *            原始日期格式 s - 表示 "yyyy-mm-dd" 形式的日期的 String 对象
	 * @param format
	 *            格式化后日期格式
	 * @return 格式化后的日期显示
	 */
	public static String dateFormat(String sdate, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		java.sql.Date date = java.sql.Date.valueOf(sdate);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 求两个日期相差天数
	 * 
	 * @param startDate
	 *            起始日期，格式yyyy-MM-dd
	 * @param endDate
	 *            终止日期，格式yyyy-MM-dd
	 * @return 两个日期相差天数
	 */
	public static long getIntervalDays(String startDate, String endDate) {
		long lEnd = (java.sql.Date.valueOf(endDate)).getTime();
		long lStart = (java.sql.Date.valueOf(startDate)).getTime();
		long lDiff = (lEnd - lStart) / (3600 * 24 * 1000) + 1;
		return lDiff;
	}

	/**
	 * 取得当前日期的年份，以yyyy格式返回.
	 * 
	 * @return 当年 yyyy
	 */
	public static String getCurrentYear() {
		return getFormatCurrentTime("yyyy");
	}

	/**
	 * 取得当前日期的月份，以MM格式返回.
	 * 
	 * @return 当前月份 MM
	 */
	public static String getCurrentMonth() {
		return getFormatCurrentTime("MM");
	}
	
	/**
	 * 取得当前日期的天数，以格式"dd"返回.
	 * 
	 * @return 当前月中的某天dd
	 */
	public static String getCurrentDay() {
		return getFormatCurrentTime("dd");
	}

	/**
	 * 返回当前时间字符串。 格式：yyyy-MM-dd
	 * 
	 * @return String 指定格式的日期字符串.
	 */
	public static String getCurrentDate() {
		return getFormatDateTime(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 返回当前指定的时间戳。格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 格式为yyyy-MM-dd HH:mm:ss，总共19位。
	 */
	public static String getCurrentDateTime() {
		return getFormatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 
	 * 返回给定时间字符串。 格式：yyyy-MM-dd
	 * 
	 * @param date
	 *            日期
	 * @return String 指定格式的日期字符串.
	 */
	public static String getFormatDate(Date date) {
		return getFormatDateTime(date, "yyyy-MM-dd");
	}

	/**
	 * 
	 * 根据制定的格式，返回日期字符串。例如2007-05-05
	 * 
	 * @param format
	 *            "yyyy-MM-dd" 或者 "yyyy/MM/dd",当然也可以是别的形式。
	 * @return 指定格式的日期字符串。
	 */
	public static String getFormatDate(String format) {
		return getFormatDateTime(new Date(), format);
	}

	/**
	 * 
	 * 返回当前时间字符串。 格式：HH:mm:ss
	 * 
	 * @return String 指定格式的日期字符串.
	 */
	public static String getCurrentTime() {
		return getFormatDateTime(new Date(), "HH:mm:ss");
	}

	/**
	 * 
	 * 返回给定时间字符串。 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            日期
	 * @return String 指定格式的日期字符串.
	 */

	public static String getFormatTime(Date date) {
		return getFormatDateTime(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * 根据给定的格式，返回时间字符串。 格式参照类描绘中说明.和方法getFormatDate是一样的。
	 * 
	 * @param format
	 *            日期格式字符串
	 * @return String 指定格式的日期字符串.
	 */
	public static String getFormatCurrentTime(String format) {
		return getFormatDateTime(new Date(), format);
	}

	/**
	 * 根据给定的格式与时间(Date类型的)，返回时间字符串。最为通用。<br>
	 * 
	 * @param date
	 *            指定的日期
	 * @param format
	 *            日期格式字符串
	 * @return String 指定格式的日期字符串.
	 */
	public static String getFormatDateTime(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 
	 * 取得当前Date对象.
	 * 
	 * @return Date 当前Date对象.
	 */
	public static Date getDateObj() {
		Calendar c = new GregorianCalendar();
		return c.getTime();
	}

	/**
	 * 计算当前月份有多少天
	 * 
	 * @return 当前月份有多少天；
	 */
	public static int getDaysOfCurMonth() {
		int curyear = new Integer(getCurrentYear()).intValue(); // 当前年份
		int curMonth = new Integer(getCurrentMonth()).intValue();// 当前月份
		int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		// 判断闰年的情况 ，2月份有29天；
		if ((curyear % 400 == 0)
				|| ((curyear % 100 != 0) && (curyear % 4 == 0))) {
			mArray[1] = 29;
		}
		return mArray[curMonth - 1];
		// 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
		// 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
	}

	/**
	 * 
	 * 根据指定的年月 返回指定月份（yyyy-MM）有多少天。
	 * 
	 * @param time
	 *            yyyy-MM
	 * @return 天数，指定月份的天数。
	 */
	public static int getDaysOfCurMonth(final String time) {
		if (time.length() != 7) {
			throw new NullPointerException("参数的格式必须是yyyy-MM");
		}
		String[] timeArray = time.split("-");
		int curyear = new Integer(timeArray[0]).intValue(); // 当前年份
		int curMonth = new Integer(timeArray[1]).intValue();// 当前月份
		if (curMonth > 12) {
			throw new NullPointerException("参数的格式必须是yyyy-MM，而且月份必须小于等于12。");
		}
		int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		// 判断闰年的情况 ，2月份有29天；
		if ((curyear % 400 == 0)
				|| ((curyear % 100 != 0) && (curyear % 4 == 0))) {
			mArray[1] = 29;
		}
		if (curMonth == 12) {
			return mArray[0];
		}
		return mArray[curMonth - 1];
		// 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
		// 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
	}

	/**
	 * 
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的天数，如果是向前的天数，使用负数就可以.
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static Date getDateAdd(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return cal.getTime();

	}

	/**
	 * 
	 * 根据主机的默认 TimeZone，来获得指定形式的时间字符串。
	 * 
	 * @param dateFormat
	 * @return 返回日期字符串，形式和formcat一致。
	 */
	public static String getCurrentDateString(String dateFormat) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setTimeZone(TimeZone.getDefault());
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * 根据给定的格式，返回时间字符串。 和getFormatDate(String format)相似。
	 * 
	 * @param format
	 *            yyyy MM dd hh mm ss
	 * @return 返回一个时间字符串
	 */
	public static String getCurTimeByFormat(String format) {
		Date newdate = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(newdate);
	}

	/**
	 * 
	 * 获取两个时间串时间的差值，单位为秒
	 * 
	 * @param startTime
	 *            开始时间 yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 *            结束时间 yyyy-MM-dd HH:mm:ss
	 * @return 两个时间的差值(秒)
	 */
	public static long getDiff(String startTime, String endTime) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date startDate = ft.parse(startTime);
			Date endDate = ft.parse(endTime);
			diff = endDate.getTime() - startDate.getTime();
			diff = diff / 1000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}

	/**
	 * 
	 * 获取两个时间串时间的差值，单位为小时
	 * 
	 * @param startTime
	 *            开始时间 yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 *            结束时间 yyyy-MM-dd HH:mm:ss
	 * @return 两个时间的差值(秒)
	 */
	public static int getDiffHour(String startTime, String endTime) {
		long diff = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date startDate = ft.parse(startTime);
			Date endDate = ft.parse(endTime);
			diff = startDate.getTime() - endDate.getTime();
			diff = diff / (1000 * 60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Long(diff).intValue();
	}

	/**
	 * 返回两个日期之间相差多少天。 注意开始日期和结束日期要统一起来。
	 * 
	 * @param startDate
	 *            格式"yyyy/MM/dd" 或者"yyyy-MM-dd"
	 * @param endDate
	 *            格式"yyyy/MM/dd" 或者"yyyy-MM-dd"
	 * @return 整数。
	 */
	public static int getDiffDays(String startDate, String endDate) {
		long diff = 0;
		SimpleDateFormat ft = null;
		if (startDate.indexOf("/") > 0 && endDate.indexOf("/") > 0) {
			ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}
		if (startDate.indexOf("-") > 0 && endDate.indexOf("-") > 0) {
			ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		try {
			Date sDate = ft.parse(startDate + " 00:00:00");
			Date eDate = ft.parse(endDate + " 00:00:00");
			diff = eDate.getTime() - sDate.getTime();
			diff = diff / 86400000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) diff;
	}

	/**
	 * 
	 * 返回指定日期的后一天。<br>
	 * 
	 * @param sourceDate
	 * @param format
	 * @return 返回日期字符串，形式和formcat一致。
	 */
	public static String getFormatDateTommorrow(String sourceDate, String format) {
		return getFormatDateAdd(getDateFromString(sourceDate, format), 1,
				format);
	}

	/**
	 * 
	 * 返回指定日期的前一天。<br>
	 * 
	 * @param sourceDate
	 * @param format
	 *            yyyy MM dd hh mm ss
	 * @return 返回日期字符串，形式和formcat一致。
	 */
	public static String getFormatDateYestoday(String sourceDate, String format) {
		return getFormatDateAdd(getDateFromString(sourceDate, format), -1,
				format);
	}

	/**
	 * 取得给定字符串描述的日期对象，描述模式采用pattern指定的格式.
	 * 
	 * @param dateStr
	 *            日期描述 从给定字符串的开始分析文本，以生成一个日期。该方法不使用给定字符串的整个文本。 有关日期分析的更多信息，请参阅
	 *            parse(String, ParsePosition) 方法。一个 String，应从其开始处进行分析
	 * @param pattern
	 *            日期模式
	 * @return 给定字符串描述的日期对象。
	 */
	public static Date getDateFromString(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date resDate = null;
		try {
			resDate = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resDate;
	}

	/**
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的天数，如果是向前的天数，使用负数就可以.
	 * @param format
	 *            输出格式.
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static String getFormatDateAdd(Date date, int amount, String format) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return getFormatDateTime(cal.getTime(), format);

	}

	/**
	 * 判断时间date1是否在时间date2之前 时间格式 2005-4-21 16:16:34
	 * 
	 * @param date1
	 *            给定的日期对象
	 * @param date2
	 *            给定的日期对象
	 * @return 判断结果
	 */
	public static boolean isDateBefore(String date1, String date2) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[DateUtil]Exception: " + e.getMessage());
			return false;
		}
	}

	/**
	 * 给定日期、小时分钟，返回yyyy-MM-dd hh:mm:ss格式
	 * 
	 * @param sdate
	 * @param hm
	 * @return
	 */
	public static String getFormartDateStrHM(String sdate, String hm) {
		String s = sdate + " " + hm + ":00";
		return s;
	}

	public static String getFormatDateAddMonth(String date, int amount,
			String format) {
		java.util.Date d = getDateFromString(date, "yyyy-MM");
		Calendar cal = new GregorianCalendar();
		cal.setTime(d);
		cal.add(2, amount);
		return getFormatDateTime(cal.getTime(), format);
	}

	public static String getFormatDateAddYear(String date, int amount,
			String format) {
		java.util.Date d = getDateFromString(date, "yyyy-MM");
		Calendar cal = new GregorianCalendar();
		cal.setTime(d);
		cal.add(1, amount);
		return getFormatDateTime(cal.getTime(), format);
	}
	
	public static String getDatePoor(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟"+sec+"秒";
	}
	
	public static Date parseStr(String date) throws ParseException{
		SimpleDateFormat format= new SimpleDateFormat();
		Date parse = format.parse(date);
		return parse;
	}
}
