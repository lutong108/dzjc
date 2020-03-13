package com.chinacreator.dzjc_tongji.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_tongji.service.IncrSendCardsService;

public class IncrSendCardsJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		long s = System.currentTimeMillis();
		IncrSendCardsService imp = new IncrSendCardsService();
		imp.incrSendCards();
		long e = System.currentTimeMillis();
		System.out.println(e - s);
	}

}
