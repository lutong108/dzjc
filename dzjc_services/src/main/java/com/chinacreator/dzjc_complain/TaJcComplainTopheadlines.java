package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 首页滚动图片
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.taJcComplainTopheadlines", table = "TA_COMPLAIN_TOPHEADLINES", ds = "acceptdata", cache = false)
public class TaJcComplainTopheadlines {
	/**
	 *主键ID
	 */
	@Column(id = "top_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String topId;

	/**
	 *图片内容
	 */
	@Column(id = "top_content", datatype = "smallclob")
	private java.lang.String topContent;

	/**
	 *图片路径
	 */
	@Column(id = "top_pic", datatype = "blob")
	private byte[] topPic;

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
	 *标题
	 */
	@Column(id = "top_title", datatype = "string256")
	private java.lang.String topTitle;

	/**
	 *内容简介
	 */
	@Column(id = "content_validity", datatype = "string2000")
	private java.lang.String contentValidity;

	/**
	 *是否发布'Y' 是  'N'  否
	 */
	@Column(id = "is_public", datatype = "string5")
	private java.lang.String isPublic;

	/**
	 * 设置主键ID
	 */
	public void setTopId(java.lang.String topId) {
		this.topId = topId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getTopId() {
		return topId;
	}

	/**
	 * 设置图片内容
	 */
	public void setTopContent(java.lang.String topContent) {
		this.topContent = topContent;
	}

	/**
	 * 获取图片内容
	 */
	public java.lang.String getTopContent() {
		return topContent;
	}

	/**
	 * 设置图片路径
	 */
	public void setTopPic(byte[] topPic) {
		this.topPic = topPic;
	}

	/**
	 * 获取图片路径
	 */
	public byte[] getTopPic() {
		return topPic;
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
	 * 设置标题
	 */
	public void setTopTitle(java.lang.String topTitle) {
		this.topTitle = topTitle;
	}

	/**
	 * 获取标题
	 */
	public java.lang.String getTopTitle() {
		return topTitle;
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
	 * 设置是否发布'Y' 是  'N'  否
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取是否发布'Y' 是  'N'  否
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}
}
