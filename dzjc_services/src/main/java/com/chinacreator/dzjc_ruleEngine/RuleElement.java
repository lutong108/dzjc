package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察要素
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.RuleElement", table = "TA_JC_RULE_ELEMENT", ds = "acceptdata")
public class RuleElement implements Serializable {
	private static final long serialVersionUID = 2618016843300864L;
	/**
	 *监察要素ID，通过序列SEQ_JC_RULE_ELEMENT取值
	 */
	@Column(id = "elem_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String elemId;

	/**
	 *监察要素名称
	 */
	@Column(id = "elem_name", datatype = "string128")
	private java.lang.String elemName;

	/**
	 *所属业务类型，取值参考业务类型表
	 */
	@Column(id = "business_type_code", datatype = "string64")
	private java.lang.String businessTypeCode;

	/**
	 *监察要素计算逻辑
	 */
	@Column(id = "elem_caculator", datatype = "string256")
	private java.lang.String elemCaculator;

	/**
	 *是否有效
	 */
	@Column(id = "is_valid", datatype = "string64")
	private java.lang.String isValid;

	/**
	 *结果类型 1, 数值 2, boolean
	 */
	@Column(id = "result_type", datatype = "string64")
	private java.lang.String resultType;

	/**
	 *监察要素结果值说明
	 */
	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	/**
	 *区域编号
	 */
	@Column(id = "area_code", datatype = "string32")
	private java.lang.String areaCode;

	/**
	 *是否是公用  Y-公用  N-私有
	 */
	@Column(id = "is_public", datatype = "string64")
	private java.lang.String isPublic;

	/**
	 *所属业务类型名称
	 */
	@Column(id = "businessTypeName", datatype = "string64")
	private java.lang.String businessTypeName;

	/**
	 * 设置监察要素ID，通过序列SEQ_JC_RULE_ELEMENT取值
	 */
	public void setElemId(java.lang.String elemId) {
		this.elemId = elemId;
	}

	/**
	 * 获取监察要素ID，通过序列SEQ_JC_RULE_ELEMENT取值
	 */
	public java.lang.String getElemId() {
		return elemId;
	}

	/**
	 * 设置监察要素名称
	 */
	public void setElemName(java.lang.String elemName) {
		this.elemName = elemName;
	}

	/**
	 * 获取监察要素名称
	 */
	public java.lang.String getElemName() {
		return elemName;
	}

	/**
	 * 设置所属业务类型，取值参考业务类型表
	 */
	public void setBusinessTypeCode(java.lang.String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	/**
	 * 获取所属业务类型，取值参考业务类型表
	 */
	public java.lang.String getBusinessTypeCode() {
		return businessTypeCode;
	}

	/**
	 * 设置监察要素计算逻辑
	 */
	public void setElemCaculator(java.lang.String elemCaculator) {
		this.elemCaculator = elemCaculator;
	}

	/**
	 * 获取监察要素计算逻辑
	 */
	public java.lang.String getElemCaculator() {
		return elemCaculator;
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
	 * 设置结果类型 1, 数值 2, boolean
	 */
	public void setResultType(java.lang.String resultType) {
		this.resultType = resultType;
	}

	/**
	 * 获取结果类型 1, 数值 2, boolean
	 */
	public java.lang.String getResultType() {
		return resultType;
	}

	/**
	 * 设置监察要素结果值说明
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取监察要素结果值说明
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * 设置区域编号
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取区域编号
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置是否是公用  Y-公用  N-私有
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取是否是公用  Y-公用  N-私有
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置所属业务类型名称
	 */
	public void setBusinessTypeName(java.lang.String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	/**
	 * 获取所属业务类型名称
	 */
	public java.lang.String getBusinessTypeName() {
		return businessTypeName;
	}
}
