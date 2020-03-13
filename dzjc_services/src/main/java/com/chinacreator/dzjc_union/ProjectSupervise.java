

package com.chinacreator.dzjc_union;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 并联项目监察
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_union.ProjectSupervise",table="VA_TA_UNION_PROJECT_SUPERVISE",ds="acceptdata",cache=false)
public class ProjectSupervise {
				 /**
	 *项目id
	 */
			@Column(id="project_id", type=ColumnType.uuid , datatype="string64"   )
		private java.lang.String projectId;
	
			 /**
	 *项目名称
	 */
			@Column(id="project_name" , datatype="string1024"   )
		private java.lang.String projectName;
	
			 /**
	 *项目最后修改时间
	 */
			@Column(id="project_update_time" , datatype="string20"   )
		private java.lang.String projectUpdateTime;
	
			 /**
	 *模板名称
	 */
			@Column(id="template_name" , datatype="string256"   )
		private java.lang.String templateName;
	
			 /**
	 *模板id
	 */
			@Column(id="template_id" , datatype="string64"   )
		private java.lang.String templateId;
	
			 /**
	 *主事项实例id
	 */
			@Column(id="main_instance_id" , datatype="string64"   )
		private java.lang.String mainInstanceId;
	
			 /**
	 *实例编码
	 */
			@Column(id="instance_code" , datatype="string64"   )
		private java.lang.String instanceCode;
	
			 /**
	 *申请实例名称
	 */
			@Column(id="instance_name" , datatype="string256"   )
		private java.lang.String instanceName;
	
			 /**
	 *受理人
	 */
			@Column(id="accept_name" , datatype="string64"   )
		private java.lang.String acceptName;
	
			 /**
	 *受理时间
	 */
			@Column(id="accept_time" , datatype="string20"   )
		private java.util.Date acceptTime;
	
			 /**
	 *受理人id
	 */
			@Column(id="accept_id" , datatype="string64"   )
		private java.lang.String acceptId;
	
			 /**
	 *申请人id
	 */
			@Column(id="apply_id" , datatype="string64"   )
		private java.lang.String applyId;
	
			 /**
	 *申请人类型
	 */
			@Column(id="apply_type" , datatype="string5"   )
		private java.lang.String applyType;
	
			 /**
	 *申请人名称
	 */
			@Column(id="apply_name" , datatype="string256"   )
		private java.lang.String applyName;
	
			 /**
	 *办件状态
	 */
			@Column(id="instance_state" , datatype="string5"   )
		private java.lang.String instanceState;
	
			 /**
	 *是否阶段受理
	 */
			@Column(id="is_stageaccept" , datatype="string5"   )
		private java.lang.String isStageaccept;
	
			 /**
	 *主事项名称
	 */
			@Column(id="mainapprove_name" , datatype="string256"   )
		private java.lang.String mainapproveName;
	
			 /**
	 *主事项编码
	 */
			@Column(id="mainapprove_code" , datatype="string128"   )
		private java.lang.String mainapproveCode;
	
			 /**
	 *主事项id
	 */
			@Column(id="mainapprove_id" , datatype="string64"   )
		private java.lang.String mainapproveId;
	
			 /**
	 *项目属性
	 */
			@Column(id="project_attr" , datatype="string20"   )
		private java.lang.String projectAttr;
	
			 /**
	 *法定时限
	 */
			@Column(id="approve_limit" , datatype="bigdouble"   )
		private java.lang.Double approveLimit;
	
			 /**
	 *承诺时限
	 */
			@Column(id="commitment_limit" , datatype="bigdouble"   )
		private java.lang.Double commitmentLimit;
	
			 /**
	 *区域编码
	 */
			@Column(id="area_code" , datatype="string64"   )
		private java.lang.String areaCode;
	
			 /**
	 *当前受理阶段
	 */
			@Column(id="stage_name" , datatype="string256"   )
		private java.lang.String stageName;
	
			 /**
	 *项目编码
	 */
			@Column(id="project_code" , datatype="string64"   )
		private java.lang.String projectCode;
	
			 /**
	 *项目总投资
	 */
			@Column(id="total_investment" , datatype="int"   )
		private java.lang.Integer totalInvestment;
	
			 /**
	 *阶段别名
	 */
			@Column(id="stage_alias" , datatype="string64"   )
		private java.lang.String stageAlias;
	
			 /**
	 *监察状态
	 */
			@Column(id="supervise_result_no" , datatype="char1"   )
		private java.lang.String superviseResultNo;
	
			 /**
	 *预警次数
	 */
			@Column(id="yujing_count" , datatype="int"   )
		private java.lang.Integer yujingCount;
	
			 /**
	 *黄牌(张)
	 */
			@Column(id="huangpai_count" , datatype="int"   )
		private java.lang.Integer huangpaiCount;
	
			 /**
	 *红牌(张)
	 */
			@Column(id="hongpai_count" , datatype="int"   )
		private java.lang.Integer hongpaiCount;
	
			 /**
	 *机构id
	 */
			@Column(id="org_id" , datatype="string64"   )
		private java.lang.String orgId;
	
			 /**
	 *机构名称
	 */
			@Column(id="org_name" , datatype="string64"   )
		private java.lang.String orgName;
	
			 /**
	 *开始日期
	 */
			@Column(id="begin_date" , datatype="char10"   )
		private java.util.Date beginDate;
	
			 /**
	 *结束日期
	 */
			@Column(id="end_date" , datatype="char10"   )
		private java.util.Date endDate;
	
	
	private String acceptSupResult;
		
	public String getAcceptSupResult() {
		return acceptSupResult;
	}

	public void setAcceptSupResult(String acceptSupResult) {
		this.acceptSupResult = acceptSupResult;
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
	* 设置项目最后修改时间
	*/
	public void setProjectUpdateTime(java.lang.String projectUpdateTime) {
		this.projectUpdateTime = projectUpdateTime;
	}
	
	/**
	* 获取项目最后修改时间
	*/
	public java.lang.String getProjectUpdateTime() {
		return projectUpdateTime;
	}
		/**
	* 设置模板名称
	*/
	public void setTemplateName(java.lang.String templateName) {
		this.templateName = templateName;
	}
	
	/**
	* 获取模板名称
	*/
	public java.lang.String getTemplateName() {
		return templateName;
	}
		/**
	* 设置模板id
	*/
	public void setTemplateId(java.lang.String templateId) {
		this.templateId = templateId;
	}
	
	/**
	* 获取模板id
	*/
	public java.lang.String getTemplateId() {
		return templateId;
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
	* 设置实例编码
	*/
	public void setInstanceCode(java.lang.String instanceCode) {
		this.instanceCode = instanceCode;
	}
	
	/**
	* 获取实例编码
	*/
	public java.lang.String getInstanceCode() {
		return instanceCode;
	}
		/**
	* 设置申请实例名称
	*/
	public void setInstanceName(java.lang.String instanceName) {
		this.instanceName = instanceName;
	}
	
	/**
	* 获取申请实例名称
	*/
	public java.lang.String getInstanceName() {
		return instanceName;
	}
		/**
	* 设置受理人
	*/
	public void setAcceptName(java.lang.String acceptName) {
		this.acceptName = acceptName;
	}
	
	/**
	* 获取受理人
	*/
	public java.lang.String getAcceptName() {
		return acceptName;
	}
	
	
	public java.util.Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(java.util.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

		/**
	* 设置受理人id
	*/
	public void setAcceptId(java.lang.String acceptId) {
		this.acceptId = acceptId;
	}
	
	/**
	* 获取受理人id
	*/
	public java.lang.String getAcceptId() {
		return acceptId;
	}
		/**
	* 设置申请人id
	*/
	public void setApplyId(java.lang.String applyId) {
		this.applyId = applyId;
	}
	
	/**
	* 获取申请人id
	*/
	public java.lang.String getApplyId() {
		return applyId;
	}
		/**
	* 设置申请人类型
	*/
	public void setApplyType(java.lang.String applyType) {
		this.applyType = applyType;
	}
	
	/**
	* 获取申请人类型
	*/
	public java.lang.String getApplyType() {
		return applyType;
	}
		/**
	* 设置申请人名称
	*/
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}
	
	/**
	* 获取申请人名称
	*/
	public java.lang.String getApplyName() {
		return applyName;
	}
		/**
	* 设置办件状态
	*/
	public void setInstanceState(java.lang.String instanceState) {
		this.instanceState = instanceState;
	}
	
	/**
	* 获取办件状态
	*/
	public java.lang.String getInstanceState() {
		return instanceState;
	}
		/**
	* 设置是否阶段受理
	*/
	public void setIsStageaccept(java.lang.String isStageaccept) {
		this.isStageaccept = isStageaccept;
	}
	
	/**
	* 获取是否阶段受理
	*/
	public java.lang.String getIsStageaccept() {
		return isStageaccept;
	}
		/**
	* 设置主事项名称
	*/
	public void setMainapproveName(java.lang.String mainapproveName) {
		this.mainapproveName = mainapproveName;
	}
	
	/**
	* 获取主事项名称
	*/
	public java.lang.String getMainapproveName() {
		return mainapproveName;
	}
		/**
	* 设置主事项编码
	*/
	public void setMainapproveCode(java.lang.String mainapproveCode) {
		this.mainapproveCode = mainapproveCode;
	}
	
	/**
	* 获取主事项编码
	*/
	public java.lang.String getMainapproveCode() {
		return mainapproveCode;
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
	* 设置项目属性
	*/
	public void setProjectAttr(java.lang.String projectAttr) {
		this.projectAttr = projectAttr;
	}
	
	/**
	* 获取项目属性
	*/
	public java.lang.String getProjectAttr() {
		return projectAttr;
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
	* 设置区域编码
	*/
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}
	
	/**
	* 获取区域编码
	*/
	public java.lang.String getAreaCode() {
		return areaCode;
	}
		/**
	* 设置当前受理阶段
	*/
	public void setStageName(java.lang.String stageName) {
		this.stageName = stageName;
	}
	
	/**
	* 获取当前受理阶段
	*/
	public java.lang.String getStageName() {
		return stageName;
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
	* 设置项目总投资
	*/
	public void setTotalInvestment(java.lang.Integer totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	
	/**
	* 获取项目总投资
	*/
	public java.lang.Integer getTotalInvestment() {
		return totalInvestment;
	}
		/**
	* 设置阶段别名
	*/
	public void setStageAlias(java.lang.String stageAlias) {
		this.stageAlias = stageAlias;
	}
	
	/**
	* 获取阶段别名
	*/
	public java.lang.String getStageAlias() {
		return stageAlias;
	}
		/**
	* 设置监察状态
	*/
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}
	
	/**
	* 获取监察状态
	*/
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}
		/**
	* 设置预警次数
	*/
	public void setYujingCount(java.lang.Integer yujingCount) {
		this.yujingCount = yujingCount;
	}
	
	/**
	* 获取预警次数
	*/
	public java.lang.Integer getYujingCount() {
		return yujingCount;
	}
		/**
	* 设置黄牌(张)
	*/
	public void setHuangpaiCount(java.lang.Integer huangpaiCount) {
		this.huangpaiCount = huangpaiCount;
	}
	
	/**
	* 获取黄牌(张)
	*/
	public java.lang.Integer getHuangpaiCount() {
		return huangpaiCount;
	}
		/**
	* 设置红牌(张)
	*/
	public void setHongpaiCount(java.lang.Integer hongpaiCount) {
		this.hongpaiCount = hongpaiCount;
	}
	
	/**
	* 获取红牌(张)
	*/
	public java.lang.Integer getHongpaiCount() {
		return hongpaiCount;
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

	public java.util.Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	
		}