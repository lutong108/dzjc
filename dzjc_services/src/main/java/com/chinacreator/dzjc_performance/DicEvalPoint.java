package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效考评指标字典
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.dicEvalPoint", table = "TA_JC_DIC_EVALPOINT", ds = "acceptdata", cache = false)
public class DicEvalPoint {
	/**
	 *指标编号，通过行政区划代码+序列SEQ_EVAL_POINT取值
	 */
	@Column(id = "point_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String pointId;

	/**
	 *指标排序号
	 */
	@Column(id = "point_sn", datatype = "smallint")
	private java.lang.Integer pointSn;

	/**
	 *绩效分类编号
	 */
	@Column(id = "point_type_id", datatype = "string64")
	private java.lang.String pointTypeId;

	/**
	 *指标名称
	 */
	@Column(id = "point_name", datatype = "string256")
	private java.lang.String pointName;

	/**
	 *打分类型，通过TA_DIC_GRADE_TYPE取值
	 */
	@Column(id = "eval_type", datatype = "string10")
	private java.lang.String evalType;

	/**
	 *打分方式，通过TA_DIC_GRADE_METHOD取值
	 */
	@Column(id = "eval_mode", datatype = "string10")
	private java.lang.String evalMode;

	/**
	 *计算公式
	 */
	@Column(id = "eval_formula", datatype = "string512")
	private java.lang.String evalFormula;

	/**
	 *行政区划代码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *是否启用
	 */
	@Column(id = "is_used", datatype = "char1")
	private java.lang.String isUsed;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

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
	 *最大值依赖
	 */
	@Column(id = "maxval_depend", datatype = "string20")
	private java.lang.String maxvalDepend;

	/**
	 *最小值依赖
	 */
	@Column(id = "minval_depend", datatype = "string20")
	private java.lang.String minvalDepend;

	/**
	 *操作方式（Y-自动，N-人工）
	 */
	@Column(id = "is_auto", datatype = "char1")
	private java.lang.String isAuto;

	/**
	 * 设置指标编号，通过行政区划代码+序列SEQ_EVAL_POINT取值
	 */
	public void setPointId(java.lang.String pointId) {
		this.pointId = pointId;
	}

	/**
	 * 获取指标编号，通过行政区划代码+序列SEQ_EVAL_POINT取值
	 */
	public java.lang.String getPointId() {
		return pointId;
	}

	/**
	 * 设置指标排序号
	 */
	public void setPointSn(java.lang.Integer pointSn) {
		this.pointSn = pointSn;
	}

	/**
	 * 获取指标排序号
	 */
	public java.lang.Integer getPointSn() {
		return pointSn;
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
	 * 设置指标名称
	 */
	public void setPointName(java.lang.String pointName) {
		this.pointName = pointName;
	}

	/**
	 * 获取指标名称
	 */
	public java.lang.String getPointName() {
		return pointName;
	}

	/**
	 * 设置打分类型，通过TA_DIC_GRADE_TYPE取值
	 */
	public void setEvalType(java.lang.String evalType) {
		this.evalType = evalType;
	}

	/**
	 * 获取打分类型，通过TA_DIC_GRADE_TYPE取值
	 */
	public java.lang.String getEvalType() {
		return evalType;
	}

	/**
	 * 设置打分方式，通过TA_DIC_GRADE_METHOD取值
	 */
	public void setEvalMode(java.lang.String evalMode) {
		this.evalMode = evalMode;
	}

	/**
	 * 获取打分方式，通过TA_DIC_GRADE_METHOD取值
	 */
	public java.lang.String getEvalMode() {
		return evalMode;
	}

	/**
	 * 设置计算公式
	 */
	public void setEvalFormula(java.lang.String evalFormula) {
		this.evalFormula = evalFormula;
	}

	/**
	 * 获取计算公式
	 */
	public java.lang.String getEvalFormula() {
		return evalFormula;
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
	 * 设置最大值依赖
	 */
	public void setMaxvalDepend(java.lang.String maxvalDepend) {
		this.maxvalDepend = maxvalDepend;
	}

	/**
	 * 获取最大值依赖
	 */
	public java.lang.String getMaxvalDepend() {
		return maxvalDepend;
	}

	/**
	 * 设置最小值依赖
	 */
	public void setMinvalDepend(java.lang.String minvalDepend) {
		this.minvalDepend = minvalDepend;
	}

	/**
	 * 获取最小值依赖
	 */
	public java.lang.String getMinvalDepend() {
		return minvalDepend;
	}

	/**
	 * 设置操作方式（Y-自动，N-人工）
	 */
	public void setIsAuto(java.lang.String isAuto) {
		this.isAuto = isAuto;
	}

	/**
	 * 获取操作方式（Y-自动，N-人工）
	 */
	public java.lang.String getIsAuto() {
		return isAuto;
	}
}
