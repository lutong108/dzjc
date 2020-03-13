package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效附件表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.EvalEnclosure", table = "TA_JC_EVAL_ENCLOSURE", ds = "acceptdata", cache = false)
public class EvalEnclosure {
	/**
	 *ID号
	 */
	@Column(id = "enclosure_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String enclosureId;

	/**
	 *附件路径
	 */
	@Column(id = "path_url", datatype = "string128")
	private java.lang.String pathUrl;

	/**
	 *考核对象名称
	 */
	@Column(id = "evalobject_name", datatype = "string64")
	private java.lang.String evalobjectName;

	/**
	 *上传时间
	 */
	@Column(id = "upload_time", datatype = "date")
	private java.sql.Date uploadTime;

	/**
	 *附件绑定ID
	 */
	@Column(id = "id", datatype = "string64")
	private java.lang.String id;

	/**
	 *是否显示 Y显示 N不显示
	 */
	@Column(id = "is_show", datatype = "string64")
	private java.lang.String isShow;

	/**
	 * 设置ID号
	 */
	public void setEnclosureId(java.lang.String enclosureId) {
		this.enclosureId = enclosureId;
	}

	/**
	 * 获取ID号
	 */
	public java.lang.String getEnclosureId() {
		return enclosureId;
	}

	/**
	 * 设置附件路径
	 */
	public void setPathUrl(java.lang.String pathUrl) {
		this.pathUrl = pathUrl;
	}

	/**
	 * 获取附件路径
	 */
	public java.lang.String getPathUrl() {
		return pathUrl;
	}

	/**
	 * 设置考核对象名称
	 */
	public void setEvalobjectName(java.lang.String evalobjectName) {
		this.evalobjectName = evalobjectName;
	}

	/**
	 * 获取考核对象名称
	 */
	public java.lang.String getEvalobjectName() {
		return evalobjectName;
	}

	/**
	 * 设置上传时间
	 */
	public void setUploadTime(java.sql.Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * 获取上传时间
	 */
	public java.sql.Date getUploadTime() {
		return uploadTime;
	}

	/**
	 * 设置附件绑定ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取附件绑定ID
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置是否显示 Y显示 N不显示
	 */
	public void setIsShow(java.lang.String isShow) {
		this.isShow = isShow;
	}

	/**
	 * 获取是否显示 Y显示 N不显示
	 */
	public java.lang.String getIsShow() {
		return isShow;
	}
}
