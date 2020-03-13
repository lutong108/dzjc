package com.chinacreator.dzjc_performance.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_performance.EvalCheckin;
import com.chinacreator.dzjc_performance.EvalCheckinExp;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalCheckinController {
	@DELETE
	@Path("evalCheckin/{recordId}")
	public void delete(@PathParam(value = "recordId") java.lang.String recordId) {
		EvalCheckin entity = new EvalCheckin();
		entity.setRecordId(recordId);
		DaoFactory.create(EvalCheckin.class).delete(entity);
	}

	@DELETE
	@Path("evalCheckin")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(EvalCheckin.class).deleteBatch(ids);
	}

	@GET
	@Path("evalCheckin/{recordId}")
	public EvalCheckin get(
			@PathParam(value = "recordId") java.lang.String recordId) {
		return DaoFactory.create(EvalCheckin.class).selectByID(recordId);
	}

	@POST
	@Path("evalCheckin")
	public EvalCheckin insert(EvalCheckin entity) {
		DaoFactory.create(EvalCheckin.class).insert(entity);
		return entity;
	}

	@POST
	@Path("evalCheckin/{recordId}")
	public EvalCheckin update(EvalCheckin entity) {
		DaoFactory.create(EvalCheckin.class).update(entity);
		return entity;
	}

	@GET
	@Path("evalCheckin")
	public Page<EvalCheckin> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		EvalCheckin entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EvalCheckin.class) : new EvalCheckin();
		return DaoFactory.create(EvalCheckin.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
	}

	@GET
	@Path("evalCheckin2")
	public Page<EvalCheckinExp> getListByPage2(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		EvalCheckinExp entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalCheckinExp.class) : new EvalCheckinExp();
		RowBounds4Page rowBounds = new RowBounds4Page((page - 1) * rows, rows,
				sortable, conditions, true);
		List<EvalCheckinExp> list = DaoFactory
				.create(EvalCheckinExp.class)
				.getSession()
				.selectList(
						"com.chinacreator.dzjc_performance.EvalCheckinMapper.selectByPage2",
						entity, rowBounds);
		Page<EvalCheckinExp> pagingResult = new Page<EvalCheckinExp>(page,
				rows, rowBounds.getTotalSize(), list);
		return pagingResult;
	}
}
