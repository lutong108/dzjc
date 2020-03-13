package com.chinacreator.dzjc_complain.web.rest;

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
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.TaJcComplainTasklist;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcComplainTasklistController {

	@DELETE
	@Path("taJcComplainTasklist/{taskId}")
	public void delete(@PathParam(value = "taskId") java.lang.String taskId) {
		//TODO auto-generated method stub
		TaJcComplainTasklist entity = new TaJcComplainTasklist();
		entity.setTaskId(taskId);
		DaoFactory.create(TaJcComplainTasklist.class).delete(entity);
	}

	@DELETE
	@Path("taJcComplainTasklist")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(TaJcComplainTasklist.class).deleteBatch(ids);
	}

	@GET
	@Path("taJcComplainTasklist/{taskId}")
	public TaJcComplainTasklist get(@PathParam(value = "taskId") java.lang.String taskId) {
		//TODO auto-generated method stub
		return DaoFactory.create(TaJcComplainTasklist.class).selectByID(taskId);
	}

	@POST
	@Path("taJcComplainTasklist")
	public TaJcComplainTasklist insert(TaJcComplainTasklist entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaJcComplainTasklist.class).insert(entity);
		return entity;
	}

	@POST
	@Path("taJcComplainTasklist/{taskId}")
	public TaJcComplainTasklist update(TaJcComplainTasklist entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaJcComplainTasklist.class).update(entity);
		return entity;
	}

	@GET
	@Path("taJcComplainTasklist")
	public Page<TaJcComplainTasklist> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//TODO auto-generated method stub
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		TaJcComplainTasklist entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainTasklist.class)
				: new TaJcComplainTasklist();

		return DaoFactory.create(TaJcComplainTasklist.class).selectPageByCondition(entity, conditions, pageable,
				sortable, true);

	}
	
	@POST
	@Path("getSingleTask")
	public TaJcComplainTasklist getSingleTask(TaJcComplainTasklist entity) {
		return DaoFactory.create(TaJcComplainTasklist.class).selectOne(entity);
	}
	
	
}
