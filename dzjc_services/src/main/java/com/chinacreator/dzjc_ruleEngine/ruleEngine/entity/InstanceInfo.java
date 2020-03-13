package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 获取办件信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo", table = "TA_SP_INSTANCE", ds = "acceptdata", cache = false)
public class InstanceInfo {
	/**
	 *实例ID
	 */
	@Column(id = "instance_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *实例编码
	 */
	@Column(id = "instance_code", datatype = "string64")
	private java.lang.String instanceCode;

	/**
	 *事项编码
	 */
	@Column(id = "approve_code", datatype = "string64")
	private java.lang.String approveCode;

	/**
	 *项目名称
	 */
	@Column(id = "instance_name", datatype = "string256")
	private java.lang.String instanceName;

	/**
	 *项目拼音首字母
	 */
	@Column(id = "instance_py", datatype = "string256")
	private java.lang.String instancePy;

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
	 *办结人
	 */
	@Column(id = "end_name", datatype = "string64")
	private java.lang.String endName;

	/**
	 *办结时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *所用时长
	 */
	@Column(id = "time_use", datatype = "bigdouble")
	private java.lang.Double timeUse;

	/**
	 *申请者ID
	 */
	@Column(id = "apply_id", datatype = "string64")
	private java.lang.String applyId;

	/**
	 *申请者类型(1个人；2企业
	 */
	@Column(id = "apply_type", datatype = "string5")
	private java.lang.String applyType;

	/**
	 *申请者名称
	 */
	@Column(id = "apply_name", datatype = "string256")
	private java.lang.String applyName;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *项目状态(0；暂存；1受理；2不予受理；3补正补齐；4业务办结；5统一办结；6办结；7作废办结；8退窗

	办结；9网上提交；10预审通过；11预审不通过；12 已下发;20： 并联暂存  21：并联分发)
	 */
	@Column(id = "project_state", datatype = "string5")
	private java.lang.String projectState;

	/**
	 *排队取号票号
	 */
	@Column(id = "queue_code", datatype = "string10")
	private java.lang.String queueCode;

	/**
	 *查询编码
	 */
	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	/**
	 *不予受理或补正补齐原因
	 */
	@Column(id = "notaccept_reason", datatype = "string2000")
	private java.lang.String notacceptReason;

	/**
	 *补正补齐时间
	 */
	@Column(id = "correction_time", datatype = "date")
	private java.sql.Date correctionTime;

	/**
	 *事项ID
	 */
	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	/**
	 *办件来源(1,统一受理；2，网上大厅；3，一体机；4，微信；5，APP；6，自建系统）
	 */
	@Column(id = "instance_source", datatype = "string5")
	private java.lang.String instanceSource;

	/**
	 *提交时间
	 */
	@Column(id = "submit_time", datatype = "date")
	private java.sql.Date submitTime;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	/**
	 *预审意见
	 */
	@Column(id = "pre_reason", datatype = "string512")
	private java.lang.String preReason;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string512")
	private java.lang.String approveName;

	/**
	 *受理人ID
	 */
	@Column(id = "accept_id", datatype = "string64")
	private java.lang.String acceptId;

	/**
	 *办结人ID
	 */
	@Column(id = "end_id", datatype = "string64")
	private java.lang.String endId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string128")
	private java.lang.String orgName;

	/**
	 *当前环节名称
	 */
	@Column(id = "cur_tache", datatype = "string128")
	private java.lang.String curTache;

	/**
	 *是否需要寄送办件结果物(Y/N)
	 */
	@Column(id = "is_sendresult", datatype = "string5")
	private java.lang.String isSendresult;

	/**
	 *认证平台的申请者ID
	 */
	@Column(id = "rz_apply_id", datatype = "string64")
	private java.lang.String rzApplyId;

	/**
	 *流程实例ID
	 */
	@Column(id = "process_instanceid", datatype = "string128")
	private java.lang.String processInstanceid;

	/**
	 *流程定义ID
	 */
	@Column(id = "process_definitionid", datatype = "string128")
	private java.lang.String processDefinitionid;

	/**
	 *是否推送物流（0未推送，1已推送）
	 */
	@Column(id = "is_wlts", datatype = "string32")
	private java.lang.String isWlts;

	/**
	 *代办人证件号
	 */
	@Column(id = "transactor_id", datatype = "string64")
	private java.lang.String transactorId;

	/**
	 *是否已交换(N/Y,默认:N)
	 */
	@Column(id = "is_exchange", datatype = "string5")
	private java.lang.String isExchange;

	/**
	 *受理人机构ID
	 */
	@Column(id = "accept_org_id", datatype = "string64")
	private java.lang.String acceptOrgId;

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
	 *网办是否需要监察
	 */
	@Column(id = "is_need_supervise2", datatype = "string20")
	private java.lang.String isNeedSupervise2;

	/**
	 *网办最近发牌
	 */
	@Column(id = "last_supervise2", datatype = "string64")
	private java.lang.String lastSupervise2;

	/**
	 *预审时间
	 */
	@Column(id = "pretrial_time", datatype = "date")
	private java.sql.Date pretrialTime;

	/**
	 *法定时限
	 */
	@Column(id = "approve_limit", datatype = "double")
	private java.lang.Double approveLimit;

	/**
	 *承诺时限
	 */
	@Column(id = "commitment_limit", datatype = "double")
	private java.lang.Double commitmentLimit;

	/**
	 *电子证照是否需要监察
	 */
	@Column(id = "is_need_supervise3", datatype = "string20")
	private java.lang.String isNeedSupervise3;

	/**
	 *电子证照最近发牌
	 */
	@Column(id = "last_supervise3", datatype = "string64")
	private java.lang.String lastSupervise3;

	/**
	 *办件所用时长（单位：天）
	 */
	@Column(id = "completed_time", datatype = "bigdouble")
	private java.lang.Double completedTime;

	/**
	 *发牌时特别程序计算时间
	 */
	@Column(id = "tbcx_times", datatype = "string128")
	private java.lang.String tbcxTimes;

	/**
	 *工作时间拼接的字符串，以##拼接
	 */
	@Column(id = "work_time", datatype = "string128")
	private java.lang.String workTime;

	/**
	 *节假日天数
	 */
	@Column(id = "holiday_count", datatype = "int")
	private java.lang.Integer holidayCount;

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
	 * 设置实例编码
	 */
	public void setInstanceCode(java.lang.String instanceCode) {
		this.instanceCode = instanceCode;
	}

	/**
	 * 获取实例编码
	 */
	public java.lang.String getInstanceCode() {
		return instanceCode;
	}

	/**
	 * 设置事项编码
	 */
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 获取事项编码
	 */
	public java.lang.String getApproveCode() {
		return approveCode;
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
	 * 设置项目拼音首字母
	 */
	public void setInstancePy(java.lang.String instancePy) {
		this.instancePy = instancePy;
	}

	/**
	 * 获取项目拼音首字母
	 */
	public java.lang.String getInstancePy() {
		return instancePy;
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
	 * 设置办结人
	 */
	public void setEndName(java.lang.String endName) {
		this.endName = endName;
	}

	/**
	 * 获取办结人
	 */
	public java.lang.String getEndName() {
		return endName;
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
	 * 设置所用时长
	 */
	public void setTimeUse(java.lang.Double timeUse) {
		this.timeUse = timeUse;
	}

	/**
	 * 获取所用时长
	 */
	public java.lang.Double getTimeUse() {
		return timeUse;
	}

	/**
	 * 设置申请者ID
	 */
	public void setApplyId(java.lang.String applyId) {
		this.applyId = applyId;
	}

	/**
	 * 获取申请者ID
	 */
	public java.lang.String getApplyId() {
		return applyId;
	}

	/**
	 * 设置申请者类型(1个人；2企业
	 */
	public void setApplyType(java.lang.String applyType) {
		this.applyType = applyType;
	}

	/**
	 * 获取申请者类型(1个人；2企业
	 */
	public java.lang.String getApplyType() {
		return applyType;
	}

	/**
	 * 设置申请者名称
	 */
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	/**
	 * 获取申请者名称
	 */
	public java.lang.String getApplyName() {
		return applyName;
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
	 * 设置区域编码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取区域编码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置项目状态(0；暂存；1受理；2不予受理；3补正补齐；4业务办结；5统一办结；6办结；7作废办结；8退窗

	办结；9网上提交；10预审通过；11预审不通过；12 已下发;20： 并联暂存  21：并联分发)
	 */
	public void setProjectState(java.lang.String projectState) {
		this.projectState = projectState;
	}

	/**
	 * 获取项目状态(0；暂存；1受理；2不予受理；3补正补齐；4业务办结；5统一办结；6办结；7作废办结；8退窗

	办结；9网上提交；10预审通过；11预审不通过；12 已下发;20： 并联暂存  21：并联分发)
	 */
	public java.lang.String getProjectState() {
		return projectState;
	}

	/**
	 * 设置排队取号票号
	 */
	public void setQueueCode(java.lang.String queueCode) {
		this.queueCode = queueCode;
	}

	/**
	 * 获取排队取号票号
	 */
	public java.lang.String getQueueCode() {
		return queueCode;
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
	 * 设置不予受理或补正补齐原因
	 */
	public void setNotacceptReason(java.lang.String notacceptReason) {
		this.notacceptReason = notacceptReason;
	}

	/**
	 * 获取不予受理或补正补齐原因
	 */
	public java.lang.String getNotacceptReason() {
		return notacceptReason;
	}

	/**
	 * 设置补正补齐时间
	 */
	public void setCorrectionTime(java.sql.Date correctionTime) {
		this.correctionTime = correctionTime;
	}

	/**
	 * 获取补正补齐时间
	 */
	public java.sql.Date getCorrectionTime() {
		return correctionTime;
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
	 * 设置办件来源(1,统一受理；2，网上大厅；3，一体机；4，微信；5，APP；6，自建系统）
	 */
	public void setInstanceSource(java.lang.String instanceSource) {
		this.instanceSource = instanceSource;
	}

	/**
	 * 获取办件来源(1,统一受理；2，网上大厅；3，一体机；4，微信；5，APP；6，自建系统）
	 */
	public java.lang.String getInstanceSource() {
		return instanceSource;
	}

	/**
	 * 设置提交时间
	 */
	public void setSubmitTime(java.sql.Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * 获取提交时间
	 */
	public java.sql.Date getSubmitTime() {
		return submitTime;
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
	 * 设置预审意见
	 */
	public void setPreReason(java.lang.String preReason) {
		this.preReason = preReason;
	}

	/**
	 * 获取预审意见
	 */
	public java.lang.String getPreReason() {
		return preReason;
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
	 * 设置受理人ID
	 */
	public void setAcceptId(java.lang.String acceptId) {
		this.acceptId = acceptId;
	}

	/**
	 * 获取受理人ID
	 */
	public java.lang.String getAcceptId() {
		return acceptId;
	}

	/**
	 * 设置办结人ID
	 */
	public void setEndId(java.lang.String endId) {
		this.endId = endId;
	}

	/**
	 * 获取办结人ID
	 */
	public java.lang.String getEndId() {
		return endId;
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
	 * 设置当前环节名称
	 */
	public void setCurTache(java.lang.String curTache) {
		this.curTache = curTache;
	}

	/**
	 * 获取当前环节名称
	 */
	public java.lang.String getCurTache() {
		return curTache;
	}

	/**
	 * 设置是否需要寄送办件结果物(Y/N)
	 */
	public void setIsSendresult(java.lang.String isSendresult) {
		this.isSendresult = isSendresult;
	}

	/**
	 * 获取是否需要寄送办件结果物(Y/N)
	 */
	public java.lang.String getIsSendresult() {
		return isSendresult;
	}

	/**
	 * 设置认证平台的申请者ID
	 */
	public void setRzApplyId(java.lang.String rzApplyId) {
		this.rzApplyId = rzApplyId;
	}

	/**
	 * 获取认证平台的申请者ID
	 */
	public java.lang.String getRzApplyId() {
		return rzApplyId;
	}

	/**
	 * 设置流程实例ID
	 */
	public void setProcessInstanceid(java.lang.String processInstanceid) {
		this.processInstanceid = processInstanceid;
	}

	/**
	 * 获取流程实例ID
	 */
	public java.lang.String getProcessInstanceid() {
		return processInstanceid;
	}

	/**
	 * 设置流程定义ID
	 */
	public void setProcessDefinitionid(java.lang.String processDefinitionid) {
		this.processDefinitionid = processDefinitionid;
	}

	/**
	 * 获取流程定义ID
	 */
	public java.lang.String getProcessDefinitionid() {
		return processDefinitionid;
	}

	/**
	 * 设置是否推送物流（0未推送，1已推送）
	 */
	public void setIsWlts(java.lang.String isWlts) {
		this.isWlts = isWlts;
	}

	/**
	 * 获取是否推送物流（0未推送，1已推送）
	 */
	public java.lang.String getIsWlts() {
		return isWlts;
	}

	/**
	 * 设置代办人证件号
	 */
	public void setTransactorId(java.lang.String transactorId) {
		this.transactorId = transactorId;
	}

	/**
	 * 获取代办人证件号
	 */
	public java.lang.String getTransactorId() {
		return transactorId;
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
	 * 设置受理人机构ID
	 */
	public void setAcceptOrgId(java.lang.String acceptOrgId) {
		this.acceptOrgId = acceptOrgId;
	}

	/**
	 * 获取受理人机构ID
	 */
	public java.lang.String getAcceptOrgId() {
		return acceptOrgId;
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
	 * 设置网办是否需要监察
	 */
	public void setIsNeedSupervise2(java.lang.String isNeedSupervise2) {
		this.isNeedSupervise2 = isNeedSupervise2;
	}

	/**
	 * 获取网办是否需要监察
	 */
	public java.lang.String getIsNeedSupervise2() {
		return isNeedSupervise2;
	}

	/**
	 * 设置网办最近发牌
	 */
	public void setLastSupervise2(java.lang.String lastSupervise2) {
		this.lastSupervise2 = lastSupervise2;
	}

	/**
	 * 获取网办最近发牌
	 */
	public java.lang.String getLastSupervise2() {
		return lastSupervise2;
	}

	/**
	 * 设置预审时间
	 */
	public void setPretrialTime(java.sql.Date pretrialTime) {
		this.pretrialTime = pretrialTime;
	}

	/**
	 * 获取预审时间
	 */
	public java.sql.Date getPretrialTime() {
		return pretrialTime;
	}

	/**
	 * 设置法定时限
	 */
	public void setApproveLimit(java.lang.Double approveLimit) {
		this.approveLimit = approveLimit;
	}

	/**
	 * 获取法定时限
	 */
	public java.lang.Double getApproveLimit() {
		return approveLimit;
	}

	/**
	 * 设置承诺时限
	 */
	public void setCommitmentLimit(java.lang.Double commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}

	/**
	 * 获取承诺时限
	 */
	public java.lang.Double getCommitmentLimit() {
		return commitmentLimit;
	}

	/**
	 * 设置电子证照是否需要监察
	 */
	public void setIsNeedSupervise3(java.lang.String isNeedSupervise3) {
		this.isNeedSupervise3 = isNeedSupervise3;
	}

	/**
	 * 获取电子证照是否需要监察
	 */
	public java.lang.String getIsNeedSupervise3() {
		return isNeedSupervise3;
	}

	/**
	 * 设置电子证照最近发牌
	 */
	public void setLastSupervise3(java.lang.String lastSupervise3) {
		this.lastSupervise3 = lastSupervise3;
	}

	/**
	 * 获取电子证照最近发牌
	 */
	public java.lang.String getLastSupervise3() {
		return lastSupervise3;
	}

	/**
	 * 设置办件所用时长（单位：天）
	 */
	public void setCompletedTime(java.lang.Double completedTime) {
		this.completedTime = completedTime;
	}

	/**
	 * 获取办件所用时长（单位：天）
	 */
	public java.lang.Double getCompletedTime() {
		return completedTime;
	}

	/**
	 * 设置发牌时特别程序计算时间
	 */
	public void setTbcxTimes(java.lang.String tbcxTimes) {
		this.tbcxTimes = tbcxTimes;
	}

	/**
	 * 获取发牌时特别程序计算时间
	 */
	public java.lang.String getTbcxTimes() {
		return tbcxTimes;
	}

	/**
	 * 设置工作时间拼接的字符串，以##拼接
	 */
	public void setWorkTime(java.lang.String workTime) {
		this.workTime = workTime;
	}

	/**
	 * 获取工作时间拼接的字符串，以##拼接
	 */
	public java.lang.String getWorkTime() {
		return workTime;
	}

	/**
	 * 设置节假日天数
	 */
	public void setHolidayCount(java.lang.Integer holidayCount) {
		this.holidayCount = holidayCount;
	}

	/**
	 * 获取节假日天数
	 */
	public java.lang.Integer getHolidayCount() {
		return holidayCount;
	}
}
