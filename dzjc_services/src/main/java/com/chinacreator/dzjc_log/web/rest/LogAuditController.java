package com.chinacreator.dzjc_log.web.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_log.LogAudit;

@Controller
@Path("dzjc_log/")
@Consumes(MediaType.APPLICATION_JSON)
public class LogAuditController {

	@POST
	@Path("LogAudit/{logId}")
	public LogAudit update(LogAudit entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(LogAudit.class).update(entity);
		return entity;
	}

	@POST
	@Path("LogAudit")
	public LogAudit insert(LogAudit entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(LogAudit.class).insert(entity);
		return entity;
	}

	@GET
	@Path("LogAudit/{logId}")
	public LogAudit get(@PathParam(value = "logId") java.lang.String logId) {
		//TODO auto-generated method stub
		return DaoFactory.create(LogAudit.class).selectByID(logId);
	}

	@DELETE
	@Path("LogAudit")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(LogAudit.class).deleteBatch(ids);
	}

	@DELETE
	@Path("LogAudit/{logId}")
	public void delete(@PathParam(value = "logId") java.lang.String logId) {
		//TODO auto-generated method stub
		LogAudit entity = new LogAudit();
		entity.setLogId(logId);
		DaoFactory.create(LogAudit.class).delete(entity);
	}

	@GET
	@Path("LogAudit")
	public Page<LogAudit> getListByPage(@QueryParam(value = "page") int page,
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
		LogAudit entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				LogAudit.class) : new LogAudit();

		//分页条件封装
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,null,true);
		//查询数据
		List<LogAudit> selectDataList = selectDataList(pageable,sortable,conditions,entity,rowbound,false);
		//封装返回
		Page<LogAudit> pagingResult = new Page<LogAudit>(page, rows, rowbound.getTotalSize(),selectDataList);
		
		return	pagingResult;

	}
	
	/**
	 * 数据查询公共方法
	 * @param pageable
	 * @param sortable
	 * @param conditions
	 * @param entity
	 * @param rowbound
	 * @param isExcel	excel导出标识符
	 * @return
	 */
	private List<LogAudit> selectDataList(Pageable pageable,Sortable sortable,
			Conditions conditions,LogAudit entity,RowBounds4Page rowbound,Boolean isExcel){
		//1,定义参数
		Map<String,Object> params = new HashMap<String,Object>();
		
		//2,查询封装params
		if (entity.getBeginDate() != null) {
			Date  beginDate = new Date (entity.getBeginDate().getTime());
			params.put("beginDate", beginDate);
		}
		if (entity.getEndDate() != null) {
			Date  endDate = new Date (entity.getEndDate().getTime());
			params.put("endDate", endDate);
		}
		if(entity.getUserName() != null){
			params.put("userName","%"+ entity.getUserName() +"%");
		}
		if(entity.getOpType() != null){
			params.put("opType", entity.getOpType());
		}

		//3,数据查询
		List<LogAudit> list = new ArrayList<LogAudit>();
		list = DaoFactory.create(LogAudit.class).getSession().selectList("com.chinacreator.dzjc_log.LogAuditMapper.selectData",  params, rowbound);
		return	list;
		
	}
	
}
