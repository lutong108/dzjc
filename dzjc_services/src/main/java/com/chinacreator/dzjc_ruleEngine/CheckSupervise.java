package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 发牌巡检
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.CheckSupervise", table = "VA_JC_CHECK_SUPERVISE", ds = "acceptdata", cache = false)
public class CheckSupervise {
	/**
	 *发牌id
	 */
	@Column(id = "supervise_info_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String superviseInfoId;

	/**
	 *业务实例id
	 */
	@Column(id = "business_id", datatype = "string1024")
	private java.lang.String businessId;

	/**
	 *监察类型代码
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *监察结果代码
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *受理时间
	 */
	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	/**
	 *办结时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *承诺时限
	 */
	@Column(id = "commitment_limit", datatype = "double")
	private java.lang.Double commitmentLimit;

	/**
	 *法定时限
	 */
	@Column(id = "approve_limit", datatype = "double")
	private java.lang.Double approveLimit;

	/**
	 *监察规则id
	 */
	@Column(id = "rule_id", datatype = "string64")
	private java.lang.String ruleId;

	/**
	 *规则版本
	 */
	@Column(id = "rule_version", datatype = "string64")
	private java.lang.String ruleVersion;

	/**
	 *监察规则天数
	 */
	@Column(id = "cfg_value", datatype = "string64")
	private java.lang.String cfgValue;

	/**
	 *监察规则比较符
	 */
	@Column(id = "compa", datatype = "string20")
	private java.lang.String compa;

	/**
	 *所用天数
	 */
	@Column(id = "use_time", datatype = "double")
	private java.lang.Double useTime;

	/**
	 *超期天数
	 */
	@Column(id = "out_days", datatype = "double")
	private java.lang.Double outDays;

	/**
	 *工作时间拼接的字符串，以##拼接
	 */
	@Column(id = "work_time", datatype = "string128")
	private java.lang.String workTime;

	/**
	 *节假日天数
	 */
	@Column(id = "holiday_count", datatype = "int")
	private java.lang.Integer holidayCount;

	/**
	 * 设置发牌id
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取发牌id
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置业务实例id
	 */
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取业务实例id
	 */
	public java.lang.String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置监察类型代码
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取监察类型代码
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置监察结果代码
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果代码
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
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
	 * 设置承诺时限
	 */
	public void setCommitmentLimit(java.lang.Double commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}

	/**
	 * 获取承诺时限
	 */
	public java.lang.Double getCommitmentLimit() {
		return commitmentLimit;
	}

	/**
	 * 设置法定时限
	 */
	public void setApproveLimit(java.lang.Double approveLimit) {
		this.approveLimit = approveLimit;
	}

	/**
	 * 获取法定时限
	 */
	public java.lang.Double getApproveLimit() {
		return approveLimit;
	}

	/**
	 * 设置监察规则id
	 */
	public void setRuleId(java.lang.String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 获取监察规则id
	 */
	public java.lang.String getRuleId() {
		return ruleId;
	}

	/**
	 * 设置规则版本
	 */
	public void setRuleVersion(java.lang.String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}

	/**
	 * 获取规则版本
	 */
	public java.lang.String getRuleVersion() {
		return ruleVersion;
	}

	/**
	 * 设置监察规则天数
	 */
	public void setCfgValue(java.lang.String cfgValue) {
		this.cfgValue = cfgValue;
	}

	/**
	 * 获取监察规则天数
	 */
	public java.lang.String getCfgValue() {
		return cfgValue;
	}

	/**
	 * 设置监察规则比较符
	 */
	public void setCompa(java.lang.String compa) {
		this.compa = compa;
	}

	/**
	 * 获取监察规则比较符
	 */
	public java.lang.String getCompa() {
		return compa;
	}

	/**
	 * 设置所用天数
	 */
	public void setUseTime(java.lang.Double useTime) {
		this.useTime = useTime;
	}

	/**
	 * 获取所用天数
	 */
	public java.lang.Double getUseTime() {
		return useTime;
	}

	/**
	 * 设置超期天数
	 */
	public void setOutDays(java.lang.Double outDays) {
		this.outDays = outDays;
	}

	/**
	 * 获取超期天数
	 */
	public java.lang.Double getOutDays() {
		return outDays;
	}

	/**
	 * 设置工作时间拼接的字符串，以##拼接
	 */
	public void setWorkTime(java.lang.String workTime) {
		this.workTime = workTime;
	}

	/**
	 * 获取工作时间拼接的字符串，以##拼接
	 */
	public java.lang.String getWorkTime() {
		return workTime;
	}

	/**
	 * 设置节假日天数
	 */
	public void setHolidayCount(java.lang.Integer holidayCount) {
		this.holidayCount = holidayCount;
	}

	/**
	 * 获取节假日天数
	 */
	public java.lang.Integer getHolidayCount() {
		return holidayCount;
	}
}
