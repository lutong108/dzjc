package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 所有被删除业务列表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.BussinessAllDeleteInfo", table = "VA_TA_JC_ALLDELETEBUSSINESS_INFO", ds = "acceptdata", cache = false)
public class BussinessAllDeleteInfo {
	/**
	 *业务类型
	 */
	@Column(id = "bussiness_type", datatype = "string64")
	private java.lang.String bussinessType;

	/**
	 *主键ID
	 */
	@Column(id = "supervise_info_id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String superviseInfoId;

	/**
	 *发牌类型编号
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *发牌类型名称
	 */
	@Column(id = "supervise_type_name", datatype = "string64")
	private java.lang.String superviseTypeName;

	/**
	 *发牌结果编号
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *发牌结果名称
	 */
	@Column(id = "supervise_result_name", datatype = "string64")
	private java.lang.String superviseResultName;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *发牌状态
	 */
	@Column(id = "state", datatype = "string20")
	private java.lang.String state;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string64")
	private java.lang.String orgName;

	/**
	 *监察开始时间
	 */
	@Column(id = "jcBeginDate", datatype = "date")
	private java.sql.Date jcBeginDate;

	/**
	 *监察结束时间
	 */
	@Column(id = "jcEndDate", datatype = "date")
	private java.sql.Date jcEndDate;

	/**
	 *递归机构
	 */
	@Column(id = "temporgid", datatype = "string128")
	private java.lang.String temporgid;

	/**
	 *业务ID
	 */
	@Column(id = "business_id", datatype = "string128")
	private java.lang.String businessId;

	/**
	 * 设置业务类型
	 */
	public void setBussinessType(java.lang.String bussinessType) {
		this.bussinessType = bussinessType;
	}

	/**
	 * 获取业务类型
	 */
	public java.lang.String getBussinessType() {
		return bussinessType;
	}

	/**
	 * 设置主键ID
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置发牌类型编号
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取发牌类型编号
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置发牌类型名称
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取发牌类型名称
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}

	/**
	 * 设置发牌结果编号
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取发牌结果编号
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置发牌结果名称
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取发牌结果名称
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
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
	 * 设置发牌状态
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取发牌状态
	 */
	public java.lang.String getState() {
		return state;
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
	 * 设置监察开始时间
	 */
	public void setJcBeginDate(java.sql.Date jcBeginDate) {
		this.jcBeginDate = jcBeginDate;
	}

	/**
	 * 获取监察开始时间
	 */
	public java.sql.Date getJcBeginDate() {
		return jcBeginDate;
	}

	/**
	 * 设置监察结束时间
	 */
	public void setJcEndDate(java.sql.Date jcEndDate) {
		this.jcEndDate = jcEndDate;
	}

	/**
	 * 获取监察结束时间
	 */
	public java.sql.Date getJcEndDate() {
		return jcEndDate;
	}

	/**
	 * 设置递归机构
	 */
	public void setTemporgid(java.lang.String temporgid) {
		this.temporgid = temporgid;
	}

	/**
	 * 获取递归机构
	 */
	public java.lang.String getTemporgid() {
		return temporgid;
	}

	/**
	 * 设置业务ID
	 */
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取业务ID
	 */
	public java.lang.String getBusinessId() {
		return businessId;
	}
}
