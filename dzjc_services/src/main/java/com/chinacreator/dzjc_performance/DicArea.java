package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 行政区域
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.dicArea", table = "TA_JC_DIC_AREA", ds = "acceptdata", cache = false)
public class DicArea {
	/**
	 *id
	 */
	@Column(id = "area_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String areaId;

	/**
	 *行政区域编码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *区域名称
	 */
	@Column(id = "area_name", datatype = "string64")
	private java.lang.String areaName;

	/**
	 *父区域编码
	 */
	@Column(id = "parent_area_code", datatype = "string20")
	private java.lang.String parentAreaCode;

	/**
	 *状态
	 */
	@Column(id = "state", datatype = "boolean")
	private java.lang.Boolean state;

	/**
	 *排序字段
	 */
	@Column(id = "area_sn", datatype = "string5")
	private java.lang.String areaSn;

	/**
	 * 设置id
	 */
	public void setAreaId(java.lang.String areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获取id
	 */
	public java.lang.String getAreaId() {
		return areaId;
	}

	/**
	 * 设置行政区域编码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取行政区域编码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置区域名称
	 */
	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 获取区域名称
	 */
	public java.lang.String getAreaName() {
		return areaName;
	}

	/**
	 * 设置父区域编码
	 */
	public void setParentAreaCode(java.lang.String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	/**
	 * 获取父区域编码
	 */
	public java.lang.String getParentAreaCode() {
		return parentAreaCode;
	}

	/**
	 * 设置状态
	 */
	public void setState(java.lang.Boolean state) {
		this.state = state;
	}

	/**
	 * 获取状态
	 */
	public java.lang.Boolean isState() {
		return state;
	}

	/**
	 * 设置排序字段
	 */
	public void setAreaSn(java.lang.String areaSn) {
		this.areaSn = areaSn;
	}

	/**
	 * 获取排序字段
	 */
	public java.lang.String getAreaSn() {
		return areaSn;
	}
}
