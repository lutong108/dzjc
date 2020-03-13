package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.chinacreator.dzjc_performance.EvalInstance;
import com.chinacreator.dzjc_performance.EvalObject;
import com.chinacreator.dzjc_performance.EvalTable;
import com.chinacreator.dzjc_performance.service.EvalInstanceService;
import com.chinacreator.dzjc_performance.service.EvalObjectService;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalInstanceController {
	
	@Autowired
	EvalInstanceService evalInstanceService;
	
	@Autowired 
	HttpServletRequest request; //这里可以获取到request
	
	@DELETE
	@Path("evalInstance/{id}")
	public String delete(@PathParam(value = "id") java.lang.String id) {
		List<String> instanceIds = new ArrayList<>();
		instanceIds.add(id);
		String message = "";
		try {
			message = evalInstanceService.deleteByInstanceId(instanceIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}

	@DELETE
	@Path("evalInstance")
	public String deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		String message = "";
		try {
			message = evalInstanceService.deleteByInstanceId(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}

	@GET
	@Path("evalInstance/{id}")
	public EvalInstance get(@PathParam(value = "id") java.lang.String id) {
		return DaoFactory.create(EvalInstance.class).selectByID(id);
	}
	
	@GET
	@Path("evalInstance/selectByList{id}")
	public List<EvalInstance> selectByList(@PathParam(value = "id") java.lang.String id) {
		EvalInstance evalInstance =new EvalInstance();
		evalInstance.setTableId(id);
		List<EvalInstance> evalinstancelist= DaoFactory.create(EvalInstance.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalInstanceMapper.selectByList",evalInstance);
		return evalinstancelist;
	}

	@POST
	@Path("evalInstance")
	public String insert(List<Object> list) {	
		String stutas = "";
		try {
			Object[] array = list.toArray();
			
			EvalObject evalObject = JSON.parseObject(array[1].toString(), EvalObject.class);
			
			EvalInstance entity = JSON.parseObject(array[0].toString(), EvalInstance.class);
			
			System.err.println(entity.getOrgId()+"-->"+entity.getOrgName()+"===="+entity.getTableId()+"-->"+entity.getTableName());
			
			//调用service的方法，进行下发操作
			stutas = evalInstanceService.calculateValue(entity,evalObject);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stutas = e.getMessage();
		}
		return stutas;
	}

	@POST
	@Path("evalInstance/{id}")
	public EvalInstance update(EvalInstance entity) {
		DaoFactory.create(EvalInstance.class).update(entity);
		return entity;
	}

	@GET
	@Path("evalInstance")
	public Page<EvalInstance> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		
		// 获取用户信息
		String code = "";
		try {
			// 获取行政区码
			code = RoleUtil.getInstance().queryCodeByUserId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		EvalInstance entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EvalInstance.class) : new EvalInstance();
		if(code.startsWith("43")){
			entity.setAreaCode(code);
		}
		return DaoFactory.create(EvalInstance.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
	}
	
	//这方法暂时没用
	@POST
	@Path("evalInstance/getTableList")
	public Map<String, List<EvalTable>> getTableList(){
		
		Map<String, List<EvalTable>> map = new HashMap<String, List<EvalTable>>();
		
		List<EvalTable> list = DaoFactory.create(EvalTable.class).selectAll();
		
		map.put("result", list);
		
		return map;
	}
	
	/**
	 * 根据当前登录用户的权限，获取对应的表
	 * @return
	 */
	@POST
	@Path("getTableTreeByAreaCode")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getElements() {
		
		// 获取用户信息
		String code = "";
		String evalObjectType=request.getParameter("evalObjectType");
		//System.err.println("@@@@@@@@@@@@@"+evalObjectType);
		try {
			// 获取行政区码
			code = RoleUtil.getInstance().queryCodeByUserId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 获取用户信息
		/*String areaCode = "";
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");

		// 获取指定机构详情(用户所在机构)
		Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,
				orgIds[0]);
		areaCode = (String) org.get("xzqm");*/

		EvalTable evalTable = new EvalTable();
		evalTable.setAreaCode(code);
		evalTable.setEvalObjectType(evalObjectType);
		// 根据当前用户的权限，查询出对应的表
		List<EvalTable> tList = DaoFactory.create(EvalTable.class)
				.getSession().selectList("com.chinacreator.dzjc_performance.EvalTableMapper.selectByAreaCode2", evalTable);

		CommonTreeNode orgTreeNode = null;
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		for (int i = 0; i < tList.size(); i++) {
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(tList.get(i).getTableId());
			orgTreeNode.setShowName(tList.get(i).getTableName());
			orgTreeNode.setName(tList.get(i).getTableName());
			//orgTreeNode.setPid(orgList.get(i).getPid());
			list.add(orgTreeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
}
