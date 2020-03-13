package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.VEvalPoint;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class VEvalPointController {

	@DELETE
	@Path("vEvalPoint/{pointId}")
	public void delete(@PathParam(value = "pointId") java.lang.String pointId) {
		//TODO auto-generated method stub
		VEvalPoint entity = new VEvalPoint();
		entity.setPointId(pointId);
		DaoFactory.create(VEvalPoint.class).delete(entity);
	}

	@DELETE
	@Path("vEvalPoint")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(VEvalPoint.class).deleteBatch(ids);
	}

	@GET
	@Path("vEvalPoint/{pointId}")
	public VEvalPoint get(@PathParam(value = "pointId") java.lang.String pointId) {
		//TODO auto-generated method stub
		return DaoFactory.create(VEvalPoint.class).selectByID(pointId);
	}

	@POST
	@Path("vEvalPoint")
	public VEvalPoint insert(VEvalPoint entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(VEvalPoint.class).insert(entity);
		return entity;
	}

	@POST
	@Path("vEvalPoint/{pointId}")
	public VEvalPoint update(VEvalPoint entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(VEvalPoint.class).update(entity);
		return entity;
	}

	@GET
	@Path("vEvalPoint")
	public Page<VEvalPoint> getListByPage(@QueryParam(value = "page") int page,
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
		VEvalPoint entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, VEvalPoint.class) : new VEvalPoint();

		return DaoFactory.create(VEvalPoint.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 获取考核指标树
	 * @param orgId
	 * @return TreeNode[]
	 */
	@POST
	@Path("vEvalPoint/getPointTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getPointTree(@FormParam(value = "orgId") String  orgId){
		VEvalPoint entity = new VEvalPoint();
		if (org.apache.commons.lang3.StringUtils.isBlank(orgId)){
			return null;
		}
		Orgview orgview = DaoFactory.create(Orgview.class).selectByID(orgId);
		if (orgview == null){
			return null;
		}
		String code = orgview.getCode();
		String areaCode = code.substring(0, 12);
		entity.setAreaCode(areaCode);
		List<VEvalPoint> pointList = DaoFactory.create(VEvalPoint.class).getSession().selectList("com.chinacreator.dzjc_performance.VEvalPointMapper.selectByAreaCode", entity);
		CommonTreeNode pointTreeNode = null;
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		for (int i = 0; i < pointList.size(); i++) {
			pointTreeNode = new CommonTreeNode();
			pointTreeNode.setId(pointList.get(i).getPointId());
			pointTreeNode.setName(pointList.get(i).getPointName());
			pointTreeNode.setPid(pointList.get(i).getParentPointId());
			pointTreeNode.setShowName(pointList.get(i).getPointName());
			list.add(pointTreeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}

}
