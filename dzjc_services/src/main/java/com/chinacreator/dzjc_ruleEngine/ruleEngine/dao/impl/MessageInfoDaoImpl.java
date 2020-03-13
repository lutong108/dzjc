package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.TaJcComplainBaseinfo;
import com.chinacreator.dzjc_ruleEngine.MsgTemplate;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.MessageInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ConsultInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ServicesGuideInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseDefinitionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.support.RuleConstants;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright:Copyright (c) 2011
 * </p>
 * <p>
 * Company:湖南科创
 * </p>
 * 
 * @author 刘尹
 * @version 1.0
 * @date 2011-9-4
 */
public class MessageInfoDaoImpl implements MessageInfoDao {

	//private static final Logger LOG = Logger.getLogger(MessageInfoDaoImpl.class.getName());

	/**
	 * 根据事项ID获取消息通知用户列表
	 * 
	 * @param approveId
	 * @return
	 * @throws Exception
	 */
	//	public List<MessageUserBean> findMessageUserByApprove(String approveId) throws Exception {
	//		PreparedDBUtil preDbUtil = new PreparedDBUtil();
	//		List<MessageUserBean> userList = new ArrayList<MessageUserBean>();
	//		StringBuffer sqlStr = new StringBuffer();
	//		try {
	//			sqlStr.append("SELECT u.message_user_id,u.warn_level,u.user_name, ");
	//			sqlStr.append("u.user_mobile,u.state,u.remark FROM TA_MESSAGE_USER u ");
	//			sqlStr.append("WHERE u.approve_id='").append(approveId).append("'");
	//			preDbUtil.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
	//			preDbUtil.executePrepared();
	//			
	//			if(preDbUtil!=null && preDbUtil.size()>0){
	//				MessageUserBean messageUserBean = new MessageUserBean();
	//				messageUserBean.setMessage_user_id(preDbUtil.getString(0,"message_user_id"));
	//				messageUserBean.setWarn_level(preDbUtil.getString(0,"warn_level"));
	//				messageUserBean.setUser_name(preDbUtil.getString(0,"user_name"));
	//				messageUserBean.setUser_mobile(preDbUtil.getString(0,"user_mobile"));
	//				messageUserBean.setState(preDbUtil.getString(0,"state"));
	//				messageUserBean.setRemark(preDbUtil.getString(0,"remark"));
	//				userList.add(messageUserBean);
	//			}
	//		} catch(Exception e) {
	//			LOG.error(e.getMessage());
	//			throw new Exception(e.getMessage());
	//		}
	//		return userList;
	//	}

	/**
	 * 根据监察级别和事项配置加载用户 1、如果是预警，则只加载预警配置用户 2、如果是黄牌，则加载黄牌配置用户与预警配置用户
	 * 3、如果是红牌，则加载预警、黄牌和红牌配置用户
	 * 
	 * @param superviseLevel
	 * @param businessId
	 * @return
	 * @throws Exception
	 */
	//	public List<MessageUserBean> findCfgUserList(String superviseLevel, String businessId) throws Exception {
	//		PreparedDBUtil preDbUtil = new PreparedDBUtil();
	//		PreparedDBUtil preDbUtil2 = new PreparedDBUtil();
	//		List<MessageUserBean> userList = new ArrayList<MessageUserBean>();
	//		StringBuffer sqlStr = new StringBuffer();
	//		StringBuffer sqlStr2 = new StringBuffer();//获取在办件当前环节用户的号码
	//		try {
	//			sqlStr.append("select distinct u.user_name,u.user_mobile,(select i.org_id  from ta_sp_instance i where i.instance_id ='").append(businessId).append("') as org_id ");
	//			sqlStr.append(" from ta_message_user u ");
	//			sqlStr.append(" where u.approve_id=(select i.approve_id from ta_sp_instance i");
	//			sqlStr.append(" where i.instance_id='").append(businessId).append("')");
	//			sqlStr.append(" and to_number(u.warn_level)<=to_number('").append(superviseLevel).append("')");
	//			sqlStr.append(" and u.state='Y'");
	//			preDbUtil.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
	//			preDbUtil.executePrepared();
	//			
	//			sqlStr2.append(" select u.user_realname,u.user_mobiletel1,i.batchsl_id,i.org_id ");
	//			sqlStr2.append("  from app_xzsp.ta_sp_instance i, creatorepp.td_proc_todo_task t,creatorepp.td_sm_user u ");
	//			sqlStr2.append(" where t.businessid = i.cc_form_instanceid and t.username = u.user_name ");
	//			sqlStr2.append(" and '01@' || i.item_insid = '").append(businessId).append("' ");
	//			sqlStr2.append(" and i.end_time is null and u.user_mobiletel1 is not null");
	//			preDbUtil2.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sqlStr2.toString());
	//			preDbUtil2.executePrepared();
	//			if(preDbUtil2.size()>0 && !"".equals(preDbUtil2.getString(0,"batchsl_id"))){
	//				return userList;
	//			}else{
	//				for(int a = 0; a < preDbUtil.size(); a ++){
	//					MessageUserBean messageUserBean = new MessageUserBean();
	//					messageUserBean.setMessage_user_id(preDbUtil.getString(a,"message_user_id"));
	//					messageUserBean.setWarn_level(preDbUtil.getString(a,"warn_level"));
	//					messageUserBean.setUser_name(preDbUtil.getString(a,"user_name"));
	//					messageUserBean.setUser_mobile(preDbUtil.getString(a,"user_mobile"));
	//					messageUserBean.setState(preDbUtil.getString(a,"state"));
	//					messageUserBean.setRemark(preDbUtil.getString(a,"remark")); 
	//					messageUserBean.setOrg_id(preDbUtil.getString(a,"org_id"));
	//					userList.add(messageUserBean);
	//				}
	//				if(preDbUtil2!=null && preDbUtil2.size()>0){
	//					MessageUserBean messageUserBean = new MessageUserBean();
	//					messageUserBean.setUser_name(preDbUtil2.getString(0,"user_realname"));
	//					messageUserBean.setUser_mobile(preDbUtil2.getString(0,"user_mobiletel1"));
	//					messageUserBean.setOrg_id(preDbUtil2.getString(0,"org_id"));
	//					userList.add(messageUserBean);
	//				}
	//			}
	//			
	//		} catch(Exception e) {
	//			LOG.error(e.getMessage());
	//			throw new Exception(e.getMessage());
	//		}
	//		
	//		return userList;
	//	}

	/**
	 * 发送消息 参数： businessId：业务ID 根据业务ID获取消息通知配置信息 templateId：模板ID
	 * 根据模板ID与业务ID获取消息内容 superviseId：监察信息ID 根据监察信息ID将信息更新到监察信息表
	 * superviseLevel：预警级别 为红牌时要同时加载黄牌、预警通知人员，为黄牌时同时加载预警通知人员
	 * 
	 * @param paraMap
	 * @return msgContent
	 * @throws Exception
	 */
	public String sendMessage(Map paraMap) throws Exception {
		String msgContent = null;
		String businessId = (String) paraMap.get("businessId");
		String suspendId = (String) paraMap.get("suspendId");
		//String superviseId = (String) paraMap.get("superviseId");
		SuperviseDefinitionBean superviseDefBean = (SuperviseDefinitionBean) paraMap.get("superviseDefBean");
		//RuleBean ruleBean = (RuleBean) paraMap.get("ruleBean");

		//String superviseLevel = superviseDefBean.getSupervise_level_code();
		String templateId = superviseDefBean.getMsg_template_id();
		String superviseTypeCode = superviseDefBean.getSupervise_type_code();
		String sendStatus = "0";
		boolean isAutoSendMsg = false;
		if (RuleConstants.SENDCARD_TYPE_AUTO.equals(superviseDefBean.getSendcard_type())) {
			sendStatus = "1";
			isAutoSendMsg = true;
		}
		try {
			//获取邮件发送模板信息
			MsgTemplate msgTemplateBean = DaoFactory.create(MsgTemplate.class).selectByID(templateId);
			if(null == msgTemplateBean){
				return msgContent;
			}
			else if(!RuleConstants.BUSINESS_TYPE_XZSP.equals(msgTemplateBean.getTemplateType().trim())){
				return msgContent;
			}
			DecimalFormat df = new DecimalFormat("#");
			if ("1011".equals(superviseTypeCode)) {
				//办件类型的模板
				//查询办件信息
				InstanceInfo instanceBean = DaoFactory.create(InstanceInfo.class)
						.getSession()
						.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectMainByID", 
								businessId);
				if (instanceBean != null) {
					msgContent = msgTemplateBean.getTemplateContent().replace("$instance_code$",
							instanceBean.getInstanceCode());
					msgContent = msgContent.replace("$instance_name$", instanceBean.getInstanceName());
					/*ServicesGuideInfo servicesGuideBean = DaoFactory.create(ServicesGuideInfo.class).selectByID(
							instanceBean.getApproveId());*/
					Double approveLimit = instanceBean.getApproveLimit();
					Double commitmentLimit = instanceBean.getCommitmentLimit();
					String approveLimitString = "";
					String commitmentLimitString = "";
					if (approveLimit != null) {
						approveLimitString = df.format(approveLimit);
					}
					if (commitmentLimit != null) {
						commitmentLimitString = df.format(commitmentLimit);
					}
					msgContent = msgContent.replace("$approve_limit$", approveLimitString);
					msgContent = msgContent.replace("$commitment_limit$", commitmentLimitString);
				}

			} else if ("1013".equals(superviseTypeCode)) {
				//咨询类型模板
				//查询咨询信息

				ConsultInfo consultInfoBean = DaoFactory.create(ConsultInfo.class).selectByID(businessId);
				msgContent = msgTemplateBean.getTemplateContent();
				if (consultInfoBean != null) {
					msgContent = msgContent.replace("$consult_title$", consultInfoBean.getConsultTitle());
					Double consultLimit = consultInfoBean.getConsultLimit();
					if (consultLimit != null) {
						msgContent = msgContent.replace("$consult_limit$", df.format(consultLimit));
					} else {
						msgContent = msgContent.replace("$consult_limit$", "");
					}
				}

			} else if ("1012".equals(superviseTypeCode)) {
				//投诉类型模板
				//查询投诉信息
				TaJcComplainBaseinfo complain = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(businessId);
				msgContent = msgTemplateBean.getTemplateContent();
				if (complain != null) {
					msgContent = msgContent.replace("$complain_id$", complain.getComplainId());
					msgContent = msgContent.replace("$complain_title$", complain.getComplainTitle());
					Date expireDate = complain.getExpireDate();
					if (expireDate != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						msgContent = msgContent.replace("$complain_limit$", sdf.format(expireDate));
					} else {
						msgContent = msgContent.replace("$complain_limit$", "");
					}
				}

			} else if ("1016".equals(superviseTypeCode)) {
				//特别程序类型的模板
				//查询办件信息
				Map<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("suspendId", suspendId);
				paramMap.put("businessId", businessId);
				InstanceInfo instanceBean = DaoFactory.create(InstanceInfo.class)
						.getSession()
						.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectSuspendByID", 
								paramMap);
				if (instanceBean != null) {
					msgContent = msgTemplateBean.getTemplateContent().replace("$instance_code$",
							instanceBean.getInstanceCode());
					msgContent = msgContent.replace("$instance_name$", instanceBean.getInstanceName());
					Double commitmentLimit = instanceBean.getCommitmentLimit();
					String commitmentLimitString = "";
					if (commitmentLimit != null) {
						commitmentLimitString = df.format(commitmentLimit);
					}
					msgContent = msgContent.replace("$time_limit$", commitmentLimitString);
				}
			} else if ("1017".equals(superviseTypeCode)) {
				//网上办事的模板
				//查询办件信息
				InstanceInfo instanceBean = DaoFactory.create(InstanceInfo.class).selectByID(businessId);
				if (instanceBean != null) {
					msgContent = msgTemplateBean.getTemplateContent().replace("$instance_code$",
							instanceBean.getInstanceCode());
					msgContent = msgContent.replace("$instance_name$", instanceBean.getInstanceName());
				}
			} else if ("1018".equals(superviseTypeCode)) {
				//办件类型的模板
				//查询办件信息
				InstanceInfo instanceBean = DaoFactory.create(InstanceInfo.class)
						.getSession()
						.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectMainByID", 
								businessId);
				if (instanceBean != null) {
					msgContent = msgTemplateBean.getTemplateContent().replace("$instance_code$",
							instanceBean.getInstanceCode());
					msgContent = msgContent.replace("$instance_name$", instanceBean.getInstanceName());
				}

			}
			/*
			 * InstanceInfo instanceBean =
			 * DaoFactory.create(InstanceInfo.class).selectByID(businessId);
			 * ServicesGuideInfo servicesGuideBean =
			 * DaoFactory.create(ServicesGuideInfo
			 * .class).selectByID(instanceBean.getApproveId()); msgContent =
			 * msgTemplateBean.getTemplateContent().replace("$instance_code$",
			 * instanceBean.getInstanceCode()); msgContent =
			 * msgContent.replace("$instance_name$",
			 * instanceBean.getInstanceName()); msgContent =
			 * msgContent.replace("$approve_limit$",
			 * servicesGuideBean.getApproveLimit().toString()); msgContent =
			 * msgContent.replace("$commitment_limit$",
			 * servicesGuideBean.getCommitmentLimit().toString());
			 */

			//			MessageTemplateParserDao templateParser = new MessageTemplateParserDaoImpl();
			//			msgContent = templateParser.parseMsgTemplate(templateId, businessId);
			//			if("Y".equals(ruleBean.getIssendmsg())){
			//				//MessageManager messageManager = ComponentFactory.getMessageManagerInstance();
			//				MessageInfoDao messageInfoDao = new MessageInfoDaoImpl();
			//				List<MessageUserBean> userList = messageInfoDao.findCfgUserList(superviseLevel, businessId);
			//	
			//				for(int i=0; i<userList.size(); i++){
			//					MessageUserBean messageUserBean = (MessageUserBean)userList.get(i);
			//					WainningSendHistoryBean wainningSendHistoryBean = new WainningSendHistoryBean();
			//					wainningSendHistoryBean.setReceiver_name(messageUserBean.getUser_name());
			//					wainningSendHistoryBean.setReceiver_handset(messageUserBean.getUser_mobile());
			//					wainningSendHistoryBean.setSupervise_info_id(superviseId);
			//					wainningSendHistoryBean.setSend_status(sendStatus);
			//					insertSendHistory(wainningSendHistoryBean);//保存预警发送历史
			//					if(isAutoSendMsg){ //自动发牌 
			//						MsgFactory.getMsgBoxInstance().msgSendV3(businessId,"",messageUserBean.getUser_mobile(),
			//								messageUserBean.getUser_name(),msgContent,"Supervise",messageUserBean.getOrg_id(),superviseDefBean.getArea_code(),getDateTime());
			//						//messageManager.sendMessage(msgContent, messageUserBean.getUser_mobile());//调用消息发送接口
			//					}
			//				}
			//			}
		} catch (Exception e) {
			e.printStackTrace();
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return msgContent;
	}

	/**
	 * 保存消息发送历史记录
	 * 
	 * @param businessId
	 * @param ruleBean
	 * @return
	 * @throws Exception
	 */
	//	public void insertSendHistory(WainningSendHistoryBean wainningSendHistoryBean) throws Exception {
	//		PreparedDBUtil preDbUtil = new PreparedDBUtil();
	//		StringBuffer sqlStr = new StringBuffer();
	//		try {
	//			sqlStr.append("insert into ta_warning_sendhistory(id,supervise_info_id, ");
	//			sqlStr.append("receiver_name,receiver_handset,send_status,send_time) ");
	//			sqlStr.append(" values (SEQ_WARN_SEND_HISTORY.nextval, ");
	//			sqlStr.append("'").append(wainningSendHistoryBean.getSupervise_info_id()).append("',");
	//			sqlStr.append("'").append(wainningSendHistoryBean.getReceiver_name()).append("',");
	//			sqlStr.append("'").append(wainningSendHistoryBean.getReceiver_handset()).append("',");
	//			sqlStr.append("'").append(wainningSendHistoryBean.getSend_status()).append("',");
	//			sqlStr.append("sysdate)");
	//			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
	//			preDbUtil.executePrepared();
	//		} catch (Exception e) {
	//			LOG.error(e.getMessage());
	//			throw new Exception(e.getMessage());
	//		}
	//	}
	//	public String getDateTime() {
	//		String curTime = "";
	//		try {
	//			Date date = new Date();
	//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	//			curTime = df.format(date);
	//		} catch (Exception e) {
	//			LOG.error(e.getMessage(), e);
	//		}
	//		return curTime;
	//	}
}
