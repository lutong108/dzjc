package com.chinacreator.dzjc_ruleEngine;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 手动发牌规则维护
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.Supervisoryrules", table = "TA_JC_SUPERVISORY_RULES", ds = "acceptdata", cache = false)
public class Supervisoryrules {
	/**
	 *规则ID
	 */
	@Column(id = "supervisory_rules_id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String supervisoryRulesId;

	/**
	 *规则名称
	 */
	@Column(id = "supervisory_rules_name", datatype = "string128")
	private java.lang.String supervisoryRulesName;

	/**
	 * 设置规则ID
	 */
	public void setSupervisoryRulesId(java.lang.String supervisoryRulesId) {
		this.supervisoryRulesId = supervisoryRulesId;
	}

	/**
	 * 获取规则ID
	 */
	public java.lang.String getSupervisoryRulesId() {
		return supervisoryRulesId;
	}

	/**
	 * 设置规则名称
	 */
	public void setSupervisoryRulesName(java.lang.String supervisoryRulesName) {
		this.supervisoryRulesName = supervisoryRulesName;
	}

	/**
	 * 获取规则名称
	 */
	public java.lang.String getSupervisoryRulesName() {
		return supervisoryRulesName;
	}
}
