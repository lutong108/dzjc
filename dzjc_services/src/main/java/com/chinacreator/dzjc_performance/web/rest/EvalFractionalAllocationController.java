package com.chinacreator.dzjc_performance.web.rest;

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
import com.chinacreator.dzjc_performance.EvalFractionalAllocation;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalFractionalAllocationController {
	@POST
	@Path("EvalFractionalAllocation/{id}")
	public EvalFractionalAllocation update(EvalFractionalAllocation entity) {
		DaoFactory.create(EvalFractionalAllocation.class).update(entity);
		return entity;
	}

	@POST
	@Path("EvalFractionalAllocation")
	public EvalFractionalAllocation insert(EvalFractionalAllocation entity) {
		DaoFactory.create(EvalFractionalAllocation.class).insert(entity);
		return entity;
	}

	@GET
	@Path("EvalFractionalAllocation/{id}")
	public EvalFractionalAllocation get(
			@PathParam(value = "id") java.lang.String id) {
		return DaoFactory.create(EvalFractionalAllocation.class).selectByID(id);
	}

	@DELETE
	@Path("EvalFractionalAllocation")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(EvalFractionalAllocation.class).deleteBatch(ids);
	}

	@DELETE
	@Path("EvalFractionalAllocation/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		EvalFractionalAllocation entity = new EvalFractionalAllocation();
		entity.setId(id);
		DaoFactory.create(EvalFractionalAllocation.class).delete(entity);
	}

	@GET
	@Path("EvalFractionalAllocation")
	public Page<EvalFractionalAllocation> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		EvalFractionalAllocation entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalFractionalAllocation.class)
				: new EvalFractionalAllocation();
		return DaoFactory.create(EvalFractionalAllocation.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);
	}

}
