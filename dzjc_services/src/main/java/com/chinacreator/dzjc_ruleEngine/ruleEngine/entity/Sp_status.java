package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 功能执行结果监察
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status", table = "TA_JC_SP_STATUS", ds = "acceptdata", cache = false)
public class Sp_status {
	@Column(id = "id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String id;

	/**
	 *功能执行名称
	 */
	@Column(id = "function_name", datatype = "string128")
	private java.lang.String functionName;

	/**
	 *功能执行的行政区划编码
	 */
	@Column(id = "supervise_areacode", datatype = "string64")
	private java.lang.String superviseAreacode;

	/**
	 *功能执行的行政区划名称
	 */
	@Column(id = "supervise_areaname", datatype = "string64")
	private java.lang.String superviseAreaname;

	/**
	 *功能执行时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *功能执行状态，Y：成功；N：失败
	 */
	@Column(id = "status", datatype = "string5")
	private java.lang.String status;

	/**
	 *异常
	 */
	@Column(id = "exception", datatype = "string2000")
	private java.lang.String exception;

	/**
	 *类别
	 */
	@Column(id = "type", datatype = "string10")
	private java.lang.String type;

	/**
	 *处理条数
	 */
	@Column(id = "process_qty", datatype = "double")
	private java.lang.Double processQty;

	/**
	 *批量id
	 */
	@Column(id = "batch_id", datatype = "string64")
	private java.lang.String batchId;

	/**
	 *监察总数
	 */
	@Column(id = "supervise_total", datatype = "double")
	private java.lang.Double superviseTotal;

	/**
	 *查询次数
	 */
	@Column(id = "query_count", datatype = "double")
	private java.lang.Double queryCount;

	/**
	 *查询总数
	 */
	@Column(id = "query_total", datatype = "double")
	private java.lang.Double queryTotal;

	/**
	 *查询次数
	 */
	@Column(id = "run_count", datatype = "double")
	private java.lang.Double runCount;

	/**
	 *成功数
	 */
	@Column(id = "success_total", datatype = "double")
	private java.lang.Double successTotal;

	/**
	 *失败数
	 */
	@Column(id = "fail_total", datatype = "double")
	private java.lang.Double failTotal;

	/**
	 *执行结束时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *所用时长（分钟）
	 */
	@Column(id = "use_time", datatype = "double")
	private java.lang.Double useTime;

	/**
	 *监察开始时间
	 */
	@Column(id = "supervise_start_time", datatype = "date")
	private java.sql.Date superviseStartTime;

	/**
	 *监察结束时间
	 */
	@Column(id = "supervise_end_time", datatype = "date")
	private java.sql.Date superviseEndTime;

	/**
	 *监察执行时长
	 */
	@Column(id = "supervise_use_times", datatype = "double")
	private java.lang.Double superviseUseTimes;

	/**
	 * 设置${field.desc}
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置功能执行名称
	 */
	public void setFunctionName(java.lang.String functionName) {
		this.functionName = functionName;
	}

	/**
	 * 获取功能执行名称
	 */
	public java.lang.String getFunctionName() {
		return functionName;
	}

	/**
	 * 设置功能执行的行政区划编码
	 */
	public void setSuperviseAreacode(java.lang.String superviseAreacode) {
		this.superviseAreacode = superviseAreacode;
	}

	/**
	 * 获取功能执行的行政区划编码
	 */
	public java.lang.String getSuperviseAreacode() {
		return superviseAreacode;
	}

	/**
	 * 设置功能执行的行政区划名称
	 */
	public void setSuperviseAreaname(java.lang.String superviseAreaname) {
		this.superviseAreaname = superviseAreaname;
	}

	/**
	 * 获取功能执行的行政区划名称
	 */
	public java.lang.String getSuperviseAreaname() {
		return superviseAreaname;
	}

	/**
	 * 设置功能执行时间
	 */
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取功能执行时间
	 */
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置功能执行状态，Y：成功；N：失败
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 获取功能执行状态，Y：成功；N：失败
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * 设置异常
	 */
	public void setException(java.lang.String exception) {
		this.exception = exception;
	}

	/**
	 * 获取异常
	 */
	public java.lang.String getException() {
		return exception;
	}

	/**
	 * 设置类别
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取类别
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置处理条数
	 */
	public void setProcessQty(java.lang.Double processQty) {
		this.processQty = processQty;
	}

	/**
	 * 获取处理条数
	 */
	public java.lang.Double getProcessQty() {
		return processQty;
	}

	/**
	 * 设置批量id
	 */
	public void setBatchId(java.lang.String batchId) {
		this.batchId = batchId;
	}

	/**
	 * 获取批量id
	 */
	public java.lang.String getBatchId() {
		return batchId;
	}

	/**
	 * 设置监察总数
	 */
	public void setSuperviseTotal(java.lang.Double superviseTotal) {
		this.superviseTotal = superviseTotal;
	}

	/**
	 * 获取监察总数
	 */
	public java.lang.Double getSuperviseTotal() {
		return superviseTotal;
	}

	/**
	 * 设置查询次数
	 */
	public void setQueryCount(java.lang.Double queryCount) {
		this.queryCount = queryCount;
	}

	/**
	 * 获取查询次数
	 */
	public java.lang.Double getQueryCount() {
		return queryCount;
	}

	/**
	 * 设置查询总数
	 */
	public void setQueryTotal(java.lang.Double queryTotal) {
		this.queryTotal = queryTotal;
	}

	/**
	 * 获取查询总数
	 */
	public java.lang.Double getQueryTotal() {
		return queryTotal;
	}

	/**
	 * 设置查询次数
	 */
	public void setRunCount(java.lang.Double runCount) {
		this.runCount = runCount;
	}

	/**
	 * 获取查询次数
	 */
	public java.lang.Double getRunCount() {
		return runCount;
	}

	/**
	 * 设置成功数
	 */
	public void setSuccessTotal(java.lang.Double successTotal) {
		this.successTotal = successTotal;
	}

	/**
	 * 获取成功数
	 */
	public java.lang.Double getSuccessTotal() {
		return successTotal;
	}

	/**
	 * 设置失败数
	 */
	public void setFailTotal(java.lang.Double failTotal) {
		this.failTotal = failTotal;
	}

	/**
	 * 获取失败数
	 */
	public java.lang.Double getFailTotal() {
		return failTotal;
	}

	/**
	 * 设置执行结束时间
	 */
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取执行结束时间
	 */
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置所用时长（分钟）
	 */
	public void setUseTime(java.lang.Double useTime) {
		this.useTime = useTime;
	}

	/**
	 * 获取所用时长（分钟）
	 */
	public java.lang.Double getUseTime() {
		return useTime;
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
	 * 设置监察执行时长
	 */
	public void setSuperviseUseTimes(java.lang.Double superviseUseTimes) {
		this.superviseUseTimes = superviseUseTimes;
	}

	/**
	 * 获取监察执行时长
	 */
	public java.lang.Double getSuperviseUseTimes() {
		return superviseUseTimes;
	}
}
