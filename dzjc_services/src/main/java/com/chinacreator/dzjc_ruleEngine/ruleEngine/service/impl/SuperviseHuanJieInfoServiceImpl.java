package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.HuanJieSuperviseInfo;
import com.chinacreator.dzjc_ruleEngine.YiZhengData;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseDefinitionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.HuanJieSuperviseInfoServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.support.RuleConstants;
import com.chinacreator.util.HolidayDateUtil;
import com.chinacreator.util.HttpInterfaceUtil;
import com.chinacreator.util.StringUtil;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author liaolong
 *@version 1.0
 *@date 2019-1-15
 */
public class SuperviseHuanJieInfoServiceImpl implements HuanJieSuperviseInfoServiceIfc {
	
	@SuppressWarnings("unchecked")
	public String doSupervise(List<Map<String,Object>> list,ServletContext application) throws Exception {
		String superviseId = "";
		List<HuanJieSuperviseInfo> superviserInfoList = new ArrayList<HuanJieSuperviseInfo>(); 
		RuleBean ruleBean = null;
		SuperviseDefinitionBean superviseDefinitionBean = null;
		//定义益政接口返回的数据列表，用于插入到数据库
		List<YiZhengData> dataList = new ArrayList<YiZhengData>();
		
		//定义益政接口返回的异常数据列表，两边的环节状态不一致，或者益政没有配置经办人
		List<String> opinionIdList = new ArrayList<String>();
		
		Map<String,Object> logMap = null;
		try {
			for (Map<String,Object> paraMap : list) {
				String businessId = (String)paraMap.get("businessId");
				String orgId = "";
				if(paraMap.get("orgId") != null){
					orgId = (String)paraMap.get("orgId");
				}
				logMap = null;
				if(paraMap.get("businessLogMap") != null){
					logMap = (Map<String,Object>)paraMap.get("businessLogMap");
				}
				String outDays = (String)paraMap.get("outDays");
				logMap.put("outDays", outDays);
				ruleBean = (RuleBean)paraMap.get("ruleBean");
				superviseDefinitionBean = (SuperviseDefinitionBean)paraMap.get("superviseDefBean");
				
				addSuperviseInfo(businessId, orgId, logMap, ruleBean, 
						superviseDefinitionBean,superviserInfoList,
						dataList,opinionIdList,application); //封装发牌信息
			}
			//批量插入发牌数据
			if(superviserInfoList.size() > 0){
				DaoFactory.create(HuanJieSuperviseInfo.class).insertBatch(superviserInfoList);
			}
			//批量插入益政接口数据
			if(dataList.size() > 0){
				DaoFactory.create(YiZhengData.class).insertBatch(dataList);
			}
			//批量删除发牌临时表中的数据
			if(opinionIdList.size() > 0){
				DaoFactory.create(YiZhengData.class).getSession()
					.delete("com.chinacreator.dzjc_ruleEngine.SuperviseHuanJieTempMapper.deleteBatch",opinionIdList);
			}
		} catch(Exception e) {
			System.err.println("环节监察写入发牌数据异常："+e.getMessage());
			e.printStackTrace();
		}
		return superviseId;
	}
	
	/**
	 * 封装监察信息
	 * @param businessId
	 * @param ruleBean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void addSuperviseInfo(String businessId, String orgId, 
			Map<String,Object> logMap, RuleBean ruleBean, 
			SuperviseDefinitionBean superviseDefinitionBean,
			List<HuanJieSuperviseInfo> superviserInfoList,
			List<YiZhengData> dataList,
			List<String> opinionIdList,
			ServletContext application) throws Exception {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String superviseId = UUID.randomUUID().toString();
		HuanJieSuperviseInfo superviserInfoBean = new HuanJieSuperviseInfo();
		superviserInfoBean.setSuperviseInfoId(superviseId);
		superviserInfoBean.setOpinionId(businessId);
		
		superviserInfoBean.setSuperviseTypeNo(superviseDefinitionBean.getSupervise_type_code());
		superviserInfoBean.setSuperviseResultNo(superviseDefinitionBean.getSupervise_level_code());
		superviserInfoBean.setSuperviseTime(ft.format(new java.util.Date()));
		superviserInfoBean.setSuperviseDescript(ruleBean.getRuleName());
		superviserInfoBean.setSuperviseGist(ruleBean.getRuleName());
		superviserInfoBean.setRuleId(ruleBean.getRuleId());
		superviserInfoBean.setFeedbackEndtime(HolidayDateUtil.getDate(30));
		superviserInfoBean.setRuleVersion(ruleBean.getRuleVersion());
		//封装短信内容
		superviserInfoBean.setWarningContent(sendMessage(superviseDefinitionBean,logMap));
		String markId = null;
		if(logMap != null){
			superviserInfoBean.setBusinessId(logMap.get("instanceId") != null ? (String)logMap.get("instanceId") : null);
			superviserInfoBean.setStartTime(logMap.get("startTime") != null ? (java.sql.Date)logMap.get("startTime") : null);
			superviserInfoBean.setEndTime(logMap.get("endTime") != null ? (java.sql.Date)logMap.get("endTime") : null);
			superviserInfoBean.setStatus(logMap.get("state") != null ? (String)logMap.get("state") : null);
			superviserInfoBean.setPromiseLimit(logMap.get("promiseLimit") != null ? (Double)logMap.get("promiseLimit") : null);
			superviserInfoBean.setOutDays(logMap.get("outDays") != null ? (String)logMap.get("outDays") : null);
			superviserInfoBean.setTbcxTimes(logMap.get("tbcxTimes") != null ? (String)logMap.get("tbcxTimes") : null);
			superviserInfoBean.setBatchId(logMap.get("batchId") != null ? (String)logMap.get("batchId") : null);
			markId = logMap.get("markId") != null ? (String)logMap.get("markId") : null;
		}
		if(RuleConstants.SENDCARD_TYPE_AUTO.equals(superviseDefinitionBean.getSendcard_type())){
			superviserInfoBean.setState("Y");
		}else{
			superviserInfoBean.setState("D");
		}
		//确定牌发给哪个单位
		if(StringUtils.isNotBlank(orgId)){
			superviserInfoBean.setOrgId(orgId);
			superviserInfoList.add(superviserInfoBean);
		}
		else {
			//调用接口，获取相关责任单位，然后给每个单位发一张牌
			if(StringUtils.isNotBlank(markId)){
				Map<String,Object> resultMap = HttpInterfaceUtil.getYiZhengData(markId, businessId,application);
				//接口返回的经办人列表数据
				List<YiZhengData> dList = (List<YiZhengData>)resultMap.get("dataList");
				if(dList != null && dList.size() > 0){
					HuanJieSuperviseInfo info = null;
					for(YiZhengData d : dList){
						//如果接口返回成功，则加入到发牌列表
						if("1".equals(d.getOk())){
							info = (HuanJieSuperviseInfo)BeanUtils.cloneBean(superviserInfoBean);
							info.setSuperviseInfoId(StringUtil.getUUID2());
							info.setOrgId(d.getOrgId());
							superviserInfoList.add(info);
						}
					}
					dataList.addAll(dList);
				}
				//接口返回的异常的数据，需要从发牌临时表里删除的数据
				List<String> oList = (List<String>)resultMap.get("opinionIdList");
				if(oList != null && oList.size() > 0){
					opinionIdList.addAll(oList);
				}
			}
			else {
				System.err.println("环节id=="+businessId+"的流程唯一标识markId为空");
			}
		}
	}
	
	private String sendMessage(SuperviseDefinitionBean superviseDefinitionBean,
			Map<String,Object> logMap) throws Exception {
		
		String msgContent = "";
		if(StringUtils.isNotBlank(superviseDefinitionBean.getMsg_template_id())){
			msgContent = String.valueOf(logMap.get("msgTemplate"));
			try {
				//获取邮件发送模板信息
				if(StringUtil.isBlank(msgContent)){
					return msgContent;
				}
				DecimalFormat df = new DecimalFormat("#");
				msgContent = msgContent.replace("$instance_code$",
						String.valueOf(logMap.get("businessCode")));
				msgContent = msgContent.replace("$instance_name$", 
						String.valueOf(logMap.get("businessName")));
				msgContent = msgContent.replace("$apply_name$", 
						String.valueOf(logMap.get("applyName")));
				msgContent = msgContent.replace("$org_name$", 
						String.valueOf(logMap.get("orgName")));
				msgContent = msgContent.replace("$tache_name$", 
						String.valueOf(logMap.get("tacheName")));
				String commitmentLimit = String.valueOf(logMap.get("promiseLimit"));
				if(StringUtils.isBlank(commitmentLimit) 
						|| "null".equalsIgnoreCase(commitmentLimit)){
					commitmentLimit = "";
				}
				if (StringUtils.isNotBlank(commitmentLimit)) {
					commitmentLimit = df.format(new Double(commitmentLimit));
				}
				msgContent = msgContent.replace("$commitment_limit$", commitmentLimit);
			} catch (Exception e) {
				e.printStackTrace();
				//LOG.error(e.getMessage());
				throw new Exception(e.getMessage());
			}
			return msgContent;
		}
		return msgContent;
	}
	
}
