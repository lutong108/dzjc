package com.chinacreator.dzjc_union;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 并联办件详情
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_union.UnionInstanceDetail", table = "VA_TA_UNION_INSTANCE_DETAIL", ds = "acceptdata", cache = false)
public class UnionInstanceDetail {
	/**
	 *项目名称
	 */
	@Column(id = "project_name", datatype = "string1024")
	private java.lang.String projectName;

	/**
	 *主事项
	 */
	@Column(id = "mainapprove_name", datatype = "string256")
	private java.lang.String mainapproveName;

	/**
	 *处理阶段
	 */
	@Column(id = "stage_alias", datatype = "string256")
	private java.lang.String stageAlias;

	/**
	 *办理事项
	 */
	@Column(id = "approve_name", datatype = "string1024")
	private java.lang.String approveName;

	/**
	 *办件id
	 */
	@Column(id = "instance_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *受理时间
	 */
	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	/**
	 *结束时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *办件状态
	 */
	@Column(id = "project_state", datatype = "string5")
	private java.lang.String projectState;

	/**
	 *受理事项id
	 */
	@Column(id = "approve_id", datatype = "string64")
	private java.lang.String approveId;

	/**
	 *完成天数
	 */
	@Column(id = "completed_time", datatype = "bigdouble")
	private java.lang.Double completedTime;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 *法定时限
	 */
	@Column(id = "approve_limit", datatype = "bigdouble")
	private java.lang.Double approveLimit;

	/**
	 *承诺时限
	 */
	@Column(id = "commitment_limit", datatype = "bigdouble")
	private java.lang.Double commitmentLimit;

	/**
	 *阶段id
	 */
	@Column(id = "stage_id", datatype = "string64")
	private java.lang.String stageId;

	/**
	 *主事项实例id
	 */
	@Column(id = "main_instance_id", datatype = "string64")
	private java.lang.String mainInstanceId;

	/**
	 *项目id
	 */
	@Column(id = "project_id", datatype = "string64")
	private java.lang.String projectId;

	/**
	 *主事项id
	 */
	@Column(id = "mainapprove_id", datatype = "string64")
	private java.lang.String mainapproveId;

	/**
	 *监察结果
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *发牌时间
	 */
	@Column(id = "supervise_time", datatype = "char10")
	private java.lang.String superviseTime;

	/**
	 *查询开始时间
	 */
	@Column(id = "begin_date", datatype = "char10")
	private java.lang.String beginDate;

	/**
	 *查询结束时间
	 */
	@Column(id = "end_date", datatype = "char10")
	private java.lang.String endDate;

	/**
	 *提交时间
	 */
	@Column(id = "submit_time", datatype = "date")
	private java.sql.Date submitTime;

	/**
	 * 设置项目名称
	 */
	public void setProjectName(java.lang.String projectName) {
		this.projectName = projectName;
	}

	/**
	 * 获取项目名称
	 */
	public java.lang.String getProjectName() {
		return projectName;
	}

	/**
	 * 设置主事项
	 */
	public void setMainapproveName(java.lang.String mainapproveName) {
		this.mainapproveName = mainapproveName;
	}

	/**
	 * 获取主事项
	 */
	public java.lang.String getMainapproveName() {
		return mainapproveName;
	}

	/**
	 * 设置处理阶段
	 */
	public void setStageAlias(java.lang.String stageAlias) {
		this.stageAlias = stageAlias;
	}

	/**
	 * 获取处理阶段
	 */
	public java.lang.String getStageAlias() {
		return stageAlias;
	}

	/**
	 * 设置办理事项
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取办理事项
	 */
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	 * 设置办件id
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取办件id
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
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
	 * 设置结束时间
	 */
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取结束时间
	 */
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置办件状态
	 */
	public void setProjectState(java.lang.String projectState) {
		this.projectState = projectState;
	}

	/**
	 * 获取办件状态
	 */
	public java.lang.String getProjectState() {
		return projectState;
	}

	/**
	 * 设置受理事项id
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取受理事项id
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置完成天数
	 */
	public void setCompletedTime(java.lang.Double completedTime) {
		this.completedTime = completedTime;
	}

	/**
	 * 获取完成天数
	 */
	public java.lang.Double getCompletedTime() {
		return completedTime;
	}

	/**
	 * 设置机构id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构id
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
	 * 设置阶段id
	 */
	public void setStageId(java.lang.String stageId) {
		this.stageId = stageId;
	}

	/**
	 * 获取阶段id
	 */
	public java.lang.String getStageId() {
		return stageId;
	}

	/**
	 * 设置主事项实例id
	 */
	public void setMainInstanceId(java.lang.String mainInstanceId) {
		this.mainInstanceId = mainInstanceId;
	}

	/**
	 * 获取主事项实例id
	 */
	public java.lang.String getMainInstanceId() {
		return mainInstanceId;
	}

	/**
	 * 设置项目id
	 */
	public void setProjectId(java.lang.String projectId) {
		this.projectId = projectId;
	}

	/**
	 * 获取项目id
	 */
	public java.lang.String getProjectId() {
		return projectId;
	}

	/**
	 * 设置主事项id
	 */
	public void setMainapproveId(java.lang.String mainapproveId) {
		this.mainapproveId = mainapproveId;
	}

	/**
	 * 获取主事项id
	 */
	public java.lang.String getMainapproveId() {
		return mainapproveId;
	}

	/**
	 * 设置监察结果
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置发牌时间
	 */
	public void setSuperviseTime(java.lang.String superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取发牌时间
	 */
	public java.lang.String getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置查询开始时间
	 */
	public void setBeginDate(java.lang.String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取查询开始时间
	 */
	public java.lang.String getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置查询结束时间
	 */
	public void setEndDate(java.lang.String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取查询结束时间
	 */
	public java.lang.String getEndDate() {
		return endDate;
	}

	/**
	 * 设置提交时间
	 */
	public void setSubmitTime(java.sql.Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * 获取提交时间
	 */
	public java.sql.Date getSubmitTime() {
		return submitTime;
	}
}
