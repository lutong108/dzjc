package com.chinacreator.dzjc_complain.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_tongji.JcOrgView;
import com.chinacreator.util.RoleUtil;
import com.chinacreator.util.StringUtil;

@Controller
@Path("orgTree/v1")
@Consumes(MediaType.APPLICATION_JSON)
public class OrgTreeController {

	private static final Logger logger = LoggerFactory.getLogger(OrgTreeController.class);

	@POST
	@Path("/getOrgTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getElements() {
		List<CommonTreeNode> commonTreeNodeList = new ArrayList<CommonTreeNode>();
		String code = null;
		
		try {
			//1,查询当前登录用户行政区码
			code = RoleUtil.getInstance().queryCodeByUserId();
			//2,根据区号查询可操作机构
			List<Orgview> orgList = DaoFactory
					.create(Orgview.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgByCode",
							code);
			//3,封装实体
			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < orgList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgList.get(i).getId());
				orgTreeNode.setShowName(orgList.get(i).getName());
				orgTreeNode.setName(orgList.get(i).getName());
				orgTreeNode.setPid(orgList.get(i).getPid());
				commonTreeNodeList.add(orgTreeNode);
			}

		} catch (Exception e) {
			logger.error("接收单位查询失败"+e.getMessage(), e);
		}

		return (TreeNode[]) commonTreeNodeList.toArray(new CommonTreeNode[commonTreeNodeList.size()]);
	}
	
	@POST
	@Path("/getOrgTreeNoBenJi")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getOrgTreeNoBenJi() {
		List<CommonTreeNode> commonTreeNodeList = new ArrayList<CommonTreeNode>();
		String code = null;
		try {
			//1,查询当前登录用户行政区码
			code = RoleUtil.getInstance().queryCodeByUserId();
			//补全机构编码，并查询该机构编码的机构id，从这个机构开始递归，并且不查询该机构数据
			String completeCode = StringUtil.completeOrgCode(code);
			Orgview org = DaoFactory
					.create(Orgview.class)
					.getSession()
					.selectOne("com.chinacreator.dzjc_complain.OrgviewMapper.findOrgByCode",completeCode);
			String orgId = org == null ? "" : org.getId();
			
			//2,根据区号查询可操作机构
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("id", orgId);
			paramMap.put("code", code);
			List<Orgview> orgList = DaoFactory
					.create(Orgview.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgByCodeNoBenJi",paramMap);
			//3,封装实体
			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < orgList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgList.get(i).getId());
				orgTreeNode.setShowName(orgList.get(i).getName());
				orgTreeNode.setName(orgList.get(i).getName());
				orgTreeNode.setPid(orgList.get(i).getPid());
				commonTreeNodeList.add(orgTreeNode);
			}
		} catch (Exception e) {
			logger.error("查询机构树失败"+e.getMessage(), e);
		}
		return (TreeNode[]) commonTreeNodeList.toArray(new CommonTreeNode[commonTreeNodeList.size()]);
	}
	
	@POST
	@Path("/tsAcceptOrgTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] tsAcceptOrgTree() {
		List<CommonTreeNode> commonTreeNodeList = new ArrayList<CommonTreeNode>();
		String code = null;
		try{
			//1,查询当前登录用户行政区码
			code = RoleUtil.getInstance().queryCodeByUserId();
			//2,根据区号查询可操作机构
			List<Orgview> orgList = DaoFactory
					.create(Orgview.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_complain.OrgviewMapper.tsAcceptOrg",
							code);
			//3,封装实体
			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < orgList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgList.get(i).getId());
				orgTreeNode.setShowName(orgList.get(i).getName());
				orgTreeNode.setName(orgList.get(i).getName());
				orgTreeNode.setPid(orgList.get(i).getPid());
				commonTreeNodeList.add(orgTreeNode);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return (TreeNode[]) commonTreeNodeList.toArray(new CommonTreeNode[commonTreeNodeList.size()]);
	}
	
	/**
	 * 根据当前机构id，找到其对应下优化办的机构id
	 * */
	@GET
	@Path("/findYhbOrgId/{orgId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String findYhbOrgId(@PathParam("orgId") String orgId){
		Orgview yhbOrg=DaoFactory.create(Orgview.class).getSession()
						.selectOne("com.chinacreator.dzjc_complain.OrgviewMapper.findYhbOrgId",StringUtil.deNull(orgId));
		if(yhbOrg!=null){
			return StringUtil.deNull(yhbOrg.getId());
		}else{
			return "";
		}
	}
	
	/**
	 * 根据所选区域编码，找到其对应下优化办的机构id
	 * */
	@GET
	@Path("/findYhbOrgIdByAreaCode/{areaCode}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String findYhbOrgIdByAreaCode(@PathParam("areaCode") String areaCode){
		String orgId = "";
		//根据区域编码，判断所选择是否是区县，如果是区县，要将区域编码修改一下
		if(StringUtils.isNotBlank(areaCode)){
			String subCode = areaCode.substring(4, 6);
			//截取区域编码，如果第4-6位不是00，以及01，则选择的是区县
			if(!subCode.equals("00") && !subCode.equals("01")){
				areaCode = areaCode.substring(0, 6) + "999" + areaCode.substring(9);
			}
			List<Orgview> yhbList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.findYhbOrgIdByAreaCode",StringUtil.deNull(areaCode));
			if(yhbList != null && yhbList.size() > 0){
				orgId = StringUtil.deNull(yhbList.get(0).getId());
			}
		}
		return orgId;
	}
	@POST
	@Path("/getOrgTreeByLeave3NoBenJi")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getOrgTreeByLeave3NoBenJi() {
		List<CommonTreeNode> commonTreeNodeList = new ArrayList<CommonTreeNode>();
		String code = null;
		try {
			//1,查询当前登录用户行政区码
			code = "43";
			//2,根据区号查询可操作机构
			List<Orgview> orgList = DaoFactory
					.create(Orgview.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgTreeByLeave3NoBenJi",code);
			//3,封装实体
			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < orgList.size(); i++) {
				JcOrgView orgView = new JcOrgView();
				orgView.setOrgCode(orgList.get(i).getCode());
				if(orgView.getOrgThisLevel()!=9)continue;
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgList.get(i).getId());
				orgTreeNode.setShowName(orgList.get(i).getName());
				orgTreeNode.setName(orgList.get(i).getName());
				orgTreeNode.setPid(orgList.get(i).getPid());
				commonTreeNodeList.add(orgTreeNode);
			}
		} catch (Exception e) {
			logger.error("查询失败"+e.getMessage(), e);
		}
		return (TreeNode[]) commonTreeNodeList.toArray(new CommonTreeNode[commonTreeNodeList.size()]);
	}
}
