package com.chinacreator.dzjc_quartz;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * quartz定时任务信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_quartz.QuartzInfo", table = "QRTZ_JOB_DETAILS", ds = "acceptdata", cache = false)
public class QuartzInfo {
	@Column(id = "sched_name", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String schedName;

	/**
	 *job名称
	 */
	@Column(id = "job_name", type = ColumnType.uuid, datatype = "string256")
	private java.lang.String jobName;

	/**
	 *所属组
	 */
	@Column(id = "job_group", type = ColumnType.uuid, datatype = "string256")
	private java.lang.String jobGroup;

	/**
	 *
	 */
	@Column(id = "description", datatype = "string256")
	private java.lang.String description;

	/**
	 *实现类的完全包名
	 */
	@Column(id = "job_class_name", datatype = "string256")
	private java.lang.String jobClassName;

	/**
	 *是否持久化
	 */
	@Column(id = "is_durable", datatype = "string5")
	private java.lang.String isDurable;

	/**
	 *
	 */
	@Column(id = "is_nonconcurrent", datatype = "string5")
	private java.lang.String isNonconcurrent;

	/**
	 *
	 */
	@Column(id = "is_update_data", datatype = "string5")
	private java.lang.String isUpdateData;

	/**
	 *
	 */
	@Column(id = "requests_recovery", datatype = "string5")
	private java.lang.String requestsRecovery;

	@Column(id = "job_data", datatype = "smallblob")
	private byte[] jobData;

	/**
	 *trigger的名字
	 */
	@Column(id = "trigger_name", datatype = "string128")
	private java.lang.String triggerName;

	/**
	 *trigger所属组的名字
	 */
	@Column(id = "trigger_group", datatype = "string128")
	private java.lang.String triggerGroup;

	/**
	 *cron表达式
	 */
	@Column(id = "cron_expression", datatype = "string128")
	private java.lang.String cronExpression;

	/**
	 *下次执行时间
	 */
	@Column(id = "next_fire_time", datatype = "string128")
	private java.lang.String nextFireTime;

	/**
	 *上次执行时间
	 */
	@Column(id = "prev_fire_time", datatype = "string128")
	private java.lang.String prevFireTime;

	/**
	 *开始时间
	 */
	@Column(id = "start_time", datatype = "string128")
	private java.lang.String startTime;

	/**
	 *
	 */
	@Column(id = "priority", datatype = "string128")
	private java.lang.String priority;

	/**
	 *trigger状态
	 */
	@Column(id = "trigger_state", datatype = "string128")
	private java.lang.String triggerState;

	/**
	 * 设置${field.desc}
	 */
	public void setSchedName(java.lang.String schedName) {
		this.schedName = schedName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSchedName() {
		return schedName;
	}

	/**
	 * 设置job名称
	 */
	public void setJobName(java.lang.String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 获取job名称
	 */
	public java.lang.String getJobName() {
		return jobName;
	}

	/**
	 * 设置所属组
	 */
	public void setJobGroup(java.lang.String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * 获取所属组
	 */
	public java.lang.String getJobGroup() {
		return jobGroup;
	}

	/**
	 * 设置
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * 获取
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 设置实现类的完全包名
	 */
	public void setJobClassName(java.lang.String jobClassName) {
		this.jobClassName = jobClassName;
	}

	/**
	 * 获取实现类的完全包名
	 */
	public java.lang.String getJobClassName() {
		return jobClassName;
	}

	/**
	 * 设置是否持久化
	 */
	public void setIsDurable(java.lang.String isDurable) {
		this.isDurable = isDurable;
	}

	/**
	 * 获取是否持久化
	 */
	public java.lang.String getIsDurable() {
		return isDurable;
	}

	/**
	 * 设置
	 */
	public void setIsNonconcurrent(java.lang.String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}

	/**
	 * 获取
	 */
	public java.lang.String getIsNonconcurrent() {
		return isNonconcurrent;
	}

	/**
	 * 设置
	 */
	public void setIsUpdateData(java.lang.String isUpdateData) {
		this.isUpdateData = isUpdateData;
	}

	/**
	 * 获取
	 */
	public java.lang.String getIsUpdateData() {
		return isUpdateData;
	}

	/**
	 * 设置
	 */
	public void setRequestsRecovery(java.lang.String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}

	/**
	 * 获取
	 */
	public java.lang.String getRequestsRecovery() {
		return requestsRecovery;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

	/**
	 * 获取${field.desc}
	 */
	public byte[] getJobData() {
		return jobData;
	}

	/**
	 * 设置trigger的名字
	 */
	public void setTriggerName(java.lang.String triggerName) {
		this.triggerName = triggerName;
	}

	/**
	 * 获取trigger的名字
	 */
	public java.lang.String getTriggerName() {
		return triggerName;
	}

	/**
	 * 设置trigger所属组的名字
	 */
	public void setTriggerGroup(java.lang.String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	/**
	 * 获取trigger所属组的名字
	 */
	public java.lang.String getTriggerGroup() {
		return triggerGroup;
	}

	/**
	 * 设置cron表达式
	 */
	public void setCronExpression(java.lang.String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 获取cron表达式
	 */
	public java.lang.String getCronExpression() {
		return cronExpression;
	}

	/**
	 * 设置下次执行时间
	 */
	public void setNextFireTime(java.lang.String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	/**
	 * 获取下次执行时间
	 */
	public java.lang.String getNextFireTime() {
		return nextFireTime;
	}

	/**
	 * 设置上次执行时间
	 */
	public void setPrevFireTime(java.lang.String prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	/**
	 * 获取上次执行时间
	 */
	public java.lang.String getPrevFireTime() {
		return prevFireTime;
	}

	/**
	 * 设置开始时间
	 */
	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取开始时间
	 */
	public java.lang.String getStartTime() {
		return startTime;
	}

	/**
	 * 设置
	 */
	public void setPriority(java.lang.String priority) {
		this.priority = priority;
	}

	/**
	 * 获取
	 */
	public java.lang.String getPriority() {
		return priority;
	}

	/**
	 * 设置trigger状态
	 */
	public void setTriggerState(java.lang.String triggerState) {
		this.triggerState = triggerState;
	}

	/**
	 * 获取trigger状态
	 */
	public java.lang.String getTriggerState() {
		return triggerState;
	}
}
