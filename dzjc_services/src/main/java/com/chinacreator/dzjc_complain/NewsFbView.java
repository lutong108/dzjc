package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 新闻发布视图
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.NewsFbView", table = "V_NEWS_FB", ds = "acceptdata", cache = false)
public class NewsFbView {
	@Column(id = "laws_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String lawsId;

	@Column(id = "laws_content", datatype = "smallclob")
	private java.lang.String lawsContent;

	@Column(id = "create_time", datatype = "date", sort = SortType.asc)
	private java.sql.Date createTime;

	@Column(id = "create_user", datatype = "string64")
	private java.lang.String createUser;

	@Column(id = "laws_title", datatype = "string256")
	private java.lang.String lawsTitle;

	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	@Column(id = "org_name", datatype = "string2000")
	private java.lang.String orgName;

	@Column(id = "column_category_name", datatype = "string256")
	private java.lang.String columnCategoryName;

	/**
	 * 设置${field.desc}
	 */
	public void setLawsId(java.lang.String lawsId) {
		this.lawsId = lawsId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getLawsId() {
		return lawsId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setLawsContent(java.lang.String lawsContent) {
		this.lawsContent = lawsContent;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getLawsContent() {
		return lawsContent;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCreateTime(java.sql.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setLawsTitle(java.lang.String lawsTitle) {
		this.lawsTitle = lawsTitle;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getLawsTitle() {
		return lawsTitle;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setColumnCategoryName(java.lang.String columnCategoryName) {
		this.columnCategoryName = columnCategoryName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getColumnCategoryName() {
		return columnCategoryName;
	}
}
