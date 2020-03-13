package com.chinacreator.dzjc_union;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 项目监察统计
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_union.ProjectCount", table = "VA_TA_UNION_PROJECT_SUPERVISE_COUNT", ds = "acceptdata", cache = false)
public class ProjectCount {
	@Column(id = "xzqm", type = ColumnType.uuid, datatype = "char12")
	private java.lang.String xzqm;

	@Column(id = "project_total", datatype = "bigdouble")
	private java.lang.Double projectTotal;

	@Column(id = "stage1_total", datatype = "bigdouble")
	private java.lang.Double stage1Total;

	@Column(id = "stage2_total", datatype = "bigdouble")
	private java.lang.Double stage2Total;

	@Column(id = "stage3_total", datatype = "bigdouble")
	private java.lang.Double stage3Total;

	@Column(id = "stage4_total", datatype = "bigdouble")
	private java.lang.Double stage4Total;

	@Column(id = "stage5_total", datatype = "bigdouble")
	private java.lang.Double stage5Total;

	@Column(id = "yujing_total", datatype = "bigdouble")
	private java.lang.Double yujingTotal;

	@Column(id = "huangpai_total", datatype = "bigdouble")
	private java.lang.Double huangpaiTotal;

	@Column(id = "hongpai_total", datatype = "bigdouble")
	private java.lang.Double hongpaiTotal;
	
	@Column(id = "stage0_total", datatype = "bigdouble")
	private java.lang.Double stage0Total;

	public java.lang.Double getStage0Total() {
		return stage0Total;
	}

	public void setStage0Total(java.lang.Double stage0Total) {
		this.stage0Total = stage0Total;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setXzqm(java.lang.String xzqm) {
		this.xzqm = xzqm;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getXzqm() {
		return xzqm;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProjectTotal(java.lang.Double projectTotal) {
		this.projectTotal = projectTotal;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getProjectTotal() {
		return projectTotal;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setStage1Total(java.lang.Double stage1Total) {
		this.stage1Total = stage1Total;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getStage1Total() {
		return stage1Total;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setStage2Total(java.lang.Double stage2Total) {
		this.stage2Total = stage2Total;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getStage2Total() {
		return stage2Total;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setStage3Total(java.lang.Double stage3Total) {
		this.stage3Total = stage3Total;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getStage3Total() {
		return stage3Total;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setStage4Total(java.lang.Double stage4Total) {
		this.stage4Total = stage4Total;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getStage4Total() {
		return stage4Total;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setStage5Total(java.lang.Double stage5Total) {
		this.stage5Total = stage5Total;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getStage5Total() {
		return stage5Total;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setYujingTotal(java.lang.Double yujingTotal) {
		this.yujingTotal = yujingTotal;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getYujingTotal() {
		return yujingTotal;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setHuangpaiTotal(java.lang.Double huangpaiTotal) {
		this.huangpaiTotal = huangpaiTotal;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getHuangpaiTotal() {
		return huangpaiTotal;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setHongpaiTotal(java.lang.Double hongpaiTotal) {
		this.hongpaiTotal = hongpaiTotal;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.Double getHongpaiTotal() {
		return hongpaiTotal;
	}
}
