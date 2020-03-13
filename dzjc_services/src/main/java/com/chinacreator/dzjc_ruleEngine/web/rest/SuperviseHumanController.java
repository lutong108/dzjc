package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.chinacreator.dzjc_ruleEngine.SuperviseHuman;
import com.chinacreator.util.RoleUtil;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SuperviseHumanController {

	@DELETE
	@Path("SuperviseHuman/{superviseHumanId}")
	public void delete(
			@PathParam(value = "superviseHumanId") java.lang.String superviseHumanId) {
		//TODO auto-generated method stub
		SuperviseHuman entity = new SuperviseHuman();
		entity.setSuperviseHumanId(superviseHumanId);
		DaoFactory.create(SuperviseHuman.class).delete(entity);
	}

	@DELETE
	@Path("SuperviseHuman")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(SuperviseHuman.class).deleteBatch(ids);
	}

	@GET
	@Path("SuperviseHuman/{superviseHumanId}")
	public SuperviseHuman get(
			@PathParam(value = "superviseHumanId") java.lang.String superviseHumanId) {
		//TODO auto-generated method stub
		return DaoFactory.create(SuperviseHuman.class).selectByID(
				superviseHumanId);
	}

	@POST
	@Path("SuperviseHuman")
	public SuperviseHuman insert(SuperviseHuman entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(SuperviseHuman.class).insert(entity);
		return entity;
	}

	@POST
	@Path("SuperviseHuman/{superviseHumanId}")
	public SuperviseHuman update(SuperviseHuman entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(SuperviseHuman.class).update(entity);
		return entity;
	}

	@GET
	@Path("SuperviseHuman")
	public Page<SuperviseHuman> getListByPage (
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) throws Exception{
		//TODO auto-generated method stub
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		SuperviseHuman entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SuperviseHuman.class) : new SuperviseHuman();
		String code = null;
		// 查询当前登录用户编号(行政区码)
		code = RoleUtil.getInstance().queryCodeByUserId();
		if (entity != null) {
			if ("".equals(code)) {
				return null;
			}
			entity.setOrgId(code);
		}			
		if(conditions!=null){
			List<Rule> rules = conditions.getRules();
			for (int i = 0; i < rules.size(); i++) {
				String field = rules.get(i).getField();
				if("temporgid".equals(field)){
					Rule remove = rules.remove(i);
					String string = remove.getData().toString();
					entity.setTemporgid(string);
					entity.setOrgId(null);
					if(rules.size()==0){
						conditions=null;
						break;
					}
				}	
				if("superviseTime".equals(field)){
					String op = rules.get(i).getOp();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if ("le".equals(op)) {
						long dataTime = (long) (rules.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 23:59:59";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
							long ts = date1.getTime();
							rules.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}else if("ge".equals(op)){
						long dataTime = (long) (rules.get(i).getData());
						String dataTimeStr = sdf.format(new Date(Long
								.parseLong(String.valueOf(dataTime))));
						dataTimeStr = dataTimeStr + " 00:00:01";
						java.util.Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(dataTimeStr);
							long ts = date1.getTime();
							rules.get(i).setData(ts);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(entity.getFpbeginDate()!=null){
				Date srart_date = entity.getFpbeginDate();
				String srart_dateStr = sdf.format(srart_date);
				srart_dateStr=srart_dateStr+" 00:00:01";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(srart_dateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setFpbeginDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
			
			if(entity.getFpendDate()!=null){
				Date end_date = entity.getFpendDate();
				String end_dateStr = sdf.format(end_date);
				end_dateStr=end_dateStr+" 23:59:59";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(end_dateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setFpendDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		return DaoFactory.create(SuperviseHuman.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	@GET
	@Path("SuperviseHumanNew")
	public Page<SuperviseHuman> getListByPageNew (
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) throws Exception{
		//TODO auto-generated method stub
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		SuperviseHuman entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, SuperviseHuman.class) : new SuperviseHuman();
		String code = null;
		// 查询当前登录用户编号(行政区码)
		//code = RoleUtil.getInstance().queryCodeByUserId();
		Map<String, String> userInfo = StringUtil.getUserInfo();
		code=userInfo.get("orgId");
		if (entity != null) {
			if ("".equals(code)) {
				return null;
			}
			entity.setOrgId(code);
		}			
		if(conditions!=null){
			List<Rule> rules = conditions.getRules();
			for (int i = 0; i < rules.size(); i++) {
				String field = rules.get(i).getField();
				if("temporgid".equals(field)){
					Rule remove = rules.remove(i);
					String string = remove.getData().toString();
					entity.setTemporgid(string);
					entity.setOrgId(null);
					if(rules.size()==0){
						conditions=null;
						break;
					}
				}
			}
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(entity.getFpbeginDate()!=null){
				Date srart_date = entity.getFpbeginDate();
				String srart_dateStr = sdf.format(srart_date);
				srart_dateStr=srart_dateStr+" 00:00:01";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(srart_dateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setFpbeginDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
			
			if(entity.getFpendDate()!=null){
				Date end_date = entity.getFpendDate();
				String end_dateStr = sdf.format(end_date);
				end_dateStr=end_dateStr+" 23:59:59";
				java.util.Date date1 = null;
				java.sql.Date date2 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(end_dateStr);
					date2 = new java.sql.Date(date1.getTime());
					f.format(date2);
					entity.setFpendDate(date2);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		return DaoFactory.create(SuperviseHuman.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
}
