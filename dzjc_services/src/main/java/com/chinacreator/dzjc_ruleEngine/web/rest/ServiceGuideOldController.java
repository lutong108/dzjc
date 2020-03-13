package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.ServiceGuideOld;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceGuideOldController {

	@POST
	@Path("superviseGuideOld/{approveId}")
	public ServiceGuideOld update(ServiceGuideOld entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(ServiceGuideOld.class).update(entity);
		return entity;
	}

	@POST
	@Path("superviseGuideOld")
	public ServiceGuideOld insert(ServiceGuideOld entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(ServiceGuideOld.class).insert(entity);
		return entity;
	}

	@GET
	@Path("superviseGuideOld/{approveId}")
	public ServiceGuideOld get(
			@PathParam(value = "approveId") java.lang.String approveId) {
		//TODO auto-generated method stub
		return DaoFactory.create(ServiceGuideOld.class).selectByID(approveId);
	}

	@DELETE
	@Path("superviseGuideOld")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(ServiceGuideOld.class).deleteBatch(ids);
	}

	@DELETE
	@Path("superviseGuideOld/{approveId}")
	public void delete(
			@PathParam(value = "approveId") java.lang.String approveId) {
		//TODO auto-generated method stub
		ServiceGuideOld entity = new ServiceGuideOld();
		entity.setApproveId(approveId);
		DaoFactory.create(ServiceGuideOld.class).delete(entity);
	}

	@GET
	@Path("superviseGuideOld")
	public Page<ServiceGuideOld> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		//TODO auto-generated method stub
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		ServiceGuideOld entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, ServiceGuideOld.class)
				: new ServiceGuideOld();

		return DaoFactory.create(ServiceGuideOld.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
