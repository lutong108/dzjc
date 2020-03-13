package com.chinacreator.dzjc_evaluation.person;

import java.io.Serializable;
import java.util.List;

/**
 * 测评项父级
 * 
 * @author 谌欣慰
 */
public class PersonParent implements Serializable {

	private static final long serialVersionUID = -7095205177592737141L;

	private String parent_item_id;
	private String parent_item_name;
	private List<PersonItem> list;

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

	public List<PersonItem> getList() {
		return list;
	}

	public void setList(List<PersonItem> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PersonParent [parent_item_id=" + parent_item_id + ", parent_item_name=" + parent_item_name + ", list="
				+ list + "]";
	}

}
