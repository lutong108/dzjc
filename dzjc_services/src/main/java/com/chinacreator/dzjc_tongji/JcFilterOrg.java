package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 需要过滤的机构
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.JcFilterOrg", table = "TA_JC_FILTER_ORG", ds = "acceptdata", cache = false)
public class JcFilterOrg {
	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string256")
	private java.lang.String orgId;

	/**
	 *机构名
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

	/**
	 *主键
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string256")
	private java.lang.String id;

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
	 * 设置机构名
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取机构名
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键
	 */
	public java.lang.String getId() {
		return id;
	}
}
