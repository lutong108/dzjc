package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_complain.Constant;
import com.chinacreator.dzjc_dealtMatter.TaJcSuperviseInfo;
import com.chinacreator.dzjc_ruleEngine.MsgRecord;
import com.chinacreator.dzjc_ruleEngine.MsgTemplateOrg;
import com.chinacreator.dzjc_ruleEngine.UserInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.SendMsgServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.SmsYueYangUtil;
import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;
import com.chinacreator.dzjc_ruleEngine.web.rest.MsgTemplateOrgController;
import com.chinacreator.dzjc_ruleEngine.web.rest.UserInfoController;
import com.chinacreator.util.HolidayDateUtil;
import com.chinacreator.util.StringUtil;

public class SendMsgOrgServiceImpl implements SendMsgServiceIfc {
	private static final String ORG_NAME="$org_name$";
	private static final String TIME="$time$";
	private static final String WARNING="$warning$";
	private static final String YELLOW_CARD="$yellow_card$";
	private static final String RED_CARD="$red_card$";
	private static final String MOBILE_REGEX="1[34578]\\d{9}";
	String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
	@Override
	public void doStartup() {
		List<MsgRecord> allMsgList = getAllMsgList();
		runSendMsg(allMsgList);
	}
	
	/**
	 * 1：判断当前时间是否为节假日 
	 * 2：查询发牌情况
	 * 3：根据机构ID批量查询管理员
	 * 4：加载短信模板
	 * 5：根据查询到的发牌信息封装消息模版
	 */
	private List<MsgRecord> getAllMsgList() {
		List<MsgRecord> msgRecordlist = new ArrayList<MsgRecord>();//smsList
		try {
			String nowData = DateUtil.getCurrentDate();//"2019-01-17";//DateUtil.getCurrentDate();//"2018-12-04";
			String formatNowDate = DateUtil.getFormatDate("yyyy年MM月dd日");//"2019年01月17日";//DateUtil.getFormatDate("yyyy年MM月dd日");
			//1：判断当前时间是否为节假日
			if(!HolidayDateUtil.isHoliday(nowData)){
				//2：不是节假日则查询当然发牌情况
				//Map<String, String> params = new HashMap<>();
				//params.put("date", nowData);
				//List<Map<String,Object>> sendCardList = DaoFactory.create(TaJcSuperviseInfo.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.SumSuperviseInfoMapper.selectSendCardsByOrgCode",params);
				List<Map<String,Object>> sendCardList = DaoFactory.create(TaJcSuperviseInfo.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.SumSuperviseInfoMapper.selectNotOverSendCardsYueYang");
				if(sendCardList != null && sendCardList.size() > 0){//3：根据机构ID批量查询管理员
					StringBuffer sb = new StringBuffer();
					for (Map<String, Object> map : sendCardList) {
						String orgId = map.get("ORGID").toString();
						sb.append("orgId=").append(orgId).append("&");
					}
					//sb.deleteCharAt(sb.length() - 1);List<Map<String,Map<String,String>>>
					 String resultJson = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgmanagers?"+sb.toString()+"categoryId=10-Z", String.class);
					 JSONObject jsonObject = JSONObject.fromObject(resultJson);
					 MsgRecord msgRecord = null;
					 if(StringUtils.isNotBlank(resultJson)){
						 
						 //4：加载短信模板
						 MsgTemplateOrgController msgTemplateOrgController = new MsgTemplateOrgController();
						 List<MsgTemplateOrg> msgTemplateOrgAll = msgTemplateOrgController.getMsgTemplateOrgAll();
						 MsgTemplateOrg msgTemplateOrgArea = null;//区域模板
						 MsgTemplateOrg msgTemplateOrgOrg = null;//单位模板
						 for (MsgTemplateOrg msgTemplateOrg : msgTemplateOrgAll) {
							 if("0".equals(msgTemplateOrg.getTemplateType())){
								 msgTemplateOrgArea = msgTemplateOrg;
							 }else if("1".equals(msgTemplateOrg.getTemplateType())){
								 msgTemplateOrgOrg = msgTemplateOrg;
							 }
						 }
						 
						 UserInfoController userInfoController = new UserInfoController();
						 for (Map<String, Object> sendCardMap : sendCardList) {//5：根据查询到的发牌信息封装消息模版
							String orgId = sendCardMap.get("ORGID").toString();
							
							 List<Map<String,String>> adminList = (List<Map<String, String>>) jsonObject.get(orgId);//得到当前机构管理员
							 if(adminList != null && adminList.size() > 0 ){
								  for (int j = 0; j < adminList.size(); j++) {
									  Map<String, String> adminMap = adminList.get(j);
									  String adminId = adminMap.get("id");
									  UserInfo adminInfo = userInfoController.get(adminId+"_"+orgId);//根据用户ID与机构ID查询用户信息
									  if(adminInfo != null && StringUtils.isNotBlank(adminInfo.getPhone())){
										  List<String> matcherMobile = matcherMobile(adminInfo.getPhone(),MOBILE_REGEX);
										  if(matcherMobile.size() > 0){
											  String orgLevel = sendCardMap.get("ORGLEVEL") == null ? "" : (String)sendCardMap.get("ORGLEVEL");
											  String orgName = sendCardMap.get("ORGNAME") == null ? "" : (String)sendCardMap.get("ORGNAME");
											  String warning = sendCardMap.get("WARNING") == null ? "0" : sendCardMap.get("WARNING").toString();
											  String yellowCard = sendCardMap.get("YELLOWCARD") == null ? "0" : sendCardMap.get("YELLOWCARD").toString();
											  String redCard = sendCardMap.get("REDCARD") == null ? "0" : sendCardMap.get("REDCARD").toString();
											  if(StringUtils.isNotBlank(orgLevel)){
												  String sendContent = "";
												  if("2".equals(orgLevel)){//区域管理员
													  String templateContentArea = msgTemplateOrgArea.getTemplateContent();
													  sendContent = templateContentArea.replace(ORG_NAME,orgName).replace(TIME,formatNowDate).replace(WARNING,warning).replace(YELLOW_CARD,yellowCard).replace(RED_CARD,redCard);
												  }else if("5".equals(orgLevel)){//单位管理员
													  String templateContentOrg = msgTemplateOrgOrg.getTemplateContent();
													  sendContent = templateContentOrg.replace(ORG_NAME,orgName).replace(TIME,formatNowDate).replace(WARNING,warning).replace(YELLOW_CARD,yellowCard).replace(RED_CARD,redCard);
												  }
												  for (String phone : matcherMobile) {
													  msgRecord = new MsgRecord();
													  msgRecord.setOrgId(orgId);
													  msgRecord.setMsgId(UUID.randomUUID().toString());
													  msgRecord.setSendMobile(phone);
													  msgRecord.setSendContent(sendContent);
													  msgRecordlist.add(msgRecord);
												  }
											  }
										  }else{
											  System.err.println("用户ID为："+adminId+"，预留电话号码不符合 "+MOBILE_REGEX+" 规则匹配失败......");
										  }
										  
									  }else{
										  System.err.println("用户ID为："+adminId+"，用户不存在或者用户没有预留电话号码......");
									  }
								  }
								  
							  }else{
								  System.err.println("机构ID为："+orgId+"，无法查询到管理员");
							  }
						 }
					 }else{
						 System.err.println("无法查询到管理员,短信引擎停止启动......");
					 }
				}else{
					System.err.println(formatNowDate+"没有产生发牌,短信引擎停止启动......");
				}
			}else{
				System.err.println(formatNowDate+"为节假日,短信引擎停止启动......");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgRecordlist;
	}
	private void runSendMsg(List<MsgRecord> list) {
		try {
			List<MsgRecord> sendList = new ArrayList<MsgRecord>();
			MsgRecord msg = null;
			for (MsgRecord send : list) {
				//调用接口
				String returnvalue = SmsYueYangUtil.sendMessageWebserviceInfo(send.getSendContent(), send.getSendMobile());
				//String returnvalue = SmsYueYangUtil.sendMessageHttp(send.getSendContent(), send.getSendMobile());
				msg = new MsgRecord();
				msg.setMsgId(send.getMsgId());
				msg.setOrgId(send.getOrgId());
				msg.setSendMobile(send.getSendMobile());
				msg.setSendTime(new java.sql.Date(System.currentTimeMillis()));
				if(!StringUtil.isBlank(returnvalue)){
					//	现场返回测试只有 成功:0  失败:其他
					if("0".equals(returnvalue)){
						msg.setSendFlag(Constant.YES);
					}else{
						msg.setSendFlag(Constant.NO);
					}
					msg.setResultDes(returnvalue);
				}else {
					msg.setSendFlag(Constant.NO);
					msg.setResultDes("接口调用失败");
				}
				sendList.add(msg);
			}
			if(sendList.size() > 0){
				//批量添加短信发送纪录
				DaoFactory.create(MsgRecord.class).insertBatch(sendList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("短信引擎启动失败......");
		}
	}
	public static List<String> matcherMobile(String phone,String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(phone);
		//由于不知道有多少个电话号码会被匹配出来，所以采用List存放电话号码
		List<String> mobileList = new ArrayList<String>();
		while(m.find()){
			mobileList.add(m.group()); //将匹配出的电话号码存放到mobileList中
		}
		return mobileList;
	}
}
