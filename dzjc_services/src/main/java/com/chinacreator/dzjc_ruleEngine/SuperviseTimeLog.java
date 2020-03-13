package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 发牌执行时间日志
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseTimeLog", table = "TA_JC_SUPERVISE_TIME_LOG", ds = "acceptdata", cache = false)
public class SuperviseTimeLog {
	/**
	 *发牌时间日志表id
	 */
	@Column(id = "supervise_time_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String superviseTimeId;

	/**
	 *本次发牌的批量id，用于标识同一次发牌
	 */
	@Column(id = "batch_id", datatype = "string64")
	private java.lang.String batchId;

	/**
	 *监察开始时间
	 */
	@Column(id = "supervise_start_time", datatype = "date")
	private java.sql.Date superviseStartTime;

	/**
	 *监察开始时办件总数
	 */
	@Column(id = "start_business_qty", datatype = "double")
	private java.lang.Double startBusinessQty;

	/**
	 *监察结束时间
	 */
	@Column(id = "supervise_end_time", datatype = "date")
	private java.sql.Date superviseEndTime;

	/**
	 *监察结束时办件总数
	 */
	@Column(id = "end_business_qty", datatype = "double")
	private java.lang.Double endBusinessQty;

	/**
	 *监察总时长
	 */
	@Column(id = "supervise_total_times", datatype = "double")
	private java.lang.Double superviseTotalTimes;

	/**
	 *本次监察的办件数
	 */
	@Column(id = "supervise_business_qty", datatype = "double")
	private java.lang.Double superviseBusinessQty;

	/**
	 *本次发牌预警数
	 */
	@Column(id = "blue_qty", datatype = "double")
	private java.lang.Double blueQty;

	/**
	 *本次发牌黄牌数
	 */
	@Column(id = "yellow_qty", datatype = "double")
	private java.lang.Double yellowQty;

	/**
	 *本次发牌红牌数
	 */
	@Column(id = "red_qty", datatype = "double")
	private java.lang.Double redQty;

	/**
	 *发牌表当前发牌总数
	 */
	@Column(id = "supervise_total_qty", datatype = "double")
	private java.lang.Double superviseTotalQty;

	/**
	 *查询开始时间
	 */
	@Column(id = "query_start_time", datatype = "date")
	private java.sql.Date queryStartTime;

	/**
	 *查询结束时间
	 */
	@Column(id = "query_end_time", datatype = "date")
	private java.sql.Date queryEndTime;

	/**
	 *查询总时长
	 */
	@Column(id = "query_total_times", datatype = "double")
	private java.lang.Double queryTotalTimes;

	/**
	 *发牌线程开始时间
	 */
	@Column(id = "thread_start_time", datatype = "date")
	private java.sql.Date threadStartTime;

	/**
	 *发牌线程结束
	 */
	@Column(id = "thread_end_time", datatype = "date")
	private java.sql.Date threadEndTime;

	/**
	 *发牌线程总时长
	 */
	@Column(id = "thread_total_times", datatype = "double")
	private java.lang.Double threadTotalTimes;

	/**
	 *反写开始时间
	 */
	@Column(id = "write_start_time", datatype = "date")
	private java.sql.Date writeStartTime;

	/**
	 *反写结束时间
	 */
	@Column(id = "write_end_time", datatype = "date")
	private java.sql.Date writeEndTime;

	/**
	 *反写总时长
	 */
	@Column(id = "write_total_times", datatype = "double")
	private java.lang.Double writeTotalTimes;

	/**
	 * 设置发牌时间日志表id
	 */
	public void setSuperviseTimeId(java.lang.String superviseTimeId) {
		this.superviseTimeId = superviseTimeId;
	}

	/**
	 * 获取发牌时间日志表id
	 */
	public java.lang.String getSuperviseTimeId() {
		return superviseTimeId;
	}

	/**
	 * 设置本次发牌的批量id，用于标识同一次发牌
	 */
	public void setBatchId(java.lang.String batchId) {
		this.batchId = batchId;
	}

	/**
	 * 获取本次发牌的批量id，用于标识同一次发牌
	 */
	public java.lang.String getBatchId() {
		return batchId;
	}

	/**
	 * 设置监察开始时间
	 */
	public void setSuperviseStartTime(java.sql.Date superviseStartTime) {
		this.superviseStartTime = superviseStartTime;
	}

	/**
	 * 获取监察开始时间
	 */
	public java.sql.Date getSuperviseStartTime() {
		return superviseStartTime;
	}

	/**
	 * 设置监察开始时办件总数
	 */
	public void setStartBusinessQty(java.lang.Double startBusinessQty) {
		this.startBusinessQty = startBusinessQty;
	}

	/**
	 * 获取监察开始时办件总数
	 */
	public java.lang.Double getStartBusinessQty() {
		return startBusinessQty;
	}

	/**
	 * 设置监察结束时间
	 */
	public void setSuperviseEndTime(java.sql.Date superviseEndTime) {
		this.superviseEndTime = superviseEndTime;
	}

	/**
	 * 获取监察结束时间
	 */
	public java.sql.Date getSuperviseEndTime() {
		return superviseEndTime;
	}

	/**
	 * 设置监察结束时办件总数
	 */
	public void setEndBusinessQty(java.lang.Double endBusinessQty) {
		this.endBusinessQty = endBusinessQty;
	}

	/**
	 * 获取监察结束时办件总数
	 */
	public java.lang.Double getEndBusinessQty() {
		return endBusinessQty;
	}

	/**
	 * 设置监察总时长
	 */
	public void setSuperviseTotalTimes(java.lang.Double superviseTotalTimes) {
		this.superviseTotalTimes = superviseTotalTimes;
	}

	/**
	 * 获取监察总时长
	 */
	public java.lang.Double getSuperviseTotalTimes() {
		return superviseTotalTimes;
	}

	/**
	 * 设置本次监察的办件数
	 */
	public void setSuperviseBusinessQty(java.lang.Double superviseBusinessQty) {
		this.superviseBusinessQty = superviseBusinessQty;
	}

	/**
	 * 获取本次监察的办件数
	 */
	public java.lang.Double getSuperviseBusinessQty() {
		return superviseBusinessQty;
	}

	/**
	 * 设置本次发牌预警数
	 */
	public void setBlueQty(java.lang.Double blueQty) {
		this.blueQty = blueQty;
	}

	/**
	 * 获取本次发牌预警数
	 */
	public java.lang.Double getBlueQty() {
		return blueQty;
	}

	/**
	 * 设置本次发牌黄牌数
	 */
	public void setYellowQty(java.lang.Double yellowQty) {
		this.yellowQty = yellowQty;
	}

	/**
	 * 获取本次发牌黄牌数
	 */
	public java.lang.Double getYellowQty() {
		return yellowQty;
	}

	/**
	 * 设置本次发牌红牌数
	 */
	public void setRedQty(java.lang.Double redQty) {
		this.redQty = redQty;
	}

	/**
	 * 获取本次发牌红牌数
	 */
	public java.lang.Double getRedQty() {
		return redQty;
	}

	/**
	 * 设置发牌表当前发牌总数
	 */
	public void setSuperviseTotalQty(java.lang.Double superviseTotalQty) {
		this.superviseTotalQty = superviseTotalQty;
	}

	/**
	 * 获取发牌表当前发牌总数
	 */
	public java.lang.Double getSuperviseTotalQty() {
		return superviseTotalQty;
	}

	/**
	 * 设置查询开始时间
	 */
	public void setQueryStartTime(java.sql.Date queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	/**
	 * 获取查询开始时间
	 */
	public java.sql.Date getQueryStartTime() {
		return queryStartTime;
	}

	/**
	 * 设置查询结束时间
	 */
	public void setQueryEndTime(java.sql.Date queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	/**
	 * 获取查询结束时间
	 */
	public java.sql.Date getQueryEndTime() {
		return queryEndTime;
	}

	/**
	 * 设置查询总时长
	 */
	public void setQueryTotalTimes(java.lang.Double queryTotalTimes) {
		this.queryTotalTimes = queryTotalTimes;
	}

	/**
	 * 获取查询总时长
	 */
	public java.lang.Double getQueryTotalTimes() {
		return queryTotalTimes;
	}

	/**
	 * 设置发牌线程开始时间
	 */
	public void setThreadStartTime(java.sql.Date threadStartTime) {
		this.threadStartTime = threadStartTime;
	}

	/**
	 * 获取发牌线程开始时间
	 */
	public java.sql.Date getThreadStartTime() {
		return threadStartTime;
	}

	/**
	 * 设置发牌线程结束
	 */
	public void setThreadEndTime(java.sql.Date threadEndTime) {
		this.threadEndTime = threadEndTime;
	}

	/**
	 * 获取发牌线程结束
	 */
	public java.sql.Date getThreadEndTime() {
		return threadEndTime;
	}

	/**
	 * 设置发牌线程总时长
	 */
	public void setThreadTotalTimes(java.lang.Double threadTotalTimes) {
		this.threadTotalTimes = threadTotalTimes;
	}

	/**
	 * 获取发牌线程总时长
	 */
	public java.lang.Double getThreadTotalTimes() {
		return threadTotalTimes;
	}

	/**
	 * 设置反写开始时间
	 */
	public void setWriteStartTime(java.sql.Date writeStartTime) {
		this.writeStartTime = writeStartTime;
	}

	/**
	 * 获取反写开始时间
	 */
	public java.sql.Date getWriteStartTime() {
		return writeStartTime;
	}

	/**
	 * 设置反写结束时间
	 */
	public void setWriteEndTime(java.sql.Date writeEndTime) {
		this.writeEndTime = writeEndTime;
	}

	/**
	 * 获取反写结束时间
	 */
	public java.sql.Date getWriteEndTime() {
		return writeEndTime;
	}

	/**
	 * 设置反写总时长
	 */
	public void setWriteTotalTimes(java.lang.Double writeTotalTimes) {
		this.writeTotalTimes = writeTotalTimes;
	}

	/**
	 * 获取反写总时长
	 */
	public java.lang.Double getWriteTotalTimes() {
		return writeTotalTimes;
	}
}
