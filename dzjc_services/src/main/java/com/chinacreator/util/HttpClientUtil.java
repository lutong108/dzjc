 package com.chinacreator.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
    
//    public static String getAuth() {
//    	Base64UploadUtil token = Base64UploadUtil.getFileBase64();
//        return token.fileToBase64(new String(USERKEY + ":" + PASSWORD));
//    }

	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			//用户认证
			//httpGet.setHeader(AUTHENKEY, BASICKEY + getAuth());

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	public static String doGet(String url) {
		return doGet(url, null);
	}

	public static String doGetHeader(String url, Map<String, String> param, Map<String,String> headerParam) {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 封装header信息
			if(headerParam != null){
				for (String key : headerParam.keySet()) {
					httpGet.setHeader(key, headerParam.get(key));
				}
			}
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	

	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,Charset.forName("UTF-8"));
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doPost(String url) {
		return doPost(url, null);
	}
	
	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	public static String doPut(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Put请求
			HttpPut httpPut = new HttpPut(url);
			
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				httpPut.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPut);
			resultString = EntityUtils.toString(response.getEntity(), "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	public static String doPut(String url) {
		return doPut(url, null);
	}
	
	public static String doPutJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Put请求
			HttpPut httpPut = new HttpPut(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPut.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPut);
			resultString = EntityUtils.toString(response.getEntity(), "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultString;
	}
	
	public static void main(String[] args){
		String base_url = "http://dev.egov-china.com:9004";
		String token_url = "/api/Users/GetAccessToken";
		String data_url = "/api/Affairs/GetNodeContactByMark";
		String token = "dZz5vjSYr2rHtJHtR939cnXXetTWZOnh+zGqmfJZXOwQ60UGmbJoLcCiwx3Bl9BV4z3BsMANHRadho4fzoOAlQ7hE/gQL/I5lr3GuOqPoyFKUOdMeBg5T0pYnTG9eHOsLXXnUjsQSjkohRx5mbaSSsKhyB0QqsR7tX8snRCfXzw=";
		try {
			token = URLEncoder.encode(token, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//Base64UploadUtil tk = Base64UploadUtil.getFileBase64();
		//token = tk.byteToBase64(token.getBytes());
		//6054014102592593
		//6053919612477455
		String markId = "6053919612477474";
		//String tokenUrl = base_url+token_url+"?token="+token;
		String dataUrl = base_url+data_url+"?markId="+markId;
		//System.out.println("tokenUrl===="+tokenUrl);
		//获取访问令牌
		//String tokenStr = doGetHeader(tokenUrl,null,null);
		//System.out.println("tokenStr=="+tokenStr);
		//eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDc4MDQwMTYsIlRva2VuVHlwZSI6MSwiVG9rZW4iOnsidXNlcl9pZCI6OCwidG9rZW5fdHlwZSI6MSwiY3JlYXRlX3RpbWUiOiIyMDE5LTAxLTE4VDE1OjMzOjM2Ljc0Mjc4MzgwNyswODowMCIsImludmFsaWRfdGltZSI6IjIwMTktMDEtMThUMTc6MzM6MzYuNzQyNzgzOTk2KzA4OjAwIn0sImFkZGl0aW9uIjp7ImlwIjoiOjpmZmZmOjExMS44LjU3LjYwIn0sInVzZXJfaW5mbyI6eyJ1c2VyX2lkIjo4LCJ1c2VyX2xldmVsX2lkIjo0MDk2LCJhY2NvdW50IjoiT1VUVEVSIiwidXNlcl9uYW1lIjoi5YWs572R5o6l5Y-jIiwiZ2VuZGVyIjoxLCJ1c2VyX3JvbGUiOjAsInBob25lIjoiIiwid29ya2VyX25vIjoiOTAwOCIsImRlcGFydG1lbnQiOjIsImxvY2F0aW9uX2lkIjowLCJwb3RyYWl0X3VybCI6IiIsInBvc2l0aW9uIjoxfX0.mlh-QszChAiFEZroxe5MSHtCv95docEGKSuWX2xDfSTxKcijmp1etcADEgg5chCXuyfYUbN9RWF8n2tLtH2atv_kLz2umkPQjfCq8oUWhm2KWzwB82nbhMnRi_g1WLsmbdzyVrTOejw7wYDcon43EeSyojS48CsAMogGPqLZuHE
		Map<String,String> headerParam = new HashMap<String,String>();
		headerParam.put("Authorization", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDc4MDQwMTYsIlRva2VuVHlwZSI6MSwiVG9rZW4iOnsidXNlcl9pZCI6OCwidG9rZW5fdHlwZSI6MSwiY3JlYXRlX3RpbWUiOiIyMDE5LTAxLTE4VDE1OjMzOjM2Ljc0Mjc4MzgwNyswODowMCIsImludmFsaWRfdGltZSI6IjIwMTktMDEtMThUMTc6MzM6MzYuNzQyNzgzOTk2KzA4OjAwIn0sImFkZGl0aW9uIjp7ImlwIjoiOjpmZmZmOjExMS44LjU3LjYwIn0sInVzZXJfaW5mbyI6eyJ1c2VyX2lkIjo4LCJ1c2VyX2xldmVsX2lkIjo0MDk2LCJhY2NvdW50IjoiT1VUVEVSIiwidXNlcl9uYW1lIjoi5YWs572R5o6l5Y-jIiwiZ2VuZGVyIjoxLCJ1c2VyX3JvbGUiOjAsInBob25lIjoiIiwid29ya2VyX25vIjoiOTAwOCIsImRlcGFydG1lbnQiOjIsImxvY2F0aW9uX2lkIjowLCJwb3RyYWl0X3VybCI6IiIsInBvc2l0aW9uIjoxfX0.mlh-QszChAiFEZroxe5MSHtCv95docEGKSuWX2xDfSTxKcijmp1etcADEgg5chCXuyfYUbN9RWF8n2tLtH2atv_kLz2umkPQjfCq8oUWhm2KWzwB82nbhMnRi_g1WLsmbdzyVrTOejw7wYDcon43EeSyojS48CsAMogGPqLZuHE");
		String dataStr = doGetHeader(dataUrl,null,headerParam);
		System.out.println("dataStr=="+dataStr);
		
		System.exit(0);
	}
}