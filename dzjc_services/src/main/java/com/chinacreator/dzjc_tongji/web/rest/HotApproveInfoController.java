package com.chinacreator.dzjc_tongji.web.rest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_tongji.HotApproveInfo;
import com.chinacreator.dzjc_tongji.JcOrgView;
import com.chinacreator.dzjc_tongji.servlet.HotApproveInfoExcelExport;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class HotApproveInfoController {

	@POST
	@Path("showHotApprove/{approveId}")
	public HotApproveInfo update(HotApproveInfo entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(HotApproveInfo.class).update(entity);
		return entity;
	}

	@POST
	@Path("showHotApprove")
	public HotApproveInfo insert(HotApproveInfo entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(HotApproveInfo.class).insert(entity);
		return entity;
	}

	@GET
	@Path("showHotApprove/{approveId}")
	public HotApproveInfo get(
			@PathParam(value = "approveId") java.lang.String approveId) {
		//TODO auto-generated method stub
		return DaoFactory.create(HotApproveInfo.class).selectByID(approveId);
	}

	@DELETE
	@Path("showHotApprove")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(HotApproveInfo.class).deleteBatch(ids);
	}

	@DELETE
	@Path("showHotApprove/{approveId}")
	public void delete(@PathParam(value = "approveId") java.lang.String approveId) {
		//TODO auto-generated method stub
		HotApproveInfo entity = new HotApproveInfo();
		entity.setApproveId(approveId);
		DaoFactory.create(HotApproveInfo.class).delete(entity);
	}

	@GET
	@Path("showHotApprove")
	public Page<HotApproveInfo> getListByPage(@QueryParam(value = "page") int page,
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
		HotApproveInfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, HotApproveInfo.class) : new HotApproveInfo();

		//分页条件封装
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,null,true);
		//查询数据
		List<HotApproveInfo> selectDataList = selectDataList(pageable,sortable,conditions,entity,rowbound,false);
		//封装返回
		Page<HotApproveInfo> pagingResult = new Page<HotApproveInfo>(page, rows, rowbound.getTotalSize(),selectDataList);
		
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
	private List<HotApproveInfo> selectDataList(Pageable pageable,Sortable sortable,
			Conditions conditions,HotApproveInfo entity,RowBounds4Page rowbound,Boolean isExcel){
		//1,定义参数
		String code = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> params = new HashMap<String,Object>();
		//2,查询登录用户权限
		JcOrgView orgView = RoleUtil.getInstance().getUserOrgId();
		code = orgView.getOrgId();
		params.put("orgId", code);
		
		if(conditions != null){
			//3,带参查询封装params
			List<Rule> listRule= conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field= listRule.get(i).getField();
				if("orgId".equals(field)){
					params.put("orgId", (String)listRule.get(i).getData());
					continue;
				}
				if("beginDate".equals(field)){
					Date  beginDate = new Date ((long)listRule.get(i).getData());
					String beginDateStr = df.format(beginDate);
					params.put("beginDate", beginDateStr);
					continue;
				}
				if("endDate".equals(field)){
					Date  endDate = new Date ((long)listRule.get(i).getData());
					String endDateStr = df.format(endDate);
					params.put("endDate", endDateStr);
					continue;
				}
				if("jcBeginDate".equals(field)){
					Date  beginDate = new Date ((long)listRule.get(i).getData());
					String beginDateStr = df.format(beginDate);
					params.put("jcBeginDate", beginDateStr);
					continue;
				}
				if("jcEndDate".equals(field)){
					Date  endDate = new Date ((long)listRule.get(i).getData());
					String endDateStr = df.format(endDate);
					params.put("jcEndDate", endDateStr);
					continue;
				}
				if("approveCode".equals(field)){
					params.put("approveCode", (String)listRule.get(i).getData());
					continue;
				}
				if("taskhandleitem".equals(field)){
					params.put("taskhandleitem", (String)listRule.get(i).getData());
					continue;
				}
				if("approveName".equals(field)){
					params.put("approveName", (String) "%"+ listRule.get(i).getData() +"%");
					continue;
				}
				if("orgName".equals(field)){
					params.put("orgName", (String) "%"+ listRule.get(i).getData() +"%");
					continue;
				}
				if("typeCode".equals(field)){
					params.put("typeCode", (String)listRule.get(i).getData());
					continue;
				}
				if("approveSource".equals(field)){
					params.put("approveSource", (String)listRule.get(i).getData());
					continue;
				}
				if("rightsId".equals(field)){
					params.put("rightsId", (String)listRule.get(i).getData());
					continue;
				}
			}
		}else{
			//4,不带参查询封装params
			if (entity.getBeginDate() != null) {
				Date  beginDate = new Date (entity.getBeginDate().getTime());
				String beginDateStr = df.format(beginDate);
				params.put("beginDate", beginDateStr);
			}
			if (entity.getEndDate() != null) {
				Date  endDate = new Date (entity.getEndDate().getTime());
				String endDateStr = df.format(endDate);
				params.put("endDate", endDateStr);
			}
			if (entity.getJcBeginDate() != null) {
				Date  beginDate = new Date (entity.getJcBeginDate().getTime());
				String beginDateStr = df.format(beginDate);
				params.put("jcBeginDate", beginDateStr);
			}
			if (entity.getJcEndDate() != null) {
				Date  endDate = new Date (entity.getJcEndDate().getTime());
				String endDateStr = df.format(endDate);
				params.put("jcEndDate", endDateStr);
			}
			if (entity.getRightsId() != null) {
				params.put("rightsId", entity.getRightsId());
			}
		}

		//5,数据查询
		List<HotApproveInfo> list = new ArrayList<HotApproveInfo>();
		//5.1,是否是导出excel
		if(isExcel){
			list = DaoFactory.create(HotApproveInfo.class).getSession().selectList("com.chinacreator.dzjc_tongji.HotApproveInfoMapper.selectData",  params);
		}else{
			list = DaoFactory.create(HotApproveInfo.class).getSession().selectList("com.chinacreator.dzjc_tongji.HotApproveInfoMapper.selectData",  params, rowbound);
		}
		return	list;
		
	}
	
	/**
	 * 导出excel
	 */
	public String exportExcel(String cond,String tableName,HttpServletRequest request, HttpServletResponse response){
		//1,导出数据查询
		List<HotApproveInfo> list = getList(cond);
		List<List<HotApproveInfo>> dataList = new ArrayList<List<HotApproveInfo>>();
		dataList.add(list);
		//2,表名
		String titleName = tableName;
		//3,Excel导出
		HotApproveInfoExcelExport excelExport = new HotApproveInfoExcelExport();
		excelExport.excelExport(titleName, dataList);
		//4,返回
		return titleName;
	}
	/**
	 * 导出时条件查询
	 * 
	 * @param cond
	 * @return
	 */
	private List<HotApproveInfo> getList(String cond) {

		// 创建分页对象
		Pageable pageable = new Pageable(1, 9999999);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("", "");
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		HotApproveInfo entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, HotApproveInfo.class)
				: new HotApproveInfo();
		
		return selectDataList(pageable,sortable,conditions,entity,null,true);
	}
	
	
}
