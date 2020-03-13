package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 投诉问题类型
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.problemType", table = "TA_JC_DIC_PROBLEM_TYPE", ds = "acceptdata", cache = false)
public class ProblemType {
	/**
	 *问题类型ID
	 */
	@Column(id = "problem_type_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String problemTypeId;

	/**
	 *问题类型编码
	 */
	@Column(id = "problem_type_code", datatype = "char2")
	private java.lang.String problemTypeCode;

	/**
	 *问题类型名称
	 */
	@Column(id = "problem_type_name", datatype = "string256")
	private java.lang.String problemTypeName;

	/**
	 *状态
	 */
	@Column(id = "state", datatype = "boolean")
	private java.lang.Boolean state;

	/**
	 * 设置问题类型ID
	 */
	public void setProblemTypeId(java.lang.String problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	/**
	 * 获取问题类型ID
	 */
	public java.lang.String getProblemTypeId() {
		return problemTypeId;
	}

	/**
	 * 设置问题类型编码
	 */
	public void setProblemTypeCode(java.lang.String problemTypeCode) {
		this.problemTypeCode = problemTypeCode;
	}

	/**
	 * 获取问题类型编码
	 */
	public java.lang.String getProblemTypeCode() {
		return problemTypeCode;
	}

	/**
	 * 设置问题类型名称
	 */
	public void setProblemTypeName(java.lang.String problemTypeName) {
		this.problemTypeName = problemTypeName;
	}

	/**
	 * 获取问题类型名称
	 */
	public java.lang.String getProblemTypeName() {
		return problemTypeName;
	}

	/**
	 * 设置状态
	 */
	public void setState(java.lang.Boolean state) {
		this.state = state;
	}

	/**
	 * 获取状态
	 */
	public java.lang.Boolean isState() {
		return state;
	}
}
