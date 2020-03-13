package com.chinacreator.dzjc_performance.web.rest;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_performance.EvalRevieweAgree;
import com.chinacreator.dzjc_performance.service.EvalRevieweAgreeService;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalRevieweAgreeController {
	
	@Autowired
	EvalRevieweAgreeService evalRevieweAgreeService;

	@DELETE
	@Path("EvalRevieweAgree/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		EvalRevieweAgree entity = new EvalRevieweAgree();
		entity.setId(id);
		DaoFactory.create(EvalRevieweAgree.class).delete(entity);
	}

	@DELETE
	@Path("EvalRevieweAgree")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(EvalRevieweAgree.class).deleteBatch(ids);
	}

	@GET
	@Path("EvalRevieweAgree/{id}")
	public EvalRevieweAgree get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalRevieweAgree.class).selectByID(id);
	}
	
	//根据申诉审核表的id查询意见信息表
	@GET
	@Path("EvalRevieweAgree/getByRevieweId/{revieweId}")
	public EvalRevieweAgree getByRevieweId(@PathParam(value = "revieweId") java.lang.String revieweId) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalRevieweAgree.class).getSession()
					.selectOne("com.chinacreator.dzjc_performance.EvalRevieweAgreeMapper.selectByRevieweId", revieweId);
	}
	
	//根据指标成绩的id查询意见信息表
	@GET
	@Path("EvalRevieweAgree/getByEvalPointValueId/{evalPointValueId}")
	public EvalRevieweAgree getByEvalPointValueId(@PathParam(value = "evalPointValueId") java.lang.String evalPointValueId) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalRevieweAgree.class).getSession()
				.selectOne("com.chinacreator.dzjc_performance.EvalRevieweAgreeMapper.selectByEvalPointValueId", evalPointValueId);
	}

	//审核意见保存的方法
	@POST
	@Path("EvalRevieweAgree")
	public EvalRevieweAgree insert(EvalRevieweAgree entity) {
		//TODO auto-generated method stub	
		
		evalRevieweAgreeService.insertRevieweAgree(entity);
		return entity;
	}

	@POST
	@Path("EvalRevieweAgree/{id}")
	public EvalRevieweAgree update(EvalRevieweAgree entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalRevieweAgree.class).update(entity);
		return entity;
	}

	@GET
	@Path("EvalRevieweAgree")
	public Page<EvalRevieweAgree> getListByPage(
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
		EvalRevieweAgree entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalRevieweAgree.class)
				: new EvalRevieweAgree();

		return DaoFactory.create(EvalRevieweAgree.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
