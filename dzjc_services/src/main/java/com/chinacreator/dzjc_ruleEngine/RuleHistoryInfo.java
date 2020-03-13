package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 规则历史
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.RuleHistoryInfo", table = "TA_JC_RULE_HISTORYINFO", ds = "acceptdata")
public class RuleHistoryInfo implements Serializable {
	private static final long serialVersionUID = 2635901383278592L;
	/**
	 *历史记录ID
	 */
	@Column(id = "his_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String hisId;

	/**
	 *规则ID
	 */
	@Column(id = "rule_id", datatype = "string64")
	private java.lang.String ruleId;

	/**
	 *规则名称
	 */
	@Column(id = "rule_name", datatype = "string128")
	private java.lang.String ruleName;

	/**
	 *规则描述
	 */
	@Column(id = "rule_memo", datatype = "string512")
	private java.lang.String ruleMemo;

	/**
	 *规则版本号
	 */
	@Column(id = "rule_version", datatype = "string32")
	private java.lang.String ruleVersion;

	/**
	 *是否启用
	 */
	@Column(id = "is_valid", datatype = "string64")
	private java.lang.String isValid;

	/**
	 *是否自动运行
	 */
	@Column(id = "is_auto_run", datatype = "string64")
	private java.lang.String isAutoRun;

	/**
	 *运行优先级
	 */
	@Column(id = "run_priority", datatype = "string10")
	private java.lang.String runPriority;

	/**
	 *1- 简单类型2-复杂类型
	 */
	@Column(id = "rule_type", datatype = "string64")
	private java.lang.String ruleType;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *即办件是否监察（默认Y-监察，N-不监察）
	 */
	@Column(id = "jbj_is_monitor", datatype = "string64")
	private java.lang.String jbjIsMonitor;

	/**
	 * 设置历史记录ID
	 */
	public void setHisId(java.lang.String hisId) {
		this.hisId = hisId;
	}

	/**
	 * 获取历史记录ID
	 */
	public java.lang.String getHisId() {
		return hisId;
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
	 * 设置规则描述
	 */
	public void setRuleMemo(java.lang.String ruleMemo) {
		this.ruleMemo = ruleMemo;
	}

	/**
	 * 获取规则描述
	 */
	public java.lang.String getRuleMemo() {
		return ruleMemo;
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
	 * 设置是否启用
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否启用
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置是否自动运行
	 */
	public void setIsAutoRun(java.lang.String isAutoRun) {
		this.isAutoRun = isAutoRun;
	}

	/**
	 * 获取是否自动运行
	 */
	public java.lang.String getIsAutoRun() {
		return isAutoRun;
	}

	/**
	 * 设置运行优先级
	 */
	public void setRunPriority(java.lang.String runPriority) {
		this.runPriority = runPriority;
	}

	/**
	 * 获取运行优先级
	 */
	public java.lang.String getRunPriority() {
		return runPriority;
	}

	/**
	 * 设置1- 简单类型2-复杂类型
	 */
	public void setRuleType(java.lang.String ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * 获取1- 简单类型2-复杂类型
	 */
	public java.lang.String getRuleType() {
		return ruleType;
	}

	/**
	 * 设置平台实例编号
	 */
	public void setCcFormInstanceid(java.lang.String ccFormInstanceid) {
		this.ccFormInstanceid = ccFormInstanceid;
	}

	/**
	 * 获取平台实例编号
	 */
	public java.lang.String getCcFormInstanceid() {
		return ccFormInstanceid;
	}

	/**
	 * 设置即办件是否监察（默认Y-监察，N-不监察）
	 */
	public void setJbjIsMonitor(java.lang.String jbjIsMonitor) {
		this.jbjIsMonitor = jbjIsMonitor;
	}

	/**
	 * 获取即办件是否监察（默认Y-监察，N-不监察）
	 */
	public java.lang.String getJbjIsMonitor() {
		return jbjIsMonitor;
	}
}
