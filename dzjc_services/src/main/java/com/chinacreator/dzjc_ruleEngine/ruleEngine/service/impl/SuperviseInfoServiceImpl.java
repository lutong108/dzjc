package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.MessageUser;
import com.chinacreator.dzjc_ruleEngine.MsgRecord;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.MessageInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.SuperviseInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.MessageInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.SuperviseInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseDefinitionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SuperviseInfoServiceIfc;
import com.chinacreator.dzjc_ruleEngine.web.rest.MessageUserController;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.StringUtil;
import com.chinacreator.util.SystemPropertiesInstance;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public class SuperviseInfoServiceImpl implements SuperviseInfoServiceIfc {
	
	//private static final Logger LOG = Logger.getLogger(SuperviseInfoServiceImpl.class.getName());
	
	private static MessageUserController msgUsercontroller= new MessageUserController();
	
	@SuppressWarnings("unchecked")
	public String doSupervise(List<Map<String,Object>> list) throws Exception {
		String superviseId = "";
		List<SuperviserInfo> superviserInfoList = new ArrayList<>(); 
		List<String> approveList=null;
		RuleBean ruleBean = null;
		SuperviseDefinitionBean superviseDefinitionBean = null;
		SuperviseInfoDao superviseDao = new SuperviseInfoDaoImpl();
		SuperviserInfo insertSuperviseInfo = null;
		List<MsgRecord> msgRecordList=new ArrayList<MsgRecord>();
		Map<String,Object> logMap = null;
		String superviseResultCode = null;
		Object endTime = null; 
		Object approveId=null;
		String streetOrgId=null;
		approveList=new ArrayList<String>();
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
				//监察级别：1：预警；2：黄牌；3：红牌；
				superviseResultCode = superviseDefinitionBean.getSupervise_level_code();
				endTime = logMap.get("endTime");
				//事项id
				approveId = logMap.get("approveId");
				//雨花区短信
				streetOrgId=StringUtil.DeNull(logMap.get("streetOrgId"));
				//如果是预警牌，并且已经办结，则不发预警牌
				if(endTime != null && "1".equals(superviseResultCode)){
					continue;
				}
				
				insertSuperviseInfo = superviseDao.insertSuperviseInfo(businessId, orgId, logMap, ruleBean, superviseDefinitionBean); //封装预警信息
				superviseId = insertSuperviseInfo.getSuperviseInfoId();
				
//				String msgBusinessId = businessId;
//				String suspendId = "";
//				//处理特别程序的业务id
//				if(businessId.indexOf("$$") != -1)
//				{
//					msgBusinessId = businessId.substring(0,businessId.indexOf("$$"));
//					suspendId = businessId.substring(businessId.indexOf("$$")+2, businessId.length());
//				}
//				//处理网上办事的业务id
//				else if(businessId.indexOf("##") != -1)
//				{
//					msgBusinessId = businessId.substring(0,businessId.indexOf("##"));
//				}
//				//处理电子证照的业务id
//				else if(businessId.indexOf("@@") != -1)
//				{
//					msgBusinessId = businessId.substring(0,businessId.indexOf("@@"));
//				}
				String msgContent = "";
				msgContent = sendMessage(superviseDefinitionBean,logMap); // 发送消息
				if(msgContent!=null && msgContent!=""){
					insertSuperviseInfo.setWarningContent(msgContent);
					//封装短信实体
					if("Y".equals(ruleBean.getIssendmsg())){
						insertSuperviseInfo.setApproveId(approveId.toString());
						insertSuperviseInfo.setStreetOrgId(streetOrgId);
						if(!approveList.contains(approveId.toString())){
							approveList.add(approveId.toString());
						}
					}
				}
				//MsgRecord msgRecord=buildMsgRecord(msgContent);
				superviserInfoList.add(insertSuperviseInfo);
				//System.out.println("插入-------businessId="+insertSuperviseInfo.getBusinessId()+"级别："+insertSuperviseInfo.getSuperviseResultNo());
			}
			
			if(superviserInfoList.size() > 0){
				DaoFactory.create(SuperviserInfo.class).insertBatch(superviserInfoList);
				//封装短信实体
				if(approveList.size() > 0){
					List<MessageUser> allByApproveList = msgUsercontroller.getAllByApproveList(approveList);
					List<String> streetOrgList=msgUsercontroller.getStreetOrgList();
					String[] bjArray= new String[]{"4","5","6","7","8"};
					List<String> bjList = Arrays.asList(bjArray);
					//雨花区 
					if("4301110000".equals(SystemPropertiesInstance.getInstance().getSystem_areaCode())){
						for (SuperviserInfo superviserInfo : superviserInfoList) {
							//4,5,6,7,8 办结不发短信
							if(bjList.contains(superviserInfo.getStatus())){
								continue;
							}
							String superApproveId = superviserInfo.getApproveId();
							String superResultNo = superviserInfo.getSuperviseResultNo();
							String streetOrgIdTemp=superviserInfo.getStreetOrgId();
							if(StringUtil.isBlank(streetOrgIdTemp)){
								//区县
								for (MessageUser messageUser : allByApproveList) {
									if( superApproveId.equals(messageUser.getApproveId())&&
										    superResultNo.equals(messageUser.getWarnLevel())&&
										    !streetOrgList.contains(messageUser.getOrgId())){
										//所有配置人员 去除
											msgRecordList.add(buildMsgRecord(superviserInfo,messageUser));
									} 
								}
							}else{
								//街道
								for (MessageUser messageUser : allByApproveList) {
									if( superApproveId.equals(messageUser.getApproveId())&&
										    superResultNo.equals(messageUser.getWarnLevel())
										    && streetOrgIdTemp.equals(messageUser.getOrgId())){
										msgRecordList.add(buildMsgRecord(superviserInfo,messageUser));
									}  
								}
							}
						}
					}else{
						for (SuperviserInfo superviserInfo : superviserInfoList) {
							String superApproveId = superviserInfo.getApproveId();
							String superResultNo = superviserInfo.getSuperviseResultNo();
							for (MessageUser messageUser : allByApproveList) {
								if( superApproveId.equals(messageUser.getApproveId())&&
									    superResultNo.equals(messageUser.getWarnLevel())){
										msgRecordList.add(buildMsgRecord(superviserInfo,messageUser));
								} 
							}
						}
					}
				}
			}
			if(msgRecordList!=null && msgRecordList.size()>0){
				DaoFactory.create(MsgRecord.class).insertBatch(msgRecordList);
			}
		} catch(Exception e) {
			System.err.println("监察规则在写入发牌表时异常："+e.getMessage());
			e.printStackTrace();
		}
		return superviseId;
			/*String businessId = (String)paraMap.get("businessId");
			RuleBean ruleBean = (RuleBean)paraMap.get("ruleBean");
			SuperviseDefinitionBean superviseDefinitionBean = (SuperviseDefinitionBean)paraMap.get("superviseDefBean");
			String superviseId = "";*/
		//TransactionManager tm = new TransactionManager();
		/*try {
			//tm.begin();//事务开始
			SuperviseInfoDao superviseDao = new SuperviseInfoDaoImpl();
			
			
			superviseId = superviseDao.insertSuperviseInfo(businessId,ruleBean,superviseDefinitionBean); //保存预警信息
			//superviseDao.insertKuaiZhao(businessId,superviseId,superviseDefinitionBean);//保存业务快照信息
			
			
			
			
			String msgBusinessId = businessId;//去掉“-”的业务实例ID
			if(businessId.indexOf("$$") != -1)
			{
				msgBusinessId = businessId.substring(0,businessId.indexOf("$$"));
			}
			String msgContent = "";
			//String busissid = businessId.substring(3);
			
			if (!StringUtil.isBlank(busissid) && new MsgBoxImpl().checktheXiangXi(busissid, "", "", "")) {
				// 调用湘西个性化短信接口
				
				MessageTemplateParserDao templateParser = new MessageTemplateParserDaoImpl();
				String templateId = superviseDefinitionBean.getMsg_template_id();
				msgContent = templateParser.parseMsgTemplate(templateId, msgBusinessId);
				MessageInfoDao messageInfoDao = new MessageInfoDaoImpl();
				List<MessageUserBean> userList = messageInfoDao.findCfgUserList("", businessId);
				for (int i = 0; i < userList.size(); i++) {
					String mobile = userList.get(i).getUser_mobile();
					if (!StringUtil.isBlank(mobile)) {
						MessageUtil messageUtil = new MessageUtil();
						String result = messageUtil.sendMessage(mobile, msgContent);	
						// 解析result
						result=result.replace("\r", "").replace("\n", "");
						Document document= DocumentHelper.parseText(result);
						String jsonStr = document.getRootElement().getText();
						JSONObject json = JSONObject.fromObject(jsonStr);
						String retcode = json.getString("retcode");
						String retmessage = json.getString("retmessage");
						String tempresult = "";
			            if("0".equals(retcode)){
			            	tempresult = "Y";
			            }else{
			            	tempresult = "N";
			            }
			            boolean flagSend = new MsgBoxImpl().imSendBaks(0, "", mobile, msgContent, "", "Member", tempresult, retmessage);
			            if(!flagSend)throw new Exception("保存短信发送记录失败！");	
					}
				}
			} else {
				msgContent = sendMessage(msgBusinessId, superviseId, ruleBean, superviseDefinitionBean); // 发送消息
				sendEmail(msgBusinessId, superviseId, ruleBean, superviseDefinitionBean);// 发送邮件
			}
			
			URLDecoder.decode(msgContent,"UTF-8");
			msgContent = sendMessage(msgBusinessId, superviseId, ruleBean, superviseDefinitionBean); // 发送消息
			if(msgContent!=null && msgContent!=""){
				superviseDao.saveMsgContent(superviseId, msgContent); // 将消息内容更新到监察信息表
			}
			
			
			//查询最严重、最近一次预警
			//ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
			//String superviseIdMax = elementInfoDao.getMaxSuperviseInfo(msgBusinessId);
			//修改行政审批事项的最近一次，最严重的一次发牌
			//elementInfoDao.updateInstanceMaxSupervise(msgBusinessId,superviseIdMax);
			//tm.commit();//事务提交
			
		} catch(Exception e) {
			e.printStackTrace();
			//LOG.error(e.getMessage());
			//tm.rollback();//事务回滚
			throw new Exception(e.getMessage());
		}
		return superviseId;
		*/
		
		
	}
	
	public MsgRecord buildMsgRecordYhq(SuperviserInfo insertSuperviseInfo,
			MessageUser messageUser, String streetOrgId) {
		List<MsgRecord> msgRecordList= new ArrayList<MsgRecord>();
		String userMobile="";
		String sendName="";
		MsgRecord msgRecord= new MsgRecord();
		if(insertSuperviseInfo!=null ){
			if(messageUser!=null){
				userMobile=StringUtil.transferDoubleToString(messageUser.getUserMobile());
				sendName=messageUser.getUserName();
			}
			String superviseResult=insertSuperviseInfo.getSuperviseResultNo();
			msgRecord.setMsgId(StringUtil.getUUID());
			msgRecord.setBusinessId(insertSuperviseInfo.getBusinessId());
			msgRecord.setSuperviseId(insertSuperviseInfo.getSuperviseInfoId());
			msgRecord.setOrgId(insertSuperviseInfo.getOrgId());
			msgRecord.setSuperviseTypeNo(insertSuperviseInfo.getSuperviseTypeNo());
			msgRecord.setSuperviseResultNo(superviseResult);
			msgRecord.setSuperviseTime(DateUtil.strToDate(insertSuperviseInfo.getSuperviseTime()));
			msgRecord.setSendMobile(userMobile);
			msgRecord.setSendName(sendName);
			msgRecord.setSendContent(insertSuperviseInfo.getWarningContent());
			msgRecord.setSendFlag("N");
			msgRecordList.add(msgRecord);
		}
		return msgRecord;
	}

	private MsgRecord buildMsgRecord(SuperviserInfo insertSuperviseInfo,
			MessageUser messageUser) {
		List<MsgRecord> msgRecordList= new ArrayList<MsgRecord>();
		String userMobile="";
		String sendName="";
		MsgRecord msgRecord= new MsgRecord();
		try {
			if(insertSuperviseInfo!=null ){
				if(messageUser!=null){
					userMobile=StringUtil.transferDoubleToString(messageUser.getUserMobile());
					sendName=messageUser.getUserName();
				}
				String superviseResult=insertSuperviseInfo.getSuperviseResultNo();
				msgRecord.setMsgId(StringUtil.getUUID());
				msgRecord.setBusinessId(insertSuperviseInfo.getBusinessId());
				msgRecord.setSuperviseId(insertSuperviseInfo.getSuperviseInfoId());
				msgRecord.setOrgId(insertSuperviseInfo.getOrgId());
				msgRecord.setSuperviseTypeNo(insertSuperviseInfo.getSuperviseTypeNo());
				msgRecord.setSuperviseResultNo(superviseResult);
				msgRecord.setSuperviseTime(DateUtil.strToDate(insertSuperviseInfo.getSuperviseTime()));
				msgRecord.setSendMobile(userMobile);
				msgRecord.setSendName(sendName);
				msgRecord.setSendContent(insertSuperviseInfo.getWarningContent());
				msgRecord.setSendFlag("N");
				msgRecordList.add(msgRecord);
			}
		} catch (Exception e) {
			System.err.println("监察规则在构造短信内容时异常："+e.getMessage());
			e.printStackTrace();
		}
		return msgRecord;
	}




	/**
	 * 消息发送
	 * @param businessId
	 * @param superviseId
	 * @param ruleBean
	 * @return
	 * @throws Exception
	 */
	
	public String sendMessage(String businessId, String suspendId, String superviseId, RuleBean ruleBean, SuperviseDefinitionBean superviseDefinitionBean) throws Exception {
		MessageInfoDao messageInfoDao = new MessageInfoDaoImpl();
		String msgContent = "";
		if(StringUtils.isNotBlank(superviseDefinitionBean.getMsg_template_id())){
			Map<String,Object> msgParamMap = new HashMap<String,Object>(); //消息参数
			msgParamMap.put("businessId", businessId);
			msgParamMap.put("suspendId", suspendId);
			msgParamMap.put("superviseId", superviseId);
			msgParamMap.put("ruleBean", ruleBean);	
			msgParamMap.put("superviseDefBean", superviseDefinitionBean);	
			msgContent = messageInfoDao.sendMessage(msgParamMap); //发送消息
		}
		return msgContent;
	}
	
	private String sendMessage(SuperviseDefinitionBean superviseDefinitionBean,
			Map<String,Object> logMap) throws Exception {
		
		String msgContent = "";
		if(StringUtils.isNotBlank(superviseDefinitionBean.getMsg_template_id())){
			String superviseTypeCode = superviseDefinitionBean.getSupervise_type_code();
			msgContent = String.valueOf(logMap.get("msgTemplate"));
			//msgContent = superviseDefinitionBean.getMsgTemplate();
//			String sendStatus = "0";
//			boolean isAutoSendMsg = false;
//			if (RuleConstants.SENDCARD_TYPE_AUTO.equals(superviseDefinitionBean.getSendcard_type())) {
//				sendStatus = "1";
//				isAutoSendMsg = true;
//			}
			try {
				//获取邮件发送模板信息
				if(StringUtil.isBlank(msgContent)){
					return msgContent;
				}
//				else if(!RuleConstants.BUSINESS_TYPE_XZSP.equals(msgTemplateBean.getTemplateType().trim())){
//					return msgContent;
//				}
				DecimalFormat df = new DecimalFormat("#.#");
				if ("1011".equals(superviseTypeCode)) {
					//办件类型的模板
					msgContent = msgContent.replace("$instance_code$",
							String.valueOf(logMap.get("businessCode")));
					msgContent = msgContent.replace("$instance_name$", 
							String.valueOf(logMap.get("businessName")));
					msgContent = msgContent.replace("$apply_name$", 
							String.valueOf(logMap.get("applyName")));
					msgContent = msgContent.replace("$org_name$", 
							String.valueOf(logMap.get("orgName")));
					String approveLimit = String.valueOf(logMap.get("processLimit"));
					if(StringUtils.isBlank(approveLimit) 
							|| "null".equalsIgnoreCase(approveLimit)){
						approveLimit = "";
					}
					String commitmentLimit = String.valueOf(logMap.get("promiseLimit"));
					if(StringUtils.isBlank(commitmentLimit) 
							|| "null".equalsIgnoreCase(commitmentLimit)){
						commitmentLimit = "";
					}
					if (StringUtils.isNotBlank(approveLimit)) {
						approveLimit = df.format(new Double(approveLimit));
					}
					if (StringUtils.isNotBlank(commitmentLimit)) {
						commitmentLimit = df.format(new Double(commitmentLimit));
					}
					msgContent = msgContent.replace("$approve_limit$", approveLimit);
					msgContent = msgContent.replace("$commitment_limit$", commitmentLimit);
				
				} else if ("1013".equals(superviseTypeCode)) {
					//咨询类型模板
					msgContent = msgContent.replace("$consult_title$", 
							String.valueOf(logMap.get("businessName")));
					Double consultLimit = new Double(String.valueOf(logMap.get("promiseLimit") == null ? 0 : logMap.get("promiseLimit")));
					if (consultLimit != null) {
						msgContent = msgContent.replace("$consult_limit$", df.format(consultLimit));
					} 

				} else if ("1012".equals(superviseTypeCode)) {
					//投诉类型模板
					msgContent = msgContent.replace("$complain_id$", 
							String.valueOf(logMap.get("businessCode")));
					msgContent = msgContent.replace("$complain_title$", 
							String.valueOf(logMap.get("businessName")));
					Date expireDate = (Date)logMap.get("expireDate");
					if (expireDate != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						msgContent = msgContent.replace("$complain_limit$", sdf.format(expireDate));
					} else {
						msgContent = msgContent.replace("$complain_limit$", "");
					}

				} else if ("1016".equals(superviseTypeCode)) {
					//特别程序类型的模板
					msgContent = msgContent.replace("$instance_code$",
							String.valueOf(logMap.get("businessCode")));
					msgContent = msgContent.replace("$instance_name$", 
							String.valueOf(logMap.get("businessName")));
					msgContent = msgContent.replace("$apply_name$", 
							String.valueOf(logMap.get("applyName")));
					msgContent = msgContent.replace("$org_name$", 
							String.valueOf(logMap.get("orgName")));
					String commitmentLimit = String.valueOf(logMap.get("promiseLimit"));
					if(StringUtils.isBlank(commitmentLimit) 
							|| "null".equalsIgnoreCase(commitmentLimit)){
						commitmentLimit = "";
					}
					if (StringUtils.isNotBlank(commitmentLimit)) {
						commitmentLimit = df.format(new Double(commitmentLimit));
					}
					msgContent = msgContent.replace("$time_limit$", commitmentLimit);
					
				} else if ("1017".equals(superviseTypeCode)) {
					//网上办事的模板
					msgContent = msgContent.replace("$instance_code$",
							String.valueOf(logMap.get("businessCode")));
					msgContent = msgContent.replace("$instance_name$", 
							String.valueOf(logMap.get("businessName")));
					
				} else if ("1018".equals(superviseTypeCode)) {
					//办件类型的模板
					msgContent = msgContent.replace("$instance_code$",
							String.valueOf(logMap.get("businessCode")));
					msgContent = msgContent.replace("$instance_name$", 
							String.valueOf(logMap.get("businessName")));
				}
			} catch (Exception e) {
				e.printStackTrace();
				//LOG.error(e.getMessage());
				throw new Exception(e.getMessage());
			}
			return msgContent;
		}
		return msgContent;
	}
	
	/**
	 * 邮件发送
	 * @param businessId
	 * @param superviseId
	 * @param ruleBean
	 * @return
	 * @throws Exception
	 */
	/*
	private void sendEmail(String businessId, String superviseId, RuleBean ruleBean, SuperviseDefinitionBean superviseDefinitionBean) throws Exception {
		
		MessageTemplateParserDao templateParser = new MessageTemplateParserDaoImpl();
		String businessType = templateParser.getBusinessTypeById(superviseDefinitionBean.getMsg_template_id());
		if(RuleConstants.BUSINESS_TYPE_XZSP.equals(businessType.trim())){
			EmailInfoDao email = new EmailInfoDaoImpl();
			Map msgParamMap = new HashMap(); //消息参数
			msgParamMap.put("businessId", businessId);
			msgParamMap.put("superviseId", superviseId);
			msgParamMap.put("ruleBean", ruleBean);	
			msgParamMap.put("superviseDefBean", superviseDefinitionBean);	
			email.sendEmail(msgParamMap); //发送消息
		}
	}*/
}
