package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 区域
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.DictArea", table = "TA_DIC_AREA", ds = "acceptdata", cache = false)
public class DictArea {
	/**
	 *区域编码
	 */
	@Column(id = "area_code", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *区域名称
	 */
	@Column(id = "area_name", datatype = "string64")
	private java.lang.String areaName;

	/**
	 *父区域编码
	 */
	@Column(id = "parent_code", datatype = "string64")
	private java.lang.String parentCode;

	/**
	 *排序
	 */
	@Column(id = "order_num", datatype = "tinyint")
	private java.lang.Integer orderNum;

	/**
	 * 设置区域编码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取区域编码
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
	public void setParentCode(java.lang.String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * 获取父区域编码
	 */
	public java.lang.String getParentCode() {
		return parentCode;
	}

	/**
	 * 设置排序
	 */
	public void setOrderNum(java.lang.Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取排序
	 */
	public java.lang.Integer getOrderNum() {
		return orderNum;
	}
}
