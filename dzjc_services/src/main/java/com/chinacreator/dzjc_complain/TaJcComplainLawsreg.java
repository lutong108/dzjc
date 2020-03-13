package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 法律法规
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.taJcComplainLawsreg", table = "TA_COMPLAIN_LAWSREG", ds = "acceptdata", cache = false)
public class TaJcComplainLawsreg {
	/**
	 *主键ID
	 */
	@Column(id = "laws_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String lawsId;

	/**
	 *法律法规内容
	 */
	@Column(id = "laws_content", datatype = "smallclob")
	private java.lang.String lawsContent;

	/**
	 *创建时间
	 */
	@Column(id = "create_time", datatype = "date", sort = SortType.asc)
	private java.sql.Date createTime;

	/**
	 *最后修改时间
	 */
	@Column(id = "lastupdatetime", datatype = "date")
	private java.sql.Date lastupdatetime;

	/**
	 *创建人
	 */
	@Column(id = "create_user", datatype = "string64")
	private java.lang.String createUser;

	/**
	 *法律法规标题
	 */
	@Column(id = "laws_title", datatype = "string256")
	private java.lang.String lawsTitle;

	/**
	 *机构ID
	 */
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	/**
	 *栏目类别(TA_DIC_column_category)
	 */
	@Column(id = "column_code", datatype = "string5")
	private java.lang.String columnCode;

	/**
	 *是否公开' Y'  是  ‘N’ 否
	 */
	@Column(id = "is_public", datatype = "string5")
	private java.lang.String isPublic;

	/**
	 *内容简介
	 */
	@Column(id = "content_validity", datatype = "string2000")
	private java.lang.String contentValidity;

	/**
	 *副标题
	 */
	@Column(id = "short_title", datatype = "string256")
	private java.lang.String shortTitle;

	/**
	 * 设置主键ID
	 */
	public void setLawsId(java.lang.String lawsId) {
		this.lawsId = lawsId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getLawsId() {
		return lawsId;
	}

	/**
	 * 设置法律法规内容
	 */
	public void setLawsContent(java.lang.String lawsContent) {
		this.lawsContent = lawsContent;
	}

	/**
	 * 获取法律法规内容
	 */
	public java.lang.String getLawsContent() {
		return lawsContent;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(java.sql.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建时间
	 */
	public java.sql.Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置最后修改时间
	 */
	public void setLastupdatetime(java.sql.Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	/**
	 * 获取最后修改时间
	 */
	public java.sql.Date getLastupdatetime() {
		return lastupdatetime;
	}

	/**
	 * 设置创建人
	 */
	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取创建人
	 */
	public java.lang.String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置法律法规标题
	 */
	public void setLawsTitle(java.lang.String lawsTitle) {
		this.lawsTitle = lawsTitle;
	}

	/**
	 * 获取法律法规标题
	 */
	public java.lang.String getLawsTitle() {
		return lawsTitle;
	}

	/**
	 * 设置机构ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置栏目类别(TA_DIC_column_category)
	 */
	public void setColumnCode(java.lang.String columnCode) {
		this.columnCode = columnCode;
	}

	/**
	 * 获取栏目类别(TA_DIC_column_category)
	 */
	public java.lang.String getColumnCode() {
		return columnCode;
	}

	/**
	 * 设置是否公开' Y'  是  ‘N’ 否
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取是否公开' Y'  是  ‘N’ 否
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置内容简介
	 */
	public void setContentValidity(java.lang.String contentValidity) {
		this.contentValidity = contentValidity;
	}

	/**
	 * 获取内容简介
	 */
	public java.lang.String getContentValidity() {
		return contentValidity;
	}

	/**
	 * 设置副标题
	 */
	public void setShortTitle(java.lang.String shortTitle) {
		this.shortTitle = shortTitle;
	}

	/**
	 * 获取副标题
	 */
	public java.lang.String getShortTitle() {
		return shortTitle;
	}
}
