package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效测评指标成绩表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.EvalPointValue", table = "TA_JC_EVAL_POINT_VALUE", ds = "acceptdata", cache = false)
public class EvalPointValue {
	/**
	 *ID号
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

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
	 *考评项ID
	 */
	@Column(id = "item_id", datatype = "string64")
	private java.lang.String itemId;

	/**
	 *考评项名称
	 */
	@Column(id = "item_name", datatype = "string256")
	private java.lang.String itemName;

	/**
	 *测评单位ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

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
	 *指标评分(指标实际得分)
	 */
	@Column(id = "point_score", datatype = "tinydouble")
	private java.lang.Double pointScore;

	/**
	 *测评单位名称
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 *考核对象类型（1-部门，2-窗口，3-个人）
	 */
	@Column(id = "eval_object_type", datatype = "string5")
	private java.lang.String evalObjectType;

	/**
	 *申诉开始时间
	 */
	@Column(id = "complain_start_time", datatype = "date")
	private java.sql.Date complainStartTime;

	/**
	 *申诉结束时间
	 */
	@Column(id = "complain_end_time", datatype = "date")
	private java.sql.Date complainEndTime;

	/**
	 *是否已评分('Y'-已评分，'N'-未评分）
	 */
	@Column(id = "is_grade", datatype = "string10")
	private java.lang.String isGrade;

	/**
	 *扣分原因
	 */
	@Column(id = "deduct_reasons", datatype = "string512")
	private java.lang.String deductReasons;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	/**
	 *是否申诉('Y'-已申诉，'N'-未申诉）
	 */
	@Column(id = "is_complain", datatype = "string10")
	private java.lang.String isComplain;

	/**
	 *是否自动评分('Y'-自动，'N'-人工）
	 */
	@Column(id = "is_auto", datatype = "string10")
	private java.lang.String isAuto;

	/**
	 *考核对象的ID
	 */
	@Column(id = "eval_object_id", datatype = "string64")
	private java.lang.String evalObjectId;

	/**
	 *考核对象的名称
	 */
	@Column(id = "eval_object_name", datatype = "string256")
	private java.lang.String evalObjectName;

	/**
	 *测评实例ID
	 */
	@Column(id = "eval_instance_id", datatype = "string64")
	private java.lang.String evalInstanceId;

	/**
	 *考评时间
	 */
	@Column(id = "eval_time", datatype = "date")
	private java.sql.Date evalTime;

	/**
	 *申诉时间
	 */
	@Column(id = "comp_time", datatype = "date")
	private java.sql.Date compTime;

	/**
	 *申诉原因
	 */
	@Column(id = "comp_reasons", datatype = "string512")
	private java.lang.String compReasons;

	/**
	 *本次减分
	 */
	@Column(id = "subtraction", datatype = "tinydouble")
	private java.lang.Double subtraction;

	/**
	 *指标分数最大值
	 */
	@Column(id = "max_score", datatype = "tinydouble")
	private java.lang.Double maxScore;

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
	 * 设置考评项ID
	 */
	public void setItemId(java.lang.String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获取考评项ID
	 */
	public java.lang.String getItemId() {
		return itemId;
	}

	/**
	 * 设置考评项名称
	 */
	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取考评项名称
	 */
	public java.lang.String getItemName() {
		return itemName;
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
	 * 设置申诉开始时间
	 */
	public void setComplainStartTime(java.sql.Date complainStartTime) {
		this.complainStartTime = complainStartTime;
	}

	/**
	 * 获取申诉开始时间
	 */
	public java.sql.Date getComplainStartTime() {
		return complainStartTime;
	}

	/**
	 * 设置申诉结束时间
	 */
	public void setComplainEndTime(java.sql.Date complainEndTime) {
		this.complainEndTime = complainEndTime;
	}

	/**
	 * 获取申诉结束时间
	 */
	public java.sql.Date getComplainEndTime() {
		return complainEndTime;
	}

	/**
	 * 设置是否已评分('Y'-已评分，'N'-未评分）
	 */
	public void setIsGrade(java.lang.String isGrade) {
		this.isGrade = isGrade;
	}

	/**
	 * 获取是否已评分('Y'-已评分，'N'-未评分）
	 */
	public java.lang.String getIsGrade() {
		return isGrade;
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
	 * 设置备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * 设置是否申诉('Y'-已申诉，'N'-未申诉）
	 */
	public void setIsComplain(java.lang.String isComplain) {
		this.isComplain = isComplain;
	}

	/**
	 * 获取是否申诉('Y'-已申诉，'N'-未申诉）
	 */
	public java.lang.String getIsComplain() {
		return isComplain;
	}

	/**
	 * 设置是否自动评分('Y'-自动，'N'-人工）
	 */
	public void setIsAuto(java.lang.String isAuto) {
		this.isAuto = isAuto;
	}

	/**
	 * 获取是否自动评分('Y'-自动，'N'-人工）
	 */
	public java.lang.String getIsAuto() {
		return isAuto;
	}

	/**
	 * 设置考核对象的ID
	 */
	public void setEvalObjectId(java.lang.String evalObjectId) {
		this.evalObjectId = evalObjectId;
	}

	/**
	 * 获取考核对象的ID
	 */
	public java.lang.String getEvalObjectId() {
		return evalObjectId;
	}

	/**
	 * 设置考核对象的名称
	 */
	public void setEvalObjectName(java.lang.String evalObjectName) {
		this.evalObjectName = evalObjectName;
	}

	/**
	 * 获取考核对象的名称
	 */
	public java.lang.String getEvalObjectName() {
		return evalObjectName;
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
	 * 设置考评时间
	 */
	public void setEvalTime(java.sql.Date evalTime) {
		this.evalTime = evalTime;
	}

	/**
	 * 获取考评时间
	 */
	public java.sql.Date getEvalTime() {
		return evalTime;
	}

	/**
	 * 设置申诉时间
	 */
	public void setCompTime(java.sql.Date compTime) {
		this.compTime = compTime;
	}

	/**
	 * 获取申诉时间
	 */
	public java.sql.Date getCompTime() {
		return compTime;
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
	 * 设置本次减分
	 */
	public void setSubtraction(java.lang.Double subtraction) {
		this.subtraction = subtraction;
	}

	/**
	 * 获取本次减分
	 */
	public java.lang.Double getSubtraction() {
		return subtraction;
	}

	/**
	 * 设置指标分数最大值
	 */
	public void setMaxScore(java.lang.Double maxScore) {
		this.maxScore = maxScore;
	}

	/**
	 * 获取指标分数最大值
	 */
	public java.lang.Double getMaxScore() {
		return maxScore;
	}
}
