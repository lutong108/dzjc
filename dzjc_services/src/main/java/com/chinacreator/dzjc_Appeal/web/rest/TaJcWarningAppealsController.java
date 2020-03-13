package com.chinacreator.dzjc_Appeal.web.rest;

import java.sql.Date;
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

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_Appeal.TaJcWarningAppeals;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcWarningAppealsController {

	@POST
	@Path("taJcWarningAppeal/{id}")
	public TaJcWarningAppeals update(TaJcWarningAppeals entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaJcWarningAppeals.class).update(entity);
		return entity;
	}

	@POST
	@Path("taJcWarningAppeal")
	public TaJcWarningAppeals insert(TaJcWarningAppeals entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaJcWarningAppeals.class).insert(entity);
		return entity;
	}

	@GET
	@Path("taJcWarningAppeal/{id}")
	public TaJcWarningAppeals get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(TaJcWarningAppeals.class).selectByID(id);
	}

	@DELETE
	@Path("taJcWarningAppeal")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(TaJcWarningAppeals.class).deleteBatch(ids);
	}

	@DELETE
	@Path("taJcWarningAppeal/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		TaJcWarningAppeals entity = new TaJcWarningAppeals();
		entity.setId(id);
		DaoFactory.create(TaJcWarningAppeals.class).delete(entity);
	}
	
	/**
	 * 自定义提交申诉信息
	 * 
	 * @param map
	 * @return
	 */
	@POST
	@Path("taJcWarningAppeal/zdy")
	public TaJcWarningAppeals insertZdy(Map<String, Object> map) {

		String superviseInfoId = (String) map.get("superviseInfoId");
		String appeal_content = (String) map.get("appeal_content");
		String id = (String) map.get("id");

		TaJcWarningAppeals entity = new TaJcWarningAppeals();
		entity.setId(id);
		entity.setSuperviseInfoId(superviseInfoId);//监察编号
		entity.setAppealOrgid(StringUtil.getUserInfo().get("orgId"));//申诉单位ID
		entity.setAppealUserName(StringUtil.getUserInfo().get("name"));//申诉人名字
		entity.setAppealContent(appeal_content);//申诉内容
		entity.setAppealTime(new Date(new java.util.Date().getTime()));//申诉日期

		DaoFactory.create(TaJcWarningAppeals.class).insert(entity);
		return entity;
	}

	@GET
	@Path("taJcWarningAppeal")
	public Page<TaJcWarningAppeals> getListByPage(
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
		TaJcWarningAppeals entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, TaJcWarningAppeals.class)
				: new TaJcWarningAppeals();

		return DaoFactory.create(TaJcWarningAppeals.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
}
