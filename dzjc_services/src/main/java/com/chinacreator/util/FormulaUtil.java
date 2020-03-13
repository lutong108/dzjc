package com.chinacreator.util;




import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 公式解析
 * 支持对“+” “-” “*” “/” “[]” “()”的解析
 * 
 */
public class FormulaUtil {
	
	private static  Logger log = LoggerFactory.getLogger(FormulaUtil.class);

	//公式解析模式
	private static final Pattern FORMUlA_PATEERN = Pattern.compile("[\\*]|[\\(]|[\\-]|[\\/]|[\\+]|[\\)]|[\\]|[\\[]]");
	//数字模式
	private static final Pattern NUMBER_PATTERN= Pattern.compile("^(\\d+(.{1}[0-9]{1}[0-9]*)?$)");
	
	/**
	 * 解析公式字符串，提取计算项
	 * 比如：公式字符串为：业务项1*3+考核项2/2-未知项3
	 * 解析得到的List<String>是：业务项1   考核项2  未知项3
	 * @param formula
	 * @return
	 */
	public static List<String> parse(String formula){
		Matcher m = FORMUlA_PATEERN.matcher(formula);
		List<String> pointList = new ArrayList<String>();
		int start = 0;
		while(m.find()){
			String point = formula.substring(start,m.end()-1);
			if(!"".equals(point) && !isNumber(point)){
				pointList.add(point.trim());
			}
			start = m.end();
		}
		int formulaLength = formula.length();
		if(start<formulaLength){
			String point = formula.substring(start, formulaLength);
			if(!"".equals(point) && !isNumber(point)){
				pointList.add(point.trim());
			}
		}
		return pointList;
	}
	
	
	/**
	 * 判断是否为数字
	 * @param data
	 * @return
	 */
	private static boolean isNumber(String data){
		Matcher isNum = NUMBER_PATTERN.matcher(data);
		return isNum.matches();
	}
	
	/**
     * 计算数学表达式
     *
     * @param formula 数学表达式
     * @param itemName 指标名称
     *
     * @return 计算结果
     *
     * @throws CalculateException 如果公式格式不正确
     */
    public static double calculateFormula(String formula, String itemName) /*throws CalculateException*/ {
        /*try {
        	if(StringUtils.isEmpty(formula)){
        		return 0.0;
        	}
        	// 判断字符串中是否包含多字节字符
            if (formula.length() != formula.getBytes().length) {
                //throw new CalculateException("检查“" + itemName + "”公式失败：“" + formula + "”指标没有定义。");
            }
            Double result = new Parser(formula).getValue();
            if(result.isNaN()){
            	result = 0.0;
            }
            return result;
        } catch (ParseError e) {
            throw new CalculateException("指标\"" + itemName + "\"核算失败：核算公式格式不正确。");
        } catch (EvaluationException e) {
            throw new CalculateException("指标\"" + itemName + "\"核算失败。", e);
        }*/
    	return 1.2;
    }
	
	public static void main(String[] args)throws Exception{
		List<String> list = FormulaUtil.parse("1.02");//(给你个+你好)*哈哈/[我是*尼玛]
		for(String p:list){
			System.out.println(p);
		}
		System.out.println(FormulaUtil.isNumber("1.02"));
		System.out.println(FormulaUtil.isNumber(".3"));
		System.out.println(FormulaUtil.isNumber("13"));
		System.out.println(FormulaUtil.isNumber("13."));
		
		//Double t = FormulaUtil.calculateFormula("0/0*3", "测试");
        /*if(t.isNaN()){
        	t = 0.0;
        	System.out.println("重新赋值");
        }
        System.out.println(t);*/
	}
}
