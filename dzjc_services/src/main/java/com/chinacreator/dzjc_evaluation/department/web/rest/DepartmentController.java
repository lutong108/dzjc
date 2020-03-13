package com.chinacreator.dzjc_evaluation.department.web.rest;

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

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Organization;
import com.chinacreator.dzjc_evaluation.department.DepartmentItem;
import com.chinacreator.dzjc_evaluation.department.DepartmentOrg;
import com.chinacreator.dzjc_evaluation.department.DepartmentParent;
import com.chinacreator.dzjc_evaluation.department.DepartmentScore;
import com.chinacreator.dzjc_evaluation.department.DepartmentTable;
import com.chinacreator.dzjc_evaluation.export.EvaluationExcel;
import com.chinacreator.dzjc_performance.DataUpload;
import com.chinacreator.util.ParamsUtil;

/**
 * 测评考核分数统计
 * 
 * @author 谌欣慰
 */
@Controller
@Path("evaluation/v1")
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentController {

	private String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

	/**
	 * 获取部门类型测评分数报表
	 * 
	 * @param org_id 机构编号
	 * @param tableId 考核表编号
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	@GET
	@Path("/getDepartmentEval")
	public Map<String, Object> getDepartmentEval(@QueryParam("org_id") String org_id,
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

			List<DepartmentParent> parentList = this.getPointTitle(areaCode, tableId, beginDate, endDate);//父级表头
			List<DepartmentItem> itemListAndParent = this.getItemAndParent(areaCode, tableId, beginDate, endDate);//子级表头（有父级表头的）
			List<DepartmentItem> itemListNoParent = this.getItemNoParent(areaCode, tableId, beginDate, endDate);//子级表头（无父级表头的）
			List<DepartmentOrg> orgList = this.getEvalORG(areaCode, tableId, beginDate, endDate,itemListNoParent);//测评机构

			map.put("parentList", parentList.size() > 0 ? parentList : null);
			map.put("itemListAndParent", itemListAndParent.size() > 0 ? itemListAndParent : null);
			map.put("itemListNoParent", itemListNoParent.size() > 0 ? itemListNoParent : null);
			map.put("orgList", orgList.size() > 0 ? orgList : null);
		}

		return map;
	}

	/**
	 * 获取父级表头
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<DepartmentParent> getPointTitle(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取父级表头
		List<DepartmentParent> list = DaoFactory.create(DepartmentParent.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.department.DepartmentMapper.getParentTitle", map);

		//获取子级表头（有父级表头的）
		List<DepartmentItem> itemList = this.getItemAndParent(areaCode, tableId, beginDate, endDate);

		//父级和自己相关联
		for (DepartmentParent parent : list) {
			List<DepartmentItem> newList = new ArrayList<>();

			for (DepartmentItem item : itemList) {
				if (parent.getParent_item_id().equals(item.getParent_item_id())) {
					newList.add(item);
				}
			}
			parent.setList(newList);
		}

		return list;

	}

	/**
	 * 获取子级表头 （有父级表头的）
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<DepartmentItem> getItemAndParent(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取子级表头
		List<DepartmentItem> list = DaoFactory.create(DepartmentItem.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.department.DepartmentMapper.getItemTitleAndParent", map);

		return list;

	}

	/**
	 * 获取子级表头 （无父级表头的）
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<DepartmentItem> getItemNoParent(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取子级表头
		List<DepartmentItem> list = DaoFactory.create(DepartmentItem.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.department.DepartmentMapper.getItemTitleNoParent", map);

		return list;

	}

	/**
	 * 获取测评机构
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<DepartmentOrg> getEvalORG(String areaCode, String tableId, String beginDate, String endDate,List<DepartmentItem> itemListNoParent) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取测评机构信息
		List<DepartmentOrg> list = DaoFactory.create(DepartmentOrg.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.department.DepartmentMapper.getEvalORG", map);
/*		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				String instance_id=	list.get(i).getInstance_id();
				String user_id= list.get(i).getOrg_id();
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
		//先获取有父表头的测评分数
		List<DepartmentScore> scoreList1 = this.getEvalScoreAndParent(areaCode, tableId, beginDate, endDate);

		//再获取没有父表头的测评分数
		List<DepartmentScore> scoreList2 = this.getEvalScoreNoParent(areaCode, tableId, beginDate, endDate);
		for (DepartmentOrg departmentOrg : list) {
			List<DepartmentScore> newList = new ArrayList<>();
			for (DepartmentScore score : scoreList1) {
				if (departmentOrg.getOrg_id().equals(score.getOrg_id())) {
					newList.add(score);
				}
			}
			for (DepartmentItem  dept  : itemListNoParent) {
				for (DepartmentScore score : scoreList2) {
					if (departmentOrg.getOrg_id().equals(score.getOrg_id()) && dept.getItem_id().equals(score.getItem_id()) && departmentOrg.getInstance_id().equals(score.getInstanceId())) {
						newList.add(score);
					}	
				}
				
			}

			departmentOrg.setList(newList);
		}

		return list;
	}

	/**
	 * 获取测评分数（有父级标题的列分数）
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<DepartmentScore> getEvalScoreAndParent(String areaCode, String tableId, String beginDate,
			String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取测评分数
		List<DepartmentScore> list = DaoFactory.create(DepartmentScore.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.department.DepartmentMapper.getEvalScoreAndParent", map);

		return list;
	}

	/**
	 * 获取测评分数（无父级标题的列分数）
	 * 
	 * @param areaCode //区域编码
	 * @param tableId //考评表编号
	 */
	private List<DepartmentScore> getEvalScoreNoParent(String areaCode, String tableId, String beginDate, String endDate) {

		Map<String, String> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("tableId", tableId);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);

		//获取测评分数
		List<DepartmentScore> list = DaoFactory.create(DepartmentScore.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.department.DepartmentMapper.getEvalScoreNoParent", map);

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
		List<DepartmentTable> list = DaoFactory.create(DepartmentTable.class).getSession()
				.selectList("com.chinacreator.dzjc_evaluation.department.DepartmentMapper.getEvalTable", org_id);

		List<CommonTreeNode> treeList = new ArrayList<CommonTreeNode>();
		for (DepartmentTable departmentTable : list) {

			CommonTreeNode orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(departmentTable.getTable_id());
			orgTreeNode.setName(departmentTable.getTable_name());

			treeList.add(orgTreeNode);
		}

		return (TreeNode[]) treeList.toArray(new CommonTreeNode[treeList.size()]);
	}

	/**
	 * 部门统计Excel导出
	 * 
	 * @param org_id 机构编号
	 * @param tableId 考核表编号
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 */
	@SuppressWarnings("unchecked")
	public String DepartmentExcel(String org_id, String tableId, String beginDate, String endDate) {

		//导出所需数据
		Map<String, Object> map = this.getDepartmentEval(org_id, tableId, beginDate, endDate);

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

			parentStr.add("单位名称");
			parentIndex.add("1,2,1,1");

			parentStr.add("名次");
			parentIndex.add("1,2,2,2");

			int listSize = 0;//因为最后一列为总分固定列，前面有多少列得通过累加才知道

			//如果带父级的子表头不为空，那一定有父级
			if (objectList2 != null) {

				//下层表头（有父级）
				List<DepartmentItem> itemAndParentList = (List<DepartmentItem>) objectList2;
				listSize += itemAndParentList.size();

				int num1 = 3;//因为前面已经占了三格固定标题，父标题从下标3，也就是第4格开始

				//计算父表头下标
				List<DepartmentParent> parentList = (List<DepartmentParent>) objectList1;
				for (DepartmentParent parent : parentList) {
					int num2 = 0;

					//匹配每个父级下有多少个子级表头
					for (DepartmentItem item : itemAndParentList) {
						if (parent.getParent_item_name().equals(item.getParent_item_name())) {

							//有多少个子级表头，就要重复加入对应的父级表头
							parentStr.add(parent.getParent_item_name());
							num2++;
						}
					}

					/**
					 * 下标规则： 1位：从第几行开始。 2位：从第几行结束。3位：从第几列开始。4位：从第几列结束
					 */
					parentIndex.add("1,1," + num1 + "," + (num1 + num2 - 1));
					num1 += num2;
				}

				int num3 = 3;//因为前面已经占了三格固定标题，子标题从下标3，也就是第4格开始

				//计算下层表头（有父级）的下标
				for (DepartmentItem item : itemAndParentList) {
					itemStr.add(item.getItem_name());
					itemIndex.add("2,2," + num3 + "," + num3);//每个子标题占一列
					num3++;
				}

			}

			//如果存在不带父级的表头，计算其下标
			int num4 = listSize + 3;
			if (objectList3 != null) {

				List<DepartmentItem> itemNoParentList = (List<DepartmentItem>) objectList3;

				for (DepartmentItem item : itemNoParentList) {
					parentStr.add(item.getItem_name());
					parentIndex.add("1,2," + num4 + "," + num4);
					num4++;
				}

				listSize += itemNoParentList.size();
			}

			parentStr.add("加分");
			parentIndex.add("1,2," + (listSize + 3) + "," + (listSize + 3));//加3是因为最开始有3列固定的列
			parentStr.add("最终得分");
			parentIndex.add("1,2," + (listSize + 4) + "," + (listSize + 4));//加3是因为最开始有3列固定的列
			Map<String, Object> newMap = new HashMap<>();
			newMap.put("parentStr", parentStr.toArray());
			newMap.put("parentIndex", parentIndex.toArray());
			newMap.put("itemStr", itemStr.toArray());
			newMap.put("itemIndex", itemIndex.toArray());
			newMap.put("orgList", map.get("orgList"));
			newMap.put("toptitle", 3);

			//生成Excel文件
			EvaluationExcel excel = new EvaluationExcel();
			excel.excelExport("部门绩效测评统计表", newMap, "1");

		}

		return "部门绩效测评统计表";
	}
}
