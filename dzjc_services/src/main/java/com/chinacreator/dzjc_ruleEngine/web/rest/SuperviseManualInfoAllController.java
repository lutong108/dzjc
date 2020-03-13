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

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoAllExport;
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoAllWordExport;
import com.chinacreator.dzjc_ruleEngine.utils.SuperviseManualInfoExport;
import com.chinacreator.dzjc_todoStatistics.ViewFlagConstant;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.RoleUtil;

import freemarker.template.Configuration;



@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SuperviseManualInfoAllController {
	
	private Configuration configuration = null;
	
	@GET
	@Path("/getSuperviseManualInfoAll")
	public Map<String, Object> getSuperviseManualInfoAll(
			@QueryParam(value = "org_xzqm") String org_xzqm,
			@QueryParam(value = "beginDate") String beginDate,
			@QueryParam(value = "endDate") String endDate,
			@QueryParam(value = "parentCode") String parentCode,
			@QueryParam(value = "viewFlag") String viewFlag
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentCode",parentCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/// 权限控制
		/*String code = null;
		try {
			// 查询当前登录用户编号(行政区码)
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		JCSumSuperviseInfoAll jCSumSuperviseInfoAll=new JCSumSuperviseInfoAll();
		Date d = new Date();  
		if (jCSumSuperviseInfoAll != null) {
			if ("".equals(parentCode)) {
				return null;
			}
			jCSumSuperviseInfoAll.setORG_ID(parentCode);
		}
		
		String dataTimeStr="";
		String dataTimeStr1=""; 
		/*if(!"".equals(org_xzqm) && org_xzqm!=null && !"undefined".equals(org_xzqm)){
			jCSumSuperviseInfoAll.setArea_code(org_xzqm);
		}*/
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
		// DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoAll",
		// jCSumSuperviseInfoAll);
		// 判断
		if ("430000000000".equals(parentCode)) {
			// 查询全省
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByProvince",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.CITY_FLAG);
		} else if ("439900000000".equals(parentCode)) {
			// 查询省本级
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByLevel",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.PROVINCE_FLAG);
		} else if (!"439900000000".equals(parentCode)
				&& "city".equals(viewFlag)
				&& !"430000000000".equals(parentCode)) {
			// 根据城市查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByCity",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.AREA_FLAG);
		} else if ("org".equals(viewFlag)) {
			// 根据机构查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByOrg",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} else if (parentCode.substring(6).equals("000000")
				&& !parentCode.equals("433199000000")
				&& (!"01".equals(parentCode.substring(4, 6)) || "area".equals(viewFlag))) {
			// 根据区级查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByArea",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);

		} else {
			// 市直，区县本级、街道
			// 市直行政区划 后六位 为000000
			/*list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea1",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);*/
		}

		if (list.size() > 1) {
			int yjsSum = 0;
			int yellowSum=0;
			int redSum=0;
			int cancel_yj_num_Sum=0;
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
				String cancel_yj_num = jcSumSuperviseInfoAll2.getCancel_yj_num();
				if (cancel_yj_num != null && !"".equals(cancel_yj_num)) {
					cancel_yj_num_Sum += Integer.parseInt(cancel_yj_num);
				}
			}
			
			jcSumSuperviseInfoAll.setYjs(String.valueOf(yjsSum));
			jcSumSuperviseInfoAll.setYellow(String.valueOf(yellowSum));
			jcSumSuperviseInfoAll.setRed(String.valueOf(redSum));
			jcSumSuperviseInfoAll.setCancel_yellow_num(String.valueOf(cancel_yellow_num_Sum));
			jcSumSuperviseInfoAll.setCancel_red_num(String.valueOf(cancel_red_num_sum));
			jcSumSuperviseInfoAll.setCancel_yj_num(String.valueOf(cancel_yj_num_Sum));
			list.add(jcSumSuperviseInfoAll);
		}
		map.put("dataList", list);
		map.put("titleName","人工发牌统计情况（"+jCSumSuperviseInfoAll.getStart_time().replaceAll("-", "/")+"----"+jCSumSuperviseInfoAll.getEnd_time().replaceAll("-", "/")+"）");
		return map;
	}
	
	/**
	 * 人工发牌统计excel导出
	 */
	@SuppressWarnings("unchecked")
	public String excelExport(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) {
		
		Map<String, Object> map= getSuperviseManualInfoAll(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		
		List<JCSumSuperviseInfoAll> list = (List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName = (String) map.get("titleName");
		if (titleName.contains("/")) {
			titleName = titleName.replaceAll("/", "-");
		}
		// 创建excel导出对象
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		SuperviseManualInfoExport exporter = new SuperviseManualInfoExport();
		exporter.excelExport(titleName, jcList);
		return titleName;
	}
	
	/**
	 * 人工发牌统计word导出
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String wordExport(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) {
		
		Map<String, Object> map = getSuperviseManualInfoAll(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		
		List<JCSumSuperviseInfoAll> list = (List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName = (String) map.get("titleName");
		if (titleName.contains("/")) {
			titleName=titleName.replaceAll("/", "-");
		}
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		// 创建word导出对象
		SuperviseManualInfoExport export = new SuperviseManualInfoExport();
		export.wordExport(configuration, titleName, jcList);
		return titleName;
	}
	
	//供排序功能调用
	@GET
	@Path("/getSuperviseManualInfoBySortCol")
	public Map<String, Object> getSuperviseManualInfoBySortCol(
			@QueryParam(value = "org_xzqm") String org_xzqm,
			@QueryParam(value = "beginDate") String beginDate,
			@QueryParam(value = "endDate") String endDate,
			@QueryParam(value = "parentCode") String parentCode,
			@QueryParam(value = "viewFlag") String viewFlag,
			@QueryParam(value = "sortCol") String sortCol
			) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentCode",parentCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		JCSumSuperviseInfoAll jCSumSuperviseInfoAll=new JCSumSuperviseInfoAll();
		//将需排序的列封装进实体对象
		if(sortCol != null)
		{
			jCSumSuperviseInfoAll.setSortCol(sortCol);
		}
		Date d = new Date();  
		if (jCSumSuperviseInfoAll != null) 
		{
			if ("".equals(parentCode)) 
			{
				return null;
			}
			jCSumSuperviseInfoAll.setORG_ID(parentCode);
		}
		String dataTimeStr="";
		String dataTimeStr1=""; 
		
		if(!"".equals(beginDate) && beginDate!=null && !"NaN".equals(beginDate) && !"null".equals(beginDate))
		{
			 dataTimeStr = sdf.format(new Date(Long.parseLong(String
					.valueOf(beginDate))));
			jCSumSuperviseInfoAll.setStart_time(dataTimeStr);
		}
		if(!"".equals(endDate) && endDate!=null && !"NaN".equals(endDate) && !"null".equals(endDate))
		{
			 dataTimeStr1 = sdf.format(new Date(Long.parseLong(String
					.valueOf(endDate))));
			jCSumSuperviseInfoAll.setEnd_time(dataTimeStr1);
		}
		if(jCSumSuperviseInfoAll.getStart_time()==null){
			jCSumSuperviseInfoAll.setStart_time(DateUtil.getFormatDate(DateUtil.getFirstDate(d.getYear()+1900,d.getMonth()+1)));//日期为null时 设置为当前月第一天
		}
		if (jCSumSuperviseInfoAll.getEnd_time() == null) 
		{
			jCSumSuperviseInfoAll.setEnd_time(DateUtil.getFormatDate(DateUtil
					.getLastDate(d.getYear() + 1900, d.getMonth() + 1)));// 日期为null时
																			// 设置为当前月最后一天
		}
		List<JCSumSuperviseInfoAll> list = new ArrayList<JCSumSuperviseInfoAll>();
		// 判断
		if ("430000000000".equals(parentCode)) 
		{ // 查询全省
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByProvince",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.CITY_FLAG);
		} 
		else if ("439900000000".equals(parentCode)) 
		{ // 查询省本级
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByLevel",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.PROVINCE_FLAG);
		} 
		else if (!"439900000000".equals(parentCode) && "city".equals(viewFlag) && !"430000000000".equals(parentCode)) 
		{ // 根据城市查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByCity",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.AREA_FLAG);
		} else if ("org".equals(viewFlag)) 
		{ /*// 根据机构查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByOrg",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);*/
		} 
		else if (parentCode.substring(6).equals("000000") && !parentCode.equals("433199000000")
				&& (!"01".equals(parentCode.substring(4, 6)) || "area".equals(viewFlag))) 
		{ // 根据区级查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.SuperviseManualInfoAllMapper.selectSuperviseManualInfoAllByArea",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} 
		if (list.size() > 1) 
		{
			int yjsSum = 0;
			int yellowSum=0;
			int redSum=0;
			int cancel_yj_num_Sum=0;
			int cancel_yellow_num_Sum=0;
			int cancel_red_num_sum=0;
			JCSumSuperviseInfoAll jcSumSuperviseInfoAll=new JCSumSuperviseInfoAll();
			jcSumSuperviseInfoAll.setArea_name("合计");
			for (JCSumSuperviseInfoAll jcSumSuperviseInfoAll2 : list)
			{
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
				String cancel_yj_num = jcSumSuperviseInfoAll2.getCancel_yj_num();
				if (cancel_yj_num != null && !"".equals(cancel_yj_num)) {
					cancel_yj_num_Sum += Integer.parseInt(cancel_yj_num);
				}
			}
			jcSumSuperviseInfoAll.setYjs(String.valueOf(yjsSum));
			jcSumSuperviseInfoAll.setYellow(String.valueOf(yellowSum));
			jcSumSuperviseInfoAll.setRed(String.valueOf(redSum));
			jcSumSuperviseInfoAll.setCancel_yellow_num(String.valueOf(cancel_yellow_num_Sum));
			jcSumSuperviseInfoAll.setCancel_red_num(String.valueOf(cancel_red_num_sum));
			jcSumSuperviseInfoAll.setCancel_yj_num(String.valueOf(cancel_yj_num_Sum));
			list.add(jcSumSuperviseInfoAll);
		}
		map.put("dataList", list);
		map.put("titleName","人工发牌统计情况（"+jCSumSuperviseInfoAll.getStart_time().replaceAll("-", "/")+"----"+jCSumSuperviseInfoAll.getEnd_time().replaceAll("-", "/")+"）");
		return map;
	}

}
