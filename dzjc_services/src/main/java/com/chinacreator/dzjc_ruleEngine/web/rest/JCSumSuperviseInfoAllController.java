package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.NumberFormat;
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
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoExport;
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoWordExport;
import com.chinacreator.dzjc_todoStatistics.ViewFlagConstant;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.SystemPropertiesInstance;

import freemarker.template.Configuration;



@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class JCSumSuperviseInfoAllController {
	private Configuration configuration = null;

	private final static String SYSTEM_SCOPE_VALUE = SystemPropertiesInstance.getInstance().getSystem_scope();
	
	@GET
	@Path("/getJCSumSuperviseInfoAll")
	public Map<String, Object> getJCSumSuperviseInfoAll(
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
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoAllByprovince",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.CITY_FLAG);
		} else if ("439900000000".equals(parentCode)) {
			// 查询省本级
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea3",
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
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoAllByCity",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.AREA_FLAG);
		}else if ("org".equals(viewFlag)) {
			// 根据结构查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoAll",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		}else if(parentCode.substring(6).equals("000000") && !parentCode.equals("433199000000") && (!"01".equals(parentCode.substring(4,6)) || parentCode.equals("433101000000") && "area".equals(viewFlag))){
			//区县    行政区划 后六位 为000000,并且不为市直及湘西州州直，吉首市行政区划编码为433101000000，属于区县级，特例
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
			
		}else {
			//市直，区县本级、街道
			//市直行政区划 后六位 为000000
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea1",
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
		map.put("titleName",SYSTEM_SCOPE_VALUE+"发牌统计情况"+jCSumSuperviseInfoAll.getStart_time().replaceAll("-", "/")+"----"+jCSumSuperviseInfoAll.getEnd_time().replaceAll("-", "/"));

		return map;
	}
	
	/**
	 * 全省发牌统计excel导出
	 * 
	 * @return
	 */
	public String excelExport(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) {
		
		Map<String, Object> map= getJCSumSuperviseInfoAll(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		
		List<JCSumSuperviseInfoAll> list=(List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName=(String) map.get("titleName");
		if(titleName.contains("/")){
			titleName=titleName.replaceAll("/", "-");
		}
		// 创建excel导出对象
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		JCSumSuperviseInfoAllExport jCSumSuperviseInfoAllExport=new JCSumSuperviseInfoAllExport();
		jCSumSuperviseInfoAllExport.excelExport(titleName, jcList);
		
		return titleName;
	}
	
	/**
	 * 全省发牌统计word导出
	 * @param org_xzqm
	 * @return
	 */
	public String wordExport(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) {
		
		Map<String, Object> map= getJCSumSuperviseInfoAll(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		
		List<JCSumSuperviseInfoAll> list=(List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName=(String) map.get("titleName");
		if(titleName.contains("/")){
			titleName=titleName.replaceAll("/", "-");
		}
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		
		// 创建word导出对象
		JCSumSuperviseInfoAllWordExport jCSumSuperviseInfoAllWordExport=new JCSumSuperviseInfoAllWordExport();
		jCSumSuperviseInfoAllWordExport.createWord(configuration, titleName, jcList);
		
		return titleName;
	
	}
	
	/**
	 * 办件统计word导出
	 * @param org_xzqm
	 * @return
	 */
	public String wordExport2(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) {
		
		Map<String, Object> map= getJCSumSuperviseInfoAll(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		
		List<JCSumSuperviseInfoAll> list=(List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName=(String) map.get("titleName");
		if(titleName.contains("/")){
			titleName=titleName.replaceAll("/", "-");
		}
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		
		// 创建word导出对象
		JCSumSuperviseInfoAllWordExport jCSumSuperviseInfoAllWordExport=new JCSumSuperviseInfoAllWordExport();
		jCSumSuperviseInfoAllWordExport.createWord(configuration, titleName, jcList);
		
		return titleName;
	
	}
	
	
	
	/**
	 * 发牌情况详情list
	 * @param org_xzqm
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/getJCSumSuperviseInfo")
	public Map<String, Object> getJCSumSuperviseInfo(
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
		if(!"".equals(beginDate) && beginDate!=null && !"NaN".equals(beginDate)){
			 dataTimeStr = sdf.format(new Date(Long.parseLong(String
					.valueOf(beginDate))));
			jCSumSuperviseInfoAll.setStart_time(dataTimeStr);
		}
		if(!"".equals(endDate) && endDate!=null && !"NaN".equals(endDate) ){
			 dataTimeStr1 = sdf.format(new Date(Long.parseLong(String
					.valueOf(endDate))));
			jCSumSuperviseInfoAll.setEnd_time(dataTimeStr1);
		}
		if(jCSumSuperviseInfoAll.getStart_time()==null){
			jCSumSuperviseInfoAll.setStart_time(DateUtil.getFormatDate(DateUtil.getFirstDate(d.getYear()+1900,d.getMonth()+1)));//日期为null时 设置为当前月第一天
		}
		if(jCSumSuperviseInfoAll.getEnd_time()==null){
			jCSumSuperviseInfoAll.setEnd_time(DateUtil.getFormatDate(DateUtil.getLastDate(d.getYear()+1900,d.getMonth()+1)));//日期为null时 设置为当前月最后一天
		}
		List<JCSumSuperviseInfoAll> list=new ArrayList<JCSumSuperviseInfoAll>();
		
		//判断
		if("430000000000".equals(parentCode)){
			//查询全省
			list=DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByprovince", jCSumSuperviseInfoAll);
			map.put("viewFlag",ViewFlagConstant.CITY_FLAG);
		}else if("439900000000".equals(parentCode)){
			//查询省本级
			list=DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea3", jCSumSuperviseInfoAll);
			map.put("viewFlag",ViewFlagConstant.PROVINCE_FLAG);
		}else if(!"439900000000".equals(parentCode) && "city".equals(viewFlag) && !"430000000000".equals(parentCode) ){
			//根据城市city查询
			list=DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByCity", jCSumSuperviseInfoAll);
			map.put("viewFlag",ViewFlagConstant.AREA_FLAG);
		}else if("org".equals(viewFlag)){
			//根据结构查询
			list=DaoFactory.create(JCSumSuperviseInfoAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfo", jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		}else if(parentCode.substring(6).equals("000000") && !parentCode.equals("433199000000") && (!"01".equals(parentCode.substring(4,6)) || parentCode.equals("433101000000") && "area".equals(viewFlag))){
			//区县    行政区划 后六位 为000000,并且不为市直及湘西州州直，吉首市行政区划编码为433101000000，属于区县级，特例
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
			
		}else {
			//市直，区县本级、街道
			//市直行政区划 后六位 为000000
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea1",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		} 
		
		//将“特别程序业务”的预警、黄牌、红牌，分别合并入“办件业务”的预警、黄牌、红牌
		for (JCSumSuperviseInfoAll jcSumSuperviseInfoAllBean : list) {
			String supervise_green_num_temp = jcSumSuperviseInfoAllBean.getSpecial_supervise_green_num();
			String supervise_yellow_num_temp = jcSumSuperviseInfoAllBean.getSpecial_supervise_yellow_num();
			String supervise_red_num_temp = jcSumSuperviseInfoAllBean.getSpecial_supervise_red_num();

			Integer temp_Instance_yjs = Integer.parseInt(jcSumSuperviseInfoAllBean.getInstance_yjs()) + 
										Integer.parseInt(supervise_green_num_temp);
			jcSumSuperviseInfoAllBean.setInstance_yjs(temp_Instance_yjs + "");
			
			Integer temp_Instance_yellow = Integer.parseInt(jcSumSuperviseInfoAllBean.getInstance_yellow()) + 
					Integer.parseInt(supervise_yellow_num_temp);
			jcSumSuperviseInfoAllBean.setInstance_yellow(temp_Instance_yellow + "");
			
			Integer temp_Instance_red = Integer.parseInt(jcSumSuperviseInfoAllBean.getInstance_red()) + 
					Integer.parseInt(supervise_red_num_temp);
			jcSumSuperviseInfoAllBean.setInstance_red(temp_Instance_red + "");
		}
		
		NumberFormat     nf =    NumberFormat.getPercentInstance();  
		//计算合计
		int slsCount = 0;
		int bjsCount = 0;
		int zcbjsCount = 0;
		int thbjsCount = 0;
		int zfbjsCount = 0; 
		int scbjsCount = 0;
		int ljslsCount = 0;
		int ljbjsCount = 0;
		int byslsCount = 0;
		int yjsCount = 0;
		int yellowCount = 0;
		int redCount = 0; 
		int complain_numCount=0;
		int complain_reply_numCount=0;
		int consult_numCount=0;
		int consult_replay_numCount=0;
		int cancel_yellow_numCount=0;
		int cancel_red_numCount=0;
		
		int instance_yjsCount=0;
		int instance_yellowCount=0;
		int instance_redCount=0;
		int instance_cancel_yellow_numCount=0;
		int instance_cancel_red_numCount=0;
		
		int complain_yjsCount=0;
		int complain_yellowCount=0;
		int complain_redCount=0;
		int complain_cancel_yellow_numCount=0;
		int complain_cancel_red_numCount=0;
		
		int consult_yjsCount=0;
		int consult_yellowCount=0;
		int consult_redCount=0;
		int consult_cancel_yellow_numCount=0;
		int consult_cancel_red_numCount=0;
		
		int special_supervise_green_numCount=0;
		int special_supervise_yellow_numCount=0;
		int special_supervise_red_numCount=0;
		for (JCSumSuperviseInfoAll jcSumSuperviseInfoAll2 : list) {
			//受理数
			int slsnum=0;
			String sls=jcSumSuperviseInfoAll2.getSls();
			if(sls!=null && !"".equals(sls)){
				slsCount+=Integer.parseInt(sls);
				slsnum=Integer.parseInt(sls);
			}
			//办结数
			int bjsnum=0;
			String bjs=jcSumSuperviseInfoAll2.getBjs();
			if(bjs!=null && !"".equals(bjs)){
				bjsnum=Integer.parseInt(bjs);
				bjsCount+=Integer.parseInt(bjs);
			}
			//计算办结率
			//受理数为0 的时候  办结率为 100%
			if (slsnum==0 && bjsnum!=0) {
				jcSumSuperviseInfoAll2.setBjl("100%");
			}else{
				if (bjsnum!=0 && slsnum !=0) { 
					//获得办结率    保留两位小数
					jcSumSuperviseInfoAll2.setBjl(nf.format((double)bjsnum/slsnum)+"");	
				}else{
					jcSumSuperviseInfoAll2.setBjl("0");
				}
			}
			//正常办结数
			String zcbjs=jcSumSuperviseInfoAll2.getZcbjs();
			if(zcbjs!=null && !"".equals(zcbjs)){
				zcbjsCount+=Integer.parseInt(zcbjs);
			}
			//  退回办结数
			String thbjs=jcSumSuperviseInfoAll2.getThbjs();
			if(thbjs!=null && !"".equals(thbjs)){
				thbjsCount+=Integer.parseInt(thbjs);
			}
			//  作废办结数
			String zfbjs=jcSumSuperviseInfoAll2.getZfbjs();
			if(zfbjs!=null && !"".equals(zfbjs)){
				zfbjsCount+=Integer.parseInt(zfbjs);
			}
			//  删除办结数
			String scbjs=jcSumSuperviseInfoAll2.getScbjs();
			if(scbjs!=null && !"".equals(scbjs)){
				scbjsCount+=Integer.parseInt(scbjs);
			}
			//  累计受理数（不加时间条件）
			String ljsls=jcSumSuperviseInfoAll2.getLjsls();
			if(ljsls!=null && !"".equals(ljsls)){
				ljslsCount+=Integer.parseInt(ljsls);
			}
			//  累计办结数 （不加时间条件）
			String ljbjs=jcSumSuperviseInfoAll2.getLjbjs();
			if(ljbjs!=null && !"".equals(ljbjs)){
				ljbjsCount+=Integer.parseInt(ljbjs);
			}
			//  不予受理数
			String bysls=jcSumSuperviseInfoAll2.getBysls();
			if(bysls!=null && !"".equals(bysls)){
				byslsCount+=Integer.parseInt(bysls);
			}
			//投诉数  
			String complain_num=jcSumSuperviseInfoAll2.getComplain_num();
			if(complain_num!=null && !"".equals(complain_num)){
				complain_numCount+=Integer.parseInt(complain_num);
			}
			//投诉回复数
			String complain_reply_num=jcSumSuperviseInfoAll2.getComplain_reply_num();
			if(complain_reply_num!=null && !"".equals(complain_reply_num)){
				complain_reply_numCount+=Integer.parseInt(complain_reply_num);
			}
			//咨询数
			String consult_num=jcSumSuperviseInfoAll2.getConsult_num();
			if(consult_num!=null && !"".equals(consult_num)){
				consult_numCount+=Integer.parseInt(consult_num);
			}
			//咨询回复数
			String consult_replay_num=jcSumSuperviseInfoAll2.getConsult_replay_num();
			if(consult_replay_num!=null && !"".equals(consult_replay_num)){
				consult_replay_numCount+=Integer.parseInt(consult_replay_num);
			}
			//预警
			String yjs=jcSumSuperviseInfoAll2.getYjs();
			if(yjs!=null && !"".equals(yjs)){
				yjsCount+=Integer.parseInt(yjs);
			}
			//黄牌
			String yellow=jcSumSuperviseInfoAll2.getYellow();
			if(yellow!=null && !"".equals(yellow)){
				yellowCount+=Integer.parseInt(yellow);
			}
			//红牌
			String red=jcSumSuperviseInfoAll2.getRed();
			if(red!=null && !"".equals(red)){
				redCount+=Integer.parseInt(red);
			}
			//撤销黄牌
			String cancel_yellow_num=jcSumSuperviseInfoAll2.getCancel_yellow_num();
			if(cancel_yellow_num!=null && !"".equals(cancel_yellow_num)){
				cancel_yellow_numCount+=Integer.parseInt(cancel_yellow_num);
			}
			//撤销红牌
			String cancel_red_num=jcSumSuperviseInfoAll2.getCancel_red_num();
			if(cancel_red_num!=null && !"".equals(cancel_red_num)){
				cancel_red_numCount+=Integer.parseInt(cancel_red_num);
			}
			
			String instance_yjs=jcSumSuperviseInfoAll2.getInstance_yjs();
			if(instance_yjs!=null && !"".equals(instance_yjs)){
				instance_yjsCount+=Integer.parseInt(instance_yjs);
			}
			String instance_yellow=jcSumSuperviseInfoAll2.getInstance_yellow();
			if(instance_yellow!=null && !"".equals(instance_yellow)){
				instance_yellowCount+=Integer.parseInt(instance_yellow);
			}
			String instance_red=jcSumSuperviseInfoAll2.getInstance_red();
			if(instance_red!=null && !"".equals(instance_red)){
				instance_redCount+=Integer.parseInt(instance_red);
			}
			String instance_cancel_yellow_num=jcSumSuperviseInfoAll2.getInstance_cancel_yellow_num();
			if(instance_cancel_yellow_num!=null && !"".equals(instance_cancel_yellow_num)){
				instance_cancel_yellow_numCount+=Integer.parseInt(instance_cancel_yellow_num);
			}
			String instance_cancel_red_num=jcSumSuperviseInfoAll2.getInstance_cancel_red_num();
			if(instance_cancel_red_num!=null && !"".equals(instance_cancel_red_num)){
				instance_cancel_red_numCount+=Integer.parseInt(instance_cancel_red_num);
			}
			
			String complain_yjs=jcSumSuperviseInfoAll2.getComplain_yjs();
			if(complain_yjs!=null && !"".equals(complain_yjs)){
				complain_yjsCount+=Integer.parseInt(complain_yjs);
			}
			String complain_yellow=jcSumSuperviseInfoAll2.getComplain_yellow();
			if(complain_yellow!=null && !"".equals(complain_yellow)){
				complain_yellowCount+=Integer.parseInt(complain_yellow);
			}
			String complain_red=jcSumSuperviseInfoAll2.getComplain_red();
			if(complain_red!=null && !"".equals(complain_red)){
				complain_redCount+=Integer.parseInt(complain_red);
			}
			String complain_cancel_yellow_num=jcSumSuperviseInfoAll2.getComplain_cancel_yellow_num();
			if(complain_cancel_yellow_num!=null && !"".equals(complain_cancel_yellow_num)){
				complain_cancel_yellow_numCount+=Integer.parseInt(complain_cancel_yellow_num);
			}
			String complain_cancel_red_num=jcSumSuperviseInfoAll2.getComplain_cancel_red_num();
			if(complain_cancel_red_num!=null && !"".equals(complain_cancel_red_num)){
				complain_cancel_red_numCount+=Integer.parseInt(complain_cancel_red_num);
			}
			
			String consult_yjs=jcSumSuperviseInfoAll2.getConsult_yjs();
			if(consult_yjs!=null && !"".equals(consult_yjs)){
				consult_yjsCount+=Integer.parseInt(consult_yjs);
			}
			String consult_yellow=jcSumSuperviseInfoAll2.getConsult_yellow();
			if(consult_yellow!=null && !"".equals(consult_yellow)){
				consult_yellowCount+=Integer.parseInt(consult_yellow);
			}
			String consult_red=jcSumSuperviseInfoAll2.getConsult_red();
			if(consult_red!=null && !"".equals(consult_red)){
				consult_redCount+=Integer.parseInt(consult_red);
			}
			String consult_cancel_yellow_num=jcSumSuperviseInfoAll2.getConsult_cancel_yellow_num();
			if(consult_cancel_yellow_num!=null && !"".equals(consult_cancel_yellow_num)){
				consult_cancel_yellow_numCount+=Integer.parseInt(consult_cancel_yellow_num);
			}
			String consult_cancel_red_num=jcSumSuperviseInfoAll2.getConsult_cancel_red_num();
			if(consult_cancel_red_num!=null && !"".equals(consult_cancel_red_num)){
				consult_cancel_red_numCount+=Integer.parseInt(consult_cancel_red_num);
			}
			String special_supervise_green_num=jcSumSuperviseInfoAll2.getSpecial_supervise_green_num();
			if(special_supervise_green_num!=null && !"".equals(special_supervise_green_num)){
				special_supervise_green_numCount+=Integer.parseInt(special_supervise_green_num);
			}
			String special_supervise_yellow_num=jcSumSuperviseInfoAll2.getSpecial_supervise_yellow_num();
			if(special_supervise_yellow_num!=null && !"".equals(special_supervise_yellow_num)){
				special_supervise_yellow_numCount+=Integer.parseInt(special_supervise_yellow_num);
			}
			String special_supervise_red_num=jcSumSuperviseInfoAll2.getSpecial_supervise_red_num();
			if(special_supervise_red_num!=null && !"".equals(special_supervise_red_num)){
				special_supervise_red_numCount+=Integer.parseInt(special_supervise_red_num);
			}
			
		}
		
		
		
		if(list.size() > 1){
			JCSumSuperviseInfoAll jcSumSuperviseInfoAll=new JCSumSuperviseInfoAll();
			jcSumSuperviseInfoAll.setArea_name("合计");
			
			jcSumSuperviseInfoAll.setSls(String.valueOf(slsCount));
			
			//计算办结率
			//受理数为0 的时候  办结率为 100%
			if (slsCount==0 && bjsCount!=0) {
				jcSumSuperviseInfoAll.setBjl("100%");
			}else{
				if (slsCount!=0 && bjsCount !=0) { 
					//获得办结率    保留两位小数
					jcSumSuperviseInfoAll.setBjl(nf.format((double)bjsCount/slsCount)+"");	
				}else{
					jcSumSuperviseInfoAll.setBjl("0");
				}
			}
			
			jcSumSuperviseInfoAll.setZcbjs(String.valueOf(zcbjsCount));
			jcSumSuperviseInfoAll.setThbjs(String.valueOf(thbjsCount));
			jcSumSuperviseInfoAll.setZfbjs(String.valueOf(zfbjsCount));
			jcSumSuperviseInfoAll.setScbjs(String.valueOf(scbjsCount));
			jcSumSuperviseInfoAll.setLjsls(String.valueOf(ljslsCount));
			jcSumSuperviseInfoAll.setLjbjs(String.valueOf(ljbjsCount));
			jcSumSuperviseInfoAll.setBysls(String.valueOf(byslsCount));
			jcSumSuperviseInfoAll.setComplain_num(String.valueOf(complain_numCount));
			jcSumSuperviseInfoAll.setComplain_reply_num(String.valueOf(complain_reply_numCount));
			jcSumSuperviseInfoAll.setConsult_num(String.valueOf(consult_numCount));
			jcSumSuperviseInfoAll.setConsult_replay_num(String.valueOf(consult_replay_numCount));
			
			jcSumSuperviseInfoAll.setYjs(String.valueOf(yjsCount));
			jcSumSuperviseInfoAll.setYellow(String.valueOf(yellowCount));
			jcSumSuperviseInfoAll.setRed(String.valueOf(redCount));
			jcSumSuperviseInfoAll.setCancel_yellow_num(String.valueOf(cancel_yellow_numCount));
			jcSumSuperviseInfoAll.setCancel_red_num(String.valueOf(cancel_red_numCount));
			
			jcSumSuperviseInfoAll.setInstance_yjs(String.valueOf(instance_yjsCount));
			jcSumSuperviseInfoAll.setInstance_yellow(String.valueOf(instance_yellowCount));
			jcSumSuperviseInfoAll.setInstance_red(String.valueOf(instance_redCount));
			jcSumSuperviseInfoAll.setInstance_cancel_yellow_num(String.valueOf(instance_cancel_yellow_numCount));
			jcSumSuperviseInfoAll.setInstance_cancel_red_num(String.valueOf(instance_cancel_red_numCount));
			
			jcSumSuperviseInfoAll.setComplain_yjs(String.valueOf(complain_yjsCount));
			jcSumSuperviseInfoAll.setComplain_yellow(String.valueOf(complain_yellowCount));
			jcSumSuperviseInfoAll.setComplain_red(String.valueOf(complain_redCount));
			jcSumSuperviseInfoAll.setComplain_cancel_yellow_num(String.valueOf(complain_cancel_yellow_numCount));
			jcSumSuperviseInfoAll.setComplain_cancel_red_num(String.valueOf(complain_cancel_red_numCount));
			
			jcSumSuperviseInfoAll.setConsult_yjs(String.valueOf(consult_yjsCount));
			jcSumSuperviseInfoAll.setConsult_yellow(String.valueOf(consult_yellowCount));
			jcSumSuperviseInfoAll.setConsult_red(String.valueOf(consult_redCount));
			jcSumSuperviseInfoAll.setConsult_cancel_yellow_num(String.valueOf(consult_cancel_yellow_numCount));
			jcSumSuperviseInfoAll.setConsult_cancel_red_num(String.valueOf(consult_cancel_red_numCount));
			
			jcSumSuperviseInfoAll.setSpecial_supervise_green_num(String.valueOf(special_supervise_green_numCount));
			jcSumSuperviseInfoAll.setSpecial_supervise_yellow_num(String.valueOf(special_supervise_yellow_numCount));
			jcSumSuperviseInfoAll.setSpecial_supervise_red_num(String.valueOf(special_supervise_red_numCount));
			list.add(jcSumSuperviseInfoAll);
		}
		
		
		map.put("dataList", list);
		map.put("titleName","业务发牌统计情况（"+jCSumSuperviseInfoAll.getStart_time().replaceAll("-", "/")+"----"+jCSumSuperviseInfoAll.getEnd_time().replaceAll("-", "/")+"）");

		return map;
	}
	
	
	
	/**
	 * 发牌统计情况excel导出
	 * 
	 * @return
	 */
	public String excelExport1(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) {
		
		Map<String, Object> map= getJCSumSuperviseInfo(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		
		List<JCSumSuperviseInfoAll> list=(List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName=(String) map.get("titleName");
		if(titleName.contains("/")){
			titleName=titleName.replaceAll("/", "-");
		}
		// 创建excel导出对象
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		JCSumSuperviseInfoExport jCSumSuperviseInfoExport=new JCSumSuperviseInfoExport();
		jCSumSuperviseInfoExport.excelExport(titleName, jcList);
		
		return titleName;
	}
	
	/**
	 * 发牌统计word导出
	 * @param org_xzqm
	 * @return
	 */
	public String wordExport1(String org_xzqm,String beginDate,String endDate,String parentCode,String viewFlag) {
		
		Map<String, Object> map= getJCSumSuperviseInfo(org_xzqm, beginDate, endDate,parentCode,viewFlag);
		
		List<JCSumSuperviseInfoAll> list=(List<JCSumSuperviseInfoAll>) map.get("dataList");
		String titleName=(String) map.get("titleName");
		if(titleName.contains("/")){
			titleName=	titleName.replaceAll("/", "-");
		}
		List<List<JCSumSuperviseInfoAll>> jcList = new ArrayList<List<JCSumSuperviseInfoAll>>();
		jcList.add(list);
		
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		
		// 创建word导出对象
		JCSumSuperviseInfoWordExport jCSumSuperviseInfoWordExport=new JCSumSuperviseInfoWordExport();
		jCSumSuperviseInfoWordExport.createWord(configuration, titleName, jcList);
		
		return titleName;
	}
	
	/**
	 * 数据权限查询判断
	 */
	@POST
	@Path("JCSumSuperviseInfo/getOrgCode")
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
			result.put("message", "获取权限失败");
			result.put("success", false);
		}
		return result;
	}
	
	//供排序功能调用
	@GET
	@Path("/getJCSumSuperviseInfoAllBySortCol")
	public Map<String, Object> getJCSumSuperviseInfoAllBySortCol(
			@QueryParam(value = "org_xzqm") String org_xzqm,
			@QueryParam(value = "beginDate") String beginDate,
			@QueryParam(value = "endDate") String endDate,
			@QueryParam(value = "parentCode") String parentCode,
			@QueryParam(value = "viewFlag") String viewFlag,
			@QueryParam(value = "sortCol") String sortCol
			) {
		
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
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoAllByprovince",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.CITY_FLAG);
		} else if ("439900000000".equals(parentCode)) {
			// 查询省本级
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea3",
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
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoAllByCity",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.AREA_FLAG);
		}else if ("org".equals(viewFlag)) {
			// 根据结构查询
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoAll",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
		}else if(parentCode.substring(6).equals("000000") && !parentCode.equals("433199000000") && (!"01".equals(parentCode.substring(4,6)) || parentCode.equals("433101000000") && "area".equals(viewFlag))){
			//区县    行政区划 后六位 为000000,并且不为市直及湘西州州直，吉首市行政区划编码为433101000000，属于区县级，特例
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea",
							jCSumSuperviseInfoAll);
			map.put("viewFlag", ViewFlagConstant.ORG_FLAG);
			
		}else {
			//市直，区县本级、街道
			//市直行政区划 后六位 为000000
			list = DaoFactory
					.create(JCSumSuperviseInfoAll.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAllMapper.selectJCSumSuperviseInfoByArea1",
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
		map.put("titleName","全省发牌统计情况（"+jCSumSuperviseInfoAll.getStart_time().replaceAll("-", "/")+"----"+jCSumSuperviseInfoAll.getEnd_time().replaceAll("-", "/")+"）");

		return map;
	}

}
