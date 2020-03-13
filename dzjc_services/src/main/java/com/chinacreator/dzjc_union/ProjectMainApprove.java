

package com.chinacreator.dzjc_union;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 项目主事项
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_union.ProjectMainApprove",table="TA_UNION_MAINAPPROVE",ds="acceptdata",cache=false)
public class ProjectMainApprove {
				 /**
	 *并联主事项ID
	 */
			@Column(id="mainapprove_id", type=ColumnType.uuid , datatype="string64"   )
		private java.lang.String mainapproveId;
	
			 /**
	 *名称
	 */
			@Column(id="mainapprove_name" , datatype="string256"   )
		private java.lang.String mainapproveName;
	
			 /**
	 *编码
	 */
			@Column(id="mainapprove_code" , datatype="string128"   )
		private java.lang.String mainapproveCode;
	
			 /**
	 *项目属性(取字典数据)
	 */
			@Column(id="project_attr" , datatype="string5"   )
		private java.lang.String projectAttr;
	
			 /**
	 *牵头单位ID
	 */
			@Column(id="leadunit_id" , datatype="string64"   )
		private java.lang.String leadunitId;
	
			 /**
	 *牵头单位
	 */
			@Column(id="leadunit_name" , datatype="string256"   )
		private java.lang.String leadunitName;
	
			 /**
	 *收件窗口
	 */
			@Column(id="collection_window" , datatype="string256"   )
		private java.lang.String collectionWindow;
	
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
	 *是否有效
	 */
			@Column(id="is_valid" , datatype="string5"   )
		private java.lang.String isValid;
	
			 /**
	 *审批依据
	 */
			@Column(id="according" , datatype="string2000"   )
		private java.lang.String according;
	
			 /**
	 *备注
	 */
			@Column(id="remark" , datatype="string512"   )
		private java.lang.String remark;
	
			 /**
	 *创建时间
	 */
			@Column(id="create_time" , datatype="date"   )
		private java.sql.Date createTime;
	
			 /**
	 *创建人ID
	 */
			@Column(id="creater_id" , datatype="string64"   )
		private java.lang.String createrId;
	
			 /**
	 *更新时间
	 */
			@Column(id="update_time" , datatype="date"   )
		private java.sql.Date updateTime;
	
			 /**
	 *更新人ID
	 */
			@Column(id="update_id" , datatype="string64"   )
		private java.lang.String updateId;
	
			 /**
	 *是否阶段受理
	 */
			@Column(id="is_stageaccept" , datatype="string5"   )
		private java.lang.String isStageaccept;
	
			 /**
	 *并联主事项状态(0:暂存,1:发布,2:取消)
	 */
			@Column(id="mainapprove_state" , datatype="string5"   )
		private java.lang.String mainapproveState;
	
			 /**
	 *区域编码
	 */
			@Column(id="area_code" , datatype="string64"   )
		private java.lang.String areaCode;
	
	
		/**
	* 设置并联主事项ID
	*/
	public void setMainapproveId(java.lang.String mainapproveId) {
		this.mainapproveId = mainapproveId;
	}
	
	/**
	* 获取并联主事项ID
	*/
	public java.lang.String getMainapproveId() {
		return mainapproveId;
	}
		/**
	* 设置名称
	*/
	public void setMainapproveName(java.lang.String mainapproveName) {
		this.mainapproveName = mainapproveName;
	}
	
	/**
	* 获取名称
	*/
	public java.lang.String getMainapproveName() {
		return mainapproveName;
	}
		/**
	* 设置编码
	*/
	public void setMainapproveCode(java.lang.String mainapproveCode) {
		this.mainapproveCode = mainapproveCode;
	}
	
	/**
	* 获取编码
	*/
	public java.lang.String getMainapproveCode() {
		return mainapproveCode;
	}
		/**
	* 设置项目属性(取字典数据)
	*/
	public void setProjectAttr(java.lang.String projectAttr) {
		this.projectAttr = projectAttr;
	}
	
	/**
	* 获取项目属性(取字典数据)
	*/
	public java.lang.String getProjectAttr() {
		return projectAttr;
	}
		/**
	* 设置牵头单位ID
	*/
	public void setLeadunitId(java.lang.String leadunitId) {
		this.leadunitId = leadunitId;
	}
	
	/**
	* 获取牵头单位ID
	*/
	public java.lang.String getLeadunitId() {
		return leadunitId;
	}
		/**
	* 设置牵头单位
	*/
	public void setLeadunitName(java.lang.String leadunitName) {
		this.leadunitName = leadunitName;
	}
	
	/**
	* 获取牵头单位
	*/
	public java.lang.String getLeadunitName() {
		return leadunitName;
	}
		/**
	* 设置收件窗口
	*/
	public void setCollectionWindow(java.lang.String collectionWindow) {
		this.collectionWindow = collectionWindow;
	}
	
	/**
	* 获取收件窗口
	*/
	public java.lang.String getCollectionWindow() {
		return collectionWindow;
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
	* 设置是否有效
	*/
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}
	
	/**
	* 获取是否有效
	*/
	public java.lang.String getIsValid() {
		return isValid;
	}
		/**
	* 设置审批依据
	*/
	public void setAccording(java.lang.String according) {
		this.according = according;
	}
	
	/**
	* 获取审批依据
	*/
	public java.lang.String getAccording() {
		return according;
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
	* 设置并联主事项状态(0:暂存,1:发布,2:取消)
	*/
	public void setMainapproveState(java.lang.String mainapproveState) {
		this.mainapproveState = mainapproveState;
	}
	
	/**
	* 获取并联主事项状态(0:暂存,1:发布,2:取消)
	*/
	public java.lang.String getMainapproveState() {
		return mainapproveState;
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
		}