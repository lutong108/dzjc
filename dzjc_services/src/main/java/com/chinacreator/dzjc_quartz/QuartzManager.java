package com.chinacreator.dzjc_quartz;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.core.jmx.JobDetailSupport;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.web.init.ServerStartup;

/**
 * quartz管理类
 * 
 * @author zyz818
 * 
 */
@Component
public class QuartzManager implements ServerStartup {
	private static Logger LOG = LoggerFactory.getLogger(QuartzManager.class);
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

	/**
	 * 增加任务
	 * 
	 * @param jobName
	 * @param time
	 * @param groupName
	 * @param jobClass
	 * @param task
	 * @param triggerName
	 * @return
	 */
	public int addCronJob(String jobName, String time, String groupName,
			String jobClass, Object task, String triggerName) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			// 判断任务是否存在
			JobKey jobKey = JobKey.jobKey(jobName, groupName);
			if (scheduler.checkExists(jobKey)) {
				return 1;// 任务已经存在
			}
			Map<String, Object> JobDetailmap = new HashMap<String, Object>();
			JobDetailmap.put("name", jobName);// 设置任务名字
			JobDetailmap.put("group", groupName);// 设置任务组
			JobDetailmap.put("jobClass", jobClass);// 指定执行类
			JobDetail jobDetail = JobDetailSupport.newJobDetail(JobDetailmap);
			// 添加数据内容
			jobDetail.getJobDataMap().put("task", task);// 传输的上下文
			// 触发器
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setName(triggerName);
			trigger.setGroup(groupName);
			trigger.setCronExpression(time);
			trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
			scheduler.scheduleJob(jobDetail, trigger);
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
			return 2;
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			return 2;
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
			return 2;
		}
		return 0;
	}

	/**
	 * 删除任务
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @return
	 */
	public int removeCronJob(String jobName, String jobGroup, String triggerName) {
		try {
			TriggerKey tKey = new TriggerKey(triggerName, jobGroup);
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.pauseTrigger(tKey);// 停止触发器
			scheduler.unscheduleJob(tKey);// 移除触发器
			scheduler.deleteJob(new JobKey(jobName, jobGroup));// 删除任务
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			return 2;
		}
		return 0;
	}

	/**
	 * 从数据库中找到已经存在的job，并重新开始调度
	 */
	public int resumeJob() {
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/quartz.properties");
		try {
			prop.load(in);
			prop.setProperty("org.quartz.scheduler.instanceName", ConfigManager.getInstance().getConfig("sched_name"));
			prop.setProperty("org.quartz.dataSource.qzDS.driver", ConfigManager.getInstance().getConfig("accept_db_driver"));
			prop.setProperty("org.quartz.dataSource.qzDS.URL", ConfigManager.getInstance().getConfig("accept_db_url"));
			prop.setProperty("org.quartz.dataSource.qzDS.user", ConfigManager.getInstance().getConfig("accept_db_username"));
			prop.setProperty("org.quartz.dataSource.qzDS.password", ConfigManager.getInstance().getConfig("accept_db_password"));
			prop.setProperty("org.quartz.dataSource.qzDS.maxConnection", ConfigManager.getInstance().getConfig("org_quartz_dataSource_qzDS_maxConnection"));
			schedulerFactory = new StdSchedulerFactory(prop);
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return 2;
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
			return 2;
		}
		return 0;
	}

	@Override
	public void close() {

	}

	@Override
	public void startup(ServletContext context) {
		LOG.info("quartz start");
		resumeJob();
	}

}
