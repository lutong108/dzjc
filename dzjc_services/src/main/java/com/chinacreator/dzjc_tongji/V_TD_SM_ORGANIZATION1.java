package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 机构视图
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.V_TD_SM_ORGANIZATION1", table = "V_TD_SM_ORGANIZATION1", ds = "acceptdata", cache = false)
public class V_TD_SM_ORGANIZATION1 {
	@Column(id = "org_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String orgId;

	@Column(id = "org_code", datatype = "string256")
	private java.lang.String orgCode;

	@Column(id = "org_name", datatype = "string1024")
	private java.lang.String orgName;

	@Column(id = "order_num", datatype = "bigdouble")
	private java.lang.Double orderNum;

	@Column(id = "province_code", datatype = "string20")
	private java.lang.String provinceCode;

	@Column(id = "city_code", datatype = "string20")
	private java.lang.String cityCode;

	@Column(id = "county_code", datatype = "string20")
	private java.lang.String countyCode;

	@Column(id = "street_code", datatype = "string32")
	private java.lang.String streetCode;

	@Column(id = "village_code", datatype = "string32")
	private java.lang.String villageCode;

	@Column(id = "org_level", datatype = "string32")
	private java.lang.String orgLevel;

	@Column(id = "org_this_level", datatype = "string32")
	private java.lang.String orgThisLevel;

	/**
	 * 设置${field.desc}
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrderNum(java.lang.Double orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getOrderNum() {
		return orderNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProvinceCode(java.lang.String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCityCode(java.lang.String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCityCode() {
		return cityCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCountyCode(java.lang.String countyCode) {
		this.countyCode = countyCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCountyCode() {
		return countyCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setStreetCode(java.lang.String streetCode) {
		this.streetCode = streetCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getStreetCode() {
		return streetCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setVillageCode(java.lang.String villageCode) {
		this.villageCode = villageCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getVillageCode() {
		return villageCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgLevel(java.lang.String orgLevel) {
		this.orgLevel = orgLevel;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgLevel() {
		return orgLevel;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgThisLevel(java.lang.String orgThisLevel) {
		this.orgThisLevel = orgThisLevel;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgThisLevel() {
		return orgThisLevel;
	}
}
