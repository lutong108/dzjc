package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 短信记录
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.MsgRecord", table = "TA_JC_MSG_RECORD", ds = "acceptdata", cache = false)
public class MsgRecord {
	/**
	 *短信记录id
	 */
	@Column(id = "msg_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String msgId;

	/**
	 *业务id
	 */
	@Column(id = "business_id", datatype = "string64")
	private java.lang.String businessId;

	/**
	 *发牌记录id
	 */
	@Column(id = "supervise_id", datatype = "string1024")
	private java.lang.String superviseId;

	/**
	 *发牌机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *监察类型
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *监察结果：1：预警；2：黄牌；3：红牌
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *手机号码
	 */
	@Column(id = "send_mobile", datatype = "string20")
	private java.lang.String sendMobile;

	/**
	 *接收人姓名
	 */
	@Column(id = "send_name", datatype = "string64")
	private java.lang.String sendName;

	/**
	 *短信内容
	 */
	@Column(id = "send_content", datatype = "string512")
	private java.lang.String sendContent;

	/**
	 *发送标志：Y：已发；N：未发
	 */
	@Column(id = "send_flag", datatype = "string10")
	private java.lang.String sendFlag;

	/**
	 *发送时间
	 */
	@Column(id = "send_time", datatype = "date")
	private java.sql.Date sendTime;

	/**
	 *短信messageId
	 */
	@Column(id = "send_msg_id", datatype = "string1024")
	private java.lang.String sendMsgId;

	/**
	 *失败错误信息
	 */
	@Column(id = "result_des", datatype = "string1536")
	private java.lang.String resultDes;

	/**
	 * 设置短信记录id
	 */
	public void setMsgId(java.lang.String msgId) {
		this.msgId = msgId;
	}

	/**
	 * 获取短信记录id
	 */
	public java.lang.String getMsgId() {
		return msgId;
	}

	/**
	 * 设置业务id
	 */
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取业务id
	 */
	public java.lang.String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置发牌记录id
	 */
	public void setSuperviseId(java.lang.String superviseId) {
		this.superviseId = superviseId;
	}

	/**
	 * 获取发牌记录id
	 */
	public java.lang.String getSuperviseId() {
		return superviseId;
	}

	/**
	 * 设置发牌机构id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取发牌机构id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置监察类型
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取监察类型
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置监察结果：1：预警；2：黄牌；3：红牌
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果：1：预警；2：黄牌；3：红牌
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置监察时间
	 */
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取监察时间
	 */
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置手机号码
	 */
	public void setSendMobile(java.lang.String sendMobile) {
		this.sendMobile = sendMobile;
	}

	/**
	 * 获取手机号码
	 */
	public java.lang.String getSendMobile() {
		return sendMobile;
	}

	/**
	 * 设置接收人姓名
	 */
	public void setSendName(java.lang.String sendName) {
		this.sendName = sendName;
	}

	/**
	 * 获取接收人姓名
	 */
	public java.lang.String getSendName() {
		return sendName;
	}

	/**
	 * 设置短信内容
	 */
	public void setSendContent(java.lang.String sendContent) {
		this.sendContent = sendContent;
	}

	/**
	 * 获取短信内容
	 */
	public java.lang.String getSendContent() {
		return sendContent;
	}

	/**
	 * 设置发送标志：Y：已发；N：未发
	 */
	public void setSendFlag(java.lang.String sendFlag) {
		this.sendFlag = sendFlag;
	}

	/**
	 * 获取发送标志：Y：已发；N：未发
	 */
	public java.lang.String getSendFlag() {
		return sendFlag;
	}

	/**
	 * 设置发送时间
	 */
	public void setSendTime(java.sql.Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 获取发送时间
	 */
	public java.sql.Date getSendTime() {
		return sendTime;
	}

	/**
	 * 设置短信messageId
	 */
	public void setSendMsgId(java.lang.String sendMsgId) {
		this.sendMsgId = sendMsgId;
	}

	/**
	 * 获取短信messageId
	 */
	public java.lang.String getSendMsgId() {
		return sendMsgId;
	}

	/**
	 * 设置失败错误信息
	 */
	public void setResultDes(java.lang.String resultDes) {
		this.resultDes = resultDes;
	}

	/**
	 * 获取失败错误信息
	 */
	public java.lang.String getResultDes() {
		return resultDes;
	}
}
