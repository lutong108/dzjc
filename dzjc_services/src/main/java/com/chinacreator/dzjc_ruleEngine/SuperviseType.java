package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察类型
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseType", table = "TA_JC_DIC_SUPERVISE_TYPE", ds = "acceptdata")
public class SuperviseType implements Serializable {
	private static final long serialVersionUID = 2630502803783680L;
	/**
	 *ID
	 */
	@Column(id = "supervise_type_id", datatype = "string64")
	private java.lang.String superviseTypeId;

	/**
	 *类型编号
	 */
	@Column(id = "supervise_type_code", type = ColumnType.uuid, datatype = "char6")
	private java.lang.String superviseTypeCode;

	/**
	 *类型名称
	 */
	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	/**
	 *父类型
	 */
	@Column(id = "parent_type_code", datatype = "char6")
	private java.lang.String parentTypeCode;

	/**
	 *状态
	 */
	@Column(id = "state", datatype = "boolean")
	private java.lang.Boolean state;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string1024")
	private java.lang.String remark;

	/**
	 * 设置ID
	 */
	public void setSuperviseTypeId(java.lang.String superviseTypeId) {
		this.superviseTypeId = superviseTypeId;
	}

	/**
	 * 获取ID
	 */
	public java.lang.String getSuperviseTypeId() {
		return superviseTypeId;
	}

	/**
	 * 设置类型编号
	 */
	public void setSuperviseTypeCode(java.lang.String superviseTypeCode) {
		this.superviseTypeCode = superviseTypeCode;
	}

	/**
	 * 获取类型编号
	 */
	public java.lang.String getSuperviseTypeCode() {
		return superviseTypeCode;
	}

	/**
	 * 设置类型名称
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取类型名称
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}

	/**
	 * 设置父类型
	 */
	public void setParentTypeCode(java.lang.String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}

	/**
	 * 获取父类型
	 */
	public java.lang.String getParentTypeCode() {
		return parentTypeCode;
	}

	/**
	 * 设置状态
	 */
	public void setState(java.lang.Boolean state) {
		this.state = state;
	}

	/**
	 * 获取状态
	 */
	public java.lang.Boolean isState() {
		return state;
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
	 * 设置备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getRemark() {
		return remark;
	}
}
