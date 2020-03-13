package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 热点事项-目录
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.RightsInfo", table = "V_SP_RIGHTS_INFO", ds = "acceptdata", cache = false)
public class RightsInfo {
	/**
	 *目录id
	 */
	@Column(id = "rights_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String rightsId;

	/**
	 *目录编码
	 */
	@Column(id = "rights_code", datatype = "string20")
	private java.lang.String rightsCode;

	/**
	 *目录名称
	 */
	@Column(id = "rights_name", datatype = "string1024")
	private java.lang.String rightsName;

	/**
	 *事项类型
	 */
	@Column(id = "approve_type", datatype = "char60")
	private java.lang.String approveType;

	/**
	 *行使层级
	 */
	@Column(id = "exercise_hierarchy", datatype = "char60")
	private java.lang.String exerciseHierarchy;

	/**
	 *收件数
	 */
	@Column(id = "sjs_num", datatype = "bigdouble")
	private java.lang.Double sjsNum;

	/**
	 *办结数
	 */
	@Column(id = "bjs_num", datatype = "bigdouble")
	private java.lang.Double bjsNum;

	/**
	 *红牌
	 */
	@Column(id = "red_num", datatype = "bigdouble")
	private java.lang.Double redNum;

	/**
	 *黄牌
	 */
	@Column(id = "yellow_num", datatype = "bigdouble")
	private java.lang.Double yellowNum;

	/**
	 *红牌
	 */
	@Column(id = "green_num", datatype = "bigdouble")
	private java.lang.Double greenNum;

	/**
	 *受理开始时间
	 */
	@Column(id = "beginDate", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *受理结束时间
	 */
	@Column(id = "endDate", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *监管开始时间
	 */
	@Column(id = "jcBeginDate", datatype = "date")
	private java.sql.Date jcBeginDate;

	/**
	 *监管结束时间
	 */
	@Column(id = "jcEndDate", datatype = "date")
	private java.sql.Date jcEndDate;

	/**
	 * 设置目录id
	 */
	public void setRightsId(java.lang.String rightsId) {
		this.rightsId = rightsId;
	}

	/**
	 * 获取目录id
	 */
	public java.lang.String getRightsId() {
		return rightsId;
	}

	/**
	 * 设置目录编码
	 */
	public void setRightsCode(java.lang.String rightsCode) {
		this.rightsCode = rightsCode;
	}

	/**
	 * 获取目录编码
	 */
	public java.lang.String getRightsCode() {
		return rightsCode;
	}

	/**
	 * 设置目录名称
	 */
	public void setRightsName(java.lang.String rightsName) {
		this.rightsName = rightsName;
	}

	/**
	 * 获取目录名称
	 */
	public java.lang.String getRightsName() {
		return rightsName;
	}

	/**
	 * 设置事项类型
	 */
	public void setApproveType(java.lang.String approveType) {
		this.approveType = approveType;
	}

	/**
	 * 获取事项类型
	 */
	public java.lang.String getApproveType() {
		return approveType;
	}

	/**
	 * 设置行使层级
	 */
	public void setExerciseHierarchy(java.lang.String exerciseHierarchy) {
		this.exerciseHierarchy = exerciseHierarchy;
	}

	/**
	 * 获取行使层级
	 */
	public java.lang.String getExerciseHierarchy() {
		return exerciseHierarchy;
	}

	/**
	 * 设置收件数
	 */
	public void setSjsNum(java.lang.Double sjsNum) {
		this.sjsNum = sjsNum;
	}

	/**
	 * 获取收件数
	 */
	public java.lang.Double getSjsNum() {
		return sjsNum;
	}

	/**
	 * 设置办结数
	 */
	public void setBjsNum(java.lang.Double bjsNum) {
		this.bjsNum = bjsNum;
	}

	/**
	 * 获取办结数
	 */
	public java.lang.Double getBjsNum() {
		return bjsNum;
	}

	/**
	 * 设置红牌
	 */
	public void setRedNum(java.lang.Double redNum) {
		this.redNum = redNum;
	}

	/**
	 * 获取红牌
	 */
	public java.lang.Double getRedNum() {
		return redNum;
	}

	/**
	 * 设置黄牌
	 */
	public void setYellowNum(java.lang.Double yellowNum) {
		this.yellowNum = yellowNum;
	}

	/**
	 * 获取黄牌
	 */
	public java.lang.Double getYellowNum() {
		return yellowNum;
	}

	/**
	 * 设置红牌
	 */
	public void setGreenNum(java.lang.Double greenNum) {
		this.greenNum = greenNum;
	}

	/**
	 * 获取红牌
	 */
	public java.lang.Double getGreenNum() {
		return greenNum;
	}

	/**
	 * 设置受理开始时间
	 */
	public void setBeginDate(java.sql.Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取受理开始时间
	 */
	public java.sql.Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置受理结束时间
	 */
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取受理结束时间
	 */
	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置监管开始时间
	 */
	public void setJcBeginDate(java.sql.Date jcBeginDate) {
		this.jcBeginDate = jcBeginDate;
	}

	/**
	 * 获取监管开始时间
	 */
	public java.sql.Date getJcBeginDate() {
		return jcBeginDate;
	}

	/**
	 * 设置监管结束时间
	 */
	public void setJcEndDate(java.sql.Date jcEndDate) {
		this.jcEndDate = jcEndDate;
	}

	/**
	 * 获取监管结束时间
	 */
	public java.sql.Date getJcEndDate() {
		return jcEndDate;
	}
}
