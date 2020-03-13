package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 环节视图
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.TacheView", table = "V_TA_JC_TACHE", ds = "acceptdata", cache = false)
public class TacheView {
	/**
	 *无用id
	 */
	@Column(id = "opinion_id", datatype = "string64")
	private java.lang.String opinionId;

	/**
	 *办件id
	 */
	@Column(id = "instance_id", datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *环节名称
	 */
	@Column(id = "tache_name", datatype = "string128")
	private java.lang.String tacheName;

	/**
	 *环节时间
	 */
	@Column(id = "opinion_time", datatype = "date")
	private java.sql.Date opinionTime;

	/**
	 *姓名
	 */
	@Column(id = "user_name", datatype = "string64")
	private java.lang.String userName;

	/**
	 *内容
	 */
	@Column(id = "opinion_content", datatype = "string1024")
	private java.lang.String opinionContent;

	/**
	 *类型(来源表)
	 */
	@Column(id = "type", datatype = "string10")
	private java.lang.String type;

	/**
	 *主键id
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *环节办理标志（1，已办环节；2，待办环节）
	 */
	@Column(id = "linkflag", datatype = "string64")
	private java.lang.String linkflag;

	/**
	 *环节类型
	 */
	@Column(id = "hjtype", datatype = "string64")
	private java.lang.String hjtype;

	/**
	 * 设置无用id
	 */
	public void setOpinionId(java.lang.String opinionId) {
		this.opinionId = opinionId;
	}

	/**
	 * 获取无用id
	 */
	public java.lang.String getOpinionId() {
		return opinionId;
	}

	/**
	 * 设置办件id
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取办件id
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置环节名称
	 */
	public void setTacheName(java.lang.String tacheName) {
		this.tacheName = tacheName;
	}

	/**
	 * 获取环节名称
	 */
	public java.lang.String getTacheName() {
		return tacheName;
	}

	/**
	 * 设置环节时间
	 */
	public void setOpinionTime(java.sql.Date opinionTime) {
		this.opinionTime = opinionTime;
	}

	/**
	 * 获取环节时间
	 */
	public java.sql.Date getOpinionTime() {
		return opinionTime;
	}

	/**
	 * 设置姓名
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * 获取姓名
	 */
	public java.lang.String getUserName() {
		return userName;
	}

	/**
	 * 设置内容
	 */
	public void setOpinionContent(java.lang.String opinionContent) {
		this.opinionContent = opinionContent;
	}

	/**
	 * 获取内容
	 */
	public java.lang.String getOpinionContent() {
		return opinionContent;
	}

	/**
	 * 设置类型(来源表)
	 */
	public void setType(java.lang.String type) {
		this.type = type;
	}

	/**
	 * 获取类型(来源表)
	 */
	public java.lang.String getType() {
		return type;
	}

	/**
	 * 设置主键id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置环节办理标志（1，已办环节；2，待办环节）
	 */
	public void setLinkflag(java.lang.String linkflag) {
		this.linkflag = linkflag;
	}

	/**
	 * 获取环节办理标志（1，已办环节；2，待办环节）
	 */
	public java.lang.String getLinkflag() {
		return linkflag;
	}

	/**
	 * 设置环节类型
	 */
	public void setHjtype(java.lang.String hjtype) {
		this.hjtype = hjtype;
	}

	/**
	 * 获取环节类型
	 */
	public java.lang.String getHjtype() {
		return hjtype;
	}
}
