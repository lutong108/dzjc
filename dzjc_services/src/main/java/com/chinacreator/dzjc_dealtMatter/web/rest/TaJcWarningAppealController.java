package com.chinacreator.dzjc_dealtMatter.web.rest;

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
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_complain.web.rest.OrgviewController;
import com.chinacreator.dzjc_dealtMatter.TaJcSuperviseInfo;
import com.chinacreator.dzjc_dealtMatter.TaJcWarningAppeal;
import com.chinacreator.dzjc_ruleEngine.SuperviseHuman;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_dealtMatter/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TaJcWarningAppealController {

	@DELETE
	@Path("taJcWarningAppeal/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		TaJcWarningAppeal entity = new TaJcWarningAppeal();
		entity.setId(id);
		DaoFactory.create(TaJcWarningAppeal.class).delete(entity);
	}

	@DELETE
	@Path("taJcWarningAppeal")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(TaJcWarningAppeal.class).deleteBatch(ids);
	}

	@GET
	@Path("taJcWarningAppeal/{id}")
	public TaJcWarningAppeal get(@PathParam(value = "id") java.lang.String id) {
		return DaoFactory.create(TaJcWarningAppeal.class).selectByID(id);
	}

	@POST
	@Path("taJcWarningAppeal")
	public TaJcWarningAppeal insert(TaJcWarningAppeal entity) {
		DaoFactory.create(TaJcWarningAppeal.class).insert(entity);
		return entity;
	}
	public TaJcWarningAppeal updateNew(TaJcWarningAppeal entity) {
		DaoFactory.create(TaJcWarningAppeal.class).update(entity);
		return entity;
	}
	
	

	@POST
	@Path("taJcWarningAppeal/{id}")
	public TaJcWarningAppeal update(TaJcWarningAppeal entity) {

		if (entity != null) {
			String orgId = StringUtil.getUserInfo().get("orgId");
			Orgview orgview = new OrgviewController().get(orgId);
			String orgName = orgview.getName();
			entity.setReplyOrgId(orgId);
			entity.setReplyOrgName(orgName);
			entity.setReplyTime(new Date(new java.util.Date().getTime()));
			entity.setReplyUserId(StringUtil.getUserInfo().get("id"));
			entity.setReplyUserName(StringUtil.getUserInfo().get("name"));
		}

		int rows = DaoFactory.create(TaJcWarningAppeal.class).update(entity);

		//只有撤销才同步更新
		if ("C".equalsIgnoreCase(entity.getProcessResult())) {

			//同步更新发牌表
			if (rows > 0) {

				String superviseInfoId = entity.getSuperviseInfoId();
				TaJcSuperviseInfo taJcSuperviseInfo = DaoFactory.create(TaJcSuperviseInfo.class).selectByID(
						superviseInfoId);

				taJcSuperviseInfo.setState(entity.getProcessResult());

				DaoFactory.create(TaJcSuperviseInfo.class).update(taJcSuperviseInfo);
			}
		}

		return entity;
	}

	
	@POST
	@Path("taJcWarningAppeal/updateHumanAppeal")
	@Transactional
	public TaJcWarningAppeal updateHumanAppeal(TaJcWarningAppeal entity) {
		if (entity != null){
			DaoFactory.create(TaJcWarningAppeal.class).update(entity);
			if ("C".equalsIgnoreCase(entity.getProcessResult())) {
				String superviseHumanId=entity.getSuperviseInfoId();
				SuperviseHuman selectByID = DaoFactory.create(SuperviseHuman.class).selectByID(superviseHumanId);
				selectByID.setState(entity.getProcessResult());
				selectByID.setTemporgid(null);
				selectByID.setOrgName(null);
				selectByID.setApproveName(null);
				selectByID.setRuleName(null);
				DaoFactory.create(SuperviseHuman.class).update(selectByID);
			}
		}
		return entity;
	}
	
	@GET
	@Path("taJcWarningAppeal")
	public Page<TaJcWarningAppeal> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {

		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		TaJcWarningAppeal entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, TaJcWarningAppeal.class)
				: new TaJcWarningAppeal();

		return DaoFactory.create(TaJcWarningAppeal.class).selectPageByCondition(entity, conditions, pageable, sortable,
				true);

	}
	
	@GET
	@Path("taJcWarningAppeal/getBySuperviseHumanId/{superviseHumanId}")
	public TaJcWarningAppeal getBySuperviseHumanId(@PathParam(value = "superviseHumanId") java.lang.String superviseHumanId) {
		TaJcWarningAppeal appeal=DaoFactory.create(TaJcWarningAppeal.class)
				  .getSession()
				  .selectOne("com.chinacreator.dzjc_dealtMatter.TaJcWarningAppealMapper.selectBySuperviseHumanId", superviseHumanId);
		return appeal;
		//return DaoFactory.create(TaJcWarningAppeal.class).selectByID(id);
	} 
}
