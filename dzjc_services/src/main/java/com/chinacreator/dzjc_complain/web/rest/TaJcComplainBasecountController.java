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
import com.chinacreator.dzjc_complain.TaJcComplainBasecount;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcComplainBasecountController {

	@DELETE
	@Path("taJcComplainBasecount/{basecountid}")
	public void delete(@PathParam(value = "basecountid") java.lang.String basecountid) {
		TaJcComplainBasecount entity = new TaJcComplainBasecount();
		entity.setBasecountid(basecountid);
		DaoFactory.create(TaJcComplainBasecount.class).delete(entity);
	}

	@DELETE
	@Path("taJcComplainBasecount")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(TaJcComplainBasecount.class).deleteBatch(ids);
	}

	@GET
	@Path("taJcComplainBasecount/{basecountid}")
	public TaJcComplainBasecount get(@PathParam(value = "basecountid") java.lang.String basecountid) {
		return DaoFactory.create(TaJcComplainBasecount.class).selectByID(basecountid);
	}

	@POST
	@Path("taJcComplainBasecount")
	public TaJcComplainBasecount insert(TaJcComplainBasecount entity) {
		DaoFactory.create(TaJcComplainBasecount.class).insert(entity);
		return entity;
	}

	@POST
	@Path("taJcComplainBasecount/{basecountid}")
	public TaJcComplainBasecount update(TaJcComplainBasecount entity) {
		DaoFactory.create(TaJcComplainBasecount.class).update(entity);
		return entity;
	}

	@GET
	@Path("taJcComplainBasecount")
	public Page<TaJcComplainBasecount> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainBasecount entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				TaJcComplainBasecount.class) : new TaJcComplainBasecount();

		return DaoFactory.create(TaJcComplainBasecount.class).selectPageByCondition(entity, conditions, pageable,
				sortable, true);

	}
}
