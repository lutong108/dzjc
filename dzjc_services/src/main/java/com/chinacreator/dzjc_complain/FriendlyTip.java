package com.chinacreator.dzjc_complain;

import java.io.Serializable;

/**
 * 封装首页“发牌情况温馨提示”的数据实体
 * @author Administrator
 *
 */
public class FriendlyTip implements Serializable{


	private static final long serialVersionUID = 1L;
	
	//机构ID
	private String orgId;
	//发牌类型(0-自动发牌的结果,1-人工发牌的结果)
	private String executeType;
	//预警牌数
	private String greenNum;
	//黄牌数
	private String yelloNum;
	//红牌数
	private String redNum;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getExecuteType() {
		return executeType;
	}
	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}
	public String getGreenNum() {
		return greenNum;
	}
	public void setGreenNum(String greenNum) {
		this.greenNum = greenNum;
	}
	public String getYelloNum() {
		return yelloNum;
	}
	public void setYelloNum(String yelloNum) {
		this.yelloNum = yelloNum;
	}
	public String getRedNum() {
		return redNum;
	}
	public void setRedNum(String redNum) {
		this.redNum = redNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
