package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 特别程序申请
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.Suspend", table = "TA_SP_SUSPEND", ds = "acceptdata", cache = false)
public class Suspend {
	/**
	 *特别程序主键ID
	 */
	@Column(id = "suspend_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String suspendId;

	/**
	 *审批实例ID
	 */
	@Column(id = "instance_id", datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *特别程序类型
	 */
	@Column(id = "special_type", datatype = "string64")
	private java.lang.String specialType;

	/**
	 *申请依据
	 */
	@Column(id = "start_reason", datatype = "string2000")
	private java.lang.String startReason;

	/**
	 *特别程序开始时间
	 */
	@Column(id = "start_date", datatype = "date")
	private java.sql.Date startDate;

	/**
	 *特别程序时限
	 */
	@Column(id = "special_timelimit", datatype = "int")
	private java.lang.Integer specialTimelimit;

	/**
	 *特别程序批准人
	 */
	@Column(id = "approver", datatype = "string64")
	private java.lang.String approver;

	/**
	 *特别程序批准人电话
	 */
	@Column(id = "approver_tel", datatype = "string64")
	private java.lang.String approverTel;

	/**
	 *最后修改时间
	 */
	@Column(id = "lastmodifiedtime", datatype = "date")
	private java.sql.Date lastmodifiedtime;

	/**
	 *操作人ID
	 */
	@Column(id = "suspend_userid", datatype = "string64")
	private java.lang.String suspendUserid;

	/**
	 *操作人
	 */
	@Column(id = "suspend_username", datatype = "string64")
	private java.lang.String suspendUsername;

	/**
	 *流程实例ID
	 */
	@Column(id = "proc_inst_id", datatype = "string64")
	private java.lang.String procInstId;

	/**
	 *特别程序环节定义ID
	 */
	@Column(id = "action_defid", datatype = "string64")
	private java.lang.String actionDefid;

	/**
	 *特别程序环节名称
	 */
	@Column(id = "action_name", datatype = "string64")
	private java.lang.String actionName;

	/**
	 *办件是否是特别程序申请阶段，且还未产生结果 ： Y是，N否
	 */
	@Column(id = "flag", datatype = "string5")
	private java.lang.String flag;

	/**
	 *是否已交换(N/Y,默认:N)
	 */
	@Column(id = "is_exchange", datatype = "string5")
	private java.lang.String isExchange;

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
	 *是否已交换标志2(N/Y,默认:N)
	 */
	@Column(id = "mod_flag", datatype = "string5")
	private java.lang.String modFlag;

	/**
	 * 设置特别程序主键ID
	 */
	public void setSuspendId(java.lang.String suspendId) {
		this.suspendId = suspendId;
	}

	/**
	 * 获取特别程序主键ID
	 */
	public java.lang.String getSuspendId() {
		return suspendId;
	}

	/**
	 * 设置审批实例ID
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取审批实例ID
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置特别程序类型
	 */
	public void setSpecialType(java.lang.String specialType) {
		this.specialType = specialType;
	}

	/**
	 * 获取特别程序类型
	 */
	public java.lang.String getSpecialType() {
		return specialType;
	}

	/**
	 * 设置申请依据
	 */
	public void setStartReason(java.lang.String startReason) {
		this.startReason = startReason;
	}

	/**
	 * 获取申请依据
	 */
	public java.lang.String getStartReason() {
		return startReason;
	}

	/**
	 * 设置特别程序开始时间
	 */
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取特别程序开始时间
	 */
	public java.sql.Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置特别程序时限
	 */
	public void setSpecialTimelimit(java.lang.Integer specialTimelimit) {
		this.specialTimelimit = specialTimelimit;
	}

	/**
	 * 获取特别程序时限
	 */
	public java.lang.Integer getSpecialTimelimit() {
		return specialTimelimit;
	}

	/**
	 * 设置特别程序批准人
	 */
	public void setApprover(java.lang.String approver) {
		this.approver = approver;
	}

	/**
	 * 获取特别程序批准人
	 */
	public java.lang.String getApprover() {
		return approver;
	}

	/**
	 * 设置特别程序批准人电话
	 */
	public void setApproverTel(java.lang.String approverTel) {
		this.approverTel = approverTel;
	}

	/**
	 * 获取特别程序批准人电话
	 */
	public java.lang.String getApproverTel() {
		return approverTel;
	}

	/**
	 * 设置最后修改时间
	 */
	public void setLastmodifiedtime(java.sql.Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}

	/**
	 * 获取最后修改时间
	 */
	public java.sql.Date getLastmodifiedtime() {
		return lastmodifiedtime;
	}

	/**
	 * 设置操作人ID
	 */
	public void setSuspendUserid(java.lang.String suspendUserid) {
		this.suspendUserid = suspendUserid;
	}

	/**
	 * 获取操作人ID
	 */
	public java.lang.String getSuspendUserid() {
		return suspendUserid;
	}

	/**
	 * 设置操作人
	 */
	public void setSuspendUsername(java.lang.String suspendUsername) {
		this.suspendUsername = suspendUsername;
	}

	/**
	 * 获取操作人
	 */
	public java.lang.String getSuspendUsername() {
		return suspendUsername;
	}

	/**
	 * 设置流程实例ID
	 */
	public void setProcInstId(java.lang.String procInstId) {
		this.procInstId = procInstId;
	}

	/**
	 * 获取流程实例ID
	 */
	public java.lang.String getProcInstId() {
		return procInstId;
	}

	/**
	 * 设置特别程序环节定义ID
	 */
	public void setActionDefid(java.lang.String actionDefid) {
		this.actionDefid = actionDefid;
	}

	/**
	 * 获取特别程序环节定义ID
	 */
	public java.lang.String getActionDefid() {
		return actionDefid;
	}

	/**
	 * 设置特别程序环节名称
	 */
	public void setActionName(java.lang.String actionName) {
		this.actionName = actionName;
	}

	/**
	 * 获取特别程序环节名称
	 */
	public java.lang.String getActionName() {
		return actionName;
	}

	/**
	 * 设置办件是否是特别程序申请阶段，且还未产生结果 ： Y是，N否
	 */
	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	/**
	 * 获取办件是否是特别程序申请阶段，且还未产生结果 ： Y是，N否
	 */
	public java.lang.String getFlag() {
		return flag;
	}

	/**
	 * 设置是否已交换(N/Y,默认:N)
	 */
	public void setIsExchange(java.lang.String isExchange) {
		this.isExchange = isExchange;
	}

	/**
	 * 获取是否已交换(N/Y,默认:N)
	 */
	public java.lang.String getIsExchange() {
		return isExchange;
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
	 * 设置是否已交换标志2(N/Y,默认:N)
	 */
	public void setModFlag(java.lang.String modFlag) {
		this.modFlag = modFlag;
	}

	/**
	 * 获取是否已交换标志2(N/Y,默认:N)
	 */
	public java.lang.String getModFlag() {
		return modFlag;
	}
}
