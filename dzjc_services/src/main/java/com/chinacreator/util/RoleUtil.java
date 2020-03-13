package com.chinacreator.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_log.util.ConsoleLogUtil;
import com.chinacreator.dzjc_tongji.JcOrgView;

public class RoleUtil {

	private static RoleUtil instance;
	private static Logger log = Logger.getLogger(RoleUtil.class);

	private RoleUtil() {
	}

	public static RoleUtil getInstance() {
		if (instance == null) {
			instance = new RoleUtil();
		}
		return instance;
	}

	/**
	 * 根据用户ID查询该用户的机构操作范围 
	 * 1、超级管理员： 
	 * 		a、省超级管理员，可以查看和操作全省下面的机构，并返回行政区码前2位
	 * 		b、市超级管理员，可以查看和操作全市下面的机构，并返回行政区码前4位 
	 * 		c、区超级管理员，可以查看和操作全区下面的机构，并返回行政区码前6位
	 * 
	 * 2、本级管理员：省本级、市本级、区本级，可以查看和操作该本级以下的机构，并返回行政区码
	 * 
	 * 3、具有单位角色或没有角色，则只能操作用户所在的机构，返回该人员所在的机构ID
	 * 
	 * @param userId
	 * @return
	 */
	public String queryCodeByUserId() {

		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");
		String code = null;
		Organization org = null;

		// 获取指定机构详情(用户所在机构)
		if(orgIds != null&&orgIds.length>0){
			org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,orgIds[0]);
			code = org.getId();
		}else{
			StringBuilder msg = new StringBuilder();
			if(user!=null&&StringUtils.isNotBlank(user.getName())){
				msg.append("["+user.getName()+"]");
				msg.append("用户");
			}
			msg.append("未绑定机构，无法获取权限");
			log.error(msg);
		}

		// admin用户没有挂机构
		if (org == null) {
			return "";
		}
		
		if (roles != null && roles.length > 0) {

			String roleNames = "";

			for (String roleName : roles) {
				roleNames += roleName + ",";
			}

			//省级超级管理员
			if (roleNames.contains("dzjc_sjcjgly")) {
				code = ((String) org.get("xzqm")).substring(0, 2);
				return code;
			}

			//市级超级管理员
			if (roleNames.contains("dzjc_shijcjgly")) {
				code = ((String) org.get("xzqm")).substring(0, 4);
				return code;
			}

			//区县级超级管理员
			if (roleNames.contains("dzjc_qxjcjgly")) {
				code = ((String) org.get("xzqm")).substring(0, 6);
				return code;
			}

			//省本级管理员、市本级管理员、区县级管理员
			if (roleNames.contains("dzjc_sjgly") || roleNames.contains("dzjc_shijgly")
					|| roleNames.contains("dzjc_qxjgly")) {
				code = (String) org.get("xzqm");
				return code;
			}
		}

		//单位角色或没有角色，返回该用户机构ID
		return code;
	}
	
	/**
	 * 根据用户ID查询该用户的机构操作范围 
	 * 1、超级管理员： 
	 * 		a、省超级管理员，可以查看和操作全省下面的机构，并返回行政区码前2位
	 * 		b、市超级管理员，可以查看和操作全市下面的机构，并返回行政区码前4位 
	 * 		c、区超级管理员，可以查看和操作全区下面的机构，并返回行政区码前6位
	 * 
	 * 2、本级管理员：省本级、市本级、区本级，可以查看和操作该本级以下的机构，并返回行政区码
	 * 
	 * 3，如果是优化办，则根据层级，返回前几位机构编码
	 * 
	 * 4、具有单位角色或没有角色，则只能操作用户所在的机构，返回该人员所在的机构ID
	 * 
	 * @param userId
	 * @return
	 */
	public String queryCodeByUserIdTs() {

		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");

		// 获取指定机构详情(用户所在机构)
		Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,
				orgIds[0]);

		// admin用户没有挂机构
		if (org == null) {
			return "";
		}

		String code = org.getId();

		if (roles != null && roles.length > 0) {

			String roleNames = "";

			for (String roleName : roles) {
				roleNames += roleName + ",";
			}

			//省级超级管理员
			if (roleNames.contains("dzjc_sjcjgly")) {
				code = ((String) org.get("xzqm")).substring(0, 2);
				return code;
			}

			//市级超级管理员
			if (roleNames.contains("dzjc_shijcjgly")) {
				code = ((String) org.get("xzqm")).substring(0, 4);
				return code;
			}

			//区县级超级管理员
			if (roleNames.contains("dzjc_qxjcjgly")) {
				code = ((String) org.get("xzqm")).substring(0, 6);
				return code;
			}

			//省本级管理员、市本级管理员、区县级管理员
			if (roleNames.contains("dzjc_sjgly") || roleNames.contains("dzjc_shijgly")
					|| roleNames.contains("dzjc_qxjgly")) {
				code = (String) org.get("xzqm");
				return code;
			}
		}
		String orgCode = org.getCode();
		String orgName = org.getName();
		String orgLevel = "0";
		if ("99".equals(orgCode.substring(2, 4))) {
			orgLevel = "1";
		} else if ("00".equals(orgCode.substring(4, 6)) || "01".equals(orgCode.substring(4, 6))) {
			orgLevel = "2";
		} else {
			orgLevel = "3";
		}
		if(orgName.indexOf("优化办") >= 0){
			if("1".equals(orgLevel)){
				code = orgCode.substring(0, 2);
			}
			else if("2".equals(orgLevel)){
				code = orgCode.substring(0, 4);
			}
			else if("3".equals(orgLevel)){
				code = orgCode.substring(0, 6);
			}
		}
		//单位角色或没有角色，返回该用户机构ID
		return code;
	}
	
	/**获取用户和所关联角色最高机构id
	 * @return
	 */
	public JcOrgView getUserOrgId(){
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
//		userOrgView = DaoFactory.create(JcOrgView.class).selectByID(userOrgId);
		userOrgView = DaoFactory.create(JcOrgView.class).getSession().selectOne("com.chinacreator.dzjc_tongji.JcOrgViewMapper.selectJcOrgViewByID", userOrgId);
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
			if(list != null && list.size() > 0){
				roleOrgView = list.get(0);
			} else {
				roleOrgView = null;
			}
		}
		/*当角色的机构大于用户机构则返回角色机构ID*/
		if(roleOrgView!=null&&roleOrgView.getOrgLevel()<userOrgView.getOrgLevel()){
			return roleOrgView;
		}
		return userOrgView;
	}
}
