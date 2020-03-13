package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.SuperviseHuanJieTemp;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ExpressionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.ExpressionServicesIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.HuanJieSuperviseInfoServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineRunServiceIfc;
import com.chinacreator.util.StringUtil;

/**
 *<p>Title:</p>
 *<p>Description:简单规则运行</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author liaolong
 *@version 1.0
 *@date 2019-1-15
 */
public class SimpleHuanJieEngineRunServiceImpl implements RuleEngineRunServiceIfc {
	
	public void doRun2(RuleBean ruleBean, List<String> businessIdList,
			RuleWithBusinessBean ruleWithBusinessBean,
			ServletContext application) throws Exception {
		
		HuanJieSuperviseInfoServiceIfc superviseinfoservice = new SuperviseHuanJieInfoServiceImpl();
		ExpressionServicesIfc expreesionServises = new ExpressionServicesImpl();
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		//简单规则对应一个表达式（一个表达式对应多个表达式片段）
		try{
			ExpressionBean expreesionBean = ruleWithBusinessBean.getRuleIdAndExpressionBeanList().get(ruleBean.getRuleId()).get(0);

			String tempLevel;
			boolean needFlag = false;
			boolean lastFlag = false;
			Map<String,Object> superviseParamMap = null;
			
			List<ResultOpinion> huanjieList = new ArrayList<ResultOpinion>();
			ResultOpinion resultOpinion = null;
			
			boolean huanjieFlag = false;
			
			String superviseInfoId = "";
			Map<String,Object> logMap = null;
			String superviseLevelNo = "";
			
	        for(String businessId : businessIdList){
	        	tempLevel="";
	        	huanjieFlag = false;
	        	superviseLevelNo = ruleWithBusinessBean.getExpIdAndSuperviseDefinitionBean().get(expreesionBean.getExp_id()).getSupervise_level_code();
	        	logMap = ruleWithBusinessBean.getBusinessLogMap().get(businessId+"#"+superviseLevelNo);
	        	if(null != logMap){
	        		superviseInfoId = String.valueOf(logMap.get("superviseInfoId"));
	        	}
	        	if(StringUtil.isBlank(superviseInfoId) || "null".equals(superviseInfoId)){
	        		//表达式计算为true,执行
	        		if(expreesionServises.execute(expreesionBean, businessId, ruleWithBusinessBean)){
	        			superviseParamMap = new HashMap<String,Object>(); // 监察逻辑的参数
	        			superviseParamMap.put("businessId", businessId);
	        			if(ruleWithBusinessBean.getOrgIdMap() != null){
	        				superviseParamMap.put("orgId", ruleWithBusinessBean.getOrgIdMap().get(businessId));
	        			}
	        			if(ruleWithBusinessBean.getBusinessLogMap() != null){
	        				superviseParamMap.put("businessLogMap", logMap);
	        			}
	        			superviseParamMap.put("outDays", expreesionServises.getOutDays(expreesionBean, businessId, ruleWithBusinessBean));
	        			superviseParamMap.put("ruleBean", ruleBean);
	        			superviseParamMap.put("superviseDefBean",ruleWithBusinessBean.getExpIdAndSuperviseDefinitionBean().get(expreesionBean.getExp_id()));
	        			tempLevel = ruleWithBusinessBean.getExpIdAndSuperviseDefinitionBean().get(expreesionBean.getExp_id()).getSupervise_level_code();
	        			list.add(superviseParamMap);
	        		}
	        	}
	        	try {
					if(!StringUtil.isBlank(businessId)){
						for(ResultOpinion info : huanjieList){
							if(info.getOpinionId().equals(businessId)){
								if(Integer.valueOf(info.getLastSupervise()) < Integer.valueOf(tempLevel)){
									info.setLastSupervise(tempLevel);
								}
								huanjieFlag = true;
							}
						}
						if(!huanjieFlag){
							resultOpinion = new ResultOpinion();
							resultOpinion.setOpinionId(businessId);
							if(StringUtils.isNotBlank(tempLevel)){
								resultOpinion.setLastSupervise(tempLevel);
							}
							else {
								//如果这次没发牌，设为0，方便计算比较
								resultOpinion.setLastSupervise("0");
							}
							huanjieList.add(resultOpinion);
						}
					}
				} catch (Exception e) {
					System.err.println("环节监察判断是否需要反写异常："+e.getMessage());
					e.printStackTrace();
				}
	        }
	        try {
	        	//定义需要修改的列表
	        	List<SuperviseHuanJieTemp> updateList = new ArrayList<SuperviseHuanJieTemp>();
	        	SuperviseHuanJieTemp temp = null;
				//批量查询环节信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(huanjieList.size() > 0){
					List<ResultOpinion> queryList = DaoFactory.create(ResultOpinion.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.ResultOpinionMapper.selectFinishByBusinessId",huanjieList);
					if(queryList != null && queryList.size() >0){
						ResultOpinion info = null;
						for(int i = 0; i < queryList.size(); i++){
							huanjieFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise())){
								info.setLastSupervise("0");
							}
							if(StringUtils.isNotBlank(info.getLinkFlag()) 
									&& "1".equals(info.getLinkFlag())){
								info.setIsNeedSupervise("N");
								needFlag = true;
							}
							for(int j = 0; j < huanjieList.size(); j++){
								if(huanjieList.get(j).getOpinionId().equals(info.getOpinionId())){
									if(Integer.valueOf(huanjieList.get(j).getLastSupervise()) 
											> Integer.valueOf(info.getLastSupervise())){
										info.setLastSupervise(huanjieList.get(j).getLastSupervise());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseHuanJieTemp();
								temp.setBusinessId(info.getOpinionId());
								temp.setBusinessType("7");
								if(StringUtils.isBlank(info.getIsNeedSupervise())){
									temp.setIsNeedSupervise("Y");
								}
								else {
									temp.setIsNeedSupervise(info.getIsNeedSupervise());
								}
								if(StringUtils.isNotBlank(info.getLastSupervise()) 
										&& !"0".equals(info.getLastSupervise())){
									temp.setLastSupervise(info.getLastSupervise());
								}
								updateList.add(temp);
							}
						}
					}
				}
				//批量插入到临时表
				if(updateList.size() > 0){
					DaoFactory.create(ConsultInfo.class).getSession()
					.insert("com.chinacreator.dzjc_ruleEngine.SuperviseHuanJieTempMapper.insertBatch",updateList);
				}
			} catch (Exception e) {
				System.err.println("环节监察写入到反写临时表异常："+e.getMessage());
				e.printStackTrace();
			}
	        if(list.size()>0){
	        	superviseinfoservice.doSupervise(list,application);
	        }
		}catch(Exception e){
			System.err.println("环节监察表达式处理异常："+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void doRun(RuleBean ruleBean, List<String> businessIdList,
			RuleWithBusinessBean ruleWithBusinessBean,String feedbackDate) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doRunConsult(RuleBean ruleBean, List<String> businessIdList,
			RuleWithBusinessBean ruleWithBusinessBean, String feedbackDate)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
