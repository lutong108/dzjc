package com.chinacreator.dzjc_performance;

import java.sql.Date;

/**
 * 数据上报Bean
 * @author 
 *
 */
public class DataUpload {
	/*
	 * 考核表id
	 */
	private String tableId;
	/*
	 * 考核项id
	 */
	private String itemId;
	/*
	 * 考核指标id
	 */
	private String pointId;
	/*
	 * 实例id
	 */
	private String instanceId;
	/*
	 * 权重
	 */
	private String proportion1;
	/*
	 * 考核项分值
	 */
	private Double value1;
	/*
	 * 考核项名称
	 */
	private String name1;
	/*
	 * 扣分原因
	 */
	private String deductReasons;
	/*
	 * 指标得分
	 */
	private Double pointScore;
	/*
	 * 指标名称
	 */
	private String pointName;
	/*
	 * 操作方式
	 */
	private String isAuto;
	/**
	 * 考核对象名称
	 */
	private String evalObjectName;
	/*
	 * 考核表名称
	 */
	private String tableName;
	/*
	 * 考核对象类型
	 */
	private String evalObjectType;
	/*
	 * 考核时间
	 */
	private Date evalTime;
	/*
	 * 考核表分数
	 */
	private String totalValue;
	/*
	 * 考核对象id
	 */
	private String objectId;
	/*
	 * 父考核项id
	 */
	private String parentId;
	/*
	 * 用户权限编码
	 */
	private String code;
	/**
	 * 是否上报
	 */
	private String state;
	/**
	 * 考核对象主键ID
	 */
	private String id;
	/**
	 * 加分
	 */
	private Double bonuspoints;
	/**
	 * 加分原因
	 */
	private String reasonsforbonus;
	/**
	 * 所属单位
	 */
	private String orgName;
	/**
	 * 是否评分
	 */
	private String isGrade;
	/*
	 * 父考核项属性
	 */
	private String Name2;
	
	private String value2;
	
	private String proportion2;
	
	private String Name3;
	
	private String value3;
	
	private String proportion3;
	
	private String Name4;
	
	private String value4;
	
	private String proportion4;

	
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getPointId() {
		return pointId;
	}
	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	
	public String getProportion1() {
		return proportion1;
	}
	public void setProportion1(String proportion1) {
		this.proportion1 = proportion1;
	}
	public Double getValue1() {
		return value1;
	}
	public void setValue1(Double value1) {
		this.value1 = value1;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getDeductReasons() {
		return deductReasons;
	}
	public void setDeductReasons(String deductReasons) {
		this.deductReasons = deductReasons;
	}
	public Double getPointScore() {
		return pointScore;
	}
	public void setPointScore(Double pointScore) {
		this.pointScore = pointScore;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
	}
	public String getEvalObjectName() {
		return evalObjectName;
	}
	public void setEvalObjectName(String evalObjectName) {
		this.evalObjectName = evalObjectName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getEvalObjectType() {
		return evalObjectType;
	}
	public void setEvalObjectType(String evalObjectType) {
		this.evalObjectType = evalObjectType;
	}
	public Date getEvalTime() {
		return evalTime;
	}
	public void setEvalTime(Date evalTime) {
		this.evalTime = evalTime;
	}
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getName2() {
		return Name2;
	}
	public void setName2(String name2) {
		Name2 = name2;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getProportion2() {
		return proportion2;
	}
	public void setProportion2(String proportion2) {
		this.proportion2 = proportion2;
	}
	public String getName3() {
		return Name3;
	}
	public void setName3(String name3) {
		Name3 = name3;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getProportion3() {
		return proportion3;
	}
	public void setProportion3(String proportion3) {
		this.proportion3 = proportion3;
	}
	public String getName4() {
		return Name4;
	}
	public void setName4(String name4) {
		Name4 = name4;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getProportion4() {
		return proportion4;
	}
	public void setProportion4(String proportion4) {
		this.proportion4 = proportion4;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Double getBonuspoints() {
		return bonuspoints;
	}
	public void setBonuspoints(Double bonuspoints) {
		this.bonuspoints = bonuspoints;
	}
	public String getReasonsforbonus() {
		return reasonsforbonus;
	}
	public void setReasonsforbonus(String reasonsforbonus) {
		this.reasonsforbonus = reasonsforbonus;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getIsGrade() {
		return isGrade;
	}
	public void setIsGrade(String isGrade) {
		this.isGrade = isGrade;
	}
	@Override
	public String toString() {
		return "DataUpload [tableId=" + tableId + ", itemId=" + itemId
				+ ", pointId=" + pointId + ", instanceId=" + instanceId
				+ ", proportion1=" + proportion1 + ", value1=" + value1
				+ ", name1=" + name1 + ", deductReasons=" + deductReasons
				+ ", pointScore=" + pointScore + ", pointName=" + pointName
				+ ", isAuto=" + isAuto + ", evalObjectName=" + evalObjectName
				+ ", tableName=" + tableName + ", evalObjectType="
				+ evalObjectType + ", evalTime=" + evalTime + ", totalValue="
				+ totalValue + ", objectId=" + objectId + ", parentId="
				+ parentId + ", code=" + code + ", Name2=" + Name2
				+ ", value2=" + value2 + ", proportion2=" + proportion2
				+ ", Name3=" + Name3 + ", value3=" + value3 + ", proportion3="
				+ proportion3 + ", Name4=" + Name4 + ", value4=" + value4
				+ ", proportion4=" + proportion4 + "]";
	}
	
	
}
