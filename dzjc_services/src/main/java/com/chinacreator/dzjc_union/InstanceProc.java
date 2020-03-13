package com.chinacreator.dzjc_union;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 办件流转记录
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_union.InstanceProc", table = "V_INSTANCE_PROC", ds = "acceptdata", cache = false)
public class InstanceProc {
	/**
	 *主键id
	 */
	@Column(id = "mid", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String mid;

	/**
	 *办件流程实例id
	 */
	@Column(id = "proc_inst_id_", datatype = "string64")
	private java.lang.String procInstId;

	/**
	 *办件实例id
	 */
	@Column(id = "instance_id", datatype = "string32")
	private java.lang.String instanceId;

	/**
	 *用户id
	 */
	@Column(id = "user_id", datatype = "string64")
	private java.lang.String userId;

	/**
	 *活动名称
	 */
	@Column(id = "act_name", datatype = "string32")
	private java.lang.String actName;

	/**
	 *处理用户
	 */
	@Column(id = "user_name", datatype = "string1024")
	private java.lang.String userName;

	/**
	 *处理意见id
	 */
	@Column(id = "task_id", datatype = "string64")
	private java.lang.String taskId;

	/**
	 *活动开始时间
	 */
	@Column(id = "start_time", datatype = "string20")
	private java.lang.String startTime;

	/**
	 *活动结束时间
	 */
	@Column(id = "end_time", datatype = "string20")
	private java.lang.String endTime;

	/**
	 * 设置主键id
	 */
	public void setMid(java.lang.String mid) {
		this.mid = mid;
	}

	/**
	 * 获取主键id
	 */
	public java.lang.String getMid() {
		return mid;
	}

	/**
	 * 设置办件流程实例id
	 */
	public void setProcInstId(java.lang.String procInstId) {
		this.procInstId = procInstId;
	}

	/**
	 * 获取办件流程实例id
	 */
	public java.lang.String getProcInstId() {
		return procInstId;
	}

	/**
	 * 设置办件实例id
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取办件实例id
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户id
	 */
	public java.lang.String getUserId() {
		return userId;
	}

	/**
	 * 设置活动名称
	 */
	public void setActName(java.lang.String actName) {
		this.actName = actName;
	}

	/**
	 * 获取活动名称
	 */
	public java.lang.String getActName() {
		return actName;
	}

	/**
	 * 设置处理用户
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * 获取处理用户
	 */
	public java.lang.String getUserName() {
		return userName;
	}

	/**
	 * 设置处理意见id
	 */
	public void setTaskId(java.lang.String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 获取处理意见id
	 */
	public java.lang.String getTaskId() {
		return taskId;
	}

	/**
	 * 设置活动开始时间
	 */
	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取活动开始时间
	 */
	public java.lang.String getStartTime() {
		return startTime;
	}

	/**
	 * 设置活动结束时间
	 */
	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取活动结束时间
	 */
	public java.lang.String getEndTime() {
		return endTime;
	}
}
