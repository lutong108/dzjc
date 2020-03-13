package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 机构缓存配置表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.OrgCacheConfig", table = "TA_JC_TONGJI_ORGCACHE_CONFIG", ds = "acceptdata", cache = false)
public class OrgCacheConfig {
	/**
	 *机构id
	 */
	@Column(id = "org_id", type = ColumnType.uuid, datatype = "string256")
	private java.lang.String orgId;

	/**
	 *机构名
	 */
	@Column(id = "org_name", datatype = "string256")
	private java.lang.String orgName;

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
}
