package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 业务类型
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.DicBusinessType", table = "TA_JC_DIC_BUSINESS_TYPE", ds = "acceptdata")
public class DicBusinessType implements Serializable {
	private static final long serialVersionUID = 2626311914651648L;
	/**
	 *业务类型ID
	 */
	@Column(id = "business_type_id", datatype = "string64")
	private java.lang.String businessTypeId;

	/**
	 *业务类型编码
	 */
	@Column(id = "business_type_code", type = ColumnType.uuid, datatype = "char6")
	private java.lang.String businessTypeCode;

	/**
	 *业务类型名称
	 */
	@Column(id = "business_type_name", datatype = "string64")
	private java.lang.String businessTypeName;

	/**
	 *业务类型排序
	 */
	@Column(id = "business_order", datatype = "string10")
	private java.lang.String businessOrder;

	/**
	 * 设置业务类型ID
	 */
	public void setBusinessTypeId(java.lang.String businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	/**
	 * 获取业务类型ID
	 */
	public java.lang.String getBusinessTypeId() {
		return businessTypeId;
	}

	/**
	 * 设置业务类型编码
	 */
	public void setBusinessTypeCode(java.lang.String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	/**
	 * 获取业务类型编码
	 */
	public java.lang.String getBusinessTypeCode() {
		return businessTypeCode;
	}

	/**
	 * 设置业务类型名称
	 */
	public void setBusinessTypeName(java.lang.String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	/**
	 * 获取业务类型名称
	 */
	public java.lang.String getBusinessTypeName() {
		return businessTypeName;
	}

	/**
	 * 设置业务类型排序
	 */
	public void setBusinessOrder(java.lang.String businessOrder) {
		this.businessOrder = businessOrder;
	}

	/**
	 * 获取业务类型排序
	 */
	public java.lang.String getBusinessOrder() {
		return businessOrder;
	}
}
