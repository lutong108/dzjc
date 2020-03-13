package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 表达式和规则对应
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.RuleExpInfo", table = "TA_JC_RULE_EXPINFO", ds = "acceptdata")
public class RuleExpInfo implements Serializable {
	private static final long serialVersionUID = 2635907535142912L;
	/**
	 *表达式ID
	 */
	@Column(id = "exp_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String expId;

	/**
	 *规则ID
	 */
	@Column(id = "rule_id", datatype = "string64")
	private java.lang.String ruleId;

	/**
	 *规则版本号
	 */
	@Column(id = "rule_version", datatype = "string32")
	private java.lang.String ruleVersion;

	/**
	 *表达式序号，默认与表达式ID一致
	 */
	@Column(id = "exp_seq", datatype = "string20")
	private java.lang.String expSeq;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 * 设置表达式ID
	 */
	public void setExpId(java.lang.String expId) {
		this.expId = expId;
	}

	/**
	 * 获取表达式ID
	 */
	public java.lang.String getExpId() {
		return expId;
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
	 * 设置表达式序号，默认与表达式ID一致
	 */
	public void setExpSeq(java.lang.String expSeq) {
		this.expSeq = expSeq;
	}

	/**
	 * 获取表达式序号，默认与表达式ID一致
	 */
	public java.lang.String getExpSeq() {
		return expSeq;
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
}
