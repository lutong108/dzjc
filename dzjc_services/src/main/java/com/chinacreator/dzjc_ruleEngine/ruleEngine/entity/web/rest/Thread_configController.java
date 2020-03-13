package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.web.rest;

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
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_config;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class Thread_configController {
	@POST
	@Path("thread_config/{configId}")
	public Thread_config update(Thread_config entity) {
		DaoFactory.create(Thread_config.class).update(entity);
		return entity;
	}

	@POST
	@Path("thread_config")
	public Thread_config insert(Thread_config entity) {
		DaoFactory.create(Thread_config.class).insert(entity);
		return entity;
	}

	@POST
	@Path("/selectAllList")
	public String selectAllList() {
		String result = "";
		List<Thread_config> list = null;
		list = DaoFactory.create(Thread_config.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Thread_configMapper.selectAllList");
		if (list.size() > 0) {
			result = "1";
		} else {
			result = "0";
		}
		return result;
	}

	@GET
	@Path("thread_config/{configId}")
	public Thread_config get(
			@PathParam(value = "configId") java.lang.String configId) {
		return DaoFactory.create(Thread_config.class).selectByID(configId);
	}

	@DELETE
	@Path("thread_config")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(Thread_config.class).deleteBatch(ids);
	}

	@DELETE
	@Path("thread_config/{configId}")
	public void delete(@PathParam(value = "configId") java.lang.String configId) {
		Thread_config entity = new Thread_config();
		entity.setConfigId(configId);
		DaoFactory.create(Thread_config.class).delete(entity);
	}

	@GET
	@Path("thread_config")
	public Page<Thread_config> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		Thread_config entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, Thread_config.class) : new Thread_config();
		return DaoFactory.create(Thread_config.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
	}

}
