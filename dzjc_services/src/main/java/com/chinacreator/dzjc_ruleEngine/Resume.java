package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 特别程序结果
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.resume", table = "TA_SP_RESUME", ds = "acceptdata", cache = false)
public class Resume {
	/**
	 *主键ID
	 */
	@Column(id = "resume_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String resumeId;

	/**
	 *流程挂起ID
	 */
	@Column(id = "suspend_id", datatype = "string64")
	private java.lang.String suspendId;

	/**
	 *审批项目实例ID
	 */
	@Column(id = "instance_id", datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *特别程序结果
	 */
	@Column(id = "special_result", datatype = "string2000")
	private java.lang.String specialResult;

	/**
	 *特别程序结束日期
	 */
	@Column(id = "end_date", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *操作用户id
	 */
	@Column(id = "resume_userid", datatype = "string64")
	private java.lang.String resumeUserid;

	/**
	 *用户名称
	 */
	@Column(id = "resume_username", datatype = "string64")
	private java.lang.String resumeUsername;

	/**
	 *备注1
	 */
	@Column(id = "remarka", datatype = "string256")
	private java.lang.String remarka;

	/**
	 *平台实例id
	 */
	@Column(id = "proc_inst_id", datatype = "string64")
	private java.lang.String procInstId;

	/**
	 *最后修改时间
	 */
	@Column(id = "lastmodifiedtime", datatype = "date")
	private java.sql.Date lastmodifiedtime;

	/**
	 *是否已交换(N/Y,默认:N)
	 */
	@Column(id = "is_exchange", datatype = "string5")
	private java.lang.String isExchange;

	/**
	 *是否已交换标志2(N/Y,默认:N)
	 */
	@Column(id = "mod_flag", datatype = "string5")
	private java.lang.String modFlag;

	/**
	 * 设置主键ID
	 */
	public void setResumeId(java.lang.String resumeId) {
		this.resumeId = resumeId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getResumeId() {
		return resumeId;
	}

	/**
	 * 设置流程挂起ID
	 */
	public void setSuspendId(java.lang.String suspendId) {
		this.suspendId = suspendId;
	}

	/**
	 * 获取流程挂起ID
	 */
	public java.lang.String getSuspendId() {
		return suspendId;
	}

	/**
	 * 设置审批项目实例ID
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取审批项目实例ID
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置特别程序结果
	 */
	public void setSpecialResult(java.lang.String specialResult) {
		this.specialResult = specialResult;
	}

	/**
	 * 获取特别程序结果
	 */
	public java.lang.String getSpecialResult() {
		return specialResult;
	}

	/**
	 * 设置特别程序结束日期
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取特别程序结束日期
	 */
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置操作用户id
	 */
	public void setResumeUserid(java.lang.String resumeUserid) {
		this.resumeUserid = resumeUserid;
	}

	/**
	 * 获取操作用户id
	 */
	public java.lang.String getResumeUserid() {
		return resumeUserid;
	}

	/**
	 * 设置用户名称
	 */
	public void setResumeUsername(java.lang.String resumeUsername) {
		this.resumeUsername = resumeUsername;
	}

	/**
	 * 获取用户名称
	 */
	public java.lang.String getResumeUsername() {
		return resumeUsername;
	}

	/**
	 * 设置备注1
	 */
	public void setRemarka(java.lang.String remarka) {
		this.remarka = remarka;
	}

	/**
	 * 获取备注1
	 */
	public java.lang.String getRemarka() {
		return remarka;
	}

	/**
	 * 设置平台实例id
	 */
	public void setProcInstId(java.lang.String procInstId) {
		this.procInstId = procInstId;
	}

	/**
	 * 获取平台实例id
	 */
	public java.lang.String getProcInstId() {
		return procInstId;
	}

	/**
	 * 设置最后修改时间
	 */
	public void setLastmodifiedtime(java.sql.Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}

	/**
	 * 获取最后修改时间
	 */
	public java.sql.Date getLastmodifiedtime() {
		return lastmodifiedtime;
	}

	/**
	 * 设置是否已交换(N/Y,默认:N)
	 */
	public void setIsExchange(java.lang.String isExchange) {
		this.isExchange = isExchange;
	}

	/**
	 * 获取是否已交换(N/Y,默认:N)
	 */
	public java.lang.String getIsExchange() {
		return isExchange;
	}

	/**
	 * 设置是否已交换标志2(N/Y,默认:N)
	 */
	public void setModFlag(java.lang.String modFlag) {
		this.modFlag = modFlag;
	}

	/**
	 * 获取是否已交换标志2(N/Y,默认:N)
	 */
	public java.lang.String getModFlag() {
		return modFlag;
	}
}
