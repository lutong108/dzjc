package com.chinacreator.dzjc_union.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_tongji.JcOrgView;
import com.chinacreator.dzjc_tongji.ParamEntity;
import com.chinacreator.dzjc_union.UnionInstanceCount;
import com.chinacreator.util.RoleUtil;

@Controller
@Path("dzjc_union/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class UnionInstanceCountController {

	@GET
	@Path("UnionInstanceCount")
	public Page<UnionInstanceCount> getListByPage(
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
		UnionInstanceCount entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, UnionInstanceCount.class)
				: new UnionInstanceCount();

		return DaoFactory.create(UnionInstanceCount.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}
	
	@GET
	@Path("getUnionInstanceCount")
	public Page<UnionInstanceCount> getListByDatePage(
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
		UnionInstanceCount entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, UnionInstanceCount.class)
				: new UnionInstanceCount();
				boolean isHeJi=false;
		return findBanJianData(page,rows,sidx,sord,cond,isHeJi);

	}
	
	@GET
	@Path("GetOrgInstanceCount")
	public List<UnionInstanceCount> getDataListByBanJianTongJinew(
			String beginDate, String endDate, String orgId) {
		List<UnionInstanceCount> list;
	 System.out.println("===beginDate:"+beginDate+" endDate:"+endDate+" orgId:"+orgId);
			Map<String,String> params = new HashMap<String,String>();
			//params.put("xzqm",xzqm);
			if(StringUtils.isNotBlank(beginDate)){
				params.put("beginDate", beginDate);
			}
			if(StringUtils.isNotBlank(endDate)){
				params.put("endDate", endDate);
			}
		 
				params.put("orgId", orgId);
				list = DaoFactory.create(UnionInstanceCount.class).getSession().selectList("com.chinacreator.dzjc_union.UnionInstanceCountMapper.selectByDate",  params);
			 
		 
		return list;
	}
	
private Page<UnionInstanceCount> findBanJianData(int page, int rows, String sidx,String sord, String cond,boolean isHeJi) {
		
		//创建分页对象
		Pageable pageable = new Pageable(page, isHeJi?rows-1:rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		
		/*填充查询添加 机构id、开始时间、结束时间*/
		UnionInstanceCount entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, UnionInstanceCount.class) : new UnionInstanceCount();
		String orgId = entity.getOrgId();
		 
		String beginDateStr = entity.getBeginDate();
		String endDateStr = entity.getEndDate();
		 System.out.println("===beginDate:"+beginDateStr+" endDate:"+endDateStr+" orgId:"+orgId);
		List<UnionInstanceCount> list = null;
 
		/*将添加带入到sql中查询*/
		RowBounds4Page rowbound = new RowBounds4Page(pageable,sortable,conditions,true);
		Map<String,String> params = new HashMap<String,String>();
		if(orgId!=null){
			params.put("orgId", orgId);
			if(StringUtils.isNotBlank(beginDateStr)){
				params.put("beginDate", beginDateStr);
			}
			if(StringUtils.isNotBlank(endDateStr)){
				params.put("endDate", endDateStr);
			}
			list = DaoFactory.create(UnionInstanceCount.class).getSession().selectList("com.chinacreator.dzjc_union.UnionInstanceCountMapper.selectByDate",  params, rowbound);
			 
		}else{
			list = new ArrayList<UnionInstanceCount>();
		}
		/*当集合数据大于1是，算计合计数据并放入集合中*/
//		if(isHeJi&&list!=null&&list.size()>1){
//			if (page == 1) {
//				TongJi total = countTotal(userOrgView, params);
//				list.add(0, total);
//			}
//		}
		Page<UnionInstanceCount> pagingResult = new Page<UnionInstanceCount>(page, rows, rowbound.getTotalSize(),list);
		return pagingResult;
	}
}
