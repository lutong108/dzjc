

package com.chinacreator.dzjc_union;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 项目阶段审核
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_union.ProjectStageApprove",table="VA_TA_UNION_STAGE_APPROVE",ds="acceptdata",cache=false)
public class ProjectStageApprove {
				 /**
	 *主键
	 */
			@Column(id="mid", type=ColumnType.uuid , datatype="string64"   )
		private java.lang.String mid;
	
			 /**
	 *办件实例id
	 */
			@Column(id="main_instance_id" , datatype="string64"   )
		private java.lang.String mainInstanceId;
	
			 /**
	 *项目id
	 */
			@Column(id="project_id" , datatype="string64"   )
		private java.lang.String projectId;
	
			 /**
	 *主事项id
	 */
			@Column(id="mainapprove_id" , datatype="string64"   )
		private java.lang.String mainapproveId;
	
			 /**
	 *阶段id
	 */
			@Column(id="stage_id" , datatype="string64"   )
		private java.lang.String stageId;
	
			 /**
	 *阶段名称
	 */
			@Column(id="stage_name" , datatype="string256"   )
		private java.lang.String stageName;
	
			 /**
	 *阶段别名
	 */
			@Column(id="stage_alias" , datatype="string256"   )
		private java.lang.String stageAlias;
	
			 /**
	 *事项id
	 */
			@Column(id="approve_id" , datatype="string64"   )
		private java.lang.String approveId;
	
			 /**
	 *事项名称
	 */
			@Column(id="approve_name" , datatype="string1024"   )
		private java.lang.String approveName;
	
			 /**
	 *受理机构
	 */
			@Column(id="org_name" , datatype="string256"   )
		private java.lang.String orgName;
	
			 /**
	 *法定时限
	 */
			@Column(id="approve_limit" , datatype="bigdouble"   )
		private java.lang.Double approveLimit;
	
			 /**
	 *承若时限
	 */
			@Column(id="commitment_limit" , datatype="bigdouble"   )
		private java.lang.Double commitmentLimit;
	
			 /**
	 *阶段排序
	 */
			@Column(id="stage_order" , datatype="bigdouble"   )
		private java.lang.Double stageOrder;
	
			 /**
	 *子事项排序
	 */
			@Column(id="uapprove_order" , datatype="bigdouble"   )
		private java.lang.Double uapproveOrder;
	
			 /**
	 *办件状态
	 */
			@Column(id="project_state" , datatype="string10"   )
		private java.lang.String projectState;
	
			 /**
	 *办件id
	 */
			@Column(id="instance_id" , datatype="string64"   )
		private java.lang.String instanceId;
	
			 /**
	 *监察结果
	 */
			@Column(id="supervise_result_no" , datatype="string20"   )
		private java.lang.String superviseResultNo;
	
			 /**
	 *受理人
	 */
			@Column(id="accept_name" , datatype="string64"   )
		private java.lang.String acceptName;
	
			 /**
	 *受理时间
	 */
			@Column(id="accept_time" , datatype="string20"   )
		private java.lang.String acceptTime;
			
	@Column(id="notaccept_reason" , datatype="string2048"   )
	private	String notacceptReason;
	
	private String acceptSupResult;
	
	public String getAcceptSupResult() {
		return acceptSupResult;
	}

	public void setAcceptSupResult(String acceptSupResult) {
		this.acceptSupResult = acceptSupResult;
	}
	
	public String getNotacceptReason() {
		return notacceptReason;
	}

	public void setNotacceptReason(String notacceptReason) {
		this.notacceptReason = notacceptReason;
	}

		/**
	* 设置主键
	*/
	public void setMid(java.lang.String mid) {
		this.mid = mid;
	}
	
	/**
	* 获取主键
	*/
	public java.lang.String getMid() {
		return mid;
	}
		/**
	* 设置办件实例id
	*/
	public void setMainInstanceId(java.lang.String mainInstanceId) {
		this.mainInstanceId = mainInstanceId;
	}
	
	/**
	* 获取办件实例id
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
	* 设置阶段名称
	*/
	public void setStageName(java.lang.String stageName) {
		this.stageName = stageName;
	}
	
	/**
	* 获取阶段名称
	*/
	public java.lang.String getStageName() {
		return stageName;
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
	* 设置事项id
	*/
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}
	
	/**
	* 获取事项id
	*/
	public java.lang.String getApproveId() {
		return approveId;
	}
		/**
	* 设置事项名称
	*/
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}
	
	/**
	* 获取事项名称
	*/
	public java.lang.String getApproveName() {
		return approveName;
	}
		/**
	* 设置受理机构
	*/
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}
	
	/**
	* 获取受理机构
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
	* 设置承若时限
	*/
	public void setCommitmentLimit(java.lang.Double commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}
	
	/**
	* 获取承若时限
	*/
	public java.lang.Double getCommitmentLimit() {
		return commitmentLimit;
	}
		/**
	* 设置阶段排序
	*/
	public void setStageOrder(java.lang.Double stageOrder) {
		this.stageOrder = stageOrder;
	}
	
	/**
	* 获取阶段排序
	*/
	public java.lang.Double getStageOrder() {
		return stageOrder;
	}
		/**
	* 设置子事项排序
	*/
	public void setUapproveOrder(java.lang.Double uapproveOrder) {
		this.uapproveOrder = uapproveOrder;
	}
	
	/**
	* 获取子事项排序
	*/
	public java.lang.Double getUapproveOrder() {
		return uapproveOrder;
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
		/**
	* 设置受理时间
	*/
	public void setAcceptTime(java.lang.String acceptTime) {
		this.acceptTime = acceptTime;
	}
	
	/**
	* 获取受理时间
	*/
	public java.lang.String getAcceptTime() {
		return acceptTime;
	}
		}