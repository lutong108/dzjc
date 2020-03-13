

package com.chinacreator.dzjc_ruleEngine;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 环节监察信息
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_ruleEngine.Superviseinfo_huanjie",table="VA_TA_JC_HUANJIE_SUPERVISE_INFO",ds="acceptdata",cache=false)
public class Superviseinfo_huanjie {
				 /**
	 *办件ID
	 */
			@Column(id="instance_id", type=ColumnType.uuid , datatype="string64"   )
		private java.lang.String instanceId;
	
			 /**
	 *监察结果
	 */
			@Column(id="supervise_result_no" , datatype="string20"   )
		private java.lang.String superviseResultNo;
	
			 /**
	 *监察类型
	 */
			@Column(id="supervise_type_name" , datatype="string256"   )
		private java.lang.String superviseTypeName;
	
			 /**
	 *单位
	 */
			@Column(id="org_name" , datatype="string128"   )
		private java.lang.String orgName;
	
			 /**
	 *办件名称
	 */
			@Column(id="instance_name" , datatype="string256"   )
		private java.lang.String instanceName;
	
			 /**
	 *申请者
	 */
			@Column(id="apply_name" , datatype="string256"   )
		private java.lang.String applyName;
	
			 /**
	 *环节超期单位
	 */
			@Column(id="name" , datatype="string1024"   )
		private java.lang.String name;
	
			 /**
	 *环节时限
	 */
			@Column(id="link_time" , datatype="bigdouble"   )
		private java.lang.Double linkTime;
	
			 /**
	 *受理时间
	 */
			@Column(id="accept_time" , datatype="date"   )
		private java.sql.Date acceptTime;
	
			 /**
	 *办结时间
	 */
			@Column(id="end_time" , datatype="date"   )
		private java.sql.Date endTime;
	
			 /**
	 *监察时间
	 */
			@Column(id="supervise_time" , datatype="date"   )
		private java.sql.Date superviseTime;
	
			 /**
	 *办理状态
	 */
			@Column(id="project_state" , datatype="string5"   )
		private java.lang.String projectState;
	
			 /**
	 *办件编号
	 */
			@Column(id="instance_code" , datatype="string128"   )
		private java.lang.String instanceCode;
	
			 /**
	 *机构ID
	 */
			@Column(id="org_id" , datatype="string128"   )
		private java.lang.String orgId;
	
			 /**
	 *监察开始时间
	 */
			@Column(id="start_date" , datatype="date"   )
		private java.sql.Date startDate;
	
			 /**
	 *监察结束时间
	 */
			@Column(id="end_date" , datatype="date"   )
		private java.sql.Date endDate;
	
			 /**
	 *监察类型code
	 */
			@Column(id="superviseTypeCode" , datatype="string64"   )
		private java.lang.String superviseTypeCode;
	
			 /**
	 *监察结果ID
	 */
			@Column(id="supervise_info_id" , datatype="string64"   )
		private java.lang.String superviseInfoId;
	
			 /**
	 *法定时限
	 */
			@Column(id="timeLimit" , datatype="string64"   )
		private java.lang.String timeLimit;
	
			 /**
	 *承诺时限
	 */
			@Column(id="promisesLimit" , datatype="string64"   )
		private java.lang.String promisesLimit;
	
			 /**
	 *事项名称
	 */
			@Column(id="approveName" , datatype="string64"   )
		private java.lang.String approveName;
	
			 /**
	 *查询码
	 */
			@Column(id="query_code" , datatype="string128"   )
		private java.lang.String queryCode;
	
			 /**
	 *所在环节
	 */
			@Column(id="tache_name" , datatype="string64"   )
		private java.lang.String tacheName;
	
			 /**
	 *数据交换时间
	 */
			@Column(id="etl_time" , datatype="date"   )
		private java.sql.Date etlTime;
	
	
		/**
	* 设置办件ID
	*/
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}
	
	/**
	* 获取办件ID
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
	* 设置监察类型
	*/
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}
	
	/**
	* 获取监察类型
	*/
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}
		/**
	* 设置单位
	*/
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}
	
	/**
	* 获取单位
	*/
	public java.lang.String getOrgName() {
		return orgName;
	}
		/**
	* 设置办件名称
	*/
	public void setInstanceName(java.lang.String instanceName) {
		this.instanceName = instanceName;
	}
	
	/**
	* 获取办件名称
	*/
	public java.lang.String getInstanceName() {
		return instanceName;
	}
		/**
	* 设置申请者
	*/
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}
	
	/**
	* 获取申请者
	*/
	public java.lang.String getApplyName() {
		return applyName;
	}
		/**
	* 设置环节超期单位
	*/
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	/**
	* 获取环节超期单位
	*/
	public java.lang.String getName() {
		return name;
	}
		/**
	* 设置环节时限
	*/
	public void setLinkTime(java.lang.Double linkTime) {
		this.linkTime = linkTime;
	}
	
	/**
	* 获取环节时限
	*/
	public java.lang.Double getLinkTime() {
		return linkTime;
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
	* 设置办结时间
	*/
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	* 获取办结时间
	*/
	public java.sql.Date getEndTime() {
		return endTime;
	}
		/**
	* 设置监察时间
	*/
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}
	
	/**
	* 获取监察时间
	*/
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}
		/**
	* 设置办理状态
	*/
	public void setProjectState(java.lang.String projectState) {
		this.projectState = projectState;
	}
	
	/**
	* 获取办理状态
	*/
	public java.lang.String getProjectState() {
		return projectState;
	}
		/**
	* 设置办件编号
	*/
	public void setInstanceCode(java.lang.String instanceCode) {
		this.instanceCode = instanceCode;
	}
	
	/**
	* 获取办件编号
	*/
	public java.lang.String getInstanceCode() {
		return instanceCode;
	}
		/**
	* 设置机构ID
	*/
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}
	
	/**
	* 获取机构ID
	*/
	public java.lang.String getOrgId() {
		return orgId;
	}
		/**
	* 设置监察开始时间
	*/
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	* 获取监察开始时间
	*/
	public java.sql.Date getStartDate() {
		return startDate;
	}
		/**
	* 设置监察结束时间
	*/
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	* 获取监察结束时间
	*/
	public java.sql.Date getEndDate() {
		return endDate;
	}
		/**
	* 设置监察类型code
	*/
	public void setSuperviseTypeCode(java.lang.String superviseTypeCode) {
		this.superviseTypeCode = superviseTypeCode;
	}
	
	/**
	* 获取监察类型code
	*/
	public java.lang.String getSuperviseTypeCode() {
		return superviseTypeCode;
	}
		/**
	* 设置监察结果ID
	*/
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}
	
	/**
	* 获取监察结果ID
	*/
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}
		/**
	* 设置法定时限
	*/
	public void setTimeLimit(java.lang.String timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	/**
	* 获取法定时限
	*/
	public java.lang.String getTimeLimit() {
		return timeLimit;
	}
		/**
	* 设置承诺时限
	*/
	public void setPromisesLimit(java.lang.String promisesLimit) {
		this.promisesLimit = promisesLimit;
	}
	
	/**
	* 获取承诺时限
	*/
	public java.lang.String getPromisesLimit() {
		return promisesLimit;
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
	* 设置查询码
	*/
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}
	
	/**
	* 获取查询码
	*/
	public java.lang.String getQueryCode() {
		return queryCode;
	}
		/**
	* 设置所在环节
	*/
	public void setTacheName(java.lang.String tacheName) {
		this.tacheName = tacheName;
	}
	
	/**
	* 获取所在环节
	*/
	public java.lang.String getTacheName() {
		return tacheName;
	}
		/**
	* 设置数据交换时间
	*/
	public void setEtlTime(java.sql.Date etlTime) {
		this.etlTime = etlTime;
	}
	
	/**
	* 获取数据交换时间
	*/
	public java.sql.Date getEtlTime() {
		return etlTime;
	}
		}