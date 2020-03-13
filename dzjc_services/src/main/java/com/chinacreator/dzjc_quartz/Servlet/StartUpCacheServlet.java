package com.chinacreator.dzjc_quartz.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.Sp_status;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.FunctionSuperviseIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.FunctionSuperviseImpl;
import com.chinacreator.dzjc_tongji.SanJiJianChaPage;
import com.chinacreator.dzjc_tongji.TongJi;

/**
 * 将三级监察首页数据进行缓存com.chinacreator.dzjc_quartz.Servlet.StartUpCacheServlet
 */
public class StartUpCacheServlet extends HttpServlet implements Job {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StartUpCacheServlet t = new StartUpCacheServlet();
		t.cacheData();
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		cacheData();
	}

	/**
	 * 缓存三级监察累计数据
	 */
	private void cacheData() {
		Date beginDate = new Date();
		FunctionSuperviseIfc functionsuperviseService = new FunctionSuperviseImpl();
		Sp_status stauts = new Sp_status();
		stauts.setFunctionName("缓存三级监察累计数据");
		stauts.setType("7");
		stauts.setStatus("Y");
		Integer count = 0;
		System.out.println("启动三级监察首页缓存！");
		try {
			// 1,清理表中数据
			DaoFactory
					.create(SanJiJianChaPage.class)
					.getSession()
					.delete("com.chinacreator.dzjc_tongji.SanJiJianChaPageMapper.deleteCache");
			// 2,将指定机构数据缓存入表 ta_jc_tongji_shouye 中
			Map<String, String> params = new HashMap<>();
			// 2.1获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String nowDate = df.format(new Date());// new Date()为获取当前系统时间
			// 创建list存放paramsMap，以便批量插入
			List<Map<String, Object>> paramsMapList = new ArrayList<Map<String, Object>>();
			// 2.2查询缓存配置表 ta_jc_tongji_orgcache_config，缓存表中机构数据
			List<Map<String, String>> selectList = DaoFactory
					.create(SanJiJianChaPage.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.SanJiJianChaPageMapper.selectCacheConfig");
			for (int i = 0; i < selectList.size(); i++) {
				Map<String, String> map = selectList.get(i);
				String orgId = map.get("ORG_ID");
				if (orgId.equals("shiZhouHeJi")) {
					params.put("isShiZhouHeJi", "true");
					// 此处将"市州"orgLevel固定，因为无此数据
					params.put("orgLevel", "1");
					params.put("orgId", "1");
				} else {
					String orgLevel = map.get("ORG_LEVEL");
					params.put("orgLevel", orgLevel);
					params.put("orgId", orgId);
				}

				// 获取累计数据
				List<TongJi> list = DaoFactory
						.create(TongJi.class)
						.getSession()
						.selectList(
								"com.chinacreator.dzjc_tongji.TongJiMapper.selectBySanJiJianChaLeiJi",
								params);
				if (list != null && list.size() > 0) {
					TongJi tongJi = list.get(0);
					// 3,把查询数据存入缓存表
					Map<String, Object> paramsMap = new HashMap<>();
					// 封装参数并插入("市州"的orgId为shiZhouHeJi
					// 其判断存在依据是isShiZhouHeJi存在且为true)
					if (orgId.equals("shiZhouHeJi")) {
						paramsMap.put("orgId", "shiZhouHeJi");
					} else {
						paramsMap.put("orgId", tongJi.getOrgId());
					}
					paramsMap.put("nowDate", nowDate);
					paramsMap.put("acceptNumAll", tongJi.getAcceptNum());
					paramsMap.put("finishNumAll", tongJi.getFinishNum());
					paramsMap
							.put("banJieLvFloatAll", tongJi.getBanJieLvFloat());
					paramsMap.put("comNumAll", tongJi.getComNum());
					paramsMap.put("legalAccelerationRateAll",
							tongJi.getLegalAccelerationRate());
					paramsMap.put("promiseAccelerationRateAll",
							tongJi.getPromiseAccelerationRate());
					paramsMap.put("instanceYellowNumAll",
							tongJi.getInstanceSuperviseYelloNum());
					paramsMap.put("instanceRedNumAll",
							tongJi.getInstanceSuperviseRedNum());
					paramsMap.put("consultYellowNumAll",
							tongJi.getConsultSuperviseYelloNum());
					paramsMap.put("consultRedNumAll",
							tongJi.getConsultSuperviseRedNum());

					paramsMapList.add(paramsMap);
				}
				// 每1000条执行一次插入操作
				if (paramsMapList.size() % 1000 == 0) {
					DaoFactory
							.create(SanJiJianChaPage.class)
							.getSession()
							.insert("com.chinacreator.dzjc_tongji.SanJiJianChaPageMapper.batchInsertResults",
									paramsMapList);
					paramsMapList.clear();
				}
				// 记录操作数据条数
				count++;
			}
			if (paramsMapList != null && paramsMapList.size() > 0) {
				DaoFactory
						.create(SanJiJianChaPage.class)
						.getSession()
						.insert("com.chinacreator.dzjc_tongji.SanJiJianChaPageMapper.batchInsertResults",
								paramsMapList);
			}

		} catch (Exception e) {
			stauts.setStatus("N");
			stauts.setException(e.toString());
			e.printStackTrace();

		}
		stauts.setProcessQty(new Double(count));

		Date endDate = new Date();
		stauts.setSuperviseStartTime(new java.sql.Date(beginDate.getTime()));
		stauts.setSuperviseEndTime(new java.sql.Date(endDate.getTime()));
		functionsuperviseService.insertFunctionSupervise(stauts);
		System.out.println("三级监察首页缓存完毕！");
	}
}
