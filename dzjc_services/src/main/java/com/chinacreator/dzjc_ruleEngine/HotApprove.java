package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 热点事项
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.HotApprove", table = "V_HOT_APPROVE", ds = "acceptdata", cache = false)
public class HotApprove {
	/**
	 *事项id
	 */
	@Column(id = "approve_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String approveId;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string1024")
	private java.lang.String approveName;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string512")
	private java.lang.String orgName;

	/**
	 *办结数
	 */
	@Column(id = "cn", datatype = "bigdouble")
	private java.lang.Double cn;

	/**
	 *承诺期限
	 */
	@Column(id = "promises_limit", datatype = "bigdouble")
	private java.lang.Double promisesLimit;

	/**
	 *统计开始时间
	 */
	@Column(id = "beginDate", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *统计结束时间
	 */
	@Column(id = "endDate", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *机构Id
	 */
	@Column(id = "orgId", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *行政区码
	 */
	@Column(id = "areaCode", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *受理数
	 */
	@Column(id = "acceptNum", datatype = "bigdouble")
	private java.lang.Double acceptNum;

	/**
	 *法定期限
	 */
	@Column(id = "timeLimit", datatype = "bigdouble")
	private java.lang.Double timeLimit;

	/**
	 * 设置事项id
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取事项id
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置事项名称
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取事项名称
	 */
	public java.lang.String getApproveName() {
		return approveName;
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
	 * 设置办结数
	 */
	public void setCn(java.lang.Double cn) {
		this.cn = cn;
	}

	/**
	 * 获取办结数
	 */
	public java.lang.Double getCn() {
		return cn;
	}

	/**
	 * 设置承诺期限
	 */
	public void setPromisesLimit(java.lang.Double promisesLimit) {
		this.promisesLimit = promisesLimit;
	}

	/**
	 * 获取承诺期限
	 */
	public java.lang.Double getPromisesLimit() {
		return promisesLimit;
	}

	/**
	 * 设置统计开始时间
	 */
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取统计开始时间
	 */
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置统计结束时间
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取统计结束时间
	 */
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置机构Id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构Id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置行政区码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取行政区码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置受理数
	 */
	public void setAcceptNum(java.lang.Double acceptNum) {
		this.acceptNum = acceptNum;
	}

	/**
	 * 获取受理数
	 */
	public java.lang.Double getAcceptNum() {
		return acceptNum;
	}

	/**
	 * 设置法定期限
	 */
	public void setTimeLimit(java.lang.Double timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 获取法定期限
	 */
	public java.lang.Double getTimeLimit() {
		return timeLimit;
	}
}
