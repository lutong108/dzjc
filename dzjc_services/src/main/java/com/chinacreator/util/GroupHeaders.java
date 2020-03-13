package com.chinacreator.util;

/**
 * 平台表单表头分组
 * @author zyz818
 *
 */
public class GroupHeaders {

	private String startColumnName;
	
	private Integer numberOfColumns;
	
	private String titleText;
	
	private String headName;

	private Integer numberOfStart;
	
	private Integer numberOfEnd;
	
	public String getStartColumnName() {
		return startColumnName;
	}

	public void setStartColumnName(String startColumnName) {
		this.startColumnName = startColumnName;
	}

	public Integer getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(Integer numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public Integer getNumberOfEnd() {
		return numberOfEnd;
	}

	public void setNumberOfEnd(Integer numberOfEnd) {
		this.numberOfEnd = numberOfEnd;
	}

	public Integer getNumberOfStart() {
		return numberOfStart;
	}

	public void setNumberOfStart(Integer numberOfStart) {
		this.numberOfStart = numberOfStart;
	}
}
