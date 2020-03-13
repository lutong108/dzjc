package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
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
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.EvalPointValue;
import com.chinacreator.dzjc_performance.service.EvalPointValueService;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalPointValueController {
	
	private String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
	
	@Autowired
	EvalPointValueService evalPointValueService;

	@DELETE
	@Path("evalPointValue/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		EvalPointValue entity = new EvalPointValue();
		entity.setId(id);
		DaoFactory.create(EvalPointValue.class).delete(entity);
	}

	@DELETE
	@Path("evalPointValue")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(EvalPointValue.class).deleteBatch(ids);
	}

	@GET
	@Path("evalPointValue/{id}")
	public EvalPointValue get(@PathParam(value = "id") java.lang.String id) {
		DicEvalPoint dicEvalPoint =new DicEvalPoint();
		EvalPointValue evalPointValue = new EvalPointValue();
		evalPointValue = DaoFactory.create(EvalPointValue.class).selectByID(id);
		if(evalPointValue.getIsGrade().equals("N")){
			dicEvalPoint=DaoFactory.create(DicEvalPoint.class).selectByID(evalPointValue.getPointId());
			evalPointValue.setPointScore(dicEvalPoint.getMaxValue());
		}
		return evalPointValue;
	}

	@POST
	@Path("evalPointValue")
	public EvalPointValue insert(EvalPointValue entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalPointValue.class).insert(entity);
		return entity;
	}

	//评分的方法
	@POST
	@Path("evalPointValue/{id}")
	public EvalPointValue update(EvalPointValue entity) {
		//TODO auto-generated method stub	
		evalPointValueService.updatePointValue(entity);
		return entity;
	}
	
	//申诉的方法
	@POST
	@Path("evalPointValue/updateRevieweStates")
	public EvalPointValue updateRevieweStates(EvalPointValue entity) {
		//TODO auto-generated method stub	
		evalPointValueService.updateRevieweStates(entity);
		return entity;
	}

	@GET
	@Path("evalPointValue")
	public Page<EvalPointValue> getListByPage(
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
		EvalPointValue entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalPointValue.class) : new EvalPointValue();
		
		return DaoFactory.create(EvalPointValue.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	//查询当前登录用户的个人，所在部门，所在窗口（先不考虑）的指标成绩信息
	@GET
	@Path("evalPointValue/getAllList")
	public Page<EvalPointValue> getAllListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		//TODO auto-generated method stub
		
		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		//String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");
		
		String userId = user.getId();//用户个人id
		String userOrgId = orgIds[0];//用户所在机构的id
		
		//创建分页对象
		//Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		EvalPointValue entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalPointValue.class) : new EvalPointValue();
				
		RowBounds4Page rowBounds = new RowBounds4Page((page - 1) * rows, rows, sortable, conditions, true);
		
		//根据用户id查询用户的指标成绩信息
		entity.setEvalObjectId(userId);
		List<EvalPointValue> userList = DaoFactory.create(EvalPointValue.class)
			.getSession().selectList("com.chinacreator.dzjc_performance.EvalPointValueMapper.selectByEvalObjectId", entity,rowBounds);
		
		//根据用户的所在的部门id，查询部门的指标成绩信息
		entity.setEvalObjectId(userOrgId);
		List<EvalPointValue> orgList = DaoFactory.create(EvalPointValue.class)
				.getSession().selectList("com.chinacreator.dzjc_performance.EvalPointValueMapper.selectByEvalObjectId", entity,rowBounds);
		
		List<EvalPointValue> list = new ArrayList<EvalPointValue>();
		list.addAll(userList);
		list.addAll(orgList);
		
		Page<EvalPointValue> pagingResult =
				new Page<EvalPointValue>(page, rows, true ? rowBounds.getTotalSize() : 0, list);
		
		return pagingResult;

	}
	
	@GET
	@Path("evalPointValue/getDeptList")
	public Page<EvalPointValue> getDeptListByPage(
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
		EvalPointValue entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalPointValue.class) : new EvalPointValue();
		if(conditions!=null){
			List<Rule> listRule= conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field= listRule.get(i).getField();
				if("pointId".equals(field)){
					String  pointId=(String) listRule.get(i).getData();
					String array[] = pointId.split(",");
					listRule.get(i).setData(array);
					System.out.println(array);
				}
			}
		}
		entity.setEvalObjectType("1");//考核类型是部门的
		 Page<EvalPointValue> pagelist= DaoFactory.create(EvalPointValue.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
		if(pagelist.getTotal()>0){
			for (int i = 0; i < pagelist.getContents().size(); i++) {
				if(pagelist.getContents().get(i).getIsGrade().equals("N")){
					pagelist.getContents().get(i).setPointScore(pagelist.getContents().get(i).getMaxScore());
				}
			}
		}
		 return pagelist;
	}
	
	@GET
	@Path("evalPointValue/getByType")
	public Page<EvalPointValue> getListPageByType(
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
		EvalPointValue entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalPointValue.class) : new EvalPointValue();
		if(conditions!=null){
					List<Rule> listRule= conditions.getRules();
					for (int i = 0; i < listRule.size(); i++) {
						String field= listRule.get(i).getField();
						if("pointId".equals(field)){
							String  pointId=(String) listRule.get(i).getData();
							String array[] = pointId.split(",");
							listRule.get(i).setData(array);
							System.out.println(array);
						}
					}
		}
		entity.setEvalObjectType("3");
				
		Page<EvalPointValue> pagelist= DaoFactory.create(EvalPointValue.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
		if(pagelist.getTotal()>0){
			for (int i = 0; i < pagelist.getContents().size(); i++) {
				if(pagelist.getContents().get(i).getIsGrade().equals("N")){
					pagelist.getContents().get(i).setPointScore(pagelist.getContents().get(i).getMaxScore());
				}
			}
		}
		return pagelist;
				
	}
}
