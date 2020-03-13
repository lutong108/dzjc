package com.chinacreator.dzjc_union;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 并联项目信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_union.UnionProject", table = "TA_UNION_PROJECT", ds = "acceptdata", cache = false)
public class UnionProject {
	/**
	 *并联项目ID
	 */
	@Column(id = "project_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String projectId;

	/**
	 *并联项目模板ID
	 */
	@Column(id = "template_id", datatype = "string64")
	private java.lang.String templateId;

	/**
	 *项目名称
	 */
	@Column(id = "project_name", datatype = "string1024")
	private java.lang.String projectName;

	/**
	 *项目编码
	 */
	@Column(id = "project_code", datatype = "string256")
	private java.lang.String projectCode;

	/**
	 *项目建设单位ID
	 */
	@Column(id = "construction_unit_id", datatype = "string64")
	private java.lang.String constructionUnitId;

	/**
	 *项目建设单位名称
	 */
	@Column(id = "construction_unit_name", datatype = "string256")
	private java.lang.String constructionUnitName;

	/**
	 *项目建设规模及主要内容
	 */
	@Column(id = "project_content", datatype = "string2000")
	private java.lang.String projectContent;

	/**
	 *申建理由或依据
	 */
	@Column(id = "project_accord", datatype = "string2000")
	private java.lang.String projectAccord;

	/**
	 *总投资金额（万元）
	 */
	@Column(id = "total_investment", datatype = "bigdouble")
	private java.lang.Double totalInvestment;

	/**
	 *总投资(基建)（万元）
	 */
	@Column(id = "capital_construction_investmen", datatype = "bigdouble")
	private java.lang.Double capitalConstructionInvestmen;

	/**
	 *总投资(其他)（万元）
	 */
	@Column(id = "other_investment", datatype = "bigdouble")
	private java.lang.Double otherInvestment;

	/**
	 *资金来源自筹（万元）
	 */
	@Column(id = "source_oneself", datatype = "bigdouble")
	private java.lang.Double sourceOneself;

	/**
	 *资金来源政府财政
	 */
	@Column(id = "source_org", datatype = "bigdouble")
	private java.lang.Double sourceOrg;

	/**
	 *资金来源上级补助
	 */
	@Column(id = "source_superior", datatype = "bigdouble")
	private java.lang.Double sourceSuperior;

	/**
	 *资金来源贷款
	 */
	@Column(id = "source_loan", datatype = "bigdouble")
	private java.lang.Double sourceLoan;

	/**
	 *资金来源其他
	 */
	@Column(id = "source_other", datatype = "bigdouble")
	private java.lang.Double sourceOther;

	/**
	 *计划开始时间
	 */
	@Column(id = "plan_starttime", datatype = "date")
	private java.sql.Date planStarttime;

	/**
	 *计划结束时间
	 */
	@Column(id = "plan_endtime", datatype = "date")
	private java.sql.Date planEndtime;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	/**
	 *创建人ID
	 */
	@Column(id = "creater_id", datatype = "string64")
	private java.lang.String createrId;

	/**
	 *创建人
	 */
	@Column(id = "create_man", datatype = "string64")
	private java.lang.String createMan;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "date")
	private java.sql.Date createTime;

	/**
	 *更新人ID
	 */
	@Column(id = "update_id", datatype = "string64")
	private java.lang.String updateId;

	/**
	 *更新时间
	 */
	@Column(id = "update_time", datatype = "date")
	private java.sql.Date updateTime;

	/**
	 * 设置并联项目ID
	 */
	public void setProjectId(java.lang.String projectId) {
		this.projectId = projectId;
	}

	/**
	 * 获取并联项目ID
	 */
	public java.lang.String getProjectId() {
		return projectId;
	}

	/**
	 * 设置并联项目模板ID
	 */
	public void setTemplateId(java.lang.String templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取并联项目模板ID
	 */
	public java.lang.String getTemplateId() {
		return templateId;
	}

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
	 * 设置项目编码
	 */
	public void setProjectCode(java.lang.String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * 获取项目编码
	 */
	public java.lang.String getProjectCode() {
		return projectCode;
	}

	/**
	 * 设置项目建设单位ID
	 */
	public void setConstructionUnitId(java.lang.String constructionUnitId) {
		this.constructionUnitId = constructionUnitId;
	}

	/**
	 * 获取项目建设单位ID
	 */
	public java.lang.String getConstructionUnitId() {
		return constructionUnitId;
	}

	/**
	 * 设置项目建设单位名称
	 */
	public void setConstructionUnitName(java.lang.String constructionUnitName) {
		this.constructionUnitName = constructionUnitName;
	}

	/**
	 * 获取项目建设单位名称
	 */
	public java.lang.String getConstructionUnitName() {
		return constructionUnitName;
	}

	/**
	 * 设置项目建设规模及主要内容
	 */
	public void setProjectContent(java.lang.String projectContent) {
		this.projectContent = projectContent;
	}

	/**
	 * 获取项目建设规模及主要内容
	 */
	public java.lang.String getProjectContent() {
		return projectContent;
	}

	/**
	 * 设置申建理由或依据
	 */
	public void setProjectAccord(java.lang.String projectAccord) {
		this.projectAccord = projectAccord;
	}

	/**
	 * 获取申建理由或依据
	 */
	public java.lang.String getProjectAccord() {
		return projectAccord;
	}

	/**
	 * 设置总投资金额（万元）
	 */
	public void setTotalInvestment(java.lang.Double totalInvestment) {
		this.totalInvestment = totalInvestment;
	}

	/**
	 * 获取总投资金额（万元）
	 */
	public java.lang.Double getTotalInvestment() {
		return totalInvestment;
	}

	/**
	 * 设置总投资(基建)（万元）
	 */
	public void setCapitalConstructionInvestmen(
			java.lang.Double capitalConstructionInvestmen) {
		this.capitalConstructionInvestmen = capitalConstructionInvestmen;
	}

	/**
	 * 获取总投资(基建)（万元）
	 */
	public java.lang.Double getCapitalConstructionInvestmen() {
		return capitalConstructionInvestmen;
	}

	/**
	 * 设置总投资(其他)（万元）
	 */
	public void setOtherInvestment(java.lang.Double otherInvestment) {
		this.otherInvestment = otherInvestment;
	}

	/**
	 * 获取总投资(其他)（万元）
	 */
	public java.lang.Double getOtherInvestment() {
		return otherInvestment;
	}

	/**
	 * 设置资金来源自筹（万元）
	 */
	public void setSourceOneself(java.lang.Double sourceOneself) {
		this.sourceOneself = sourceOneself;
	}

	/**
	 * 获取资金来源自筹（万元）
	 */
	public java.lang.Double getSourceOneself() {
		return sourceOneself;
	}

	/**
	 * 设置资金来源政府财政
	 */
	public void setSourceOrg(java.lang.Double sourceOrg) {
		this.sourceOrg = sourceOrg;
	}

	/**
	 * 获取资金来源政府财政
	 */
	public java.lang.Double getSourceOrg() {
		return sourceOrg;
	}

	/**
	 * 设置资金来源上级补助
	 */
	public void setSourceSuperior(java.lang.Double sourceSuperior) {
		this.sourceSuperior = sourceSuperior;
	}

	/**
	 * 获取资金来源上级补助
	 */
	public java.lang.Double getSourceSuperior() {
		return sourceSuperior;
	}

	/**
	 * 设置资金来源贷款
	 */
	public void setSourceLoan(java.lang.Double sourceLoan) {
		this.sourceLoan = sourceLoan;
	}

	/**
	 * 获取资金来源贷款
	 */
	public java.lang.Double getSourceLoan() {
		return sourceLoan;
	}

	/**
	 * 设置资金来源其他
	 */
	public void setSourceOther(java.lang.Double sourceOther) {
		this.sourceOther = sourceOther;
	}

	/**
	 * 获取资金来源其他
	 */
	public java.lang.Double getSourceOther() {
		return sourceOther;
	}

	/**
	 * 设置计划开始时间
	 */
	public void setPlanStarttime(java.sql.Date planStarttime) {
		this.planStarttime = planStarttime;
	}

	/**
	 * 获取计划开始时间
	 */
	public java.sql.Date getPlanStarttime() {
		return planStarttime;
	}

	/**
	 * 设置计划结束时间
	 */
	public void setPlanEndtime(java.sql.Date planEndtime) {
		this.planEndtime = planEndtime;
	}

	/**
	 * 获取计划结束时间
	 */
	public java.sql.Date getPlanEndtime() {
		return planEndtime;
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
	 * 设置创建人ID
	 */
	public void setCreaterId(java.lang.String createrId) {
		this.createrId = createrId;
	}

	/**
	 * 获取创建人ID
	 */
	public java.lang.String getCreaterId() {
		return createrId;
	}

	/**
	 * 设置创建人
	 */
	public void setCreateMan(java.lang.String createMan) {
		this.createMan = createMan;
	}

	/**
	 * 获取创建人
	 */
	public java.lang.String getCreateMan() {
		return createMan;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(java.sql.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建时间
	 */
	public java.sql.Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置更新人ID
	 */
	public void setUpdateId(java.lang.String updateId) {
		this.updateId = updateId;
	}

	/**
	 * 获取更新人ID
	 */
	public java.lang.String getUpdateId() {
		return updateId;
	}

	/**
	 * 设置更新时间
	 */
	public void setUpdateTime(java.sql.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取更新时间
	 */
	public java.sql.Date getUpdateTime() {
		return updateTime;
	}
}
