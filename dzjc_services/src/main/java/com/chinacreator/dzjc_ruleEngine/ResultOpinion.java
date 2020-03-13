package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 审批结果意见
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ResultOpinion", table = "TA_SP_RESULT_OPINION", ds = "acceptdata", cache = false)
public class ResultOpinion {
	/**
	 *意见ID
	 */
	@Column(id = "opinion_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String opinionId;

	/**
	 *实例ID
	 */
	@Column(id = "instance_id", datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *意见内容
	 */
	@Column(id = "opinion_content", datatype = "string1024")
	private java.lang.String opinionContent;

	/**
	 *审核人名称
	 */
	@Column(id = "user_name", datatype = "string64")
	private java.lang.String userName;

	/**
	 *审核时间
	 */
	@Column(id = "opinion_time", datatype = "date")
	private java.sql.Date opinionTime;

	/**
	 *环节名称
	 */
	@Column(id = "tache_name", datatype = "string128")
	private java.lang.String tacheName;

	/**
	 *审核人ID
	 */
	@Column(id = "user_id", datatype = "string64")
	private java.lang.String userId;

	/**
	 *处理结果(是：通过；否：回退; 特别程序申请;特别申请结果;)
	 */
	@Column(id = "handle_result", datatype = "string64")
	private java.lang.String handleResult;

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
	 *业务标识
	 */
	@Column(id = "business_key", datatype = "string256")
	private java.lang.String businessKey;

	/**
	 *意见类型（1:受理;2:中间环节;3:办结）
	 */
	@Column(id = "type", datatype = "string5")
	private java.lang.String type;

	/**
	 *环节归属单位
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *环节开始时间
	 */
	@Column(id = "begin_time", datatype = "date")
	private java.sql.Date beginTime;

	/**
	 *环节时限
	 */
	@Column(id = "link_time", datatype = "bigdouble")
	private java.lang.Double linkTime;

	/**
	 *环节办理标志（1，已办环节；2，待办环节）
	 */
	@Column(id = "link_flag", datatype = "string5")
	private java.lang.String linkFlag;

	/**
	 *流程唯一id，用于调益政接口，获取该环节相关责任人列表
	 */
	@Column(id = "mark_id", datatype = "string128")
	private java.lang.String markId;

	/**
	 *是否需要监察：Y：需要；N：不需要；默认Y
	 */
	@Column(id = "is_need_supervise", datatype = "string5")
	private java.lang.String isNeedSupervise;

	/**
	 *最近发牌
	 */
	@Column(id = "last_supervise", datatype = "string5")
	private java.lang.String lastSupervise;

	/**
	 *环节使用时长
	 */
	@Column(id = "time_use", datatype = "bigdouble")
	private java.lang.Double timeUse;

	/**
	 *监察日期
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *工作时间拼接的字符串
	 */
	@Column(id = "work_time", datatype = "string128")
	private java.lang.String workTime;

	/**
	 *节假日天数
	 */
	@Column(id = "holiday_count", datatype = "int")
	private java.lang.Integer holidayCount;

	/**
	 *监察标识：Y：正常；N：异常，如果开始时间或环节时限为空，则会修改该字段为N
	 */
	@Column(id = "supervise_flag", datatype = "string5")
	private java.lang.String superviseFlag;

	/**
	 * 设置意见ID
	 */
	public void setOpinionId(java.lang.String opinionId) {
		this.opinionId = opinionId;
	}

	/**
	 * 获取意见ID
	 */
	public java.lang.String getOpinionId() {
		return opinionId;
	}

	/**
	 * 设置实例ID
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取实例ID
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置意见内容
	 */
	public void setOpinionContent(java.lang.String opinionContent) {
		this.opinionContent = opinionContent;
	}

	/**
	 * 获取意见内容
	 */
	public java.lang.String getOpinionContent() {
		return opinionContent;
	}

	/**
	 * 设置审核人名称
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * 获取审核人名称
	 */
	public java.lang.String getUserName() {
		return userName;
	}

	/**
	 * 设置审核时间
	 */
	public void setOpinionTime(java.sql.Date opinionTime) {
		this.opinionTime = opinionTime;
	}

	/**
	 * 获取审核时间
	 */
	public java.sql.Date getOpinionTime() {
		return opinionTime;
	}

	/**
	 * 设置环节名称
	 */
	public void setTacheName(java.lang.String tacheName) {
		this.tacheName = tacheName;
	}

	/**
	 * 获取环节名称
	 */
	public java.lang.String getTacheName() {
		return tacheName;
	}

	/**
	 * 设置审核人ID
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 获取审核人ID
	 */
	public java.lang.String getUserId() {
		return userId;
	}

	/**
	 * 设置处理结果(是：通过；否：回退; 特别程序申请;特别申请结果;)
	 */
	public void setHandleResult(java.lang.String handleResult) {
		this.handleResult = handleResult;
	}

	/**
	 * 获取处理结果(是：通过；否：回退; 特别程序申请;特别申请结果;)
	 */
	public java.lang.String getHandleResult() {
		return handleResult;
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

	/**
	 * 设置业务标识
	 */
	public void setBusinessKey(java.lang.String businessKey) {
		this.businessKey = businessKey;
	}

	/**
	 * 获取业务标识
	 */
	public java.lang.String getBusinessKey() {
		return businessKey;
	}

	/**
	 * 设置意见类型（1:受理;2:中间环节;3:办结）
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取意见类型（1:受理;2:中间环节;3:办结）
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置环节归属单位
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取环节归属单位
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置环节开始时间
	 */
	public void setBeginTime(java.sql.Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 获取环节开始时间
	 */
	public java.sql.Date getBeginTime() {
		return beginTime;
	}

	/**
	 * 设置环节时限
	 */
	public void setLinkTime(java.lang.Double linkTime) {
		this.linkTime = linkTime;
	}

	/**
	 * 获取环节时限
	 */
	public java.lang.Double getLinkTime() {
		return linkTime;
	}

	/**
	 * 设置环节办理标志（1，已办环节；2，待办环节）
	 */
	public void setLinkFlag(java.lang.String linkFlag) {
		this.linkFlag = linkFlag;
	}

	/**
	 * 获取环节办理标志（1，已办环节；2，待办环节）
	 */
	public java.lang.String getLinkFlag() {
		return linkFlag;
	}

	/**
	 * 设置流程唯一id，用于调益政接口，获取该环节相关责任人列表
	 */
	public void setMarkId(java.lang.String markId) {
		this.markId = markId;
	}

	/**
	 * 获取流程唯一id，用于调益政接口，获取该环节相关责任人列表
	 */
	public java.lang.String getMarkId() {
		return markId;
	}

	/**
	 * 设置是否需要监察：Y：需要；N：不需要；默认Y
	 */
	public void setIsNeedSupervise(java.lang.String isNeedSupervise) {
		this.isNeedSupervise = isNeedSupervise;
	}

	/**
	 * 获取是否需要监察：Y：需要；N：不需要；默认Y
	 */
	public java.lang.String getIsNeedSupervise() {
		return isNeedSupervise;
	}

	/**
	 * 设置最近发牌
	 */
	public void setLastSupervise(java.lang.String lastSupervise) {
		this.lastSupervise = lastSupervise;
	}

	/**
	 * 获取最近发牌
	 */
	public java.lang.String getLastSupervise() {
		return lastSupervise;
	}

	/**
	 * 设置环节使用时长
	 */
	public void setTimeUse(java.lang.Double timeUse) {
		this.timeUse = timeUse;
	}

	/**
	 * 获取环节使用时长
	 */
	public java.lang.Double getTimeUse() {
		return timeUse;
	}

	/**
	 * 设置监察日期
	 */
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取监察日期
	 */
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置工作时间拼接的字符串
	 */
	public void setWorkTime(java.lang.String workTime) {
		this.workTime = workTime;
	}

	/**
	 * 获取工作时间拼接的字符串
	 */
	public java.lang.String getWorkTime() {
		return workTime;
	}

	/**
	 * 设置节假日天数
	 */
	public void setHolidayCount(java.lang.Integer holidayCount) {
		this.holidayCount = holidayCount;
	}

	/**
	 * 获取节假日天数
	 */
	public java.lang.Integer getHolidayCount() {
		return holidayCount;
	}

	/**
	 * 设置监察标识：Y：正常；N：异常，如果开始时间或环节时限为空，则会修改该字段为N
	 */
	public void setSuperviseFlag(java.lang.String superviseFlag) {
		this.superviseFlag = superviseFlag;
	}

	/**
	 * 获取监察标识：Y：正常；N：异常，如果开始时间或环节时限为空，则会修改该字段为N
	 */
	public java.lang.String getSuperviseFlag() {
		return superviseFlag;
	}
}
