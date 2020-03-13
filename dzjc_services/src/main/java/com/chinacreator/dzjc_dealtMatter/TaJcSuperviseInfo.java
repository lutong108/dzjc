package com.chinacreator.dzjc_dealtMatter;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 发牌信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_dealtMatter.taJcSuperviseInfo", table = "TA_JC_SUPERVISE_INFO", ds = "acceptdata", cache = false)
public class TaJcSuperviseInfo {
	/**
	 *主键ID，通过序列SEQ_SUPERVISE_INFO取值，从其他系统交换的数据保持原值，形式为行政区划代码+序列值
	 */
	@Column(id = "supervise_info_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String superviseInfoId;

	/**
	 *业务ID
	 */
	@Column(id = "business_id", datatype = "string1024")
	private java.lang.String businessId;

	/**
	 *接收单位名称
	 */
	@Column(id = "receive_org_name", datatype = "string128")
	private java.lang.String receiveOrgName;

	/**
	 *监察类型代码
	 */
	@Column(id = "supervise_type_no", datatype = "string20")
	private java.lang.String superviseTypeNo;

	/**
	 *监察结果
	 */
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *监察描述
	 */
	@Column(id = "supervise_descript", datatype = "string512")
	private java.lang.String superviseDescript;

	/**
	 *监察依据
	 */
	@Column(id = "supervise_gist", datatype = "string512")
	private java.lang.String superviseGist;

	/**
	 *督办信息内容
	 */
	@Column(id = "warning_content", datatype = "string1024")
	private java.lang.String warningContent;

	/**
	 *责任环节代码
	 */
	@Column(id = "duty_tache_code", datatype = "string20")
	private java.lang.String dutyTacheCode;

	/**
	 *规则ID
	 */
	@Column(id = "rule_id", datatype = "string64")
	private java.lang.String ruleId;

	/**
	 *反馈终止时间
	 */
	@Column(id = "feedback_endtime", datatype = "date")
	private java.sql.Date feedbackEndtime;

	/**
	 *预警状态(已发Y,待发D,撤销C,取消Q，督办B)
	 */
	@Column(id = "state", datatype = "string20")
	private java.lang.String state;

	/**
	 *最后更新时间
	 */
	@Column(id = "last_updatetime", datatype = "date")
	private java.sql.Date lastUpdatetime;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *预留字段A
	 */
	@Column(id = "remarka", datatype = "string256")
	private java.lang.String remarka;

	/**
	 *预留字段B
	 */
	@Column(id = "remarkb", datatype = "string256")
	private java.lang.String remarkb;

	/**
	 *规则版本号
	 */
	@Column(id = "rule_version", datatype = "string32")
	private java.lang.String ruleVersion;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *特殊程序监察的业务ID形式
	 */
	@Column(id = "tscx_code", datatype = "string1024")
	private java.lang.String tscxCode;

	@Column(id = "pici", datatype = "string10")
	private java.lang.String pici;

	/**
	 * 设置主键ID，通过序列SEQ_SUPERVISE_INFO取值，从其他系统交换的数据保持原值，形式为行政区划代码+序列值
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取主键ID，通过序列SEQ_SUPERVISE_INFO取值，从其他系统交换的数据保持原值，形式为行政区划代码+序列值
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
	 * 设置接收单位名称
	 */
	public void setReceiveOrgName(java.lang.String receiveOrgName) {
		this.receiveOrgName = receiveOrgName;
	}

	/**
	 * 获取接收单位名称
	 */
	public java.lang.String getReceiveOrgName() {
		return receiveOrgName;
	}

	/**
	 * 设置监察类型代码
	 */
	public void setSuperviseTypeNo(java.lang.String superviseTypeNo) {
		this.superviseTypeNo = superviseTypeNo;
	}

	/**
	 * 获取监察类型代码
	 */
	public java.lang.String getSuperviseTypeNo() {
		return superviseTypeNo;
	}

	/**
	 * 设置监察结果
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取监察结果
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
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
	 * 设置监察描述
	 */
	public void setSuperviseDescript(java.lang.String superviseDescript) {
		this.superviseDescript = superviseDescript;
	}

	/**
	 * 获取监察描述
	 */
	public java.lang.String getSuperviseDescript() {
		return superviseDescript;
	}

	/**
	 * 设置监察依据
	 */
	public void setSuperviseGist(java.lang.String superviseGist) {
		this.superviseGist = superviseGist;
	}

	/**
	 * 获取监察依据
	 */
	public java.lang.String getSuperviseGist() {
		return superviseGist;
	}

	/**
	 * 设置督办信息内容
	 */
	public void setWarningContent(java.lang.String warningContent) {
		this.warningContent = warningContent;
	}

	/**
	 * 获取督办信息内容
	 */
	public java.lang.String getWarningContent() {
		return warningContent;
	}

	/**
	 * 设置责任环节代码
	 */
	public void setDutyTacheCode(java.lang.String dutyTacheCode) {
		this.dutyTacheCode = dutyTacheCode;
	}

	/**
	 * 获取责任环节代码
	 */
	public java.lang.String getDutyTacheCode() {
		return dutyTacheCode;
	}

	/**
	 * 设置规则ID
	 */
	public void setRuleId(java.lang.String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 获取规则ID
	 */
	public java.lang.String getRuleId() {
		return ruleId;
	}

	/**
	 * 设置反馈终止时间
	 */
	public void setFeedbackEndtime(java.sql.Date feedbackEndtime) {
		this.feedbackEndtime = feedbackEndtime;
	}

	/**
	 * 获取反馈终止时间
	 */
	public java.sql.Date getFeedbackEndtime() {
		return feedbackEndtime;
	}

	/**
	 * 设置预警状态(已发Y,待发D,撤销C,取消Q，督办B)
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取预警状态(已发Y,待发D,撤销C,取消Q，督办B)
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * 设置最后更新时间
	 */
	public void setLastUpdatetime(java.sql.Date lastUpdatetime) {
		this.lastUpdatetime = lastUpdatetime;
	}

	/**
	 * 获取最后更新时间
	 */
	public java.sql.Date getLastUpdatetime() {
		return lastUpdatetime;
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
	 * 设置预留字段A
	 */
	public void setRemarka(java.lang.String remarka) {
		this.remarka = remarka;
	}

	/**
	 * 获取预留字段A
	 */
	public java.lang.String getRemarka() {
		return remarka;
	}

	/**
	 * 设置预留字段B
	 */
	public void setRemarkb(java.lang.String remarkb) {
		this.remarkb = remarkb;
	}

	/**
	 * 获取预留字段B
	 */
	public java.lang.String getRemarkb() {
		return remarkb;
	}

	/**
	 * 设置规则版本号
	 */
	public void setRuleVersion(java.lang.String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}

	/**
	 * 获取规则版本号
	 */
	public java.lang.String getRuleVersion() {
		return ruleVersion;
	}

	/**
	 * 设置机构ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置特殊程序监察的业务ID形式
	 */
	public void setTscxCode(java.lang.String tscxCode) {
		this.tscxCode = tscxCode;
	}

	/**
	 * 获取特殊程序监察的业务ID形式
	 */
	public java.lang.String getTscxCode() {
		return tscxCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setPici(java.lang.String pici) {
		this.pici = pici;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getPici() {
		return pici;
	}
}
