package com.chinacreator.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {

	/**
	 * 根据偏移的月份获取指定日期的第一天
	 */
	public static Date getFirstDateByOffsetMonth(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offset);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 根据偏移的月份获取指定日期的最后一天
	 */
	public static Date getLastDateByOffsetMonth(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offset);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	/**
	 * 根据偏移的年份获取指定日期的第一天
	 */
	public static Date getFirstDateByOffsetYear(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + offset);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 根据偏移的年份获取指定日期的最后一天
	 */
	public static Date getLastDateByOffsetYear(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + offset);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
}
