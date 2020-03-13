package com.chinacreator.dzjc_ruleEngine.web.rest;

import javax.ws.rs.Consumes;
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
import com.chinacreator.dzjc_ruleEngine.DeleteSupervise;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DeleteSuperviseController {
	@POST
	@Path("deleteSupervise/{superviseInfoId}")
	public DeleteSupervise update(DeleteSupervise entity) {
		DaoFactory.create(DeleteSupervise.class).update(entity);
		return entity;
	}

	@POST
	@Path("deleteSupervise")
	public DeleteSupervise insert(DeleteSupervise entity) {
		DaoFactory.create(DeleteSupervise.class).insert(entity);
		return entity;
	}

	@GET
	@Path("deleteSupervise/{superviseInfoId}")
	public DeleteSupervise get(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		return DaoFactory.create(DeleteSupervise.class).selectByID(
				superviseInfoId);
	}

	@GET
	@Path("deleteSupervise")
	public Page<DeleteSupervise> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		DeleteSupervise entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, DeleteSupervise.class)
				: new DeleteSupervise();
		return DaoFactory.create(DeleteSupervise.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
	}

}
