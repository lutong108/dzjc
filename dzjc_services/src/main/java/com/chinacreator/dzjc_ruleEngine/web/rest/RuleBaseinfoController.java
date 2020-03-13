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
import com.chinacreator.dzjc_ruleEngine.RuleBaseinfo;
import com.chinacreator.dzjc_ruleEngine.RuleExpInfo;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class RuleBaseinfoController {

	@DELETE
	@Path("ruleBaseinfo/{ruleId}")
	public void delete(@PathParam(value = "ruleId") java.lang.String ruleId) {
		//TODO auto-generated method stub
		RuleBaseinfo entity = new RuleBaseinfo();
		entity.setRuleId(ruleId);
		DaoFactory.create(RuleBaseinfo.class).delete(entity);
	}

	@DELETE
	@Path("ruleBaseinfo")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(RuleBaseinfo.class).deleteBatch(ids);
	}

	@GET
	@Path("ruleBaseinfo/{ruleId}")
	public RuleBaseinfo get(@PathParam(value = "ruleId") java.lang.String ruleId) {
		//TODO auto-generated method stub
		return DaoFactory.create(RuleBaseinfo.class).selectByID(ruleId);
	}

	@POST
	@Path("ruleBaseinfo")
	public RuleBaseinfo insert(RuleBaseinfo entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(RuleBaseinfo.class).insert(entity);
		RuleBaseinfo ruleBaseinfo =new RuleBaseinfo();
		ruleBaseinfo.setRuleName(entity.getRuleName());
		ruleBaseinfo.setRuleVersion(entity.getRuleVersion());
		RuleBaseinfo entity1= DaoFactory.create(RuleBaseinfo.class).getSession().selectOne("com.chinacreator.dzjc_ruleEngine.RuleBaseinfoMapper.selectByRuleName",ruleBaseinfo);
		return entity1;
	}

	@POST
	@Path("ruleBaseinfo/{ruleId}")
	public RuleBaseinfo update(RuleBaseinfo entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(RuleBaseinfo.class).update(entity);
		return entity;
	}

	@GET
	@Path("ruleBaseinfo")
	public Page<RuleBaseinfo> getListByPage(
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
		RuleBaseinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, RuleBaseinfo.class) : new RuleBaseinfo();

		return DaoFactory.create(RuleBaseinfo.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
