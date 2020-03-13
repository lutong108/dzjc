package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 所有业务列表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.BussinessAllInfo", table = "VA_TA_JC_ALLBUSSINESS_INFO", ds = "acceptdata", cache = false)
public class BussinessAllInfo {
	/**
	 *业务类型
	 */
	@Column(id = "bussiness_type", datatype = "string64")
	private java.lang.String bussinessType;

	/**
	 *发牌结果编号
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *发牌类型编号
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *发牌状态
	 */
	@Column(id = "state", datatype = "string20")
	private java.lang.String state;

	/**
	 *业务ID
	 */
	@Column(id = "bussiness_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String bussinessId;

	/**
	 *业务名称
	 */
	@Column(id = "bussiness_name", datatype = "string512")
	private java.lang.String bussinessName;

	/**
	 *时间
	 */
	@Column(id = "bussiness_time", datatype = "date", sort = SortType.desc)
	private java.sql.Date bussinessTime;

	/**
	 *发牌类型名称
	 */
	@Column(id = "supervise_type_name", datatype = "string64")
	private java.lang.String superviseTypeName;

	/**
	 *发牌结果名称
	 */
	@Column(id = "supervise_result_name", datatype = "string64")
	private java.lang.String superviseResultName;

	/**
	 *开始时间
	 */
	@Column(id = "beginDate", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *结束时间
	 */
	@Column(id = "endDate", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *结构id
	 */
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	/**
	 *结构名称
	 */
	@Column(id = "org_name", datatype = "string64")
	private java.lang.String orgName;

	/**
	 *业务类型名称
	 */
	@Column(id = "bussiness_type_name", datatype = "string64")
	private java.lang.String bussinessTypeName;

	/**
	 *发牌状态名称
	 */
	@Column(id = "state_name", datatype = "string64")
	private java.lang.String stateName;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *监察开始时间
	 */
	@Column(id = "jcBeginDate", datatype = "date")
	private java.sql.Date jcBeginDate;

	/**
	 *监察结束时间
	 */
	@Column(id = "jcEndDate", datatype = "date")
	private java.sql.Date jcEndDate;

	/**
	 *办件办理状态
	 */
	@Column(id = "bj_state", datatype = "string5")
	private java.lang.String bjState;

	/**
	 *递归机构
	 */
	@Column(id = "temporgid", datatype = "string1024")
	private java.lang.String temporgid;

	/**
	 *主键id
	 */
	@Column(id = "id", datatype = "string256")
	private java.lang.String id;

	/**
	 *回复时间或办结时间
	 */
	@Column(id = "reply_time", datatype = "date")
	private java.sql.Date replyTime;

	/**
	 *是否有效
	 */
	@Column(id = "is_valid", datatype = "string64")
	private java.lang.String isValid;

	/**
	 *承诺时限
	 */
	@Column(id = "promise_limit", datatype = "string64")
	private java.lang.String promiseLimit;

	/**
	 *法定时限
	 */
	@Column(id = "process_limit", datatype = "string64")
	private java.lang.String processLimit;

	/**
	 *承诺所剩时间
	 */
	@Column(id = "commitment", datatype = "string64")
	private java.lang.String commitment;

	/**
	 *法定所剩时间
	 */
	@Column(id = "legal", datatype = "string64")
	private java.lang.String legal;

	/**
	 *所用时长
	 */
	@Column(id = "time_use", datatype = "int")
	private java.lang.Integer timeUse;

	/**
	 *申请者姓名
	 */
	@Column(id = "apply_name", datatype = "string64")
	private java.lang.String applyName;

	/**
	 *实例编号
	 */
	@Column(id = "instance_code", datatype = "string64")
	private java.lang.String instanceCode;

	/**
	 *办结时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *查询编码
	 */
	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	/**
	 *事项id
	 */
	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	/**
	 *数据交换时间
	 */
	@Column(id = "etl_time", datatype = "string64")
	private java.lang.String etlTime;

	/**
	 * 设置业务类型
	 */
	public void setBussinessType(java.lang.String bussinessType) {
		this.bussinessType = bussinessType;
	}

	/**
	 * 获取业务类型
	 */
	public java.lang.String getBussinessType() {
		return bussinessType;
	}

	/**
	 * 设置发牌结果编号
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取发牌结果编号
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置发牌类型编号
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取发牌类型编号
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置发牌状态
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取发牌状态
	 */
	public java.lang.String getState() {
		return state;
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
	 * 设置业务名称
	 */
	public void setBussinessName(java.lang.String bussinessName) {
		this.bussinessName = bussinessName;
	}

	/**
	 * 获取业务名称
	 */
	public java.lang.String getBussinessName() {
		return bussinessName;
	}

	/**
	 * 设置时间
	 */
	public void setBussinessTime(java.sql.Date bussinessTime) {
		this.bussinessTime = bussinessTime;
	}

	/**
	 * 获取时间
	 */
	public java.sql.Date getBussinessTime() {
		return bussinessTime;
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
	 * 设置发牌结果名称
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取发牌结果名称
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
	}

	/**
	 * 设置开始时间
	 */
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取开始时间
	 */
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置结束时间
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取结束时间
	 */
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置结构id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取结构id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置结构名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取结构名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置业务类型名称
	 */
	public void setBussinessTypeName(java.lang.String bussinessTypeName) {
		this.bussinessTypeName = bussinessTypeName;
	}

	/**
	 * 获取业务类型名称
	 */
	public java.lang.String getBussinessTypeName() {
		return bussinessTypeName;
	}

	/**
	 * 设置发牌状态名称
	 */
	public void setStateName(java.lang.String stateName) {
		this.stateName = stateName;
	}

	/**
	 * 获取发牌状态名称
	 */
	public java.lang.String getStateName() {
		return stateName;
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
	 * 设置监察开始时间
	 */
	public void setJcBeginDate(java.sql.Date jcBeginDate) {
		this.jcBeginDate = jcBeginDate;
	}

	/**
	 * 获取监察开始时间
	 */
	public java.sql.Date getJcBeginDate() {
		return jcBeginDate;
	}

	/**
	 * 设置监察结束时间
	 */
	public void setJcEndDate(java.sql.Date jcEndDate) {
		this.jcEndDate = jcEndDate;
	}

	/**
	 * 获取监察结束时间
	 */
	public java.sql.Date getJcEndDate() {
		return jcEndDate;
	}

	/**
	 * 设置办件办理状态
	 */
	public void setBjState(java.lang.String bjState) {
		this.bjState = bjState;
	}

	/**
	 * 获取办件办理状态
	 */
	public java.lang.String getBjState() {
		return bjState;
	}

	/**
	 * 设置递归机构
	 */
	public void setTemporgid(java.lang.String temporgid) {
		this.temporgid = temporgid;
	}

	/**
	 * 获取递归机构
	 */
	public java.lang.String getTemporgid() {
		return temporgid;
	}

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
	 * 设置回复时间或办结时间
	 */
	public void setReplyTime(java.sql.Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * 获取回复时间或办结时间
	 */
	public java.sql.Date getReplyTime() {
		return replyTime;
	}

	/**
	 * 设置是否有效
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置承诺时限
	 */
	public void setPromiseLimit(java.lang.String promiseLimit) {
		this.promiseLimit = promiseLimit;
	}

	/**
	 * 获取承诺时限
	 */
	public java.lang.String getPromiseLimit() {
		return promiseLimit;
	}

	/**
	 * 设置法定时限
	 */
	public void setProcessLimit(java.lang.String processLimit) {
		this.processLimit = processLimit;
	}

	/**
	 * 获取法定时限
	 */
	public java.lang.String getProcessLimit() {
		return processLimit;
	}

	/**
	 * 设置承诺所剩时间
	 */
	public void setCommitment(java.lang.String commitment) {
		this.commitment = commitment;
	}

	/**
	 * 获取承诺所剩时间
	 */
	public java.lang.String getCommitment() {
		return commitment;
	}

	/**
	 * 设置法定所剩时间
	 */
	public void setLegal(java.lang.String legal) {
		this.legal = legal;
	}

	/**
	 * 获取法定所剩时间
	 */
	public java.lang.String getLegal() {
		return legal;
	}

	/**
	 * 设置所用时长
	 */
	public void setTimeUse(java.lang.Integer timeUse) {
		this.timeUse = timeUse;
	}

	/**
	 * 获取所用时长
	 */
	public java.lang.Integer getTimeUse() {
		return timeUse;
	}

	/**
	 * 设置申请者姓名
	 */
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	/**
	 * 获取申请者姓名
	 */
	public java.lang.String getApplyName() {
		return applyName;
	}

	/**
	 * 设置实例编号
	 */
	public void setInstanceCode(java.lang.String instanceCode) {
		this.instanceCode = instanceCode;
	}

	/**
	 * 获取实例编号
	 */
	public java.lang.String getInstanceCode() {
		return instanceCode;
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
	 * 设置事项id
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取事项id
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置数据交换时间
	 */
	public void setEtlTime(java.lang.String etlTime) {
		this.etlTime = etlTime;
	}

	/**
	 * 获取数据交换时间
	 */
	public java.lang.String getEtlTime() {
		return etlTime;
	}
}
