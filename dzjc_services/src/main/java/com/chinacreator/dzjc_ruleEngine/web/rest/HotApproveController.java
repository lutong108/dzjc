package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
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
import com.chinacreator.dzjc_ruleEngine.HotApprove;
import com.chinacreator.dzjc_ruleEngine.utils.HotApproveExport;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class HotApproveController {

	@GET
	@Path("HotApprove")
	public Page<HotApprove> getListByPage(@QueryParam(value = "page") int page,
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
		HotApprove entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, HotApprove.class) : new HotApprove();
		
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("beginDate".equals(field)) {
					long dataTime = (long) (listRule.get(i).getData());
					entity.setBeginDate(formatDate(dataTime, " 00:00:01"));
				}
				if ("endDate".equals(field)) {
					long dataTime = (long) (listRule.get(i).getData());
					entity.setEndDate(formatDate(dataTime, " 23:59:59"));
				}
				if ("approveName".equals(field)) {
					entity.setApproveName((String)listRule.get(i).getData());
				}
				if ("orgId".equals(field)) {
					entity.setOrgId((String)listRule.get(i).getData());
				}
			}
		} else {
			long endTime = new Date().getTime();
			long beginTime = endTime - 7*24*3600*1000;
			entity.setEndDate(formatDate(endTime, " 23:59:59"));
			entity.setBeginDate(formatDate(beginTime, " 00:00:01"));
		}
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
			entity.setAreaCode(code);
		}
		return DaoFactory.create(HotApprove.class).selectPageByCondition(entity, null, pageable, sortable, true);
	}

	public String excelExport(String cond) {
		List<HotApprove> list = this.getList(cond);
		
		List<List<HotApprove>> busList = new ArrayList<List<HotApprove>>();
		busList.add(list);
		
		HotApproveExport hotApproveExport = new HotApproveExport();
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		String titleName="", beginDate="", endDate="", beginStr="0", endStr="0";
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("beginDate".equals(field)) {
					long dataTime = (long) (listRule.get(i).getData());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					beginDate = sdf.format(new Date(Long.parseLong(String.valueOf(dataTime))));
					beginStr="1";
				}
				if ("endDate".equals(field)) {
					long dataTime = (long) (listRule.get(i).getData());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					endDate = sdf.format(new Date(Long.parseLong(String.valueOf(dataTime))));
					endStr="1";
				}
			}
			if ("1".equals(beginStr) && "1".equals(endStr)) {
				titleName = "高频事项办理分析表（"+beginDate+"——"+endDate+ "）";
			} else if ("1".equals(beginStr) && "0".equals(endStr)) {
				titleName = "高频事项办理分析表（"+beginDate+"——"+new SimpleDateFormat("YYYY年MM月dd日").format(new Date())+ "）";
			} else if ("0".equals(beginStr) && "1".equals(endStr)){
				titleName = "高频事项办理分析表（截止"+endDate+ "）";
			} else {
				titleName = "高频事项办理分析表（截止"+new SimpleDateFormat("YYYY年MM月dd日").format(new Date())+ "）";
			}
		} else {
			titleName = "高频事项办理分析表（截止"+new SimpleDateFormat("YYYY年MM月dd日").format(new Date())+ "）";
		}
		//创建excel导出对象
		hotApproveExport.excelExport(titleName, busList);
		return titleName;
	}

	private List<HotApprove> getList(String cond) {
		// 创建分页对象
		Pageable pageable = new Pageable(1, 9999999);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("","");
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		HotApprove entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, HotApprove.class) : new HotApprove();
		
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("beginDate".equals(field)) {
					long dataTime = (long) (listRule.get(i).getData());
					entity.setBeginDate(formatDate(dataTime, " 00:00:01"));
				}
				if ("endDate".equals(field)) {
					long dataTime = (long) (listRule.get(i).getData());
					entity.setEndDate(formatDate(dataTime, " 23:59:59"));
				}
				if ("approveName".equals(field)) {
					entity.setApproveName((String)listRule.get(i).getData());
				}
				if ("orgId".equals(field)) {
					entity.setOrgId((String)listRule.get(i).getData());
				}
			}
		} 
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
			entity.setAreaCode(code);
		}
		return DaoFactory.create(HotApprove.class).selectPageByCondition(entity, null, pageable, sortable, true).getContents();
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
