package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;

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
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.EvalItem;
import com.chinacreator.dzjc_performance.EvalTable;
import com.chinacreator.dzjc_performance.EvalTableExp;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalTableController {

	@DELETE
	@Path("evalTable/{tableId}")
	public void delete(@PathParam(value = "tableId") java.lang.String tableId) {
		//TODO auto-generated method stub
		EvalTable entity = new EvalTable();
		entity.setTableId(tableId);
		DaoFactory.create(EvalTable.class).delete(entity);
	}

	@DELETE
	@Path("evalTable")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(EvalTable.class).deleteBatch(ids);
	}

	@GET
	@Path("evalTable/{tableId}")
	public EvalTable get(@PathParam(value = "tableId") java.lang.String tableId) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalTable.class).selectByID(tableId);
	}

	@POST
	@Path("evalTable")
	public EvalTable insert(EvalTable entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalTable.class).insert(entity);
		return entity;
	}

	@POST
	@Path("evalTable/{tableId}")
	public EvalTable update(EvalTable entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalTable.class).update(entity);
		return entity;
	}

	@GET
	@Path("evalTable")
	public Page<EvalTable> getListByPage(@QueryParam(value = "page") int page,
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
		EvalTable entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EvalTable.class) : new EvalTable();

		return DaoFactory.create(EvalTable.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);

	}
	
	@GET
	@Path("evalTable2")
	public Page<EvalTableExp> getListByPage2(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		EvalTableExp entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalTableExp.class) : new EvalTableExp();
		RowBounds4Page rowBounds = new RowBounds4Page((page - 1) * rows, rows,
				sortable, conditions, true);
		List<EvalTableExp> list = DaoFactory.create(EvalTableExp.class).getSession()
				.selectList("com.chinacreator.dzjc_performance.EvalTableMapper.selectByPage2",entity, rowBounds);
		Page<EvalTableExp> pagingResult = new Page<EvalTableExp>(page,rows, rowBounds.getTotalSize(), list);
		return pagingResult;
	}
	
	/**
	 * 获取考核项树
	 * @return
	 */
	@POST
	@Path("/getTableTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getElements(){
		
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
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
		List<EvalTable> tableList =new ArrayList<>();
		EvalTable evalTable =new EvalTable();
		if(code!="" && code!=null){
			evalTable.setAreaCode(code);
		}
		if(code.startsWith("43")){
			// 查询考核指标数树形信息
			tableList = DaoFactory.create(EvalTable.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.EvalTableMapper.selectByAreaCode2", evalTable);
		}else{
			Orgview orgview = DaoFactory.create(Orgview.class).selectByID(code);
			code = orgview.getCode().substring(0, 12);
			tableList = DaoFactory.create(EvalTable.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.EvalTableMapper.selectByAreaCode2", evalTable);
		}
		
		CommonTreeNode treeNode = null;
		
		for (int i = 0; i < tableList.size(); i++) {
			treeNode = new CommonTreeNode();
			treeNode.setId(tableList.get(i).getTableId());
			treeNode.setShowName(tableList.get(i).getTableName());
			treeNode.setName(tableList.get(i).getTableName());
			list.add(treeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
}
