package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.chinacreator.dzjc_ruleEngine.UserInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.StringUtil;
import com.chinacreator.util.BeanUtil;
import com.chinacreator.util.BeanUtils;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class UserInfoController {

	@GET
	@Path("userOrgInfo/{id}")
	public UserInfo get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(UserInfo.class).selectByID(id);
	}

	@GET
	@Path("userOrgInfo")
	public Page<UserInfo> getListByPage(@QueryParam(value = "page") int page,
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
		UserInfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				UserInfo.class) : new UserInfo();
		Map<String, Object> params = BeanUtils.transBean2Map(entity);
		if(conditions!=null){
			List<Rule> rules = conditions.getRules();
			if(rules!=null && rules.size()>0){
				for (int i = 0; i < rules.size(); i++) {
					if("orgId".equals(rules.get(i).getField())){
						Object data = rules.get(i).getData();
						params.put("orgId", data.toString());
						rules.remove(i);
					}
					if(rules.size()==0){
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
		if(params!=null){
			if(StringUtil.isBlank(code)){
				return null;
			}
			if(StringUtil.isBlank(com.chinacreator.util.StringUtil.DeNull(params.get("orgId")))){
				params.put("orgCode", code);
			}
		}
		return DaoFactory.create(UserInfo.class).selectPageByCondition(params,
				conditions, pageable, sortable, true);

	}
}
