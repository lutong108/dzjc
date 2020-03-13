package com.chinacreator.dzjc_ruleEngine.ruleEngine.support;

import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;



/**
 * <p>Title: 电子监察系统</p>
 * <p>Description:规则常量类</p>
 * <p>创建日期:2011-07-31</p>
 * @author xianlu.lu
 * @version 1.0
 * <p>科创</p>
 */

public interface RuleConstants {
	
	public static final  String RULE_STAUTS_VALID = "1"; //规则有效
	
	public static final String IS_AUTO_RUN_TRUE = "1"; //规则自动运行
	
	public static final String CFG_TYPE_INIT = "init"; //初始化启动
	
	public static final String CFG_TYPE_END = "end";  //结束
	
	public static final String RULE_TYPE_SIMPLE = "1"; //简单规则
	
	public static final String RULE_TYPE_COMPLEX = "2"; //复杂规则
	
/************************************监察结果**************************/
	
	public static final String SUPERVISE_RESULT_NORMAL = "0"; //正常
	
	public static final String SUPERVISE_RESULT_WARN = "1"; //预警
	
	public static final String SUPERVISE_RESULT_YELLOW = "2"; //黄牌
	
	public static final String SUPERVISE_RESULT_RED = "3"; //红牌
	
	
/************************************发牌方式**************************/
	
	public static final String SENDCARD_TYPE_AUTO = "1"; //自动	
	
	public static final String SENDCARD_TYPE_MANUAL = "2"; //人工
	
/************************************邮件发送**************************/
	public static final String EMAILSEND_USER_ID = "1"; // 邮件发送人ID
	
	public static final String EMAILSEND_USER_NAME = "系统管理员"; // 邮件发送人名称
	
	public static final String EMAILSEND_TITLE_WARNING = DateUtil.getCurrentDate()+"日 监察结果为预警，请查收。"; //邮件标题
	
	public static final String EMAILSEND_TITLE_YELLOW = DateUtil.getCurrentDate()+"日 监察结果为黄牌，请查收。";
	
	public static final String EMAILSEND_TITLE_RED = DateUtil.getCurrentDate()+"日 监察结果为红牌，请查收。";

/***********************************模板解析**************************/
	
	public static final String VARAIABLE_PREFIX = "$";
	
	public static final String VARAIABLE_SUFFIX = "$";
	
	public static final String BUSINESS_TYPE_XZSP = "1000";  //行政审批

}
