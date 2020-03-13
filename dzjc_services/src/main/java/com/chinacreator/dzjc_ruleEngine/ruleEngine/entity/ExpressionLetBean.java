package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

/**
 * <p>Title: 电子监察系统</p>
 * <p>Description:规则引擎表达式片段BEAN</p>
 * <p>创建日期:2011-07-30</p>
 * @author xianlu.lu
 * @version 1.0
 * <p>科创</p>
 */

public class ExpressionLetBean {
	
	private String explet_id;  //表达式片段ID
	
	private String exp_id;  //表达式ID
	
	private Integer left_paren; //左括号
	
	private String elem_id; //监察要素ID
	
	private String compa_id;  //比较运算符ID
	
	private String compa;   //比较运算符
	
	private String cfg_value;  //条件值
	
	private Integer right_paren; //右括号
	
	private String logic_id; //逻辑运算符ID
	
	private String logic;   //逻辑运算符
	
	private String area_code; //区域编码
	
	private String resultValue; //计算结果（规则计算的实际值）
	
	private String businessid; //业务id

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public String getCfg_value() {
		return cfg_value;
	}

	public void setCfg_value(String cfg_value) {
		this.cfg_value = cfg_value;
	}

	public String getCompa() {
		return compa;
	}

	public void setCompa(String compa) {
		this.compa = compa;
	}

	public String getCompa_id() {
		return compa_id;
	}

	public void setCompa_id(String compa_id) {
		this.compa_id = compa_id;
	}

	public String getElem_id() {
		return elem_id;
	}

	public void setElem_id(String elem_id) {
		this.elem_id = elem_id;
	}

	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}

	public String getExplet_id() {
		return explet_id;
	}

	public void setExplet_id(String explet_id) {
		this.explet_id = explet_id;
	}

	public Integer getLeft_paren() {
		return left_paren;
	}

	public void setLeft_paren(Integer left_paren) {
		this.left_paren = left_paren;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getLogic_id() {
		return logic_id;
	}

	public void setLogic_id(String logic_id) {
		this.logic_id = logic_id;
	}

	public Integer getRight_paren() {
		return right_paren;
	}

	public void setRight_paren(Integer right_paren) {
		this.right_paren = right_paren;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

}
