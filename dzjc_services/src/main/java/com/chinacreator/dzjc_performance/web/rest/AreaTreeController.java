package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.DicArea;
import com.chinacreator.util.RoleUtil;


@Controller
@Path("areaTree/v1")
@Consumes(MediaType.APPLICATION_JSON)
public class AreaTreeController {
	
		@POST
		@Path("/getAreaTree")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public TreeNode[] getElements() {
			List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
			
			// 获取用户信息
			String areaCode = "";
			/*
			String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

			User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
			String[] roles = (String[]) user.get("roles");
			String[] orgIds = (String[]) user.get("orgIds");

			// 获取指定机构详情(用户所在机构)
			Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,
					orgIds[0]);
			areaCode = (String) org.get("xzqm");
			 */	
			// 查询全湖南省机构树形信息
			
			List<DicArea> areaList = DaoFactory.create(DicArea.class).getSession()
					.selectList("com.chinacreator.dzjc_performance.selectAreaTree",areaCode);
	
			CommonTreeNode areaTreeNode = null;
			for (int i = 0; i < areaList.size(); i++) {
				areaTreeNode = new CommonTreeNode();
				areaTreeNode.setId(areaList.get(i).getAreaCode());
				areaTreeNode.setShowName(areaList.get(i).getAreaName());
				areaTreeNode.setName(areaList.get(i).getAreaName());
				areaTreeNode.setPid(areaList.get(i).getParentAreaCode());
				list.add(areaTreeNode);
			}
	
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}
		
		/**
		 * 获取投诉举报录入的区域树，只查询到区县
		 * @return
		 */
		@POST
		@Path("/getTsAreaTree")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public TreeNode[] getTsAreaTree() {
			List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
			
			// 获取用户信息
			String areaCode = "";
			
			// 查询全湖南省机构树形信息
			
			List<DicArea> areaList = DaoFactory.create(DicArea.class).getSession()
					.selectList("com.chinacreator.dzjc_performance.DicAreaMapper.selectAreaTreeTs",areaCode);
	
			CommonTreeNode areaTreeNode = null;
			for (int i = 0; i < areaList.size(); i++) {
				areaTreeNode = new CommonTreeNode();
				areaTreeNode.setId(areaList.get(i).getAreaCode());
				areaTreeNode.setShowName(areaList.get(i).getAreaName());
				areaTreeNode.setName(areaList.get(i).getAreaName());
				areaTreeNode.setPid(areaList.get(i).getParentAreaCode());
				list.add(areaTreeNode);
			}
	
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}
		
		@POST
		@Path("/getTreeByUser")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public TreeNode[] getTree() {
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
			
			List<DicArea> areaList = DaoFactory.create(DicArea.class).getSession()
					.selectList("com.chinacreator.dzjc_performance.DicAreaMapper.selectTreeByUser",code);
	
			CommonTreeNode areaTreeNode = null;
			for (int i = 0; i < areaList.size(); i++) {
				areaTreeNode = new CommonTreeNode();
				areaTreeNode.setId(areaList.get(i).getAreaCode());
				areaTreeNode.setShowName(areaList.get(i).getAreaName());
				areaTreeNode.setName(areaList.get(i).getAreaName());
				areaTreeNode.setPid(areaList.get(i).getParentAreaCode());
				list.add(areaTreeNode);
			}
	
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}
		
		/**
		 * 获取用户权限
		 * @return
		 */
		@GET
		@Path("/getCodeByUser")
		public String getCodeByUser(){
			return RoleUtil.getInstance().queryCodeByUserId();
		}
			
}