package com.chinacreator.dzjc_tongji.web.rest;

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
import com.chinacreator.dzjc_tongji.DevSupervise;
import com.chinacreator.dzjc_tongji.ParamEntity;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DevSuperviseController {

	@GET
	@Path("DevSupervise/{superviseInfoId}")
	public DevSupervise get(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		//TODO auto-generated method stub
		return DaoFactory.create(DevSupervise.class)
				.selectByID(superviseInfoId);
	}

	@GET
	@Path("DevSupervise")
	public Page<DevSupervise> getListByPage(
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
		DevSupervise entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, DevSupervise.class) : new DevSupervise();
		ArrayList<Rule> revmoveList = new ArrayList<Rule>();
		if(conditions!=null){
			List<Rule> ruleList = conditions.getRules();
			if(ruleList!=null&&ruleList.size()>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (Rule rule : ruleList) {
					String field = rule.getField();
					Object data = rule.getData();
					if(data!=null){
						if("orgId".equals(field)){
							revmoveList.add(rule);
							entity.setOrgId((String) rule.getData());
							if(ruleList.size()==0){
								conditions=null;
								break;
							}
						}else if ("superviseTime".equals(field)) {
							String op = rule.getOp();
							if ("le".equals(op)) {
								long dataTime = (long) (rule.getData());
								String dataTimeStr = sdf.format(new Date(Long.parseLong(String.valueOf(dataTime))));
								dataTimeStr = dataTimeStr + " 23:59:59";
								java.util.Date date1 = null;
								try {
									date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataTimeStr);
									long ts = date1.getTime();
									rule.setData(ts);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
							}else if("ge".equals(op)){
								long dataTime = (long) (rule.getData());
								String dataTimeStr = sdf.format(new Date(Long.parseLong(String.valueOf(dataTime))));
								dataTimeStr = dataTimeStr + " 00:00:01";
								java.util.Date date1 = null;
								try {
									date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataTimeStr);
									long ts = date1.getTime();
									rule.setData(ts);
								} catch (ParseException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				}
				ruleList.removeAll(revmoveList);
			}
		}
		Page<DevSupervise> result = DaoFactory.create(DevSupervise.class).selectPageByCondition(entity, conditions, pageable, sortable, true);
		return result;
	}
}
