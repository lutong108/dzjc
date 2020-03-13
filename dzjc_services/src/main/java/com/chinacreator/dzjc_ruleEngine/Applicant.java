package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 办件申请者信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.Applicant", table = "TA_SP_APPLICANT", ds = "acceptdata", cache = false)
public class Applicant {
	/**
	 *申请人ID
	 */
	@Column(id = "applicant_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String applicantId;

	/**
	 *证件类型(取数据字典)
	 */
	@Column(id = "certificate_type", datatype = "string20")
	private java.lang.String certificateType;

	/**
	 *姓名
	 */
	@Column(id = "name", datatype = "string256")
	private java.lang.String name;

	/**
	 *性别(取数据字典)
	 */
	@Column(id = "sex", datatype = "string5")
	private java.lang.String sex;

	/**
	 *证件号码
	 */
	@Column(id = "certificate_num", datatype = "string64")
	private java.lang.String certificateNum;

	/**
	 *手机号码
	 */
	@Column(id = "phone", datatype = "string20")
	private java.lang.String phone;

	/**
	 *邮箱
	 */
	@Column(id = "email", datatype = "string256")
	private java.lang.String email;

	/**
	 *户籍地址
	 */
	@Column(id = "addr", datatype = "string256")
	private java.lang.String addr;

	/**
	 *居住地址
	 */
	@Column(id = "residence", datatype = "string256")
	private java.lang.String residence;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	/**
	 *是否有效
	 */
	@Column(id = "is_valid", datatype = "string5")
	private java.lang.String isValid;

	/**
	 *是否已交换(N/Y,默认:N)
	 */
	@Column(id = "is_exchange", datatype = "string5")
	private java.lang.String isExchange;

	/**
	 *是否已交换标志2(N/Y,默认:N)
	 */
	@Column(id = "mod_flag", datatype = "string5")
	private java.lang.String modFlag;

	/**
	 * 设置申请人ID
	 */
	public void setApplicantId(java.lang.String applicantId) {
		this.applicantId = applicantId;
	}

	/**
	 * 获取申请人ID
	 */
	public java.lang.String getApplicantId() {
		return applicantId;
	}

	/**
	 * 设置证件类型(取数据字典)
	 */
	public void setCertificateType(java.lang.String certificateType) {
		this.certificateType = certificateType;
	}

	/**
	 * 获取证件类型(取数据字典)
	 */
	public java.lang.String getCertificateType() {
		return certificateType;
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
	 * 设置性别(取数据字典)
	 */
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}

	/**
	 * 获取性别(取数据字典)
	 */
	public java.lang.String getSex() {
		return sex;
	}

	/**
	 * 设置证件号码
	 */
	public void setCertificateNum(java.lang.String certificateNum) {
		this.certificateNum = certificateNum;
	}

	/**
	 * 获取证件号码
	 */
	public java.lang.String getCertificateNum() {
		return certificateNum;
	}

	/**
	 * 设置手机号码
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	 * 获取手机号码
	 */
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * 设置邮箱
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	/**
	 * 获取邮箱
	 */
	public java.lang.String getEmail() {
		return email;
	}

	/**
	 * 设置户籍地址
	 */
	public void setAddr(java.lang.String addr) {
		this.addr = addr;
	}

	/**
	 * 获取户籍地址
	 */
	public java.lang.String getAddr() {
		return addr;
	}

	/**
	 * 设置居住地址
	 */
	public void setResidence(java.lang.String residence) {
		this.residence = residence;
	}

	/**
	 * 获取居住地址
	 */
	public java.lang.String getResidence() {
		return residence;
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
	 * 设置是否有效
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置是否已交换(N/Y,默认:N)
	 */
	public void setIsExchange(java.lang.String isExchange) {
		this.isExchange = isExchange;
	}

	/**
	 * 获取是否已交换(N/Y,默认:N)
	 */
	public java.lang.String getIsExchange() {
		return isExchange;
	}

	/**
	 * 设置是否已交换标志2(N/Y,默认:N)
	 */
	public void setModFlag(java.lang.String modFlag) {
		this.modFlag = modFlag;
	}

	/**
	 * 获取是否已交换标志2(N/Y,默认:N)
	 */
	public java.lang.String getModFlag() {
		return modFlag;
	}
}
