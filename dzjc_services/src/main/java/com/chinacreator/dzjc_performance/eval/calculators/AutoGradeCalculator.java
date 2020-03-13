package com.chinacreator.dzjc_performance.eval.calculators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.EvalInstance;
import com.chinacreator.dzjc_performance.EvalItem;
import com.chinacreator.dzjc_performance.EvalObject;
import com.chinacreator.dzjc_performance.EvalPointValue;
import com.chinacreator.util.FormulaUtil;

/**
 * 考核指标分数计算的类
 * @author Administrator
 *
 */
public class AutoGradeCalculator {

	private static final AutoGradeCalculator autoGradeCalculator = new AutoGradeCalculator();
	
	//private static  Logger log = LoggerFactory.getLogger(AutoGradeCalculator.class);
	
	public static AutoGradeCalculator getInstance() {
		return autoGradeCalculator;
	}
	
	/**
	 * 根据指标名称计算考核指标的信息
	 */
	public void calculateEvalPoint(List<EvalObject> objectList, EvalInstance evalTable,EvalItem evalItem,String pointName,java.util.Date startime,java.util.Date endTime){
		DicEvalPoint evalPoint = getPointByName(pointName);
		try {
			
			//Double pointValue = getPointValue(evalPoint);//得到指标的分配的分值
			Double pointScore = 0.0;//指标实际得分
			
			String isAuto = evalPoint.getIsAuto();//是否自动
			String isGrade = "";
			Date evalTime = null;//考评时间，也就是指标实际得分产生的时间
			
			List<String> radixNameList = new ArrayList<>();//用来保存已经计算过的基数的名称
			if("Y".equals(isAuto)){//该考核指标自动评分
				isGrade = "Y";
				evalTime = new Date(new java.util.Date().getTime());
				//evalTime = date.getTime();
				String evalPointFormula = evalPoint.getEvalFormula();//得到考核指标的公式
				List<String> dicEvalRadixNames = FormulaUtil.parse(evalPointFormula);//调用工具类方法，解析考核指标的公式，得到该指标所有基数的名字的集合
				
				for (String radixName : dicEvalRadixNames) {
					if(!radixNameList.contains(radixName)){//只计算没有计算过的基数值
						Double radixValue = RadixCalculatorFactory.getInstance()
								.calclulateRadix(evalPoint,evalTable,radixName,startime,endTime);//根据基数的名字计算基数的分数
						
						//把得到的分数转成字符串，替换掉考核指标的公式中的基数名称
						evalPointFormula = evalPointFormula.replaceAll(radixName, String.valueOf(radixValue));
						
						radixNameList.add(radixName);
					}
				}
				//计算指标的分数
				pointScore = calculateValue(evalPointFormula);
			    
			    /**
			     * 根据指标配置的时候，超出最大值取值和超出最小值取值来决定最终的得分
			     */
			    if("max".equals(evalPoint.getMaxvalDepend())){//超出最大值取值是取最大值
			    	Double maxValue = evalPoint.getMaxValue();//得到指标的最大值
			    	if(pointScore > maxValue){//超出了最大值
			    		pointScore = maxValue;
			    	}
			    }
			    if("min".equals(evalPoint.getMinvalDepend())){//超出最小值取值是取最小值
			    	Double minValue = evalPoint.getMinValue();//得到指标的最小值
			    	if(pointScore < minValue){//超出了最小值
			    		pointScore = minValue;
			    	}
			    }
			    
			    
			    //插入对象，指标表的分数信息
			    for (EvalObject evalObject : objectList) {
			    	insertPointValue(evalTable, evalItem, evalPoint, 
							pointScore,isGrade,evalObject,evalTime);
				}
			    
			    //int i = 1/0;

			}else{//人工评分,往TA_JC_EVAL_POINT_VALUE（绩效测评指标成绩表）中插入对象和指标对应关系的记录
				isGrade = "N";
				for (EvalObject evalObject : objectList) {
			    	insertPointValue(evalTable, evalItem, evalPoint, 
							pointScore,isGrade,evalObject,evalTime);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(evalPoint.getPointName()+"指标信息获取失败==="+e.getMessage());
		}
	}

	public Double calculateValue(String evalPointFormula) {
		Double pointScore = 0.0;
		JexlEngine jexlEngine = new JexlBuilder().create();
		JexlExpression jexlExpression = jexlEngine.createExpression(evalPointFormula);
		pointScore = (Double) jexlExpression.evaluate(null);
		return pointScore;
	}


	//单条记录插入的方法
	public void insertPointValue(EvalInstance evalTable, EvalItem evalItem,
			DicEvalPoint evalPoint,  Double pointScore,String isGrade,EvalObject evalObject,Date evalTime) {
		EvalPointValue evalPointValue = new EvalPointValue();
		evalPointValue.setPointId(evalPoint.getPointId());
		evalPointValue.setPointName(evalPoint.getPointName());
		evalPointValue.setItemId(evalItem.getItemId());
		evalPointValue.setItemName(evalItem.getItemName());
		evalPointValue.setOrgId(evalTable.getOrgId());
		evalPointValue.setTableId(evalTable.getTableId());
		evalPointValue.setTableName(evalTable.getTableName());
		evalPointValue.setPointScore(pointScore);//指标实际得分
		evalPointValue.setOrgName(evalTable.getOrgName());//测评单位名称
		evalPointValue.setEvalObjectType(evalTable.getEvalObjectType());//考核对象类型
		evalPointValue.setComplainStartTime(evalTable.getEvalStartTime());
		evalPointValue.setComplainEndTime(evalTable.getEvalEndTime());
		evalPointValue.setIsGrade(isGrade);
		evalPointValue.setIsAuto(evalPoint.getIsAuto());//是否自动评分
		evalPointValue.setIsComplain("N");//未申诉
		evalPointValue.setEvalObjectId(evalObject.getObjectId());
		evalPointValue.setEvalObjectName(evalObject.getObjectName());
		evalPointValue.setEvalInstanceId(evalTable.getId());//测评实例id
		evalPointValue.setEvalTime(evalTime);//设置考评时间
		
		DaoFactory.create(EvalPointValue.class).insert(evalPointValue);
	}

	//查询指标对应的指标类型的分配的分值
	/*public Double getPointValue(DicEvalPoint evalPoint) {
		//根据指标查询他对应的考核类型，得到他分配的分数
		String pointTypeId = evalPoint.getPointTypeId();
		DicEvalPointClass pointClass = new DicEvalPointClass();
		pointClass.setPointTypeId(pointTypeId);
		DicEvalPointClass evalPointClass = DaoFactory.create(DicEvalPointClass.class).selectByID(pointClass);
		Double value = evalPointClass.getValue();//得到考核指标分配的分值
		return value;
	}*/

	//根据名称得到指标
	public DicEvalPoint getPointByName(String pointName) {
		DicEvalPoint point = new DicEvalPoint();
		point.setPointName(pointName);
		DicEvalPoint evalPoint = DaoFactory.create(DicEvalPoint.class).selectOne(point);
		return evalPoint;
	}
}
