package com.chinacreator.util;

import java.util.List;

/**
 * 参数判断工具类
 * 
 * @author xinwei.chen
 * @date 2017-11-27上午11:21:11
 */
public class ParamsUtil {

	public static boolean notNull(String param) {

		if (param != null) {

			if ("".equals(param.trim())) {
				return false;
			} else if ("null".equals(param)) {
				return false;
			} else if ("undefined".equals(param)) {
				return false;
			} else {
				return true;
			}

		} else {
			return false;
		}
	}

	public static boolean notNull(List<Object> list) {

		if (list != null) {

			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean isNull(String param) {

		if (param != null) {

			if ("".equals(param.trim())) {
				return true;
			} else if ("null".equals(param)) {
				return true;
			} else if ("undefined".equals(param)) {
				return true;
			} else {
				return false;
			}

		} else {
			return true;
		}
	}
	
	/**
	 * 判断传入参数是否为null
	 * @param t
	 * @return
	 */
	public static <T> boolean isNull(T t) {
		if(t == null){
			return true;
		}
		return false;
	}
}
