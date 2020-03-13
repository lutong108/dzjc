package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * v_org视图
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.VOrganization", table = "V_ORG", ds = "acceptdata", cache = false)
public class VOrganization {
	@Column(id = "id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String id;

	@Column(id = "name", datatype = "string2000")
	private java.lang.String name;

	@Column(id = "code", datatype = "string512")
	private java.lang.String code;

	@Column(id = "pid", datatype = "string512")
	private java.lang.String pid;

	@Column(id = "sn", datatype = "bigdouble")
	private java.lang.Double sn;

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
	 * 设置${field.desc}
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCode() {
		return code;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getPid() {
		return pid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSn(java.lang.Double sn) {
		this.sn = sn;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getSn() {
		return sn;
	}
}
