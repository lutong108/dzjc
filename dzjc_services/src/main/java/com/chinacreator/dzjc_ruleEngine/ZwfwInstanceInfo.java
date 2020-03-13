package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 政务服务办件信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ZwfwInstanceInfo", table = "VA_TA_JC_ZWFW_INSTANCE", ds = "acceptdata", cache = false)
public class ZwfwInstanceInfo {
	/**
	 *办件Id
	 */
	@Column(id = "instance_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *监察结果
	 */
	@Column(id = "supervise_result_name", datatype = "string32")
	private java.lang.String superviseResultName;

	/**
	 *监察类型
	 */
	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	/**
	 *单位名称
	 */
	@Column(id = "area_org_name", datatype = "string1024")
	private java.lang.String areaOrgName;

	/**
	 *办件名称
	 */
	@Column(id = "instance_name", datatype = "string512")
	private java.lang.String instanceName;

	/**
	 *办件编号
	 */
	@Column(id = "instance_code", datatype = "string64")
	private java.lang.String instanceCode;

	/**
	 *受理时间
	 */
	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	/**
	 *办结时间
	 */
	@Column(id = "finish_time", datatype = "date")
	private java.sql.Date finishTime;

	/**
	 *办理状态
	 */
	@Column(id = "processing_state", datatype = "string5")
	private java.lang.String processingState;

	/**
	 *业务类别
	 */
	@Column(id = "approve_type_code", datatype = "string5")
	private java.lang.String approveTypeCode;

	/**
	 *监察类型编号
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *监察结果编号
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *发牌状态
	 */
	@Column(id = "state", datatype = "string20")
	private java.lang.String state;

	/**
	 *机构Id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string1024")
	private java.lang.String approveName;

	/**
	 *行政区码
	 */
	@Column(id = "areaCode", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *受理开始时间
	 */
	@Column(id = "acceptBeginDate", datatype = "date")
	private java.sql.Date acceptBeginDate;

	/**
	 *受理结束时间
	 */
	@Column(id = "acceptEndDate", datatype = "date")
	private java.sql.Date acceptEndDate;

	/**
	 *办结开始时间
	 */
	@Column(id = "beginDate", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *办结结束时间
	 */
	@Column(id = "endDate", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *事项类型
	 */
	@Column(id = "approve_type_code_name", datatype = "string64")
	private java.lang.String approveTypeCodeName;

	/**
	 *事项id
	 */
	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	/**
	 *数据交换时间
	 */
	@Column(id = "etl_time", datatype = "date")
	private java.sql.Date etlTime;

	/**
	 *机构编码
	 */
	@Column(id = "org_code", datatype = "string64")
	private java.lang.String orgCode;

	/**
	 * 设置办件Id
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取办件Id
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置监察结果
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取监察结果
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
	}

	/**
	 * 设置监察类型
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取监察类型
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}

	/**
	 * 设置单位名称
	 */
	public void setAreaOrgName(java.lang.String areaOrgName) {
		this.areaOrgName = areaOrgName;
	}

	/**
	 * 获取单位名称
	 */
	public java.lang.String getAreaOrgName() {
		return areaOrgName;
	}

	/**
	 * 设置办件名称
	 */
	public void setInstanceName(java.lang.String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * 获取办件名称
	 */
	public java.lang.String getInstanceName() {
		return instanceName;
	}

	/**
	 * 设置办件编号
	 */
	public void setInstanceCode(java.lang.String instanceCode) {
		this.instanceCode = instanceCode;
	}

	/**
	 * 获取办件编号
	 */
	public java.lang.String getInstanceCode() {
		return instanceCode;
	}

	/**
	 * 设置受理时间
	 */
	public void setAcceptTime(java.sql.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取受理时间
	 */
	public java.sql.Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置办结时间
	 */
	public void setFinishTime(java.sql.Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * 获取办结时间
	 */
	public java.sql.Date getFinishTime() {
		return finishTime;
	}

	/**
	 * 设置办理状态
	 */
	public void setProcessingState(java.lang.String processingState) {
		this.processingState = processingState;
	}

	/**
	 * 获取办理状态
	 */
	public java.lang.String getProcessingState() {
		return processingState;
	}

	/**
	 * 设置业务类别
	 */
	public void setApproveTypeCode(java.lang.String approveTypeCode) {
		this.approveTypeCode = approveTypeCode;
	}

	/**
	 * 获取业务类别
	 */
	public java.lang.String getApproveTypeCode() {
		return approveTypeCode;
	}

	/**
	 * 设置监察类型编号
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取监察类型编号
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置监察结果编号
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果编号
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
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
	 * 设置机构Id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构Id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置事项名称
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取事项名称
	 */
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	 * 设置行政区码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取行政区码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置受理开始时间
	 */
	public void setAcceptBeginDate(java.sql.Date acceptBeginDate) {
		this.acceptBeginDate = acceptBeginDate;
	}

	/**
	 * 获取受理开始时间
	 */
	public java.sql.Date getAcceptBeginDate() {
		return acceptBeginDate;
	}

	/**
	 * 设置受理结束时间
	 */
	public void setAcceptEndDate(java.sql.Date acceptEndDate) {
		this.acceptEndDate = acceptEndDate;
	}

	/**
	 * 获取受理结束时间
	 */
	public java.sql.Date getAcceptEndDate() {
		return acceptEndDate;
	}

	/**
	 * 设置办结开始时间
	 */
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取办结开始时间
	 */
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置办结结束时间
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取办结结束时间
	 */
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置事项类型
	 */
	public void setApproveTypeCodeName(java.lang.String approveTypeCodeName) {
		this.approveTypeCodeName = approveTypeCodeName;
	}

	/**
	 * 获取事项类型
	 */
	public java.lang.String getApproveTypeCodeName() {
		return approveTypeCodeName;
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
	public void setEtlTime(java.sql.Date etlTime) {
		this.etlTime = etlTime;
	}

	/**
	 * 获取数据交换时间
	 */
	public java.sql.Date getEtlTime() {
		return etlTime;
	}

	/**
	 * 设置机构编码
	 */
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取机构编码
	 */
	public java.lang.String getOrgCode() {
		return orgCode;
	}
}
