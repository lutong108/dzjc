package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

/**
 * <p>Title: 电子监察系统</p>
 * <p>Description:环节信息BEAN</p>
 * <p>创建日期:2011-07-30</p>
 * @author xianlu.lu
 * @version 1.0
 * <p>科创</p>
 */

public class TacheInfoBean {

	private String instanceId; //实例ID
	
	private String approveId; //事项ID
	
	private String tacheCode; //环节编码
	
	private String tacheSeq; //环节序号

	public String getApproveId() {
		return approveId;
	}

	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getTacheCode() {
		return tacheCode;
	}

	public void setTacheCode(String tacheCode) {
		this.tacheCode = tacheCode;
	}

	public String getTacheSeq() {
		return tacheSeq;
	}

	public void setTacheSeq(String tacheSeq) {
		this.tacheSeq = tacheSeq;
	}

}
