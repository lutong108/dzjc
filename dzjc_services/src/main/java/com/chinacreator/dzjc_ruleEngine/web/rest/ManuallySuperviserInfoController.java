package com.chinacreator.dzjc_ruleEngine.web.rest;

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
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.ManuallySuperviserInfo;
import com.chinacreator.dzjc_ruleEngine.RuleHistoryInfo;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ManuallySuperviserInfoController {

	@POST
	@Path("manuallySuperviserInfo/{superviseInfoId}")
	public ManuallySuperviserInfo update(ManuallySuperviserInfo entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(ManuallySuperviserInfo.class).update(entity);
		return entity;
	}

	@POST
	@Path("manuallySuperviserInfo")
	public ManuallySuperviserInfo insert(ManuallySuperviserInfo entity) {
		//TODO auto-generated method stub	
		java.util.Date utilDate=new Date();
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		entity.setSuperviseTime(sqlDate);
		entity.setState("D");
		DaoFactory.create(ManuallySuperviserInfo.class).insert(entity);
		return entity;
	}

	@GET
	@Path("manuallySuperviserInfo/{superviseInfoId}")
	public ManuallySuperviserInfo get(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		//TODO auto-generated method stub
		return DaoFactory.create(ManuallySuperviserInfo.class).selectByID(
				superviseInfoId);
	}

	@DELETE
	@Path("manuallySuperviserInfo")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(ManuallySuperviserInfo.class).deleteBatch(ids);
	}

	@DELETE
	@Path("manuallySuperviserInfo/{superviseInfoId}")
	public void delete(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		//TODO auto-generated method stub
		ManuallySuperviserInfo entity = new ManuallySuperviserInfo();
		entity.setSuperviseInfoId(superviseInfoId);
		DaoFactory.create(ManuallySuperviserInfo.class).delete(entity);
	}

	@GET
	@Path("manuallySuperviserInfo")
	public Page<ManuallySuperviserInfo> getListByPage(
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
		ManuallySuperviserInfo entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, ManuallySuperviserInfo.class)
				: new ManuallySuperviserInfo();

		return DaoFactory.create(ManuallySuperviserInfo.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
	
	
	
	/**
	 * 自定义新增，规则历史表
	 */
	@POST
	@Path("manuallySuperviserInfo/updatemanuallySuperviserInfo")
	public String updatemanuallySuperviserInfo(@RequestBody Map<String,String> map){
		try {
			String state=map.get("state");
			String superviseInfoId=map.get("superviseInfoId");
			ManuallySuperviserInfo entity=new ManuallySuperviserInfo();
			entity.setSuperviseInfoId(superviseInfoId);
			entity.setState(state);
			DaoFactory.create(ManuallySuperviserInfo.class).getSession().update("com.chinacreator.dzjc_ruleEngine.ManuallySuperviserInfoMapper.updateById", entity);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		
		
	}
	
}
