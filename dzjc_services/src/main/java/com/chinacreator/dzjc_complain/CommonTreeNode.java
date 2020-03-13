package com.chinacreator.dzjc_complain;

import java.io.Serializable;

import com.chinacreator.c2.web.ds.TreeNode;

public class CommonTreeNode implements TreeNode, Serializable {
	private static final long serialVersionUID = 1L;
	private String pid;
	private String id;
	private String name;
	private boolean isParent;
	private String iconSkin;
	private boolean chkDisabled;
	private boolean checked;
	private boolean nocheck;
	private String hiddenName;
	private String showName;
	private String sex;
	private String phone;
	

	public String getPid() {
		return this.pid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsParent() {
		return this.isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getIconSkin() {
		return this.iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public boolean isChkDisabled() {
		return this.chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isNocheck() {
		return this.nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getHiddenName() {
		return this.hiddenName;
	}

	public void setHiddenName(String hiddenName) {
		this.hiddenName = hiddenName;
	}

	public String getShowName() {
		return this.showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}
}
