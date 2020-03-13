package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.BussinessAllInfo;
import com.chinacreator.dzjc_ruleEngine.SuperviseInfoInstance;
import com.chinacreator.dzjc_ruleEngine.utils.BussinessAllImport;
import com.chinacreator.util.AuditLogUtil;
import com.chinacreator.util.ConstantUtil;
import com.chinacreator.util.RoleUtil;

import com.chinacreator.c2.dao.mybatis.enhance.Rule;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class BussinessAllInfoController {

	@GET
	@Path("bussinessAllInfo/{bussinessId}")
	public BussinessAllInfo get(
			@PathParam(value = "bussinessId") java.lang.String bussinessId) {
		// TODO auto-generated method stub
		return DaoFactory.create(BussinessAllInfo.class)
				.selectByID(bussinessId);
	}

	@GET
	@Path("bussinessAllInfo")
	public Page<BussinessAllInfo> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		// TODO auto-generated method stub
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");

		/* 初始化实体对象 */
		BussinessAllInfo entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, BussinessAllInfo.class)
				: new BussinessAllInfo();
		// 权限控制
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
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if("orgId".equals(listRule.get(i).getField())){
					temporgId=(String) listRule.get(i).getData();
					listRule.remove(i);
					i=0;
					if(!com.chinacreator.util.StringUtil.isBlank(temporgId)){
						entity.setTemporgid(temporgId);
						entity.setOrgId(null);
					}
					if(listRule.size()==0){
						conditions=null;
						break;
					}
				}
				if ("bussinessTime".equals(field) || "superviseTime".equals(field)) {
					String op = listRule.get(i).getOp();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd");
					if ("le".equals(op)) {
						long dataTime = (long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 23:59:59";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
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
			

		} else {
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
			}
			if (entity.getEndDate() != null) {
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
			if (entity.getJcBeginDate() != null) {
				Date beginDate = entity.getJcBeginDate();
				String beginDateStr = sdf.format(beginDate);
				beginDateStr = beginDateStr + " 00:00:01";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(beginDateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setJcBeginDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			if (entity.getJcEndDate() != null) {
				Date endDate = entity.getJcEndDate();
				String endDateStr = sdf.format(endDate);
				endDateStr = endDateStr + " 23:59:59";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(endDateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setJcEndDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		Page<BussinessAllInfo> pagelist = DaoFactory.create(BussinessAllInfo.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);
		SuperviseInfoInstanceController sb= new SuperviseInfoInstanceController();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SuperviseInfoInstance supInstance =new SuperviseInfoInstance();
		double  a = 0;
		double  b = 0;
		//法定所剩时长
		double legal=0;
		//承诺所剩时长
		double commitment=0;
		//每条数据的计算结果
		String result="";
		for (int i = 0; i < pagelist.getContents().size(); i++) {
			/**
			 * 过滤掉投诉咨询
			 */
			if(!pagelist.getContents().get(i).getSuperviseTypeNo().equals("1011")){
					pagelist.getContents().get(i).setProcessLimit("");
					pagelist.getContents().get(i).setPromiseLimit("");
					pagelist.getContents().get(i).setCommitment("");
					pagelist.getContents().get(i).setLegal("");
			}else{
				/**
				 * 计算所剩时长 
				 */
				if(pagelist.getContents().get(i).getSuperviseResultNo().equals("3") 
						&& (pagelist.getContents().get(i).getReplyTime()==null 
								|| pagelist.getContents().get(i).getReplyTime().after(pagelist.getContents().get(i).getSuperviseTime()))){
					
					if(pagelist.getContents().get(i).getReplyTime()==null){
						supInstance.setEndTime("");
					}else{
						supInstance.setEndTime(ft.format(pagelist.getContents().get(i).getReplyTime()==null ? new java.sql.Date(System.currentTimeMillis()) : pagelist.getContents().get(i).getReplyTime()));	
					}
					
					if(pagelist.getContents().get(i).getBussinessTime()==null){
						supInstance.setAcceptTime(null);
					}else{
						supInstance.setAcceptTime(pagelist.getContents().get(i).getBussinessTime());	
					}
					supInstance.setInstanceId(pagelist.getContents().get(i).getBussinessId());
					try {
						result = sb.getUseTime(supInstance);
						
						if(pagelist.getContents().get(i).getProcessLimit()!=null){
							a = Double.parseDouble(pagelist.getContents().get(i).getProcessLimit());
						}
						if(pagelist.getContents().get(i).getPromiseLimit()!=null){
							b = Double.parseDouble(pagelist.getContents().get(i).getPromiseLimit());	
						}
						double date = Double.parseDouble(result);
						//向上取整
						a = Math.ceil(a);
						b = Math.ceil(b);
						//法定所剩时长
						legal=(a-0) - (date-0);
						//承诺所剩时长
						commitment=(b-0) - (date-0);
						pagelist.getContents().get(i).setCommitment(String.valueOf(commitment));
						pagelist.getContents().get(i).setLegal(String.valueOf(legal));
								
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					if(pagelist.getContents().get(i).getProcessLimit()!=null){
						a = Double.parseDouble(pagelist.getContents().get(i).getProcessLimit());
					}
					if(pagelist.getContents().get(i).getPromiseLimit()!=null){
						b =	Double.parseDouble(pagelist.getContents().get(i).getPromiseLimit());	
					}
					//向上取整
					a = Math.ceil(a);
					b = Math.ceil(b);
					//法定所剩时长
					if( pagelist.getContents().get(i).getTimeUse()==null){
						pagelist.getContents().get(i).setLegal("");
					}else{
						legal=(a-0) - pagelist.getContents().get(i).getTimeUse();
						pagelist.getContents().get(i).setLegal(String.valueOf(legal));
					}
					//承诺所剩时长
					if(pagelist.getContents().get(i).getTimeUse()==null){
						pagelist.getContents().get(i).setCommitment("");
					}else{
						commitment=(b-0) - pagelist.getContents().get(i).getTimeUse();
						pagelist.getContents().get(i).setCommitment(String.valueOf(commitment));
					}
				}
			}
		}
		try {
			//记录日志
			AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_JGXX, "getListByPage",
					"",ConstantUtil.OP_OTHER, "全部发牌信息管理");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagelist;
	}

	/**
	 * 自定义获取业务信息
	 * 
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("bussinessAllInfo/getByIdBussinessAllInfo")
	public BussinessAllInfo getByIdBussinessAllInfo(
			@RequestBody Map<String, String> map) {
		String bussinessId = map.get("bussinessId").trim();

		BussinessAllInfo bussinessAllInfo = new BussinessAllInfo();

		bussinessAllInfo = DaoFactory
				.create(BussinessAllInfo.class)
				.getSession()
				.selectOne(
						"com.chinacreator.dzjc_ruleEngine.BussinessAllInfoMapper.selectByID",
						bussinessId);
		return bussinessAllInfo;
	}

	public List<BussinessAllInfo> getAllList(String ids, String cond) {
		List<BussinessAllInfo> BussinessAllInfolist = new ArrayList<BussinessAllInfo>();

		// 创建分页对象
		Pageable pageable = new Pageable(1, 99999);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("", "");
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		BussinessAllInfo entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, BussinessAllInfo.class)
				: new BussinessAllInfo();
				
				// 权限控制
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
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			String temporgId="";
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if("orgId".equals(listRule.get(i).getField())){
					temporgId=(String) listRule.get(i).getData();
					listRule.remove(i);
					i=0;
					if(!com.chinacreator.util.StringUtil.isBlank(temporgId)){
						entity.setTemporgid(temporgId);
						entity.setOrgId(null);
					}
					if(listRule.size()==0){
						conditions=null;
						break;
					}
				}
				
				if ("bussinessTime".equals(field) || "superviseTime".equals(field)) {
					String op = listRule.get(i).getOp();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd");
					if ("le".equals(op)) {
						long dataTime = (long) (listRule.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 23:59:59";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
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

		BussinessAllInfolist=	  DaoFactory.create(BussinessAllInfo.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true).getContents();
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SuperviseInfoInstance supInstance = new SuperviseInfoInstance();
		SuperviseInfoInstanceController sb = new SuperviseInfoInstanceController();
		double  a =0;
		double  b =0;
		//计算结果
		String result="";
		//法定所剩时长
		double legal=0;
		//承诺所剩时长
		double commitment=0;
		for (int j = 0; j < BussinessAllInfolist.size(); j++) {
			/**
			 * 过滤掉投诉咨询
			 */
			if(!BussinessAllInfolist.get(j).getSuperviseTypeNo().equals("1011")){
				BussinessAllInfolist.get(j).setProcessLimit("");
				BussinessAllInfolist.get(j).setPromiseLimit("");
				BussinessAllInfolist.get(j).setCommitment("");
				BussinessAllInfolist.get(j).setLegal("");
			}else{
				/**
				 * 计算所剩时长 
				 */
			if(BussinessAllInfolist.get(j).getSuperviseResultNo().equals("3") && (BussinessAllInfolist.get(j).getReplyTime() == null || BussinessAllInfolist.get(j).getReplyTime().after(BussinessAllInfolist.get(j).getSuperviseTime()))){
				
			if(BussinessAllInfolist.get(j).getReplyTime()==null){
					supInstance.setEndTime("");
			}else{
				supInstance.setEndTime(ft.format(BussinessAllInfolist.get(j).getReplyTime()==null ? new java.sql.Date(System.currentTimeMillis()) : BussinessAllInfolist.get(j).getReplyTime()));	
			}
				
			if(BussinessAllInfolist.get(j).getBussinessTime()==null){
					supInstance.setAcceptTime(null);
			}else{
					supInstance.setAcceptTime(BussinessAllInfolist.get(j).getBussinessTime());	
			}
					supInstance.setInstanceId(BussinessAllInfolist.get(j).getBussinessId());
				
			try {
				 result=sb.getUseTime(supInstance);
			if(BussinessAllInfolist.get(j).getProcessLimit()!=null){
					a=Double.parseDouble(BussinessAllInfolist.get(j).getProcessLimit());	
			}
			if(BussinessAllInfolist.get(j).getPromiseLimit()!=null){
					b=	Double.parseDouble(BussinessAllInfolist.get(j).getPromiseLimit());
			}
			double date = Double.parseDouble(result);
				//法定所剩时长
				 legal=(a-0) - (date-0);
				//承诺所剩时长
				 commitment=(b-0) - (date-0);
				BussinessAllInfolist.get(j).setCommitment(String.valueOf(commitment));
				BussinessAllInfolist.get(j).setLegal(String.valueOf(legal));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			}else{
				
			if(BussinessAllInfolist.get(j).getProcessLimit()!=null){
					 a =  Double.parseDouble(BussinessAllInfolist.get(j).getProcessLimit());
			}
			if(BussinessAllInfolist.get(j).getPromiseLimit()!=null){
					 b = Double.parseDouble(BussinessAllInfolist.get(j).getPromiseLimit());
			}
			//法定所剩时长
			if( BussinessAllInfolist.get(j).getTimeUse()==null){
					BussinessAllInfolist.get(j).setLegal("");
			}else{
					legal=(a-0) - BussinessAllInfolist.get(j).getTimeUse();
					BussinessAllInfolist.get(j).setLegal(String.valueOf(legal));
			}
				
			//承诺所剩时长
			if(BussinessAllInfolist.get(j).getTimeUse()==null){
					BussinessAllInfolist.get(j).setCommitment("");
			}else{
					 commitment=(b-0) - BussinessAllInfolist.get(j).getTimeUse();
					 BussinessAllInfolist.get(j).setCommitment(String.valueOf(commitment));
			}
			
				
		}
			}

		}
	return BussinessAllInfolist;
	}

	/**
	 * excel导出
	 * 
	 * @return
	 */
	public String excelExport(String ids, String cond) {

		List<BussinessAllInfo> list = this.getAllList(ids, cond);

		List<List<BussinessAllInfo>> busList = new ArrayList<List<BussinessAllInfo>>();
		busList.add(list);
		BussinessAllImport bussinessAllImport = new BussinessAllImport();
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
				if ("bussinessTime".equals(field) || "superviseTime".equals(field)) {
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
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						beginDate = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						beginStr="1";
					}
				}
	
			}
			
			if("1".equals(beginStr) && "1".equals(endStr)){
				titleName="全部发牌信息管理"+beginDate+"——"+endDate;
			}else if("1".equals(beginStr) && "0".equals(endStr)){
				titleName="全部发牌信息管理"+beginDate+"——"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			}else if("0".equals(beginStr) && "1".equals(endStr)){
				titleName="全部发牌信息管理"+endDate;
			}else{
				titleName="全部发牌信息管理"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			}
		}else{
			titleName="全部发牌信息管理"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		}
		

		// 创建excel导出对象
		bussinessAllImport.excelExport(titleName, busList);

		return titleName;

	}

}
