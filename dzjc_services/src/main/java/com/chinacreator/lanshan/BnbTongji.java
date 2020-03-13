
package com.chinacreator.lanshan;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 帮你办办件统计
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.lanshan.BnbTongji", table = "V_BNB_TONGJI", ds = "acceptdata", cache = false)
public class BnbTongji {
	/**
	*ID
	*/
	@Column(id = "v_bnb_tongji_id", type = ColumnType.uuid, datatype = "string2000")
	private java.lang.String vBnbTongjiId;

	/**
	*接单总数
	*/
	@Column(id = "jdzs", datatype = "int")
	private java.lang.Integer jdzs;

	/**
	*办结总数
	*/
	@Column(id = "bjzs", datatype = "int")
	private java.lang.Integer bjzs;

	/**
	*网格员办件数
	*/
	@Column(id = "bjs_wgxt", datatype = "int")
	private java.lang.Integer bjsWgxt;

	/**
	*党员办件数
	*/
	@Column(id = "bjs_djxt", datatype = "int")
	private java.lang.Integer bjsDjxt;

	/**
	*一类事项办件数
	*/
	@Column(id = "bjs_ylsx", datatype = "int")
	private java.lang.Integer bjsYlsx;

	/**
	*二类事项办件数
	*/
	@Column(id = "bjs_elsx", datatype = "int")
	private java.lang.Integer bjsElsx;

	/**
	*接单时间(月份)
	*/
	@Column(id = "order_month", datatype = "string10")
	private java.lang.String orderMonth;

	/**
	* 设置ID
	*/
	public void setVBnbTongjiId(java.lang.String vBnbTongjiId) {
		this.vBnbTongjiId = vBnbTongjiId;
	}

	/**
	* 获取ID
	*/
	public java.lang.String getVBnbTongjiId() {
		return vBnbTongjiId;
	}

	/**
	* 设置接单总数
	*/
	public void setJdzs(java.lang.Integer jdzs) {
		this.jdzs = jdzs;
	}

	/**
	* 获取接单总数
	*/
	public java.lang.Integer getJdzs() {
		return jdzs;
	}

	/**
	* 设置办结总数
	*/
	public void setBjzs(java.lang.Integer bjzs) {
		this.bjzs = bjzs;
	}

	/**
	* 获取办结总数
	*/
	public java.lang.Integer getBjzs() {
		return bjzs;
	}

	/**
	* 设置网格员办件数
	*/
	public void setBjsWgxt(java.lang.Integer bjsWgxt) {
		this.bjsWgxt = bjsWgxt;
	}

	/**
	* 获取网格员办件数
	*/
	public java.lang.Integer getBjsWgxt() {
		return bjsWgxt;
	}

	/**
	* 设置党员办件数
	*/
	public void setBjsDjxt(java.lang.Integer bjsDjxt) {
		this.bjsDjxt = bjsDjxt;
	}

	/**
	* 获取党员办件数
	*/
	public java.lang.Integer getBjsDjxt() {
		return bjsDjxt;
	}

	/**
	* 设置一类事项办件数
	*/
	public void setBjsYlsx(java.lang.Integer bjsYlsx) {
		this.bjsYlsx = bjsYlsx;
	}

	/**
	* 获取一类事项办件数
	*/
	public java.lang.Integer getBjsYlsx() {
		return bjsYlsx;
	}

	/**
	* 设置二类事项办件数
	*/
	public void setBjsElsx(java.lang.Integer bjsElsx) {
		this.bjsElsx = bjsElsx;
	}

	/**
	* 获取二类事项办件数
	*/
	public java.lang.Integer getBjsElsx() {
		return bjsElsx;
	}

	/**
	* 设置接单时间(月份)
	*/
	public void setOrderMonth(java.lang.String orderMonth) {
		this.orderMonth = orderMonth;
	}

	/**
	* 获取接单时间(月份)
	*/
	public java.lang.String getOrderMonth() {
		return orderMonth;
	}
}
