package com.chinacreator.dzjc_dealtMatter;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * 投诉监察详情
 * 
 * @author xinwei.chen
 * 
 */
public class SuperviseTS implements Serializable {

	private static final long serialVersionUID = 581454954545219526L;

	private String complain_id;
	private String complain_user_name;
	private String complain_user_org;
	private String bycomplained_user_org;
	private String bycomplained_org;
	private String complain_title;
	private String problem_type;
	private String complain_content;
	private String mainappeal;
	private Date expire_date;
	private String commitment_limit;
	private String supervise_result_name;

	public String getComplain_id() {
		return complain_id;
	}

	public void setComplain_id(String complain_id) {
		this.complain_id = complain_id;
	}

	public String getComplain_user_name() {
		return complain_user_name;
	}

	public void setComplain_user_name(String complain_user_name) {
		this.complain_user_name = complain_user_name;
	}

	public String getComplain_user_org() {
		return complain_user_org;
	}

	public void setComplain_user_org(String complain_user_org) {
		this.complain_user_org = complain_user_org;
	}

	public String getBycomplained_user_org() {
		return bycomplained_user_org;
	}

	public void setBycomplained_user_org(String bycomplained_user_org) {
		this.bycomplained_user_org = bycomplained_user_org;
	}

	public String getBycomplained_org() {
		return bycomplained_org;
	}

	public void setBycomplained_org(String bycomplained_org) {
		this.bycomplained_org = bycomplained_org;
	}

	public String getComplain_title() {
		return complain_title;
	}

	public void setComplain_title(String complain_title) {
		this.complain_title = complain_title;
	}

	public String getProblem_type() {
		return problem_type;
	}

	public void setProblem_type(String problem_type) {
		this.problem_type = problem_type;
	}

	public String getComplain_content() {
		return complain_content;
	}

	public void setComplain_content(String complain_content) {
		this.complain_content = complain_content;
	}

	public String getMainappeal() {
		return mainappeal;
	}

	public void setMainappeal(String mainappeal) {
		this.mainappeal = mainappeal;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public String getCommitment_limit() {
		return commitment_limit;
	}

	public void setCommitment_limit(String commitment_limit) {
		this.commitment_limit = commitment_limit;
	}

	public String getSupervise_result_name() {
		return supervise_result_name;
	}

	public void setSupervise_result_name(String supervise_result_name) {
		this.supervise_result_name = supervise_result_name;
	}

}
