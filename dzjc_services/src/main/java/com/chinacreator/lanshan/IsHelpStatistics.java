
package com.chinacreator.lanshan;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 帮你办办件列表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.lanshanxian.IsHelpStatistics", table = "V_BJTJ", ds = "acceptdata", cache = false)
public class IsHelpStatistics {
	/**
	*抢单ID
	*/
	@Column(id = "order_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String orderId;

	/**
	*办件编号
	*/
	@Column(id = "bjbh", datatype = "string128")
	private java.lang.String bjbh;

	/**
	*申请人姓名
	*/
	@Column(id = "apply_name", datatype = "string256")
	private java.lang.String applyName;

	/**
	*申请人电话
	*/
	@Column(id = "apply_phone", datatype = "string20")
	private java.lang.String applyPhone;

	/**
	*事项名称(需求)
	*/
	@Column(id = "approve_name", datatype = "string2000")
	private java.lang.String approveName;

	/**
	*抢单人姓名
	*/
	@Column(id = "username", datatype = "string256")
	private java.lang.String username;

	/**
	*抢单人电话
	*/
	@Column(id = "order_phone", datatype = "string64")
	private java.lang.String orderPhone;

	/**
	*抢单时间
	*/
	@Column(id = "order_time", datatype = "date")
	private java.sql.Date orderTime;

	/**
	*办结时间(取消时间)
	*/
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	*系统类型(0:网格系统,1:党建系统)
	*/
	@Column(id = "systemtype", datatype = "char1")
	private java.lang.String systemtype;

	/**
	*状态(0:待接单,1:已接单,2:待接单取消,3:已接单取消,4:已办结)
	*/
	@Column(id = "status", datatype = "char1")
	private java.lang.String status;

	/**
	*事项类型(1:一类事项,2:二类事项)
	*/
	@Column(id = "sxlx", datatype = "char1")
	private java.lang.String sxlx;

	/**
	*退单原因
	*/
	@Column(id = "tdyy", datatype = "string2000")
	private java.lang.String tdyy;

	/**
	* 设置抢单ID
	*/
	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}

	/**
	* 获取抢单ID
	*/
	public java.lang.String getOrderId() {
		return orderId;
	}

	/**
	* 设置办件编号
	*/
	public void setBjbh(java.lang.String bjbh) {
		this.bjbh = bjbh;
	}

	/**
	* 获取办件编号
	*/
	public java.lang.String getBjbh() {
		return bjbh;
	}

	/**
	* 设置申请人姓名
	*/
	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	/**
	* 获取申请人姓名
	*/
	public java.lang.String getApplyName() {
		return applyName;
	}

	/**
	* 设置申请人电话
	*/
	public void setApplyPhone(java.lang.String applyPhone) {
		this.applyPhone = applyPhone;
	}

	/**
	* 获取申请人电话
	*/
	public java.lang.String getApplyPhone() {
		return applyPhone;
	}

	/**
	* 设置事项名称(需求)
	*/
	public void setApproveName(java.lang.String approveName) {
		this.approveName = approveName;
	}

	/**
	* 获取事项名称(需求)
	*/
	public java.lang.String getApproveName() {
		return approveName;
	}

	/**
	* 设置抢单人姓名
	*/
	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	/**
	* 获取抢单人姓名
	*/
	public java.lang.String getUsername() {
		return username;
	}

	/**
	* 设置抢单人电话
	*/
	public void setOrderPhone(java.lang.String orderPhone) {
		this.orderPhone = orderPhone;
	}

	/**
	* 获取抢单人电话
	*/
	public java.lang.String getOrderPhone() {
		return orderPhone;
	}

	/**
	* 设置抢单时间
	*/
	public void setOrderTime(java.sql.Date orderTime) {
		this.orderTime = orderTime;
	}

	/**
	* 获取抢单时间
	*/
	public java.sql.Date getOrderTime() {
		return orderTime;
	}

	/**
	* 设置办结时间(取消时间)
	*/
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	* 获取办结时间(取消时间)
	*/
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	* 设置系统类型(0:网格系统,1:党建系统)
	*/
	public void setSystemtype(java.lang.String systemtype) {
		this.systemtype = systemtype;
	}

	/**
	* 获取系统类型(0:网格系统,1:党建系统)
	*/
	public java.lang.String getSystemtype() {
		return systemtype;
	}

	/**
	* 设置状态(0:待接单,1:已接单,2:待接单取消,3:已接单取消,4:已办结)
	*/
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	* 获取状态(0:待接单,1:已接单,2:待接单取消,3:已接单取消,4:已办结)
	*/
	public java.lang.String getStatus() {
		return status;
	}

	/**
	* 设置事项类型(1:一类事项,2:二类事项)
	*/
	public void setSxlx(java.lang.String sxlx) {
		this.sxlx = sxlx;
	}

	/**
	* 获取事项类型(1:一类事项,2:二类事项)
	*/
	public java.lang.String getSxlx() {
		return sxlx;
	}

	/**
	* 设置退单原因
	*/
	public void setTdyy(java.lang.String tdyy) {
		this.tdyy = tdyy;
	}

	/**
	* 获取退单原因
	*/
	public java.lang.String getTdyy() {
		return tdyy;
	}
}
