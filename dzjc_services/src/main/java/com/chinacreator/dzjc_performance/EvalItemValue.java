package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效测评成绩
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.evalItemValue", table = "TA_JC_EVAL_ITEM_VALUE", ds = "acceptdata", cache = false)
public class EvalItemValue {
	/**
	 *记录ID
	 */
	@Column(id = "record_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String recordId;

	/**
	 *考评项目编号，通过行政区划代码+序列SEQ_EVAL_ITEM取值
	 */
	@Column(id = "item_id", datatype = "string64")
	private java.lang.String itemId;

	/**
	 *考评表编号，通过行政区划代码+序列SEQ_EVAL_TABLE取值
	 */
	@Column(id = "table_id", datatype = "string64")
	private java.lang.String tableId;

	/**
	 *单位组织机构代码
	 */
	@Column(id = "org_code", datatype = "string64")
	private java.lang.String orgCode;

	/**
	 *系统考评分数
	 */
	@Column(id = "item_value", datatype = "tinydouble")
	private java.lang.Double itemValue;

	/**
	 *最终考评分数
	 */
	@Column(id = "final_item_value", datatype = "tinydouble")
	private java.lang.Double finalItemValue;

	/**
	 *排序号
	 */
	@Column(id = "order_no", datatype = "smallint")
	private java.lang.Integer orderNo;

	/**
	 *行政区划代码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string1024")
	private java.lang.String remark;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *考评时间
	 */
	@Column(id = "eval_time", datatype = "date")
	private java.sql.Date evalTime;

	/**
	 *考评类型
	 */
	@Column(id = "eval_type", datatype = "string64")
	private java.lang.String evalType;

	/**
	 *是否最终得分
	 */
	@Column(id = "is_totalvalue", datatype = "char1")
	private java.lang.String isTotalvalue;

	/**
	 *是否已发布
	 */
	@Column(id = "is_issue", datatype = "string10")
	private java.lang.String isIssue;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *实例表id
	 */
	@Column(id = "instance_id", datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *考核对象id
	 */
	@Column(id = "eval_object_id", datatype = "string64")
	private java.lang.String evalObjectId;

	/**
	 *考核对象名称
	 */
	@Column(id = "eval_object_name", datatype = "string64")
	private java.lang.String evalObjectName;

	/**
	 * 设置记录ID
	 */
	public void setRecordId(java.lang.String recordId) {
		this.recordId = recordId;
	}

	/**
	 * 获取记录ID
	 */
	public java.lang.String getRecordId() {
		return recordId;
	}

	/**
	 * 设置考评项目编号，通过行政区划代码+序列SEQ_EVAL_ITEM取值
	 */
	public void setItemId(java.lang.String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获取考评项目编号，通过行政区划代码+序列SEQ_EVAL_ITEM取值
	 */
	public java.lang.String getItemId() {
		return itemId;
	}

	/**
	 * 设置考评表编号，通过行政区划代码+序列SEQ_EVAL_TABLE取值
	 */
	public void setTableId(java.lang.String tableId) {
		this.tableId = tableId;
	}

	/**
	 * 获取考评表编号，通过行政区划代码+序列SEQ_EVAL_TABLE取值
	 */
	public java.lang.String getTableId() {
		return tableId;
	}

	/**
	 * 设置单位组织机构代码
	 */
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取单位组织机构代码
	 */
	public java.lang.String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置系统考评分数
	 */
	public void setItemValue(java.lang.Double itemValue) {
		this.itemValue = itemValue;
	}

	/**
	 * 获取系统考评分数
	 */
	public java.lang.Double getItemValue() {
		return itemValue;
	}

	/**
	 * 设置最终考评分数
	 */
	public void setFinalItemValue(java.lang.Double finalItemValue) {
		this.finalItemValue = finalItemValue;
	}

	/**
	 * 获取最终考评分数
	 */
	public java.lang.Double getFinalItemValue() {
		return finalItemValue;
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
	 * 设置平台实例编号
	 */
	public void setCcFormInstanceid(java.lang.String ccFormInstanceid) {
		this.ccFormInstanceid = ccFormInstanceid;
	}

	/**
	 * 获取平台实例编号
	 */
	public java.lang.String getCcFormInstanceid() {
		return ccFormInstanceid;
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
	 * 设置考评类型
	 */
	public void setEvalType(java.lang.String evalType) {
		this.evalType = evalType;
	}

	/**
	 * 获取考评类型
	 */
	public java.lang.String getEvalType() {
		return evalType;
	}

	/**
	 * 设置是否最终得分
	 */
	public void setIsTotalvalue(java.lang.String isTotalvalue) {
		this.isTotalvalue = isTotalvalue;
	}

	/**
	 * 获取是否最终得分
	 */
	public java.lang.String getIsTotalvalue() {
		return isTotalvalue;
	}

	/**
	 * 设置是否已发布
	 */
	public void setIsIssue(java.lang.String isIssue) {
		this.isIssue = isIssue;
	}

	/**
	 * 获取是否已发布
	 */
	public java.lang.String getIsIssue() {
		return isIssue;
	}

	/**
	 * 设置机构id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置实例表id
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取实例表id
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置考核对象id
	 */
	public void setEvalObjectId(java.lang.String evalObjectId) {
		this.evalObjectId = evalObjectId;
	}

	/**
	 * 获取考核对象id
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
}
