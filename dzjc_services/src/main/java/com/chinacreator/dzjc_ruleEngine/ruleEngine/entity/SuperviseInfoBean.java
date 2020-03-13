package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.Entity;

/**
 * 办件实例信息实体
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseInfoBean", table = "TA_SP_INSTANCE", ds = "acceptdata")
public class SuperviseInfoBean implements Serializable {
	private static final long serialVersionUID = 2639083025154048L;
	/**
	 *实例ID
	 */
	@Column(id = "instanceId", datatype = "string64")
	private java.lang.String instanceId;

	/**
	 *监察信息ID
	 */
	@Column(id = "superviseInfoId", datatype = "string64")
	private java.lang.String superviseInfoId;

	/**
	 *监察级别
	 */
	@Column(id = "superviseLevel", datatype = "string64")
	private java.lang.String superviseLevel;

	/**
	 *受理时间
	 */
	@Column(id = "acceptTime", datatype = "string64")
	private java.lang.String acceptTime;

	/**
	 *办结时间
	 */
	@Column(id = "endTime", datatype = "string64")
	private java.lang.String endTime;

	/**
	 *预警反馈是否超期
	 */
	@Column(id = "outEndtime", datatype = "double")
	private java.lang.Double outEndtime;

	/**
	 * 设置实例ID
	 */
	public void setInstanceId(java.lang.String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 获取实例ID
	 */
	public java.lang.String getInstanceId() {
		return instanceId;
	}

	/**
	 * 设置监察信息ID
	 */
	public void setSuperviseInfoId(java.lang.String superviseInfoId) {
		this.superviseInfoId = superviseInfoId;
	}

	/**
	 * 获取监察信息ID
	 */
	public java.lang.String getSuperviseInfoId() {
		return superviseInfoId;
	}

	/**
	 * 设置监察级别
	 */
	public void setSuperviseLevel(java.lang.String superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	/**
	 * 获取监察级别
	 */
	public java.lang.String getSuperviseLevel() {
		return superviseLevel;
	}

	/**
	 * 设置受理时间
	 */
	public void setAcceptTime(java.lang.String acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取受理时间
	 */
	public java.lang.String getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置办结时间
	 */
	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取办结时间
	 */
	public java.lang.String getEndTime() {
		return endTime;
	}

	/**
	 * 设置预警反馈是否超期
	 */
	public void setOutEndtime(java.lang.Double outEndtime) {
		this.outEndtime = outEndtime;
	}

	/**
	 * 获取预警反馈是否超期
	 */
	public java.lang.Double getOutEndtime() {
		return outEndtime;
	}
}
