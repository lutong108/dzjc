package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 初始化配置bean
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBean", table = "TA_JC_RULE_ENGINECFG", ds = "acceptdata")
public class EnginecfgBean implements Serializable {
	private static final long serialVersionUID = 2631641014435840L;
	@Column(id = "cfg_id", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String cfgId;

	@Column(id = "cfg_type", datatype = "string64")
	private java.lang.String cfgType;

	@Column(id = "cfg_value", datatype = "string256")
	private java.lang.String cfgValue;

	@Column(id = "cfg_remark", datatype = "string256")
	private java.lang.String cfgRemark;

	@Column(id = "cfg_order", datatype = "string20")
	private java.lang.String cfgOrder;

	/**
	 *
	 */
	@Column(id = "is_valid", datatype = "string5")
	private java.lang.String isValid;

	/**
	 *是否使用时间限制，Y表示是
	 */
	@Column(id = "is_usetimelimit", datatype = "string5")
	private java.lang.String isUsetimelimit;

	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 * 设置${field.desc}
	 */
	public void setCfgId(java.lang.String cfgId) {
		this.cfgId = cfgId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCfgId() {
		return cfgId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCfgType(java.lang.String cfgType) {
		this.cfgType = cfgType;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCfgType() {
		return cfgType;
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
	public void setCfgRemark(java.lang.String cfgRemark) {
		this.cfgRemark = cfgRemark;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCfgRemark() {
		return cfgRemark;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCfgOrder(java.lang.String cfgOrder) {
		this.cfgOrder = cfgOrder;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCfgOrder() {
		return cfgOrder;
	}

	/**
	 * 设置
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置是否使用时间限制，Y表示是
	 */
	public void setIsUsetimelimit(java.lang.String isUsetimelimit) {
		this.isUsetimelimit = isUsetimelimit;
	}

	/**
	 * 获取是否使用时间限制，Y表示是
	 */
	public java.lang.String getIsUsetimelimit() {
		return isUsetimelimit;
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
}
