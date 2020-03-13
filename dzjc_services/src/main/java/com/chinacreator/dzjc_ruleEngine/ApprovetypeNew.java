package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 业务类别
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ApprovetypeNew", table = "v_jc_ta_dic_approvetype", ds = "acceptdata", cache = false)
public class ApprovetypeNew {
	/**
	 *类型编号
	 */
	@Column(id = "type_code", datatype = "string5")
	private java.lang.String typeCode;

	/**
	 *类型名称
	 */
	@Column(id = "type_name", datatype = "string64")
	private java.lang.String typeName;

	/**
	 *序列，从0开始
	 */
	@Column(id = "seq", type = ColumnType.uuid, datatype = "bigdouble")
	private java.lang.Double seq;

	/**
	 *排序
	 */
	@Column(id = "order_num", datatype = "bigdouble")
	private java.lang.Double orderNum;

	/**
	 *父类型编号
	 */
	@Column(id = "parent_type_code", datatype = "string5")
	private java.lang.String parentTypeCode;

	/**
	 * 设置类型编号
	 */
	public void setTypeCode(java.lang.String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 获取类型编号
	 */
	public java.lang.String getTypeCode() {
		return typeCode;
	}

	/**
	 * 设置类型名称
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取类型名称
	 */
	public java.lang.String getTypeName() {
		return typeName;
	}

	/**
	 * 设置序列，从0开始
	 */
	public void setSeq(java.lang.Double seq) {
		this.seq = seq;
	}

	/**
	 * 获取序列，从0开始
	 */
	public java.lang.Double getSeq() {
		return seq;
	}

	/**
	 * 设置排序
	 */
	public void setOrderNum(java.lang.Double orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取排序
	 */
	public java.lang.Double getOrderNum() {
		return orderNum;
	}

	/**
	 * 设置父类型编号
	 */
	public void setParentTypeCode(java.lang.String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}

	/**
	 * 获取父类型编号
	 */
	public java.lang.String getParentTypeCode() {
		return parentTypeCode;
	}
}
