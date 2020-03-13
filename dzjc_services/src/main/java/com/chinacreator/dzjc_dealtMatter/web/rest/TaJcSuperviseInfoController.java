package com.chinacreator.dzjc_dealtMatter.web.rest;

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
import com.chinacreator.dzjc_dealtMatter.TaJcSuperviseInfo;

@Controller
@Path("dzjc_dealtMatter/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcSuperviseInfoController {

	@DELETE
	@Path("taJcSuperviseInfo/{superviseInfoId}")
	public void delete(@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		TaJcSuperviseInfo entity = new TaJcSuperviseInfo();
		entity.setSuperviseInfoId(superviseInfoId);
		DaoFactory.create(TaJcSuperviseInfo.class).delete(entity);
	}

	@DELETE
	@Path("taJcSuperviseInfo")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(TaJcSuperviseInfo.class).deleteBatch(ids);
	}

	@GET
	@Path("taJcSuperviseInfo/{superviseInfoId}")
	public TaJcSuperviseInfo get(@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		return DaoFactory.create(TaJcSuperviseInfo.class).selectByID(superviseInfoId);
	}

	@POST
	@Path("taJcSuperviseInfo")
	public TaJcSuperviseInfo insert(TaJcSuperviseInfo entity) {
		DaoFactory.create(TaJcSuperviseInfo.class).insert(entity);
		return entity;
	}

	@POST
	@Path("taJcSuperviseInfo/{superviseInfoId}")
	public TaJcSuperviseInfo update(TaJcSuperviseInfo entity) {
		DaoFactory.create(TaJcSuperviseInfo.class).update(entity);
		return entity;
	}

	@GET
	@Path("taJcSuperviseInfo")
	public Page<TaJcSuperviseInfo> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcSuperviseInfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcSuperviseInfo.class)
				: new TaJcSuperviseInfo();

		return DaoFactory.create(TaJcSuperviseInfo.class).selectPageByCondition(entity, conditions, pageable, sortable,
				true);

	}

	@GET
	@Path("taJcSuperviseInfo/updateZDY")
	public TaJcSuperviseInfo updateZDY(@QueryParam("superviseInfoId") String superviseInfoId) {

		TaJcSuperviseInfo entity = DaoFactory.create(TaJcSuperviseInfo.class).selectByID(superviseInfoId);

		if (entity != null) {
			entity.setState("Y");//已发牌
		}

		DaoFactory.create(TaJcSuperviseInfo.class).update(entity);

		return entity;
	}

}
