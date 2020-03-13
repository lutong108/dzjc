package com.chinacreator.dzjc_complain.web.rest;

import java.sql.Date;
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
import com.chinacreator.dzjc_complain.TaJcComplainTopheadlines;
import com.chinacreator.util.FtpUtils;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcComplainTopheadlinesController {

	@DELETE
	@Path("taJcComplainTopheadlines/{topId}")
	public void delete(@PathParam(value = "topId") java.lang.String topId) {
		TaJcComplainTopheadlines entity = new TaJcComplainTopheadlines();
		entity.setTopId(topId);
		DaoFactory.create(TaJcComplainTopheadlines.class).delete(entity);
	}

	@DELETE
	@Path("taJcComplainTopheadlines")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(TaJcComplainTopheadlines.class).deleteBatch(ids);
	}

	@GET
	@Path("taJcComplainTopheadlines/{topId}")
	public TaJcComplainTopheadlines get(@PathParam(value = "topId") java.lang.String topId) {
		TaJcComplainTopheadlines selectByID = DaoFactory.create(TaJcComplainTopheadlines.class).selectByID(topId);
		return selectByID;
	}

	@POST
	@Path("taJcComplainTopheadlines")
	public TaJcComplainTopheadlines insert(TaJcComplainTopheadlines entity) {

		if (entity != null) {
			entity.setCreateTime(new Date(new java.util.Date().getTime()));
			entity.setCreateUser(StringUtil.getUserInfo().get("name"));
			entity.setLastupdatetime(entity.getCreateTime());
			if(StringUtils.isNotBlank(entity.getTopContent())){
				entity.setTopContent(FtpUtils.ftpUpload(entity.getTopContent()));
			}
		}

		DaoFactory.create(TaJcComplainTopheadlines.class).insert(entity);
		return entity;
	}

	@POST
	@Path("taJcComplainTopheadlines/{topId}")
	public TaJcComplainTopheadlines update(TaJcComplainTopheadlines entity) {

		if (entity != null) {
			entity.setLastupdatetime(new Date(new java.util.Date().getTime()));
			if(StringUtils.isNotBlank(entity.getTopContent())){
				entity.setTopContent(FtpUtils.ftpUpload(entity.getTopContent()));
			}
		}

		DaoFactory.create(TaJcComplainTopheadlines.class).update(entity);
		return entity;
	}

	@GET
	@Path("taJcComplainTopheadlines")
	public Page<TaJcComplainTopheadlines> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {

		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainTopheadlines entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				TaJcComplainTopheadlines.class) : new TaJcComplainTopheadlines();

		return DaoFactory.create(TaJcComplainTopheadlines.class).selectPageByCondition(entity, conditions, pageable,
				sortable, true);

	}

}
