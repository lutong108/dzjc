package com.chinacreator.dzjc_performance.web.rest;

import java.util.Calendar;
import java.util.Date;
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
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_performance.EvalItemValue;
import com.chinacreator.dzjc_performance.EvalItemValueExp;
import com.chinacreator.dzjc_performance.EvalCheckinExp;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalItemValueController {

	@DELETE
	@Path("evalItemValue/{recordId}")
	public void delete(@PathParam(value = "recordId") java.lang.String recordId) {
		//TODO auto-generated method stub
		EvalItemValue entity = new EvalItemValue();
		entity.setRecordId(recordId);
		DaoFactory.create(EvalItemValue.class).delete(entity);
	}

	@DELETE
	@Path("evalItemValue")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(EvalItemValue.class).deleteBatch(ids);
	}

	@GET
	@Path("evalItemValue/{recordId}")
	public EvalItemValue get(
			@PathParam(value = "recordId") java.lang.String recordId) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalItemValue.class).selectByID(recordId);
	}

	@POST
	@Path("evalItemValue")
	public EvalItemValue insert(EvalItemValue entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalItemValue.class).insert(entity);
		return entity;
	}

	@POST
	@Path("evalItemValue/{recordId}")
	public EvalItemValue update(EvalItemValue entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalItemValue.class).update(entity);
		return entity;
	}

	@GET
	@Path("evalItemValue")
	public Page<EvalItemValue> getListByPage(
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
		EvalItemValue entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EvalItemValue.class) : new EvalItemValue();

		return DaoFactory.create(EvalItemValue.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 自定义查询绩效测评统计表
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("evalItemValue2")
	public Page<EvalItemValue> getListByPage2(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		EvalItemValueExp entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EvalItemValueExp.class) : new EvalItemValueExp();
		int pages = pageable == null ? 1 : pageable.getPageIndex();
  		int size = pageable == null ? 10 : pageable.getPageSize();
    	RowBounds4Page rowBounds = new RowBounds4Page((pages-1) * size, size, sortable, conditions,true);
    	
		//创建分页对象
		List<EvalItemValue> list = DaoFactory.create(EvalItemValue.class).getSession().selectList(
				"com.chinacreator.dzjc_performance.EvalItemValueMapper.selectByPage2", entity, rowBounds);
		Page<EvalItemValue> pagingResult = new Page<EvalItemValue>(page, rows, rowBounds.getTotalSize(), list);
		System.out.println(1111111);
		return pagingResult;
	}
}
