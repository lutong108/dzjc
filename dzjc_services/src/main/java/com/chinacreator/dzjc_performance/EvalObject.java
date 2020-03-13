package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 绩效考核对象
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.EvalObject", table = "TA_JC_EVAL_OBJECT", ds = "acceptdata", cache = false)
public class EvalObject {
	/**
	 *主键id
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *考核对象ID
	 */
	@Column(id = "object_id", datatype = "string64")
	private java.lang.String objectId;

	/**
	 *考核对象名称
	 */
	@Column(id = "object_name", datatype = "string256")
	private java.lang.String objectName;

	/**
	 *考核对象类型(1-部门，2-窗口，3-个人)
	 */
	@Column(id = "object_type", datatype = "string5")
	private java.lang.String objectType;

	/**
	 *测评实例ID
	 */
	@Column(id = "eval_instance_id", datatype = "string64")
	private java.lang.String evalInstanceId;

	/**
	 *上报状态
	 */
	@Column(id = "state", datatype = "string5")
	private java.lang.String state;

	/**
	 *加分
	 */
	@Column(id = "bonuspoints", datatype = "tinydouble")
	private java.lang.Double bonuspoints;

	/**
	 *加分原因
	 */
	@Column(id = "reasonsforbonus", datatype = "string2000")
	private java.lang.String reasonsforbonus;

	/**
	 * 设置主键id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键id
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置考核对象ID
	 */
	public void setObjectId(java.lang.String objectId) {
		this.objectId = objectId;
	}

	/**
	 * 获取考核对象ID
	 */
	public java.lang.String getObjectId() {
		return objectId;
	}

	/**
	 * 设置考核对象名称
	 */
	public void setObjectName(java.lang.String objectName) {
		this.objectName = objectName;
	}

	/**
	 * 获取考核对象名称
	 */
	public java.lang.String getObjectName() {
		return objectName;
	}

	/**
	 * 设置考核对象类型(1-部门，2-窗口，3-个人)
	 */
	public void setObjectType(java.lang.String objectType) {
		this.objectType = objectType;
	}

	/**
	 * 获取考核对象类型(1-部门，2-窗口，3-个人)
	 */
	public java.lang.String getObjectType() {
		return objectType;
	}

	/**
	 * 设置测评实例ID
	 */
	public void setEvalInstanceId(java.lang.String evalInstanceId) {
		this.evalInstanceId = evalInstanceId;
	}

	/**
	 * 获取测评实例ID
	 */
	public java.lang.String getEvalInstanceId() {
		return evalInstanceId;
	}

	/**
	 * 设置上报状态
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取上报状态
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * 设置加分
	 */
	public void setBonuspoints(java.lang.Double bonuspoints) {
		this.bonuspoints = bonuspoints;
	}

	/**
	 * 获取加分
	 */
	public java.lang.Double getBonuspoints() {
		return bonuspoints;
	}

	/**
	 * 设置加分原因
	 */
	public void setReasonsforbonus(java.lang.String reasonsforbonus) {
		this.reasonsforbonus = reasonsforbonus;
	}

	/**
	 * 获取加分原因
	 */
	public java.lang.String getReasonsforbonus() {
		return reasonsforbonus;
	}
}
