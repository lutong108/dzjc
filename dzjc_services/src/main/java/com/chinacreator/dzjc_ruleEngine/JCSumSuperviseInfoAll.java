package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

public class JCSumSuperviseInfoAll implements Serializable{
	
	private static final long serialVersionUID = 287593280635484111L;
	
	private String area_code;           //行政区划码
	private String area_name;           //行政区划名称
	private String start_time;   //开始时间
	private String end_time;     //结束时间
	
	private String PROVINCE_CODE;//      --省
	private String CITY_CODE;//          --市 
	private String County_CODE;//        --县区
	private String ORG_ID;//             --机构id
	private String ORG_NAME;//           --机构名称
	private String ORG_LEVEL; //       级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
	private String ORG_SHOW_NAME; //       机构显示名称
 
	
	private String sls;            //    受理数
	private String bjs;			//办结数
    private String zcbjs;            //  正常办结数 （一般办结，出证办结，转报办结，上报办结）
    private String thbjs;            //  退回办结数
    private String zfbjs;            //  作废办结数
    private String scbjs;            //  删除办结数
    private String bjl;            //    办结率（办结数/受理数）
    private String ljsls;            //  累计受理数（不加时间条件）
    private String ljbjs;            //  累计办结数 （不加时间条件）
    private String bysls;            //  不予受理数
    private String yjs;            //    预警数
    private String yellow;            //  黄牌数
    private String red; 			//  红牌数
    private String complain_num;    //投诉数            
    private String complain_reply_num;  //投诉回复数
    private String consult_num;  //咨询数
    private String consult_replay_num; //咨询回复数 
    private String cancel_yellow_num; //撤销黄牌数
    private String cancel_red_num; //撤销红牌数
    private String cancel_yj_num; //撤销预警数

	private String sortCol;//需排序的列
    
    private String instance_yjs;            //    办件预警数
    private String instance_yellow;            //  办件黄牌数
    private String instance_red; 			//  办件红牌数
    private String instance_cancel_yellow_num; //办件撤销黄牌数
    private String instance_cancel_red_num; //办件撤销红牌数
    
    private String complain_yjs;            //    投诉预警数
    private String complain_yellow;            //  投诉黄牌数
    private String complain_red; 			//  投诉红牌数
    private String complain_cancel_yellow_num; //投诉撤销黄牌数
    private String complain_cancel_red_num; //投诉撤销红牌数
    
    private String consult_yjs;            //    咨询预警数
    private String consult_yellow;            //  咨询黄牌数
    private String consult_red; 			//  咨询红牌数
    private String consult_cancel_yellow_num; //咨询撤销黄牌数
    private String consult_cancel_red_num; //咨询撤销红牌数
    
    private String special_supervise_green_num;            //    特别程序预警数
    private String special_supervise_yellow_num;            //  特别程序黄牌数
    private String special_supervise_red_num; 			//  特别程序红牌数
    
    
	public String getBjs() {
		return bjs;
	}
	public void setBjs(String bjs) {
		this.bjs = bjs;
	}
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
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getPROVINCE_CODE() {
		return PROVINCE_CODE;
	}
	public void setPROVINCE_CODE(String pROVINCE_CODE) {
		PROVINCE_CODE = pROVINCE_CODE;
	}
	public String getCITY_CODE() {
		return CITY_CODE;
	}
	public void setCITY_CODE(String cITY_CODE) {
		CITY_CODE = cITY_CODE;
	}
	public String getCounty_CODE() {
		return County_CODE;
	}
	public void setCounty_CODE(String county_CODE) {
		County_CODE = county_CODE;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String oRG_ID) {
		ORG_ID = oRG_ID;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRG_NAME) {
		ORG_NAME = oRG_NAME;
	}
	public String getORG_LEVEL() {
		return ORG_LEVEL;
	}
	public void setORG_LEVEL(String oRG_LEVEL) {
		ORG_LEVEL = oRG_LEVEL;
	}
	public String getORG_SHOW_NAME() {
		return ORG_SHOW_NAME;
	}
	public void setORG_SHOW_NAME(String oRG_SHOW_NAME) {
		ORG_SHOW_NAME = oRG_SHOW_NAME;
	}
	public String getSls() {
		return sls;
	}
	public void setSls(String sls) {
		this.sls = sls;
	}
	public String getZcbjs() {
		return zcbjs;
	}
	public void setZcbjs(String zcbjs) {
		this.zcbjs = zcbjs;
	}
	public String getThbjs() {
		return thbjs;
	}
	public void setThbjs(String thbjs) {
		this.thbjs = thbjs;
	}
	public String getZfbjs() {
		return zfbjs;
	}
	public void setZfbjs(String zfbjs) {
		this.zfbjs = zfbjs;
	}
	public String getScbjs() {
		return scbjs;
	}
	public void setScbjs(String scbjs) {
		this.scbjs = scbjs;
	}
	public String getBjl() {
		return bjl;
	}
	public void setBjl(String bjl) {
		this.bjl = bjl;
	}
	public String getLjsls() {
		return ljsls;
	}
	public void setLjsls(String ljsls) {
		this.ljsls = ljsls;
	}
	public String getLjbjs() {
		return ljbjs;
	}
	public void setLjbjs(String ljbjs) {
		this.ljbjs = ljbjs;
	}
	public String getBysls() {
		return bysls;
	}
	public void setBysls(String bysls) {
		this.bysls = bysls;
	}
	public String getYjs() {
		return yjs;
	}
	public void setYjs(String yjs) {
		this.yjs = yjs;
	}
	public String getYellow() {
		return yellow;
	}
	public void setYellow(String yellow) {
		this.yellow = yellow;
	}
	public String getRed() {
		return red;
	}
	public void setRed(String red) {
		this.red = red;
	}
	public String getComplain_num() {
		return complain_num;
	}
	public void setComplain_num(String complain_num) {
		this.complain_num = complain_num;
	}
	public String getComplain_reply_num() {
		return complain_reply_num;
	}
	public void setComplain_reply_num(String complain_reply_num) {
		this.complain_reply_num = complain_reply_num;
	}
	public String getConsult_num() {
		return consult_num;
	}
	public void setConsult_num(String consult_num) {
		this.consult_num = consult_num;
	}
	public String getConsult_replay_num() {
		return consult_replay_num;
	}
	public void setConsult_replay_num(String consult_replay_num) {
		this.consult_replay_num = consult_replay_num;
	}
	public String getCancel_yellow_num() {
		return cancel_yellow_num;
	}
	public void setCancel_yellow_num(String cancel_yellow_num) {
		this.cancel_yellow_num = cancel_yellow_num;
	}
	public String getCancel_red_num() {
		return cancel_red_num;
	}
	public void setCancel_red_num(String cancel_red_num) {
		this.cancel_red_num = cancel_red_num;
	}
	public String getInstance_yjs() {
		return instance_yjs;
	}
	public void setInstance_yjs(String instance_yjs) {
		this.instance_yjs = instance_yjs;
	}
	public String getInstance_yellow() {
		return instance_yellow;
	}
	public void setInstance_yellow(String instance_yellow) {
		this.instance_yellow = instance_yellow;
	}
	public String getInstance_red() {
		return instance_red;
	}
	public void setInstance_red(String instance_red) {
		this.instance_red = instance_red;
	}
	public String getInstance_cancel_yellow_num() {
		return instance_cancel_yellow_num;
	}
	public void setInstance_cancel_yellow_num(String instance_cancel_yellow_num) {
		this.instance_cancel_yellow_num = instance_cancel_yellow_num;
	}
	public String getInstance_cancel_red_num() {
		return instance_cancel_red_num;
	}
	public void setInstance_cancel_red_num(String instance_cancel_red_num) {
		this.instance_cancel_red_num = instance_cancel_red_num;
	}
	public String getComplain_yjs() {
		return complain_yjs;
	}
	public void setComplain_yjs(String complain_yjs) {
		this.complain_yjs = complain_yjs;
	}
	public String getComplain_yellow() {
		return complain_yellow;
	}
	public void setComplain_yellow(String complain_yellow) {
		this.complain_yellow = complain_yellow;
	}
	public String getComplain_red() {
		return complain_red;
	}
	public void setComplain_red(String complain_red) {
		this.complain_red = complain_red;
	}
	public String getComplain_cancel_yellow_num() {
		return complain_cancel_yellow_num;
	}
	public void setComplain_cancel_yellow_num(String complain_cancel_yellow_num) {
		this.complain_cancel_yellow_num = complain_cancel_yellow_num;
	}
	public String getComplain_cancel_red_num() {
		return complain_cancel_red_num;
	}
	public void setComplain_cancel_red_num(String complain_cancel_red_num) {
		this.complain_cancel_red_num = complain_cancel_red_num;
	}
	public String getConsult_yjs() {
		return consult_yjs;
	}
	public void setConsult_yjs(String consult_yjs) {
		this.consult_yjs = consult_yjs;
	}
	public String getConsult_yellow() {
		return consult_yellow;
	}
	public void setConsult_yellow(String consult_yellow) {
		this.consult_yellow = consult_yellow;
	}
	public String getConsult_red() {
		return consult_red;
	}
	public void setConsult_red(String consult_red) {
		this.consult_red = consult_red;
	}
	public String getConsult_cancel_yellow_num() {
		return consult_cancel_yellow_num;
	}
	public void setConsult_cancel_yellow_num(String consult_cancel_yellow_num) {
		this.consult_cancel_yellow_num = consult_cancel_yellow_num;
	}
	public String getConsult_cancel_red_num() {
		return consult_cancel_red_num;
	}
	public void setConsult_cancel_red_num(String consult_cancel_red_num) {
		this.consult_cancel_red_num = consult_cancel_red_num;
	}
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSpecial_supervise_green_num() {
		return special_supervise_green_num;
	}
	public void setSpecial_supervise_green_num(String special_supervise_green_num) {
		this.special_supervise_green_num = special_supervise_green_num;
	}
	public String getSpecial_supervise_yellow_num() {
		return special_supervise_yellow_num;
	}
	public void setSpecial_supervise_yellow_num(String special_supervise_yellow_num) {
		this.special_supervise_yellow_num = special_supervise_yellow_num;
	}
	public String getSpecial_supervise_red_num() {
		return special_supervise_red_num;
	}
	public void setSpecial_supervise_red_num(String special_supervise_red_num) {
		this.special_supervise_red_num = special_supervise_red_num;
	}
    public String getCancel_yj_num() {
		return cancel_yj_num;
	}
	public void setCancel_yj_num(String cancel_yj_num) {
		this.cancel_yj_num = cancel_yj_num;
	}
	
	
}
