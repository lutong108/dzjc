package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效考评项
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.evalItem", table = "TA_JC_EVAL_ITEM", ds = "acceptdata", cache = false)
public class EvalItem {
	/**
	 *考评项目编号，通过行政区划代码+序列SEQ_EVAL_ITEM取值
	 */
	@Column(id = "item_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String itemId;

	/**
	 *考评项目名称
	 */
	@Column(id = "item_name", datatype = "string256")
	private java.lang.String itemName;

	/**
	 *最小值
	 */
	@Column(id = "min_value", datatype = "smalldouble")
	private java.lang.Double minValue;

	/**
	 *最大值
	 */
	@Column(id = "max_value", datatype = "smalldouble")
	private java.lang.Double maxValue;

	/**
	 *高于最大值取值
	 */
	@Column(id = "maxval_depend", datatype = "string20")
	private java.lang.String maxvalDepend;

	/**
	 *低于最小值取值
	 */
	@Column(id = "minval_depend", datatype = "string20")
	private java.lang.String minvalDepend;

	/**
	 *考评公式
	 */
	@Column(id = "item_formula", datatype = "string1536")
	private java.lang.String itemFormula;

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
	 *是否启用
	 */
	@Column(id = "is_used", datatype = "char1")
	private java.lang.String isUsed;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *是否最终得分
	 */
	@Column(id = "is_totalvalue", datatype = "char1")
	private java.lang.String isTotalvalue;

	/**
	 *是否通用 （Y-通用性，N-个性化）
	 */
	@Column(id = "is_public", datatype = "char1")
	private java.lang.String isPublic;

	/**
	 *版本
	 */
	@Column(id = "version", datatype = "string20")
	private java.lang.String version;

	/**
	 *绩效分类编号
	 */
	@Column(id = "point_type_id", datatype = "string32")
	private java.lang.String pointTypeId;

	/**
	 *父考核项id
	 */
	@Column(id = "parent_item_id", datatype = "string64")
	private java.lang.String parentItemId;

	/**
	 *是否是父项
	 */
	@Column(id = "is_parent", datatype = "char1")
	private java.lang.String isParent;

	/**
	 *分数
	 */
	@Column(id = "value", datatype = "smalldouble")
	private java.lang.Double value;

	@Column(id = "proportion", datatype = "string5")
	private java.lang.String proportion;

	/**
	 * 设置考评项目编号，通过行政区划代码+序列SEQ_EVAL_ITEM取值
	 */
	public void setItemId(java.lang.String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 获取考评项目编号，通过行政区划代码+序列SEQ_EVAL_ITEM取值
	 */
	public java.lang.String getItemId() {
		return itemId;
	}

	/**
	 * 设置考评项目名称
	 */
	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取考评项目名称
	 */
	public java.lang.String getItemName() {
		return itemName;
	}

	/**
	 * 设置最小值
	 */
	public void setMinValue(java.lang.Double minValue) {
		this.minValue = minValue;
	}

	/**
	 * 获取最小值
	 */
	public java.lang.Double getMinValue() {
		return minValue;
	}

	/**
	 * 设置最大值
	 */
	public void setMaxValue(java.lang.Double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * 获取最大值
	 */
	public java.lang.Double getMaxValue() {
		return maxValue;
	}

	/**
	 * 设置高于最大值取值
	 */
	public void setMaxvalDepend(java.lang.String maxvalDepend) {
		this.maxvalDepend = maxvalDepend;
	}

	/**
	 * 获取高于最大值取值
	 */
	public java.lang.String getMaxvalDepend() {
		return maxvalDepend;
	}

	/**
	 * 设置低于最小值取值
	 */
	public void setMinvalDepend(java.lang.String minvalDepend) {
		this.minvalDepend = minvalDepend;
	}

	/**
	 * 获取低于最小值取值
	 */
	public java.lang.String getMinvalDepend() {
		return minvalDepend;
	}

	/**
	 * 设置考评公式
	 */
	public void setItemFormula(java.lang.String itemFormula) {
		this.itemFormula = itemFormula;
	}

	/**
	 * 获取考评公式
	 */
	public java.lang.String getItemFormula() {
		return itemFormula;
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
	 * 设置是否启用
	 */
	public void setIsUsed(java.lang.String isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * 获取是否启用
	 */
	public java.lang.String getIsUsed() {
		return isUsed;
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
	 * 设置是否最终得分
	 */
	public void setIsTotalvalue(java.lang.String isTotalvalue) {
		this.isTotalvalue = isTotalvalue;
	}

	/**
	 * 获取是否最终得分
	 */
	public java.lang.String getIsTotalvalue() {
		return isTotalvalue;
	}

	/**
	 * 设置是否通用 （Y-通用性，N-个性化）
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取是否通用 （Y-通用性，N-个性化）
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置版本
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	/**
	 * 获取版本
	 */
	public java.lang.String getVersion() {
		return version;
	}

	/**
	 * 设置绩效分类编号
	 */
	public void setPointTypeId(java.lang.String pointTypeId) {
		this.pointTypeId = pointTypeId;
	}

	/**
	 * 获取绩效分类编号
	 */
	public java.lang.String getPointTypeId() {
		return pointTypeId;
	}

	/**
	 * 设置父考核项id
	 */
	public void setParentItemId(java.lang.String parentItemId) {
		this.parentItemId = parentItemId;
	}

	/**
	 * 获取父考核项id
	 */
	public java.lang.String getParentItemId() {
		return parentItemId;
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

	/**
	 * 设置分数
	 */
	public void setValue(java.lang.Double value) {
		this.value = value;
	}

	/**
	 * 获取分数
	 */
	public java.lang.Double getValue() {
		return value;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProportion(java.lang.String proportion) {
		this.proportion = proportion;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProportion() {
		return proportion;
	}
}
