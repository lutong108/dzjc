package com.chinacreator.util;

import com.chinacreator.c2.dao.PKWrapper;
import com.chinacreator.c2.dao.PKWrapperFactory;
import com.chinacreator.c2.dao.util.PrimaryKeyUtil;

public class EntityUtil {

	/**
	 * 生成实体类主键UUID
	 * 
	 * @param t
	 * @return
	 */
	public void entityPK(Object entity) {

		PKWrapper wrapper;
		try {

			if (PrimaryKeyUtil.getPK(entity) == null && (wrapper = PKWrapperFactory.getPKWrapper(entity)) != null) {
				wrapper.wrapSingleEntityWithPK(entity);
			}

		} catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("实体类UUID主键生成失败");
		}
	}

}
