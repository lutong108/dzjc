package com.chinacreator.dzjc_tongji.web.rest;

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
import com.chinacreator.dzjc_ruleEngine.SuperviserInfoConsult;
import com.chinacreator.dzjc_ruleEngine.utils.DepartmentStatisticsExport;
import com.chinacreator.dzjc_ruleEngine.utils.DepartmentStatisticsSonExport;
import com.chinacreator.dzjc_ruleEngine.utils.TaJcComplainSuggestExport;
import com.chinacreator.dzjc_ruleEngine.utils.TownshipStatisticsExport;
import com.chinacreator.dzjc_ruleEngine.utils.TownshipStatisticsSonExport;
import com.chinacreator.dzjc_ruleEngine.utils.VillageLevelStatisticsExport;
import com.chinacreator.dzjc_ruleEngine.utils.VillageLevelStatisticsExportSon;
import com.chinacreator.dzjc_tongji.Ta_Jc_Sum_Xxylttj;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class Ta_Jc_Sum_XxylttjController {

	@POST
	@Path("Ta_Jc_Sum_Xxylttj/{id}")
	public Ta_Jc_Sum_Xxylttj update(Ta_Jc_Sum_Xxylttj entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).update(entity);
		return entity;
	}

	@POST
	@Path("Ta_Jc_Sum_Xxylttj")
	public Ta_Jc_Sum_Xxylttj insert(Ta_Jc_Sum_Xxylttj entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).insert(entity);
		return entity;
	}

	@GET
	@Path("Ta_Jc_Sum_Xxylttj/{id}")
	public Ta_Jc_Sum_Xxylttj get(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		return DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).selectByID(id);
	}

	@DELETE
	@Path("Ta_Jc_Sum_Xxylttj")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).deleteBatch(ids);
	}

	@DELETE
	@Path("Ta_Jc_Sum_Xxylttj/{id}")
	public void delete(@PathParam(value = "id") java.lang.String id) {
		//TODO auto-generated method stub
		Ta_Jc_Sum_Xxylttj entity = new Ta_Jc_Sum_Xxylttj();
		entity.setId(id);
		DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).delete(entity);
	}

	@GET
	@Path("Ta_Jc_Sum_Xxylttj")
	public Page<Ta_Jc_Sum_Xxylttj> getListByPage(
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
		Ta_Jc_Sum_Xxylttj entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, Ta_Jc_Sum_Xxylttj.class)
				: new Ta_Jc_Sum_Xxylttj();
		if(conditions!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Rule> listRule= conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field= listRule.get(i).getField();
				if("codeName".equals(field)){
					entity.setCodeName(listRule.get(i).getData().toString());
				}
				if("approveName".equals(field)){
					entity.setApproveName(listRule.get(i).getData().toString());
				}
				if("approveType".equals(field)){
					entity.setApproveType(listRule.get(i).getData().toString());
				}
				if("orgId".equals(field)){
					entity.setOrgId(listRule.get(i).getData().toString());
				}
				if("beginData".equals(field)){
					String start_date=	listRule.get(i).getData().toString();
					if(start_date.contains("-")){
						entity.setBeginData(start_date);
					}else{
						String  beginData = sdf.format(listRule.get(i).getData());
						entity.setBeginData(beginData);
					}
				}
				if("endData".equals(field)){
					String end_date=listRule.get(i).getData().toString();
					if(end_date.contains("-")){
						entity.setEndData(end_date);
					}else{
						String ebdData=sdf.format(listRule.get(i).getData());
						entity.setEndData(ebdData);
					}
				}	
			}
		}
		Page<Ta_Jc_Sum_Xxylttj> pagelist=	 DaoFactory.create(Ta_Jc_Sum_Xxylttj.class)
				.selectPageByCondition(entity, null, pageable, sortable,
						true);
		return pagelist;

	}
	public String excelExport(String cond,String type) {
		
		List<Ta_Jc_Sum_Xxylttj> busList = this.getList(cond);
		String titleName ="";
		if("7".equals(type)){
			VillageLevelStatisticsExport export = new VillageLevelStatisticsExport();
			 titleName = "村级办件统计";	
			 export.excelExport(titleName, busList);
		}else if("8".equals(type)){
			VillageLevelStatisticsExportSon export = new VillageLevelStatisticsExportSon();
			 titleName = "村级办件统计明细";	
			 export.excelExport(titleName, busList);
		}else if("9".equals(type)){
			TownshipStatisticsExport  export = new TownshipStatisticsExport();
			 titleName = "乡镇办件统计";	
			export.excelExport(titleName, busList);
		}else if("10".equals(type)){
			TownshipStatisticsSonExport  export = new TownshipStatisticsSonExport();
			 titleName = "乡镇办件统计明细";	
			export.excelExport(titleName, busList);
		}else if("11".equals(type)){
			DepartmentStatisticsExport  export = new DepartmentStatisticsExport();
			 titleName = "部门办件统计";	
			export.excelExport(titleName, busList);
		}else if("12".equals(type)){
			DepartmentStatisticsSonExport  export = new DepartmentStatisticsSonExport();
			 titleName = "部门办件统计明细";	
			export.excelExport(titleName, busList);
		}
		
		//创建excel导出对象
		
		return titleName;
	}
	/**
	 * 导出查询
	 * @param cond
	 * @return
	 */
	private List<Ta_Jc_Sum_Xxylttj> getList(String cond) {
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("","");
		// 创建分页对象
		Pageable pageable = new Pageable(1, 9999999);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		Ta_Jc_Sum_Xxylttj entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, Ta_Jc_Sum_Xxylttj.class) : new Ta_Jc_Sum_Xxylttj();
		
		if(conditions!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Rule> listRule= conditions.getRules();
			for (int i = 0; i < listRule.size(); i++) {
				String field= listRule.get(i).getField();
				if("codeName".equals(field)){
					entity.setCodeName(listRule.get(i).getData().toString());
				}
				if("approveName".equals(field)){
					entity.setApproveName(listRule.get(i).getData().toString());
				}
				if("approveType".equals(field)){
					entity.setApproveType(listRule.get(i).getData().toString());
				}
				if("orgId".equals(field)){
					entity.setOrgId(listRule.get(i).getData().toString());
				}
				if("beginData".equals(field)){
				 String start_date=	listRule.get(i).getData().toString();
					if(start_date.contains("-")){
						entity.setBeginData(start_date);
					}else{
						String  beginData = sdf.format(listRule.get(i).getData());
						entity.setBeginData(beginData);
					}
				 
				}
				if("endData".equals(field)){
					String end_date=listRule.get(i).getData().toString();
					if(end_date.contains("-")){
						entity.setEndData(end_date);
					}else{
						String ebdData=sdf.format(listRule.get(i).getData());
						entity.setEndData(ebdData);
					}
					
				}	
			}
		}
		return DaoFactory
				.create(Ta_Jc_Sum_Xxylttj.class)
				.selectPageByCondition(entity, null, pageable, sortable,
						true).getContents();
				
	}
}
