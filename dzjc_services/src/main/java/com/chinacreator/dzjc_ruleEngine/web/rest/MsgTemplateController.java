package com.chinacreator.dzjc_ruleEngine.web.rest;

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
import com.chinacreator.dzjc_ruleEngine.MsgTemplate;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class MsgTemplateController {

	@DELETE
	@Path("msgTemplate/{templateId}")
	public void delete(
			@PathParam(value = "templateId") java.lang.String templateId) {
		//TODO auto-generated method stub
		MsgTemplate entity = new MsgTemplate();
		entity.setTemplateId(templateId);
		DaoFactory.create(MsgTemplate.class).delete(entity);
	}

	@DELETE
	@Path("msgTemplate")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(MsgTemplate.class).deleteBatch(ids);
	}

	@GET
	@Path("msgTemplate/{templateId}")
	public MsgTemplate get(
			@PathParam(value = "templateId") java.lang.String templateId) {
		//TODO auto-generated method stub
		return DaoFactory.create(MsgTemplate.class).selectByID(templateId);
	}

	@POST
	@Path("msgTemplate")
	public MsgTemplate insert(MsgTemplate entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MsgTemplate.class).insert(entity);
		return entity;
	}

	@POST
	@Path("msgTemplate/{templateId}")
	public MsgTemplate update(MsgTemplate entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MsgTemplate.class).update(entity);
		return entity;
	}

	@GET
	@Path("msgTemplate")
	public Page<MsgTemplate> getListByPage(
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
		MsgTemplate entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, MsgTemplate.class) : new MsgTemplate();

		return DaoFactory.create(MsgTemplate.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
