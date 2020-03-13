package com.chinacreator.dzjc_complain.web.rest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Constant;
import com.chinacreator.dzjc_complain.OrgUserInfo;
import com.chinacreator.dzjc_complain.Organization;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_complain.TaJcComplainAttachinfo;
import com.chinacreator.dzjc_complain.TaJcComplainBasecount;
import com.chinacreator.dzjc_complain.TaJcComplainBaseinfo;
import com.chinacreator.dzjc_complain.TaJcComplainBaseinfoSub;
import com.chinacreator.dzjc_complain.TaJcComplainTasklist;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.EntityUtil;
import com.chinacreator.util.HolidayDateUtil;
import com.chinacreator.util.RoleUtil;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcComplainBaseinfoController {

	private String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 默认工作日
	private static String WORK_DAY = ConfigManager.getInstance().getConfig("work_day");
	/**
	 * 删除单条投诉信息
	 * 
	 * @param complainId
	 */
	@DELETE
	@Path("taJcComplainBaseinfo/{complainId}")
	public void delete(@PathParam(value = "complainId") java.lang.String complainId) {
		TaJcComplainBaseinfo entity = new TaJcComplainBaseinfo();
		entity.setComplainId(complainId);
		DaoFactory.create(TaJcComplainBaseinfo.class).delete(entity);
	}

	/**
	 * 批量删除投诉信息
	 * 
	 * @param ids
	 */
	@DELETE
	@Path("taJcComplainBaseinfo")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(TaJcComplainBaseinfo.class).deleteBatch(ids);
	}

	/**
	 * 更新投诉信息
	 * 
	 * @param entity
	 * @return
	 */
	@POST
	@Path("taJcComplainBaseinfo/{complainId}")
	public TaJcComplainBaseinfo update(TaJcComplainBaseinfo entity) {
		return entity;
	}

	/**
	 * 根据ID获取投诉信息
	 * 
	 * @param complainId
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/{complainId}")
	public TaJcComplainBaseinfo get(@PathParam(value = "complainId") java.lang.String complainId) {

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).getSession()
				.selectOne("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectByID2", complainId);

		if (entity != null) {
			String org_id = entity.getBycomplainedUserAddress();
			List<Orgview> orgList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgAndPi", org_id);

			if (orgList.size() > 0) {

				String orgLongName = "";
				for (Orgview orgView : orgList) {
					if (orgView.getName().indexOf("本级") == -1) {
						orgLongName += orgView.getName();
					}
				}
				entity.setBycomplainedUserAddress(orgLongName);
			}

			// 获取是否满意（Y：满意；M：比较满意 ；N：不满意）
			String isSatisfied = entity.getIsSatisfied();
			if ("Y".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("满意");
			} else if ("M".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("比较满意");
			} else if ("N".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("不满意");
			}

			// 获取投诉方式
			String problemMode = entity.getProblemMode();
			if ("1".equals(problemMode)) {
				entity.setProblemMode("网络");
			} else if ("2".equals(problemMode)) {
				entity.setProblemMode("电话");
			} else if ("3".equals(problemMode)) {
				entity.setProblemMode("来访");
			} else if ("4".equals(problemMode)) {
				entity.setProblemMode("函件");
			} else if ("5".equals(problemMode)) {
				entity.setProblemMode("批办");
			}

		}

		return entity;
	}

	/**
	 * 新增录入投诉信息
	 * 
	 * @param entity
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@POST
	@Path("taJcComplainBaseinfo")
	public TaJcComplainBaseinfo insert(TaJcComplainBaseinfo entity) throws ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException {

		if(StringUtils.isNotBlank(entity.getAcceptOrgTemp())){
			entity.setAcceptOrgId(entity.getAcceptOrgTemp());
		}
		if(StringUtils.isNotBlank(entity.getComplainIdTemp())){
			entity.setComplainId(entity.getComplainIdTemp());
		}
		// 自定义新增投诉录入
		entity = this.customInsert(entity);

		return entity;
	}

	/**
	 * 自定义新增投诉录入
	 * @param entity
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	private TaJcComplainBaseinfo customInsert(TaJcComplainBaseinfo entity) {
		// 判断区域：1省，2市，3区县
		String acceptOrgId = entity.getAcceptOrgId();
		OrgviewController view = new OrgviewController();
		Orgview orgView = view.get(acceptOrgId);
		String orgCode = orgView.getCode();
		if ("99".equals(orgCode.substring(2, 4))) {
			entity.setAcceptOrgLevel("1");
		} else if ("00".equals(orgCode.substring(4, 6)) || "01".equals(orgCode.substring(4, 6))) {
			entity.setAcceptOrgLevel("2");
		} else {
			entity.setAcceptOrgLevel("3");
		}
		//设置录入时选择的接收单位
		entity.setAcceptOrgFirst(acceptOrgId);
		entity.setAcceptOrgFirstName(orgView.getName());
		entity.setAcceptOrgName(orgView.getName());
		//在监察系统录入的，投诉来源默认为线下投诉
		entity.setComplainSource(Constant.COMPLAIN_XIANXIA);
		//投诉人看到的状态为已提交
		entity.setState(Constant.COMPLAIN_YITIJIAO);
		//投诉状态为预受理
		entity.setProessstate(Constant.COMPLAIN_YUSHOULI);
		//处理状态为预受理
		entity.setHandleStatus(Constant.COMPLAIN_YUSHOULI);
		// 生成投诉信息主键UUID
		if(StringUtils.isBlank(entity.getComplainIdTemp())){
			new EntityUtil().entityPK(entity);
		}
		// 提交次数关联id,取值数据库UUID
		entity.setCorrelationid(StringUtil.getUUID());
		int rows = DaoFactory.create(TaJcComplainBaseinfo.class).getSession()
				.insert("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.customInsert", entity);
		if (rows > 0) {// 记录投诉次数
			TaJcComplainBasecount countEntity = new TaJcComplainBasecount();
			new EntityUtil().entityPK(countEntity);// 生成投诉次数主键UUID
			countEntity.setComplainId(entity.getComplainId());
			countEntity.setCorrelationid(entity.getCorrelationid());
			countEntity.setBasecount("1");
			// 记录投诉次数
			new TaJcComplainBasecountController().insert(countEntity);
		}
		return entity;
	}

	/**
	 * 自定义受理，不予受理，交办转办
	 * 
	 * @param complainId
	 * @param accept
	 * @param handleIdea
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Path("taJcComplainBaseinfo/update")
	public TaJcComplainBaseinfo zdyUpdate(Map<String, Object> map) throws ParseException {

		String complainId = (String) map.get("complainId");// 投诉编号
		String accept = (String) map.get("accept");// 是否受理
		String brachcanshift = (String) map.get("brachcanshift");// 是否准许市州转办交办
		String caseno = (String) map.get("caseno");// 受理编号
		Integer commitmentLimits = (Integer) map.get("commitmentLimit");// 办理时限
		String commitmentLimit = commitmentLimits + "";
		String expireDate = (String) map.get("expireDate");// 到期时间
		String chulitype = (String) map.get("chulitype");// 处理方式
		String handleUserId = (String) map.get("handleUserId");// 执行人
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		String userId = StringUtil.getUserInfo().get("id");// 用户ID
		String userName = StringUtil.getUserInfo().get("name");// 用户名称

		// 用户所在机构
		String orgId = StringUtil.getUserInfo().get("orgId");
		Orgview orgInfo = DaoFactory.create(Orgview.class).selectByID(orgId);

		// 机构名称
		String orgName = null;
		if (orgInfo != null) {
			orgName = orgInfo.getName();
		}

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);
		if (entity != null) {

			entity.setBrachcanshift(brachcanshift);
			entity.setCaseno(caseno);
			entity.setCommitmentLimit(commitmentLimit);
			entity.setLastmodifytime(new java.sql.Date(new Date().getTime()));// 最后修改时间

			entity.setLeaderIdea(handleIdea);// 批示意见
			entity.setReplyUserId(userId);// 回复人ID
			entity.setReplyOrgid(orgId);//回复单位ID
			entity.setReplyOrgname(orgName);// 回复单位名称
			entity.setReplyTime(new java.sql.Date(new Date().getTime()));// 回复时间

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}

			if (expireDate != null) {

				if (expireDate.indexOf("/") != -1) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					entity.setExpireDate(new java.sql.Date(sdf.parse(expireDate).getTime()));

				} else {
					entity.setExpireDate(new java.sql.Date(sdf.parse(expireDate).getTime()));
				}

			}

			// 受理，交办转办
			if ("yes".equalsIgnoreCase(accept)) {

				// 获取转办交办人机构编号
				List<OrgUserInfo> userList = null;
				String handleOrgId = null;
				if (handleUserId != null) {

					try {
						@SuppressWarnings("unchecked")
						List<Object> objectList = RestUtils.createRestTemplate().getForObject(
								url + "/uop/v1/users/" + handleUserId + "/orgs?categoryId=10-Z", List.class);

						// 转换成对象集合
						userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);
						handleOrgId = userList.get(0).getId();//转办交办人机构编号

					} catch (Exception e) {
						throw new RuntimeException("用户获取失败");
					}
				}

				entity.setState("正在办理");
				entity.setAcceptUserId(userId);// 受理人ID
				entity.setAcceptTime(new java.sql.Date(new Date().getTime()));// 受理时间
				entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数

				// 省本级自办
				if ("0".equals(chulitype) && "1".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus("省级自办中");
					entity.setProessstate("待提出办理意见");
					entity.setHandleUserId(userId);//自办

					// 市本级自办
				} else if ("0".equals(chulitype) && "2".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus("市级自办中");
					entity.setProessstate("待提出办理意见");
					entity.setHandleUserId(userId);//自办

					// 区县级自办
				} else if ("0".equals(chulitype) && "3".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus("区县自办中");
					entity.setProessstate("待提出办理意见");
					entity.setHandleUserId(userId);//自办

					// 省级转办
				} else if ("1".equals(chulitype) && "1".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus("省级转办中");
					entity.setProessstate("省直待接收");
					entity.setHandleUserId(handleUserId);//转办人
					entity.setAcceptOrgId(handleOrgId);//转办机构

					// 市级转办
				} else if ("1".equals(chulitype) && "2".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus("市级转办中");
					entity.setProessstate("市州待接收");
					entity.setHandleUserId(handleUserId);//转办人
					entity.setAcceptOrgId(handleOrgId);//转办机构

					// 省级交办
				} else if ("2".equals(chulitype) && "1".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus("省级交办中");
					entity.setProessstate("市州待接收");
					entity.setHandleUserId(handleUserId);//交办人
					entity.setAcceptOrgId(handleOrgId);//交办机构

					// 市级交办
				} else if ("2".equals(chulitype) && "2".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus("市级交办中");
					entity.setProessstate("区县待回复");
					entity.setHandleUserId(handleUserId);//交办人
					entity.setAcceptOrgId(handleOrgId);//交办机构
				}

				// 不予受理
			} else {
				entity.setHandleStatus("不予自办");
				entity.setState("不予受理");
				entity.setProessstate("不予受理");
			}

			// 投诉处理意见
			TaJcComplainTasklist taskEntity = new TaJcComplainTasklist();
			new EntityUtil().entityPK(taskEntity);// 生成处理意见主键UUID

			entity.setTaskid(taskEntity.getTaskId());

			// 写入受理
			int rows = DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);

			if (rows > 0) {

				taskEntity.setComplainId(entity.getComplainId());

				if ("yes".equals(accept)) {
					taskEntity.setTacheName("受理审核");
				} else {
					taskEntity.setTacheName("不予受理");
				}

				taskEntity.setHandleOrgId(orgId);// 处理人单位ID
				taskEntity.setHandleOrgName(orgName);// 处理人单位名称
				taskEntity.setHandleUserId(userId);// 处理人ID
				taskEntity.setHandleUserName(userName);// 处理人姓名
				taskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
				taskEntity.setHandleIdea(handleIdea);// 处理意见

				DaoFactory.create(TaJcComplainTasklist.class).insert(taskEntity);
			}
		}

		return entity;
	}

	/**
	 * 提交审核
	 * 
	 * @param complainId
	 * @param accept
	 * @param handleIdea
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Path("taJcComplainBaseinfo/commit")
	public TaJcComplainBaseinfo zdyCommit(Map<String, Object> map) throws ParseException {

		String complainId = (String) map.get("complainId");// 投诉编号
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		String pass = (String) map.get("pass");// 是否通过
		String userId = StringUtil.getUserInfo().get("id");// 用户ID
		String userName = StringUtil.getUserInfo().get("name");// 用户名称

		// 用户所在机构
		String orgId = StringUtil.getUserInfo().get("orgId");
		Orgview orgInfo = DaoFactory.create(Orgview.class).selectByID(orgId);

		// 机构名称
		String orgName = null;
		if (orgInfo != null) {
			orgName = orgInfo.getName();
		}

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);
		if (entity != null) {

			// 最后修改时间
			entity.setLastmodifytime(new java.sql.Date(new Date().getTime()));

			entity.setLeaderIdea(handleIdea);// 批示意见
			entity.setReplyUserId(userId);// 回复人ID
			entity.setReplyOrgid(orgId);//回复单位ID
			entity.setReplyOrgname(orgName);// 回复单位名称
			entity.setReplyTime(new java.sql.Date(new Date().getTime()));// 回复时间

			// 查询正式盖章扫描件数量
			TaJcComplainAttachinfo attach = new TaJcComplainAttachinfo();
			attach.setComplainId(complainId + "_zhengshi");// 正式盖章扫描件关联的投诉ID会带_zhengshi后缀
			List<TaJcComplainAttachinfo> attachList = DaoFactory.create(TaJcComplainAttachinfo.class).select(attach);

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}

			// 过程状态
			String currentstate = entity.getProessstate();

			// 有正式盖章扫描件才能提交
			if (attachList.size() > 0) {

				if ("待提出办理意见".equals(currentstate)) {

					entity.setHandleStatus("提交自办中");
					entity.setState("正在办理");
					entity.setProessstate("待办理结果审核");
					entity.setHandleUserId(userId);

				} else if ("待办理结果审核".equals(currentstate)) {

					if ("Y".equalsIgnoreCase(pass)) {// 通过
						entity.setHandleStatus("自办完成");
						entity.setState("确认完成");
						entity.setProessstate("确认完成");
						entity.setHandleUserId(null);

					} else {// 不通过

						String teskId = entity.getTaskid();
						TaJcComplainTasklist oldTaskEntity = DaoFactory.create(TaJcComplainTasklist.class).selectByID(
								teskId);

						if ("省直提办理结果回复意见".equals(oldTaskEntity.getTacheName())) {
							entity.setProessstate("省直待回复");

						} else if ("市州提办理结果回复意见".equals(oldTaskEntity.getTacheName())) {
							entity.setProessstate("市州待回复");

						} else if ("市州办理结果审核".equals(oldTaskEntity.getTacheName())) {
							entity.setProessstate("市州待审核");

						} else {
							entity.setProessstate("待提出办理意见");
						}

						entity.setHandleStatus("自办中");
						entity.setState("正在办理");
						entity.setHandleUserId(oldTaskEntity.getHandleUserId());

					}

				}

				// 投诉处理意见
				TaJcComplainTasklist newTaskEntity = new TaJcComplainTasklist();
				new EntityUtil().entityPK(newTaskEntity);// 生成处理意见主键UUID

				entity.setTaskid(newTaskEntity.getTaskId());
				entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数

				// 写入受理
				int rows = DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);

				if (rows > 0) {

					newTaskEntity.setComplainId(entity.getComplainId());

					if ("待提出办理意见".equals(currentstate)) {
						newTaskEntity.setTacheName("提办理结果回复意见");
					} else if ("待办理结果审核".equals(currentstate)) {
						newTaskEntity.setTacheName("办理结果审核");
					}

					newTaskEntity.setHandleOrgId(orgId);// 处理人单位ID
					newTaskEntity.setHandleOrgName(orgName);// 处理人单位名称
					newTaskEntity.setHandleUserId(userId);// 处理人ID
					newTaskEntity.setHandleUserName(userName);// 处理人姓名
					newTaskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
					newTaskEntity.setHandleIdea(handleIdea);// 处理意见

					DaoFactory.create(TaJcComplainTasklist.class).insert(newTaskEntity);
				}

			} else {
				return null;
			}

		}
		return entity;
	}

	/**
	 * 预受理
	 * 
	 * @param complainId
	 * @param accept
	 * @param handleIdea
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Path("taJcComplainBaseinfo/updateYsl")
	public TaJcComplainBaseinfo updateYsl(Map<String, Object> map) throws ParseException {

		String complainId = (String) map.get("complainId");// 投诉编号
		String accept = (String) map.get("accept");// 是否受理
		String caseno = (String) map.get("caseno");// 受理编号
		Integer commitmentLimits = (Integer) map.get("commitmentLimit");// 办理时限
		String commitmentLimit = commitmentLimits + "";
		String expireDate = (String) map.get("expireDate");// 到期时间
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		String userId = StringUtil.getUserInfo().get("id");// 用户ID
		String userName = StringUtil.getUserInfo().get("name");// 用户名称

		// 用户所在机构
		String orgId = StringUtil.getUserInfo().get("orgId");
		Orgview orgInfo = DaoFactory.create(Orgview.class).selectByID(orgId);

		// 机构名称
		String orgName = null;
		if (orgInfo != null) {
			orgName = orgInfo.getName();
		}

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);
		if (entity != null) {
			entity.setCaseno(caseno);
			entity.setCommitmentLimit(commitmentLimit);
			if (StringUtils.isNotBlank(commitmentLimit)) {
				entity.setProcessLimit(Double.valueOf(commitmentLimit));//办理时限数字型，用于监察
			}
			entity.setLastmodifytime(new java.sql.Date(new Date().getTime()));// 最后修改时间

			entity.setLeaderIdea(handleIdea);// 批示意见
			entity.setReplyUserId(userId);// 回复人ID
			entity.setReplyOrgid(orgId);//回复单位ID
			entity.setReplyOrgname(orgName);// 回复单位名称
			entity.setReplyTime(new java.sql.Date(new Date().getTime()));// 回复时间

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}

			if (expireDate != null) {
				if (expireDate.indexOf("/") != -1) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					entity.setExpireDate(new java.sql.Date(sdf.parse(expireDate).getTime()));
				} else {
					entity.setExpireDate(new java.sql.Date(sdf.parse(expireDate).getTime()));
				}
			}

			// 预受理
			if ("yes".equalsIgnoreCase(accept)) {
				entity.setHandleStatus(Constant.COMPLAIN_DAISHOULI);
				entity.setProessstate(Constant.COMPLAIN_DAISHOULI);
				entity.setAcceptUserId(userId);// 受理人ID
				entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数
			// 不予受理
			} else {
				entity.setHandleStatus(Constant.COMPLAIN_BUYUSHOULI);
				entity.setState(Constant.COMPLAIN_BUYUSHOULI);
				entity.setProessstate(Constant.COMPLAIN_BUYUSHOULI);
			}

			// 投诉处理意见
			TaJcComplainTasklist taskEntity = new TaJcComplainTasklist();
			new EntityUtil().entityPK(taskEntity);// 生成处理意见主键UUID

			entity.setTaskid(taskEntity.getTaskId());

			// 写入受理
			int rows = DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);
			if (rows > 0) {
				taskEntity.setComplainId(entity.getComplainId());
				if ("yes".equals(accept)) {
					taskEntity.setTacheName("预受理");
				} else {
					taskEntity.setTacheName("不予受理");
				}
				taskEntity.setHandleOrgId(orgId);// 处理人单位ID
				taskEntity.setHandleOrgName(orgName);// 处理人单位名称
				taskEntity.setHandleUserId(userId);// 处理人ID
				taskEntity.setHandleUserName(userName);// 处理人姓名
				taskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
				taskEntity.setHandleIdea(handleIdea);// 处理意见

				DaoFactory.create(TaJcComplainTasklist.class).insert(taskEntity);
			}
		}
		return entity;
	}
	
	/**
	 * 受理，不予受理
	 * 
	 * @param complainId
	 * @param accept
	 * @param handleIdea
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Path("taJcComplainBaseinfo/updateSl")
	public TaJcComplainBaseinfo updateSl(Map<String, Object> map) throws ParseException {

		String complainId = (String) map.get("complainId");// 投诉编号
		String accept = (String) map.get("accept");// 是否受理
		String chulitype = (String) map.get("chulitype");// 处理方式
		String handleUserId = (String) map.get("handleUserId");// 执行人
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		String userId = StringUtil.getUserInfo().get("id");// 用户ID
		String userName = StringUtil.getUserInfo().get("name");// 用户名称

		// 用户所在机构
		String orgId = StringUtil.getUserInfo().get("orgId");
		Orgview orgInfo = DaoFactory.create(Orgview.class).selectByID(orgId);

		// 机构名称
		String orgName = null;
		if (orgInfo != null) {
			orgName = orgInfo.getName();
		}

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);
		if (entity != null) {
			entity.setLastmodifytime(new java.sql.Date(new Date().getTime()));// 最后修改时间

			entity.setLeaderIdea(handleIdea);// 批示意见
			entity.setReplyUserId(userId);// 回复人ID
			entity.setReplyOrgid(orgId);//回复单位ID
			entity.setReplyOrgname(orgName);// 回复单位名称
			entity.setReplyTime(new java.sql.Date(new Date().getTime()));// 回复时间

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}

			// 受理，交办转办
			if ("yes".equalsIgnoreCase(accept)) {
				// 获取转办交办人机构编号
				List<OrgUserInfo> userList = null;
				String handleOrgId = null;
				String handelOrgName = null;
				if (StringUtils.isNotBlank(handleUserId)) {
					try {
						@SuppressWarnings("unchecked")
						List<Object> objectList = RestUtils.createRestTemplate().getForObject(
								url + "/uop/v1/users/" + handleUserId + "/orgs?categoryId=10-Z", List.class);
						// 转换成对象集合
						userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);
						handleOrgId = userList.get(0).getId();//转办交办人机构id
						handelOrgName = userList.get(0).getName();//转办交办人机构名称
					} catch (Exception e) {
						throw new RuntimeException("用户获取失败");
					}
				}
				entity.setState(Constant.COMPLAIN_ZHENGZAIBANLI);
				entity.setAcceptUserId(userId);// 受理人ID
				entity.setAcceptTime(new java.sql.Date(new Date().getTime()));// 受理时间
				entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数

				// 省本级自办
				if ("0".equals(chulitype) && "1".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_SHENGJIZIBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIHUIFU);
					entity.setHandleUserId(userId);//自办

					// 市本级自办
				} else if ("0".equals(chulitype) && "2".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_SHIJIZIBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIHUIFU);
					entity.setHandleUserId(userId);//自办

					// 区县级自办
				} else if ("0".equals(chulitype) && "3".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_QUXIANJIZIBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIHUIFU);
					entity.setHandleUserId(userId);//自办

					// 省级转办
				} else if ("1".equals(chulitype) && "1".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_SHENGJIZHUANBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIJIESHOU);
					entity.setHandleUserId(handleUserId);//转办人
					entity.setAcceptOrgId(handleOrgId);//转办机构id
					entity.setAcceptOrgName(handelOrgName);//转办机构名称

					// 市级转办
				} else if ("1".equals(chulitype) && "2".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_SHIJIZHUANBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIJIESHOU);
					entity.setHandleUserId(handleUserId);//转办人
					entity.setAcceptOrgId(handleOrgId);//转办机构
					entity.setAcceptOrgName(handelOrgName);//转办机构名称

					// 区县级转办
				} else if ("1".equals(chulitype) && "3".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_QUXIANJIZHUANBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIJIESHOU);
					entity.setHandleUserId(handleUserId);//转办人
					entity.setAcceptOrgId(handleOrgId);//转办机构
					entity.setAcceptOrgName(handelOrgName);//转办机构名称

					// 省级交办
				} else if ("2".equals(chulitype) && "1".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_SHENGJIJIAOBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIJIESHOU);
					entity.setHandleUserId(handleUserId);//交办人
					entity.setAcceptOrgId(handleOrgId);//交办机构
					entity.setAcceptOrgName(handelOrgName);//交办机构名称

					// 市级交办
				} else if ("2".equals(chulitype) && "2".equals(entity.getAcceptOrgLevel())) {
					entity.setHandleStatus(Constant.COMPLAIN_SHIJIJIAOBANZHONG);
					entity.setProessstate(Constant.COMPLAIN_DAIJIESHOU);
					entity.setHandleUserId(handleUserId);//交办人
					entity.setAcceptOrgId(handleOrgId);//交办机构
					entity.setAcceptOrgName(handelOrgName);//交办机构名称
				}
			// 不予受理
			} else {
				entity.setHandleStatus(Constant.COMPLAIN_BUYUSHOULI);
				entity.setState(Constant.COMPLAIN_BUYUSHOULI);
				entity.setProessstate(Constant.COMPLAIN_BUYUSHOULI);
			}

			// 投诉处理意见
			TaJcComplainTasklist taskEntity = new TaJcComplainTasklist();
			new EntityUtil().entityPK(taskEntity);// 生成处理意见主键UUID

			entity.setTaskid(taskEntity.getTaskId());

			// 写入受理
			int rows = DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);

			if (rows > 0) {
				taskEntity.setComplainId(entity.getComplainId());
				if ("yes".equals(accept)) {
					taskEntity.setTacheName("受理审核");
				} else {
					taskEntity.setTacheName("不予受理");
				}

				taskEntity.setHandleOrgId(orgId);// 处理人单位ID
				taskEntity.setHandleOrgName(orgName);// 处理人单位名称
				taskEntity.setHandleUserId(userId);// 处理人ID
				taskEntity.setHandleUserName(userName);// 处理人姓名
				taskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
				taskEntity.setHandleIdea(handleIdea);// 处理意见

				DaoFactory.create(TaJcComplainTasklist.class).insert(taskEntity);
			}
		}

		return entity;
	}
	
	/**
	 * 确认接收
	 * 
	 * @param complainId
	 * @param accept
	 * @param handleIdea
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Path("taJcComplainBaseinfo/updateJs")
	public TaJcComplainBaseinfo updateJs(Map<String, Object> map) throws ParseException {

		String complainId = (String) map.get("complainId");// 投诉编号
		String accept = (String) map.get("accept");// 是否受理
		String chulitype = (String) map.get("chulitype");// 处理方式
		String handleUserId = (String) map.get("handleUserId");// 执行人
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		String userId = StringUtil.getUserInfo().get("id");// 用户ID
		String userName = StringUtil.getUserInfo().get("name");// 用户名称

		// 用户所在机构
		String orgId = StringUtil.getUserInfo().get("orgId");
		Orgview orgInfo = DaoFactory.create(Orgview.class).selectByID(orgId);

		// 机构名称
		String orgName = null;
		if (orgInfo != null) {
			orgName = orgInfo.getName();
		}

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);
		if (entity != null) {
			entity.setLastmodifytime(new java.sql.Date(new Date().getTime()));// 最后修改时间

			entity.setLeaderIdea(handleIdea);// 批示意见
			entity.setReplyUserId(userId);// 回复人ID
			entity.setReplyOrgid(orgId);//回复单位ID
			entity.setReplyOrgname(orgName);// 回复单位名称
			entity.setReplyTime(new java.sql.Date(new Date().getTime()));// 回复时间

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}

			// 受理，交办转办
			if ("yes".equalsIgnoreCase(accept)) {
				// 获取转办交办人机构编号
				List<OrgUserInfo> userList = null;
				String handleOrgId = null;
				String handleOrgName = null;
				if (handleUserId != null) {
					try {
						@SuppressWarnings("unchecked")
						List<Object> objectList = RestUtils.createRestTemplate().getForObject(
								url + "/uop/v1/users/" + handleUserId + "/orgs?categoryId=10-Z", List.class);
						// 转换成对象集合
						userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);
						handleOrgId = userList.get(0).getId();//转办交办人机构id
						handleOrgName = userList.get(0).getName();//转办交办人机构名称
					} catch (Exception e) {
						throw new RuntimeException("用户获取失败");
					}
				}
				entity.setState(Constant.COMPLAIN_ZHENGZAIBANLI);
				entity.setHandleUserId(userId);//自办
				entity.setAcceptUserId(userId);// 受理人ID
				entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数

				// 自办
				if ("0".equals(chulitype)) {
					entity.setProessstate(Constant.COMPLAIN_DAIHUIFU);
					entity.setHandleUserId(userId);//自办
				} 
				// 转办
				else if ("1".equals(chulitype)) {
					entity.setProessstate(Constant.COMPLAIN_DAIJIESHOU);
					entity.setHandleUserId(handleUserId);//转办人
					entity.setAcceptOrgId(handleOrgId);//转办机构id
					entity.setAcceptOrgName(handleOrgName);//转办机构名称
				} 
				//交办
				else if ("2".equals(chulitype)) {
					entity.setProessstate(Constant.COMPLAIN_DAIJIESHOU);
					entity.setHandleUserId(handleUserId);//交办人
					entity.setAcceptOrgId(handleOrgId);//交办机构
					entity.setAcceptOrgName(handleOrgName);//转办机构名称
				}
			// 不予受理
			} else {
				entity.setHandleStatus(Constant.COMPLAIN_BUYUSHOULI);
				entity.setState(Constant.COMPLAIN_BUYUSHOULI);
				entity.setProessstate(Constant.COMPLAIN_BUYUSHOULI);
			}

			// 投诉处理意见
			TaJcComplainTasklist taskEntity = new TaJcComplainTasklist();
			new EntityUtil().entityPK(taskEntity);// 生成处理意见主键UUID

			entity.setTaskid(taskEntity.getTaskId());

			// 写入受理
			int rows = DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);

			if (rows > 0) {
				taskEntity.setComplainId(entity.getComplainId());
				if ("yes".equals(accept)) {
					taskEntity.setTacheName("确认接收");
				} else {
					taskEntity.setTacheName("不予受理");
				}

				taskEntity.setHandleOrgId(orgId);// 处理人单位ID
				taskEntity.setHandleOrgName(orgName);// 处理人单位名称
				taskEntity.setHandleUserId(userId);// 处理人ID
				taskEntity.setHandleUserName(userName);// 处理人姓名
				taskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
				taskEntity.setHandleIdea(handleIdea);// 处理意见

				DaoFactory.create(TaJcComplainTasklist.class).insert(taskEntity);
			}
		}

		return entity;
	}
	
	/**
	 * 提交回复
	 * 
	 * @param complainId
	 * @param accept
	 * @param handleIdea
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Path("taJcComplainBaseinfo/updateHf")
	public TaJcComplainBaseinfo updateHf(Map<String, Object> map) throws ParseException {

		String complainId = (String) map.get("complainId");// 投诉编号
		String accept = (String) map.get("accept");// 是否受理
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		Map<String,String> userMap = getUserInfo();
		String userId = userMap.get("id");// 用户ID
		String userName = userMap.get("name");// 用户名称
		String orgId = userMap.get("orgId");// 单位id
		String orgCode = userMap.get("orgCode");// 单位编码
		String orgName = userMap.get("orgName");// 单位名称
		String orgLevel = userMap.get("orgLevel");// 单位层级

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);
		if (entity != null) {
			entity.setLastmodifytime(new java.sql.Date(new Date().getTime()));// 最后修改时间

			entity.setLeaderIdea(handleIdea);// 批示意见
			entity.setReplyUserId(userId);// 回复人ID
			entity.setReplyOrgid(orgId);//回复单位ID
			entity.setReplyOrgname(orgName);// 回复单位名称
			entity.setReplyTime(new java.sql.Date(new Date().getTime()));// 回复时间

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}

			// 提交
			if ("yes".equalsIgnoreCase(accept)) {
				if(orgCode != null){
					if("1".equals(orgLevel)){
						entity.setProessstate(Constant.COMPLAIN_DAISHENGJISHENHE);
					}
					else if("2".equals(orgLevel)){
						entity.setProessstate(Constant.COMPLAIN_DAISHIJISHENHE);
					}
					else if("3".equals(orgLevel)){
						entity.setProessstate(Constant.COMPLAIN_DAIQUXIANJISHENHE);
					}
				}
				entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数
			} 

			// 投诉处理意见
			TaJcComplainTasklist taskEntity = new TaJcComplainTasklist();
			new EntityUtil().entityPK(taskEntity);// 生成处理意见主键UUID

			entity.setTaskid(taskEntity.getTaskId());

			// 写入受理
			int rows = DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);

			if (rows > 0) {
				taskEntity.setComplainId(entity.getComplainId());
				if ("yes".equals(accept)) {
					String tacheName = "";
					if("1".equals(orgLevel)){
						if(orgName.indexOf("优化办") >= 0){
							tacheName = "省级回复";
						}
						else {
							tacheName = "省直回复";
						}
					}
					else if("2".equals(orgLevel)){
						if(orgName.indexOf("优化办") >= 0){
							tacheName = "市级回复";
						}
						else {
							tacheName = "市直回复";
						}
					}
					else if("3".equals(orgLevel)){
						if(orgName.indexOf("优化办") >= 0){
							tacheName = "区县级回复";
						}
						else {
							tacheName = "区县回复";
						}
					}
					taskEntity.setTacheName(tacheName);
				} 

				taskEntity.setHandleOrgId(orgId);// 处理人单位ID
				taskEntity.setHandleOrgName(orgName);// 处理人单位名称
				taskEntity.setHandleUserId(userId);// 处理人ID
				taskEntity.setHandleUserName(userName);// 处理人姓名
				taskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
				taskEntity.setHandleIdea(handleIdea);// 处理意见

				DaoFactory.create(TaJcComplainTasklist.class).insert(taskEntity);
			}
		}

		return entity;
	}
	
	/**
	 * 提交审核
	 * 
	 * @param complainId
	 * @param accept
	 * @param handleIdea
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Path("taJcComplainBaseinfo/updateSh")
	public TaJcComplainBaseinfo updateSh(Map<String, Object> map) throws ParseException {

		String complainId = (String) map.get("complainId");// 投诉编号
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		String pass = (String) map.get("pass");// 是否通过
		String handleisPublic = (String) map.get("handleisPublic");// 处理结果是否公开
		Map<String,String> userMap = getUserInfo();
		String userId = userMap.get("id");// 用户ID
		String userName = userMap.get("name");// 用户名称
		String orgId = userMap.get("orgId");// 单位id
		String orgName = userMap.get("orgName");// 单位名称
		String orgLevel = userMap.get("orgLevel");// 单位层级

		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);
		if (entity != null) {

			// 最后修改时间
			entity.setLastmodifytime(new java.sql.Date(new Date().getTime()));

			entity.setLeaderIdea(handleIdea);// 批示意见
			entity.setReplyUserId(userId);// 回复人ID
			entity.setReplyOrgid(orgId);//回复单位ID
			entity.setReplyOrgname(orgName);// 回复单位名称
			entity.setReplyTime(new java.sql.Date(new Date().getTime()));// 回复时间
			if(StringUtils.isNotBlank(handleisPublic)){
				entity.setHandleisPublic(handleisPublic);
			}

			// 查询正式盖章扫描件数量
			//TaJcComplainAttachinfo attach = new TaJcComplainAttachinfo();
			//attach.setComplainId(complainId + "_zhengshi");// 正式盖章扫描件关联的投诉ID会带_zhengshi后缀
			//List<TaJcComplainAttachinfo> attachList = DaoFactory.create(TaJcComplainAttachinfo.class).select(attach);

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}

			// 通过
			if ("Y".equalsIgnoreCase(pass)) {
				//判断当前登录用户机构层级是否等于录入时选择的接收单位层级
				//如果等于，则状态为办理完成，否则，状态为待上一级审核
				if(orgLevel.equals(entity.getAcceptOrgLevel())){
					entity.setHandleStatus(Constant.COMPLAIN_BANLIWANCHENG);
					entity.setState(Constant.COMPLAIN_BANLIWANCHENG);
					entity.setProessstate(Constant.COMPLAIN_BANLIWANCHENG);
					entity.setEndTime(new java.sql.Date(new Date().getTime()));
					entity.setHandleUserId(null);
				}
				else {
					//如果是区县级审核，则通过后状态为待市级审核
					if("3".equals(orgLevel)){
						entity.setProessstate(Constant.COMPLAIN_DAISHIJISHENHE);
					}
					//如果是市级审核，则通过后状态为待省级审核
					else if("2".equals(orgLevel)){
						entity.setProessstate(Constant.COMPLAIN_DAISHENGJISHENHE);
					}
					//如果是省级审核，通过后状态为办理完成
					else if("1".equals(orgLevel)){
						entity.setProessstate(Constant.COMPLAIN_BANLIWANCHENG);
					}
				}
			} 
			// 不通过
			else {
				String teskId = entity.getTaskid();
				TaJcComplainTasklist oldTaskEntity = DaoFactory.create(TaJcComplainTasklist.class).selectByID(
						teskId);
				//如果上一环节是回复，则直接取上一环节处理信息
				if(oldTaskEntity.getTacheName().indexOf("回复") >= 0){
					entity.setProessstate(Constant.COMPLAIN_DAIHUIFU);
					entity.setHandleUserId(oldTaskEntity.getHandleUserId());
				}
				else {
					String processState = "";
					String handleUserId = "";
					String tacheName = "";
					//如果上一环节是审核，则退回到上一级重新审核
					//不能直接取上一环节的处理意见信息，因为如果是到省级审核再退回到市级审核
					//市级审核再退回到区县级审核时，上一环节信息就不对了
					//如果当前是省级审核
					if("1".equals(orgLevel)){
						processState = Constant.COMPLAIN_DAISHIJISHENHE;
						handleUserId = oldTaskEntity.getHandleUserId();
					}
					//如果当前是市级审核，有两种情况，1，上一环节是区县级审核，2，上一环节是省级审核
					else if("2".equals(orgLevel)){
						//状态为区县级待审核
						processState = Constant.COMPLAIN_DAIQUXIANJISHENHE;
						//如果是区县级审核，则直接取上一环节处理人id
						if("区县级审核".equals(oldTaskEntity.getTacheName())){
							handleUserId = oldTaskEntity.getHandleUserId();
						}
						//如果上一环节是省级审核，又有两种情况，1，接收单位是市级，2，接收单位是区县级
						else if("省级审核".equals(oldTaskEntity.getTacheName())){
							String acceptOrgId = entity.getAcceptOrgId();
							OrgviewController view = new OrgviewController();
							Orgview orgView = view.get(acceptOrgId);
							String acceptOrgCode = orgView.getCode();
							String acceptOrgLevel = StringUtil.getOrgLevel(acceptOrgCode);
							//如果接收单位是市级
							if("2".equals(acceptOrgLevel)){
								processState = Constant.COMPLAIN_DAIHUIFU;
								tacheName = "回复";
							}
							else {
								tacheName = "区县级审核";
							}
						}
					}
					//如果当前是区县级审核，有两种情况，1，上一环节是区县回复，2，上一环节是市级审核
					//由于第一种情况在前面已经处理了，所以进入到这里的只能上一环节是市级审核
					else if("3".equals(orgLevel)){
						//状态为待回复
						processState = Constant.COMPLAIN_DAIHUIFU;
						tacheName = "回复";
					}
					//根据环节名称，查询环节信息，获取处理人id
					if(StringUtils.isNotBlank(tacheName)){
						TaJcComplainTasklist param = new TaJcComplainTasklist();
						param.setComplainId(complainId);
						param.setTacheName(tacheName);
						List<TaJcComplainTasklist> taskList = DaoFactory.create(TaJcComplainTasklist.class).getSession()
								.selectList("com.chinacreator.dzjc_complain.TaJcComplainTasklistMapper.selectByTacheName", param);
						if(taskList != null && taskList.size() > 0){
							handleUserId = taskList.get(0).getHandleUserId();
						}
					}
					entity.setProessstate(processState);
					entity.setHandleUserId(handleUserId);
				}
			}

			// 投诉处理意见
			TaJcComplainTasklist newTaskEntity = new TaJcComplainTasklist();
			new EntityUtil().entityPK(newTaskEntity);// 生成处理意见主键UUID

			entity.setTaskid(newTaskEntity.getTaskId());
			entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数

			// 写入受理
			int rows = DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);

			if (rows > 0) {
				newTaskEntity.setComplainId(entity.getComplainId());
				if("1".equals(orgLevel)){
					newTaskEntity.setTacheName("省级审核");
				}
				else if("2".equals(orgLevel)){
					newTaskEntity.setTacheName("市级审核");
				}
				else if("3".equals(orgLevel)){
					newTaskEntity.setTacheName("区县级审核");
				}

				newTaskEntity.setHandleOrgId(orgId);// 处理人单位ID
				newTaskEntity.setHandleOrgName(orgName);// 处理人单位名称
				newTaskEntity.setHandleUserId(userId);// 处理人ID
				newTaskEntity.setHandleUserName(userName);// 处理人姓名
				newTaskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
				newTaskEntity.setHandleIdea(handleIdea);// 处理意见

				DaoFactory.create(TaJcComplainTasklist.class).insert(newTaskEntity);
			}
		}
		return entity;
	}
	
	/**
	 * 延期申请
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@POST
	@Path("taJcComplainBaseinfo/yqsqUpdate")
	public TaJcComplainBaseinfo yqsqUpdate(Map<String, Object> map) throws NumberFormatException, Exception {

		String complainId = (String) map.get("complainId");// 投诉编号
		String isExamine = (String) map.get("isExamine");// 是否延期
		String extensDay = (String) map.get("extensDay");// 延期天数
		String extensReason = (String) map.get("extensReason");// 延期原因
		String handleIdea = (String) map.get("handleIdea");// 处理意见
		
		
		TaJcComplainBaseinfo entity = DaoFactory.create(TaJcComplainBaseinfo.class).selectByID(complainId);

		if (entity != null) {

			// 满意度
			if (entity.getIsSatisfied() != null) {
				if ("满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("Y");
				} else if ("比较满意".equals(entity.getIsSatisfied())) {
					entity.setIsSatisfied("M");
				} else {
					entity.setIsSatisfied("N");
				}
			}
			String tacheName = "";// 处理环节
			// 状态如果改成N，代表同意延期
			if ("N".equalsIgnoreCase(isExamine)) {

				entity.setCommitmentLimit(entity.getCommitmentLimit() + "+" + extensDay);
				if (null == entity.getProcessLimit() || new Double(0.00).equals(entity.getProcessLimit())) {//兼容以前为空的数据根据表达式重新计算[PROCESS_LIMIT 办理时限数字型，用于监察]的值:10+2+11+20+2+1+1+6
					BigDecimal flag = new BigDecimal(0.00);
					String[] elem = entity.getCommitmentLimit().split("\\+");
					if (elem.length > 0) {
						for (String day : elem) {
							flag = flag.add(new BigDecimal(Double.toString(Double.parseDouble(day))));
						}
						entity.setProcessLimit(new Double(flag.doubleValue()));
					}
				}else{
					if (StringUtils.isNotBlank(extensDay)) {
						BigDecimal day = new BigDecimal(Double.toString(entity.getProcessLimit())).add(new BigDecimal(Double.toString(Double.parseDouble(extensDay))));
						entity.setProcessLimit(new Double(day.doubleValue()));
					}
				}
				// 获取原本到期时限
				Date expireDate = entity.getExpireDate();

				// 加上延长天数，计算延长后的结办日期
				String ycDate = this.getYcDate(sdf.format(expireDate), Integer.parseInt(extensDay));

				Date date = null;
				if (ycDate.indexOf("/") != -1) {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					date = sdf1.parse(ycDate);
				} else {
					date = sdf.parse(ycDate);
				}

				java.sql.Date yqDate = new java.sql.Date(date.getTime());
				entity.setExpireDate(yqDate);

				extensDay = "";// 计算完成后，申请的天数需要变成空
				tacheName = "延期审核";
			} else {

				// 如果等于"Y"，有两种情况，一种是申请时是Y，延期时间必须有值。无值则说明是不同意申请
				if ("".equals(extensDay.trim()) && "".equals(extensReason.trim())) {
					// 不同意申请，则一切回归默认状态，是否申请变成N，延期信息变成空
					isExamine = "N";
					tacheName = "延期审核";
				}
				else {
					handleIdea = extensReason;
					tacheName = "延期申请";
				}
			}

			// 设置是否延期申请，Y延期，N不延期
			entity.setIsExamine(isExamine);
			entity.setExtensDay(extensDay);
			entity.setExtensReason(extensReason);
			entity.setCommitnum(entity.getCommitnum() + 1);//累加提交次数

			Map<String,String> userMap = getUserInfo();
			String userId = userMap.get("id");// 用户ID
			String userName = userMap.get("name");// 用户名称
			String orgId = userMap.get("orgId");// 单位id
			String orgName = userMap.get("orgName");// 单位名称
			
			// 投诉处理意见
			TaJcComplainTasklist newTaskEntity = new TaJcComplainTasklist();
			new EntityUtil().entityPK(newTaskEntity);// 生成处理意见主键UUID
			newTaskEntity.setComplainId(entity.getComplainId());
			newTaskEntity.setHandleOrgId(orgId);// 处理人单位ID
			newTaskEntity.setHandleOrgName(orgName);// 处理人单位名称
			newTaskEntity.setHandleUserId(userId);// 处理人ID
			newTaskEntity.setHandleUserName(userName);// 处理人姓名
			newTaskEntity.setHandleTime(new java.sql.Date(new Date().getTime()));// 处理时间
			newTaskEntity.setHandleIdea(handleIdea);// 处理意见
			newTaskEntity.setTacheName(tacheName);

			DaoFactory.create(TaJcComplainTasklist.class).insert(newTaskEntity);
		}

		DaoFactory.create(TaJcComplainBaseinfo.class).update(entity);

		return entity;
	}

	/**
	 * 获取查询码
	 * 
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/seq/getSeqJcComplainBaseinfo")
	public static String getSeqJcComplainBaseinfo() {
		String seq = DaoFactory.create(String.class).getSession()
				.selectOne("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.select_seq_jc_complain_baseinfo");
		return seq;
	}

	/**
	 * 获取登录人信息
	 * 
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/user")
	public static String getUserID() {
		String userId = StringUtil.getUserInfo().get("id");
		return userId;
	}

	/**
	 * 计算投诉办理到期日期（去除节假日）
	 * 
	 * @param days
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("taJcComplainBaseinfo/getDqDate/{days}")
	public String getDqDate(@PathParam("days") int days) throws Exception {

		String date = HolidayDateUtil.getDate(days);
		return date;
	}

	/**
	 * 获取到期时间延长天数后的日期（去除节假日）
	 * 
	 * @param date 到期时间
	 * @param days 延长天数
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("taJcComplainBaseinfo/getYcDate")
	public String getYcDate(@QueryParam("date")String dateStr, @QueryParam("days")int days) throws Exception {
		String pattern = "yyyy/MM/dd HH:mm:ss";
		if(dateStr.indexOf("/") == -1){
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		Date date = DateUtil.getDateFromString(dateStr, pattern);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		int i = 1;
		int j = 1;
		String result = null;
		boolean flag = true;
		while (flag) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, i);// 今天+i天
			Date iday = c.getTime();
			result = sdf2.format(iday);
			if (!HolidayDateUtil.isHoliday(result.substring(0, 10))) {
				if (j == days) {
					flag = false;
				} else {
					j++;
				}
			}
			i++;
		}
		return result;
	}

	/**
	 * 获取转办、交办机构数信息
	 * 
	 * @param treeType
	 * @return
	 */
	@POST
	@Path("getHandleTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getHandleTree(@FormParam("treeType") String treeType) {

		if ("1".equals(treeType)) {// 转办
			return this.getOrgTree();
		} else if ("2".equals(treeType)) {// 交办
			return this.getOrgUserTree();
		} else {
			return null;
		}

	}

	/**
	 * 交办业务：获取本级以下机构下的用户信息
	 * @return
	 */
	private TreeNode[] getOrgUserTree() {
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		// 获取登录人信息
		Map<String, String> userMap = getUserInfo();
		String orgCode = userMap.get("orgCode");// 登录人所属机构编码
		String orgLevel = userMap.get("orgLevel");// 登录人所属机构层级
		
		//下一级机构列表
		List<Orgview> orgList = null;
		
		List<Map<String,String>> userMapList = null;
		Map<String,String> paramMap = new HashMap<String,String>();
		//如果是省优化办交办给市优化办
		if("1".equals(orgLevel)){
			paramMap.put("startWith", orgCode.substring(0, 2));
			paramMap.put("endWith", "00000000000000000000");
			
			userMapList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.findSonYhbUser1", paramMap);
		}
		//如果是市优化办交办给区县优化办
		else if("2".equals(orgLevel)){
			paramMap.put("startWith", orgCode.substring(0, 4));
			paramMap.put("endWith", "000000000000000000");
			paramMap.put("orgCode", orgCode);
			
			userMapList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.findSonYhbUser2", paramMap);
		}
		orgList = DaoFactory.create(Orgview.class).getSession()
				.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.findSonOrg", paramMap);
		
		// 去掉没有用户的机构
		List<Orgview> orgList2 = new ArrayList<>();
		if(orgList != null && userMapList != null){
			for (Orgview organization : orgList) {
				String orgId = organization.getId();
				String code = "";
				//把省本级，市本级去掉
				if("1".equals(orgLevel)){
					code = organization.getCode().substring(2, 4);
					if("00".equals(code) || "99".equals(code)){
						continue;
					}
				}
				else if("2".equals(orgLevel)){
					code = organization.getCode().substring(4, 6);
					if("00".equals(code) || "01".equals(code)){
						continue;
					}
				}
				for (Map<String,String> userInfo : userMapList) {
					if (StringUtils.isNotBlank(userInfo.get("ORGID")) 
							&& orgId.equalsIgnoreCase(userInfo.get("ORGID"))) {
						orgList2.add(organization);
						break;
					}
				}
			}
		}
		else {
			orgList2 = orgList;
			orgList = null;
		}
		
		CommonTreeNode orgTreeNode = null;
		for (int i = 0; i < orgList2.size(); i++) {
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(orgList2.get(i).getId());
			orgTreeNode.setShowName(orgList2.get(i).getName());
			orgTreeNode.setName(orgList2.get(i).getName());
			orgTreeNode.setPid(orgList2.get(i).getPid());
			list.add(orgTreeNode);
		}

		for (int i = 0; i < userMapList.size(); i++) {
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(userMapList.get(i).get("ID"));
			orgTreeNode.setShowName("user");
			orgTreeNode.setName(userMapList.get(i).get("NAME"));
			orgTreeNode.setPid(userMapList.get(i).get("ORGID"));
			list.add(orgTreeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}

	/**
	 * 转办业务：获取本机构下的用户信息
	 * @return
	 */
	private TreeNode[] getOrgTree() {

		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();

		// 获取登录人信息
		Map<String, String> userMap = getUserInfo();
		String orgId = userMap.get("orgId");
		String orgCode = userMap.get("orgCode");// 登录人所属机构编码
		String orgLevel = userMap.get("orgLevel");// 登录人所属机构层级
		String parentCode = "";
		//如果是省优化办转办
		if("1".equals(orgLevel)){
			parentCode = orgCode.substring(0, 4)+"00000000000000000000";
		}
		//如果是市优化办转办
		else if("2".equals(orgLevel)){
			parentCode = orgCode.substring(0, 6)+"000000000000000000";
		}
		//如果是区县优化办转办
		else if("3".equals(orgLevel)){
			parentCode = orgCode.substring(0, 9)+"000000000000000";
		}
		// 查询该机构所属父级
		Orgview parentOrg = DaoFactory.create(Orgview.class).getSession()
				.selectOne("com.chinacreator.dzjc_complain.OrgviewMapper.findOrgByCode", parentCode);

		if (parentOrg == null) {
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}

		// 查询当前父级机构所有子机构
		List<Organization> orgList = null;
		try {

			@SuppressWarnings("unchecked")
			List<Object> objectList = RestUtils.createRestTemplate().getForObject(
					url + "/uop/v1/orgs/" + parentOrg.getId() + "/children?cascade=true&categoryId=10-Z", List.class);

			// 转换成对象集合
			orgList = JSON.parseArray(objectList.toString(), Organization.class);

		} catch (Exception e) {
			throw new RuntimeException("机构获取失败");
		}

		// -----------------------------------------------------------------------------------------------------------

		// 查询当前父级机构所有子机构下的用户
		List<OrgUserInfo> userList = null;
		try {

			@SuppressWarnings("unchecked")
			List<Object> objectList = RestUtils.createRestTemplate().getForObject(
					url + "/uop/v1/orgs/" + parentOrg.getId() + "/users?state=1&cascade=true&categoryId=10-Z",
					List.class);

			// 转换成对象集合
			userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);

		} catch (Exception e) {
			throw new RuntimeException("用户获取失败");
		}

		// -----------------------------------------------------------------------------------------------------------

		// 去掉没有用户的机构
		List<Organization> orgList2 = new ArrayList<>();
		for (Organization organization : orgList) {
			String orgIds = organization.getId();
			if(orgId!=null && orgId.equals(orgIds)){//去掉当前用户所属机构
				continue;
			}
			for (OrgUserInfo orgUserInfo : userList) {
				if (orgIds.equalsIgnoreCase(orgUserInfo.getOrgIds()[0])) {
					orgList2.add(organization);
					break;
				}
			}
		}

		CommonTreeNode orgTreeNode = null;
		for (int i = 0; i < orgList2.size(); i++) {
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(orgList2.get(i).getId());
			orgTreeNode.setShowName(orgList2.get(i).getName());
			orgTreeNode.setName(orgList2.get(i).getName());
			orgTreeNode.setPid(orgList2.get(i).getPid());
			list.add(orgTreeNode);
		}

		for (int i = 0; i < userList.size(); i++) {
			if (orgId!=null && orgId.equals(userList.get(i).getOrgIds()[0])) {//去掉当前用户所属机构下的所有用户
				continue;
			}
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(userList.get(i).getId());
			orgTreeNode.setShowName("user");
			orgTreeNode.setName(userList.get(i).getName());
			orgTreeNode.setPid(userList.get(i).getOrgIds()[0]);
			list.add(orgTreeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}

	/**
	 * 投诉查询分页
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo")
	public Page<TaJcComplainBaseinfo> getListByPageSL(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {

		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainBaseinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainBaseinfo.class)
				: new TaJcComplainBaseinfo();
		List<Rule> rules = null; 
		if( conditions!=null ){
			rules = conditions.getRules();
			for (int i = 0; i < rules.size(); i++) {
				String field= rules.get(i).getField();
				String op=rules.get(i).getOp();
				//投诉时间
				if ("enregisterTime".equals(field)) {
					
					//投诉结束时间
					if ("le".equals(op)) {
						Object dataTime = rules.get(i).getData()+ " 23:59:59";
						rules.get(i).setData(dataTime);
					}
					//投诉开始时间
					if ("ge".equals(op)) {
						Object dataTime = rules.get(i).getData()+ " 00:00:00";
						rules.get(i).setData(dataTime);
					}
				}
			}	
		}
		String code = null;
		try {
			// 查询当前登录用户编号(行政区码)
			code = RoleUtil.getInstance().queryCodeByUserIdTs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (entity != null) {
			if ("".equals(code)) {
				return null;
			}
			entity.setAcceptOrgId(code);
		}
		return DaoFactory.create(TaJcComplainBaseinfo.class).selectPageByCondition(entity, conditions, pageable,
				sortable, true);
	}

	/**
	 * 投诉受理分页（只显示需要自己所在单位预受理，受理的数据）
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/tssl")
	public Page<TaJcComplainBaseinfo> getListByPageShouLi(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainBaseinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainBaseinfo.class)
				: new TaJcComplainBaseinfo();
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,null,true);
		String code = null;
		try {
			// 查询当前登录用户编号(行政区码)
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TaJcComplainBaseinfo> list = new ArrayList<>();
		if (entity != null) {
			TaJcComplainBaseinfoSub complainSub = new TaJcComplainBaseinfoSub();
			complainSub.setAcceptOrgId(code);
			complainSub.setStatusArr(getStatusArray(Constant.COMPLAIN_SHOULI_LIST));
			if (conditions != null) {
				List<Rule> rules = conditions.getRules();
				//获取查询参数
				for (Rule rule : rules) {
					//投诉标题
					if ("complainTitle".equals(rule.getField())) {
						complainSub.setComplainTitle((String) rule.getData());
					}
					//投诉人名字
					if ("complainUserName".equals(rule.getField())) {
						complainSub.setComplainUserName((String) rule.getData());
					}
					//投诉时间
					if ("enregisterTime".equals(rule.getField())) {
						//投诉开始时间
						if ("ge".equals(rule.getOp())) {
							complainSub.setBeginDate((String) rule.getData()+"00:00:00");
						}
						//投诉结束时间
						if ("le".equals(rule.getOp())) {
							complainSub.setEndDate((String) rule.getData()+"23:59:59");
						}
					}
					//查询码
					if("queryCode".equals(rule.getField())){
						complainSub.setQueryCode((String)rule.getData());
					}
					//投诉状态
					if("proessstate".equals(rule.getField())){
						complainSub.setProessstate((String)rule.getData());
					}
				}
			}
			list = DaoFactory
					.create(TaJcComplainBaseinfo.class)
					.getSession()
					.selectList("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectListByPage", complainSub,rowbound);
		}
		Page<TaJcComplainBaseinfo> selectPageByCondition = new Page<TaJcComplainBaseinfo>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}
	
	/**
	 * 投诉处理分页（只显示需要自己接收，回复的数据）
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/tscl")
	public Page<TaJcComplainBaseinfo> getListByPageChuLi(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainBaseinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainBaseinfo.class)
				: new TaJcComplainBaseinfo();
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,null,true);
		String code = null;
		try {
			// 查询当前登录用户编号(行政区码)
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取登录人信息
		Map<String, String> userMap = StringUtil.getUserInfo();
		String userId = userMap.get("id");// 登录人id
		List<TaJcComplainBaseinfo> list = new ArrayList<>();
		if (entity != null) {
			TaJcComplainBaseinfoSub complainSub = new TaJcComplainBaseinfoSub();
			complainSub.setAcceptOrgId(code);
			complainSub.setHandleUserId(userId);
			complainSub.setStatusArr(getStatusArray(Constant.COMPLAIN_CHULI_LIST));
			if (conditions != null) {
				List<Rule> rules = conditions.getRules();
				//获取查询参数
				for (Rule rule : rules) {
					//投诉标题
					if ("complainTitle".equals(rule.getField())) {
						complainSub.setComplainTitle((String) rule.getData());
					}
					//投诉人名字
					if ("complainUserName".equals(rule.getField())) {
						complainSub.setComplainUserName((String) rule.getData());
					}
					//投诉时间
					if ("enregisterTime".equals(rule.getField())) {
						//投诉开始时间
						if ("ge".equals(rule.getOp())) {
							complainSub.setBeginDate((String) rule.getData()+"00:00:00");
						}
						//投诉结束时间
						if ("le".equals(rule.getOp())) {
							complainSub.setEndDate((String) rule.getData()+"23:59:59");
						}
					}
					//查询码
					if ("queryCode".equals(rule.getField())) {
						complainSub.setQueryCode((String) rule.getData());
					}
					//投诉状态
					if("proessstate".equals(rule.getField())){
						complainSub.setProessstate((String)rule.getData());
					}
				}
			}
			list = DaoFactory
					.create(TaJcComplainBaseinfo.class)
					.getSession()
					.selectList("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectListByPage", complainSub,rowbound);
		}
		Page<TaJcComplainBaseinfo> selectPageByCondition = new Page<TaJcComplainBaseinfo>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}

	/**
	 * 投诉审核分页（只显示需要自己所在单位审核的数据）
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/tssh")
	public Page<TaJcComplainBaseinfo> getListByPageShenHe(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainBaseinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainBaseinfo.class)
				: new TaJcComplainBaseinfo();
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,null,true);
		String code = "null";
		try {
			Map<String, String> userMap = getUserInfo();
			String orgCode = userMap.get("orgCode");
			String orgName = userMap.get("orgName");
			String orgLevel = userMap.get("orgLevel");
			if(orgName.indexOf("优化办") >= 0){
				if("1".equals(orgLevel)){
					code = orgCode.substring(0, 2);
				}
				else if("2".equals(orgLevel)){
					code = orgCode.substring(0, 4);
				}
				else if("3".equals(orgLevel)){
					code = orgCode.substring(0, 6);
				}
				else {
					code = orgCode;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TaJcComplainBaseinfo> list = new ArrayList<>();
		if (entity != null) {
			TaJcComplainBaseinfoSub complainSub = new TaJcComplainBaseinfoSub();
			complainSub.setAcceptOrgId(code);
			complainSub.setStatusArr(getStatusArray(Constant.COMPLAIN_SHENHE_LIST));
			if (conditions != null) {
				List<Rule> rules = conditions.getRules();
				//获取查询参数
				for (Rule rule : rules) {
					//投诉标题
					if ("complainTitle".equals(rule.getField())) {
						complainSub.setComplainTitle((String) rule.getData());
					}
					//投诉人名字
					if ("complainUserName".equals(rule.getField())) {
						complainSub.setComplainUserName((String) rule.getData());
					}
					//投诉时间
					if ("enregisterTime".equals(rule.getField())) {
						//投诉开始时间
						if ("ge".equals(rule.getOp())) {
							complainSub.setBeginDate((String) rule.getData()+"00:00:00");
						}
						//投诉结束时间
						if ("le".equals(rule.getOp())) {
							complainSub.setEndDate((String) rule.getData()+"23:59:59");
						}
					}
					//查询码
					if ("queryCode".equals(rule.getField())) {
						complainSub.setQueryCode((String) rule.getData());
					}
				}
			}
			list = DaoFactory
					.create(TaJcComplainBaseinfo.class)
					.getSession()
					.selectList("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectListByPage", complainSub,rowbound);
		}
		Page<TaJcComplainBaseinfo> selectPageByCondition = new Page<TaJcComplainBaseinfo>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}
	
	/**
	 * 投诉延期申请分页（只显示自己所在单位受理的数据）
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/yqsq")
	public Page<TaJcComplainBaseinfo> getListByPageYqsq(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainBaseinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainBaseinfo.class)
				: new TaJcComplainBaseinfo();
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,null,true);
		int workDay = 5;//默认工作日
		Map<String, String> userMap = getUserInfo();
		String orgId = userMap.get("orgId");
		List<TaJcComplainBaseinfo> list = new ArrayList<>();
		if (entity != null) {
			TaJcComplainBaseinfoSub complainSub = new TaJcComplainBaseinfoSub();
			String deadline=null;//最后期限
			try {
				if(!StringUtils.isBlank(WORK_DAY)){
					workDay = Integer.parseInt(WORK_DAY);
				}
				deadline = getDqDate(workDay);
			} catch (Exception e) {
				e.printStackTrace();
			}
			complainSub.setDeadline(deadline);
			complainSub.setAcceptOrgId(orgId);
			complainSub.setIsExamine(Constant.NO);
			if (conditions != null) {
				List<Rule> rules = conditions.getRules();
				//获取查询参数
				for (Rule rule : rules) {
					//投诉标题
					if ("complainTitle".equals(rule.getField())) {
						complainSub.setComplainTitle((String) rule.getData());
					}
					//投诉人名字
					if ("complainUserName".equals(rule.getField())) {
						complainSub.setComplainUserName((String) rule.getData());
					}
					//投诉时间
					if ("enregisterTime".equals(rule.getField())) {
						//投诉开始时间
						if ("ge".equals(rule.getOp())) {
							complainSub.setBeginDate((String) rule.getData()+"00:00:00");
						}
						//投诉结束时间
						if ("le".equals(rule.getOp())) {
							complainSub.setEndDate((String) rule.getData()+"23:59:59");
						}
					}
					//查询码
					if ("queryCode".equals(rule.getField())) {
						complainSub.setQueryCode((String) rule.getData());
					}
				}
			}
			list = DaoFactory
					.create(TaJcComplainBaseinfo.class)
					.getSession()
					.selectList("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectListByPage", complainSub,rowbound);
		}
		Page<TaJcComplainBaseinfo> selectPageByCondition = new Page<TaJcComplainBaseinfo>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}
	
	/**
	 * 延期审核分页（只显示需要自己所在单位审核的数据）
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/yqsh")
	public Page<TaJcComplainBaseinfo> getListByPageYqsh(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainBaseinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainBaseinfo.class)
				: new TaJcComplainBaseinfo();
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,null,true);
		String code = null;
		try {
			Map<String, String> userMap = getUserInfo();
			String orgCode = userMap.get("orgCode");
			String orgName = userMap.get("orgName");
			String orgLevel = userMap.get("orgLevel");
			if(orgName.indexOf("优化办") >= 0){
				if("1".equals(orgLevel)){
					code = orgCode.substring(0, 2);
				}
				else if("2".equals(orgLevel)){
					code = orgCode.substring(0, 4);
				}
				else if("3".equals(orgLevel)){
					code = orgCode.substring(0, 6);
				}
				else {
					code = orgCode;
				}
			}
			else {
				code = RoleUtil.getInstance().queryCodeByUserId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TaJcComplainBaseinfo> list = new ArrayList<>();
		if (entity != null) {
			TaJcComplainBaseinfoSub complainSub = new TaJcComplainBaseinfoSub();
			complainSub.setAcceptOrgId(code);
			complainSub.setIsExamine(Constant.YES);
			if (conditions != null) {
				List<Rule> rules = conditions.getRules();
				//获取查询参数
				for (Rule rule : rules) {
					//投诉标题
					if ("complainTitle".equals(rule.getField())) {
						complainSub.setComplainTitle((String) rule.getData());
					}
					//投诉人名字
					if ("complainUserName".equals(rule.getField())) {
						complainSub.setComplainUserName((String) rule.getData());
					}
					//投诉时间
					if ("enregisterTime".equals(rule.getField())) {
						//投诉开始时间
						if ("ge".equals(rule.getOp())) {
							complainSub.setBeginDate((String) rule.getData()+"00:00:00");
						}
						//投诉结束时间
						if ("le".equals(rule.getOp())) {
							complainSub.setEndDate((String) rule.getData()+"23:59:59");
						}
					}
					//查询码
					if ("queryCode".equals(rule.getField())) {
						complainSub.setQueryCode((String) rule.getData());
					}
				}
			}
			list = DaoFactory
					.create(TaJcComplainBaseinfo.class)
					.getSession()
					.selectList("com.chinacreator.dzjc_complain.TaJcComplainBaseinfoMapper.selectListByPage", complainSub,rowbound);
		}
		Page<TaJcComplainBaseinfo> selectPageByCondition = new Page<TaJcComplainBaseinfo>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	@GET
	@Path("taJcComplainBaseinfo/getUserInfo")
	public Map<String,String> getUserInfo() {
		Map<String, String> userMap = StringUtil.getUserInfo();
		String userOrgId = userMap.get("orgId");
		OrgviewController view = new OrgviewController();
		Orgview orgView = view.get(userOrgId);
		String orgCode = orgView.getCode();
		userMap.put("orgCode", orgCode);
		userMap.put("orgName", orgView.getName());
		if ("99".equals(orgCode.substring(2, 4))) {
			userMap.put("orgLevel", "1");
		} else if ("00".equals(orgCode.substring(4, 6)) || "01".equals(orgCode.substring(4, 6))) {
			userMap.put("orgLevel", "2");
		} else {
			userMap.put("orgLevel", "3");
		}
		return userMap;
	}
	
	public String[] getStatusArray(String pageType){
		//如果是受理列表
		if(pageType.equals(Constant.COMPLAIN_SHOULI_LIST)){
			String[] statusArr = {Constant.COMPLAIN_YUSHOULI,Constant.COMPLAIN_DAISHOULI};
			return statusArr;
		}
		//如果是处理列表
		else if(pageType.equals(Constant.COMPLAIN_CHULI_LIST)){
			String[] statusArr = {Constant.COMPLAIN_DAIJIESHOU,Constant.COMPLAIN_DAIHUIFU};
			return statusArr;
		}
		//如果是审核列表
		else if(pageType.equals(Constant.COMPLAIN_SHENHE_LIST)){
			Map<String, String> userMap = getUserInfo();
			String orgLevel = userMap.get("orgLevel");
			if ("1".equals(orgLevel)) {
				String[] statusArr = {Constant.COMPLAIN_DAISHENGJISHENHE};
				return statusArr;
			} else if ("2".equals(orgLevel)) {
				String[] statusArr = {Constant.COMPLAIN_DAISHIJISHENHE};
				return statusArr;
			} else {
				String[] statusArr = {Constant.COMPLAIN_DAIQUXIANJISHENHE};
				return statusArr;
			}
		}
		//如果是延期申请列表
		else if(pageType.equals(Constant.COMPLAIN_YQSQ_LIST)){
			String[] statusArr = {Constant.COMPLAIN_ZHENGZAIBANLI};
			return statusArr;
		}
		return null;
	}
	
}
