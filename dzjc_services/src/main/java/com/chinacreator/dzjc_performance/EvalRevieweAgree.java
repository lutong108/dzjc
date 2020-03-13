package com.chinacreator.dzjc_performance;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 在线申诉审核意见
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_performance.EvalRevieweAgree", table = "TA_JC_EVAL_REVIEWE_AGREE", ds = "acceptdata", cache = false)
public class EvalRevieweAgree {
	/**
	 *ID
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String id;

	/**
	 *申诉审核表ID
	 */
	@Column(id = "reviewe_id", datatype = "string64")
	private java.lang.String revieweId;

	/**
	 *指标成绩表ID
	 */
	@Column(id = "eval_point_value_id", datatype = "string64")
	private java.lang.String evalPointValueId;

	/**
	 *是否同意(1-通过,2-驳回)
	 */
	@Column(id = "is_agree", datatype = "string5")
	private java.lang.String isAgree;

	/**
	 *审核意见内容
	 */
	@Column(id = "content", datatype = "string512")
	private java.lang.String content;

	/**
	 * 设置ID
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取ID
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置申诉审核表ID
	 */
	public void setRevieweId(java.lang.String revieweId) {
		this.revieweId = revieweId;
	}

	/**
	 * 获取申诉审核表ID
	 */
	public java.lang.String getRevieweId() {
		return revieweId;
	}

	/**
	 * 设置指标成绩表ID
	 */
	public void setEvalPointValueId(java.lang.String evalPointValueId) {
		this.evalPointValueId = evalPointValueId;
	}

	/**
	 * 获取指标成绩表ID
	 */
	public java.lang.String getEvalPointValueId() {
		return evalPointValueId;
	}

	/**
	 * 设置是否同意(1-通过,2-驳回)
	 */
	public void setIsAgree(java.lang.String isAgree) {
		this.isAgree = isAgree;
	}

	/**
	 * 获取是否同意(1-通过,2-驳回)
	 */
	public java.lang.String getIsAgree() {
		return isAgree;
	}

	/**
	 * 设置审核意见内容
	 */
	public void setContent(java.lang.String content) {
		this.content = content;
	}

	/**
	 * 获取审核意见内容
	 */
	public java.lang.String getContent() {
		return content;
	}
}
