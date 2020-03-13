package com.chinacreator.dzjc_ruleEngine.web.rest;

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
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.Superviseinfo_Message;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class Superviseinfo_MessageController {

	@POST
	@Path("Superviseinfo_Message/{superviseInfoId}")
	public Superviseinfo_Message update(Superviseinfo_Message entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Superviseinfo_Message.class).update(entity);
		return entity;
	}

	@POST
	@Path("Superviseinfo_Message")
	public Superviseinfo_Message insert(Superviseinfo_Message entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Superviseinfo_Message.class).insert(entity);
		return entity;
	}

	@GET
	@Path("Superviseinfo_Message/{superviseInfoId}")
	public Superviseinfo_Message get(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		//TODO auto-generated method stub
		Superviseinfo_Message superviseinfo_Message= DaoFactory.create(Superviseinfo_Message.class).selectByID(
				superviseInfoId);
		if(superviseinfo_Message.getState()!=null){
			//预警状态(已发Y,待发D,撤销C,取消Q，督办B)
			if(superviseinfo_Message.getState().equals("Y")){
				superviseinfo_Message.setState("已发");
			}else if(superviseinfo_Message.getState().equals("D")){
				superviseinfo_Message.setState("待发");
			}else if(superviseinfo_Message.getState().equals("C")){
				superviseinfo_Message.setState("撤销");
			}else if(superviseinfo_Message.getState().equals("Q")){
				superviseinfo_Message.setState("取消");
			}else if(superviseinfo_Message.getState().equals("B")){
				superviseinfo_Message.setState("督办");
			}
			
		}
		if(superviseinfo_Message.getSuperviseTypeNo()!=null){
			if(superviseinfo_Message.getSuperviseTypeNo().equals("1019")){
				superviseinfo_Message.setSuperviseTypeNo("环节时限监察");
			}
		}
		
		if(superviseinfo_Message.getSuperviseResultNo()!=null){
			if(superviseinfo_Message.getSuperviseResultNo().equals("1")){
				superviseinfo_Message.setSuperviseResultNo("预警");
			}else if(superviseinfo_Message.getSuperviseResultNo().equals("2")){
				superviseinfo_Message.setSuperviseResultNo("黄牌");
			}else if(superviseinfo_Message.getSuperviseResultNo().equals("3")){
				superviseinfo_Message.setSuperviseResultNo("红牌");
			}
		}
		
		return superviseinfo_Message;
	}

	@DELETE
	@Path("Superviseinfo_Message")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(Superviseinfo_Message.class).deleteBatch(ids);
	}

	@DELETE
	@Path("Superviseinfo_Message/{superviseInfoId}")
	public void delete(
			@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		//TODO auto-generated method stub
		Superviseinfo_Message entity = new Superviseinfo_Message();
		entity.setSuperviseInfoId(superviseInfoId);
		DaoFactory.create(Superviseinfo_Message.class).delete(entity);
	}

	@GET
	@Path("Superviseinfo_Message")
	public Page<Superviseinfo_Message> getListByPage(
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
		Superviseinfo_Message entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, Superviseinfo_Message.class)
				: new Superviseinfo_Message();

		return DaoFactory.create(Superviseinfo_Message.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
}
