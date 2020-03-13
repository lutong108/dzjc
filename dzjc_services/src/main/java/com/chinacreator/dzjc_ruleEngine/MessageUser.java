package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 预警通知人员
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.MessageUser", table = "TA_JC_MESSAGE_USER", ds = "acceptdata", cache = false)
public class MessageUser {
	/**
	 *通过序列SEQ_MESSAGE_USER取值
	 */
	@Column(id = "message_user_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String messageUserId;

	/**
	 *事项ID
	 */
	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	/**
	 *预警级别；黄牌包含预警的用户，红牌包含黄牌和预警的用户
	 */
	@Column(id = "warn_level", datatype = "char2")
	private java.lang.String warnLevel;

	/**
	 *姓名
	 */
	@Column(id = "user_name", datatype = "string32")
	private java.lang.String userName;

	/**
	 *手机号码
	 */
	@Column(id = "user_mobile", datatype = "mediumdouble")
	private java.lang.Double userMobile;

	/**
	 *状态；1：有效，0，无效
	 */
	@Column(id = "state", datatype = "string64")
	private java.lang.String state;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string256")
	private java.lang.String remark;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *用户ID
	 */
	@Column(id = "user_id", datatype = "string64")
	private java.lang.String userId;

	/**
	 *预警结果名称
	 */
	@Column(id = "supervise_result_name", datatype = "string64")
	private java.lang.String superviseResultName;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string512")
	private java.lang.String orgId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string512")
	private java.lang.String orgName;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string1536")
	private java.lang.String approveName;

	/**
	 *事项编码
	 */
	@Column(id = "approve_code", datatype = "string512")
	private java.lang.String approveCode;

	/**
	 * 设置通过序列SEQ_MESSAGE_USER取值
	 */
	public void setMessageUserId(java.lang.String messageUserId) {
		this.messageUserId = messageUserId;
	}

	/**
	 * 获取通过序列SEQ_MESSAGE_USER取值
	 */
	public java.lang.String getMessageUserId() {
		return messageUserId;
	}

	/**
	 * 设置事项ID
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取事项ID
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置预警级别；黄牌包含预警的用户，红牌包含黄牌和预警的用户
	 */
	public void setWarnLevel(java.lang.String warnLevel) {
		this.warnLevel = warnLevel;
	}

	/**
	 * 获取预警级别；黄牌包含预警的用户，红牌包含黄牌和预警的用户
	 */
	public java.lang.String getWarnLevel() {
		return warnLevel;
	}

	/**
	 * 设置姓名
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * 获取姓名
	 */
	public java.lang.String getUserName() {
		return userName;
	}

	/**
	 * 设置手机号码
	 */
	public void setUserMobile(java.lang.Double userMobile) {
		this.userMobile = userMobile;
	}

	/**
	 * 获取手机号码
	 */
	public java.lang.Double getUserMobile() {
		return userMobile;
	}

	/**
	 * 设置状态；1：有效，0，无效
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取状态；1：有效，0，无效
	 */
	public java.lang.String getState() {
		return state;
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
	 * 设置用户ID
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户ID
	 */
	public java.lang.String getUserId() {
		return userId;
	}

	/**
	 * 设置预警结果名称
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取预警结果名称
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
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
	 * 设置事项名称
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取事项名称
	 */
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	 * 设置事项编码
	 */
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 获取事项编码
	 */
	public java.lang.String getApproveCode() {
		return approveCode;
	}
}
