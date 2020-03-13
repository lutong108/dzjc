package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察结果
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement", table = "TA_JC_RULE_ELEM_RESULT", ds = "acceptdata", cache = false)
public class ResultElement {
	/**
	 *要素计算ID
	 */
	@Column(id = "rlt_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String rltId;

	/**
	 *监察要素ID，通过序列SEQ_JC_RULE_ELEMENT取值
	 */
	@Column(id = "elem_id", datatype = "string64")
	private java.lang.String elemId;

	/**
	 *业务ID
	 */
	@Column(id = "business_id", datatype = "string512")
	private java.lang.String businessId;

	/**
	 *计算结果
	 */
	@Column(id = "rlt_value", datatype = "string64")
	private java.lang.String rltValue;

	/**
	 *计算结果描述
	 */
	@Column(id = "rlt_memo", datatype = "string256")
	private java.lang.String rltMemo;

	/**
	 *是否办理时限监察，Y表示是
	 */
	@Column(id = "is_timesupervise", datatype = "string5")
	private java.lang.String isTimesupervise;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *监察类型
	 */
	@Column(id = "business_type", datatype = "string20")
	private java.lang.String businessType;

	/**
	 *本次发牌批次id，用于区分同一次发牌
	 */
	@Column(id = "batch_id", datatype = "string64")
	private java.lang.String batchId;

	/**
	 *发牌时特别程序计算时间
	 */
	@Column(id = "tbcx_times", datatype = "string128")
	private java.lang.String tbcxTimes;

	/**
	 * 设置要素计算ID
	 */
	public void setRltId(java.lang.String rltId) {
		this.rltId = rltId;
	}

	/**
	 * 获取要素计算ID
	 */
	public java.lang.String getRltId() {
		return rltId;
	}

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
	 * 设置业务ID
	 */
	public void setBusinessId(java.lang.String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取业务ID
	 */
	public java.lang.String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置计算结果
	 */
	public void setRltValue(java.lang.String rltValue) {
		this.rltValue = rltValue;
	}

	/**
	 * 获取计算结果
	 */
	public java.lang.String getRltValue() {
		return rltValue;
	}

	/**
	 * 设置计算结果描述
	 */
	public void setRltMemo(java.lang.String rltMemo) {
		this.rltMemo = rltMemo;
	}

	/**
	 * 获取计算结果描述
	 */
	public java.lang.String getRltMemo() {
		return rltMemo;
	}

	/**
	 * 设置是否办理时限监察，Y表示是
	 */
	public void setIsTimesupervise(java.lang.String isTimesupervise) {
		this.isTimesupervise = isTimesupervise;
	}

	/**
	 * 获取是否办理时限监察，Y表示是
	 */
	public java.lang.String getIsTimesupervise() {
		return isTimesupervise;
	}

	/**
	 * 设置区域编码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取区域编码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}

	/**
	 * 设置监察类型
	 */
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 获取监察类型
	 */
	public java.lang.String getBusinessType() {
		return businessType;
	}

	/**
	 * 设置本次发牌批次id，用于区分同一次发牌
	 */
	public void setBatchId(java.lang.String batchId) {
		this.batchId = batchId;
	}

	/**
	 * 获取本次发牌批次id，用于区分同一次发牌
	 */
	public java.lang.String getBatchId() {
		return batchId;
	}

	/**
	 * 设置发牌时特别程序计算时间
	 */
	public void setTbcxTimes(java.lang.String tbcxTimes) {
		this.tbcxTimes = tbcxTimes;
	}

	/**
	 * 获取发牌时特别程序计算时间
	 */
	public java.lang.String getTbcxTimes() {
		return tbcxTimes;
	}
}
