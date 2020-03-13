package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 字典类型
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.Dicttype", table = "TD_SM_DICTTYPE", ds = "acceptdata")
public class Dicttype implements Serializable {
	private static final long serialVersionUID = 2672811261444096L;
	/**
	 *字典类型ID
	 */
	@Column(id = "dicttype_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String dicttypeId;

	/**
	 *字典类型名称
	 */
	@Column(id = "dicttype_name", datatype = "string128")
	private java.lang.String dicttypeName;

	/**
	 *字典类型描述
	 */
	@Column(id = "dicttype_desc", datatype = "string128")
	private java.lang.String dicttypeDesc;

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
	 * 设置字典类型名称
	 */
	public void setDicttypeName(java.lang.String dicttypeName) {
		this.dicttypeName = dicttypeName;
	}

	/**
	 * 获取字典类型名称
	 */
	public java.lang.String getDicttypeName() {
		return dicttypeName;
	}

	/**
	 * 设置字典类型描述
	 */
	public void setDicttypeDesc(java.lang.String dicttypeDesc) {
		this.dicttypeDesc = dicttypeDesc;
	}

	/**
	 * 获取字典类型描述
	 */
	public java.lang.String getDicttypeDesc() {
		return dicttypeDesc;
	}
}
