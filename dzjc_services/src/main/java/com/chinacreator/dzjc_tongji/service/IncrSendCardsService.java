package com.chinacreator.dzjc_tongji.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_dealtMatter.TaJcSuperviseInfo;
import com.chinacreator.dzjc_tongji.StatisticalSumCache;
import com.chinacreator.dzjc_tongji.TongJi;
import com.chinacreator.dzjc_tongji.V_TD_SM_ORGANIZATION1;
import com.chinacreator.dzjc_tongji.util.ThreadPoolUtils;

/**
 * 发牌增量更新service
 * @author zyz818 FIXME 1.发过的牌被删掉，再次统计之前需要检测
 *         2.发改委的牌已发，第二天过滤掉该机构，统计过的牌无法过滤，再次统计之前需要检测 3.增量发牌之前的检测代码暂时未写
 *         4.减牌操作需要数据验证 5.具体细节以后有时间重构
 */
public class IncrSendCardsService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IncrSendCardsService.class);
	private List<Map<String, Map<String, Integer>>> greenMapList = new ArrayList<>();
	private List<Map<String, Map<String, Integer>>> yellowMapList = new ArrayList<>();
	private List<Map<String, Map<String, Integer>>> redMapList = new ArrayList<>();
	private List<String> cardIdsList = new ArrayList<>();
	private int superviseCount = 1000;

	public void incrSendCards() {
		LOGGER.info("发牌增量更新start");
		int index = 0;
		int startIndex = 0;
		int endIndex = 0;
		Map<String, Integer> paramMap = null;
		List<TaJcSuperviseInfo> list = null;
		while (true) {
			startIndex = index * superviseCount;
			endIndex = (index + 1) * superviseCount;
			paramMap = new HashMap<String, Integer>();
			paramMap.put("startIndex", startIndex);
			paramMap.put("endIndex", endIndex);
			list = DaoFactory
					.create(TaJcSuperviseInfo.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_dealtMatter.TaJcSuperviseInfoMapper.getSendCardsList",
							paramMap);
			if (list.size() > 0) {
				index++;
				JoiningRunnable jr = new JoiningRunnable(list);
				ThreadPoolUtils.execute(jr);
			} else if (list.size() == 0 && index != 0) {
				index = 0;
			} else {
				break;
			}
		}
		// 顺序：1预警牌>2黄牌>3红牌>4更新到统计表>5把统计过的数据状态改为已统计
		Map<String, String> cardType = new HashMap<>();
		cardType.put("type", "1");
		if (batchData(cardType) == false) {
			return;
		}
		cardType.put("type", "2");
		if (batchData(cardType) == false) {
			return;
		}
		cardType.put("type", "3");
		if (batchData(cardType) == false) {
			return;
		}
		updateTongJi();
		recordTongJiStatus();
		LOGGER.info("发牌增量更新end");
		LOGGER.error("发牌增量更新：ORA-00001: 违反唯一约束条件 (SUPERVISE.PK_SUPERVISE_ID)异常可忽略");
	}

	/**
	 * 写入状态
	 */
	private void recordTongJiStatus() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < cardIdsList.size(); i++) {
			list.add(cardIdsList.get(i));
			if (list.size() == 1000) {
				DaoFactory
						.create(StatisticalSumCache.class)
						.getSession()
						.update("com.chinacreator.dzjc_tongji.StatisticalSumCacheMapper.updateCardsStatus",
								list);
				list.clear();
			}
		}
		if (list.size() > 0) {
			DaoFactory
					.create(StatisticalSumCache.class)
					.getSession()
					.update("com.chinacreator.dzjc_tongji.StatisticalSumCacheMapper.updateCardsStatus",
							list);
		}
		LOGGER.info("状态反写完成");
	}

	/**
	 * 将统计好的数据更新到统计表中
	 */
	private void updateTongJi() {
		// map格式：{orgID#date:{1011:0,1012:0,......}}
		Map<String, List<TongJi>> orgMap = getTongJiOrg();
		List<TongJi> insertOrg = orgMap.get("insertOrg");
		List<TongJi> calculateInsertOrg = calculateTongJi(insertOrg);
		if (calculateInsertOrg.size() > 0) {
			DaoFactory
					.create(TongJi.class)
					.getSession()
					.insert("com.chinacreator.dzjc_tongji.TongJiMapper.insertCards",
							calculateInsertOrg);
		}
		List<TongJi> updateOrg = orgMap.get("updateOrg");
		List<TongJi> calculateUpdateOrg = calculateTongJi(updateOrg);
		if (calculateUpdateOrg.size() > 0) {
			DaoFactory.create(TongJi.class).updateBatch(calculateUpdateOrg);
		}
		LOGGER.info("统计表发牌更新完成");
	}

	/**
	 * 计算发牌数据
	 * 
	 * @param tongJiList
	 */
	private List<TongJi> calculateTongJi(List<TongJi> tongJiList) {
		List<TongJi> demo = new ArrayList<>();
		List<Map<String, Map<String, Integer>>> mapList = new ArrayList<>();
		for (int i = 0; i < tongJiList.size(); i++) {
			TongJi tj = tongJiList.get(i);
			for (int j = 0; j < 3; j++) {
				Double superviseNum = 0.0;
				if (j == 0) {
					mapList = greenMapList;
					if (tj.getSuperviseGreenNum() != null) {
						superviseNum = tj.getSuperviseGreenNum();
					}
				}
				if (j == 1) {
					mapList = yellowMapList;
					if (tj.getSuperviseYelloNum() != null) {
						superviseNum = tj.getSuperviseYelloNum();
					}
				}
				if (j == 2) {
					mapList = redMapList;
					if (tj.getSuperviseRedNum() != null) {
						superviseNum = tj.getSuperviseRedNum();
					}
				}
				for (int x = 0; x < mapList.size(); x++) {
					boolean flag = false;
					Map<String, Map<String, Integer>> map = mapList.get(x);
					for (Entry<String, Map<String, Integer>> entry : map
							.entrySet()) {
						if (tj.getRecordId().equals(entry.getKey())) {
							Map<String, Integer> value = entry.getValue();
							for (Entry<String, Integer> entry2 : value
									.entrySet()) {
								// 办件
								if (entry2.getKey().equals("1011")
										|| entry2.getKey().equals("1014")) {
									Double num = 0.0;
									if (j == 0) {
										if (tj.getInstanceSuperviseGreenNum() != null) {
											num = tj.getInstanceSuperviseGreenNum();
										}
										num += entry2.getValue();
										tj.setInstanceSuperviseGreenNum(num);
									}
									if (j == 1) {
										if (tj.getInstanceSuperviseYelloNum() != null) {
											num = tj.getInstanceSuperviseYelloNum();
										}
										num += entry2.getValue();
										tj.setInstanceSuperviseYelloNum(num);
									}
									if (j == 2) {
										if (tj.getInstanceSuperviseRedNum() != null) {
											num = tj.getInstanceSuperviseRedNum();
										}
										num += entry2.getValue();
										tj.setInstanceSuperviseRedNum(num);
									}
									superviseNum += num;
								}
								// 投诉
								if (entry2.getKey().equals("1012")) {
									Double num = 0.0;
									if (j == 0) {
										if (tj.getComplainSuperviseGreenNum() != null) {
											num = tj.getComplainSuperviseGreenNum();
										}
										num += entry2.getValue();
										tj.setComplainSuperviseGreenNum(num);
									}
									if (j == 1) {
										if (tj.getComplainSuperviseYelloNum() != null) {
											num = tj.getComplainSuperviseYelloNum();
										}
										num += entry2.getValue();
										tj.setComplainSuperviseYelloNum(num);
									}
									if (j == 2) {
										if (tj.getComplainSuperviseRedNum() != null) {
											num = tj.getComplainSuperviseRedNum();
										}
										num += entry2.getValue();
										tj.setComplainSuperviseRedNum(num);
									}
									superviseNum += num;
								}
								// 咨询
								if (entry2.getKey().equals("1013")
										|| entry2.getKey().equals("1015")) {
									Double num = 0.0;
									if (j == 0) {
										if (tj.getConsultSuperviseGreenNum() != null) {
											num = tj.getConsultSuperviseGreenNum();
										}
										num += entry2.getValue();
										tj.setConsultSuperviseGreenNum(num);
									}
									if (j == 1) {
										if (tj.getConsultSuperviseYelloNum() != null) {
											num = tj.getConsultSuperviseYelloNum();
										}
										num += entry2.getValue();
										tj.setConsultSuperviseYelloNum(num);
									}
									if (j == 2) {
										if (tj.getConsultSuperviseRedNum() != null) {
											num = tj.getConsultSuperviseRedNum();
										}
										num += entry2.getValue();
										tj.setConsultSuperviseRedNum(num);
									}
									superviseNum += num;
								}
								// 特别程序
								if (entry2.getKey().equals("1016")) {
									Double num = 0.0;
									if (j == 0) {
										if (tj.getSpecialSuperviseGreenNum() != null) {
											num = tj.getSpecialSuperviseGreenNum();
										}
										num += entry2.getValue();
										tj.setSpecialSuperviseGreenNum(num);
									}
									if (j == 1) {
										if (tj.getSpecialSuperviseYellowNum() != null) {
											num = tj.getSpecialSuperviseYellowNum();
										}
										num += entry2.getValue();
										tj.setSpecialSuperviseYellowNum(num);
									}
									if (j == 2) {
										if (tj.getSpecialSuperviseRedNum() != null) {
											num = tj.getSpecialSuperviseRedNum();
										}
										num += entry2.getValue();
										tj.setSpecialSuperviseRedNum(num);
									}
									superviseNum += num;
								}
								// 网上办事
								if (entry2.getKey().equals("1017")) {
									Double num = 0.0;
									if (j == 0) {
										if (tj.getWsbsGreenNum() != null) {
											num = tj.getWsbsGreenNum();
										}
										num += entry2.getValue();
										tj.setWsbsGreenNum(num);
									}
									if (j == 1) {
										if (tj.getWsbsYellowNum() != null) {
											num = tj.getWsbsYellowNum();
										}
										num += entry2.getValue();
										tj.setWsbsYellowNum(num);
									}
									if (j == 2) {
										if (tj.getWsbsRedNum() != null) {
											num = tj.getWsbsRedNum();
										}
										num += entry2.getValue();
										tj.setWsbsRedNum(num);
									}
									superviseNum += num;
								}
								// 办件出证
								if (entry2.getKey().equals("1018")) {
									Double num = 0.0;
									if (j == 0) {
										if (tj.getBjczGreenNum() != null) {
											num = tj.getBjczGreenNum();
										}
										num += entry2.getValue();
										tj.setBjczGreenNum(num);
									}
									if (j == 1) {
										if (tj.getBjczYellowNum() != null) {
											num = tj.getBjczYellowNum();
										}
										num += entry2.getValue();
										tj.setBjczYellowNum(num);
									}
									if (j == 2) {
										if (tj.getBjczRedNum() != null) {
											num = tj.getBjczRedNum();
										}
										num += entry2.getValue();
										tj.setBjczRedNum(num);
									}
									superviseNum += num;
								}
							}
							if (j == 0) {
								tj.setSuperviseGreenNum(superviseNum);
							}
							if (j == 1) {
								tj.setSuperviseYelloNum(superviseNum);
							}
							if (j == 2) {
								tj.setSuperviseRedNum(superviseNum);
							}
							flag = true;
						}
					}
					if (flag) {
						break;
					}
				}
			}
			demo.add(tj);
		}
		return demo;
	}

	/**
	 * 分类批量处理数据
	 * 
	 * @param cardType
	 */
	private boolean batchData(Map<String, String> cardType) {
		String type = null;
		if (cardType != null) {
			type = cardType.get("type");
		}
		boolean flag = false;
		boolean flag1 = true;
		int index = 0;
		int startIndex = 0;
		int endIndex = 0;
		int total = 0;
		List<StatisticalSumCache> list = null;
		Map<String, Integer> params = null;
		while (true) {
			params = new HashMap<>();
			params.put("type", Integer.valueOf(type));
			startIndex = index * superviseCount;
			endIndex = (index + 1) * superviseCount;
			params.put("startIndex", startIndex);
			params.put("endIndex", endIndex);
			list = DaoFactory
					.create(StatisticalSumCache.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.StatisticalSumCacheMapper.getMaxCardsList",
							params);
			if (list.size() > 0) {
				total += list.size();
				index++;
				TongJiRunnable jr = new TongJiRunnable(list, type);
				ThreadPoolUtils.execute(jr);
			} else {
				break;
			}
		}
		if (type.equals("1")) {
			LOGGER.info("预警牌：" + total + "条");
		}
		if (type.equals("2")) {
			LOGGER.info("黄牌：" + total + "条");
		}
		if (type.equals("3")) {
			LOGGER.info("红牌：" + total + "条");
		}
		while (true) {
			if (ThreadPoolUtils.getQueue() == 0
					&& ThreadPoolUtils.getActiveCount() == 0) {
				flag = true;
				break;
			} else if (flag1) {
				LOGGER.info("正在统计发牌中......");
				flag1 = false;
			}
		}
		if (type.equals("1")) {
			LOGGER.info("预警牌统计完成");
		}
		if (type.equals("2")) {
			LOGGER.info("黄牌统计完成");
		}
		if (type.equals("3")) {
			LOGGER.info("红牌统计完成");
		}
		return flag;
	}

	/**
	 * 把增量表数据的机构ID和插入时间作为粒度
	 */
	private Map<String, List<TongJi>> getTongJiOrg() {
		Set<String> id = new HashSet<>();
		for (int i = 0; i < greenMapList.size(); i++) {
			Map<String, Map<String, Integer>> map = greenMapList.get(i);
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				id.add(string);
			}
		}
		for (int i = 0; i < yellowMapList.size(); i++) {
			Map<String, Map<String, Integer>> map = yellowMapList.get(i);
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				id.add(string);
			}
		}
		for (int i = 0; i < redMapList.size(); i++) {
			Map<String, Map<String, Integer>> map = redMapList.get(i);
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				id.add(string);
			}
		}
		// 分为需要插入的和需要更新的
		List<String> insertOrgId = new ArrayList<>();
		List<TongJi> updateOrg = new ArrayList<>();
		if (id.size() > 0) {
			updateOrg = DaoFactory
					.create(TongJi.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.TongJiMapper.getOrg",
							new ArrayList<>(id));
		}
		if (updateOrg.size() > 0) {
			for (String s : id) {
				for (TongJi tj : updateOrg) {
					if (!s.equals(tj.getRecordId())) {
						insertOrgId.add(s);
					}
				}
			}
		} else {
			insertOrgId.addAll(id);
		}
		Set<String> orgIds = new HashSet<>();
		for (String string : id) {
			String[] split = string.split("#");
			String orgId = split[0];
			orgIds.add(orgId);
		}
		List<V_TD_SM_ORGANIZATION1> orgList = new ArrayList<>();
		if (orgIds.size() > 0) {
			orgList = DaoFactory
					.create(V_TD_SM_ORGANIZATION1.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.V_TD_SM_ORGANIZATION1Mapper.getOrgInfo",
							new ArrayList<>(orgIds));
		}
		List<TongJi> insertOrg = new ArrayList<>();
		for (String string : insertOrgId) {
			String[] split = string.split("#");
			String orgId = split[0];
			for (int i = 0; i < orgList.size(); i++) {
				if (orgId.equals(orgList.get(i).getOrgId())) {
					TongJi tj = new TongJi();
					String date = string.substring(string.indexOf("#") + 1);
					tj.setOrgId(orgList.get(i).getOrgId());
					tj.setAreaOrgName(orgList.get(i).getOrgName());
					tj.setAreaOrgCode(orgList.get(i).getOrgCode());
					tj.setProvinceCode(orgList.get(i).getProvinceCode());
					tj.setCityCode(orgList.get(i).getCityCode());
					tj.setCountyCode(orgList.get(i).getCountyCode());
					tj.setStreetCode(orgList.get(i).getStreetCode());
					tj.setVillageCode(orgList.get(i).getVillageCode());
					tj.setOrgLevel(orgList.get(i).getOrgLevel());
					tj.setOrgThisLevel(orgList.get(i).getOrgThisLevel());
					tj.setNowDate(date);
					tj.setAcceptNowDate(date);
					tj.setRecordId(string);
					insertOrg.add(tj);
				}
			}
		}
		LOGGER.info("统计表需要新增的机构信息：" + insertOrg.size());
		LOGGER.info("统计表需要更新的机构信息：" + updateOrg.size());
		Map<String, List<TongJi>> orgMap = new HashMap<>();
		orgMap.put("insertOrg", insertOrg);
		orgMap.put("updateOrg", updateOrg);
		return orgMap;
	}

	/**
	 * 将发牌实时表的数据插入增量表
	 * 
	 * @param list
	 * @param i
	 */
	private void insertSendCardsList(List<TaJcSuperviseInfo> list) {
		ArrayList<StatisticalSumCache> joiningList = new ArrayList<StatisticalSumCache>();
		for (TaJcSuperviseInfo tjsi : list) {
			StatisticalSumCache ssc = new StatisticalSumCache();
			ssc.setStatisticalType("1");// 1代表发牌
			ssc.setBusinessId(tjsi.getBusinessId());
			ssc.setSuperviseId(tjsi.getSuperviseInfoId());
			ssc.setStatisticalTime(tjsi.getSuperviseTime());
			ssc.setBusinessStatus(tjsi.getState());
			ssc.setBusinessType(tjsi.getSuperviseResultNo());
			ssc.setOrgId(tjsi.getOrgId());
			ssc.setDataSource(tjsi.getSuperviseTypeNo());
			joiningList.add(ssc);
		}
		DaoFactory.create(StatisticalSumCache.class).insertBatch(joiningList);
	}

	/**
	 * 统计数据
	 * 
	 * @param list
	 * @param colorType
	 * 
	 */
	private void TongJi(List<StatisticalSumCache> list, String colorType) {
		List<Map<String, Map<String, Integer>>> mapList = null;
		if ("1".equals(colorType)) {
			mapList = greenMapList;
		} else if ("2".equals(colorType)) {
			mapList = yellowMapList;
		} else if ("3".equals(colorType)) {
			mapList = redMapList;
		}
		for (int i = 0; i < list.size(); i++) {
			StatisticalSumCache ssc = list.get(i);
			String dataSource = ssc.getDataSource();
			String businessStatus = ssc.getBusinessStatus();
			String id = ssc.getOrgId() + "#" + ssc.getStatisticalTime();
			if (mapList.size() > 0) {
				for (int j = 0; j < mapList.size(); j++) {
					if (mapList.get(j).containsKey(id)) {
						Map<String, Integer> map2 = (Map<String, Integer>) mapList
								.get(j).get(id);
						Integer count = map2.get(dataSource);
						// 发牌只有已发Y和撤销C两种状态
						if (businessStatus.equals("Y")) {
							if (count != null) {
								count++;
							} else {
								count = 1;
							}
						}
						if (businessStatus.equals("C")) {
							if (count != null) {
								count--;
							} else {
								count = 1;
							}
						}
						map2.put(dataSource, count);
						break;
					}
					if (j == mapList.size() - 1) {
						Map<String, Map<String, Integer>> counter = new HashMap<>();
						Map<String, Integer> typeCount = new HashMap<>();
						if (businessStatus.equals("Y")) {
							typeCount.put(dataSource, 1);
						}
						if (businessStatus.equals("C")) {
							typeCount.put(dataSource, 1);
						}
						counter.put(id, typeCount);
						mapList.add(counter);
						break;
					}
				}
			} else {
				Map<String, Map<String, Integer>> counter = new HashMap<>();
				Map<String, Integer> typeCount = new HashMap<>();
				typeCount.put(dataSource, 1);
				counter.put(id, typeCount);
				mapList.add(counter);
			}
		}
		// 预警牌
		if ("1".equals(colorType)) {
			List<String> cardIds = DaoFactory
					.create(StatisticalSumCache.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.StatisticalSumCacheMapper.getGreenCardIds",
							list);
			cardIdsList.addAll(cardIds);
		}
		// 黄牌
		if ("2".equals(colorType)) {
			List<StatisticalSumCache> cardIds = DaoFactory
					.create(StatisticalSumCache.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.StatisticalSumCacheMapper.getYellowWithRedCardIds",
							list);
			for (int i = 0; i < cardIds.size(); i++) {
				cardIdsList.add(cardIds.get(i).getSuperviseId());
			}
			List<Map<String, String>> idsMap = new ArrayList<>();
			List<String> greenTongJiIds = new ArrayList<>();
			for (int i = 0; i < cardIds.size() - 1; i++) {
				StatisticalSumCache ssc = cardIds.get(i);
				if (ssc.getIsStatistical() != null) {
					continue;
				}
				for (int j = i + 1; j < cardIds.size(); j++) {
					StatisticalSumCache ssc1 = cardIds.get(j);
					if (ssc.getBusinessId().equals(ssc.getBusinessId())
							&& ssc1.getOrgId().equals(ssc.getOrgId())
							&& ssc1.getDataSource().equals(ssc.getDataSource())) {
						// 如果该办件以前统计过，则上一级发牌数减一，没统计则不计算
						if (ssc1.getIsStatistical() != null
								&& "Y".equals(ssc1.getIsStatistical())) {
							String id = ssc1.getOrgId() + "#"
									+ ssc1.getStatisticalTime();
							String type = ssc1.getDataSource();
							greenTongJiIds.add(id);
							Map<String, String> param = new HashMap<>();
							param.put("id", id);
							param.put("type", type);
							idsMap.add(param);
							break;
						}
					}
				}
			}
			if (greenTongJiIds.size() > 0 && idsMap.size() > 0) {
				jianpaicaozuo(greenTongJiIds, idsMap, "1");
			}
		}
		// 红牌
		if ("3".equals(colorType)) {
			List<StatisticalSumCache> cardIds = DaoFactory
					.create(StatisticalSumCache.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.StatisticalSumCacheMapper.getYellowWithRedCardIds",
							list);
			for (int i = 0; i < cardIds.size(); i++) {
				cardIdsList.add(cardIds.get(i).getSuperviseId());
			}
			List<Map<String, String>> idsMap = new ArrayList<>();
			List<String> greenTongJiIds = new ArrayList<>();// 考虑到有红牌，有预警牌，但是没发黄牌的情况
			List<String> yellowTongJiIds = new ArrayList<>();
			for (int i = 0; i < cardIds.size() - 1; i++) {
				StatisticalSumCache ssc = cardIds.get(i);
				if (ssc.getIsStatistical() != null) {
					continue;
				}
				for (int j = i + 1; j < cardIds.size(); j++) {
					StatisticalSumCache ssc1 = cardIds.get(j);
					if (ssc.getBusinessId().equals(ssc.getBusinessId())
							&& ssc1.getOrgId().equals(ssc.getOrgId())
							&& ssc1.getDataSource().equals(ssc.getDataSource())) {
						// 如果该办件以前统计过，则上一级发牌数减一，没统计则不计算
						if (ssc1.getIsStatistical() != null
								&& "Y".equals(ssc1.getIsStatistical())) {
							String id = ssc1.getOrgId() + "#"
									+ ssc1.getStatisticalTime();
							String type = ssc1.getDataSource();
							HashMap<String, String> param = new HashMap<>();
							param.put("id", id);
							param.put("type", type);
							idsMap.add(param);
							if ("1".equals(ssc1.getBusinessType())) {
								greenTongJiIds.add(id);
								break;
							}
							if ("2".equals(ssc1.getBusinessType())) {
								yellowTongJiIds.add(id);
								break;
							}
						}
					}
				}
			}
			if (greenTongJiIds.size() > 0 && idsMap.size() > 0) {
				jianpaicaozuo(greenTongJiIds, idsMap, "1");
			}
			if (yellowTongJiIds.size() > 0 && idsMap.size() > 0) {
				jianpaicaozuo(yellowTongJiIds, idsMap, "2");
			}
		}
	}

	/**
	 * 减牌操作，只针对已统计了的预警牌、黄牌
	 * 
	 * @param tongJiids
	 * @param idsMap
	 * @param colorType
	 */
	private void jianpaicaozuo(List<String> tongJiIds,
			List<Map<String, String>> idsMap, String colorType) {
		List<TongJi> orgList = new ArrayList<>();
		if (tongJiIds.size() > 0) {
			orgList = DaoFactory
					.create(TongJi.class)
					.getSession()
					.selectList(
							"com.chinacreator.dzjc_tongji.TongJiMapper.getOrg",
							tongJiIds);
		}
		List<TongJi> tongJiList = new ArrayList<>();
		if (idsMap.size() > 0) {
			for (int i = 0; i < idsMap.size(); i++) {
				Map<String, String> param = idsMap.get(i);
				String id = param.get("id");
				String type = param.get("type");
				for (TongJi tj : orgList) {
					if (tj.getRecordId().equals(id)) {
						Double superviseNum = 0.0;
						// 办件
						if (type.equals("1011") || type.equals("1014")) {
							if (colorType.equals("1")) {
								Double num = tj.getInstanceSuperviseGreenNum();
								num--;
								tj.setInstanceSuperviseGreenNum(num);
								superviseNum += num;
							}
							if (colorType.equals("2")) {
								Double num = tj.getInstanceSuperviseYelloNum();
								num--;
								tj.setInstanceSuperviseYelloNum(num);
								superviseNum += num;
							}
						}
						// 投诉
						if (type.equals("1012")) {
							if (colorType.equals("1")) {
								Double num = tj.getComplainSuperviseGreenNum();
								num--;
								tj.setComplainSuperviseGreenNum(num);
								superviseNum += num;
							}
							if (colorType.equals("2")) {
								Double num = tj.getComplainSuperviseYelloNum();
								num--;
								tj.setComplainSuperviseYelloNum(num);
								superviseNum += num;
							}
						}
						// 咨询
						if (type.equals("1013") || type.equals("1015")) {
							if (colorType.equals("1")) {
								Double num = tj.getConsultSuperviseGreenNum();
								num--;
								tj.setConsultSuperviseGreenNum(num);
								superviseNum += num;

							}
							if (colorType.equals("2")) {
								Double num = tj.getConsultSuperviseYelloNum();
								num--;
								tj.setConsultSuperviseYelloNum(num);
								superviseNum += num;
							}
						}
						// 特别程序
						if (type.equals("1016")) {
							if (colorType.equals("1")) {
								Double num = tj.getSpecialSuperviseGreenNum();
								num--;
								tj.setSpecialSuperviseGreenNum(num);
								superviseNum += num;
							}
							if (colorType.equals("2")) {
								Double num = tj.getSpecialSuperviseYellowNum();
								num--;
								tj.setSpecialSuperviseYellowNum(num);
								superviseNum += num;
							}
						}
						// 网上办事
						if (type.equals("1017")) {
							if (colorType.equals("1")) {
								Double num = tj.getWsbsGreenNum();
								num--;
								tj.setWsbsGreenNum(num);
								superviseNum += num;
							}
							if (colorType.equals("2")) {
								Double num = tj.getWsbsYellowNum();
								num--;
								tj.setWsbsYellowNum(num);
								superviseNum += num;
							}
						}
						// 办件出证
						if (type.equals("1018")) {
							if (colorType.equals("1")) {
								Double num = tj.getBjczGreenNum();
								num--;
								tj.setBjczGreenNum(num);
								superviseNum += num;
							}
							if (colorType.equals("2")) {
								Double num = tj.getBjczYellowNum();
								num--;
								tj.setBjczYellowNum(num);
								superviseNum += num;
							}
						}
						if (colorType.equals("1")) {
							tj.setSuperviseGreenNum(superviseNum);
						}
						if (colorType.equals("2")) {
							tj.setSuperviseYelloNum(superviseNum);
						}
					}
					tongJiList.add(tj);
				}
			}
			DaoFactory.create(TongJi.class).updateBatch(tongJiList);
		}
	}

	private class JoiningRunnable implements Runnable {
		private List<TaJcSuperviseInfo> list;

		public JoiningRunnable(List<TaJcSuperviseInfo> list) {
			this.list = list;
		}

		@Override
		public void run() {
			try {
				insertSendCardsList(list);
			} catch (Exception e) {
				Throwable cause = e.getCause().getCause();
				if (!(cause instanceof SQLIntegrityConstraintViolationException)) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}

	private class TongJiRunnable implements Runnable {
		private List<StatisticalSumCache> list;
		private String colorType;

		public TongJiRunnable(List<StatisticalSumCache> list, String colorType) {
			this.list = list;
			this.colorType = colorType;
		}

		@Override
		public void run() {
			synchronized (TongJiRunnable.class) {
				try {
					TongJi(list, colorType);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}

}
