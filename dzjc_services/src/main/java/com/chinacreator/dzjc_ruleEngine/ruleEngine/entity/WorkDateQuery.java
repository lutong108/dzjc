package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 工作时间查询
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.WorkDateQuery", table = "WF_WORKDATE", ds = "acceptdata", cache = false)
public class WorkDateQuery {
	/**
	 *起始日期
	 */
	@Column(id = "begin_date", datatype = "string20")
	private java.lang.String beginDate;

	/**
	 *结束日期
	 */
	@Column(id = "end_date", datatype = "string20")
	private java.lang.String endDate;

	/**
	 *上午班结束时间
	 */
	@Column(id = "am_end_time", datatype = "string10")
	private java.lang.String amEndTime;

	/**
	 *主键ID
	 */
	@Column(id = "work_id", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String workId;

	/**
	 *下午班结束时间
	 */
	@Column(id = "pm_end_time", datatype = "string10")
	private java.lang.String pmEndTime;

	/**
	 *上午班开始时间
	 */
	@Column(id = "am_begin_time", datatype = "string10")
	private java.lang.String amBeginTime;

	/**
	 *下午班开始时间
	 */
	@Column(id = "pm_begin_time", datatype = "string10")
	private java.lang.String pmBeginTime;

	/**
	 *备注说明
	 */
	@Column(id = "remark", datatype = "string1024")
	private java.lang.String remark;

	/**
	 *最后修改时间
	 */
	@Column(id = "last_modify_time", datatype = "date")
	private java.sql.Date lastModifyTime;

	/**
	 *租户ID
	 */
	@Column(id = "tenant_id", datatype = "string64")
	private java.lang.String tenantId;

	@Column(id = "query_date", datatype = "string32")
	private java.lang.String queryDate;

	/**
	 * 设置起始日期
	 */
	public void setBeginDate(java.lang.String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取起始日期
	 */
	public java.lang.String getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置结束日期
	 */
	public void setEndDate(java.lang.String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取结束日期
	 */
	public java.lang.String getEndDate() {
		return endDate;
	}

	/**
	 * 设置上午班结束时间
	 */
	public void setAmEndTime(java.lang.String amEndTime) {
		this.amEndTime = amEndTime;
	}

	/**
	 * 获取上午班结束时间
	 */
	public java.lang.String getAmEndTime() {
		return amEndTime;
	}

	/**
	 * 设置主键ID
	 */
	public void setWorkId(java.lang.String workId) {
		this.workId = workId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getWorkId() {
		return workId;
	}

	/**
	 * 设置下午班结束时间
	 */
	public void setPmEndTime(java.lang.String pmEndTime) {
		this.pmEndTime = pmEndTime;
	}

	/**
	 * 获取下午班结束时间
	 */
	public java.lang.String getPmEndTime() {
		return pmEndTime;
	}

	/**
	 * 设置上午班开始时间
	 */
	public void setAmBeginTime(java.lang.String amBeginTime) {
		this.amBeginTime = amBeginTime;
	}

	/**
	 * 获取上午班开始时间
	 */
	public java.lang.String getAmBeginTime() {
		return amBeginTime;
	}

	/**
	 * 设置下午班开始时间
	 */
	public void setPmBeginTime(java.lang.String pmBeginTime) {
		this.pmBeginTime = pmBeginTime;
	}

	/**
	 * 获取下午班开始时间
	 */
	public java.lang.String getPmBeginTime() {
		return pmBeginTime;
	}

	/**
	 * 设置备注说明
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取备注说明
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * 设置最后修改时间
	 */
	public void setLastModifyTime(java.sql.Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	/**
	 * 获取最后修改时间
	 */
	public java.sql.Date getLastModifyTime() {
		return lastModifyTime;
	}

	/**
	 * 设置租户ID
	 */
	public void setTenantId(java.lang.String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 获取租户ID
	 */
	public java.lang.String getTenantId() {
		return tenantId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setQueryDate(java.lang.String queryDate) {
		this.queryDate = queryDate;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getQueryDate() {
		return queryDate;
	}
}
