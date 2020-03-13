package com.chinacreator.dzjc_tongji;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 机构数据实体
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.JcOrgView", table = "V_JC_TONGJI_ORG", ds = "acceptdata", cache = false)
public class JcOrgView {
	/**
	 *机构id
	 */
	@Column(id = "org_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String orgId;

	/**
	 *机构编码
	 */
	@Column(id = "org_code", datatype = "string256")
	private java.lang.String orgCode;

	/**
	 *机构名称
	 */
	@Column(id = "org_name", datatype = "string1024")
	private java.lang.String orgName;

	/**
	 *排序
	 */
	@Column(id = "order_num", datatype = "bigdouble")
	private java.lang.Double orderNum;

	/**
	 *父机构id
	 */
	@Column(id = "p_id", datatype = "string128")
	private java.lang.String pId;

	/**
	 *省级编码
	 */
	@Column(id = "province_code", datatype = "string20")
	private java.lang.String provinceCode;

	/**
	 *市级编码
	 */
	@Column(id = "city_code", datatype = "string20")
	private java.lang.String cityCode;

	/**
	 *街道编码
	 */
	@Column(id = "county_code", datatype = "string20")
	private java.lang.String countyCode;

	/**
	 *机构id
	 */
	@Column(id = "street_code", datatype = "string32")
	private java.lang.String streetCode;

	/**
	 *社区编码
	 */
	@Column(id = "village_code", datatype = "string32")
	private java.lang.String villageCode;

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
	 * 设置机构编码
	 */
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取机构编码
	 */
	public java.lang.String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置机构名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取机构名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置排序
	 */
	public void setOrderNum(java.lang.Double orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取排序
	 */
	public java.lang.Double getOrderNum() {
		return orderNum;
	}

	/**
	 * 设置父机构id
	 */
	public void setPId(java.lang.String pId) {
		this.pId = pId;
	}

	/**
	 * 获取父机构id
	 */
	public java.lang.String getPId() {
		return pId;
	}

	/**
	 * 设置省级编码
	 */
	public void setProvinceCode(java.lang.String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * 获取省级编码
	 */
	public java.lang.String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * 设置市级编码
	 */
	public void setCityCode(java.lang.String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 获取市级编码
	 */
	public java.lang.String getCityCode() {
		return cityCode;
	}

	/**
	 * 设置街道编码
	 */
	public void setCountyCode(java.lang.String countyCode) {
		this.countyCode = countyCode;
	}

	/**
	 * 获取街道编码
	 */
	public java.lang.String getCountyCode() {
		return countyCode;
	}

	/**
	 * 设置机构id
	 */
	public void setStreetCode(java.lang.String streetCode) {
		this.streetCode = streetCode;
	}

	/**
	 * 获取机构id
	 */
	public java.lang.String getStreetCode() {
		return streetCode;
	}

	/**
	 * 设置社区编码
	 */
	public void setVillageCode(java.lang.String villageCode) {
		this.villageCode = villageCode;
	}

	/**
	 * 获取社区编码
	 */
	public java.lang.String getVillageCode() {
		return villageCode;
	}
	/**级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
	湖南省		430000000000000000000000
	省本级		439900000000000000000000
	长沙市		430100000000000000000000
	长沙市值		430101000000000000000000
	雨花区		430111000000000000000000
	雨花区区本级	430111999000000000000000
	井湾子街道	    430111 01 0000000000000000
井湾子街道街道本级	430111 01 0999000000000000
井湾子街道三湘社区	430111010008000000000000
	机构			430723999000430723020000
	 * @return  430723999000430723020000
	 */
	public Integer getOrgLevel(){
		Integer orgLevel = null;
		if(StringUtils.isNotBlank(orgCode)&&orgCode.length()>=24){
			try {
				if((!"000".equals(orgCode.substring(9,12)))
						&&"000000000000".equals(orgCode.substring(12))){//街道本级,社区下面因为没有机构，所有直接算第5级
					return 5;
				}
				if("000000000".equals(orgCode.substring(12, 21))){
					if("000000".equals(orgCode.substring(6,12))){
						if("00".equals(orgCode.substring(4,6))){
							if("00".equals(orgCode.substring(2,4))){
								orgLevel = 1;
							}else{
								orgLevel = 2;
							}
						}else{
							orgLevel = 3;
						}
					}else{
						orgLevel = 4;
					}
				}else{
					orgLevel = 5;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orgLevel;
	}
	
	/** 本级 
		1：省本级 	439900000000000000000000
		2： 市本级   430101000000000000000000
		3： 区本级   430111999000000000000000
		9: 其他
	 * @return
	 */
	public Integer getOrgThisLevel(){
		Integer orgThisLevel = 9;
		if(StringUtils.isNotBlank(orgCode)&&orgCode.length()>=24){
			if("433101000000000000000000".equals(orgCode)){
				orgThisLevel = 9;
				return orgThisLevel;
			}
			if((!"99".equals(orgCode.substring(2,4)))||(!"00000000000000000000".equals(orgCode.substring(4,24)))){
				if("01".equals(orgCode.substring(4,6))&&"000000000000000000".equals(orgCode.substring(6,24))){
					orgThisLevel = 2;
				}else{
					if("999".equals(orgCode.substring(6,9))&&"000000000000000".equals(orgCode.substring(9,24))){
						orgThisLevel = 3;
					}else{
						orgThisLevel = 9;
					}
				}
			}else{
				orgThisLevel = 1;
			}
		}
		return orgThisLevel;
	}
}
