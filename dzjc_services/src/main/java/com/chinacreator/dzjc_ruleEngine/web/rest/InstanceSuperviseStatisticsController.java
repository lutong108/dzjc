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

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.sysmgr.User;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoAllExport;
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoAllWordExport;
import com.chinacreator.dzjc_todoStatistics.ViewFlagConstant;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.SystemPropertiesInstance;

import freemarker.template.Configuration;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class InstanceSuperviseStatisticsController {
	
	private Configuration configuration = null;

	//private final static String SYSTEM_SCOPE_VALUE = SystemPropertiesInstance.getInstance().getSystem_scope();
	
	@SuppressWarnings("deprecation")
	@GET
	@Path("/getInstanceSuperviseStatisticsInfo")
	public Map<String, Object> getInstanceSuperviseStatisticsInfo(
					@QueryParam(value = "org_xzqm") String org_xzqm,@QueryParam(value = "beginDate") String beginDate,
					@QueryParam(value = "endDate") String endDate,@QueryParam(value = "parentCode") String parentCode,
					@QueryParam(value = "viewFlag") String viewFlag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentCode",parentCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JCSumSuperviseInfoAll jCSumSuperviseInfoAll=new JCSumSuperviseInfoAll();
		Date d = new Date();  
		if (jCSumSuperviseInfoAll != null) {
			if ("".equals(parentCode)) {
				return null;
			}
			jCSumSuperviseInfoAll.setORG_ID(parentCode);
		}
		String dataTimeStr = "";
		String dataTimeStr1 = "";
		if (!"".equals(beginDate) && beginDate != null && !"NaN".equals(beginDate) && !"null".equals(beginDate)) {
			dataTimeStr = sdf.format(new Date(Long.parseLong(String.valueOf(beginDate))));
			jCSumSuperviseInfoAll.setStart_time(dataTimeStr);
		}
		if (!"".equals(endDate) && endDate != null && !"NaN".equals(endDate) && !"null".equals(endDate)) {
			dataTimeStr1 = sdf.format(new Date(Long.parseLong(String.valueOf(endDate))));
			jCSumSuperviseInfoAll.setEnd_time(dataTimeStr1);
		}
		if (jCSumSuperviseInfoAll.getStart_time() == null) {
			// 日期为null时设置为当前月第一天
			jCSumSuperviseInfoAll.setStart_time(DateUtil.getFormatDate(DateUtil.getFirstDate(d.getYear() + 1900, d.getMonth() + 1)));
		}
		if (jCSumSuperviseInfoAll.getEnd_time() == null) {
			jCSumSuperviseInfoAll.setEnd_time(DateUtil.getFormatDate(DateUtil.getLastDate(d.getYear() + 1900, d.getMonth() + 1)));
		}
		List<JCSumSuperviseInfoAll> list = new ArrayList<JCSumSuperviseInfoAll>();
		if ("430000000000".equals(parentCode)) {
			// 查询全省
			list = DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList(
					"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByprovince"
					,jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.CITY_FLAG);
		} else if ("439900000000".equals(parentCode)) {
			// 查询省本级
			list = DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByArea3",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.PROVINCE_FLAG);
		} else if (!"439900000000".equals(parentCode) && "city".equals(viewFlag) && !"430000000000".equals(parentCode)) {
			// 根据城市city查询
			list = DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByCity",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.AREA_FLAG);
		} else if ("org".equals(viewFlag)) {
			// 根据结构查询
			list = DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoAll",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} else if(parentCode.substring(6).equals("000000") && !parentCode.equals("433199000000")
				  && (!"01".equals(parentCode.substring(4,6)) || parentCode.equals("433101000000") 
						  && "area".equals(viewFlag))){
			//区县    行政区划 后六位 为000000,并且不为市直及湘西州州直，吉首市行政区划编码为433101000000，属于区县级，特例
			list = DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByArea",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} else {
			//市直，区县本级、街道，市直行政区划 后六位 为000000
			list = DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByArea1",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} 
		if (list.size() > 1) {
			int yjsSum = 0;
			int yellowSum = 0;
			int redSum = 0;
			int cancel_yellow_num_Sum = 0;
			int cancel_red_num_sum = 0;
			JCSumSuperviseInfoAll jcSumSuperviseInfoAll = new JCSumSuperviseInfoAll();
			jcSumSuperviseInfoAll.setArea_name("合计");
			for (JCSumSuperviseInfoAll jcSumSuperviseInfoAll2 : list) {
				String yjs = jcSumSuperviseInfoAll2.getYjs();
				if (yjs != null && !"".equals(yjs)) {
					yjsSum += Integer.parseInt(yjs);
				}
				String yellow = jcSumSuperviseInfoAll2.getYellow();
				if (yellow != null && !"".equals(yellow)) {
					yellowSum += Integer.parseInt(yellow);
				}
				String red = jcSumSuperviseInfoAll2.getRed();
				if (red != null && !"".equals(red)) {
					redSum += Integer.parseInt(red);
				}
				String cancel_yellow_num = jcSumSuperviseInfoAll2
						.getCancel_yellow_num();
				if (cancel_yellow_num != null && !"".equals(cancel_yellow_num)) {
					cancel_yellow_num_Sum += Integer
							.parseInt(cancel_yellow_num);
				}
				String cancel_red_num = jcSumSuperviseInfoAll2
						.getCancel_red_num();
				if (cancel_red_num != null && !"".equals(cancel_red_num)) {
					cancel_red_num_sum += Integer.parseInt(cancel_red_num);
				}
			}
			jcSumSuperviseInfoAll.setYjs(String.valueOf(yjsSum));
			jcSumSuperviseInfoAll.setYellow(String.valueOf(yellowSum));
			jcSumSuperviseInfoAll.setRed(String.valueOf(redSum));
			jcSumSuperviseInfoAll.setCancel_yellow_num(String.valueOf(cancel_yellow_num_Sum));
			jcSumSuperviseInfoAll.setCancel_red_num(String.valueOf(cancel_red_num_sum));
			list.add(jcSumSuperviseInfoAll);
		}
		map.put("dataList", list);
		map.put("titleName","办件发牌统计情况（"+ jCSumSuperviseInfoAll.getStart_time().replaceAll("-","/")+ "----"+ jCSumSuperviseInfoAll.getEnd_time().replaceAll("-","/") + "）");
		return map;
	}
	
	/**
	 * 办件发牌统计excel导出
	 */
	@SuppressWarnings("unchecked")
	public String excelExport(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag)
	{	
		Map<String, Object> map = getInstanceSuperviseStatisticsInfo(org_xzqm,beginDate, endDate, parentCode, viewFlag);
		List<JCSumSuperviseInfoAll> list = (List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName = (String) map.get("titleName");
		if (titleName.contains("/")) 
		{
			titleName = titleName.replaceAll("/", "-");
		}
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		JCSumSuperviseInfoAllExport jCSumSuperviseInfoAllExport = new JCSumSuperviseInfoAllExport();
		jCSumSuperviseInfoAllExport.excelExport(titleName, jcList);
		return titleName;
	}
	
	/**
	 * 办件发牌统计word导出
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String wordExport(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) 
	{
		Map<String, Object> map = getInstanceSuperviseStatisticsInfo(org_xzqm,beginDate, endDate, parentCode, viewFlag);
		List<JCSumSuperviseInfoAll> list = (List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName = (String) map.get("titleName");
		if (titleName.contains("/")) 
		{
			titleName = titleName.replaceAll("/", "-");
		}
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		JCSumSuperviseInfoAllWordExport jCSumSuperviseInfoAllWordExport = new JCSumSuperviseInfoAllWordExport();
		jCSumSuperviseInfoAllWordExport.createWord(configuration, titleName, jcList);
		return titleName;
	}

	
	/**
	 * 数据权限查询判断
	 */
	@POST
	@Path("InstanceSuperviseStatistics/getOrgCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getOrgCode(){
		Map<String,Object> result = new HashMap<>();
		try {
			String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
			User user = (User) ((WebOperationContext) OperationContextHolder.getContext()).getUser();
			String[] roles = (String[]) user.get("roles");
			String[] orgIds = (String[]) user.get("orgIds");
			// 获取指定机构详情(用户所在机构)
			Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class,orgIds[0]);
			String orgCode = (String)org.get("xzqm");
			int index = -1;
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
				}
				//省本级管理员、市本级管理员、区县级管理员
				if (roleNames.contains("dzjc_sjgly") || roleNames.contains("dzjc_shijgly")|| roleNames.contains("dzjc_qxjgly")) {
					orgCode = (String) org.get("xzqm");
				}
			}
			StringBuffer br=new StringBuffer(orgCode);
			if (orgCode.length() <= 12) {
				if (orgCode.length() == 2) {
					result.put("viewFlag", ViewFlagConstant.PROVINCE_FLAG);
				} else if (orgCode.length() == 4 || index == 4) {
					result.put("viewFlag", ViewFlagConstant.CITY_FLAG);
				} else if (orgCode.length() == 6 || index == 6) {
					result.put("viewFlag", ViewFlagConstant.AREA_FLAG);
				}
				if (orgCode.length() < 12) {
					for (int i = 0; i < 12 - orgCode.length(); i++) {
						br.append("0");
					}
				} else {
					br = new StringBuffer((String) org.get("xzqm"));
				}
			} else {
				result.put("viewFlag", ViewFlagConstant.ORG_FLAG);
			}
			result.put("parentCode", br);
			result.put("success",true);

		} catch (Exception e) {
			result.put("message", "获取权限失败");
			result.put("success", false);
		}
		return result;
	}
	
	//供排序功能调用
	@SuppressWarnings("deprecation")
	@GET
	@Path("/getInstanceSuperviseStatisticsInfoBySortCol")
	public Map<String, Object> getInstanceSuperviseStatisticsInfoBySortCol(
			@QueryParam(value = "org_xzqm") String org_xzqm,@QueryParam(value = "beginDate") String beginDate,
			@QueryParam(value = "endDate") String endDate,@QueryParam(value = "parentCode") String parentCode,
			@QueryParam(value = "viewFlag") String viewFlag,@QueryParam(value = "sortCol") String sortCol) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentCode",parentCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JCSumSuperviseInfoAll jCSumSuperviseInfoAll=new JCSumSuperviseInfoAll();
		//将需排序的列封装进实体对象
		if(sortCol != null){
			jCSumSuperviseInfoAll.setSortCol(sortCol);
		}
		Date d = new Date();  
		if (jCSumSuperviseInfoAll != null) {
			if ("".equals(parentCode)) {
				return null;
			}
			jCSumSuperviseInfoAll.setORG_ID(parentCode);
		}
		String dataTimeStr="";
		String dataTimeStr1=""; 
		if(!"".equals(beginDate) && beginDate!=null && !"NaN".equals(beginDate) && !"null".equals(beginDate)){
			 dataTimeStr = sdf.format(new Date(Long.parseLong(String
					.valueOf(beginDate))));
			jCSumSuperviseInfoAll.setStart_time(dataTimeStr);
		}
		if(!"".equals(endDate) && endDate!=null && !"NaN".equals(endDate) && !"null".equals(endDate)){
			 dataTimeStr1 = sdf.format(new Date(Long.parseLong(String
					.valueOf(endDate))));
			jCSumSuperviseInfoAll.setEnd_time(dataTimeStr1);
		}
		if(jCSumSuperviseInfoAll.getStart_time()==null){
			jCSumSuperviseInfoAll.setStart_time(DateUtil.getFormatDate(DateUtil.getFirstDate(d.getYear()+1900,d.getMonth()+1)));//日期为null时 设置为当前月第一天
		}
		if (jCSumSuperviseInfoAll.getEnd_time() == null) {
			jCSumSuperviseInfoAll.setEnd_time(DateUtil.getFormatDate(DateUtil
					.getLastDate(d.getYear() + 1900, d.getMonth() + 1)));// 日期为null时
																			// 设置为当前月最后一天
		}
		List<JCSumSuperviseInfoAll> list = new ArrayList<JCSumSuperviseInfoAll>();
		// 判断
		if ("430000000000".equals(parentCode)) {
			// 查询全省
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByprovince",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.CITY_FLAG);
		} else if ("439900000000".equals(parentCode)) {
			// 查询省本级
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByArea3",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.PROVINCE_FLAG);
		} else if (!"439900000000".equals(parentCode)
				&& "city".equals(viewFlag)
				&& !"430000000000".equals(parentCode)) {
			// 根据城市city查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByCity",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.AREA_FLAG);
		}else if ("org".equals(viewFlag)) {
			// 根据结构查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoAll",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		}else if(parentCode.substring(6).equals("000000") && !parentCode.equals("433199000000") && (!"01".equals(parentCode.substring(4,6)) || parentCode.equals("433101000000") && "area".equals(viewFlag))){
			//区县    行政区划 后六位 为000000,并且不为市直及湘西州州直，吉首市行政区划编码为433101000000，属于区县级，特例
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByArea",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
			
		}else {
			//市直，区县本级、街道
			//市直行政区划 后六位 为000000
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.InstanceSuperviseStatisticsMapper.selectInstanceSuperviseStatisticsInfoByArea1",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} 
		if (list.size() > 1) {
			int yjsSum = 0;
			int yellowSum=0;
			int redSum=0;
			int cancel_yellow_num_Sum=0;
			int cancel_red_num_sum=0;
			JCSumSuperviseInfoAll jcSumSuperviseInfoAll=new JCSumSuperviseInfoAll();
			jcSumSuperviseInfoAll.setArea_name("合计");
			for (JCSumSuperviseInfoAll jcSumSuperviseInfoAll2 : list) {
				String yjs=jcSumSuperviseInfoAll2.getYjs();
				if(yjs!=null && !"".equals(yjs)){
					yjsSum+=Integer.parseInt(yjs);
				}
				String yellow=jcSumSuperviseInfoAll2.getYellow();
				if(yellow!=null && !"".equals(yellow)){
					yellowSum+=Integer.parseInt(yellow);
				}
				String red=jcSumSuperviseInfoAll2.getRed();
				if(red!=null && !"".equals(red)){
					redSum+=Integer.parseInt(red);
				}
				String cancel_yellow_num=jcSumSuperviseInfoAll2.getCancel_yellow_num();
				if(cancel_yellow_num!=null && !"".equals(cancel_yellow_num)){
					cancel_yellow_num_Sum+=Integer.parseInt(cancel_yellow_num);
				}
				String cancel_red_num=jcSumSuperviseInfoAll2.getCancel_red_num();
				if(cancel_red_num!=null && !"".equals(cancel_red_num)){
					cancel_red_num_sum+=Integer.parseInt(cancel_red_num);
				}
			}
			jcSumSuperviseInfoAll.setYjs(String.valueOf(yjsSum));
			jcSumSuperviseInfoAll.setYellow(String.valueOf(yellowSum));
			jcSumSuperviseInfoAll.setRed(String.valueOf(redSum));
			jcSumSuperviseInfoAll.setCancel_yellow_num(String.valueOf(cancel_yellow_num_Sum));
			jcSumSuperviseInfoAll.setCancel_red_num(String.valueOf(cancel_red_num_sum));
			list.add(jcSumSuperviseInfoAll);
		}
		map.put("dataList", list);
		map.put("titleName","办件发牌统计情况（"+jCSumSuperviseInfoAll.getStart_time().replaceAll("-", "/")+"----"+jCSumSuperviseInfoAll.getEnd_time().replaceAll("-", "/")+"）");
		return map;
	}

}
