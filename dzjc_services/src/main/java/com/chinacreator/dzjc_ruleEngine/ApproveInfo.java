package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 事项信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ApproveInfo", table = "TA_SP_APPROVE_INFO", ds = "acceptdata", cache = false)
public class ApproveInfo {
	/**
	 *基础编码
	 */
	@Column(id = "rights_code", datatype = "string64")
	private java.lang.String rightsCode;

	/**
	 *目录ID
	 */
	@Column(id = "rights_id", datatype = "string64")
	private java.lang.String rightsId;

	/**
	 *事项ID
	 */
	@Column(id = "approve_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String approveId;

	/**
	 *实施编码
	 */
	@Column(id = "approve_code", datatype = "string64")
	private java.lang.String approveCode;

	/**
	 *父基础编码
	 */
	@Column(id = "parent_code", datatype = "string64")
	private java.lang.String parentCode;

	/**
	 *父事项名称
	 */
	@Column(id = "parent_name", datatype = "string512")
	private java.lang.String parentName;

	/**
	 *事项名称
	 */
	@Column(id = "approve_name", datatype = "string1024")
	private java.lang.String approveName;

	/**
	 *区域编码
	 */
	@Column(id = "area_code", datatype = "string64")
	private java.lang.String areaCode;

	/**
	 *所属部门
	 */
	@Column(id = "org_id", datatype = "string64")
	private java.lang.String orgId;

	/**
	 *部门名称
	 */
	@Column(id = "org_name", datatype = "string64")
	private java.lang.String orgName;

	/**
	 *事项类型
	 */
	@Column(id = "type_code", datatype = "string5")
	private java.lang.String typeCode;

	/**
	 *办件类型(1，承诺件；2，即办件；3，转报件；4，上报件；
	 */
	@Column(id = "trans_type", datatype = "string5")
	private java.lang.String transType;

	/**
	 *事项来源（取字典数据）
	 */
	@Column(id = "approve_source", datatype = "string5")
	private java.lang.String approveSource;

	/**
	 *事项性质（取字典数据）
	 */
	@Column(id = "approve_properties", datatype = "string5")
	private java.lang.String approveProperties;

	/**
	 *办理深度（取字典数据）
	 */
	@Column(id = "transact_level", datatype = "string5")
	private java.lang.String transactLevel;

	/**
	 *最少次数
	 */
	@Column(id = "min_seq", datatype = "bigdouble")
	private java.lang.Double minSeq;

	/**
	 *标准说明
	 */
	@Column(id = "min_explain", datatype = "string2000")
	private java.lang.String minExplain;

	/**
	 *服务对象（取字典数据）
	 */
	@Column(id = "serve_object", datatype = "string5")
	private java.lang.String serveObject;

	/**
	 *法人分类
	 */
	@Column(id = "theme_type", datatype = "string256")
	private java.lang.String themeType;

	/**
	 *（取字典数据）生命周期分类
	 */
	@Column(id = "life_concern", datatype = "string5")
	private java.lang.String lifeConcern;

	/**
	 *是否为自有系统办理
	 */
	@Column(id = "is_self_system", datatype = "string5")
	private java.lang.String isSelfSystem;

	/**
	 *是否批量操作
	 */
	@Column(id = "is_batch", datatype = "string5")
	private java.lang.String isBatch;

	/**
	 *是否存在特殊程序
	 */
	@Column(id = "is_specific", datatype = "string5")
	private java.lang.String isSpecific;

	/**
	 *是否有常见问题
	 */
	@Column(id = "is_faq", datatype = "string5")
	private java.lang.String isFaq;

	/**
	 *是否系统出证
	 */
	@Column(id = "is_self_certificate", datatype = "string5")
	private java.lang.String isSelfCertificate;

	/**
	 *是否外网咨询
	 */
	@Column(id = "is_web_consult", datatype = "string5")
	private java.lang.String isWebConsult;

	/**
	 *是否协同办理事项
	 */
	@Column(id = "is_synergy", datatype = "string5")
	private java.lang.String isSynergy;

	/**
	 *是否为政府投资项目
	 */
	@Column(id = "is_government_project", datatype = "string5")
	private java.lang.String isGovernmentProject;

	/**
	 *事项状态（取字典数据）
	 */
	@Column(id = "approve_state", datatype = "string5")
	private java.lang.String approveState;

	/**
	 *是否统一受理
	 */
	@Column(id = "is_unify_accept", datatype = "string5")
	private java.lang.String isUnifyAccept;

	/**
	 *是否同城通办
	 */
	@Column(id = "is_city_open_do", datatype = "string5")
	private java.lang.String isCityOpenDo;

	/**
	 *是否异地可办
	 */
	@Column(id = "is_elsewhere_do", datatype = "string5")
	private java.lang.String isElsewhereDo;

	/**
	 *最近一次修改的时间
	 */
	@Column(id = "lastupdate_time", datatype = "date")
	private java.sql.Date lastupdateTime;

	/**
	 *版本号
	 */
	@Column(id = "version_num", datatype = "bigdouble")
	private java.lang.Double versionNum;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string512")
	private java.lang.String remark;

	/**
	 *是否收费
	 */
	@Column(id = "is_charge", datatype = "string5")
	private java.lang.String isCharge;

	/**
	 *是否证照寄送
	 */
	@Column(id = "is_send", datatype = "string5")
	private java.lang.String isSend;

	/**
	 *是否中介服务事项
	 */
	@Column(id = "is_intermediary_services", datatype = "string5")
	private java.lang.String isIntermediaryServices;

	/**
	 *中介服务事项名称
	 */
	@Column(id = "intermediary_services", datatype = "string512")
	private java.lang.String intermediaryServices;

	/**
	 *中介服务事项ID
	 */
	@Column(id = "services_id", datatype = "string64")
	private java.lang.String servicesId;

	/**
	 *是否有效
	 */
	@Column(id = "is_valid", datatype = "string5")
	private java.lang.String isValid;

	/**
	 *是否进驻窗口
	 */
	@Column(id = "is_inwindow", datatype = "string5")
	private java.lang.String isInwindow;

	/**
	 *自然人分类
	 */
	@Column(id = "naturalperson_type", datatype = "string256")
	private java.lang.String naturalpersonType;

	/**
	 *认证类型（0；无需认证；1；实名认证；2；实人认证）
	 */
	@Column(id = "certification_type", datatype = "string5")
	private java.lang.String certificationType;

	/**
	 *场景分类（取字典数据）
	 */
	@Column(id = "scene_type", datatype = "string5")
	private java.lang.String sceneType;

	/**
	 *在线申办地址
	 */
	@Column(id = "onlinebid_address", datatype = "string512")
	private java.lang.String onlinebidAddress;

	/**
	 *是否最多跑一次事项
	 */
	@Column(id = "is_most_runonce", datatype = "string5")
	private java.lang.String isMostRunonce;

	/**
	 * 设置基础编码
	 */
	public void setRightsCode(java.lang.String rightsCode) {
		this.rightsCode = rightsCode;
	}

	/**
	 * 获取基础编码
	 */
	public java.lang.String getRightsCode() {
		return rightsCode;
	}

	/**
	 * 设置目录ID
	 */
	public void setRightsId(java.lang.String rightsId) {
		this.rightsId = rightsId;
	}

	/**
	 * 获取目录ID
	 */
	public java.lang.String getRightsId() {
		return rightsId;
	}

	/**
	 * 设置事项ID
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取事项ID
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置实施编码
	 */
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 获取实施编码
	 */
	public java.lang.String getApproveCode() {
		return approveCode;
	}

	/**
	 * 设置父基础编码
	 */
	public void setParentCode(java.lang.String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * 获取父基础编码
	 */
	public java.lang.String getParentCode() {
		return parentCode;
	}

	/**
	 * 设置父事项名称
	 */
	public void setParentName(java.lang.String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 获取父事项名称
	 */
	public java.lang.String getParentName() {
		return parentName;
	}

	/**
	 * 设置事项名称
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取事项名称
	 */
	public java.lang.String getApproveName() {
		return approveName;
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

	/**
	 * 设置所属部门
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取所属部门
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置部门名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取部门名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置事项类型
	 */
	public void setTypeCode(java.lang.String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * 获取事项类型
	 */
	public java.lang.String getTypeCode() {
		return typeCode;
	}

	/**
	 * 设置办件类型(1，承诺件；2，即办件；3，转报件；4，上报件；
	 */
	public void setTransType(java.lang.String transType) {
		this.transType = transType;
	}

	/**
	 * 获取办件类型(1，承诺件；2，即办件；3，转报件；4，上报件；
	 */
	public java.lang.String getTransType() {
		return transType;
	}

	/**
	 * 设置事项来源（取字典数据）
	 */
	public void setApproveSource(java.lang.String approveSource) {
		this.approveSource = approveSource;
	}

	/**
	 * 获取事项来源（取字典数据）
	 */
	public java.lang.String getApproveSource() {
		return approveSource;
	}

	/**
	 * 设置事项性质（取字典数据）
	 */
	public void setApproveProperties(java.lang.String approveProperties) {
		this.approveProperties = approveProperties;
	}

	/**
	 * 获取事项性质（取字典数据）
	 */
	public java.lang.String getApproveProperties() {
		return approveProperties;
	}

	/**
	 * 设置办理深度（取字典数据）
	 */
	public void setTransactLevel(java.lang.String transactLevel) {
		this.transactLevel = transactLevel;
	}

	/**
	 * 获取办理深度（取字典数据）
	 */
	public java.lang.String getTransactLevel() {
		return transactLevel;
	}

	/**
	 * 设置最少次数
	 */
	public void setMinSeq(java.lang.Double minSeq) {
		this.minSeq = minSeq;
	}

	/**
	 * 获取最少次数
	 */
	public java.lang.Double getMinSeq() {
		return minSeq;
	}

	/**
	 * 设置标准说明
	 */
	public void setMinExplain(java.lang.String minExplain) {
		this.minExplain = minExplain;
	}

	/**
	 * 获取标准说明
	 */
	public java.lang.String getMinExplain() {
		return minExplain;
	}

	/**
	 * 设置服务对象（取字典数据）
	 */
	public void setServeObject(java.lang.String serveObject) {
		this.serveObject = serveObject;
	}

	/**
	 * 获取服务对象（取字典数据）
	 */
	public java.lang.String getServeObject() {
		return serveObject;
	}

	/**
	 * 设置法人分类
	 */
	public void setThemeType(java.lang.String themeType) {
		this.themeType = themeType;
	}

	/**
	 * 获取法人分类
	 */
	public java.lang.String getThemeType() {
		return themeType;
	}

	/**
	 * 设置（取字典数据）生命周期分类
	 */
	public void setLifeConcern(java.lang.String lifeConcern) {
		this.lifeConcern = lifeConcern;
	}

	/**
	 * 获取（取字典数据）生命周期分类
	 */
	public java.lang.String getLifeConcern() {
		return lifeConcern;
	}

	/**
	 * 设置是否为自有系统办理
	 */
	public void setIsSelfSystem(java.lang.String isSelfSystem) {
		this.isSelfSystem = isSelfSystem;
	}

	/**
	 * 获取是否为自有系统办理
	 */
	public java.lang.String getIsSelfSystem() {
		return isSelfSystem;
	}

	/**
	 * 设置是否批量操作
	 */
	public void setIsBatch(java.lang.String isBatch) {
		this.isBatch = isBatch;
	}

	/**
	 * 获取是否批量操作
	 */
	public java.lang.String getIsBatch() {
		return isBatch;
	}

	/**
	 * 设置是否存在特殊程序
	 */
	public void setIsSpecific(java.lang.String isSpecific) {
		this.isSpecific = isSpecific;
	}

	/**
	 * 获取是否存在特殊程序
	 */
	public java.lang.String getIsSpecific() {
		return isSpecific;
	}

	/**
	 * 设置是否有常见问题
	 */
	public void setIsFaq(java.lang.String isFaq) {
		this.isFaq = isFaq;
	}

	/**
	 * 获取是否有常见问题
	 */
	public java.lang.String getIsFaq() {
		return isFaq;
	}

	/**
	 * 设置是否系统出证
	 */
	public void setIsSelfCertificate(java.lang.String isSelfCertificate) {
		this.isSelfCertificate = isSelfCertificate;
	}

	/**
	 * 获取是否系统出证
	 */
	public java.lang.String getIsSelfCertificate() {
		return isSelfCertificate;
	}

	/**
	 * 设置是否外网咨询
	 */
	public void setIsWebConsult(java.lang.String isWebConsult) {
		this.isWebConsult = isWebConsult;
	}

	/**
	 * 获取是否外网咨询
	 */
	public java.lang.String getIsWebConsult() {
		return isWebConsult;
	}

	/**
	 * 设置是否协同办理事项
	 */
	public void setIsSynergy(java.lang.String isSynergy) {
		this.isSynergy = isSynergy;
	}

	/**
	 * 获取是否协同办理事项
	 */
	public java.lang.String getIsSynergy() {
		return isSynergy;
	}

	/**
	 * 设置是否为政府投资项目
	 */
	public void setIsGovernmentProject(java.lang.String isGovernmentProject) {
		this.isGovernmentProject = isGovernmentProject;
	}

	/**
	 * 获取是否为政府投资项目
	 */
	public java.lang.String getIsGovernmentProject() {
		return isGovernmentProject;
	}

	/**
	 * 设置事项状态（取字典数据）
	 */
	public void setApproveState(java.lang.String approveState) {
		this.approveState = approveState;
	}

	/**
	 * 获取事项状态（取字典数据）
	 */
	public java.lang.String getApproveState() {
		return approveState;
	}

	/**
	 * 设置是否统一受理
	 */
	public void setIsUnifyAccept(java.lang.String isUnifyAccept) {
		this.isUnifyAccept = isUnifyAccept;
	}

	/**
	 * 获取是否统一受理
	 */
	public java.lang.String getIsUnifyAccept() {
		return isUnifyAccept;
	}

	/**
	 * 设置是否同城通办
	 */
	public void setIsCityOpenDo(java.lang.String isCityOpenDo) {
		this.isCityOpenDo = isCityOpenDo;
	}

	/**
	 * 获取是否同城通办
	 */
	public java.lang.String getIsCityOpenDo() {
		return isCityOpenDo;
	}

	/**
	 * 设置是否异地可办
	 */
	public void setIsElsewhereDo(java.lang.String isElsewhereDo) {
		this.isElsewhereDo = isElsewhereDo;
	}

	/**
	 * 获取是否异地可办
	 */
	public java.lang.String getIsElsewhereDo() {
		return isElsewhereDo;
	}

	/**
	 * 设置最近一次修改的时间
	 */
	public void setLastupdateTime(java.sql.Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}

	/**
	 * 获取最近一次修改的时间
	 */
	public java.sql.Date getLastupdateTime() {
		return lastupdateTime;
	}

	/**
	 * 设置版本号
	 */
	public void setVersionNum(java.lang.Double versionNum) {
		this.versionNum = versionNum;
	}

	/**
	 * 获取版本号
	 */
	public java.lang.Double getVersionNum() {
		return versionNum;
	}

	/**
	 * 设置备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 * 获取备注
	 */
	public java.lang.String getRemark() {
		return remark;
	}

	/**
	 * 设置是否收费
	 */
	public void setIsCharge(java.lang.String isCharge) {
		this.isCharge = isCharge;
	}

	/**
	 * 获取是否收费
	 */
	public java.lang.String getIsCharge() {
		return isCharge;
	}

	/**
	 * 设置是否证照寄送
	 */
	public void setIsSend(java.lang.String isSend) {
		this.isSend = isSend;
	}

	/**
	 * 获取是否证照寄送
	 */
	public java.lang.String getIsSend() {
		return isSend;
	}

	/**
	 * 设置是否中介服务事项
	 */
	public void setIsIntermediaryServices(
			java.lang.String isIntermediaryServices) {
		this.isIntermediaryServices = isIntermediaryServices;
	}

	/**
	 * 获取是否中介服务事项
	 */
	public java.lang.String getIsIntermediaryServices() {
		return isIntermediaryServices;
	}

	/**
	 * 设置中介服务事项名称
	 */
	public void setIntermediaryServices(java.lang.String intermediaryServices) {
		this.intermediaryServices = intermediaryServices;
	}

	/**
	 * 获取中介服务事项名称
	 */
	public java.lang.String getIntermediaryServices() {
		return intermediaryServices;
	}

	/**
	 * 设置中介服务事项ID
	 */
	public void setServicesId(java.lang.String servicesId) {
		this.servicesId = servicesId;
	}

	/**
	 * 获取中介服务事项ID
	 */
	public java.lang.String getServicesId() {
		return servicesId;
	}

	/**
	 * 设置是否有效
	 */
	public void setIsValid(java.lang.String isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效
	 */
	public java.lang.String getIsValid() {
		return isValid;
	}

	/**
	 * 设置是否进驻窗口
	 */
	public void setIsInwindow(java.lang.String isInwindow) {
		this.isInwindow = isInwindow;
	}

	/**
	 * 获取是否进驻窗口
	 */
	public java.lang.String getIsInwindow() {
		return isInwindow;
	}

	/**
	 * 设置自然人分类
	 */
	public void setNaturalpersonType(java.lang.String naturalpersonType) {
		this.naturalpersonType = naturalpersonType;
	}

	/**
	 * 获取自然人分类
	 */
	public java.lang.String getNaturalpersonType() {
		return naturalpersonType;
	}

	/**
	 * 设置认证类型（0；无需认证；1；实名认证；2；实人认证）
	 */
	public void setCertificationType(java.lang.String certificationType) {
		this.certificationType = certificationType;
	}

	/**
	 * 获取认证类型（0；无需认证；1；实名认证；2；实人认证）
	 */
	public java.lang.String getCertificationType() {
		return certificationType;
	}

	/**
	 * 设置场景分类（取字典数据）
	 */
	public void setSceneType(java.lang.String sceneType) {
		this.sceneType = sceneType;
	}

	/**
	 * 获取场景分类（取字典数据）
	 */
	public java.lang.String getSceneType() {
		return sceneType;
	}

	/**
	 * 设置在线申办地址
	 */
	public void setOnlinebidAddress(java.lang.String onlinebidAddress) {
		this.onlinebidAddress = onlinebidAddress;
	}

	/**
	 * 获取在线申办地址
	 */
	public java.lang.String getOnlinebidAddress() {
		return onlinebidAddress;
	}

	/**
	 * 设置是否最多跑一次事项
	 */
	public void setIsMostRunonce(java.lang.String isMostRunonce) {
		this.isMostRunonce = isMostRunonce;
	}

	/**
	 * 获取是否最多跑一次事项
	 */
	public java.lang.String getIsMostRunonce() {
		return isMostRunonce;
	}
}
