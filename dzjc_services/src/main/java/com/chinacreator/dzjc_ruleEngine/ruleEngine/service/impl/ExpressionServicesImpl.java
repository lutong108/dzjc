package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.List;

import bsh.Interpreter;

import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ExpressionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ExpressionLetBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.ExpressionServicesIfc;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public class ExpressionServicesImpl implements ExpressionServicesIfc {
	
	//private static final Logger LOG = Logger.getLogger(ExpressionServicesImpl.class.getName());


	public boolean execute(ExpressionBean expreesionBean,String businessId,RuleWithBusinessBean ruleWithBusinessBean) throws Exception {
		boolean bRlst = false;
		String expStr = buildExpression(expreesionBean,ruleWithBusinessBean,businessId);
		expStr = expStr.replace(" ", "");
		
		//这个地方调用BeanShell运算
		/*BeanShell执行代码*/
		Interpreter i = new Interpreter();
		i.setStrictJava(true);//严格使用java类型 ;
		try{
			bRlst =(Boolean)i.eval(expStr); 
		}catch(Exception e){
			//LOG.error(e.getMessage());
		}
		catch(Throwable t){
			//LOG.info("BeanShell运行异常："+t.getClass().getName());
			//LOG.error(t.getMessage());
		}
		return bRlst;
	}
	
	/*
	 * 构造表达式字符串
	 * 这个字符串将应用于每一条业务记录
	 * @return
	 */
	private String buildExpression(ExpressionBean expreesionBean,RuleWithBusinessBean ruleWithBusinessBean,String businessId) throws Exception{
		StringBuffer expStr = new StringBuffer();
		try{
			//根据表达式获取表达式片段集合
			List<ExpressionLetBean> expLetList = ruleWithBusinessBean.getExpIdAndExpressionLetBeanList().get(expreesionBean.getExp_id()+"_"+businessId);
			ExpressionLetBean expressionLetBean = null;
			for(int i=0; i<expLetList.size(); i++){
				expressionLetBean = (ExpressionLetBean)expLetList.get(i);
				if(expressionLetBean.getLeft_paren()==1){
					expStr.append("("); //左括号
				}
				
				String resultValue = expressionLetBean.getResultValue();
				if(resultValue == null || "".equals(resultValue))
				{
					resultValue = "0";
				}
				expStr.append(resultValue); //实际值

				if(expressionLetBean.getCompa_id() != null)
				{
					expStr.append(expressionLetBean.getCompa()); //比较运算符 
				}
				expStr.append(expressionLetBean.getCfg_value());

				if(expressionLetBean.getRight_paren()==1){
					expStr.append(")");   //右括号
				}
				if(expressionLetBean.getLogic_id() != null&&!"".equals(expressionLetBean.getLogic_id())){
					expStr.append(expressionLetBean.getLogic()); //逻辑运算符
				}
			}
		}catch(Exception e){
			//LOG.error(e.getMessage());
		}
		catch(Throwable t){
			//LOG.info("构造表达式字符串运行异常："+t.getClass().getName());
			//LOG.error(t.getMessage());
		}
		return expStr.toString();
	}

	@Override
	public String getOutDays(ExpressionBean expreesionBean, String businessId,
			RuleWithBusinessBean ruleWithBusinessBean) throws Exception {
		List<ExpressionLetBean> expLetList = ruleWithBusinessBean.getExpIdAndExpressionLetBeanList().get(expreesionBean.getExp_id()+"_"+businessId);
		if(expLetList != null && expLetList.size() > 0){
			return expLetList.get(0).getResultValue();
		}
		return "";
	}
	

}
