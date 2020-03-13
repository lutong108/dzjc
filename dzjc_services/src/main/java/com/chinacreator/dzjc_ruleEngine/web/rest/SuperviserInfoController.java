package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.util.StringUtil;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.DeleteSupervise;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.util.BeanUtil;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.HolidayDateUtil;
import com.chinacreator.util.SystemPropertiesInstance;
import com.ibm.db2.jcc.am.de;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SuperviserInfoController {
	private final static String SYSTEM_AREACODE_VALUE = SystemPropertiesInstance.getInstance().getSystem_areaCode();
	
	@GET
	@Path("SuperviseInfo/{superviseInfoId}")
	public SuperviserInfo get(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		return DaoFactory.create(SuperviserInfo.class).selectByID(
				superviseInfoId);
	}

	@GET
	@Path("SuperviseInfo")
	public Page<SuperviserInfo> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		SuperviserInfo entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SuperviserInfo.class) : new SuperviserInfo();
		return DaoFactory.create(SuperviserInfo.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
	}

	@POST
	@Path("SuperviseInfo/{superviseInfoId}")
	public SuperviserInfo update(SuperviserInfo entity) {
		DaoFactory.create(SuperviserInfo.class).update(entity);
		return entity;
	}
	
	
	@DELETE
	@Path("deleteSuperviseInfo/{superviseInfoId}")
	public SuperviserInfo delete(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		SuperviserInfo superviserInfo = get(superviseInfoId);
		DeleteSupervise deleteSupervise=new DeleteSupervise();
		BeanUtil.coverBean2Bean(superviserInfo, deleteSupervise);
		deleteSupervise.setDeleteDate(new java.sql.Date(System.currentTimeMillis()));
		deleteSupervise.setSuperviseTime(DateUtil.strToDate(superviserInfo.getSuperviseTime()));
		new DeleteSuperviseController().insert(deleteSupervise);
		DaoFactory.create(SuperviserInfo.class).delete(superviserInfo);
		return superviserInfo;
	}

	/** 
	 * 自定义撤销
	 */
	@POST
	@Path("SuperviseInfo/cxState")
	public String cxState(@RequestBody Map<String, String> map) {
		String temp = "0";
		try {
			String superviseInfoId = map.get("superviseInfoId").trim();
			//获取"撤销原因"
			String revocationReason = map.get("revocationReason").trim();
			SuperviserInfo entity = new SuperviserInfo();
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			entity.setLastUpdatetime(sqlDate);
			entity.setSuperviseInfoId(superviseInfoId);
			entity.setState("C");
			//"撤销原因"存入实体
			entity.setRevocationReason(revocationReason);
			DaoFactory.create(SuperviserInfo.class).update(entity);
			temp = "1";
		} catch (Exception e) {
			temp = "0";
		}
		return temp;
	}

	/** 
	 * 自定义撤销权限判断
	 */
	@POST
	@Path("SuperviseInfo/pdRoles")
	public String pdRoles() {
		String temp = "0";
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
		User user = (User) ((WebOperationContext) OperationContextHolder
				.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		if (roles != null && roles.length > 0) {
			String roleNames = "";
			for (String roleName : roles) {
				roleNames += roleName + ",";
			}
			if (roleNames.contains("dzjc_fpchex")) {
				temp = "1";
			}
		}
		return temp;
	}
	
	/**
	 * 取行政区码
	 */
	@POST
	@Path("SuperviseInfo/areaCode")
	public String areaCode() {
		if (SYSTEM_AREACODE_VALUE != null && !"".equals(SYSTEM_AREACODE_VALUE)) {
			return SYSTEM_AREACODE_VALUE;
		}

		return null;
	}
	

	@POST
	@Path("SuperviseInfo")
	public SuperviserInfo insert(SuperviserInfo entity) throws Exception{
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String superviseId = UUID.randomUUID().toString();
		String businessId = entity.getBusinessId();
		if(businessId.indexOf("$$") != -1){//update by yin.liu 20110829 
			businessId = businessId.substring(0,businessId.indexOf("$$"));
		}
		
		entity.setSuperviseInfoId(superviseId);
		entity.setBusinessId(businessId);
		entity.setSuperviseTime(ft.format(new Date()));
		entity.setFeedbackEndtime(HolidayDateUtil.getDate(30));
		entity.setState("Y");
		
		DaoFactory.create(SuperviserInfo.class).insert(entity);
		return entity;
	}

}
