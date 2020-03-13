package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 首页配置
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.homecfg", table = "TA_SP_HOME_CFG", ds = "acceptdata", cache = false)
public class Homecfg {
	@Column(id = "id", type = ColumnType.uuid, datatype = "string32")
	private java.lang.String id;

	/**
	 *用户ID
	 */
	@Column(id = "user_id", datatype = "string64")
	private java.lang.String userId;

	/**
	 *菜单图标
	 */
	@Column(id = "icon", datatype = "string512")
	private java.lang.String icon;

	/**
	 *菜单名称
	 */
	@Column(id = "name", datatype = "string64")
	private java.lang.String name;

	/**
	 *菜单链接
	 */
	@Column(id = "url", datatype = "string512")
	private java.lang.String url;

	/**
	 *菜单顺序
	 */
	@Column(id = "seq", datatype = "smallint")
	private java.lang.Integer seq;

	/**
	 * 设置${field.desc}
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户ID
	 */
	public java.lang.String getUserId() {
		return userId;
	}

	/**
	 * 设置菜单图标
	 */
	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	/**
	 * 获取菜单图标
	 */
	public java.lang.String getIcon() {
		return icon;
	}

	/**
	 * 设置菜单名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取菜单名称
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置菜单链接
	 */
	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	/**
	 * 获取菜单链接
	 */
	public java.lang.String getUrl() {
		return url;
	}

	/**
	 * 设置菜单顺序
	 */
	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	/**
	 * 获取菜单顺序
	 */
	public java.lang.Integer getSeq() {
		return seq;
	}
}
