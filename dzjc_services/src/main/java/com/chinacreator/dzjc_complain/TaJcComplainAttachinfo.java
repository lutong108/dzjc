package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;
import com.chinacreator.c2.annotation.SortType;

/**
 * 投诉材料信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.taJcComplainAttachinfo", table = "TA_JC_COMPLAIN_ATTACHINFO", ds = "acceptdata", cache = false)
public class TaJcComplainAttachinfo {
	/**
	 *附件ID
	 */
	@Column(id = "attach_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String attachId;

	/**
	 *投诉信息ID
	 */
	@Column(id = "complain_id", datatype = "string64")
	private java.lang.String complainId;

	/**
	 *附件ID
	 */
	@Column(id = "attach_content", datatype = "smallblob")
	private byte[] attachContent;

	/**
	 *附件名称
	 */
	@Column(id = "attach_name", datatype = "string256")
	private java.lang.String attachName;

	@Column(id = "create_time", datatype = "date", sort = SortType.desc)
	private java.sql.Date createTime;

	/**
	 *附件来源(1.外网   2.内网)
	 */
	@Column(id = "datasource", datatype = "string64")
	private java.lang.String datasource;

	/**
	 *内网附件上传人
	 */
	@Column(id = "creater_user_id", datatype = "string64")
	private java.lang.String createrUserId;

	/**
	 *业务类型
	 */
	@Column(id = "business_type", datatype = "string32")
	private java.lang.String businessType;

	/**
	 * 设置附件ID
	 */
	public void setAttachId(java.lang.String attachId) {
		this.attachId = attachId;
	}

	/**
	 * 获取附件ID
	 */
	public java.lang.String getAttachId() {
		return attachId;
	}

	/**
	 * 设置投诉信息ID
	 */
	public void setComplainId(java.lang.String complainId) {
		this.complainId = complainId;
	}

	/**
	 * 获取投诉信息ID
	 */
	public java.lang.String getComplainId() {
		return complainId;
	}

	/**
	 * 设置附件ID
	 */
	public void setAttachContent(byte[] attachContent) {
		this.attachContent = attachContent;
	}

	/**
	 * 获取附件ID
	 */
	public byte[] getAttachContent() {
		return attachContent;
	}

	/**
	 * 设置附件名称
	 */
	public void setAttachName(java.lang.String attachName) {
		this.attachName = attachName;
	}

	/**
	 * 获取附件名称
	 */
	public java.lang.String getAttachName() {
		return attachName;
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
	 * 设置附件来源(1.外网   2.内网)
	 */
	public void setDatasource(java.lang.String datasource) {
		this.datasource = datasource;
	}

	/**
	 * 获取附件来源(1.外网   2.内网)
	 */
	public java.lang.String getDatasource() {
		return datasource;
	}

	/**
	 * 设置内网附件上传人
	 */
	public void setCreaterUserId(java.lang.String createrUserId) {
		this.createrUserId = createrUserId;
	}

	/**
	 * 获取内网附件上传人
	 */
	public java.lang.String getCreaterUserId() {
		return createrUserId;
	}

	/**
	 * 设置业务类型
	 */
	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	/**
	 * 获取业务类型
	 */
	public java.lang.String getBusinessType() {
		return businessType;
	}
}
