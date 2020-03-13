package com.chinacreator.dzjc_log;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 日志
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_log.ConsoleLog", table = "TA_JC_CONSOLE_LOG", ds = "acceptdata", cache = false)
public class ConsoleLog {
	/**
	 *主键id
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string256")
	private java.lang.String id;

	/**
	 *类型
	 */
	@Column(id = "log_type", datatype = "string10")
	private java.lang.String logType;

	/**
	 *标题
	 */
	@Column(id = "log_title", datatype = "string256")
	private java.lang.String logTitle;

	/**
	 *内容
	 */
	@Column(id = "log_content", datatype = "string2000")
	private java.lang.String logContent;

	/**
	 *日期
	 */
	@Column(id = "log_date", datatype = "timestamp")
	private java.sql.Timestamp logDate;

	/**
	 * 设置主键id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置类型
	 */
	public void setLogType(java.lang.String logType) {
		this.logType = logType;
	}

	/**
	 * 获取类型
	 */
	public java.lang.String getLogType() {
		return logType;
	}

	/**
	 * 设置标题
	 */
	public void setLogTitle(java.lang.String logTitle) {
		this.logTitle = logTitle;
	}

	/**
	 * 获取标题
	 */
	public java.lang.String getLogTitle() {
		return logTitle;
	}

	/**
	 * 设置内容
	 */
	public void setLogContent(java.lang.String logContent) {
		this.logContent = logContent;
	}

	/**
	 * 获取内容
	 */
	public java.lang.String getLogContent() {
		return logContent;
	}

	/**
	 * 设置日期
	 */
	public void setLogDate(java.sql.Timestamp logDate) {
		this.logDate = logDate;
	}

	/**
	 * 获取日期
	 */
	public java.sql.Timestamp getLogDate() {
		return logDate;
	}
}
