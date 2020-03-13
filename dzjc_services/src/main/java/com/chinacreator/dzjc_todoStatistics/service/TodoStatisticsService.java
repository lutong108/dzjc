package com.chinacreator.dzjc_todoStatistics.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_todoStatistics.EgovernmentListBean;
import com.chinacreator.dzjc_todoStatistics.EinstanceListBean;

@Service
public class TodoStatisticsService {
	
	public Map<String,Object> getTodoStatisticsList(Map<String, String> params){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Map<String,Object> result=new HashMap<>();
		String selectType=params.get("selectType");
		if(selectType.equals("0")){
			//按月统计
			 Calendar cal = Calendar.getInstance();
			 cal.set(Calendar.YEAR,Integer.valueOf(params.get("year")));
			 cal.set(Calendar.MONTH, Integer.valueOf(params.get("month")));
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 cal.add(Calendar.DAY_OF_MONTH, -1);
			 Date lastDate = cal.getTime();			  
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 Date firstDate = cal.getTime();
			 
			 params.put("startDate", format.format(firstDate));
			 params.put("endDate", format.format(lastDate)+" 23:59:59");
		}else{
			//按时间段统计
			String startDate = format.format(new Date(Long.parseLong(params.get("startDate"))));
			String endDate = format.format(new Date(Long.parseLong(params.get("endDate"))));
			params.put("startDate", startDate);
			params.put("endDate", endDate+" 23:59:59");
		}
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accBjRed = 0; //累计办件红牌
		long accBjYellow = 0; //累计办件黄牌
		long accCnRed = 0; //累计投诉红牌
		long accCnYellow = 0; //累计投诉黄牌
		long accCsRed = 0; //累计咨询红牌
		long accCsYellow = 0; //累计咨询黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getTodoStatisticsList", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			
			accBjRed += ego.getBjred();
			accBjYellow += ego.getBjyellow();	
			accCnRed += ego.getCnred();
			accCnYellow += ego.getCnyellow();
			accCsRed += ego.getCsred();
			accCsYellow += ego.getCsyellow();
			
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		if(egover.size()>1){
			EgovernmentListBean egovernmentListBean=new EgovernmentListBean();
			egovernmentListBean.setAreaName("合计");
			egovernmentListBean.setSj(accCollection);
			egovernmentListBean.setBj(accWork);
			egovernmentListBean.setTsbj(acctsColl);
			float overRate = 0;
			float riseRate = 0;
			if (0 != egovernmentListBean.getSj()) {
				overRate = (new Long(egovernmentListBean.getBj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue())* 100;
				riseRate = (new Long(egovernmentListBean.getTsbj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue()) * 100;
			}else if(0==egovernmentListBean.getSj() && (0!=egovernmentListBean.getBj() || 0!=egovernmentListBean.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			egovernmentListBean.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			egovernmentListBean.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			egovernmentListBean.setBjyellow(accBjYellow);
			egovernmentListBean.setBjred(accBjRed);
			egovernmentListBean.setCnyellow(accCnYellow);
			egovernmentListBean.setCnred(accCnRed);
			egovernmentListBean.setCsyellow(accCsYellow);
			egovernmentListBean.setCsred(accCsRed);
			
			egovernmentListBean.setCn(accComplaint);
			egovernmentListBean.setCrn(accComplaintReply);
			egovernmentListBean.setCsn(accConsult);
			egovernmentListBean.setCsrn(accConsultReply);
			egover.add(egovernmentListBean);
		}
		
		result.put("list", egover);
		result.put("accCollection", accCollection);
		result.put("accWork", accWork);
		
		result.put("accBjRed", accBjRed);
		result.put("accBjYellow", accBjYellow);
		result.put("accCnRed", accCnRed);
		result.put("accCnYellow", accCnYellow);
		result.put("accCsRed", accCsRed);
		result.put("accCsYellow", accCsYellow);
		
		result.put("accComplaint", accComplaint);
		result.put("accComplaintReply", accComplaintReply);
		result.put("accConsult", accConsult);
		result.put("accConsultReply", accConsultReply);
		return result;
	}
	
	public Map<String,Object> getLevelStatisticsList(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		String selectType=params.get("selectType");
		if(selectType.equals("0")){
			//按月统计
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			 Calendar cal = Calendar.getInstance();
			 cal.set(Calendar.YEAR,Integer.valueOf(params.get("year")));
			 cal.set(Calendar.MONTH, Integer.valueOf(params.get("month")));
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 cal.add(Calendar.DAY_OF_MONTH, -1);
			 Date lastDate = cal.getTime();			  
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 Date firstDate = cal.getTime();
			 
			 params.put("startDate", format.format(firstDate));
			 params.put("endDate", format.format(lastDate)+" 23:59:59");
		}else{
			//按时间段统计
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			String startDate = format.format(new Date(Long.parseLong(params.get("startDate"))));
			String endDate = format.format(new Date(Long.parseLong(params.get("endDate"))));
			params.put("startDate", startDate);
			params.put("endDate", endDate+" 23:59:59");
		}
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accBjRed = 0; //累计办件红牌
		long accBjYellow = 0; //累计办件黄牌
		long accCnRed = 0; //累计投诉红牌
		long accCnYellow = 0; //累计投诉黄牌
		long accCsRed = 0; //累计咨询红牌
		long accCsYellow = 0; //累计咨询黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getLevelStatisticsList", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			
			accBjRed += ego.getBjred();
			accBjYellow += ego.getBjyellow();	
			accCnRed += ego.getCnred();
			accCnYellow += ego.getCnyellow();
			accCsRed += ego.getCsred();
			accCsYellow += ego.getCsyellow();
			
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		if(egover.size()>1){
			EgovernmentListBean egovernmentListBean=new EgovernmentListBean();
			egovernmentListBean.setAreaName("合计");
			egovernmentListBean.setSj(accCollection);
			egovernmentListBean.setBj(accWork);
			egovernmentListBean.setTsbj(acctsColl);
			float overRate = 0;
			float riseRate = 0;
			if (0 != egovernmentListBean.getSj()) {
				overRate = (new Long(egovernmentListBean.getBj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue())* 100;
				riseRate = (new Long(egovernmentListBean.getTsbj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue()) * 100;
			}else if(0==egovernmentListBean.getSj() && (0!=egovernmentListBean.getBj() || 0!=egovernmentListBean.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			egovernmentListBean.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			egovernmentListBean.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			egovernmentListBean.setBjyellow(accBjYellow);
			egovernmentListBean.setBjred(accBjRed);
			egovernmentListBean.setCnyellow(accCnYellow);
			egovernmentListBean.setCnred(accCnRed);
			egovernmentListBean.setCsyellow(accCsYellow);
			egovernmentListBean.setCsred(accCsRed);
			
			egovernmentListBean.setCn(accComplaint);
			egovernmentListBean.setCrn(accComplaintReply);
			egovernmentListBean.setCsn(accConsult);
			egovernmentListBean.setCsrn(accConsultReply);
			egover.add(egovernmentListBean);
		}
		
		result.put("list", egover);
		result.put("accCollection", accCollection);
		result.put("accWork", accWork);
		
		result.put("accBjRed", accBjRed);
		result.put("accBjYellow", accBjYellow);
		result.put("accCnRed", accCnRed);
		result.put("accCnYellow", accCnYellow);
		result.put("accCsRed", accCsRed);
		result.put("accCsYellow", accCsYellow);
		
		result.put("accComplaint", accComplaint);
		result.put("accComplaintReply", accComplaintReply);
		result.put("accConsult", accConsult);
		result.put("accConsultReply", accConsultReply);
		return result;
	}
	
	public Map<String,Object> getCityLevelStatisticsList(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		String selectType=params.get("selectType");
		if(selectType.equals("0")){
			//按月统计
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			 Calendar cal = Calendar.getInstance();
			 cal.set(Calendar.YEAR,Integer.valueOf(params.get("year")));
			 cal.set(Calendar.MONTH, Integer.valueOf(params.get("month")));
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 cal.add(Calendar.DAY_OF_MONTH, -1);
			 Date lastDate = cal.getTime();			  
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 Date firstDate = cal.getTime();
			 
			 params.put("startDate", format.format(firstDate));
			 params.put("endDate", format.format(lastDate)+" 23:59:59");
		}else{
			//按时间段统计
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			String startDate = format.format(new Date(Long.parseLong(params.get("startDate"))));
			String endDate = format.format(new Date(Long.parseLong(params.get("endDate"))));
			params.put("startDate", startDate);
			params.put("endDate", endDate+" 23:59:59");
		}		
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accBjRed = 0; //累计办件红牌
		long accBjYellow = 0; //累计办件黄牌
		long accCnRed = 0; //累计投诉红牌
		long accCnYellow = 0; //累计投诉黄牌
		long accCsRed = 0; //累计咨询红牌
		long accCsYellow = 0; //累计咨询黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getCityLevelStatisticsList", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			
			accBjRed += ego.getBjred();
			accBjYellow += ego.getBjyellow();	
			accCnRed += ego.getCnred();
			accCnYellow += ego.getCnyellow();
			accCsRed += ego.getCsred();
			accCsYellow += ego.getCsyellow();
			
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		if(egover.size()>1){
			EgovernmentListBean egovernmentListBean=new EgovernmentListBean();
			egovernmentListBean.setAreaName("合计");
			egovernmentListBean.setSj(accCollection);
			egovernmentListBean.setBj(accWork);
			egovernmentListBean.setTsbj(acctsColl);
			float overRate = 0;
			float riseRate = 0;
			if (0 != egovernmentListBean.getSj()) {
				overRate = (new Long(egovernmentListBean.getBj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue())* 100;
				riseRate = (new Long(egovernmentListBean.getTsbj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue()) * 100;
			}else if(0==egovernmentListBean.getSj() && (0!=egovernmentListBean.getBj() || 0!=egovernmentListBean.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			egovernmentListBean.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			egovernmentListBean.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			egovernmentListBean.setBjyellow(accBjYellow);
			egovernmentListBean.setBjred(accBjRed);
			egovernmentListBean.setCnyellow(accCnYellow);
			egovernmentListBean.setCnred(accCnRed);
			egovernmentListBean.setCsyellow(accCsYellow);
			egovernmentListBean.setCsred(accCsRed);
			
			egovernmentListBean.setCn(accComplaint);
			egovernmentListBean.setCrn(accComplaintReply);
			egovernmentListBean.setCsn(accConsult);
			egovernmentListBean.setCsrn(accConsultReply);
			egover.add(egovernmentListBean);
		}
		
		result.put("list", egover);
		result.put("accCollection", accCollection);
		result.put("accWork", accWork);
		
		result.put("accBjRed", accBjRed);
		result.put("accBjYellow", accBjYellow);
		result.put("accCnRed", accCnRed);
		result.put("accCnYellow", accCnYellow);
		result.put("accCsRed", accCsRed);
		result.put("accCsYellow", accCsYellow);
		
		result.put("accComplaint", accComplaint);
		result.put("accComplaintReply", accComplaintReply);
		result.put("accConsult", accConsult);
		result.put("accConsultReply", accConsultReply);
		return result;
	}
	
	public Map<String,Object> getCityAreaStatisticsList(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		String selectType=params.get("selectType");
		if(selectType.equals("0")){
			//按月统计
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			 Calendar cal = Calendar.getInstance();
			 cal.set(Calendar.YEAR,Integer.valueOf(params.get("year")));
			 cal.set(Calendar.MONTH, Integer.valueOf(params.get("month")));
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 cal.add(Calendar.DAY_OF_MONTH, -1);
			 Date lastDate = cal.getTime();			  
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 Date firstDate = cal.getTime();
			 
			 params.put("startDate", format.format(firstDate));
			 params.put("endDate", format.format(lastDate)+" 23:59:59");
		}else{
			//按时间段统计
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			String startDate = format.format(new Date(Long.parseLong(params.get("startDate"))));
			String endDate = format.format(new Date(Long.parseLong(params.get("endDate"))));
			params.put("startDate", startDate);
			params.put("endDate", endDate+" 23:59:59");
		}
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accBjRed = 0; //累计办件红牌
		long accBjYellow = 0; //累计办件黄牌
		long accCnRed = 0; //累计投诉红牌
		long accCnYellow = 0; //累计投诉黄牌
		long accCsRed = 0; //累计咨询红牌
		long accCsYellow = 0; //累计咨询黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getCityAreaStatisticsList", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			
			accBjRed += ego.getBjred();
			accBjYellow += ego.getBjyellow();	
			accCnRed += ego.getCnred();
			accCnYellow += ego.getCnyellow();
			accCsRed += ego.getCsred();
			accCsYellow += ego.getCsyellow();
			
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		if(egover.size()>1){
			EgovernmentListBean egovernmentListBean=new EgovernmentListBean();
			egovernmentListBean.setAreaName("合计");
			egovernmentListBean.setSj(accCollection);
			egovernmentListBean.setBj(accWork);
			egovernmentListBean.setTsbj(acctsColl);
			float overRate = 0;
			float riseRate = 0;
			if (0 != egovernmentListBean.getSj()) {
				overRate = (new Long(egovernmentListBean.getBj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue())* 100;
				riseRate = (new Long(egovernmentListBean.getTsbj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue()) * 100;
			}else if(0==egovernmentListBean.getSj() && (0!=egovernmentListBean.getBj() || 0!=egovernmentListBean.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			egovernmentListBean.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			egovernmentListBean.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			egovernmentListBean.setBjyellow(accBjYellow);
			egovernmentListBean.setBjred(accBjRed);
			egovernmentListBean.setCnyellow(accCnYellow);
			egovernmentListBean.setCnred(accCnRed);
			egovernmentListBean.setCsyellow(accCsYellow);
			egovernmentListBean.setCsred(accCsRed);
			
			egovernmentListBean.setCn(accComplaint);
			egovernmentListBean.setCrn(accComplaintReply);
			egovernmentListBean.setCsn(accConsult);
			egovernmentListBean.setCsrn(accConsultReply);
			egover.add(egovernmentListBean);
		}
		
		result.put("list", egover);
		result.put("accCollection", accCollection);
		result.put("accWork", accWork);
		
		result.put("accBjRed", accBjRed);
		result.put("accBjYellow", accBjYellow);
		result.put("accCnRed", accCnRed);
		result.put("accCnYellow", accCnYellow);
		result.put("accCsRed", accCsRed);
		result.put("accCsYellow", accCsYellow);
		
		result.put("accComplaint", accComplaint);
		result.put("accComplaintReply", accComplaintReply);
		result.put("accConsult", accConsult);
		result.put("accConsultReply", accConsultReply);
		return result;
	}
	
	public Map<String,Object> getCityOrgStatisticsList(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		String selectType=params.get("selectType");
		if(selectType.equals("0")){
			//按月统计
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
			 Calendar cal = Calendar.getInstance();
			 cal.set(Calendar.YEAR,Integer.valueOf(params.get("year")));
			 cal.set(Calendar.MONTH, Integer.valueOf(params.get("month")));
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 cal.add(Calendar.DAY_OF_MONTH, -1);
			 Date lastDate = cal.getTime();			  
			 cal.set(Calendar.DAY_OF_MONTH, 1);
			 Date firstDate = cal.getTime();
			 
			 params.put("startDate", format.format(firstDate));
			 params.put("endDate", format.format(lastDate)+" 23:59:59");
		}else{
			//按时间段统计
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
			String startDate = format.format(new Date(Long.parseLong(params.get("startDate"))));
			String endDate = format.format(new Date(Long.parseLong(params.get("endDate"))));
			params.put("startDate", startDate);
			params.put("endDate", endDate+" 23:59:59");
		}
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accBjRed = 0; //累计办件红牌
		long accBjYellow = 0; //累计办件黄牌
		long accCnRed = 0; //累计投诉红牌
		long accCnYellow = 0; //累计投诉黄牌
		long accCsRed = 0; //累计咨询红牌
		long accCsYellow = 0; //累计咨询黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getCityOrgStatisticsList", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			
			accBjRed += ego.getBjred();
			accBjYellow += ego.getBjyellow();	
			accCnRed += ego.getCnred();
			accCnYellow += ego.getCnyellow();
			accCsRed += ego.getCsred();
			accCsYellow += ego.getCsyellow();
			
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		if(egover.size()>1){
			EgovernmentListBean egovernmentListBean=new EgovernmentListBean();
			egovernmentListBean.setAreaName("合计");
			egovernmentListBean.setSj(accCollection);
			egovernmentListBean.setBj(accWork);
			egovernmentListBean.setTsbj(acctsColl);
			float overRate = 0;
			float riseRate = 0;
			if (0 != egovernmentListBean.getSj()) {
				overRate = (new Long(egovernmentListBean.getBj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue())* 100;
				riseRate = (new Long(egovernmentListBean.getTsbj()).floatValue()/new Long(egovernmentListBean.getSj()).floatValue()) * 100;
			}else if(0==egovernmentListBean.getSj() && (0!=egovernmentListBean.getBj() || 0!=egovernmentListBean.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			egovernmentListBean.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			egovernmentListBean.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			egovernmentListBean.setBjyellow(accBjYellow);
			egovernmentListBean.setBjred(accBjRed);
			egovernmentListBean.setCnyellow(accCnYellow);
			egovernmentListBean.setCnred(accCnRed);
			egovernmentListBean.setCsyellow(accCsYellow);
			egovernmentListBean.setCsred(accCsRed);
			
			egovernmentListBean.setCn(accComplaint);
			egovernmentListBean.setCrn(accComplaintReply);
			egovernmentListBean.setCsn(accConsult);
			egovernmentListBean.setCsrn(accConsultReply);
			egover.add(egovernmentListBean);
		}
		
		result.put("list", egover);
		result.put("accCollection", accCollection);
		result.put("accWork", accWork);
		
		result.put("accBjRed", accBjRed);
		result.put("accBjYellow", accBjYellow);
		result.put("accCnRed", accCnRed);
		result.put("accCnYellow", accCnYellow);
		result.put("accCsRed", accCsRed);
		result.put("accCsYellow", accCsYellow);
		
		result.put("accComplaint", accComplaint);
		result.put("accComplaintReply", accComplaintReply);
		result.put("accConsult", accConsult);
		result.put("accConsultReply", accConsultReply);
		return result;
	}
	
	
	public Map<String,Object> getTodoStatisticsListAll(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accRed = 0; //累计红牌
		long accYellow = 0; //累计黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		
		float accbjPercent=0;//办结率
		float acctsPercent=0;//提速率
		
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getTodoStatisticsListAll", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			accRed += ego.getRed();
			accYellow += ego.getYellow();				
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		
		if(egover.size()>0){
			float overRate = 0;
			float riseRate = 0;
			overRate = (new Long(accWork).floatValue()/new Long(accCollection).floatValue())* 100;
			riseRate = (new Long(acctsColl).floatValue()/new Long(accCollection).floatValue()) * 100;
			accbjPercent=(float) (Math.round(overRate * 100)) / 100;
			acctsPercent=(float) (Math.round(riseRate * 100)) / 100;
		}
		
		
		result.put("accCollectionAll", accCollection);
		result.put("accWorkAll", accWork);
		result.put("accRedAll", accRed);
		result.put("accYellowAll", accYellow);
		result.put("accComplaintAll", accComplaint);
		result.put("accComplaintReplyAll", accComplaintReply);
		result.put("accConsultAll", accConsult);
		result.put("accConsultReplyAll", accConsultReply);
		result.put("accbjPercentAll", accbjPercent);
		result.put("acctsPercentAll", acctsPercent);
		
		String areaNameAll=DaoFactory.create(EgovernmentListBean.class).getSession().selectOne("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getAreaName", params);
		result.put("areaNameAll", areaNameAll);
		return result;
	}
	
	
	public Map<String,Object> getLevelStatisticsListAll(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		
		
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accRed = 0; //累计红牌
		long accYellow = 0; //累计黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		
		float accbjPercent=0;//办结率
		float acctsPercent=0;//提速率
		
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getLevelStatisticsListAll", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			accRed += ego.getRed();
			accYellow += ego.getYellow();				
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		
		if(egover.size()>0){
			float overRate = 0;
			float riseRate = 0;
			overRate = (new Long(accWork).floatValue()/new Long(accCollection).floatValue())* 100;
			riseRate = (new Long(acctsColl).floatValue()/new Long(accCollection).floatValue()) * 100;
			accbjPercent=(float) (Math.round(overRate * 100)) / 100;
			acctsPercent=(float) (Math.round(riseRate * 100)) / 100;
		}
		
		
		
		result.put("accCollectionAll", accCollection);
		result.put("accWorkAll", accWork);
		result.put("accRedAll", accRed);
		result.put("accYellowAll", accYellow);
		result.put("accComplaintAll", accComplaint);
		result.put("accComplaintReplyAll", accComplaintReply);
		result.put("accConsultAll", accConsult);
		result.put("accConsultReplyAll", accConsultReply);
		result.put("accbjPercentAll", accbjPercent);
		result.put("acctsPercentAll", acctsPercent);
		
		String areaNameAll=DaoFactory.create(EgovernmentListBean.class).getSession().selectOne("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getAreaName", params);
		result.put("areaNameAll", areaNameAll);
		
		return result;
	}

	
	public Map<String,Object> getCityLevelStatisticsListAll(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
			
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accRed = 0; //累计红牌
		long accYellow = 0; //累计黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		
		float accbjPercent=0;//办结率
		float acctsPercent=0;//提速率
		
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getCityLevelStatisticsListAll", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			accRed += ego.getRed();
			accYellow += ego.getYellow();				
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		
		if(egover.size()>0){
			float overRate = 0;
			float riseRate = 0;
			overRate = (new Long(accWork).floatValue()/new Long(accCollection).floatValue())* 100;
			riseRate = (new Long(acctsColl).floatValue()/new Long(accCollection).floatValue()) * 100;
			accbjPercent=(float) (Math.round(overRate * 100)) / 100;
			acctsPercent=(float) (Math.round(riseRate * 100)) / 100;
		}
		
		
		
		result.put("accCollectionAll", accCollection);
		result.put("accWorkAll", accWork);
		result.put("accRedAll", accRed);
		result.put("accYellowAll", accYellow);
		result.put("accComplaintAll", accComplaint);
		result.put("accComplaintReplyAll", accComplaintReply);
		result.put("accConsultAll", accConsult);
		result.put("accConsultReplyAll", accConsultReply);
		result.put("accbjPercentAll", accbjPercent);
		result.put("acctsPercentAll", acctsPercent);
		
		String areaNameAll=DaoFactory.create(EgovernmentListBean.class).getSession().selectOne("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getAreaName", params);
		result.put("areaNameAll", areaNameAll);
		
		return result;
	}
	
	
	
	public Map<String,Object> getCityAreaStatisticsListAll(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accRed = 0; //累计红牌
		long accYellow = 0; //累计黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		
		float accbjPercent=0;//办结率
		float acctsPercent=0;//提速率
		
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getCityAreaStatisticsListAll", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			accRed += ego.getRed();
			accYellow += ego.getYellow();				
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		
		
		if(egover.size()>0){
			float overRate = 0;
			float riseRate = 0;
			overRate = (new Long(accWork).floatValue()/new Long(accCollection).floatValue())* 100;
			riseRate = (new Long(acctsColl).floatValue()/new Long(accCollection).floatValue()) * 100;
			accbjPercent=(float) (Math.round(overRate * 100)) / 100;
			acctsPercent=(float) (Math.round(riseRate * 100)) / 100;
		}
		
		
		
		result.put("accCollectionAll", accCollection);
		result.put("accWorkAll", accWork);
		result.put("accRedAll", accRed);
		result.put("accYellowAll", accYellow);
		result.put("accComplaintAll", accComplaint);
		result.put("accComplaintReplyAll", accComplaintReply);
		result.put("accConsultAll", accConsult);
		result.put("accConsultReplyAll", accConsultReply);
		result.put("accbjPercentAll", accbjPercent);
		result.put("acctsPercentAll", acctsPercent);
		
		String areaNameAll=DaoFactory.create(EgovernmentListBean.class).getSession().selectOne("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getAreaName", params);
		result.put("areaNameAll", areaNameAll);
		
		return result;
	}
	
	
	public Map<String,Object> getCityOrgStatisticsListAll(Map<String, String> params){
		Map<String,Object> result=new HashMap<>();
		
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accRed = 0; //累计红牌
		long accYellow = 0; //累计黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		
		float accbjPercent=0;//办结率
		float acctsPercent=0;//提速率
		
		List<EgovernmentListBean> egover=DaoFactory.create(EgovernmentListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getCityOrgStatisticsListAll", params);
		for(EgovernmentListBean ego:egover){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ego.getSj()) {
				overRate = (new Long(ego.getBj()).floatValue()/new Long(ego.getSj()).floatValue())* 100;
				riseRate = (new Long(ego.getTsbj()).floatValue()/new Long(ego.getSj()).floatValue()) * 100;
			}else if(0==ego.getSj() && (0!=ego.getBj() || 0!=ego.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ego.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ego.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ego.getSj();
			accWork += ego.getBj();
			acctsColl += ego.getTsbj();
			accRed += ego.getRed();
			accYellow += ego.getYellow();				
			accComplaint  +=ego.getCn();
			accComplaintReply +=ego.getCrn();
			accConsult  +=ego.getCsn();
			accConsultReply  +=ego.getCsrn();
		}
		
		
		if(egover.size()>0){
			float overRate = 0;
			float riseRate = 0;
			overRate = (new Long(accWork).floatValue()/new Long(accCollection).floatValue())* 100;
			riseRate = (new Long(acctsColl).floatValue()/new Long(accCollection).floatValue()) * 100;
			accbjPercent=(float) (Math.round(overRate * 100)) / 100;
			acctsPercent=(float) (Math.round(riseRate * 100)) / 100;
		}
		
		
		
		result.put("accCollectionAll", accCollection);
		result.put("accWorkAll", accWork);
		result.put("accRedAll", accRed);
		result.put("accYellowAll", accYellow);
		result.put("accComplaintAll", accComplaint);
		result.put("accComplaintReplyAll", accComplaintReply);
		result.put("accConsultAll", accConsult);
		result.put("accConsultReplyAll", accConsultReply);
		result.put("accbjPercentAll", accbjPercent);
		result.put("acctsPercentAll", acctsPercent);
		
		String areaNameAll=DaoFactory.create(EgovernmentListBean.class).getSession().selectOne("com.chinacreator.dzjc_todoStatistics.TodoStatisticsMapper.getAreaName", params);
		result.put("areaNameAll", areaNameAll);
		
		return result;
	}

	public Map<String, Object> getInstanceStatisticsListAll(Map<String, String> params) {
		Map<String,Object> result=new HashMap<>();
		
		long accCollection = 0; //累计收件数
		long accWork = 0; //累计办结数
		long acctsColl=0;
		long accRed = 0; //累计红牌
		long accYellow = 0; //累计黄牌
		long accComplaint=0;//累计投诉数
		long accComplaintReply=0;//累计投诉回复数
		long accConsult=0;//累计咨询数
		long accConsultReply=0;//累计咨询回复数
		
		float accbjPercent=0;//办结率
		float acctsPercent=0;//提速率
		
		List<EinstanceListBean> einstance=DaoFactory.create(EinstanceListBean.class).getSession().selectList("com.chinacreator.dzjc_todoStatistics.EinstanceListBeanMapper.getInstanceStatisticsListAll", params);
		for(EinstanceListBean ein:einstance){
			float overRate = 0;
			float riseRate = 0;
			if (0 != ein.getSj()) {
				overRate = (new Long(ein.getBj()).floatValue()/new Long(ein.getSj()).floatValue())* 100;
				riseRate = (new Long(ein.getTsbj()).floatValue()/new Long(ein.getSj()).floatValue()) * 100;
			}else if(0==ein.getSj() && (0!=ein.getBj() || 0!=ein.getTsbj()) ){
				overRate=100;
				riseRate=100;
			}
			ein.setBjPercent((float) (Math.round(overRate * 100)) / 100);
			ein.setTsPercent((float) (Math.round(riseRate * 100)) / 100);
			
			accCollection += ein.getSj();
			accWork += ein.getBj();
			acctsColl += ein.getTsbj();
			accRed += ein.getRed();
			accYellow += ein.getYellow();				
			accComplaint  +=ein.getCn();
			accComplaintReply +=ein.getCrn();
			accConsult  +=ein.getCsn();
			accConsultReply  +=ein.getCsrn();
		}
		
		
		if(einstance.size()>0){
			float overRate = 0;
			float riseRate = 0;
			overRate = (new Long(accWork).floatValue()/new Long(accCollection).floatValue())* 100;
			riseRate = (new Long(acctsColl).floatValue()/new Long(accCollection).floatValue()) * 100;
			accbjPercent=(float) (Math.round(overRate * 100)) / 100;
			acctsPercent=(float) (Math.round(riseRate * 100)) / 100;
		}
		
		
		
		result.put("accCollectionAll", accCollection);
		result.put("accWorkAll", accWork);
		result.put("accRedAll", accRed);
		result.put("accYellowAll", accYellow);
		result.put("accComplaintAll", accComplaint);
		result.put("accComplaintReplyAll", accComplaintReply);
		result.put("accConsultAll", accConsult);
		result.put("accConsultReplyAll", accConsultReply);
		result.put("accbjPercentAll", accbjPercent);
		result.put("acctsPercentAll", acctsPercent);
		return result;
	}


	
	
}
