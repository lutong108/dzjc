package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 办件监察信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseInfoInstance", table = "VA_TA_JC_SUPERVISE_INFO", ds = "acceptdata", cache = false)
public class SuperviseInfoInstance {
	/**
	 *
	 */
	@Column(id = "supervise_result_no", datatype = "string64")
	private java.lang.String superviseResultNo;

	@Column(id = "instance_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String instanceId;

	@Column(id = "instance_code", datatype = "string64")
	private java.lang.String instanceCode;

	@Column(id = "approve_code", datatype = "string64")
	private java.lang.String approveCode;

	@Column(id = "instance_name", datatype = "string256")
	private java.lang.String instanceName;

	@Column(id = "instance_py", datatype = "string256")
	private java.lang.String instancePy;

	@Column(id = "accept_name", datatype = "string64")
	private java.lang.String acceptName;

	@Column(id = "accept_time", datatype = "date", sort = SortType.desc)
	private java.sql.Date acceptTime;

	@Column(id = "end_name", datatype = "string64")
	private java.lang.String endName;

	@Column(id = "end_time", datatype = "string64")
	private java.lang.String endTime;

	@Column(id = "time_use", datatype = "string64")
	private java.lang.String timeUse;

	@Column(id = "apply_id", datatype = "string64")
	private java.lang.String applyId;

	@Column(id = "apply_type", datatype = "string5")
	private java.lang.String applyType;

	@Column(id = "apply_name", datatype = "string256")
	private java.lang.String applyName;

	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	@Column(id = "project_state", datatype = "string5")
	private java.lang.String projectState;

	@Column(id = "queue_code", datatype = "string10")
	private java.lang.String queueCode;

	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	@Column(id = "notaccept_reason", datatype = "string1024")
	private java.lang.String notacceptReason;

	@Column(id = "correction_time", datatype = "string64")
	private java.lang.String correctionTime;

	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	@Column(id = "instance_source", datatype = "string5")
	private java.lang.String instanceSource;

	@Column(id = "submit_time", datatype = "string64")
	private java.lang.String submitTime;

	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	@Column(id = "pre_reason", datatype = "string512")
	private java.lang.String preReason;

	@Column(id = "approve_name", datatype = "string512")
	private java.lang.String approveName;

	@Column(id = "accept_id", datatype = "string64")
	private java.lang.String acceptId;

	@Column(id = "end_id", datatype = "string64")
	private java.lang.String endId;

	@Column(id = "org_name", datatype = "string128")
	private java.lang.String orgName;

	@Column(id = "cur_tache", datatype = "string128")
	private java.lang.String curTache;

	@Column(id = "is_sendresult", datatype = "string5")
	private java.lang.String isSendresult;

	@Column(id = "rz_apply_id", datatype = "string64")
	private java.lang.String rzApplyId;

	@Column(id = "process_instanceid", datatype = "string128")
	private java.lang.String processInstanceid;

	@Column(id = "process_definitionid", datatype = "string128")
	private java.lang.String processDefinitionid;

	@Column(id = "supervise_type_code", datatype = "string64")
	private java.lang.String superviseTypeCode;

	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	@Column(id = "sp_approve_name", datatype = "string512")
	private java.lang.String spApproveName;

	@Column(id = "sp_approve_code", datatype = "string64")
	private java.lang.String spApproveCode;

	@Column(id = "sp_trans_type_code", datatype = "string64")
	private java.lang.String spTransTypeCode;

	@Column(id = "sp_approve_type_code", datatype = "string64")
	private java.lang.String spApproveTypeCode;

	@Column(id = "time_limit", datatype = "string64")
	private java.lang.String timeLimit;

	@Column(id = "is_charge", datatype = "string64")
	private java.lang.String isCharge;

	@Column(id = "is_available", datatype = "string64")
	private java.lang.String isAvailable;

	@Column(id = "promises_limit", datatype = "string64")
	private java.lang.String promisesLimit;

	/**
	 *监察结果
	 */
	@Column(id = "supervise_Result_Name", datatype = "string64")
	private java.lang.String superviseResultName;

	/**
	 *查询开始时间
	 */
	@Column(id = "beginDate", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *查询结束时间
	 */
	@Column(id = "endDate", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *监察开始时间
	 */
	@Column(id = "beginJcDate", datatype = "date")
	private java.sql.Date beginJcDate;

	/**
	 *监察结束时间
	 */
	@Column(id = "endJcDate", datatype = "date")
	private java.sql.Date endJcDate;

	/**
	 *监察结果id
	 */
	@Column(id = "supervise_info_id", datatype = "string256")
	private java.lang.String superviseInfoId;

	/**
	 *递归机构id
	 */
	@Column(id = "temporgid", datatype = "string128")
	private java.lang.String temporgid;

	/**
	 *数据交换时间
	 */
	@Column(id = "etl_time", datatype = "date")
	private java.sql.Date etlTime;

	/**
	 *超期时间
	 */
	@Column(id = "outDays", datatype = "string256")
	private java.lang.String outDays;

	/**
	 * 设置
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceCode(java.lang.String instanceCode) {
		this.instanceCode = instanceCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getInstanceCode() {
		return instanceCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApproveCode() {
		return approveCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceName(java.lang.String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getInstanceName() {
		return instanceName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstancePy(java.lang.String instancePy) {
		this.instancePy = instancePy;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getInstancePy() {
		return instancePy;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptName(java.lang.String acceptName) {
		this.acceptName = acceptName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAcceptName() {
		return acceptName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptTime(java.sql.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setEndName(java.lang.String endName) {
		this.endName = endName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getEndName() {
		return endName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getEndTime() {
		return endTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setTimeUse(java.lang.String timeUse) {
		this.timeUse = timeUse;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getTimeUse() {
		return timeUse;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setApplyId(java.lang.String applyId) {
		this.applyId = applyId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApplyId() {
		return applyId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setApplyType(java.lang.String applyType) {
		this.applyType = applyType;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApplyType() {
		return applyType;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApplyName() {
		return applyName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProjectState(java.lang.String projectState) {
		this.projectState = projectState;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProjectState() {
		return projectState;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setQueueCode(java.lang.String queueCode) {
		this.queueCode = queueCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getQueueCode() {
		return queueCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getQueryCode() {
		return queryCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNotacceptReason(java.lang.String notacceptReason) {
		this.notacceptReason = notacceptReason;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getNotacceptReason() {
		return notacceptReason;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCorrectionTime(java.lang.String correctionTime) {
		this.correctionTime = correctionTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCorrectionTime() {
		return correctionTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceSource(java.lang.String instanceSource) {
		this.instanceSource = instanceSource;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getInstanceSource() {
		return instanceSource;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSubmitTime(java.lang.String submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSubmitTime() {
		return submitTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setPreReason(java.lang.String preReason) {
		this.preReason = preReason;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getPreReason() {
		return preReason;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptId(java.lang.String acceptId) {
		this.acceptId = acceptId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAcceptId() {
		return acceptId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setEndId(java.lang.String endId) {
		this.endId = endId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getEndId() {
		return endId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCurTache(java.lang.String curTache) {
		this.curTache = curTache;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCurTache() {
		return curTache;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsSendresult(java.lang.String isSendresult) {
		this.isSendresult = isSendresult;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsSendresult() {
		return isSendresult;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRzApplyId(java.lang.String rzApplyId) {
		this.rzApplyId = rzApplyId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRzApplyId() {
		return rzApplyId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProcessInstanceid(java.lang.String processInstanceid) {
		this.processInstanceid = processInstanceid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProcessInstanceid() {
		return processInstanceid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProcessDefinitionid(java.lang.String processDefinitionid) {
		this.processDefinitionid = processDefinitionid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProcessDefinitionid() {
		return processDefinitionid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseTypeCode(java.lang.String superviseTypeCode) {
		this.superviseTypeCode = superviseTypeCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseTypeCode() {
		return superviseTypeCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSpApproveName(java.lang.String spApproveName) {
		this.spApproveName = spApproveName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSpApproveName() {
		return spApproveName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSpApproveCode(java.lang.String spApproveCode) {
		this.spApproveCode = spApproveCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSpApproveCode() {
		return spApproveCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSpTransTypeCode(java.lang.String spTransTypeCode) {
		this.spTransTypeCode = spTransTypeCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSpTransTypeCode() {
		return spTransTypeCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSpApproveTypeCode(java.lang.String spApproveTypeCode) {
		this.spApproveTypeCode = spApproveTypeCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSpApproveTypeCode() {
		return spApproveTypeCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setTimeLimit(java.lang.String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsCharge(java.lang.String isCharge) {
		this.isCharge = isCharge;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsCharge() {
		return isCharge;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsAvailable(java.lang.String isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsAvailable() {
		return isAvailable;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setPromisesLimit(java.lang.String promisesLimit) {
		this.promisesLimit = promisesLimit;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getPromisesLimit() {
		return promisesLimit;
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

	/**
	 * 设置查询开始时间
	 */
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取查询开始时间
	 */
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置查询结束时间
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取查询结束时间
	 */
	public java.sql.Date getEndDate() {
		return endDate;
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
	 * 设置监察开始时间
	 */
	public void setBeginJcDate(java.sql.Date beginJcDate) {
		this.beginJcDate = beginJcDate;
	}

	/**
	 * 获取监察开始时间
	 */
	public java.sql.Date getBeginJcDate() {
		return beginJcDate;
	}

	/**
	 * 设置监察结束时间
	 */
	public void setEndJcDate(java.sql.Date endJcDate) {
		this.endJcDate = endJcDate;
	}

	/**
	 * 获取监察结束时间
	 */
	public java.sql.Date getEndJcDate() {
		return endJcDate;
	}

	/**
	 * 设置监察结果id
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取监察结果id
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置递归机构id
	 */
	public void setTemporgid(java.lang.String temporgid) {
		this.temporgid = temporgid;
	}

	/**
	 * 获取递归机构id
	 */
	public java.lang.String getTemporgid() {
		return temporgid;
	}

	/**
	 * 设置数据交换时间
	 */
	public void setEtlTime(java.sql.Date etlTime) {
		this.etlTime = etlTime;
	}

	/**
	 * 获取数据交换时间
	 */
	public java.sql.Date getEtlTime() {
		return etlTime;
	}

	/**
	 * 设置超期时间
	 */
	public void setOutDays(java.lang.String outDays) {
		this.outDays = outDays;
	}

	/**
	 * 获取超期时间
	 */
	public java.lang.String getOutDays() {
		return outDays;
	}
}
