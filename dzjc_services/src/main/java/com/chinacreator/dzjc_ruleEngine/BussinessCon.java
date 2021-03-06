package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 咨询信息合成事项
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.BussinessCon", table = "TA_SP_CONSULT", ds = "acceptdata", cache = false)
public class BussinessCon {
	/**
	 *咨询ID
	 */
	@Column(id = "consult_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String consultId;

	/**
	 *咨询标题
	 */
	@Column(id = "consult_title", datatype = "string256")
	private java.lang.String consultTitle;

	/**
	 *咨询单位ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *咨询单位名称
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 *申请者ID
	 */
	@Column(id = "apply_id", datatype = "string64")
	private java.lang.String applyId;

	/**
	 *申请者类型(1个人；2企业）
	 */
	@Column(id = "apply_type", datatype = "string5")
	private java.lang.String applyType;

	/**
	 *申请者名称
	 */
	@Column(id = "apply_name", datatype = "string256")
	private java.lang.String applyName;

	/**
	 *事项ID
	 */
	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	/**
	 *咨询时间
	 */
	@Column(id = "consult_time", datatype = "date")
	private java.sql.Date consultTime;

	/**
	 *咨询内容
	 */
	@Column(id = "consult_content", datatype = "string2000")
	private java.lang.String consultContent;

	/**
	 *是否公开(默认公开，Y：公开；N：不公开)
	 */
	@Column(id = "is_open", datatype = "string5")
	private java.lang.String isOpen;

	/**
	 *回复单位名称
	 */
	@Column(id = "reply_org", datatype = "string256")
	private java.lang.String replyOrg;

	/**
	 *回复时间
	 */
	@Column(id = "reply_time", datatype = "date")
	private java.sql.Date replyTime;

	/**
	 *回复内容
	 */
	@Column(id = "reply_content", datatype = "string2000")
	private java.lang.String replyContent;

	/**
	 *信息来源(1,实体大厅；2，网上大厅；3，一体机；4，微信；5，APP；）
	 */
	@Column(id = "consult_source", datatype = "string5")
	private java.lang.String consultSource;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *回复人
	 */
	@Column(id = "reply_name", datatype = "string64")
	private java.lang.String replyName;

	/**
	 *申请人联系电话
	 */
	@Column(id = "apply_phone", datatype = "string64")
	private java.lang.String applyPhone;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string512")
	private java.lang.String approveName;

	/**
	 *咨询时限
	 */
	@Column(id = "consult_limit", datatype = "bigdouble")
	private java.lang.Double consultLimit;

	/**
	 *到期时间
	 */
	@Column(id = "expire_time", datatype = "date")
	private java.sql.Date expireTime;

	/**
	 *法定时限
	 */
	@Column(id = "time_limit", datatype = "string64")
	private java.lang.String timeLimit;

	/**
	 *承诺时限
	 */
	@Column(id = "promises_limit", datatype = "string64")
	private java.lang.String promisesLimit;

	/**
	 *申请者类型名称
	 */
	@Column(id = "apply_type_name", datatype = "string64")
	private java.lang.String applyTypeName;

	/**
	 *是否公开名称
	 */
	@Column(id = "is_open_name", datatype = "string64")
	private java.lang.String isOpenName;

	/**
	 *信息来源名称
	 */
	@Column(id = "consult_source_name", datatype = "string64")
	private java.lang.String consultSourceName;

	/**
	 *查询编码
	 */
	@Column(id = "query_code", datatype = "string128")
	private java.lang.String queryCode;

	/**
	 * 设置咨询ID
	 */
	public void setConsultId(java.lang.String consultId) {
		this.consultId = consultId;
	}

	/**
	 * 获取咨询ID
	 */
	public java.lang.String getConsultId() {
		return consultId;
	}

	/**
	 * 设置咨询标题
	 */
	public void setConsultTitle(java.lang.String consultTitle) {
		this.consultTitle = consultTitle;
	}

	/**
	 * 获取咨询标题
	 */
	public java.lang.String getConsultTitle() {
		return consultTitle;
	}

	/**
	 * 设置咨询单位ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取咨询单位ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置咨询单位名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取咨询单位名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置申请者ID
	 */
	public void setApplyId(java.lang.String applyId) {
		this.applyId = applyId;
	}

	/**
	 * 获取申请者ID
	 */
	public java.lang.String getApplyId() {
		return applyId;
	}

	/**
	 * 设置申请者类型(1个人；2企业）
	 */
	public void setApplyType(java.lang.String applyType) {
		this.applyType = applyType;
	}

	/**
	 * 获取申请者类型(1个人；2企业）
	 */
	public java.lang.String getApplyType() {
		return applyType;
	}

	/**
	 * 设置申请者名称
	 */
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	/**
	 * 获取申请者名称
	 */
	public java.lang.String getApplyName() {
		return applyName;
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
	 * 设置咨询时间
	 */
	public void setConsultTime(java.sql.Date consultTime) {
		this.consultTime = consultTime;
	}

	/**
	 * 获取咨询时间
	 */
	public java.sql.Date getConsultTime() {
		return consultTime;
	}

	/**
	 * 设置咨询内容
	 */
	public void setConsultContent(java.lang.String consultContent) {
		this.consultContent = consultContent;
	}

	/**
	 * 获取咨询内容
	 */
	public java.lang.String getConsultContent() {
		return consultContent;
	}

	/**
	 * 设置是否公开(默认公开，Y：公开；N：不公开)
	 */
	public void setIsOpen(java.lang.String isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * 获取是否公开(默认公开，Y：公开；N：不公开)
	 */
	public java.lang.String getIsOpen() {
		return isOpen;
	}

	/**
	 * 设置回复单位名称
	 */
	public void setReplyOrg(java.lang.String replyOrg) {
		this.replyOrg = replyOrg;
	}

	/**
	 * 获取回复单位名称
	 */
	public java.lang.String getReplyOrg() {
		return replyOrg;
	}

	/**
	 * 设置回复时间
	 */
	public void setReplyTime(java.sql.Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * 获取回复时间
	 */
	public java.sql.Date getReplyTime() {
		return replyTime;
	}

	/**
	 * 设置回复内容
	 */
	public void setReplyContent(java.lang.String replyContent) {
		this.replyContent = replyContent;
	}

	/**
	 * 获取回复内容
	 */
	public java.lang.String getReplyContent() {
		return replyContent;
	}

	/**
	 * 设置信息来源(1,实体大厅；2，网上大厅；3，一体机；4，微信；5，APP；）
	 */
	public void setConsultSource(java.lang.String consultSource) {
		this.consultSource = consultSource;
	}

	/**
	 * 获取信息来源(1,实体大厅；2，网上大厅；3，一体机；4，微信；5，APP；）
	 */
	public java.lang.String getConsultSource() {
		return consultSource;
	}

	/**
	 * 设置区域编码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取区域编码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置回复人
	 */
	public void setReplyName(java.lang.String replyName) {
		this.replyName = replyName;
	}

	/**
	 * 获取回复人
	 */
	public java.lang.String getReplyName() {
		return replyName;
	}

	/**
	 * 设置申请人联系电话
	 */
	public void setApplyPhone(java.lang.String applyPhone) {
		this.applyPhone = applyPhone;
	}

	/**
	 * 获取申请人联系电话
	 */
	public java.lang.String getApplyPhone() {
		return applyPhone;
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
	 * 设置咨询时限
	 */
	public void setConsultLimit(java.lang.Double consultLimit) {
		this.consultLimit = consultLimit;
	}

	/**
	 * 获取咨询时限
	 */
	public java.lang.Double getConsultLimit() {
		return consultLimit;
	}

	/**
	 * 设置到期时间
	 */
	public void setExpireTime(java.sql.Date expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * 获取到期时间
	 */
	public java.sql.Date getExpireTime() {
		return expireTime;
	}

	/**
	 * 设置法定时限
	 */
	public void setTimeLimit(java.lang.String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 获取法定时限
	 */
	public java.lang.String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 设置承诺时限
	 */
	public void setPromisesLimit(java.lang.String promisesLimit) {
		this.promisesLimit = promisesLimit;
	}

	/**
	 * 获取承诺时限
	 */
	public java.lang.String getPromisesLimit() {
		return promisesLimit;
	}

	/**
	 * 设置申请者类型名称
	 */
	public void setApplyTypeName(java.lang.String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}

	/**
	 * 获取申请者类型名称
	 */
	public java.lang.String getApplyTypeName() {
		return applyTypeName;
	}

	/**
	 * 设置是否公开名称
	 */
	public void setIsOpenName(java.lang.String isOpenName) {
		this.isOpenName = isOpenName;
	}

	/**
	 * 获取是否公开名称
	 */
	public java.lang.String getIsOpenName() {
		return isOpenName;
	}

	/**
	 * 设置信息来源名称
	 */
	public void setConsultSourceName(java.lang.String consultSourceName) {
		this.consultSourceName = consultSourceName;
	}

	/**
	 * 获取信息来源名称
	 */
	public java.lang.String getConsultSourceName() {
		return consultSourceName;
	}

	/**
	 * 设置查询编码
	 */
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}

	/**
	 * 获取查询编码
	 */
	public java.lang.String getQueryCode() {
		return queryCode;
	}
}
