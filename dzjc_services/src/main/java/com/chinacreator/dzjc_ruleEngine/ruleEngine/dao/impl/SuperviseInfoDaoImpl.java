package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.BussinessCom;
import com.chinacreator.dzjc_ruleEngine.BussinessCon;
import com.chinacreator.dzjc_ruleEngine.BussinessIns;
import com.chinacreator.dzjc_ruleEngine.SuperviseInfo;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.SuperviseInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseDefinitionBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.support.RuleConstants;

/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-4
 */
public class SuperviseInfoDaoImpl implements SuperviseInfoDao {

	//private static final Logger LOG = Logger.getLogger(SuperviseInfoDaoImpl.class.getName());
	
	/**
	 * 保存监察信息
	 * @param businessId
	 * @param ruleBean
	 * @throws Exception
	 */
	public SuperviserInfo insertSuperviseInfo(String businessId, String orgId, Map<String,Object> logMap, RuleBean ruleBean, SuperviseDefinitionBean superviseDefinitionBean) throws Exception {
		try {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//去掉特殊程序结果监察时(因为特殊程序可以多次，为了更好的判断同一业务同一规则只能监察1次)拼接的环节编码
			String tscxCode = "";//存储特殊程序的业务ID实例形式
			if(businessId.indexOf("$$") != -1){//update by yin.liu 20110829 
				tscxCode = businessId.substring(businessId.indexOf("$$")+2, businessId.length());
				businessId = businessId.substring(0,businessId.indexOf("$$"));
			}
			else if(businessId.indexOf("##") != -1){//update by yin.liu 20110829 
				businessId = businessId.substring(0,businessId.indexOf("##"));
			}
			else if(businessId.indexOf("@@") != -1){//update by yin.liu 20110829 
				businessId = businessId.substring(0,businessId.indexOf("@@"));
			}
			String superviseId = UUID.randomUUID().toString();
			SuperviserInfo superviserInfoBean = new SuperviserInfo();
			superviserInfoBean.setSuperviseInfoId(superviseId);
			superviserInfoBean.setBusinessId(businessId);
			//superviserInfoBean.setOrgId(getOrgIdAndOrgName(businessId,superviseDefinitionBean.getSupervise_type_code()));
			superviserInfoBean.setOrgId(orgId);
			superviserInfoBean.setSuperviseTypeNo(superviseDefinitionBean.getSupervise_type_code());
			superviserInfoBean.setSuperviseResultNo(superviseDefinitionBean.getSupervise_level_code());
			superviserInfoBean.setSuperviseTime(ft.format(new Date()));
			superviserInfoBean.setSuperviseDescript(ruleBean.getRuleName());
			superviserInfoBean.setSuperviseGist(ruleBean.getRuleName());
			superviserInfoBean.setWarningContent(superviseDefinitionBean.getMsg_template_id());
			superviserInfoBean.setRuleId(ruleBean.getRuleId());
			superviserInfoBean.setRuleVersion(ruleBean.getRuleVersion());
			superviserInfoBean.setTscxCode(tscxCode);
			
			if(logMap != null){
				superviserInfoBean.setFeedbackEndtime(logMap.get("feedbackDate") != null ? (String)logMap.get("feedbackDate") : null);
				superviserInfoBean.setStartTime(logMap.get("startTime") != null ? (java.sql.Date)logMap.get("startTime") : null);
				superviserInfoBean.setEndTime(logMap.get("endTime") != null ? (java.sql.Date)logMap.get("endTime") : null);
				superviserInfoBean.setStatus(logMap.get("state") != null ? (String)logMap.get("state") : null);
				superviserInfoBean.setPromiseLimit(logMap.get("promiseLimit") != null ? (Double)logMap.get("promiseLimit") : null);
				superviserInfoBean.setProcessLimit(logMap.get("processLimit") != null ? (Double)logMap.get("processLimit") : null);
				superviserInfoBean.setExpireDate(logMap.get("expireDate") != null ? (java.sql.Date)logMap.get("expireDate") : null);
				superviserInfoBean.setOutDays(logMap.get("outDays") != null ? (String)logMap.get("outDays") : null);
				superviserInfoBean.setTbcxTimes(logMap.get("tbcxTimes") != null ? (String)logMap.get("tbcxTimes") : null);
				superviserInfoBean.setBatchId(logMap.get("batchId") != null ? (String)logMap.get("batchId") : null);
			}
			
			if(RuleConstants.SENDCARD_TYPE_AUTO.equals(superviseDefinitionBean.getSendcard_type())){
				superviserInfoBean.setState("Y");
			}else{
				superviserInfoBean.setState("D");
			}
			//DaoFactory.create(SuperviserInfo.class).insert(superviserInfoBean);
			/*
			PreparedDBUtil preDbUtil = new PreparedDBUtil();
			StringBuffer sqlStr = new StringBuffer();
			try {
				sqlStr.append("insert into ta_supervise_info(supervise_info_id,");
				sqlStr.append("business_id,tscx_code,org_id,supervise_type_no,supervise_result_no,");
				sqlStr.append("supervise_time,supervise_descript,supervise_gist,warning_content,rule_id,");
				sqlStr.append("feedback_endtime,rule_version,state ");
				sqlStr.append(")");
				sqlStr.append(" values (").append(superviseId).append(",");
				sqlStr.append("'").append(businessId).append("',");
				sqlStr.append("'").append(tscxCode).append("',");
				sqlStr.append("'").append(getOrgIdAndOrgName(businessId)).append("',");
				sqlStr.append("'").append(superviseDefinitionBean.getSupervise_type_code()).append("',");
				sqlStr.append("'").append(superviseDefinitionBean.getSupervise_level_code()).append("',");
				sqlStr.append("sysdate,");
				sqlStr.append("'").append(ruleBean.getRule_name()).append("',");
				sqlStr.append("'").append(ruleBean.getRule_name()).append("',");
				sqlStr.append("'").append(superviseDefinitionBean.getMsg_template_id()).append("',");
				sqlStr.append("'").append(ruleBean.getRule_id()).append("',");
				sqlStr.append("to_date('");
				sqlStr.append(HolidayDateUtil.getDate(30));
				sqlStr.append("','yyyy-mm-dd hh24:mi:ss'),");
				sqlStr.append("'").append(ruleBean.getRule_version()).append("',");
				if(RuleConstants.SENDCARD_TYPE_AUTO.equals(superviseDefinitionBean.getSendcard_type())){
					sqlStr.append("'Y'");
				}else{
					sqlStr.append("'D'");
				}
				sqlStr.append(")");

				preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
				preDbUtil.executePrepared();
			} catch(Exception e) {
				//LOG.error(e.getMessage());
				throw new Exception(e.getMessage());
			}
			*/
			return superviserInfoBean;
		} catch (Exception e) {
			System.err.println("监察规则在封装发牌数据时异常："+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 保存快照信息
	 * @param businessId
	 * @param superviseDefinitionBean
	 * @throws Exception
	 */
	public void insertKuaiZhao(String businessId, String superviseId, SuperviseDefinitionBean superviseDefinitionBean) throws Exception {
		/*String instance_id = "";
		String tscxsq_id = "";
		if(businessId.indexOf("$$") != -1){
			instance_id = businessId.substring(0,businessId.indexOf("$$"));
			tscxsq_id = businessId.substring(businessId.indexOf("$$")+2,businessId.length());
		}else{
			instance_id = businessId;
		}*/
		/*
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		try{ 
				StringBuffer sqlStr = new StringBuffer();
				sqlStr.append("insert into ta_sp_kuaizhao z ");
				sqlStr.append("(z.id, z.instance_id, z.SUPERVISE_ID, STATE, z.accept_time, z.finish_time,"); 
				sqlStr.append(" z.item_limit,z.promises_limit,z.unaccepted_cause,z.untread_cause,z.time_limit,z.start_date,z.end_date,z.bjgz_apprize_time,z.bjsl_accept_time) "); 
				//如果特别程序申请ID为空
				if(StringUtil.isBlank(tscxsq_id)){
					sqlStr.append(" select seq_sp_kuaizhao.nextval, ");
					sqlStr.append("          ?, ");
					sqlStr.append("          ?, ");
					sqlStr.append("          decode(i.processing_state,1,'0',2,'1'), ");
					sqlStr.append("          s.accept_time, ");
					sqlStr.append("          b.finish_time, ");
					sqlStr.append("          xm.time_limit, ");
					sqlStr.append("          xm.promises_limit, ");
					sqlStr.append("          s.unaccepted_cause, ");
					sqlStr.append("          b.untread_cause, ");
					sqlStr.append("          '', ");
					sqlStr.append("          '', ");
					sqlStr.append("          '', ");
					sqlStr.append("          bjgz.apprize_time, ");
					sqlStr.append("          bjsl.accept_time ");
					sqlStr.append("     from  ta_spxm xm  ");
					sqlStr.append("     inner join ta_sp_instance i on xm.approve_id = i.approve_id ");
					sqlStr.append("     left join ta_sp_shouli s on i.instance_id = s.instance_id ");
					sqlStr.append("     left join ta_sp_banjie b on s.instance_id = b.instance_id "); 
					sqlStr.append("     left join ta_sp_bujiaogaozhi bjgz on i.instance_id = bjgz.instance_id "); 
					sqlStr.append("     left join ta_sp_bujiaoshouli bjsl on i.instance_id = bjsl.instance_id "); 
					sqlStr.append("     where s.instance_id = ? "); 
				}else{
					sqlStr.append(" select seq_sp_kuaizhao.nextval, ");
					sqlStr.append("          ?, ");
					sqlStr.append("          ?, ");
					sqlStr.append("          decode(i.processing_state,1,'0',2,'1'), ");
					sqlStr.append("          s.accept_time, ");
					sqlStr.append("          b.finish_time, ");
					sqlStr.append("          xm.time_limit, ");
					sqlStr.append("          xm.promises_limit, ");
					sqlStr.append("          s.unaccepted_cause, ");
					sqlStr.append("          b.untread_cause, ");
					sqlStr.append("          q.time_limit, ");
					sqlStr.append("          q.start_date, ");
					sqlStr.append("          j.end_date, ");
					sqlStr.append("          bjgz.apprize_time, ");
					sqlStr.append("          bjsl.accept_time ");
					sqlStr.append("     from  ta_spxm xm  ");
					sqlStr.append("     inner join ta_sp_instance i on xm.approve_id = i.approve_id ");
					sqlStr.append("     left join ta_sp_shouli s on i.instance_id = s.instance_id ");
					sqlStr.append("     left join ta_sp_banjie b on s.instance_id = b.instance_id ");
					sqlStr.append("     left join ta_sp_tscxsq q on i.instance_id = q.instance_id  ");
					sqlStr.append("     left join ta_sp_tscxjg j on q.tscxsq_id = j.tscxsq_id ");
					sqlStr.append("     left join ta_sp_bujiaogaozhi bjgz on i.instance_id = bjgz.instance_id "); 
					sqlStr.append("     left join ta_sp_bujiaoshouli bjsl on i.instance_id = bjsl.instance_id "); 
					sqlStr.append("     where s.instance_id = ? "); 
					sqlStr.append("     and q.tscxsq_id = '").append(tscxsq_id.trim()).append("' "); 
				} 
				preDbUtil = new PreparedDBUtil();
				preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
				preDbUtil.setString(1, instance_id);
				preDbUtil.setString(2, superviseId);
				preDbUtil.setString(3, instance_id);
				preDbUtil.executePrepared();
			 
			  
		}catch (Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		*/
	}
	
	/**
	 * 更新快照办结时间信息
	 * @param 
	 * @throws Exception
	 */
	public void updateKuaiZhao() throws Exception {
		/*
		
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		PreparedDBUtil db = new PreparedDBUtil();
		PreparedDBUtil updateDb = new PreparedDBUtil();
		try{
			StringBuffer sql = new StringBuffer();
			StringBuffer sql1 = new StringBuffer();
			StringBuffer sql2 = new StringBuffer();
			sql.append("select * from ta_sp_kuaizhao k where k.finish_time is null");
			sql1.append("select b.finish_time from ta_sp_banjie b where b.instance_id = ?");
			sql2.append("update ta_sp_kuaizhao set finish_time = ? where instance_id = ?");
			preDbUtil.executeSelect(DBSourceInfo.getDBProperties().getDzjc(),sql.toString());
			if(preDbUtil.size() > 0){
				for(int n=0; n < preDbUtil.size(); n++){
					db = new PreparedDBUtil();
					db.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sql1.toString());
					db.setString(1, preDbUtil.getString(n, "instance_id"));
					db.executePrepared();
					if(db.size() > 0){
						updateDb = new PreparedDBUtil();
						updateDb.preparedUpdate(DBSourceInfo.getDBProperties().getDzjc(),sql2.toString());
						updateDb.setDate(1, db.getDate(0, "finish_time"));
						updateDb.setString(2, preDbUtil.getString(n, "instance_id"));
						updateDb.executePrepared();
					}
				}
			}
		}catch (Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		*/
	}
	
	/**
	 * 保存消息内容
	 * @param superviseId
	 * @param msgContent
	 * @throws Exception
	 */
	public SuperviseInfo saveMsgContent(String superviseId, String msgContent) throws Exception {
		SuperviseInfo superviseInfoBean = new SuperviseInfo();
		superviseInfoBean.setSuperviseInfoId(superviseId);
		superviseInfoBean.setWarningContent(msgContent);
		return superviseInfoBean;
		//DaoFactory.create(SuperviseInfo.class).getSession().update("com.chinacreator.dzjc_ruleEngine.SuperviserInfoMapper.update", superviseInfoBean);
		
		/*
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("update ta_supervise_info s");
			sqlStr.append(" set s.warning_content=?");
			sqlStr.append(" where s.supervise_info_id=?");
			preDbUtil.preparedUpdate(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.setString(1, msgContent);
			preDbUtil.setString(2, superviseId);
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		*/
	}

	/**
	 * 同一条业务，同一条规则，同一版本，不可监察2次
	 * @param businessId
	 * @param ruleId
	 * @param ruleVersion
	 * @return
	 */
	public boolean isSuperviseBefore(String businessId, String ruleId,String superviseTypeNo) throws Exception {
		boolean isSupervise = true;
		SuperviserInfo superviseInfoBean = new SuperviserInfo();
		if(businessId.indexOf("$$") != -1){
			superviseInfoBean.setBusinessId(businessId.substring(0, businessId.indexOf("$$")));
		}
		else if(businessId.indexOf("##") != -1){
			superviseInfoBean.setBusinessId(businessId.substring(0, businessId.indexOf("##")));
		}
		else if(businessId.indexOf("@@") != -1){
			superviseInfoBean.setBusinessId(businessId.substring(0, businessId.indexOf("@@")));
		}
		else {
			superviseInfoBean.setBusinessId(businessId);
		}
		superviseInfoBean.setRuleId(ruleId);
		superviseInfoBean.setSuperviseTypeNo(superviseTypeNo);
		//superviseInfoBean.setRuleVersion(ruleVersion);
		List<SuperviserInfo> superviseInfoList = DaoFactory.create(SuperviseInfo.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.SuperviserInfoMapper.select", superviseInfoBean);
		if(superviseInfoList!=null && superviseInfoList.size() > 0){
			isSupervise = false;
		}
		/*
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("select count(supervise_info_id) from ta_supervise_info s");
			sqlStr.append(" where decode(s.tscx_code,null,s.business_id,s.tscx_code) = ? ");//修改ta_supervise_info的业务ID存储方式后完善代码update by yin.liu 20110829
			sqlStr.append(" and s.rule_id=? ");
			sqlStr.append(" and s.rule_version=? ");
			preDbUtil.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.setString(1, businessId);
			preDbUtil.setString(2, ruleId);
			preDbUtil.setString(3, ruleVersion);
			preDbUtil.executePrepared();

			if(preDbUtil!=null && preDbUtil.size()>0){
				if(preDbUtil.getInt(0,0)==0){
					isSupervise =true;
				}
			}
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		*/
		return isSupervise;
	} 
	
	/**  
	 * 得到单位代码和单位名称
	 * @param businessId
	 * @param superviseTypeCode
	 * @throws Exception
	 */
	public String getOrgIdAndOrgName(String businessId,String superviseTypeCode) throws Exception {
		String orgCode = "";
		
		if(businessId.indexOf("$$") != -1)
		{
			businessId = businessId.substring(0,businessId.indexOf("$$"));
		}
		try {
			if("1011".equals(superviseTypeCode) || "1014".equals(superviseTypeCode)){
				BussinessIns bussinessIns=DaoFactory.create(BussinessIns.class).getSession().selectOne("com.chinacreator.dzjc_ruleEngine.BussinessInsMapper.selectByID",businessId);
				orgCode=bussinessIns.getOrgId();
			}else if("1012".equals(superviseTypeCode)){
				BussinessCom bussinessCom=DaoFactory.create(BussinessCom.class).getSession().selectOne("com.chinacreator.dzjc_ruleEngine.BussinessComMapper.selectByID",businessId);
				orgCode=bussinessCom.getAcceptOrgId();
			}else if("1013".equals(superviseTypeCode) || "1015".equals(superviseTypeCode)){
				BussinessCon bussinessCon=DaoFactory.create(BussinessCon.class).getSession().selectOne("com.chinacreator.dzjc_ruleEngine.BussinessConMapper.selectByID",businessId);
				orgCode=bussinessCon.getOrgId();
			}
			
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return orgCode;
	}
	
	/**
	 * 取得序列的下一个值
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public String getSeqNextVal(String seqName) throws Exception {
		StringBuffer sqlStr = new StringBuffer();
		String seqNexVal = "";
		sqlStr.append("select ").append(seqName).append(".nextval from dual");
		/*
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		preDbUtil.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
		preDbUtil.executePrepared();
		if(preDbUtil!=null && preDbUtil.size()>0){
			seqNexVal = preDbUtil.getString(0, 0);
		}
		*/
		return seqNexVal;
	}

	/**  
	 * 判断规则引擎执行当天区域是否完成了交换
	 * @param area_code
	 * @throws Exception
	 */
	public boolean isChangeDate(String area_code) throws Exception {
		boolean flag = false;
		//StringBuffer sql1 = new StringBuffer();
		//StringBuffer sql2 = new StringBuffer();
		/*
		try {
			sql1.append("select * from ta_sp_date_time t where t.area_code=? ");
			sql2.append("select * from ta_sp_date_time t where t.area_code=? and to_char(t.change_time,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd')");
			
			PreparedDBUtil db = new PreparedDBUtil();
			db.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sql1.toString());
			db.setString(1, area_code);
			db.executePrepared();
			if(db.size() > 0){
				db = new PreparedDBUtil();
				db.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sql2.toString());
				db.setString(1, area_code);
				db.executePrepared();
				if(db.size() > 0){
					flag = true;
				}
			}else{
				flag = true;
			}
		}catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		*/
		return flag;
	}

}
