package com.chinacreator.dzjc_ruleEngine;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 投诉监察信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_ruleEngine.SuperviseInfoComplain", table = "VA_TA_JC_COMPLAIN_BASEINFO", ds = "acceptdata")
public class SuperviseInfoComplain implements Serializable {
	private static final long serialVersionUID = 2638935251697664L;
	@Column(id = "supervise_result_no", datatype = "string20")
	private java.lang.String superviseResultNo;

	@Column(id = "complain_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String complainId;

	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	@Column(id = "bycomplained_user_org", datatype = "string128")
	private java.lang.String bycomplainedUserOrg;

	@Column(id = "bycomplained_user_post", datatype = "string128")
	private java.lang.String bycomplainedUserPost;

	@Column(id = "bycomplained_org", datatype = "string128")
	private java.lang.String bycomplainedOrg;

	@Column(id = "complain_user_name", datatype = "string128")
	private java.lang.String complainUserName;

	@Column(id = "complain_user_mobile", datatype = "string64")
	private java.lang.String complainUserMobile;

	@Column(id = "complain_user_card", datatype = "string64")
	private java.lang.String complainUserCard;

	@Column(id = "complain_title", datatype = "string512")
	private java.lang.String complainTitle;

	@Column(id = "complain_content", datatype = "string2000")
	private java.lang.String complainContent;

	@Column(id = "leader_idea", datatype = "string1536")
	private java.lang.String leaderIdea;

	@Column(id = "enregister_time", datatype = "date")
	private java.sql.Date enregisterTime;

	@Column(id = "state", datatype = "string10")
	private java.lang.String state;

	@Column(id = "problem_type", datatype = "string64")
	private java.lang.String problemType;

	@Column(id = "problem_small_type", datatype = "string64")
	private java.lang.String problemSmallType;

	@Column(id = "is_satisfied", datatype = "string64")
	private java.lang.String isSatisfied;

	@Column(id = "not_satisfied_reason", datatype = "string2000")
	private java.lang.String notSatisfiedReason;

	@Column(id = "lastmodifytime", datatype = "date")
	private java.sql.Date lastmodifytime;

	@Column(id = "is_public", datatype = "string64")
	private java.lang.String isPublic;

	@Column(id = "handle_status", datatype = "string10")
	private java.lang.String handleStatus;

	@Column(id = "expire_date", datatype = "string64")
	private java.lang.String expireDate;

	@Column(id = "accept_user_id", datatype = "string64")
	private java.lang.String acceptUserId;

	@Column(id = "handle_user_id", datatype = "string2000")
	private java.lang.String handleUserId;

	@Column(id = "complain_user_org", datatype = "string256")
	private java.lang.String complainUserOrg;

	@Column(id = "complain_user_phone", datatype = "string64")
	private java.lang.String complainUserPhone;

	@Column(id = "reply_orgname", datatype = "string64")
	private java.lang.String replyOrgname;

	@Column(id = "reply_orgid", datatype = "string64")
	private java.lang.String replyOrgid;

	@Column(id = "reply_time", datatype = "date")
	private java.sql.Date replyTime;

	@Column(id = "reply_user_id", datatype = "string64")
	private java.lang.String replyUserId;

	@Column(id = "commitnum", datatype = "string64")
	private java.lang.String commitnum;

	@Column(id = "complain_user_address", datatype = "string128")
	private java.lang.String complainUserAddress;

	@Column(id = "bycomplained_user_address", datatype = "string128")
	private java.lang.String bycomplainedUserAddress;

	@Column(id = "bycomplained_user_phone", datatype = "string20")
	private java.lang.String bycomplainedUserPhone;

	@Column(id = "problem_mode", datatype = "string5")
	private java.lang.String problemMode;

	@Column(id = "mainappeal", datatype = "string2000")
	private java.lang.String mainappeal;

	@Column(id = "proessstate", datatype = "string20")
	private java.lang.String proessstate;

	@Column(id = "correlationid", datatype = "string64")
	private java.lang.String correlationid;

	@Column(id = "handleis_public", datatype = "string64")
	private java.lang.String handleisPublic;

	@Column(id = "caseno", datatype = "string256")
	private java.lang.String caseno;

	@Column(id = "commitment_limit", datatype = "string256")
	private java.lang.String commitmentLimit;

	@Column(id = "issurereceive", datatype = "string256")
	private java.lang.String issurereceive;

	@Column(id = "taskid", datatype = "string64")
	private java.lang.String taskid;

	@Column(id = "is_examine", datatype = "string64")
	private java.lang.String isExamine;

	@Column(id = "is_preaccepsend", datatype = "string64")
	private java.lang.String isPreaccepsend;

	@Column(id = "is_acceptsend", datatype = "string64")
	private java.lang.String isAcceptsend;

	@Column(id = "accept_org_id", datatype = "string64")
	private java.lang.String acceptOrgId;

	@Column(id = "islocalsector", datatype = "string64")
	private java.lang.String islocalsector;

	@Column(id = "accept_org_name", datatype = "string256")
	private java.lang.String acceptOrgName;

	@Column(id = "accept_org_level", datatype = "string64")
	private java.lang.String acceptOrgLevel;

	@Column(id = "sure_receive_time", datatype = "date")
	private java.sql.Date sureReceiveTime;

	@Column(id = "brachcanshift", datatype = "string64")
	private java.lang.String brachcanshift;

	@Column(id = "commitment_city_limit", datatype = "string256")
	private java.lang.String commitmentCityLimit;

	@Column(id = "expire_city_date", datatype = "date")
	private java.sql.Date expireCityDate;

	@Column(id = "extens_day", datatype = "string64")
	private java.lang.String extensDay;

	@Column(id = "extens_reason", datatype = "string2000")
	private java.lang.String extensReason;

	@Column(id = "supervise_result_name", datatype = "string32")
	private java.lang.String superviseResultName;

	@Column(id = "supervise_type_code", datatype = "string20")
	private java.lang.String superviseTypeCode;

	@Column(id = "supervise_type_name", datatype = "string256")
	private java.lang.String superviseTypeName;

	/**
	 *查询开始时间
	 */
	@Column(id = "beginDate", datatype = "date")
	private java.sql.Date beginDate;

	/**
	 *查询结束时间
	 */
	@Column(id = "endDate", datatype = "date")
	private java.sql.Date endDate;

	/**
	 *是否满意名称
	 */
	@Column(id = "Is_Satisfied_name", datatype = "string64")
	private java.lang.String IsSatisfiedName;

	/**
	 *投诉方式名称
	 */
	@Column(id = "problem_mode_name", datatype = "string64")
	private java.lang.String problemModeName;

	/**
	 *完成时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *监察时间
	 */
	@Column(id = "supervise_time", datatype = "date")
	private java.sql.Date superviseTime;

	/**
	 *监察开始时间
	 */
	@Column(id = "beginJcDate", datatype = "date")
	private java.sql.Date beginJcDate;

	/**
	 *监察结束时间
	 */
	@Column(id = "endJcDate", datatype = "date")
	private java.sql.Date endJcDate;

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseResultNo(java.lang.String superviseResultNo) {
		this.superviseResultNo = superviseResultNo;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseResultNo() {
		return superviseResultNo;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainId(java.lang.String complainId) {
		this.complainId = complainId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainId() {
		return complainId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getQueryCode() {
		return queryCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptTime(java.sql.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBycomplainedUserOrg(java.lang.String bycomplainedUserOrg) {
		this.bycomplainedUserOrg = bycomplainedUserOrg;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBycomplainedUserOrg() {
		return bycomplainedUserOrg;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBycomplainedUserPost(java.lang.String bycomplainedUserPost) {
		this.bycomplainedUserPost = bycomplainedUserPost;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBycomplainedUserPost() {
		return bycomplainedUserPost;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBycomplainedOrg(java.lang.String bycomplainedOrg) {
		this.bycomplainedOrg = bycomplainedOrg;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBycomplainedOrg() {
		return bycomplainedOrg;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainUserName(java.lang.String complainUserName) {
		this.complainUserName = complainUserName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainUserName() {
		return complainUserName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainUserMobile(java.lang.String complainUserMobile) {
		this.complainUserMobile = complainUserMobile;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainUserMobile() {
		return complainUserMobile;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainUserCard(java.lang.String complainUserCard) {
		this.complainUserCard = complainUserCard;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainUserCard() {
		return complainUserCard;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainTitle(java.lang.String complainTitle) {
		this.complainTitle = complainTitle;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainTitle() {
		return complainTitle;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainContent(java.lang.String complainContent) {
		this.complainContent = complainContent;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainContent() {
		return complainContent;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setLeaderIdea(java.lang.String leaderIdea) {
		this.leaderIdea = leaderIdea;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getLeaderIdea() {
		return leaderIdea;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setEnregisterTime(java.sql.Date enregisterTime) {
		this.enregisterTime = enregisterTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getEnregisterTime() {
		return enregisterTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProblemType(java.lang.String problemType) {
		this.problemType = problemType;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProblemType() {
		return problemType;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProblemSmallType(java.lang.String problemSmallType) {
		this.problemSmallType = problemSmallType;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProblemSmallType() {
		return problemSmallType;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsSatisfied(java.lang.String isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsSatisfied() {
		return isSatisfied;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setNotSatisfiedReason(java.lang.String notSatisfiedReason) {
		this.notSatisfiedReason = notSatisfiedReason;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getNotSatisfiedReason() {
		return notSatisfiedReason;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setLastmodifytime(java.sql.Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getLastmodifytime() {
		return lastmodifytime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setHandleStatus(java.lang.String handleStatus) {
		this.handleStatus = handleStatus;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getHandleStatus() {
		return handleStatus;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setExpireDate(java.lang.String expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getExpireDate() {
		return expireDate;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptUserId(java.lang.String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAcceptUserId() {
		return acceptUserId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setHandleUserId(java.lang.String handleUserId) {
		this.handleUserId = handleUserId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getHandleUserId() {
		return handleUserId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainUserOrg(java.lang.String complainUserOrg) {
		this.complainUserOrg = complainUserOrg;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainUserOrg() {
		return complainUserOrg;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainUserPhone(java.lang.String complainUserPhone) {
		this.complainUserPhone = complainUserPhone;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainUserPhone() {
		return complainUserPhone;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setReplyOrgname(java.lang.String replyOrgname) {
		this.replyOrgname = replyOrgname;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getReplyOrgname() {
		return replyOrgname;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setReplyOrgid(java.lang.String replyOrgid) {
		this.replyOrgid = replyOrgid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getReplyOrgid() {
		return replyOrgid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setReplyTime(java.sql.Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getReplyTime() {
		return replyTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setReplyUserId(java.lang.String replyUserId) {
		this.replyUserId = replyUserId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getReplyUserId() {
		return replyUserId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCommitnum(java.lang.String commitnum) {
		this.commitnum = commitnum;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCommitnum() {
		return commitnum;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setComplainUserAddress(java.lang.String complainUserAddress) {
		this.complainUserAddress = complainUserAddress;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getComplainUserAddress() {
		return complainUserAddress;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBycomplainedUserAddress(
			java.lang.String bycomplainedUserAddress) {
		this.bycomplainedUserAddress = bycomplainedUserAddress;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBycomplainedUserAddress() {
		return bycomplainedUserAddress;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBycomplainedUserPhone(java.lang.String bycomplainedUserPhone) {
		this.bycomplainedUserPhone = bycomplainedUserPhone;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBycomplainedUserPhone() {
		return bycomplainedUserPhone;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProblemMode(java.lang.String problemMode) {
		this.problemMode = problemMode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProblemMode() {
		return problemMode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setMainappeal(java.lang.String mainappeal) {
		this.mainappeal = mainappeal;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getMainappeal() {
		return mainappeal;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setProessstate(java.lang.String proessstate) {
		this.proessstate = proessstate;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getProessstate() {
		return proessstate;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCorrelationid(java.lang.String correlationid) {
		this.correlationid = correlationid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCorrelationid() {
		return correlationid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setHandleisPublic(java.lang.String handleisPublic) {
		this.handleisPublic = handleisPublic;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getHandleisPublic() {
		return handleisPublic;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCaseno(java.lang.String caseno) {
		this.caseno = caseno;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCaseno() {
		return caseno;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCommitmentLimit(java.lang.String commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCommitmentLimit() {
		return commitmentLimit;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIssurereceive(java.lang.String issurereceive) {
		this.issurereceive = issurereceive;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIssurereceive() {
		return issurereceive;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setTaskid(java.lang.String taskid) {
		this.taskid = taskid;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getTaskid() {
		return taskid;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsExamine(java.lang.String isExamine) {
		this.isExamine = isExamine;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsExamine() {
		return isExamine;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsPreaccepsend(java.lang.String isPreaccepsend) {
		this.isPreaccepsend = isPreaccepsend;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsPreaccepsend() {
		return isPreaccepsend;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIsAcceptsend(java.lang.String isAcceptsend) {
		this.isAcceptsend = isAcceptsend;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIsAcceptsend() {
		return isAcceptsend;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptOrgId(java.lang.String acceptOrgId) {
		this.acceptOrgId = acceptOrgId;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAcceptOrgId() {
		return acceptOrgId;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setIslocalsector(java.lang.String islocalsector) {
		this.islocalsector = islocalsector;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getIslocalsector() {
		return islocalsector;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptOrgName(java.lang.String acceptOrgName) {
		this.acceptOrgName = acceptOrgName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAcceptOrgName() {
		return acceptOrgName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setAcceptOrgLevel(java.lang.String acceptOrgLevel) {
		this.acceptOrgLevel = acceptOrgLevel;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getAcceptOrgLevel() {
		return acceptOrgLevel;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSureReceiveTime(java.sql.Date sureReceiveTime) {
		this.sureReceiveTime = sureReceiveTime;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getSureReceiveTime() {
		return sureReceiveTime;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setBrachcanshift(java.lang.String brachcanshift) {
		this.brachcanshift = brachcanshift;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getBrachcanshift() {
		return brachcanshift;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setCommitmentCityLimit(java.lang.String commitmentCityLimit) {
		this.commitmentCityLimit = commitmentCityLimit;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getCommitmentCityLimit() {
		return commitmentCityLimit;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setExpireCityDate(java.sql.Date expireCityDate) {
		this.expireCityDate = expireCityDate;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.sql.Date getExpireCityDate() {
		return expireCityDate;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setExtensDay(java.lang.String extensDay) {
		this.extensDay = extensDay;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getExtensDay() {
		return extensDay;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setExtensReason(java.lang.String extensReason) {
		this.extensReason = extensReason;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getExtensReason() {
		return extensReason;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseResultName(java.lang.String superviseResultName) {
		this.superviseResultName = superviseResultName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseResultName() {
		return superviseResultName;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseTypeCode(java.lang.String superviseTypeCode) {
		this.superviseTypeCode = superviseTypeCode;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseTypeCode() {
		return superviseTypeCode;
	}

	/**
	 * 设置${field.desc}
	 */
	public void setSuperviseTypeName(java.lang.String superviseTypeName) {
		this.superviseTypeName = superviseTypeName;
	}

	/**
	 * 获取${field.desc}
	 */
	public java.lang.String getSuperviseTypeName() {
		return superviseTypeName;
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

	/**
	 * 设置是否满意名称
	 */
	public void setIsSatisfiedName(java.lang.String IsSatisfiedName) {
		this.IsSatisfiedName = IsSatisfiedName;
	}

	/**
	 * 获取是否满意名称
	 */
	public java.lang.String getIsSatisfiedName() {
		return IsSatisfiedName;
	}

	/**
	 * 设置投诉方式名称
	 */
	public void setProblemModeName(java.lang.String problemModeName) {
		this.problemModeName = problemModeName;
	}

	/**
	 * 获取投诉方式名称
	 */
	public java.lang.String getProblemModeName() {
		return problemModeName;
	}

	/**
	 * 设置完成时间
	 */
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取完成时间
	 */
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置监察时间
	 */
	public void setSuperviseTime(java.sql.Date superviseTime) {
		this.superviseTime = superviseTime;
	}

	/**
	 * 获取监察时间
	 */
	public java.sql.Date getSuperviseTime() {
		return superviseTime;
	}

	/**
	 * 设置监察开始时间
	 */
	public void setBeginJcDate(java.sql.Date beginJcDate) {
		this.beginJcDate = beginJcDate;
	}

	/**
	 * 获取监察开始时间
	 */
	public java.sql.Date getBeginJcDate() {
		return beginJcDate;
	}

	/**
	 * 设置监察结束时间
	 */
	public void setEndJcDate(java.sql.Date endJcDate) {
		this.endJcDate = endJcDate;
	}

	/**
	 * 获取监察结束时间
	 */
	public java.sql.Date getEndJcDate() {
		return endJcDate;
	}
}
