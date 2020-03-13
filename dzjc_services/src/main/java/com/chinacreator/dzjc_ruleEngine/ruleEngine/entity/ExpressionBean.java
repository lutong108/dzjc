package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;


/**
 * <p>Title: 电子监察系统</p>
 * <p>Description:表达式计算BEAN</p>
 * <p>创建日期:2011-07-31</p>
 * @author xianlu.lu
 * @version 1.0
 * <p>科创</p>
 */

public class ExpressionBean {
	
	private String exp_id;  //表达式ID
	
	private String rule_id; //规则ID
	
	private String rule_version; //规则版本号
	
	private String exp_seq; //表达式属性值ID串
	
	//private List<ExpressionLetBean> expressionLetList; //表达式片段
	
	//private SuperviseDefinitionBean superviseDefineBean; //检查信息定义ID
	
	private String area_code; //区域编码
	
	


	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}

	public String getExp_seq() {
		return exp_seq;
	}

	public void setExp_seq(String exp_seq) {
		this.exp_seq = exp_seq;
	}


	public String getRule_id() {
		return rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}

	public String getRule_version() {
		return rule_version;
	}

	public void setRule_version(String rule_version) {
		this.rule_version = rule_version;
	}



	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

}
