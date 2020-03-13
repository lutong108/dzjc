package com.chinacreator.dzjc_performance;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 扣分与加分原因配置表
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_performance.EvalFractionalAllocation",table="TA_JC_EVAL_FRACTIONAL_ALLOCATION",ds="acceptdata",cache=false)
public class EvalFractionalAllocation {
				 /**
	 *ID号
	 */
			@Column(id="id", type=ColumnType.uuid , datatype="string64"   )
		private java.lang.String id;
	
			 /**
	 *类型 1扣分 2加分
	 */
			@Column(id="allocation_type" , datatype="string64"   )
		private java.lang.String allocationType;
	
			 /**
	 *考核项
	 */
			@Column(id="allocation_examination" , datatype="string128"   )
		private java.lang.String allocationExamination;
	
			 /**
	 *扣分与加分原因
	 */
			@Column(id="allocation_reason" , datatype="string2000"   )
		private java.lang.String allocationReason;
	
			 /**
	 *考核表名
	 */
			@Column(id="table_name" , datatype="string128"   )
		private java.lang.String tableName;
	
	
		/**
	* 设置ID号
	*/
	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	/**
	* 获取ID号
	*/
	public java.lang.String getId() {
		return id;
	}
		/**
	* 设置类型 1扣分 2加分
	*/
	public void setAllocationType(java.lang.String allocationType) {
		this.allocationType = allocationType;
	}
	
	/**
	* 获取类型 1扣分 2加分
	*/
	public java.lang.String getAllocationType() {
		return allocationType;
	}
		/**
	* 设置考核项
	*/
	public void setAllocationExamination(java.lang.String allocationExamination) {
		this.allocationExamination = allocationExamination;
	}
	
	/**
	* 获取考核项
	*/
	public java.lang.String getAllocationExamination() {
		return allocationExamination;
	}
		/**
	* 设置扣分与加分原因
	*/
	public void setAllocationReason(java.lang.String allocationReason) {
		this.allocationReason = allocationReason;
	}
	
	/**
	* 获取扣分与加分原因
	*/
	public java.lang.String getAllocationReason() {
		return allocationReason;
	}
		/**
	* 设置考核表名
	*/
	public void setTableName(java.lang.String tableName) {
		this.tableName = tableName;
	}
	
	/**
	* 获取考核表名
	*/
	public java.lang.String getTableName() {
		return tableName;
	}
		}