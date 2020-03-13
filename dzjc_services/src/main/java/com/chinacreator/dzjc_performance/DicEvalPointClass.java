package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 考核类型
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.dicEvalPointClass", table = "TA_JC_DIC_EVALPOINT_CLASS", ds = "acceptdata", cache = false)
public class DicEvalPointClass {
	/**
	 *绩效分类编号，通过行政区划代码+序列SEQ_EVALPOINT_CLASS取值
	 */
	@Column(id = "point_type_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String pointTypeId;

	/**
	 *上级分类编号
	 */
	@Column(id = "parent_pointtype_id", datatype = "string64")
	private java.lang.String parentPointtypeId;

	/**
	 *分类名称
	 */
	@Column(id = "point_type_name", datatype = "string256")
	private java.lang.String pointTypeName;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string256")
	private java.lang.String remark;

	/**
	 *行政区划代码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *排序号
	 */
	@Column(id = "order_no", datatype = "smallint")
	private java.lang.Integer orderNo;

	/**
	 *是否有效
	 */
	@Column(id = "is_valid", datatype = "string10")
	private java.lang.String isValid;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *是否通用（Y-通用性，N-个性化）
	 */
	@Column(id = "is_public", datatype = "string10")
	private java.lang.String isPublic;

	/**
	 *分值
	 */
	@Column(id = "value", datatype = "smalldouble")
	private java.lang.Double value;

	/**
	 *权重
	 */
	@Column(id = "proportion", datatype = "string10")
	private java.lang.String proportion;

	/**
	 *是否是父项
	 */
	@Column(id = "is_parent", datatype = "char1")
	private java.lang.String isParent;

	/**
	 * 设置绩效分类编号，通过行政区划代码+序列SEQ_EVALPOINT_CLASS取值
	 */
	public void setPointTypeId(java.lang.String pointTypeId) {
		this.pointTypeId = pointTypeId;
	}

	/**
	 * 获取绩效分类编号，通过行政区划代码+序列SEQ_EVALPOINT_CLASS取值
	 */
	public java.lang.String getPointTypeId() {
		return pointTypeId;
	}

	/**
	 * 设置上级分类编号
	 */
	public void setParentPointtypeId(java.lang.String parentPointtypeId) {
		this.parentPointtypeId = parentPointtypeId;
	}

	/**
	 * 获取上级分类编号
	 */
	public java.lang.String getParentPointtypeId() {
		return parentPointtypeId;
	}

	/**
	 * 设置分类名称
	 */
	public void setPointTypeName(java.lang.String pointTypeName) {
		this.pointTypeName = pointTypeName;
	}

	/**
	 * 获取分类名称
	 */
	public java.lang.String getPointTypeName() {
		return pointTypeName;
	}

	/**
	 * 设置备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * 设置行政区划代码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取行政区划代码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置排序号
	 */
	public void setOrderNo(java.lang.Integer orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取排序号
	 */
	public java.lang.Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置是否有效
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置平台实例编号
	 */
	public void setCcFormInstanceid(java.lang.String ccFormInstanceid) {
		this.ccFormInstanceid = ccFormInstanceid;
	}

	/**
	 * 获取平台实例编号
	 */
	public java.lang.String getCcFormInstanceid() {
		return ccFormInstanceid;
	}

	/**
	 * 设置是否通用（Y-通用性，N-个性化）
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取是否通用（Y-通用性，N-个性化）
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置分值
	 */
	public void setValue(java.lang.Double value) {
		this.value = value;
	}

	/**
	 * 获取分值
	 */
	public java.lang.Double getValue() {
		return value;
	}

	/**
	 * 设置权重
	 */
	public void setProportion(java.lang.String proportion) {
		this.proportion = proportion;
	}

	/**
	 * 获取权重
	 */
	public java.lang.String getProportion() {
		return proportion;
	}

	/**
	 * 设置是否是父项
	 */
	public void setIsParent(java.lang.String isParent) {
		this.isParent = isParent;
	}

	/**
	 * 获取是否是父项
	 */
	public java.lang.String getIsParent() {
		return isParent;
	}
}
