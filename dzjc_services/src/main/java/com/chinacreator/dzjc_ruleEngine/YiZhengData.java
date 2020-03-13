package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 益政经办人接口数据
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.YiZhengData", table = "TA_JC_YIZHENG_DATA", ds = "acceptdata", cache = false)
public class YiZhengData {
	/**
	 *主键
	 */
	@Column(id = "data_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String dataId;

	/**
	 *流程唯一id
	 */
	@Column(id = "mark_id", datatype = "string64")
	private java.lang.String markId;

	/**
	 *接口调用标识：1：成功，0：失败
	 */
	@Column(id = "ok", datatype = "string5")
	private java.lang.String ok;

	/**
	 *错误编码
	 */
	@Column(id = "code", datatype = "string64")
	private java.lang.String code;

	/**
	 *错误信息
	 */
	@Column(id = "msg", datatype = "string512")
	private java.lang.String msg;

	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *节点主键
	 */
	@Column(id = "node_id", datatype = "string64")
	private java.lang.String nodeId;

	/**
	 *节点名称
	 */
	@Column(id = "node_name", datatype = "string64")
	private java.lang.String nodeName;

	/**
	 *归属地主键
	 */
	@Column(id = "region_id", datatype = "string64")
	private java.lang.String regionId;

	/**
	 *归属地名称
	 */
	@Column(id = "region_name", datatype = "string64")
	private java.lang.String regionName;

	/**
	 *经办人主键
	 */
	@Column(id = "transactor_id", datatype = "string64")
	private java.lang.String transactorId;

	/**
	 *经办人名称
	 */
	@Column(id = "transactor_name", datatype = "string64")
	private java.lang.String transactorName;

	/**
	 *经办人手机号
	 */
	@Column(id = "phone", datatype = "string20")
	private java.lang.String phone;

	/**
	 *创建时间
	 */
	@Column(id = "create_date", datatype = "date")
	private java.sql.Date createDate;

	/**
	 *环节id
	 */
	@Column(id = "opinion_id", datatype = "string64")
	private java.lang.String opinionId;

	/**
	 * 设置主键
	 */
	public void setDataId(java.lang.String dataId) {
		this.dataId = dataId;
	}

	/**
	 * 获取主键
	 */
	public java.lang.String getDataId() {
		return dataId;
	}

	/**
	 * 设置流程唯一id
	 */
	public void setMarkId(java.lang.String markId) {
		this.markId = markId;
	}

	/**
	 * 获取流程唯一id
	 */
	public java.lang.String getMarkId() {
		return markId;
	}

	/**
	 * 设置接口调用标识：1：成功，0：失败
	 */
	public void setOk(java.lang.String ok) {
		this.ok = ok;
	}

	/**
	 * 获取接口调用标识：1：成功，0：失败
	 */
	public java.lang.String getOk() {
		return ok;
	}

	/**
	 * 设置错误编码
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * 获取错误编码
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * 设置错误信息
	 */
	public void setMsg(java.lang.String msg) {
		this.msg = msg;
	}

	/**
	 * 获取错误信息
	 */
	public java.lang.String getMsg() {
		return msg;
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

	/**
	 * 设置节点主键
	 */
	public void setNodeId(java.lang.String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * 获取节点主键
	 */
	public java.lang.String getNodeId() {
		return nodeId;
	}

	/**
	 * 设置节点名称
	 */
	public void setNodeName(java.lang.String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * 获取节点名称
	 */
	public java.lang.String getNodeName() {
		return nodeName;
	}

	/**
	 * 设置归属地主键
	 */
	public void setRegionId(java.lang.String regionId) {
		this.regionId = regionId;
	}

	/**
	 * 获取归属地主键
	 */
	public java.lang.String getRegionId() {
		return regionId;
	}

	/**
	 * 设置归属地名称
	 */
	public void setRegionName(java.lang.String regionName) {
		this.regionName = regionName;
	}

	/**
	 * 获取归属地名称
	 */
	public java.lang.String getRegionName() {
		return regionName;
	}

	/**
	 * 设置经办人主键
	 */
	public void setTransactorId(java.lang.String transactorId) {
		this.transactorId = transactorId;
	}

	/**
	 * 获取经办人主键
	 */
	public java.lang.String getTransactorId() {
		return transactorId;
	}

	/**
	 * 设置经办人名称
	 */
	public void setTransactorName(java.lang.String transactorName) {
		this.transactorName = transactorName;
	}

	/**
	 * 获取经办人名称
	 */
	public java.lang.String getTransactorName() {
		return transactorName;
	}

	/**
	 * 设置经办人手机号
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	 * 获取经办人手机号
	 */
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取创建时间
	 */
	public java.sql.Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置环节id
	 */
	public void setOpinionId(java.lang.String opinionId) {
		this.opinionId = opinionId;
	}

	/**
	 * 获取环节id
	 */
	public java.lang.String getOpinionId() {
		return opinionId;
	}
}
