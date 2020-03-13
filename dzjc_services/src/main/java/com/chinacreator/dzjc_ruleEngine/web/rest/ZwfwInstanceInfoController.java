package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
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
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.ApprovetypeNew;
import com.chinacreator.dzjc_ruleEngine.ZwfwInstanceInfo;
import com.chinacreator.dzjc_ruleEngine.utils.ZwfwInstanceExcelExport;
import com.chinacreator.dzjc_tongji.ExportExcelUtils;
import com.chinacreator.dzjc_tongji.JcOrgView;
import com.chinacreator.dzjc_tongji.TongJi;
import com.chinacreator.dzjc_tongji.service.CacheService;
import com.chinacreator.util.AuditLogUtil;
import com.chinacreator.util.ConstantUtil;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ZwfwInstanceInfoController {
	@Autowired
	private CacheService cacheService;

	@GET
	@Path("ZwfwInstanceInfo")
	public Page<ZwfwInstanceInfo> getListByPage(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		
		ZwfwInstanceInfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, ZwfwInstanceInfo.class) : new ZwfwInstanceInfo();
		
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		
		initialEntity(conditions, entity);
		if (entity == null) {
			return new Page<ZwfwInstanceInfo>();
		}
		try {
			//记录日志
			AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_JGXX, "getListByPage","",ConstantUtil.OP_OTHER, "综合业务信息管理");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotBlank(entity.getOrgId())){
			String orgCode = null;
			JcOrgView orgView = DaoFactory.create(JcOrgView.class).selectByID(entity.getOrgId());
			if(orgView!=null){
				if(orgView.getOrgLevel()==5){
				}else if(orgView.getOrgLevel()==4){
					orgCode = orgView.getOrgCode().substring(0, 8);
				}else if(orgView.getOrgLevel()==3){
					orgCode = orgView.getOrgCode().substring(0, 6);
				}else if(orgView.getOrgLevel()==2){
					orgCode = orgView.getOrgCode().substring(0, 4);
				}else if(orgView.getOrgLevel()==1){
					orgCode = orgView.getOrgCode().substring(0, 2);
				}
				entity.setOrgCode(orgCode);
			}
		}
		/*区级机构使用区域编码查询，职能机构使用机构id递归查询*/
		if(StringUtils.isNotBlank(entity.getOrgCode())){
			entity.setOrgId(null);
		}
		Page<ZwfwInstanceInfo> selectPageByCondition = DaoFactory.create(ZwfwInstanceInfo.class).selectPageByCondition(entity, null, pageable, sortable, true);
		if(selectPageByCondition.getContents()!=null&&selectPageByCondition.getContents().size()>0){
			Map<String, ApprovetypeNew> approvetypeMap = cacheService.getApprovetypeNewMap();
			if(approvetypeMap==null)approvetypeMap = new HashMap<>();
			for (ZwfwInstanceInfo instanceInfo : selectPageByCondition.getContents()) {
				if(StringUtils.isNotBlank(instanceInfo.getSuperviseResultNo())){
					instanceInfo.setState("已发");
					instanceInfo.setSuperviseTypeNo("1011");
					instanceInfo.setSuperviseTypeName("办件时限监管");
				}
				ApprovetypeNew approvetypeNew = approvetypeMap.get(instanceInfo.getApproveTypeCode());
				if(approvetypeNew!=null){
					instanceInfo.setApproveTypeCodeName(approvetypeNew.getTypeName());
				}
			}
		}
		return selectPageByCondition;
	}

	public String excelExport(String cond) {
		//获得要导出的数据
		List<ZwfwInstanceInfo> list = queryExportList(cond);
		List<List<ZwfwInstanceInfo>> excelList = new ArrayList<>();
		excelList.add(list);
		//获得要导出的文件名
		String titleName = getTitleName();
		new ZwfwInstanceExcelExport().excelExport(titleName, excelList);
		return titleName;
	}
	
	//用于获取要导出的文件名，在initialEntity方法中初始化
	private String beginDate = "";
	private String endDate = "";
	private boolean hasBegin = false;
	private boolean hasEnd = false;

	private String getTitleName() {
		String titleName = "全部办件查询";
		if (hasBegin && hasEnd) {
			titleName =  titleName + beginDate + "——" + endDate;
		} else if (hasBegin && !hasEnd) {
			titleName = titleName + beginDate + "——" + new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		} else if (!hasBegin && hasEnd) {
			titleName = titleName + "截止到" +endDate;
		} else {
			titleName = titleName + "截止到" + new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		}
		return titleName;
	}

	/**
	 * @param request
	 * @param response
	 */
	@GET
	@Path("zwfwinstanceinfocontroller_exp_excel")
	public void expExcelToBanJianTongJi(@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		String cond = request.getParameter("cond");
		List<ZwfwInstanceInfo> list = queryExportList(cond);
		List<List<ZwfwInstanceInfo>> excelList = new ArrayList<>();
		excelList.add(list);
		String fileName = getTitleName();
		byte[] excelFile = new ZwfwInstanceExcelExport().excelExport(fileName, excelList);
		ExportExcelUtils.download(request,response, excelFile, fileName+".xls");
	}
	
	private List<ZwfwInstanceInfo> queryExportList(String cond) {
		//创建分页对象 
		Pageable pageable = new Pageable(1, 99999);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable("acceptTime", "desc");
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		ZwfwInstanceInfo entity = new ZwfwInstanceInfo();
		initialEntity(conditions, entity);
		if (entity == null) {
			return new ArrayList<ZwfwInstanceInfo>();
		}
		if(StringUtils.isNotBlank(entity.getOrgId())){
			String orgCode = null;
			JcOrgView orgView = DaoFactory.create(JcOrgView.class).selectByID(entity.getOrgId());
			if(orgView!=null){
				if(orgView.getOrgLevel()==5){
				}else if(orgView.getOrgLevel()==4){
					orgCode = orgView.getOrgCode().substring(0, 8);
				}else if(orgView.getOrgLevel()==3){
					orgCode = orgView.getOrgCode().substring(0, 6);
				}else if(orgView.getOrgLevel()==2){
					orgCode = orgView.getOrgCode().substring(0, 4);
				}else if(orgView.getOrgLevel()==1){
					orgCode = orgView.getOrgCode().substring(0, 2);
				}
				entity.setOrgCode(orgCode);
			}
		}
		/*区级机构使用区域编码查询，职能机构使用机构id递归查询*/
		if(StringUtils.isNotBlank(entity.getOrgCode())){
			entity.setOrgId(null);
		}
		Page<ZwfwInstanceInfo> dataList = DaoFactory.create(ZwfwInstanceInfo.class).selectPageByCondition(entity, null, pageable, sortable, true);
		if(dataList.getContents()!=null&&dataList.getContents().size()>0){
			Map<String, ApprovetypeNew> approvetypeMap = cacheService.getApprovetypeNewMap();
			if(approvetypeMap==null)approvetypeMap = new HashMap<>();
			for (ZwfwInstanceInfo instanceInfo : dataList.getContents()) {
				if(StringUtils.isNotBlank(instanceInfo.getSuperviseResultNo())){
					if("1".equals(instanceInfo.getSuperviseResultNo())){
						instanceInfo.setSuperviseResultName("预警");
					}else if("2".equals(instanceInfo.getSuperviseResultNo())){
						instanceInfo.setSuperviseResultName("黄牌");
					}else if("3".equals(instanceInfo.getSuperviseResultNo())){
						instanceInfo.setSuperviseResultName("红牌");
					}
					instanceInfo.setState("已发");
					instanceInfo.setSuperviseTypeNo("1011");
					instanceInfo.setSuperviseTypeName("办件时限监管");
				}
				ApprovetypeNew approvetypeNew = approvetypeMap.get(instanceInfo.getApproveTypeCode());
				if(approvetypeNew!=null){
					instanceInfo.setApproveTypeCodeName(approvetypeNew.getTypeName());
				}
			}
			return dataList.getContents();
		}
		return new ArrayList<ZwfwInstanceInfo>();
	}
	
	/*
	 * 将查询条件封装到实体对象
	 */
	private void initialEntity(Conditions conditions, ZwfwInstanceInfo entity) {
		if (conditions != null) {
			List<Rule> rules = conditions.getRules();
			for (Rule item : rules) {
				String field = item.getField();
				Object obj = item.getData();
				switch (field) {
					case "instanceCode":
						entity.setInstanceCode((String) obj);
						break;
					case "instanceName":
						entity.setInstanceName((String) obj);
						break;
					case "approveTypeCode":
						entity.setApproveTypeCode((String) obj);
						break;
					case "processingState":
						entity.setProcessingState((String) obj);
						break;
					case "superviseResultNo":
						if(obj!=null){
							entity.setSuperviseResultNo(obj.toString().trim());
						}
						break;
					case "orgId":
						entity.setOrgId((String) obj);
						break;
					case "approveName":
						entity.setApproveName((String) obj);
						break;
					case "beginDate":
						beginDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date((long) obj));
						hasBegin = true;
						entity.setBeginDate(formatDate((long) obj, " 00:00:01"));
						break;
					case "endDate":
						endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date((long) obj));
						hasEnd = true;
						entity.setEndDate(formatDate((long) obj, " 23:59:59"));
						break;
					case "acceptBeginDate":
						beginDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date((long) obj));
						hasBegin = true;
						entity.setAcceptBeginDate(formatDate((long) obj, " 00:00:01"));
						break;
					case "acceptEndDate":
						endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date((long) obj));
						hasEnd = true;
						entity.setAcceptEndDate(formatDate((long) obj, " 23:59:59"));
						break;
					default:
						break;
				}
			}
		}
		if(StringUtils.isBlank(entity.getOrgId())){
			JcOrgView userOrgView = RoleUtil.getInstance().getUserOrgId();
			if(userOrgView!=null){
				entity.setOrgId(userOrgView.getOrgId());
			}else{
				/* 权限控制*/
				String code = null;
				try {
					/*查询当前登录用户编号(行政区码)*/
					code = RoleUtil.getInstance().queryCodeByUserId();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if ( code == null || "".equals(code)) {
					entity = null;
					return;
				}
				entity.setAreaCode(code);
			}
		}
	}
	
	private java.sql.Date formatDate(long dataTime, String hmsStr) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf1.format(new Date(Long.parseLong(String.valueOf(dataTime))));
		dateStr += hmsStr;
		java.util.Date utilDate = null;
		java.sql.Date sqlDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
			sqlDate = new java.sql.Date(utilDate.getTime());
			sdf2.format(sqlDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return sqlDate;
	}
}
