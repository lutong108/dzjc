package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察统计缓存表实体
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.StatisticalSumCache", table = "TA_JC_STATISTICAL_SUM_CACHE", ds = "acceptdata", cache = false)
public class StatisticalSumCache {
	/**
	 *发牌ID
	 */
	@Column(id = "supervise_id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String superviseId;

	/**
	 *统计类型（1发牌）
	 */
	@Column(id = "statistical_type", datatype = "string10")
	private java.lang.String statisticalType;

	/**
	 *业务ID（办件咨询投诉ID）
	 */
	@Column(id = "business_id", datatype = "string128")
	private java.lang.String businessId;

	/**
	 *插入系统当前时间
	 */
	@Column(id = "current_time", datatype = "date")
	private java.sql.Date currentTime;

	/**
	 *业务统计时间（发牌:监察时间）
	 */
	@Column(id = "statistical_time", datatype = "date")
	private java.sql.Date statisticalTime;

	/**
	 *业务状态（发牌:已发Y,待发D,撤销C,取消Q,督办B）
	 */
	@Column(id = "business_status", datatype = "string10")
	private java.lang.String businessStatus;

	/**
	 *业务类型（发牌:1预2黄3红）
	 */
	@Column(id = "business_type", datatype = "string10")
	private java.lang.String businessType;

	/**
	 *业务机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *业务数据来源（发牌:1011办件时限,1012投诉时限,1013咨询异常,1014办件异常,1015咨询异常,1016特别程序时限,1017网上办事时限,1018办件出证时限）
	 */
	@Column(id = "data_source", datatype = "string10")
	private java.lang.String dataSource;

	/**
	 *是否统计（Y已统计）
	 */
	@Column(id = "is_statistical", datatype = "string5")
	private java.lang.String isStatistical;

	/**
	 * 设置发牌ID
	 */
	public void setSuperviseId(java.lang.String superviseId) {
		this.superviseId = superviseId;
	}

	/**
	 * 获取发牌ID
	 */
	public java.lang.String getSuperviseId() {
		return superviseId;
	}

	/**
	 * 设置统计类型（1发牌）
	 */
	public void setStatisticalType(java.lang.String statisticalType) {
		this.statisticalType = statisticalType;
	}

	/**
	 * 获取统计类型（1发牌）
	 */
	public java.lang.String getStatisticalType() {
		return statisticalType;
	}

	/**
	 * 设置业务ID（办件咨询投诉ID）
	 */
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取业务ID（办件咨询投诉ID）
	 */
	public java.lang.String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置插入系统当前时间
	 */
	public void setCurrentTime(java.sql.Date currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * 获取插入系统当前时间
	 */
	public java.sql.Date getCurrentTime() {
		return currentTime;
	}

	/**
	 * 设置业务统计时间（发牌:监察时间）
	 */
	public void setStatisticalTime(java.sql.Date statisticalTime) {
		this.statisticalTime = statisticalTime;
	}

	/**
	 * 获取业务统计时间（发牌:监察时间）
	 */
	public java.sql.Date getStatisticalTime() {
		return statisticalTime;
	}

	/**
	 * 设置业务状态（发牌:已发Y,待发D,撤销C,取消Q,督办B）
	 */
	public void setBusinessStatus(java.lang.String businessStatus) {
		this.businessStatus = businessStatus;
	}

	/**
	 * 获取业务状态（发牌:已发Y,待发D,撤销C,取消Q,督办B）
	 */
	public java.lang.String getBusinessStatus() {
		return businessStatus;
	}

	/**
	 * 设置业务类型（发牌:1预2黄3红）
	 */
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 获取业务类型（发牌:1预2黄3红）
	 */
	public java.lang.String getBusinessType() {
		return businessType;
	}

	/**
	 * 设置业务机构ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取业务机构ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置业务数据来源（发牌:1011办件时限,1012投诉时限,1013咨询异常,1014办件异常,1015咨询异常,1016特别程序时限,1017网上办事时限,1018办件出证时限）
	 */
	public void setDataSource(java.lang.String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取业务数据来源（发牌:1011办件时限,1012投诉时限,1013咨询异常,1014办件异常,1015咨询异常,1016特别程序时限,1017网上办事时限,1018办件出证时限）
	 */
	public java.lang.String getDataSource() {
		return dataSource;
	}

	/**
	 * 设置是否统计（Y已统计）
	 */
	public void setIsStatistical(java.lang.String isStatistical) {
		this.isStatistical = isStatistical;
	}

	/**
	 * 获取是否统计（Y已统计）
	 */
	public java.lang.String getIsStatistical() {
		return isStatistical;
	}
}
