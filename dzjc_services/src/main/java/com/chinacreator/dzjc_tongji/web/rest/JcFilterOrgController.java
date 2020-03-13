package com.chinacreator.dzjc_tongji.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_tongji.JcFilterOrg;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class JcFilterOrgController {

	@DELETE
	@Path("JcFilterOrg/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		JcFilterOrg entity = new JcFilterOrg();
		entity.setId(id);
		DaoFactory.create(JcFilterOrg.class).delete(entity);
	}

	@DELETE
	@Path("JcFilterOrg")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(JcFilterOrg.class).deleteBatch(ids);
	}

	@GET
	@Path("JcFilterOrg/{id}")
	public JcFilterOrg get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(JcFilterOrg.class).selectByID(id);
	}

	@POST
	@Path("JcFilterOrg")
	public JcFilterOrg insert(JcFilterOrg entity) {
		if(entity!=null&&StringUtils.isNotBlank(entity.getOrgId())){
			JcFilterOrg param = new JcFilterOrg();
			param.setOrgId(entity.getOrgId());
			List list = DaoFactory.create(JcFilterOrg.class).select(param);
			if(list==null||list.size()==0){
				DaoFactory.create(JcFilterOrg.class).insert(entity);
			}
		}
		return entity;
	}

	@POST
	@Path("JcFilterOrg/{id}")
	public JcFilterOrg update(JcFilterOrg entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(JcFilterOrg.class).update(entity);
		return entity;
	}

	@GET
	@Path("JcFilterOrg")
	public Page<JcFilterOrg> getListByPage(
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
		JcFilterOrg entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, JcFilterOrg.class) : new JcFilterOrg();

		return DaoFactory.create(JcFilterOrg.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
