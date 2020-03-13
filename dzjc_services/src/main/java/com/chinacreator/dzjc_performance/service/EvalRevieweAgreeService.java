package com.chinacreator.dzjc_performance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_performance.EvalComplainReviewe;
import com.chinacreator.dzjc_performance.EvalPointValue;
import com.chinacreator.dzjc_performance.EvalRevieweAgree;

@Service
public class EvalRevieweAgreeService {

	//插入审核意见，同时修改申诉审核表中   指标的状态
	@Transactional
	public void insertRevieweAgree(EvalRevieweAgree entity){
		try {
			int row = DaoFactory.create(EvalRevieweAgree.class).insert(entity);
			String agree = entity.getIsAgree();
			if("1".equals(agree)){
				agree = "2";
			}else if("2".equals(agree)){
				agree = "3";
			}
			if(row > 0){
				EvalComplainReviewe evalComplainReviewe = new EvalComplainReviewe();
				evalComplainReviewe.setId(entity.getRevieweId());
				evalComplainReviewe.setRevieweStates(agree);
				
				//修改申诉审核表中  指标的状态
				DaoFactory.create(EvalComplainReviewe.class).update(evalComplainReviewe);
				
				//如果是申诉审核通过了的话，应该把指标成绩表中，对应的指标成绩状态改为未评分
				if("2".equals(evalComplainReviewe.getRevieweStates())){//审核通过，修改指标成绩为未评分
					EvalPointValue evalPointValue = new EvalPointValue();
					evalPointValue.setIsGrade("N");
					evalPointValue.setId(entity.getEvalPointValueId());
					DaoFactory.create(EvalPointValue.class).update(evalPointValue);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
