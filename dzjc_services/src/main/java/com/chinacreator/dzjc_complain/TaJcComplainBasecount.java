package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 投诉次数记录表
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.taJcComplainBasecount", table = "TA_JC_COMPLAIN_BASECOUNT", ds = "acceptdata", cache = false)
public class TaJcComplainBasecount {
	/**
	 *记录ID
	 */
	@Column(id = "basecountid", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String basecountid;

	/**
	 *投诉ID
	 */
	@Column(id = "complain_id", datatype = "string64")
	private java.lang.String complainId;

	/**
	 *投诉表关联ID
	 */
	@Column(id = "correlationid", datatype = "string64")
	private java.lang.String correlationid;

	/**
	 *记录次数
	 */
	@Column(id = "basecount", datatype = "string20")
	private java.lang.String basecount;

	/**
	 * 设置记录ID
	 */
	public void setBasecountid(java.lang.String basecountid) {
		this.basecountid = basecountid;
	}

	/**
	 * 获取记录ID
	 */
	public java.lang.String getBasecountid() {
		return basecountid;
	}

	/**
	 * 设置投诉ID
	 */
	public void setComplainId(java.lang.String complainId) {
		this.complainId = complainId;
	}

	/**
	 * 获取投诉ID
	 */
	public java.lang.String getComplainId() {
		return complainId;
	}

	/**
	 * 设置投诉表关联ID
	 */
	public void setCorrelationid(java.lang.String correlationid) {
		this.correlationid = correlationid;
	}

	/**
	 * 获取投诉表关联ID
	 */
	public java.lang.String getCorrelationid() {
		return correlationid;
	}

	/**
	 * 设置记录次数
	 */
	public void setBasecount(java.lang.String basecount) {
		this.basecount = basecount;
	}

	/**
	 * 获取记录次数
	 */
	public java.lang.String getBasecount() {
		return basecount;
	}
}
