package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 机构平台用户信息列表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.UserInfo", table = "V_JC_MSG_USER", ds = "acceptdata", cache = false)
public class UserInfo {
	/**
	 *id
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *姓名
	 */
	@Column(id = "name", datatype = "string1024")
	private java.lang.String name;

	/**
	 *电话
	 */
	@Column(id = "phone", datatype = "string64")
	private java.lang.String phone;

	/**
	 *性别
	 */
	@Column(id = "sex", datatype = "string64")
	private java.lang.String sex;

	/**
	 *状态
	 */
	@Column(id = "state", datatype = "string64")
	private java.lang.String state;

	/**
	 *用户名
	 */
	@Column(id = "user_name", datatype = "string2000")
	private java.lang.String userName;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string1024")
	private java.lang.String orgName;

	/**
	 *排序号
	 */
	@Column(id = "sn", datatype = "string128")
	private java.lang.String sn;

	/**
	 * 设置id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置姓名
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取姓名
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置电话
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	 * 获取电话
	 */
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * 设置性别
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	/**
	 * 获取性别
	 */
	public java.lang.String getSex() {
		return sex;
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
	 * 设置用户名
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * 获取用户名
	 */
	public java.lang.String getUserName() {
		return userName;
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
	 * 设置排序号
	 */
	public void setSn(java.lang.String sn) {
		this.sn = sn;
	}

	/**
	 * 获取排序号
	 */
	public java.lang.String getSn() {
		return sn;
	}
}
