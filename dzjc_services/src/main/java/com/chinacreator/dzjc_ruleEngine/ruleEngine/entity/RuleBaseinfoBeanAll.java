package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 规则信息合集
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAll", table = "V_RULEBASEINFOBEANALL", ds = "acceptdata", cache = false)
public class RuleBaseinfoBeanAll {
	@Column(id = "area_code", datatype = "string32")
	private java.lang.String areaCode;

	@Column(id = "exp_id", datatype = "string64")
	private java.lang.String expId;

	@Column(id = "rule_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String ruleId;

	@Column(id = "rule_version", datatype = "string32")
	private java.lang.String ruleVersion;

	@Column(id = "issendmsg", datatype = "string5")
	private java.lang.String issendmsg;

	@Column(id = "business_id", datatype = "string512")
	private java.lang.String businessId;

	@Column(id = "rlt_value", datatype = "string64")
	private java.lang.String rltValue;

	@Column(id = "rule_name", datatype = "string128")
	private java.lang.String ruleName;

	@Column(id = "rule_memo", datatype = "string512")
	private java.lang.String ruleMemo;

	@Column(id = "is_valid", datatype = "string20")
	private java.lang.String isValid;

	@Column(id = "is_auto_run", datatype = "string20")
	private java.lang.String isAutoRun;

	@Column(id = "run_priority", datatype = "string5")
	private java.lang.String runPriority;

	@Column(id = "has_runed", datatype = "string5")
	private java.lang.String hasRuned;

	@Column(id = "rule_type", datatype = "string5")
	private java.lang.String ruleType;

	@Column(id = "is_public", datatype = "string5")
	private java.lang.String isPublic;

	@Column(id = "exp_seq", datatype = "string20")
	private java.lang.String expSeq;

	@Column(id = "explet_id", datatype = "string64")
	private java.lang.String expletId;

	@Column(id = "left_paren", datatype = "string5")
	private java.lang.String leftParen;

	@Column(id = "elem_id", datatype = "string64")
	private java.lang.String elemId;

	@Column(id = "compa_id", datatype = "string20")
	private java.lang.String compaId;

	@Column(id = "cfg_value", datatype = "string64")
	private java.lang.String cfgValue;

	@Column(id = "right_paren", datatype = "string5")
	private java.lang.String rightParen;

	@Column(id = "compa", datatype = "char6")
	private java.lang.String compa;

	@Column(id = "logic", datatype = "char6")
	private java.lang.String logic;

	@Column(id = "logic_id", datatype = "string20")
	private java.lang.String logicId;

	@Column(id = "supervise_def_id", datatype = "string64")
	private java.lang.String superviseDefId;

	@Column(id = "supervise_level_code", datatype = "string5")
	private java.lang.String superviseLevelCode;

	@Column(id = "supervise_type_code", datatype = "string10")
	private java.lang.String superviseTypeCode;

	@Column(id = "sendcard_type", datatype = "string5")
	private java.lang.String sendcardType;

	@Column(id = "msg_template_id", datatype = "string64")
	private java.lang.String msgTemplateId;

	@Column(id = "business_type", datatype = "string64")
	private java.lang.String businessType;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *受理时间
	 */
	@Column(id = "start_time", datatype = "date")
	private java.sql.Date startTime;

	/**
	 *办结时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *状态
	 */
	@Column(id = "state", datatype = "string64")
	private java.lang.String state;

	/**
	 *承诺时限
	 */
	@Column(id = "promise_limit", datatype = "double")
	private java.lang.Double promiseLimit;

	/**
	 *法定时限
	 */
	@Column(id = "process_limit", datatype = "double")
	private java.lang.Double processLimit;

	/**
	 *到期时间
	 */
	@Column(id = "expire_date", datatype = "date")
	private java.sql.Date expireDate;

	/**
	 *事项id
	 */
	@Column(id = "approve_id", datatype = "string256")
	private java.lang.String approveId;

	/**
	 *发牌时特别程序计算时间
	 */
	@Column(id = "tbcx_times", datatype = "string128")
	private java.lang.String tbcxTimes;

	/**
	 *本次发牌批次id，用于区分同一次发牌
	 */
	@Column(id = "batch_id", datatype = "string64")
	private java.lang.String batchId;

	/**
	 *发牌结果表id
	 */
	@Column(id = "supervise_info_id", datatype = "string64")
	private java.lang.String superviseInfoId;

	/**
	 *业务编码
	 */
	@Column(id = "business_code", datatype = "string128")
	private java.lang.String businessCode;

	/**
	 *业务名称
	 */
	@Column(id = "business_name", datatype = "string256")
	private java.lang.String businessName;

	/**
	 *短信模板内容
	 */
	@Column(id = "msg_template", datatype = "string512")
	private java.lang.String msgTemplate;

	/**
	 *街道机构id
	 */
	@Column(id = "street_org_id", datatype = "string512")
	private java.lang.String streetOrgId;

	/**
	 *受理地点
	 */
	@Column(id = "org_name", datatype = "string128")
	private java.lang.String orgName;

	/**
	 *申请者
	 */
	@Column(id = "apply_name", datatype = "string256")
	private java.lang.String applyName;

	/**
	 *环节名称
	 */
	@Column(id = "tache_name", datatype = "string128")
	private java.lang.String tacheName;

	/**
	 *办件id
	 */
	@Column(id = "instance_id", datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *流程唯一id，用于调接口
	 */
	@Column(id = "mark_id", datatype = "string64")
	private java.lang.String markId;

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
	public void setExpId(java.lang.String expId) {
		this.expId = expId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getExpId() {
		return expId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRuleId(java.lang.String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRuleId() {
		return ruleId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRuleVersion(java.lang.String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRuleVersion() {
		return ruleVersion;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIssendmsg(java.lang.String issendmsg) {
		this.issendmsg = issendmsg;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIssendmsg() {
		return issendmsg;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRltValue(java.lang.String rltValue) {
		this.rltValue = rltValue;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRltValue() {
		return rltValue;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRuleName(java.lang.String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRuleName() {
		return ruleName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRuleMemo(java.lang.String ruleMemo) {
		this.ruleMemo = ruleMemo;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRuleMemo() {
		return ruleMemo;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsAutoRun(java.lang.String isAutoRun) {
		this.isAutoRun = isAutoRun;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsAutoRun() {
		return isAutoRun;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRunPriority(java.lang.String runPriority) {
		this.runPriority = runPriority;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRunPriority() {
		return runPriority;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setHasRuned(java.lang.String hasRuned) {
		this.hasRuned = hasRuned;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getHasRuned() {
		return hasRuned;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRuleType(java.lang.String ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRuleType() {
		return ruleType;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setExpSeq(java.lang.String expSeq) {
		this.expSeq = expSeq;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getExpSeq() {
		return expSeq;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setExpletId(java.lang.String expletId) {
		this.expletId = expletId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getExpletId() {
		return expletId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setLeftParen(java.lang.String leftParen) {
		this.leftParen = leftParen;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getLeftParen() {
		return leftParen;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setElemId(java.lang.String elemId) {
		this.elemId = elemId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getElemId() {
		return elemId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCompaId(java.lang.String compaId) {
		this.compaId = compaId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCompaId() {
		return compaId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCfgValue(java.lang.String cfgValue) {
		this.cfgValue = cfgValue;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCfgValue() {
		return cfgValue;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRightParen(java.lang.String rightParen) {
		this.rightParen = rightParen;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRightParen() {
		return rightParen;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCompa(java.lang.String compa) {
		this.compa = compa;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCompa() {
		return compa;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setLogic(java.lang.String logic) {
		this.logic = logic;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getLogic() {
		return logic;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setLogicId(java.lang.String logicId) {
		this.logicId = logicId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getLogicId() {
		return logicId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseDefId(java.lang.String superviseDefId) {
		this.superviseDefId = superviseDefId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseDefId() {
		return superviseDefId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseLevelCode(java.lang.String superviseLevelCode) {
		this.superviseLevelCode = superviseLevelCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseLevelCode() {
		return superviseLevelCode;
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
	public void setSendcardType(java.lang.String sendcardType) {
		this.sendcardType = sendcardType;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSendcardType() {
		return sendcardType;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setMsgTemplateId(java.lang.String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getMsgTemplateId() {
		return msgTemplateId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBusinessType() {
		return businessType;
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
	 * 设置受理时间
	 */
	public void setStartTime(java.sql.Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取受理时间
	 */
	public java.sql.Date getStartTime() {
		return startTime;
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
	 * 设置承诺时限
	 */
	public void setPromiseLimit(java.lang.Double promiseLimit) {
		this.promiseLimit = promiseLimit;
	}

	/**
	 * 获取承诺时限
	 */
	public java.lang.Double getPromiseLimit() {
		return promiseLimit;
	}

	/**
	 * 设置法定时限
	 */
	public void setProcessLimit(java.lang.Double processLimit) {
		this.processLimit = processLimit;
	}

	/**
	 * 获取法定时限
	 */
	public java.lang.Double getProcessLimit() {
		return processLimit;
	}

	/**
	 * 设置到期时间
	 */
	public void setExpireDate(java.sql.Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * 获取到期时间
	 */
	public java.sql.Date getExpireDate() {
		return expireDate;
	}

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
	 * 设置本次发牌批次id，用于区分同一次发牌
	 */
	public void setBatchId(java.lang.String batchId) {
		this.batchId = batchId;
	}

	/**
	 * 获取本次发牌批次id，用于区分同一次发牌
	 */
	public java.lang.String getBatchId() {
		return batchId;
	}

	/**
	 * 设置发牌结果表id
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取发牌结果表id
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置业务编码
	 */
	public void setBusinessCode(java.lang.String businessCode) {
		this.businessCode = businessCode;
	}

	/**
	 * 获取业务编码
	 */
	public java.lang.String getBusinessCode() {
		return businessCode;
	}

	/**
	 * 设置业务名称
	 */
	public void setBusinessName(java.lang.String businessName) {
		this.businessName = businessName;
	}

	/**
	 * 获取业务名称
	 */
	public java.lang.String getBusinessName() {
		return businessName;
	}

	/**
	 * 设置短信模板内容
	 */
	public void setMsgTemplate(java.lang.String msgTemplate) {
		this.msgTemplate = msgTemplate;
	}

	/**
	 * 获取短信模板内容
	 */
	public java.lang.String getMsgTemplate() {
		return msgTemplate;
	}

	/**
	 * 设置街道机构id
	 */
	public void setStreetOrgId(java.lang.String streetOrgId) {
		this.streetOrgId = streetOrgId;
	}

	/**
	 * 获取街道机构id
	 */
	public java.lang.String getStreetOrgId() {
		return streetOrgId;
	}

	/**
	 * 设置受理地点
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取受理地点
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置申请者
	 */
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	/**
	 * 获取申请者
	 */
	public java.lang.String getApplyName() {
		return applyName;
	}

	/**
	 * 设置环节名称
	 */
	public void setTacheName(java.lang.String tacheName) {
		this.tacheName = tacheName;
	}

	/**
	 * 获取环节名称
	 */
	public java.lang.String getTacheName() {
		return tacheName;
	}

	/**
	 * 设置办件id
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取办件id
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置流程唯一id，用于调接口
	 */
	public void setMarkId(java.lang.String markId) {
		this.markId = markId;
	}

	/**
	 * 获取流程唯一id，用于调接口
	 */
	public java.lang.String getMarkId() {
		return markId;
	}
}
