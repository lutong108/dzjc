package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.DicEvalPointClass;
import com.chinacreator.dzjc_performance.DicEvalPointClassExp;
import com.chinacreator.dzjc_performance.VEvalPoint;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DicEvalPointClassController {

	@DELETE
	@Path("dicEvalPointClass/{pointTypeId}")
	public void delete(
			@PathParam(value = "pointTypeId") java.lang.String pointTypeId) {
		//TODO auto-generated method stub
		DicEvalPointClass entity = new DicEvalPointClass();
		entity.setPointTypeId(pointTypeId);
		DaoFactory.create(DicEvalPointClass.class).delete(entity);
	}

	@DELETE
	@Path("dicEvalPointClass")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(DicEvalPointClass.class).deleteBatch(ids);
	}

	@GET
	@Path("dicEvalPointClass/{pointTypeId}")
	public DicEvalPointClass get(
			@PathParam(value = "pointTypeId") java.lang.String pointTypeId) {
		//TODO auto-generated method stub
		return DaoFactory.create(DicEvalPointClass.class).selectByID(
				pointTypeId);
	}

	@POST
	@Path("dicEvalPointClass")
	public DicEvalPointClass insert(DicEvalPointClass entity) {
		//TODO auto-generated method stub	
		if(StringUtils.isBlank(entity.getParentPointtypeId())){
			entity.setParentPointtypeId("0");
		}
		DaoFactory.create(DicEvalPointClass.class).insert(entity);
		return entity;
	}

	@POST
	@Path("dicEvalPointClass/{pointTypeId}")
	public DicEvalPointClass update(DicEvalPointClass entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(DicEvalPointClass.class).update(entity);
		return entity;
	}

	@GET
	@Path("dicEvalPointClass")
	public Page<DicEvalPointClassExp> getListByPage(
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
		DicEvalPointClassExp entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, DicEvalPointClassExp.class)
				: new DicEvalPointClassExp();

		return DaoFactory.create(DicEvalPointClassExp.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
	
	/**
	 * 查询指标类型树
	 * @param areaCode
	 * @return
	 */
	@POST
	@Path("dicEvalPointClass/getTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getPointTypeTree() {
		
		// 获取用户信息
		String areaCode = "";
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");

		// 获取指定机构详情(用户所在机构)
		Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,
				orgIds[0]);
		areaCode = (String) org.get("xzqm");
		if (StringUtils.isBlank(areaCode)){
			return null;
		}
		// 查询指标类型
		List<DicEvalPointClass> pointTypeList = DaoFactory.create(DicEvalPointClass.class).getSession().selectList(
				"com.chinacreator.dzjc_performance.DicEvalPointClassMapper.selectByAreaCode", areaCode);
		//包装成树数据
		CommonTreeNode orgTreeNode = null;
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		for (int i = 0; i < pointTypeList.size(); i++) {
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(pointTypeList.get(i).getPointTypeId());
			orgTreeNode.setShowName(pointTypeList.get(i).getPointTypeName());
			orgTreeNode.setName(pointTypeList.get(i).getPointTypeName());
			orgTreeNode.setPid(pointTypeList.get(i).getParentPointtypeId());
			list.add(orgTreeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	

	@POST
	@Path("dicEvalRadix/getTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getParentTreeNodes(){
		List<CommonTreeNode> treeNodes = new ArrayList<CommonTreeNode>();
		
		// 获取用户信息
		String code = "";
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");

		// 获取指定机构详情(用户所在机构)
		Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,
				orgIds[0]);
		code = (String) org.get("xzqm");
		
		//根据用户code查询
		List<DicEvalPointClass> pList = DaoFactory.create(DicEvalPointClass.class).getSession().selectList("com.chinacreator.dzjc_performance.DicEvalPointClassMapper.selectListByCode", code);
		
		for (DicEvalPointClass dicEvalPointClass : pList) {
			CommonTreeNode node = new CommonTreeNode();
			node.setId(dicEvalPointClass.getPointTypeId());
			node.setName(dicEvalPointClass.getPointTypeName());
			node.setPid(dicEvalPointClass.getParentPointtypeId());
			
			treeNodes.add(node);
		}
		
		return treeNodes.toArray(new CommonTreeNode[treeNodes.size()]);
	}
	
}
