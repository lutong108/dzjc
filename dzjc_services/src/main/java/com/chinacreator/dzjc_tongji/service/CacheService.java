package com.chinacreator.dzjc_tongji.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chinacreator.c2.cache.Cache;
import com.chinacreator.c2.cache.CacheFactory;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ApprovetypeNew;
import com.chinacreator.dzjc_tongji.util.CacheModel;

@Service
public class CacheService {
	private static final Object lock_ApproveTypeNew = new Object();

	/**返回事项类型数据
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<ApprovetypeNew> getApprovetypeNewList() {
		List<ApprovetypeNew> list = new ArrayList<>();
		Cache cache = CacheFactory.getCache();
		CacheModel cacheModel = (CacheModel) cache.get("ApprovetypeNew_List");
		if (cacheModel == null) {
			synchronized (lock_ApproveTypeNew) {
				cacheModel = (CacheModel) cache.get("ApprovetypeNew_List");
				if (cacheModel == null) {
					list = DaoFactory.create(ApprovetypeNew.class).selectAll();
					cacheModel = new CacheModel(list, System.currentTimeMillis(), 1 * 60 * 60 * 1000L);//1小时刷一次
					cache.put("ApprovetypeNew_List", cacheModel);
				}
			}
		}
		return (List<ApprovetypeNew>) cacheModel.getValue();
	}
	/**返回事项类型数据
	 * @return Map
	 */
	public Map<String, ApprovetypeNew> getApprovetypeNewMap() {
		Map<String, ApprovetypeNew> map = new HashMap<String, ApprovetypeNew>();
		List<ApprovetypeNew> list = getApprovetypeNewList();
		if(list!=null){
			for (ApprovetypeNew approvetypeNew : list) {
				map.put(approvetypeNew.getTypeCode(), approvetypeNew);
			}
		}
		return map;
	}
}
