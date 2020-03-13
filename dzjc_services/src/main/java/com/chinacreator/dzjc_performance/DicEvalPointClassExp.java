package com.chinacreator.dzjc_performance;

/**
 * 考核指标类型管理实体  扩展类
 * @author Administrator
 *
 */
public class DicEvalPointClassExp extends DicEvalPointClass {

	/**
	 * 上级指标类型名称
	 */
	private String parentPointtypeName;
	/**
	 * 区域名称
	 */
	private String areaName;
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentPointtypeName() {
		return parentPointtypeName;
	}

	public void setParentPointtypeName(String parentPointtypeName) {
		this.parentPointtypeName = parentPointtypeName;
	}
	
}
