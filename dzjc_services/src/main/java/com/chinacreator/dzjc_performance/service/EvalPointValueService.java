package com.chinacreator.dzjc_performance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.EvalComplainReviewe;
import com.chinacreator.dzjc_performance.EvalPointValue;
import com.chinacreator.dzjc_performance.EvalPointValueHis;
import com.chinacreator.util.BeanUtil;
import com.chinacreator.util.BeanUtils;
import com.chinacreator.util.StringUtil;
/**
 * 指标成绩表的service
 * @author Administrator
 *
 */
@Service
public class EvalPointValueService {

	@Transactional(rollbackFor=Exception.class)
	public void updatePointValue(EvalPointValue evalPointValue){
		//获取指标成绩表的类型
		String objectType = evalPointValue.getEvalObjectType();
		if("部门".equals(objectType)){
			evalPointValue.setEvalObjectType("1");
		}else if("窗口".equals(objectType)) {
			evalPointValue.setEvalObjectType("2");
		}else {
			evalPointValue.setEvalObjectType("3");
		}
		//获取指标的评分
		Double pointScore = evalPointValue.getPointScore();
		//获取本次减分
		Double subtraction = evalPointValue.getSubtraction();
		if(subtraction!=null){
			pointScore = pointScore-subtraction;
		}
		String pointId = evalPointValue.getPointId();
		//根据指标id查询指标详情
		DicEvalPoint evalPoint = DaoFactory.create(DicEvalPoint.class).selectByID(pointId);
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
	    evalPointValue.setPointScore(pointScore);
	    
	   // evalPointValue.setSubtraction(subtraction);
	    
	    evalPointValue.setEvalTime(new java.sql.Date(new Date().getTime()));//考评时间，评分时间
	    
	    //保存指标分数信息
	    DaoFactory.create(EvalPointValue.class).update(evalPointValue);
	    
	    Map<String, String> map= StringUtil.getUserInfo();
	
	    EvalPointValueHis evalPointValueHis = new EvalPointValueHis();
	    evalPointValueHis.setOperatorName(map.get("name"));
	    evalPointValueHis.setTaJcEvalPointValueId(evalPointValue.getId());
	    evalPointValueHis.setPointName(evalPointValue.getPointName());
	    evalPointValueHis.setPointScore(pointScore);
	    evalPointValueHis.setSubtraction(subtraction);
	    evalPointValueHis.setScoringTime(new java.sql.Date(new Date().getTime()));
	    evalPointValueHis.setDeductReasons(evalPointValue.getDeductReasons());
	    //保存历史扣分记录
	    DaoFactory.create(EvalPointValueHis.class).insert(evalPointValueHis);
	}
	
	
	
	//更新申诉状态
	@Transactional(rollbackFor=Exception.class)
	public void updateRevieweStates(EvalPointValue entity) {
		//获取指标成绩表的类型
		String objectType = entity.getEvalObjectType();
		if("部门".equals(objectType)){
			entity.setEvalObjectType("1");
		}else if("窗口".equals(objectType)) {
			entity.setEvalObjectType("2");
		}else {
			entity.setEvalObjectType("3");
		}
		entity.setCompTime(new java.sql.Date(new Date().getTime()));
		entity.setIsComplain("Y");
		int row = DaoFactory.create(EvalPointValue.class).update(entity);
		
		if(row > 0){//说明申诉成功，这时把信息保存到申诉审核表中
	    	insertEvalComplainReviewe(entity);
	    }
	}
	

	//插入信息到申诉审核表中
	public void insertEvalComplainReviewe(EvalPointValue evalPointValue) {
		EvalComplainReviewe complainReviewe = new EvalComplainReviewe();
		complainReviewe.setEvalInstanceId(evalPointValue.getEvalInstanceId());//测评实例id
		complainReviewe.setTableId(evalPointValue.getTableId());//考核表id
		complainReviewe.setTableName(evalPointValue.getTableName());//考核表名称
		complainReviewe.setPointId(evalPointValue.getPointId());//指标id
		complainReviewe.setPointName(evalPointValue.getPointName());//指标名称
		complainReviewe.setPointScore(evalPointValue.getPointScore());//指标分数
		complainReviewe.setOrgId(evalPointValue.getOrgId());//测评对象id
		complainReviewe.setOrgName(evalPointValue.getOrgName());//测评对象名称
		complainReviewe.setCompReasons(evalPointValue.getCompReasons());//申诉原因
		complainReviewe.setCompTime(evalPointValue.getCompTime());//申诉时间
		complainReviewe.setDeductReasons(evalPointValue.getDeductReasons());//扣分原因
		
		complainReviewe.setEvalObjectId(evalPointValue.getEvalObjectId());//考核对象id
		complainReviewe.setEvalObjectName(evalPointValue.getEvalObjectName());//考核对象名称
		complainReviewe.setEvalObjectType(evalPointValue.getEvalObjectType());//考核对象类型
		complainReviewe.setEvalPointValueId(evalPointValue.getId());//关联的指标成绩表id
		complainReviewe.setEvalTime(evalPointValue.getEvalTime());//考核时间，也就是评分时间
		
		complainReviewe.setRevieweStates("1");//审核状态 (1-审核，2-通过，3-驳回)
		
		DaoFactory.create(EvalComplainReviewe.class).insert(complainReviewe);//插入信息到申诉审核表中
	}
	
	/**
	 * 根据实例id删除对应的考核指标的成绩信息的方法
	 */
	public Integer deletePointValueByInstanceId(String instanceId){
		
		int rows = DaoFactory.create(EvalPointValue.class).getSession()
				.delete("com.chinacreator.dzjc_performance.EvalPointValueMapper.deletePointValueByInstanceId", instanceId);
		
		return rows;
	}
	
	/**
	 * 定时器，当前时间定时运行的时候，检查考核指标的的测评结束时间是否小于当前时间
	 * 如果小于当前时间，则需要修改改考核指标的状态为已评分，并且成绩就默认是0.0
	 * 该定时器还没进行配置，配置即可生效
	 */
	public void updatePointStateByTime(){
		System.out.println("=====================================系统自动扫描超出测评时间未评分的指标===开始========================================");
		String isGrade = "N";//未评分
		List<EvalPointValue> pointLists = DaoFactory.create(EvalPointValue.class).getSession()
			.selectList("com.chinacreator.dzjc_performance.EvalPointValueMapper.selectListByIsGrade", isGrade);
		
		List<EvalPointValue> list = new ArrayList<>();
		
		for (EvalPointValue evalPointValue : pointLists) {
			Date complainEndTime = evalPointValue.getComplainEndTime();//取的是申诉结束时间，来自于实例表的测评结束时间
			if(new Date().getTime() > complainEndTime.getTime()){
				evalPointValue.setIsGrade("Y");
				evalPointValue.setDeductReasons("无");
				evalPointValue.setRemark("由于该指标未在测评期间进行评分，最终是系统默认的分数！！！");
				list.add(evalPointValue);
			}
		}
		
		//批量更新指标成绩信息
		DaoFactory.create(EvalPointValue.class).updateBatch(list);
		
		System.out.println("=====================================系统自动评分超出测评时间未评分的指标===结束========================================");
		
	}

	
}
