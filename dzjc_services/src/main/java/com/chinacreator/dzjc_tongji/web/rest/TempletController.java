package com.chinacreator.dzjc_tongji.web.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_tongji.JcOrgView;
import com.chinacreator.dzjc_tongji.ParamEntity;
import com.chinacreator.dzjc_tongji.TongJi;
import com.chinacreator.util.DateUtil;
/**
 * 将统计中Controller层公用代码提出
 * @author Administrator
 *
 */
public class TempletController {
	//private Configuration configuration = null;
	
	/**
	 * 导出
	 */
	public String expExcel(String type,String beginDate,String endDate,String org_id,String year,String month,Class clazz){
		return null;
	}
	/**办件统计(新)
	 */

	@SuppressWarnings("unchecked")
	public Map<String,Object> getTongJiList(Integer page,Integer rows,String sidx,String sord,String cond,Class clazz) {
		

		//创建分页对象
		Pageable pageable = new Pageable(page, rows-1);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		
		/*填充查询添加 机构id、开始时间、结束时间*/
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if(beginDateSql!=null){
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if(endDateSql!=null){
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		
		List<Object> list = null;
		JcOrgView userOrgView = null;
		if(StringUtils.isNotBlank(orgId)){
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		}else{
			userOrgView = getUserOrgId();
		}
		/*将添加带入到sql中查询*/
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,conditions,true);
		if(userOrgView!=null){
			Map<String,String> params = new HashMap<String,String>();
			params.put("orgLevel", userOrgView.getOrgLevel()+"");
			if(StringUtils.isNotBlank(beginDateStr)){
				params.put("beginDate", beginDateStr);
			}
			if(StringUtils.isNotBlank(endDateStr)){
				params.put("endDate", endDateStr);
			}
			/*不同机构级别根据不同sql查询*/
			if(userOrgView.getOrgLevel()<3&& !(userOrgView.getOrgThisLevel()==2||userOrgView.getOrgThisLevel()==1)){
				//湖南省、长沙市
				params.put("pId", userOrgView.getOrgId());
				list = DaoFactory.create(clazz).getSession().selectList(clazz.getName()+"Mapper.selectByTongJi",  params, rowbound);
			}else if(userOrgView.getOrgThisLevel()==2||userOrgView.getOrgThisLevel()==1){
				//省本级，市本级
				params.put("pId", userOrgView.getOrgId());
				list = DaoFactory.create(clazz).getSession().selectList(clazz.getName()+"Mapper.selectByTongJiBenJi",  params, rowbound);
			}else if(userOrgView.getOrgLevel()==3){
				//长沙市
				params.put("countyCode", userOrgView.getCountyCode());
				list = DaoFactory.create(clazz).getSession().selectList(clazz.getName()+"Mapper.selectByTongJiCounty",  params, rowbound);
			}else if(userOrgView.getOrgLevel()==5){
				//职能机构
				params.put("orgId", userOrgView.getOrgId());
				list = DaoFactory.create(clazz).getSession().selectList(clazz.getName()+"Mapper.selectByTongJiOrg",  params, rowbound);
			}else{
				list = new ArrayList<Object>();
			}
		}else{
			list = new ArrayList<Object>();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rowbound", rowbound);
		map.put("list", list);
		return map;
	}

	/**
	 * 导出查询,不需要分页
	 * @param beginDateStr
	 * @param endDateStr
	 * @param jcOrgView
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private List getDataListByBanJianTongJinew(String beginDateStr, String endDateStr, JcOrgView jcOrgView,Class clazz) {
		List list = null;;
		if(jcOrgView!=null){
			Map<String,String> params = new HashMap<String,String>();
			params.put("orgLevel", jcOrgView.getOrgLevel()+"");
			if(StringUtils.isNotBlank(beginDateStr)){
				params.put("beginDate", beginDateStr);
			}
			if(StringUtils.isNotBlank(endDateStr)){
				params.put("endDate", endDateStr);
			}
			/*不同机构级别根据不同sql查询*/
			if(jcOrgView.getOrgLevel()<3&& !(jcOrgView.getOrgThisLevel()==2||jcOrgView.getOrgThisLevel()==1)){
				params.put("pId", jcOrgView.getOrgId());
				list = DaoFactory.create(clazz).getSession().selectList(clazz.getName()+"Mapper.selectByTongJi",  params);
			}else if(jcOrgView.getOrgThisLevel()==2||jcOrgView.getOrgThisLevel()==1){
				params.put("pId", jcOrgView.getOrgId());
				list = DaoFactory.create(TongJi.class).getSession().selectList(clazz.getName()+"Mapper.selectByTongJiBenJi",  params);
			}else if(jcOrgView.getOrgLevel()==3){
				params.put("countyCode", jcOrgView.getCountyCode());
				list = DaoFactory.create(clazz).getSession().selectList(clazz.getName()+"Mapper.selectByTongJiCounty",  params);
			}else if(jcOrgView.getOrgLevel()==5){
				params.put("orgId", jcOrgView.getOrgId());
				list = DaoFactory.create(clazz).getSession().selectList(clazz.getName()+"Mapper.selectByTongJiOrg",  params);
			}else{
				list = new ArrayList<TongJi>();
			}
		}else{
			list = new ArrayList<TongJi>();
		}
		return list;
	}
	/**获取用户和所关联角色最高机构id
	 * @return
	 */
	private JcOrgView getUserOrgId(){
		String userOrgId = "";//用户机构id
		JcOrgView userOrgView = null;//用户所在机构
		JcOrgView roleOrgView = null;//用户角色最高机构
		String xzqm = "";//当前登录用户行政区码
		
		/*获取当前登录用户信息*/
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");
		if(orgIds!=null&&orgIds.length>0){
			Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,orgIds[0]);
			if(org!=null){
				userOrgId = org.getId();
				xzqm = (String) org.get("xzqm");
			}
		}
		/*当用户未机构则不返回空*/
		if(StringUtils.isBlank(userOrgId)){
			return userOrgView;
		}
		/*获取用户机构*/
		userOrgView = DaoFactory.create(JcOrgView.class).selectByID(userOrgId);
		if(userOrgView==null){
			return userOrgView;
		}
		/*获取角色最高机构*/
		Set<String> roleSet = new HashSet<String>();
		if(roles!=null&&roles.length>0){
			roleSet = new HashSet<>(Arrays.asList(roles));
		}
		String code = null;
		if (roleSet.contains("dzjc_sjcjgly")) {//省级超级管理员
			code = userOrgView.getProvinceCode()+"000000000000";
		}else if (roleSet.contains("dzjc_shijcjgly")) {//市级超级管理员
			code = userOrgView.getCityCode()+"000000000000";
		}else if (roleSet.contains("dzjc_qxjcjgly")) {//区县级超级管理员
			code = userOrgView.getCountyCode()+"000000000000";
		}else if(roleSet.contains("dzjc_sjgly")||roleSet.contains("dzjc_shijgly")||roleSet.contains("dzjc_qxjgly")){
			code = xzqm+"000000000000";
		}
		if(StringUtils.isNotBlank(code)){
			roleOrgView = new JcOrgView();
			roleOrgView.setOrgCode(code);
			List<JcOrgView> list = DaoFactory.create(JcOrgView.class).select(roleOrgView);
			if(list!=null&&list.size()>0){
				roleOrgView = list.get(0);
			}
		}
		/*当角色的机构大于用户机构则返回角色机构ID*/
		if(roleOrgView!=null&&roleOrgView.getOrgLevel()<userOrgView.getOrgLevel()){
			return roleOrgView;
		}
		return userOrgView;
	}
}
