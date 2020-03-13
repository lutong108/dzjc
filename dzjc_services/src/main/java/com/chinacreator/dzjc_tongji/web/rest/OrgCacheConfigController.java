package com.chinacreator.dzjc_tongji.web.rest;

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
import com.chinacreator.dzjc_tongji.OrgCacheConfig;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class OrgCacheConfigController {

	@POST
	@Path("OrgCacheConfig/{orgId}")
	public OrgCacheConfig update(OrgCacheConfig entity) {
		//TODO auto-generated method stub	
//		DaoFactory.create(OrgCacheConfig.class).update(entity);
//		return entity;
		return insert(entity);
	}

	@POST
	@Path("OrgCacheConfig")
	public OrgCacheConfig insert(OrgCacheConfig entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(OrgCacheConfig.class).insert(entity);
		return entity;
	}

	@GET
	@Path("OrgCacheConfig/{orgId}")
	public OrgCacheConfig get(@PathParam(value = "orgId") java.lang.String orgId) {
		//TODO auto-generated method stub
		return DaoFactory.create(OrgCacheConfig.class).selectByID(orgId);
	}

	@DELETE
	@Path("OrgCacheConfig")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(OrgCacheConfig.class).deleteBatch(ids);
	}

	@DELETE
	@Path("OrgCacheConfig/{orgId}")
	public void delete(@PathParam(value = "orgId") java.lang.String orgId) {
		//TODO auto-generated method stub
		OrgCacheConfig entity = new OrgCacheConfig();
		entity.setOrgId(orgId);
		DaoFactory.create(OrgCacheConfig.class).delete(entity);
	}

	@GET
	@Path("OrgCacheConfig")
	public Page<OrgCacheConfig> getListByPage(
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
		OrgCacheConfig entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, OrgCacheConfig.class) : new OrgCacheConfig();

		return DaoFactory.create(OrgCacheConfig.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
