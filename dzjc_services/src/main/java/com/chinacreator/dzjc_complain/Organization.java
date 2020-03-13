package com.chinacreator.dzjc_complain;

import java.io.Serializable;
import java.util.Date;

public class Organization implements Serializable {

	private static final long serialVersionUID = 6295594381635970033L;

	private String id;// 机构编号ORG_ID
	private int sn;// 排序编号ORG_SN
	private String name;// 机构名称ORG_NAME
	private String showname;// 显示名称ORG_SHOWNAME
	private String pid;// 父级编号PARENT_ID
	private String code;// 机构代号CODE
	private Date createAt;// 创建时间CREATINGTIME
	private String creator;// 创建人CREATOR
	private String orgnumber;// 机构编号ORGNUMBER
	private String categoryOrgId;// 主管机构CHARGEORGID
	private String orgtype;// 机构类型ORG_LEVEL
	private String xzqm;// 行政区码ORG_XZQM

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOrgnumber() {
		return orgnumber;
	}

	public void setOrgnumber(String orgnumber) {
		this.orgnumber = orgnumber;
	}

	public String getCategoryOrgId() {
		return categoryOrgId;
	}

	public void setCategoryOrgId(String categoryOrgId) {
		this.categoryOrgId = categoryOrgId;
	}

	public String getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	public String getXzqm() {
		return xzqm;
	}

	public void setXzqm(String xzqm) {
		this.xzqm = xzqm;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", sn=" + sn + ", name=" + name + ", showname=" + showname + ", pid=" + pid
				+ ", code=" + code + ", createAt=" + createAt + ", creator=" + creator + ", orgnumber=" + orgnumber
				+ ", categoryOrgId=" + categoryOrgId + ", orgtype=" + orgtype + ", xzqm=" + xzqm + "]";
	}
}
