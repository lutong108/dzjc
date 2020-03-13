package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.TaJcComplainBaseinfo;
import com.chinacreator.dzjc_ruleEngine.SuperviseTemp;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ExpressionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.ExpressionServicesIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineRunServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SuperviseInfoServiceIfc;
import com.chinacreator.util.StringUtil;

/**
 *<p>Title:</p>
 *<p>Description:简单规则运行</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public class SimpleRuleEngineRunServiceImpl implements RuleEngineRunServiceIfc {
	
	//private static final Logger LOG = Logger.getLogger(SimpleRuleEngineRunServiceImpl.class.getName());


	public void doRun(RuleBean ruleBean, List<String> businessIdList,
			RuleWithBusinessBean ruleWithBusinessBean,String feedbackDate) throws Exception {
		//SuperviseInfoDao superviseInfoDao = new SuperviseInfoDaoImpl();
		SuperviseInfoServiceIfc superviseinfoservice = new SuperviseInfoServiceImpl();
		ExpressionServicesIfc expreesionServises = new ExpressionServicesImpl();
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		//简单规则对应一个表达式（一个表达式对应多个表达式片段）
		ExpressionBean expreesionBean = ruleWithBusinessBean.getRuleIdAndExpressionBeanList().get(ruleBean.getRuleId()).get(0);
		//办件已办结状态字符串
		String instanceStatus = "4,5,6,7,8";
		try{
			String tempLevel;
			boolean needFlag = false;
			boolean lastFlag = false;
			Map<String,Object> superviseParamMap = null;
			Map<String, String> bussinessTypeMap = null;
			List<InstanceInfo> instanceList = new ArrayList<InstanceInfo>();
			List<TaJcComplainBaseinfo> complainList = new ArrayList<TaJcComplainBaseinfo>();
			List<ConsultInfo> consultList = new ArrayList<ConsultInfo>();
			List<TbcxsqInfoBean> suspendList = new ArrayList<TbcxsqInfoBean>();
			List<InstanceInfo> internetList = new ArrayList<InstanceInfo>();
			List<InstanceInfo> dzzzList = new ArrayList<InstanceInfo>();
			InstanceInfo instance = null;
			TaJcComplainBaseinfo complain = null;
			ConsultInfo consultInfo = null;
			TbcxsqInfoBean suspend = null;
			InstanceInfo internet = null;
			InstanceInfo dzzz = null;
			boolean instanceFlag = false;
			boolean complainFlag = false;
			boolean consultFlag = false;
			boolean suspendFlag = false;
			boolean internetFlag = false;
			boolean dzzzFlag = false;
			String suspendId = "";
			String internetId = "";
			String dzzzId = "";
			//String superviseTypeNo = "";
			String superviseInfoId = "";
			Map<String,Object> logMap = null;
			String superviseLevelNo = "";
			
	        for(String businessId : businessIdList){
	        	tempLevel="";
	        	instanceFlag = false;
	        	complainFlag = false;
	        	consultFlag = false;
	        	suspendFlag = false;
	        	internetFlag = false;
	        	dzzzFlag = false;
	        	superviseLevelNo = ruleWithBusinessBean.getExpIdAndSuperviseDefinitionBean().get(expreesionBean.getExp_id()).getSupervise_level_code();
	        	//superviseInfoId = ruleWithBusinessBean.getExpIdAndSuperviseDefinitionBean().get(expreesionBean.getExp_id()).getSuperviseInfoId();
	        	//如果以前没有监察过，执行
	        	//if(superviseInfoDao.isSuperviseBefore(businessId, ruleBean.getRuleId(), superviseTypeNo)){
	        	logMap = ruleWithBusinessBean.getBusinessLogMap().get(businessId+"#"+superviseLevelNo);
	        	if(null != logMap){
	        		superviseInfoId = String.valueOf(logMap.get("superviseInfoId"));
	        		logMap.put("feedbackDate", feedbackDate);
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
						bussinessTypeMap = ruleWithBusinessBean.getBussinessTypeMap();
						String bussinessType = bussinessTypeMap.get(businessId);
						switch (bussinessType) {
							//查询是否办结, 已办结 修改为不需要监察
							//tempLevel		最新发牌
							case "1":
								//办件
								for(InstanceInfo info : instanceList){
									if(info.getInstanceId().equals(businessId)){
										if(Integer.valueOf(info.getLastSupervise()) < Integer.valueOf(tempLevel)){
											info.setLastSupervise(tempLevel);
										}
										instanceFlag = true;
										break;
									}
								}
								if(!instanceFlag){
									instance = new InstanceInfo();
									instance.setInstanceId(businessId);
									if(StringUtils.isNotBlank(tempLevel)){
										instance.setLastSupervise(tempLevel);
									}
									else {
										//如果这次没发牌，设为0，方便计算比较
										instance.setLastSupervise("0");
									}
									instanceList.add(instance);
								}
								break;
							case "2":
								//投诉 
								for(TaJcComplainBaseinfo info : complainList){
									if(info.getComplainId().equals(businessId)){
										if(Integer.valueOf(info.getLastSupervise()) < Integer.valueOf(tempLevel)){
											info.setLastSupervise(tempLevel);
										}
										complainFlag = true;
										break;
									}
								}
								if(!complainFlag){
									complain = new TaJcComplainBaseinfo();
									complain.setComplainId(businessId);
									if(StringUtils.isNotBlank(tempLevel)){
										complain.setLastSupervise(tempLevel);
									}
									else {
										//如果这次没发牌，设为0，方便计算比较
										complain.setLastSupervise("0");
									}
									complainList.add(complain);
								}
								break;
							case "3":
								//咨询
								for(ConsultInfo info : consultList){
									if(info.getConsultId().equals(businessId)){
										if(Integer.valueOf(info.getLastSupervise()) < Integer.valueOf(tempLevel)){
											info.setLastSupervise(tempLevel);
										}
										consultFlag = true;
										break;
									}
								}
								if(!consultFlag){
									consultInfo = new ConsultInfo();
									consultInfo.setConsultId(businessId);
									if(StringUtils.isNotBlank(tempLevel)){
										consultInfo.setLastSupervise(tempLevel);
									}
									else {
										//如果这次没发牌，设为0，方便计算比较
										consultInfo.setLastSupervise("0");
									}
									consultList.add(consultInfo);
								}
								break;
							case "4":
								//特别程序
								for(TbcxsqInfoBean info : suspendList){
									if(businessId.indexOf("$$") != -1){
										suspendId = businessId.substring(businessId.indexOf("$$")+2, businessId.length());
									}
									else {
										suspendId = businessId;
									}
									if(info.getSuspendId().equals(suspendId)){
										if(Integer.valueOf(info.getLastSupervise()) < Integer.valueOf(tempLevel)){
											info.setLastSupervise(tempLevel);
										}
										suspendFlag = true;
										break;
									}
								}
								if(!suspendFlag){
									suspend = new TbcxsqInfoBean();
									if(businessId.indexOf("$$") != -1){
										suspend.setSuspendId(businessId.substring(businessId.indexOf("$$")+2, businessId.length()));
									}
									else {
										suspend.setSuspendId(businessId);
									}
									if(StringUtils.isNotBlank(tempLevel)){
										suspend.setLastSupervise(tempLevel);
									}
									else {
										//如果这次没发牌，设为0，方便计算比较
										suspend.setLastSupervise("0");
									}
									suspendList.add(suspend);
								}
								break;
							case "5":
								//网上办事
								for(InstanceInfo info : internetList){
									if(businessId.indexOf("##") != -1){
										internetId = businessId.substring(0, businessId.indexOf("##"));
									}
									else {
										internetId = businessId;
									}
									if(info.getInstanceId().equals(internetId)){
										if(Integer.valueOf(info.getLastSupervise2()) < Integer.valueOf(tempLevel)){
											info.setLastSupervise2(tempLevel);
										}
										internetFlag = true;
										break;
									}
								}
								if(!internetFlag){
									internet = new InstanceInfo();
									if(businessId.indexOf("##") != -1){
										internet.setInstanceId(businessId.substring(0, businessId.indexOf("##")));
									}
									else {
										internet.setInstanceId(businessId);
									}
									if(StringUtils.isNotBlank(tempLevel)){
										internet.setLastSupervise2(tempLevel);
									}
									else {
										//如果这次没发牌，设为0，方便计算比较
										internet.setLastSupervise2("0");
									}
									internetList.add(internet);
								}
								break;
							case "6":
								//电子证照
								for(InstanceInfo info : dzzzList){
									if(businessId.indexOf("@@") != -1){
										dzzzId = businessId.substring(0, businessId.indexOf("@@"));
									}
									else {
										dzzzId = businessId;
									}
									if(info.getInstanceId().equals(dzzzId)){
										if(Integer.valueOf(info.getLastSupervise3()) < Integer.valueOf(tempLevel)){
											info.setLastSupervise3(tempLevel);
										}
										dzzzFlag = true;
										break;
									}
								}
								if(!dzzzFlag){
									dzzz = new InstanceInfo();
									if(businessId.indexOf("@@") != -1){
										dzzz.setInstanceId(businessId.substring(0, businessId.indexOf("@@")));
									}
									else {
										dzzz.setInstanceId(businessId);
									}
									if(StringUtils.isNotBlank(tempLevel)){
										dzzz.setLastSupervise3(tempLevel);
									}
									else {
										//如果这次没发牌，设为0，方便计算比较
										dzzz.setLastSupervise3("0");
									}
									dzzzList.add(dzzz);
								}
								break;
							default:
								break;
						}
					}
				} catch (Exception e) {
					System.err.println("监察规则在判断是否需要反写时异常："+e.getMessage());
					e.printStackTrace();
				}
	        }
	        try {
	        	//定义需要修改的列表
	        	List<SuperviseTemp> updateList = new ArrayList<SuperviseTemp>();
	        	SuperviseTemp temp = null;
				//批量查询办件信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(instanceList.size() > 0){
					List<InstanceInfo> queryList = DaoFactory.create(InstanceInfo.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectFinishByBusinessId",instanceList);
					if(queryList != null && queryList.size() >0){
						InstanceInfo info = null;
						for(int i = 0; i < queryList.size(); i++){
							instanceFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise())){
								info.setLastSupervise("0");
							}
							if(instanceStatus.indexOf(info.getProjectState()) >= 0){
								info.setIsNeedSupervise("N");
								needFlag = true;
							}
							for(int j = 0; j < instanceList.size(); j++){
								if(instanceList.get(j).getInstanceId().equals(info.getInstanceId())){
									if(Integer.valueOf(instanceList.get(j).getLastSupervise()) 
											> Integer.valueOf(info.getLastSupervise())){
										info.setLastSupervise(instanceList.get(j).getLastSupervise());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseTemp();
								temp.setBusinessId(info.getInstanceId());
								temp.setBusinessType("1");
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
				//批量查询投诉信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(complainList.size() > 0){
					List<TaJcComplainBaseinfo> queryList = DaoFactory.create(TaJcComplainBaseinfo.class).getSession()
							.selectList("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectFinishByBusinessId",complainList);
					if(queryList != null && queryList.size() >0){
						TaJcComplainBaseinfo info = null;
						for(int i = 0; i < queryList.size(); i++){
							complainFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise())){
								info.setLastSupervise("0");
							}
							if(info.getEndTime() != null){
								info.setIsNeedSupervise("N");
								needFlag = true;
							}
							for(int j = 0; j < complainList.size(); j++){
								if(complainList.get(j).getComplainId().equals(info.getComplainId())){
									if(Integer.valueOf(complainList.get(j).getLastSupervise()) 
											> Integer.valueOf(info.getLastSupervise())){
										info.setLastSupervise(complainList.get(j).getLastSupervise());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseTemp();
								temp.setBusinessId(info.getComplainId());
								temp.setBusinessType("2");
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
				//批量查询咨询信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(consultList.size() > 0){
					List<ConsultInfo> queryList = DaoFactory.create(ConsultInfo.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfoMapper.selectFinishByBusinessId",consultList);
					if(queryList != null && queryList.size() >0){
						ConsultInfo info = null;
						for(int i = 0; i < queryList.size(); i++){
							consultFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise())){
								info.setLastSupervise("0");
							}
							if(info.getReplyTime() != null){
								info.setIsNeedSupervise("N");
								needFlag = true;
							}
							for(int j = 0; j < consultList.size(); j++){
								if(consultList.get(j).getConsultId().equals(info.getConsultId())){
									if(Integer.valueOf(consultList.get(j).getLastSupervise()) 
											> Integer.valueOf(info.getLastSupervise())){
										info.setLastSupervise(consultList.get(j).getLastSupervise());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseTemp();
								temp.setBusinessId(info.getConsultId());
								temp.setBusinessType("3");
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
				//批量查询特别程序信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(suspendList.size() > 0){
					List<TbcxsqInfoBean> queryList = DaoFactory.create(TbcxsqInfoBean.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBeanMapper.selectFinishByBusinessId",suspendList);
					if(queryList != null && queryList.size() >0){
						TbcxsqInfoBean info = null;
						for(int i = 0; i < queryList.size(); i++){
							suspendFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise())){
								info.setLastSupervise("0");
							}
							if(info.getEndDate() != null){
								info.setIsNeedSupervise("N");
								needFlag = true;
							}
							for(int j = 0; j < suspendList.size(); j++){
								if(suspendList.get(j).getSuspendId().equals(info.getSuspendId())){
									if(Integer.valueOf(suspendList.get(j).getLastSupervise()) 
											> Integer.valueOf(info.getLastSupervise())){
										info.setLastSupervise(suspendList.get(j).getLastSupervise());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseTemp();
								temp.setBusinessId(info.getSuspendId());
								temp.setBusinessType("4");
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
				//批量查询网办信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(internetList.size() > 0){
					List<InstanceInfo> queryList = DaoFactory.create(InstanceInfo.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectFinishByInternetId",internetList);
					if(queryList != null && queryList.size() >0){
						InstanceInfo info = null;
						for(int i = 0; i < queryList.size(); i++){
							internetFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise2())){
								info.setLastSupervise2("0");
							}
							if(info.getPretrialTime() != null){
								info.setIsNeedSupervise2("N");
								needFlag = true;
							}
							for(int j = 0; j < internetList.size(); j++){
								if(internetList.get(j).getInstanceId().equals(info.getInstanceId())){
									if(Integer.valueOf(internetList.get(j).getLastSupervise2()) 
											> Integer.valueOf(info.getLastSupervise2())){
										info.setLastSupervise2(internetList.get(j).getLastSupervise2());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseTemp();
								temp.setBusinessId(info.getInstanceId());
								temp.setBusinessType("5");
								if(StringUtils.isBlank(info.getIsNeedSupervise2())){
									temp.setIsNeedSupervise("Y");
								}
								else {
									temp.setIsNeedSupervise(info.getIsNeedSupervise2());
								}
								if(StringUtils.isNotBlank(info.getLastSupervise2()) 
										&& !"0".equals(info.getLastSupervise2())){
									temp.setLastSupervise(info.getLastSupervise2());
								}
								updateList.add(temp);
							}
						}
					}
				}
				//批量查询办件的电子证照信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(dzzzList.size() > 0){
					List<InstanceInfo> queryList = DaoFactory.create(InstanceInfo.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectFinishByDzzzId",dzzzList);
					if(queryList != null && queryList.size() >0){
						InstanceInfo info = null;
						for(int i = 0; i < queryList.size(); i++){
							dzzzFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise3())){
								info.setLastSupervise3("0");
							}
							if(info.getPretrialTime() != null){
								info.setIsNeedSupervise3("N");
								needFlag = true;
							}
							for(int j = 0; j < dzzzList.size(); j++){
								if(dzzzList.get(j).getInstanceId().equals(info.getInstanceId())){
									if(Integer.valueOf(dzzzList.get(j).getLastSupervise3()) 
											> Integer.valueOf(info.getLastSupervise3())){
										info.setLastSupervise3(dzzzList.get(j).getLastSupervise3());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseTemp();
								temp.setBusinessId(info.getInstanceId());
								temp.setBusinessType("6");
								if(StringUtils.isBlank(info.getIsNeedSupervise3())){
									temp.setIsNeedSupervise("Y");
								}
								else {
									temp.setIsNeedSupervise(info.getIsNeedSupervise3());
								}
								if(StringUtils.isNotBlank(info.getLastSupervise3()) 
										&& !"0".equals(info.getLastSupervise3())){
									temp.setLastSupervise(info.getLastSupervise3());
								}
								updateList.add(temp);
							}
						}
					}
				}
				//批量插入到临时表
				if(updateList.size() > 0){
					DaoFactory.create(ConsultInfo.class).getSession()
					.insert("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.insertBatch",updateList);
				}
			} catch (Exception e) {
				System.err.println("监察规则在写入反写表时异常："+e.getMessage());
				e.printStackTrace();
			}
	        if(list.size()>0){
	        	superviseinfoservice.doSupervise(list);
	        }
		}catch(Exception e){
			System.err.println("监察规则运行时异常："+e.getMessage());
			e.printStackTrace();
		}
	}

	public void doRunConsult(RuleBean ruleBean, List<String> businessIdList,
			RuleWithBusinessBean ruleWithBusinessBean,String feedbackDate) throws Exception {
		SuperviseInfoServiceIfc superviseinfoservice = new SuperviseInfoServiceImpl();
		ExpressionServicesIfc expreesionServises = new ExpressionServicesImpl();
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		//简单规则对应一个表达式（一个表达式对应多个表达式片段）
		ExpressionBean expreesionBean = ruleWithBusinessBean.getRuleIdAndExpressionBeanList().get(ruleBean.getRuleId()).get(0);
		try{
			String tempLevel;
			boolean needFlag = false;
			boolean lastFlag = false;
			Map<String,Object> superviseParamMap = null;
			Map<String, String> bussinessTypeMap = null;
			List<ConsultInfo> consultList = new ArrayList<ConsultInfo>();
			ConsultInfo consultInfo = null;
			boolean consultFlag = false;
			String superviseInfoId = "";
			Map<String,Object> logMap = null;
			String superviseLevelNo = "";
			
	        for(String businessId : businessIdList){
	        	tempLevel="";
	        	consultFlag = false;
	        	superviseLevelNo = ruleWithBusinessBean.getExpIdAndSuperviseDefinitionBean().get(expreesionBean.getExp_id()).getSupervise_level_code();
	        	//superviseInfoId = ruleWithBusinessBean.getExpIdAndSuperviseDefinitionBean().get(expreesionBean.getExp_id()).getSuperviseInfoId();
	        	//如果以前没有监察过，执行
	        	//if(superviseInfoDao.isSuperviseBefore(businessId, ruleBean.getRuleId(), superviseTypeNo)){
	        	logMap = ruleWithBusinessBean.getBusinessLogMap().get(businessId+"#"+superviseLevelNo);
	        	if(null != logMap){
	        		superviseInfoId = String.valueOf(logMap.get("superviseInfoId"));
	        		logMap.put("feedbackDate", feedbackDate);
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
						bussinessTypeMap = ruleWithBusinessBean.getBussinessTypeMap();
						String bussinessType = bussinessTypeMap.get(businessId);
						switch (bussinessType) {
							//查询是否办结, 已办结 修改为不需要监察
							//tempLevel		最新发牌
							case "3":
								//咨询
								for(ConsultInfo info : consultList){
									if(info.getConsultId().equals(businessId)){
										if(Integer.valueOf(info.getLastSupervise()) < Integer.valueOf(tempLevel)){
											info.setLastSupervise(tempLevel);
										}
										consultFlag = true;
										break;
									}
								}
								if(!consultFlag){
									consultInfo = new ConsultInfo();
									consultInfo.setConsultId(businessId);
									if(StringUtils.isNotBlank(tempLevel)){
										consultInfo.setLastSupervise(tempLevel);
									}
									else {
										//如果这次没发牌，设为0，方便计算比较
										consultInfo.setLastSupervise("0");
									}
									consultList.add(consultInfo);
								}
								break;
							default:
								break;
						}
					}
				} catch (Exception e) {
					System.err.println("咨询监察规则在判断是否需要反写时异常："+e.getMessage());
					e.printStackTrace();
				}
	        }
	        try {
	        	//定义需要修改的列表
	        	List<SuperviseTemp> updateList = new ArrayList<SuperviseTemp>();
	        	SuperviseTemp temp = null;
				//批量查询咨询信息，并且与本次发牌结果比对，如果需要修改，则批量修改
				if(consultList.size() > 0){
					List<ConsultInfo> queryList = DaoFactory.create(ConsultInfo.class).getSession()
							.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfoMapper.selectFinishByBusinessId",consultList);
					if(queryList != null && queryList.size() >0){
						ConsultInfo info = null;
						for(int i = 0; i < queryList.size(); i++){
							consultFlag = false;
							needFlag = false;
							lastFlag = false;
							info = queryList.get(i);
							if(StringUtils.isBlank(info.getLastSupervise())){
								info.setLastSupervise("0");
							}
							if(info.getReplyTime() != null){
								info.setIsNeedSupervise("N");
								needFlag = true;
							}
							for(int j = 0; j < consultList.size(); j++){
								if(consultList.get(j).getConsultId().equals(info.getConsultId())){
									if(Integer.valueOf(consultList.get(j).getLastSupervise()) 
											> Integer.valueOf(info.getLastSupervise())){
										info.setLastSupervise(consultList.get(j).getLastSupervise());
										lastFlag = true;
									}
									break;
								}
							}
							if(needFlag || lastFlag){
								temp = new SuperviseTemp();
								temp.setBusinessId(info.getConsultId());
								temp.setBusinessType("3");
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
					.insert("com.chinacreator.dzjc_ruleEngine.SuperviseTempMapper.insertBatchConsult",updateList);
				}
			} catch (Exception e) {
				System.err.println("咨询监察规则在写入反写表时异常："+e.getMessage());
				e.printStackTrace();
			}
	        if(list.size()>0){
	        	superviseinfoservice.doSupervise(list);
	        }
		}catch(Exception e){
			System.err.println("咨询监察规则运行时异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void doRun2(RuleBean ruleBean, List<String> businessIdList,
			RuleWithBusinessBean ruleWithBusinessBean,
			ServletContext application) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
