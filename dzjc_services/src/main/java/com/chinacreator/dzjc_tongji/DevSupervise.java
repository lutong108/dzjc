package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 开发监察
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.DevSupervise", table = "V_TA_JC_DEV_SUPERVISE", ds = "acceptdata", cache = false)
public class DevSupervise {
	/**
	 *发牌id
	 */
	@Column(id = "supervise_info_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String superviseInfoId;

	/**
	 *业务id
	 */
	@Column(id = "business_id", datatype = "string1024")
	private java.lang.String businessId;

	/**
	 *发牌类型Code
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *发牌结果Code
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *发牌时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *状态
	 */
	@Column(id = "state", datatype = "string20")
	private java.lang.String state;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *发牌类型Name
	 */
	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	/**
	 *业务类型
	 */
	@Column(id = "business_type", datatype = "string20")
	private java.lang.String businessType;

	/**
	 *发牌结果Name
	 */
	@Column(id = "supervise_result_name", datatype = "string32")
	private java.lang.String superviseResultName;

	/**
	 *发牌级别
	 */
	@Column(id = "supervise_level", datatype = "tinyint")
	private java.lang.Integer superviseLevel;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string1024")
	private java.lang.String orgName;

	/**
	 *是否最高发牌(1:是；0:否)
	 */
	@Column(id = "ismaxsupervise", datatype = "string32")
	private java.lang.String ismaxsupervise;
	
	private java.sql.Date beginDate;
	private java.sql.Date endDate;
	
	/**
	 *承诺时限
	 */
	@Column(id = "promise_limit", datatype = "string32")
	private java.lang.Double promiseLimit;
	/**
	 *承诺时限
	 */
	@Column(id = "process_limit", datatype = "string32")
	private java.lang.Double processLimit;

	
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 设置发牌id
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取发牌id
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置业务id
	 */
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取业务id
	 */
	public java.lang.String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置发牌类型Code
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取发牌类型Code
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置发牌结果Code
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取发牌结果Code
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置发牌时间
	 */
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取发牌时间
	 */
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置状态
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取状态
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * 设置机构ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置发牌类型Name
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取发牌类型Name
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}

	/**
	 * 设置业务类型
	 */
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 获取业务类型
	 */
	public java.lang.String getBusinessType() {
		return businessType;
	}

	/**
	 * 设置发牌结果Name
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取发牌结果Name
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
	}

	/**
	 * 设置发牌级别
	 */
	public void setSuperviseLevel(java.lang.Integer superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	/**
	 * 获取发牌级别
	 */
	public java.lang.Integer getSuperviseLevel() {
		return superviseLevel;
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
	 * 设置是否最高发牌(1:是；0:否)
	 */
	public void setIsmaxsupervise(java.lang.String ismaxsupervise) {
		this.ismaxsupervise = ismaxsupervise;
	}

	/**
	 * 获取是否最高发牌(1:是；0:否)
	 */
	public java.lang.String isIsmaxsupervise() {
		return ismaxsupervise;
	}

	public java.lang.Double getPromiseLimit() {
		return promiseLimit;
	}

	public void setPromiseLimit(java.lang.Double promiseLimit) {
		this.promiseLimit = promiseLimit;
	}

	public java.lang.Double getProcessLimit() {
		return processLimit;
	}

	public void setProcessLimit(java.lang.Double processLimit) {
		this.processLimit = processLimit;
	}
}
