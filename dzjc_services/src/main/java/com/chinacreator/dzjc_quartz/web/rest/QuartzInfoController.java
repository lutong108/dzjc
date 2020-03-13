package com.chinacreator.dzjc_quartz.web.rest;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_quartz.QuartzInfo;
import com.chinacreator.dzjc_quartz.QuartzManager;

@Controller
@Path("dzjc_quartz/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class QuartzInfoController{
	private static final String schedName = ConfigManager.getInstance().getConfig("sched_name");

	@Autowired
	private QuartzManager quartzManager;

	@DELETE
	@Path("quartzInfo/{jobGroup}")
	public void delete(@PathParam(value = "jobGroup") java.lang.String jobGroup) {
		// TODO auto-generated method stub
		QuartzInfo entity = new QuartzInfo();
		entity.setJobGroup(jobGroup);
		DaoFactory.create(QuartzInfo.class).delete(entity);
	}

	@DELETE
	@Path("quartzInfo")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		// TODO auto-generated method stub
		DaoFactory.create(QuartzInfo.class).deleteBatch(ids);
	}

	@GET
	@Path("quartzInfo/{jobGroup}")
	public QuartzInfo get(
			@PathParam(value = "jobGroup") java.lang.String jobGroup) {
		// TODO auto-generated method stub
		return DaoFactory.create(QuartzInfo.class).selectByID(jobGroup);
	}

	@POST
	@Path("quartzInfo")
	public QuartzInfo insert(QuartzInfo entity) {
		// TODO auto-generated method stub
		quartzManager.addCronJob(entity.getJobName(),
				entity.getCronExpression(), entity.getJobGroup(),
				entity.getJobClassName(), "", entity.getTriggerName());
		return entity;
	}

	@POST
	@Path("quartzInfo/{jobGroup}")
	public QuartzInfo update(QuartzInfo entity) {
		// TODO auto-generated method stub
		DaoFactory.create(QuartzInfo.class).update(entity);
		return entity;
	}

	@GET
	@Path("quartzInfo")
	public Page<QuartzInfo> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		// TODO auto-generated method stub

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 创建分页对象
		Pageable pageable = new Pageable(page, rows);
		// 创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/* 创建高级查询对象 */
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/* 初始化实体对象 */
		QuartzInfo entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, QuartzInfo.class) : new QuartzInfo();
		entity.setSchedName(schedName);
		Page<QuartzInfo> pages = DaoFactory.create(QuartzInfo.class)
				.selectPageByCondition(entity, conditions, pageable, sortable, true);
		List<QuartzInfo> list = pages.getContents();
		for (QuartzInfo quartzInfo : list) {
			String nextFireTime = quartzInfo.getNextFireTime();
			Long time = new Long(nextFireTime);
			String d = format.format(time);
			quartzInfo.setNextFireTime(d);
		}
		pages.setContents(list);
		return pages;

	}

	@POST
	@Path("quartzInfo/insertQuartz")
	public int insertQuartz(@RequestBody Map<String, String> map) {
		String jobName = map.get("jobName");
		String jobGroup = map.get("jobGroup");
		String triggerName = map.get("triggerName");
		String cronExpression = map.get("cronExpression");
		String jobClassName = map.get("jobClassName");
		int rulet = quartzManager.addCronJob(jobName, cronExpression, jobGroup, jobClassName, "", triggerName);
		return rulet;
	}

	@POST
	@Path("quartzInfo/getQuartz")
	public QuartzInfo getQuartz(@RequestBody Map<String, String> map) {
		String jobName = map.get("jobName");
		String jobGroup = map.get("jobGroup");
		String schedName = map.get("schedName");
		QuartzInfo entity = new QuartzInfo();
		entity.setJobName(jobName);
		entity.setJobGroup(jobGroup);
		entity.setSchedName(schedName);
		entity = DaoFactory.create(QuartzInfo.class).getSession()
					.selectOne("com.chinacreator.dzjc_quartz.QuartzInfoMapper.selectByID", entity);
		return entity;
	}

	@POST
	@Path("quartzInfo/updateQuartz")
	public int updateQuartz(@RequestBody Map<String, String> map) {
		String jobName = map.get("jobName");
		String jobGroup = map.get("jobGroup");
		String schedName = map.get("schedName");
		String triggerName = map.get("triggerName");
		String cronExpression = map.get("cronExpression");
		QuartzInfo entity = new QuartzInfo();
		entity.setJobName(jobName);
		entity.setJobGroup(jobGroup);
		entity.setSchedName(schedName);
		entity.setCronExpression(cronExpression);
		entity.setTriggerName(triggerName);
		DaoFactory.create(QuartzInfo.class).getSession()
			.update("com.chinacreator.dzjc_quartz.QuartzInfoMapper.updateCron", entity);
		DaoFactory.create(QuartzInfo.class).getSession()
			.update("com.chinacreator.dzjc_quartz.QuartzInfoMapper.updateFireTime", map);
		return quartzManager.resumeJob();
	}

	@POST
	@Path("quartzInfo/deleteQuartz")
	public int deleteQuartz(@RequestBody Map<String, String> map) {
		String jobName = map.get("jobName");
		String jobGroup = map.get("jobGroup");
		String triggerName = map.get("triggerName");
		return quartzManager.removeCronJob(jobName, jobGroup, triggerName);
	}

}
