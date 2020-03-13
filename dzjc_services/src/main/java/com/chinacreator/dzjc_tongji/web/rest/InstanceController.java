package com.chinacreator.dzjc_tongji.web.rest;

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
import com.chinacreator.dzjc_tongji.Instance;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class InstanceController {

	@GET
	@Path("Instance")
	public Page<Instance> getListByPage(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		Instance entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, Instance.class) : new Instance();
		
		if (conditions != null) {
			List<Rule> rules = conditions.getRules();
			if (rules != null && rules.size() > 0) {
				
				for (Rule item : rules) {
					
					String field = item.getField();
					
					switch (field) {
						case "orgId":
							entity.setOrgId((String) item.getData());
							break;
						case "projectState":
							entity.setProjectState((String) item.getData());
							break;
						case "acceptBeginTime":
							entity.setAcceptBeginTime(formatDate((long) item.getData(), " 00:00:01"));
							break;
						case "acceptEndTime":
							entity.setAcceptEndTime(formatDate((long) item.getData(), " 23:59:59"));
							break;
						case "instanceId":
							entity.setInstanceId((String) item.getData());
							break;
						case "finishBeginTime":
							entity.setFinishBeginTime(formatDate((long) item.getData(), " 00:00:01"));
							break;
						case "finishEndTime":
							entity.setFinishEndTime(formatDate((long) item.getData(), " 23:59:59"));
							break;
						case "isNeedSupervise":
							entity.setIsNeedSupervise((String) item.getData());
							break;
						case "approveName":
							entity.setApproveName((String) item.getData());
							break;
						default:
							break;
					}
				}
			}
		}
		return DaoFactory.create(Instance.class).selectPageByCondition(entity, null, pageable, sortable, true);
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
