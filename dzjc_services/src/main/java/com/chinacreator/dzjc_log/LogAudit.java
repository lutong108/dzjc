package com.chinacreator.dzjc_log;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 日志审计
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_log.logAudit", table = "TA_JC_LOG_AUDIT", ds = "acceptdata", cache = false)
public class LogAudit {
	/**
	 *主键ID
	 */
	@Column(id = "log_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String logId;

	/**
	 *系统名称（xzxnjd:行政效能监督，ywsl:受理系统，ywsp:业务审批，blsp:并联审批）
	 */
	@Column(id = "sys_name", datatype = "string20")
	private java.lang.String sysName;

	/**
	 *模块名称
	 */
	@Column(id = "module_name", datatype = "string128")
	private java.lang.String moduleName;

	/**
	 *方法名称
	 */
	@Column(id = "method_name", datatype = "string128")
	private java.lang.String methodName;

	/**
	 *操作类型(1:新增,2:编辑,3:删除,4:其他)
	 */
	@Column(id = "op_type", datatype = "string5")
	private java.lang.String opType;

	/**
	 *操作时间
	 */
	@Column(id = "op_time", datatype = "date")
	private java.sql.Date opTime;

	/**
	 *操作内容
	 */
	@Column(id = "op_content", datatype = "string256")
	private java.lang.String opContent;

	/**
	 *操作人ID
	 */
	@Column(id = "user_id", datatype = "string64")
	private java.lang.String userId;

	/**
	 *操作人名称
	 */
	@Column(id = "user_name", datatype = "string64")
	private java.lang.String userName;

	/**
	 *业务标识
	 */
	@Column(id = "business_key", datatype = "string128")
	private java.lang.String businessKey;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string128")
	private java.lang.String remark;

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
	 * 设置主键ID
	 */
	public void setLogId(java.lang.String logId) {
		this.logId = logId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getLogId() {
		return logId;
	}

	/**
	 * 设置系统名称（xzxnjd:行政效能监督，ywsl:受理系统，ywsp:业务审批，blsp:并联审批）
	 */
	public void setSysName(java.lang.String sysName) {
		this.sysName = sysName;
	}

	/**
	 * 获取系统名称（xzxnjd:行政效能监督，ywsl:受理系统，ywsp:业务审批，blsp:并联审批）
	 */
	public java.lang.String getSysName() {
		return sysName;
	}

	/**
	 * 设置模块名称
	 */
	public void setModuleName(java.lang.String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 获取模块名称
	 */
	public java.lang.String getModuleName() {
		return moduleName;
	}

	/**
	 * 设置方法名称
	 */
	public void setMethodName(java.lang.String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 获取方法名称
	 */
	public java.lang.String getMethodName() {
		return methodName;
	}

	/**
	 * 设置操作类型(1:新增,2:编辑,3:删除,4:其他)
	 */
	public void setOpType(java.lang.String opType) {
		this.opType = opType;
	}

	/**
	 * 获取操作类型(1:新增,2:编辑,3:删除,4:其他)
	 */
	public java.lang.String getOpType() {
		return opType;
	}

	/**
	 * 设置操作时间
	 */
	public void setOpTime(java.sql.Date opTime) {
		this.opTime = opTime;
	}

	/**
	 * 获取操作时间
	 */
	public java.sql.Date getOpTime() {
		return opTime;
	}

	/**
	 * 设置操作内容
	 */
	public void setOpContent(java.lang.String opContent) {
		this.opContent = opContent;
	}

	/**
	 * 获取操作内容
	 */
	public java.lang.String getOpContent() {
		return opContent;
	}

	/**
	 * 设置操作人ID
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 获取操作人ID
	 */
	public java.lang.String getUserId() {
		return userId;
	}

	/**
	 * 设置操作人名称
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * 获取操作人名称
	 */
	public java.lang.String getUserName() {
		return userName;
	}

	/**
	 * 设置业务标识
	 */
	public void setBusinessKey(java.lang.String businessKey) {
		this.businessKey = businessKey;
	}

	/**
	 * 获取业务标识
	 */
	public java.lang.String getBusinessKey() {
		return businessKey;
	}

	/**
	 * 设置备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getRemark() {
		return remark;
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
}
