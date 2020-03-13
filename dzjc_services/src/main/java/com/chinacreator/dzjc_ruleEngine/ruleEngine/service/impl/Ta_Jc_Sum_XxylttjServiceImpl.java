package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.Date;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_log.util.ConsoleLogUtil;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.Ta_Jc_Sum_XxylttjServiceIfc;
import com.chinacreator.dzjc_ruleEngine.utils.DateUtil;
import com.chinacreator.dzjc_tongji.Ta_Jc_Sum_Xxylttj;

/**@Title	: 湘西一路通统计
 * @Author	: zouhailin
 * @Date	: 2019-4-9
 */
public class Ta_Jc_Sum_XxylttjServiceImpl implements
		Ta_Jc_Sum_XxylttjServiceIfc {

	@Override
	public void doStartup()throws Exception{
		Date beginDate = new Date();
		try {
			DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).getSession().insert("com.chinacreator.dzjc_tongji.Ta_Jc_Sum_XxylttjMapper.proc_insert_ta_jc_sum_xxylttj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date endDate = new Date();
		try {
			String shichang = DateUtil.getDatePoor(endDate, beginDate);
			ConsoleLogUtil.insertConsoleLog("湘西一路通统计","完成时间："+DateUtil.getFormatDateTime(endDate, "yyyy-MM-dd HH:mm:ss"+" 所用时长："+shichang));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Sp_status stauts = new Sp_status();
		stauts.setFunctionName("湘西一路通统计固化");
		stauts.setType("51");
		stauts.setStatus("Y");
		String startTimeStr = sdf.format(new Date());
		
		try {
			DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).getSession().delete("com.chinacreator.dzjc_tongji.Ta_Jc_Sum_XxylttjMapper.deleteAll");
			DaoFactory.create(Ta_Jc_Sum_Xxylttj.class).getSession().insert("com.chinacreator.dzjc_tongji.Ta_Jc_Sum_XxylttjMapper.init");
		} catch (Exception e) {
			stauts.setStatus("N");
			stauts.setException(e.getMessage().substring(0, 2000));
			e.printStackTrace();
		}
		stauts.setSuperviseStartTime(DateUtil.strToDate(startTimeStr));
		String endTimeStr = sdf.format(new Date());
		stauts.setSuperviseEndTime(DateUtil.strToDate(endTimeStr));
		stauts.setSuperviseUseTimes(new Double(DateUtil.getDiff(startTimeStr, endTimeStr)));
		DaoFactory.create(Sp_status.class).insert(stauts);*/
	}

}
