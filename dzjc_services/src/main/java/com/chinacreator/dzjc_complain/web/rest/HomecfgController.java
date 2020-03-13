package com.chinacreator.dzjc_complain.web.rest;

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
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.FriendlyTip;
import com.chinacreator.dzjc_complain.Homecfg;
import com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatistics;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class HomecfgController {

	@DELETE
	@Path("homecfg/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		Homecfg entity = new Homecfg();
		entity.setId(id);
		DaoFactory.create(Homecfg.class).delete(entity);
	}

	@DELETE
	@Path("homecfg")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(Homecfg.class).deleteBatch(ids);
	}

	@GET
	@Path("homecfg/{id}")
	public Homecfg get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(Homecfg.class).selectByID(id);
	}

	@POST
	@Path("homecfg")
	public Homecfg insert(Homecfg entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Homecfg.class).insert(entity);
		return entity;
	}

	@POST
	@Path("homecfg/{id}")
	public Homecfg update(Homecfg entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Homecfg.class).update(entity);
		return entity;
	}

	@GET
	@Path("homecfg")
	public Page<Homecfg> getListByPage(@QueryParam(value = "page") int page,
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
		Homecfg entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				Homecfg.class) : new Homecfg();

		return DaoFactory.create(Homecfg.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);

	}
	
	@GET
	@Path("homecfg/select/{userId}")
	public List<Homecfg> select(@PathParam(value = "userId") java.lang.String userId) {
		Homecfg homeCfg = new Homecfg();
		homeCfg.setUserId(userId);
		return DaoFactory.create(Homecfg.class).select(homeCfg);

	}
	
	/**
	 * 查询展示首页“发牌情况温馨提示”中的数据
	 */
	@GET
	@Path("homecfg/friendlyTip")
	public Map<String, Object> selectFriendlyTip() {
		//1,定义实体用于封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		FriendlyTip friendlyTip = new FriendlyTip();
		List<FriendlyTip> dataList = new ArrayList<FriendlyTip>();
		String code = null;
		try {
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(code)){
			return null;
		}
		friendlyTip.setOrgId(code);
		friendlyTip.setGreenNum("0");
		friendlyTip.setYelloNum("0");
		friendlyTip.setRedNum("0");
		//3,根据org_id查询
		dataList = DaoFactory
				.create(FriendlyTip.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_complain.FriendlyTipMapper.getFriendlyTip",friendlyTip);
		//4,根据executeType判断发牌数据来源，0：自动发牌数据、1：人工发牌数据
		map.put("autoDate", friendlyTip);
		map.put("humanDate", friendlyTip);
		if(dataList != null && dataList.size() > 0){	
			for (FriendlyTip friendlyTipBean : dataList) {
				String executeType = friendlyTipBean.getExecuteType();
				if("0".equals(executeType)){
					map.put("autoDate", friendlyTipBean);
				}else{
					map.put("humanDate", friendlyTipBean);
				}
			}
		}	
		return map;
	}

	/**
	 * 获取当前登录用户所属机构的org_id
	 * @return
	 */
	private String getOrgId() {
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] orgIds = (String[]) user.get("orgIds");
		
		// 获取指定机构详情(用户所在机构)
		Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,
				orgIds[0]);
		
		return org.getId();
	}
}
