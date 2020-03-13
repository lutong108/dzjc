package com.chinacreator.area.bean;

import java.io.Serializable;

import com.chinacreator.c2.web.ds.TreeNode;

public class PublicTreeNode implements TreeNode, Serializable{

	/** 序列化 */
	private static final long serialVersionUID = -8821309114631370506L;
	private String id;	//树节点ID
	private String pid;	//树节点父
	private String name;	//树节点名称
	private String code;	//树节点编码
	private boolean isParent;	//是否为父节点
	private boolean checked;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	@Override
	public boolean getIsParent() {
		// TODO Auto-generated method stub
		return isParent;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
