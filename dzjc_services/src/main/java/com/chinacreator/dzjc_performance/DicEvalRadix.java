package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效基数管理
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.dicEvalRadix", table = "TA_JC_DIC_EVAL_RADIX", ds = "acceptdata", cache = false)
public class DicEvalRadix {
	/**
	 *基数ID，通过行政区划代码+序列SEQ_RADIX取值
	 */
	@Column(id = "radix_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String radixId;

	/**
	 *基数名称
	 */
	@Column(id = "radix_name", datatype = "string64")
	private java.lang.String radixName;

	/**
	 *基数SQL
	 */
	@Column(id = "radix_sql", datatype = "string1536")
	private java.lang.String radixSql;

	/**
	 *是否有效，取值为Y/N
	 */
	@Column(id = "is_valid", datatype = "string10")
	private java.lang.String isValid;

	/**
	 *业务类型，通过TA_DIC_BUSINESS_TYPE取值
	 */
	@Column(id = "business_type_code", datatype = "char6")
	private java.lang.String businessTypeCode;

	/**
	 *行政区划代码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

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
	@Column(id = "is_public", datatype = "string10")
	private java.lang.String isPublic;

	/**
	 *版本号
	 */
	@Column(id = "version", datatype = "string20")
	private java.lang.String version;

	/**
	 *排序号
	 */
	@Column(id = "order_no", datatype = "smallint")
	private java.lang.Integer orderNo;

	/**
	 * 设置基数ID，通过行政区划代码+序列SEQ_RADIX取值
	 */
	public void setRadixId(java.lang.String radixId) {
		this.radixId = radixId;
	}

	/**
	 * 获取基数ID，通过行政区划代码+序列SEQ_RADIX取值
	 */
	public java.lang.String getRadixId() {
		return radixId;
	}

	/**
	 * 设置基数名称
	 */
	public void setRadixName(java.lang.String radixName) {
		this.radixName = radixName;
	}

	/**
	 * 获取基数名称
	 */
	public java.lang.String getRadixName() {
		return radixName;
	}

	/**
	 * 设置基数SQL
	 */
	public void setRadixSql(java.lang.String radixSql) {
		this.radixSql = radixSql;
	}

	/**
	 * 获取基数SQL
	 */
	public java.lang.String getRadixSql() {
		return radixSql;
	}

	/**
	 * 设置是否有效，取值为Y/N
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效，取值为Y/N
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置业务类型，通过TA_DIC_BUSINESS_TYPE取值
	 */
	public void setBusinessTypeCode(java.lang.String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	/**
	 * 获取业务类型，通过TA_DIC_BUSINESS_TYPE取值
	 */
	public java.lang.String getBusinessTypeCode() {
		return businessTypeCode;
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
	 * 设置版本号
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	/**
	 * 获取版本号
	 */
	public java.lang.String getVersion() {
		return version;
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
}
