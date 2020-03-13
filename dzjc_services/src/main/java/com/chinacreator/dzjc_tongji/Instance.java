package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 办件实例
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.Instance", table = "TA_SP_INSTANCE", ds = "acceptdata", cache = false)
public class Instance {
	/**
	 *实例ID
	 */
	@Column(id = "instance_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *项目名称
	 */
	@Column(id = "instance_name", datatype = "string256")
	private java.lang.String instanceName;

	/**
	 *受理人
	 */
	@Column(id = "accept_name", datatype = "string64")
	private java.lang.String acceptName;

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
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *项目状态(0；暂存；1受理；2不予受理；3补正补齐；4业务办结；5统一办结；6办结；7作废办结；8退窗办结；9网上提交；10预审通过；11预审不通过；12 已下发;20： 并联暂存  21：并联分发)
	 */
	@Column(id = "project_state", datatype = "string5")
	private java.lang.String projectState;

	/**
	 *事项ID
	 */
	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string512")
	private java.lang.String approveName;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string128")
	private java.lang.String orgName;

	/**
	 *是否需要监察
	 */
	@Column(id = "is_need_supervise", datatype = "string20")
	private java.lang.String isNeedSupervise;

	/**
	 *最近发牌
	 */
	@Column(id = "last_supervise", datatype = "string64")
	private java.lang.String lastSupervise;

	/**
	 *受理开始时间
	 */
	@Column(id = "acceptBeginTime", datatype = "date")
	private java.sql.Date acceptBeginTime;

	/**
	 *受理结束时间
	 */
	@Column(id = "acceptEndTime", datatype = "date")
	private java.sql.Date acceptEndTime;

	/**
	 *办结开始时间
	 */
	@Column(id = "finishBeginTime", datatype = "date")
	private java.sql.Date finishBeginTime;

	/**
	 *办结结束时间
	 */
	@Column(id = "finishEndTime", datatype = "date")
	private java.sql.Date finishEndTime;

	/**
	 * 设置实例ID
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取实例ID
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置项目名称
	 */
	public void setInstanceName(java.lang.String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * 获取项目名称
	 */
	public java.lang.String getInstanceName() {
		return instanceName;
	}

	/**
	 * 设置受理人
	 */
	public void setAcceptName(java.lang.String acceptName) {
		this.acceptName = acceptName;
	}

	/**
	 * 获取受理人
	 */
	public java.lang.String getAcceptName() {
		return acceptName;
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
	 * 设置项目状态(0；暂存；1受理；2不予受理；3补正补齐；4业务办结；5统一办结；6办结；7作废办结；8退窗办结；9网上提交；10预审通过；11预审不通过；12 已下发;20： 并联暂存  21：并联分发)
	 */
	public void setProjectState(java.lang.String projectState) {
		this.projectState = projectState;
	}

	/**
	 * 获取项目状态(0；暂存；1受理；2不予受理；3补正补齐；4业务办结；5统一办结；6办结；7作废办结；8退窗办结；9网上提交；10预审通过；11预审不通过；12 已下发;20： 并联暂存  21：并联分发)
	 */
	public java.lang.String getProjectState() {
		return projectState;
	}

	/**
	 * 设置事项ID
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取事项ID
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
	 * 设置是否需要监察
	 */
	public void setIsNeedSupervise(java.lang.String isNeedSupervise) {
		this.isNeedSupervise = isNeedSupervise;
	}

	/**
	 * 获取是否需要监察
	 */
	public java.lang.String getIsNeedSupervise() {
		return isNeedSupervise;
	}

	/**
	 * 设置最近发牌
	 */
	public void setLastSupervise(java.lang.String lastSupervise) {
		this.lastSupervise = lastSupervise;
	}

	/**
	 * 获取最近发牌
	 */
	public java.lang.String getLastSupervise() {
		return lastSupervise;
	}

	/**
	 * 设置受理开始时间
	 */
	public void setAcceptBeginTime(java.sql.Date acceptBeginTime) {
		this.acceptBeginTime = acceptBeginTime;
	}

	/**
	 * 获取受理开始时间
	 */
	public java.sql.Date getAcceptBeginTime() {
		return acceptBeginTime;
	}

	/**
	 * 设置受理结束时间
	 */
	public void setAcceptEndTime(java.sql.Date acceptEndTime) {
		this.acceptEndTime = acceptEndTime;
	}

	/**
	 * 获取受理结束时间
	 */
	public java.sql.Date getAcceptEndTime() {
		return acceptEndTime;
	}

	/**
	 * 设置办结开始时间
	 */
	public void setFinishBeginTime(java.sql.Date finishBeginTime) {
		this.finishBeginTime = finishBeginTime;
	}

	/**
	 * 获取办结开始时间
	 */
	public java.sql.Date getFinishBeginTime() {
		return finishBeginTime;
	}

	/**
	 * 设置办结结束时间
	 */
	public void setFinishEndTime(java.sql.Date finishEndTime) {
		this.finishEndTime = finishEndTime;
	}

	/**
	 * 获取办结结束时间
	 */
	public java.sql.Date getFinishEndTime() {
		return finishEndTime;
	}
}
