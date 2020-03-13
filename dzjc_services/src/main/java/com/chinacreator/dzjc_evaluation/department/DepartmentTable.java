package com.chinacreator.dzjc_evaluation.department;

import java.io.Serializable;

/**
 * 考核表
 * 
 * @author 谌欣慰
 */
public class DepartmentTable implements Serializable {

	private static final long serialVersionUID = -3024682765903187098L;

	private String table_id;
	private String table_name;

	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	@Override
	public String toString() {
		return "PersonTable [table_id=" + table_id + ", table_name=" + table_name + "]";
	}

}
