package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核指标视图
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.vEvalPoint", table = "V_JC_EVAL_POINT", ds = "acceptdata", cache = false)
public class VEvalPoint {
	/**
	 *指标id
	 */
	@Column(id = "point_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String pointId;

	/**
	 *指标名称
	 */
	@Column(id = "point_name", datatype = "string256")
	private java.lang.String pointName;

	/**
	 *父指标名称
	 */
	@Column(id = "parent_point_id", datatype = "string64")
	private java.lang.String parentPointId;

	/**
	 *行政区划代码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *排序号
	 */
	@Column(id = "order_no", datatype = "smallint")
	private java.lang.Integer orderNo;

	/**
	 *类型
	 */
	@Column(id = "type", datatype = "string10")
	private java.lang.String type;

	/**
	 * 设置指标id
	 */
	public void setPointId(java.lang.String pointId) {
		this.pointId = pointId;
	}

	/**
	 * 获取指标id
	 */
	public java.lang.String getPointId() {
		return pointId;
	}

	/**
	 * 设置指标名称
	 */
	public void setPointName(java.lang.String pointName) {
		this.pointName = pointName;
	}

	/**
	 * 获取指标名称
	 */
	public java.lang.String getPointName() {
		return pointName;
	}

	/**
	 * 设置父指标名称
	 */
	public void setParentPointId(java.lang.String parentPointId) {
		this.parentPointId = parentPointId;
	}

	/**
	 * 获取父指标名称
	 */
	public java.lang.String getParentPointId() {
		return parentPointId;
	}

	/**
	 * 设置行政区划代码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取行政区划代码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置排序号
	 */
	public void setOrderNo(java.lang.Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取排序号
	 */
	public java.lang.Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置类型
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取类型
	 */
	public java.lang.String getType() {
		return type;
	}
}
