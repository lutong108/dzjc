package com.chinacreator.dzjc_Appeal;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 申诉信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_Appeal.taJcWarningAppeals", table = "TA_JC_WARNING_APPEAL", ds = "acceptdata", cache = false)
public class TaJcWarningAppeals {
	/**
	 *主键ID
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String id;

	/**
	 *监察信息ID
	 */
	@Column(id = "supervise_info_id", datatype = "string1024")
	private java.lang.String superviseInfoId;

	/**
	 *申诉单位
	 */
	@Column(id = "appeal_orgname", datatype = "string256")
	private java.lang.String appealOrgname;

	/**
	 *申诉时间
	 */
	@Column(id = "appeal_time", datatype = "date")
	private java.sql.Date appealTime;

	/**
	 *申诉内容
	 */
	@Column(id = "appeal_content", datatype = "string512")
	private java.lang.String appealContent;

	/**
	 *申诉人名称
	 */
	@Column(id = "appeal_user_name", datatype = "string128")
	private java.lang.String appealUserName;

	/**
	 *处理结果(撤销 C,打回D,上报B)
	 */
	@Column(id = "process_result", datatype = "string32")
	private java.lang.String processResult;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *最大申诉处理ID
	 */
	@Column(id = "max_process_id", datatype = "string64")
	private java.lang.String maxProcessId;

	/**
	 *申诉单位ID
	 */
	@Column(id = "appeal_orgid", datatype = "string64")
	private java.lang.String appealOrgid;

	/**
	 *数据交换批次
	 */
	@Column(id = "pici", datatype = "string20")
	private java.lang.String pici;

	/**
	 *回复单位编号
	 */
	@Column(id = "reply_org_id", datatype = "string64")
	private java.lang.String replyOrgId;

	/**
	 *回复单位名称
	 */
	@Column(id = "reply_org_name", datatype = "string256")
	private java.lang.String replyOrgName;

	/**
	 *回复用户编号
	 */
	@Column(id = "reply_user_id", datatype = "string64")
	private java.lang.String replyUserId;

	/**
	 *回复用户名称
	 */
	@Column(id = "reply_user_name", datatype = "string256")
	private java.lang.String replyUserName;

	/**
	 *回复内容
	 */
	@Column(id = "reply_content", datatype = "string2000")
	private java.lang.String replyContent;

	/**
	 *回复时间
	 */
	@Column(id = "reply_time", datatype = "date")
	private java.sql.Date replyTime;

	/**
	 * 设置主键ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置监察信息ID
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取监察信息ID
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置申诉单位
	 */
	public void setAppealOrgname(java.lang.String appealOrgname) {
		this.appealOrgname = appealOrgname;
	}

	/**
	 * 获取申诉单位
	 */
	public java.lang.String getAppealOrgname() {
		return appealOrgname;
	}

	/**
	 * 设置申诉时间
	 */
	public void setAppealTime(java.sql.Date appealTime) {
		this.appealTime = appealTime;
	}

	/**
	 * 获取申诉时间
	 */
	public java.sql.Date getAppealTime() {
		return appealTime;
	}

	/**
	 * 设置申诉内容
	 */
	public void setAppealContent(java.lang.String appealContent) {
		this.appealContent = appealContent;
	}

	/**
	 * 获取申诉内容
	 */
	public java.lang.String getAppealContent() {
		return appealContent;
	}

	/**
	 * 设置申诉人名称
	 */
	public void setAppealUserName(java.lang.String appealUserName) {
		this.appealUserName = appealUserName;
	}

	/**
	 * 获取申诉人名称
	 */
	public java.lang.String getAppealUserName() {
		return appealUserName;
	}

	/**
	 * 设置处理结果(撤销 C,打回D,上报B)
	 */
	public void setProcessResult(java.lang.String processResult) {
		this.processResult = processResult;
	}

	/**
	 * 获取处理结果(撤销 C,打回D,上报B)
	 */
	public java.lang.String getProcessResult() {
		return processResult;
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
	 * 设置最大申诉处理ID
	 */
	public void setMaxProcessId(java.lang.String maxProcessId) {
		this.maxProcessId = maxProcessId;
	}

	/**
	 * 获取最大申诉处理ID
	 */
	public java.lang.String getMaxProcessId() {
		return maxProcessId;
	}

	/**
	 * 设置申诉单位ID
	 */
	public void setAppealOrgid(java.lang.String appealOrgid) {
		this.appealOrgid = appealOrgid;
	}

	/**
	 * 获取申诉单位ID
	 */
	public java.lang.String getAppealOrgid() {
		return appealOrgid;
	}

	/**
	 * 设置数据交换批次
	 */
	public void setPici(java.lang.String pici) {
		this.pici = pici;
	}

	/**
	 * 获取数据交换批次
	 */
	public java.lang.String getPici() {
		return pici;
	}

	/**
	 * 设置回复单位编号
	 */
	public void setReplyOrgId(java.lang.String replyOrgId) {
		this.replyOrgId = replyOrgId;
	}

	/**
	 * 获取回复单位编号
	 */
	public java.lang.String getReplyOrgId() {
		return replyOrgId;
	}

	/**
	 * 设置回复单位名称
	 */
	public void setReplyOrgName(java.lang.String replyOrgName) {
		this.replyOrgName = replyOrgName;
	}

	/**
	 * 获取回复单位名称
	 */
	public java.lang.String getReplyOrgName() {
		return replyOrgName;
	}

	/**
	 * 设置回复用户编号
	 */
	public void setReplyUserId(java.lang.String replyUserId) {
		this.replyUserId = replyUserId;
	}

	/**
	 * 获取回复用户编号
	 */
	public java.lang.String getReplyUserId() {
		return replyUserId;
	}

	/**
	 * 设置回复用户名称
	 */
	public void setReplyUserName(java.lang.String replyUserName) {
		this.replyUserName = replyUserName;
	}

	/**
	 * 获取回复用户名称
	 */
	public java.lang.String getReplyUserName() {
		return replyUserName;
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
}
