package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察信息已删除列表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseDeletelog", table = "V_TA_JC_SUPERVISE_DELETELOG", ds = "acceptdata", cache = false)
public class SuperviseDeletelog {
	/**
	 *主键
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String id;

	/**
	 *监察结果
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *监察类型编码
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *监察类型名称
	 */
	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *业务id
	 */
	@Column(id = "bussiness_id", datatype = "string64")
	private java.lang.String bussinessId;

	/**
	 *查询编码
	 */
	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	/**
	 *业务编码
	 */
	@Column(id = "bussiness_code", datatype = "string128")
	private java.lang.String bussinessCode;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string1024")
	private java.lang.String orgName;

	/**
	 *业务名称
	 */
	@Column(id = "bussiness_name", datatype = "string512")
	private java.lang.String bussinessName;

	/**
	 *接受时间
	 */
	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	/**
	 *删除时间
	 */
	@Column(id = "delete_date", datatype = "date")
	private java.sql.Date deleteDate;

	/**
	 * 设置主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置监察结果
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置监察类型编码
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取监察类型编码
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置监察类型名称
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取监察类型名称
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}

	/**
	 * 设置监察时间
	 */
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取监察时间
	 */
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置业务id
	 */
	public void setBussinessId(java.lang.String bussinessId) {
		this.bussinessId = bussinessId;
	}

	/**
	 * 获取业务id
	 */
	public java.lang.String getBussinessId() {
		return bussinessId;
	}

	/**
	 * 设置查询编码
	 */
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}

	/**
	 * 获取查询编码
	 */
	public java.lang.String getQueryCode() {
		return queryCode;
	}

	/**
	 * 设置业务编码
	 */
	public void setBussinessCode(java.lang.String bussinessCode) {
		this.bussinessCode = bussinessCode;
	}

	/**
	 * 获取业务编码
	 */
	public java.lang.String getBussinessCode() {
		return bussinessCode;
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
	 * 设置机构名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取机构名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置业务名称
	 */
	public void setBussinessName(java.lang.String bussinessName) {
		this.bussinessName = bussinessName;
	}

	/**
	 * 获取业务名称
	 */
	public java.lang.String getBussinessName() {
		return bussinessName;
	}

	/**
	 * 设置接受时间
	 */
	public void setAcceptTime(java.sql.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取接受时间
	 */
	public java.sql.Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置删除时间
	 */
	public void setDeleteDate(java.sql.Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	/**
	 * 获取删除时间
	 */
	public java.sql.Date getDeleteDate() {
		return deleteDate;
	}
}
