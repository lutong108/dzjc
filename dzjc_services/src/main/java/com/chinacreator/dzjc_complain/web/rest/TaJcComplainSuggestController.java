package com.chinacreator.dzjc_complain.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.chinacreator.dzjc_complain.TaJcComplainSuggest;
import com.chinacreator.dzjc_ruleEngine.utils.TaJcComplainSuggestExport;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcComplainSuggestController {

	@POST
	@Path("taJcComplainSuggest/{suggestId}")
	public TaJcComplainSuggest update(TaJcComplainSuggest entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaJcComplainSuggest.class).update(entity);
		return entity;
	}

	@POST
	@Path("taJcComplainSuggest")
	public TaJcComplainSuggest insert(TaJcComplainSuggest entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(TaJcComplainSuggest.class).insert(entity);
		return entity;
	}

	@GET
	@Path("taJcComplainSuggest/{suggestId}")
	public TaJcComplainSuggest get(
			@PathParam(value = "suggestId") java.lang.String suggestId) {
		return DaoFactory.create(TaJcComplainSuggest.class).selectByID(
				suggestId);
	}

	@DELETE
	@Path("taJcComplainSuggest")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(TaJcComplainSuggest.class).deleteBatch(ids);
	}

	@DELETE
	@Path("taJcComplainSuggest/{suggestId}")
	public void delete(
			@PathParam(value = "suggestId") java.lang.String suggestId) {
		//TODO auto-generated method stub
		TaJcComplainSuggest entity = new TaJcComplainSuggest();
		entity.setSuggestId(suggestId);
		DaoFactory.create(TaJcComplainSuggest.class).delete(entity);
	}

	@GET
	@Path("taJcComplainSuggest")
	public Page<TaJcComplainSuggest> getListByPage(
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
		TaJcComplainSuggest entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, TaJcComplainSuggest.class)
				: new TaJcComplainSuggest();
		
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("suggestDate".equals(field)) {
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
				
		return DaoFactory.create(TaJcComplainSuggest.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
	public String excelExport(String cond) {
		List<TaJcComplainSuggest> busList = this.getList(cond);
		
		TaJcComplainSuggestExport export = new TaJcComplainSuggestExport();
		String titleName = "建言献策情况表";
		//创建excel导出对象
		export.excelExport(titleName, busList);
		return titleName;
	}

	private List<TaJcComplainSuggest> getList(String cond) {
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("","");
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		//TaJcComplainSuggest entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainSuggest.class) : new TaJcComplainSuggest();
		
		if (conditions != null) {
			List<Rule> listRule = conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field = listRule.get(i).getField();
				if ("suggestDate".equals(field)) {
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
		return DaoFactory.create(TaJcComplainSuggest.class).selectByCondition(conditions, sortable);
	}
}
