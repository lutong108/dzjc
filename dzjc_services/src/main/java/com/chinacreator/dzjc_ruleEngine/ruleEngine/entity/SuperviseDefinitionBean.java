package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

/**
 * <p>Title: 电子监察系统</p>
 * <p>Description:规则引擎监察规则定义BEAN</p>
 * <p>创建日期:2011-07-30</p>
 * @author xianlu.lu
 * @version 1.0
 * <p>科创</p>
 */

public class SuperviseDefinitionBean {
	
	private String supervise_def_id; //监察信息定义ID
	
	private String exp_id; //表达式ID
	
	private String supervise_level_code; //监察级别代码
	
	private String supervise_type_code; //监察类型代码
	
	private String sendcard_type; //发牌方式
	
	private String msg_template_id; //消息模板
	
	private String area_code; //区域编码
	
	private String superviseInfoId;//发牌结果表id，用于判断是否已发牌
	
	private String msgTemplate;//短信模板内容

	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}

	public String getMsg_template_id() {
		return msg_template_id;
	}

	public void setMsg_template_id(String msg_template_id) {
		this.msg_template_id = msg_template_id;
	}

	public String getSendcard_type() {
		return sendcard_type;
	}

	public void setSendcard_type(String sendcard_type) {
		this.sendcard_type = sendcard_type;
	}

	public String getSupervise_def_id() {
		return supervise_def_id;
	}

	public void setSupervise_def_id(String supervise_def_id) {
		this.supervise_def_id = supervise_def_id;
	}

	public String getSupervise_level_code() {
		return supervise_level_code;
	}

	public void setSupervise_level_code(String supervise_level_code) {
		this.supervise_level_code = supervise_level_code;
	}

	public String getSupervise_type_code() {
		return supervise_type_code;
	}

	public void setSupervise_type_code(String supervise_type_code) {
		this.supervise_type_code = supervise_type_code;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getSuperviseInfoId() {
		return superviseInfoId;
	}

	public void setSuperviseInfoId(String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	public String getMsgTemplate() {
		return msgTemplate;
	}

	public void setMsgTemplate(String msgTemplate) {
		this.msgTemplate = msgTemplate;
	}
	
}
