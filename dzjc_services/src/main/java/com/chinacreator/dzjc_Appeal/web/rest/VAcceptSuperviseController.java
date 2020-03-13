package com.chinacreator.dzjc_Appeal.web.rest;
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
import com.chinacreator.dzjc_Appeal.VAcceptSupervise;
import com.chinacreator.dzjc_dealtMatter.SuperviseBJ;
import com.chinacreator.dzjc_dealtMatter.SuperviseTS;
import com.chinacreator.dzjc_dealtMatter.SuperviseZX;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class VAcceptSuperviseController {

	@DELETE
	@Path("VAcceptSupervise/{superviseInfoId}")
	public void delete(@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		VAcceptSupervise entity = new VAcceptSupervise();
		entity.setSuperviseInfoId(superviseInfoId);
		DaoFactory.create(VAcceptSupervise.class).delete(entity);
	}

	@DELETE
	@Path("VAcceptSupervise")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(VAcceptSupervise.class).deleteBatch(ids);
	}

	@GET
	@Path("VAcceptSupervise/{superviseInfoId}")
	public VAcceptSupervise get(@PathParam(value = "superviseInfoId") java.lang.String superviseInfoId) {
		return DaoFactory.create(VAcceptSupervise.class).selectByID(superviseInfoId);
	}

	/**
	 * 查询办件监察详情
	 * 
	 * @param businessId
	 * @return
	 */
	@GET
	@Path("SuperviseBJ")
	public SuperviseBJ getBJ(@QueryParam(value = "businessId") java.lang.String businessId) {

		List<SuperviseBJ> selectList = DaoFactory.create(SuperviseBJ.class).getSession()
				.selectList("com.chinacreator.dzjc_Appeal.VAcceptSuperviseMapper.selectByID2", businessId);

		SuperviseBJ superviseBJ = null;
		if (selectList.size() > 0) {
			superviseBJ = selectList.get(0);
		}

		return superviseBJ;
	}

	/**
	 * 查询投诉监察详情
	 * 
	 * @param complainId
	 * @return
	 */
	@GET
	@Path("SuperviseTS")
	public SuperviseTS getTS(@QueryParam(value = "complainId") java.lang.String complainId) {

		List<SuperviseTS> selectList = DaoFactory.create(SuperviseTS.class).getSession()
				.selectList("com.chinacreator.dzjc_Appeal.VAcceptSuperviseMapper.selectByID3", complainId);

		SuperviseTS superviseTS = null;
		if (selectList.size() > 0) {
			superviseTS = selectList.get(0);
		}

		return superviseTS;
	}

	/**
	 * 查询咨询监察详情
	 * 
	 * @param complainId
	 * @return
	 */
	@GET
	@Path("SuperviseZX")
	public SuperviseZX getZX(@QueryParam(value = "consultId") java.lang.String consultId) {

		List<SuperviseZX> selectList = DaoFactory.create(SuperviseZX.class).getSession()
				.selectList("com.chinacreator.dzjc_Appeal.VAcceptSuperviseMapper.selectByID4", consultId);

		SuperviseZX superviseZX = null;
		if (selectList.size() > 0) {
			superviseZX = selectList.get(0);
		}

		return superviseZX;
	}

	@POST
	@Path("VAcceptSupervise")
	public VAcceptSupervise insert(VAcceptSupervise entity) {
		DaoFactory.create(VAcceptSupervise.class).insert(entity);
		return entity;
	}

	@POST
	@Path("VAcceptSupervise/{superviseInfoId}")
	public VAcceptSupervise update(VAcceptSupervise entity) {
		DaoFactory.create(VAcceptSupervise.class).update(entity);
		return entity;
	}

	@GET
	@Path("VAcceptSupervise")
	public Page<VAcceptSupervise> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		VAcceptSupervise entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, VAcceptSupervise.class)
				: new VAcceptSupervise();

		//发牌信息只能看本机构的
		String orgId = null;
		try {
			orgId = StringUtil.getUserInfo().get("orgId");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (entity != null) {
			if ("".equals(orgId)) {
				return null;
			}
			entity.setOrgId(orgId);
		}

		return DaoFactory.create(VAcceptSupervise.class).selectPageByCondition(entity, conditions, pageable, sortable,
				true);

	}
}
