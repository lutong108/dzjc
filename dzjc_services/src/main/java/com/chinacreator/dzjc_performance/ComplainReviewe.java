package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 申诉审核实体
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.ComplainReviewe", table = "TA_JC_EVAL_COMPLAIN_REVIEWE", ds = "acceptdata", cache = false)
public class ComplainReviewe {
	/**
	 *ID
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *测评实例ID
	 */
	@Column(id = "eval_instance_id", datatype = "string64")
	private java.lang.String evalInstanceId;

	/**
	 *考核表ID
	 */
	@Column(id = "table_id", datatype = "string64")
	private java.lang.String tableId;

	/**
	 *考核表名称
	 */
	@Column(id = "table_name", datatype = "string256")
	private java.lang.String tableName;

	/**
	 *指标ID
	 */
	@Column(id = "point_id", datatype = "string64")
	private java.lang.String pointId;

	/**
	 *指标名称
	 */
	@Column(id = "point_name", datatype = "string256")
	private java.lang.String pointName;

	/**
	 *考核对象类型（1-部门，2-窗口，3-个人）
	 */
	@Column(id = "eval_object_type", datatype = "string5")
	private java.lang.String evalObjectType;

	/**
	 *指标评分(指标实际得分)
	 */
	@Column(id = "point_score", datatype = "tinydouble")
	private java.lang.Double pointScore;

	/**
	 *考核对象ID
	 */
	@Column(id = "eval_object_id", datatype = "string64")
	private java.lang.String evalObjectId;

	/**
	 *考核对象名称
	 */
	@Column(id = "eval_object_name", datatype = "string256")
	private java.lang.String evalObjectName;

	/**
	 *扣分原因
	 */
	@Column(id = "deduct_reasons", datatype = "string512")
	private java.lang.String deductReasons;

	/**
	 *申诉原因
	 */
	@Column(id = "comp_reasons", datatype = "string512")
	private java.lang.String compReasons;

	/**
	 *测评单位ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *测评单位名称
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 *考评时间(自动的取指标成绩插入的时间，人工取评分操作时候的时间)
	 */
	@Column(id = "eval_time", datatype = "date")
	private java.sql.Date evalTime;

	/**
	 *申诉时间(在线申诉客户进行申诉的时间)
	 */
	@Column(id = "comp_time", datatype = "date")
	private java.sql.Date compTime;

	/**
	 *审核状态(1-审核，2-通过，3-驳回)
	 */
	@Column(id = "reviewe_states", datatype = "string5")
	private java.lang.String revieweStates;

	/**
	 *指标成绩表ID
	 */
	@Column(id = "eval_point_value_id", datatype = "string64")
	private java.lang.String evalPointValueId;

	/**
	 * 设置ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取ID
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置测评实例ID
	 */
	public void setEvalInstanceId(java.lang.String evalInstanceId) {
		this.evalInstanceId = evalInstanceId;
	}

	/**
	 * 获取测评实例ID
	 */
	public java.lang.String getEvalInstanceId() {
		return evalInstanceId;
	}

	/**
	 * 设置考核表ID
	 */
	public void setTableId(java.lang.String tableId) {
		this.tableId = tableId;
	}

	/**
	 * 获取考核表ID
	 */
	public java.lang.String getTableId() {
		return tableId;
	}

	/**
	 * 设置考核表名称
	 */
	public void setTableName(java.lang.String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 获取考核表名称
	 */
	public java.lang.String getTableName() {
		return tableName;
	}

	/**
	 * 设置指标ID
	 */
	public void setPointId(java.lang.String pointId) {
		this.pointId = pointId;
	}

	/**
	 * 获取指标ID
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
	 * 设置考核对象类型（1-部门，2-窗口，3-个人）
	 */
	public void setEvalObjectType(java.lang.String evalObjectType) {
		this.evalObjectType = evalObjectType;
	}

	/**
	 * 获取考核对象类型（1-部门，2-窗口，3-个人）
	 */
	public java.lang.String getEvalObjectType() {
		return evalObjectType;
	}

	/**
	 * 设置指标评分(指标实际得分)
	 */
	public void setPointScore(java.lang.Double pointScore) {
		this.pointScore = pointScore;
	}

	/**
	 * 获取指标评分(指标实际得分)
	 */
	public java.lang.Double getPointScore() {
		return pointScore;
	}

	/**
	 * 设置考核对象ID
	 */
	public void setEvalObjectId(java.lang.String evalObjectId) {
		this.evalObjectId = evalObjectId;
	}

	/**
	 * 获取考核对象ID
	 */
	public java.lang.String getEvalObjectId() {
		return evalObjectId;
	}

	/**
	 * 设置考核对象名称
	 */
	public void setEvalObjectName(java.lang.String evalObjectName) {
		this.evalObjectName = evalObjectName;
	}

	/**
	 * 获取考核对象名称
	 */
	public java.lang.String getEvalObjectName() {
		return evalObjectName;
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
	 * 设置申诉原因
	 */
	public void setCompReasons(java.lang.String compReasons) {
		this.compReasons = compReasons;
	}

	/**
	 * 获取申诉原因
	 */
	public java.lang.String getCompReasons() {
		return compReasons;
	}

	/**
	 * 设置测评单位ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取测评单位ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置测评单位名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取测评单位名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置考评时间(自动的取指标成绩插入的时间，人工取评分操作时候的时间)
	 */
	public void setEvalTime(java.sql.Date evalTime) {
		this.evalTime = evalTime;
	}

	/**
	 * 获取考评时间(自动的取指标成绩插入的时间，人工取评分操作时候的时间)
	 */
	public java.sql.Date getEvalTime() {
		return evalTime;
	}

	/**
	 * 设置申诉时间(在线申诉客户进行申诉的时间)
	 */
	public void setCompTime(java.sql.Date compTime) {
		this.compTime = compTime;
	}

	/**
	 * 获取申诉时间(在线申诉客户进行申诉的时间)
	 */
	public java.sql.Date getCompTime() {
		return compTime;
	}

	/**
	 * 设置审核状态(1-审核，2-通过，3-驳回)
	 */
	public void setRevieweStates(java.lang.String revieweStates) {
		this.revieweStates = revieweStates;
	}

	/**
	 * 获取审核状态(1-审核，2-通过，3-驳回)
	 */
	public java.lang.String getRevieweStates() {
		return revieweStates;
	}

	/**
	 * 设置指标成绩表ID
	 */
	public void setEvalPointValueId(java.lang.String evalPointValueId) {
		this.evalPointValueId = evalPointValueId;
	}

	/**
	 * 获取指标成绩表ID
	 */
	public java.lang.String getEvalPointValueId() {
		return evalPointValueId;
	}
}
