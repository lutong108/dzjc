package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;


import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;

import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.DicEvalPointExp;
import com.chinacreator.dzjc_performance.EvalItem;
import com.chinacreator.dzjc_performance.EvalPointValue;
import com.chinacreator.dzjc_performance.EvalTable;

import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DicEvalPointController {
	@Autowired 
	HttpServletRequest request; //这里可以获取到request
	@DELETE
	@Path("dicEvalPoint/{pointId}")
	public void delete(@PathParam(value = "pointId") java.lang.String pointId) {
		DicEvalPoint entity = new DicEvalPoint();
		entity.setPointId(pointId);
		DaoFactory.create(DicEvalPoint.class).delete(entity);
	}

	@DELETE
	@Path("dicEvalPoint")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(DicEvalPoint.class).deleteBatch(ids);
	}

	@GET
	@Path("dicEvalPoint/{pointId}")
	public DicEvalPoint get(
			@PathParam(value = "pointId") java.lang.String pointId) {
		return DaoFactory.create(DicEvalPoint.class).selectByID(pointId);
	}

	@POST
	@Path("dicEvalPoint")
	public DicEvalPoint insert(DicEvalPoint entity) {
		DaoFactory.create(DicEvalPoint.class).insert(entity);
		return entity;
	}

	@POST
	@Path("dicEvalPoint/{pointId}")
	public DicEvalPoint update(DicEvalPoint entity) {
		DaoFactory.create(DicEvalPoint.class).update(entity);
		return entity;
	}

	@GET
	@Path("dicEvalPoint")
	public Page<DicEvalPoint> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		DicEvalPoint entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, DicEvalPoint.class) : new DicEvalPoint();
		return DaoFactory.create(DicEvalPoint.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
	}

	@GET
	@Path("dicEvalPoint2")
	public Page<DicEvalPointExp> getListByPage2(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		DicEvalPoint entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, DicEvalPoint.class) : new DicEvalPoint();
		RowBounds4Page rowBounds = new RowBounds4Page((page - 1) * rows, rows,
				sortable, conditions, true);
		List<DicEvalPointExp> list = DaoFactory.create(DicEvalPointExp.class).getSession()
				.selectList("com.chinacreator.dzjc_performance.DicEvalPointMapper.selectByPage2",entity, rowBounds);
		Page<DicEvalPointExp> pagingResult = new Page<DicEvalPointExp>(page,rows, rowBounds.getTotalSize(), list);
		return pagingResult;
	}
	
	
	
	@POST
	@Path("/getPointTreeList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getPointTreeList(@FormParam(value = "tableName") java.lang.String tableName) {
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		CommonTreeNode treeNode = null;
		List<EvalPointValue> pointList =new ArrayList<>();
		EvalPointValue evalPointValue =new EvalPointValue();
		if(tableName==null || tableName=="" || tableName.equals("")){
			return null;
		}
		evalPointValue.setTableId(tableName);
		pointList =  DaoFactory.create(DicEvalPointExp.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalPointValueMapper.selectList",evalPointValue);
		for (int i = 0; i < pointList.size(); i++) {
			treeNode = new CommonTreeNode();
			treeNode.setId(pointList.get(i).getPointId());
			treeNode.setShowName(pointList.get(i).getPointName());
			treeNode.setName(pointList.get(i).getPointName());
			list.add(treeNode);
		}
		/**
		 * 大于0,表示该考核表产生过实例直接返回
		 */
		if(list.size()>0){
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}else{
			/**
			 * 考核表未产生直接取字典表
			 */
			//通过考核表获取考核项引用关系
			EvalTable evalTable = DaoFactory.create(EvalTable.class).selectByID(tableName);
			//获取考核表引用的所有考核项
			String tableFormula=evalTable.getTableFormula();
			//取到考核项数组
			String [] tableFormulaArray = tableFormula.split("\\+");
			for (int i = 0; i < tableFormulaArray.length; i++) {
				//通过考核项获取考核指标引用关系
				List<EvalItem> evalItemlist = DaoFactory.create(EvalItem.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalItemMapper.selectByIDcustom",tableFormulaArray[i]);
				//获取考核指标公式
				if(evalItemlist.size()>0){
						String evalItemFormula = evalItemlist.get(0).getItemFormula();
						if(evalItemFormula==null || evalItemFormula==""){
							return null;
						}else{
							//取到考核指标数组
							String [] evalItemFormulaArray=evalItemFormula.split("\\+");
							for (int j = 0; j < evalItemFormulaArray.length; j++) {
								List<DicEvalPoint> dicEvalPointlist =DaoFactory.create(DicEvalPoint.class).getSession().selectList("com.chinacreator.dzjc_performance.DicEvalPointMapper.selectByIDcustom",evalItemFormulaArray[j]);
								treeNode = new CommonTreeNode();
								treeNode.setId(dicEvalPointlist.get(0).getPointId());
								treeNode.setShowName(dicEvalPointlist.get(0).getPointName());
								treeNode.setName(dicEvalPointlist.get(0).getPointName());
								list.add(treeNode);
							}
						}
				}else{
					return null;
				}
			}
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}
	}
	
	
	/**
	 * 获取指标树
	 * @return
	 */
	@POST
	@Path("/getPointTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getElements(){
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
	String tableName =	request.getParameter("tableName");
	System.out.println(tableName);
		// 获取用户信息
		String code = "";
		try {
			// 获取行政区码
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// code为空时，只能是admin用户，目前只有admin用户不是挂在机构下的用户，没有系统业务操作权限
		if (StringUtils.isBlank(code)) {
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}
		List<DicEvalPoint> pointList =new ArrayList<>();
		
		if(code.startsWith("43")){
			// 查询考核指标数树形信息
			pointList = DaoFactory.create(DicEvalPoint.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.DicEvalPointMapper.selectByAreaCode", code);
		}else{
			Orgview orgview = DaoFactory.create(Orgview.class).selectByID(code);
			code = orgview.getCode().substring(0, 12);
			pointList = DaoFactory.create(DicEvalPoint.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.DicEvalPointMapper.selectByAreaCode", code);
		}

		CommonTreeNode treeNode = null;
		for (int i = 0; i < pointList.size(); i++) {
			treeNode = new CommonTreeNode();
			treeNode.setId(pointList.get(i).getPointId());
			treeNode.setShowName(pointList.get(i).getPointName());
			treeNode.setName(pointList.get(i).getPointName());
			list.add(treeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	@GET
	@Path("dicEvalPoint2/{pointName}")
	public DicEvalPoint getByName(
			@PathParam(value = "pointName") java.lang.String pointName) {
		return DaoFactory.create(DicEvalPoint.class).getSession()
				.selectOne("com.chinacreator.dzjc_performance.DicEvalPointMapper.selectByName", pointName);
	}
	
}
