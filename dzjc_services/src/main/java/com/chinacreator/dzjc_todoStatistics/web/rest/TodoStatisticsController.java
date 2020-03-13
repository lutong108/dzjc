package com.chinacreator.dzjc_todoStatistics.web.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.StringUtil;
import com.chinacreator.dzjc_ruleEngine.servlet.JCSumSuperviseInfoAllServlet;
import com.chinacreator.dzjc_ruleEngine.utils.DownLoadUtil;
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoAllWordExport;
import com.chinacreator.dzjc_ruleEngine.web.rest.JCSumSuperviseInfoAllController;
import com.chinacreator.dzjc_todoStatistics.EgovernmentListBean;
import com.chinacreator.dzjc_todoStatistics.EinstanceListBean;
import com.chinacreator.dzjc_todoStatistics.ViewFlagConstant;
import com.chinacreator.dzjc_todoStatistics.service.TodoStatisticsService;
import com.chinacreator.util.FilesUtil;
import com.chinacreator.util.RoleUtil;
import com.chinacreator.util.SystemPropertiesInstance;

import freemarker.template.Configuration;

@Controller
@Path("dzjc_todoStatistics/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TodoStatisticsController {
	private Configuration configuration = null;
	private static final Logger logger = LoggerFactory
			.getLogger(TodoStatisticsController.class);
	private final static String SYSTEM_AREANAME_VALUE = SystemPropertiesInstance
			.getInstance().getSystem_areaName();
	
	@Autowired
	private TodoStatisticsService todoStatisticsService;
	
	/**
	 * 湖南省统计
	 * @param params
	 * @return
	 */
	@POST
	@Path("getTodoStatisticsList")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getTodoStatisticsList(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			if(params.get("selectType")!=null){
				result=todoStatisticsService.getTodoStatisticsList(params);
				
				result.put("viewFlag",ViewFlagConstant.CITY_FLAG);
				
				Map<String,Object> result1=new HashMap<>();
				result1=getTodoStatisticsListAll(params);
				result.put("accCollectionAll", result1.get("accCollectionAll"));
				result.put("accWorkAll", result1.get("accWorkAll"));
				result.put("accRedAll", result1.get("accRedAll"));
				result.put("accYellowAll", result1.get("accYellowAll"));
				result.put("accComplaintAll", result1.get("accComplaintAll"));
				result.put("accComplaintReplyAll", result1.get("accComplaintReplyAll"));
				result.put("accConsultAll", result1.get("accConsultAll"));
				result.put("accConsultReplyAll", result1.get("accConsultReplyAll"));
				result.put("accbjPercentAll", result1.get("accbjPercentAll"));
				result.put("acctsPercentAll", result1.get("acctsPercentAll"));
				
				result.put("areaNameAll", result1.get("areaNameAll"));
				
				result.put("success",true);
			}else{
				result.put("message", "参数传入失败");
				result.put("success", false);
			}
		}catch(Exception e){
			logger.error("数据统计失败"+e.getMessage(), e);
			result.put("message", "数据统计失败");
			result.put("success", false);
		}
		result.put("parentCode", parentCode);
		result.put("welcome", "欢迎使用"+SYSTEM_AREANAME_VALUE+"政务服务一体化平台行政效能电子监管系统!");
		return result;
	}
	
	/**
	 * 省本级统计
	 * @param params
	 * @return
	 */
	@POST
	@Path("getLevelStatisticsList")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getProvincialLevelStatisticsList(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			System.out.println(params.get("selectType"));
			if(params.get("selectType")!=null&&params.get("viewFlag")!=null){
				result=todoStatisticsService.getLevelStatisticsList(params);
				result.put("viewFlag",ViewFlagConstant.PROVINCE_FLAG);
				
				Map<String,Object> result1=new HashMap<>();
				result1=getProvincialLevelStatisticsListAll(params);
				
				result.put("accCollectionAll", result1.get("accCollectionAll"));
				result.put("accWorkAll", result1.get("accWorkAll"));
				result.put("accRedAll", result1.get("accRedAll"));
				result.put("accYellowAll", result1.get("accYellowAll"));
				result.put("accComplaintAll", result1.get("accComplaintAll"));
				result.put("accComplaintReplyAll", result1.get("accComplaintReplyAll"));
				result.put("accConsultAll", result1.get("accConsultAll"));
				result.put("accConsultReplyAll", result1.get("accConsultReplyAll"));
				result.put("accbjPercentAll", result1.get("accbjPercentAll"));
				result.put("acctsPercentAll", result1.get("acctsPercentAll"));
				result.put("areaNameAll", result1.get("areaNameAll"));
				
				result.put("success",true);
				
			}else{
				result.put("message", "参数传入失败");
				result.put("success", false);
			}
		}catch(Exception e){
			logger.error("数据统计失败"+e.getMessage(), e);
			result.put("message", "数据统计失败");
			result.put("success", false);
		}
		result.put("parentCode", parentCode);
		result.put("welcome", "欢迎使用"+SYSTEM_AREANAME_VALUE+"政务服务一体化平台行政效能电子监管系统!");
		return result;
	}
	
	/**
	 * 市级查询
	 * @param params
	 * @return
	 */
	@POST
	@Path("getCityLevelStatisticsList")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getCityLevelStatisticsList(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			if(params.get("selectType")!=null){
				result=todoStatisticsService.getCityLevelStatisticsList(params);
				result.put("viewFlag",ViewFlagConstant.AREA_FLAG);
				
				Map<String,Object> result1=new HashMap<>();
				result1=getCityLevelStatisticsListAll(params);
				
				result.put("accCollectionAll", result1.get("accCollectionAll"));
				result.put("accWorkAll", result1.get("accWorkAll"));
				result.put("accRedAll", result1.get("accRedAll"));
				result.put("accYellowAll", result1.get("accYellowAll"));
				result.put("accComplaintAll", result1.get("accComplaintAll"));
				result.put("accComplaintReplyAll", result1.get("accComplaintReplyAll"));
				result.put("accConsultAll", result1.get("accConsultAll"));
				result.put("accConsultReplyAll", result1.get("accConsultReplyAll"));
				result.put("accbjPercentAll", result1.get("accbjPercentAll"));
				result.put("acctsPercentAll", result1.get("acctsPercentAll"));
				result.put("areaNameAll", result1.get("areaNameAll"));
				
				result.put("success",true);
				
			}else{
				result.put("message", "参数传入失败");
				result.put("success", false);
			}
		}catch(Exception e){
			logger.error("数据统计失败"+e.getMessage(), e);
			result.put("message", "数据统计失败");
			result.put("success", false);
		}
		result.put("parentCode", parentCode);
		result.put("welcome", "欢迎使用"+SYSTEM_AREANAME_VALUE+"政务服务一体化平台行政效能电子监管系统!");
		return result;
	}
	
	/**
	 * 区县级查询
	 * @param params
	 * @return
	 */
	@POST
	@Path("getCityAreaStatisticsList")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getCityAreaStatisticsList(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			if(params.get("selectType")!=null){
				result=todoStatisticsService.getCityAreaStatisticsList(params);
				result.put("viewFlag",ViewFlagConstant.ORG_FLAG);
				
				Map<String,Object> result1=new HashMap<>();
				result1=getCityAreaStatisticsListAll(params);
				result.put("accCollectionAll", result1.get("accCollectionAll"));
				result.put("accWorkAll", result1.get("accWorkAll"));
				result.put("accRedAll", result1.get("accRedAll"));
				result.put("accYellowAll", result1.get("accYellowAll"));
				result.put("accComplaintAll", result1.get("accComplaintAll"));
				result.put("accComplaintReplyAll", result1.get("accComplaintReplyAll"));
				result.put("accConsultAll", result1.get("accConsultAll"));
				result.put("accConsultReplyAll", result1.get("accConsultReplyAll"));
				result.put("accbjPercentAll", result1.get("accbjPercentAll"));
				result.put("acctsPercentAll", result1.get("acctsPercentAll"));
				result.put("areaNameAll", result1.get("areaNameAll"));
				result.put("success",true);
			}else{
				result.put("message", "参数传入失败");
				result.put("success", false);
			}
		}catch(Exception e){
			logger.error("数据统计失败"+e.getMessage(), e);
			result.put("message", "数据统计失败");
			result.put("success", false);
		}
		result.put("parentCode", parentCode);
		result.put("welcome", "欢迎使用"+SYSTEM_AREANAME_VALUE+"政务服务一体化平台行政效能电子监管系统!");
		return result;
	}
	
	/**
	 * 机构信息查询
	 * @param params
	 * @return
	 */
	@POST
	@Path("getCityOrgStatisticsList")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getCityOrgStatisticsList(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			if(params.get("selectType")!=null){
				result=todoStatisticsService.getCityOrgStatisticsList(params);
				result.put("viewFlag",ViewFlagConstant.ORG_FLAG);
				
				Map<String,Object> result1=new HashMap<>();
				result1=getCityOrgStatisticsListAll(params);
				result.put("accCollectionAll", result1.get("accCollectionAll"));
				result.put("accWorkAll", result1.get("accWorkAll"));
				result.put("accRedAll", result1.get("accRedAll"));
				result.put("accYellowAll", result1.get("accYellowAll"));
				result.put("accComplaintAll", result1.get("accComplaintAll"));
				result.put("accComplaintReplyAll", result1.get("accComplaintReplyAll"));
				result.put("accConsultAll", result1.get("accConsultAll"));
				result.put("accConsultReplyAll", result1.get("accConsultReplyAll"));
				result.put("accbjPercentAll", result1.get("accbjPercentAll"));
				result.put("acctsPercentAll", result1.get("acctsPercentAll"));
				result.put("areaNameAll", result1.get("areaNameAll"));
				result.put("success",true);
			}else{
				result.put("message", "参数传入失败");
				result.put("success", false);
			}
		}catch(Exception e){
			logger.error("数据统计失败"+e.getMessage(), e);
			result.put("message", "数据统计失败");
			result.put("success", false);
		}
		result.put("parentCode", parentCode);
		result.put("welcome", "欢迎使用"+SYSTEM_AREANAME_VALUE+"政务服务一体化平台行政效能电子监管系统!");
		return result;
	}
	
	/**
	 * 数据权限查询判断
	 * @return
	 */
	@POST
	@Path("getOrgCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getOrgCode(){
		Map<String,Object> result=new HashMap<>();
		try{
			String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
			User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
			String[] roles = (String[]) user.get("roles");
			String[] orgIds = (String[]) user.get("orgIds");
			// 获取指定机构详情(用户所在机构)
			Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,orgIds[0]);
			String orgCode = (String)org.get("xzqm");
			int index = -1;
			boolean isRoles = true;
			if (roles != null && roles.length > 0&&StringUtils.isNotBlank(orgCode)) {
				String roleNames = "";
				for (String roleName : roles) {
					roleNames += roleName + ",";
				}
				if(StringUtils.isNotBlank(orgCode)){
					char chars[] = orgCode.toCharArray();
					index = chars.length;
					for (int i = chars.length; i > 0; i--) {
						index = i;
						if('0' != chars[i-1]){
							break;
						}
					}
				}
				if (roleNames.contains("dzjc_sjcjgly")) {//省级超级管理员
					orgCode = ((String) org.get("xzqm")).substring(0, 2);
				}else if (roleNames.contains("dzjc_shijcjgly")) {//市级超级管理员
					orgCode = ((String) org.get("xzqm")).substring(0, 4);
				}else if (roleNames.contains("dzjc_qxjcjgly")) {//区县级超级管理员
					orgCode = ((String) org.get("xzqm")).substring(0, 6);
				}else if (roleNames.contains("dzjc_sjgly") || roleNames.contains("dzjc_shijgly")|| roleNames.contains("dzjc_qxjgly")) {
					orgCode = (String) org.get("xzqm");
				}else{
					isRoles = false;
				}
			}
			StringBuffer br = new StringBuffer(orgCode);
			if(orgCode.length()<=12&&isRoles){
				if(orgCode.length()==2){
					result.put("viewFlag",ViewFlagConstant.PROVINCE_FLAG);
				}else if(orgCode.length()==4||index==4){
					result.put("viewFlag",ViewFlagConstant.CITY_FLAG);
				}else if(orgCode.length()==6||index==6){
					result.put("viewFlag",ViewFlagConstant.AREA_FLAG);
				}
				if(orgCode.length()<12){
					for(int i=0;i<12-orgCode.length();i++){
						br.append("0");
					}
				}else{
					br = new StringBuffer((String)org.get("xzqm"));
				}
			}else if(!isRoles){
				br = new StringBuffer(org.getId());
				result.put("viewFlag",ViewFlagConstant.ORG_FLAG);
			}
			result.put("parentCode", br);
			result.put("success",true);

		}catch(Exception e){
			logger.error("获取权限失败"+e.getMessage(), e);
			result.put("message", "获取权限失败");
			result.put("success", false);
		}
		return result;
	}
	
	/**
	 * 导出excel文件
	 */
	@GET
	@Path("expExcel")
	public void expExcel(@Context HttpServletRequest request,
			@Context HttpServletResponse response){
		try{
			
			String queryCondition = request.getParameter("queryCondition");
		
			Map<String,String> params=new HashMap<>();
			if(!"undefined".equals(queryCondition)){
				JSONObject jsonobject = JSONObject.parseObject(queryCondition);
				for (Object map : jsonobject.entrySet()){
					params.put(((Map.Entry)map).getKey().toString(), ((Map.Entry)map).getValue().toString());  
		        } 
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
				String startdDate = params.get("startDate");
				String endDate = params.get("endDate");
				if(startdDate!=null &&  !"".equals(startdDate) &&  endDate!=null &&  !"".equals(endDate) ){
					startdDate = format.format(new Date(Long.parseLong(params.get("startDate"))));
					
					endDate = format.format(new Date(Long.parseLong(params.get("endDate"))));
				}
				
				String year=params.get("year");
				String month=params.get("month");
				String titleNames="";
				if(year!=null && !"".equals(year) && month!=null && !"".equals(month)  ){
					titleNames="全部办件查询统计"+"-"+year+"年"+month+"月";
				}else if(startdDate!=null &&  !"".equals(startdDate) &&  endDate!=null &&  !"".equals(endDate) ){
					titleNames="全部办件查询统计"+"-"+startdDate+"——"+endDate;
				}else{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
					String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
					titleNames= "全部办件查询统计"+"-"+date;
			}
			String wordtype=request.getParameter("wordtype");
			String expType=request.getParameter("expType");
			Map<String,Object> result=new HashMap<>();
			if(expType.endsWith(ViewFlagConstant.CityArea_STATISTICSLIST)){
				result=todoStatisticsService.getCityAreaStatisticsList(params);
			}else if(expType.endsWith(ViewFlagConstant.CityLevel_STATISTICSLIST)){
				result=todoStatisticsService.getCityLevelStatisticsList(params);
			}else if(expType.endsWith(ViewFlagConstant.CityOrg_STATISTICSLIST)){
				result=todoStatisticsService.getCityOrgStatisticsList(params);
			}else if(expType.endsWith(ViewFlagConstant.Level_STATISTICSLIST)){
				result=todoStatisticsService.getLevelStatisticsList(params);
			}else if(expType.endsWith(ViewFlagConstant.Todo_STATISTICSLIST)){
				result=todoStatisticsService.getTodoStatisticsList(params);
			}
			
			List<EgovernmentListBean> listEgo=(List<EgovernmentListBean>)result.get("list");
			
			if("wordtype".equals(wordtype)){
				List<List<EgovernmentListBean>> jclist=new ArrayList<List<EgovernmentListBean>>();
				jclist.add(listEgo);
					//办件统计word导出
				configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				
				// 创建word导出对象
				JCSumSuperviseInfoAllWordExport jCSumSuperviseInfoAllWordExport=new JCSumSuperviseInfoAllWordExport();
				String titleName= jCSumSuperviseInfoAllWordExport.createWordbjty(configuration, titleNames, jclist);
					// 用户下载
				 //JCSumSuperviseInfoAllServlet jc=new JCSumSuperviseInfoAllServlet();

				DownLoadUtil.download(titleName, response, request);

					// 删除临时文件
				FilesUtil.deleteFile(titleName);
			}else{
				//Excel导出
				creatorExcelValue(titleNames,response,listEgo);
			}
		}catch(Exception e){
			
		}
		
		
	}
	
	/**
	 * 查询用户角色
	 * @return
	 */
	@GET
	@Path("todoStatisticsList/selectRole")
	public String selectDetail(){
		String role = null;
		try {
			// 查询当前登录用户编号(行政区码)
			role =queryCodeByUserId().get("role");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	
	/**TodoStatisticsController
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
	public Map<String,String> queryCodeByUserId() {
		
		Map<String,String> map=new HashMap<String,String>();
		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

		User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
		String realName=user.getRealname();
		map.put("realName", realName);
		String[] roles = (String[]) user.get("roles");
		String[] orgIds = (String[]) user.get("orgIds");
		Organization org = null;

		// 获取指定机构详情(用户所在机构)
		if(orgIds != null&&orgIds.length>0){
			org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,orgIds[0]);
		}

		// admin用户没有挂机构
		if (org == null) {
			map.put("role", "2");
			return map;
		}

		//String code = org.getId();
		String role="";

		if (roles != null && roles.length > 0) {

			String roleNames = "";

			for (String roleName : roles) {
				roleNames += roleName + ",";
			}

			//省级超级管理员
			if (roleNames.contains("dzjc_sjcjgly")) {
				map.put("role", "1");
				return map;
			}

			//市级超级管理员
			if (roleNames.contains("dzjc_shijcjgly")) {
				map.put("role", "1");
				return map;
			}

			//区县级超级管理员
			if (roleNames.contains("dzjc_qxjcjgly")) {
				map.put("role", "1");
				return map;
			}

			//省本级管理员、市本级管理员、区县级管理员
			if (roleNames.contains("dzjc_sjgly") || roleNames.contains("dzjc_shijgly")
					|| roleNames.contains("dzjc_qxjgly")) {
				map.put("role", "1");
				return map;
			}
		}

		//单位角色或没有角色，返回该用户机构ID
		map.put("role", "0");
		return map;
	}
	
	
	
	/**
	 * 湖南省统计所有
	 * @param params
	 * @return
	 */
	@POST
	@Path("getTodoStatisticsListAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getTodoStatisticsListAll(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			result=todoStatisticsService.getTodoStatisticsListAll(params);
		}catch(Exception e){
			logger.error("数据统计所有失败"+e.getMessage(), e);
			result.put("message", "数据统计所有失败");
			result.put("success", false);
		}
		return result;
	}
	
	/**
	 * 省本级统计所有
	 * @param params
	 * @return
	 */
	@POST
	@Path("getLevelStatisticsListAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getProvincialLevelStatisticsListAll(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			
			result=todoStatisticsService.getLevelStatisticsListAll(params);
			
		}catch(Exception e){
			logger.error("数据统计所有失败"+e.getMessage(), e);
			result.put("message", "数据统计所有失败");
			result.put("success", false);
		}
		return result;
	}
	
	
	/**
	 * 市级查询所有
	 * @param params
	 * @return
	 */
	@POST
	@Path("getCityLevelStatisticsListAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getCityLevelStatisticsListAll(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			
			result=todoStatisticsService.getCityLevelStatisticsListAll(params);
		
		}catch(Exception e){
			logger.error("数据统计所有失败"+e.getMessage(), e);
			result.put("message", "数据统计所有失败");
			result.put("success", false);
		}
		return result;
	}
	
	/**
	 * 区县级查询所有
	 * @param params
	 * @return
	 */
	@POST
	@Path("getCityAreaStatisticsListAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getCityAreaStatisticsListAll(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			result=todoStatisticsService.getCityAreaStatisticsListAll(params);
		}catch(Exception e){
			logger.error("数据统计所有失败"+e.getMessage(), e);
			result.put("message", "数据统计所有失败");
			result.put("success", false);
		}
		return result;
	}
	
	/**
	 * 机构信息查询所有
	 * @param params
	 * @return
	 */
	@POST
	@Path("getCityOrgStatisticsListAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getCityOrgStatisticsListAll(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			result=todoStatisticsService.getCityOrgStatisticsListAll(params);
		}catch(Exception e){
			logger.error("数据统计失败"+e.getMessage(), e);
			result.put("message", "数据统计失败");
			result.put("success", false);
		}
		return result;
	}
	
	
	/**
	 * 获取用户姓名
	 * @return
	 */
	@POST
	@Path("getUserName")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserName(){
		Map<String,String> map=new HashMap<>();
		try{
			map=queryCodeByUserId();
			String userName=map.get("realName");
			return userName;
		}catch(Exception e){
			logger.error("获取权限失败"+e.getMessage(), e);
			return "";
		}
	}
	
	/**
	 * 三级监察最末级的监察信息
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("EinstanceListBean")
	public Page<EinstanceListBean> getListByPage(
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
		EinstanceListBean entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EinstanceListBean.class) : new EinstanceListBean();

		return DaoFactory.create(EinstanceListBean.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 省本级机构统计
	 * @param params
	 * @return
	 */
	@POST
	@Path("getInstanceStatisticsList")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getInstanceStatisticsList(@RequestBody Map<String,String> params){
		Map<String,Object> result=new HashMap<>();
		String parentCode=params.get("parentCode");
		
		try{
			if(params.get("selectType")!=null){
				result.put("viewFlag",ViewFlagConstant.CITY_FLAG);
				Map<String,Object> result1=new HashMap<>();
				result1=todoStatisticsService.getInstanceStatisticsListAll(params);
				result.put("accCollectionAll", result1.get("accCollectionAll"));
				result.put("accWorkAll", result1.get("accWorkAll"));
				result.put("accRedAll", result1.get("accRedAll"));
				result.put("accYellowAll", result1.get("accYellowAll"));
				result.put("accComplaintAll", result1.get("accComplaintAll"));
				result.put("accComplaintReplyAll", result1.get("accComplaintReplyAll"));
				result.put("accConsultAll", result1.get("accConsultAll"));
				result.put("accConsultReplyAll", result1.get("accConsultReplyAll"));
				result.put("accbjPercentAll", result1.get("accbjPercentAll"));
				result.put("acctsPercentAll", result1.get("acctsPercentAll"));
				result.put("success",true);
			}else{
				result.put("message", "参数传入失败");
				result.put("success", false);
			}
		}catch(Exception e){
			logger.error("数据统计失败"+e.getMessage(), e);
			result.put("message", "数据统计失败");
			result.put("success", false);
		}
		result.put("parentCode", parentCode);
		return result;
	}
	/**
	 * Excel导出
	 * @param titleNames
	 * @param response
	 * @param listEgo
	 */
	public static void creatorExcelValue(String titleNames,HttpServletResponse response,List<EgovernmentListBean> listEgo){
		try {
			String[] coloumItems = { "行政区划", "收件数(件)","办结数(件)", "办结率(%)", "提速率(%)", "办件黄牌数(张)", "办件红牌数(张)", "投诉黄牌数(张)", "投诉红牌数(张)", "咨询黄牌数(张)", "咨询红牌数(张)", "投诉数(件)","投诉回复数(件)","咨询数(件)","咨询回复数(件)" };
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
			sheet.setColumnWidth((short) 0, (short) 20 * 256);// 
			sheet.setColumnWidth((short) 1, (short) 10 * 256);// 
			sheet.setColumnWidth((short) 2, (short) 10 * 256);// 
			sheet.setColumnWidth((short) 3, (short) 10 * 256);// 
			sheet.setColumnWidth((short) 4, (short) 10 * 256);//
			sheet.setColumnWidth((short) 5, (short) 10 * 256);// 
			sheet.setColumnWidth((short) 6, (short) 10 * 256);//
			sheet.setColumnWidth((short) 7, (short) 10 * 256);//
			sheet.setColumnWidth((short) 8, (short) 10 * 256);//
			sheet.setColumnWidth((short) 9, (short) 10 * 256);//
			sheet.setColumnWidth((short) 10, (short) 10 * 256);//
			sheet.setColumnWidth((short) 11, (short) 10 * 256);//
			sheet.setColumnWidth((short) 12, (short) 10 * 256);//
			sheet.setColumnWidth((short) 13, (short) 10 * 256);//
			sheet.setColumnWidth((short) 14, (short) 10 * 256);//
			
	        
	        //1
	        Font headfonts = workbook.createFont();
	        headfonts.setFontHeightInPoints((short) 20);// 字体大小
	        //2
	        Font headfont_ = workbook.createFont();
	        headfont_.setFontHeightInPoints((short) 15);// 字体大小
	        //3
	        Font headfont = workbook.createFont();
	        headfont.setFontName("宋体");
	        headfont.setFontHeightInPoints((short) 10);// 字体大小
	        headfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗
	        
	        
	        //1
	        CellStyle styles = workbook.createCellStyle();
	        styles.setFont(headfonts);
	        styles.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
	        styles.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
	        styles.setBorderRight(CellStyle.BORDER_THIN);
	        styles.setBorderTop(CellStyle.BORDER_THIN);
	        styles.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
	        styles.setBorderRight(CellStyle.BORDER_THIN);//右边框    
	        
	        //2
	        CellStyle style_ = workbook.createCellStyle();
	        style_.setFont(headfont_);
	        style_.setAlignment(CellStyle.ALIGN_RIGHT);// 左右居中
	        style_.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
	        style_.setBorderRight(CellStyle.BORDER_THIN);
	        style_.setBorderTop(CellStyle.BORDER_THIN);
	        /* styles.setBorderBottom(CellStyle.BORDER_THIN); */// 设置单元格的边框为粗体
	        style_.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
	        style_.setBorderRight(CellStyle.BORDER_THIN);//右边框    
	        
	        //3
	        CellStyle headStyle = workbook.createCellStyle(); // 表头第一列的样式			
	        headStyle.setFont(headfont);
	        headStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
	        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
	        headStyle.setWrapText(true);
	        headStyle.setBorderRight(CellStyle.BORDER_THIN);
	        headStyle.setBorderTop(CellStyle.BORDER_THIN);
	      
	        //4
	        CellStyle style = workbook.createCellStyle();
	        style.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
	        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
	        style.setBorderRight(CellStyle.BORDER_THIN);
	        style.setBorderTop(CellStyle.BORDER_THIN);
	        style.setBorderBottom(CellStyle.BORDER_THIN); // 设置单元格的边框为粗体
	        
	        //1
	        Row  row;
	        Cell cell;
	        row =sheet.createRow(0);// 第一行，标题
	        cell=row.createCell(0);
	        cell.setCellValue("全部办件查询");
	        cell.setCellStyle(styles);
	        sheet.addMergedRegion(new CellRangeAddress(0,0,0,14));
	        //2
	        row =sheet.createRow(1);// 第一行，标题
	        cell=row.createCell(0);
	        titleNames=titleNames.substring(11);
	        cell.setCellValue(titleNames);
	        cell.setCellStyle(style_);
	        sheet.addMergedRegion(new CellRangeAddress(1,1,0,14));
	        //3
	        row = sheet.createRow(2);//第二行 标题
	        for (int i = 0; i < coloumItems.length; i++) {
	            cell = row.createCell(i);
	            cell.setCellValue(coloumItems[i]);
	            cell.setCellStyle(headStyle);
	        }
	        int len = listEgo.size();
	        //循环创建数据行,填充数据.
	        for (int i = 0; i < len; i++) {
	        	//4
	        	row = sheet.createRow(i + 3);
	            cell = row.createCell(0);
	            cell.setCellValue(listEgo.get(i).getAreaName());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(1);
	            cell.setCellValue(listEgo.get(i).getSj());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(2);
	            cell.setCellValue(listEgo.get(i).getBj());
	            cell.setCellStyle(style);
	            //保留2位小数
				float  bjPercent   =  (float)(Math.round(listEgo.get(i).getBjPercent()*100))/100;
				cell = row.createCell(3);
	            cell.setCellValue(String.valueOf(bjPercent));
	            cell.setCellStyle(style);
	            //保留2位小数
	            float  tsPercent   =  (float)(Math.round(listEgo.get(i).getTsPercent()*100))/100;
	            cell = row.createCell(4);
	            cell.setCellValue(String.valueOf(tsPercent));
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(5);
	            cell.setCellValue(listEgo.get(i).getBjyellow());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(6);
	            cell.setCellValue(listEgo.get(i).getBjred());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(7);
	            cell.setCellValue(listEgo.get(i).getCnyellow());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(8);
	            cell.setCellValue(listEgo.get(i).getCnred());
	            cell.setCellStyle(style);

	            cell = row.createCell(9);
	            cell.setCellValue(listEgo.get(i).getCsyellow());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(10);
	            cell.setCellValue(listEgo.get(i).getCsred());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(11);
	            cell.setCellValue(listEgo.get(i).getCn());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(12);
	            cell.setCellValue(listEgo.get(i).getCrn());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(13);
	            cell.setCellValue(listEgo.get(i).getCsn());
	            cell.setCellStyle(style);
	            
	            cell = row.createCell(14);
	            cell.setCellValue(listEgo.get(i).getCsrn());
	            cell.setCellStyle(style);
	        }           
			String filename = "全部办件查询.xls";//设置下载时客户端Excel的名称    
			String wordname=java.net.URLEncoder.encode(filename,"UTF-8");
			response.setContentType("application/vnd.ms-excel");   
			response.setHeader("Content-disposition", "attachment;filename=" + wordname);   
			OutputStream ouputStream = response.getOutputStream();   
			workbook.write(ouputStream);   
			ouputStream.flush();   
			ouputStream.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
