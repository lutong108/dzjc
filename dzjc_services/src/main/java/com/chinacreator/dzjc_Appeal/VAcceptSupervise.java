package com.chinacreator.dzjc_Appeal;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 发牌信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_Appeal.VAcceptSupervise", table = "V_ACCEPT_SUPERVISE", ds = "acceptdata", cache = false)
public class VAcceptSupervise {
	/**
	 *标题
	 */
	@Column(id = "title", datatype = "string512")
	private java.lang.String title;

	/**
	 *接收机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *接收机构code
	 */
	@Column(id = "org_code", datatype = "string512")
	private java.lang.String orgCode;

	/**
	 *接收机构名称
	 */
	@Column(id = "org_name", datatype = "string2000")
	private java.lang.String orgName;

	/**
	 *发牌信息ID
	 */
	@Column(id = "supervise_info_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String superviseInfoId;

	/**
	 *业务ID
	 */
	@Column(id = "business_id", datatype = "string1024")
	private java.lang.String businessId;

	/**
	 *业务类型ID
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *业务类型名称
	 */
	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	/**
	 *发牌类型ID
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *发牌类型名称
	 */
	@Column(id = "supervise_result_name", datatype = "string32")
	private java.lang.String superviseResultName;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *申诉状态
	 */
	@Column(id = "su_state", datatype = "string10")
	private java.lang.String suState;

	/**
	 *发牌状态
	 */
	@Column(id = "fp_state", datatype = "string5")
	private java.lang.String fpState;

	/**
	 *查询编码
	 */
	@Column(id = "query_code", datatype = "string128")
	private java.lang.String queryCode;

	/**
	 * 设置标题
	 */
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * 获取标题
	 */
	public java.lang.String getTitle() {
		return title;
	}

	/**
	 * 设置接收机构ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取接收机构ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置接收机构code
	 */
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取接收机构code
	 */
	public java.lang.String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置接收机构名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取接收机构名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置发牌信息ID
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取发牌信息ID
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
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
	 * 设置业务类型ID
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取业务类型ID
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置业务类型名称
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取业务类型名称
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
	}

	/**
	 * 设置发牌类型ID
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取发牌类型ID
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置发牌类型名称
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取发牌类型名称
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
	}

	/**
	 * 设置监察时间
	 */
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取监察时间
	 */
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置申诉状态
	 */
	public void setSuState(java.lang.String suState) {
		this.suState = suState;
	}

	/**
	 * 获取申诉状态
	 */
	public java.lang.String getSuState() {
		return suState;
	}

	/**
	 * 设置发牌状态
	 */
	public void setFpState(java.lang.String fpState) {
		this.fpState = fpState;
	}

	/**
	 * 获取发牌状态
	 */
	public java.lang.String getFpState() {
		return fpState;
	}

	/**
	 * 设置查询编码
	 */
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}

	/**
	 * 获取查询编码
	 */
	public java.lang.String getQueryCode() {
		return queryCode;
	}
}
