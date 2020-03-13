package com.chinacreator.dzjc_evaluation.department;

import java.io.Serializable;
import java.util.List;

/**
 * 测评机构
 * 
 * @author 谌欣慰
 */
public class DepartmentOrg implements Serializable {

	private static final long serialVersionUID = -7445622550561546379L;

	private String rid;
	private String org_id;
	private String org_name;
	private String order_no;
	private double sum_score;
	private String instance_id;
	private double bonuspoints;
	private String objectId;
	private List<DepartmentScore> list;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
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

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public List<DepartmentScore> getList() {
		return list;
	}

	public void setList(List<DepartmentScore> list) {
		this.list = list;
	}

	public double getSum_score() {
		return sum_score;
	}

	public void setSum_score(double sum_score) {
		this.sum_score = sum_score;
	}
	
	

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}
	
	

	public double getBonuspoints() {
		return bonuspoints;
	}

	public void setBonuspoints(double bonuspoints) {
		this.bonuspoints = bonuspoints;
	}
	
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String toString() {
		return "PersonUser [rid=" + rid + ", org_id=" + org_id + ", org_name=" + org_name + ", order_no=" + order_no
				+ ", sum_score=" + sum_score + ", list=" + list + "]";
	}

}
