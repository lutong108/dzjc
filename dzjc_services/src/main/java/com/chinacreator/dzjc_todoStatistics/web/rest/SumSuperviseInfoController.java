package com.chinacreator.dzjc_todoStatistics.web.rest;

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
import com.chinacreator.dzjc_todoStatistics.SumSuperviseInfo;

@Controller
@Path("dzjc_todoStatistics/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SumSuperviseInfoController {

	@DELETE
	@Path("sumSuperviseInfo/{recordId}")
	public void delete(@PathParam(value = "recordId") java.lang.String recordId) {
		//TODO auto-generated method stub
		SumSuperviseInfo entity = new SumSuperviseInfo();
		entity.setRecordId(recordId);
		DaoFactory.create(SumSuperviseInfo.class).delete(entity);
	}

	@DELETE
	@Path("sumSuperviseInfo")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(SumSuperviseInfo.class).deleteBatch(ids);
	}

	@GET
	@Path("sumSuperviseInfo/{recordId}")
	public SumSuperviseInfo get(
			@PathParam(value = "recordId") java.lang.String recordId) {
		//TODO auto-generated method stub
		return DaoFactory.create(SumSuperviseInfo.class).selectByID(recordId);
	}

	@POST
	@Path("sumSuperviseInfo")
	public SumSuperviseInfo insert(SumSuperviseInfo entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(SumSuperviseInfo.class).insert(entity);
		return entity;
	}

	@POST
	@Path("sumSuperviseInfo/{recordId}")
	public SumSuperviseInfo update(SumSuperviseInfo entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(SumSuperviseInfo.class).update(entity);
		return entity;
	}

	@GET
	@Path("sumSuperviseInfo")
	public Page<SumSuperviseInfo> getListByPage(
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
		SumSuperviseInfo entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SumSuperviseInfo.class)
				: new SumSuperviseInfo();

		return DaoFactory.create(SumSuperviseInfo.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
