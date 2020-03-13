

package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 办事指南信息
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ServicesGuideInfo",table="V_JC_TA_SP_SERVICES_GUIDE",ds="acceptdata",cache=false)
public class ServicesGuideInfo {
					@Column(id="approve_code" , datatype="string64"   )
		private java.lang.String approveCode;
	
			 /**
	 *
	 */
			@Column(id="area_code" , datatype="string64"   )
		private java.lang.String areaCode;
	
				@Column(id="approve_name" , datatype="string1024"   )
		private java.lang.String approveName;
	
				@Column(id="type_code" , datatype="string5"   )
		private java.lang.String typeCode;
	
				@Column(id="approve_id", type=ColumnType.uuid , datatype="string64"   )
		private java.lang.String approveId;
	
				@Column(id="approve_limit" , datatype="bigdouble"   )
		private java.lang.Double approveLimit;
	
				@Column(id="commitment_limit" , datatype="bigdouble"   )
		private java.lang.Double commitmentLimit;
	
	
		/**
	* 设置${field.desc}
	*/
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}
	
	/**
	* 获取${field.desc}
	*/
	public java.lang.String getApproveCode() {
		return approveCode;
	}
		/**
	* 设置
	*/
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}
	
	/**
	* 获取
	*/
	public java.lang.String getAreaCode() {
		return areaCode;
	}
		/**
	* 设置${field.desc}
	*/
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}
	
	/**
	* 获取${field.desc}
	*/
	public java.lang.String getApproveName() {
		return approveName;
	}
		/**
	* 设置${field.desc}
	*/
	public void setTypeCode(java.lang.String typeCode) {
		this.typeCode = typeCode;
	}
	
	/**
	* 获取${field.desc}
	*/
	public java.lang.String getTypeCode() {
		return typeCode;
	}
		/**
	* 设置${field.desc}
	*/
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}
	
	/**
	* 获取${field.desc}
	*/
	public java.lang.String getApproveId() {
		return approveId;
	}
		/**
	* 设置${field.desc}
	*/
	public void setApproveLimit(java.lang.Double approveLimit) {
		this.approveLimit = approveLimit;
	}
	
	/**
	* 获取${field.desc}
	*/
	public java.lang.Double getApproveLimit() {
		return approveLimit;
	}
		/**
	* 设置${field.desc}
	*/
	public void setCommitmentLimit(java.lang.Double commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}
	
	/**
	* 获取${field.desc}
	*/
	public java.lang.Double getCommitmentLimit() {
		return commitmentLimit;
	}
		}