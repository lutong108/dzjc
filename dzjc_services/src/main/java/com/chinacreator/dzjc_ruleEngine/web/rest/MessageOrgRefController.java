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
import com.chinacreator.dzjc_ruleEngine.MessageOrgRef;

@Controller
@Path("message/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class MessageOrgRefController {

	@POST
	@Path("orgref/{id}")
	public MessageOrgRef update(MessageOrgRef entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MessageOrgRef.class).update(entity);
		return entity;
	}

	@POST
	@Path("orgref")
	public MessageOrgRef insert(MessageOrgRef entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MessageOrgRef.class).insert(entity);
		return entity;
	}

	@GET
	@Path("orgref/{id}")
	public MessageOrgRef get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(MessageOrgRef.class).selectByID(id);
	}

	@DELETE
	@Path("orgref")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(MessageOrgRef.class).deleteBatch(ids);
	}

	@DELETE
	@Path("orgref/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		MessageOrgRef entity = new MessageOrgRef();
		entity.setId(id);
		DaoFactory.create(MessageOrgRef.class).delete(entity);
	}

	@GET
	@Path("orgref")
	public Page<MessageOrgRef> getListByPage(
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
		MessageOrgRef entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, MessageOrgRef.class) : new MessageOrgRef();

		return DaoFactory.create(MessageOrgRef.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
