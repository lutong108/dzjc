package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效登记
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.evalCheckin", table = "TA_JC_EVAL_CHECKIN", ds = "acceptdata", cache = false)
public class EvalCheckin {
	/**
	 *记录ID，通过行政区划代码+序列SEQ_CHECKIN取值
	 */
	@Column(id = "record_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String recordId;

	/**
	 *考核单位
	 */
	@Column(id = "org_code", datatype = "string64")
	private java.lang.String orgCode;

	/**
	 *考评指标编号
	 */
	@Column(id = "evaluate_point_no", datatype = "string64")
	private java.lang.String evaluatePointNo;

	/**
	 *考评说明
	 */
	@Column(id = "evaluate_explain", datatype = "string512")
	private java.lang.String evaluateExplain;

	/**
	 *考评时间
	 */
	@Column(id = "evaluate_time", datatype = "date")
	private java.sql.Date evaluateTime;

	/**
	 *考评人
	 */
	@Column(id = "evaluate_man", datatype = "string128")
	private java.lang.String evaluateMan;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *行政区划代码
	 */
	@Column(id = "area_code", datatype = "string20")
	private java.lang.String areaCode;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 * 设置记录ID，通过行政区划代码+序列SEQ_CHECKIN取值
	 */
	public void setRecordId(java.lang.String recordId) {
		this.recordId = recordId;
	}

	/**
	 * 获取记录ID，通过行政区划代码+序列SEQ_CHECKIN取值
	 */
	public java.lang.String getRecordId() {
		return recordId;
	}

	/**
	 * 设置考核单位
	 */
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取考核单位
	 */
	public java.lang.String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置考评指标编号
	 */
	public void setEvaluatePointNo(java.lang.String evaluatePointNo) {
		this.evaluatePointNo = evaluatePointNo;
	}

	/**
	 * 获取考评指标编号
	 */
	public java.lang.String getEvaluatePointNo() {
		return evaluatePointNo;
	}

	/**
	 * 设置考评说明
	 */
	public void setEvaluateExplain(java.lang.String evaluateExplain) {
		this.evaluateExplain = evaluateExplain;
	}

	/**
	 * 获取考评说明
	 */
	public java.lang.String getEvaluateExplain() {
		return evaluateExplain;
	}

	/**
	 * 设置考评时间
	 */
	public void setEvaluateTime(java.sql.Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	/**
	 * 获取考评时间
	 */
	public java.sql.Date getEvaluateTime() {
		return evaluateTime;
	}

	/**
	 * 设置考评人
	 */
	public void setEvaluateMan(java.lang.String evaluateMan) {
		this.evaluateMan = evaluateMan;
	}

	/**
	 * 获取考评人
	 */
	public java.lang.String getEvaluateMan() {
		return evaluateMan;
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
	 * 设置机构id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}
}
