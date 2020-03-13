package com.chinacreator.dzjc_ruleEngine.web.rest;

import io.swagger.annotations.ApiOperation;

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
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.ApprovetypeNew;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ApprovetypeNewController {

	@GET
	@Path("approveTypeNew/{seq}")
	public ApprovetypeNew get(@PathParam(value = "seq") java.lang.Double seq) {
		//TODO auto-generated method stub
		return DaoFactory.create(ApprovetypeNew.class).selectByID(seq);
	}

	@GET
	@Path("approveTypeNew")
	public Page<ApprovetypeNew> getListByPage(
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
		ApprovetypeNew entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, ApprovetypeNew.class) : new ApprovetypeNew();

		return DaoFactory.create(ApprovetypeNew.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	
	@POST
	@Path("ApprovetypeTreeNew")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "获取事项类别树", tags = "ApprovetypeTreeResource", notes = "")
	public TreeNode[] getElements() {
		List<DefaultTreeNode> childrenTree = new ArrayList<DefaultTreeNode>(); 
		
		List<ApprovetypeNew>  lst = getAll();
		
		for(int j=0;j<lst.size();j++){
			DefaultTreeNode dtC = new DefaultTreeNode(lst.get(j).getTypeCode(),lst.get(j).getTypeName());
			dtC.setPid(lst.get(j).getParentTypeCode());
			childrenTree.add(dtC);
		}
		 
		return childrenTree.toArray(new DefaultTreeNode[childrenTree.size()]);
			 
	}
	
	
	@GET
	@Path("approveTypeNew/getAll")
	@ApiOperation(value = "查找所有事项类型", tags = "ApproveTypeResource", notes = "")
	public List<ApprovetypeNew> getAll() {
		return DaoFactory.create(ApprovetypeNew.class).selectAll();
	}
	
}
