package com.chinacreator.dzjc_evaluation.person.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.OrgUserInfo;
import com.chinacreator.dzjc_complain.Organization;
import com.chinacreator.dzjc_evaluation.export.EvaluationExcel;
import com.chinacreator.dzjc_evaluation.person.PersonItem;
import com.chinacreator.dzjc_evaluation.person.PersonParent;
import com.chinacreator.dzjc_evaluation.person.PersonScore;
import com.chinacreator.dzjc_evaluation.person.PersonTable;
import com.chinacreator.dzjc_evaluation.person.PersonUser;
import com.chinacreator.dzjc_performance.DataUpload;
import com.chinacreator.util.ParamsUtil;
import com.chinacreator.util.StringUtil;

/**
 * 测评考核分数统计
 * 
 * @author 谌欣慰
 */
@Controller
@Path("person/v1")
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

	private String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

	/**
	 * 获取用户类型测评分数报表
	 * 
	 * @param areaCode
	 * @param tableId
	 * @return
	 */
	@GET
	@Path("/getPersonEval")
	public Map<String, Object> getPersonEval(@QueryParam("org_id") String org_id,
			@QueryParam("tableId") String tableId, @QueryParam("beginDate") String beginDate,
			@QueryParam("endDate") String endDate) {

		//获取区域行政区码
		String areaCode = null;
		if (ParamsUtil.notNull(org_id)) {
			Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}",
					Organization.class, org_id);
			areaCode = (String) org.getXzqm();
		}

		Map<String, Object> map = new HashMap<>();

		//行政区码和考核表不能为空
		if (ParamsUtil.notNull(areaCode) && ParamsUtil.notNull(tableId)) {

			if (ParamsUtil.notNull(beginDate)) {
				beginDate = beginDate + " 00:00:00";
			} else {
				beginDate = "1970-01-01 00:00:00";
			}

			if (ParamsUtil.notNull(endDate)) {
				endDate = endDate + " 23:59:59";
			} else {
				endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 23:59:59";
			}

			List<PersonParent> parentList = this.getParentTitle(areaCode, tableId, beginDate, endDate);//父级表头
			List<PersonItem> itemListAndParent = this.getItemTitleAndParent(areaCode, tableId, beginDate, endDate);//子级表头（有父级的）
			List<PersonItem> itemListNoParent = this.getItemTitleNoParent(areaCode, tableId, beginDate, endDate);//子级表头（无父级的）
			List<PersonUser> userList = this.getEvalUser(areaCode, tableId, beginDate, endDate,itemListNoParent);//测评用户

			map.put("parentList", parentList.size() > 0 ? parentList : null);
			map.put("itemListAndParent", itemListAndParent.size() > 0 ? itemListAndParent : null);
			map.put("itemListNoParent", itemListNoParent.size() > 0 ? itemListNoParent : null);
			map.put("userList", userList.size() > 0 ? userList : null);
		}

		return map;
	}

	/**
	 * 获取父级表头
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<PersonParent> getParentTitle(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取父级表头
		List<PersonParent> list = DaoFactory.create(PersonParent.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.person.PersonMapper.getParentTitle", map);

		//获取子级表头（有父表头的）
		List<PersonItem> itemList = this.getItemTitleAndParent(areaCode, tableId, beginDate, endDate);

		//父级和自己相关联
		for (PersonParent point : list) {
			List<PersonItem> newList = new ArrayList<>();

			for (PersonItem item : itemList) {
				if (point.getParent_item_id().equals(item.getParent_item_id())) {
					newList.add(item);
				}
			}
			point.setList(newList);
		}

		return list;

	}

	/**
	 * 获取子级表头(有父级)
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<PersonItem> getItemTitleAndParent(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		List<PersonItem> list = DaoFactory.create(PersonItem.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.person.PersonMapper.getItemTitleAndParent", map);

		return list;

	}

	/**
	 * 获取子级表头(无父级)
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<PersonItem> getItemTitleNoParent(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		List<PersonItem> list = DaoFactory.create(PersonItem.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.person.PersonMapper.getItemTitleNoParent", map);

		return list;

	}

	/**
	 * 获取测评用户
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	@SuppressWarnings("unchecked")
	private List<PersonUser> getEvalUser(String areaCode, String tableId, String beginDate, String endDate,List<PersonItem> itemListNoParent) {

		StringUtil.getUserInfo();

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取测评用户信息
		List<PersonUser> list = DaoFactory.create(PersonUser.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.person.PersonMapper.getEvalUser", map);
	/*	if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				String instance_id=	list.get(i).getInstance_id();
				String user_id= list.get(i).getUser_id();
				DataUpload dataUpload =new DataUpload();
				dataUpload.setObjectId(user_id);
				dataUpload.setInstanceId(instance_id);
				List<DataUpload> updatelist=	DaoFactory.create(DataUpload.class).getSession().selectList("com.chinacreator.dzjc_performance.DataUploadMapper.FindPreservationList", dataUpload);
				Collection nuCon = new Vector(); 
				nuCon.add(null);
				updatelist.removeAll(nuCon);
				if(updatelist != null && updatelist.size() != 0){
					list.get(i).setSum_score(list.get(i).getSum_score()+updatelist.get(0).getBonuspoints());
					list.get(i).setBonuspoints(updatelist.get(0).getBonuspoints());	
				}
				
			}
		}*/
		//先获取有父表头测评分数
		List<PersonScore> scoreList1 = this.getEvalScoreAndParent(areaCode, tableId, beginDate, endDate);

		//再获取有父表头测评分数
		List<PersonScore> scoreList2 = this.getEvalScoreNoParent(areaCode, tableId, beginDate, endDate);
		for (PersonUser user : list) {

			if (ParamsUtil.notNull(user.getUser_id())) {

				try {
					List<Object> objectList = RestUtils.createRestTemplate().getForObject(
							url + "/uop/v1/users/" + user.getUser_id() + "/orgs?categoryId=10-Z", List.class);

					// 转换成对象集合
					List<OrgUserInfo> orgList = JSON.parseArray(objectList.toString(), OrgUserInfo.class);
					user.setOrg_id(orgList.get(0).getId());
					user.setOrg_name(orgList.get(0).getName());

				} catch (Exception e) {
					throw new RuntimeException("PersonController类->getEvalUser->用户机构获取失败");
				}
			}

			List<PersonScore> newList = new ArrayList<>();
			for (PersonScore score : scoreList1) {
				if (user.getUser_id().equals(score.getUser_id())) {
					newList.add(score);
				}
			}
			for( PersonItem personItem : itemListNoParent){
				for (PersonScore score : scoreList2) {
					if (user.getUser_id().equals(score.getUser_id()) && personItem.getItem_id().equals(score.getItem_id()) && user.getInstance_id().equals(score.getInstanceId())) {
						newList.add(score);
					}
				}
			}
			user.setList(newList);
		}

		return list;
	}

	/**
	 * 获取测评分数（有父级标题的列分数）
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<PersonScore> getEvalScoreAndParent(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取测评分数
		List<PersonScore> list = DaoFactory.create(PersonScore.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.person.PersonMapper.getEvalScoreAndParent", map);

		return list;
	}

	/**
	 * 获取测评分数（无父级标题的列分数）
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<PersonScore> getEvalScoreNoParent(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取测评分数
		List<PersonScore> list = DaoFactory.create(PersonScore.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.person.PersonMapper.getEvalScoreNoParent", map);

		return list;
	}

	/**
	 * 根据区域，获取对应的考核表
	 * 
	 * @param org_id
	 * @return
	 */
	@POST
	@Path("/getEvalTable")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getEvalTable(@FormParam("org_id") String org_id) {

		//获取测评分数
		List<PersonTable> list = DaoFactory.create(PersonTable.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.person.PersonMapper.getEvalTable", org_id);

		List<CommonTreeNode> treeList = new ArrayList<CommonTreeNode>();
		for (PersonTable table : list) {

			CommonTreeNode orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(table.getTable_id());
			orgTreeNode.setName(table.getTable_name());

			treeList.add(orgTreeNode);
		}

		return (TreeNode[]) treeList.toArray(new CommonTreeNode[treeList.size()]);
	}

	/**
	 * 个人统计Excel导出
	 * 
	 * @param org_id 机构编号
	 * @param tableId 考核表编号
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 */
	@SuppressWarnings("unchecked")
	public String PersonExcel(String org_id, String tableId, String beginDate, String endDate) {

		//导出所需数据
		Map<String, Object> map = this.getPersonEval(org_id, tableId, beginDate, endDate);

		if (map != null) {

			List<String> parentStr = new ArrayList<>();//顶层表头名称
			List<String> parentIndex = new ArrayList<>();//顶层表头下标

			List<String> itemStr = new ArrayList<>();//下层表头名称
			List<String> itemIndex = new ArrayList<>();//下层表头下标

			Object objectList1 = map.get("parentList");//获取顶层表头
			Object objectList2 = map.get("itemListAndParent");//下层表头（有父级）
			Object objectList3 = map.get("itemListNoParent");//下层表头（无父级）

			//先加入固定表头、固定下标
			parentStr.add("序号");
			parentIndex.add("1,2,0,0");

			parentStr.add("姓名");
			parentIndex.add("1,2,1,1");

			parentStr.add("单位");
			parentIndex.add("1,2,2,2");

			parentStr.add("名次");
			parentIndex.add("1,2,3,3");

			int listSize = 0;//因为最后一列为总分固定列，前面有多少列得通过累加才知道

			//如果带父级的子表头不为空，那一定有父级
			if (objectList2 != null) {

				//下层表头（有父级）
				List<PersonItem> itemAndParentList = (List<PersonItem>) objectList2;
				listSize += itemAndParentList.size();

				int num1 = 4;//因为前面已经占了四格固定标题，父标题从下标4，也就是第5格开始

				//计算父表头下标
				List<PersonParent> pointList = (List<PersonParent>) objectList1;
				for (PersonParent point : pointList) {
					int num2 = 0;

					//匹配每个父级下有多少个子级表头
					for (PersonItem item : itemAndParentList) {
						if (point.getParent_item_name().equals(item.getParent_item_name())) {

							//有多少个子级表头，就要重复加入对应的父级表头
							parentStr.add(point.getParent_item_name());
							num2++;
						}
					}

					/**
					 * 下标规则： 1位：从第几行开始。 2位：从第几行结束。3位：从第几列开始。4位：从第几列结束
					 */
					parentIndex.add("1,1," + num1 + "," + (num1 + num2 - 1));
					num1 += num2;
				}

				int num3 = 4;//因为前面已经占了四格固定标题，父标题从下标4，也就是第5格开始

				//计算下层表头（有父级）的下标
				for (PersonItem item : itemAndParentList) {
					itemStr.add(item.getItem_name());
					itemIndex.add("2,2," + num3 + "," + num3);
					num3++;
				}

			}

			//如果存在不带父级的表头，计算其下标
			int num4 = listSize + 4;
			if (objectList3 != null) {

				List<PersonItem> itemNoParentList = (List<PersonItem>) objectList3;

				for (PersonItem item : itemNoParentList) {
					parentStr.add(item.getItem_name());
					parentIndex.add("1,2," + num4 + "," + num4);
					num4++;
				}

				listSize += itemNoParentList.size();
			}
			
			parentStr.add("加分");
			parentIndex.add("1,2," + (listSize + 4) + "," + (listSize + 4));
			
			parentStr.add("最终得分");
			parentIndex.add("1,2," + (listSize + 5) + "," + (listSize + 5));

			Map<String, Object> newMap = new HashMap<>();
			newMap.put("parentStr", parentStr.toArray());
			newMap.put("parentIndex", parentIndex.toArray());
			newMap.put("itemStr", itemStr.toArray());
			newMap.put("itemIndex", itemIndex.toArray());
			newMap.put("orgList", map.get("userList"));
			newMap.put("toptitle", 4);

			//生成Excel文件
			EvaluationExcel excel = new EvaluationExcel();
			excel.excelExport("个人绩效测评统计表", newMap, "2");

		}

		return "个人绩效测评统计表";
	}
}
