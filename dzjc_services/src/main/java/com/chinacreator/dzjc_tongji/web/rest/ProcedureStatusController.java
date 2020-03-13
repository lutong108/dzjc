package com.chinacreator.dzjc_tongji.web.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_tongji.ProcedureStatus;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ProcedureStatusController {

	@GET
	@Path("ProcedureStatus")
	public Page<ProcedureStatus> getListByPage(
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
		ProcedureStatus entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, ProcedureStatus.class): new ProcedureStatus();
		return DaoFactory.create(ProcedureStatus.class).selectPageByCondition(entity, conditions, pageable, sortable, true);
	}
	
	
	@POST
	@Path("ProcedureStatus/getProcedureStatusByID")
	public ProcedureStatus get(@RequestBody Map<String, String> map) {
		String id = map.get("id");
		return DaoFactory.create(ProcedureStatus.class).selectByID(id);
	}
	
	@GET
	@Path("ProcedureStatus/{id}")
	public ProcedureStatus get(@PathParam(value = "id") java.lang.String id) {
		return DaoFactory.create(ProcedureStatus.class).selectByID(id);
	}
	
}
