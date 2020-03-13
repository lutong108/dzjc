package com.chinacreator.dzjc_evaluation.department;

import java.io.Serializable;

/**
 * 考评项分数
 * 
 * @author 谌欣慰
 */
public class DepartmentScore implements Serializable {

	private static final long serialVersionUID = 7882629075684276706L;

	private String org_id;
	private String parent_item_id;
	private String item_id;
	private double final_item_value;
	private String instanceId;

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getParent_item_id() {
		return parent_item_id;
	}

	public void setParent_item_id(String parent_item_id) {
		this.parent_item_id = parent_item_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public double getFinal_item_value() {
		return final_item_value;
	}

	public void setFinal_item_value(double final_item_value) {
		this.final_item_value = final_item_value;
	}
	

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	@Override
	public String toString() {
		return "DepartmentScore [org_id=" + org_id + ", parent_item_id=" + parent_item_id + ", item_id=" + item_id
				+ ", final_item_value=" + final_item_value + "]";
	}

}
