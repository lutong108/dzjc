package com.chinacreator.dzjc_tongji;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 省本级机构预警数据
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.sbjGreenData", table = "V_SBJ_GREENDATA", ds = "acceptdata", cache = false)
public class SbjGreenData {
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	@Column(id = "name", datatype = "string256")
	private java.lang.String name;

	@Column(id = "sbj_num_instance", datatype = "string20")
	private java.lang.String sbjNumInstance;

	/**
	 *
	 */
	@Column(id = "sbj_num_counsel", datatype = "string20")
	private java.lang.String sbjNumCounsel;

	@Column(id = "sbj_num_special", datatype = "string20")
	private java.lang.String sbjNumSpecial;

	/**
	 * 设置${field.desc}
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSbjNumInstance(java.lang.String sbjNumInstance) {
		this.sbjNumInstance = sbjNumInstance;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSbjNumInstance() {
		return sbjNumInstance;
	}

	/**
	 * 设置
	 */
	public void setSbjNumCounsel(java.lang.String sbjNumCounsel) {
		this.sbjNumCounsel = sbjNumCounsel;
	}

	/**
	 * 获取
	 */
	public java.lang.String getSbjNumCounsel() {
		return sbjNumCounsel;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSbjNumSpecial(java.lang.String sbjNumSpecial) {
		this.sbjNumSpecial = sbjNumSpecial;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSbjNumSpecial() {
		return sbjNumSpecial;
	}
}
