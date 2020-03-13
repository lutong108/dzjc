package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 业务集合
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum", table = "VA_TA_JC_ALLBUSSINESS_INFOSUM", ds = "acceptdata")
public class JcAllbussinesSum implements Serializable {
	private static final long serialVersionUID = 2698391821434880L;
	/**
	 *业务类别
	 */
	@Column(id = "bussiness_type", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String bussinessType;

	/**
	 *业务ID
	 */
	@Column(id = "bussiness_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String bussinessId;

	/**
	 *业务名称
	 */
	@Column(id = "bussiness_name", datatype = "string512")
	private java.lang.String bussinessName;

	/**
	 *业务时间
	 */
	@Column(id = "bussiness_time", datatype = "date")
	private java.sql.Date bussinessTime;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 * 设置业务类别
	 */
	public void setBussinessType(java.lang.String bussinessType) {
		this.bussinessType = bussinessType;
	}

	/**
	 * 获取业务类别
	 */
	public java.lang.String getBussinessType() {
		return bussinessType;
	}

	/**
	 * 设置业务ID
	 */
	public void setBussinessId(java.lang.String bussinessId) {
		this.bussinessId = bussinessId;
	}

	/**
	 * 获取业务ID
	 */
	public java.lang.String getBussinessId() {
		return bussinessId;
	}

	/**
	 * 设置业务名称
	 */
	public void setBussinessName(java.lang.String bussinessName) {
		this.bussinessName = bussinessName;
	}

	/**
	 * 获取业务名称
	 */
	public java.lang.String getBussinessName() {
		return bussinessName;
	}

	/**
	 * 设置业务时间
	 */
	public void setBussinessTime(java.sql.Date bussinessTime) {
		this.bussinessTime = bussinessTime;
	}

	/**
	 * 获取业务时间
	 */
	public java.sql.Date getBussinessTime() {
		return bussinessTime;
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
}
