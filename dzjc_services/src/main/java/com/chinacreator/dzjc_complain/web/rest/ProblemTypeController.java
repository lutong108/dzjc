package com.chinacreator.dzjc_complain.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import com.chinacreator.dzjc_complain.ProblemType;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ProblemTypeController {

	@DELETE
	@Path("problemType/{problemTypeId}")
	public void delete(@PathParam(value = "problemTypeId") java.lang.String problemTypeId) {
		ProblemType entity = new ProblemType();
		entity.setProblemTypeId(problemTypeId);
		DaoFactory.create(ProblemType.class).delete(entity);
	}

	@DELETE
	@Path("problemType")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(ProblemType.class).deleteBatch(ids);
	}

	@GET
	@Path("problemType/{problemTypeId}")
	public ProblemType get(@PathParam(value = "problemTypeId") java.lang.String problemTypeId) {
		return DaoFactory.create(ProblemType.class).selectByID(problemTypeId);
	}

	@POST
	@Path("problemType")
	public ProblemType insert(ProblemType entity) {
		DaoFactory.create(ProblemType.class).insert(entity);
		return entity;
	}

	@POST
	@Path("problemType/{problemTypeId}")
	public ProblemType update(ProblemType entity) {
		DaoFactory.create(ProblemType.class).update(entity);
		return entity;
	}

	@GET
	@Path("problemType")
	public Page<ProblemType> getListByPage(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		ProblemType entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, ProblemType.class)
				: new ProblemType();

		return DaoFactory.create(ProblemType.class).selectPageByCondition(entity, conditions, pageable, sortable, true);

	}

	/**
	 * 获取全部投诉问题类型
	 * 
	 * @return
	 */
	@POST
	@Path("problemType/getProblemType")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Map<String, List<ProblemType>>> getProblemTypeMap() {

		List<ProblemType> list = DaoFactory.create(ProblemType.class).selectAll();

		Map<String, List<ProblemType>> map = new HashMap<String, List<ProblemType>>();
		map.put("problemTypeList", list);

		Map<String, Map<String, List<ProblemType>>> rtmap = new HashMap<String, Map<String, List<ProblemType>>>();
		rtmap.put("result", map);

		return rtmap;
	}
}
