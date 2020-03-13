package com.chinacreator.dzjc_performance.eval.calculators;

import java.util.Date;
import java.util.List;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.EvalInstance;
import com.chinacreator.dzjc_performance.EvalItem;
import com.chinacreator.dzjc_performance.EvalObject;
import com.chinacreator.dzjc_performance.EvalPointValue;
import com.chinacreator.dzjc_performance.eval.exception.CalculateException;
import com.chinacreator.dzjc_performance.service.EvalPointValueService;
import com.chinacreator.util.FormulaUtil;
import com.sun.star.uno.RuntimeException;

/**
 * 考核项分数计算的类
 * @author Administrator
 *
 */
public class ItemCalculatorFactory {
	
	private static final ItemCalculatorFactory itemCalculatorFactory = new ItemCalculatorFactory();
	
	//private static  Logger log = LoggerFactory.getLogger(ItemCalculatorFactory.class);
		@Autowired
		EvalPointValueService evalPointValueService;
	
	public static ItemCalculatorFactory getInstance() {
		return itemCalculatorFactory;
	}
	
	/**
	 * 查询考核项的信息
	 */
	public void calculateEvalItem(List<String> pointNameList,List<EvalObject> objectList, EvalInstance evalTable,String itemName,Date startime,Date endTime){
		try {
			EvalItem evalItem = getEvalItemByName(itemName);//根据考核项名称获取考核项信息
			
			/**
			 * 得到考核项之后，查询他对应的所有的最底层的子考核项
			 * 
			 */
			String itemId = evalItem.getItemId();
			List<EvalItem> itemList = DaoFactory.create(EvalItem.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalItemMapper.selectByItemId", itemId);
			/**
			 * 得到考核项的考核公式
			 * 这个时候，考核项的计算公式是由考核指标做数学运算得到，有+，-，*，/，(),[]
			 * 所以这里需要对计算公式进行解析   比如考核公式是：(给你个+你好)*哈哈/[是*尼玛]
			 * 经过  FormulaUtil.parse(evalItemFormula); 后得到：给你个  你好  哈哈  是  尼玛
			 * 调用工具类解析公式字符串，得到该		考核项		的所有   计算项
			 */
			
			for (EvalItem evalItem2 : itemList) {
				String evalItemFormula = evalItem2.getItemFormula();
				List<String> evalPointNames = FormulaUtil.parse(evalItemFormula);
				for (String pointName : evalPointNames) {
					if(!pointNameList.contains(pointName)){
						//只计算还没计算过的考核指标的分数
						AutoGradeCalculator.getInstance().calculateEvalPoint(objectList,evalTable,evalItem,pointName,startime,endTime);//根据考核指标的名字计算考核指标的信息
						pointNameList.add(pointName);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("考核项信息获取失败");
		}
	}


	
	/**
	 * 根据考核项的pointTypeId查询考核项类型表，这方法暂时先不用了，在考核类型中又父子关系才用这个方法
	 * @param pointTypeId
	 *//*
	public void selectByPointTypeId(List<EvalObject> objectList,EvalInstance evalTable,String pointTypeId,Date startime,Date endTime) {
		//根据分类编号查询考核项分类表，得到该考核项所属的考核项类型的下面的所有的子项集合
		List<DicEvalPointClass> evalPointList = DaoFactory.create(DicEvalPointClass.class)
								.getSession()
								.selectList("com.chinacreator.dzjc_performance.DicEvalPointClassMapper.selectByPointTypeId", pointTypeId);
		for (DicEvalPointClass dicEvalPointClass : evalPointList) {
			String isParent = dicEvalPointClass.getIsParent();
			if("Y".equals(isParent)){//判断当前的这个是否父项
				selectByPointTypeId(objectList,evalTable,dicEvalPointClass.getPointTypeId(),startime,endTime);//递归调用自己，查询考核项信息
			}else{
				//不是父项，根据这个考核项类型的pointTypeId来查询该考核项的详细信息，得到的是最底层的考核项，他是有考核公式的
				EvalItem evalItem = DaoFactory.create(EvalItem.class)
						.getSession()
						.selectOne("com.chinacreator.dzjc_performance.EvalItemMapper.selectByPointTypeId",
									dicEvalPointClass.getPointTypeId());
				
				*//**
				 * 得到考核项的考核公式
				 * 这个时候，考核项的计算公式是由考核指标做数学运算得到，有+，-，*，/，(),[]
				 * 所以这里需要对计算公式进行解析   比如考核公式是：(给你个+你好)*哈哈/[是*尼玛]
				 * 经过  FormulaUtil.parse(evalItemFormula); 后得到：给你个  你好  哈哈  是  尼玛
				 * 调用工具类解析公式字符串，得到该		考核项		的所有   计算项
				 *//*
				String evalItemFormula = evalItem.getItemFormula();
				List<String> evalPointNames = FormulaUtil.parse(evalItemFormula);
				
				for (String pointName : evalPointNames) {
					
					AutoGradeCalculator.getInstance().calculateEvalPoint(evalTable,evalItem,pointName,startime,endTime);//根据考核指标的名字计算考核指标的信息
					
				}
			}
		}
	}*/
	
	
	
	//根据考核项名称获取考核项信息
	public EvalItem getEvalItemByName(String itemName) {
		EvalItem item = new EvalItem();
		item.setItemName(itemName);
		EvalItem evalItem = DaoFactory.create(EvalItem.class).selectOne(item);
		return evalItem;
	}
	
	/**
	 * 绩效考核项分数
	 * @param itemId 考核项id
	 * @param evalObjectId 考核对象id
	 * @param tableId 考核表id
	 * @param instanceId 实例id
	 * return 考核项分数
	 */
	public Double getItemValue(String itemId, String evalObjectId, String tableId,String instanceId){
		EvalItem evalItem = DaoFactory.create(EvalItem.class).selectByID(itemId);
		String formula = evalItem.getItemFormula();
		//获取公式包含的所有计算项（指标）
		List<String> pointList = FormulaUtil.parse(formula);
		if(pointList == null ||pointList.size() < 1){
			return null;
		}
		EvalPointValue entity = new EvalPointValue();
		entity.setEvalObjectId(evalObjectId);
		entity.setTableId(tableId);
		entity.setEvalInstanceId(instanceId);
		//获取指定考核对象指定考核表的所有指标成绩
		List<EvalPointValue> pointValueList = DaoFactory.create(EvalPointValue.class).getSession().selectList(
				"com.chinacreator.dzjc_performance.EvalPointValueMapper.selectByTableAndObject", entity);	
		if(pointValueList == null ||pointValueList.size() < 1){
			return null;
		}
		DicEvalPoint dicEvalPoint =null;
		for(String pointName : pointList){
			//指标名称唯一
			for (EvalPointValue evalPointValue : pointValueList) {
				//如果已评分，直接获取分数值
				if(pointName.equals(evalPointValue.getPointName())){
					//判断是否评分,如果已评分就取实际分。未评分通过指标ID取该指标的最大值
					if("Y".equals(evalPointValue.getIsGrade())){
						Double value = evalPointValue.getPointScore();
						formula = formula.replaceAll(pointName, String.valueOf(value));
					}else{
						//未评分通过指标ID取改指标的最大值
						dicEvalPoint = new DicEvalPoint();
						dicEvalPoint=DaoFactory.create(DicEvalPoint.class).selectByID(evalPointValue.getPointId());
						Double value = dicEvalPoint.getMaxValue();
						formula = formula.replaceAll(pointName, String.valueOf(value));
					}
				}
				//如果自动评分，直接获取分数值
				if(pointName.equals(evalPointValue.getPointName()) && "Y".equals(evalPointValue.getIsAuto())){
					Double value = evalPointValue.getPointScore();
					formula = formula.replaceAll(pointName, String.valueOf(value));
				}
			}
		}
		//计算指标的分数
		JexlEngine jexlEngine = new JexlBuilder().create();
		JexlExpression jexlExpression = jexlEngine.createExpression(formula);
	    Double itemValue = (Double) jexlExpression.evaluate(null);
		//如果值大于最大值，且考核项定义为大于最大值取最大值
	    if("最大值".equals(evalItem.getMaxvalDepend())&&itemValue > evalItem.getMaxValue()){
	    	itemValue =  evalItem.getMaxValue();
	    }else if("最小值".equals(evalItem.getMinvalDepend())&&itemValue < evalItem.getMinValue()){
	    	itemValue = evalItem.getMinValue();
	    }

		return itemValue;
	}
	

}
