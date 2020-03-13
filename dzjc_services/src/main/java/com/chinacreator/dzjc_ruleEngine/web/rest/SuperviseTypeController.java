package com.chinacreator.dzjc_ruleEngine.web.rest;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
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
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_ruleEngine.ApprovetypeNew;
import com.chinacreator.dzjc_ruleEngine.MsgTemplate;
import com.chinacreator.dzjc_ruleEngine.RuleElement;
import com.chinacreator.dzjc_ruleEngine.RuleExpInfo;
import com.chinacreator.dzjc_ruleEngine.RuleExpletInfo;
import com.chinacreator.dzjc_ruleEngine.RuleHistoryInfo;
import com.chinacreator.dzjc_ruleEngine.RuleSuperviseDefinition;
import com.chinacreator.dzjc_ruleEngine.SuperviseResult;
import com.chinacreator.dzjc_ruleEngine.SuperviseType;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SuperviseTypeController {

	@GET
	@Path("superviseType/{superviseTypeCode}")
	public SuperviseType get(
			@PathParam(value = "superviseTypeCode") java.lang.String superviseTypeCode) {
		//TODO auto-generated method stub
		return DaoFactory.create(SuperviseType.class).selectByID(
				superviseTypeCode);
	}

	@GET
	@Path("superviseType")
	public Page<SuperviseType> getListByPage(
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
		SuperviseType entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, SuperviseType.class) : new SuperviseType();

		return DaoFactory.create(SuperviseType.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 自定义获取监察类型
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("publicType/superviseType")
	public List<SuperviseType> getSuperviseType() {
		List<SuperviseType> list = DaoFactory
				.create(SuperviseType.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseTypeMapper.selectType");
		
 		return list;
	}
	
	
	/**
	 * 自定义获取监察级别
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("publicType/superviseResult")
	public List<SuperviseResult> getSuperviseResult() {
		List<SuperviseResult> list = DaoFactory
				.create(SuperviseResult.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseResultMapper.selectOption");
		
 		return list;
	}
	
	
	
	/**
	 * 自定义获取消息模板
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("publicType/msgTemplate")
	public List<MsgTemplate> getMsgTemplate(@RequestBody Map<String,String> areaCodeMap) {
		String areaCode=areaCodeMap.get("areacode");
		List<MsgTemplate> list = DaoFactory
				.create(MsgTemplate.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.MsgTemplateMapper.selectOption",areaCode);
		
 		return list;
	}
	
	
	/**
	 * 自定义获取消息模板
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("publicType/msgTemplateAll")
	public List<MsgTemplate> getMsgTemplateAll(@RequestBody Map<String,String> areaCodeMap) {
		List<MsgTemplate> list = DaoFactory
				.create(MsgTemplate.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.MsgTemplateMapper.selectOptionAll");
		
 		return list;
	}
	
	/**
	 * 自定义获取监察要素
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("publicType/ruleElement")
	public List<RuleElement> getEuleElement(@RequestBody Map<String,String> elemIdMap) {
		String elemId=elemIdMap.get("elemId").trim();
		List<RuleElement> list = DaoFactory
				.create(RuleElement.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.RuleElementMapper.selectByID",elemId);
		
 		return list;
	}
	
	/**
	 * 自定义新增，规则历史表
	 */
	@POST
	@Path("publicType/insertRuleHistoy")
	public RuleHistoryInfo insertRuleHistoy(@RequestBody Map<String,String> map){
		RuleHistoryInfo entity=new RuleHistoryInfo();
		entity.setRuleId(map.get("ruleId").trim());
		entity.setRuleName(map.get("ruleName").trim());
		entity.setRuleMemo(map.get("ruleMemo").trim());
		entity.setRuleVersion(map.get("ruleVersion").trim());
		entity.setIsValid(map.get("isValid").trim());
		entity.setIsAutoRun(map.get("isAutoRun").trim());
		entity.setRuleType(map.get("ruleType").trim());
		entity.setJbjIsMonitor(map.get("jbjIsMonitor"));
		entity.setRunPriority(map.get("runPriority").trim());
		
		DaoFactory.create(RuleHistoryInfo.class).insert(entity);
		return entity;
	}
	
	
	/**
	 * 自定义新增，表达式和规则对应表
	 */
	@POST
	@Path("publicType/insertRuleExpInfo")
	public RuleExpInfo insertRuleExpInfo(@RequestBody Map<String,String> map){
		RuleExpInfo entity=new RuleExpInfo();
		entity.setRuleId(map.get("ruleId").trim());
		entity.setAreaCode(map.get("areaCode"));
		entity.setExpSeq(map.get("expSeq").trim());
		entity.setRuleVersion(map.get("ruleVersion").trim());
		//根据ruleId判断数据是否存在，存在，就修改，不存在，就新增
		DaoFactory.create(RuleExpInfo.class).insert(entity);
		
		RuleExpInfo entity1= DaoFactory.create(RuleExpInfo.class).getSession().selectOne("com.chinacreator.dzjc_ruleEngine.RuleExpInfoMapper.selectByRuleID",entity);
		
		return entity1;
	}
	
	
	/**
	 * 自定义新增，表达式信息表
	 */
	@POST
	@Path("publicType/insertRuleletExpInfo")
	public RuleExpletInfo insertRuleletExpInfo(@RequestBody Map<String,String> map){
		RuleExpletInfo entity=new RuleExpletInfo();
		entity.setExpId(map.get("expId").trim());
		entity.setLeftParen(map.get("leftParen").trim());
		entity.setElemId(map.get("elemId").trim());
		entity.setCompaId(map.get("compaId").trim());
		entity.setCfgValue(map.get("cfgValue").trim());
		entity.setRightParen(map.get("rightParen").trim());
		entity.setLogicId(map.get("logicId").trim());
		entity.setAreaCode(map.get("areaCode"));
		
		DaoFactory.create(RuleExpletInfo.class).insert(entity);
		
		
		return entity;
	}
	
	
	/**
	 * 自定义新增，监察定义与表达式定义表
	 */
	@POST
	@Path("publicType/insertRuleSuperviseDefinition")
	public RuleSuperviseDefinition insertRuleSuperviseDefinition(@RequestBody Map<String,String> map){
		RuleSuperviseDefinition entity=new RuleSuperviseDefinition();
		entity.setExpId(map.get("expId").trim());
		entity.setAreaCode(map.get("areaCode"));
		entity.setSuperviseLevelCode(map.get("superviseLevelCode").trim());
		entity.setSendcardType(map.get("sendcardType").trim());
		entity.setMsgTemplateId(map.get("msgTemplateId").trim());
		entity.setSuperviseTypeCode(map.get("superviseTypeCode").trim());
		DaoFactory.create(RuleSuperviseDefinition.class).insert(entity);
		
		
		return entity;
	}
	
	
	/**
	 * 自定义删除，ta_jc_rule_expletinfo TA_JC_RULE_SUPERVISE_DEFINITION  TA_JC_RULE_EXPINFO
	 */
	@POST
	@Path("publicType/delete")
	public void delete(@RequestBody Map<String,String> map){
		String ruleId=map.get("ruleId").trim();
		DaoFactory.create(RuleExpletInfo.class).getSession().delete("com.chinacreator.dzjc_ruleEngine.RuleExpletInfoMapper.deleteByRuleId",ruleId);
		DaoFactory.create(RuleExpletInfo.class).getSession().delete("com.chinacreator.dzjc_ruleEngine.RuleSuperviseDefinitionMapper.deleteByRuleId",ruleId);
		DaoFactory.create(RuleExpInfo.class).getSession().delete("com.chinacreator.dzjc_ruleEngine.RuleExpInfoMapper.deleteByRuleId",ruleId);
		
		
	}
	
	
	/**
	 * 自定义获取监察级别
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("publicType/selectRuleSuperviseDefinition")
	public RuleSuperviseDefinition selectRuleSuperviseDefinition(@RequestBody Map<String,String> map) {
		String ruleId=map.get("ruleId").trim();
		String expSeq=map.get("expSeq").trim();
		RuleExpInfo entity=new RuleExpInfo();
		entity.setRuleId(ruleId);
		entity.setExpSeq(expSeq);
		RuleSuperviseDefinition ruleSuperviseDefinition=new RuleSuperviseDefinition();
		
		ruleSuperviseDefinition= DaoFactory.create(RuleSuperviseDefinition.class).getSession().selectOne("com.chinacreator.dzjc_ruleEngine.RuleSuperviseDefinitionMapper.selectByRuleId",entity);
 		return ruleSuperviseDefinition;
	}
	
	
	/**
	 * 自定义获取监察类型
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("publicType/ruleExpletInfoByRuleId")
	public List<RuleExpletInfo> getRuleExpletInfoByRuleId(@RequestBody Map<String,String> map) {
		String ruleId=map.get("ruleId").trim();
		String expSeq=map.get("expSeq").trim();
		String ruleVersion=map.get("ruleVersion").trim();
		RuleExpInfo ruleExpInfo=new RuleExpInfo();
		ruleExpInfo.setRuleId(ruleId);
		ruleExpInfo.setExpSeq(expSeq);
		ruleExpInfo.setRuleVersion(ruleVersion);
		List<RuleExpletInfo> list = DaoFactory
				.create(RuleExpletInfo.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.RuleExpletInfoMapper.selectByRuleId",ruleExpInfo);
		
 		return list;
	}
	
	
	
	/**
	 * 自定义根据父Code获取监察类型
	 * @return
	 */
	@POST
	@Path("publicType/superviseTypeByParent")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getsuperviseTypeByParent(@FormParam("bussinessType") String bussinessType) {
		List<DefaultTreeNode> childrenTree = new ArrayList<DefaultTreeNode>(); 
		
		String parentTypeCode=bussinessType;
		if(parentTypeCode!=null){
			parentTypeCode=parentTypeCode.trim();
		}
		List<SuperviseType> list = DaoFactory
				.create(SuperviseType.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseTypeMapper.selectTypeByParent",parentTypeCode);
		
		for(int j=0;j<list.size();j++){
			DefaultTreeNode dtC = new DefaultTreeNode(list.get(j).getSuperviseTypeCode(),list.get(j).getSuperviseTypeName());
			dtC.setPid(list.get(j).getParentTypeCode());
			childrenTree.add(dtC);
			
		}
		 
		return childrenTree.toArray(new DefaultTreeNode[childrenTree.size()]);
			 
	}
	
	
	/**
	 * 自定义获取监察级别
	 * @return
	 */
	@POST
	@Path("publicType/superviseResultClen")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getsuperviseResultClen() {
		List<DefaultTreeNode> childrenTree = new ArrayList<DefaultTreeNode>(); 
		
		List<SuperviseResult> list = DaoFactory
				.create(SuperviseResult.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseResultMapper.selectOptionClen");
		
		for(int j=0;j<list.size();j++){
			DefaultTreeNode dtC = new DefaultTreeNode(list.get(j).getSuperviseResultCode(),list.get(j).getSuperviseResultName());
			//dtC.setPid(list.get(j).getParentTypeCode());
			childrenTree.add(dtC);
		}
		 
		return childrenTree.toArray(new DefaultTreeNode[childrenTree.size()]);
			 
	}
	
	@POST
	@Path("publicType/superviseResultAll")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getsuperviseResultAll() {
		List<CommonTreeNode> list1 = new ArrayList<CommonTreeNode>(); 
		
		List<SuperviseResult> list = DaoFactory
				.create(SuperviseResult.class)
				.selectAll();
		/*for(int j=0;j<list.size();j++){
			DefaultTreeNode dtC = new DefaultTreeNode(list.get(j).getSuperviseResultCode(),list.get(j).getSuperviseResultName());
			//dtC.setPid(list.get(j).getParentTypeCode());
			childrenTree.add(dtC);
		}*/
		CommonTreeNode orgTreeNode = null;
		for (int i = 0; i < list.size(); i++) {
			if("正常".equals(list.get(i).getSuperviseResultName())){
				continue;
			}
			orgTreeNode = new CommonTreeNode();
			orgTreeNode.setId(list.get(i).getSuperviseResultId());
			orgTreeNode.setShowName(list.get(i).getSuperviseResultName());
			orgTreeNode.setName(list.get(i).getSuperviseResultName());
			orgTreeNode.setPid("0");
			list1.add(orgTreeNode);
		}

		return list1.toArray(new CommonTreeNode[list1.size()]);
			 
	}
	
	
	
	/**
	 * 自定义获取监察类型
	 * @return
	 */
	@POST
	@Path("publicType/superviseTypeAll")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getsuperviseTypeByAll() {
		List<DefaultTreeNode> childrenTree = new ArrayList<DefaultTreeNode>(); 
		
		
		List<SuperviseType> list = DaoFactory
				.create(SuperviseType.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.SuperviseTypeMapper.selectTypeAll");
		
		for(int j=0;j<list.size();j++){
			DefaultTreeNode dtC = new DefaultTreeNode(list.get(j).getSuperviseTypeCode(),list.get(j).getSuperviseTypeName());
			dtC.setPid(list.get(j).getParentTypeCode());
			childrenTree.add(dtC);
		}
		 
		return childrenTree.toArray(new DefaultTreeNode[childrenTree.size()]);
			 
	}
	
	
	
	
	
	
}
