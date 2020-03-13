
package com.chinacreator.lanshan;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 蓝山县帮你办评价
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.lanshanxian.Evaluate", table = "V_TJ_EVALUATE", ds = "acceptdata", cache = false)
public class Evaluate {
	/**
	*ID
	*/
	@Column(id = "evaluation_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String evaluationId;

	/**
	*社区
	*/
	@Column(id = "community", datatype = "string256")
	private java.lang.String community;

	/**
	*办理人姓名
	*/
	@Column(id = "username", datatype = "string128")
	private java.lang.String username;

	/**
	*办理人电话
	*/
	@Column(id = "phone", datatype = "string20")
	private java.lang.String phone;

	/**
	*办理人所在系统
	*/
	@Column(id = "systemtype", datatype = "string256")
	private java.lang.String systemtype;

	/**
	*评价结果
	*/
	@Column(id = "result_str", datatype = "string256")
	private java.lang.String resultStr;

	/**
	*评价结果（分数）
	*/
	@Column(id = "evaluation_result", datatype = "char2")
	private java.lang.String evaluationResult;

	/**
	*评价内容
	*/
	@Column(id = "evaluation_content", datatype = "string512")
	private java.lang.String evaluationContent;

	/**
	*评价时间
	*/
	@Column(id = "evaluation_time", datatype = "date")
	private java.sql.Date evaluationTime;

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
	* 设置ID
	*/
	public void setEvaluationId(java.lang.String evaluationId) {
		this.evaluationId = evaluationId;
	}

	/**
	* 获取ID
	*/
	public java.lang.String getEvaluationId() {
		return evaluationId;
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
	* 设置办理人姓名
	*/
	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	/**
	* 获取办理人姓名
	*/
	public java.lang.String getUsername() {
		return username;
	}

	/**
	* 设置办理人电话
	*/
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	* 获取办理人电话
	*/
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	* 设置办理人所在系统
	*/
	public void setSystemtype(java.lang.String systemtype) {
		this.systemtype = systemtype;
	}

	/**
	* 获取办理人所在系统
	*/
	public java.lang.String getSystemtype() {
		return systemtype;
	}

	/**
	* 设置评价结果
	*/
	public void setResultStr(java.lang.String resultStr) {
		this.resultStr = resultStr;
	}

	/**
	* 获取评价结果
	*/
	public java.lang.String getResultStr() {
		return resultStr;
	}

	/**
	* 设置评价结果（分数）
	*/
	public void setEvaluationResult(java.lang.String evaluationResult) {
		this.evaluationResult = evaluationResult;
	}

	/**
	* 获取评价结果（分数）
	*/
	public java.lang.String getEvaluationResult() {
		return evaluationResult;
	}

	/**
	* 设置评价内容
	*/
	public void setEvaluationContent(java.lang.String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	/**
	* 获取评价内容
	*/
	public java.lang.String getEvaluationContent() {
		return evaluationContent;
	}

	/**
	* 设置评价时间
	*/
	public void setEvaluationTime(java.sql.Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	/**
	* 获取评价时间
	*/
	public java.sql.Date getEvaluationTime() {
		return evaluationTime;
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
	public java.sql.Date getBeginDate() {
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
