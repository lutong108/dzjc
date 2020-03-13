package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.Entity;

/**
 * 需要反写业务数据表的临时表
 * @author L
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseTemp", table = "ta_jc_supervise_temp", ds = "acceptdata", cache = false)
public class SuperviseTemp {
	/**
	 *业务ID
	 */
	@Column(id = "business_id", datatype = "string64")
	private java.lang.String businessId;

	/**
	 *业务类型：1：办件，2：投诉，3：咨询
	 */
	@Column(id = "business_type", datatype = "string1")
	private java.lang.String businessType;

	/**
	 *是否需要监察，Y：是：N：否，需要反写的字段
	 */
	@Column(id = "is_need_supervise", datatype = "string20")
	private java.lang.String isNeedSupervise;

	/**
	 *最近一次发牌结果：1：预警，2：黄牌，3：红牌，需要反写的字段
	 */
	@Column(id = "last_supervise", datatype = "string50")
	private java.lang.String lastSupervise;

	public java.lang.String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	public java.lang.String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	public java.lang.String getIsNeedSupervise() {
		return isNeedSupervise;
	}

	public void setIsNeedSupervise(java.lang.String isNeedSupervise) {
		this.isNeedSupervise = isNeedSupervise;
	}

	public java.lang.String getLastSupervise() {
		return lastSupervise;
	}

	public void setLastSupervise(java.lang.String lastSupervise) {
		this.lastSupervise = lastSupervise;
	}

	
}
