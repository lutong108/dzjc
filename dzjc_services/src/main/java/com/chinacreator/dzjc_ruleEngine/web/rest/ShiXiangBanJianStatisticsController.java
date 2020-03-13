package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatistics;
import com.chinacreator.dzjc_ruleEngine.utils.ShiXiangBanJianStatisticsInfoExport;
import com.chinacreator.dzjc_todoStatistics.ViewFlagConstant;
import com.chinacreator.util.DateUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ShiXiangBanJianStatisticsController {
	
	//private Configuration configuration = null;
	
	/**
	 * 查询事项办件数据
	 * @param org_xzqm
	 * @param beginDate
	 * @param endDate
	 * @param parentCode
	 * @param viewFlag
	 * @return
	 */
	@GET
	@Path("getShiXiangBanJianStatisticsInfo")
	public Map<String, Object> getShiXiangBanJianStatisticsInfo(
			@QueryParam(value = "org_xzqm") String org_xzqm,
			@QueryParam(value = "beginDate") String beginDate,
			@QueryParam(value = "endDate") String endDate,
			@QueryParam(value = "parentCode") String parentCode,
			@QueryParam(value = "viewFlag") String viewFlag
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentCode",parentCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		ShiXiangBanJianStatistics shiXiangBanJianStatistics = new ShiXiangBanJianStatistics();
		Date d = new Date();  
		if (shiXiangBanJianStatistics != null) {
			if ("".equals(parentCode)) {
				return null;
			}
			shiXiangBanJianStatistics.setOrg_id(parentCode);
		}
		
		String dataTimeStr="";
		String dataTimeStr1=""; 

		if(!"".equals(beginDate) && beginDate!=null && !"NaN".equals(beginDate) && !"null".equals(beginDate)&& !"undefined".equals(beginDate)){
			dataTimeStr = sdf.format(new Date(Long.parseLong(String
					.valueOf(beginDate))));
			shiXiangBanJianStatistics.setAccept_start_time(dataTimeStr);
		}
		if(!"".equals(endDate) && endDate!=null && !"NaN".equals(endDate) && !"null".equals(endDate)&& !"undefined".equals(beginDate)){
			dataTimeStr1 = sdf.format(new Date(Long.parseLong(String
					.valueOf(endDate))));
			shiXiangBanJianStatistics.setAccept_end_time(dataTimeStr1);
		}
		if(shiXiangBanJianStatistics.getAccept_start_time()==null || "undefined".equals(beginDate)){
			shiXiangBanJianStatistics.setAccept_start_time(DateUtil.getFormatDate(DateUtil.getFirstDate(d.getYear()+1900,d.getMonth()+1)));//日期为null时 设置为当前月第一天
		}
		if (shiXiangBanJianStatistics.getAccept_end_time() == null || "undefined".equals(beginDate)) {
			shiXiangBanJianStatistics.setAccept_end_time(DateUtil.getFormatDate(DateUtil
					.getLastDate(d.getYear() + 1900, d.getMonth() + 1)));// 日期为null时
																			// 设置为当前月最后一天
		}

		List<ShiXiangBanJianStatistics> list = new ArrayList<ShiXiangBanJianStatistics>();
			
		// 判断
		if ("430000000000".equals(parentCode)) {
				// 页面加载，查询全省数据
				list = DaoFactory
						.create(ShiXiangBanJianStatistics.class)
						.getSession()
						.selectList("com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getSXBJStatisticsInfoProvince",shiXiangBanJianStatistics);
			
			map.put("viewFlag", ViewFlagConstant.CITY_FLAG);
		} else if ("439900000000".equals(parentCode)) {
				// 点击链接，查询省本级
				list = DaoFactory
						.create(ShiXiangBanJianStatistics.class)
						.getSession()
						.selectList("com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getSXBJStatisticsInfoProvincialLevel",shiXiangBanJianStatistics);

			map.put("viewFlag", ViewFlagConstant.PROVINCE_FLAG);
		} else if (!"439900000000".equals(parentCode)
				&& "city".equals(viewFlag)
				&& !"430000000000".equals(parentCode)) {
			// 根据城市city查询
			list = DaoFactory
					.create(ShiXiangBanJianStatistics.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getSXBJStatisticsInfoCity",
							shiXiangBanJianStatistics);
			map.put("viewFlag", ViewFlagConstant.AREA_FLAG);
		}else if ("org".equals(viewFlag)) {
			// 根据机构查询
			list = DaoFactory
					.create(ShiXiangBanJianStatistics.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getSXBJStatisticsInfoOrg",
							shiXiangBanJianStatistics);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		}else if(parentCode.substring(6).equals("000000") && !parentCode.equals("433199000000") && (!"01".equals(parentCode.substring(4,6)) || parentCode.equals("433101000000") && "area".equals(viewFlag))){
			//根据区县查询    行政区划 后六位 为000000,并且不为市直及湘西州州直，吉首市行政区划编码为433101000000，属于区县级，特例
			list = DaoFactory
					.create(ShiXiangBanJianStatistics.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getSXBJStatisticsInfoQvXian",
							shiXiangBanJianStatistics);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
			
		}else {
			//根据  市直/区县本级/街道  查数据
			
			//市直行政区划 后六位 为000000
			if(parentCode.substring(6).equals("000000")){
				list = DaoFactory
						.create(ShiXiangBanJianStatistics.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getSXBJStatisticsInfoXZ",
								shiXiangBanJianStatistics);
			}else{
				list = DaoFactory
						.create(ShiXiangBanJianStatistics.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getSXBJStatisticsInfoOthers",
								shiXiangBanJianStatistics);
			}
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} 
		if(list.size()>0){
			
			List<ShiXiangBanJianStatistics> dataList = getSum(list);
			map.put("dataList", dataList);
			map.put("titleName","分类事项办件情况（"+shiXiangBanJianStatistics.getAccept_start_time().replaceAll("-", "/")+"----"+shiXiangBanJianStatistics.getAccept_end_time().replaceAll("-", "/")+"）");
		}
		return map;
	}

	/**
	 * 数据权限查询判断
	 * @return
	 */
	@POST
	@Path("shiXiangBanJianStatistics/getOrgCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getOrgCode(){

		Map<String,Object> result=new HashMap<>();
		try{
			StringBuffer bu=new StringBuffer();
			String code = queryCodeByUserId();
			bu.append(code);
			if(code.length()<12){
				if(code.length()==2){
					result.put("viewFlag",ViewFlagConstant.PROVINCE_FLAG);
				}else if(code.length()==4){
					result.put("viewFlag",ViewFlagConstant.CITY_FLAG);
				}else if(code.length()==6){
					result.put("viewFlag",ViewFlagConstant.AREA_FLAG);
				}
				
				for(int i=0;i<12-code.length();i++){
					bu.append("0");
				}
			}else if(code.length()==12){
				if(code.endsWith(ViewFlagConstant.PROVINCE_CODE)){
					result.put("viewFlag",ViewFlagConstant.PROVINCE_FLAG);
				}else{
					result.put("viewFlag",ViewFlagConstant.AREA_FLAG);
				}
			}else{
				result.put("viewFlag",ViewFlagConstant.ORG_FLAG);
			}
			result.put("parentCode", bu.toString());
			result.put("success",true);

		}catch(Exception e){
			result.put("message", "获取权限失败");
			result.put("success", false);
		}
		return result;
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
	private String queryCodeByUserId() {

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
			
			//用户没有赋权限
			if (roleNames.contains("default")) {
				ShiXiangBanJianStatistics sXBJStatistics = new ShiXiangBanJianStatistics();
				sXBJStatistics.setOrg_id(code);
				sXBJStatistics = DaoFactory
						.create(ShiXiangBanJianStatistics.class)
						.getSession()
						.selectOne(
								"com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatisticsMapper.getDataByCode",
								sXBJStatistics);
				String org_level = sXBJStatistics.getOrg_level();
				//用户绑定在省一级
				if("1".equals(org_level)){
					code = sXBJStatistics.getProvince_code().substring(0,2);
					return code;
				}
				//用户绑定在市一级
				if("2".equals(org_level)){
					code = sXBJStatistics.getCity_code().substring(0,4);
					return code;				
				}
				//用户绑定在区一级
				if("3".equals(org_level)){
					code = sXBJStatistics.getCountry_code().substring(0,6);
					return code;
				}
				//用户绑定在机构
				if("5".equals(org_level)){
					code = sXBJStatistics.getOrg_id();
					return code;
				}
			}
		}
		return code;
	}
	/**
	 * excel表格导出
	 */
	public String expExcel(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag){
		//1,获取当前页面的数据
		Map<String, Object> map= getShiXiangBanJianStatisticsInfo(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		@SuppressWarnings("unchecked")
		List<ShiXiangBanJianStatistics> list=(List<ShiXiangBanJianStatistics>) map.get("dataList");
		String titleName=(String) map.get("titleName");
		
		if(titleName.contains("/")){
			titleName=titleName.replaceAll("/", "-");
		}
		//2,创建excel导出对象
		List<List<ShiXiangBanJianStatistics>> sxbjList = new ArrayList<List<ShiXiangBanJianStatistics>>();
		sxbjList.add(list);
		
		ShiXiangBanJianStatisticsInfoExport shiXiangBanJianStatisticsInfoExport=new ShiXiangBanJianStatisticsInfoExport();
		shiXiangBanJianStatisticsInfoExport.excelExport(titleName, sxbjList);
		
		return titleName;
	}
	
	/**
	 * 计算小计和总计数
	 * @param list
	 * @return
	 */
	private List<ShiXiangBanJianStatistics> getSum(List<ShiXiangBanJianStatistics> list){
	
		int xzxksxsCount = 0;
		int xzcfsxsCount = 0;
		int xzqzsxsCount = 0;
		int xzzssxsCount = 0;
		int xzgfsxsCount = 0;
		int xzjcsxsCount = 0;
		int xzqrsxsCount = 0;
		int xzjlsxsCount = 0;
		int xzcjsxsCount = 0;
		int qtxzqlsxsCount = 0;
		int ggfwsxsCount = 0;
		
		int xzxksjsCount = 0;
		int xzcfsjsCount = 0;
		int xzqzsjsCount = 0;
		int xzzssjsCount = 0;
		int xzgfsjsCount = 0;
		int xzjcsjsCount = 0;
		int xzqrsjsCount = 0;
		int xzjlsjsCount = 0;
		int xzcjsjsCount = 0;
		int qtxzqlsjsCount = 0;
		int ggfwsjsCount = 0;
		
		int xzxkbjsCount = 0;
		int xzcfbjsCount = 0;
		int xzqzbjsCount = 0;
		int xzzsbjsCount = 0;
		int xzgfbjsCount = 0;
		int xzjcbjsCount = 0;
		int xzqrbjsCount = 0;
		int xzjlbjsCount = 0;
		int xzcjbjsCount = 0;
		int qtxzqlbjsCount = 0;
		int ggfwbjsCount = 0;
		
		List<ShiXiangBanJianStatistics> shiXiangBanJianStatisticsList= new ArrayList<ShiXiangBanJianStatistics>();
		for (int i = 0; i < list.size(); i++) {	
			
			ShiXiangBanJianStatistics shiXiangBanJianStatisticsBean = list.get(i);
			
			//事项数 及 小计
			shiXiangBanJianStatisticsBean.setXzxksxs(list.get(i).getXzxksxs());
			shiXiangBanJianStatisticsBean.setXzcfsxs(list.get(i).getXzcfsxs());
			shiXiangBanJianStatisticsBean.setXzqzsxs(list.get(i).getXzqzsxs());
			shiXiangBanJianStatisticsBean.setXzzssxs(list.get(i).getXzzssxs());
			shiXiangBanJianStatisticsBean.setXzgfsxs(list.get(i).getXzgfsxs());
			shiXiangBanJianStatisticsBean.setXzjcsxs(list.get(i).getXzjcsxs());
			shiXiangBanJianStatisticsBean.setXzqrsxs(list.get(i).getXzqrsxs());
			shiXiangBanJianStatisticsBean.setXzjlsxs(list.get(i).getXzjlsxs());
			shiXiangBanJianStatisticsBean.setXzcjsxs(list.get(i).getXzcjsxs());
			shiXiangBanJianStatisticsBean.setQtxzqlsxs(list.get(i).getQtxzqlsxs());
			shiXiangBanJianStatisticsBean.setGgfwsxs(list.get(i).getGgfwsxs());
			
			Integer xzxksxsTemp = Integer.parseInt(list.get(i).getXzxksxs());
			Integer xzcfsxsTemp = Integer.parseInt(list.get(i).getXzcfsxs());
			Integer xzqzsxsTemp = Integer.parseInt(list.get(i).getXzqzsxs());
			Integer xzzssxsTemp = Integer.parseInt(list.get(i).getXzzssxs());
			Integer xzgfsxsTemp = Integer.parseInt(list.get(i).getXzgfsxs());
			Integer xzjcsxsTemp = Integer.parseInt(list.get(i).getXzjcsxs());
			Integer xzqrsxsTemp = Integer.parseInt(list.get(i).getXzqrsxs());
			Integer xzjlsxsTemp = Integer.parseInt(list.get(i).getXzjlsxs());
			Integer xzcjsxsTemp = Integer.parseInt(list.get(i).getXzcjsxs());
			Integer qtxzqlsxsTemp = Integer.parseInt(list.get(i).getQtxzqlsxs());
			Integer ggfwsxsTemp = Integer.parseInt(list.get(i).getGgfwsxs());

			shiXiangBanJianStatisticsBean.setSxscount((xzxksxsTemp + xzcfsxsTemp + xzqzsxsTemp + xzzssxsTemp 
					+ xzgfsxsTemp + xzjcsxsTemp + xzqrsxsTemp + xzjlsxsTemp + xzcjsxsTemp + qtxzqlsxsTemp 
					+ ggfwsxsTemp)+""); 
			
			//收件数 及 小计
			shiXiangBanJianStatisticsBean.setXzxksjs(list.get(i).getXzxksjs());
			shiXiangBanJianStatisticsBean.setXzcfsjs(list.get(i).getXzcfsjs());
			shiXiangBanJianStatisticsBean.setXzqzsjs(list.get(i).getXzqzsjs());
			shiXiangBanJianStatisticsBean.setXzzssjs(list.get(i).getXzzssjs());
			shiXiangBanJianStatisticsBean.setXzgfsjs(list.get(i).getXzgfsjs());
			shiXiangBanJianStatisticsBean.setXzjcsjs(list.get(i).getXzjcsjs());
			shiXiangBanJianStatisticsBean.setXzqrsjs(list.get(i).getXzqrsjs());
			shiXiangBanJianStatisticsBean.setXzjlsjs(list.get(i).getXzjlsjs());
			shiXiangBanJianStatisticsBean.setXzcjsjs(list.get(i).getXzcjsjs());
			shiXiangBanJianStatisticsBean.setQtxzqlsjs(list.get(i).getQtxzqlsjs());
			shiXiangBanJianStatisticsBean.setGgfwsjs(list.get(i).getGgfwsjs());

			Integer xzxksjsTemp = Integer.parseInt(list.get(i).getXzxksjs());
			Integer xzcfsjsTemp = Integer.parseInt(list.get(i).getXzcfsjs());
			Integer xzqzsjsTemp = Integer.parseInt(list.get(i).getXzqzsjs());
			Integer xzzssjsTemp = Integer.parseInt(list.get(i).getXzzssjs());
			Integer xzgfsjsTemp = Integer.parseInt(list.get(i).getXzgfsjs());
			Integer xzjcsjsTemp = Integer.parseInt(list.get(i).getXzjcsjs());
			Integer xzqrsjsTemp = Integer.parseInt(list.get(i).getXzqrsjs());
			Integer xzjlsjsTemp = Integer.parseInt(list.get(i).getXzjlsjs());
			Integer xzcjsjsTemp = Integer.parseInt(list.get(i).getXzcjsjs());
			Integer qtxzqlsjsTemp = Integer.parseInt(list.get(i).getQtxzqlsjs());
			Integer ggfwsjsTemp = Integer.parseInt(list.get(i).getGgfwsjs());

			shiXiangBanJianStatisticsBean.setSjscount((xzxksjsTemp + xzcfsjsTemp + xzqzsjsTemp + xzzssjsTemp 
					+ xzgfsjsTemp + xzjcsjsTemp + xzqrsjsTemp + xzjlsjsTemp + xzcjsjsTemp + qtxzqlsjsTemp 
					+ ggfwsjsTemp)+""); 
			
			//办结数 及 小计
			shiXiangBanJianStatisticsBean.setXzxkbjs(list.get(i).getXzxkbjs());
			shiXiangBanJianStatisticsBean.setXzcfbjs(list.get(i).getXzcfbjs());
			shiXiangBanJianStatisticsBean.setXzqzbjs(list.get(i).getXzqzbjs());
			shiXiangBanJianStatisticsBean.setXzzsbjs(list.get(i).getXzzsbjs());
			shiXiangBanJianStatisticsBean.setXzgfbjs(list.get(i).getXzgfbjs());
			shiXiangBanJianStatisticsBean.setXzjcbjs(list.get(i).getXzjcbjs());
			shiXiangBanJianStatisticsBean.setXzqrbjs(list.get(i).getXzqrbjs());
			shiXiangBanJianStatisticsBean.setXzjlbjs(list.get(i).getXzjlbjs());
			shiXiangBanJianStatisticsBean.setXzcjbjs(list.get(i).getXzcjbjs());
			shiXiangBanJianStatisticsBean.setQtxzqlbjs(list.get(i).getQtxzqlbjs());
			shiXiangBanJianStatisticsBean.setGgfwbjs(list.get(i).getGgfwbjs());

			Integer xzxkbjsTemp = Integer.parseInt(list.get(i).getXzxkbjs());
			Integer xzcfbjsTemp = Integer.parseInt(list.get(i).getXzcfbjs());
			Integer xzqzbjsTemp = Integer.parseInt(list.get(i).getXzqzbjs());
			Integer xzzsbjsTemp = Integer.parseInt(list.get(i).getXzzsbjs());
			Integer xzgfbjsTemp = Integer.parseInt(list.get(i).getXzgfbjs());
			Integer xzjcbjsTemp = Integer.parseInt(list.get(i).getXzjcbjs());
			Integer xzqrbjsTemp = Integer.parseInt(list.get(i).getXzqrbjs());
			Integer xzjlbjsTemp = Integer.parseInt(list.get(i).getXzjlbjs());
			Integer xzcjbjsTemp = Integer.parseInt(list.get(i).getXzcjbjs());
			Integer qtxzqlbjsTemp = Integer.parseInt(list.get(i).getQtxzqlbjs());
			Integer ggfwbjsTemp = Integer.parseInt(list.get(i).getGgfwbjs());

			shiXiangBanJianStatisticsBean.setBjscount((xzxkbjsTemp + xzcfbjsTemp + xzqzbjsTemp + xzzsbjsTemp 
					+ xzgfbjsTemp + xzjcbjsTemp + xzqrbjsTemp + xzjlbjsTemp + xzcjbjsTemp + qtxzqlbjsTemp 
					+ ggfwbjsTemp)+""); 
			
			
			shiXiangBanJianStatisticsList.add(shiXiangBanJianStatisticsBean);
			
			//计算列数据总数
			xzxksxsCount += Integer.parseInt(list.get(i).getXzxksxs());
			xzcfsxsCount += Integer.parseInt(list.get(i).getXzcfsxs());
			xzqzsxsCount += Integer.parseInt(list.get(i).getXzqzsxs());
			xzzssxsCount += Integer.parseInt(list.get(i).getXzzssxs());
			xzgfsxsCount += Integer.parseInt(list.get(i).getXzgfsxs());
			xzjcsxsCount += Integer.parseInt(list.get(i).getXzjcsxs());
			xzqrsxsCount += Integer.parseInt(list.get(i).getXzqrsxs());
			xzjlsxsCount += Integer.parseInt(list.get(i).getXzjlsxs());
			xzcjsxsCount += Integer.parseInt(list.get(i).getXzcjsxs());
			qtxzqlsxsCount += Integer.parseInt(list.get(i).getQtxzqlsxs());
			ggfwsxsCount += Integer.parseInt(list.get(i).getGgfwsxs());
			
			xzxksjsCount += Integer.parseInt(list.get(i).getXzxksjs());
			xzcfsjsCount += Integer.parseInt(list.get(i).getXzcfsjs());
			xzqzsjsCount += Integer.parseInt(list.get(i).getXzqzsjs());
			xzzssjsCount += Integer.parseInt(list.get(i).getXzzssjs());
			xzgfsjsCount += Integer.parseInt(list.get(i).getXzgfsjs());
			xzjcsjsCount += Integer.parseInt(list.get(i).getXzjcsjs());
			xzqrsjsCount += Integer.parseInt(list.get(i).getXzqrsjs());
			xzjlsjsCount += Integer.parseInt(list.get(i).getXzjlsjs());
			xzcjsjsCount += Integer.parseInt(list.get(i).getXzcjsjs());
			qtxzqlsjsCount += Integer.parseInt(list.get(i).getQtxzqlsjs());
			ggfwsjsCount += Integer.parseInt(list.get(i).getGgfwsjs());
			
			xzxkbjsCount += Integer.parseInt(list.get(i).getXzxkbjs());
			xzcfbjsCount += Integer.parseInt(list.get(i).getXzcfbjs());
			xzqzbjsCount += Integer.parseInt(list.get(i).getXzqzbjs());
			xzzsbjsCount += Integer.parseInt(list.get(i).getXzzsbjs());
			xzgfbjsCount += Integer.parseInt(list.get(i).getXzgfbjs());
			xzjcbjsCount += Integer.parseInt(list.get(i).getXzjcbjs());
			xzqrbjsCount += Integer.parseInt(list.get(i).getXzqrbjs());
			xzjlbjsCount += Integer.parseInt(list.get(i).getXzjlbjs());
			xzcjbjsCount += Integer.parseInt(list.get(i).getXzcjbjs());
			qtxzqlbjsCount += Integer.parseInt(list.get(i).getQtxzqlbjs());
			ggfwbjsCount += Integer.parseInt(list.get(i).getGgfwbjs());
		}
		
		//计算总计行数据
		ShiXiangBanJianStatistics fenLeiTongJiBean = new ShiXiangBanJianStatistics();
		fenLeiTongJiBean.setArea_name("合计");
		
		fenLeiTongJiBean.setXzxksxs(xzxksxsCount+"");
		fenLeiTongJiBean.setXzcfsxs(xzcfsxsCount+"");
		fenLeiTongJiBean.setXzqzsxs(xzqzsxsCount+"");
		fenLeiTongJiBean.setXzzssxs(xzzssxsCount+"");
		fenLeiTongJiBean.setXzgfsxs(xzgfsxsCount+"");
		fenLeiTongJiBean.setXzjcsxs(xzjcsxsCount+"");
		fenLeiTongJiBean.setXzqrsxs(xzqrsxsCount+"");
		fenLeiTongJiBean.setXzjlsxs(xzjlsxsCount+"");
		fenLeiTongJiBean.setXzcjsxs(xzcjsxsCount+"");
		fenLeiTongJiBean.setQtxzqlsxs(qtxzqlsxsCount+"");
		fenLeiTongJiBean.setGgfwsxs(ggfwsxsCount+"");
		fenLeiTongJiBean.setSxscount((xzxksxsCount + xzcfsxsCount + xzqzsxsCount + xzzssxsCount + xzgfsxsCount 
				+ xzjcsxsCount + xzqrsxsCount + xzjlsxsCount + xzcjsxsCount + qtxzqlsxsCount + ggfwsxsCount)+"");
		
		fenLeiTongJiBean.setXzxksjs(xzxksjsCount+"");
		fenLeiTongJiBean.setXzcfsjs(xzcfsjsCount+"");
		fenLeiTongJiBean.setXzqzsjs(xzqzsjsCount+"");
		fenLeiTongJiBean.setXzzssjs(xzzssjsCount+"");
		fenLeiTongJiBean.setXzgfsjs(xzgfsjsCount+"");
		fenLeiTongJiBean.setXzjcsjs(xzjcsjsCount+"");
		fenLeiTongJiBean.setXzqrsjs(xzqrsjsCount+"");
		fenLeiTongJiBean.setXzjlsjs(xzjlsjsCount+"");
		fenLeiTongJiBean.setXzcjsjs(xzcjsjsCount+"");
		fenLeiTongJiBean.setQtxzqlsjs(qtxzqlsjsCount+"");
		fenLeiTongJiBean.setGgfwsjs(ggfwsjsCount+"");
		fenLeiTongJiBean.setSjscount((xzxksjsCount + xzcfsjsCount + xzqzsjsCount + xzzssjsCount + xzgfsjsCount 
			  + xzjcsjsCount + xzqrsjsCount + xzjlsjsCount + xzcjsjsCount + qtxzqlsjsCount + ggfwsjsCount)+"");
		
		fenLeiTongJiBean.setXzxkbjs(xzxkbjsCount+"");
		fenLeiTongJiBean.setXzcfbjs(xzcfbjsCount+"");
		fenLeiTongJiBean.setXzqzbjs(xzqzbjsCount+"");
		fenLeiTongJiBean.setXzzsbjs(xzzsbjsCount+"");
		fenLeiTongJiBean.setXzgfbjs(xzgfbjsCount+"");
		fenLeiTongJiBean.setXzjcbjs(xzjcbjsCount+"");
		fenLeiTongJiBean.setXzqrbjs(xzqrbjsCount+"");
		fenLeiTongJiBean.setXzjlbjs(xzjlbjsCount+"");
		fenLeiTongJiBean.setXzcjbjs(xzcjbjsCount+"");
		fenLeiTongJiBean.setQtxzqlbjs(qtxzqlbjsCount+"");
		fenLeiTongJiBean.setGgfwbjs(ggfwbjsCount+"");
		fenLeiTongJiBean.setBjscount((xzxkbjsCount + xzcfbjsCount + xzqzbjsCount + xzzsbjsCount + xzgfbjsCount 
			  + xzjcbjsCount + xzqrbjsCount + xzjlbjsCount + xzcjbjsCount + qtxzqlbjsCount + ggfwbjsCount)+"");
		
		//将“合计”行加入集合
		shiXiangBanJianStatisticsList.add(fenLeiTongJiBean);
		
		return shiXiangBanJianStatisticsList;
	}
	
}
