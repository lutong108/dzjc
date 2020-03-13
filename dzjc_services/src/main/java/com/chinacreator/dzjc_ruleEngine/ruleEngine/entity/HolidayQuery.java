package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 节假日查询
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.HolidayQuery", table = "WF_HOLIDAY", ds = "acceptdata", cache = false)
public class HolidayQuery {
	/**
	 *保存假日日期
	 */
	@Column(id = "holiday", datatype = "string10")
	private java.lang.String holiday;

	/**
	 *保存假日所属的年分，保存4位
	 */
	@Column(id = "yholiday", datatype = "string5")
	private java.lang.String yholiday;

	/**
	 *保存节假日所属的月份，保存2位
	 */
	@Column(id = "mholiday", datatype = "string5")
	private java.lang.String mholiday;

	/**
	 *租户ID
	 */
	@Column(id = "tenant_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String tenantId;

	/**
	 *
	 */
	@Column(id = "startDate", datatype = "string64")
	private java.lang.String startDate;

	@Column(id = "endDate", datatype = "string64")
	private java.lang.String endDate;

	/**
	 * 设置保存假日日期
	 */
	public void setHoliday(java.lang.String holiday) {
		this.holiday = holiday;
	}

	/**
	 * 获取保存假日日期
	 */
	public java.lang.String getHoliday() {
		return holiday;
	}

	/**
	 * 设置保存假日所属的年分，保存4位
	 */
	public void setYholiday(java.lang.String yholiday) {
		this.yholiday = yholiday;
	}

	/**
	 * 获取保存假日所属的年分，保存4位
	 */
	public java.lang.String getYholiday() {
		return yholiday;
	}

	/**
	 * 设置保存节假日所属的月份，保存2位
	 */
	public void setMholiday(java.lang.String mholiday) {
		this.mholiday = mholiday;
	}

	/**
	 * 获取保存节假日所属的月份，保存2位
	 */
	public java.lang.String getMholiday() {
		return mholiday;
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
	 * 设置
	 */
	public void setStartDate(java.lang.String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取
	 */
	public java.lang.String getStartDate() {
		return startDate;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setEndDate(java.lang.String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getEndDate() {
		return endDate;
	}
}
