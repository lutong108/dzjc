package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
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
import com.chinacreator.dzjc_ruleEngine.ApproveInfo;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ApproveInfoController {

	@GET
	@Path("ApproveInfo/{approveId}")
	public ApproveInfo get(
			@PathParam(value = "approveId") java.lang.String approveId) {
		//TODO auto-generated method stub
		return DaoFactory.create(ApproveInfo.class).selectByID(approveId);
	}

	@GET
	@Path("ApproveInfo")
	public Page<ApproveInfo> getListByPage(
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
		ApproveInfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ApproveInfo.class) : new ApproveInfo();

		return DaoFactory.create(ApproveInfo.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	@POST
	@Path("GetApproveTree/{orgId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getApproveTree(@PathParam(value="orgId") String orgId) {

		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		List<ApproveInfo> approveList= new ArrayList<>();
		// 获取登录人信息
		String code = null;
		try {
			approveList = DaoFactory.create(ApproveInfo.class).getSession()
					.selectList("com.chinacreator.dzjc_ruleEngine.ApproveInfoMapper.selectApproveByOrg", orgId);
			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < approveList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(approveList.get(i).getApproveId());
				orgTreeNode.setShowName(approveList.get(i).getApproveName());
				orgTreeNode.setName(approveList.get(i).getApproveName());
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
