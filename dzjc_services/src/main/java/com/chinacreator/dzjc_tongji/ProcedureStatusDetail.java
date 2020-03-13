package com.chinacreator.dzjc_tongji;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 存储过程详细执行情况
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.ProcedureStatusDetail", table = "TA_JC_PROCEDURE_STATUS_DETAIL", ds = "acceptdata", cache = false)
public class ProcedureStatusDetail {
	/**
	 *主键
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String id;

	/**
	 *存储过程名称
	 */
	@Column(id = "procedure_name", datatype = "string64")
	private java.lang.String procedureName;

	/**
	 *开始时间
	 */
	@Column(id = "begin_time", datatype = "string64")
	private java.lang.String beginTime;

	/**
	 *结束时间
	 */
	@Column(id = "end_time", datatype = "string64")
	private java.lang.String endTime;

	/**
	 *父id
	 */
	@Column(id = "pid", datatype = "string1024")
	private java.lang.String pid;
	
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
	 * 设置存储过程名称
	 */
	public void setProcedureName(java.lang.String procedureName) {
		this.procedureName = procedureName;
	}

	/**
	 * 获取存储过程名称
	 */
	public java.lang.String getProcedureName() {
		return procedureName;
	}

	/**
	 * 设置开始时间
	 */
	public void setBeginTime(java.lang.String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 获取开始时间
	 */
	public java.lang.String getBeginTime() {
		return beginTime;
	}

	/**
	 * 设置结束时间
	 */
	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取结束时间
	 */
	public java.lang.String getEndTime() {
		return endTime;
	}

	/**
	 * 设置父id
	 */
	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}

	/**
	 * 获取父id
	 */
	public java.lang.String getPid() {
		return pid;
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
