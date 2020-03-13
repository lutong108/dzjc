package com.chinacreator.dzjc_tongji.util;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.chinacreator.c2.cache.Cache;
import com.chinacreator.c2.cache.exception.CacheException;


/**
 * 本地缓存管理器
 *
 */
public class LocalCacheManager implements Cache{
	
	private LocalCacheManager(){
		ThreadPoolUtils.execute(cleaner);
	}
	
	private Map<String, SoftReference<Object>> cacheMap = new ConcurrentHashMap<String, SoftReference<Object>>();
	
	private Long checkTime = 5 * 1000L;
	
	private Runnable cleaner = new Runnable() {
		
		@Override
		public void run() {
			while(true){
				Set<Entry<String, SoftReference<Object>>> entrySet = cacheMap.entrySet();
				for (Entry<String, SoftReference<Object>> entry : entrySet) {
					Object obj = entry.getValue().get();
					if (obj instanceof CacheModel) {
						CacheModel cacheModel = (CacheModel)obj;
						if (cacheModel == null || cacheModel.isValid()) {
							cacheMap.remove(entry.getKey());
						}
					}
				}
				try {
					Thread.sleep(checkTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	};

	@Override
	public Object get(String key) throws CacheException {
		if (cacheMap.containsKey(key)) {
			if (cacheMap.get(key).get() != null) {
				return cacheMap.get(key).get();
			}
		}
		return null;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void put(String key, Object obj) throws CacheException {
		SoftReference<Object> srf = new SoftReference<>(obj);
		cacheMap.put(key, srf);
	}

	@Override
	public void remove(String key) throws CacheException {
		cacheMap.remove(key);
	}
	
}
