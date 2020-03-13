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
import com.chinacreator.dzjc_performance.VOrganization;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class VOrganizationController {

	@DELETE
	@Path("VOrganization/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		VOrganization entity = new VOrganization();
		entity.setId(id);
		DaoFactory.create(VOrganization.class).delete(entity);
	}

	@DELETE
	@Path("VOrganization")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(VOrganization.class).deleteBatch(ids);
	}

	@GET
	@Path("VOrganization/{id}")
	public VOrganization get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(VOrganization.class).selectByID(id);
	}

	@POST
	@Path("VOrganization")
	public VOrganization insert(VOrganization entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(VOrganization.class).insert(entity);
		return entity;
	}

	@POST
	@Path("VOrganization/{id}")
	public VOrganization update(VOrganization entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(VOrganization.class).update(entity);
		return entity;
	}

	@GET
	@Path("VOrganization")
	public Page<VOrganization> getListByPage(
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
		VOrganization entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, VOrganization.class) : new VOrganization();

		return DaoFactory.create(VOrganization.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 根据id查询code
	 */
	@GET
	@Path("VOrganization/getCodeById/{id}")
	public VOrganization getCodeById(@PathParam(value = "id") String id){
		return DaoFactory.create(VOrganization.class).selectByID(id);
	}
}
