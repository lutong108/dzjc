package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效考评表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.evalTable", table = "TA_JC_EVAL_TABLE", ds = "acceptdata", cache = false)
public class EvalTable {
	/**
	 *考评表编号，通过行政区划代码+序列SEQ_EVAL_TABLE取值
	 */
	@Column(id = "table_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String tableId;

	/**
	 *考评表名称
	 */
	@Column(id = "table_name", datatype = "string256")
	private java.lang.String tableName;

	/**
	 *考评公式
	 */
	@Column(id = "table_formula", datatype = "string1024")
	private java.lang.String tableFormula;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string256")
	private java.lang.String remark;

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
	 *是否启用
	 */
	@Column(id = "is_used", datatype = "char1")
	private java.lang.String isUsed;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *是否通用 (Y-通用性，N-个性化)
	 */
	@Column(id = "is_public", datatype = "char1")
	private java.lang.String isPublic;

	/**
	 *机构编码
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *版本号
	 */
	@Column(id = "version", datatype = "string20")
	private java.lang.String version;

	/**
	 *考核对象类型
	 */
	@Column(id = "eval_object_type", datatype = "char1")
	private java.lang.String evalObjectType;

	/**
	 *是否有效
	 */
	@Column(id = "is_valid", datatype = "char1")
	private java.lang.String isValid;

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
	 * 设置考评表名称
	 */
	public void setTableName(java.lang.String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 获取考评表名称
	 */
	public java.lang.String getTableName() {
		return tableName;
	}

	/**
	 * 设置考评公式
	 */
	public void setTableFormula(java.lang.String tableFormula) {
		this.tableFormula = tableFormula;
	}

	/**
	 * 获取考评公式
	 */
	public java.lang.String getTableFormula() {
		return tableFormula;
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
	 * 设置是否通用 (Y-通用性，N-个性化)
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取是否通用 (Y-通用性，N-个性化)
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置机构编码
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构编码
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置版本号
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	/**
	 * 获取版本号
	 */
	public java.lang.String getVersion() {
		return version;
	}

	/**
	 * 设置考核对象类型
	 */
	public void setEvalObjectType(java.lang.String evalObjectType) {
		this.evalObjectType = evalObjectType;
	}

	/**
	 * 获取考核对象类型
	 */
	public java.lang.String getEvalObjectType() {
		return evalObjectType;
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
}
