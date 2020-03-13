package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.QuanShengYingYongQingKuangStatistics;
import com.chinacreator.dzjc_ruleEngine.utils.QuanShengYingYongQingKuangStatisticsInfoWordExport;

import freemarker.template.Configuration;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class QuanShengYingYongQingKuangStatisticsController {
		
	private Configuration configuration = null;
	
	/**
	 * 全省应用情况统计
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("getQuanShengYingYongQingKuangStatisticsInfo")
	public Map<String, Object> getQuanShengYingYongQingKuangStatisticsInfo(
			@QueryParam(value = "beginDate") String beginDate,
			@QueryParam(value = "endDate") String endDate
			) {
		//创建实体用于封装数据
		Map<String, Object> map = new HashMap<String, Object>();
		QuanShengYingYongQingKuangStatistics qSYYQKStatistics= new QuanShengYingYongQingKuangStatistics();
		//将日期转换成指定格式并赋值
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		beginDate = sdf.format(new Date(Long.parseLong(beginDate)));
		endDate = sdf.format(new Date(Long.parseLong(endDate)));
		qSYYQKStatistics.setBeginDate(beginDate);
		qSYYQKStatistics.setEndDate(endDate);
		//查询发牌数据
		getPaiData(qSYYQKStatistics,map);
		//获取省本级事项数、收件数、办结数、产生应用事项数
		getDataOfProvince(qSYYQKStatistics,map);	
		//获取14个市州本级事项数、收件数、办结数、产生应用事项数
		getDataOfShiZhou(qSYYQKStatistics,map);
		//获取县市区事项数、收件数、办结数、产生应用事项数
		getDataOfXianShiQv(qSYYQKStatistics,map);
		//查询全省接入部门数
		getOrgNumber(map);
		return map;
	}
	
	/**
	 * 查询全省接入部门数
	 * @param qSYYQKStatistics
	 * @param map
	 * @return
	 */
	private void getOrgNumber(Map<String, Object> map){
		QuanShengYingYongQingKuangStatistics qSYYQKStatistics = DaoFactory
				.create(QuanShengYingYongQingKuangStatistics.class)
				.getSession()
				.selectOne(
						"com.chinacreator.dzjc_ruleEngine.QuanShengYingYongQingKuangStatisticsMapper.getOrgNumber");
		
		map.put("orgNumber", qSYYQKStatistics.getOrgNumber());
		
	}

	/**
	 *获取县市区事项数、收件数、办结数、产生应用事项数(全省总数据-省本级数据-14市州数据总数据)
	 * @param map
	 * @return
	 */
	private void getDataOfXianShiQv(QuanShengYingYongQingKuangStatistics qSYYQKStatistics,Map<String, Object> map){
		
		//全省合计数据
		QuanShengYingYongQingKuangStatistics allDataByTimeTotal = (QuanShengYingYongQingKuangStatistics) map.get("allDataByTimeTotal");
		QuanShengYingYongQingKuangStatistics allDataTotal = (QuanShengYingYongQingKuangStatistics) map.get("allDataTotal");
		//省本级合计数据
		QuanShengYingYongQingKuangStatistics sbjDataByTime = (QuanShengYingYongQingKuangStatistics) map.get("sbjDataByTime");
		QuanShengYingYongQingKuangStatistics sbjData = (QuanShengYingYongQingKuangStatistics) map.get("sbjData");
		//14市州本级合计数据
		QuanShengYingYongQingKuangStatistics shizhouDataByTime = (QuanShengYingYongQingKuangStatistics) map.get("shizhouDataByTime");
		QuanShengYingYongQingKuangStatistics shizhouData = (QuanShengYingYongQingKuangStatistics) map.get("shizhouData");
		//计算县市区合计数据(输入时间) 和  县市区产生应用事项数
		QuanShengYingYongQingKuangStatistics xianShiQvDataByTime = getDataOfTotal(allDataByTimeTotal,sbjDataByTime,shizhouDataByTime);
		map.put("xianshiqvDataByTime",xianShiQvDataByTime);
		ArrayList<QuanShengYingYongQingKuangStatistics> listDataByTime = new ArrayList<QuanShengYingYongQingKuangStatistics>();
		listDataByTime.add(xianShiQvDataByTime);
		List<Integer> applicationItemsCountByTime = getApplicationItemsCount(listDataByTime);
		Integer sjsCount = applicationItemsCountByTime.get(1);
		Integer bjsCount = applicationItemsCountByTime.get(2);
		map.put("xianshiqvAICSJSByTime", sjsCount);
		map.put("xianshiqvAICBJSByTime", bjsCount);
		
		//计算县市区合计数据(默认时间) 和  县市区产生应用事项数
		QuanShengYingYongQingKuangStatistics xianShiQvData = getDataOfTotal(allDataTotal,sbjData,shizhouData);
		map.put("xianshiqvData",xianShiQvData);
		ArrayList<QuanShengYingYongQingKuangStatistics> listData = new ArrayList<QuanShengYingYongQingKuangStatistics>();
		listData.add(xianShiQvData);
		List<Integer> applicationItemsCount = getApplicationItemsCount(listData);
		Integer sxsCount = applicationItemsCount.get(0);
		map.put("xianshiqvAICSXS", sxsCount);
		
	}
	/**
	 * 获取县市区合计数
	 * @param allData
	 * @param sbjData
	 * @param shizhouData
	 * @return
	 */
	private QuanShengYingYongQingKuangStatistics getDataOfTotal(QuanShengYingYongQingKuangStatistics allData,QuanShengYingYongQingKuangStatistics sbjData,QuanShengYingYongQingKuangStatistics shizhouData){
		QuanShengYingYongQingKuangStatistics bean = new QuanShengYingYongQingKuangStatistics();
		//事项数
		bean.setXzxksxs(Integer.parseInt(allData.getXzxksxs()) - Integer.parseInt(sbjData.getXzxksxs()) - Integer.parseInt(shizhouData.getXzxksxs())+ "")  ;//       --事项数   行政许可 
		bean.setXzjlsxs(Integer.parseInt(allData.getXzjlsxs()) - Integer.parseInt(sbjData.getXzjlsxs()) - Integer.parseInt(shizhouData.getXzjlsxs())+ "")  ;//       --事项数   行政奖励
		bean.setXzcjsxs(Integer.parseInt(allData.getXzcjsxs()) - Integer.parseInt(sbjData.getXzcjsxs()) - Integer.parseInt(shizhouData.getXzcjsxs())+ "")  ;//       --事项数   行政裁决
		bean.setXzjcsxs(Integer.parseInt(allData.getXzjcsxs()) - Integer.parseInt(sbjData.getXzjcsxs()) - Integer.parseInt(shizhouData.getXzjcsxs())+ "")  ;//       --事项数   行政检查
		bean.setXzgfsxs(Integer.parseInt(allData.getXzgfsxs()) - Integer.parseInt(sbjData.getXzgfsxs()) - Integer.parseInt(shizhouData.getXzgfsxs())+ "")  ;//       --事项数   行政给付
		bean.setXzqzsxs(Integer.parseInt(allData.getXzqzsxs()) - Integer.parseInt(sbjData.getXzqzsxs()) - Integer.parseInt(shizhouData.getXzqzsxs())+ "")  ;//       --事项数   行政强制
		bean.setXzqrsxs(Integer.parseInt(allData.getXzqrsxs()) - Integer.parseInt(sbjData.getXzqrsxs()) - Integer.parseInt(shizhouData.getXzqrsxs())+ "")  ;//       --事项数   行政确认
		bean.setXzcfsxs(Integer.parseInt(allData.getXzcfsxs()) - Integer.parseInt(sbjData.getXzcfsxs()) - Integer.parseInt(shizhouData.getXzcfsxs())+ "")  ;//       --事项数   行政处罚
		bean.setXzzssxs(Integer.parseInt(allData.getXzzssxs()) - Integer.parseInt(sbjData.getXzzssxs()) - Integer.parseInt(shizhouData.getXzzssxs())+ "")  ;//       --事项数   行政征收
		bean.setQtxzqlsxs(Integer.parseInt(allData.getQtxzqlsxs()) - Integer.parseInt(sbjData.getQtxzqlsxs()) - Integer.parseInt(shizhouData.getQtxzqlsxs())+ "")  ;//     --事项数   其他行政权利
		bean.setGgfwsxs(Integer.parseInt(allData.getGgfwsxs()) - Integer.parseInt(sbjData.getGgfwsxs()) - Integer.parseInt(shizhouData.getGgfwsxs())+ "")  ;//       --事项数   公共服务
		bean.setSxscount(Integer.parseInt(allData.getSxscount()) - Integer.parseInt(sbjData.getSxscount()) - Integer.parseInt(shizhouData.getSxscount())+ "")  ;//      --事项数   小计
		//收件数
		bean.setXzxksjs(Integer.parseInt(allData.getXzxksjs()) - Integer.parseInt(sbjData.getXzxksjs()) - Integer.parseInt(shizhouData.getXzxksjs())+ "")  ;//       --收件数   行政许可 
		bean.setXzjlsjs(Integer.parseInt(allData.getXzjlsjs()) - Integer.parseInt(sbjData.getXzjlsjs()) - Integer.parseInt(shizhouData.getXzjlsjs())+ "")  ;//       --收件数   行政奖励
		bean.setXzcjsjs(Integer.parseInt(allData.getXzcjsjs()) - Integer.parseInt(sbjData.getXzcjsjs()) - Integer.parseInt(shizhouData.getXzcjsjs())+ "")  ;//       --收件数   行政裁决
		bean.setXzjcsjs(Integer.parseInt(allData.getXzjcsjs()) - Integer.parseInt(sbjData.getXzjcsjs()) - Integer.parseInt(shizhouData.getXzjcsjs())+ "")  ;//       --收件数   行政检查
		bean.setXzgfsjs(Integer.parseInt(allData.getXzgfsjs()) - Integer.parseInt(sbjData.getXzgfsjs()) - Integer.parseInt(shizhouData.getXzgfsjs())+ "")  ;//       --收件数   行政给付
		bean.setXzqzsjs(Integer.parseInt(allData.getXzqzsjs()) - Integer.parseInt(sbjData.getXzqzsjs()) - Integer.parseInt(shizhouData.getXzqzsjs())+ "")  ;//       --收件数   行政强制
		bean.setXzqrsjs(Integer.parseInt(allData.getXzqrsjs()) - Integer.parseInt(sbjData.getXzqrsjs()) - Integer.parseInt(shizhouData.getXzqrsjs())+ "")  ;//       --收件数   行政确认
		bean.setXzcfsjs(Integer.parseInt(allData.getXzcfsjs()) - Integer.parseInt(sbjData.getXzcfsjs()) - Integer.parseInt(shizhouData.getXzcfsjs())+ "")  ;//       --收件数   行政处罚
		bean.setXzzssjs(Integer.parseInt(allData.getXzzssjs()) - Integer.parseInt(sbjData.getXzzssjs()) - Integer.parseInt(shizhouData.getXzzssjs())+ "")  ;//       --收件数   行政征收
		bean.setQtxzqlsjs(Integer.parseInt(allData.getQtxzqlsjs()) - Integer.parseInt(sbjData.getQtxzqlsjs()) - Integer.parseInt(shizhouData.getQtxzqlsjs())+ "")  ;//     --收件数   其他行政权利
		bean.setGgfwsjs(Integer.parseInt(allData.getGgfwsjs()) - Integer.parseInt(sbjData.getGgfwsjs()) - Integer.parseInt(shizhouData.getGgfwsjs())+ "")  ;//       --收件数   公共服务
		bean.setSjscount(Integer.parseInt(allData.getSjscount()) - Integer.parseInt(sbjData.getSjscount()) - Integer.parseInt(shizhouData.getSjscount())+ "")  ;//      --收件数   小计
		//办结数
		bean.setXzxkbjs(Integer.parseInt(allData.getXzxkbjs()) - Integer.parseInt(sbjData.getXzxkbjs()) - Integer.parseInt(shizhouData.getXzxkbjs())+ "")  ;//       --办结数   行政许可 
		bean.setXzjlbjs(Integer.parseInt(allData.getXzjlbjs()) - Integer.parseInt(sbjData.getXzjlbjs()) - Integer.parseInt(shizhouData.getXzjlbjs())+ "")  ;//       --办结数   行政奖励
		bean.setXzcjbjs(Integer.parseInt(allData.getXzcjbjs()) - Integer.parseInt(sbjData.getXzcjbjs()) - Integer.parseInt(shizhouData.getXzcjbjs())+ "")  ;//       --办结数   行政裁决
		bean.setXzjcbjs(Integer.parseInt(allData.getXzjcbjs()) - Integer.parseInt(sbjData.getXzjcbjs()) - Integer.parseInt(shizhouData.getXzjcbjs())+ "")  ;//       --办结数   行政检查
		bean.setXzgfbjs(Integer.parseInt(allData.getXzgfbjs()) - Integer.parseInt(sbjData.getXzgfbjs()) - Integer.parseInt(shizhouData.getXzgfbjs())+ "")  ;//       --办结数   行政给付
		bean.setXzqzbjs(Integer.parseInt(allData.getXzqzbjs()) - Integer.parseInt(sbjData.getXzqzbjs()) - Integer.parseInt(shizhouData.getXzqzbjs())+ "")  ;//       --办结数   行政强制
		bean.setXzqrbjs(Integer.parseInt(allData.getXzqrbjs()) - Integer.parseInt(sbjData.getXzqrbjs()) - Integer.parseInt(shizhouData.getXzqrbjs())+ "")  ;//       --办结数   行政确认
		bean.setXzcfbjs(Integer.parseInt(allData.getXzcfbjs()) - Integer.parseInt(sbjData.getXzcfbjs()) - Integer.parseInt(shizhouData.getXzcfbjs())+ "")  ;//       --办结数   行政处罚
		bean.setXzzsbjs(Integer.parseInt(allData.getXzzsbjs()) - Integer.parseInt(sbjData.getXzzsbjs()) - Integer.parseInt(shizhouData.getXzzsbjs())+ "")  ;//       --办结数   行政征收
		bean.setQtxzqlbjs(Integer.parseInt(allData.getQtxzqlbjs()) - Integer.parseInt(sbjData.getQtxzqlbjs()) - Integer.parseInt(shizhouData.getQtxzqlbjs())+ "")  ;//     --办结数   其他行政权利
		bean.setGgfwbjs(Integer.parseInt(allData.getGgfwbjs()) - Integer.parseInt(sbjData.getGgfwbjs()) - Integer.parseInt(shizhouData.getGgfwbjs())+ "")  ;//       --办结数   公共服务
		bean.setBjscount(Integer.parseInt(allData.getBjscount()) - Integer.parseInt(sbjData.getBjscount()) - Integer.parseInt(shizhouData.getBjscount())+ "")  ;//      --办结数   小计
		
		return bean;
	}
	
	/**
	 *获取14个市州本级事项数、收件数、办结数、产生应用事项数
	 * @param map
	 * @return
	 */
	private void getDataOfShiZhou(QuanShengYingYongQingKuangStatistics qSYYQKStatistics,Map<String, Object> map){
		List<QuanShengYingYongQingKuangStatistics> shizhouData= new ArrayList<QuanShengYingYongQingKuangStatistics>();
		List<QuanShengYingYongQingKuangStatistics> shizhouDataByTime= new ArrayList<QuanShengYingYongQingKuangStatistics>();
		//A,查询数据(依据传入时间)
		//1,获取事项数、收件数、办结数
		shizhouDataByTime = DaoFactory
				.create(QuanShengYingYongQingKuangStatistics.class)
				.getSession()
				.selectList(
						"com.chinacreator.dzjc_ruleEngine.QuanShengYingYongQingKuangStatisticsMapper.getDataOfShiZhou",
						qSYYQKStatistics);
		//2,得到带有合计列的数据,填充‘小计’列
		List<QuanShengYingYongQingKuangStatistics> shizhouDataByTimeList = getSum(shizhouDataByTime);
		for (QuanShengYingYongQingKuangStatistics data : shizhouDataByTimeList) {
			String area_name = data.getArea_name();
			//取14个市州本级行的数据、统计产生应用事项的个数
			if(area_name != null && area_name.equals("合计")){
				//赋值
				map.put("shizhouDataByTime",data);
				break;
			}			
		}
		//3,产生应用事项数(取收件数 和 办结数)
		List<Integer> applicationItemsCountByTime = getApplicationItemsCount(shizhouDataByTime);
		Integer sjsAICCount = applicationItemsCountByTime.get(1);
		Integer bjsAICCount = applicationItemsCountByTime.get(2);
		map.put("shizhouAICSJSByTime",sjsAICCount);
		map.put("shizhouAICBJSByTime",bjsAICCount);
		
		//B,查询数据(默认无时间限制)
		//1将日期转换成指定格式并赋值
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate = "2000-01-01";
		String endDate = sdf.format(new Date());
		QuanShengYingYongQingKuangStatistics condition = new QuanShengYingYongQingKuangStatistics();
		condition.setBeginDate(beginDate);
		condition.setEndDate(endDate);
		//2,获取事项数、收件数、办结数
		shizhouData = DaoFactory
				.create(QuanShengYingYongQingKuangStatistics.class)
				.getSession()
				.selectList(
						"com.chinacreator.dzjc_ruleEngine.QuanShengYingYongQingKuangStatisticsMapper.getDataOfShiZhou",
						condition);
		//3,得到带有合计列的数据,填充‘小计’列
		List<QuanShengYingYongQingKuangStatistics> shizhouDataList = getSum(shizhouData);
		for (QuanShengYingYongQingKuangStatistics data : shizhouDataList) {
			String area_name = data.getArea_name();
			//取14个市州本级行的数据、统计产生应用事项的个数
			if(area_name != null && area_name.equals("合计")){
				//赋值
				map.put("shizhouData",data);
				break;
			}			
		}
		//4,产生应用事项数(取事项数)
		List<Integer> applicationItemsCount = getApplicationItemsCount(shizhouData);
		Integer sxsCount = applicationItemsCount.get(0);
		map.put("shizhouAICSXS", sxsCount);

	}
	/**
	 * 获取省本级的事项数、收件数、办结数、产生应用事项数
	 * @param map
	 * @return
	 */
	private void getDataOfProvince(QuanShengYingYongQingKuangStatistics qSYYQKStatistics,Map<String, Object> map){
		List<QuanShengYingYongQingKuangStatistics> sbjData= new ArrayList<QuanShengYingYongQingKuangStatistics>();
		List<QuanShengYingYongQingKuangStatistics> sbjDataByTime= new ArrayList<QuanShengYingYongQingKuangStatistics>();
		//A,查询数据(依据传入时间)
		//1,获取事项数、收件数、办结数
		sbjDataByTime = DaoFactory
				.create(QuanShengYingYongQingKuangStatistics.class)
				.getSession()
				.selectList(
						"com.chinacreator.dzjc_ruleEngine.QuanShengYingYongQingKuangStatisticsMapper.getDataOfProvince",
						qSYYQKStatistics);
		//2,得到带有合计列的数据,填充‘小计’列
		List<QuanShengYingYongQingKuangStatistics> sbjDataByTimeList = getSum(sbjDataByTime);
		for (QuanShengYingYongQingKuangStatistics data : sbjDataByTimeList) {
			String area_code = data.getArea_code();
			String area_name = data.getArea_name();
			//取省本级行的数据、统计产生应用事项的个数
			if(area_code != null && area_code.equals("439900000000")){
				//赋值
				map.put("sbjDataByTime",data);
			}
			if(area_name != null && area_name.equals("合计")){
				map.put("allDataByTimeTotal",data);
			}
		}
		
		//3,产生应用事项数(取收件数 和 办结数),传入数据为省本级行数据
		QuanShengYingYongQingKuangStatistics sbjDataByTimeBean = (QuanShengYingYongQingKuangStatistics) map.get("sbjDataByTime");
		List<QuanShengYingYongQingKuangStatistics> sbjDataByTimeBeanList = new ArrayList<QuanShengYingYongQingKuangStatistics>();
		sbjDataByTimeBeanList.add(sbjDataByTimeBean);
		List<Integer> applicationItemsCountByTime = getApplicationItemsCount(sbjDataByTimeBeanList);
		
		Integer sjsAICCount = applicationItemsCountByTime.get(1);
		Integer bjsAICCount = applicationItemsCountByTime.get(2);
		map.put("sbjAICSJSByTime",sjsAICCount);
		map.put("sbjAICBJSByTime",bjsAICCount);
		//4,根据页面数据要求，计算全省产生应用事项个数
		List<QuanShengYingYongQingKuangStatistics> list = new ArrayList<QuanShengYingYongQingKuangStatistics>();
		//4.1,拿到全省“合计”行数据
		QuanShengYingYongQingKuangStatistics allDataByTimeTotal = (QuanShengYingYongQingKuangStatistics) map.get("allDataByTimeTotal");
		list.add(allDataByTimeTotal);
		//4.2,以集合形式传参
		List<Integer> applicationItemsCountByTimeTotal = getApplicationItemsCount(list);
		//4.3,获取收件数一栏的产生事项个数
		Integer sjsAICCountTotal = applicationItemsCountByTimeTotal.get(1);
		map.put("sjsAICCountTotal",sjsAICCountTotal);
		
		//B,查询数据(默认无时间限制)
		//1将日期转换成指定格式并赋值
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate = "2000-01-01";
		String endDate = sdf.format(new Date());
		QuanShengYingYongQingKuangStatistics condition = new QuanShengYingYongQingKuangStatistics();
		condition.setBeginDate(beginDate);
		condition.setEndDate(endDate);
		//2,获取事项数、收件数、办结数
		sbjData = DaoFactory
				.create(QuanShengYingYongQingKuangStatistics.class)
				.getSession()
				.selectList(
						"com.chinacreator.dzjc_ruleEngine.QuanShengYingYongQingKuangStatisticsMapper.getDataOfProvince",
						condition);
		//3,添加合计列的数据
		List<QuanShengYingYongQingKuangStatistics> sbjDataList = getSum(sbjData);
		//4,循环遍历
		for (QuanShengYingYongQingKuangStatistics data : sbjDataList) {
			String area_code = data.getArea_code();
			String area_name = data.getArea_name();
			//取省本级行的数据、统计产生应用事项的个数
			if(area_code != null && area_code.equals("439900000000")){
				//赋值
				map.put("sbjData",data);
			}
			if(area_name != null && area_name.equals("合计")){
				map.put("allDataTotal",data);
			}			
		}
		//5,产生应用事项数(取事项数)
		QuanShengYingYongQingKuangStatistics sbjDataBean = (QuanShengYingYongQingKuangStatistics) map.get("sbjData");
		List<QuanShengYingYongQingKuangStatistics> sbjDataBeanList = new ArrayList<QuanShengYingYongQingKuangStatistics>();
		sbjDataBeanList.add(sbjDataBean);
		List<Integer> applicationItemsCount = getApplicationItemsCount(sbjDataBeanList);
		
		Integer sxsCount = applicationItemsCount.get(0);
		map.put("sbjAICSXS", sxsCount);

	}
	
	/**
	 * 获取产生应用事项数(即：事项不为0的有哪些)
	 * @param qSYYQKStatistics
	 * @return
	 */
	private List<Integer> getApplicationItemsCount(List<QuanShengYingYongQingKuangStatistics> qSYYQKStatisticsList){
		//1,定义计数器
		List<Integer> countList = new ArrayList<Integer>();
		Integer sxsCount = 0;
		Integer sjsCount = 0;
		Integer bjsCount = 0;
		//2,获取全省或14个市州本级总计数
		QuanShengYingYongQingKuangStatistics dataOfSum = null;
		List<QuanShengYingYongQingKuangStatistics> sum = getSum(qSYYQKStatisticsList);
		for (QuanShengYingYongQingKuangStatistics quanShengYingYongQingKuangStatistics : sum) {
			if("合计".equals(quanShengYingYongQingKuangStatistics.getArea_name())){
				dataOfSum = quanShengYingYongQingKuangStatistics;
				break;
			}
		}
		//3,遍历对应属性判断其值是否为0，不为0即该事项为产生应用的事项
		
		//事项数
		if(!"0".equals(dataOfSum.getXzxksxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzjlsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzcjsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzjcsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzgfsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzqzsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzqrsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzcfsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getXzzssxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getQtxzqlsxs())){
			sxsCount++;
		}
		if(!"0".equals(dataOfSum.getGgfwsxs())){
			sxsCount++;
		}
		
		//收件数
		if(!"0".equals(dataOfSum.getXzxksjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzjlsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzcjsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzjcsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzgfsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzqzsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzqrsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzcfsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzzssjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getQtxzqlsjs())){
			sjsCount++;
		}
		if(!"0".equals(dataOfSum.getGgfwsjs())){
			sjsCount++;
		}
		
		//办结数
		if(!"0".equals(dataOfSum.getXzxkbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzjlbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzcjbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzjcbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzgfbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzqzbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzqrbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzcfbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getXzzsbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getQtxzqlbjs())){
			bjsCount++;
		}
		if(!"0".equals(dataOfSum.getGgfwbjs())){
			bjsCount++;
		}

		//4,将应用事项数量返回
		countList.add(sxsCount);
		countList.add(sjsCount);
		countList.add(bjsCount);
		
		return countList;
	}

	/**
	 * 发牌信息
	 * @param beginDate
	 * @param endDate
	 */
	private void getPaiData(QuanShengYingYongQingKuangStatistics qSYYQKStatistics,Map<String, Object> map){
		
		QuanShengYingYongQingKuangStatistics paiData= new QuanShengYingYongQingKuangStatistics();
		//查询发牌数据(红黄牌-撤销数和有效数)
		paiData = DaoFactory
				.create(QuanShengYingYongQingKuangStatistics.class)
				.getSession()
				.selectOne(
						"com.chinacreator.dzjc_ruleEngine.QuanShengYingYongQingKuangStatisticsMapper.getPaiData",
						qSYYQKStatistics);
		//计算发牌总数
		String paitotal = Integer.parseInt(paiData.getRed_yx()) + Integer.parseInt(paiData.getYellow_yx())+ 
				          Integer.parseInt(paiData.getRed_cx()) + Integer.parseInt(paiData.getYellow_cx())+ "";
		//计算黄牌总数
		String yellow_sum = Integer.parseInt(paiData.getYellow_yx())+ Integer.parseInt(paiData.getYellow_cx()) + "";
		//计算红牌总数
		String red_sum = Integer.parseInt(paiData.getRed_yx())+ Integer.parseInt(paiData.getRed_cx()) + "";
		//赋值
		paiData.setPaitotal(paitotal);
		paiData.setRed_sum(red_sum);
		paiData.setYellow_sum(yellow_sum);
		
		map.put("paiData",paiData);
		
	};
	
	/**
	 * 计算小计和总计数
	 * @param list
	 * @return
	 */
	private List<QuanShengYingYongQingKuangStatistics> getSum(List<QuanShengYingYongQingKuangStatistics> list){
	
		int xzxksxsCount = 0;
		int xzcfsxsCount = 0;
		int xzqzsxsCount = 0;
		int xzzssxsCount = 0;
		int xzgfsxsCount = 0;
		int xzjcsxsCount = 0;
		int xzqrsxsCount = 0;
		int xzjlsxsCount = 0;
		int xzcjsxsCount = 0;
		int qtxzqlsxsCount = 0;
		int ggfwsxsCount = 0;
		
		int xzxksjsCount = 0;
		int xzcfsjsCount = 0;
		int xzqzsjsCount = 0;
		int xzzssjsCount = 0;
		int xzgfsjsCount = 0;
		int xzjcsjsCount = 0;
		int xzqrsjsCount = 0;
		int xzjlsjsCount = 0;
		int xzcjsjsCount = 0;
		int qtxzqlsjsCount = 0;
		int ggfwsjsCount = 0;
		
		int xzxkbjsCount = 0;
		int xzcfbjsCount = 0;
		int xzqzbjsCount = 0;
		int xzzsbjsCount = 0;
		int xzgfbjsCount = 0;
		int xzjcbjsCount = 0;
		int xzqrbjsCount = 0;
		int xzjlbjsCount = 0;
		int xzcjbjsCount = 0;
		int qtxzqlbjsCount = 0;
		int ggfwbjsCount = 0;
		
		List<QuanShengYingYongQingKuangStatistics> quanShengYingYongQingKuangStatisticsList= new ArrayList<QuanShengYingYongQingKuangStatistics>();
		for (int i = 0; i < list.size(); i++) {	
			
			QuanShengYingYongQingKuangStatistics quanShengYingYongQingKuangStatisticsBean = list.get(i);
			
			//事项数 及 小计
			quanShengYingYongQingKuangStatisticsBean.setXzxksxs(list.get(i).getXzxksxs());
			quanShengYingYongQingKuangStatisticsBean.setXzcfsxs(list.get(i).getXzcfsxs());
			quanShengYingYongQingKuangStatisticsBean.setXzqzsxs(list.get(i).getXzqzsxs());
			quanShengYingYongQingKuangStatisticsBean.setXzzssxs(list.get(i).getXzzssxs());
			quanShengYingYongQingKuangStatisticsBean.setXzgfsxs(list.get(i).getXzgfsxs());
			quanShengYingYongQingKuangStatisticsBean.setXzjcsxs(list.get(i).getXzjcsxs());
			quanShengYingYongQingKuangStatisticsBean.setXzqrsxs(list.get(i).getXzqrsxs());
			quanShengYingYongQingKuangStatisticsBean.setXzjlsxs(list.get(i).getXzjlsxs());
			quanShengYingYongQingKuangStatisticsBean.setXzcjsxs(list.get(i).getXzcjsxs());
			quanShengYingYongQingKuangStatisticsBean.setQtxzqlsxs(list.get(i).getQtxzqlsxs());
			quanShengYingYongQingKuangStatisticsBean.setGgfwsxs(list.get(i).getGgfwsxs());
			
			Integer xzxksxsTemp = Integer.parseInt(list.get(i).getXzxksxs());
			Integer xzcfsxsTemp = Integer.parseInt(list.get(i).getXzcfsxs());
			Integer xzqzsxsTemp = Integer.parseInt(list.get(i).getXzqzsxs());
			Integer xzzssxsTemp = Integer.parseInt(list.get(i).getXzzssxs());
			Integer xzgfsxsTemp = Integer.parseInt(list.get(i).getXzgfsxs());
			Integer xzjcsxsTemp = Integer.parseInt(list.get(i).getXzjcsxs());
			Integer xzqrsxsTemp = Integer.parseInt(list.get(i).getXzqrsxs());
			Integer xzjlsxsTemp = Integer.parseInt(list.get(i).getXzjlsxs());
			Integer xzcjsxsTemp = Integer.parseInt(list.get(i).getXzcjsxs());
			Integer qtxzqlsxsTemp = Integer.parseInt(list.get(i).getQtxzqlsxs());
			Integer ggfwsxsTemp = Integer.parseInt(list.get(i).getGgfwsxs());

			quanShengYingYongQingKuangStatisticsBean.setSxscount((xzxksxsTemp + xzcfsxsTemp + xzqzsxsTemp + xzzssxsTemp 
					+ xzgfsxsTemp + xzjcsxsTemp + xzqrsxsTemp + xzjlsxsTemp + xzcjsxsTemp + qtxzqlsxsTemp 
					+ ggfwsxsTemp)+""); 
			
			//收件数 及 小计
			quanShengYingYongQingKuangStatisticsBean.setXzxksjs(list.get(i).getXzxksjs());
			quanShengYingYongQingKuangStatisticsBean.setXzcfsjs(list.get(i).getXzcfsjs());
			quanShengYingYongQingKuangStatisticsBean.setXzqzsjs(list.get(i).getXzqzsjs());
			quanShengYingYongQingKuangStatisticsBean.setXzzssjs(list.get(i).getXzzssjs());
			quanShengYingYongQingKuangStatisticsBean.setXzgfsjs(list.get(i).getXzgfsjs());
			quanShengYingYongQingKuangStatisticsBean.setXzjcsjs(list.get(i).getXzjcsjs());
			quanShengYingYongQingKuangStatisticsBean.setXzqrsjs(list.get(i).getXzqrsjs());
			quanShengYingYongQingKuangStatisticsBean.setXzjlsjs(list.get(i).getXzjlsjs());
			quanShengYingYongQingKuangStatisticsBean.setXzcjsjs(list.get(i).getXzcjsjs());
			quanShengYingYongQingKuangStatisticsBean.setQtxzqlsjs(list.get(i).getQtxzqlsjs());
			quanShengYingYongQingKuangStatisticsBean.setGgfwsjs(list.get(i).getGgfwsjs());

			Integer xzxksjsTemp = Integer.parseInt(list.get(i).getXzxksjs());
			Integer xzcfsjsTemp = Integer.parseInt(list.get(i).getXzcfsjs());
			Integer xzqzsjsTemp = Integer.parseInt(list.get(i).getXzqzsjs());
			Integer xzzssjsTemp = Integer.parseInt(list.get(i).getXzzssjs());
			Integer xzgfsjsTemp = Integer.parseInt(list.get(i).getXzgfsjs());
			Integer xzjcsjsTemp = Integer.parseInt(list.get(i).getXzjcsjs());
			Integer xzqrsjsTemp = Integer.parseInt(list.get(i).getXzqrsjs());
			Integer xzjlsjsTemp = Integer.parseInt(list.get(i).getXzjlsjs());
			Integer xzcjsjsTemp = Integer.parseInt(list.get(i).getXzcjsjs());
			Integer qtxzqlsjsTemp = Integer.parseInt(list.get(i).getQtxzqlsjs());
			Integer ggfwsjsTemp = Integer.parseInt(list.get(i).getGgfwsjs());

			quanShengYingYongQingKuangStatisticsBean.setSjscount((xzxksjsTemp + xzcfsjsTemp + xzqzsjsTemp + xzzssjsTemp 
					+ xzgfsjsTemp + xzjcsjsTemp + xzqrsjsTemp + xzjlsjsTemp + xzcjsjsTemp + qtxzqlsjsTemp 
					+ ggfwsjsTemp)+""); 
			
			//办结数 及 小计
			quanShengYingYongQingKuangStatisticsBean.setXzxkbjs(list.get(i).getXzxkbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzcfbjs(list.get(i).getXzcfbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzqzbjs(list.get(i).getXzqzbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzzsbjs(list.get(i).getXzzsbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzgfbjs(list.get(i).getXzgfbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzjcbjs(list.get(i).getXzjcbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzqrbjs(list.get(i).getXzqrbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzjlbjs(list.get(i).getXzjlbjs());
			quanShengYingYongQingKuangStatisticsBean.setXzcjbjs(list.get(i).getXzcjbjs());
			quanShengYingYongQingKuangStatisticsBean.setQtxzqlbjs(list.get(i).getQtxzqlbjs());
			quanShengYingYongQingKuangStatisticsBean.setGgfwbjs(list.get(i).getGgfwbjs());

			Integer xzxkbjsTemp = Integer.parseInt(list.get(i).getXzxkbjs());
			Integer xzcfbjsTemp = Integer.parseInt(list.get(i).getXzcfbjs());
			Integer xzqzbjsTemp = Integer.parseInt(list.get(i).getXzqzbjs());
			Integer xzzsbjsTemp = Integer.parseInt(list.get(i).getXzzsbjs());
			Integer xzgfbjsTemp = Integer.parseInt(list.get(i).getXzgfbjs());
			Integer xzjcbjsTemp = Integer.parseInt(list.get(i).getXzjcbjs());
			Integer xzqrbjsTemp = Integer.parseInt(list.get(i).getXzqrbjs());
			Integer xzjlbjsTemp = Integer.parseInt(list.get(i).getXzjlbjs());
			Integer xzcjbjsTemp = Integer.parseInt(list.get(i).getXzcjbjs());
			Integer qtxzqlbjsTemp = Integer.parseInt(list.get(i).getQtxzqlbjs());
			Integer ggfwbjsTemp = Integer.parseInt(list.get(i).getGgfwbjs());

			quanShengYingYongQingKuangStatisticsBean.setBjscount((xzxkbjsTemp + xzcfbjsTemp + xzqzbjsTemp + xzzsbjsTemp 
					+ xzgfbjsTemp + xzjcbjsTemp + xzqrbjsTemp + xzjlbjsTemp + xzcjbjsTemp + qtxzqlbjsTemp 
					+ ggfwbjsTemp)+""); 
			
			
			quanShengYingYongQingKuangStatisticsList.add(quanShengYingYongQingKuangStatisticsBean);
			
			//计算列数据总数
			xzxksxsCount += Integer.parseInt(list.get(i).getXzxksxs());
			xzcfsxsCount += Integer.parseInt(list.get(i).getXzcfsxs());
			xzqzsxsCount += Integer.parseInt(list.get(i).getXzqzsxs());
			xzzssxsCount += Integer.parseInt(list.get(i).getXzzssxs());
			xzgfsxsCount += Integer.parseInt(list.get(i).getXzgfsxs());
			xzjcsxsCount += Integer.parseInt(list.get(i).getXzjcsxs());
			xzqrsxsCount += Integer.parseInt(list.get(i).getXzqrsxs());
			xzjlsxsCount += Integer.parseInt(list.get(i).getXzjlsxs());
			xzcjsxsCount += Integer.parseInt(list.get(i).getXzcjsxs());
			qtxzqlsxsCount += Integer.parseInt(list.get(i).getQtxzqlsxs());
			ggfwsxsCount += Integer.parseInt(list.get(i).getGgfwsxs());
			
			xzxksjsCount += Integer.parseInt(list.get(i).getXzxksjs());
			xzcfsjsCount += Integer.parseInt(list.get(i).getXzcfsjs());
			xzqzsjsCount += Integer.parseInt(list.get(i).getXzqzsjs());
			xzzssjsCount += Integer.parseInt(list.get(i).getXzzssjs());
			xzgfsjsCount += Integer.parseInt(list.get(i).getXzgfsjs());
			xzjcsjsCount += Integer.parseInt(list.get(i).getXzjcsjs());
			xzqrsjsCount += Integer.parseInt(list.get(i).getXzqrsjs());
			xzjlsjsCount += Integer.parseInt(list.get(i).getXzjlsjs());
			xzcjsjsCount += Integer.parseInt(list.get(i).getXzcjsjs());
			qtxzqlsjsCount += Integer.parseInt(list.get(i).getQtxzqlsjs());
			ggfwsjsCount += Integer.parseInt(list.get(i).getGgfwsjs());
			
			xzxkbjsCount += Integer.parseInt(list.get(i).getXzxkbjs());
			xzcfbjsCount += Integer.parseInt(list.get(i).getXzcfbjs());
			xzqzbjsCount += Integer.parseInt(list.get(i).getXzqzbjs());
			xzzsbjsCount += Integer.parseInt(list.get(i).getXzzsbjs());
			xzgfbjsCount += Integer.parseInt(list.get(i).getXzgfbjs());
			xzjcbjsCount += Integer.parseInt(list.get(i).getXzjcbjs());
			xzqrbjsCount += Integer.parseInt(list.get(i).getXzqrbjs());
			xzjlbjsCount += Integer.parseInt(list.get(i).getXzjlbjs());
			xzcjbjsCount += Integer.parseInt(list.get(i).getXzcjbjs());
			qtxzqlbjsCount += Integer.parseInt(list.get(i).getQtxzqlbjs());
			ggfwbjsCount += Integer.parseInt(list.get(i).getGgfwbjs());
		}
		
		//计算总计行数据
		QuanShengYingYongQingKuangStatistics fenLeiTongJiBean = new QuanShengYingYongQingKuangStatistics();
		fenLeiTongJiBean.setArea_name("合计");
		
		fenLeiTongJiBean.setXzxksxs(xzxksxsCount+"");
		fenLeiTongJiBean.setXzcfsxs(xzcfsxsCount+"");
		fenLeiTongJiBean.setXzqzsxs(xzqzsxsCount+"");
		fenLeiTongJiBean.setXzzssxs(xzzssxsCount+"");
		fenLeiTongJiBean.setXzgfsxs(xzgfsxsCount+"");
		fenLeiTongJiBean.setXzjcsxs(xzjcsxsCount+"");
		fenLeiTongJiBean.setXzqrsxs(xzqrsxsCount+"");
		fenLeiTongJiBean.setXzjlsxs(xzjlsxsCount+"");
		fenLeiTongJiBean.setXzcjsxs(xzcjsxsCount+"");
		fenLeiTongJiBean.setQtxzqlsxs(qtxzqlsxsCount+"");
		fenLeiTongJiBean.setGgfwsxs(ggfwsxsCount+"");
		fenLeiTongJiBean.setSxscount((xzxksxsCount + xzcfsxsCount + xzqzsxsCount + xzzssxsCount + xzgfsxsCount 
				+ xzjcsxsCount + xzqrsxsCount + xzjlsxsCount + xzcjsxsCount + qtxzqlsxsCount + ggfwsxsCount)+"");
		
		fenLeiTongJiBean.setXzxksjs(xzxksjsCount+"");
		fenLeiTongJiBean.setXzcfsjs(xzcfsjsCount+"");
		fenLeiTongJiBean.setXzqzsjs(xzqzsjsCount+"");
		fenLeiTongJiBean.setXzzssjs(xzzssjsCount+"");
		fenLeiTongJiBean.setXzgfsjs(xzgfsjsCount+"");
		fenLeiTongJiBean.setXzjcsjs(xzjcsjsCount+"");
		fenLeiTongJiBean.setXzqrsjs(xzqrsjsCount+"");
		fenLeiTongJiBean.setXzjlsjs(xzjlsjsCount+"");
		fenLeiTongJiBean.setXzcjsjs(xzcjsjsCount+"");
		fenLeiTongJiBean.setQtxzqlsjs(qtxzqlsjsCount+"");
		fenLeiTongJiBean.setGgfwsjs(ggfwsjsCount+"");
		fenLeiTongJiBean.setSjscount((xzxksjsCount + xzcfsjsCount + xzqzsjsCount + xzzssjsCount + xzgfsjsCount 
			  + xzjcsjsCount + xzqrsjsCount + xzjlsjsCount + xzcjsjsCount + qtxzqlsjsCount + ggfwsjsCount)+"");
		
		fenLeiTongJiBean.setXzxkbjs(xzxkbjsCount+"");
		fenLeiTongJiBean.setXzcfbjs(xzcfbjsCount+"");
		fenLeiTongJiBean.setXzqzbjs(xzqzbjsCount+"");
		fenLeiTongJiBean.setXzzsbjs(xzzsbjsCount+"");
		fenLeiTongJiBean.setXzgfbjs(xzgfbjsCount+"");
		fenLeiTongJiBean.setXzjcbjs(xzjcbjsCount+"");
		fenLeiTongJiBean.setXzqrbjs(xzqrbjsCount+"");
		fenLeiTongJiBean.setXzjlbjs(xzjlbjsCount+"");
		fenLeiTongJiBean.setXzcjbjs(xzcjbjsCount+"");
		fenLeiTongJiBean.setQtxzqlbjs(qtxzqlbjsCount+"");
		fenLeiTongJiBean.setGgfwbjs(ggfwbjsCount+"");
		fenLeiTongJiBean.setBjscount((xzxkbjsCount + xzcfbjsCount + xzqzbjsCount + xzzsbjsCount + xzgfbjsCount 
			  + xzjcbjsCount + xzqrbjsCount + xzjlbjsCount + xzcjbjsCount + qtxzqlbjsCount + ggfwbjsCount)+"");
		
		//将“合计”行加入集合
		quanShengYingYongQingKuangStatisticsList.add(fenLeiTongJiBean);
		
		return quanShengYingYongQingKuangStatisticsList;
	}
	
	/**
	 * 数据权限查询判断(暂无校验)
	 * @return
	 */
	@POST
	@Path("QuanShengYingYongQingKuangStatistics/getOrgCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getOrgCode(){
		Map<String,Object> result=new HashMap<>();
		
		result.put("success",true);
		return result;
	}
	
	/**
	 * word表格导出
	 */
	public String expWord(String beginDate,String endDate){
		Map<String, Object> map= getQuanShengYingYongQingKuangStatisticsInfo(beginDate, endDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = sdf.format(new Date(Long.parseLong(beginDate)));
		String[] beginData = beginTime.split("-");
		String endTime = sdf.format(new Date(Long.parseLong(endDate)));
		String[] endData = endTime.split("-");
		map.put("beginYear", beginData[0]);
		map.put("beginMonth", beginData[1]);
		map.put("beginDay", beginData[2]);
		map.put("endYear", endData[0]);
		map.put("endMonth", endData[1]);
		map.put("endDay", endData[2]);
		
		String titleName="全省应用情况统计";	
		
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		
		// 创建word导出对象
		QuanShengYingYongQingKuangStatisticsInfoWordExport qSYYQKStatisticsInfoWordExport=new QuanShengYingYongQingKuangStatisticsInfoWordExport();
		qSYYQKStatisticsInfoWordExport.createWord(configuration, titleName, map);
		
		return titleName;
	}
	
}
