package com.chinacreator.dzjc_tongji;

/**@Title	: 统计条件Model
 * @Author	: zouhailin
 * @Date	: 2018-8-16
 */
public class ParamEntity {
	private String orgId;
	private java.sql.Date beginDate;
	private java.sql.Date endDate;
	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public java.sql.Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	
	
}
