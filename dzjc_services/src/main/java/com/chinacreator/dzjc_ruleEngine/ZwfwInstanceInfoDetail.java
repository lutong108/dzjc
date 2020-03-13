package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 政务服务办件信息详情
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ZwfwInstanceInfoDetail", table = "VA_TA_JC_ZWFW_INSTANCE_DETAIL", ds = "acceptdata", cache = false)
public class ZwfwInstanceInfoDetail {
	/**
	 *办件Id
	 */
	@Column(id = "instance_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string128")
	private java.lang.String orgName;

	/**
	 *办件名称
	 */
	@Column(id = "instance_name", datatype = "string256")
	private java.lang.String instanceName;

	/**
	 *办件编号
	 */
	@Column(id = "instance_code", datatype = "string64")
	private java.lang.String instanceCode;

	/**
	 *申请人
	 */
	@Column(id = "apply_name", datatype = "string256")
	private java.lang.String applyName;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string512")
	private java.lang.String approveName;

	/**
	 *查询编码
	 */
	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	/**
	 *办理状态
	 */
	@Column(id = "project_state", datatype = "string5")
	private java.lang.String projectState;

	/**
	 *受理时间
	 */
	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	/**
	 *办结时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *法定期限
	 */
	@Column(id = "fdqx", datatype = "bigdouble")
	private java.lang.Double fdqx;

	/**
	 *承诺期限
	 */
	@Column(id = "cnqx", datatype = "bigdouble")
	private java.lang.Double cnqx;

	/**
	 *已用时长
	 */
	@Column(id = "time_use", datatype = "bigdouble")
	private java.lang.Double timeUse;

	/**
	 *监察结果编号
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *监察结果
	 */
	@Column(id = "supervise_result_name", datatype = "string32")
	private java.lang.String superviseResultName;

	/**
	 * 设置办件Id
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取办件Id
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
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
	 * 设置办件名称
	 */
	public void setInstanceName(java.lang.String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * 获取办件名称
	 */
	public java.lang.String getInstanceName() {
		return instanceName;
	}

	/**
	 * 设置办件编号
	 */
	public void setInstanceCode(java.lang.String instanceCode) {
		this.instanceCode = instanceCode;
	}

	/**
	 * 获取办件编号
	 */
	public java.lang.String getInstanceCode() {
		return instanceCode;
	}

	/**
	 * 设置申请人
	 */
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	/**
	 * 获取申请人
	 */
	public java.lang.String getApplyName() {
		return applyName;
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
	 * 设置办理状态
	 */
	public void setProjectState(java.lang.String projectState) {
		this.projectState = projectState;
	}

	/**
	 * 获取办理状态
	 */
	public java.lang.String getProjectState() {
		return projectState;
	}

	/**
	 * 设置受理时间
	 */
	public void setAcceptTime(java.sql.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取受理时间
	 */
	public java.sql.Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置办结时间
	 */
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取办结时间
	 */
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置法定期限
	 */
	public void setFdqx(java.lang.Double fdqx) {
		this.fdqx = fdqx;
	}

	/**
	 * 获取法定期限
	 */
	public java.lang.Double getFdqx() {
		return fdqx;
	}

	/**
	 * 设置承诺期限
	 */
	public void setCnqx(java.lang.Double cnqx) {
		this.cnqx = cnqx;
	}

	/**
	 * 获取承诺期限
	 */
	public java.lang.Double getCnqx() {
		return cnqx;
	}

	/**
	 * 设置已用时长
	 */
	public void setTimeUse(java.lang.Double timeUse) {
		this.timeUse = timeUse;
	}

	/**
	 * 获取已用时长
	 */
	public java.lang.Double getTimeUse() {
		return timeUse;
	}

	/**
	 * 设置监察结果编号
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果编号
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置监察结果
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取监察结果
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
	}
}
