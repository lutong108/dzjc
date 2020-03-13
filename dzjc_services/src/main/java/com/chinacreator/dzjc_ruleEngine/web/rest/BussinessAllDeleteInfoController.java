package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.chinacreator.dzjc_ruleEngine.BussinessAllDeleteInfo;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class BussinessAllDeleteInfoController {

	@GET
	@Path("bussinessAllDeleteInfo")
	public Page<BussinessAllDeleteInfo> getListByPage(
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
		BussinessAllDeleteInfo entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, BussinessAllDeleteInfo.class)
				: new BussinessAllDeleteInfo();
		
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
				if("orgId".equals(field)){
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
				/*if ("superviseTime".equals(field)) {
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
				}*/
			}
			if(listRule.size()==0){
				conditions=null;
			}
			
		} /*else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
		}*/
		return DaoFactory.create(BussinessAllDeleteInfo.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
}
