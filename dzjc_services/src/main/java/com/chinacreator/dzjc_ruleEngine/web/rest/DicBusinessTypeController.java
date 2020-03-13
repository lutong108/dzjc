package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.DicBusinessType;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DicBusinessTypeController {

	@DELETE
	@Path("dicBusinessType/{businessTypeCode}")
	public void delete(
			@PathParam(value = "businessTypeCode") java.lang.String businessTypeCode) {
		//TODO auto-generated method stub
		DicBusinessType entity = new DicBusinessType();
		entity.setBusinessTypeCode(businessTypeCode);
		DaoFactory.create(DicBusinessType.class).delete(entity);
	}

	@DELETE
	@Path("dicBusinessType")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(DicBusinessType.class).deleteBatch(ids);
	}

	@GET
	@Path("dicBusinessType/{businessTypeCode}")
	public DicBusinessType get(
			@PathParam(value = "businessTypeCode") java.lang.String businessTypeCode) {
		//TODO auto-generated method stub
		return DaoFactory.create(DicBusinessType.class).selectByID(
				businessTypeCode);
	}

	@POST
	@Path("dicBusinessType")
	public DicBusinessType insert(DicBusinessType entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(DicBusinessType.class).insert(entity);
		return entity;
	}

	@POST
	@Path("dicBusinessType/{businessTypeCode}")
	public DicBusinessType update(DicBusinessType entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(DicBusinessType.class).update(entity);
		return entity;
	}

	@GET
	@Path("dicBusinessType")
	public Page<DicBusinessType> getListByPage(
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
		DicBusinessType entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, DicBusinessType.class)
				: new DicBusinessType();

		return DaoFactory.create(DicBusinessType.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	
	@POST
	@Path("dicBusinessType/dicBusinessName")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	//@ApiOperation(value = "根据字典类型查询字典值", tags = "dictResource", notes = "")
	public TreeNode[] getDictDataMap() {
		List<DefaultTreeNode> childrenTree = new ArrayList<DefaultTreeNode>(); 
		List<DicBusinessType> list = DaoFactory
				.create(DicBusinessType.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.DicBusinessTypeMapper.selectAll");
		
		for (DicBusinessType dicBusinessType : list) {
			DefaultTreeNode dtC = new DefaultTreeNode(dicBusinessType.getBusinessTypeCode(),dicBusinessType.getBusinessTypeName());
			childrenTree.add(dtC);
		}
		
		return childrenTree.toArray(new DefaultTreeNode[childrenTree.size()]);
	}
}
