package com.chinacreator.dzjc_ruleEngine.ruleEngine.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import com.alibaba.fastjson.JSONObject;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.MsgRecord;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.MessageBean;
import com.chinacreator.util.HttpClientUtil;

/**
 * 短信发送的工具类
 * @author ZDW
 *
 */
public class SmsUtil {
	/**处理成功*/
	public static String SUCCESS="0";
	/** 登录认证失败*/
	public static String RES_ERROR_001="RES_ERROR_001";
	/**短信平台短信数据处理失败*/
	public static String RES_ERROR_002="RES_ERROR_002";
	/**短信发送时 手机号码为空*/
	public static String RES_ERROR_003="RES_ERROR_003";
	/**短信发送时 内容为空*/
	public static String RES_ERROR_004="RES_ERROR_004";
	/**短信发送时应用id为空*/
	public static String RES_ERROR_005="RES_ERROR_005";
	/** 是否需要回执 值错误*/
	public static String RES_ERROR_006="RES_ERROR_006";
	
	public static String endpoint = ConfigManager.getInstance().getConfig("sms_server_url");
	public static String applicationid = ConfigManager.getInstance().getConfig("sms_server_applicationid");
	public static String username = ConfigManager.getInstance().getConfig("sms_server_username");
	public static String password = ConfigManager.getInstance().getConfig("sms_server_password");
	public static String orgcode = ConfigManager.getInstance().getConfig("sms_server_orgcode");
	public static String orgname = ConfigManager.getInstance().getConfig("sms_server_orgname");
	public static String key = ConfigManager.getInstance().getConfig("sms_server_key");
	

	//短信验证码接口调用webservices			永兴
	public static String sendMessageWebserviceInfo(String content, String phone) {
		String returnvalue="";
		try { 
			//创建一个服务（service）调用（call）
			Service service = new Service();
			Call call = (Call) service.createCall();      
			//设置service所在的url
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			MessageBean msbean = new MessageBean();
			msbean.setSmID(UUID.randomUUID().toString());
			msbean.setPhones(phone);
			msbean.setContent(content);
			msbean.setApplicationid(applicationid);
			
			call.setOperation("sendSingleSms");//调用的是 单条短信发送给若干个手机号码的接口
			String send = (String)call.invoke(new Object[]{username,password,key,applicationid,orgcode,orgname,msbean});  
			returnvalue=send;
			System.out.println("调用返回结果集=="+send);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
			return returnvalue;
	}
	
	
	public static List<MsgRecord> getAllMsgList(MsgRecord msg){
		List<MsgRecord> list = new ArrayList<MsgRecord>();
		list = DaoFactory.create(MsgRecord.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.MsgRecordMapper.selectAllNotSend",msg);
		return list;
	}
	
	public static String sendMessageHttp(String content,String phone){
		String returnvalue="";
		try {
			Map<String,String> paramsMap= new HashMap<>();
			paramsMap.put("userName",username);
			paramsMap.put("password",password);
			paramsMap.put("buesinessCode",applicationid);
			paramsMap.put("key",key);
			paramsMap.put("applicationid",applicationid);
			paramsMap.put("phones",phone);
			paramsMap.put("content",content);
			paramsMap.put("reqdeliveryreport","0");
			String send = HttpClientUtil.doPost(endpoint, paramsMap);
			JSONObject parseObject = JSONObject.parseObject(send);
			if(parseObject==null){
				parseObject= new JSONObject();
			}
			Object object = parseObject.get("result");
			System.out.println("调用返回结果集=="+object.toString());
			returnvalue=object.toString();
		} catch (Exception e) {
			return "error";
		}
		return returnvalue;
	}
	
	
}
