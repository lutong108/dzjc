package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

public class ShiXiangBanJianStatistics implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String area_code;           //行政区划码
	private String area_name;           //行政区划名称
	private String accept_start_time;   //开始时间
	private String accept_end_time;     //结束时间
	
	private String province_code;//      --省
	private String city_code;//          --市 
	private String country_code;//        --县区
	private String org_id;//             --机构id
	private String org_name;//           --机构名称
	private String org_level; //       级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
	private String org_this_level;//     1,省本级， 2，市本级， 9其他
	private String org_show_name; //       机构显示名称
	
	private String xzxksxs;//       --事项数   行政许可 
	private String xzjlsxs;//       --事项数   行政奖励
	private String xzcjsxs;//       --事项数   行政裁决
	private String xzjcsxs;//       --事项数   行政检查
	private String xzgfsxs;//       --事项数   行政给付
	private String xzqzsxs;//       --事项数   行政强制
	private String xzqrsxs;//       --事项数   行政确认
	private String xzcfsxs;//       --事项数   行政处罚
	private String xzzssxs;//       --事项数   行政征收
	private String qtxzqlsxs;//     --事项数   其他行政权利
	private String ggfwsxs;//       --事项数   公共服务
	private String sxscount;//      --事项数   小计
	
	private String xzxksjs;//       --收件数   行政许可 
	private String xzjlsjs;//       --收件数   行政奖励
	private String xzcjsjs;//       --收件数   行政裁决
	private String xzjcsjs;//       --收件数   行政检查
	private String xzgfsjs;//       --收件数   行政给付
	private String xzqzsjs;//       --收件数   行政强制
	private String xzqrsjs;//       --收件数   行政确认
	private String xzcfsjs;//       --收件数   行政处罚
	private String xzzssjs;//       --收件数   行政征收
	private String qtxzqlsjs;//     --收件数   其他行政权利
	private String ggfwsjs;//       --收件数   公共服务 
	private String sjscount;//      --收件数   小计
	
	private String xzxkbjs;//       --办结数   行政许可 
	private String xzjlbjs;//       --办结数   行政奖励
	private String xzcjbjs;//       --办结数   行政裁决
	private String xzjcbjs;//       --办结数   行政检查
	private String xzgfbjs;//       --办结数   行政给付
	private String xzqzbjs;//       --办结数   行政强制
	private String xzqrbjs;//       --办结数   行政确认
	private String xzcfbjs;//       --办结数   行政处罚
	private String xzzsbjs;//       --办结数   行政征收
	private String qtxzqlbjs;//     --办结数   其他行政权利
	private String ggfwbjs;//       --办结数   公共服务
	private String bjscount;//      --办结数   小计
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getAccept_start_time() {
		return accept_start_time;
	}
	public void setAccept_start_time(String accept_start_time) {
		this.accept_start_time = accept_start_time;
	}
	public String getAccept_end_time() {
		return accept_end_time;
	}
	public void setAccept_end_time(String accept_end_time) {
		this.accept_end_time = accept_end_time;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_level() {
		return org_level;
	}
	public void setOrg_level(String org_level) {
		this.org_level = org_level;
	}
	public String getOrg_this_level() {
		return org_this_level;
	}
	public void setOrg_this_level(String org_this_level) {
		this.org_this_level = org_this_level;
	}
	public String getOrg_show_name() {
		return org_show_name;
	}
	public void setOrg_show_name(String org_show_name) {
		this.org_show_name = org_show_name;
	}
	public String getXzxksxs() {
		return xzxksxs;
	}
	public void setXzxksxs(String xzxksxs) {
		this.xzxksxs = xzxksxs;
	}
	public String getXzjlsxs() {
		return xzjlsxs;
	}
	public void setXzjlsxs(String xzjlsxs) {
		this.xzjlsxs = xzjlsxs;
	}
	public String getXzcjsxs() {
		return xzcjsxs;
	}
	public void setXzcjsxs(String xzcjsxs) {
		this.xzcjsxs = xzcjsxs;
	}
	public String getXzjcsxs() {
		return xzjcsxs;
	}
	public void setXzjcsxs(String xzjcsxs) {
		this.xzjcsxs = xzjcsxs;
	}
	public String getXzgfsxs() {
		return xzgfsxs;
	}
	public void setXzgfsxs(String xzgfsxs) {
		this.xzgfsxs = xzgfsxs;
	}
	public String getXzqzsxs() {
		return xzqzsxs;
	}
	public void setXzqzsxs(String xzqzsxs) {
		this.xzqzsxs = xzqzsxs;
	}
	public String getXzqrsxs() {
		return xzqrsxs;
	}
	public void setXzqrsxs(String xzqrsxs) {
		this.xzqrsxs = xzqrsxs;
	}
	public String getXzcfsxs() {
		return xzcfsxs;
	}
	public void setXzcfsxs(String xzcfsxs) {
		this.xzcfsxs = xzcfsxs;
	}
	public String getXzzssxs() {
		return xzzssxs;
	}
	public void setXzzssxs(String xzzssxs) {
		this.xzzssxs = xzzssxs;
	}
	public String getQtxzqlsxs() {
		return qtxzqlsxs;
	}
	public void setQtxzqlsxs(String qtxzqlsxs) {
		this.qtxzqlsxs = qtxzqlsxs;
	}
	public String getGgfwsxs() {
		return ggfwsxs;
	}
	public void setGgfwsxs(String ggfwsxs) {
		this.ggfwsxs = ggfwsxs;
	}
	public String getSxscount() {
		return sxscount;
	}
	public void setSxscount(String sxscount) {
		this.sxscount = sxscount;
	}
	public String getXzxksjs() {
		return xzxksjs;
	}
	public void setXzxksjs(String xzxksjs) {
		this.xzxksjs = xzxksjs;
	}
	public String getXzjlsjs() {
		return xzjlsjs;
	}
	public void setXzjlsjs(String xzjlsjs) {
		this.xzjlsjs = xzjlsjs;
	}
	public String getXzcjsjs() {
		return xzcjsjs;
	}
	public void setXzcjsjs(String xzcjsjs) {
		this.xzcjsjs = xzcjsjs;
	}
	public String getXzjcsjs() {
		return xzjcsjs;
	}
	public void setXzjcsjs(String xzjcsjs) {
		this.xzjcsjs = xzjcsjs;
	}
	public String getXzgfsjs() {
		return xzgfsjs;
	}
	public void setXzgfsjs(String xzgfsjs) {
		this.xzgfsjs = xzgfsjs;
	}
	public String getXzqzsjs() {
		return xzqzsjs;
	}
	public void setXzqzsjs(String xzqzsjs) {
		this.xzqzsjs = xzqzsjs;
	}
	public String getXzqrsjs() {
		return xzqrsjs;
	}
	public void setXzqrsjs(String xzqrsjs) {
		this.xzqrsjs = xzqrsjs;
	}
	public String getXzcfsjs() {
		return xzcfsjs;
	}
	public void setXzcfsjs(String xzcfsjs) {
		this.xzcfsjs = xzcfsjs;
	}
	public String getXzzssjs() {
		return xzzssjs;
	}
	public void setXzzssjs(String xzzssjs) {
		this.xzzssjs = xzzssjs;
	}
	public String getQtxzqlsjs() {
		return qtxzqlsjs;
	}
	public void setQtxzqlsjs(String qtxzqlsjs) {
		this.qtxzqlsjs = qtxzqlsjs;
	}
	public String getGgfwsjs() {
		return ggfwsjs;
	}
	public void setGgfwsjs(String ggfwsjs) {
		this.ggfwsjs = ggfwsjs;
	}
	public String getSjscount() {
		return sjscount;
	}
	public void setSjscount(String sjscount) {
		this.sjscount = sjscount;
	}
	public String getXzxkbjs() {
		return xzxkbjs;
	}
	public void setXzxkbjs(String xzxkbjs) {
		this.xzxkbjs = xzxkbjs;
	}
	public String getXzjlbjs() {
		return xzjlbjs;
	}
	public void setXzjlbjs(String xzjlbjs) {
		this.xzjlbjs = xzjlbjs;
	}
	public String getXzcjbjs() {
		return xzcjbjs;
	}
	public void setXzcjbjs(String xzcjbjs) {
		this.xzcjbjs = xzcjbjs;
	}
	public String getXzjcbjs() {
		return xzjcbjs;
	}
	public void setXzjcbjs(String xzjcbjs) {
		this.xzjcbjs = xzjcbjs;
	}
	public String getXzgfbjs() {
		return xzgfbjs;
	}
	public void setXzgfbjs(String xzgfbjs) {
		this.xzgfbjs = xzgfbjs;
	}
	public String getXzqzbjs() {
		return xzqzbjs;
	}
	public void setXzqzbjs(String xzqzbjs) {
		this.xzqzbjs = xzqzbjs;
	}
	public String getXzqrbjs() {
		return xzqrbjs;
	}
	public void setXzqrbjs(String xzqrbjs) {
		this.xzqrbjs = xzqrbjs;
	}
	public String getXzcfbjs() {
		return xzcfbjs;
	}
	public void setXzcfbjs(String xzcfbjs) {
		this.xzcfbjs = xzcfbjs;
	}
	public String getXzzsbjs() {
		return xzzsbjs;
	}
	public void setXzzsbjs(String xzzsbjs) {
		this.xzzsbjs = xzzsbjs;
	}
	public String getQtxzqlbjs() {
		return qtxzqlbjs;
	}
	public void setQtxzqlbjs(String qtxzqlbjs) {
		this.qtxzqlbjs = qtxzqlbjs;
	}
	public String getGgfwbjs() {
		return ggfwbjs;
	}
	public void setGgfwbjs(String ggfwbjs) {
		this.ggfwbjs = ggfwbjs;
	}
	public String getBjscount() {
		return bjscount;
	}
	public void setBjscount(String bjscount) {
		this.bjscount = bjscount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
