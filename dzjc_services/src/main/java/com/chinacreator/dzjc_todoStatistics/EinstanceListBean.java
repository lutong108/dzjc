package com.chinacreator.dzjc_todoStatistics;

import java.sql.Date;

public class EinstanceListBean extends EgovernmentBean{
	private String superviseResultNo;	// 监察结果
	private String instanceName;		// 办件名称
	private String approveName;			// 事项名称
	private String projectState;		// 项目状态
	private String orgName;				// 机构名称
	private String applyName;			// 联系人
	private String instanceId;			// 办件ID
	private String superviseInfoId;		// 监察信息ID
	private String orgId;				// 机构ID
	private Date acceptTime;			// 受理时间
	private String startDate;			// 开始日期
	private String endDate;				// 结束日期
	private String typeSort;			// 监察类别
	
	public String getSuperviseResultNo() {
		return superviseResultNo;
	}
	public void setSuperviseResultNo(String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public String getApproveName() {
		return approveName;
	}
	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}
	public String getProjectState() {
		return projectState;
	}
	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getSuperviseInfoId() {
		return superviseInfoId;
	}
	public void setSuperviseInfoId(String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Date getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTypeSort() {
		return typeSort;
	}
	public void setTypeSort(String typeSort) {
		this.typeSort = typeSort;
	}
	
	
}
