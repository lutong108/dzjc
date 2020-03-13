package com.chinacreator.dzjc_performance.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_performance.EvalInstance;
import com.chinacreator.dzjc_performance.UserPerformance;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class UserPerformanceController {

	@POST
	@Path("UserPerformance/{id}")
	public UserPerformance update(UserPerformance entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(UserPerformance.class).update(entity);
		return entity;
	}

	@POST
	@Path("UserPerformance")
	public UserPerformance insert(UserPerformance entity) {
		//TODO auto-generated method stub	
		 String uuid = UUID.randomUUID().toString().replaceAll("-","");  
		 entity.setUserId(uuid);
		DaoFactory.create(UserPerformance.class).insert(entity);
		return entity;
	}

	@GET
	@Path("UserPerformance/{id}")
	public UserPerformance get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(UserPerformance.class).selectByID(id);
	}

	@DELETE
	@Path("UserPerformance")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(UserPerformance.class).deleteBatch(ids);
	}

	@DELETE
	@Path("UserPerformance/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		UserPerformance entity = new UserPerformance();
		entity.setId(id);
		DaoFactory.create(UserPerformance.class).delete(entity);
	}

	@GET
	@Path("UserPerformance")
	public Page<UserPerformance> getListByPage(
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
		UserPerformance entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, UserPerformance.class)
				: new UserPerformance();
	      if (conditions != null) {
	    	  List<Rule> listRule = conditions.getRules();
	    	  for (int i = 0; i < listRule.size(); i++) {
	    		  if("orgId".equals(listRule.get(i).getField())){
	    			entity.setOrgId((String) listRule.get(i).getData());
	  				
	  			}else if("orgName".equals(listRule.get(i).getField())){
	    			  entity.setId((String) listRule.get(i).getData());
	    			
	  			}else if("name".equals(listRule.get(i).getField())){
	  				 entity.setName((String) listRule.get(i).getData());
	  			}
	    	  }
	      }
		return DaoFactory.create(UserPerformance.class).selectPageByCondition(
				entity, null, pageable, sortable, true);

	}
	
	@GET
	@Path("UserPerformance/getCheckList")
	public String getCheckList() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String id = request.getParameter("id");
		UserPerformance result= DaoFactory.create(UserPerformance.class).selectByID(id);
		if(result!=null){
			if(result.getId().equals(id)){
				return "1";
			}else{	
				return "0";
			}
		}else{
			return "0";
		}
		
	}
	
	
	@GET
	@Path("UserPerformance/getCheckList_isdelete")
	public List<String> getCheckList_isdelete() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String [] user_id =  request.getParameterValues("user_id");
		 List<String> listString_true =new ArrayList<String>();
		 List<String> listString_false =new ArrayList<String>();
		if(user_id!=null){
			for(int i=0; i<user_id.length; i++){
				EvalInstance evalInstance = new EvalInstance();
				evalInstance.setEvalObjectIds(user_id[i]);
				List<EvalInstance> list=	DaoFactory.create(EvalInstance.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalInstanceMapper.select_List", evalInstance);
				if(list.size()>=1){
					listString_true.add(0, list.get(0).getRemark());
					listString_true.add(1, "1");
				}else{
					listString_false.add(0, "");
					listString_false.add(1, "0");
				}
			}
		}
		
		if (listString_true.size() >= 1) {
			return listString_true;
		} else {
			return listString_false;
		}
		
		
	}
}
