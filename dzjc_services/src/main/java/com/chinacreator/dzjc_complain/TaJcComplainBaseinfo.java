package com.chinacreator.dzjc_complain;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 投诉历史信息
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_complain.taJcComplainBaseinfo", table = "TA_JC_COMPLAIN_BASEINFO", ds = "acceptdata", cache = false)
public class TaJcComplainBaseinfo {
	/**
	 *投诉信息ID，通过seq_complain_baseinfo取值
	 */
	@Column(id = "complain_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String complainId;

	/**
	 *查询码
	 */
	@Column(id = "query_code", datatype = "string64")
	private java.lang.String queryCode;

	/**
	 *受理时间
	 */
	@Column(id = "accept_time", datatype = "date")
	private java.sql.Date acceptTime;

	/**
	 *被投诉人姓名或单位
	 */
	@Column(id = "bycomplained_user_org", datatype = "string128")
	private java.lang.String bycomplainedUserOrg;

	/**
	 *被投诉人职务
	 */
	@Column(id = "bycomplained_user_post", datatype = "string128")
	private java.lang.String bycomplainedUserPost;

	/**
	 *被投诉单位名称
	 */
	@Column(id = "bycomplained_org", datatype = "string128")
	private java.lang.String bycomplainedOrg;

	/**
	 *投诉人姓名
	 */
	@Column(id = "complain_user_name", datatype = "string128")
	private java.lang.String complainUserName;

	/**
	 *投诉人手机
	 */
	@Column(id = "complain_user_mobile", datatype = "string64")
	private java.lang.String complainUserMobile;

	/**
	 *投诉人身份证号码
	 */
	@Column(id = "complain_user_card", datatype = "string64")
	private java.lang.String complainUserCard;

	/**
	 *投诉标题
	 */
	@Column(id = "complain_title", datatype = "string512")
	private java.lang.String complainTitle;

	/**
	 *投诉内容
	 */
	@Column(id = "complain_content", datatype = "string2000")
	private java.lang.String complainContent;

	/**
	 *领导批示意见
	 */
	@Column(id = "leader_idea", datatype = "string1536")
	private java.lang.String leaderIdea;

	/**
	 *登记时间
	 */
	@Column(id = "enregister_time", datatype = "date")
	private java.sql.Date enregisterTime;

	/**
	 *处理状态
	 */
	@Column(id = "state", datatype = "string10")
	private java.lang.String state;

	/**
	 *问题类型
	 */
	@Column(id = "problem_type", datatype = "string64")
	private java.lang.String problemType;

	/**
	 *问题细类
	 */
	@Column(id = "problem_small_type", datatype = "string64")
	private java.lang.String problemSmallType;

	/**
	 *是否满意（Y：满意；M：比较满意 N：不满意）
	 */
	@Column(id = "is_satisfied", datatype = "string64")
	private java.lang.String isSatisfied;

	/**
	 *不满意原因
	 */
	@Column(id = "not_satisfied_reason", datatype = "string2000")
	private java.lang.String notSatisfiedReason;

	/**
	 *最后修改时间
	 */
	@Column(id = "lastmodifytime", datatype = "date")
	private java.sql.Date lastmodifytime;

	/**
	 *Y 公开 ，N 不公开
	 */
	@Column(id = "is_public", datatype = "string64")
	private java.lang.String isPublic;

	/**
	 *处理状态（处理中，已处理）
	 */
	@Column(id = "handle_status", datatype = "string10")
	private java.lang.String handleStatus;

	/**
	 *到期时间
	 */
	@Column(id = "expire_date", datatype = "date")
	private java.sql.Date expireDate;

	/**
	 *受理人ID
	 */
	@Column(id = "accept_user_id", datatype = "string64")
	private java.lang.String acceptUserId;

	/**
	 *下一环节处理人ID
	 */
	@Column(id = "handle_user_id", datatype = "string2000")
	private java.lang.String handleUserId;

	/**
	 *投诉人单位
	 */
	@Column(id = "complain_user_org", datatype = "string256")
	private java.lang.String complainUserOrg;

	/**
	 *投诉人电话
	 */
	@Column(id = "complain_user_phone", datatype = "string64")
	private java.lang.String complainUserPhone;

	/**
	 *回复单位名称
	 */
	@Column(id = "reply_orgname", datatype = "string64")
	private java.lang.String replyOrgname;

	/**
	 *回复单位ID
	 */
	@Column(id = "reply_orgid", datatype = "string64")
	private java.lang.String replyOrgid;

	/**
	 *回复时间
	 */
	@Column(id = "reply_time", datatype = "date")
	private java.sql.Date replyTime;

	/**
	 *回复人ID
	 */
	@Column(id = "reply_user_id", datatype = "string64")
	private java.lang.String replyUserId;

	/**
	 *提交次数
	 */
	@Column(id = "commitnum", datatype = "bigdouble")
	private java.lang.Double commitnum;

	/**
	 *投诉人地址
	 */
	@Column(id = "complain_user_address", datatype = "string128")
	private java.lang.String complainUserAddress;

	/**
	 *被投诉人地址
	 */
	@Column(id = "bycomplained_user_address", datatype = "string128")
	private java.lang.String bycomplainedUserAddress;

	/**
	 *被投诉人电话
	 */
	@Column(id = "bycomplained_user_phone", datatype = "string20")
	private java.lang.String bycomplainedUserPhone;

	/**
	 *投诉方式(字典表ta_dic_problem_mode)
	 */
	@Column(id = "problem_mode", datatype = "string5")
	private java.lang.String problemMode;

	/**
	 *主要诉求
	 */
	@Column(id = "mainappeal", datatype = "string2000")
	private java.lang.String mainappeal;

	/**
	 *过程状态
	 */
	@Column(id = "proessstate", datatype = "string20")
	private java.lang.String proessstate;

	/**
	 *提交次数关联id,取值数据库UUID
	 */
	@Column(id = "correlationid", datatype = "string64")
	private java.lang.String correlationid;

	/**
	 *处理结果是否公开(Y 公开 ，N 不公开)
	 */
	@Column(id = "handleis_public", datatype = "string64")
	private java.lang.String handleisPublic;

	/**
	 *受理编号
	 */
	@Column(id = "caseno", datatype = "string256")
	private java.lang.String caseno;

	/**
	 *办理时限
	 */
	@Column(id = "commitment_limit", datatype = "string256")
	private java.lang.String commitmentLimit;

	/**
	 *是否接收
	 */
	@Column(id = "issurereceive", datatype = "string256")
	private java.lang.String issurereceive;

	/**
	 *当前处理意见ID，方便查询
	 */
	@Column(id = "taskid", datatype = "string64")
	private java.lang.String taskid;

	/**
	 *是否申请延期 'Y'  是 'N'否
	 */
	@Column(id = "is_examine", datatype = "string64")
	private java.lang.String isExamine;

	/**
	 *是否发送预受理短信‘Y’ 已发送  ‘N’未发送
	 */
	@Column(id = "is_preaccepsend", datatype = "string64")
	private java.lang.String isPreaccepsend;

	/**
	 *是否发送受理短信‘Y’ 已发送  ‘N’未发送
	 */
	@Column(id = "is_acceptsend", datatype = "string64")
	private java.lang.String isAcceptsend;

	/**
	 *受理单位ID
	 */
	@Column(id = "accept_org_id", datatype = "string64")
	private java.lang.String acceptOrgId;

	/**
	 *是否向当地部门投诉
	 */
	@Column(id = "islocalsector", datatype = "boolean")
	private java.lang.Boolean islocalsector;

	/**
	 *受理单位名称
	 */
	@Column(id = "accept_org_name", datatype = "string256")
	private java.lang.String acceptOrgName;

	/**
	 *受理单位层级 1 省，2 市，3 区县
	 */
	@Column(id = "accept_org_level", datatype = "string64")
	private java.lang.String acceptOrgLevel;

	/**
	 *确认接收时间
	 */
	@Column(id = "sure_receive_time", datatype = "date")
	private java.sql.Date sureReceiveTime;

	/**
	 *是否准许市州转办交办(Y:是 N:否)
	 */
	@Column(id = "brachcanshift", datatype = "string64")
	private java.lang.String brachcanshift;

	/**
	 *市州要求办理时限
	 */
	@Column(id = "commitment_city_limit", datatype = "string256")
	private java.lang.String commitmentCityLimit;

	/**
	 *市州要求办理时限到期时间
	 */
	@Column(id = "expire_city_date", datatype = "date")
	private java.sql.Date expireCityDate;

	/**
	 *延期天数
	 */
	@Column(id = "extens_day", datatype = "string64")
	private java.lang.String extensDay;

	/**
	 *延期原因
	 */
	@Column(id = "extens_reason", datatype = "string2000")
	private java.lang.String extensReason;

	/**
	 *是否需要监察
	 */
	@Column(id = "is_need_supervise", datatype = "string20")
	private java.lang.String isNeedSupervise;

	/**
	 *最近发牌
	 */
	@Column(id = "last_supervise", datatype = "string64")
	private java.lang.String lastSupervise;

	/**
	 *投诉来源
	 */
	@Column(id = "complain_source", datatype = "string128")
	private java.lang.String complainSource;

	/**
	 *接收单位临时值
	 */
	@Column(id = "accept_org_temp", datatype = "string64")
	private java.lang.String acceptOrgTemp;

	/**
	 *投诉信息ID临时值
	 */
	@Column(id = "complain_id_temp", datatype = "string64")
	private java.lang.String complainIdTemp;

	/**
	 *办理完成时间
	 */
	@Column(id = "end_time", datatype = "date")
	private java.sql.Date endTime;

	/**
	 *录入时选择的接收单位
	 */
	@Column(id = "accept_org_first", datatype = "string64")
	private java.lang.String acceptOrgFirst;

	/**
	 *录入时选择的接收单位名称
	 */
	@Column(id = "accept_org_first_name", datatype = "string128")
	private java.lang.String acceptOrgFirstName;

	/**
	 *工作时间拼接的字符串
	 */
	@Column(id = "work_time", datatype = "string128")
	private java.lang.String workTime;

	/**
	 *节假日天数
	 */
	@Column(id = "holiday_count", datatype = "int")
	private java.lang.Integer holidayCount;

	/**
	 *办理时限数字型，用于监察
	 */
	@Column(id = "process_limit", datatype = "double")
	private java.lang.Double processLimit;

	/**
	 * 设置投诉信息ID，通过seq_complain_baseinfo取值
	 */
	public void setComplainId(java.lang.String complainId) {
		this.complainId = complainId;
	}

	/**
	 * 获取投诉信息ID，通过seq_complain_baseinfo取值
	 */
	public java.lang.String getComplainId() {
		return complainId;
	}

	/**
	 * 设置查询码
	 */
	public void setQueryCode(java.lang.String queryCode) {
		this.queryCode = queryCode;
	}

	/**
	 * 获取查询码
	 */
	public java.lang.String getQueryCode() {
		return queryCode;
	}

	/**
	 * 设置受理时间
	 */
	public void setAcceptTime(java.sql.Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 获取受理时间
	 */
	public java.sql.Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 设置被投诉人姓名或单位
	 */
	public void setBycomplainedUserOrg(java.lang.String bycomplainedUserOrg) {
		this.bycomplainedUserOrg = bycomplainedUserOrg;
	}

	/**
	 * 获取被投诉人姓名或单位
	 */
	public java.lang.String getBycomplainedUserOrg() {
		return bycomplainedUserOrg;
	}

	/**
	 * 设置被投诉人职务
	 */
	public void setBycomplainedUserPost(java.lang.String bycomplainedUserPost) {
		this.bycomplainedUserPost = bycomplainedUserPost;
	}

	/**
	 * 获取被投诉人职务
	 */
	public java.lang.String getBycomplainedUserPost() {
		return bycomplainedUserPost;
	}

	/**
	 * 设置被投诉单位名称
	 */
	public void setBycomplainedOrg(java.lang.String bycomplainedOrg) {
		this.bycomplainedOrg = bycomplainedOrg;
	}

	/**
	 * 获取被投诉单位名称
	 */
	public java.lang.String getBycomplainedOrg() {
		return bycomplainedOrg;
	}

	/**
	 * 设置投诉人姓名
	 */
	public void setComplainUserName(java.lang.String complainUserName) {
		this.complainUserName = complainUserName;
	}

	/**
	 * 获取投诉人姓名
	 */
	public java.lang.String getComplainUserName() {
		return complainUserName;
	}

	/**
	 * 设置投诉人手机
	 */
	public void setComplainUserMobile(java.lang.String complainUserMobile) {
		this.complainUserMobile = complainUserMobile;
	}

	/**
	 * 获取投诉人手机
	 */
	public java.lang.String getComplainUserMobile() {
		return complainUserMobile;
	}

	/**
	 * 设置投诉人身份证号码
	 */
	public void setComplainUserCard(java.lang.String complainUserCard) {
		this.complainUserCard = complainUserCard;
	}

	/**
	 * 获取投诉人身份证号码
	 */
	public java.lang.String getComplainUserCard() {
		return complainUserCard;
	}

	/**
	 * 设置投诉标题
	 */
	public void setComplainTitle(java.lang.String complainTitle) {
		this.complainTitle = complainTitle;
	}

	/**
	 * 获取投诉标题
	 */
	public java.lang.String getComplainTitle() {
		return complainTitle;
	}

	/**
	 * 设置投诉内容
	 */
	public void setComplainContent(java.lang.String complainContent) {
		this.complainContent = complainContent;
	}

	/**
	 * 获取投诉内容
	 */
	public java.lang.String getComplainContent() {
		return complainContent;
	}

	/**
	 * 设置领导批示意见
	 */
	public void setLeaderIdea(java.lang.String leaderIdea) {
		this.leaderIdea = leaderIdea;
	}

	/**
	 * 获取领导批示意见
	 */
	public java.lang.String getLeaderIdea() {
		return leaderIdea;
	}

	/**
	 * 设置登记时间
	 */
	public void setEnregisterTime(java.sql.Date enregisterTime) {
		this.enregisterTime = enregisterTime;
	}

	/**
	 * 获取登记时间
	 */
	public java.sql.Date getEnregisterTime() {
		return enregisterTime;
	}

	/**
	 * 设置处理状态
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * 获取处理状态
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * 设置问题类型
	 */
	public void setProblemType(java.lang.String problemType) {
		this.problemType = problemType;
	}

	/**
	 * 获取问题类型
	 */
	public java.lang.String getProblemType() {
		return problemType;
	}

	/**
	 * 设置问题细类
	 */
	public void setProblemSmallType(java.lang.String problemSmallType) {
		this.problemSmallType = problemSmallType;
	}

	/**
	 * 获取问题细类
	 */
	public java.lang.String getProblemSmallType() {
		return problemSmallType;
	}

	/**
	 * 设置是否满意（Y：满意；M：比较满意 N：不满意）
	 */
	public void setIsSatisfied(java.lang.String isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	/**
	 * 获取是否满意（Y：满意；M：比较满意 N：不满意）
	 */
	public java.lang.String getIsSatisfied() {
		return isSatisfied;
	}

	/**
	 * 设置不满意原因
	 */
	public void setNotSatisfiedReason(java.lang.String notSatisfiedReason) {
		this.notSatisfiedReason = notSatisfiedReason;
	}

	/**
	 * 获取不满意原因
	 */
	public java.lang.String getNotSatisfiedReason() {
		return notSatisfiedReason;
	}

	/**
	 * 设置最后修改时间
	 */
	public void setLastmodifytime(java.sql.Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

	/**
	 * 获取最后修改时间
	 */
	public java.sql.Date getLastmodifytime() {
		return lastmodifytime;
	}

	/**
	 * 设置Y 公开 ，N 不公开
	 */
	public void setIsPublic(java.lang.String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 获取Y 公开 ，N 不公开
	 */
	public java.lang.String getIsPublic() {
		return isPublic;
	}

	/**
	 * 设置处理状态（处理中，已处理）
	 */
	public void setHandleStatus(java.lang.String handleStatus) {
		this.handleStatus = handleStatus;
	}

	/**
	 * 获取处理状态（处理中，已处理）
	 */
	public java.lang.String getHandleStatus() {
		return handleStatus;
	}

	/**
	 * 设置到期时间
	 */
	public void setExpireDate(java.sql.Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * 获取到期时间
	 */
	public java.sql.Date getExpireDate() {
		return expireDate;
	}

	/**
	 * 设置受理人ID
	 */
	public void setAcceptUserId(java.lang.String acceptUserId) {
		this.acceptUserId = acceptUserId;
	}

	/**
	 * 获取受理人ID
	 */
	public java.lang.String getAcceptUserId() {
		return acceptUserId;
	}

	/**
	 * 设置下一环节处理人ID
	 */
	public void setHandleUserId(java.lang.String handleUserId) {
		this.handleUserId = handleUserId;
	}

	/**
	 * 获取下一环节处理人ID
	 */
	public java.lang.String getHandleUserId() {
		return handleUserId;
	}

	/**
	 * 设置投诉人单位
	 */
	public void setComplainUserOrg(java.lang.String complainUserOrg) {
		this.complainUserOrg = complainUserOrg;
	}

	/**
	 * 获取投诉人单位
	 */
	public java.lang.String getComplainUserOrg() {
		return complainUserOrg;
	}

	/**
	 * 设置投诉人电话
	 */
	public void setComplainUserPhone(java.lang.String complainUserPhone) {
		this.complainUserPhone = complainUserPhone;
	}

	/**
	 * 获取投诉人电话
	 */
	public java.lang.String getComplainUserPhone() {
		return complainUserPhone;
	}

	/**
	 * 设置回复单位名称
	 */
	public void setReplyOrgname(java.lang.String replyOrgname) {
		this.replyOrgname = replyOrgname;
	}

	/**
	 * 获取回复单位名称
	 */
	public java.lang.String getReplyOrgname() {
		return replyOrgname;
	}

	/**
	 * 设置回复单位ID
	 */
	public void setReplyOrgid(java.lang.String replyOrgid) {
		this.replyOrgid = replyOrgid;
	}

	/**
	 * 获取回复单位ID
	 */
	public java.lang.String getReplyOrgid() {
		return replyOrgid;
	}

	/**
	 * 设置回复时间
	 */
	public void setReplyTime(java.sql.Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * 获取回复时间
	 */
	public java.sql.Date getReplyTime() {
		return replyTime;
	}

	/**
	 * 设置回复人ID
	 */
	public void setReplyUserId(java.lang.String replyUserId) {
		this.replyUserId = replyUserId;
	}

	/**
	 * 获取回复人ID
	 */
	public java.lang.String getReplyUserId() {
		return replyUserId;
	}

	/**
	 * 设置提交次数
	 */
	public void setCommitnum(java.lang.Double commitnum) {
		this.commitnum = commitnum;
	}

	/**
	 * 获取提交次数
	 */
	public java.lang.Double getCommitnum() {
		return commitnum;
	}

	/**
	 * 设置投诉人地址
	 */
	public void setComplainUserAddress(java.lang.String complainUserAddress) {
		this.complainUserAddress = complainUserAddress;
	}

	/**
	 * 获取投诉人地址
	 */
	public java.lang.String getComplainUserAddress() {
		return complainUserAddress;
	}

	/**
	 * 设置被投诉人地址
	 */
	public void setBycomplainedUserAddress(
			java.lang.String bycomplainedUserAddress) {
		this.bycomplainedUserAddress = bycomplainedUserAddress;
	}

	/**
	 * 获取被投诉人地址
	 */
	public java.lang.String getBycomplainedUserAddress() {
		return bycomplainedUserAddress;
	}

	/**
	 * 设置被投诉人电话
	 */
	public void setBycomplainedUserPhone(java.lang.String bycomplainedUserPhone) {
		this.bycomplainedUserPhone = bycomplainedUserPhone;
	}

	/**
	 * 获取被投诉人电话
	 */
	public java.lang.String getBycomplainedUserPhone() {
		return bycomplainedUserPhone;
	}

	/**
	 * 设置投诉方式(字典表ta_dic_problem_mode)
	 */
	public void setProblemMode(java.lang.String problemMode) {
		this.problemMode = problemMode;
	}

	/**
	 * 获取投诉方式(字典表ta_dic_problem_mode)
	 */
	public java.lang.String getProblemMode() {
		return problemMode;
	}

	/**
	 * 设置主要诉求
	 */
	public void setMainappeal(java.lang.String mainappeal) {
		this.mainappeal = mainappeal;
	}

	/**
	 * 获取主要诉求
	 */
	public java.lang.String getMainappeal() {
		return mainappeal;
	}

	/**
	 * 设置过程状态
	 */
	public void setProessstate(java.lang.String proessstate) {
		this.proessstate = proessstate;
	}

	/**
	 * 获取过程状态
	 */
	public java.lang.String getProessstate() {
		return proessstate;
	}

	/**
	 * 设置提交次数关联id,取值数据库UUID
	 */
	public void setCorrelationid(java.lang.String correlationid) {
		this.correlationid = correlationid;
	}

	/**
	 * 获取提交次数关联id,取值数据库UUID
	 */
	public java.lang.String getCorrelationid() {
		return correlationid;
	}

	/**
	 * 设置处理结果是否公开(Y 公开 ，N 不公开)
	 */
	public void setHandleisPublic(java.lang.String handleisPublic) {
		this.handleisPublic = handleisPublic;
	}

	/**
	 * 获取处理结果是否公开(Y 公开 ，N 不公开)
	 */
	public java.lang.String getHandleisPublic() {
		return handleisPublic;
	}

	/**
	 * 设置受理编号
	 */
	public void setCaseno(java.lang.String caseno) {
		this.caseno = caseno;
	}

	/**
	 * 获取受理编号
	 */
	public java.lang.String getCaseno() {
		return caseno;
	}

	/**
	 * 设置办理时限
	 */
	public void setCommitmentLimit(java.lang.String commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}

	/**
	 * 获取办理时限
	 */
	public java.lang.String getCommitmentLimit() {
		return commitmentLimit;
	}

	/**
	 * 设置是否接收
	 */
	public void setIssurereceive(java.lang.String issurereceive) {
		this.issurereceive = issurereceive;
	}

	/**
	 * 获取是否接收
	 */
	public java.lang.String getIssurereceive() {
		return issurereceive;
	}

	/**
	 * 设置当前处理意见ID，方便查询
	 */
	public void setTaskid(java.lang.String taskid) {
		this.taskid = taskid;
	}

	/**
	 * 获取当前处理意见ID，方便查询
	 */
	public java.lang.String getTaskid() {
		return taskid;
	}

	/**
	 * 设置是否申请延期 'Y'  是 'N'否
	 */
	public void setIsExamine(java.lang.String isExamine) {
		this.isExamine = isExamine;
	}

	/**
	 * 获取是否申请延期 'Y'  是 'N'否
	 */
	public java.lang.String getIsExamine() {
		return isExamine;
	}

	/**
	 * 设置是否发送预受理短信‘Y’ 已发送  ‘N’未发送
	 */
	public void setIsPreaccepsend(java.lang.String isPreaccepsend) {
		this.isPreaccepsend = isPreaccepsend;
	}

	/**
	 * 获取是否发送预受理短信‘Y’ 已发送  ‘N’未发送
	 */
	public java.lang.String getIsPreaccepsend() {
		return isPreaccepsend;
	}

	/**
	 * 设置是否发送受理短信‘Y’ 已发送  ‘N’未发送
	 */
	public void setIsAcceptsend(java.lang.String isAcceptsend) {
		this.isAcceptsend = isAcceptsend;
	}

	/**
	 * 获取是否发送受理短信‘Y’ 已发送  ‘N’未发送
	 */
	public java.lang.String getIsAcceptsend() {
		return isAcceptsend;
	}

	/**
	 * 设置受理单位ID
	 */
	public void setAcceptOrgId(java.lang.String acceptOrgId) {
		this.acceptOrgId = acceptOrgId;
	}

	/**
	 * 获取受理单位ID
	 */
	public java.lang.String getAcceptOrgId() {
		return acceptOrgId;
	}

	/**
	 * 设置是否向当地部门投诉
	 */
	public void setIslocalsector(java.lang.Boolean islocalsector) {
		this.islocalsector = islocalsector;
	}

	/**
	 * 获取是否向当地部门投诉
	 */
	public java.lang.Boolean isIslocalsector() {
		return islocalsector;
	}

	/**
	 * 设置受理单位名称
	 */
	public void setAcceptOrgName(java.lang.String acceptOrgName) {
		this.acceptOrgName = acceptOrgName;
	}

	/**
	 * 获取受理单位名称
	 */
	public java.lang.String getAcceptOrgName() {
		return acceptOrgName;
	}

	/**
	 * 设置受理单位层级 1 省，2 市，3 区县
	 */
	public void setAcceptOrgLevel(java.lang.String acceptOrgLevel) {
		this.acceptOrgLevel = acceptOrgLevel;
	}

	/**
	 * 获取受理单位层级 1 省，2 市，3 区县
	 */
	public java.lang.String getAcceptOrgLevel() {
		return acceptOrgLevel;
	}

	/**
	 * 设置确认接收时间
	 */
	public void setSureReceiveTime(java.sql.Date sureReceiveTime) {
		this.sureReceiveTime = sureReceiveTime;
	}

	/**
	 * 获取确认接收时间
	 */
	public java.sql.Date getSureReceiveTime() {
		return sureReceiveTime;
	}

	/**
	 * 设置是否准许市州转办交办(Y:是 N:否)
	 */
	public void setBrachcanshift(java.lang.String brachcanshift) {
		this.brachcanshift = brachcanshift;
	}

	/**
	 * 获取是否准许市州转办交办(Y:是 N:否)
	 */
	public java.lang.String getBrachcanshift() {
		return brachcanshift;
	}

	/**
	 * 设置市州要求办理时限
	 */
	public void setCommitmentCityLimit(java.lang.String commitmentCityLimit) {
		this.commitmentCityLimit = commitmentCityLimit;
	}

	/**
	 * 获取市州要求办理时限
	 */
	public java.lang.String getCommitmentCityLimit() {
		return commitmentCityLimit;
	}

	/**
	 * 设置市州要求办理时限到期时间
	 */
	public void setExpireCityDate(java.sql.Date expireCityDate) {
		this.expireCityDate = expireCityDate;
	}

	/**
	 * 获取市州要求办理时限到期时间
	 */
	public java.sql.Date getExpireCityDate() {
		return expireCityDate;
	}

	/**
	 * 设置延期天数
	 */
	public void setExtensDay(java.lang.String extensDay) {
		this.extensDay = extensDay;
	}

	/**
	 * 获取延期天数
	 */
	public java.lang.String getExtensDay() {
		return extensDay;
	}

	/**
	 * 设置延期原因
	 */
	public void setExtensReason(java.lang.String extensReason) {
		this.extensReason = extensReason;
	}

	/**
	 * 获取延期原因
	 */
	public java.lang.String getExtensReason() {
		return extensReason;
	}

	/**
	 * 设置是否需要监察
	 */
	public void setIsNeedSupervise(java.lang.String isNeedSupervise) {
		this.isNeedSupervise = isNeedSupervise;
	}

	/**
	 * 获取是否需要监察
	 */
	public java.lang.String getIsNeedSupervise() {
		return isNeedSupervise;
	}

	/**
	 * 设置最近发牌
	 */
	public void setLastSupervise(java.lang.String lastSupervise) {
		this.lastSupervise = lastSupervise;
	}

	/**
	 * 获取最近发牌
	 */
	public java.lang.String getLastSupervise() {
		return lastSupervise;
	}

	/**
	 * 设置投诉来源
	 */
	public void setComplainSource(java.lang.String complainSource) {
		this.complainSource = complainSource;
	}

	/**
	 * 获取投诉来源
	 */
	public java.lang.String getComplainSource() {
		return complainSource;
	}

	/**
	 * 设置接收单位临时值
	 */
	public void setAcceptOrgTemp(java.lang.String acceptOrgTemp) {
		this.acceptOrgTemp = acceptOrgTemp;
	}

	/**
	 * 获取接收单位临时值
	 */
	public java.lang.String getAcceptOrgTemp() {
		return acceptOrgTemp;
	}

	/**
	 * 设置投诉信息ID临时值
	 */
	public void setComplainIdTemp(java.lang.String complainIdTemp) {
		this.complainIdTemp = complainIdTemp;
	}

	/**
	 * 获取投诉信息ID临时值
	 */
	public java.lang.String getComplainIdTemp() {
		return complainIdTemp;
	}

	/**
	 * 设置办理完成时间
	 */
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取办理完成时间
	 */
	public java.sql.Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置录入时选择的接收单位
	 */
	public void setAcceptOrgFirst(java.lang.String acceptOrgFirst) {
		this.acceptOrgFirst = acceptOrgFirst;
	}

	/**
	 * 获取录入时选择的接收单位
	 */
	public java.lang.String getAcceptOrgFirst() {
		return acceptOrgFirst;
	}

	/**
	 * 设置录入时选择的接收单位名称
	 */
	public void setAcceptOrgFirstName(java.lang.String acceptOrgFirstName) {
		this.acceptOrgFirstName = acceptOrgFirstName;
	}

	/**
	 * 获取录入时选择的接收单位名称
	 */
	public java.lang.String getAcceptOrgFirstName() {
		return acceptOrgFirstName;
	}

	/**
	 * 设置工作时间拼接的字符串
	 */
	public void setWorkTime(java.lang.String workTime) {
		this.workTime = workTime;
	}

	/**
	 * 获取工作时间拼接的字符串
	 */
	public java.lang.String getWorkTime() {
		return workTime;
	}

	/**
	 * 设置节假日天数
	 */
	public void setHolidayCount(java.lang.Integer holidayCount) {
		this.holidayCount = holidayCount;
	}

	/**
	 * 获取节假日天数
	 */
	public java.lang.Integer getHolidayCount() {
		return holidayCount;
	}

	/**
	 * 设置办理时限数字型，用于监察
	 */
	public void setProcessLimit(java.lang.Double processLimit) {
		this.processLimit = processLimit;
	}

	/**
	 * 获取办理时限数字型，用于监察
	 */
	public java.lang.Double getProcessLimit() {
		return processLimit;
	}
}
