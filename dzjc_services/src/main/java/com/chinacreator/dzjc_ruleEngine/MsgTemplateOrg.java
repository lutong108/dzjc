package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 机构消息模板
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.MsgTemplateOrg", table = "TA_JC_MSG_TEMPLATE_ORG", ds = "acceptdata")
public class MsgTemplateOrg implements Serializable {
	private static final long serialVersionUID = 3047819548573696L;
	/**
	 *模版ID
	 */
	@Column(id = "template_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String templateId;

	/**
	 *管理员类型（0 - 区域管理员 ， 1 - 单位管理员）
	 */
	@Column(id = "template_type", datatype = "char1")
	private java.lang.String templateType;

	/**
	 *模板名称
	 */
	@Column(id = "template_name", datatype = "string128")
	private java.lang.String templateName;

	/**
	 *模板内容
	 */
	@Column(id = "template_content", datatype = "string512")
	private java.lang.String templateContent;

	/**
	 *模板描述
	 */
	@Column(id = "template_desc", datatype = "string512")
	private java.lang.String templateDesc;

	/**
	 *是否有效（Y-有效，N-无效）
	 */
	@Column(id = "is_valid", datatype = "string64")
	private java.lang.String isValid;

	/**
	 *平台实例编号
	 */
	@Column(id = "cc_form_instanceid", datatype = "string64")
	private java.lang.String ccFormInstanceid;

	/**
	 *是否通用  （Y-通用性 ，N-个性化）
	 */
	@Column(id = "is_public", datatype = "string64")
	private java.lang.String isPublic;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 * 设置模版ID
	 */
	public void setTemplateId(java.lang.String templateId) {
		this.templateId = templateId;
	}

	/**
	 * 获取模版ID
	 */
	public java.lang.String getTemplateId() {
		return templateId;
	}

	/**
	 * 设置管理员类型（0 - 区域管理员 ， 1 - 单位管理员）
	 */
	public void setTemplateType(java.lang.String templateType) {
		this.templateType = templateType;
	}

	/**
	 * 获取管理员类型（0 - 区域管理员 ， 1 - 单位管理员）
	 */
	public java.lang.String getTemplateType() {
		return templateType;
	}

	/**
	 * 设置模板名称
	 */
	public void setTemplateName(java.lang.String templateName) {
		this.templateName = templateName;
	}

	/**
	 * 获取模板名称
	 */
	public java.lang.String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置模板内容
	 */
	public void setTemplateContent(java.lang.String templateContent) {
		this.templateContent = templateContent;
	}

	/**
	 * 获取模板内容
	 */
	public java.lang.String getTemplateContent() {
		return templateContent;
	}

	/**
	 * 设置模板描述
	 */
	public void setTemplateDesc(java.lang.String templateDesc) {
		this.templateDesc = templateDesc;
	}

	/**
	 * 获取模板描述
	 */
	public java.lang.String getTemplateDesc() {
		return templateDesc;
	}

	/**
	 * 设置是否有效（Y-有效，N-无效）
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效（Y-有效，N-无效）
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置平台实例编号
	 */
	public void setCcFormInstanceid(java.lang.String ccFormInstanceid) {
		this.ccFormInstanceid = ccFormInstanceid;
	}

	/**
	 * 获取平台实例编号
	 */
	public java.lang.String getCcFormInstanceid() {
		return ccFormInstanceid;
	}

	/**
	 * 设置是否通用  （Y-通用性 ，N-个性化）
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取是否通用  （Y-通用性 ，N-个性化）
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置区域编码
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 获取区域编码
	 */
	public java.lang.String getAreaCode() {
		return areaCode;
	}
}
