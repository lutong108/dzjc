package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 热点事项-事项
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.HotApproveInfo", table = "V_SP_APPROVE_INFO", ds = "acceptdata", cache = false)
public class HotApproveInfo {
	/**
	 *实施机关id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *目录id
	 */
	@Column(id = "rights_id", datatype = "string64")
	private java.lang.String rightsId;

	/**
	 *事项id
	 */
	@Column(id = "approve_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String approveId;

	/**
	 *实施编码
	 */
	@Column(id = "approve_code", datatype = "string64")
	private java.lang.String approveCode;

	/**
	 *业务办理项编码
	 */
	@Column(id = "taskhandleitem", datatype = "string64")
	private java.lang.String taskhandleitem;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string1024")
	private java.lang.String approveName;

	/**
	 *实施机关名称
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 *事项类型
	 */
	@Column(id = "type_code", datatype = "string5")
	private java.lang.String typeCode;

	/**
	 *行驶层级
	 */
	@Column(id = "approve_source", datatype = "string20")
	private java.lang.String approveSource;

	/**
	 *收件数
	 */
	@Column(id = "sjs_num", datatype = "bigdouble")
	private java.lang.Double sjsNum;

	/**
	 *办结数
	 */
	@Column(id = "bjs_num", datatype = "bigdouble")
	private java.lang.Double bjsNum;

	/**
	 *红牌
	 */
	@Column(id = "red_num", datatype = "bigdouble")
	private java.lang.Double redNum;

	/**
	 *黄牌
	 */
	@Column(id = "yellow_num", datatype = "bigdouble")
	private java.lang.Double yellowNum;

	/**
	 *预警牌
	 */
	@Column(id = "green_num", datatype = "bigdouble")
	private java.lang.Double greenNum;

	/**
	 *受理开始时间
	 */
	@Column(id = "beginDate", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *受理结束时间
	 */
	@Column(id = "endDate", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *监管开始时间
	 */
	@Column(id = "jcBeginDate", datatype = "date")
	private java.sql.Date jcBeginDate;

	/**
	 *监管结束时间
	 */
	@Column(id = "jcEndDate", datatype = "date")
	private java.sql.Date jcEndDate;

	/**
	 * 设置实施机关id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取实施机关id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置目录id
	 */
	public void setRightsId(java.lang.String rightsId) {
		this.rightsId = rightsId;
	}

	/**
	 * 获取目录id
	 */
	public java.lang.String getRightsId() {
		return rightsId;
	}

	/**
	 * 设置事项id
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取事项id
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置实施编码
	 */
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 获取实施编码
	 */
	public java.lang.String getApproveCode() {
		return approveCode;
	}

	/**
	 * 设置业务办理项编码
	 */
	public void setTaskhandleitem(java.lang.String taskhandleitem) {
		this.taskhandleitem = taskhandleitem;
	}

	/**
	 * 获取业务办理项编码
	 */
	public java.lang.String getTaskhandleitem() {
		return taskhandleitem;
	}

	/**
	 * 设置事项名称
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取事项名称
	 */
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	 * 设置实施机关名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取实施机关名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置事项类型
	 */
	public void setTypeCode(java.lang.String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 获取事项类型
	 */
	public java.lang.String getTypeCode() {
		return typeCode;
	}

	/**
	 * 设置行驶层级
	 */
	public void setApproveSource(java.lang.String approveSource) {
		this.approveSource = approveSource;
	}

	/**
	 * 获取行驶层级
	 */
	public java.lang.String getApproveSource() {
		return approveSource;
	}

	/**
	 * 设置收件数
	 */
	public void setSjsNum(java.lang.Double sjsNum) {
		this.sjsNum = sjsNum;
	}

	/**
	 * 获取收件数
	 */
	public java.lang.Double getSjsNum() {
		return sjsNum;
	}

	/**
	 * 设置办结数
	 */
	public void setBjsNum(java.lang.Double bjsNum) {
		this.bjsNum = bjsNum;
	}

	/**
	 * 获取办结数
	 */
	public java.lang.Double getBjsNum() {
		return bjsNum;
	}

	/**
	 * 设置红牌
	 */
	public void setRedNum(java.lang.Double redNum) {
		this.redNum = redNum;
	}

	/**
	 * 获取红牌
	 */
	public java.lang.Double getRedNum() {
		return redNum;
	}

	/**
	 * 设置黄牌
	 */
	public void setYellowNum(java.lang.Double yellowNum) {
		this.yellowNum = yellowNum;
	}

	/**
	 * 获取黄牌
	 */
	public java.lang.Double getYellowNum() {
		return yellowNum;
	}

	/**
	 * 设置预警牌
	 */
	public void setGreenNum(java.lang.Double greenNum) {
		this.greenNum = greenNum;
	}

	/**
	 * 获取预警牌
	 */
	public java.lang.Double getGreenNum() {
		return greenNum;
	}

	/**
	 * 设置受理开始时间
	 */
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取受理开始时间
	 */
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置受理结束时间
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取受理结束时间
	 */
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置监管开始时间
	 */
	public void setJcBeginDate(java.sql.Date jcBeginDate) {
		this.jcBeginDate = jcBeginDate;
	}

	/**
	 * 获取监管开始时间
	 */
	public java.sql.Date getJcBeginDate() {
		return jcBeginDate;
	}

	/**
	 * 设置监管结束时间
	 */
	public void setJcEndDate(java.sql.Date jcEndDate) {
		this.jcEndDate = jcEndDate;
	}

	/**
	 * 获取监管结束时间
	 */
	public java.sql.Date getJcEndDate() {
		return jcEndDate;
	}
}
