package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 定时任务配置表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_config", table = "TA_JC_THREAD_CONFIG", ds = "acceptdata", cache = false)
public class Thread_config {
	/**
	 *定时任务配置表id
	 */
	@Column(id = "config_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String configId;

	/**
	 *每次查询条数
	 */
	@Column(id = "query_size", datatype = "int")
	private java.lang.Integer querySize;

	/**
	 *线程总数
	 */
	@Column(id = "thread_size", datatype = "int")
	private java.lang.Integer threadSize;

	/**
	 *最近修改时间
	 */
	@Column(id = "last_update_time", datatype = "date")
	private java.sql.Date lastUpdateTime;

	/**
	 * 设置定时任务配置表id
	 */
	public void setConfigId(java.lang.String configId) {
		this.configId = configId;
	}

	/**
	 * 获取定时任务配置表id
	 */
	public java.lang.String getConfigId() {
		return configId;
	}

	/**
	 * 设置每次查询条数
	 */
	public void setQuerySize(java.lang.Integer querySize) {
		this.querySize = querySize;
	}

	/**
	 * 获取每次查询条数
	 */
	public java.lang.Integer getQuerySize() {
		return querySize;
	}

	/**
	 * 设置线程总数
	 */
	public void setThreadSize(java.lang.Integer threadSize) {
		this.threadSize = threadSize;
	}

	/**
	 * 获取线程总数
	 */
	public java.lang.Integer getThreadSize() {
		return threadSize;
	}

	/**
	 * 设置最近修改时间
	 */
	public void setLastUpdateTime(java.sql.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * 获取最近修改时间
	 */
	public java.sql.Date getLastUpdateTime() {
		return lastUpdateTime;
	}
}
