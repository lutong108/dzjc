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
import com.chinacreator.dzjc_performance.EvalCheckinExp;
import com.chinacreator.dzjc_performance.EvalItemExp;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalItemController {

	@DELETE
	@Path("evalItem/{itemId}")
	public void delete(@PathParam(value = "itemId") java.lang.String itemId) {
		//TODO auto-generated method stub
		EvalItem entity = new EvalItem();
		entity.setItemId(itemId);
		DaoFactory.create(EvalItem.class).delete(entity);
	}

	@DELETE
	@Path("evalItem")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(EvalItem.class).deleteBatch(ids);
	}

	@GET
	@Path("evalItem/{itemId}")
	public EvalItem get(@PathParam(value = "itemId") java.lang.String itemId) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalItem.class).selectByID(itemId);
	}

	@POST
	@Path("evalItem")
	public EvalItem insert(EvalItem entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalItem.class).insert(entity);
		return entity;
	}

	@POST
	@Path("evalItem/{itemId}")
	public EvalItem update(EvalItem entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalItem.class).update(entity);
		return entity;
	}

	@GET
	@Path("evalItem")
	public Page<EvalItem> getListByPage(@QueryParam(value = "page") int page,
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
		EvalItem entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				EvalItem.class) : new EvalItem();

		return DaoFactory.create(EvalItem.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);

	}
	
	@GET
	@Path("evalItem2")
	public Page<EvalItemExp> getListByPage2(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		EvalItemExp entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, EvalItemExp.class) : new EvalItemExp();
		RowBounds4Page rowBounds = new RowBounds4Page((page - 1) * rows, rows,
				sortable, conditions, true);
		List<EvalItemExp> list = DaoFactory.create(EvalItemExp.class).getSession()
				.selectList("com.chinacreator.dzjc_performance.EvalItemMapper.selectByPage2",entity, rowBounds);
		Page<EvalItemExp> pagingResult = new Page<EvalItemExp>(page,rows, rowBounds.getTotalSize(), list);
		return pagingResult;
	}
	
	@GET
	@Path("evalItem2/{itemName}")
	public EvalItem getByName(
			@PathParam(value = "itemName") java.lang.String itemName) {
		return DaoFactory.create(EvalItem.class).getSession()
				.selectOne("com.chinacreator.dzjc_performance.EvalItemMapper.selectByName", itemName);
	}
	
	
	/**
	 * 获取父考核项树
	 * @return
	 */
	@POST
	@Path("/getItemTree")
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
		List<EvalItem> itemList = new ArrayList<>();
		if(code.startsWith("43")){
			// 查询考核指标数树形信息
			itemList = DaoFactory.create(EvalItem.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.EvalItemMapper.selectByAreaCode", code);
		}else{
			Orgview orgview = DaoFactory.create(Orgview.class).selectByID(code);
			code = orgview.getCode().substring(0, 12);
			itemList = DaoFactory.create(EvalItem.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.EvalItemMapper.selectByAreaCode", code);
		}
	
		CommonTreeNode treeNode = null;
		
		for (int i = 0; i < itemList.size(); i++) {
			treeNode = new CommonTreeNode();
			treeNode.setId(itemList.get(i).getItemId());
			treeNode.setShowName(itemList.get(i).getItemName());
			treeNode.setName(itemList.get(i).getItemName());
			treeNode.setPid(itemList.get(i).getParentItemId());
			list.add(treeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	/**
	 * 获取父考核项树
	 * @return
	 */
	@POST
	@Path("/getPatentTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getPatentTree(){
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
		List<EvalItem> itemList = new ArrayList<>();
		if(code.startsWith("43")){
			// 查询考核指标数树形信息
			itemList = DaoFactory.create(EvalItem.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.EvalItemMapper.selectParents", code);
		}else{
			Orgview orgview = DaoFactory.create(Orgview.class).selectByID(code);
			code = orgview.getCode().substring(0, 12);
			itemList = DaoFactory.create(EvalItem.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.EvalItemMapper.selectParents", code);
		}
	
		CommonTreeNode treeNode = null;
		
		for (int i = 0; i < itemList.size(); i++) {
			treeNode = new CommonTreeNode();
			treeNode.setId(itemList.get(i).getItemId());
			treeNode.setShowName(itemList.get(i).getItemName());
			treeNode.setName(itemList.get(i).getItemName());
			treeNode.setPid(itemList.get(i).getParentItemId());
			list.add(treeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
}
