package com.chinacreator.dzjc_complain.web.rest;

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
import com.chinacreator.dzjc_complain.NewsFbView;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class NewsFbViewController {

	@DELETE
	@Path("newsfbview/{lawsId}")
	public void delete(@PathParam(value = "lawsId") java.lang.String lawsId) {
		NewsFbView entity = new NewsFbView();
		entity.setLawsId(lawsId);
		DaoFactory.create(NewsFbView.class).delete(entity);
	}

	@DELETE
	@Path("newsfbview")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(NewsFbView.class).deleteBatch(ids);
	}

	@GET
	@Path("newsfbview/{lawsId}")
	public NewsFbView get(@PathParam(value = "lawsId") java.lang.String lawsId) {
		return DaoFactory.create(NewsFbView.class).selectByID(lawsId);
	}

	@POST
	@Path("newsfbview")
	public NewsFbView insert(NewsFbView entity) {
		DaoFactory.create(NewsFbView.class).insert(entity);
		return entity;
	}

	@POST
	@Path("newsfbview/{lawsId}")
	public NewsFbView update(NewsFbView entity) {
		DaoFactory.create(NewsFbView.class).update(entity);
		return entity;
	}

	@GET
	@Path("newsfbview")
	public Page<NewsFbView> getListByPage(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {

		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		NewsFbView entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, NewsFbView.class) : new NewsFbView();

		return DaoFactory.create(NewsFbView.class).selectPageByCondition(entity, conditions, pageable, sortable, true);

	}
}
