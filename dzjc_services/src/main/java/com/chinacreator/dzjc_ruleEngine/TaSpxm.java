package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 行政审批事项规范
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.TaSpxm", table = "TA_SPXM", ds = "acceptdata")
public class TaSpxm implements Serializable {
	private static final long serialVersionUID = 2626495519948800L;
	/**
	 *通过序列SEQ_SPXM取值，从其他系统交换过来的数据保持原值不变，其形式为行政区划代码+序列值
	 */
	@Column(id = "approve_id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String approveId;

	/**
	 *所属单位名称
	 */
	@Column(id = "org_name", datatype = "string128")
	private java.lang.String orgName;

	/**
	 *审批事项名称
	 */
	@Column(id = "approve_name", datatype = "string512")
	private java.lang.String approveName;

	/**
	 *审批事项编码
	 */
	@Column(id = "approve_code", datatype = "string64")
	private java.lang.String approveCode;

	/**
	 *事项类型；0：即办件，1：承诺件，2：上报件，3：并联审批件
	 */
	@Column(id = "trans_type_code", datatype = "char2")
	private java.lang.String transTypeCode;

	/**
	 *事项类别；1：行政许可事项，2：非行政许可事项，9：便民服务事项
	 */
	@Column(id = "approve_type_code", datatype = "char2")
	private java.lang.String approveTypeCode;

	/**
	 *审批时限
	 */
	@Column(id = "time_limit", datatype = "int")
	private java.lang.Integer timeLimit;

	/**
	 *颁证状态；1：颁证，0：不颁证
	 */
	@Column(id = "is_certificate", datatype = "boolean")
	private java.lang.Boolean isCertificate;

	/**
	 *收费状态；Y：收费，N：不收费
	 */
	@Column(id = "is_charge", datatype = "boolean")
	private java.lang.Boolean isCharge;

	/**
	 *状态；Y：在用，N：未用
	 */
	@Column(id = "is_available", datatype = "string64")
	private java.lang.String isAvailable;

	/**
	 *备注
	 */
	@Column(id = "remark", datatype = "string1024")
	private java.lang.String remark;

	/**
	 *最后修改时间
	 */
	@Column(id = "last_updatetime", datatype = "date")
	private java.sql.Date lastUpdatetime;

	/**
	 *数据来源
	 */
	@Column(id = "data_source", datatype = "string64")
	private java.lang.String dataSource;

	/**
	 *版本号，新增时为1，如果修改的版本为2、3、4.......
	 */
	@Column(id = "version", datatype = "tinyint")
	private java.lang.Integer version;

	/**
	 *创建时间
	 */
	@Column(id = "create_date", datatype = "date")
	private java.sql.Date createDate;

	/**
	 *承诺时限
	 */
	@Column(id = "promises_limit", datatype = "int")
	private java.lang.Integer promisesLimit;

	/**
	 *组织机构ID
	 */
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	/**
	 *所属单位编码
	 */
	@Column(id = "org_code", datatype = "string128")
	private java.lang.String orgCode;

	/**
	 *业务类别
	 */
	@Column(id = "type_name", datatype = "string64")
	private java.lang.String typeName;

	/**
	 * 设置通过序列SEQ_SPXM取值，从其他系统交换过来的数据保持原值不变，其形式为行政区划代码+序列值
	 */
	public void setApproveId(java.lang.String approveId) {
		this.approveId = approveId;
	}

	/**
	 * 获取通过序列SEQ_SPXM取值，从其他系统交换过来的数据保持原值不变，其形式为行政区划代码+序列值
	 */
	public java.lang.String getApproveId() {
		return approveId;
	}

	/**
	 * 设置所属单位名称
	 */
	public void setOrgName(java.lang.String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 获取所属单位名称
	 */
	public java.lang.String getOrgName() {
		return orgName;
	}

	/**
	 * 设置审批事项名称
	 */
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	 * 获取审批事项名称
	 */
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	 * 设置审批事项编码
	 */
	public void setApproveCode(java.lang.String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 获取审批事项编码
	 */
	public java.lang.String getApproveCode() {
		return approveCode;
	}

	/**
	 * 设置事项类型；0：即办件，1：承诺件，2：上报件，3：并联审批件
	 */
	public void setTransTypeCode(java.lang.String transTypeCode) {
		this.transTypeCode = transTypeCode;
	}

	/**
	 * 获取事项类型；0：即办件，1：承诺件，2：上报件，3：并联审批件
	 */
	public java.lang.String getTransTypeCode() {
		return transTypeCode;
	}

	/**
	 * 设置事项类别；1：行政许可事项，2：非行政许可事项，9：便民服务事项
	 */
	public void setApproveTypeCode(java.lang.String approveTypeCode) {
		this.approveTypeCode = approveTypeCode;
	}

	/**
	 * 获取事项类别；1：行政许可事项，2：非行政许可事项，9：便民服务事项
	 */
	public java.lang.String getApproveTypeCode() {
		return approveTypeCode;
	}

	/**
	 * 设置审批时限
	 */
	public void setTimeLimit(java.lang.Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 获取审批时限
	 */
	public java.lang.Integer getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 设置颁证状态；1：颁证，0：不颁证
	 */
	public void setIsCertificate(java.lang.Boolean isCertificate) {
		this.isCertificate = isCertificate;
	}

	/**
	 * 获取颁证状态；1：颁证，0：不颁证
	 */
	public java.lang.Boolean isIsCertificate() {
		return isCertificate;
	}

	/**
	 * 设置收费状态；Y：收费，N：不收费
	 */
	public void setIsCharge(java.lang.Boolean isCharge) {
		this.isCharge = isCharge;
	}

	/**
	 * 获取收费状态；Y：收费，N：不收费
	 */
	public java.lang.Boolean isIsCharge() {
		return isCharge;
	}

	/**
	 * 设置状态；Y：在用，N：未用
	 */
	public void setIsAvailable(java.lang.String isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * 获取状态；Y：在用，N：未用
	 */
	public java.lang.String getIsAvailable() {
		return isAvailable;
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
	 * 设置最后修改时间
	 */
	public void setLastUpdatetime(java.sql.Date lastUpdatetime) {
		this.lastUpdatetime = lastUpdatetime;
	}

	/**
	 * 获取最后修改时间
	 */
	public java.sql.Date getLastUpdatetime() {
		return lastUpdatetime;
	}

	/**
	 * 设置数据来源
	 */
	public void setDataSource(java.lang.String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 获取数据来源
	 */
	public java.lang.String getDataSource() {
		return dataSource;
	}

	/**
	 * 设置版本号，新增时为1，如果修改的版本为2、3、4.......
	 */
	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	/**
	 * 获取版本号，新增时为1，如果修改的版本为2、3、4.......
	 */
	public java.lang.Integer getVersion() {
		return version;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取创建时间
	 */
	public java.sql.Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置承诺时限
	 */
	public void setPromisesLimit(java.lang.Integer promisesLimit) {
		this.promisesLimit = promisesLimit;
	}

	/**
	 * 获取承诺时限
	 */
	public java.lang.Integer getPromisesLimit() {
		return promisesLimit;
	}

	/**
	 * 设置组织机构ID
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取组织机构ID
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置所属单位编码
	 */
	public void setOrgCode(java.lang.String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 获取所属单位编码
	 */
	public java.lang.String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置业务类别
	 */
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取业务类别
	 */
	public java.lang.String getTypeName() {
		return typeName;
	}
}
