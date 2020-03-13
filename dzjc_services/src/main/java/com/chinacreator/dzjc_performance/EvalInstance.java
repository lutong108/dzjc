package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效测评实例
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.EvalInstance", table = "TA_JC_EVAL_INSTANCE", ds = "acceptdata", cache = false)
public class EvalInstance {
	/**
	 *测评实例ID
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

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
	 *考核测评单位ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *考核测评单位名称
	 */
	@Column(id = "org_name", datatype = "string512")
	private java.lang.String orgName;

	/**
	 *考核对象类型（1-部门，2-窗口，3-个人）
	 */
	@Column(id = "eval_object_type", datatype = "string5")
	private java.lang.String evalObjectType;

	/**
	 *评测开始时间
	 */
	@Column(id = "eval_start_time", datatype = "date")
	private java.sql.Date evalStartTime;

	/**
	 *评测结束时间
	 */
	@Column(id = "eval_end_time", datatype = "date")
	private java.sql.Date evalEndTime;

	/**
	 *是否启用
	 */
	@Column(id = "is_used", datatype = "string10")
	private java.lang.String isUsed;

	/**
	 *排序号
	 */
	@Column(id = "order_no", datatype = "smallint")
	private java.lang.Integer orderNo;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string256")
	private java.lang.String remark;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *是否有效
	 */
	@Column(id = "is_valid", datatype = "string10")
	private java.lang.String isValid;

	/**
	 *所有考核对象的id
	 */
	@Column(id = "eval_object_ids", datatype = "string1024")
	private java.lang.String evalObjectIds;

	/**
	 *考评时间
	 */
	@Column(id = "eval_time", datatype = "date")
	private java.sql.Date evalTime;

	/**
	 * 设置测评实例ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取测评实例ID
	 */
	public java.lang.String getId() {
		return id;
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
	 * 设置考核测评单位ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取考核测评单位ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置考核测评单位名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取考核测评单位名称
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
	 * 设置评测开始时间
	 */
	public void setEvalStartTime(java.sql.Date evalStartTime) {
		this.evalStartTime = evalStartTime;
	}

	/**
	 * 获取评测开始时间
	 */
	public java.sql.Date getEvalStartTime() {
		return evalStartTime;
	}

	/**
	 * 设置评测结束时间
	 */
	public void setEvalEndTime(java.sql.Date evalEndTime) {
		this.evalEndTime = evalEndTime;
	}

	/**
	 * 获取评测结束时间
	 */
	public java.sql.Date getEvalEndTime() {
		return evalEndTime;
	}

	/**
	 * 设置是否启用
	 */
	public void setIsUsed(java.lang.String isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * 获取是否启用
	 */
	public java.lang.String getIsUsed() {
		return isUsed;
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
	 * 设置是否有效
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置所有考核对象的id
	 */
	public void setEvalObjectIds(java.lang.String evalObjectIds) {
		this.evalObjectIds = evalObjectIds;
	}

	/**
	 * 获取所有考核对象的id
	 */
	public java.lang.String getEvalObjectIds() {
		return evalObjectIds;
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
}
