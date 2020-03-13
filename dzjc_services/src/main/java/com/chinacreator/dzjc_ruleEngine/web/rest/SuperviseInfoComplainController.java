package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_ruleEngine.SuperviseInfoComplain;
import com.chinacreator.dzjc_ruleEngine.utils.ComplainExport;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SuperviseInfoComplainController {

	@GET
	@Path("SuperviseInfoComplain/{complainId}")
	public SuperviseInfoComplain get(
			@PathParam(value = "complainId") java.lang.String complainId) {
		SuperviseInfoComplain entity=DaoFactory.create(SuperviseInfoComplain.class).selectByID(
				complainId);
		
		if (entity != null) {
			String org_id = entity.getBycomplainedUserAddress();
			List<Orgview> orgList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgAndPi", org_id);

			if (orgList.size() > 0) {

				String orgLongName = "";
				for (Orgview orgView : orgList) {
					if (orgView.getName().indexOf("本级") == -1) {
						orgLongName += orgView.getName();
					}
				}
				entity.setBycomplainedUserAddress(orgLongName);
			}

			// 获取是否满意（Y：满意；M：比较满意 ；N：不满意）
			String isSatisfied = entity.getIsSatisfied();
			if ("Y".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("满意");
			} else if ("M".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("比较满意");
			} else if ("N".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("不满意");
			}

			// 获取投诉方式
			String problemMode = entity.getProblemMode();
			if ("1".equals(problemMode)) {
				entity.setProblemMode("网络");
			} else if ("2".equals(problemMode)) {
				entity.setProblemMode("电话");
			} else if ("3".equals(problemMode)) {
				entity.setProblemMode("来访");
			} else if ("4".equals(problemMode)) {
				entity.setProblemMode("函件");
			} else if ("5".equals(problemMode)) {
				entity.setProblemMode("批办");
			}

		}
		return entity;
	}

	/**
	 * excel导出
	 * 
	 * @param cond
	 * @return
	 */
	public String excelExport(String cond) {

		List<SuperviseInfoComplain> list = this.getList(cond);
		List<List<SuperviseInfoComplain>> busList = new ArrayList<List<SuperviseInfoComplain>>();
		busList.add(list);
		
		ComplainExport complainExport = new ComplainExport();
		
	
		
		
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
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
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
				titleName="投诉监管信息情况表"+beginDate+"——"+endDate;
			}else if("1".equals(beginStr) && "0".equals(endStr)){
				titleName="投诉监管信息情况表"+beginDate+"——"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			}else if("0".equals(beginStr) && "1".equals(endStr)){
				titleName="投诉监管信息情况表"+endDate;
			}else{
				titleName="投诉监管信息情况表"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			}
		}else{
				titleName="投诉监管信息情况表"+new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		}
		
		
		//创建excel导出对象
		complainExport.excelExport(titleName, busList);
		

		return titleName;
	}

	/**
	 * 导出时带条件查询
	 * 
	 * @param cond
	 * @return
	 */
	public List<SuperviseInfoComplain> getList(String cond) {
		// 创建分页对象
		Pageable pageable = new Pageable(1, 9999999);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("", "");
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");

		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("acceptTime".equals(field) || "superviseTime".equals(field)) {
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
		
		/* 初始化实体对象 */
		SuperviseInfoComplain entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SuperviseInfoComplain.class)
				: new SuperviseInfoComplain();

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

			entity.setReplyOrgid(code);
		}
		 List<SuperviseInfoComplain> list =null;
	list=	 DaoFactory
				.create(SuperviseInfoComplain.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true).getContents();
	return list;
	}

	@GET
	@Path("SuperviseInfoComplain")
	public Page<SuperviseInfoComplain> getListByPage(
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
		SuperviseInfoComplain entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SuperviseInfoComplain.class)
				: new SuperviseInfoComplain();
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("acceptTime".equals(field)) {
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

			entity.setReplyOrgid(code);
		}

		return DaoFactory.create(SuperviseInfoComplain.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
}
