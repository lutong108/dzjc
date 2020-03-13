package com.chinacreator.dzjc_tongji.web.rest;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_log.util.ConsoleLogUtil;
import com.chinacreator.dzjc_ruleEngine.utils.BanJianFaPaiStatisticsInfoExcelExport;
import com.chinacreator.dzjc_ruleEngine.utils.BanJianFaPaiStatisticsInfoWordExport;
import com.chinacreator.dzjc_ruleEngine.utils.BusinessDealInfoExcelExport;
import com.chinacreator.dzjc_ruleEngine.utils.BusinessDealInfoWordExport;
import com.chinacreator.dzjc_ruleEngine.utils.FenLeiShiXiangBanJianStatisticsInfoExcelExport;
import com.chinacreator.dzjc_ruleEngine.utils.HuanJieFaPaiStatisticsInfoExcelExport;
import com.chinacreator.dzjc_ruleEngine.utils.HuanJieFaPaiStatisticsInfoWordExport;
import com.chinacreator.dzjc_ruleEngine.utils.JCSumSuperviseInfoAllWordExport;
import com.chinacreator.dzjc_ruleEngine.utils.ManualDealInfoExport;
import com.chinacreator.dzjc_ruleEngine.utils.QuanShengFaPaiStatisticsInfoExcelExport;
import com.chinacreator.dzjc_ruleEngine.utils.QuanShengFaPaiStatisticsInfoWordExport;
import com.chinacreator.dzjc_tongji.ExportExcelUtils;
import com.chinacreator.dzjc_tongji.JcOrgView;
import com.chinacreator.dzjc_tongji.ParamEntity;
import com.chinacreator.dzjc_tongji.SanJiJianChaPage;
import com.chinacreator.dzjc_tongji.TongJi;
import com.chinacreator.util.AuditLogUtil;
import com.chinacreator.util.ConstantUtil;
import com.chinacreator.util.DateUtil;
import com.chinacreator.util.RoleUtil;
import com.chinacreator.util.SystemPropertiesInstance;

import freemarker.template.Configuration;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TongJiController {

	private Configuration configuration = null;
	private final static String SYSTEM_AREANAME_VALUE = SystemPropertiesInstance
			.getInstance().getSystem_areaName();
	private final static String SYSTEM_SCOPE_VALUE = SystemPropertiesInstance
			.getInstance().getSystem_scope();
	private final static String SYSTEM_AREACODE_VALUE = SystemPropertiesInstance
			.getInstance().getSystem_areaCode();
	// "多规合一"指定机构AREACODE(现仅指长沙市)
	private final static String SYSTEM_DUOGUI_AREACODE_VALUE = "4301000000";

	@POST
	@Path("getSystemAreaCode")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getSystemAreaCode() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("SYSTEM_AREANAME_VALUE", SYSTEM_AREANAME_VALUE);
		map.put("SYSTEM_SCOPE_VALUE", SYSTEM_SCOPE_VALUE);
		map.put("SYSTEM_AREACODE_VALUE", SYSTEM_AREACODE_VALUE);
		map.put("SYSTEM_DUOGUI_AREACODE_VALUE", SYSTEM_DUOGUI_AREACODE_VALUE);
		map.put("success", true);
		return map;
	}

	/**
	 * @param request
	 * @param response
	 */
	@GET
	@Path("expExcelToBanJianTongJi")
	public void expExcelToBanJianTongJi(@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String orgId = request.getParameter("orgId");

		JcOrgView JcOrgView = null;
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null && !"null".equals(beginDate)) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null && !"null".equals(endDate)) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		if (StringUtils.isNotBlank(orgId)) {
			JcOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			JcOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> list = getDataListByBanJianTongJinew(params, JcOrgView);
		byte[] excelFile = ExportExcelUtils.expExcelBanJianTongJi(list);
		ExportExcelUtils.download(response, excelFile, "综合情况统计.xls");
	}

	/**
	 * 三级监察word与Excel导出
	 */
	@SuppressWarnings("deprecation")
	public String export(String type, String beginDate, String endDate,
			String org_id, String year, String month,
			HttpServletResponse response, HttpServletRequest request) {
		String titleName = "";
		JcOrgView JcOrgView = null;
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null && !"null".equals(beginDate)) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null && !"null".equals(endDate)) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		if (StringUtils.isNotBlank(org_id)) {
			JcOrgView = DaoFactory.create(JcOrgView.class).selectByID(org_id);
		} else {
			JcOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> listEgo = getDataListByBanJianTongJinew(params, JcOrgView);
		// 省本级的索引
		int provinceLevelIndex = 0;
		/* 当集合数据大于1是，算计合计数据并放入集合中 */
		if (listEgo != null && listEgo.size() > 1) {
			TongJi tongJiSum = new TongJi();
			tongJiSum.setAcceptNum(new BigDecimal(0));
			tongJiSum.setFinishNum(new BigDecimal(0));
			tongJiSum.setBeforeFinishNum(new BigDecimal(0));
			tongJiSum.setInstanceSuperviseYelloNum(0d);
			tongJiSum.setInstanceSuperviseRedNum(0d);
			tongJiSum.setComplainSuperviseYelloNum(0d);
			tongJiSum.setComplainSuperviseRedNum(0d);
			tongJiSum.setConsultSuperviseYelloNum(0d);
			tongJiSum.setConsultSuperviseRedNum(0d);
			tongJiSum.setComNum(0d);
			tongJiSum.setComplainReplyNum(0d);
			tongJiSum.setConsultNum(0d);
			tongJiSum.setConsultReplayNum(0d);
			tongJiSum.setAreaOrgName("合计");
			tongJiSum.setLegalTimeSum(new BigDecimal(0));
			tongJiSum.setPromiseTimeSum(new BigDecimal(0));
			tongJiSum.setTimeUseSumLegal(new BigDecimal(0));
			tongJiSum.setTimeUseSumPromise(new BigDecimal(0));
			for (TongJi tongJi : listEgo) {
				if ("439900000000000000000000".equals(tongJi.getAreaOrgCode())) {
					provinceLevelIndex = listEgo.indexOf(tongJi);
				}
				if (tongJi.getAcceptNum() != null)
					tongJiSum.setAcceptNum(tongJiSum.getAcceptNum().add(
							tongJi.getAcceptNum()));
				if (tongJi.getFinishNum() != null)
					tongJiSum.setFinishNum(tongJiSum.getFinishNum().add(
							tongJi.getFinishNum()));
				if (tongJi.getTiSuLvFloat() != null)
					tongJiSum.setTiSuLvFloat(tongJiSum.getTiSuLvFloat()
							+ tongJi.getTiSuLvFloat());
				if (tongJi.getBeforeFinishNum() != null)
					tongJiSum.setBeforeFinishNum(tongJiSum.getBeforeFinishNum()
							.add(tongJi.getBeforeFinishNum()));
				// 办件红牌数(张)
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getComplainSuperviseYelloNum() != null)
					tongJiSum.setComplainSuperviseYelloNum(tongJiSum
							.getComplainSuperviseYelloNum()
							+ tongJi.getComplainSuperviseYelloNum());
				if (tongJi.getComplainSuperviseRedNum() != null)
					tongJiSum.setComplainSuperviseRedNum(tongJiSum
							.getComplainSuperviseRedNum()
							+ tongJi.getComplainSuperviseRedNum());
				if (tongJi.getConsultSuperviseYelloNum() != null)
					tongJiSum.setConsultSuperviseYelloNum(tongJiSum
							.getConsultSuperviseYelloNum()
							+ tongJi.getConsultSuperviseYelloNum());
				if (tongJi.getConsultSuperviseRedNum() != null)
					tongJiSum.setConsultSuperviseRedNum(tongJiSum
							.getConsultSuperviseRedNum()
							+ tongJi.getConsultSuperviseRedNum());
				if (tongJi.getComNum() != null)
					tongJiSum.setComNum(tongJiSum.getComNum()
							+ tongJi.getComNum());
				if (tongJi.getComplainReplyNum() != null)
					tongJiSum.setComplainReplyNum(tongJiSum
							.getComplainReplyNum()
							+ tongJi.getComplainReplyNum());
				if (tongJi.getConsultNum() != null)
					tongJiSum.setConsultNum(tongJiSum.getConsultNum()
							+ tongJi.getConsultNum());
				if (tongJi.getConsultReplayNum() != null)
					tongJiSum.setConsultReplayNum(tongJiSum
							.getConsultReplayNum()
							+ tongJi.getConsultReplayNum());
				if (tongJi.getLegalTimeSum() != null)
					tongJiSum.setLegalTimeSum(tongJiSum.getLegalTimeSum().add(
							tongJi.getLegalTimeSum()));
				if (tongJi.getPromiseTimeSum() != null)
					tongJiSum.setPromiseTimeSum(tongJiSum.getPromiseTimeSum()
							.add(tongJi.getPromiseTimeSum()));
				if (tongJi.getTimeUseSumLegal() != null)
					tongJiSum.setTimeUseSumLegal(tongJiSum.getTimeUseSumLegal()
							.add(tongJi.getTimeUseSumLegal()));
				if (tongJi.getTimeUseSumPromise() != null)
					tongJiSum.setTimeUseSumPromise(tongJiSum
							.getTimeUseSumPromise().add(
									tongJi.getTimeUseSumPromise()));
			}
			tongJiSum.getBanJieLvFloat();
			tongJiSum.getTiSuLvFloat();
			// 计算市州的合计数据
			TongJi provinceLevel = listEgo.get(provinceLevelIndex);
			TongJi cityStateSum = new TongJi();
			if (JcOrgView.getOrgLevel() == 1 && provinceLevel != null) {
				cityStateSum.setAreaOrgName("市州合计");
				cityStateSum.setBeforeFinishNum(tongJiSum.getBeforeFinishNum()
						.subtract(provinceLevel.getBeforeFinishNum()));
				cityStateSum.setAcceptNum(tongJiSum.getAcceptNum().subtract(
						provinceLevel.getAcceptNum()));
				cityStateSum.setFinishNum(tongJiSum.getFinishNum().subtract(
						provinceLevel.getFinishNum()));
				cityStateSum.setComNum(tongJiSum.getComNum()
						- provinceLevel.getComNum());
				cityStateSum.setConsultNum(tongJiSum.getConsultNum()
						- provinceLevel.getConsultNum());
				cityStateSum.setInstanceSuperviseYelloNum(tongJiSum
						.getInstanceSuperviseYelloNum()
						- provinceLevel.getInstanceSuperviseYelloNum());
				cityStateSum.setInstanceSuperviseRedNum(tongJiSum
						.getInstanceSuperviseRedNum()
						- provinceLevel.getInstanceSuperviseRedNum());
				cityStateSum.setComplainReplyNum(tongJiSum
						.getComplainReplyNum()
						- provinceLevel.getComplainReplyNum());
				cityStateSum.setConsultReplayNum(tongJiSum
						.getConsultReplayNum()
						- provinceLevel.getConsultReplayNum());
				cityStateSum.setComplainSuperviseYelloNum(tongJiSum
						.getComplainSuperviseYelloNum()
						- provinceLevel.getComplainSuperviseYelloNum());
				cityStateSum.setComplainSuperviseRedNum(tongJiSum
						.getComplainSuperviseRedNum()
						- provinceLevel.getComplainSuperviseRedNum());
				cityStateSum.setConsultSuperviseYelloNum(tongJiSum
						.getConsultSuperviseYelloNum()
						- provinceLevel.getConsultSuperviseYelloNum());
				cityStateSum.setConsultSuperviseRedNum(tongJiSum
						.getConsultSuperviseRedNum()
						- provinceLevel.getConsultSuperviseRedNum());
				cityStateSum.setLegalTimeSum(tongJiSum.getLegalTimeSum()
						.subtract(provinceLevel.getLegalTimeSum()));
				cityStateSum.setPromiseTimeSum(tongJiSum.getPromiseTimeSum()
						.subtract(provinceLevel.getPromiseTimeSum()));
				cityStateSum.setTimeUseSumLegal(tongJiSum.getTimeUseSumLegal()
						.subtract(provinceLevel.getTimeUseSumLegal()));
				cityStateSum.setTimeUseSumPromise(tongJiSum
						.getTimeUseSumPromise().subtract(
								provinceLevel.getTimeUseSumPromise()));
				listEgo.add(cityStateSum);
			}
			listEgo.add(tongJiSum);
		}
		String titleNames = "";
		if (year != null && !"".equals(year) && month != null
				&& !"".equals(month)) {
			titleNames = "综合情况统计" + "-" + year + "年" + month + "月";
		} else if (beginDateStr != null && !"".equals(beginDateStr)
				&& endDateStr != null && !"".equals(endDateStr)) {
			titleNames = "综合情况统计" + "-" + beginDateStr + "——" + endDateStr;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			titleNames = "综合情况统计" + "-" + date;
		}

		// 此处插入"多规合一"数据，只有配置的SYSTEM_AREACODE_VALUE为长沙市时，导出及页面的才会对"多规合一"数据进行处理
		if (JcOrgView.getOrgThisLevel() == 2
				&& SYSTEM_DUOGUI_AREACODE_VALUE.equals(SYSTEM_AREACODE_VALUE)) {
			Map<String, TongJi> duoGuiTongJiMap = new HashMap<String, TongJi>();
			List<TongJi> tempList = new ArrayList<TongJi>();
			// 1,拥有"多规合一"数据机构的id
			Map<String, String> duoGuiOrg = getDuoGuiOrg();
			// 2,存在机构才赋值
			if (duoGuiOrg != null && duoGuiOrg.size() > 0) {
				// 3,"多规"数据封装进map
				List<TongJi> duoGuiTongJi = getByBanJianTongJiOrgAll(
						"isDuoGuiAll", beginDateStr, endDateStr, "1");
				for (int i = 0; i < duoGuiTongJi.size(); i++) {
					TongJi tongJi = duoGuiTongJi.get(i);
					String key = tongJi.getOrgId();
					if (duoGuiOrg.get(key) != null) {
						tongJi.setAreaOrgName("其中：多规合一");
						tongJi.setOrgId("DuoGui" + tongJi.getOrgId());
						TongJi value = tongJi;
						duoGuiTongJiMap.put(key, value);
					}
				}
				// 4,遍历listEgo按序列插入数据
				for (int i = 0; i < listEgo.size(); i++) {
					TongJi tongJiOrignal = listEgo.get(i);
					tempList.add(tongJiOrignal);
					TongJi duoGuiTongJiTemp = duoGuiTongJiMap.get(tongJiOrignal
							.getOrgId());
					if (duoGuiTongJiTemp != null) {
						tempList.add(duoGuiTongJiTemp);
					}
				}
				listEgo = tempList;
			}
		}

		List<List<TongJi>> jclist = new ArrayList<List<TongJi>>();
		jclist.add(listEgo);
		// 7代表word导出
		if ("7".equals(type)) {
			// 新办件统计word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 创建word导出对象
			JCSumSuperviseInfoAllWordExport jCSumSuperviseInfoAllWordExport = new JCSumSuperviseInfoAllWordExport();
			titleName = jCSumSuperviseInfoAllWordExport.createWordbjtynew(
					configuration, titleNames, jclist);
			// 新办件统计excel导出
		} else if ("8".equals(type)) {
			TongJiController.creatorExcelValue(titleNames, response, listEgo, request);
		}
		return titleName;
	}

	/**
	 * 省三级监察累计（不带时间）
	 * 
	 * @param paramsReq
	 * @return
	 */
	@POST
	@Path("getShengSanJiJianChaLeiJi")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getShengSanJiJianChaLeiJi(
			@RequestBody Map<String, String> paramsReq) {
		Date beginDate = new Date();
		Map<String, Object> result = new HashMap<>();
		String orgId = paramsReq.get("orgId");
		JcOrgView jcOrgView = null;
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange("",
				"");
		if ("shiZhouHeJi".equals(orgId)) {
			params.put("isShiZhouHeJi", "true");
			jcOrgView = RoleUtil.getInstance().getUserOrgId();
		} else if (StringUtils.isNotBlank(orgId)) {
			jcOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			jcOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		// 获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String nowDate = df.format(new Date());// new Date()为获取当前系统时间
		if (jcOrgView != null) {
			try {
				result.put("orgIdAll", jcOrgView.getOrgId());
				if ("shiZhouHeJi".equals(orgId)) {
					result.put("orgNameAll", "市州");
				} else {
					result.put("orgNameAll", jcOrgView.getOrgName());
				}
				result.put("success", true);
				result.put("orgLevel", jcOrgView.getOrgLevel() + "");
				result.put("welcome", "欢迎使用" + SYSTEM_AREANAME_VALUE
						+ "政务服务一体化平台行政效能电子监管系统!");
				// 1,查询缓存表中是否已存在该机构数据
				Map<String, String> paramSel = new HashMap<>();
				if ("shiZhouHeJi".equals(orgId)) {
					paramSel.put("orgId", "shiZhouHeJi");
				} else {
					paramSel.put("orgId", jcOrgView.getOrgId());
				}
				paramSel.put("nowDate", nowDate);
				List<SanJiJianChaPage> sanJiJianChaPageList = DaoFactory
						.create(SanJiJianChaPage.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.SanJiJianChaPageMapper.selectCache",
								paramSel);
				if (sanJiJianChaPageList != null
						&& sanJiJianChaPageList.size() > 0) {
					SanJiJianChaPage sanJiJianChaPage = sanJiJianChaPageList
							.get(0);
					result.put("acceptNumAll",
							sanJiJianChaPage.getAcceptnumall());
					result.put("finishNumAll",
							sanJiJianChaPage.getFinishnumall());
					result.put("banJieLvFloatAll",
							sanJiJianChaPage.getBanjielvfloatall());
					result.put("comNumAll", sanJiJianChaPage.getComnumall());
					result.put("legalAccelerationRateAll",
							sanJiJianChaPage.getLegalaccelerationrateall());
					result.put("promiseAccelerationRateAll",
							sanJiJianChaPage.getPromiseaccelerationrateall());
					result.put("instanceYellowNumAll",
							sanJiJianChaPage.getInstanceyellownumall());
					result.put("instanceRedNumAll",
							sanJiJianChaPage.getInstancerednumall());
					result.put("consultYellowNumAll",
							sanJiJianChaPage.getConsultyellownumall());
					result.put("consultRedNumAll",
							sanJiJianChaPage.getConsultrednumall());

				} else {
					params.put("orgId", jcOrgView.getOrgId());
					params.put("orgLevel", jcOrgView.getOrgLevel() + "");
					// 获取累计数据
					List<TongJi> list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectBySanJiJianChaLeiJi",
									params);
					if (list != null && list.size() > 0) {
						TongJi tongJi = list.get(0);
						result.put("acceptNumAll", tongJi.getAcceptNum());
						result.put("finishNumAll", tongJi.getFinishNum());
						result.put("banJieLvFloatAll",
								tongJi.getBanJieLvFloat());
						result.put("comNumAll", tongJi.getComNum());
						result.put("legalAccelerationRateAll",
								tongJi.getLegalAccelerationRate());
						result.put("promiseAccelerationRateAll",
								tongJi.getPromiseAccelerationRate());
						result.put("instanceYellowNumAll",
								tongJi.getInstanceSuperviseYelloNum());
						result.put("instanceRedNumAll",
								tongJi.getInstanceSuperviseRedNum());
						result.put("consultYellowNumAll",
								tongJi.getConsultSuperviseYelloNum());
						result.put("consultRedNumAll",
								tongJi.getConsultSuperviseRedNum());

						// 2,把查询数据存入缓存表
						// 封装参数并插入
						if ("shiZhouHeJi".equals(orgId)) {
							result.put("orgId", "shiZhouHeJi");
						} else {
							result.put("orgId", jcOrgView.getOrgId());
						}
						result.put("nowDate", nowDate);
						DaoFactory
								.create(SanJiJianChaPage.class)
								.getSession()
								.insert("com.chinacreator.dzjc_tongji.SanJiJianChaPageMapper.insertCache",
										result);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour < 9 && hour > 18) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:S");
			Date endDate = new Date();
			ConsoleLogUtil.insertConsoleLog(
					"三级监获取察累计时间:",
					"begin:" + sdf.format(beginDate) + " end:"
							+ sdf.format(endDate));
		}
		return result;
	}

	/**
	 * 三级监察累计（不带时间）
	 * 
	 * @param paramsReq
	 * @return
	 */
	@POST
	@Path("getSanJiJianChaLeiJi")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getSanJiJianChaLeiJi(
			@RequestBody Map<String, String> paramsReq) {
		Map<String, Object> result = new HashMap<>();
		String orgId = paramsReq.get("orgId");
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange("",
				"");
		JcOrgView jcOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			jcOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			jcOrgView = RoleUtil.getInstance().getUserOrgId();
		}

		if (jcOrgView != null) {
			try {
				params.put("orgId", jcOrgView.getOrgId());
				params.put("orgLevel", jcOrgView.getOrgLevel() + "");
				// 获取累计数据
				List<TongJi> list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectBySanJiJianChaLeiJi",
								params);
				if (list != null && list.size() > 0) {
					TongJi tongJi = list.get(0);
					result.put("orgIdAll", tongJi.getOrgId());
					result.put("orgNameAll", tongJi.getAreaOrgName());
					result.put("acceptNumAll", tongJi.getAcceptNum());
					result.put("finishNumAll", tongJi.getFinishNum());
					result.put("banJieLvFloatAll", tongJi.getBanJieLvFloat());
					result.put("tiSuLvFloatAll", tongJi.getTiSuLvFloat());
					result.put("comNumAll", tongJi.getComNum());
					result.put("legalAccelerationRateAll",
							tongJi.getLegalAccelerationRate());
					result.put("promiseAccelerationRateAll",
							tongJi.getPromiseAccelerationRate());
					result.put("instanceYellowNumAll",
							tongJi.getInstanceSuperviseYelloNum());
					result.put("instanceRedNumAll",
							tongJi.getInstanceSuperviseRedNum());
					result.put("consultYellowNumAll",
							tongJi.getConsultSuperviseYelloNum());
					result.put("consultRedNumAll",
							tongJi.getConsultSuperviseRedNum());
					// 获取合计数据
					Map<String, Object> resultHeJi = getSanJiJianChaHeJi(params);
					result.put("acceptNum", resultHeJi.get("acceptNum"));
					result.put("finishNum", resultHeJi.get("finishNum"));
					result.put("comNum", resultHeJi.get("comNum"));
					result.put("complainReplyNum",
							resultHeJi.get("complainReplyNum"));
					result.put("consultNum", resultHeJi.get("consultNum"));
					result.put("consultReplayNum",
							resultHeJi.get("consultReplayNum"));
					result.put("instanceSuperviseYelloNum",
							resultHeJi.get("instanceSuperviseYelloNum"));
					result.put("instanceSuperviseRedNum",
							resultHeJi.get("instanceSuperviseRedNum"));
					/*
					 * result.put("complainSuperviseYelloNum",
					 * resultHeJi.get("complainSuperviseYelloNum"));
					 * result.put("complainSuperviseRedNum",
					 * resultHeJi.get("complainSuperviseRedNum"));
					 * result.put("consultSuperviseYelloNum",
					 * resultHeJi.get("consultSuperviseYelloNum"));
					 * result.put("consultSuperviseRedNum",
					 * resultHeJi.get("consultSuperviseRedNum"));
					 */
				}
				result.put("success", true);
				result.put("orgLevel", jcOrgView.getOrgLevel() + "");
				result.put("welcome", "欢迎使用" + SYSTEM_AREANAME_VALUE
						+ "政务服务一体化平台行政效能电子监管系统!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 三级监察合计（带时间）
	 * 
	 * @param paramsReq
	 * @return
	 */
	public Map<String, Object> getSanJiJianChaHeJi(
			@RequestBody Map<String, String> params) {
		Map<String, Object> result = new HashMap<>();
		try {
			List<TongJi> list = DaoFactory
					.create(TongJi.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.TongJiMapper.selectBySanJiJianChaLeiJi",
							params);
			if (list != null && list.size() > 0) {
				TongJi tongJi = list.get(0);
				result.put("acceptNum", tongJi.getAcceptNum());
				result.put("finishNum", tongJi.getFinishNum());
				result.put("comNum", tongJi.getComNum());
				result.put("complainReplyNum", tongJi.getComplainReplyNum());
				result.put("consultNum", tongJi.getConsultNum());
				result.put("consultReplayNum", tongJi.getConsultReplayNum());
				result.put("instanceSuperviseYelloNum",
						tongJi.getInstanceSuperviseYelloNum());
				result.put("instanceSuperviseRedNum",
						tongJi.getInstanceSuperviseRedNum());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 计算市州的合计数据
	 */
	@POST
	@Path("calculateCityAndStateSum")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> calculateCityAndStateSum(
			@RequestBody Map<String, String> paramsReq) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String orgId = paramsReq.get("orgId");
		String beginDate = paramsReq.get("beginDate");
		String endDate = paramsReq.get("endDate");
		JcOrgView jcOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			jcOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			jcOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(beginDate,
				endDate);
		dataMap.put("orgLevel", jcOrgView.getOrgLevel());
		// 如果orgLevel不为1，直接返回
		if (jcOrgView.getOrgLevel() != 1) {
			return dataMap;
		} else {
			try {
				params.put("orgId", jcOrgView.getOrgId());
				params.put("orgLevel", jcOrgView.getOrgLevel() + "");
				params.put("pId", jcOrgView.getOrgId());
				List<TongJi> list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJi",
								params);
				if (list != null && list.size() > 0) {
					TongJi sum = new TongJi();
					sum.setAcceptNum(new BigDecimal(0));
					sum.setFinishNum(new BigDecimal(0));
					sum.setComNum(0d);
					sum.setConsultNum(0d);
					sum.setInstanceSuperviseYelloNum(0d);
					sum.setInstanceSuperviseRedNum(0d);
					sum.setComplainReplyNum(0d);
					sum.setConsultReplayNum(0d);

					for (TongJi item : list) {
						if ("439900000000000000000000".equals(item
								.getAreaOrgCode())) {
							continue;
						}
						if (item.getAcceptNum() != null)
							sum.setAcceptNum(sum.getAcceptNum().add(
									item.getAcceptNum()));
						if (item.getFinishNum() != null)
							sum.setFinishNum(sum.getFinishNum().add(
									item.getFinishNum()));
						if (item.getComNum() != null)
							sum.setComNum(sum.getComNum() + item.getComNum());
						if (item.getConsultNum() != null)
							sum.setConsultNum(sum.getConsultNum()
									+ item.getConsultNum());
						if (item.getInstanceSuperviseYelloNum() != null)
							sum.setInstanceSuperviseYelloNum(sum
									.getInstanceSuperviseYelloNum()
									+ item.getInstanceSuperviseYelloNum());
						if (item.getInstanceSuperviseRedNum() != null)
							sum.setInstanceSuperviseRedNum(sum
									.getInstanceSuperviseRedNum()
									+ item.getInstanceSuperviseRedNum());
						if (item.getComplainReplyNum() != null)
							sum.setComplainReplyNum(sum.getComplainReplyNum()
									+ item.getComplainReplyNum());
						if (item.getConsultReplayNum() != null)
							sum.setConsultReplayNum(sum.getConsultReplayNum()
									+ item.getConsultReplayNum());
					}
					dataMap.put("cityStateAcceptNum", sum.getAcceptNum());
					dataMap.put("cityStateFinishNum", sum.getFinishNum());
					dataMap.put("cityStateComNum", sum.getComNum());
					dataMap.put("cityStateConsultNum", sum.getConsultNum());
					dataMap.put("cityStateInstanceYelloNum",
							sum.getInstanceSuperviseYelloNum());
					dataMap.put("cityStateInstanceRedNum",
							sum.getInstanceSuperviseRedNum());
					dataMap.put("cityStateComplainReplyNum",
							sum.getComplainReplyNum());
					dataMap.put("cityStateConsultReplayNum",
							sum.getConsultReplayNum());
				}
				// dataMap.put("show_city_state", true);
				dataMap.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataMap;
	}

	/**
	 * 省三级监察统计
	 */
	@GET
	@Path("getShengSanJiJianChaTongJiList")
	public Page<TongJi> getShengSanJiJianChaTongJiList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {

		// 创建分页对象
		Pageable pageable = new Pageable(page, rows - 1);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");

		/* 填充查询添加 机构id、开始时间、结束时间 */
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		JcOrgView userOrgView = null;
		if ("shiZhouHeJi".equals(orgId)) {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		} else if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		List<TongJi> list = new ArrayList<TongJi>();
		list = calculateThreeLevelSuperviseData(userOrgView, params, orgId);
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_TJFX,
				"getShengSanJiJianChaTongJiList", null, ConstantUtil.OP_OTHER,
				"省三级监察统计");
		return pagingResult;
	}

	/**
	 * 三级监察首页统计
	 */
	@GET
	@Path("getSanJiJianChaTongJiList")
	public Page<TongJi> getSanJiJianChaTongJiList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Page<TongJi> pagingResult = findBanJianData(page, rows, sidx, sord,
				cond, false);
		return pagingResult;
	}

	/**
	 * 行政效能监管统计
	 */
	@GET
	@Path("getBanJianTongJiList")
	public Page<TongJi> getBanJianTongJiList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Page<TongJi> pagingResult = findBanJianData(page, rows, sidx, sord,
				cond, true);
		AuditLogUtil
				.AuditLogToDB(ConstantUtil.MODULE_DZJC_TJFX,
						"getBanJianTongJiList", null, ConstantUtil.OP_OTHER,
						"行政效能监管统计");
		return pagingResult;
	}

	private Page<TongJi> findBanJianData(int page, int rows, String sidx,
			String sord, String cond, boolean isHeJi) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, isHeJi ? rows - 1 : rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 填充查询添加 机构id、开始时间、结束时间 */
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		/* 将添加带入到sql中查询 */
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/* 不同机构级别根据不同sql查询 */
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 湖南省、长沙市
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJi",
									params, rowbound);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiBenJi",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 3) {
					// 长沙市
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiCounty",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiOrg",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 4) {
					// 县本级
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiCounty",
									params, rowbound);
				} else {
					list = new ArrayList<TongJi>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		/* 当集合数据大于1是，算计合计数据并放入集合中 */
		if (isHeJi && list != null && list.size() > 1) {
			if (page == 1) {
				TongJi total = countTotal(userOrgView, params);
				list.add(0, total);
			}
		}
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		return pagingResult;
	}

	private TongJi countTotal(JcOrgView userOrgView, Map<String, String> params) {
		List<TongJi> totalList = null;
		TongJi tongJiSum = new TongJi();
		tongJiSum.setAcceptNum(new BigDecimal(0));
		tongJiSum.setFinishNum(new BigDecimal(0));
		tongJiSum.setLegalTimeSum(new BigDecimal(0));
		tongJiSum.setPromiseTimeSum(new BigDecimal(0));
		tongJiSum.setTimeUseSumLegal(new BigDecimal(0));
		tongJiSum.setTimeUseSumPromise(new BigDecimal(0));
		tongJiSum.setBeforeFinishNum(new BigDecimal(0));
		tongJiSum.setInstanceSuperviseGreenNum(0d);
		tongJiSum.setInstanceSuperviseYelloNum(0d);
		tongJiSum.setInstanceSuperviseRedNum(0d);
		tongJiSum.setComplainSuperviseYelloNum(0d);
		tongJiSum.setComplainSuperviseRedNum(0d);
		tongJiSum.setConsultSuperviseGreenNum(0d);
		tongJiSum.setConsultSuperviseYelloNum(0d);
		tongJiSum.setConsultSuperviseRedNum(0d);
		tongJiSum.setSpecialSuperviseGreenNum(0d);
		tongJiSum.setSpecialSuperviseYellowNum(0d);
		tongJiSum.setSpecialSuperviseRedNum(0d);
		tongJiSum.setComNum(0d);
		tongJiSum.setComplainReplyNum(0d);
		tongJiSum.setConsultNum(0d);
		tongJiSum.setConsultReplayNum(0d);
		tongJiSum.setAreaOrgName("合计");
		try {
			if (userOrgView.getOrgLevel() < 3
					&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1)) {
				totalList = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJi",
								params);
			} else if (userOrgView.getOrgThisLevel() == 2
					|| userOrgView.getOrgThisLevel() == 1) {
				totalList = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 3
					|| userOrgView.getOrgLevel() == 4) {
				totalList = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiCounty",
								params);
			} else if (userOrgView.getOrgLevel() == 5) {
				totalList = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiOrg",
								params);
			} else {
				totalList = new ArrayList<TongJi>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (totalList != null && totalList.size() > 0) {
			for (TongJi tongJi : totalList) {
				if (tongJi.getAcceptNum() != null)
					tongJiSum.setAcceptNum(tongJiSum.getAcceptNum().add(
							tongJi.getAcceptNum()));
				if (tongJi.getFinishNum() != null)
					tongJiSum.setFinishNum(tongJiSum.getFinishNum().add(
							tongJi.getFinishNum()));
				if (tongJi.getBeforeFinishNum() != null)
					tongJiSum.setBeforeFinishNum(tongJiSum.getBeforeFinishNum()
							.add(tongJi.getBeforeFinishNum()));
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getInstanceSuperviseGreenNum() != null)
					tongJiSum.setInstanceSuperviseGreenNum(tongJiSum
							.getInstanceSuperviseGreenNum()
							+ tongJi.getInstanceSuperviseGreenNum());
				if (tongJi.getComplainSuperviseYelloNum() != null)
					tongJiSum.setComplainSuperviseYelloNum(tongJiSum
							.getComplainSuperviseYelloNum()
							+ tongJi.getComplainSuperviseYelloNum());
				if (tongJi.getComplainSuperviseRedNum() != null)
					tongJiSum.setComplainSuperviseRedNum(tongJiSum
							.getComplainSuperviseRedNum()
							+ tongJi.getComplainSuperviseRedNum());
				if (tongJi.getConsultSuperviseGreenNum() != null)
					tongJiSum.setConsultSuperviseGreenNum(tongJiSum
							.getConsultSuperviseGreenNum()
							+ tongJi.getConsultSuperviseGreenNum());
				if (tongJi.getConsultSuperviseYelloNum() != null)
					tongJiSum.setConsultSuperviseYelloNum(tongJiSum
							.getConsultSuperviseYelloNum()
							+ tongJi.getConsultSuperviseYelloNum());
				if (tongJi.getConsultSuperviseRedNum() != null)
					tongJiSum.setConsultSuperviseRedNum(tongJiSum
							.getConsultSuperviseRedNum()
							+ tongJi.getConsultSuperviseRedNum());
				if (tongJi.getSpecialSuperviseGreenNum() != null)
					tongJiSum.setSpecialSuperviseGreenNum(tongJiSum
							.getSpecialSuperviseGreenNum()
							+ tongJi.getSpecialSuperviseGreenNum());
				if (tongJi.getSpecialSuperviseYellowNum() != null)
					tongJiSum.setSpecialSuperviseYellowNum(tongJiSum
							.getSpecialSuperviseYellowNum()
							+ tongJi.getSpecialSuperviseYellowNum());
				if (tongJi.getSpecialSuperviseRedNum() != null)
					tongJiSum.setSpecialSuperviseRedNum(tongJiSum
							.getSpecialSuperviseRedNum()
							+ tongJi.getSpecialSuperviseRedNum());
				if (tongJi.getComNum() != null)
					tongJiSum.setComNum(tongJiSum.getComNum()
							+ tongJi.getComNum());
				if (tongJi.getComplainReplyNum() != null)
					tongJiSum.setComplainReplyNum(tongJiSum
							.getComplainReplyNum()
							+ tongJi.getComplainReplyNum());
				if (tongJi.getConsultNum() != null)
					tongJiSum.setConsultNum(tongJiSum.getConsultNum()
							+ tongJi.getConsultNum());
				if (tongJi.getConsultReplayNum() != null)
					tongJiSum.setConsultReplayNum(tongJiSum
							.getConsultReplayNum()
							+ tongJi.getConsultReplayNum());
				if (tongJi.getLegalTimeSum() != null)
					tongJiSum.setLegalTimeSum(tongJiSum.getLegalTimeSum().add(
							tongJi.getLegalTimeSum()));
				if (tongJi.getPromiseTimeSum() != null)
					tongJiSum.setPromiseTimeSum(tongJiSum.getPromiseTimeSum()
							.add(tongJi.getPromiseTimeSum()));
				if (tongJi.getTimeUseSumLegal() != null)
					tongJiSum.setTimeUseSumLegal(tongJiSum.getTimeUseSumLegal()
							.add(tongJi.getTimeUseSumLegal()));
				if (tongJi.getTimeUseSumPromise() != null)
					tongJiSum.setTimeUseSumPromise(tongJiSum
							.getTimeUseSumPromise().add(
									tongJi.getTimeUseSumPromise()));
			}
		}
		return tongJiSum;
	}

	/**
	 * 导出查询,不需要分页
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @param jcOrgView
	 * @return
	 */
	private List<TongJi> getDataListByBanJianTongJinew(
			Map<String, String> params, JcOrgView jcOrgView) {
		List<TongJi> list;
		if (jcOrgView != null) {
			params.put("orgLevel", jcOrgView.getOrgLevel() + "");
			/* 不同机构级别根据不同sql查询 */
			if (jcOrgView.getOrgLevel() < 3
					&& !(jcOrgView.getOrgThisLevel() == 2 || jcOrgView
							.getOrgThisLevel() == 1)) {
				params.put("pId", jcOrgView.getOrgId());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJi",
								params);
			} else if (jcOrgView.getOrgThisLevel() == 2
					|| jcOrgView.getOrgThisLevel() == 1) {
				params.put("pId", jcOrgView.getOrgId());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiBenJi",
								params);
			} else if (jcOrgView.getOrgLevel() == 3
					|| jcOrgView.getOrgLevel() == 4) {
				params.put("countyCode", jcOrgView.getCountyCode());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiCounty",
								params);
			} else if (jcOrgView.getOrgLevel() == 5) {
				params.put("orgId", jcOrgView.getOrgId());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiOrg",
								params);
			} else {
				list = new ArrayList<TongJi>();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		return list;
	}

	
	
	/**
	 * 省三级监察Excel导出
	 * 
	 * @param titleNames
	 * @param response
	 * @param listEgo
	 */
	public static String creatorExcelValueForProvince(String titleNames,
			HttpServletResponse response, List<TongJi> listEgo,HttpServletRequest request) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
			sheet.setColumnWidth((short) 0, (short) 20 * 256);//
			sheet.setColumnWidth((short) 1, (short) 10 * 256);//
			sheet.setColumnWidth((short) 2, (short) 10 * 256);//
			sheet.setColumnWidth((short) 3, (short) 10 * 256);//
			sheet.setColumnWidth((short) 4, (short) 10 * 256);//
			sheet.setColumnWidth((short) 5, (short) 10 * 256);//
			sheet.setColumnWidth((short) 6, (short) 10 * 256);//
			sheet.setColumnWidth((short) 7, (short) 10 * 256);//
			sheet.setColumnWidth((short) 8, (short) 10 * 256);//
			sheet.setColumnWidth((short) 9, (short) 10 * 256);//
			sheet.setColumnWidth((short) 10, (short) 10 * 256);//
			sheet.setColumnWidth((short) 11, (short) 10 * 256);//
			sheet.setColumnWidth((short) 12, (short) 10 * 256);//
			sheet.setColumnWidth((short) 13, (short) 10 * 256);//
			sheet.setColumnWidth((short) 14, (short) 10 * 256);//
			sheet.setColumnWidth((short) 14, (short) 10 * 256);//
			sheet.setColumnWidth((short) 15, (short) 10 * 256);
			// 1
			Font headfonts = workbook.createFont();
			headfonts.setFontHeightInPoints((short) 20);// 字体大小
			// 2
			Font headfont_ = workbook.createFont();
			headfont_.setFontHeightInPoints((short) 15);// 字体大小
			// 3
			Font headfont = workbook.createFont();
			headfont.setFontName("宋体");
			headfont.setFontHeightInPoints((short) 10);// 字体大小
			headfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗

			// 1
			CellStyle styles = workbook.createCellStyle();
			styles.setFont(headfonts);
			styles.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			styles.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			styles.setBorderTop(CellStyle.BORDER_THIN);
			styles.setBorderBottom(CellStyle.BORDER_THIN);// 右边框
			styles.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			styles.setBorderRight(CellStyle.BORDER_THIN);

			// 2
			CellStyle style_ = workbook.createCellStyle();
			style_.setFont(headfont_);
			style_.setAlignment(CellStyle.ALIGN_RIGHT);// 左右居中
			style_.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			style_.setBorderTop(CellStyle.BORDER_THIN);
			style_.setBorderBottom(CellStyle.BORDER_THIN);// 设置单元格的边框为粗体
			style_.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			style_.setBorderRight(CellStyle.BORDER_THIN);

			// 3
			CellStyle headStyle = workbook.createCellStyle(); // 表头第一列的样式
			headStyle.setFont(headfont);
			headStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			headStyle.setWrapText(true);
			headStyle.setBorderRight(CellStyle.BORDER_THIN);
			headStyle.setBorderLeft(CellStyle.BORDER_THIN);
			headStyle.setBorderTop(CellStyle.BORDER_THIN);

			// 4
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderBottom(CellStyle.BORDER_THIN); // 设置单元格的边框为粗体

			// 1
			Row row;
			Cell cell;
			row = sheet.createRow(0);// 第一行，标题
			cell = row.createCell(0);
			cell.setCellValue("综合情况统计");
			cell.setCellStyle(styles);
			CellRangeAddress cra0 = new CellRangeAddress(0, 0, 0, 15);
			sheet.addMergedRegion(cra0);
			RegionUtil.setBorderBottom(1, cra0, sheet, workbook); // 下边框
			RegionUtil.setBorderLeft(1, cra0, sheet, workbook); // 左边框
			RegionUtil.setBorderRight(1, cra0, sheet, workbook); // 有边框
			RegionUtil.setBorderTop(1, cra0, sheet, workbook); // 上边框

			// 2
			row = sheet.createRow(1);// 第一行，标题
			cell = row.createCell(0);
			titleNames = titleNames.substring(7);
			cell.setCellValue(titleNames);
			cell.setCellStyle(style_);
			CellRangeAddress cra1 = new CellRangeAddress(1, 1, 0, 15);
			sheet.addMergedRegion(cra1);
			RegionUtil.setBorderBottom(1, cra1, sheet, workbook); // 下边框
			RegionUtil.setBorderLeft(1, cra1, sheet, workbook); // 左边框
			RegionUtil.setBorderRight(1, cra1, sheet, workbook); // 有边框
			RegionUtil.setBorderTop(1, cra1, sheet, workbook); // 上边框

			String[] coloumItems = { "行政区划", "收件数(件)",/* "老收件数(件)", */"办结数(件)",
					"办结率(%)", "法定提速率(%)", "承诺提速率(%)", "办件黄牌数(张)", "办件红牌数(张)",
					"咨询数(件)", "咨询回复数(件)", "咨询黄牌数(张)", "咨询红牌数(张)", "投诉数(件)",
					"投诉回复数(件)", "投诉黄牌数(张)", "投诉红牌数(张)" };
			row = sheet.createRow(2);// 第二行 标题
			for (int i = 0; i <= 15; i++) {
				row.createCell(i);
			}
			CellRangeAddress cra3 = new CellRangeAddress(2, 2, 1, 7);
			sheet.addMergedRegion(cra3);
			RegionUtil.setBorderTop(1, cra3, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra3, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra3, sheet, workbook);
			RegionUtil.setBorderRight(1, cra3, sheet, workbook);
			CellRangeAddress cra4 = new CellRangeAddress(2, 2, 8, 11);
			sheet.addMergedRegion(cra4);
			RegionUtil.setBorderTop(1, cra4, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra4, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra4, sheet, workbook);
			RegionUtil.setBorderRight(1, cra4, sheet, workbook);
			CellRangeAddress cra5 = new CellRangeAddress(2, 2, 12, 15);
			sheet.addMergedRegion(cra5);
			RegionUtil.setBorderTop(1, cra5, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra5, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra5, sheet, workbook);
			RegionUtil.setBorderRight(1, cra5, sheet, workbook);
			CellRangeAddress cra6 = new CellRangeAddress(2, 3, 0, 0);
			sheet.addMergedRegion(cra6);
			RegionUtil.setBorderTop(1, cra6, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra6, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra6, sheet, workbook);
			RegionUtil.setBorderRight(1, cra6, sheet, workbook);
			cell = row.getCell(0);
			cell.setCellStyle(headStyle);
			cell.setCellValue("行政区划");
			cell = row.getCell(1);
			cell.setCellStyle(headStyle);
			cell.setCellValue("办件业务");
			cell = row.getCell(8);
			cell.setCellStyle(headStyle);
			cell.setCellValue("咨询业务");
			cell = row.getCell(12);
			cell.setCellStyle(headStyle);
			cell.setCellValue("投诉业务");

			row = sheet.createRow(3);// 第二行 标题
			for (int i = 0; i < coloumItems.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(coloumItems[i]);
				cell.setCellStyle(headStyle);
			}
			int len = listEgo.size();
			// 循环创建数据行,填充数据.
			for (int i = 0; i < len; i++) {
				// 4
				int cellIndex = 0;
				row = sheet.createRow(i + 4);
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getAreaOrgName());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(listEgo.get(i).getAcceptNum()));
				cell.setCellStyle(style);

				/*
				 * //新收件数 cell = row.createCell(cellIndex++);
				 * cell.setCellValue(String
				 * .valueOf(listEgo.get(i).getAcceptNumNew()));
				 * cell.setCellStyle(style);
				 */

				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(listEgo.get(i).getFinishNum()));
				cell.setCellStyle(style);
				// 保留2位小数
				float bjPercent = (float) (Math.round(listEgo.get(i)
						.getBanJieLvFloat() * 100)) / 100;
				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(bjPercent));
				cell.setCellStyle(style);
				// 保留2位小数
				float legalAccelerationRate = (float) (Math.round(listEgo
						.get(i).getLegalAccelerationRate() * 100)) / 100;
				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(legalAccelerationRate));
				cell.setCellStyle(style);

				float promiseAccelerationRate = (float) (Math.round(listEgo
						.get(i).getPromiseAccelerationRate() * 100)) / 100;
				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(promiseAccelerationRate));
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getInstanceSuperviseYelloNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getInstanceSuperviseRedNum());
				cell.setCellStyle(style);
				/* 咨询 */
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultReplayNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultSuperviseYelloNum());
				cell.setCellStyle(style);
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultSuperviseRedNum());
				cell.setCellStyle(style);
				/* 投诉 */
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComplainReplyNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComplainSuperviseYelloNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComplainSuperviseRedNum());
				cell.setCellStyle(style);
			}
			String filename = "综合情况统计.xls";// 设置下载时客户端Excel的名称
			String wordname = processFileName(request,filename);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ wordname);
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titleNames;
	}

	/**
	 * 三级监察以及办件统计Excel导出
	 * 
	 * @param titleNames
	 * @param response
	 * @param listEgo
	 */
	public static String creatorExcelValue(String titleNames,
			HttpServletResponse response, List<TongJi> listEgo, HttpServletRequest request) {
		try {
			String[] coloumItems = { "行政区划", "收件数(件)", "办结数(件)", "办结率(%)",
					"法定提速率(%)", "承诺提速率(%)", "办件黄牌数(张)", "办件红牌数(张)", "咨询数(件)",
					"咨询回复数(件)", "咨询黄牌数(张)", "咨询红牌数(张)", "投诉数(件)", "投诉回复数(件)",
					"投诉黄牌数(张)", "投诉红牌数(张)" };
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
			sheet.setColumnWidth((short) 0, (short) 20 * 256);//
			sheet.setColumnWidth((short) 1, (short) 10 * 256);//
			sheet.setColumnWidth((short) 2, (short) 10 * 256);//
			sheet.setColumnWidth((short) 3, (short) 10 * 256);//
			sheet.setColumnWidth((short) 4, (short) 10 * 256);//
			sheet.setColumnWidth((short) 5, (short) 10 * 256);//
			sheet.setColumnWidth((short) 6, (short) 10 * 256);//
			sheet.setColumnWidth((short) 7, (short) 10 * 256);//
			sheet.setColumnWidth((short) 8, (short) 10 * 256);//
			sheet.setColumnWidth((short) 9, (short) 10 * 256);//
			sheet.setColumnWidth((short) 10, (short) 10 * 256);//
			sheet.setColumnWidth((short) 11, (short) 10 * 256);//
			sheet.setColumnWidth((short) 12, (short) 10 * 256);//
			sheet.setColumnWidth((short) 13, (short) 10 * 256);//
			sheet.setColumnWidth((short) 14, (short) 10 * 256);//
			sheet.setColumnWidth((short) 14, (short) 10 * 256);//
			// 1
			Font headfonts = workbook.createFont();
			headfonts.setFontHeightInPoints((short) 20);// 字体大小
			// 2
			Font headfont_ = workbook.createFont();
			headfont_.setFontHeightInPoints((short) 15);// 字体大小
			// 3
			Font headfont = workbook.createFont();
			headfont.setFontName("宋体");
			headfont.setFontHeightInPoints((short) 10);// 字体大小
			headfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗

			// 1
			CellStyle styles = workbook.createCellStyle();
			styles.setFont(headfonts);
			styles.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			styles.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			styles.setBorderTop(CellStyle.BORDER_THIN);// 上边框
			styles.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
			styles.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			styles.setBorderRight(CellStyle.BORDER_THIN);// 右边框

			// 2
			CellStyle style_ = workbook.createCellStyle();
			style_.setFont(headfont_);
			style_.setAlignment(CellStyle.ALIGN_RIGHT);// 左右居中
			style_.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			/* styles.setBorderBottom(CellStyle.BORDER_THIN); */// 设置单元格的边框为粗体
			style_.setBorderTop(CellStyle.BORDER_THIN);// 上边框
			style_.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
			style_.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			style_.setBorderRight(CellStyle.BORDER_THIN);// 右边框

			// 3
			CellStyle headStyle = workbook.createCellStyle(); // 表头第一列的样式
			headStyle.setFont(headfont);
			headStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			headStyle.setWrapText(true);
			headStyle.setBorderLeft(CellStyle.BORDER_THIN);
			headStyle.setBorderRight(CellStyle.BORDER_THIN);
			headStyle.setBorderTop(CellStyle.BORDER_THIN);
			headStyle.setBorderBottom(CellStyle.BORDER_THIN);

			// 4
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderBottom(CellStyle.BORDER_THIN); // 设置单元格的边框为粗体

			// 1
			Row row;
			Cell cell;
			row = sheet.createRow(0);// 第一行，标题
			cell = row.createCell(0);
			CellRangeAddress cra0 = new CellRangeAddress(0, 0, 0, 15);
			sheet.addMergedRegion(cra0);
			cell.setCellValue("行政效能监管统计");
			cell.setCellStyle(styles);
			RegionUtil.setBorderTop(1, cra0, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra0, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra0, sheet, workbook);
			RegionUtil.setBorderRight(1, cra0, sheet, workbook);
			// 2
			row = sheet.createRow(1);// 第一行，标题
			cell = row.createCell(0);
			CellRangeAddress cra1 = new CellRangeAddress(1, 1, 0, 15);
			sheet.addMergedRegion(cra1);
			titleNames = titleNames.substring(9);
			cell.setCellValue(titleNames);
			cell.setCellStyle(style_);
			RegionUtil.setBorderTop(1, cra1, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra1, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra1, sheet, workbook);
			RegionUtil.setBorderRight(1, cra1, sheet, workbook);

			row = sheet.createRow(2);// 第二行 标题
			for (int i = 0; i <= 15; i++) {
				row.createCell(i);
			}
			CellRangeAddress cra3 = new CellRangeAddress(2, 2, 1, 7);
			sheet.addMergedRegion(cra3);
			RegionUtil.setBorderTop(1, cra3, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra3, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra3, sheet, workbook);
			RegionUtil.setBorderRight(1, cra3, sheet, workbook);
			CellRangeAddress cra4 = new CellRangeAddress(2, 2, 8, 11);
			sheet.addMergedRegion(cra4);
			RegionUtil.setBorderTop(1, cra4, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra4, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra4, sheet, workbook);
			RegionUtil.setBorderRight(1, cra4, sheet, workbook);
			CellRangeAddress cra5 = new CellRangeAddress(2, 2, 12, 15);
			sheet.addMergedRegion(cra5);
			RegionUtil.setBorderTop(1, cra5, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra5, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra5, sheet, workbook);
			RegionUtil.setBorderRight(1, cra5, sheet, workbook);
			CellRangeAddress cra6 = new CellRangeAddress(2, 3, 0, 0);
			sheet.addMergedRegion(cra6);
			RegionUtil.setBorderTop(1, cra6, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra6, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra6, sheet, workbook);
			RegionUtil.setBorderRight(1, cra6, sheet, workbook);
			cell = row.getCell(0);
			cell.setCellStyle(headStyle);
			cell.setCellValue("行政区划");
			cell = row.getCell(1);
			cell.setCellStyle(headStyle);
			cell.setCellValue("办件业务");
			cell = row.getCell(8);
			cell.setCellStyle(headStyle);
			cell.setCellValue("咨询业务");
			cell = row.getCell(12);
			cell.setCellStyle(headStyle);
			cell.setCellValue("投诉业务");

			// 3
			row = sheet.createRow(3);// 第二行 标题
			for (int i = 0; i < coloumItems.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(coloumItems[i]);
				cell.setCellStyle(headStyle);
			}
			int len = listEgo.size();
			// 循环创建数据行,填充数据.
			for (int i = 0; i < len; i++) {
				// 4
				int cellIndex = 0;
				row = sheet.createRow(i + 4);
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getAreaOrgName());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(listEgo.get(i).getAcceptNum()));
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(listEgo.get(i).getFinishNum()));
				cell.setCellStyle(style);
				// 保留2位小数
				float bjPercent = (float) (Math.round(listEgo.get(i)
						.getBanJieLvFloat() * 100)) / 100;
				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(bjPercent));
				cell.setCellStyle(style);
				// 保留2位小数
				float legalAccelerationRate = (float) (Math.round(listEgo
						.get(i).getLegalAccelerationRate() * 100)) / 100;
				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(legalAccelerationRate));
				cell.setCellStyle(style);

				float promiseAccelerationRate = (float) (Math.round(listEgo
						.get(i).getPromiseAccelerationRate() * 100)) / 100;
				cell = row.createCell(cellIndex++);
				cell.setCellValue(String.valueOf(promiseAccelerationRate));
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getInstanceSuperviseYelloNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getInstanceSuperviseRedNum());
				cell.setCellStyle(style);
				/* 咨询类 */

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultReplayNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultSuperviseYelloNum());
				cell.setCellStyle(style);
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultSuperviseRedNum());
				cell.setCellStyle(style);
				/* 投诉类 */

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComplainReplyNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComplainSuperviseYelloNum());
				cell.setCellStyle(style);

				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getComplainSuperviseRedNum());
				cell.setCellStyle(style);
			}
			String filename = "行政效能监管统计.xls";// 设置下载时客户端Excel的名称
			String wordname = processFileName(request,filename);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ wordname);
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titleNames;
	}

	/**
	 * 发牌统计Excel导出
	 * 
	 * @param titleNames
	 * @param response
	 * @param listEgo
	 */
	public static String creatorFaPaiExcel(String titleNames,
			HttpServletResponse response, List<TongJi> listEgo, HttpServletRequest request) {
		try {
			String[] coloumItems = { "行政区划", "办件预警", "办件黄牌", "办件红牌","特别程序预警", "特别程序黄牌", "特别程序红牌","咨询预警","咨询黄牌","咨询红牌" };
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
			sheet.setColumnWidth((short) 0, (short) 20 * 256);//
			sheet.setColumnWidth((short) 1, (short) 10 * 256);//
			sheet.setColumnWidth((short) 2, (short) 10 * 256);//
			sheet.setColumnWidth((short) 3, (short) 10 * 256);//
			sheet.setColumnWidth((short) 4, (short) 10 * 256);//
			sheet.setColumnWidth((short) 5, (short) 10 * 256);//
			sheet.setColumnWidth((short) 6, (short) 10 * 256);//
			sheet.setColumnWidth((short) 7, (short) 10 * 256);//
			sheet.setColumnWidth((short) 8, (short) 10 * 256);//
			sheet.setColumnWidth((short) 9, (short) 10 * 256);
			// 1
			Font headfonts = workbook.createFont();
			headfonts.setFontHeightInPoints((short) 20);// 字体大小
			// 2
			Font headfont_ = workbook.createFont();
			headfont_.setFontHeightInPoints((short) 15);// 字体大小
			// 3
			Font headfont = workbook.createFont();
			headfont.setFontName("宋体");
			headfont.setFontHeightInPoints((short) 10);// 字体大小
			headfont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗

			// 1
			CellStyle styles = workbook.createCellStyle();
			styles.setFont(headfonts);
			styles.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			styles.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			styles.setBorderTop(CellStyle.BORDER_THIN);// 上边框
			styles.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
			styles.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			styles.setBorderRight(CellStyle.BORDER_THIN);// 右边框

			// 2
			CellStyle style_ = workbook.createCellStyle();
			style_.setFont(headfont_);
			style_.setAlignment(CellStyle.ALIGN_RIGHT);// 左右居中
			style_.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			/* styles.setBorderBottom(CellStyle.BORDER_THIN); */// 设置单元格的边框为粗体
			style_.setBorderTop(CellStyle.BORDER_THIN);// 上边框
			style_.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
			style_.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			style_.setBorderRight(CellStyle.BORDER_THIN);// 右边框

			// 3
			CellStyle headStyle = workbook.createCellStyle(); // 表头第一列的样式
			headStyle.setFont(headfont);
			headStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			headStyle.setWrapText(true);
			headStyle.setBorderLeft(CellStyle.BORDER_THIN);
			headStyle.setBorderRight(CellStyle.BORDER_THIN);
			headStyle.setBorderTop(CellStyle.BORDER_THIN);
			headStyle.setBorderBottom(CellStyle.BORDER_THIN);

			// 4
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderBottom(CellStyle.BORDER_THIN); // 设置单元格的边框为粗体

			// 1
			Row row;
			Cell cell;
			row = sheet.createRow(0);// 第一行，标题
			cell = row.createCell(0);
			CellRangeAddress cra0 = new CellRangeAddress(0, 0, 0, 9);
			sheet.addMergedRegion(cra0);
			cell.setCellValue("行政效能发牌统计");
			cell.setCellStyle(styles);
			RegionUtil.setBorderTop(1, cra0, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra0, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra0, sheet, workbook);
			RegionUtil.setBorderRight(1, cra0, sheet, workbook);
			// 2
			row = sheet.createRow(1);// 第一行，标题
			cell = row.createCell(0);
			CellRangeAddress cra1 = new CellRangeAddress(1, 1, 0, 9);
			sheet.addMergedRegion(cra1);
			titleNames = titleNames.substring(9);
			cell.setCellValue(titleNames);
			cell.setCellStyle(style_);
			RegionUtil.setBorderTop(1, cra1, sheet, workbook);
			RegionUtil.setBorderBottom(1, cra1, sheet, workbook);
			RegionUtil.setBorderLeft(1, cra1, sheet, workbook);
			RegionUtil.setBorderRight(1, cra1, sheet, workbook);

			// 3
			row = sheet.createRow(2);
			for (int i = 0; i < coloumItems.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(coloumItems[i]);
				cell.setCellStyle(headStyle);
			}
			
			int len = listEgo.size();
			// 循环创建数据行,填充数据.
			for (int i = 0; i < len; i++) {
				// 4
				int cellIndex = 0;
				row = sheet.createRow(i + 3);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getAreaOrgName());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getInstanceSuperviseGreenNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getInstanceSuperviseYelloNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getInstanceSuperviseRedNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getSpecialSuperviseGreenNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getSpecialSuperviseYellowNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getSpecialSuperviseRedNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultSuperviseGreenNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultSuperviseYelloNum());
				cell.setCellStyle(style);
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(listEgo.get(i).getConsultSuperviseRedNum());
				cell.setCellStyle(style);
				
			}
			String filename = "行政效能发牌统计.xls";// 设置下载时客户端Excel的名称
			String wordname = processFileName(request,filename);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ wordname);
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titleNames;
	}

	/**
	 * 办件统计word与Excel导出
	 */
	@SuppressWarnings("deprecation")
	public String exportBusiness(String type, String beginDate, String endDate,
			String org_id, String year, String month,
			HttpServletResponse response, HttpServletRequest request) {
		String titleName = "";
		JcOrgView jcOrgView = null;
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null && !"null".equals(beginDate)) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null && !"null".equals(endDate)) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		if (StringUtils.isNotBlank(org_id)) {
			jcOrgView = DaoFactory.create(JcOrgView.class).selectByID(org_id);
		} else {
			jcOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> listEgo = getDataListByBanJianTongJinew(params, jcOrgView);
		/* 当集合数据大于1是，算计合计数据并放入集合中 */
		if (listEgo != null && listEgo.size() > 1) {
			TongJi tongJiSum = new TongJi();
			tongJiSum.setAcceptNum(new BigDecimal(0));
			tongJiSum.setFinishNum(new BigDecimal(0));
			tongJiSum.setBeforeFinishNum(new BigDecimal(0));
			tongJiSum.setInstanceSuperviseGreenNum(0d);
			tongJiSum.setInstanceSuperviseYelloNum(0d);
			tongJiSum.setInstanceSuperviseRedNum(0d);
			tongJiSum.setComplainSuperviseYelloNum(0d);
			tongJiSum.setComplainSuperviseRedNum(0d);
			tongJiSum.setConsultSuperviseGreenNum(0d);
			tongJiSum.setConsultSuperviseYelloNum(0d);
			tongJiSum.setConsultSuperviseRedNum(0d);
			tongJiSum.setSpecialSuperviseGreenNum(0d);
			tongJiSum.setSpecialSuperviseYellowNum(0d);
			tongJiSum.setSpecialSuperviseRedNum(0d);
			tongJiSum.setComNum(0d);
			tongJiSum.setComplainReplyNum(0d);
			tongJiSum.setConsultNum(0d);
			tongJiSum.setConsultReplayNum(0d);
			tongJiSum.setAreaOrgName("合计");
			tongJiSum.setLegalTimeSum(new BigDecimal(0));
			tongJiSum.setPromiseTimeSum(new BigDecimal(0));
			tongJiSum.setTimeUseSumLegal(new BigDecimal(0));
			tongJiSum.setTimeUseSumPromise(new BigDecimal(0));
			for (TongJi tongJi : listEgo) {
				if (tongJi.getAcceptNum() != null)
					tongJiSum.setAcceptNum(tongJiSum.getAcceptNum().add(
							tongJi.getAcceptNum()));
				if (tongJi.getFinishNum() != null)
					tongJiSum.setFinishNum(tongJiSum.getFinishNum().add(
							tongJi.getFinishNum()));
				if (tongJi.getTiSuLvFloat() != null)
					tongJiSum.setTiSuLvFloat(tongJiSum.getTiSuLvFloat()
							+ tongJi.getTiSuLvFloat());
				if (tongJi.getBeforeFinishNum() != null)
					tongJiSum.setBeforeFinishNum(tongJiSum.getBeforeFinishNum()
							.add(tongJi.getBeforeFinishNum()));
				// 办件红牌数(张)
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getInstanceSuperviseGreenNum() != null)
					tongJiSum.setInstanceSuperviseGreenNum(tongJiSum
							.getInstanceSuperviseGreenNum()
							+ tongJi.getInstanceSuperviseGreenNum());
				if (tongJi.getComplainSuperviseYelloNum() != null)
					tongJiSum.setComplainSuperviseYelloNum(tongJiSum
							.getComplainSuperviseYelloNum()
							+ tongJi.getComplainSuperviseYelloNum());
				if (tongJi.getComplainSuperviseRedNum() != null)
					tongJiSum.setComplainSuperviseRedNum(tongJiSum
							.getComplainSuperviseRedNum()
							+ tongJi.getComplainSuperviseRedNum());
				if (tongJi.getConsultSuperviseGreenNum() != null)
					tongJiSum.setConsultSuperviseGreenNum(tongJiSum
							.getConsultSuperviseGreenNum()
							+ tongJi.getConsultSuperviseGreenNum());
				if (tongJi.getConsultSuperviseYelloNum() != null)
					tongJiSum.setConsultSuperviseYelloNum(tongJiSum
							.getConsultSuperviseYelloNum()
							+ tongJi.getConsultSuperviseYelloNum());
				if (tongJi.getConsultSuperviseRedNum() != null)
					tongJiSum.setConsultSuperviseRedNum(tongJiSum
							.getConsultSuperviseRedNum()
							+ tongJi.getConsultSuperviseRedNum());
				if (tongJi.getSpecialSuperviseGreenNum() != null)
					tongJiSum.setSpecialSuperviseGreenNum(tongJiSum
							.getSpecialSuperviseGreenNum()
							+ tongJi.getSpecialSuperviseGreenNum());
				if (tongJi.getSpecialSuperviseYellowNum() != null)
					tongJiSum.setSpecialSuperviseYellowNum(tongJiSum
							.getSpecialSuperviseYellowNum()
							+ tongJi.getSpecialSuperviseYellowNum());
				if (tongJi.getSpecialSuperviseRedNum() != null)
					tongJiSum.setSpecialSuperviseRedNum(tongJiSum
							.getSpecialSuperviseRedNum()
							+ tongJi.getSpecialSuperviseRedNum());
				if (tongJi.getComNum() != null)
					tongJiSum.setComNum(tongJiSum.getComNum()
							+ tongJi.getComNum());
				if (tongJi.getComplainReplyNum() != null)
					tongJiSum.setComplainReplyNum(tongJiSum
							.getComplainReplyNum()
							+ tongJi.getComplainReplyNum());
				if (tongJi.getConsultNum() != null)
					tongJiSum.setConsultNum(tongJiSum.getConsultNum()
							+ tongJi.getConsultNum());
				if (tongJi.getConsultReplayNum() != null)
					tongJiSum.setConsultReplayNum(tongJiSum
							.getConsultReplayNum()
							+ tongJi.getConsultReplayNum());
				if (tongJi.getLegalTimeSum() != null)
					tongJiSum.setLegalTimeSum(tongJiSum.getLegalTimeSum().add(
							tongJi.getLegalTimeSum()));
				if (tongJi.getPromiseTimeSum() != null)
					tongJiSum.setPromiseTimeSum(tongJiSum.getPromiseTimeSum()
							.add(tongJi.getPromiseTimeSum()));
				if (tongJi.getTimeUseSumLegal() != null)
					tongJiSum.setTimeUseSumLegal(tongJiSum.getTimeUseSumLegal()
							.add(tongJi.getTimeUseSumLegal()));
				if (tongJi.getTimeUseSumPromise() != null)
					tongJiSum.setTimeUseSumPromise(tongJiSum
							.getTimeUseSumPromise().add(
									tongJi.getTimeUseSumPromise()));
			}
			tongJiSum.getBanJieLvFloat();
			tongJiSum.getTiSuLvFloat();
			listEgo.add(0, tongJiSum);
		}
		if("11".equals(type) || "12".equals(type)){
			titleName = instanceExport(year,month,beginDateStr,endDateStr,jcOrgView,listEgo,type,response,request);
			return titleName;
		}else if("13".equals(type) || "14".equals(type)){
			titleName = faPaiExport(year,month,beginDateStr,endDateStr,jcOrgView,listEgo,type,response,request);
			return titleName;
		}
		return titleName;
	}
	/**
	 * 发牌相关导出
	 * @param year
	 * @param month
	 * @param beginDateStr
	 * @param endDateStr
	 * @param jcOrgView
	 * @param listEgo
	 * @param type
	 * @param response
	 * @param request
	 * @return
	 */
	private String faPaiExport(String year, String month, String beginDateStr,
			String endDateStr, JcOrgView jcOrgView, List<TongJi> listEgo,
			String type, HttpServletResponse response,
			HttpServletRequest request) {
		String titleNames = "";
		String nameResult = null;
		if (year != null && !"".equals(year) && month != null
				&& !"".equals(month)) {
			titleNames = "行政效能发牌统计" + "-" + year + "年" + month + "月";
		} else if (beginDateStr != null && !"".equals(beginDateStr)
				&& endDateStr != null && !"".equals(endDateStr)) {
			titleNames = "行政效能发牌统计" + "-" + beginDateStr + "——" + endDateStr;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			titleNames = "行政效能发牌统计" + "-" + date;
		}

		List<List<TongJi>> jclist = new ArrayList<List<TongJi>>();
		jclist.add(listEgo);
		if ("13".equals(type)) {
			
		} else if ("14".equals(type)) {
			creatorFaPaiExcel(titleNames, response, listEgo, request);
		}
		return nameResult;
	}
	
	/**
	 * 办件相关导出
	 * @param year
	 * @param month
	 * @param beginDateStr
	 * @param endDateStr
	 * @param jcOrgView
	 * @param listEgo
	 * @param type
	 * @param response
	 * @param request
	 * @return
	 */
	private String instanceExport(String year,String month,String beginDateStr,
			String endDateStr,JcOrgView jcOrgView,List<TongJi> listEgo,String type,
			HttpServletResponse response, HttpServletRequest request){
		String titleNames = "";
		String nameResult = null;
		if (year != null && !"".equals(year) && month != null
				&& !"".equals(month)) {
			titleNames = "行政效能监管统计" + "-" + year + "年" + month + "月";
		} else if (beginDateStr != null && !"".equals(beginDateStr)
				&& endDateStr != null && !"".equals(endDateStr)) {
			titleNames = "行政效能监管统计" + "-" + beginDateStr + "——" + endDateStr;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			titleNames = "行政效能监管统计" + "-" + date;
		}

		// 此处插入"多规合一"数据，只有配置的SYSTEM_AREACODE_VALUE为长沙市时，导出及页面的才会对"多规合一"数据进行处理
		if (jcOrgView.getOrgThisLevel() == 2
				&& SYSTEM_DUOGUI_AREACODE_VALUE.equals(SYSTEM_AREACODE_VALUE)) {
			Map<String, TongJi> duoGuiTongJiMap = new HashMap<String, TongJi>();
			List<TongJi> tempList = new ArrayList<TongJi>();
			// 1,拥有"多规合一"数据机构的id
			Map<String, String> duoGuiOrg = getDuoGuiOrg();
			// 2,存在机构才赋值
			if (duoGuiOrg != null && duoGuiOrg.size() > 0) {
				// 3,"多规"数据封装进map
				List<TongJi> duoGuiTongJi = getByBanJianTongJiOrgAll(
						"isDuoGuiAll", beginDateStr, endDateStr, "1");
				for (int i = 0; i < duoGuiTongJi.size(); i++) {
					TongJi tongJi = duoGuiTongJi.get(i);
					String key = tongJi.getOrgId();
					if (duoGuiOrg.get(key) != null) {
						tongJi.setAreaOrgName("其中：多规合一");
						tongJi.setOrgId("DuoGui" + tongJi.getOrgId());
						TongJi value = tongJi;
						duoGuiTongJiMap.put(key, value);
					}
				}
				// 4,遍历listEgo按序列插入数据
				for (int i = 0; i < listEgo.size(); i++) {
					TongJi tongJiOrignal = listEgo.get(i);
					tempList.add(tongJiOrignal);
					TongJi duoGuiTongJiTemp = duoGuiTongJiMap.get(tongJiOrignal
							.getOrgId());
					if (duoGuiTongJiTemp != null) {
						tempList.add(duoGuiTongJiTemp);
					}
				}
				listEgo = tempList;
			}
		}

		List<List<TongJi>> jclist = new ArrayList<List<TongJi>>();
		jclist.add(listEgo);
		if ("11".equals(type)) {
			// 新办件统计word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 创建word导出对象
			JCSumSuperviseInfoAllWordExport jCSumSuperviseInfoAllWordExport = new JCSumSuperviseInfoAllWordExport();
			nameResult = jCSumSuperviseInfoAllWordExport.createWordbjtynew(
					configuration, titleNames, jclist);
		} else if ("12".equals(type)) {
			// 新办件统计excel导出
			TongJiController.creatorExcelValue(titleNames, response, listEgo, request);
		}
		return nameResult;
	}
	/**
	 * 审批业务监管统计
	 */
	@GET
	@Path("getBanJianFaPaiTongJiList")
	public Page<TongJi> getBanJianFaPaiTongJiList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Page<TongJi> pagingResult = findBanJianFaPaiData(page, rows, sidx,
				sord, cond, true);
		AuditLogUtil
				.AuditLogToDB(ConstantUtil.MODULE_DZJC_TJFX,
						"findBanJianFaPaiData", null, ConstantUtil.OP_OTHER,
						"审批业务监管统计");
		return pagingResult;
	}

	private Page<TongJi> findBanJianFaPaiData(int page, int rows, String sidx,
			String sord, String cond, boolean isHeJi) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, isHeJi ? rows - 1 : rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 填充查询添加 机构id、开始时间、结束时间 */
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}

		/* 将添加带入到sql中查询 */
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/* 不同机构级别根据不同sql查询 */
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 湖南省、长沙市
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJi",
									params, rowbound);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiBenJi",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 芙蓉区
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiCounty",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiOrg",
									params, rowbound);
				} else {
					list = new ArrayList<TongJi>();
				}
				if (page == 1) {
					TongJi sum = sumBusinessStatistics(userOrgView, params);
					list.add(0, sum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		return pagingResult;
	}

	private TongJi sumBusinessStatistics(JcOrgView userOrgView,
			Map<String, String> params) {
		List<TongJi> total = null;
		TongJi tongJiSum = createTongJiEntity();
		try {
			if (userOrgView.getOrgLevel() < 3
					&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1)) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJi",
								params);
			} else if (userOrgView.getOrgThisLevel() == 2
					|| userOrgView.getOrgThisLevel() == 1) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 3
					|| userOrgView.getOrgLevel() == 4) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiCounty",
								params);
			} else if (userOrgView.getOrgLevel() == 5) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiOrg",
								params);
			} else {
				total = new ArrayList<TongJi>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (total != null && total.size() > 0) {

			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : total) {
				if (tongJi.getInstanceSuperviseGreenNum() != null)
					tongJiSum.setInstanceSuperviseGreenNum(tongJiSum
							.getInstanceSuperviseGreenNum()
							+ tongJi.getInstanceSuperviseGreenNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceCancelYellowNum() != null)
					tongJiSum.setInstanceCancelYellowNum(tongJiSum
							.getInstanceCancelYellowNum()
							+ tongJi.getInstanceCancelYellowNum());
				if (tongJi.getInstanceCancelRedNum() != null)
					tongJiSum.setInstanceCancelRedNum(tongJiSum
							.getInstanceCancelRedNum()
							+ tongJi.getInstanceCancelRedNum());
			}
		}
		return tongJiSum;
	}

	/**
	 * 审批业务监管统计excel导出与word导出
	 */
	@SuppressWarnings("deprecation")
	public String exportBanJianFaPaiStatisticsInfo(String type, String orgId,
			String beginDate, String endDate) {
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		String titleName = "审批业务监管统计（" + beginDateStr + "---" + endDateStr
				+ "）";
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/* 不同机构级别根据不同sql查询 */
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 湖南省、长沙市
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJi",
									params);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiBenJi",
									params);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 芙蓉区
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiCounty",
									params);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianFaPaiTongJiOrg",
									params);
				} else {
					list = new ArrayList<TongJi>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		// 计算合计
		if (list != null && list.size() > 1) {
			TongJi tongJiSum = createTongJiEntity();
			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : list) {
				if (tongJi.getInstanceSuperviseGreenNum() != null)
					tongJiSum.setInstanceSuperviseGreenNum(tongJiSum
							.getInstanceSuperviseGreenNum()
							+ tongJi.getInstanceSuperviseGreenNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceCancelYellowNum() != null)
					tongJiSum.setInstanceCancelYellowNum(tongJiSum
							.getInstanceCancelYellowNum()
							+ tongJi.getInstanceCancelYellowNum());
				if (tongJi.getInstanceCancelRedNum() != null)
					tongJiSum.setInstanceCancelRedNum(tongJiSum
							.getInstanceCancelRedNum()
							+ tongJi.getInstanceCancelRedNum());
			}
			list.add(0, tongJiSum);
		}

		// 此处插入"多规合一"数据，只有配置的SYSTEM_AREACODE_VALUE为长沙市时，导出及页面的才会对"多规合一"数据进行处理
		if (userOrgView.getOrgThisLevel() == 2
				&& SYSTEM_DUOGUI_AREACODE_VALUE.equals(SYSTEM_AREACODE_VALUE)) {
			Map<String, TongJi> duoGuiTongJiMap = new HashMap<String, TongJi>();
			List<TongJi> tempList = new ArrayList<TongJi>();
			// 1,拥有"多规合一"数据机构的id
			Map<String, String> duoGuiOrg = getDuoGuiOrg();
			// 2,存在机构才赋值
			if (duoGuiOrg != null && duoGuiOrg.size() > 0) {
				// 3,"多规"数据封装进map
				List<TongJi> duoGuiTongJi = getByBanJianTongJiOrgAll(
						"isDuoGuiAll", beginDateStr, endDateStr, "1");
				for (int i = 0; i < duoGuiTongJi.size(); i++) {
					TongJi tongJi = duoGuiTongJi.get(i);
					String key = tongJi.getOrgId();
					if (duoGuiOrg.get(key) != null) {
						tongJi.setAreaOrgName("其中：多规合一");
						tongJi.setOrgId("DuoGui" + tongJi.getOrgId());
						TongJi value = tongJi;
						duoGuiTongJiMap.put(key, value);
					}
				}
				// 4,遍历listEgo按序列插入数据
				for (int i = 0; i < list.size(); i++) {
					TongJi tongJiOrignal = list.get(i);
					tempList.add(tongJiOrignal);
					TongJi duoGuiTongJiTemp = duoGuiTongJiMap.get(tongJiOrignal
							.getOrgId());
					if (duoGuiTongJiTemp != null) {
						tempList.add(duoGuiTongJiTemp);
					}
				}
				list = tempList;
			}
		}

		List<List<TongJi>> dataList = new ArrayList<List<TongJi>>();
		dataList.add(list);
		if ("1".equals(type)) { // Excel导出
			BanJianFaPaiStatisticsInfoExcelExport excelExport = new BanJianFaPaiStatisticsInfoExcelExport();
			excelExport.excelExport(titleName, dataList);
		} else if ("2".equals(type)) { // word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			BanJianFaPaiStatisticsInfoWordExport wordExport = new BanJianFaPaiStatisticsInfoWordExport();
			wordExport.createWord(configuration, titleName, dataList);
		}
		// 导出对象
		return titleName;
	}

	@GET
	@Path("getQuanShengFaPaiTongJiTitalName")
	public Map<String, Object> getQuanShengFaPaiTongJiTitalName() {
		Map<String, Object> map = new HashMap<>();
		map.put("titleName", SYSTEM_SCOPE_VALUE);
		return map;
	}

	/**
	 * 全省发牌统计
	 * 
	 */
	@GET
	@Path("getQuanShengFaPaiTongJiList")
	public Page<TongJi> getQuanShengFaPaiTongJiList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Page<TongJi> pagingResult = findQuanShengFaPaiData(page, rows, sidx,
				sord, cond, true);
		AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_TJFX,
				"getQuanShengFaPaiTongJiList", null, ConstantUtil.OP_OTHER,
				"全省发牌统计");
		return pagingResult;
	}

	private Page<TongJi> findQuanShengFaPaiData(int page, int rows,
			String sidx, String sord, String cond, boolean isHeJi) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, isHeJi ? rows - 1 : rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 填充查询添加 机构id、开始时间、结束时间 */
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		/* 将添加带入到sql中查询 */
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/* 不同机构级别根据不同sql查询 */
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 湖南省、长沙市
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJi",
									params, rowbound);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiBenJi",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 芙蓉区
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiCounty",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiOrg",
									params, rowbound);
				} else {
					list = new ArrayList<TongJi>();
				}
				if (page == 1) {
					TongJi sum = sumProvinceDealStatistics(userOrgView, params);
					list.add(0, sum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		return pagingResult;
	}

	private TongJi sumProvinceDealStatistics(JcOrgView userOrgView,
			Map<String, String> params) {
		List<TongJi> total = null;
		TongJi tongJiSum = new TongJi();
		tongJiSum.setSuperviseGreenNum(0d);
		tongJiSum.setSuperviseYelloNum(0d);
		tongJiSum.setSuperviseRedNum(0d);
		tongJiSum.setCancelYellowNum(0d);
		tongJiSum.setCancelRedNum(0d);
		tongJiSum.setAreaOrgName("合计");
		try {
			if (userOrgView.getOrgLevel() < 3
					&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1)) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJi",
								params);
			} else if (userOrgView.getOrgThisLevel() == 2
					|| userOrgView.getOrgThisLevel() == 1) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 3
					|| userOrgView.getOrgLevel() == 4) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiCounty",
								params);
			} else if (userOrgView.getOrgLevel() == 5) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiOrg",
								params);
			} else {
				total = new ArrayList<TongJi>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (total != null && total.size() > 1) {
			for (TongJi tongJi : total) {
				if (tongJi.getSuperviseGreenNum() != null)
					tongJiSum.setSuperviseGreenNum(tongJiSum
							.getSuperviseGreenNum()
							+ tongJi.getSuperviseGreenNum());
				if (tongJi.getSuperviseYelloNum() != null)
					tongJiSum.setSuperviseYelloNum(tongJiSum
							.getSuperviseYelloNum()
							+ tongJi.getSuperviseYelloNum());
				if (tongJi.getSuperviseRedNum() != null)
					tongJiSum.setSuperviseRedNum(tongJiSum.getSuperviseRedNum()
							+ tongJi.getSuperviseRedNum());
				if (tongJi.getCancelYellowNum() != null)
					tongJiSum.setCancelYellowNum(tongJiSum.getCancelYellowNum()
							+ tongJi.getCancelYellowNum());
				if (tongJi.getCancelRedNum() != null)
					tongJiSum.setCancelRedNum(tongJiSum.getCancelRedNum()
							+ tongJi.getCancelRedNum());
			}
		}
		return tongJiSum;
	}

	/**
	 * 发牌统计excel导出与word导出
	 */
	@SuppressWarnings("deprecation")
	public String exportQuanShengFaPaiStatisticsInfo(String type, String orgId,
			String beginDate, String endDate) {
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		String titleName = SYSTEM_SCOPE_VALUE + "发牌统计（" + beginDateStr + "---"
				+ endDateStr + "）";
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/* 不同机构级别根据不同sql查询 */
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 湖南省、长沙市
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJi",
									params);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiBenJi",
									params);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 芙蓉区
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiCounty",
									params);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByQuanShengFaPaiTongJiOrg",
									params);
				} else {
					list = new ArrayList<TongJi>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		// 计算合计
		if (list != null && list.size() > 1) {
			TongJi tongJiSum = new TongJi();
			tongJiSum.setSuperviseGreenNum(0d);
			tongJiSum.setSuperviseYelloNum(0d);
			tongJiSum.setSuperviseRedNum(0d);
			tongJiSum.setCancelYellowNum(0d);
			tongJiSum.setCancelRedNum(0d);
			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : list) {
				if (tongJi.getSuperviseGreenNum() != null)
					tongJiSum.setSuperviseGreenNum(tongJiSum
							.getSuperviseGreenNum()
							+ tongJi.getSuperviseGreenNum());
				if (tongJi.getSuperviseYelloNum() != null)
					tongJiSum.setSuperviseYelloNum(tongJiSum
							.getSuperviseYelloNum()
							+ tongJi.getSuperviseYelloNum());
				if (tongJi.getSuperviseRedNum() != null)
					tongJiSum.setSuperviseRedNum(tongJiSum.getSuperviseRedNum()
							+ tongJi.getSuperviseRedNum());
				if (tongJi.getCancelYellowNum() != null)
					tongJiSum.setCancelYellowNum(tongJiSum.getCancelYellowNum()
							+ tongJi.getCancelYellowNum());
				if (tongJi.getCancelRedNum() != null)
					tongJiSum.setCancelRedNum(tongJiSum.getCancelRedNum()
							+ tongJi.getCancelRedNum());
			}
			list.add(0, tongJiSum);
		}
		List<List<TongJi>> dataList = new ArrayList<List<TongJi>>();
		dataList.add(list);
		if ("1".equals(type)) { // Excel导出
			QuanShengFaPaiStatisticsInfoExcelExport excelExport = new QuanShengFaPaiStatisticsInfoExcelExport();
			excelExport.excelExport(titleName, dataList);
		} else if ("2".equals(type)) { // word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			QuanShengFaPaiStatisticsInfoWordExport wordExport = new QuanShengFaPaiStatisticsInfoWordExport();
			wordExport.createWord(configuration, titleName, dataList);
		}
		// 导出对象
		return titleName;
	}

	/**
	 * 综合业务监管统计
	 * 
	 */
	@GET
	@Path("getBusinessDealStatisticalInfoList")
	public Page<TongJi> getBusinessDealStatisticalInfoList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows - 1);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		// 创建高级查询对象
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		// 填充查询添加 机构id、开始时间、结束时间
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		// 将添加带入到sql中查询
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 查询全省，或根据城市查询
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryProvinceOrCity",
									params, rowbound);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 查询省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryBenJi",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 根据区县查询
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryCounty",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryByOrg",
									params, rowbound);
				} else {
					list = new ArrayList<TongJi>();
				}
				// 如果当前页是第一页则计算合计
				if (page == 1) {
					TongJi sum = sumBusinessDeal(userOrgView, params);
					list.add(0, sum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_TJFX,
				"getBusinessDealStatisticalInfoList", null,
				ConstantUtil.OP_OTHER, "综合业务监管统计");
		return pagingResult;
	}

	private TongJi sumBusinessDeal(JcOrgView userOrgView,
			Map<String, String> params) {
		List<TongJi> total = null;
		TongJi tongJiSum = createTongJiEntity();
		try {
			if (userOrgView == null) {
				return tongJiSum;
			}
			if (userOrgView.getOrgLevel() < 3
					&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1)) {
				// 查询全省，或根据城市查询
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryProvinceOrCity",
								params);
			} else if (userOrgView.getOrgThisLevel() == 2
					|| userOrgView.getOrgThisLevel() == 1) {
				// 查询省本级，市本级
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 3
					|| userOrgView.getOrgLevel() == 4) {
				// 根据区县查询
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryCounty",
								params);
			} else if (userOrgView.getOrgLevel() == 5) {
				// 职能机构
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryByOrg",
								params);
			} else {
				total = new ArrayList<TongJi>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (total != null && total.size() > 0) {
			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : total) {
				if (tongJi.getAcceptNum() != null)
					tongJiSum.setAcceptNum(tongJiSum.getAcceptNum().add(
							tongJi.getAcceptNum()));
				if (tongJi.getNormalFinishNum() != null)
					tongJiSum.setNormalFinishNum(tongJiSum.getNormalFinishNum()
							.add(tongJi.getNormalFinishNum()));
				if (tongJi.getUnthreadfinishNum() != null)
					tongJiSum.setUnthreadfinishNum(tongJiSum
							.getUnthreadfinishNum().add(
									tongJi.getUnthreadfinishNum()));
				if (tongJi.getThrowFinishNum() != null)
					tongJiSum.setThrowFinishNum(tongJiSum.getThrowFinishNum()
							.add(tongJi.getThrowFinishNum()));
				if (tongJi.getNaturalPersonNum() != null)
					tongJiSum.setNaturalPersonNum(tongJiSum
							.getNaturalPersonNum()
							+ tongJi.getNaturalPersonNum());
				if (tongJi.getLegalPersonNum() != null)
					tongJiSum.setLegalPersonNum(tongJiSum
							.getLegalPersonNum()
							+ tongJi.getLegalPersonNum());
				if (tongJi.getInstanceSuperviseGreenNum() != null)
					tongJiSum.setInstanceSuperviseGreenNum(tongJiSum
							.getInstanceSuperviseGreenNum()
							+ tongJi.getInstanceSuperviseGreenNum());
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getInstanceCancelYellowNum() != null)
					tongJiSum.setInstanceCancelYellowNum(tongJiSum
							.getInstanceCancelYellowNum()
							+ tongJi.getInstanceCancelYellowNum());
				if (tongJi.getInstanceCancelRedNum() != null)
					tongJiSum.setInstanceCancelRedNum(tongJiSum
							.getInstanceCancelRedNum()
							+ tongJi.getInstanceCancelRedNum());
				if (tongJi.getNoAcceptNum() != null)
					tongJiSum.setNoAcceptNum(tongJiSum.getNoAcceptNum()
							+ tongJi.getNoAcceptNum());

				if (tongJi.getComNum() != null)
					tongJiSum.setComNum(tongJiSum.getComNum()
							+ tongJi.getComNum());
				if (tongJi.getComplainReplyNum() != null)
					tongJiSum.setComplainReplyNum(tongJiSum
							.getComplainReplyNum()
							+ tongJi.getComplainReplyNum());
				if (tongJi.getComplainSuperviseGreenNum() != null)
					tongJiSum.setComplainSuperviseGreenNum(tongJiSum
							.getComplainSuperviseGreenNum()
							+ tongJi.getComplainSuperviseGreenNum());
				if (tongJi.getComplainSuperviseYelloNum() != null)
					tongJiSum.setComplainSuperviseYelloNum(tongJiSum
							.getComplainSuperviseYelloNum()
							+ tongJi.getComplainSuperviseYelloNum());
				if (tongJi.getComplainSuperviseRedNum() != null)
					tongJiSum.setComplainSuperviseRedNum(tongJiSum
							.getComplainSuperviseRedNum()
							+ tongJi.getComplainSuperviseRedNum());
				if (tongJi.getComplainCancelYellowNum() != null)
					tongJiSum.setComplainCancelYellowNum(tongJiSum
							.getComplainCancelYellowNum()
							+ tongJi.getComplainCancelYellowNum());
				if (tongJi.getComplainCancelRedNum() != null)
					tongJiSum.setComplainCancelRedNum(tongJiSum
							.getComplainCancelRedNum()
							+ tongJi.getComplainCancelRedNum());

				if (tongJi.getConsultNum() != null)
					tongJiSum.setConsultNum(tongJiSum.getConsultNum()
							+ tongJi.getConsultNum());
				if (tongJi.getConsultReplayNum() != null)
					tongJiSum.setConsultReplayNum(tongJiSum
							.getConsultReplayNum()
							+ tongJi.getConsultReplayNum());
				if (tongJi.getConsultSuperviseGreenNum() != null)
					tongJiSum.setConsultSuperviseGreenNum(tongJiSum
							.getConsultSuperviseGreenNum()
							+ tongJi.getConsultSuperviseGreenNum());
				if (tongJi.getConsultSuperviseYelloNum() != null)
					tongJiSum.setConsultSuperviseYelloNum(tongJiSum
							.getConsultSuperviseYelloNum()
							+ tongJi.getConsultSuperviseYelloNum());
				if (tongJi.getConsultSuperviseRedNum() != null)
					tongJiSum.setConsultSuperviseRedNum(tongJiSum
							.getConsultSuperviseRedNum()
							+ tongJi.getConsultSuperviseRedNum());
				if (tongJi.getConsultCancelYellowNum() != null)
					tongJiSum.setConsultCancelYellowNum(tongJiSum
							.getConsultCancelYellowNum()
							+ tongJi.getConsultCancelYellowNum());
				if (tongJi.getConsultCancelRedNum() != null)
					tongJiSum.setConsultCancelRedNum(tongJiSum
							.getConsultCancelRedNum()
							+ tongJi.getConsultCancelRedNum());
			}
		}
		return tongJiSum;
	}

	/**
	 * 综合业务监管统计excel导出与word导出
	 */
	@SuppressWarnings("deprecation")
	public String exportBusinessDealInfo(String type, String orgId,
			String beginDate, String endDate) {
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		String titleName = "综合业务监管统计（" + beginDateStr + "---" + endDateStr
				+ "）";
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 查询全省，或根据城市查询
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryProvinceOrCity",
									params);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 查询省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryBenJi",
									params);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 根据区县查询
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryCounty",
									params);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.BusinessDealStaInfoMapper.queryByOrg",
									params);
				} else {
					list = new ArrayList<TongJi>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		// 计算合计
		if (list != null && list.size() > 1) {
			TongJi tongJiSum = createTongJiEntity();
			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : list) {
				if (tongJi.getAcceptNum() != null)
					tongJiSum.setAcceptNum(tongJiSum.getAcceptNum().add(
							tongJi.getAcceptNum()));
				if (tongJi.getNormalFinishNum() != null)
					tongJiSum.setNormalFinishNum(tongJiSum.getNormalFinishNum()
							.add(tongJi.getNormalFinishNum()));
				if (tongJi.getUnthreadfinishNum() != null)
					tongJiSum.setUnthreadfinishNum(tongJiSum
							.getUnthreadfinishNum().add(
									tongJi.getUnthreadfinishNum()));
				if (tongJi.getThrowFinishNum() != null)
					tongJiSum.setThrowFinishNum(tongJiSum.getThrowFinishNum()
							.add(tongJi.getThrowFinishNum()));
				if (tongJi.getNaturalPersonNum() != null)
					tongJiSum.setNaturalPersonNum(tongJiSum
							.getNaturalPersonNum()
							+ tongJi.getNaturalPersonNum());
				if (tongJi.getLegalPersonNum() != null)
					tongJiSum.setLegalPersonNum(tongJiSum
							.getLegalPersonNum()
							+ tongJi.getLegalPersonNum());
				if (tongJi.getInstanceSuperviseGreenNum() != null)
					tongJiSum.setInstanceSuperviseGreenNum(tongJiSum
							.getInstanceSuperviseGreenNum()
							+ tongJi.getInstanceSuperviseGreenNum());
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getInstanceCancelYellowNum() != null)
					tongJiSum.setInstanceCancelYellowNum(tongJiSum
							.getInstanceCancelYellowNum()
							+ tongJi.getInstanceCancelYellowNum());
				if (tongJi.getInstanceCancelRedNum() != null)
					tongJiSum.setInstanceCancelRedNum(tongJiSum
							.getInstanceCancelRedNum()
							+ tongJi.getInstanceCancelRedNum());
				if (tongJi.getNoAcceptNum() != null)
					tongJiSum.setNoAcceptNum(tongJiSum.getNoAcceptNum()
							+ tongJi.getNoAcceptNum());

				if (tongJi.getComNum() != null)
					tongJiSum.setComNum(tongJiSum.getComNum()
							+ tongJi.getComNum());
				if (tongJi.getComplainReplyNum() != null)
					tongJiSum.setComplainReplyNum(tongJiSum
							.getComplainReplyNum()
							+ tongJi.getComplainReplyNum());
				if (tongJi.getComplainSuperviseGreenNum() != null)
					tongJiSum.setComplainSuperviseGreenNum(tongJiSum
							.getComplainSuperviseGreenNum()
							+ tongJi.getComplainSuperviseGreenNum());
				if (tongJi.getComplainSuperviseYelloNum() != null)
					tongJiSum.setComplainSuperviseYelloNum(tongJiSum
							.getComplainSuperviseYelloNum()
							+ tongJi.getComplainSuperviseYelloNum());
				if (tongJi.getComplainSuperviseRedNum() != null)
					tongJiSum.setComplainSuperviseRedNum(tongJiSum
							.getComplainSuperviseRedNum()
							+ tongJi.getComplainSuperviseRedNum());
				if (tongJi.getComplainCancelYellowNum() != null)
					tongJiSum.setComplainCancelYellowNum(tongJiSum
							.getComplainCancelYellowNum()
							+ tongJi.getComplainCancelYellowNum());
				if (tongJi.getComplainCancelRedNum() != null)
					tongJiSum.setComplainCancelRedNum(tongJiSum
							.getComplainCancelRedNum()
							+ tongJi.getComplainCancelRedNum());

				if (tongJi.getConsultNum() != null)
					tongJiSum.setConsultNum(tongJiSum.getConsultNum()
							+ tongJi.getConsultNum());
				if (tongJi.getConsultReplayNum() != null)
					tongJiSum.setConsultReplayNum(tongJiSum
							.getConsultReplayNum()
							+ tongJi.getConsultReplayNum());
				if (tongJi.getConsultSuperviseGreenNum() != null)
					tongJiSum.setConsultSuperviseGreenNum(tongJiSum
							.getConsultSuperviseGreenNum()
							+ tongJi.getConsultSuperviseGreenNum());
				if (tongJi.getConsultSuperviseYelloNum() != null)
					tongJiSum.setConsultSuperviseYelloNum(tongJiSum
							.getConsultSuperviseYelloNum()
							+ tongJi.getConsultSuperviseYelloNum());
				if (tongJi.getConsultSuperviseRedNum() != null)
					tongJiSum.setConsultSuperviseRedNum(tongJiSum
							.getConsultSuperviseRedNum()
							+ tongJi.getConsultSuperviseRedNum());
				if (tongJi.getConsultCancelYellowNum() != null)
					tongJiSum.setConsultCancelYellowNum(tongJiSum
							.getConsultCancelYellowNum()
							+ tongJi.getConsultCancelYellowNum());
				if (tongJi.getConsultCancelRedNum() != null)
					tongJiSum.setConsultCancelRedNum(tongJiSum
							.getConsultCancelRedNum()
							+ tongJi.getConsultCancelRedNum());
			}
			list.add(0, tongJiSum);
		}
		List<List<TongJi>> dataList = new ArrayList<List<TongJi>>();
		dataList.add(list);
		if ("1".equals(type)) { // Excel导出
			BusinessDealInfoExcelExport excelExport = new BusinessDealInfoExcelExport();
			excelExport.excelExport(titleName, dataList);
		} else if ("2".equals(type)) { // word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			BusinessDealInfoWordExport wordExport = new BusinessDealInfoWordExport();
			wordExport.createWord(configuration, titleName, dataList);
		}
		// 导出对象
		return titleName;
	}

	/**
	 * 人工发牌统计情况
	 * 
	 */
	@GET
	@Path("getManualDealStatisticalInfoList")
	public Page<TongJi> getManualDealStatisticalInfoList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows - 1);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		// 创建高级查询对象
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		// 填充查询添加 机构id、开始时间、结束时间
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		// 将添加带入到sql中查询
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		if (userOrgView != null) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			if (StringUtils.isNotBlank(beginDateStr)) {
				params.put("beginDate", beginDateStr);
			}
			if (StringUtils.isNotBlank(endDateStr)) {
				params.put("endDate", endDateStr);
			}
			try {
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 查询全省，或根据城市查询
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryProvinceOrCity",
									params, rowbound);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 查询省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryBenJi",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 根据区县查询
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryCounty",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryByOrg",
									params, rowbound);
				} else {
					list = new ArrayList<TongJi>();
				}
				if (page == 1) {
					TongJi sum = sumManualDealStatistics(userOrgView, params);
					list.add(0, sum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		return pagingResult;
	}

	private TongJi sumManualDealStatistics(JcOrgView userOrgView,
			Map<String, String> params) {
		List<TongJi> total = null;
		TongJi tongJiSum = new TongJi();
		tongJiSum.setSuperviseGreenNum(0d);
		tongJiSum.setSuperviseYelloNum(0d);
		tongJiSum.setSuperviseRedNum(0d);
		tongJiSum.setCancelGreenNum(0d);
		tongJiSum.setCancelYellowNum(0d);
		tongJiSum.setCancelRedNum(0d);
		tongJiSum.setAreaOrgName("合计");
		try {
			if (userOrgView.getOrgLevel() < 3
					&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1)) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryProvinceOrCity",
								params);
			} else if (userOrgView.getOrgThisLevel() == 2
					|| userOrgView.getOrgThisLevel() == 1) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 3
					|| userOrgView.getOrgLevel() == 4) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryCounty",
								params);
			} else if (userOrgView.getOrgLevel() == 5) {
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryByOrg",
								params);
			} else {
				total = new ArrayList<TongJi>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 计算合计
		if (total != null && total.size() > 1) {
			for (TongJi tongJi : total) {
				if (tongJi.getSuperviseGreenNum() != null)
					tongJiSum.setSuperviseGreenNum(tongJiSum
							.getSuperviseGreenNum()
							+ tongJi.getSuperviseGreenNum());
				if (tongJi.getSuperviseYelloNum() != null)
					tongJiSum.setSuperviseYelloNum(tongJiSum
							.getSuperviseYelloNum()
							+ tongJi.getSuperviseYelloNum());
				if (tongJi.getSuperviseRedNum() != null)
					tongJiSum.setSuperviseRedNum(tongJiSum.getSuperviseRedNum()
							+ tongJi.getSuperviseRedNum());
				if (tongJi.getCancelGreenNum() != null)
					tongJiSum.setCancelGreenNum(tongJiSum.getCancelGreenNum()
							+ tongJi.getCancelGreenNum());
				if (tongJi.getCancelYellowNum() != null)
					tongJiSum.setCancelYellowNum(tongJiSum.getCancelYellowNum()
							+ tongJi.getCancelYellowNum());
				if (tongJi.getCancelRedNum() != null)
					tongJiSum.setCancelRedNum(tongJiSum.getCancelRedNum()
							+ tongJi.getCancelRedNum());
			}
			total.add(tongJiSum);
		}
		return tongJiSum;
	}

	/**
	 * 人工发牌统计情况excel导出与word导出
	 */
	@SuppressWarnings("deprecation")
	public String exportManualDealInfo(String type, String orgId,
			String beginDate, String endDate) {
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		String titleName = "人工发牌统计情况（" + beginDateStr + "---" + endDateStr
				+ "）";
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		if (userOrgView != null) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			if (StringUtils.isNotBlank(beginDateStr)) {
				params.put("beginDate", beginDateStr);
			}
			if (StringUtils.isNotBlank(endDateStr)) {
				params.put("endDate", endDateStr);
			}
			try {
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 查询全省，或根据城市查询
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryProvinceOrCity",
									params);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 查询省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryBenJi",
									params);
				} else if (userOrgView.getOrgLevel() == 3
						|| userOrgView.getOrgLevel() == 4) {
					// 根据区县查询
					params.put("countyCode", userOrgView.getCountyCode());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryCounty",
									params);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.ManualDealStaInfoMapper.queryByOrg",
									params);
				} else {
					list = new ArrayList<TongJi>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		// 计算合计
		if (list != null && list.size() > 1) {
			TongJi tongJiSum = new TongJi();
			tongJiSum.setSuperviseGreenNum(0d);
			tongJiSum.setSuperviseYelloNum(0d);
			tongJiSum.setSuperviseRedNum(0d);
			tongJiSum.setCancelGreenNum(0d);
			tongJiSum.setCancelYellowNum(0d);
			tongJiSum.setCancelRedNum(0d);
			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : list) {
				if (tongJi.getSuperviseGreenNum() != null)
					tongJiSum.setSuperviseGreenNum(tongJiSum
							.getSuperviseGreenNum()
							+ tongJi.getSuperviseGreenNum());
				if (tongJi.getSuperviseYelloNum() != null)
					tongJiSum.setSuperviseYelloNum(tongJiSum
							.getSuperviseYelloNum()
							+ tongJi.getSuperviseYelloNum());
				if (tongJi.getSuperviseRedNum() != null)
					tongJiSum.setSuperviseRedNum(tongJiSum.getSuperviseRedNum()
							+ tongJi.getSuperviseRedNum());
				if (tongJi.getCancelGreenNum() != null)
					tongJiSum.setCancelGreenNum(tongJiSum.getCancelGreenNum()
							+ tongJi.getCancelGreenNum());
				if (tongJi.getCancelYellowNum() != null)
					tongJiSum.setCancelYellowNum(tongJiSum.getCancelYellowNum()
							+ tongJi.getCancelYellowNum());
				if (tongJi.getCancelRedNum() != null)
					tongJiSum.setCancelRedNum(tongJiSum.getCancelRedNum()
							+ tongJi.getCancelRedNum());
			}
			list.add(0, tongJiSum);
		}
		List<List<TongJi>> dataList = new ArrayList<List<TongJi>>();
		dataList.add(list);
		ManualDealInfoExport export = new ManualDealInfoExport();
		if ("1".equals(type)) {
			// Excel导出
			export.excelExport(titleName, dataList);
		} else if ("2".equals(type)) {
			// word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			export.wordExport(configuration, titleName, dataList);
		}
		return titleName;
	}

	/**
	 * 行政权力办件分析
	 * 
	 */
	@GET
	@Path("getFenLeiShiXiangBanJianTongJiList")
	public Page<TongJi> getFenLeiShiXiangBanJianTongJiList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Page<TongJi> pagingResult = findFenLeiShiXiangBanJianData(page, rows,
				sidx, sord, cond, true);
		AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_TJFX,
				"getFenLeiShiXiangBanJianTongJiList", null,
				ConstantUtil.OP_OTHER, "行政权力办件分析");
		return pagingResult;
	}

	private Page<TongJi> findFenLeiShiXiangBanJianData(int page, int rows,
			String sidx, String sord, String cond, boolean isHeJi) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, isHeJi ? rows - 1 : rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 填充查询添加 机构id、开始时间、结束时间 */
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		/* 将添加带入到sql中查询 */
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/**
				 * OrgLevel级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构 OrgThisLevel本级 1：省本级 2：
				 * 市本级 9: 其他 湘西州--州本级--特殊：暂走区县
				 */
				// 将所有可能需要的数据查出，在sql中判断
				Integer orgLevel = userOrgView.getOrgLevel();
				Integer orgThisLevel = userOrgView.getOrgThisLevel();
				String id = userOrgView.getOrgId();
				String countyCode = userOrgView.getCountyCode();

				params.put("orgLevel", orgLevel + "");
				params.put("orgThisLevel", orgThisLevel + "");
				params.put("id", id);
				params.put("countyCode", countyCode);
				// 查询数据
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.getSXBJStatisticsInfo",
								params, rowbound);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (list != null) {
			if (page == 1) {
				List<TongJi> totalList = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.getSXBJStatisticsInfo",
								params);
				// 计算合计
				TongJi total = countTotal(totalList);
				list.add(0, total);
			}
			// 计算小计
			countSubTotal(list);
		}
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		return pagingResult;
	}

	/**
	 * 分类事项合计
	 */
	private TongJi countTotal(List<TongJi> list) {
		// 事项
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
		// 收件
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
		// 办结
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
		for (TongJi item : list) {
			xzxksxsCount += Integer.parseInt(item.getXzxksxs());
			xzcfsxsCount += Integer.parseInt(item.getXzcfsxs());
			xzqzsxsCount += Integer.parseInt(item.getXzqzsxs());
			xzzssxsCount += Integer.parseInt(item.getXzzssxs());
			xzgfsxsCount += Integer.parseInt(item.getXzgfsxs());
			xzjcsxsCount += Integer.parseInt(item.getXzjcsxs());
			xzqrsxsCount += Integer.parseInt(item.getXzqrsxs());
			xzjlsxsCount += Integer.parseInt(item.getXzjlsxs());
			xzcjsxsCount += Integer.parseInt(item.getXzcjsxs());
			qtxzqlsxsCount += Integer.parseInt(item.getQtxzqlsxs());
			ggfwsxsCount += Integer.parseInt(item.getGgfwsxs());

			xzxksjsCount += Integer.parseInt(item.getXzxksjs());
			xzcfsjsCount += Integer.parseInt(item.getXzcfsjs());
			xzqzsjsCount += Integer.parseInt(item.getXzqzsjs());
			xzzssjsCount += Integer.parseInt(item.getXzzssjs());
			xzgfsjsCount += Integer.parseInt(item.getXzgfsjs());
			xzjcsjsCount += Integer.parseInt(item.getXzjcsjs());
			xzqrsjsCount += Integer.parseInt(item.getXzqrsjs());
			xzjlsjsCount += Integer.parseInt(item.getXzjlsjs());
			xzcjsjsCount += Integer.parseInt(item.getXzcjsjs());
			qtxzqlsjsCount += Integer.parseInt(item.getQtxzqlsjs());
			ggfwsjsCount += Integer.parseInt(item.getGgfwsjs());

			xzxkbjsCount += Integer.parseInt(item.getXzxkbjs());
			xzcfbjsCount += Integer.parseInt(item.getXzcfbjs());
			xzqzbjsCount += Integer.parseInt(item.getXzqzbjs());
			xzzsbjsCount += Integer.parseInt(item.getXzzsbjs());
			xzgfbjsCount += Integer.parseInt(item.getXzgfbjs());
			xzjcbjsCount += Integer.parseInt(item.getXzjcbjs());
			xzqrbjsCount += Integer.parseInt(item.getXzqrbjs());
			xzjlbjsCount += Integer.parseInt(item.getXzjlbjs());
			xzcjbjsCount += Integer.parseInt(item.getXzcjbjs());
			qtxzqlbjsCount += Integer.parseInt(item.getQtxzqlbjs());
			ggfwbjsCount += Integer.parseInt(item.getGgfwbjs());
		}
		TongJi total = new TongJi();
		total.setAreaOrgName("合计");

		total.setXzxksxs(xzxksxsCount + "");
		total.setXzcfsxs(xzcfsxsCount + "");
		total.setXzqzsxs(xzqzsxsCount + "");
		total.setXzzssxs(xzzssxsCount + "");
		total.setXzgfsxs(xzgfsxsCount + "");
		total.setXzjcsxs(xzjcsxsCount + "");
		total.setXzqrsxs(xzqrsxsCount + "");
		total.setXzjlsxs(xzjlsxsCount + "");
		total.setXzcjsxs(xzcjsxsCount + "");
		total.setQtxzqlsxs(qtxzqlsxsCount + "");
		total.setGgfwsxs(ggfwsxsCount + "");

		total.setXzxksjs(xzxksjsCount + "");
		total.setXzcfsjs(xzcfsjsCount + "");
		total.setXzqzsjs(xzqzsjsCount + "");
		total.setXzzssjs(xzzssjsCount + "");
		total.setXzgfsjs(xzgfsjsCount + "");
		total.setXzjcsjs(xzjcsjsCount + "");
		total.setXzqrsjs(xzqrsjsCount + "");
		total.setXzjlsjs(xzjlsjsCount + "");
		total.setXzcjsjs(xzcjsjsCount + "");
		total.setQtxzqlsjs(qtxzqlsjsCount + "");
		total.setGgfwsjs(ggfwsjsCount + "");

		total.setXzxkbjs(xzxkbjsCount + "");
		total.setXzcfbjs(xzcfbjsCount + "");
		total.setXzqzbjs(xzqzbjsCount + "");
		total.setXzzsbjs(xzzsbjsCount + "");
		total.setXzgfbjs(xzgfbjsCount + "");
		total.setXzjcbjs(xzjcbjsCount + "");
		total.setXzqrbjs(xzqrbjsCount + "");
		total.setXzjlbjs(xzjlbjsCount + "");
		total.setXzcjbjs(xzcjbjsCount + "");
		total.setQtxzqlbjs(qtxzqlbjsCount + "");
		total.setGgfwbjs(ggfwbjsCount + "");
		return total;
	}

	/**
	 * 分类事项小计
	 */
	private void countSubTotal(List<TongJi> list) {
		for (TongJi item : list) {
			// 事项数小计
			Integer xzxksxsTemp = Integer.parseInt(item.getXzxksxs());
			Integer xzcfsxsTemp = Integer.parseInt(item.getXzcfsxs());
			Integer xzqzsxsTemp = Integer.parseInt(item.getXzqzsxs());
			Integer xzzssxsTemp = Integer.parseInt(item.getXzzssxs());
			Integer xzgfsxsTemp = Integer.parseInt(item.getXzgfsxs());
			Integer xzjcsxsTemp = Integer.parseInt(item.getXzjcsxs());
			Integer xzqrsxsTemp = Integer.parseInt(item.getXzqrsxs());
			Integer xzjlsxsTemp = Integer.parseInt(item.getXzjlsxs());
			Integer xzcjsxsTemp = Integer.parseInt(item.getXzcjsxs());
			Integer qtxzqlsxsTemp = Integer.parseInt(item.getQtxzqlsxs());
			Integer ggfwsxsTemp = Integer.parseInt(item.getGgfwsxs());
			item.setSxscount((xzxksxsTemp + xzcfsxsTemp + xzqzsxsTemp
					+ xzzssxsTemp + xzgfsxsTemp + xzjcsxsTemp + xzqrsxsTemp
					+ xzjlsxsTemp + xzcjsxsTemp + qtxzqlsxsTemp + ggfwsxsTemp)
					+ "");
			// 收件数
			Integer xzxksjsTemp = Integer.parseInt(item.getXzxksjs());
			Integer xzcfsjsTemp = Integer.parseInt(item.getXzcfsjs());
			Integer xzqzsjsTemp = Integer.parseInt(item.getXzqzsjs());
			Integer xzzssjsTemp = Integer.parseInt(item.getXzzssjs());
			Integer xzgfsjsTemp = Integer.parseInt(item.getXzgfsjs());
			Integer xzjcsjsTemp = Integer.parseInt(item.getXzjcsjs());
			Integer xzqrsjsTemp = Integer.parseInt(item.getXzqrsjs());
			Integer xzjlsjsTemp = Integer.parseInt(item.getXzjlsjs());
			Integer xzcjsjsTemp = Integer.parseInt(item.getXzcjsjs());
			Integer qtxzqlsjsTemp = Integer.parseInt(item.getQtxzqlsjs());
			Integer ggfwsjsTemp = Integer.parseInt(item.getGgfwsjs());
			item.setSjscount((xzxksjsTemp + xzcfsjsTemp + xzqzsjsTemp
					+ xzzssjsTemp + xzgfsjsTemp + xzjcsjsTemp + xzqrsjsTemp
					+ xzjlsjsTemp + xzcjsjsTemp + qtxzqlsjsTemp + ggfwsjsTemp)
					+ "");
			// 办结数
			Integer xzxkbjsTemp = Integer.parseInt(item.getXzxkbjs());
			Integer xzcfbjsTemp = Integer.parseInt(item.getXzcfbjs());
			Integer xzqzbjsTemp = Integer.parseInt(item.getXzqzbjs());
			Integer xzzsbjsTemp = Integer.parseInt(item.getXzzsbjs());
			Integer xzgfbjsTemp = Integer.parseInt(item.getXzgfbjs());
			Integer xzjcbjsTemp = Integer.parseInt(item.getXzjcbjs());
			Integer xzqrbjsTemp = Integer.parseInt(item.getXzqrbjs());
			Integer xzjlbjsTemp = Integer.parseInt(item.getXzjlbjs());
			Integer xzcjbjsTemp = Integer.parseInt(item.getXzcjbjs());
			Integer qtxzqlbjsTemp = Integer.parseInt(item.getQtxzqlbjs());
			Integer ggfwbjsTemp = Integer.parseInt(item.getGgfwbjs());
			item.setBjscount((xzxkbjsTemp + xzcfbjsTemp + xzqzbjsTemp
					+ xzzsbjsTemp + xzgfbjsTemp + xzjcbjsTemp + xzqrbjsTemp
					+ xzjlbjsTemp + xzcjbjsTemp + qtxzqlbjsTemp + ggfwbjsTemp)
					+ "");
		}
	}

	/**
	 * 行政权力办件分析excel导出
	 */
	public String exportFenLeiShiXiangBanJianStatisticsInfo(String type,
			String orgId, String beginDate, String endDate) {

		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		String titleName = "行政权力办件分析（" + beginDateStr + "---" + endDateStr
				+ "）";
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/**
				 * OrgLevel级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构 OrgThisLevel本级 1：省本级 2：
				 * 市本级 9: 其他 湘西州--州本级--特殊：暂走区县
				 */
				// 将所有可能需要的数据查出，在sql中判断
				Integer orgLevel = userOrgView.getOrgLevel();
				Integer orgThisLevel = userOrgView.getOrgThisLevel();
				String id = userOrgView.getOrgId();
				String countyCode = userOrgView.getCountyCode();

				params.put("orgLevel", orgLevel + "");
				params.put("orgThisLevel", orgThisLevel + "");
				params.put("id", id);
				params.put("countyCode", countyCode);
				// 查询数据
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.getSXBJStatisticsInfo",
								params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		// 计算合计
		if (list != null) {
			TongJi total = countTotal(list);
			list.add(0, total);
			// 计算小计
			countSubTotal(list);
		}

		List<List<TongJi>> dataList = new ArrayList<List<TongJi>>();
		dataList.add(list);
		if ("1".equals(type)) { // Excel导出
			FenLeiShiXiangBanJianStatisticsInfoExcelExport fLSXBJStatisticsInfoExport = new FenLeiShiXiangBanJianStatisticsInfoExcelExport();
			fLSXBJStatisticsInfoExport.excelExport(titleName, dataList);
		} else if ("2".equals(type)) { // word导出
		}
		// 导出对象
		return titleName;
	}

	/**
	 * 省三级监察word与Excel导出
	 */
	@SuppressWarnings("deprecation")
	public String exportProvinceThreeLevelSupervise(String type,
			String beginDate, String endDate, String orgId, String year,
			String month, HttpServletResponse response,HttpServletRequest request) {
		String titleName = "";
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null && !"null".equals(beginDate)) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null && !"null".equals(endDate)) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		JcOrgView userOrgView = null;
		if ("shiZhouHeJi".equals(orgId)) {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		} else if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}

		List<TongJi> list = new ArrayList<TongJi>();
		list = calculateThreeLevelSuperviseData(userOrgView, params, orgId);

		String titleNames = "";
		if (year != null && !"".equals(year) && month != null
				&& !"".equals(month)) {
			titleNames = "综合情况统计" + "-" + year + "年" + month + "月";
		} else if (beginDateStr != null && !"".equals(beginDateStr)
				&& endDateStr != null && !"".equals(endDateStr)) {
			titleNames = "综合情况统计" + "-" + beginDateStr + "——" + endDateStr;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
			titleNames = "综合情况统计" + "-" + date;
		}
		List<List<TongJi>> jclist = new ArrayList<List<TongJi>>();
		jclist.add(list);

		if ("10".equals(type)) {
			// word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			JCSumSuperviseInfoAllWordExport jCSumSuperviseInfoAllWordExport = new JCSumSuperviseInfoAllWordExport();
			titleName = jCSumSuperviseInfoAllWordExport.wordExportForProvince(
					configuration, titleNames, jclist);
		} else if ("9".equals(type)) {
			// excel导出
			TongJiController.creatorExcelValueForProvince(titleNames, response,
					list,request);
		}
		return titleName;
	}

	/**
	 * 计算三级监察统计数据
	 */
	private List<TongJi> calculateThreeLevelSuperviseData(
			JcOrgView userOrgView, HashMap<String, String> params, String orgId) {
		List<TongJi> list = null;

		if (userOrgView == null) {
			list = new ArrayList<TongJi>();
			return list;
		}
		params.put("orgLevel", userOrgView.getOrgLevel() + "");
		try {
			if ("shiZhouHeJi".equals(orgId)
					|| (userOrgView.getOrgLevel() == 1 && !(userOrgView
							.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1))) {
				// 湖南省
				params.put("pId", userOrgView.getOrgId());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJi",
								params);
				ArrayList<TongJi> tempList = new ArrayList<TongJi>();
				if (list != null && list.size() > 0) {
					if ("shiZhouHeJi".equals(orgId)) {
						for (TongJi tongJi : list) {
							if ("439900000000000000000000".equals(tongJi
									.getAreaOrgCode())) {
								tempList.add(tongJi);
								break;
							}
						}
						list.removeAll(tempList);
					} else {
						TongJi shengBenJiTongJi = new TongJi();
						TongJi shiZhouHeJiTongJi = createTongJiEntity();
						shiZhouHeJiTongJi.setAreaOrgName("市州合计");
						shiZhouHeJiTongJi.setOrgId("shiZhouHeJi");
						shiZhouHeJiTongJi
								.setAreaOrgCode("430000000000000000000000");
						shiZhouHeJiTongJi.getOrgLevelInt();
						for (TongJi tongJi : list) {
							if ("439900000000000000000000".equals(tongJi
									.getAreaOrgCode())) {
								shengBenJiTongJi = tongJi;
							} else {
								if (tongJi.getAcceptNum() != null)
									shiZhouHeJiTongJi
											.setAcceptNum(shiZhouHeJiTongJi
													.getAcceptNum()
													.add(tongJi.getAcceptNum()));
								if (tongJi.getFinishNum() != null)
									shiZhouHeJiTongJi
											.setFinishNum(shiZhouHeJiTongJi
													.getFinishNum()
													.add(tongJi.getFinishNum()));
								if (tongJi.getBeforeFinishNum() != null)
									shiZhouHeJiTongJi
											.setBeforeFinishNum(shiZhouHeJiTongJi
													.getBeforeFinishNum()
													.add(tongJi
															.getBeforeFinishNum()));
								if (tongJi.getInstanceSuperviseRedNum() != null)
									shiZhouHeJiTongJi
											.setInstanceSuperviseRedNum(shiZhouHeJiTongJi
													.getInstanceSuperviseRedNum()
													+ tongJi.getInstanceSuperviseRedNum());
								if (tongJi.getInstanceSuperviseYelloNum() != null)
									shiZhouHeJiTongJi
											.setInstanceSuperviseYelloNum(shiZhouHeJiTongJi
													.getInstanceSuperviseYelloNum()
													+ tongJi.getInstanceSuperviseYelloNum());
								if (tongJi.getComplainSuperviseYelloNum() != null)
									shiZhouHeJiTongJi
											.setComplainSuperviseYelloNum(shiZhouHeJiTongJi
													.getComplainSuperviseYelloNum()
													+ tongJi.getComplainSuperviseYelloNum());
								if (tongJi.getComplainSuperviseRedNum() != null)
									shiZhouHeJiTongJi
											.setComplainSuperviseRedNum(shiZhouHeJiTongJi
													.getComplainSuperviseRedNum()
													+ tongJi.getComplainSuperviseRedNum());
								if (tongJi.getConsultSuperviseYelloNum() != null)
									shiZhouHeJiTongJi
											.setConsultSuperviseYelloNum(shiZhouHeJiTongJi
													.getConsultSuperviseYelloNum()
													+ tongJi.getConsultSuperviseYelloNum());
								if (tongJi.getConsultSuperviseRedNum() != null)
									shiZhouHeJiTongJi
											.setConsultSuperviseRedNum(shiZhouHeJiTongJi
													.getConsultSuperviseRedNum()
													+ tongJi.getConsultSuperviseRedNum());
								if (tongJi.getComNum() != null)
									shiZhouHeJiTongJi
											.setComNum(shiZhouHeJiTongJi
													.getComNum()
													+ tongJi.getComNum());
								if (tongJi.getComplainReplyNum() != null)
									shiZhouHeJiTongJi
											.setComplainReplyNum(shiZhouHeJiTongJi
													.getComplainReplyNum()
													+ tongJi.getComplainReplyNum());
								if (tongJi.getConsultNum() != null)
									shiZhouHeJiTongJi
											.setConsultNum(shiZhouHeJiTongJi
													.getConsultNum()
													+ tongJi.getConsultNum());
								if (tongJi.getConsultReplayNum() != null)
									shiZhouHeJiTongJi
											.setConsultReplayNum(shiZhouHeJiTongJi
													.getConsultReplayNum()
													+ tongJi.getConsultReplayNum());
								if (tongJi.getLegalTimeSum() != null)
									shiZhouHeJiTongJi
											.setLegalTimeSum(shiZhouHeJiTongJi
													.getLegalTimeSum()
													.add(tongJi
															.getLegalTimeSum()));
								if (tongJi.getPromiseTimeSum() != null)
									shiZhouHeJiTongJi
											.setPromiseTimeSum(shiZhouHeJiTongJi
													.getPromiseTimeSum()
													.add(tongJi
															.getPromiseTimeSum()));
								if (tongJi.getTimeUseSumLegal() != null)
									shiZhouHeJiTongJi
											.setTimeUseSumLegal(shiZhouHeJiTongJi
													.getTimeUseSumLegal()
													.add(tongJi
															.getTimeUseSumLegal()));
								if (tongJi.getTimeUseSumPromise() != null)
									shiZhouHeJiTongJi
											.setTimeUseSumPromise(shiZhouHeJiTongJi
													.getTimeUseSumPromise()
													.add(tongJi
															.getTimeUseSumPromise()));
								if (tongJi.getAcceptNumNew() != null)
									shiZhouHeJiTongJi
											.setAcceptNumNew(shiZhouHeJiTongJi
													.getAcceptNumNew()
													.add(tongJi
															.getAcceptNumNew()));
							}
						}
						tempList.add(shengBenJiTongJi);
						tempList.add(shiZhouHeJiTongJi);
						list = tempList;
					}
				}
			} else if (userOrgView.getOrgLevel() == 2
					&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1)) {
				// 长沙市
				params.put("pId", userOrgView.getOrgId());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJi",
								params);
			} else if (userOrgView.getOrgThisLevel() == 2
					|| userOrgView.getOrgThisLevel() == 1) {
				// 省本级，市本级
				params.put("pId", userOrgView.getOrgId());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 3
					|| userOrgView.getOrgLevel() == 4) {
				// 长沙市望城区
				params.put("countyCode", userOrgView.getCountyCode());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiCounty",
								params);
			} else if (userOrgView.getOrgLevel() == 5) {
				// 职能机构
				params.put("orgId", userOrgView.getOrgId());
				list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiOrg",
								params);
			} else {
				list = new ArrayList<TongJi>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null && list.size() > 1) {
			TongJi tongJiSum = createTongJiEntity();
			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : list) {
				if (tongJi.getAcceptNum() != null)
					tongJiSum.setAcceptNum(tongJiSum.getAcceptNum().add(
							tongJi.getAcceptNum()));
				if (tongJi.getFinishNum() != null)
					tongJiSum.setFinishNum(tongJiSum.getFinishNum().add(
							tongJi.getFinishNum()));
				if (tongJi.getBeforeFinishNum() != null)
					tongJiSum.setBeforeFinishNum(tongJiSum.getBeforeFinishNum()
							.add(tongJi.getBeforeFinishNum()));
				if (tongJi.getInstanceSuperviseRedNum() != null)
					tongJiSum.setInstanceSuperviseRedNum(tongJiSum
							.getInstanceSuperviseRedNum()
							+ tongJi.getInstanceSuperviseRedNum());
				if (tongJi.getInstanceSuperviseYelloNum() != null)
					tongJiSum.setInstanceSuperviseYelloNum(tongJiSum
							.getInstanceSuperviseYelloNum()
							+ tongJi.getInstanceSuperviseYelloNum());
				if (tongJi.getComplainSuperviseYelloNum() != null)
					tongJiSum.setComplainSuperviseYelloNum(tongJiSum
							.getComplainSuperviseYelloNum()
							+ tongJi.getComplainSuperviseYelloNum());
				if (tongJi.getComplainSuperviseRedNum() != null)
					tongJiSum.setComplainSuperviseRedNum(tongJiSum
							.getComplainSuperviseRedNum()
							+ tongJi.getComplainSuperviseRedNum());
				if (tongJi.getConsultSuperviseYelloNum() != null)
					tongJiSum.setConsultSuperviseYelloNum(tongJiSum
							.getConsultSuperviseYelloNum()
							+ tongJi.getConsultSuperviseYelloNum());
				if (tongJi.getConsultSuperviseRedNum() != null)
					tongJiSum.setConsultSuperviseRedNum(tongJiSum
							.getConsultSuperviseRedNum()
							+ tongJi.getConsultSuperviseRedNum());
				if (tongJi.getComNum() != null)
					tongJiSum.setComNum(tongJiSum.getComNum()
							+ tongJi.getComNum());
				if (tongJi.getComplainReplyNum() != null)
					tongJiSum.setComplainReplyNum(tongJiSum
							.getComplainReplyNum()
							+ tongJi.getComplainReplyNum());
				if (tongJi.getConsultNum() != null)
					tongJiSum.setConsultNum(tongJiSum.getConsultNum()
							+ tongJi.getConsultNum());
				if (tongJi.getConsultReplayNum() != null)
					tongJiSum.setConsultReplayNum(tongJiSum
							.getConsultReplayNum()
							+ tongJi.getConsultReplayNum());
				if (tongJi.getLegalTimeSum() != null)
					tongJiSum.setLegalTimeSum(tongJiSum.getLegalTimeSum().add(
							tongJi.getLegalTimeSum()));
				if (tongJi.getPromiseTimeSum() != null)
					tongJiSum.setPromiseTimeSum(tongJiSum.getPromiseTimeSum()
							.add(tongJi.getPromiseTimeSum()));
				if (tongJi.getTimeUseSumLegal() != null)
					tongJiSum.setTimeUseSumLegal(tongJiSum.getTimeUseSumLegal()
							.add(tongJi.getTimeUseSumLegal()));
				if (tongJi.getTimeUseSumPromise() != null)
					tongJiSum.setTimeUseSumPromise(tongJiSum
							.getTimeUseSumPromise().add(
									tongJi.getTimeUseSumPromise()));
				if (tongJi.getAcceptNumNew() != null)
					tongJiSum.setAcceptNumNew(tongJiSum.getAcceptNumNew().add(
							tongJi.getAcceptNumNew()));
			}
			tongJiSum.getBanJieLvFloat();
			tongJiSum.getLegalAccelerationRate();
			tongJiSum.getPromiseAccelerationRate();
			list.add(0, tongJiSum);
		}
		return list;
	}

	/**
	 * 创建一个初始化好的TongJi实体
	 */
	private TongJi createTongJiEntity() {
		TongJi entity = new TongJi();
		// 办件统计所需
		entity.setAcceptNum(new BigDecimal(0));
		entity.setFinishNum(new BigDecimal(0));
		entity.setLegalTimeSum(new BigDecimal(0));
		entity.setPromiseTimeSum(new BigDecimal(0));
		entity.setTimeUseSumLegal(new BigDecimal(0));
		entity.setTimeUseSumPromise(new BigDecimal(0));
		entity.setBeforeFinishNum(new BigDecimal(0));
		entity.setNaturalPersonNum(0d);
		entity.setLegalPersonNum(0d);
		entity.setInstanceSuperviseYelloNum(0d);
		entity.setInstanceSuperviseRedNum(0d);
		entity.setComplainSuperviseYelloNum(0d);
		entity.setComplainSuperviseRedNum(0d);
		entity.setConsultSuperviseYelloNum(0d);
		entity.setConsultSuperviseRedNum(0d);
		entity.setComNum(0d);
		entity.setComplainReplyNum(0d);
		entity.setConsultNum(0d);
		entity.setConsultReplayNum(0d);
		entity.setAcceptNumNew(new BigDecimal(0));
		// 业务发牌统计所需
		entity.setNormalFinishNum(new BigDecimal(0));
		entity.setUnthreadfinishNum(new BigDecimal(0));
		entity.setThrowFinishNum(new BigDecimal(0));
		entity.setInstanceSuperviseGreenNum(0d);
		entity.setInstanceCancelYellowNum(0d);
		entity.setInstanceCancelRedNum(0d);
		entity.setNoAcceptNum(0d);
		entity.setComplainSuperviseGreenNum(0d);
		entity.setComplainCancelYellowNum(0d);
		entity.setComplainCancelRedNum(0d);
		entity.setConsultSuperviseGreenNum(0d);
		entity.setConsultCancelYellowNum(0d);
		entity.setConsultCancelRedNum(0d);
		return entity;
	}

	/**
	 * 查询指定orgId"多规合一"数据(长沙市 三级监察页面)
	 * 
	 * @param orgId
	 *            机构ID
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param isInvestmentSupervision
	 *            : 1只查询多规合一数据；0不查询多规合一数据
	 * @return
	 */
	@GET
	@Path("getByBanJianTongJiOrg/{rowid}/{beginDate}/{endDate}/{isInvestmentSupervision}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public TongJi getByBanJianTongJiOrg(
			@PathParam(value = "rowid") String orgId,
			@PathParam(value = "beginDate") String beginDate,
			@PathParam(value = "endDate") String endDate,
			@PathParam(value = "isInvestmentSupervision") String isInvestmentSupervision) {

		Map<String, String> params = new HashMap<String, String>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (StringUtils.isNotBlank(beginDate)) {
			beginDate = format.format(new Date(Long.parseLong(beginDate)));
			params.put("beginDate", beginDate);
		}

		if (StringUtils.isNotBlank(endDate)) {
			endDate = format.format(new Date(Long.parseLong(endDate)));
			params.put("endDate", endDate);
		}

		if (StringUtils.isNotBlank(orgId)) {
			params.put("orgId", orgId);
		}

		if (StringUtils.isNotBlank(isInvestmentSupervision)) {
			params.put("isInvestmentSupervision", isInvestmentSupervision);
		}
		try {
			getDuoGuiOrg();
			List<TongJi> list = DaoFactory
					.create(TongJi.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiOrg",
							params);
			TongJi tongJi = null;
			if (list != null && list.size() > 0) {
				tongJi = list.get(0);
				tongJi.setAreaOrgName("其中：多规合一");
				tongJi.setOrgId("DuoGui" + tongJi.getOrgId());
			}
			return tongJi;
		} catch (Exception e) {
			System.out.println("多规合一数据查询失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有"多规合一"数据(长沙市 三级监察页面)
	 * 
	 * @param orgId
	 *            机构ID
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param isInvestmentSupervision
	 *            : 1只查询多规合一数据；0不查询多规合一数据
	 * @return
	 */
	public List<TongJi> getByBanJianTongJiOrgAll(String orgId,
			String beginDate, String endDate, String isInvestmentSupervision) {

		Map<String, String> params = new HashMap<String, String>();

		if (StringUtils.isNotBlank(beginDate)) {
			params.put("beginDate", beginDate);
		}

		if (StringUtils.isNotBlank(endDate)) {
			params.put("endDate", endDate);
		}

		if (StringUtils.isNotBlank(orgId)) {
			params.put("orgId", orgId);
		}

		if (StringUtils.isNotBlank(isInvestmentSupervision)) {
			params.put("isInvestmentSupervision", isInvestmentSupervision);
		}
		try {
			List<TongJi> list = DaoFactory
					.create(TongJi.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.TongJiMapper.selectByBanJianTongJiOrg",
							params);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			System.out.println("多规合一数据查询失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询存在"多规合一"数据的机构
	 */
	@GET
	@Path("getDuoGuiOrg")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getDuoGuiOrg() {
		// 1,定义变量
		Map<String, String> orgMap = new HashMap<String, String>();
		try {
			// 2,查询v_jc_dghy_spxm中多规合一数据的org_id
			List<TongJi> list = DaoFactory
					.create(TongJi.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.TongJiMapper.getDuoGuiOrg");
			if (list != null && list.size() > 0) {
				// 3,将机构org_id封装返回
				for (int i = 0; i < list.size(); i++) {
					String orgId = list.get(i).getOrgId();
					orgMap.put(orgId, orgId);
				}
			}
			return orgMap;
		} catch (Exception e) {
			System.out.println("多规合一数据所在机构查询失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询配置文件中统计范围名称
	 * 
	 * @return
	 */
	@GET
	@Path("getQuanShengFaPaiTongJiTitalName")
	public Map<String, Object> getHuanJieFaPaiTongJiTitalName() {
		Map<String, Object> map = new HashMap<>();
		map.put("titleName", SYSTEM_SCOPE_VALUE);
		return map;
	}

	/**
	 * 环节发牌统计
	 * 
	 */
	@GET
	@Path("getHuanJieFaPaiTongJiList")
	public Page<TongJi> getHuanJieFaPaiTongJiList(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Page<TongJi> pagingResult = findHuanJieFaPaiData(page, rows, sidx,
				sord, cond, true);
		AuditLogUtil.AuditLogToDB(ConstantUtil.MODULE_DZJC_TJFX,
				"getHuanJieFaPaiTongJiList", null, ConstantUtil.OP_OTHER,
				"环节发牌统计");
		return pagingResult;
	}

	private Page<TongJi> findHuanJieFaPaiData(int page, int rows, String sidx,
			String sord, String cond, boolean isHeJi) {
		// 创建分页对象
		Pageable pageable = new Pageable(page, isHeJi ? rows - 1 : rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 填充查询添加 机构id、开始时间、结束时间 */
		ParamEntity entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, ParamEntity.class) : new ParamEntity();
		String orgId = entity.getOrgId();
		java.sql.Date beginDateSql = entity.getBeginDate();
		java.sql.Date endDateSql = entity.getEndDate();
		String beginDateStr = "";
		String endDateStr = "";
		if (beginDateSql != null) {
			Date beginDate = new Date(beginDateSql.getTime());
			beginDateStr = DateUtil.getFormatDate(beginDate);
		}
		if (endDateSql != null) {
			Date endDate = new Date(endDateSql.getTime());
			endDateStr = DateUtil.getFormatDate(endDate);
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		/* 将添加带入到sql中查询 */
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable,
				conditions, true);
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/* 不同机构级别根据不同sql查询 */
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 湖南省、长沙市
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJi",
									params, rowbound);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiBenJi",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 3) {
					// 雨花区
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiCounty",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 4
						&& userOrgView.getOrgThisLevel() == 3) {
					// 雨花区区本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiQuBenJi",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 4) {
					// 井湾子街道
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiVillage",
									params, rowbound);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiOrg",
									params, rowbound);
				} else {
					list = new ArrayList<TongJi>();
				}
				if (page == 1) {
					TongJi sum = sumHuanJieDealStatistics(userOrgView, params);
					list.add(0, sum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}
		Page<TongJi> pagingResult = new Page<TongJi>(page, rows,
				rowbound.getTotalSize() + 1, list);
		return pagingResult;
	}

	private TongJi sumHuanJieDealStatistics(JcOrgView userOrgView,
			Map<String, String> params) {
		List<TongJi> total = null;
		TongJi tongJiSum = new TongJi();

		tongJiSum.setSuperviseSegmentGreenNum(0d);
		tongJiSum.setSuperviseSegmentYelloNum(0d);
		tongJiSum.setSuperviseSegmentRedNum(0d);
		tongJiSum.setCancelSegmentYellowNum(0d);
		tongJiSum.setCancelSegmentRedNum(0d);
		tongJiSum.setAreaOrgName("合计");
		try {

			if (userOrgView.getOrgLevel() < 3
					&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
							.getOrgThisLevel() == 1)) {
				// 湖南省、长沙市
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJi",
								params);
			} else if (userOrgView.getOrgThisLevel() == 2
					|| userOrgView.getOrgThisLevel() == 1) {
				// 省本级，市本级
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 3) {
				// 雨花区
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiCounty",
								params);
			} else if (userOrgView.getOrgLevel() == 4
					&& userOrgView.getOrgThisLevel() == 3) {
				// 雨花区区本级
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiQuBenJi",
								params);
			} else if (userOrgView.getOrgLevel() == 4) {
				// 井湾子街道
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiVillage",
								params);
			} else if (userOrgView.getOrgLevel() == 5) {
				// 职能机构
				total = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiOrg",
								params);
			} else {
				total = new ArrayList<TongJi>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (total != null && total.size() > 1) {
			for (TongJi tongJi : total) {
				if (tongJi.getSuperviseSegmentGreenNum() != null)
					tongJiSum.setSuperviseSegmentGreenNum(tongJiSum
							.getSuperviseSegmentGreenNum()
							+ tongJi.getSuperviseSegmentGreenNum());
				if (tongJi.getSuperviseSegmentYelloNum() != null)
					tongJiSum.setSuperviseSegmentYelloNum(tongJiSum
							.getSuperviseSegmentYelloNum()
							+ tongJi.getSuperviseSegmentYelloNum());
				if (tongJi.getSuperviseSegmentRedNum() != null)
					tongJiSum.setSuperviseSegmentRedNum(tongJiSum
							.getSuperviseSegmentRedNum()
							+ tongJi.getSuperviseSegmentRedNum());
				if (tongJi.getCancelSegmentYellowNum() != null)
					tongJiSum.setCancelSegmentYellowNum(tongJiSum
							.getCancelSegmentYellowNum()
							+ tongJi.getCancelSegmentYellowNum());
				if (tongJi.getCancelSegmentRedNum() != null)
					tongJiSum.setCancelSegmentRedNum(tongJiSum
							.getCancelSegmentRedNum()
							+ tongJi.getCancelSegmentRedNum());
			}
		}
		return tongJiSum;
	}

	/**
	 * 环节发牌统计excel导出与word导出
	 */
	@SuppressWarnings("deprecation")
	public String exportHuanJieFaPaiStatisticsInfo(String type, String orgId,
			String beginDate, String endDate) {
		String beginDateStr = "";
		String endDateStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (beginDate != null) {
			beginDateStr = format.format(new Date(Long.parseLong(beginDate)));
		}
		if (endDate != null) {
			endDateStr = format.format(new Date(Long.parseLong(endDate)));
		}
		HashMap<String, String> params = chooseSumSuperviseInfoByDateRange(
				beginDateStr, endDateStr);
		String titleName = "环节发牌统计（" + beginDateStr + "---" + endDateStr + "）";
		List<TongJi> list = null;
		JcOrgView userOrgView = null;
		if (StringUtils.isNotBlank(orgId)) {
			userOrgView = DaoFactory.create(JcOrgView.class).selectByID(orgId);
		} else {
			userOrgView = RoleUtil.getInstance().getUserOrgId();
		}
		if (userOrgView != null) {
			params.put("orgLevel", userOrgView.getOrgLevel() + "");
			try {
				/* 不同机构级别根据不同sql查询 */
				if (userOrgView.getOrgLevel() < 3
						&& !(userOrgView.getOrgThisLevel() == 2 || userOrgView
								.getOrgThisLevel() == 1)) {
					// 湖南省、长沙市
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJi",
									params);
				} else if (userOrgView.getOrgThisLevel() == 2
						|| userOrgView.getOrgThisLevel() == 1) {
					// 省本级，市本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiBenJi",
									params);
				} else if (userOrgView.getOrgLevel() == 3) {
					// 雨花区
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiCounty",
									params);
				} else if (userOrgView.getOrgLevel() == 4
						&& userOrgView.getOrgThisLevel() == 3) {
					// 雨花区区本级
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiQuBenJi",
									params);
				} else if (userOrgView.getOrgLevel() == 4) {
					// 井湾子街道
					params.put("pId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiVillage",
									params);
				} else if (userOrgView.getOrgLevel() == 5) {
					// 职能机构
					params.put("orgId", userOrgView.getOrgId());
					list = DaoFactory
							.create(TongJi.class)
							.getSession()
							.selectList(
									"com.chinacreator.dzjc_tongji.TongJiMapper.selectByHuanJieFaPaiTongJiOrg",
									params);
				} else {
					list = new ArrayList<TongJi>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			list = new ArrayList<TongJi>();
		}

		if (list != null && list.size() > 1) {
			TongJi tongJiSum = new TongJi();
			tongJiSum.setSuperviseSegmentGreenNum(0d);
			tongJiSum.setSuperviseSegmentYelloNum(0d);
			tongJiSum.setSuperviseSegmentRedNum(0d);
			tongJiSum.setCancelSegmentYellowNum(0d);
			tongJiSum.setCancelSegmentRedNum(0d);
			tongJiSum.setAreaOrgName("合计");
			for (TongJi tongJi : list) {
				if (tongJi.getSuperviseSegmentGreenNum() != null)
					tongJiSum.setSuperviseSegmentGreenNum(tongJiSum
							.getSuperviseSegmentGreenNum()
							+ tongJi.getSuperviseSegmentGreenNum());
				if (tongJi.getSuperviseSegmentYelloNum() != null)
					tongJiSum.setSuperviseSegmentYelloNum(tongJiSum
							.getSuperviseSegmentYelloNum()
							+ tongJi.getSuperviseSegmentYelloNum());
				if (tongJi.getSuperviseSegmentRedNum() != null)
					tongJiSum.setSuperviseSegmentRedNum(tongJiSum
							.getSuperviseSegmentRedNum()
							+ tongJi.getSuperviseSegmentRedNum());
				if (tongJi.getCancelSegmentYellowNum() != null)
					tongJiSum.setCancelSegmentYellowNum(tongJiSum
							.getCancelSegmentYellowNum()
							+ tongJi.getCancelSegmentYellowNum());
				if (tongJi.getCancelSegmentRedNum() != null)
					tongJiSum.setCancelSegmentRedNum(tongJiSum
							.getCancelSegmentRedNum()
							+ tongJi.getCancelSegmentRedNum());
			}
			list.add(0, tongJiSum);
		}
		List<List<TongJi>> dataList = new ArrayList<List<TongJi>>();
		dataList.add(list);
		if ("1".equals(type)) { // Excel导出
			HuanJieFaPaiStatisticsInfoExcelExport excelExport = new HuanJieFaPaiStatisticsInfoExcelExport();
			excelExport.excelExport(titleName, dataList);
		} else if ("2".equals(type)) { // word导出
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			HuanJieFaPaiStatisticsInfoWordExport wordExport = new HuanJieFaPaiStatisticsInfoWordExport();
			wordExport.createWord(configuration, titleName, dataList);
		}
		// 导出对象
		return titleName;
	}

	/**
	 * 根据传入的时间范围选择应查的统计固化表
	 */
	private HashMap<String, String> chooseSumSuperviseInfoByDateRange(
			String beginDateString, String endDateString) {
		HashMap<String, String> hashMap = new HashMap<>();
		if (beginDateString == "" && endDateString == "") {
			hashMap.put("beginDate", beginDateString);
			hashMap.put("endDate", endDateString);
			hashMap.put("table", "ta_jc_sum_supervise_info_year");
			return hashMap;
		}
		if ((beginDateString == "" && endDateString != "")
				|| (beginDateString != "" && endDateString == "")) {
			hashMap.put("beginDate", beginDateString);
			hashMap.put("endDate", endDateString);
			hashMap.put("table", "ta_jc_sum_supervise_info");
			return hashMap;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginDate = simpleDateFormat.parse(beginDateString);
			Date endDate = simpleDateFormat.parse(endDateString);
			return checkDateRange(beginDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return hashMap;
	}

	private HashMap<String, String> checkDateRange(Date beginDate, Date endDate) {
		HashMap<String, String> hashMap = new HashMap<>();
		if (isYear(beginDate, endDate)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
			String beginDateString = simpleDateFormat.format(beginDate);
			String endDateString = simpleDateFormat.format(endDate);
			hashMap.put("beginDate", beginDateString);
			hashMap.put("endDate", endDateString);
			hashMap.put("table", "ta_jc_sum_supervise_info_year");
			return hashMap;
		}
		if (isMonth(beginDate, endDate)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			String beginDateString = simpleDateFormat.format(beginDate);
			String endDateString = simpleDateFormat.format(endDate);
			hashMap.put("beginDate", beginDateString);
			hashMap.put("endDate", endDateString);
			hashMap.put("table", "ta_jc_sum_supervise_info_month");
			return hashMap;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String beginDateString = simpleDateFormat.format(beginDate);
		String endDateString = simpleDateFormat.format(endDate);
		hashMap.put("beginDate", beginDateString);
		hashMap.put("endDate", endDateString);
		hashMap.put("table", "ta_jc_sum_supervise_info");
		return hashMap;
	}

	@SuppressWarnings("deprecation")
	private boolean isMonth(Date beginDate, Date endDate) {
		if (beginDate.getDate() == 1) {
			int month = endDate.getMonth() + 1;
			if ((month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12)
					&& endDate.getDate() == 31) {
				return true;
			}
			if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& endDate.getDate() == 30) {
				return true;
			}
			if (month == 2) {
				if ((beginDate.getYear() + 1900) % 4 > 0
						&& endDate.getDate() == 28) {
					return true;
				}
				if ((beginDate.getYear() + 1900) % 4 == 0
						&& endDate.getDate() == 29) {
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private boolean isYear(Date beginDate, Date endDate) {
		if (beginDate.getMonth() + 1 == 1 && beginDate.getDate() == 1
				&& endDate.getMonth() + 1 == 12 && endDate.getDate() == 31) {
			return true;
		}
		return false;
	}
	
	/**
	 * 解决设置名称时的乱码
	 * @param request
	 * @param fileNames
	 * @return
	 */
	public static String processFileName(HttpServletRequest request, String filename) {
		String codedfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("Firefox")) {
				//浏览器为火狐
				codedfilename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			}else{
				codedfilename = URLEncoder.encode(filename, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codedfilename;
	}
}
