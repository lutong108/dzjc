package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察级别
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseResult", table = "TA_JC_DIC_SUPERVISE_RESULT", ds = "acceptdata")
public class SuperviseResult implements Serializable {
	private static final long serialVersionUID = 2630520803295232L;
	/**
	 *主键ID
	 */
	@Column(id = "supervise_result_id", datatype = "string64")
	private java.lang.String superviseResultId;

	/**
	 *结果编号
	 */
	@Column(id = "supervise_result_code", type = ColumnType.uuid, datatype = "char2")
	private java.lang.String superviseResultCode;

	/**
	 *结果名称
	 */
	@Column(id = "supervise_result_name", datatype = "string32")
	private java.lang.String superviseResultName;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string256")
	private java.lang.String remark;

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
	 *监察级别
	 */
	@Column(id = "supervise_level", datatype = "tinyint")
	private java.lang.Integer superviseLevel;

	/**
	 * 设置主键ID
	 */
	public void setSuperviseResultId(java.lang.String superviseResultId) {
		this.superviseResultId = superviseResultId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getSuperviseResultId() {
		return superviseResultId;
	}

	/**
	 * 设置结果编号
	 */
	public void setSuperviseResultCode(java.lang.String superviseResultCode) {
		this.superviseResultCode = superviseResultCode;
	}

	/**
	 * 获取结果编号
	 */
	public java.lang.String getSuperviseResultCode() {
		return superviseResultCode;
	}

	/**
	 * 设置结果名称
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取结果名称
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
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
	 * 设置监察级别
	 */
	public void setSuperviseLevel(java.lang.Integer superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	/**
	 * 获取监察级别
	 */
	public java.lang.Integer getSuperviseLevel() {
		return superviseLevel;
	}
}
