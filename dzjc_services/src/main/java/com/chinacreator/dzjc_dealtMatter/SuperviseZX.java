package com.chinacreator.dzjc_dealtMatter;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * 咨询监察详情
 * 
 * @author xinwei.chen
 * 
 */
public class SuperviseZX implements Serializable {

	private static final long serialVersionUID = -3937058479480175175L;

	private String consult_id;
	private String consult_title;
	private String approve_name;
	private String org_id;
	private String org_name;
	private String apply_id;
	private String apply_name;
	private String consult_content;
	private Date consult_time;
	private Date expire_time;
	private String supervise_result_name;

	public String getConsult_id() {
		return consult_id;
	}

	public void setConsult_id(String consult_id) {
		this.consult_id = consult_id;
	}

	public String getConsult_title() {
		return consult_title;
	}

	public void setConsult_title(String consult_title) {
		this.consult_title = consult_title;
	}

	public String getApprove_name() {
		return approve_name;
	}

	public void setApprove_name(String approve_name) {
		this.approve_name = approve_name;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getApply_id() {
		return apply_id;
	}

	public void setApply_id(String apply_id) {
		this.apply_id = apply_id;
	}

	public String getApply_name() {
		return apply_name;
	}

	public void setApply_name(String apply_name) {
		this.apply_name = apply_name;
	}

	public String getConsult_content() {
		return consult_content;
	}

	public void setConsult_content(String consult_content) {
		this.consult_content = consult_content;
	}

	public Date getConsult_time() {
		return consult_time;
	}

	public void setConsult_time(Date consult_time) {
		this.consult_time = consult_time;
	}

	public Date getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Date expire_time) {
		this.expire_time = expire_time;
	}

	public String getSupervise_result_name() {
		return supervise_result_name;
	}

	public void setSupervise_result_name(String supervise_result_name) {
		this.supervise_result_name = supervise_result_name;
	}

}
