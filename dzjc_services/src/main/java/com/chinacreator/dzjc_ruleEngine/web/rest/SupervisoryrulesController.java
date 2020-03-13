package com.chinacreator.dzjc_ruleEngine.web.rest;

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
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_ruleEngine.Supervisoryrules;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SupervisoryrulesController {

	@POST
	@Path("Supervisoryrules/{supervisoryRulesId}")
	public Supervisoryrules update(Supervisoryrules entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Supervisoryrules.class).update(entity);
		return entity;
	}

	@POST
	@Path("Supervisoryrules")
	public Supervisoryrules insert(Supervisoryrules entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Supervisoryrules.class).insert(entity);
		return entity;
	}

	@GET
	@Path("Supervisoryrules/{supervisoryRulesId}")
	public Supervisoryrules get(
			@PathParam(value = "supervisoryRulesId") java.lang.String supervisoryRulesId) {
		//TODO auto-generated method stub
		return DaoFactory.create(Supervisoryrules.class).selectByID(
				supervisoryRulesId);
	}

	@DELETE
	@Path("Supervisoryrules")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(Supervisoryrules.class).deleteBatch(ids);
	}

	@DELETE
	@Path("Supervisoryrules/{supervisoryRulesId}")
	public void delete(
			@PathParam(value = "supervisoryRulesId") java.lang.String supervisoryRulesId) {
		//TODO auto-generated method stub
		Supervisoryrules entity = new Supervisoryrules();
		entity.setSupervisoryRulesId(supervisoryRulesId);
		DaoFactory.create(Supervisoryrules.class).delete(entity);
	}

	@GET
	@Path("Supervisoryrules")
	public Page<Supervisoryrules> getListByPage(
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
		Supervisoryrules entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, Supervisoryrules.class)
				: new Supervisoryrules();

		return DaoFactory.create(Supervisoryrules.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	@POST
	@Path("/getSupervisoryruleTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getSupervisoryruleTree() {

		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		List<Supervisoryrules> approveList= new ArrayList<>();
		// 获取登录人信息
		String code = null;
		try {
			approveList = DaoFactory.create(Supervisoryrules.class).getSession()
					.selectList("com.chinacreator.dzjc_ruleEngine.SupervisoryrulesMapper.selectTree");
			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < approveList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(approveList.get(i).getSupervisoryRulesId());
				orgTreeNode.setName(approveList.get(i).getSupervisoryRulesName());
				orgTreeNode.setShowName(approveList.get(i).getSupervisoryRulesName());
				orgTreeNode.setPid("0");
				list.add(orgTreeNode);
			}
			//orgTreeNode.setId("0");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
}
