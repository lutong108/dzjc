package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效测评指标成绩历史表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.EvalPointValueHis", table = "TA_JC_EVAL_POINT_VALUE_HIS", ds = "acceptdata", cache = false)
public class EvalPointValueHis {
	/**
	 *ID号
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *指标名称
	 */
	@Column(id = "point_name", datatype = "string256")
	private java.lang.String pointName;

	/**
	 *指标得分
	 */
	@Column(id = "point_score", datatype = "tinydouble")
	private java.lang.Double pointScore;

	/**
	 *本次扣分
	 */
	@Column(id = "subtraction", datatype = "tinydouble")
	private java.lang.Double subtraction;

	/**
	 *扣分原因
	 */
	@Column(id = "deduct_reasons", datatype = "string512")
	private java.lang.String deductReasons;

	/**
	 *操作人
	 */
	@Column(id = "operator_name", datatype = "string64")
	private java.lang.String operatorName;

	/**
	 *打分时间
	 */
	@Column(id = "scoring_time", datatype = "date")
	private java.sql.Date scoringTime;

	/**
	 *ta_jc_eval_point_value主键ID
	 */
	@Column(id = "ta_jc_eval_point_value_id", datatype = "string64")
	private java.lang.String taJcEvalPointValueId;

	/**
	 * 设置ID号
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取ID号
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
	public void setPointScore(java.lang.Double pointScore) {
		this.pointScore = pointScore;
	}

	/**
	 * 获取指标得分
	 */
	public java.lang.Double getPointScore() {
		return pointScore;
	}

	/**
	 * 设置本次扣分
	 */
	public void setSubtraction(java.lang.Double subtraction) {
		this.subtraction = subtraction;
	}

	/**
	 * 获取本次扣分
	 */
	public java.lang.Double getSubtraction() {
		return subtraction;
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

	/**
	 * 设置操作人
	 */
	public void setOperatorName(java.lang.String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * 获取操作人
	 */
	public java.lang.String getOperatorName() {
		return operatorName;
	}

	/**
	 * 设置打分时间
	 */
	public void setScoringTime(java.sql.Date scoringTime) {
		this.scoringTime = scoringTime;
	}

	/**
	 * 获取打分时间
	 */
	public java.sql.Date getScoringTime() {
		return scoringTime;
	}

	/**
	 * 设置ta_jc_eval_point_value主键ID
	 */
	public void setTaJcEvalPointValueId(java.lang.String taJcEvalPointValueId) {
		this.taJcEvalPointValueId = taJcEvalPointValueId;
	}

	/**
	 * 获取ta_jc_eval_point_value主键ID
	 */
	public java.lang.String getTaJcEvalPointValueId() {
		return taJcEvalPointValueId;
	}
}
