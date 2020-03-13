package com.chinacreator.dzjc_ruleEngine.web.rest;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
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
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.Dictdata;
import com.chinacreator.dzjc_ruleEngine.Dicttype;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DictdataController {

	@GET
	@Path("dictdata/{dictdataId}")
	public Dictdata get(
			@PathParam(value = "dictdataId") java.lang.String dictdataId) {
		//TODO auto-generated method stub
		return DaoFactory.create(Dictdata.class).selectByID(dictdataId);
	}

	@GET
	@Path("dictdata")
	public Page<Dictdata> getListByPage(@QueryParam(value = "page") int page,
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
		Dictdata entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond,
				Dictdata.class) : new Dictdata();

		return DaoFactory.create(Dictdata.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);

	}
	
	
	@GET
	@Path("dictdatas/getAll/{dicttypeId}")
	public Map<String, List<Dictdata>> getList(
			@PathParam(value = "dicttypeId") java.lang.String dicttypeId) {
		// 创建排序对象
		Dictdata dictdata = new Dictdata();
		dictdata.setDicttypeId(dicttypeId);
		Map<String, List<Dictdata>> map = new HashMap<String, List<Dictdata>>();
		map.put("result", DaoFactory.create(Dictdata.class).select(dictdata));
		return map;
	}

	@POST
	@Path("DictdataTree/{key}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "获取字典树", tags = "DictdataTreeResource", notes = "")
	public TreeNode[] getElements(@PathParam(value = "key") java.lang.String key) {
		List<DefaultTreeNode> childrenTree = new ArrayList<DefaultTreeNode>();

		List<Dictdata> lst = getDictdata(key);

		for (int j = 0; j < lst.size(); j++) {
			DefaultTreeNode dtC = new DefaultTreeNode(lst.get(j).getDictdataName(), lst.get(j)
					.getDictdataValue());
			childrenTree.add(dtC);
		}

		return childrenTree.toArray(new DefaultTreeNode[childrenTree.size()]);

	}

	/**
	 * 根据key获取静态值列表
	 * 
	 * @param key
	 * @return
	 */
	private List<Dictdata> getDictdata(String key) {
		// 数据字典信息
		Dao<Dictdata> ddDao = DaoFactory.create(Dictdata.class);
		Dao<Dicttype> dtDao = DaoFactory.create(Dicttype.class);

		Dicttype dt = new Dicttype();
		dt.setDicttypeName(key);
		Dicttype dtE = dtDao.selectOne(dt);
		Dictdata dd = new Dictdata();
		dd.setDicttypeId(dtE.getDicttypeId());
		List<Dictdata> ddlist = ddDao.select(dd);

		return ddlist;
	}

	/**
	 * 根据字典类型ID获取字典数据
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("dictdata/findListByTypeId/{dicttypeId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "根据字典类型ID获取字典数据", tags = "DictdataResource", notes = "")
	public List<Dictdata> findListByTypeId(
			@PathParam(value = "dicttypeId") java.lang.String dicttypeId) {
		List<Dictdata> list = new ArrayList<Dictdata>();
		list = DaoFactory
				.create(Dictdata.class)
				.getSession()
				.selectList("com.chinacreator.dictManage.DictdataMapper.findListByTypeId",
						dicttypeId);
		return list;
	}

	@GET
	@Path("dictdata/getServicesTypeCode/{servicesTypeCode}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "根据中介资质编码查询资质名称", tags = "DictdataResource", notes = "")
	public String getServicesTypeCode(@PathParam(value = "servicesTypeCode") String servicesTypeCode) {
		return DaoFactory
				.create(Dictdata.class)
				.getSession()
				.selectOne("com.chinacreator.dictManage.DictdataMapper.getServicesTypeCode",
						servicesTypeCode);
	}
	
}
