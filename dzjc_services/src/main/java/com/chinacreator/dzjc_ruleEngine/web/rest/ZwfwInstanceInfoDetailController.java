package com.chinacreator.dzjc_ruleEngine.web.rest;

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
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.ZwfwInstanceInfoDetail;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class ZwfwInstanceInfoDetailController {

	@GET
	@Path("ZwfwInstanceInfoDetail/{instanceId}")
	public ZwfwInstanceInfoDetail get(@PathParam(value = "instanceId") java.lang.String instanceId) {
		ZwfwInstanceInfoDetail ins = DaoFactory.create(ZwfwInstanceInfoDetail.class).selectByID(instanceId);
		return ins;
	}

	@GET
	@Path("ZwfwInstanceInfoDetail")
	public Page<ZwfwInstanceInfoDetail> getListByPage(
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
		ZwfwInstanceInfoDetail entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, ZwfwInstanceInfoDetail.class)
				: new ZwfwInstanceInfoDetail();

		return DaoFactory.create(ZwfwInstanceInfoDetail.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
}
