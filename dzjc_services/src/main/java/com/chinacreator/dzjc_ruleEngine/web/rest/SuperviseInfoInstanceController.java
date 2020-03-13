package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.CommonTreeNode;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_ruleEngine.SuperviseInfoInstance;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.ElementInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.ElementInfoDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.StringUtil;
import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;
import com.chinacreator.dzjc_ruleEngine.utils.InstanceExport;
import com.chinacreator.dzjc_tongji.DevSupervise;
import com.chinacreator.dzjc_tongji.JcOrgView;
import com.chinacreator.util.AuditLogUtil;
import com.chinacreator.util.ConstantUtil;
import com.chinacreator.util.HolidayDateUtil;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SuperviseInfoInstanceController {

	@GET
	@Path("SuperviseInfoInst/{superviseInfoId}")
	public SuperviseInfoInstance get(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		
		SuperviseInfoInstance superviseInfoInstance=DaoFactory.create(SuperviseInfoInstance.class).selectByID(
				superviseInfoId);
		/*2019-08-31 zhl修改
		 * 特殊处理，法定时限和承诺时限显示发牌表中的，如果未发牌则显示事项中的法定时限和承诺时限
		 * 
		DevSupervise devSupervise = new DevSupervise();
		devSupervise.setBusinessId(superviseInfoInstance.getInstanceId());
		devSupervise.setBusinessType("1");//只查询办件发牌
		List<DevSupervise> devSuperviseList = DaoFactory.create(DevSupervise.class).select(devSupervise);
		if(devSuperviseList!=null){
			for (DevSupervise temp : devSuperviseList) {
				if(temp.getPromiseLimit()!=null&&temp.getProcessLimit()!=null){
					superviseInfoInstance.setPromisesLimit(temp.getPromiseLimit().toString());//承诺时限
					superviseInfoInstance.setTimeLimit(temp.getProcessLimit().toString());//法定时限
					break;
				}
			}
		}
		*/
		return superviseInfoInstance;
	}

	@GET
	@Path("SuperviseInfoInst")
	public Page<SuperviseInfoInstance> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		long beginM=System.currentTimeMillis();
		// TODO auto-generated method stub
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		//查询机构本级下面所有的办件监察
		/* 初始化实体对象 */
		SuperviseInfoInstance entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SuperviseInfoInstance.class)
				: new SuperviseInfoInstance();
		String code = null;
		try {
			// 查询当前登录用户编号(行政区码)
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		if (entity != null) {
	
			if ("".equals(code)) {
				return null;
			}
	
			entity.setOrgId(code);
		}
		String temporgId="";
		if(conditions!=null){
			//查询该机构下所有办件 ,因为省机构数据过大,所以不适用本身的高级查询
			List<Rule> listRule= conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field= listRule.get(i).getField();
				if("orgId".equals(field)){
					temporgId=(String) listRule.get(i).getData();
					listRule.remove(i);
					if(!com.chinacreator.util.StringUtil.isBlank(temporgId)){
						entity.setTemporgid(temporgId);
						entity.setOrgId(null);
					}
					if(listRule.size()==0){
						conditions=null;
						break;
					}
				}
				if("acceptTime".equals(field)){
	//{"beginDate":$params.beginDate,"endDate":$params.endDate,"beginJcDate":$params.beginJcDate,"endJcDate":$params.endJcDate}
					String op=listRule.get(i).getOp();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if("le".equals(op)){
						long dataTime=(long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long.parseLong(String
								.valueOf(dataTime))));
						dataTimeStr=dataTimeStr+" 23:59:59";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataTimeStr);
							 long ts = date1.getTime();
							 listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}else if("ge".equals(op)){
						long dataTime = (long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 00:00:01";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
							long ts = date1.getTime();
							listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}else if ("superviseTime".equals(field)) {
					String op=listRule.get(i).getOp();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if("le".equals(op)){
						long dataTime=(long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long.parseLong(String
								.valueOf(dataTime))));
						dataTimeStr=dataTimeStr+" 23:59:59";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataTimeStr);
							 long ts = date1.getTime();
							 listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}else if("ge".equals(op)){
						long dataTime = (long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 00:00:01";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
							long ts = date1.getTime();
							listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			/*if (entity.getBeginDate() != null) {
				Date beginDate = entity.getBeginDate();
				String beginDateStr = sdf.format(beginDate);
				beginDateStr = beginDateStr + " 00:00:01";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(beginDateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setBeginDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}*/
			if (entity.getBeginJcDate() != null) {
				Date beginDate = entity.getBeginJcDate();
				String beginDateStr = sdf.format(beginDate);
				beginDateStr = beginDateStr + " 00:00:01";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(beginDateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setBeginJcDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			/*if (entity.getEndDate() != null) {
				Date endDate = entity.getEndDate();
				String endDateStr = sdf.format(endDate);
				endDateStr = endDateStr + " 23:59:59";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(endDateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setEndDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}*/
			if (entity.getEndJcDate() != null) {
				Date endDate = entity.getEndJcDate();
				String endDateStr = sdf.format(endDate);
				endDateStr = endDateStr + " 23:59:59";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(endDateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setEndJcDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}

		}
		Page<SuperviseInfoInstance> selectPageByCondition = DaoFactory.create(SuperviseInfoInstance.class)
		.selectPageByCondition(entity, conditions, pageable, sortable,
				true);
		System.out.println(System.currentTimeMillis()-beginM);
		
		try {
			//记录日志
			AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_JGXX, "getListByPage",
					"",ConstantUtil.OP_OTHER, "办件监管信息管理");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return selectPageByCondition;
	}

	/**
	 * excel导出
	 * 
	 * @return
	 */
	public String excelExport(String cond) {

		List<SuperviseInfoInstance> list = this.getList(cond);

		List<List<SuperviseInfoInstance>> busList = new ArrayList<List<SuperviseInfoInstance>>();
		busList.add(list);

		InstanceExport instanceexport = new InstanceExport();
		
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		String titleName="";
		String beginDate="";
		String endDate="";
		String beginStr="0";
		String endStr="0";
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("acceptTime".equals(field)) {
					String op = listRule.get(i).getOp();
					if ("le".equals(op)) {
						long dataTime = (long) (listRule.get(i).getData());
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						endDate = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						endStr="1";
						
					}else if("ge".equals(op)){
						long dataTime = (long) (listRule.get(i).getData());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						beginDate = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						beginStr="1";
					}
				}
				if ("superviseTime".equals(field)) {
					String op = listRule.get(i).getOp();
					if ("le".equals(op)) {
						long dataTime = (long) (listRule.get(i).getData());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						endDate = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						endStr="1";
						
					}else if("ge".equals(op)){
						long dataTime = (long) (listRule.get(i).getData());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						beginDate = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						beginStr="1";
					}
				}
				
			}
			
			if("1".equals(beginStr) && "1".equals(endStr)){
				
				titleName="办件监管信息情况表"+beginDate+"——"+endDate;
			}else if("1".equals(beginStr) && "0".equals(endStr)){
				
				titleName="办件监管信息情况表"+beginDate+"——"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			}else if("0".equals(beginStr) && "1".equals(endStr)){

				titleName="办件监管信息情况表（截止"+endDate;
			}else{
			
				titleName="办件监管信息情况表（截止"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			}
		}else{
			
			titleName="办件监管信息情况表（截止"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		}
		

		// 创建excel导出对象
		instanceexport.excelExport(titleName, busList);
		return titleName;

	}

	/**
	 * 导出时条件查询
	 * 
	 * @param cond
	 * @return
	 */
	public List<SuperviseInfoInstance> getList(String cond) {
		// TODO auto-generated method stub
		// 创建分页对象
		Pageable pageable = new Pageable(1, 9999999);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("", "");
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		SuperviseInfoInstance entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SuperviseInfoInstance.class)
				: new SuperviseInfoInstance();
					String code="";
					try {
						// 查询当前登录用户编号(行政区码)
						code = RoleUtil.getInstance().queryCodeByUserId();
					} catch (Exception e) {
						e.printStackTrace();
					}
				
					if (entity != null) {
				
						if ("".equals(code)) {
							return null;
						}
				
						entity.setOrgId(code);
					}
					String pid="";
		if(conditions!=null){
			List<Rule> listRule= conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				
				String field= listRule.get(i).getField();
				if("orgId".equals(field)){
					pid=(String) listRule.get(i).getData();
					listRule.remove(i);
					if(!com.chinacreator.util.StringUtil.isBlank(pid)){
						entity.setTemporgid(pid);
						entity.setOrgId(null);
					}
					if(listRule.size()==0){
						conditions=null;
						break;
					}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if("acceptTime".equals(field)){
					String op=listRule.get(i).getOp();
					if("le".equals(op)){
						long dataTime=(long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long.parseLong(String
								.valueOf(dataTime))));
						dataTimeStr=dataTimeStr+" 23:59:59";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataTimeStr);
							 long ts = date1.getTime();
							 listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}else if("ge".equals(op)){
						long dataTime = (long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 00:00:01";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
							long ts = date1.getTime();
							listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}else if("superviseTime".equals(field)){
					String op=listRule.get(i).getOp();
					if("le".equals(op)){
						long dataTime=(long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long.parseLong(String
								.valueOf(dataTime))));
						dataTimeStr=dataTimeStr+" 23:59:59";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataTimeStr);
							 long ts = date1.getTime();
							 listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}else if("ge".equals(op)){
						long dataTime = (long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 00:00:01";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
							long ts = date1.getTime();
							listRule.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					
				} 
			}
			
		}

		
		return DaoFactory
				.create(SuperviseInfoInstance.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true).getContents();

	}

	/**
	 * 树形数据源查询当前登录用户下的所有机构
	 * 
	 * @return
	 */
	@POST
	@Path("GetOrgTree")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TreeNode[] getOrgTree() {

		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();

		// 获取登录人信息
//		String code = null;
		try {
			// 查询当前登录用户编号(行政区码)
//			code = RoleUtil.getInstance().queryCodeByUserId();
			JcOrgView userOrg = RoleUtil.getInstance().getUserOrgId();
			
			List<Orgview> orgList = DaoFactory
					.create(Orgview.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_complain.OrgviewMapper.selectChildrenOrgInfo",
							userOrg.getOrgId());
			CommonTreeNode orgTreeNode = null;
			for (int i = 0; i < orgList.size(); i++) {
				orgTreeNode = new CommonTreeNode();
				orgTreeNode.setId(orgList.get(i).getId());
				orgTreeNode.setShowName(orgList.get(i).getName());
				orgTreeNode.setName(orgList.get(i).getName());
				orgTreeNode.setPid(orgList.get(i).getPid());
				list.add(orgTreeNode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (TreeNode[]) list.toArray(new CommonTreeNode[list.size()]);
	}
	
	private static String internet_time_limit = ConfigManager.getInstance().getConfig("internet_time_limit");
	
	@POST
	@Path("allInfoWebUseTime")
	public Map<String,Object> allInfoWebUseTime(SuperviserInfo info) throws Exception{
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> params= new HashMap<>();
		SuperviserInfo selectOne = DaoFactory.create(SuperviserInfo.class).getSession()
				.selectOne("com.chinacreator.dzjc_ruleEngine.SuperviserInfoMapper.findAllInfo", info);
		String outDays=selectOne.getOutDays();
		if("3".equals(selectOne.getSuperviseResultNo()) &&(selectOne.getEndTime()==null 
				|| com.chinacreator.util.DateUtil.isLater(selectOne.getEndTime(),DateUtil.parseStr(selectOne.getSuperviseTime())))){
			Map<String,Object> infonew= new HashMap<>();
			infonew.put("bussinessId", selectOne.getBusinessId());
			infonew.put("approveId", selectOne.getApproveId());
			infonew.put("submitTime", ft.format(new Date(selectOne.getStartTime().getTime())));
			outDays=getWebUseTime(infonew);
		}
		params.put("timeLimit",selectOne.getProcessLimit());
		params.put("promisesLimit",selectOne.getPromiseLimit());
		params.put("outDays", outDays);
		return params;
	}
	
	@POST
	@Path("getWebUseTime")
	public String getWebUseTime(Map<String,Object> info){
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String bussinessId = StringUtil.DeNull(info.get("bussinessId"));
		String approveId = StringUtil.DeNull(info.get("approveId"));
		InstanceInfo entity = DaoFactory.create(InstanceInfo.class).getSession()
			.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectInternetByInstanceId",
					Arrays.asList(new String[]{bussinessId+"##"+approveId}));
		int timeLimit = 2;
		if(!StringUtils.isBlank(internet_time_limit)){
			timeLimit = Integer.parseInt(internet_time_limit);
		}
		String submitTime=StringUtil.DeNull(info.get("submitTime"));
		String finishTime = "";
		if(entity.getPretrialTime() == null){
			finishTime = DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss");
		}else{
			finishTime = ft.format(entity.getPretrialTime());
		}
		long dateInv = 0l;
		long remainInv = 0l;
		long standTimeInv = 0l;
		long diffTime = 0l;
		long[] invTimes = {0,0};
		try {
			//计算两个日期值相差天数及秒长
			invTimes = HolidayDateUtil.getInvTimesWithTime(submitTime,finishTime,
					entity.getWorkTime(),entity.getHolidayCount());
			//获取1个工作日标准秒长
			standTimeInv = HolidayDateUtil.getStandardRemainDiff(submitTime.substring(0,10),
					entity.getWorkTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//实际耗时与默认时限差值
		diffTime = ((invTimes[0]*standTimeInv)+invTimes[1]) - timeLimit*standTimeInv; 
		if(standTimeInv == 0){
			dateInv = 0;
			remainInv = 0;
		}
		else {
			dateInv = diffTime/standTimeInv; //计算第4步
			remainInv = diffTime%standTimeInv; //计算第4步
		}
		if(remainInv > 0l){ //如果还有余数，则天数加1
			dateInv = dateInv + 1;
		}
		return String.valueOf(dateInv);
	}
	
	
	@POST
	@Path("getUseTime")
	public String getUseTime(SuperviseInfoInstance supInstance) throws Exception{
		//如果没有受理时间，直接返回0，网上办事监察，可能没有受理时间
		if(supInstance.getAcceptTime() == null){
			return "0";
		}
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long dateInv = 0l;
		long timeInv = 0l;
		long remainInv = 0l;
		long standTimeInv = 0l;
		String finishTime = "";
		if( supInstance !=null && StringUtil.isBlank(supInstance.getEndTime())){
			finishTime=ft.format(new Date());
		}else{
			finishTime=supInstance.getEndTime();
			if(finishTime.indexOf("-")==-1){
				finishTime=ft.format(new Date(Long.parseLong(finishTime)));
			}
		}
		String acceptTime=ft.format(supInstance.getAcceptTime());
		String instanceId=supInstance.getInstanceId();
		standTimeInv = HolidayDateUtil.getStandardRemainDiff(acceptTime.substring(0,10));//获取1个工作日标准秒长
		long[] invTimes = HolidayDateUtil.getInvTimesWithTime(acceptTime,finishTime);//计算第1步与第2步
		long[] splTimes = procSpecialTime1(instanceId,standTimeInv); //计算第3步
		timeInv = ((invTimes[0]*standTimeInv)+invTimes[1]) - ((splTimes[0]*standTimeInv)+splTimes[1]);//计算第4步
		dateInv = timeInv/standTimeInv; //计算第4步
		remainInv = timeInv%standTimeInv; //计算第4步
		if(remainInv > 0l){ //计算第4步，如果还有余数，则差异天数加1
			dateInv = dateInv + 1;
		}
		return String.valueOf(dateInv);
	}

	public static long[] procSpecialTime1(String instanceId,long standTimeInv) throws Exception {
		ElementInfoDao elementInfoDao = new ElementInfoDaoImpl();
		long[] specialTime = new long[2];
		long splDate = 0l;
		long splTime = 0l;
		long dateInv = 0l;
		long timeInv = 0l;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
		    List <TbcxsqInfoBean> tbcxsqList = elementInfoDao.getTbcxsqInfo(instanceId);
	
			for(int i=0; i<tbcxsqList.size(); i++){
				//String tscxsqId = tbcxsqList.get(i).getSuspendId();
				String startDate = ft.format(tbcxsqList.get(i).getStartDate());
				String endDate = tbcxsqList.get(i).getEndDate() == null ? "" : ft.format(tbcxsqList.get(i).getEndDate());
				if("".equals(endDate) || endDate == null) {
					endDate = DateUtil.getCurrentDateTime();
				}
				long[] invTimes = HolidayDateUtil.getInvTimesWithTime(startDate,endDate);
				dateInv = invTimes[0];
				timeInv = invTimes[1];
				//如果特别程序时间已经超过申请的天数，则以申请的天数计算
				if((dateInv + timeInv/standTimeInv) >= tbcxsqList.get(i).getSpecialTimelimit()){
					dateInv = tbcxsqList.get(i).getSpecialTimelimit();
					timeInv = 0l;
				}
				splDate = splDate + dateInv;
				splTime = splTime + timeInv;
			}
			specialTime[0] = splDate;
			specialTime[1] = splTime;
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return specialTime;
	}
}
