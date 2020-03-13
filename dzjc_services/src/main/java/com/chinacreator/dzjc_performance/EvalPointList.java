package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效考核统计详情
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.EvalPointList", table = "TA_JC_EVAL_POINT_VALUE_HIS", ds = "acceptdata", cache = false)
public class EvalPointList {
	/**
	 *主键ID
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *指标名称
	 */
	@Column(id = "point_name", datatype = "string64")
	private java.lang.String pointName;

	/**
	 *指标得分
	 */
	@Column(id = "point_score", datatype = "int")
	private java.lang.Integer pointScore;

	/**
	 *本次扣分
	 */
	@Column(id = "subtraction", datatype = "int")
	private java.lang.Integer subtraction;

	/**
	 *加分原因
	 */
	@Column(id = "reasonsforbonus", datatype = "string2000")
	private java.lang.String reasonsforbonus;

	/**
	 *扣分原因
	 */
	@Column(id = "deduct_reasons", datatype = "string2000")
	private java.lang.String deductReasons;

	/**
	 * 设置主键ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getId() {
		return id;
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
	 * 设置指标得分
	 */
	public void setPointScore(java.lang.Integer pointScore) {
		this.pointScore = pointScore;
	}

	/**
	 * 获取指标得分
	 */
	public java.lang.Integer getPointScore() {
		return pointScore;
	}

	/**
	 * 设置本次扣分
	 */
	public void setSubtraction(java.lang.Integer subtraction) {
		this.subtraction = subtraction;
	}

	/**
	 * 获取本次扣分
	 */
	public java.lang.Integer getSubtraction() {
		return subtraction;
	}

	/**
	 * 设置加分原因
	 */
	public void setReasonsforbonus(java.lang.String reasonsforbonus) {
		this.reasonsforbonus = reasonsforbonus;
	}

	/**
	 * 获取加分原因
	 */
	public java.lang.String getReasonsforbonus() {
		return reasonsforbonus;
	}

	/**
	 * 设置扣分原因
	 */
	public void setDeductReasons(java.lang.String deductReasons) {
		this.deductReasons = deductReasons;
	}

	/**
	 * 获取扣分原因
	 */
	public java.lang.String getDeductReasons() {
		return deductReasons;
	}
}
