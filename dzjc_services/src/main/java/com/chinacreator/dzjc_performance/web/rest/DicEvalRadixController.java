package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;

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
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.DicEvalRadix;
import com.chinacreator.dzjc_performance.DicEvalRadixExp;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DicEvalRadixController {

	@DELETE
	@Path("dicEvalRadix/{radixId}")
	public void delete(@PathParam(value = "radixId") java.lang.String radixId) {
		//TODO auto-generated method stub
		DicEvalRadix entity = new DicEvalRadix();
		entity.setRadixId(radixId);
		DaoFactory.create(DicEvalRadix.class).delete(entity);
	}

	@DELETE
	@Path("dicEvalRadix")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(DicEvalRadix.class).deleteBatch(ids);
	}

	@GET
	@Path("dicEvalRadix/{radixId}")
	public DicEvalRadix get(
			@PathParam(value = "radixId") java.lang.String radixId) {
		//TODO auto-generated method stub
		return DaoFactory.create(DicEvalRadix.class).selectByID(radixId);
	}

	@POST
	@Path("dicEvalRadix")
	public DicEvalRadix insert(DicEvalRadix entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(DicEvalRadix.class).insert(entity);
		return entity;
	}

	@POST
	@Path("dicEvalRadix/{radixId}")
	public DicEvalRadix update(DicEvalRadix entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(DicEvalRadix.class).update(entity);
		return entity;
	}

	@GET
	@Path("dicEvalRadix")
	public Page<DicEvalRadixExp> getListByPage(
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
		DicEvalRadixExp entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, DicEvalRadixExp.class) : new DicEvalRadixExp();

		return DaoFactory.create(DicEvalRadixExp.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 根据用户权限获取考核基数树
	 * @return
	 */
	@POST
	@Path("/getRadixTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getElements(){
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
		List<DicEvalRadix> radixList =new ArrayList<>();
		
		if(code.startsWith("43")){
			// 查询考核基数树形信息
			radixList = DaoFactory.create(DicEvalRadix.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.DicEvalRadixMapper.selectByAreaCode", code);
		}else{
			Orgview orgview = DaoFactory.create(Orgview.class).selectByID(code);
			code = orgview.getCode().substring(0, 12);
			radixList = DaoFactory.create(DicEvalRadix.class)
					.getSession().selectList("com.chinacreator.dzjc_performance.DicEvalRadixMapper.selectByAreaCode", code);
		}
		
		CommonTreeNode treeNode = null;
		for (int i = 0; i < radixList.size(); i++) {
			treeNode = new CommonTreeNode();
			treeNode.setId(radixList.get(i).getRadixId());
			treeNode.setShowName(radixList.get(i).getRadixName());
			treeNode.setName(radixList.get(i).getRadixName());
			list.add(treeNode);
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	
	@GET
	@Path("dicEvalRadix2/{radixName}")
	public DicEvalRadix getByName(
			@PathParam(value = "radixName") java.lang.String radixName) {
		return DaoFactory.create(DicEvalRadix.class).getSession()
				.selectOne("com.chinacreator.dzjc_performance.DicEvalRadixMapper.selectByName", radixName);
	}
}
