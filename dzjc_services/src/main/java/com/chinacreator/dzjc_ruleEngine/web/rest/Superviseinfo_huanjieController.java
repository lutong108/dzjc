package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.chinacreator.dzjc_ruleEngine.SuperviseInfoInstance;
import com.chinacreator.dzjc_ruleEngine.Superviseinfo_huanjie;
import com.chinacreator.dzjc_ruleEngine.utils.InstanceExport;
import com.chinacreator.dzjc_ruleEngine.utils.Instance_huajie_Export;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class Superviseinfo_huanjieController {

	
	@GET
	@Path("Superviseinfo_huanjie/{superviseInfoId}")
	public Superviseinfo_huanjie get(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		
		Superviseinfo_huanjie superviseInfoInstance=DaoFactory.create(Superviseinfo_huanjie.class).selectByID(
				superviseInfoId);
		
		
		return superviseInfoInstance;
	}
	
	
	@POST
	@Path("Superviseinfo_huanjie/{instanceId}")
	public Superviseinfo_huanjie update(Superviseinfo_huanjie entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Superviseinfo_huanjie.class).update(entity);
		return entity;
	}

	@POST
	@Path("Superviseinfo_huanjie")
	public Superviseinfo_huanjie insert(Superviseinfo_huanjie entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Superviseinfo_huanjie.class).insert(entity);
		return entity;
	}



	@DELETE
	@Path("Superviseinfo_huanjie")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(Superviseinfo_huanjie.class).deleteBatch(ids);
	}

	@DELETE
	@Path("Superviseinfo_huanjie/{instanceId}")
	public void delete(
			@PathParam(value = "instanceId") java.lang.String instanceId) {
		//TODO auto-generated method stub
		Superviseinfo_huanjie entity = new Superviseinfo_huanjie();
		entity.setInstanceId(instanceId);
		DaoFactory.create(Superviseinfo_huanjie.class).delete(entity);
	}

	@GET
	@Path("Superviseinfo_huanjie")
	public Page<Superviseinfo_huanjie> getListByPage(
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
		Superviseinfo_huanjie entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, Superviseinfo_huanjie.class)
				: new Superviseinfo_huanjie();
				if(conditions!=null){
					List<Rule> listRule = conditions.getRules();
					String org_id="";
					for (int i = 0; i < listRule.size(); i++) {
						String field = listRule.get(i).getField();
						if("orgId".equals(field)){
							org_id=(String) listRule.get(i).getData();
							listRule.remove(i);
							i=0;
							if(!com.chinacreator.util.StringUtil.isBlank(org_id)){
								entity.setOrgId(org_id);
							}
							if(listRule.size()==0){
								conditions=null;
								break;
							}
						}
						if("superviseTime".equals(field)){
							String op = listRule.get(i).getOp();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
					
					
				}else{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					if(entity.getStartDate()!=null){
						Date srart_date = entity.getStartDate();
						String srart_dateStr = sdf.format(srart_date);
						srart_dateStr=srart_dateStr+" 00:00:01";
						java.util.Date date1 = null;
						java.sql.Date date2 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(srart_dateStr);
							date2 = new java.sql.Date(date1.getTime());
							f.format(date2);
							entity.setStartDate(date2);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
					}
					
					if(entity.getEndDate()!=null){
						Date end_date = entity.getEndDate();
						String end_dateStr = sdf.format(end_date);
						end_dateStr=end_dateStr+" 23:59:59";
						java.util.Date date1 = null;
						java.sql.Date date2 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(end_dateStr);
							date2 = new java.sql.Date(date1.getTime());
							f.format(date2);
							entity.setEndDate(date2);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					
				}
		return DaoFactory.create(Superviseinfo_huanjie.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
	
	/**
	 * excel导出
	 * 
	 * @return
	 */
	public String excelExport(String cond) {

		List<Superviseinfo_huanjie> list = this.getList(cond);

		List<List<Superviseinfo_huanjie>> busList = new ArrayList<List<Superviseinfo_huanjie>>();
		busList.add(list);

		Instance_huajie_Export instanceexport = new Instance_huajie_Export();
		
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
				
				titleName="环节监管信息情况表("+beginDate+"——"+endDate+")";
			}else if("1".equals(beginStr) && "0".equals(endStr)){
				
				titleName="环节监管信息情况表("+beginDate+"——"+new SimpleDateFormat("YYYY-MM-dd").format(new Date())+")";
			}else if("0".equals(beginStr) && "1".equals(endStr)){

				titleName="环节监管信息情况表(截止"+endDate;
			}else{
			
				titleName="环节监管信息情况表(截止"+new SimpleDateFormat("YYYY-MM-dd").format(new Date())+")";
			}
		}else{
			
			titleName="环节监管信息情况表(截止"+new SimpleDateFormat("YYYY-MM-dd").format(new Date())+")";
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
	public List<Superviseinfo_huanjie> getList(String cond) {
		// TODO auto-generated method stub
		// 创建分页对象
		Pageable pageable = new Pageable(1, 9999999);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("", "");
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		Superviseinfo_huanjie entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, Superviseinfo_huanjie.class)
				: new Superviseinfo_huanjie();
		if(conditions!=null){
			List<Rule> listRule= conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				
				String field= listRule.get(i).getField();
				String org_id="";
				if("orgId".equals(field)){
					org_id=(String) listRule.get(i).getData();
					listRule.remove(i);
					i=0;
					if(!com.chinacreator.util.StringUtil.isBlank(org_id)){
						entity.setOrgId(org_id);
					}
					if(listRule.size()==0){
						conditions=null;
						break;
					}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 if("superviseTime".equals(field)){
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
				.create(Superviseinfo_huanjie.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true).getContents();

	}
}
