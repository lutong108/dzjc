package com.chinacreator.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.dzjc_ruleEngine.YiZhengData;

/**
 * 处理接口返回的数据
 * @author liaolong
 * 
 */
public class HttpInterfaceUtil {

	// 益政api地址。
	private static String YIZHENG_API_URL = ConfigManager.getInstance().getConfig("yizheng_api_url");
	// 益政获取访问令牌接口地址。
	private static String YIZHENG_TOKEN_URL = ConfigManager.getInstance().getConfig("yizheng_token_url");
	// 益政获取经办人列表地址。
	private static String YIZHENG_DATA_URL = ConfigManager.getInstance().getConfig("yizheng_data_url");
	// 益政token。
	private static String YIZHENG_TOKEN = ConfigManager.getInstance().getConfig("yizheng_token");
	// 益政AccessToken失效时间。
	private static String YIZHENG_TOKEN_OUT = ConfigManager.getInstance().getConfig("yizheng_token_out");
	
	public static String getYiZhengAccessToken(ServletContext application){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//是否需要重新获取AccessToken的标识
		boolean flag = false;
		String accessToken = String.valueOf(application.getAttribute("yiZhengAccessToken"));
		System.out.println(sdf.format(new Date())+"application中取出的AccessToken是："+accessToken);
		if(StringUtils.isBlank(accessToken) || "null".equalsIgnoreCase(accessToken)){
			flag = true;
		}
		else {
			String lastTime = String.valueOf(application.getAttribute("yiZhengTime"));
			String nowTime = sdf.format(new Date());
			long seconds = DateUtil.getDiff(lastTime, nowTime);
			long tokenOut = Long.valueOf(YIZHENG_TOKEN_OUT);
			//益政令牌失效时间为7200秒
			//如果上次获取令牌的时间距离当前已经超过7000秒，则需要重新获取
			if(seconds > tokenOut){
				flag = true;
			}
		}
		if(flag){
			String token = null;
			try {
				token = URLEncoder.encode(YIZHENG_TOKEN, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String tokenUrl = YIZHENG_API_URL+YIZHENG_TOKEN_URL+"?token="+token;
			String tokenStr = HttpClientUtil.doGetHeader(tokenUrl,null,null);
			System.out.println(sdf.format(new Date())+"：调用益政获取访问令牌接口返回结果=="+tokenStr);
			if(StringUtils.isNotBlank(tokenStr)){
				JSONObject obj = JSONObject.parseObject(tokenStr);
				if(obj.getBooleanValue("ok")){
					JSONArray dataArray = obj.getJSONArray("data");
					if(dataArray != null && dataArray.size() > 0){
						JSONObject tokenObj = dataArray.getJSONObject(0);
						if(tokenObj != null && StringUtils.isNotBlank(tokenObj.getString("token"))){
							accessToken = tokenObj.getString("token");
						}
					}
				}
			}
			application.setAttribute("yiZhengAccessToken", accessToken);
			application.setAttribute("yiZhengTime", sdf.format(new Date()));
		}
		return accessToken;
	}
	
	public static Map<String,Object> getYiZhengData(String markId, String opinionId
			,ServletContext application){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//定义返回的数据
		List<YiZhengData> dataList = new ArrayList<YiZhengData>();
		YiZhengData data = null;
		List<String> opinionIdList = new ArrayList<String>();
		//调用接口
		String dataStr = getYiZhengDataInterface(markId,application);
		java.sql.Date createDate = DateUtil.dateToDate(new Date());
		if(StringUtils.isNotBlank(dataStr)){
			JSONObject obj = JSONObject.parseObject(dataStr);
			if(obj.getBooleanValue("ok")){
				JSONArray dataArray = obj.getJSONArray("data");
				JSONObject dataObj = null;
				if(dataArray != null && dataArray.size() > 0){
					
					for(int i=0;i<dataArray.size();i++){
						data = new YiZhengData();
						dataObj = dataArray.getJSONObject(i);
						data.setDataId(StringUtil.getUUID2());
						data.setOpinionId(opinionId);
						data.setMarkId(markId);
						data.setOk("1");
						data.setOrgId(dataObj.getString("org_id"));
						data.setTransactorId(dataObj.getString("transactor_id"));
						data.setTransactorName(dataObj.getString("transactor_name"));
						data.setPhone(dataObj.getString("phone"));
						data.setNodeId(dataObj.getString("node_id"));
						data.setNodeName(dataObj.getString("node_name"));
						data.setRegionId(dataObj.getString("region_id"));
						data.setRegionName(dataObj.getString("region_name"));
						data.setCreateDate(createDate);
						
						dataList.add(data);
					}
				}
			}
			else {
				JSONObject err = obj.getJSONObject("err");
				if(err != null && StringUtils.isNotBlank(err.getString("code"))){
					String code = err.getString("code");
					String msg = err.getString("msg");
					
					data = new YiZhengData();
					data.setDataId(StringUtil.getUUID2());
					data.setOpinionId(opinionId);
					data.setMarkId(markId);
					data.setOk("0");
					data.setCode(code);
					data.setMsg(msg);
					data.setCreateDate(createDate);
					dataList.add(data);
					
					//如果code是0或者1101，则不发牌
					//code=0:事务的该节点未有授权的账号
					//code=1101:事务的该节点未处在正在待办理状态
					if("0".equals(code) || "1101".equals(code)){
						opinionIdList.add(opinionId);
					}
				}
			}
		}
		resultMap.put("dataList", dataList); 
		resultMap.put("opinionIdList", opinionIdList); 
		return resultMap;
	}
	
	public static String getYiZhengDataInterface(String markId,ServletContext application){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataUrl = YIZHENG_API_URL+YIZHENG_DATA_URL+"?markId="+markId;
		//获取令牌
		String accessToken = getYiZhengAccessToken(application);
		Map<String,String> headerParam = new HashMap<String,String>();
		headerParam.put("Authorization", accessToken);
		String dataStr = HttpClientUtil.doGetHeader(dataUrl,null,headerParam);
		System.out.println(sdf.format(new Date())+":markId="+markId+"：调用益政获取经办人列表数据接口返回结果=="+dataStr);
		return dataStr;
	}
}
