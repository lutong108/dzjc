package com.chinacreator.dzjc_tongji.service;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_tongji.StatisticalSumCache;

@Service
public class StatisticalSumCacheService {

	public StatisticalSumCache update(StatisticalSumCache entity) {
		// TODO auto-generated method stub
		DaoFactory.create(StatisticalSumCache.class).update(entity);
		return entity;
	}

	public StatisticalSumCache insert(StatisticalSumCache entity) {
		// TODO auto-generated method stub
		DaoFactory.create(StatisticalSumCache.class).insert(entity);
		return entity;
	}

	public StatisticalSumCache get(
			@PathParam(value = "superviseId") java.lang.String superviseId) {
		// TODO auto-generated method stub
		return DaoFactory.create(StatisticalSumCache.class).selectByID(
				superviseId);
	}

	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		// TODO auto-generated method stub
		DaoFactory.create(StatisticalSumCache.class).deleteBatch(ids);
	}

	public void delete(
			@PathParam(value = "superviseId") java.lang.String superviseId) {
		// TODO auto-generated method stub
		StatisticalSumCache entity = new StatisticalSumCache();
		entity.setSuperviseId(superviseId);
		DaoFactory.create(StatisticalSumCache.class).delete(entity);
	}

	public Page<StatisticalSumCache> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		// TODO auto-generated method stub
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		StatisticalSumCache entity = StringUtils.isNotBlank(cond) ? JSON
				.parseObject(cond, StatisticalSumCache.class)
				: new StatisticalSumCache();

		return DaoFactory.create(StatisticalSumCache.class)
				.selectPageByCondition(entity, conditions, pageable, sortable,
						true);

	}

	public void incrSendCards() {
		IncrSendCardsService sendCardsService = new IncrSendCardsService();
		sendCardsService.incrSendCards();
	}
}
