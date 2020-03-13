package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效用户表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.UserPerformance", table = "ta_jc_user_performance", ds = "acceptdata", cache = false)
public class UserPerformance {
	/**
	 *用户ID
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String id;

	/**
	 *用户名
	 */
	@Column(id = "name", datatype = "string64")
	private java.lang.String name;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	/**
	 *性别
	 */
	@Column(id = "sex", datatype = "string64")
	private java.lang.String sex;

	/**
	 *电话
	 */
	@Column(id = "phone", datatype = "string64")
	private java.lang.String phone;

	/**
	 *主键用户ID
	 */
	@Column(id = "user_id", datatype = "string128")
	private java.lang.String userId;

	/**
	 * 设置用户ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取用户ID
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置用户名
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取用户名
	 */
	public java.lang.String getName() {
		return name;
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
	 * 设置主键用户ID
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 获取主键用户ID
	 */
	public java.lang.String getUserId() {
		return userId;
	}
}
