package com.chinacreator.dzjc_todoStatistics;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 监察信息统计
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_todoStatistics.SumSuperviseInfo", table = "TA_JC_SUM_SUPERVISE_INFO", ds = "acceptdata", cache = false)
public class SumSuperviseInfo {
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	@Column(id = "area_org_name", datatype = "string256")
	private java.lang.String areaOrgName;

	@Column(id = "area_org_code", datatype = "string128")
	private java.lang.String areaOrgCode;

	@Column(id = "province_code", datatype = "string64")
	private java.lang.String provinceCode;

	@Column(id = "city_code", datatype = "string64")
	private java.lang.String cityCode;

	@Column(id = "county_code", datatype = "string64")
	private java.lang.String countyCode;

	@Column(id = "street_code", datatype = "string64")
	private java.lang.String streetCode;

	@Column(id = "village_code", datatype = "string20")
	private java.lang.String villageCode;

	@Column(id = "org_level", datatype = "string64")
	private java.lang.String orgLevel;

	@Column(id = "org_this_level", datatype = "string64")
	private java.lang.String orgThisLevel;

	@Column(id = "now_date", datatype = "string20")
	private java.lang.String nowDate;

	@Column(id = "accept_now_date", datatype = "string20")
	private java.lang.String acceptNowDate;

	@Column(id = "accept_num", datatype = "bigdecimal")
	private java.math.BigDecimal acceptNum;

	@Column(id = "djz_accept_num", datatype = "bigdouble")
	private java.lang.Double djzAcceptNum;

	@Column(id = "finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal finishNum;

	@Column(id = "djz_finish_num", datatype = "bigdouble")
	private java.lang.Double djzFinishNum;

	@Column(id = "normal_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal normalFinishNum;

	@Column(id = "before_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal beforeFinishNum;

	@Column(id = "after_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal afterFinishNum;

	@Column(id = "unthreadfinish_num", datatype = "bigdecimal")
	private java.math.BigDecimal unthreadfinishNum;

	@Column(id = "throw_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal throwFinishNum;

	@Column(id = "day_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal dayFinishNum;

	@Column(id = "sum_wasting_time", datatype = "bigdecimal")
	private java.math.BigDecimal sumWastingTime;

	@Column(id = "charge_money", datatype = "bigdouble")
	private java.lang.Double chargeMoney;

	@Column(id = "rightoff_num", datatype = "bigdecimal")
	private java.math.BigDecimal rightoffNum;

	@Column(id = "respond_num", datatype = "bigdecimal")
	private java.math.BigDecimal respondNum;

	@Column(id = "union_num", datatype = "bigdecimal")
	private java.math.BigDecimal unionNum;

	@Column(id = "up_num", datatype = "bigdecimal")
	private java.math.BigDecimal upNum;

	@Column(id = "xzxk_num", datatype = "bigdouble")
	private java.lang.Double xzxkNum;

	@Column(id = "fxzxk_num", datatype = "bigdouble")
	private java.lang.Double fxzxkNum;

	@Column(id = "bmfw_num", datatype = "bigdouble")
	private java.lang.Double bmfwNum;

	@Column(id = "xzxk_finish_num", datatype = "bigdouble")
	private java.lang.Double xzxkFinishNum;

	@Column(id = "fxzxk_finish_num", datatype = "bigdouble")
	private java.lang.Double fxzxkFinishNum;

	@Column(id = "bmfw_finish_num", datatype = "bigdouble")
	private java.lang.Double bmfwFinishNum;

	@Column(id = "complain_num", datatype = "bigdecimal")
	private java.math.BigDecimal complainNum;

	@Column(id = "com_num", datatype = "bigdouble")
	private java.lang.Double comNum;

	@Column(id = "complain_reply_num", datatype = "bigdouble")
	private java.lang.Double complainReplyNum;

	@Column(id = "consult_num", datatype = "bigdouble")
	private java.lang.Double consultNum;

	@Column(id = "consult_replay_num", datatype = "bigdouble")
	private java.lang.Double consultReplayNum;

	@Column(id = "money_num", datatype = "bigdouble")
	private java.lang.Double moneyNum;

	@Column(id = "normal_num", datatype = "bigdecimal")
	private java.math.BigDecimal normalNum;

	@Column(id = "red_num", datatype = "bigdecimal")
	private java.math.BigDecimal redNum;

	@Column(id = "yello_num", datatype = "bigdecimal")
	private java.math.BigDecimal yelloNum;

	@Column(id = "green_num", datatype = "bigdecimal")
	private java.math.BigDecimal greenNum;

	@Column(id = "status", datatype = "string5")
	private java.lang.String status;

	@Column(id = "now_normal_num", datatype = "bigdouble")
	private java.lang.Double nowNormalNum;

	@Column(id = "now_red_num", datatype = "bigdouble")
	private java.lang.Double nowRedNum;

	@Column(id = "now_yello_num", datatype = "bigdouble")
	private java.lang.Double nowYelloNum;

	@Column(id = "now_green_num", datatype = "bigdouble")
	private java.lang.Double nowGreenNum;

	@Column(id = "finish_normal_num", datatype = "bigdouble")
	private java.lang.Double finishNormalNum;

	@Column(id = "finish_red_num", datatype = "bigdouble")
	private java.lang.Double finishRedNum;

	@Column(id = "finish_yello_num", datatype = "bigdouble")
	private java.lang.Double finishYelloNum;

	@Column(id = "finish_green_num", datatype = "bigdouble")
	private java.lang.Double finishGreenNum;

	@Column(id = "supervise_green_num", datatype = "bigdouble")
	private java.lang.Double superviseGreenNum;

	@Column(id = "supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double superviseYelloNum;

	@Column(id = "supervise_red_num", datatype = "bigdouble")
	private java.lang.Double superviseRedNum;

	@Column(id = "cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double cancelYellowNum;

	@Column(id = "cancel_red_num", datatype = "bigdouble")
	private java.lang.Double cancelRedNum;

	@Column(id = "record_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String recordId;

	@Column(id = "instance_supervise_green_num", datatype = "bigdouble")
	private java.lang.Double instanceSuperviseGreenNum;

	@Column(id = "instance_supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double instanceSuperviseYelloNum;

	@Column(id = "instance_supervise_red_num", datatype = "bigdouble")
	private java.lang.Double instanceSuperviseRedNum;

	@Column(id = "instance_cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double instanceCancelYellowNum;

	@Column(id = "instance_cancel_red_num", datatype = "bigdouble")
	private java.lang.Double instanceCancelRedNum;

	@Column(id = "complain_supervise_green_num", datatype = "bigdouble")
	private java.lang.Double complainSuperviseGreenNum;

	@Column(id = "complain_supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double complainSuperviseYelloNum;

	@Column(id = "complain_supervise_red_num", datatype = "bigdouble")
	private java.lang.Double complainSuperviseRedNum;

	@Column(id = "complain_cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double complainCancelYellowNum;

	@Column(id = "complain_cancel_red_num", datatype = "bigdouble")
	private java.lang.Double complainCancelRedNum;

	@Column(id = "consult_supervise_green_num", datatype = "bigdouble")
	private java.lang.Double consultSuperviseGreenNum;

	@Column(id = "consult_supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double consultSuperviseYelloNum;

	@Column(id = "consult_supervise_red_num", datatype = "bigdouble")
	private java.lang.Double consultSuperviseRedNum;

	@Column(id = "consult_cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double consultCancelYellowNum;

	@Column(id = "consult_cancel_red_num", datatype = "bigdouble")
	private java.lang.Double consultCancelRedNum;

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
	public void setAreaOrgName(java.lang.String areaOrgName) {
		this.areaOrgName = areaOrgName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAreaOrgName() {
		return areaOrgName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAreaOrgCode(java.lang.String areaOrgCode) {
		this.areaOrgCode = areaOrgCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAreaOrgCode() {
		return areaOrgCode;
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

	/**
	 * 设置${field.desc}
	 */
	public void setNowDate(java.lang.String nowDate) {
		this.nowDate = nowDate;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getNowDate() {
		return nowDate;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptNowDate(java.lang.String acceptNowDate) {
		this.acceptNowDate = acceptNowDate;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAcceptNowDate() {
		return acceptNowDate;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptNum(java.math.BigDecimal acceptNum) {
		this.acceptNum = acceptNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getAcceptNum() {
		return acceptNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDjzAcceptNum(java.lang.Double djzAcceptNum) {
		this.djzAcceptNum = djzAcceptNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getDjzAcceptNum() {
		return djzAcceptNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setFinishNum(java.math.BigDecimal finishNum) {
		this.finishNum = finishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getFinishNum() {
		return finishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDjzFinishNum(java.lang.Double djzFinishNum) {
		this.djzFinishNum = djzFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getDjzFinishNum() {
		return djzFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNormalFinishNum(java.math.BigDecimal normalFinishNum) {
		this.normalFinishNum = normalFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getNormalFinishNum() {
		return normalFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBeforeFinishNum(java.math.BigDecimal beforeFinishNum) {
		this.beforeFinishNum = beforeFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getBeforeFinishNum() {
		return beforeFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAfterFinishNum(java.math.BigDecimal afterFinishNum) {
		this.afterFinishNum = afterFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getAfterFinishNum() {
		return afterFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setUnthreadfinishNum(java.math.BigDecimal unthreadfinishNum) {
		this.unthreadfinishNum = unthreadfinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getUnthreadfinishNum() {
		return unthreadfinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setThrowFinishNum(java.math.BigDecimal throwFinishNum) {
		this.throwFinishNum = throwFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getThrowFinishNum() {
		return throwFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setDayFinishNum(java.math.BigDecimal dayFinishNum) {
		this.dayFinishNum = dayFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getDayFinishNum() {
		return dayFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSumWastingTime(java.math.BigDecimal sumWastingTime) {
		this.sumWastingTime = sumWastingTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getSumWastingTime() {
		return sumWastingTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setChargeMoney(java.lang.Double chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getChargeMoney() {
		return chargeMoney;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRightoffNum(java.math.BigDecimal rightoffNum) {
		this.rightoffNum = rightoffNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getRightoffNum() {
		return rightoffNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRespondNum(java.math.BigDecimal respondNum) {
		this.respondNum = respondNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getRespondNum() {
		return respondNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setUnionNum(java.math.BigDecimal unionNum) {
		this.unionNum = unionNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getUnionNum() {
		return unionNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setUpNum(java.math.BigDecimal upNum) {
		this.upNum = upNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getUpNum() {
		return upNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setXzxkNum(java.lang.Double xzxkNum) {
		this.xzxkNum = xzxkNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getXzxkNum() {
		return xzxkNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setFxzxkNum(java.lang.Double fxzxkNum) {
		this.fxzxkNum = fxzxkNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getFxzxkNum() {
		return fxzxkNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBmfwNum(java.lang.Double bmfwNum) {
		this.bmfwNum = bmfwNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getBmfwNum() {
		return bmfwNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setXzxkFinishNum(java.lang.Double xzxkFinishNum) {
		this.xzxkFinishNum = xzxkFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getXzxkFinishNum() {
		return xzxkFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setFxzxkFinishNum(java.lang.Double fxzxkFinishNum) {
		this.fxzxkFinishNum = fxzxkFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getFxzxkFinishNum() {
		return fxzxkFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBmfwFinishNum(java.lang.Double bmfwFinishNum) {
		this.bmfwFinishNum = bmfwFinishNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getBmfwFinishNum() {
		return bmfwFinishNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainNum(java.math.BigDecimal complainNum) {
		this.complainNum = complainNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getComplainNum() {
		return complainNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComNum(java.lang.Double comNum) {
		this.comNum = comNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getComNum() {
		return comNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainReplyNum(java.lang.Double complainReplyNum) {
		this.complainReplyNum = complainReplyNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getComplainReplyNum() {
		return complainReplyNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setConsultNum(java.lang.Double consultNum) {
		this.consultNum = consultNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getConsultNum() {
		return consultNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setConsultReplayNum(java.lang.Double consultReplayNum) {
		this.consultReplayNum = consultReplayNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getConsultReplayNum() {
		return consultReplayNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setMoneyNum(java.lang.Double moneyNum) {
		this.moneyNum = moneyNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getMoneyNum() {
		return moneyNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNormalNum(java.math.BigDecimal normalNum) {
		this.normalNum = normalNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getNormalNum() {
		return normalNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRedNum(java.math.BigDecimal redNum) {
		this.redNum = redNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getRedNum() {
		return redNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setYelloNum(java.math.BigDecimal yelloNum) {
		this.yelloNum = yelloNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getYelloNum() {
		return yelloNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setGreenNum(java.math.BigDecimal greenNum) {
		this.greenNum = greenNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.math.BigDecimal getGreenNum() {
		return greenNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNowNormalNum(java.lang.Double nowNormalNum) {
		this.nowNormalNum = nowNormalNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getNowNormalNum() {
		return nowNormalNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNowRedNum(java.lang.Double nowRedNum) {
		this.nowRedNum = nowRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getNowRedNum() {
		return nowRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNowYelloNum(java.lang.Double nowYelloNum) {
		this.nowYelloNum = nowYelloNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getNowYelloNum() {
		return nowYelloNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNowGreenNum(java.lang.Double nowGreenNum) {
		this.nowGreenNum = nowGreenNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getNowGreenNum() {
		return nowGreenNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setFinishNormalNum(java.lang.Double finishNormalNum) {
		this.finishNormalNum = finishNormalNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getFinishNormalNum() {
		return finishNormalNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setFinishRedNum(java.lang.Double finishRedNum) {
		this.finishRedNum = finishRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getFinishRedNum() {
		return finishRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setFinishYelloNum(java.lang.Double finishYelloNum) {
		this.finishYelloNum = finishYelloNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getFinishYelloNum() {
		return finishYelloNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setFinishGreenNum(java.lang.Double finishGreenNum) {
		this.finishGreenNum = finishGreenNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getFinishGreenNum() {
		return finishGreenNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseGreenNum(java.lang.Double superviseGreenNum) {
		this.superviseGreenNum = superviseGreenNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getSuperviseGreenNum() {
		return superviseGreenNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseYelloNum(java.lang.Double superviseYelloNum) {
		this.superviseYelloNum = superviseYelloNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getSuperviseYelloNum() {
		return superviseYelloNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseRedNum(java.lang.Double superviseRedNum) {
		this.superviseRedNum = superviseRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getSuperviseRedNum() {
		return superviseRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCancelYellowNum(java.lang.Double cancelYellowNum) {
		this.cancelYellowNum = cancelYellowNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getCancelYellowNum() {
		return cancelYellowNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCancelRedNum(java.lang.Double cancelRedNum) {
		this.cancelRedNum = cancelRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getCancelRedNum() {
		return cancelRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setRecordId(java.lang.String recordId) {
		this.recordId = recordId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getRecordId() {
		return recordId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceSuperviseGreenNum(
			java.lang.Double instanceSuperviseGreenNum) {
		this.instanceSuperviseGreenNum = instanceSuperviseGreenNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getInstanceSuperviseGreenNum() {
		return instanceSuperviseGreenNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceSuperviseYelloNum(
			java.lang.Double instanceSuperviseYelloNum) {
		this.instanceSuperviseYelloNum = instanceSuperviseYelloNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getInstanceSuperviseYelloNum() {
		return instanceSuperviseYelloNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceSuperviseRedNum(
			java.lang.Double instanceSuperviseRedNum) {
		this.instanceSuperviseRedNum = instanceSuperviseRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getInstanceSuperviseRedNum() {
		return instanceSuperviseRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceCancelYellowNum(
			java.lang.Double instanceCancelYellowNum) {
		this.instanceCancelYellowNum = instanceCancelYellowNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getInstanceCancelYellowNum() {
		return instanceCancelYellowNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setInstanceCancelRedNum(java.lang.Double instanceCancelRedNum) {
		this.instanceCancelRedNum = instanceCancelRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getInstanceCancelRedNum() {
		return instanceCancelRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainSuperviseGreenNum(
			java.lang.Double complainSuperviseGreenNum) {
		this.complainSuperviseGreenNum = complainSuperviseGreenNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getComplainSuperviseGreenNum() {
		return complainSuperviseGreenNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainSuperviseYelloNum(
			java.lang.Double complainSuperviseYelloNum) {
		this.complainSuperviseYelloNum = complainSuperviseYelloNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getComplainSuperviseYelloNum() {
		return complainSuperviseYelloNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainSuperviseRedNum(
			java.lang.Double complainSuperviseRedNum) {
		this.complainSuperviseRedNum = complainSuperviseRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getComplainSuperviseRedNum() {
		return complainSuperviseRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainCancelYellowNum(
			java.lang.Double complainCancelYellowNum) {
		this.complainCancelYellowNum = complainCancelYellowNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getComplainCancelYellowNum() {
		return complainCancelYellowNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainCancelRedNum(java.lang.Double complainCancelRedNum) {
		this.complainCancelRedNum = complainCancelRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getComplainCancelRedNum() {
		return complainCancelRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setConsultSuperviseGreenNum(
			java.lang.Double consultSuperviseGreenNum) {
		this.consultSuperviseGreenNum = consultSuperviseGreenNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getConsultSuperviseGreenNum() {
		return consultSuperviseGreenNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setConsultSuperviseYelloNum(
			java.lang.Double consultSuperviseYelloNum) {
		this.consultSuperviseYelloNum = consultSuperviseYelloNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getConsultSuperviseYelloNum() {
		return consultSuperviseYelloNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setConsultSuperviseRedNum(
			java.lang.Double consultSuperviseRedNum) {
		this.consultSuperviseRedNum = consultSuperviseRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getConsultSuperviseRedNum() {
		return consultSuperviseRedNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setConsultCancelYellowNum(
			java.lang.Double consultCancelYellowNum) {
		this.consultCancelYellowNum = consultCancelYellowNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getConsultCancelYellowNum() {
		return consultCancelYellowNum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setConsultCancelRedNum(java.lang.Double consultCancelRedNum) {
		this.consultCancelRedNum = consultCancelRedNum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getConsultCancelRedNum() {
		return consultCancelRedNum;
	}
}
