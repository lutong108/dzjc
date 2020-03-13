package com.chinacreator.dzjc_performance;

import java.util.List;

/**
 * 数据上报考核项层级展示实体
 * @author 
 *
 */
public class ShowEvalItem {
	
	private String id;
	
	private String proportion;
	
	private Double value;
	
	private String name;
	/**
	 * 占行数
	 */
	private Integer rows;
	
	private String parentId;
	/**
	 * 实例表id
	 */
	private String instanceId;
	private String objectId;
	/**
	 * 层级数
	 */
	private Integer level;
	/**
	 * 下属指标（考核项）集合
	 */
	private List list;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	@Override
	public String toString() {
		return "ShowEvalItem [id=" + id + ", proportion=" + proportion
				+ ", value=" + value + ", name=" + name + ", rows=" + rows
				+ ", parentId=" + parentId + ", instanceId=" + instanceId
				+ ", level=" + level + ", list=" + list + "]";
	}
	
	
	
}
