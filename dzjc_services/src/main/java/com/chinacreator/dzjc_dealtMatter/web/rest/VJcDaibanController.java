package com.chinacreator.dzjc_dealtMatter.web.rest;

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
import com.chinacreator.dzjc_dealtMatter.SuperviseBJ;
import com.chinacreator.dzjc_dealtMatter.SuperviseTS;
import com.chinacreator.dzjc_dealtMatter.SuperviseZX;
import com.chinacreator.dzjc_dealtMatter.TaJcWarningAppeal;
import com.chinacreator.dzjc_dealtMatter.VJcDaiban;
import com.chinacreator.dzjc_ruleEngine.SuperviseResult;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_dealtMatter/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class VJcDaibanController {
	@DELETE
	@Path("VJcDaiban/{dbId}")
	public void delete(@PathParam(value = "dbId") java.lang.String dbId) {
		VJcDaiban entity = new VJcDaiban();
		entity.setDbId(dbId);
		DaoFactory.create(VJcDaiban.class).delete(entity);
	}

	@DELETE
	@Path("VJcDaiban")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(VJcDaiban.class).deleteBatch(ids);
	}

	@GET
	@Path("VJcDaiban/{dbId}")
	public VJcDaiban get(@PathParam(value = "dbId") java.lang.String dbId) {
		return DaoFactory.create(VJcDaiban.class).selectByID(dbId);
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
				.selectList("com.chinacreator.dzjc_dealtMatter.VJcDaibanMapper.selectByID2", businessId);

		SuperviseBJ superviseBJ = null;
		if (selectList.size() > 0) {
			superviseBJ = selectList.get(0);
		}
		return superviseBJ;
	}

	/**
	 * 查询办件监察详情2
	 * 
	 * @param businessId
	 * @return
	 */
	@GET
	@Path("SuperviseBJ2")
	public SuperviseBJ getBJ2(@QueryParam(value = "businessId") java.lang.String businessId) {

		List<SuperviseBJ> selectList = DaoFactory.create(SuperviseBJ.class).getSession()
				.selectList("com.chinacreator.dzjc_dealtMatter.VJcDaibanMapper.selectByID22", businessId);

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
				.selectList("com.chinacreator.dzjc_dealtMatter.VJcDaibanMapper.selectByID3", complainId);

		SuperviseTS superviseTS = null;
		if (selectList.size() > 0) {
			superviseTS = selectList.get(0);
		}
		return superviseTS;
	}

	/**
	 * 查询投诉监察详情2
	 * 
	 * @param complainId
	 * @return
	 */
	@GET
	@Path("SuperviseTS2")
	public SuperviseTS getTS2(@QueryParam(value = "complainId") java.lang.String complainId) {

		List<SuperviseTS> selectList = DaoFactory.create(SuperviseTS.class).getSession()
				.selectList("com.chinacreator.dzjc_dealtMatter.VJcDaibanMapper.selectByID33", complainId);

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
				.selectList("com.chinacreator.dzjc_dealtMatter.VJcDaibanMapper.selectByID4", consultId);

		SuperviseZX superviseZX = null;
		if (selectList.size() > 0) {
			superviseZX = selectList.get(0);
		}
		return superviseZX;
	}

	/**
	 * 查询咨询监察详情2
	 * 
	 * @param complainId
	 * @return
	 */
	@GET
	@Path("SuperviseZX2")
	public SuperviseZX getZX2(@QueryParam(value = "consultId") java.lang.String consultId) {

		List<SuperviseZX> selectList = DaoFactory.create(SuperviseZX.class).getSession()
				.selectList("com.chinacreator.dzjc_dealtMatter.VJcDaibanMapper.selectByID44", consultId);

		SuperviseZX superviseZX = null;
		if (selectList.size() > 0) {
			superviseZX = selectList.get(0);
		}
		return superviseZX;
	}

	@POST
	@Path("VJcDaiban")
	public VJcDaiban insert(VJcDaiban entity) {
		DaoFactory.create(VJcDaiban.class).insert(entity);
		return entity;
	}

	@POST
	@Path("VJcDaiban/{dbId}")
	public VJcDaiban update(VJcDaiban entity) {
		DaoFactory.create(VJcDaiban.class).update(entity);
		return entity;
	}

	@GET
	@Path("VJcDaiban")
	public Page<VJcDaiban> getListByPage(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		VJcDaiban entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, VJcDaiban.class) : new VJcDaiban();
		String code = null;
		try {
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (entity != null) {
			if ("".equals(code)) {
				Page<VJcDaiban> pageDemo = new Page<VJcDaiban>(pageable,null);	// 直接返回null的话会有读取中图片挡在页面上，生成一个空属性page替代掉
				return pageDemo;
			}
			entity.setDbOrgCode(code);
		}
		return DaoFactory.create(VJcDaiban.class).selectPageByCondition(entity, conditions, pageable, sortable, true);
	}
	
	
	
	/**
	 * 查询监察信息 根据业务Id
	 * 获取监察类型
	 * @param areaCodeMap
	 * @return
	 */
	@POST
	@Path("VJcDaiban/getSuperviserInfo")
	public String getSuperviseResult(@RequestBody Map<String,String> map) {
		
		String id=map.get("dbId");
		String superviseTypeNo="";
		
		if(!"".equals(id)&&id!=null){
			id=id.trim();
			
			TaJcWarningAppeal entity1=new TaJcWarningAppeal();
			entity1= DaoFactory
					.create(TaJcWarningAppeal.class)
					.getSession()
					.selectOne("com.chinacreator.dzjc_dealtMatter.TaJcWarningAppealMapper.selectByID",id);
			
			if(entity1!=null){
				String superviseInfoId=entity1.getSuperviseInfoId();
				SuperviserInfo superviserInfo= DaoFactory
						.create(SuperviserInfo.class)
						.getSession()
						.selectOne("com.chinacreator.dzjc_ruleEngine.SuperviserInfoMapper.selectByID",superviseInfoId);

				superviseTypeNo=superviserInfo.getSuperviseTypeNo();
			}					
			
		}
		
		
 		return superviseTypeNo;
	}

}
