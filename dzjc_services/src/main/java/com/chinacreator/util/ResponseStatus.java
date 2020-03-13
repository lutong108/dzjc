package com.chinacreator.util;



/**
 * 给页面的返回信息
 * @author zyz818
 * @date 2019-2-28
 */
public class ResponseStatus{

	public static final String OK = "200";
	
	public static final String INTERNAL_SERVER_ERROR = "500";
	
	private ResponseStatus(){}
	
	public static ResponseStatus success(String msg){
		ResponseStatus rs = new ResponseStatus();
		rs.setStatus(OK);
		rs.setMsg(msg);
		return rs;
	}
	
	public static ResponseStatus error(String msg){
		ResponseStatus rs = new ResponseStatus();
		rs.setStatus(INTERNAL_SERVER_ERROR);
		rs.setMsg(msg);
		return rs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private String status;
	private String msg;
}
