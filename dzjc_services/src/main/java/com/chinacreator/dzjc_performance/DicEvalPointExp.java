package com.chinacreator.dzjc_performance;
/**
 * 绩效考评指标字典扩展类
 * @author 
 *
 */
public class DicEvalPointExp extends DicEvalPoint{
	
	
	private String pointTypeName;
	
	private String areaName;
	
	private Double value;
	
	private String proportion;
	
	public String getPointTypeName() {
		return pointTypeName;
	}

	public void setPointTypeName(String pointTypeName) {
		this.pointTypeName = pointTypeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
	
	
}
