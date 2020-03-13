package com.chinacreator.dzjc_dealtMatter;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * 办件监察详情
 * 
 * @author xinwei.chen
 * 
 */
public class SuperviseBJ implements Serializable {

	private static final long serialVersionUID = -2889346460171178700L;

	private String title;
	private String org_id;
	private String org_code;
	private String org_name;
	private String supervise_info_id;
	private String business_id;
	private String supervise_type_no;
	private String supervise_type_name;
	private String supervise_result_no;
	private String supervise_result_name;
	private Date supervise_time;
	private String su_state;
	private String fp_state;
	private String instance_code;
	private String apply_id;
	private String apply_name;
	private String approve_id;
	private String approve_name;
	private Date accept_time;
	private String time_limit;
	private String promises_limit;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getSupervise_info_id() {
		return supervise_info_id;
	}

	public void setSupervise_info_id(String supervise_info_id) {
		this.supervise_info_id = supervise_info_id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getSupervise_type_no() {
		return supervise_type_no;
	}

	public void setSupervise_type_no(String supervise_type_no) {
		this.supervise_type_no = supervise_type_no;
	}

	public String getSupervise_type_name() {
		return supervise_type_name;
	}

	public void setSupervise_type_name(String supervise_type_name) {
		this.supervise_type_name = supervise_type_name;
	}

	public String getSupervise_result_no() {
		return supervise_result_no;
	}

	public void setSupervise_result_no(String supervise_result_no) {
		this.supervise_result_no = supervise_result_no;
	}

	public String getSupervise_result_name() {
		return supervise_result_name;
	}

	public void setSupervise_result_name(String supervise_result_name) {
		this.supervise_result_name = supervise_result_name;
	}

	public Date getSupervise_time() {
		return supervise_time;
	}

	public void setSupervise_time(Date supervise_time) {
		this.supervise_time = supervise_time;
	}

	public String getSu_state() {
		return su_state;
	}

	public void setSu_state(String su_state) {
		this.su_state = su_state;
	}

	public String getFp_state() {
		return fp_state;
	}

	public void setFp_state(String fp_state) {
		this.fp_state = fp_state;
	}

	public String getInstance_code() {
		return instance_code;
	}

	public void setInstance_code(String instance_code) {
		this.instance_code = instance_code;
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

	public String getApprove_id() {
		return approve_id;
	}

	public void setApprove_id(String approve_id) {
		this.approve_id = approve_id;
	}

	public String getApprove_name() {
		return approve_name;
	}

	public void setApprove_name(String approve_name) {
		this.approve_name = approve_name;
	}

	public Date getAccept_time() {
		return accept_time;
	}

	public void setAccept_time(Date accept_time) {
		this.accept_time = accept_time;
	}

	public String getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(String time_limit) {
		this.time_limit = time_limit;
	}

	public String getPromises_limit() {
		return promises_limit;
	}

	public void setPromises_limit(String promises_limit) {
		this.promises_limit = promises_limit;
	}

}
