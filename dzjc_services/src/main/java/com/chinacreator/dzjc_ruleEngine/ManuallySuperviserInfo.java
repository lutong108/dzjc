package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 手动发牌监察信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ManuallySuperviserInfo", table = "TA_JC_SUPERVISE_INFO1", ds = "acceptdata", cache = false)
public class ManuallySuperviserInfo {
	/**
	 *监察ID
	 */
	@Column(id = "supervise_info_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String superviseInfoId;

	/**
	 *监察结果类型
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *规则名称
	 */
	@Column(id = "rule_name", datatype = "string512")
	private java.lang.String ruleName;

	/**
	 *预警状态
	 */
	@Column(id = "state", datatype = "string20")
	private java.lang.String state;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *规则描述
	 */
	@Column(id = "supervise_descript", datatype = "string512")
	private java.lang.String superviseDescript;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 *发牌结果
	 */
	@Column(id = "supervise_result_name", datatype = "string128")
	private java.lang.String superviseResultName;

	/**
	 *预警状态名称
	 */
	@Column(id = "state_name", datatype = "string128")
	private java.lang.String stateName;

	/**
	 * 设置监察ID
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取监察ID
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置监察结果类型
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果类型
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
	 * 设置预警状态
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取预警状态
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
	 * 设置规则描述
	 */
	public void setSuperviseDescript(java.lang.String superviseDescript) {
		this.superviseDescript = superviseDescript;
	}

	/**
	 * 获取规则描述
	 */
	public java.lang.String getSuperviseDescript() {
		return superviseDescript;
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
	 * 设置发牌结果
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取发牌结果
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
	}

	/**
	 * 设置预警状态名称
	 */
	public void setStateName(java.lang.String stateName) {
		this.stateName = stateName;
	}

	/**
	 * 获取预警状态名称
	 */
	public java.lang.String getStateName() {
		return stateName;
	}
}
