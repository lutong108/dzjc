package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 人工发牌
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseHuman", table = "TA_JC_SUPERVISE_HUMAN", ds = "acceptdata", cache = false)
public class SuperviseHuman {
	/**
	 *人工发牌id
	 */
	@Column(id = "supervise_human_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String superviseHumanId;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	/**
	 *事项id
	 */
	@Column(id = "approve_id", datatype = "string128")
	private java.lang.String approveId;

	/**
	 *人工规则id
	 */
	@Column(id = "human_rule_id", datatype = "string128")
	private java.lang.String humanRuleId;

	/**
	 *发牌级别
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *描述
	 */
	@Column(id = "supervise_descript", datatype = "string512")
	private java.lang.String superviseDescript;

	/**
	 *依据
	 */
	@Column(id = "supervise_gist", datatype = "string512")
	private java.lang.String superviseGist;

	/**
	 *信息内容
	 */
	@Column(id = "warning_content", datatype = "string1024")
	private java.lang.String warningContent;

	/**
	 *发牌时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *机构名称
	 */
	@Column(id = "orgName", datatype = "string128")
	private java.lang.String orgName;

	/**
	 *事项名称
	 */
	@Column(id = "approveName", datatype = "string128")
	private java.lang.String approveName;

	/**
	 *规则名称
	 */
	@Column(id = "ruleName", datatype = "string128")
	private java.lang.String ruleName;

	/**
	 *机构查询
	 */
	@Column(id = "temporgid", datatype = "string128")
	private java.lang.String temporgid;

	/**
	 *发牌状态
	 */
	@Column(id = "state", datatype = "string128")
	private java.lang.String state;

	/**
	 *发牌开始日期
	 */
	@Column(id = "fpbeginDate", datatype = "date")
	private java.sql.Date fpbeginDate;

	/**
	 *发牌结束日期
	 */
	@Column(id = "fpendDate", datatype = "date")
	private java.sql.Date fpendDate;

	/**
	 * 设置人工发牌id
	 */
	public void setSuperviseHumanId(java.lang.String superviseHumanId) {
		this.superviseHumanId = superviseHumanId;
	}

	/**
	 * 获取人工发牌id
	 */
	public java.lang.String getSuperviseHumanId() {
		return superviseHumanId;
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
	 * 设置人工规则id
	 */
	public void setHumanRuleId(java.lang.String humanRuleId) {
		this.humanRuleId = humanRuleId;
	}

	/**
	 * 获取人工规则id
	 */
	public java.lang.String getHumanRuleId() {
		return humanRuleId;
	}

	/**
	 * 设置发牌级别
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取发牌级别
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置描述
	 */
	public void setSuperviseDescript(java.lang.String superviseDescript) {
		this.superviseDescript = superviseDescript;
	}

	/**
	 * 获取描述
	 */
	public java.lang.String getSuperviseDescript() {
		return superviseDescript;
	}

	/**
	 * 设置依据
	 */
	public void setSuperviseGist(java.lang.String superviseGist) {
		this.superviseGist = superviseGist;
	}

	/**
	 * 获取依据
	 */
	public java.lang.String getSuperviseGist() {
		return superviseGist;
	}

	/**
	 * 设置信息内容
	 */
	public void setWarningContent(java.lang.String warningContent) {
		this.warningContent = warningContent;
	}

	/**
	 * 获取信息内容
	 */
	public java.lang.String getWarningContent() {
		return warningContent;
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
	 * 设置规则名称
	 */
	public void setRuleName(java.lang.String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * 获取规则名称
	 */
	public java.lang.String getRuleName() {
		return ruleName;
	}

	/**
	 * 设置机构查询
	 */
	public void setTemporgid(java.lang.String temporgid) {
		this.temporgid = temporgid;
	}

	/**
	 * 获取机构查询
	 */
	public java.lang.String getTemporgid() {
		return temporgid;
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
	 * 设置发牌开始日期
	 */
	public void setFpbeginDate(java.sql.Date fpbeginDate) {
		this.fpbeginDate = fpbeginDate;
	}

	/**
	 * 获取发牌开始日期
	 */
	public java.sql.Date getFpbeginDate() {
		return fpbeginDate;
	}

	/**
	 * 设置发牌结束日期
	 */
	public void setFpendDate(java.sql.Date fpendDate) {
		this.fpendDate = fpendDate;
	}

	/**
	 * 获取发牌结束日期
	 */
	public java.sql.Date getFpendDate() {
		return fpendDate;
	}
}
