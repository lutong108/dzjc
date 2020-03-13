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
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class Sp_statusController {

	@POST
	@Path("stauts/{id}")
	public Sp_status update(Sp_status entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Sp_status.class).update(entity);
		return entity;
	}

	@POST
	@Path("stauts")
	public Sp_status insert(Sp_status entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Sp_status.class).insert(entity);
		return entity;
	}

	@GET
	@Path("stauts/{id}")
	public Sp_status get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(Sp_status.class).selectByID(id);
	}

	@DELETE
	@Path("stauts")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(Sp_status.class).deleteBatch(ids);
	}

	@DELETE
	@Path("stauts/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		Sp_status entity = new Sp_status();
		entity.setId(id);
		DaoFactory.create(Sp_status.class).delete(entity);
	}

	@GET
	@Path("stauts")
	public Page<Sp_status> getListByPage(@QueryParam(value = "page") int page,
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
		Sp_status entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, Sp_status.class) : new Sp_status();

		return DaoFactory.create(Sp_status.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);

	}
}
