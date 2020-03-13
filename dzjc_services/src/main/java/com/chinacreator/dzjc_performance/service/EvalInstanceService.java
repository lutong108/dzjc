package com.chinacreator.dzjc_performance.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_performance.EvalInstance;
import com.chinacreator.dzjc_performance.EvalObject;
import com.chinacreator.dzjc_performance.EvalTable;
import com.chinacreator.dzjc_performance.eval.calculators.ItemCalculatorFactory;
import com.chinacreator.dzjc_performance.eval.exception.CalculateException;
import com.chinacreator.dzjc_performance.eval.exception.DeleteException;
import com.sun.star.uno.RuntimeException;

/**
 * 绩效测评实例登记的数据下发操作
 * @author Administrator
 *
 */
@Service
public class EvalInstanceService {
	
	
	@Autowired
	EvalObjectService evalObjectService;
	
	@Autowired
	EvalPointValueService evalPointValueService;
	
	@Autowired
	EvalItemValueService evalItemValueService;
	/**
	 * 数据下发操作，把各个指标的分数记录插入到 绩效测评指标成绩表（TA_JC_EVAL_POINT_VALUE）中，
	 * 包括人工和自动的，人工的实际分数插入为0.0，如果是自动的，注意修改状态为已评分（默认是未评分）
	 * 参考老系统中：/dzjc/eval/EvalServlet.java
	 */
	@Transactional(rollbackFor=Exception.class)
	public String calculateValue(EvalInstance entity,EvalObject evalObject){
		try {
			//插入测评实例信息，获取到测评实例id
			DaoFactory.create(EvalInstance.class).insert(entity);
			
			evalObject.setEvalInstanceId(entity.getId());//把测评实例id赋值给考核对象
			List<EvalObject> objectList = evalObjectService.insertBatch(evalObject);//保存考核对象信息到考核对象表中
					
			
			EvalTable evalTable = DaoFactory.create(EvalTable.class)
								.selectByID(entity.getTableId());//根据测评实例的表id查询考核表的相关信息
			
			String formula = evalTable.getTableFormula();//得到考核公式
			String[] evalItemNames = formula.split("\\+");//以+分割考核公式,得到考核项的名字的数组
			
			//测评开始和结束时间
			Date startTime = entity.getEvalStartTime();
			Date endTime = entity.getEvalEndTime();
			
			
			List<String> pointNameList = new ArrayList<>();//用来存放已经计算过分数的考核指标名称
			
			for (String itemName : evalItemNames) {
				
				ItemCalculatorFactory.getInstance().calculateEvalItem(pointNameList,objectList,entity,itemName,startTime,endTime);//根据考核项名称计算考核项信息
			
			}
			//int i = 10/0;
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//throw new RuntimeException(entity.getTableName()+"表数据下发失败==="+e.getMessage());
			throw new CalculateException("error");
		} 
		
	}
	
	/**
	 * 实例删除的方法
	 * 同时删除该实例下面的所有的考核对象和指标的成绩
	 */
	@Transactional(rollbackFor=Exception.class)
	public String deleteByInstanceId(List<String> instanceIds){
		String message = "";
		
		try {
			DaoFactory.create(EvalInstance.class).deleteBatch(instanceIds);//不管是单条还是多条，都调用批量删除
			
			//删除和实例相关的指标成绩信息和考核对象信息
			for (String instanceId : instanceIds) {
				//调用删除指标成绩信息和考核对象的方法
				deleteObjectAndPointValue(instanceId);
			}
			//int i = 20/0;
			message = "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DeleteException("error");
		}
		
		return message;
	}

	//删除考核对象和指标成绩的方法
	private void  deleteObjectAndPointValue(String instanceId) {
		// TODO Auto-generated method stub
		evalObjectService.deleteBatch(instanceId);		
		evalPointValueService.deletePointValueByInstanceId(instanceId);
		evalItemValueService.deleteItemValue(instanceId);
	}

	
}
