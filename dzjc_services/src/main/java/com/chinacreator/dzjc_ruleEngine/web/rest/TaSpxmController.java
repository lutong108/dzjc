package com.chinacreator.dzjc_ruleEngine.web.rest;

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
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.TaSpxm;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.StringUtil;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaSpxmController {

	@DELETE
	@Path("taSpxm/{approveId}")
	public void delete(
			@PathParam(value = "approveId") java.lang.String approveId) {
		//TODO auto-generated method stub
		TaSpxm entity = new TaSpxm();
		entity.setApproveId(approveId);
		DaoFactory.create(TaSpxm.class).delete(entity);
	}

	@DELETE
	@Path("taSpxm")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(TaSpxm.class).deleteBatch(ids);
	}

	@GET
	@Path("taSpxm/{approveId}")
	public TaSpxm get(@PathParam(value = "approveId") java.lang.String approveId) {
		//TODO auto-generated method stub
		return DaoFactory.create(TaSpxm.class).selectByID(approveId);
	}

	@POST
	@Path("taSpxm")
	public TaSpxm insert(TaSpxm entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaSpxm.class).insert(entity);
		return entity;
	}

	@POST
	@Path("taSpxm/{approveId}")
	public TaSpxm update(TaSpxm entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaSpxm.class).update(entity);
		return entity;
	}

	@GET
	@Path("taSpxm")
	public Page<TaSpxm> getListByPage(@QueryParam(value = "page") int page,
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
		TaSpxm entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				TaSpxm.class) : new TaSpxm();
		if(conditions!=null){
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				if("orgId".equals(listRule.get(i).getField())){
					entity.setOrgId((String) listRule.get(i).getData());
					listRule.remove(i);
					i=0;
					if(listRule.size()==0){
						conditions=null;
						break;
					}
				}
			}
		}
		String code=null;
		try{
			code=RoleUtil.getInstance().queryCodeByUserId();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(entity!=null){
			if(StringUtil.isBlank(code)){
				return null;
			}
			if(StringUtil.isBlank(entity.getOrgId())){
				entity.setOrgCode(code);
			}else{
				entity.setOrgCode(null);
			}
		}
		long start = System.currentTimeMillis();
		Page<TaSpxm> selectPageByCondition = DaoFactory.create(TaSpxm.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		return DaoFactory.create(TaSpxm.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);
	}
}
