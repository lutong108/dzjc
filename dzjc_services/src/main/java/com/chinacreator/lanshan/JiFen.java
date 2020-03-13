
package com.chinacreator.lanshan;

import java.sql.Date;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 帮你办积分兑换
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.lanshanxian.JiFen", table = "TA_SP_REDEEM", ds = "acceptdata", cache = false)
public class JiFen {
	/**
	*id
	*/
	@Column(id = "redeem_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String redeemId;

	/**
	*社区
	*/
	@Column(id = "community", datatype = "string256")
	private java.lang.String community;

	/**
	*网格员（姓名）
	*/
	@Column(id = "grid_name", datatype = "string256")
	private java.lang.String gridName;

	/**
	*办理事项数
	*/
	@Column(id = "blsxs", datatype = "smallint")
	private java.lang.Integer blsxs;

	/**
	*一类事项数
	*/
	@Column(id = "ylsx", datatype = "smallint")
	private java.lang.Integer ylsx;

	/**
	*二类事项数
	*/
	@Column(id = "elsx", datatype = "smalldouble")
	private java.lang.Double elsx;

	/**
	*一类事项积分
	*/
	@Column(id = "ylsx_score", datatype = "smalldouble")
	private java.lang.Double ylsxScore;

	/**
	*二类事项积分
	*/
	@Column(id = "elsx_score", datatype = "smalldouble")
	private java.lang.Double elsxScore;

	/**
	*好评积分
	*/
	@Column(id = "good_points", datatype = "smalldouble")
	private java.lang.Double goodPoints;

	/**
	*总积分
	*/
	@Column(id = "total_score", datatype = "mediumdouble")
	private java.lang.Double totalScore;

	/**
	*总金额
	*/
	@Column(id = "total_money", datatype = "int")
	private java.lang.Integer totalMoney;

	/**
	*查询开始时间
	*/
	@Column(id = "begin_date", datatype = "date")
	private java.sql.Date beginDate;

	/**
	*查询结束时间
	*/
	@Column(id = "end_date", datatype = "date")
	private java.sql.Date endDate;

	/**
	* 设置id
	*/
	public void setRedeemId(java.lang.String redeemId) {
		this.redeemId = redeemId;
	}

	/**
	* 获取id
	*/
	public java.lang.String getRedeemId() {
		return redeemId;
	}

	/**
	* 设置社区
	*/
	public void setCommunity(java.lang.String community) {
		this.community = community;
	}

	/**
	* 获取社区
	*/
	public java.lang.String getCommunity() {
		return community;
	}

	/**
	* 设置网格员（姓名）
	*/
	public void setGridName(java.lang.String gridName) {
		this.gridName = gridName;
	}

	/**
	* 获取网格员（姓名）
	*/
	public java.lang.String getGridName() {
		return gridName;
	}

	/**
	* 设置办理事项数
	*/
	public void setBlsxs(java.lang.Integer blsxs) {
		this.blsxs = blsxs;
	}

	/**
	* 获取办理事项数
	*/
	public java.lang.Integer getBlsxs() {
		return blsxs;
	}

	/**
	* 设置一类事项数
	*/
	public void setYlsx(java.lang.Integer ylsx) {
		this.ylsx = ylsx;
	}

	/**
	* 获取一类事项数
	*/
	public java.lang.Integer getYlsx() {
		return ylsx;
	}

	/**
	* 设置二类事项数
	*/
	public void setElsx(java.lang.Double elsx) {
		this.elsx = elsx;
	}

	/**
	* 获取二类事项数
	*/
	public java.lang.Double getElsx() {
		return elsx;
	}

	/**
	* 设置一类事项积分
	*/
	public void setYlsxScore(java.lang.Double ylsxScore) {
		this.ylsxScore = ylsxScore;
	}

	/**
	* 获取一类事项积分
	*/
	public java.lang.Double getYlsxScore() {
		return ylsxScore;
	}

	/**
	* 设置二类事项积分
	*/
	public void setElsxScore(java.lang.Double elsxScore) {
		this.elsxScore = elsxScore;
	}

	/**
	* 获取二类事项积分
	*/
	public java.lang.Double getElsxScore() {
		return elsxScore;
	}

	/**
	* 设置好评积分
	*/
	public void setGoodPoints(java.lang.Double goodPoints) {
		this.goodPoints = goodPoints;
	}

	/**
	* 获取好评积分
	*/
	public java.lang.Double getGoodPoints() {
		return goodPoints;
	}

	/**
	* 设置总积分
	*/
	public void setTotalScore(java.lang.Double totalScore) {
		this.totalScore = totalScore;
	}

	/**
	* 获取总积分
	*/
	public java.lang.Double getTotalScore() {
		return totalScore;
	}

	/**
	* 设置总金额
	*/
	public void setTotalMoney(java.lang.Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	* 获取总金额
	*/
	public java.lang.Integer getTotalMoney() {
		return totalMoney;
	}

	/**
	* 设置查询开始时间
	*/
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	* 获取查询开始时间
	*/
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	* 设置查询结束时间
	*/
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	* 获取查询结束时间
	*/
	public java.sql.Date getEndDate() {
		return endDate;
	}
}
