package com.chinacreator.dzjc_performance.web.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_performance.DataUpload;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.EvalItemValue;
import com.chinacreator.dzjc_performance.EvalPointValue;
import com.chinacreator.dzjc_performance.ShowEvalItem;
import com.chinacreator.dzjc_performance.eval.calculators.ItemCalculatorFactory;
import com.chinacreator.dzjc_performance.eval.exception.CalculateException;
import com.chinacreator.dzjc_performance.service.EvalPointValueService;
import com.chinacreator.util.ResponseStatus;
import com.chinacreator.util.RoleUtil;


@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class DataUploadController {
	@Autowired
	EvalPointValueService evalPointValueService;
	//自动增长排序号
	private static int orderNo = 0;
	
	@GET
	@Path("dataUpload")
	public Page<DataUpload> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		
		// 获取用户信息
		String code = "";
		try {
			// 获取权限
			code = RoleUtil.getInstance().queryCodeByUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");

		DataUpload entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, DataUpload.class) : new DataUpload();
		entity.setCode(code);
		RowBounds4Page rowBounds = new RowBounds4Page((page - 1) * rows, rows,
				sortable, conditions, true);
		
		List<DataUpload> list = DaoFactory
				.create(DataUpload.class)
				.getSession()
				.selectList(
						"com.chinacreator.dzjc_performance.DataUploadMapper.selectByPage",
						entity, rowBounds);
		
		Page<DataUpload> pagingResult = new Page<DataUpload>(page,
				rows, rowBounds.getTotalSize(), list);
		if(pagingResult.getContents().size()>0){
			for (int i = 0; i < pagingResult.getContents().size(); i++) {
			String instanceId=pagingResult.getContents().get(i).getInstanceId();
			String objectId=pagingResult.getContents().get(i).getObjectId();
			EvalPointValue evalPointValue =new EvalPointValue();
			DicEvalPoint dicEvalPoint = new DicEvalPoint();
			evalPointValue.setEvalInstanceId(instanceId);
			evalPointValue.setEvalObjectId(objectId);
			List<EvalPointValue> evalpageList=	DaoFactory.create(EvalPointValue.class).getSession().selectList("com.chinacreator.dzjc_performance.EvalPointValueMapper.selectByuploadList",evalPointValue);
			double sum=0.0;
				for (int j = 0; j < evalpageList.size(); j++) {
					double pointscore=	evalpageList.get(j).getPointScore();
					if(pointscore==0.0){
						dicEvalPoint.setPointId(evalpageList.get(j).getPointId());
						List<DicEvalPoint> DicEvalPointPagelist= DaoFactory.create(DicEvalPoint.class).getSession().selectList("com.chinacreator.dzjc_performance.DicEvalPointMapper.select",dicEvalPoint);
						sum+=DicEvalPointPagelist.get(0).getMaxValue();
						pagingResult.getContents().get(i).setTotalValue(String.valueOf(sum));
					}else{
						sum+=evalpageList.get(j).getPointScore();
						pagingResult.getContents().get(i).setTotalValue(String.valueOf(sum));
					}
				}
				
				if(pagingResult.getContents().get(i).getBonuspoints()!=null){
					double bonuspoints = pagingResult.getContents().get(i).getBonuspoints();
					double totalvalue=	Double.parseDouble(pagingResult.getContents().get(i).getTotalValue());
					pagingResult.getContents().get(i).setTotalValue(String.valueOf(totalvalue+bonuspoints));
				}
				
			}
		}
		return pagingResult;
	}
	
	/**
	 * 查询上报详细信息
	 */
	@GET
	@Path("selectDetail/{instanceId}/{objectId}")
	public Map<String,List<ShowEvalItem>> selectDetail(@PathParam(value = "instanceId") String instanceId,
			@PathParam(value = "objectId") String objectId){
		//用来返回数据
		Map<String,List<ShowEvalItem>> map = new HashMap<String,List<ShowEvalItem>>();
		
		DataUpload dataUpload = new DataUpload();
		dataUpload.setInstanceId(instanceId);
		dataUpload.setObjectId(objectId);
		//获取所有指标信息
		List<DataUpload> detailList = DaoFactory.create(DataUpload.class).getSession().selectList(
				"com.chinacreator.dzjc_performance.DataUploadMapper.selectDetail",dataUpload);
		if(detailList.size()>0){
			DicEvalPoint dicEvalPoint =null;
			for (int i = 0; i < detailList.size(); i++) {
				String isGread=detailList.get(i).getIsGrade();
				//未评分状态取字典指标最大值
				if("N".equals(isGread)){
					dicEvalPoint = new DicEvalPoint();
					String pointId=detailList.get(i).getPointId();
					dicEvalPoint=DaoFactory.create(DicEvalPoint.class).selectByID(pointId);
					detailList.get(i).setPointScore(dicEvalPoint.getMaxValue());
				}
			}
		}
		//保存至map
		ShowEvalItem detail = new ShowEvalItem();
		List<ShowEvalItem> arrayList = new ArrayList<ShowEvalItem>();
		detail.setList(detailList);
		arrayList.add(detail);
		map.put("detail", arrayList);
		
		//获取考核项层级
		Integer level = selectLevel(dataUpload);
		
		//如果有多级考核项，先查询层级数，根据层级查询每层有多少考核项
		for (int i = 1; i <=level; i++) {
			ShowEvalItem entity = new ShowEvalItem();
			entity.setLevel(i);
			entity.setInstanceId(instanceId);
			entity.setObjectId(objectId);
			List<ShowEvalItem> list = selectItemByLevel(entity);
			
			//遍历每层所有考核项
			for (ShowEvalItem showEvalItem : list) {
				if(i <= 1){//是子考核项，查询所属考核指标
					dataUpload.setItemId(showEvalItem.getId());
					List<DataUpload> pointList = DaoFactory.create(DataUpload.class).getSession().selectList(
							"com.chinacreator.dzjc_performance.DataUploadMapper.selectPointByItem",dataUpload);
					showEvalItem.setList(pointList);
					showEvalItem.setRows(pointList.size());
				}else{
					//是父考核项，查询下一层级考核项
					//List<ShowEvalItem> sublist = selectItemByLevel(entity);
					List<ShowEvalItem> itemlist = map.get((i-1)+"");
					if (itemlist != null && itemlist.size()>0){
						ArrayList<ShowEvalItem> subList = new ArrayList<ShowEvalItem>();
						int rows = 0;
						//判断是否是自己的子项
						for (ShowEvalItem showEvalItem2 : itemlist) {
							if(showEvalItem.getId().equals(showEvalItem2.getParentId())){
								subList.add(showEvalItem2);
								rows += showEvalItem2.getRows();
							}
						}
						showEvalItem.setList(subList);
						showEvalItem.setRows(rows);
					}
				}
			}
			//逐级保存
			map.put(i+"", list);
		}
		
		//遍历查看数据，测试数据，可注释
		Set<Entry<String, List<ShowEvalItem>>> entrySet = map.entrySet();
		for (Entry<String, List<ShowEvalItem>> entry : entrySet) {
			List<ShowEvalItem> value = entry.getValue();
			for (ShowEvalItem showEvalItem : value) {
				System.out.println("showEvalItem:"+showEvalItem);
			}
		}	
		return map;
	}
	
	/**
	 * 查找加分操作
	 * @param bonuspoints
	 * @param reasonsforbonus
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@GET
	@Path("FindPreservationList/{id}")
	public List<DataUpload>   FindPreservationList(@PathParam(value = "id") String id){
		DataUpload dataUpload = new DataUpload();
		dataUpload.setId(id);
		List<DataUpload> list= DaoFactory.create(DataUpload.class).getSession().selectList("com.chinacreator.dzjc_performance.DataUploadMapper.FindPreservationList",dataUpload);
		
		return list;
	}
	
	/**
	 * 加分操作
	 * @param bonuspoints
	 * @param reasonsforbonus
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("Preservation/{bonuspoints}/{reasonsforbonus}/{id}")
	public double  Preservation(@PathParam(value = "bonuspoints") Double bonuspoints,@PathParam(value = "reasonsforbonus") String reasonsforbonus ,@PathParam(value = "id") String id){
		DataUpload update = new DataUpload();
		update.setId(id);
		List<DataUpload> list=	DaoFactory.create(DataUpload.class).getSession().selectList("com.chinacreator.dzjc_performance.DataUploadMapper.selectByID",update);
		double hisresult=0.0;
		Collection nuCon = new Vector(); 
		nuCon.add(null);
		list.removeAll(nuCon);
		if(list.size()>0){
			
			hisresult =	list.get(0).getBonuspoints();
		}

		DataUpload dataUpload = new DataUpload();
		dataUpload.setBonuspoints(bonuspoints);
		dataUpload.setReasonsforbonus(reasonsforbonus);
		dataUpload.setId(id);
		DaoFactory.create(DataUpload.class).update(dataUpload);
		double result=bonuspoints;
		result = result - hisresult;
		return result;
	}
	
	/**
	 * 上报操作
	 * @param objectId
	 * @param tableId
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("report/{objectId}/{instanceId}/{evalObjectType}/{id}")
	public ResponseStatus report(@PathParam(value = "objectId") String objectId,@PathParam(value = "instanceId") String instanceId,@PathParam(value = "evalObjectType") String evalObjectType,@PathParam(value = "id") String id){
		if(StringUtils.isBlank(objectId) || org.apache.commons.lang3.StringUtils.isBlank(instanceId)){
			return ResponseStatus.error("参数为空");
		}
		EvalPointValue entity = new EvalPointValue();
		entity.setEvalObjectId(objectId);
		entity.setEvalInstanceId(instanceId);
		List<EvalPointValue> list = DaoFactory.create(EvalPointValue.class).getSession().selectList(
				"com.chinacreator.dzjc_performance.EvalPointValueMapper.selectSubItem",entity);
		
		//获取考核项计算service
		ItemCalculatorFactory instance = ItemCalculatorFactory.getInstance();
		if(list==null ||list.size()<1){
			return ResponseStatus.error("实例集合为空或者长度小于1");
		}
		try {
			DicEvalPoint dicEvalPoint =null;
			for (EvalPointValue e : list) {
				String itemId = e.getItemId();
				String tableId = e.getTableId();
				EvalPointValue entitys = new EvalPointValue();
				entitys.setEvalObjectId(objectId);
				entitys.setTableId(tableId);
				//获取指定考核对象指定考核表的所有指标成绩
				List<EvalPointValue> pointValueList = DaoFactory.create(EvalPointValue.class).getSession().selectList(
						"com.chinacreator.dzjc_performance.EvalPointValueMapper.selectByTableAndObject", entitys);
				for (int i = 0; i < pointValueList.size(); i++) {
					
					if(pointValueList.get(i).getIsGrade().equals("N")){
						dicEvalPoint = new DicEvalPoint();
						
						dicEvalPoint =	DaoFactory.create(DicEvalPoint.class).selectByID(pointValueList.get(i).getPointId());
						EvalPointValue es = new EvalPointValue();
						es.setEvalObjectType(evalObjectType);
						es.setPointId(pointValueList.get(i).getPointId());
						es.setPointName(pointValueList.get(i).getPointName());
						es.setPointScore(dicEvalPoint.getMaxValue());
						es.setId(pointValueList.get(i).getId());
						evalPointValueService.updatePointValue(es);
					}
				}
				//获取考核项得分
				Double itemValue = instance.getItemValue(itemId, objectId, tableId,instanceId);
				EvalItemValue evalItemValue = new EvalItemValue();
				evalItemValue.setItemId(itemId);
				evalItemValue.setTableId(tableId);
				evalItemValue.setEvalObjectId(objectId);
				evalItemValue.setEvalTime(new Date(new java.util.Date().getTime()));
				evalItemValue.setEvalType(e.getEvalObjectType());
				evalItemValue.setFinalItemValue(itemValue);
				evalItemValue.setInstanceId(e.getEvalInstanceId());
				evalItemValue.setIsTotalvalue("Y");
				evalItemValue.setItemValue(itemValue);
				orderNo++;
				evalItemValue.setOrderNo(orderNo);
				evalItemValue.setOrgId(e.getOrgId());
				evalItemValue.setEvalObjectName(e.getEvalObjectName());
				EvalItemValue exist= DaoFactory.create(EvalItemValue.class).getSession()
						.selectOne("com.chinacreator.dzjc_performance.EvalItemValueMapper.selectByOne", evalItemValue);
				if(exist != null){
					evalItemValue.setRecordId(exist.getRecordId());
					DaoFactory.create(EvalItemValue.class).update(evalItemValue);
				}else{
					DaoFactory.create(EvalItemValue.class).insert(evalItemValue);
				}
			}
			DataUpload dataUpload = new DataUpload();
			dataUpload.setId(id);
			/*dataUpload.setObjectId(objectId);*/
			dataUpload.setState("Y");
			DaoFactory.create(DataUpload.class).update(dataUpload);
		} catch (CalculateException e) {
			return ResponseStatus.error(e.getMessage());
		}
		return ResponseStatus.success("上报成功");
	}
	
	
	/**
	 * 根据考核项层级查询
	 * @param parentId
	 * @return
	 */
	private List<ShowEvalItem> selectItemByLevel(ShowEvalItem entity){
		
		List<ShowEvalItem> showEvalItemList = DaoFactory.create(DataUpload.class).getSession().selectList(
				"com.chinacreator.dzjc_performance.DataUploadMapper.selectByLevel",entity);
		return showEvalItemList;
		
	}
	
	/**
	 * 查询考核项层级
	 * @param objectId
	 * @return
	 */
	private Integer selectLevel(DataUpload dataUpload){
		Integer level = DaoFactory.create(DataUpload.class).getSession().selectOne(
					"com.chinacreator.dzjc_performance.DataUploadMapper.selectLevel",dataUpload);
		 return level;
	}
	
	
	/**
	 * 批量上报操作
	 * @param jsonObject
	 * @return
	 */
	@POST
	@Path("reportAllList")
	public String reportAllList(JSONObject jsonObject){
		
		String message="";
		String data=jsonObject.toJSONString();
		JSONObject json = JSON.parseObject(data);
		String dataList=json.getString("dataList");
		if(StringUtils.isNotEmpty(dataList)){
			JSONArray totolArray= JSONArray.parseArray(dataList);
			for (int i = 0; i < totolArray.size();i++) {
				String objectId =  JSONObject.parseObject(JSONObject.toJSONString(totolArray.get(i))).getString("objectId");
				String instanceId= JSONObject.parseObject(JSONObject.toJSONString(totolArray.get(i))).getString("instanceId");
				String evalObjectType= JSONObject.parseObject(JSONObject.toJSONString(totolArray.get(i))).getString("evalObjectType");
				String 	id= JSONObject.parseObject(JSONObject.toJSONString(totolArray.get(i))).getString("id");
				String state=JSONObject.parseObject(JSONObject.toJSONString(totolArray.get(i))).getString("state");
				if(StringUtils.isBlank(objectId) || org.apache.commons.lang3.StringUtils.isBlank(instanceId)){
					return"参数为空";
				}
				if(state.equals("未上报")){
					EvalPointValue entity = new EvalPointValue();
					entity.setEvalObjectId(objectId);
					entity.setEvalInstanceId(instanceId);
					List<EvalPointValue> list = DaoFactory.create(EvalPointValue.class).getSession().selectList(
							"com.chinacreator.dzjc_performance.EvalPointValueMapper.selectSubItem",entity);
					
					//获取考核项计算service
					ItemCalculatorFactory instance = ItemCalculatorFactory.getInstance();
					if(list==null ||list.size()<1){
						return "实例集合为空或者长度小于1";
					}
					try {
						DicEvalPoint dicEvalPoint =null;
						for (EvalPointValue e : list) {
							String itemId = e.getItemId();
							String tableId = e.getTableId();
							EvalPointValue entitys = new EvalPointValue();
							entitys.setEvalObjectId(objectId);
							entitys.setTableId(tableId);
							//获取指定考核对象指定考核表的所有指标成绩
							List<EvalPointValue> pointValueList = DaoFactory.create(EvalPointValue.class).getSession().selectList(
									"com.chinacreator.dzjc_performance.EvalPointValueMapper.selectByTableAndObject", entitys);
							for (int j = 0; j < pointValueList.size(); j++) {
								
								if(pointValueList.get(j).getIsGrade().equals("N")){
									dicEvalPoint = new DicEvalPoint();
									
									dicEvalPoint =	DaoFactory.create(DicEvalPoint.class).selectByID(pointValueList.get(j).getPointId());
									EvalPointValue es = new EvalPointValue();
									es.setEvalObjectType(evalObjectType);
									es.setPointId(pointValueList.get(j).getPointId());
									es.setPointName(pointValueList.get(j).getPointName());
									es.setPointScore(dicEvalPoint.getMaxValue());
									es.setId(pointValueList.get(j).getId());
									evalPointValueService.updatePointValue(es);
								}
							}
							//获取考核项得分
							Double itemValue = instance.getItemValue(itemId, objectId, tableId,instanceId);
							EvalItemValue evalItemValue = new EvalItemValue();
							evalItemValue.setItemId(itemId);
							evalItemValue.setTableId(tableId);
							evalItemValue.setEvalObjectId(objectId);
							evalItemValue.setEvalTime(new Date(new java.util.Date().getTime()));
							evalItemValue.setEvalType(e.getEvalObjectType());
							evalItemValue.setFinalItemValue(itemValue);
							evalItemValue.setInstanceId(e.getEvalInstanceId());
							evalItemValue.setIsTotalvalue("Y");
							evalItemValue.setItemValue(itemValue);
							orderNo++;
							evalItemValue.setOrderNo(orderNo);
							evalItemValue.setOrgId(e.getOrgId());
							evalItemValue.setEvalObjectName(e.getEvalObjectName());
							EvalItemValue exist= DaoFactory.create(EvalItemValue.class).getSession()
									.selectOne("com.chinacreator.dzjc_performance.EvalItemValueMapper.selectByOne", evalItemValue);
							if(exist != null){
								evalItemValue.setRecordId(exist.getRecordId());
								DaoFactory.create(EvalItemValue.class).update(evalItemValue);
							}else{
								DaoFactory.create(EvalItemValue.class).insert(evalItemValue);
							}
						}
						DataUpload dataUpload = new DataUpload();
						dataUpload.setId(id);
						/*dataUpload.setObjectId(objectId);*/
						dataUpload.setState("Y");
						DaoFactory.create(DataUpload.class).update(dataUpload);
						message="上报成功";
					} catch (CalculateException e) {
						message= e.getMessage();
					}
				}else{
					//不等于未上报,直接跳过当前循环执行下一次循环
					continue;
				}
				
			}
		}
		return message;	
			
	
	}
	
}
