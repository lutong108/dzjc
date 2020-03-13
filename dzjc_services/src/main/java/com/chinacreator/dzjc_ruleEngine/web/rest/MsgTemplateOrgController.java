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
import com.chinacreator.dzjc_ruleEngine.MsgTemplateOrg;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class MsgTemplateOrgController {

	@POST
	@Path("msgTemplateOrg/{templateId}")
	public MsgTemplateOrg update(MsgTemplateOrg entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MsgTemplateOrg.class).update(entity);
		return entity;
	}

	@POST
	@Path("msgTemplateOrg")
	public MsgTemplateOrg insert(MsgTemplateOrg entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MsgTemplateOrg.class).insert(entity);
		return entity;
	}

	@GET
	@Path("msgTemplateOrg/{templateId}")
	public MsgTemplateOrg get(
			@PathParam(value = "templateId") java.lang.String templateId) {
		//TODO auto-generated method stub
		return DaoFactory.create(MsgTemplateOrg.class).selectByID(
				templateId);
	}

	@DELETE
	@Path("msgTemplateOrg")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(MsgTemplateOrg.class).deleteBatch(ids);
	}

	@DELETE
	@Path("msgTemplateOrg/{templateId}")
	public void delete(
			@PathParam(value = "templateId") java.lang.String templateId) {
		//TODO auto-generated method stub
		MsgTemplateOrg entity = new MsgTemplateOrg();
		entity.setTemplateId(templateId);
		DaoFactory.create(MsgTemplateOrg.class).delete(entity);
	}
	@GET
	@Path("getMsgTemplateOrgAll")
	public List<MsgTemplateOrg> getMsgTemplateOrgAll() {
		return DaoFactory.create(MsgTemplateOrg.class).selectAll();
	}
	@GET
	@Path("msgTemplateOrg")
	public Page<MsgTemplateOrg> getListByPage(
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
		MsgTemplateOrg entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, MsgTemplateOrg.class)
				: new MsgTemplateOrg();

		return DaoFactory.create(MsgTemplateOrg.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
}
