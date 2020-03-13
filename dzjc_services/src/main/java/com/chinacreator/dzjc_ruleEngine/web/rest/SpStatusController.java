package com.chinacreator.dzjc_ruleEngine.web.rest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class SpStatusController {

	@GET
	@Path("spstatus/{batchId}")
	public Sp_status get(@PathParam(value = "batchId") java.lang.String batchId) {
		Sp_status entity = DaoFactory.create(Sp_status.class).getSession()
				.selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.getByID",batchId);
 		return entity;
	}
	
	@GET
	@Path("spstatus/selectException")
	public Page<Sp_status> selectException(
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
		Sp_status entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, Sp_status.class) : new Sp_status();

		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,conditions,true);

		List<Sp_status> list = DaoFactory.create(Sp_status.class).getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectExceptionList",
						entity, rowbound);
		
		Page<Sp_status> pagingResult = new Page<Sp_status>(page, rows, rowbound.getTotalSize(),
                list);
		return pagingResult;
	}
	
	@GET
	@Path("spstatus/getListByPage")
	public Page<Sp_status> getListByPage(
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
		if (conditions != null) {
			List<Rule> rules = conditions.getRules();
			for (int i = 0; i < rules.size(); i++) {
				String field = rules.get(i).getField();
				if ("superviseTime".equals(field)) {
					String op = rules.get(i).getOp();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd");
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
		}
		/*初始化实体对象*/
		Sp_status entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, Sp_status.class) : new Sp_status();

		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,conditions,true);

		List<Sp_status> list = DaoFactory.create(Sp_status.class).getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectListByPage",
						entity, rowbound);
		
		Page<Sp_status> pagingResult = new Page<Sp_status>(page, rows, rowbound.getTotalSize(),
                list);
		return pagingResult;
	}
	
	@GET
	@Path("spstatus/selectListByType")
	public Page<Sp_status> selectListByType(
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
		Sp_status entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, Sp_status.class) : new Sp_status();

		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,conditions,true);

		List<Sp_status> list = DaoFactory.create(Sp_status.class).getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_statusMapper.selectListByType",
						entity, rowbound);
		
		Page<Sp_status> pagingResult = new Page<Sp_status>(page, rows, rowbound.getTotalSize(),
                list);
		return pagingResult;
	}
}
