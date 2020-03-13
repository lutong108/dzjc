

package com.chinacreator.dzjc_union;

			import java.io.Serializable;
			
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.SortType;

/**
 * 并联办件统计
 * @author 
 * @generated
 */
@Entity(id="entity:com.chinacreator.dzjc_union.UnionInstanceCount",table="VA_TA_UNION_INSTANCE_COUNT",ds="acceptdata",cache=false)
public class UnionInstanceCount {
				 /**
	 *机构id
	 */
			@Column(id="org_id", type=ColumnType.uuid , datatype="string64"   )
		private java.lang.String orgId;
	
			 /**
	 *机构名称
	 */
			@Column(id="org_name" , datatype="string1024"   )
		private java.lang.String orgName;
	
			 /**
	 *机构区域
	 */
			@Column(id="xzqm" , datatype="string2000"   )
		private java.lang.String xzqm;
	
			 /**
	 *参与项目数(个)
	 */
			@Column(id="project_count" , datatype="bigdouble"   )
		private java.lang.Double projectCount;
	
			 /**
	 *暂存件(个)
	 */
			@Column(id="zancun_count" , datatype="bigdouble"   )
		private java.lang.Double zancunCount;
	
			 /**
	 *分发件(个)
	 */
			@Column(id="fenfa_count" , datatype="bigdouble"   )
		private java.lang.Double fenfaCount;
	
			 /**
	 *受理件(个)
	 */
			@Column(id="shouli_count" , datatype="bigdouble"   )
		private java.lang.Double shouliCount;
	
			 /**
	 *办结件(个)
	 */
			@Column(id="banjie_count" , datatype="bigdouble"   )
		private java.lang.Double banjieCount;
	
			 /**
	 *预警数(个)
	 */
			@Column(id="yujing_count" , datatype="bigdouble"   )
		private java.lang.Double yujingCount;
	
			 /**
	 *黄牌数(个)
	 */
			@Column(id="huangpai_count" , datatype="bigdouble"   )
		private java.lang.Double huangpaiCount;
	
			 /**
	 *红牌数(个)
	 */
			@Column(id="hongpai_count" , datatype="bigdouble"   )
		private java.lang.Double hongpaiCount;
	
			 /**
	 *办结率(%)
	 */
			@Column(id="banjie_rate" , datatype="double"   )
		private java.lang.Double banjieRate;
	
			 /**
	 *统计开始时间
	 */
			@Column(id="begin_date" , datatype="char10"   )
		private java.lang.String beginDate;
	
			 /**
	 *统计结束时间
	 */
			@Column(id="end_date" , datatype="char10"   )
		private java.lang.String endDate;
	
	
		private String canyuCount;
			
		public String getCanyuCount() {
			return canyuCount;
		}

		public void setCanyuCount(String canyuCount) {
			this.canyuCount = canyuCount;
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
	* 设置机构区域
	*/
	public void setXzqm(java.lang.String xzqm) {
		this.xzqm = xzqm;
	}
	
	/**
	* 获取机构区域
	*/
	public java.lang.String getXzqm() {
		return xzqm;
	}
		/**
	* 设置参与项目数(个)
	*/
	public void setProjectCount(java.lang.Double projectCount) {
		this.projectCount = projectCount;
	}
	
	/**
	* 获取参与项目数(个)
	*/
	public java.lang.Double getProjectCount() {
		return projectCount;
	}
		/**
	* 设置暂存件(个)
	*/
	public void setZancunCount(java.lang.Double zancunCount) {
		this.zancunCount = zancunCount;
	}
	
	/**
	* 获取暂存件(个)
	*/
	public java.lang.Double getZancunCount() {
		return zancunCount;
	}
		/**
	* 设置分发件(个)
	*/
	public void setFenfaCount(java.lang.Double fenfaCount) {
		this.fenfaCount = fenfaCount;
	}
	
	/**
	* 获取分发件(个)
	*/
	public java.lang.Double getFenfaCount() {
		return fenfaCount;
	}
		/**
	* 设置受理件(个)
	*/
	public void setShouliCount(java.lang.Double shouliCount) {
		this.shouliCount = shouliCount;
	}
	
	/**
	* 获取受理件(个)
	*/
	public java.lang.Double getShouliCount() {
		return shouliCount;
	}
		/**
	* 设置办结件(个)
	*/
	public void setBanjieCount(java.lang.Double banjieCount) {
		this.banjieCount = banjieCount;
	}
	
	/**
	* 获取办结件(个)
	*/
	public java.lang.Double getBanjieCount() {
		return banjieCount;
	}
		/**
	* 设置预警数(个)
	*/
	public void setYujingCount(java.lang.Double yujingCount) {
		this.yujingCount = yujingCount;
	}
	
	/**
	* 获取预警数(个)
	*/
	public java.lang.Double getYujingCount() {
		return yujingCount;
	}
		/**
	* 设置黄牌数(个)
	*/
	public void setHuangpaiCount(java.lang.Double huangpaiCount) {
		this.huangpaiCount = huangpaiCount;
	}
	
	/**
	* 获取黄牌数(个)
	*/
	public java.lang.Double getHuangpaiCount() {
		return huangpaiCount;
	}
		/**
	* 设置红牌数(个)
	*/
	public void setHongpaiCount(java.lang.Double hongpaiCount) {
		this.hongpaiCount = hongpaiCount;
	}
	
	/**
	* 获取红牌数(个)
	*/
	public java.lang.Double getHongpaiCount() {
		return hongpaiCount;
	}
		/**
	* 设置办结率(%)
	*/
	public void setBanjieRate(java.lang.Double banjieRate) {
		this.banjieRate = banjieRate;
	}
	
	/**
	* 获取办结率(%)
	*/
	public java.lang.Double getBanjieRate() {
		return banjieRate;
	}
		/**
	* 设置统计开始时间
	*/
	public void setBeginDate(java.lang.String beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	* 获取统计开始时间
	*/
	public java.lang.String getBeginDate() {
		return beginDate;
	}
		/**
	* 设置统计结束时间
	*/
	public void setEndDate(java.lang.String endDate) {
		this.endDate = endDate;
	}
	
	/**
	* 获取统计结束时间
	*/
	public java.lang.String getEndDate() {
		return endDate;
	}
		}