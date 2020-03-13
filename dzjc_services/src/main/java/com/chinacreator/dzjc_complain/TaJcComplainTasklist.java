package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 投诉处理意见
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.taJcComplainTasklist", table = "TA_JC_COMPLAIN_TASKLIST", ds = "acceptdata", cache = false)
public class TaJcComplainTasklist {
	/**
	 *处理意见ID
	 */
	@Column(id = "task_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String taskId;

	/**
	 *投诉信息ID
	 */
	@Column(id = "complain_id", datatype = "string64")
	private java.lang.String complainId;

	/**
	 *环节名称
	 */
	@Column(id = "tache_name", datatype = "string128")
	private java.lang.String tacheName;

	/**
	 *处理人ID
	 */
	@Column(id = "handle_user_id", datatype = "string64")
	private java.lang.String handleUserId;

	/**
	 *处理人姓名
	 */
	@Column(id = "handle_user_name", datatype = "string64")
	private java.lang.String handleUserName;

	/**
	 *处理人所属组织机构ID
	 */
	@Column(id = "handle_org_id", datatype = "string64")
	private java.lang.String handleOrgId;

	/**
	 *处理人所属单位名称
	 */
	@Column(id = "handle_org_name", datatype = "string128")
	private java.lang.String handleOrgName;

	/**
	 *处理时间
	 */
	@Column(id = "handle_time", datatype = "date", sort = SortType.desc)
	private java.sql.Date handleTime;

	/**
	 *处理意见
	 */
	@Column(id = "handle_idea", datatype = "string1024")
	private java.lang.String handleIdea;

	/**
	 *回复意见
	 */
	@Column(id = "reply_idea", datatype = "string1024")
	private java.lang.String replyIdea;

	/**
	 * 设置处理意见ID
	 */
	public void setTaskId(java.lang.String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 获取处理意见ID
	 */
	public java.lang.String getTaskId() {
		return taskId;
	}

	/**
	 * 设置投诉信息ID
	 */
	public void setComplainId(java.lang.String complainId) {
		this.complainId = complainId;
	}

	/**
	 * 获取投诉信息ID
	 */
	public java.lang.String getComplainId() {
		return complainId;
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
	 * 设置处理人ID
	 */
	public void setHandleUserId(java.lang.String handleUserId) {
		this.handleUserId = handleUserId;
	}

	/**
	 * 获取处理人ID
	 */
	public java.lang.String getHandleUserId() {
		return handleUserId;
	}

	/**
	 * 设置处理人姓名
	 */
	public void setHandleUserName(java.lang.String handleUserName) {
		this.handleUserName = handleUserName;
	}

	/**
	 * 获取处理人姓名
	 */
	public java.lang.String getHandleUserName() {
		return handleUserName;
	}

	/**
	 * 设置处理人所属组织机构ID
	 */
	public void setHandleOrgId(java.lang.String handleOrgId) {
		this.handleOrgId = handleOrgId;
	}

	/**
	 * 获取处理人所属组织机构ID
	 */
	public java.lang.String getHandleOrgId() {
		return handleOrgId;
	}

	/**
	 * 设置处理人所属单位名称
	 */
	public void setHandleOrgName(java.lang.String handleOrgName) {
		this.handleOrgName = handleOrgName;
	}

	/**
	 * 获取处理人所属单位名称
	 */
	public java.lang.String getHandleOrgName() {
		return handleOrgName;
	}

	/**
	 * 设置处理时间
	 */
	public void setHandleTime(java.sql.Date handleTime) {
		this.handleTime = handleTime;
	}

	/**
	 * 获取处理时间
	 */
	public java.sql.Date getHandleTime() {
		return handleTime;
	}

	/**
	 * 设置处理意见
	 */
	public void setHandleIdea(java.lang.String handleIdea) {
		this.handleIdea = handleIdea;
	}

	/**
	 * 获取处理意见
	 */
	public java.lang.String getHandleIdea() {
		return handleIdea;
	}

	/**
	 * 设置回复意见
	 */
	public void setReplyIdea(java.lang.String replyIdea) {
		this.replyIdea = replyIdea;
	}

	/**
	 * 获取回复意见
	 */
	public java.lang.String getReplyIdea() {
		return replyIdea;
	}
}
