package com.chinacreator.dzjc_tongji.web.rest;

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
import com.chinacreator.dzjc_tongji.SbjGreenData;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SbjGreenDataController {

	@POST
	@Path("getSbjGreenData/{id}")
	public SbjGreenData update(SbjGreenData entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(SbjGreenData.class).update(entity);
		return entity;
	}

	@POST
	@Path("getSbjGreenData")
	public SbjGreenData insert(SbjGreenData entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(SbjGreenData.class).insert(entity);
		return entity;
	}

	@GET
	@Path("getSbjGreenData/{id}")
	public SbjGreenData get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(SbjGreenData.class).selectByID(id);
	}

	@DELETE
	@Path("getSbjGreenData")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(SbjGreenData.class).deleteBatch(ids);
	}

	@DELETE
	@Path("getSbjGreenData/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		SbjGreenData entity = new SbjGreenData();
		entity.setId(id);
		DaoFactory.create(SbjGreenData.class).delete(entity);
	}

	@GET
	@Path("getSbjGreenData")
	public Page<SbjGreenData> getListByPage(
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
		SbjGreenData entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, SbjGreenData.class) : new SbjGreenData();

		return DaoFactory.create(SbjGreenData.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 自定义获取业务信息
	 * 
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("getSbjGreenData")
	public List<SbjGreenData> getSbjGreenData() {
		List<SbjGreenData> sbjGreenDataList = DaoFactory
				.create(SbjGreenData.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_tongji.SbjGreenDataMapper.selectSbjAllData");
		return sbjGreenDataList;
	}
	
	/**
	 * 自定义获取业务信息
	 * 
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("getSzGreenData")
	public Map<String,String> getSzGreenData() {
		Map<String,String> sbjGreenDataMap = DaoFactory
				.create(SbjGreenData.class)
				.getSession()
				.selectOne("com.chinacreator.dzjc_tongji.SbjGreenDataMapper.selectSzAllData");
		return sbjGreenDataMap;
	}
}
