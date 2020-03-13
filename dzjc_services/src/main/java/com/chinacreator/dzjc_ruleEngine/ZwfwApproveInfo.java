package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 政务服务事项信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ZwfwApproveInfo", table = "VA_TA_JC_APPROVE_STATISTICS", ds = "acceptdata", cache = false)
public class ZwfwApproveInfo {
	@Column(id = "approve_code", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String approveCode;

	@Column(id = "approve_name", datatype = "string1024")
	private java.lang.String approveName;

	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	@Column(id = "is_valid", datatype = "string5")
	private java.lang.String isValid;

	@Column(id = "type_code", datatype = "string5")
	private java.lang.String typeCode;

	@Column(id = "type_name", datatype = "string64")
	private java.lang.String typeName;

	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	@Column(id = "area_code", datatype = "string256")
	private java.lang.String areaCode;

	@Column(id = "approve_state", datatype = "string10")
	private java.lang.String approveState;

	/**
	 * 设置${field.desc}
	 */
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApproveCode() {
		return approveCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setTypeCode(java.lang.String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getTypeCode() {
		return typeCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getTypeName() {
		return typeName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgId() {
		return orgId;
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

	/**
	 * 设置${field.desc}
	 */
	public void setApproveState(java.lang.String approveState) {
		this.approveState = approveState;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getApproveState() {
		return approveState;
	}
}
