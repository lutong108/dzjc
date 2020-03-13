package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 建言献策
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.TaJcComplainSuggest", table = "TA_COMPLAIN_SUGGEST", ds = "acceptdata", cache = false)
public class TaJcComplainSuggest {
	/**
	 *主键id
	 */
	@Column(id = "suggest_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String suggestId;

	/**
	 *标题
	 */
	@Column(id = "suggest_title", datatype = "string256")
	private java.lang.String suggestTitle;

	/**
	 *建议人姓名或单位名称
	 */
	@Column(id = "suggester_name", datatype = "string256")
	private java.lang.String suggesterName;

	/**
	 *联系电话
	 */
	@Column(id = "suggester_mobile", datatype = "string64")
	private java.lang.String suggesterMobile;

	/**
	 *所在城市名称
	 */
	@Column(id = "city_name", datatype = "string256")
	private java.lang.String cityName;

	/**
	 *所在区县名称
	 */
	@Column(id = "area_name", datatype = "string256")
	private java.lang.String areaName;

	/**
	 *建议1：围绕“最简的审批、最宽的准入、最优的服务、最高的效率、最省的费用和最规范的监管”的目标，最需要改革的有哪些事项？
	 */
	@Column(id = "suggest_one", datatype = "string2000")
	private java.lang.String suggestOne;

	/**
	 *建议2：企业投资与生产经营、群众办事过程中“最烦”、“最痛”、“最怕”的问题有哪些，“最盼”的是什么？
	 */
	@Column(id = "suggest_two", datatype = "string2000")
	private java.lang.String suggestTwo;

	/**
	 *建议3：对标“北上广”等先进地区，有哪些经验做法可以学习借鉴或直接引进推广？
	 */
	@Column(id = "suggest_three", datatype = "string2000")
	private java.lang.String suggestThree;

	/**
	 *建议4：其他意见建议
	 */
	@Column(id = "suggest_four", datatype = "string2000")
	private java.lang.String suggestFour;

	/**
	 *建议提交时间
	 */
	@Column(id = "suggest_date", datatype = "date", sort = SortType.desc)
	private java.sql.Date suggestDate;

	/**
	 *提交时间字符串
	 */
	@Column(id = "suggest_date_str", datatype = "char20")
	private java.lang.String suggestDateStr;

	/**
	 * 设置主键id
	 */
	public void setSuggestId(java.lang.String suggestId) {
		this.suggestId = suggestId;
	}

	/**
	 * 获取主键id
	 */
	public java.lang.String getSuggestId() {
		return suggestId;
	}

	/**
	 * 设置标题
	 */
	public void setSuggestTitle(java.lang.String suggestTitle) {
		this.suggestTitle = suggestTitle;
	}

	/**
	 * 获取标题
	 */
	public java.lang.String getSuggestTitle() {
		return suggestTitle;
	}

	/**
	 * 设置建议人姓名或单位名称
	 */
	public void setSuggesterName(java.lang.String suggesterName) {
		this.suggesterName = suggesterName;
	}

	/**
	 * 获取建议人姓名或单位名称
	 */
	public java.lang.String getSuggesterName() {
		return suggesterName;
	}

	/**
	 * 设置联系电话
	 */
	public void setSuggesterMobile(java.lang.String suggesterMobile) {
		this.suggesterMobile = suggesterMobile;
	}

	/**
	 * 获取联系电话
	 */
	public java.lang.String getSuggesterMobile() {
		return suggesterMobile;
	}

	/**
	 * 设置所在城市名称
	 */
	public void setCityName(java.lang.String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 获取所在城市名称
	 */
	public java.lang.String getCityName() {
		return cityName;
	}

	/**
	 * 设置所在区县名称
	 */
	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 获取所在区县名称
	 */
	public java.lang.String getAreaName() {
		return areaName;
	}

	/**
	 * 设置建议1：围绕“最简的审批、最宽的准入、最优的服务、最高的效率、最省的费用和最规范的监管”的目标，最需要改革的有哪些事项？
	 */
	public void setSuggestOne(java.lang.String suggestOne) {
		this.suggestOne = suggestOne;
	}

	/**
	 * 获取建议1：围绕“最简的审批、最宽的准入、最优的服务、最高的效率、最省的费用和最规范的监管”的目标，最需要改革的有哪些事项？
	 */
	public java.lang.String getSuggestOne() {
		return suggestOne;
	}

	/**
	 * 设置建议2：企业投资与生产经营、群众办事过程中“最烦”、“最痛”、“最怕”的问题有哪些，“最盼”的是什么？
	 */
	public void setSuggestTwo(java.lang.String suggestTwo) {
		this.suggestTwo = suggestTwo;
	}

	/**
	 * 获取建议2：企业投资与生产经营、群众办事过程中“最烦”、“最痛”、“最怕”的问题有哪些，“最盼”的是什么？
	 */
	public java.lang.String getSuggestTwo() {
		return suggestTwo;
	}

	/**
	 * 设置建议3：对标“北上广”等先进地区，有哪些经验做法可以学习借鉴或直接引进推广？
	 */
	public void setSuggestThree(java.lang.String suggestThree) {
		this.suggestThree = suggestThree;
	}

	/**
	 * 获取建议3：对标“北上广”等先进地区，有哪些经验做法可以学习借鉴或直接引进推广？
	 */
	public java.lang.String getSuggestThree() {
		return suggestThree;
	}

	/**
	 * 设置建议4：其他意见建议
	 */
	public void setSuggestFour(java.lang.String suggestFour) {
		this.suggestFour = suggestFour;
	}

	/**
	 * 获取建议4：其他意见建议
	 */
	public java.lang.String getSuggestFour() {
		return suggestFour;
	}

	/**
	 * 设置建议提交时间
	 */
	public void setSuggestDate(java.sql.Date suggestDate) {
		this.suggestDate = suggestDate;
	}

	/**
	 * 获取建议提交时间
	 */
	public java.sql.Date getSuggestDate() {
		return suggestDate;
	}

	/**
	 * 设置提交时间字符串
	 */
	public void setSuggestDateStr(java.lang.String suggestDateStr) {
		this.suggestDateStr = suggestDateStr;
	}

	/**
	 * 获取提交时间字符串
	 */
	public java.lang.String getSuggestDateStr() {
		return suggestDateStr;
	}
}
