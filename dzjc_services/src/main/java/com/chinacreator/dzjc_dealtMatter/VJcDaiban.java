package com.chinacreator.dzjc_dealtMatter;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 综合待办信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_dealtMatter.VJcDaiban", table = "V_JC_DAIBAN", ds = "acceptdata", cache = false)
public class VJcDaiban {
	@Column(id = "db_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String dbId;

	@Column(id = "db_title", datatype = "string512")
	private java.lang.String dbTitle;

	@Column(id = "db_org_id", datatype = "string64")
	private java.lang.String dbOrgId;

	@Column(id = "db_org_code", datatype = "string512")
	private java.lang.String dbOrgCode;

	@Column(id = "db_org_name", datatype = "string2000")
	private java.lang.String dbOrgName;

	@Column(id = "db_apply_id", datatype = "string256")
	private java.lang.String dbApplyId;

	@Column(id = "db_apply_name", datatype = "string256")
	private java.lang.String dbApplyName;

	@Column(id = "db_submit_time", datatype = "date")
	private java.sql.Date dbSubmitTime;

	@Column(id = "jc_type_id", datatype = "string20")
	private java.lang.String jcTypeId;

	@Column(id = "jc_type_name", datatype = "string256")
	private java.lang.String jcTypeName;

	@Column(id = "jc_result_id", datatype = "string20")
	private java.lang.String jcResultId;

	@Column(id = "jc_result_name", datatype = "string32")
	private java.lang.String jcResultName;

	@Column(id = "supervise_info_id", datatype = "string1024")
	private java.lang.String superviseInfoId;

	/**
	 * 设置${field.desc}
	 */
	public void setDbId(java.lang.String dbId) {
		this.dbId = dbId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getDbId() {
		return dbId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDbTitle(java.lang.String dbTitle) {
		this.dbTitle = dbTitle;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getDbTitle() {
		return dbTitle;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDbOrgId(java.lang.String dbOrgId) {
		this.dbOrgId = dbOrgId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getDbOrgId() {
		return dbOrgId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDbOrgCode(java.lang.String dbOrgCode) {
		this.dbOrgCode = dbOrgCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getDbOrgCode() {
		return dbOrgCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDbOrgName(java.lang.String dbOrgName) {
		this.dbOrgName = dbOrgName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getDbOrgName() {
		return dbOrgName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDbApplyId(java.lang.String dbApplyId) {
		this.dbApplyId = dbApplyId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getDbApplyId() {
		return dbApplyId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDbApplyName(java.lang.String dbApplyName) {
		this.dbApplyName = dbApplyName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getDbApplyName() {
		return dbApplyName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDbSubmitTime(java.sql.Date dbSubmitTime) {
		this.dbSubmitTime = dbSubmitTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getDbSubmitTime() {
		return dbSubmitTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setJcTypeId(java.lang.String jcTypeId) {
		this.jcTypeId = jcTypeId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getJcTypeId() {
		return jcTypeId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setJcTypeName(java.lang.String jcTypeName) {
		this.jcTypeName = jcTypeName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getJcTypeName() {
		return jcTypeName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setJcResultId(java.lang.String jcResultId) {
		this.jcResultId = jcResultId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getJcResultId() {
		return jcResultId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setJcResultName(java.lang.String jcResultName) {
		this.jcResultName = jcResultName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getJcResultName() {
		return jcResultName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}
}
