package com.chinacreator.dzjc_evaluation.department;

import java.io.Serializable;

/**
 * 测评项子级
 * 
 * @author 谌欣慰
 */
public class DepartmentItem implements Serializable {

	private static final long serialVersionUID = -919642913510969992L;

	private String item_id;
	private String item_name;
	private String parent_item_id;
	private String parent_item_name;

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getParent_item_id() {
		return parent_item_id;
	}

	public void setParent_item_id(String parent_item_id) {
		this.parent_item_id = parent_item_id;
	}

	public String getParent_item_name() {
		return parent_item_name;
	}

	public void setParent_item_name(String parent_item_name) {
		this.parent_item_name = parent_item_name;
	}

	@Override
	public String toString() {
		return "DepartmentItem [item_id=" + item_id + ", item_name=" + item_name + ", parent_item_id=" + parent_item_id
				+ ", parent_item_name=" + parent_item_name + "]";
	}

}
