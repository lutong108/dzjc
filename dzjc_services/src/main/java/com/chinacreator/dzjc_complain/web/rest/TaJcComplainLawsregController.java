package com.chinacreator.dzjc_complain.web.rest;

import java.sql.Date;
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
import com.chinacreator.dzjc_complain.TaJcComplainLawsreg;
import com.chinacreator.util.FtpUtils;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_complain/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcComplainLawsregController {

	@DELETE
	@Path("taJcComplainLawsreg/{lawsId}")
	public void delete(@PathParam(value = "lawsId") java.lang.String lawsId) {
		TaJcComplainLawsreg entity = new TaJcComplainLawsreg();
		entity.setLawsId(lawsId);
		DaoFactory.create(TaJcComplainLawsreg.class).delete(entity);
	}

	@DELETE
	@Path("taJcComplainLawsreg")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(TaJcComplainLawsreg.class).deleteBatch(ids);
	}

	@GET
	@Path("taJcComplainLawsreg/{lawsId}")
	public TaJcComplainLawsreg get(@PathParam(value = "lawsId") java.lang.String lawsId) {
		return DaoFactory.create(TaJcComplainLawsreg.class).selectByID(lawsId);
	}

	@POST
	@Path("taJcComplainLawsreg")
	public TaJcComplainLawsreg insert(TaJcComplainLawsreg entity) {

		if (entity != null) {
			entity.setCreateUser(StringUtil.getUserInfo().get("name"));
			entity.setOrgId(StringUtil.getUserInfo().get("orgId"));
			entity.setCreateTime(new Date(new java.util.Date().getTime()));
			entity.setLastupdatetime(entity.getCreateTime());
			if(StringUtils.isNotBlank(entity.getLawsContent())){
				entity.setLawsContent(FtpUtils.ftpUpload(entity.getLawsContent()));
			}
		}

		DaoFactory.create(TaJcComplainLawsreg.class).insert(entity);
		return entity;
	}

	@POST
	@Path("taJcComplainLawsreg/{lawsId}")
	public TaJcComplainLawsreg update(TaJcComplainLawsreg entity) {

		if (entity != null) {
			entity.setLastupdatetime(new Date(new java.util.Date().getTime()));
			if(StringUtils.isNotBlank(entity.getLawsContent())){
				entity.setLawsContent(FtpUtils.ftpUpload(entity.getLawsContent()));
			}
		}

		DaoFactory.create(TaJcComplainLawsreg.class).update(entity);
		return entity;
	}

	@GET
	@Path("taJcComplainLawsreg")
	public Page<TaJcComplainLawsreg> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {

		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcComplainLawsreg entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcComplainLawsreg.class)
				: new TaJcComplainLawsreg();

		return DaoFactory.create(TaJcComplainLawsreg.class).selectPageByCondition(entity, conditions, pageable,
				sortable, true);

	}
}
