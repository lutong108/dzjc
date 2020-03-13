package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 短信机构对应(雨花)
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.MessageOrgRef", table = "TA_JC_MESSAGE_ORGREF", ds = "acceptdata", cache = false)
public class MessageOrgRef {
	/**
	 *主键id
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String id;

	/**
	 *受理人机构id
	 */
	@Column(id = "accept_org_id", datatype = "string128")
	private java.lang.String acceptOrgId;

	/**
	 *街道管理员id
	 */
	@Column(id = "street_org_id", datatype = "string128")
	private java.lang.String streetOrgId;

	/**
	 *受理人机构名称
	 */
	@Column(id = "accept_org_name", datatype = "string128")
	private java.lang.String acceptOrgName;

	/**
	 *街道管理员名称
	 */
	@Column(id = "street_org_name", datatype = "string128")
	private java.lang.String streetOrgName;

	/**
	 * 设置主键id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置受理人机构id
	 */
	public void setAcceptOrgId(java.lang.String acceptOrgId) {
		this.acceptOrgId = acceptOrgId;
	}

	/**
	 * 获取受理人机构id
	 */
	public java.lang.String getAcceptOrgId() {
		return acceptOrgId;
	}

	/**
	 * 设置街道管理员id
	 */
	public void setStreetOrgId(java.lang.String streetOrgId) {
		this.streetOrgId = streetOrgId;
	}

	/**
	 * 获取街道管理员id
	 */
	public java.lang.String getStreetOrgId() {
		return streetOrgId;
	}

	/**
	 * 设置受理人机构名称
	 */
	public void setAcceptOrgName(java.lang.String acceptOrgName) {
		this.acceptOrgName = acceptOrgName;
	}

	/**
	 * 获取受理人机构名称
	 */
	public java.lang.String getAcceptOrgName() {
		return acceptOrgName;
	}

	/**
	 * 设置街道管理员名称
	 */
	public void setStreetOrgName(java.lang.String streetOrgName) {
		this.streetOrgName = streetOrgName;
	}

	/**
	 * 获取街道管理员名称
	 */
	public java.lang.String getStreetOrgName() {
		return streetOrgName;
	}
}
