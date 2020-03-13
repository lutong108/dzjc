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
import com.chinacreator.dzjc_complain.TaJcComplainAttachinfo;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcComplainAttachinfoController {

	@DELETE
	@Path("taJcComplainAttachinfo/{attachId}")
	public void delete(@PathParam(value = "attachId") java.lang.String attachId) {
		TaJcComplainAttachinfo entity = new TaJcComplainAttachinfo();
		entity.setAttachId(attachId);
		DaoFactory.create(TaJcComplainAttachinfo.class).delete(entity);
	}

	@DELETE
	@Path("taJcComplainAttachinfo")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(TaJcComplainAttachinfo.class).deleteBatch(ids);
	}

	@GET
	@Path("taJcComplainAttachinfo/{attachId}")
	public TaJcComplainAttachinfo get(@PathParam(value = "attachId") java.lang.String attachId) {
		return DaoFactory.create(TaJcComplainAttachinfo.class).selectByID(attachId);
	}

	@POST
	@Path("taJcComplainAttachinfo")
	public TaJcComplainAttachinfo insert(TaJcComplainAttachinfo entity) {
		DaoFactory.create(TaJcComplainAttachinfo.class).insert(entity);
		return entity;
	}

	@POST
	@Path("taJcComplainAttachinfo/{attachId}")
	public TaJcComplainAttachinfo update(TaJcComplainAttachinfo entity) {
		DaoFactory.create(TaJcComplainAttachinfo.class).update(entity);
		return entity;
	}

	@GET
	@Path("taJcComplainAttachinfo")
	public Page<TaJcComplainAttachinfo> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainAttachinfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				TaJcComplainAttachinfo.class) : new TaJcComplainAttachinfo();

		return DaoFactory.create(TaJcComplainAttachinfo.class).selectPageByCondition(entity, conditions, pageable,
				sortable, true);

	}
}
