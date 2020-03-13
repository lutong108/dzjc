package com.chinacreator.util;

import com.chinacreator.c2.config.ConfigManager;

/**
 * @Title : 系统属性文件配置
 * @Author : zouhailin
 * @Date : 2018-8-8
 */
public class SystemPropertiesInstance {

	private static SystemPropertiesInstance instance = null;

	public static SystemPropertiesInstance getInstance() {
		if (instance == null) {
			instance = new SystemPropertiesInstance();
		}
		return instance;
	}

	public String getSystem_areaCode() {
		return ConfigManager.getInstance().getConfig("system_areaCode");
	}

	public String getSystem_areaName() {
		return ConfigManager.getInstance().getConfig("system_areaName");
	}

	public String getSystem_scope() {
		return ConfigManager.getInstance().getConfig("system_scope");
	}

}
