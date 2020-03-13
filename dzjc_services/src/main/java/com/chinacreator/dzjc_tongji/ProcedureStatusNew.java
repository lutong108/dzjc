package com.chinacreator.dzjc_tongji;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 存储过程执行情况（新）
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.ProcedureStatusNew", table = "TA_JC_PROCEDURE_STATUS_NEW", ds = "acceptdata", cache = false)
public class ProcedureStatusNew {
	/**
	 *主键
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String id;

	/**
	 *固化日期
	 */
	@Column(id = "solid_date", datatype = "string64")
	private java.lang.String solidDate;

	/**
	 *固化开始时间
	 */
	@Column(id = "begin_time", datatype = "string64")
	private java.lang.String beginTime;

	/**
	 *固化结束时间
	 */
	@Column(id = "end_time", datatype = "string64")
	private java.lang.String endTime;

	/**
	 *办件量
	 */
	@Column(id = "instance_num", datatype = "bigdouble")
	private java.lang.Double instanceNum;

	/**
	 *发牌量
	 */
	@Column(id = "deal_num", datatype = "bigdouble")
	private java.lang.Double dealNum;

	/**
	 *事项量
	 */
	@Column(id = "approve_num", datatype = "bigdouble")
	private java.lang.Double approveNum;
	
	/**
	 * 总时长
	 */
	private float totalTime;

	public float getTotalTime() {
		if (beginTime != null && endTime != null) {
			totalTime = calTime(beginTime, endTime);
		}
		return totalTime;
	}

	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * 设置主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置固化日期
	 */
	public void setSolidDate(java.lang.String solidDate) {
		this.solidDate = solidDate;
	}

	/**
	 * 获取固化日期
	 */
	public java.lang.String getSolidDate() {
		return solidDate;
	}

	/**
	 * 设置固化开始时间
	 */
	public void setBeginTime(java.lang.String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 获取固化开始时间
	 */
	public java.lang.String getBeginTime() {
		return beginTime;
	}

	/**
	 * 设置固化结束时间
	 */
	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取固化结束时间
	 */
	public java.lang.String getEndTime() {
		return endTime;
	}

	/**
	 * 设置办件量
	 */
	public void setInstanceNum(java.lang.Double instanceNum) {
		this.instanceNum = instanceNum;
	}

	/**
	 * 获取办件量
	 */
	public java.lang.Double getInstanceNum() {
		return instanceNum;
	}

	/**
	 * 设置发牌量
	 */
	public void setDealNum(java.lang.Double dealNum) {
		this.dealNum = dealNum;
	}

	/**
	 * 获取发牌量
	 */
	public java.lang.Double getDealNum() {
		return dealNum;
	}

	/**
	 * 设置事项量
	 */
	public void setApproveNum(java.lang.Double approveNum) {
		this.approveNum = approveNum;
	}

	/**
	 * 获取事项量
	 */
	public java.lang.Double getApproveNum() {
		return approveNum;
	}
	
	/**
	 * 计算时间差
	 * @param start
	 * @param end
	 * @return 秒
	 */
	private float calTime (String start, String end) {
		float duration = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		try {
			Date d1 = sdf.parse(start);
			Date d2 = sdf.parse(end);
			duration = ((float)(d2.getTime() - d1.getTime())) / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return duration;
	}
}
