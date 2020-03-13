package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 字典数据
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.Dictdata", table = "TD_SM_DICTDATA", ds = "acceptdata")
public class Dictdata implements Serializable {
	private static final long serialVersionUID = 2672821134131200L;
	/**
	 *字典ID
	 */
	@Column(id = "dictdata_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String dictdataId;

	/**
	 *字典类型ID
	 */
	@Column(id = "dicttype_id", datatype = "string64")
	private java.lang.String dicttypeId;

	/**
	 *字典真实值
	 */
	@Column(id = "dictdata_name", datatype = "string128")
	private java.lang.String dictdataName;

	/**
	 *字典显示值
	 */
	@Column(id = "dictdata_value", datatype = "string128")
	private java.lang.String dictdataValue;

	/**
	 *字典排序号
	 */
	@Column(id = "dictdata_order", datatype = "string64")
	private java.lang.String dictdataOrder;

	/**
	 *是否默认值（0：否，1：是）
	 */
	@Column(id = "dictdata_isdefault", datatype = "string64")
	private java.lang.String dictdataIsdefault;

	/**
	 * 设置字典ID
	 */
	public void setDictdataId(java.lang.String dictdataId) {
		this.dictdataId = dictdataId;
	}

	/**
	 * 获取字典ID
	 */
	public java.lang.String getDictdataId() {
		return dictdataId;
	}

	/**
	 * 设置字典类型ID
	 */
	public void setDicttypeId(java.lang.String dicttypeId) {
		this.dicttypeId = dicttypeId;
	}

	/**
	 * 获取字典类型ID
	 */
	public java.lang.String getDicttypeId() {
		return dicttypeId;
	}

	/**
	 * 设置字典真实值
	 */
	public void setDictdataName(java.lang.String dictdataName) {
		this.dictdataName = dictdataName;
	}

	/**
	 * 获取字典真实值
	 */
	public java.lang.String getDictdataName() {
		return dictdataName;
	}

	/**
	 * 设置字典显示值
	 */
	public void setDictdataValue(java.lang.String dictdataValue) {
		this.dictdataValue = dictdataValue;
	}

	/**
	 * 获取字典显示值
	 */
	public java.lang.String getDictdataValue() {
		return dictdataValue;
	}

	/**
	 * 设置字典排序号
	 */
	public void setDictdataOrder(java.lang.String dictdataOrder) {
		this.dictdataOrder = dictdataOrder;
	}

	/**
	 * 获取字典排序号
	 */
	public java.lang.String getDictdataOrder() {
		return dictdataOrder;
	}

	/**
	 * 设置是否默认值（0：否，1：是）
	 */
	public void setDictdataIsdefault(java.lang.String dictdataIsdefault) {
		this.dictdataIsdefault = dictdataIsdefault;
	}

	/**
	 * 获取是否默认值（0：否，1：是）
	 */
	public java.lang.String getDictdataIsdefault() {
		return dictdataIsdefault;
	}
}
