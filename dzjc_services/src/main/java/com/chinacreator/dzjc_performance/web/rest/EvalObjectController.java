package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
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
import org.aspectj.weaver.ast.Var;
import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.EvalObject;
import com.chinacreator.dzjc_performance.service.EvalObjectService;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalObjectController {
	
	@Autowired
	EvalObjectService evalObjectService;

	@DELETE
	@Path("evalObject/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		EvalObject entity = new EvalObject();
		entity.setId(id);
		DaoFactory.create(EvalObject.class).delete(entity);
	}

	@DELETE
	@Path("evalObject")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(EvalObject.class).deleteBatch(ids);
	}

	@GET
	@Path("evalObject/{id}")
	public EvalObject get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalObject.class).selectByID(id);
	}
	
	@GET
	@Path("evalObject/getInstanceState/{evalInstanceId}")
	public List<EvalObject> getInstanceState(@PathParam(value = "evalInstanceId") java.lang.String evalInstanceId) {
		//TODO auto-generated method stub
		List<EvalObject> resultlist= DaoFactory.create(EvalObject.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalObjectMapper.getListByInstanceId",evalInstanceId); 
		
		return resultlist;
	}

	@POST
	@Path("evalObject")
	public EvalObject insert(EvalObject entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalObject.class).insert(entity);
		return entity;
	}
	
	/*@POST
	@Path("evalObject/insertBatch")//    
	public Integer insertBatch(EvalObject entity){
		Integer rows = evalObjectService.insertBatch(entity);
		
		return rows;
	}*/

	@POST
	@Path("evalObject/{id}")
	public EvalObject update(EvalObject entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalObject.class).update(entity);
		return entity;
	}

	@GET
	@Path("evalObject")
	public Page<EvalObject> getListByPage(@QueryParam(value = "page") int page,
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
		EvalObject entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EvalObject.class) : new EvalObject();
		/*if("1".equals(type)){
			entity.setObjectType("1");
		}else if("2".equals(type)){
			entity.setObjectType("2");
		}else if("3".equals(type)){
			entity.setObjectType("3");
		}*/

		return DaoFactory.create(EvalObject.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
}
