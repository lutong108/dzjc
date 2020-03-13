package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 环节监察详细信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.Superviseinfo_Message", table = "TA_JC_HUANJIE_SUPERVISE_INFO", ds = "acceptdata", cache = false)
public class Superviseinfo_Message {
	/**
	 *主键ID，通过序列SEQ_SUPERVISE_INFO取值，从其他系统交换的数据保持原值，形式为行政区划代码+序列值
	 */
	@Column(id = "supervise_info_id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String superviseInfoId;

	/**
	 *业务ID
	 */
	@Column(id = "business_id", datatype = "string128")
	private java.lang.String businessId;

	/**
	 *接收单位名称
	 */
	@Column(id = "receive_org_name", datatype = "string128")
	private java.lang.String receiveOrgName;

	/**
	 *监察类型代码
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *监察结果
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *监察描述
	 */
	@Column(id = "supervise_descript", datatype = "string512")
	private java.lang.String superviseDescript;

	/**
	 *监察依据
	 */
	@Column(id = "supervise_gist", datatype = "string512")
	private java.lang.String superviseGist;

	/**
	 *督办信息内容
	 */
	@Column(id = "warning_content", datatype = "string1024")
	private java.lang.String warningContent;

	/**
	 *环节id
	 */
	@Column(id = "opinion_id", datatype = "string128")
	private java.lang.String opinionId;

	/**
	 *规则ID
	 */
	@Column(id = "rule_id", datatype = "string64")
	private java.lang.String ruleId;

	/**
	 *反馈终止时间
	 */
	@Column(id = "feedback_endtime", datatype = "date")
	private java.sql.Date feedbackEndtime;

	/**
	 *预警状态(已发Y,待发D,撤销C,取消Q，督办B)
	 */
	@Column(id = "state", datatype = "string20")
	private java.lang.String state;

	/**
	 *最后更新时间
	 */
	@Column(id = "last_updatetime", datatype = "date")
	private java.sql.Date lastUpdatetime;

	/**
	 *规则版本号
	 */
	@Column(id = "rule_version", datatype = "string32")
	private java.lang.String ruleVersion;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *撤销原因
	 */
	@Column(id = "revocation_reason", datatype = "string2000")
	private java.lang.String revocationReason;

	/**
	 *开始时间
	 */
	@Column(id = "start_time", datatype = "date")
	private java.sql.Date startTime;

	/**
	 *结束时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *发牌时的环节状态(1:已办；2：待办)
	 */
	@Column(id = "status", datatype = "string64")
	private java.lang.String status;

	/**
	 *承诺时限
	 */
	@Column(id = "promise_limit", datatype = "bigdouble")
	private java.lang.Double promiseLimit;

	@Column(id = "out_days", datatype = "string64")
	private java.lang.String outDays;

	/**
	 *本次发牌批次id，用于区分同一次发牌
	 */
	@Column(id = "batch_id", datatype = "string64")
	private java.lang.String batchId;

	/**
	 *发牌时特别程序计算时间
	 */
	@Column(id = "tbcx_times", datatype = "string128")
	private java.lang.String tbcxTimes;

	/**
	 * 设置主键ID，通过序列SEQ_SUPERVISE_INFO取值，从其他系统交换的数据保持原值，形式为行政区划代码+序列值
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取主键ID，通过序列SEQ_SUPERVISE_INFO取值，从其他系统交换的数据保持原值，形式为行政区划代码+序列值
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
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

	/**
	 * 设置接收单位名称
	 */
	public void setReceiveOrgName(java.lang.String receiveOrgName) {
		this.receiveOrgName = receiveOrgName;
	}

	/**
	 * 获取接收单位名称
	 */
	public java.lang.String getReceiveOrgName() {
		return receiveOrgName;
	}

	/**
	 * 设置监察类型代码
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取监察类型代码
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
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
	 * 设置监察描述
	 */
	public void setSuperviseDescript(java.lang.String superviseDescript) {
		this.superviseDescript = superviseDescript;
	}

	/**
	 * 获取监察描述
	 */
	public java.lang.String getSuperviseDescript() {
		return superviseDescript;
	}

	/**
	 * 设置监察依据
	 */
	public void setSuperviseGist(java.lang.String superviseGist) {
		this.superviseGist = superviseGist;
	}

	/**
	 * 获取监察依据
	 */
	public java.lang.String getSuperviseGist() {
		return superviseGist;
	}

	/**
	 * 设置督办信息内容
	 */
	public void setWarningContent(java.lang.String warningContent) {
		this.warningContent = warningContent;
	}

	/**
	 * 获取督办信息内容
	 */
	public java.lang.String getWarningContent() {
		return warningContent;
	}

	/**
	 * 设置环节id
	 */
	public void setOpinionId(java.lang.String opinionId) {
		this.opinionId = opinionId;
	}

	/**
	 * 获取环节id
	 */
	public java.lang.String getOpinionId() {
		return opinionId;
	}

	/**
	 * 设置规则ID
	 */
	public void setRuleId(java.lang.String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 获取规则ID
	 */
	public java.lang.String getRuleId() {
		return ruleId;
	}

	/**
	 * 设置反馈终止时间
	 */
	public void setFeedbackEndtime(java.sql.Date feedbackEndtime) {
		this.feedbackEndtime = feedbackEndtime;
	}

	/**
	 * 获取反馈终止时间
	 */
	public java.sql.Date getFeedbackEndtime() {
		return feedbackEndtime;
	}

	/**
	 * 设置预警状态(已发Y,待发D,撤销C,取消Q，督办B)
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取预警状态(已发Y,待发D,撤销C,取消Q，督办B)
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * 设置最后更新时间
	 */
	public void setLastUpdatetime(java.sql.Date lastUpdatetime) {
		this.lastUpdatetime = lastUpdatetime;
	}

	/**
	 * 获取最后更新时间
	 */
	public java.sql.Date getLastUpdatetime() {
		return lastUpdatetime;
	}

	/**
	 * 设置规则版本号
	 */
	public void setRuleVersion(java.lang.String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}

	/**
	 * 获取规则版本号
	 */
	public java.lang.String getRuleVersion() {
		return ruleVersion;
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
	 * 设置撤销原因
	 */
	public void setRevocationReason(java.lang.String revocationReason) {
		this.revocationReason = revocationReason;
	}

	/**
	 * 获取撤销原因
	 */
	public java.lang.String getRevocationReason() {
		return revocationReason;
	}

	/**
	 * 设置开始时间
	 */
	public void setStartTime(java.sql.Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取开始时间
	 */
	public java.sql.Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置结束时间
	 */
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取结束时间
	 */
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置发牌时的环节状态(1:已办；2：待办)
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 获取发牌时的环节状态(1:已办；2：待办)
	 */
	public java.lang.String getStatus() {
		return status;
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
	 * 设置${field.desc}
	 */
	public void setOutDays(java.lang.String outDays) {
		this.outDays = outDays;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOutDays() {
		return outDays;
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
}
