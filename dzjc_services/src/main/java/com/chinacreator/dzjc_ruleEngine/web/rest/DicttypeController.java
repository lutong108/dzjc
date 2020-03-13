package com.chinacreator.dzjc_ruleEngine.web.rest;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import com.chinacreator.dzjc_ruleEngine.Dictdata;
import com.chinacreator.dzjc_ruleEngine.Dicttype;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DicttypeController {

	@GET
	@Path("dicttype/{dicttypeId}")
	public Dicttype get(
			@PathParam(value = "dicttypeId") java.lang.String dicttypeId) {
		//TODO auto-generated method stub
		return DaoFactory.create(Dicttype.class).selectByID(dicttypeId);
	}

	@GET
	@Path("dicttype")
	public Page<Dicttype> getListByPage(@QueryParam(value = "page") int page,
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
		Dicttype entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				Dicttype.class) : new Dicttype();

		return DaoFactory.create(Dicttype.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);

	}
	
	
	@POST
	@Path("dicttypes/dictdatas")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "根据字典类型查询字典值", tags = "dictResource", notes = "")
	public Map<String, Map> getDictDataMap(Map<String, String[]> dictType) {
		String[] dictTypeNames = dictType.get("dictTypeNames");
		Map<String, Map> rtmap = new HashMap<String, Map>();
		Map<String, List<Dictdata>> map = new HashMap<String, List<Dictdata>>();
		
		if ((null != dictTypeNames) && (dictTypeNames.length > 0)) {
			for (String dictTypeName : dictTypeNames) {
				List<Dictdata> list = DaoFactory
						.create(Dictdata.class)
						.getSession()
						.selectList("com.chinacreator.dzjc_ruleEngine.DictdataMapper.selectBatch",
								dictTypeName);
				map.put(dictTypeName, list);
			}
		}
		rtmap.put("result", map);
		return rtmap;
	}
	
	@GET
	@Path("dicttypes/getAll")
	public Map<String, List<Dicttype>> getList() {
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable("dicttypeName", "asc");
		Map<String, List<Dicttype>> map = new HashMap<String, List<Dicttype>>();
		map.put("result", DaoFactory.create(Dicttype.class).selectAll(sortable));
		return map;
	}
	
}
