package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.OrgUserInfo;
import com.chinacreator.dzjc_complain.Organization;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.DictArea;
import com.chinacreator.dzjc_performance.UserPerformance;
import com.chinacreator.dzjc_performance.VOrganization;
import com.chinacreator.util.RoleUtil;
import com.chinacreator.util.StringUtil;

/**
 * 区域信息
 * 
 */
@Controller
@Path("areaOrg/v1")
@Consumes(MediaType.APPLICATION_JSON)
public class AreaOrgController {
	
	private String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

	//获取区域树
	@POST
	@Path("/getAreaTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getListByParent() {

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

		// 查询父机构
		VOrganization parent_org = OrgRightsController.getOrgIdByCode(code);

		String parentCode = null;
		if (parent_org != null) {

			/*CommonTreeNode orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(parent_org.getCode().substring(0,12));
			//orgTreeNode.setShowName(parent_org.getName());
			orgTreeNode.setName(parent_org.getName());
			orgTreeNode.setPid(parent_org.getPid());

			list.add(orgTreeNode);*/

			parentCode = parent_org.getCode();
			
		}

		if (parentCode != null) {

			DictArea entity = new DictArea();
			//entity.setParentCode(parentCode.substring(0,12));
			entity.setParentCode(code);

			// 查询子级区域
			List<DictArea> areaList = DaoFactory.create(DictArea.class).select(entity);

			// 按树形菜单添加
			for (DictArea dictArea : areaList) {

				CommonTreeNode orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(dictArea.getAreaCode());
				//orgTreeNode.setShowName(dictArea.getAreaName());
				orgTreeNode.setName(dictArea.getAreaName());
				orgTreeNode.setPid(dictArea.getParentCode());

				list.add(orgTreeNode);
			}
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	//根据登录用户的权限查询所有的机构树信息
	@POST
	@Path("/getOrgTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getOrgTree() {
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

		// 查询父机构
		VOrganization parent_org = OrgRightsController.getOrgIdByCode(code);

		String parentCode = null;
		if (parent_org != null) {

			/*CommonTreeNode orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(parent_org.getCode().substring(0,12));
			//orgTreeNode.setShowName(parent_org.getName());
			orgTreeNode.setName(parent_org.getName());
			orgTreeNode.setPid(parent_org.getPid());

			list.add(orgTreeNode);*/

			parentCode = parent_org.getCode();
			
		}

		if (parentCode != null) {

			// 根据code查询机构树形信息
			List<Orgview> orgList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgByCode",code);
			String decodeOne="";
			String decodeTow="";
			// 按树形菜单添加
			for (Orgview orgview : orgList) {
				
				decodeOne= orgview.getCode().substring(0,6);
				decodeTow=orgview.getCode().substring(0,9);
				if(decodeOne.equals("430623") && !decodeTow.equals("430623999")  && !orgview.getId().equals("decd8a052b8c48b5ab7e8459a3da90b9")){
					continue;
				}
					CommonTreeNode orgTreeNode = new CommonTreeNode();
					orgTreeNode.setId(orgview.getId());
					orgTreeNode.setShowName(orgview.getName());
					orgTreeNode.setName(orgview.getName());
					orgTreeNode.setPid(orgview.getPid());
				list.add(orgTreeNode);
			}
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	
	//根据登录用户的所在的areaCode查询对应的的机构树信息
	@POST
	@Path("/getOrgTreeByAreaCode")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getOrgTreeByAreaCode() {
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();

		// 获取用户信息
		String areaCode = "";
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");

		// 获取指定机构详情(用户所在机构)
		com.chinacreator.c2.uop.Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", com.chinacreator.c2.uop.Organization.class,
				orgIds[0]);
		areaCode = (String) org.get("xzqm");

		// 查询父机构
		VOrganization parent_org = OrgRightsController.getOrgIdByCode(areaCode);

		String parentCode = null;
		if (parent_org != null) {

			/*CommonTreeNode orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(parent_org.getCode().substring(0,12));
			//orgTreeNode.setShowName(parent_org.getName());
			orgTreeNode.setName(parent_org.getName());
			orgTreeNode.setPid(parent_org.getPid());

			list.add(orgTreeNode);*/

			parentCode = parent_org.getCode();
			
		}

		if (parentCode != null) {

			// 根据code查询机构树形信息
			List<Orgview> orgList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgByCode",areaCode);

			// 按树形菜单添加
			for (Orgview orgview : orgList) {

				CommonTreeNode orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgview.getId());
				orgTreeNode.setShowName(orgview.getName());
				orgTreeNode.setName(orgview.getName());
				orgTreeNode.setPid(orgview.getPid());

				list.add(orgTreeNode);
			}
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	
	//获取本机构下的用户信息
	@POST
	@Path("/getUserTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getUserTree() {
		//获取本级以下机构下的用户信息
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();

		// 获取登录人信息
		Map<String, String> userMap = StringUtil.getUserInfo();
		String orgId = userMap.get("orgId");// 登录人所属机构

		// 查询该机构所属父级
		Orgview parentOrg = DaoFactory.create(Orgview.class).getSession()
				.selectOne("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgAndPi2", orgId);

		if (parentOrg == null) {
			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}

		// 查询当前父级机构所有子机构
		List<Organization> orgList = null;
		try {

			@SuppressWarnings("unchecked")
			List<Object> objectList = RestUtils.createRestTemplate().getForObject(
					url + "/uop/v1/orgs/" + parentOrg.getId() + "/children?cascade=true&categoryId=10-Z", List.class);

			// 转换成对象集合
			orgList = JSON.parseArray(objectList.toString(), Organization.class);

		} catch (Exception e) {
			throw new RuntimeException("机构获取失败");
		}

		// -----------------------------------------------------------------------------------------------------------

		// 查询当前父级机构所有子机构下的用户
		List<OrgUserInfo> userList = null;
		try {

			@SuppressWarnings("unchecked")
			List<Object> objectList = RestUtils.createRestTemplate().getForObject(
					url + "/uop/v1/orgs/" + parentOrg.getId() + "/users?state=1&cascade=true&categoryId=10-Z",
					List.class);

			// 转换成对象集合
			userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);

		} catch (Exception e) {
			throw new RuntimeException("用户获取失败");
		}

		// -----------------------------------------------------------------------------------------------------------

		// 去掉没有用户的机构
		List<Organization> orgList2 = new ArrayList<>();
		for (Organization organization : orgList) {

			String orgIds = organization.getId();
			for (OrgUserInfo orgUserInfo : userList) {
				if (orgIds.equalsIgnoreCase(orgUserInfo.getOrgIds()[0])) {
					orgList2.add(organization);
					break;
				}
			}
		}

		CommonTreeNode orgTreeNode = null;
		for (int i = 0; i < orgList2.size(); i++) {
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(orgList2.get(i).getId());
			orgTreeNode.setShowName(orgList2.get(i).getName());
			orgTreeNode.setName(orgList2.get(i).getName());
			orgTreeNode.setPid(orgList2.get(i).getPid());
			list.add(orgTreeNode);
		}

		for (int i = 0; i < userList.size(); i++) {
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(userList.get(i).getId());
			orgTreeNode.setShowName("user");
			orgTreeNode.setName(userList.get(i).getName());
			orgTreeNode.setPid(userList.get(i).getOrgIds()[0]);
			list.add(orgTreeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	
	
	
	//获取本机构下的用户信息
		@POST
		@Path("/getUserTreePerformanceSelect")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public TreeNode[] getUserTreePerformanceSelect() {
			//获取本级以下机构下的用户信息
			List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();

			// 获取登录人信息
			Map<String, String> userMap = StringUtil.getUserInfo();
			String orgId = userMap.get("orgId");// 登录人所属机构

			// 查询该机构所属父级
			Orgview parentOrg = DaoFactory.create(Orgview.class).getSession()
					.selectOne("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgAndPi2", orgId);

			if (parentOrg == null) {
				return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
			}

			// 查询当前父级机构所有子机构
			List<Organization> orgList = null;
			try {

				@SuppressWarnings("unchecked")
				List<Object> objectList = RestUtils.createRestTemplate().getForObject(
						url + "/uop/v1/orgs/" + parentOrg.getId() + "/children?cascade=true&categoryId=10-Z", List.class);

				// 转换成对象集合
				orgList = JSON.parseArray(objectList.toString(), Organization.class);

			} catch (Exception e) {
				throw new RuntimeException("机构获取失败");
			}

			// -----------------------------------------------------------------------------------------------------------

			// 查询当前父级机构所有子机构下的用户
			List<OrgUserInfo> userList = null;
			try {
					
			//	com\chinacreator\dzjc_performance\UserPerformanceMapper.xml
				
				
				@SuppressWarnings("unchecked")
				List<Object> objectList = RestUtils.createRestTemplate().getForObject(
						url + "/uop/v1/orgs/" + parentOrg.getId() + "/users?state=1&cascade=true&categoryId=10-Z",
						List.class);

				// 转换成对象集合
				userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);

			} catch (Exception e) {
				throw new RuntimeException("用户获取失败");
			}

			// -----------------------------------------------------------------------------------------------------------

			// 去掉没有用户的机构
			List<Organization> orgList2 = new ArrayList<>();
			for (Organization organization : orgList) {

				String orgIds = organization.getId();
				for (OrgUserInfo orgUserInfo : userList) {
					if (orgIds.equalsIgnoreCase(orgUserInfo.getOrgIds()[0])) {
						orgList2.add(organization);
						break;
					}
				}
			}

			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < orgList2.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgList2.get(i).getId());
				orgTreeNode.setShowName(orgList2.get(i).getName());
				orgTreeNode.setName(orgList2.get(i).getName());
				orgTreeNode.setPid(orgList2.get(i).getPid());
				list.add(orgTreeNode);
			}

			for (int i = 0; i < userList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(userList.get(i).getId());
				orgTreeNode.setShowName("user");
				orgTreeNode.setName(userList.get(i).getName());
				orgTreeNode.setPid(userList.get(i).getOrgIds()[0]);
				orgTreeNode.setPhone(userList.get(i).getPhone());
				orgTreeNode.setSex(userList.get(i).getSex());
				list.add(orgTreeNode);
			}

			return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
		}
	
	
	
	
	//根据登录用户的权限，获取对应机构及其下面的用户，该方法行不通，太慢了
	@POST
	@Path("/getUserTreeByAreaCode")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getUserTreeByAreaCode() {
		
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

		// 查询父机构
		VOrganization parent_org = OrgRightsController.getOrgIdByCode(code);

		String parentCode = null;
		if (parent_org != null) {

			/*CommonTreeNode orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(parent_org.getCode().substring(0,12));
			//orgTreeNode.setShowName(parent_org.getName());
			orgTreeNode.setName(parent_org.getName());
			orgTreeNode.setPid(parent_org.getPid());

			list.add(orgTreeNode);*/

			parentCode = parent_org.getCode();
			
		}
		if (parentCode != null) {

			// 根据code查询机构树形信息
			List<Orgview> orgList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgByCode",code);

			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < orgList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgList.get(i).getId());
				orgTreeNode.setShowName(orgList.get(i).getName());
				orgTreeNode.setName(orgList.get(i).getName());
				orgTreeNode.setPid(orgList.get(i).getPid());
				list.add(orgTreeNode);
			}

			// 查询当前父级机构所有子机构下的用户
			// 根据机构id查询该机构下的所有用户
			for (Orgview org : orgList) {
				
				List<OrgUserInfo> userList = null;
				try {
					
					@SuppressWarnings("unchecked")
					List<Object> objectList = RestUtils.createRestTemplate().getForObject(//&cascade=true
							url + "/uop/v1/orgs/" + org.getId() + "/users?state=1&categoryId=10-Z",
							List.class);
					
					// 转换成对象集合
					userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);
					
				} catch (Exception e) {
					throw new RuntimeException("用户获取失败");
				}
				
				// -----------------------------------------------------------------------------------------------------------
				
				for (int i = 0; i < userList.size(); i++) {
					orgTreeNode = new CommonTreeNode();
					orgTreeNode.setId(userList.get(i).getId());
					orgTreeNode.setShowName("user");
					orgTreeNode.setName(userList.get(i).getName());
					orgTreeNode.setPid(userList.get(i).getOrgIds()[0]);
					list.add(orgTreeNode);
				}
			}

			
		}
		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	

	//getOrgOrUserTree
	/**
	 * 获取用户树或者机构树
	 */
	@POST
	@Path("/getOrgOrUserTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getOrgOrUserTree(@FormParam("type") String type){
		if("1".equals(type)){//部门
			return getOrgTree();//根据权限
			//return getOrgTreeByAreaCode();//根据用户的areaCode
		}else if("2".equals(type)){//窗口
			return null;
		}else if("3".equals(type)){//个人
			return getUserTreePerformance(); 
			//return getUserTreeByAreaCode(); 
		}else{
			return null;
		}
	}
	
	//获取本机构下的用户信息
			@POST
			@Path("/getUserTreePerformance")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.APPLICATION_JSON)
			public TreeNode[] getUserTreePerformance() {
				//获取本级以下机构下的用户信息
				List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();

				// 获取登录人信息
				Map<String, String> userMap = StringUtil.getUserInfo();
				String orgId = userMap.get("orgId");// 登录人所属机构

				// 查询该机构所属父级
				Orgview parentOrg = DaoFactory.create(Orgview.class).getSession()
						.selectOne("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgAndPi2", orgId);

				if (parentOrg == null) {
					return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
				}

				// 查询当前父级机构所有子机构
				List<Organization> orgList = null;
				try {

					@SuppressWarnings("unchecked")
					List<Object> objectList = RestUtils.createRestTemplate().getForObject(
							url + "/uop/v1/orgs/" + parentOrg.getId() + "/children?cascade=true&categoryId=10-Z", List.class);

					// 转换成对象集合
					orgList = JSON.parseArray(objectList.toString(), Organization.class);

				} catch (Exception e) {
					throw new RuntimeException("机构获取失败");
				}

				// -----------------------------------------------------------------------------------------------------------

				// 查询当前父级机构所有子机构下的用户
				List<OrgUserInfo> userList = null;
				UserPerformance userPerformance = new UserPerformance();
				userPerformance.setOrgId(orgId);
				/*	@SuppressWarnings("unchecked")
					List<Object> objectList = RestUtils.createRestTemplate().getForObject(
							url + "/uop/v1/orgs/" + parentOrg.getId() + "/users?state=1&cascade=true&categoryId=10-Z",
							List.class);*/
					List<UserPerformance> objectList=	DaoFactory.create(UserPerformance.class).getSession()
							.selectList("com.chinacreator.dzjc_performance.UserPerformanceMapper.selectByPage",userPerformance);
					// 转换成对象集合
					//userList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);

				
				
				//com\chinacreator\dzjc_performance\UserPerformanceMapper.xml
				// -----------------------------------------------------------------------------------------------------------

				// 去掉没有用户的机构
				List<Organization> orgList2 = new ArrayList<>();
				for (Organization organization : orgList) {

					String orgIds = organization.getId();
					for (UserPerformance orgUserInfo : objectList) {
						if (orgIds.equalsIgnoreCase(orgUserInfo.getOrgId())) {
							orgList2.add(organization);
							break;
						}
					}
				}

				CommonTreeNode orgTreeNode = null;
				for (int i = 0; i < orgList2.size(); i++) {
					orgTreeNode = new CommonTreeNode();
					orgTreeNode.setId(orgList2.get(i).getId());
					orgTreeNode.setShowName(orgList2.get(i).getName());
					orgTreeNode.setName(orgList2.get(i).getName());
					orgTreeNode.setPid(orgList2.get(i).getPid());
					list.add(orgTreeNode);
				}

				for (int i = 0; i < objectList.size(); i++) {
					orgTreeNode = new CommonTreeNode();
					orgTreeNode.setId(objectList.get(i).getId());
					orgTreeNode.setShowName("user");
					orgTreeNode.setName(objectList.get(i).getName());
					orgTreeNode.setPid(objectList.get(i).getOrgId());
					orgTreeNode.setPhone(objectList.get(i).getPhone());
					orgTreeNode.setSex(objectList.get(i).getSex());
					list.add(orgTreeNode);
				}

				return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
			}
		
}
