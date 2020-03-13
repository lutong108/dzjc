package com.chinacreator.dzjc_complain;

import java.io.Serializable;
import java.util.Arrays;

public class OrgUserInfo implements Serializable {

	private static final long serialVersionUID = -890674707692232043L;

	private String id;
	private String certificateNum;
	private String certificateType;
	private String name;
	private String sex;
	private String phone;
	private String email;
	private String avatar;
	private String createAt;
	private String creator;
	private String lastModified;
	private String state;
	private String workno;
	private String authLevel;
	private String authToken;
	private String[] orgIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWorkno() {
		return workno;
	}

	public void setWorkno(String workno) {
		this.workno = workno;
	}

	public String getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String[] orgIds) {
		this.orgIds = orgIds;
	}

	@Override
	public String toString() {
		return "OrgUserInfo [id=" + id + ", certificateNum=" + certificateNum + ", certificateType=" + certificateType
				+ ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", email=" + email + ", avatar=" + avatar
				+ ", createAt=" + createAt + ", creator=" + creator + ", lastModified=" + lastModified + ", state="
				+ state + ", workno=" + workno + ", authLevel=" + authLevel + ", authToken=" + authToken + ", orgIds="
				+ Arrays.toString(orgIds) + "]";
	}

}
