package com.chinacreator.dzjc_log;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 异常发牌
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_log.ErrorSupervise", table = "V_TA_JC_SUPERVISE_ERROR", ds = "acceptdata", cache = false)
public class ErrorSupervise {
	/**
	 *主键ID
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String id;

	/**
	 *发牌结果
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *发牌类型编码
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *发牌类型名称
	 */
	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *开始时间
	 */
	@Column(id = "start_time", datatype = "date")
	private java.sql.Date startTime;

	/**
	 *办结时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *办件状态
	 */
	@Column(id = "status", datatype = "string64")
	private java.lang.String status;

	/**
	 *承诺时限
	 */
	@Column(id = "promise_limit", datatype = "bigdouble")
	private java.lang.Double promiseLimit;

	/**
	 *法定时限
	 */
	@Column(id = "process_limit", datatype = "bigdouble")
	private java.lang.Double processLimit;

	/**
	 *轮询时间
	 */
	@Column(id = "check_time", datatype = "date")
	private java.sql.Date checkTime;

	/**
	 *轮询结果
	 */
	@Column(id = "check_status", datatype = "string5")
	private java.lang.String checkStatus;

	/**
	 *轮询时办结时间
	 */
	@Column(id = "check_end_time", datatype = "date")
	private java.sql.Date checkEndTime;

	/**
	 *业务ID
	 */
	@Column(id = "bussiness_id", datatype = "string64")
	private java.lang.String bussinessId;

	/**
	 *查询编码
	 */
	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	/**
	 *业务编码
	 */
	@Column(id = "bussiness_code", datatype = "string128")
	private java.lang.String bussinessCode;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string128")
	private java.lang.String orgName;

	/**
	 *办件名称
	 */
	@Column(id = "bussiness_name", datatype = "string256")
	private java.lang.String bussinessName;

	/**
	 *接受时间
	 */
	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	/**
	 *轮询结果临时
	 */
	@Column(id = "check_status_temp", datatype = "string512")
	private java.lang.String checkStatusTemp;

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
	 * 设置发牌结果
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取发牌结果
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置发牌类型编码
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取发牌类型编码
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置发牌类型名称
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取发牌类型名称
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
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
	 * 设置开始时间
	 */
	public void setStartTime(java.sql.Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取开始时间
	 */
	public java.sql.Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置办结时间
	 */
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取办结时间
	 */
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置办件状态
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 获取办件状态
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * 设置承诺时限
	 */
	public void setPromiseLimit(java.lang.Double promiseLimit) {
		this.promiseLimit = promiseLimit;
	}

	/**
	 * 获取承诺时限
	 */
	public java.lang.Double getPromiseLimit() {
		return promiseLimit;
	}

	/**
	 * 设置法定时限
	 */
	public void setProcessLimit(java.lang.Double processLimit) {
		this.processLimit = processLimit;
	}

	/**
	 * 获取法定时限
	 */
	public java.lang.Double getProcessLimit() {
		return processLimit;
	}

	/**
	 * 设置轮询时间
	 */
	public void setCheckTime(java.sql.Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * 获取轮询时间
	 */
	public java.sql.Date getCheckTime() {
		return checkTime;
	}

	/**
	 * 设置轮询结果
	 */
	public void setCheckStatus(java.lang.String checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * 获取轮询结果
	 */
	public java.lang.String getCheckStatus() {
		return checkStatus;
	}

	/**
	 * 设置轮询时办结时间
	 */
	public void setCheckEndTime(java.sql.Date checkEndTime) {
		this.checkEndTime = checkEndTime;
	}

	/**
	 * 获取轮询时办结时间
	 */
	public java.sql.Date getCheckEndTime() {
		return checkEndTime;
	}

	/**
	 * 设置业务ID
	 */
	public void setBussinessId(java.lang.String bussinessId) {
		this.bussinessId = bussinessId;
	}

	/**
	 * 获取业务ID
	 */
	public java.lang.String getBussinessId() {
		return bussinessId;
	}

	/**
	 * 设置查询编码
	 */
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}

	/**
	 * 获取查询编码
	 */
	public java.lang.String getQueryCode() {
		return queryCode;
	}

	/**
	 * 设置业务编码
	 */
	public void setBussinessCode(java.lang.String bussinessCode) {
		this.bussinessCode = bussinessCode;
	}

	/**
	 * 获取业务编码
	 */
	public java.lang.String getBussinessCode() {
		return bussinessCode;
	}

	/**
	 * 设置机构ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置机构名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取机构名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置办件名称
	 */
	public void setBussinessName(java.lang.String bussinessName) {
		this.bussinessName = bussinessName;
	}

	/**
	 * 获取办件名称
	 */
	public java.lang.String getBussinessName() {
		return bussinessName;
	}

	/**
	 * 设置接受时间
	 */
	public void setAcceptTime(java.sql.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取接受时间
	 */
	public java.sql.Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置轮询结果临时
	 */
	public void setCheckStatusTemp(java.lang.String checkStatusTemp) {
		this.checkStatusTemp = checkStatusTemp;
	}

	/**
	 * 获取轮询结果临时
	 */
	public java.lang.String getCheckStatusTemp() {
		return checkStatusTemp;
	}
}
