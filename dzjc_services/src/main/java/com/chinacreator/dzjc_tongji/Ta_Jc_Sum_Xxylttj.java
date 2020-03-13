

package com.chinacreator.dzjc_tongji;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 湘西一路通统计
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_tongji.Ta_Jc_Sum_Xxylttj",table="TA_JC_SUM_XXYLTTJ",ds="acceptdata",cache=false)
public class Ta_Jc_Sum_Xxylttj {
				 /**
	 *主键
	 */
			@Column(id="id", type=ColumnType.uuid , datatype="string128"   )
		private java.lang.String id;
	
			 /**
	 *类型（预留字段）
	 */
			@Column(id="type" , datatype="string128"   )
		private java.lang.String type;
	
			 /**
	 *受理人机构ID
	 */
			@Column(id="accept_org_id" , datatype="string128"   )
		private java.lang.String acceptOrgId;
	
			 /**
	 *办件机构ID
	 */
			@Column(id="org_id" , datatype="string128"   )
		private java.lang.String orgId;
	
			 /**
	 *统计时间YYYY-MM-DD(受理时间)
	 */
			@Column(id="begin_data" , datatype="string10"   )
		private java.lang.String beginData;
	
			 /**
	 *统计时间YYYY-MM-DD(办结时间,暂时无用预留字段)
	 */
			@Column(id="end_data" , datatype="string10"   )
		private java.lang.String endData;
	
			 /**
	 *事项ID
	 */
			@Column(id="approve_id" , datatype="string128"   )
		private java.lang.String approveId;
	
			 /**
	 *受理数
	 */
			@Column(id="sls_num" , datatype="bigdouble"   )
		private java.lang.Double slsNum;
	
			 /**
	 *办结数
	 */
			@Column(id="bjs_num" , datatype="bigdouble"   )
		private java.lang.Double bjsNum;
	
			 /**
	 *乡镇审核数
	 */
			@Column(id="xzshl_num" , datatype="bigdouble"   )
		private java.lang.Double xzshlNum;
	
			 /**
	 *县级审核数
	 */
			@Column(id="xjshl_num" , datatype="bigdouble"   )
		private java.lang.Double xjshlNum;
	
			 /**
	 *红牌数
	 */
			@Column(id="red_num" , datatype="bigdouble"   )
		private java.lang.Double redNum;
	
			 /**
	 *黄牌数
	 */
			@Column(id="yellow_num" , datatype="bigdouble"   )
		private java.lang.Double yellowNum;
	
			 /**
	 *公用名称
	 */
			@Column(id="shareName" , datatype="string64"   )
		private java.lang.String shareName;
	
			 /**
	 *代号
	 */
			@Column(id="codeName" , datatype="string64"   )
		private java.lang.String codeName;
	
			 /**
	 *事项名称
	 */
			@Column(id="approve_name" , datatype="string64"   )
		private java.lang.String approveName;
	
			 /**
	 *事项类型
	 */
			@Column(id="approve_type" , datatype="string64"   )
		private java.lang.String approveType;
	
			 /**
	 *受理人父机构ID
	 */
			@Column(id="p_accept_org_id" , datatype="string128"   )
		private java.lang.String pAcceptOrgId;
	
			 /**
	 *备注名称
	 */
			@Column(id="remarks_name" , datatype="string256"   )
		private java.lang.String remarksName;
	
	
		/**
	* 设置主键
	*/
	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	/**
	* 获取主键
	*/
	public java.lang.String getId() {
		return id;
	}
		/**
	* 设置类型（预留字段）
	*/
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	/**
	* 获取类型（预留字段）
	*/
	public java.lang.String getType() {
		return type;
	}
		/**
	* 设置受理人机构ID
	*/
	public void setAcceptOrgId(java.lang.String acceptOrgId) {
		this.acceptOrgId = acceptOrgId;
	}
	
	/**
	* 获取受理人机构ID
	*/
	public java.lang.String getAcceptOrgId() {
		return acceptOrgId;
	}
		/**
	* 设置办件机构ID
	*/
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}
	
	/**
	* 获取办件机构ID
	*/
	public java.lang.String getOrgId() {
		return orgId;
	}
		/**
	* 设置统计时间YYYY-MM-DD(受理时间)
	*/
	public void setBeginData(java.lang.String beginData) {
		this.beginData = beginData;
	}
	
	/**
	* 获取统计时间YYYY-MM-DD(受理时间)
	*/
	public java.lang.String getBeginData() {
		return beginData;
	}
		/**
	* 设置统计时间YYYY-MM-DD(办结时间,暂时无用预留字段)
	*/
	public void setEndData(java.lang.String endData) {
		this.endData = endData;
	}
	
	/**
	* 获取统计时间YYYY-MM-DD(办结时间,暂时无用预留字段)
	*/
	public java.lang.String getEndData() {
		return endData;
	}
		/**
	* 设置事项ID
	*/
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}
	
	/**
	* 获取事项ID
	*/
	public java.lang.String getApproveId() {
		return approveId;
	}
		/**
	* 设置受理数
	*/
	public void setSlsNum(java.lang.Double slsNum) {
		this.slsNum = slsNum;
	}
	
	/**
	* 获取受理数
	*/
	public java.lang.Double getSlsNum() {
		return slsNum;
	}
		/**
	* 设置办结数
	*/
	public void setBjsNum(java.lang.Double bjsNum) {
		this.bjsNum = bjsNum;
	}
	
	/**
	* 获取办结数
	*/
	public java.lang.Double getBjsNum() {
		return bjsNum;
	}
		/**
	* 设置乡镇审核数
	*/
	public void setXzshlNum(java.lang.Double xzshlNum) {
		this.xzshlNum = xzshlNum;
	}
	
	/**
	* 获取乡镇审核数
	*/
	public java.lang.Double getXzshlNum() {
		return xzshlNum;
	}
		/**
	* 设置县级审核数
	*/
	public void setXjshlNum(java.lang.Double xjshlNum) {
		this.xjshlNum = xjshlNum;
	}
	
	/**
	* 获取县级审核数
	*/
	public java.lang.Double getXjshlNum() {
		return xjshlNum;
	}
		/**
	* 设置红牌数
	*/
	public void setRedNum(java.lang.Double redNum) {
		this.redNum = redNum;
	}
	
	/**
	* 获取红牌数
	*/
	public java.lang.Double getRedNum() {
		return redNum;
	}
		/**
	* 设置黄牌数
	*/
	public void setYellowNum(java.lang.Double yellowNum) {
		this.yellowNum = yellowNum;
	}
	
	/**
	* 获取黄牌数
	*/
	public java.lang.Double getYellowNum() {
		return yellowNum;
	}
		/**
	* 设置公用名称
	*/
	public void setShareName(java.lang.String shareName) {
		this.shareName = shareName;
	}
	
	/**
	* 获取公用名称
	*/
	public java.lang.String getShareName() {
		return shareName;
	}
		/**
	* 设置代号
	*/
	public void setCodeName(java.lang.String codeName) {
		this.codeName = codeName;
	}
	
	/**
	* 获取代号
	*/
	public java.lang.String getCodeName() {
		return codeName;
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
	* 设置事项类型
	*/
	public void setApproveType(java.lang.String approveType) {
		this.approveType = approveType;
	}
	
	/**
	* 获取事项类型
	*/
	public java.lang.String getApproveType() {
		return approveType;
	}
		/**
	* 设置受理人父机构ID
	*/
	public void setPAcceptOrgId(java.lang.String pAcceptOrgId) {
		this.pAcceptOrgId = pAcceptOrgId;
	}
	
	/**
	* 获取受理人父机构ID
	*/
	public java.lang.String getPAcceptOrgId() {
		return pAcceptOrgId;
	}
		/**
	* 设置备注名称
	*/
	public void setRemarksName(java.lang.String remarksName) {
		this.remarksName = remarksName;
	}
	
	/**
	* 获取备注名称
	*/
	public java.lang.String getRemarksName() {
		return remarksName;
	}
		}