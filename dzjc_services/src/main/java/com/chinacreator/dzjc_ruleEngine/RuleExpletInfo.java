package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 表达式信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.RuleExpletInfo", table = "TA_JC_RULE_EXPLETINFO", ds = "acceptdata")
public class RuleExpletInfo implements Serializable {
	private static final long serialVersionUID = 2635905744273408L;
	/**
	 *表达式片段ID
	 */
	@Column(id = "explet_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String expletId;

	/**
	 *表达式ID
	 */
	@Column(id = "exp_id", datatype = "string64")
	private java.lang.String expId;

	/**
	 *左括号；1-有，0-没有
	 */
	@Column(id = "left_paren", datatype = "string64")
	private java.lang.String leftParen;

	/**
	 *监察要素ID；引用监察要素表
	 */
	@Column(id = "elem_id", datatype = "string64")
	private java.lang.String elemId;

	/**
	 *比较运算符ID；参考比较运算符表
	 */
	@Column(id = "compa_id", datatype = "string20")
	private java.lang.String compaId;

	/**
	 *条件值
	 */
	@Column(id = "cfg_value", datatype = "string64")
	private java.lang.String cfgValue;

	/**
	 *右括号；1-有，0-没有
	 */
	@Column(id = "right_paren", datatype = "string64")
	private java.lang.String rightParen;

	/**
	 *逻辑运算符ID；参考逻辑运算符表
	 */
	@Column(id = "logic_id", datatype = "string20")
	private java.lang.String logicId;

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
	 * 设置表达式片段ID
	 */
	public void setExpletId(java.lang.String expletId) {
		this.expletId = expletId;
	}

	/**
	 * 获取表达式片段ID
	 */
	public java.lang.String getExpletId() {
		return expletId;
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
	 * 设置左括号；1-有，0-没有
	 */
	public void setLeftParen(java.lang.String leftParen) {
		this.leftParen = leftParen;
	}

	/**
	 * 获取左括号；1-有，0-没有
	 */
	public java.lang.String getLeftParen() {
		return leftParen;
	}

	/**
	 * 设置监察要素ID；引用监察要素表
	 */
	public void setElemId(java.lang.String elemId) {
		this.elemId = elemId;
	}

	/**
	 * 获取监察要素ID；引用监察要素表
	 */
	public java.lang.String getElemId() {
		return elemId;
	}

	/**
	 * 设置比较运算符ID；参考比较运算符表
	 */
	public void setCompaId(java.lang.String compaId) {
		this.compaId = compaId;
	}

	/**
	 * 获取比较运算符ID；参考比较运算符表
	 */
	public java.lang.String getCompaId() {
		return compaId;
	}

	/**
	 * 设置条件值
	 */
	public void setCfgValue(java.lang.String cfgValue) {
		this.cfgValue = cfgValue;
	}

	/**
	 * 获取条件值
	 */
	public java.lang.String getCfgValue() {
		return cfgValue;
	}

	/**
	 * 设置右括号；1-有，0-没有
	 */
	public void setRightParen(java.lang.String rightParen) {
		this.rightParen = rightParen;
	}

	/**
	 * 获取右括号；1-有，0-没有
	 */
	public java.lang.String getRightParen() {
		return rightParen;
	}

	/**
	 * 设置逻辑运算符ID；参考逻辑运算符表
	 */
	public void setLogicId(java.lang.String logicId) {
		this.logicId = logicId;
	}

	/**
	 * 获取逻辑运算符ID；参考逻辑运算符表
	 */
	public java.lang.String getLogicId() {
		return logicId;
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
