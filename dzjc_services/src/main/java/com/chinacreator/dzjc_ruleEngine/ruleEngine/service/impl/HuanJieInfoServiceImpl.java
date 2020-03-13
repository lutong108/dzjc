package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.HuanJieInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.HuanJieInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ExpressionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ExpressionLetBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAll;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseDefinitionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.HuanJieInfoServiceIfc;

/**
 * @author created by liaolong 2019-1-14 
 */
public class HuanJieInfoServiceImpl implements HuanJieInfoServiceIfc {


	@Override
	public RuleWithBusinessBean getRuleWithBusinessBeanList(List<ResultOpinion> list1) {

		HuanJieInfoDao dao = new HuanJieInfoDaoImpl();

		List<RuleBaseinfoBeanAll> list = dao.getRuleBaseinfoBeanAllList(list1);

		RuleWithBusinessBean ruleWithBusinessBean = null;

		if (list != null && list.size() > 0) {

			ruleWithBusinessBean = new RuleWithBusinessBean();

			// 规则信息
			Map<String, RuleBean> ruleIdAndRuleBeanMap = new HashMap<String, RuleBean>();
			// Map<规则信息ID,务ID>
			Map<String, List<String>> ruleBeanAndBusinessIdList = new HashMap<String, List<String>>();
			// 表达式信息（Map<String[:rule_id],List<ExpressionBean>>）
			Map<String, List<ExpressionBean>> ruleIdAndExpressionBeanList = new HashMap<String, List<ExpressionBean>>();
			// 表达式片段信息（Map<String[:exp_id],List<ExpressionLetBean>>）
			Map<String, List<ExpressionLetBean>> expIdAndExpressionLetBeanList = new HashMap<String, List<ExpressionLetBean>>();
			// 规则定义信息（Map<String[:exp_id],SuperviseDefinitionBean>）
			Map<String, SuperviseDefinitionBean> expIdAndSuperviseDefinitionBean = new HashMap<String, SuperviseDefinitionBean>();

			// 临时用于存入规则信息map（只用于为了判断同一业务同一规则只有一次）
			Map<String, Map<String, String>> ruleidAndbusinessidlistMap = new HashMap<String, Map<String, String>>();
			// 用于存入规则信息Bean的map（只留下唯一对象）
			Map<String, ExpressionBean> expressionBeanMap = new HashMap<String, ExpressionBean>();
			// 用于存入表达式片段Bean的map（只留下唯一对象）
			Map<String, ExpressionLetBean> expressionLetBeanMap = new HashMap<String, ExpressionLetBean>();
			// 用于存入业务id所属业务类型
		     Map<String, String> bussinessTypeMap =  new HashMap<String, String>();
		     // 用于存入业务id所属单位ID
		     Map<String, String> orgIdMap =  new HashMap<String, String>();
		     // 用于存入业务id当前的信息
		     Map<String, Map<String, Object>> businessLogMap =  new HashMap<String, Map<String, Object>>();
		     Map<String, Object> logMap = null;

		    RuleBean ruleBean = null;
		    ExpressionBean expressionBean = null;
		    ExpressionLetBean expressionLetBean = null;
		    SuperviseDefinitionBean superviseDefinitionBean = null;
		    Date startTime = null;
		    Date endTime = null;
		    String state = null;
		    Double promiseLimit = 0.0;
		    Double processLimit = 0.0;
		    Date expireDate = null;
		    String batchId = null;
		    String tbcxTimes = null;
		    String businessCode = null;
		    String businessName = null;
		    String orgName = null;
		    String applyName = null;
		    String tacheName = null;
		    String instanceId = null;
		    String markId = null;
			for (int i = 0; i < list.size(); i++) {

				String areacode = list.get(i).getAreaCode();
				String expid = list.get(i).getExpId();
				String ruleid = list.get(i).getRuleId();
				String ruleversion = list.get(i).getRuleVersion();
				String businessid = list.get(i).getBusinessId();
				String rltvalue = list.get(i).getRltValue();
				String rulename = list.get(i).getRuleName();
				String rulememo = list.get(i).getRuleMemo();
				String isvalid = list.get(i).getIsValid();
				String isautorun = list.get(i).getIsAutoRun();
				String runpriority = list.get(i).getRunPriority();
				String hasruned = list.get(i).getHasRuned();
				String ruletype = list.get(i).getRuleType();
				String ispublic = list.get(i).getIsPublic();
				String expseq = list.get(i).getExpSeq();
				String expletid = list.get(i).getExpletId();
				String leftparen = list.get(i).getLeftParen();
				String elemid = list.get(i).getElemId();
				String compaid = list.get(i).getCompaId();
				String cfgvalue = list.get(i).getCfgValue();
				String rightparen = list.get(i).getRightParen();
				String compa = list.get(i).getCompa();
				String logic = list.get(i).getLogic();
				String logicid = list.get(i).getLogicId();
				String supervisedefid = list.get(i).getSuperviseDefId();
				String superviselevelcode = list.get(i).getSuperviseLevelCode();
				String supervisetypecode = list.get(i).getSuperviseTypeCode();
				String sendcardtype = list.get(i).getSendcardType();
				String msgtemplateid = list.get(i).getMsgTemplateId();
				String msgTemplate = list.get(i).getMsgTemplate();
				String issendmsg = list.get(i).getIssendmsg();
				String businessType = list.get(i).getBusinessType();
				String orgId = list.get(i).getOrgId();
				String superviseInfoId = list.get(i).getSuperviseInfoId();
				startTime = list.get(i).getStartTime();
				endTime = list.get(i).getEndTime();
				state = list.get(i).getState();
				promiseLimit = list.get(i).getPromiseLimit();
				processLimit = list.get(i).getProcessLimit();
				expireDate = list.get(i).getExpireDate();
				tbcxTimes=list.get(i).getTbcxTimes();
				batchId=list.get(i).getBatchId();
				businessCode = list.get(i).getBusinessCode();
				businessName = list.get(i).getBusinessName();
				orgName = list.get(i).getOrgName();
				applyName = list.get(i).getApplyName();
				tacheName = list.get(i).getTacheName();
				instanceId = list.get(i).getInstanceId();
				markId = list.get(i).getMarkId();
				
				//业务id所属业务类型
				bussinessTypeMap.put(businessid, businessType);
				//业务id所属 单位
				orgIdMap.put(businessid, orgId);
				
				
				if(!businessLogMap.containsKey(businessid+"#"+superviselevelcode)){
					logMap = new HashMap<String,Object>();
					logMap.put("startTime", startTime);
					logMap.put("endTime", endTime);
					logMap.put("state", state);
					logMap.put("promiseLimit", promiseLimit);
					logMap.put("processLimit", processLimit);
					logMap.put("expireDate", expireDate);
					logMap.put("tbcxTimes", tbcxTimes);
					logMap.put("batchId", batchId);
					logMap.put("businessCode", businessCode);
					logMap.put("businessName", businessName);
					logMap.put("msgTemplate", msgTemplate);
					logMap.put("superviseInfoId", superviseInfoId);
					logMap.put("orgName", orgName);
					logMap.put("applyName", applyName);
					logMap.put("tacheName", tacheName);
					logMap.put("instanceId", instanceId);
					logMap.put("markId", markId);
					businessLogMap.put(businessid+"#"+superviselevelcode, logMap);
				}
				
				// 规则信息Bean
				ruleBean = new RuleBean();
				ruleBean.setAreaCode(areacode);
				ruleBean.setHasRuned(hasruned);
				ruleBean.setIsAutoRun(isautorun);
				ruleBean.setIsPublic(ispublic);
				ruleBean.setIsValid(isvalid);
				ruleBean.setRuleId(ruleid);
				ruleBean.setRuleMemo(rulememo);
				ruleBean.setRuleName(rulename);
				ruleBean.setRunPriority(runpriority);
				ruleBean.setRuleType(ruletype);
				ruleBean.setRuleVersion(ruleversion);
				ruleBean.setIssendmsg(issendmsg);
				
				if (!ruleIdAndRuleBeanMap.containsKey(ruleid)) {
					ruleIdAndRuleBeanMap.put(ruleid, ruleBean);
				}
				// Map<规则信息Bean,务ID>封装
				if (ruleidAndbusinessidlistMap.containsKey(ruleid)) {// 存在添加
					if (!ruleidAndbusinessidlistMap.get(ruleid).containsKey(
							businessid)) {// 该ruleid中没有存入了该业务id，即存拉；反之不用（同一业务同一规则只能一次）
						ruleidAndbusinessidlistMap.get(ruleid).put(businessid,
								businessid);
						List<String> businessidlist = ruleBeanAndBusinessIdList
								.get(ruleid);
						businessidlist.add(businessid);
						ruleBeanAndBusinessIdList.put(ruleid, businessidlist);
					}
				} else {// 第一次加入
					Map<String, String> businessidmap = new HashMap<String, String>();
					businessidmap.put(businessid, businessid);
					ruleidAndbusinessidlistMap.put(ruleid, businessidmap);
					List<String> businessidlist = new ArrayList<String>();
					businessidlist.add(businessid);
					ruleBeanAndBusinessIdList.put(ruleid, businessidlist);
				}
				// 规则表达式
				expressionBean = new ExpressionBean();
				expressionBean.setArea_code(areacode);
				expressionBean.setExp_id(expid);
				expressionBean.setExp_seq(expseq);
				expressionBean.setRule_id(ruleid);
				expressionBean.setRule_version(ruleversion);
				if (!expressionBeanMap.containsKey(expid)) {
					expressionBeanMap.put(expid, expressionBean);
				}

				// 规则表达式片段
				expressionLetBean = new ExpressionLetBean();
				expressionLetBean.setArea_code(areacode);
				expressionLetBean.setCfg_value(cfgvalue);
				expressionLetBean.setCompa(compa);
				expressionLetBean.setCompa_id(compaid);
				expressionLetBean.setElem_id(elemid);
				expressionLetBean.setExp_id(expid);
				expressionLetBean.setExplet_id(expletid);
				expressionLetBean.setLeft_paren(Integer.parseInt(leftparen));
				expressionLetBean.setLogic(logic);
				expressionLetBean.setLogic_id(logicid);
				expressionLetBean.setResultValue(rltvalue);
				expressionLetBean.setRight_paren(Integer.parseInt(rightparen));
				expressionLetBean.setBusinessid(businessid);
				// String expidAndBussinessid =
				// expid+"_"+businessid+"_"+expletid;//为了将片段细化到绑定每一个业务的规则要素计算结果
				// if(!expressionLetBeanMap.containsKey(expidAndBussinessid)){
				expressionLetBeanMap.put(i + "", expressionLetBean);// 片段是最小的记录行，所以全部加入
				// }

				// 规则定义信息封装（Map<String[:exp_id],SuperviseDefinitionBean>）
				superviseDefinitionBean = new SuperviseDefinitionBean();
				superviseDefinitionBean.setArea_code(areacode);
				superviseDefinitionBean.setExp_id(expid);
				superviseDefinitionBean.setMsg_template_id(msgtemplateid);
				//superviseDefinitionBean.setMsgTemplate(msgTemplate);
				superviseDefinitionBean.setSendcard_type(sendcardtype);
				superviseDefinitionBean.setSupervise_def_id(supervisedefid);
				superviseDefinitionBean
						.setSupervise_level_code(superviselevelcode);
				superviseDefinitionBean
						.setSupervise_type_code(supervisetypecode);
				//superviseDefinitionBean.setSuperviseInfoId(superviseInfoId);
				if (!expIdAndSuperviseDefinitionBean.containsKey(expid)) {
					expIdAndSuperviseDefinitionBean.put(expid,
							superviseDefinitionBean);
				}

			}

			// 表达式信息封装（Map<String[:rule_id],List<ExpressionBean>>）
			if (expressionBeanMap != null && !expressionBeanMap.isEmpty()) {
				Set<String> expids = (Set<String>) expressionBeanMap.keySet();
				Iterator<String> expidsit = expids.iterator();
				while (expidsit.hasNext()) {
					ExpressionBean expBean = expressionBeanMap.get(expidsit
							.next());
					if (!ruleIdAndExpressionBeanList.containsKey(expBean
							.getRule_id())) {// 第一次加入
						List<ExpressionBean> expBeanList = new ArrayList<ExpressionBean>();
						expBeanList.add(expBean);
						ruleIdAndExpressionBeanList.put(expBean.getRule_id(),
								expBeanList);
					} else {// 以后加入则先在以前的list上加入本Bean，然后删除该ruleid对应的map，再重新加入该ruleid对应的map。
						List<ExpressionBean> expBeanList = ruleIdAndExpressionBeanList
								.get(expBean.getRule_id());
						expBeanList.add(expBean);
						ruleIdAndExpressionBeanList
								.remove(expBean.getRule_id());
						ruleIdAndExpressionBeanList.put(expBean.getRule_id(),
								expBeanList);
					}
				}

			}
			// 表达式片段信息封装（Map<String[:exp_id_businessid],List<ExpressionLetBean>>）
			if (expressionLetBeanMap != null && !expressionLetBeanMap.isEmpty()) {
				Set<String> expletids = (Set<String>) expressionLetBeanMap
						.keySet();
				Iterator<String> expletidsit = expletids.iterator();
				while (expletidsit.hasNext()) {
					ExpressionLetBean expletBean = expressionLetBeanMap
							.get(expletidsit.next());
					String keyid = expletBean.getExp_id() + "_"
							+ expletBean.getBusinessid();
					if (!expIdAndExpressionLetBeanList.containsKey(keyid)) {// 第一次加入
						List<ExpressionLetBean> expletBeanList = new ArrayList<ExpressionLetBean>();
						expletBeanList.add(expletBean);
						expIdAndExpressionLetBeanList
								.put(keyid, expletBeanList);
					} else {
						List<ExpressionLetBean> expletBeanList = expIdAndExpressionLetBeanList
								.get(keyid);
						expletBeanList.add(expletBean);
						expIdAndExpressionLetBeanList
								.put(keyid, expletBeanList);// key值存在会自动覆盖(在此就不做手动remove)
					}
				}
			}
			// 封装成ruleWithBusinessBean
			ruleWithBusinessBean.setRuleIdAndRuleBeanMap(ruleIdAndRuleBeanMap);
			ruleWithBusinessBean
					.setRuleBeanAndBusinessIdList(ruleBeanAndBusinessIdList);
			ruleWithBusinessBean
					.setRuleIdAndExpressionBeanList(ruleIdAndExpressionBeanList);
			ruleWithBusinessBean
					.setExpIdAndExpressionLetBeanList(expIdAndExpressionLetBeanList);
			ruleWithBusinessBean
					.setExpIdAndSuperviseDefinitionBean(expIdAndSuperviseDefinitionBean);
			ruleWithBusinessBean.setBussinessTypeMap(bussinessTypeMap);
			ruleWithBusinessBean.setOrgIdMap(orgIdMap);
			ruleWithBusinessBean.setBusinessLogMap(businessLogMap);
			
			ruleIdAndRuleBeanMap = null;
			ruleBeanAndBusinessIdList = null;
			ruleIdAndExpressionBeanList = null;
			expIdAndExpressionLetBeanList = null;
			expIdAndSuperviseDefinitionBean = null;
			bussinessTypeMap = null;
			orgIdMap = null;
		}

		return ruleWithBusinessBean;
	}

}
