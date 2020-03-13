package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.RuleElement;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class RuleElementController {

	@DELETE
	@Path("ruleelement/{elemId}")
	public void delete(@PathParam(value = "elemId") java.lang.String elemId) {
		//TODO auto-generated method stub
		RuleElement entity = new RuleElement();
		entity.setElemId(elemId);
		DaoFactory.create(RuleElement.class).delete(entity);
	}

	@DELETE
	@Path("ruleelement")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(RuleElement.class).deleteBatch(ids);
	}

	@GET
	@Path("ruleelement/{elemId}")
	public RuleElement get(@PathParam(value = "elemId") java.lang.String elemId) {
		
		RuleElement entity = DaoFactory.create(RuleElement.class).selectByID(elemId);
		
		
 		return entity;
	}

	@POST
	@Path("ruleelement")
	public RuleElement insert(RuleElement entity) {
		//自定义新增录入
		entity = this.addRuleElement(entity);
		
		return entity;
	}

	/**
	 * 新增录入
	 * @param entity
	 * @return
	 */
	private RuleElement addRuleElement(RuleElement entity) {
		
		DaoFactory.create(RuleElement.class).getSession()
		.insert("com.chinacreator.dzjc_ruleEngine.RuleElementMapper.customInsert", entity);
		
		return entity;
	}

	@POST
	@Path("ruleelement/{elemId}")
	public RuleElement update(RuleElement entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(RuleElement.class).update(entity);
		return entity;
	}

	@GET
	@Path("ruleelement")
	public Page<RuleElement> getListByPage(
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
		RuleElement entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, RuleElement.class) : new RuleElement();

		return DaoFactory.create(RuleElement.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 自定义获取监察要素，封装为下拉列表
	 * @param areaCode
	 * @return
	 */
	@POST
	@Path("ruleelement/ruleOption")
	public List<RuleElement> getOption(@RequestBody Map<String,String> areaCodeMap) {
		String areaCode=areaCodeMap.get("areacode").trim();
		List<RuleElement> list = DaoFactory
				.create(RuleElement.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.RuleElementMapper.selectByIDOption",
						areaCode);
		
 		return list;
	}
	
	

	/**
	 * 自定义获取监察要素，封装为下拉列表
	 * @param areaCode
	 * @return
	 */
	@POST
	@Path("ruleelement/ruleOptionAll")
	public List<RuleElement> getOptionAll(@RequestBody Map<String,String> areaCodeMap) {
		List<RuleElement> list = DaoFactory
				.create(RuleElement.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.RuleElementMapper.selectByIDOptionAll");
		
 		return list;
	}
	
	
}
