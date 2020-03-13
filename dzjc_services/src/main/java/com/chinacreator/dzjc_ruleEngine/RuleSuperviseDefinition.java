package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察定义与表达式定义
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.RuleSuperviseDefinition", table = "TA_JC_RULE_SUPERVISE_DEFINITION", ds = "acceptdata")
public class RuleSuperviseDefinition implements Serializable {
	private static final long serialVersionUID = 2636077791428608L;
	/**
	 *监察定义ID
	 */
	@Column(id = "supervise_def_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String superviseDefId;

	/**
	 *表达式ID
	 */
	@Column(id = "exp_id", datatype = "string64")
	private java.lang.String expId;

	/**
	 *监察级别代码
	 */
	@Column(id = "supervise_level_code", datatype = "string64")
	private java.lang.String superviseLevelCode;

	/**
	 *监察类型代码
	 */
	@Column(id = "supervise_type_code", datatype = "string64")
	private java.lang.String superviseTypeCode;

	/**
	 *发牌方式
	 */
	@Column(id = "sendcard_type", datatype = "string64")
	private java.lang.String sendcardType;

	/**
	 *消息模板ID
	 */
	@Column(id = "msg_template_id", datatype = "string64")
	private java.lang.String msgTemplateId;

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
	 * 设置监察定义ID
	 */
	public void setSuperviseDefId(java.lang.String superviseDefId) {
		this.superviseDefId = superviseDefId;
	}

	/**
	 * 获取监察定义ID
	 */
	public java.lang.String getSuperviseDefId() {
		return superviseDefId;
	}

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
	 * 设置监察级别代码
	 */
	public void setSuperviseLevelCode(java.lang.String superviseLevelCode) {
		this.superviseLevelCode = superviseLevelCode;
	}

	/**
	 * 获取监察级别代码
	 */
	public java.lang.String getSuperviseLevelCode() {
		return superviseLevelCode;
	}

	/**
	 * 设置监察类型代码
	 */
	public void setSuperviseTypeCode(java.lang.String superviseTypeCode) {
		this.superviseTypeCode = superviseTypeCode;
	}

	/**
	 * 获取监察类型代码
	 */
	public java.lang.String getSuperviseTypeCode() {
		return superviseTypeCode;
	}

	/**
	 * 设置发牌方式
	 */
	public void setSendcardType(java.lang.String sendcardType) {
		this.sendcardType = sendcardType;
	}

	/**
	 * 获取发牌方式
	 */
	public java.lang.String getSendcardType() {
		return sendcardType;
	}

	/**
	 * 设置消息模板ID
	 */
	public void setMsgTemplateId(java.lang.String msgTemplateId) {
		this.msgTemplateId = msgTemplateId;
	}

	/**
	 * 获取消息模板ID
	 */
	public java.lang.String getMsgTemplateId() {
		return msgTemplateId;
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
