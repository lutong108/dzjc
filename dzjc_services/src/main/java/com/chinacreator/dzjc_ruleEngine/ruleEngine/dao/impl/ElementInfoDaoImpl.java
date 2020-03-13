package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.ElementInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfo;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.SuperviseInfoBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBean;


/**
 *<p>Title:</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘尹
 *@version 1.0
 *@date 2011-9-1
 */
public class ElementInfoDaoImpl  implements ElementInfoDao {

	

	/**
	 * 修改行政审批办件的所用时间
	 * @param instanceId
	 * @param userTime
	 * @throws Exception
	 */
	public void updUsedTime(String instanceId, String userTime) throws Exception {
		Map<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("instanceId", instanceId);
		queryMap.put("userTime", userTime);
		
		DaoFactory.create(SuperviseInfoBean.class).getSession().update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.updateInstanceUseTime",queryMap);
	}
	
	/**
	 * 批量修改办件的所有时长
	 */
	public void batchUpdateUsedTime(List<InstanceInfo> list) throws Exception {
		DaoFactory.create(SuperviseInfoBean.class).getSession().update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.batchUpdateInstanceUseTime",list);
	}
	
	/**
	 * 批量修改办件所用时长（办结）
	 * @param list
	 * @throws Exception
	 */
	public void batchUpdateCompletedTime(List<InstanceInfo> list) throws Exception {
		DaoFactory.create(InstanceInfo.class).getSession().update("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElementMapper.batchUpdateInstanceCompletedTime",list);
	}

	@Override
	public List<InstanceInfo> getInstanceBegEndTime() {
		return DaoFactory.create(InstanceInfo.class).selectAll();
	}
	
	@Override
	public List<InstanceInfo> getInstanceBegEndTimeByInstanceId(List<String> listInstanceId) {
		return DaoFactory.create(InstanceInfo.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectManyByInstanceId", listInstanceId);
	}

	@Override
	public List<TbcxsqInfoBean> getTbcxsqInfo(String instanceId) {
		TbcxsqInfoBean queryBean = new TbcxsqInfoBean();
		queryBean.setInstanceId(instanceId);
		return DaoFactory.create(TbcxsqInfoBean.class).select(queryBean);
	}

	@Override
	public String getTbcxjgEndDate(String tscxsqId, String instanceId) {
		Map<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("instanceId", instanceId);
		queryMap.put("tscxsqId", tscxsqId);
		String rtn = "";
		rtn = DaoFactory.create(SuperviseInfoBean.class).getSession().selectOne("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBeanMapper.selectendtime",queryMap);
		return rtn;
	}
	
	@Override
	public List<InstanceInfo> getInternetBegEndTimeByInstanceId(List<String> listInstanceId) {
		return DaoFactory.create(InstanceInfo.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectInternetByInstanceId", listInstanceId);
	}
	
	@Override
	public List<InstanceInfo> getDzzzBegEndTimeByInstanceId(List<String> listInstanceId) {
		return DaoFactory.create(InstanceInfo.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.InstanceInfoMapper.selectDzzzByInstanceId", listInstanceId);
	}

	@Override
	public List<TbcxsqInfoBean> queryTbcxsqlist(List<String> listInstanceId) {
		return DaoFactory.create(TbcxsqInfoBean.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBeanMapper.selectBatchList", listInstanceId);
	}
	
	@Override
	public List<TbcxsqInfoBean> queryHuanJieTbcxsqlist(List<String> listHuanJieId) {
		return DaoFactory.create(TbcxsqInfoBean.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.TbcxsqInfoBeanMapper.selectHuanJieList", listHuanJieId);
	}
	
	
	/**
	 * 根据实例ID获取该实例的所用时长
	 * @param instanceId
	 * @return
	 * @throws Exception
	 */
	/*public String getWastingTimeByInstanceId(String instanceId) throws Exception {
		String result = "";
		String sql = "select i.wasting_time from ta_sp_instance i where i.instance_id=? ";
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		preDbUtil.preparedSelect(DBSourceInfo.getDBProperties().getDzjc(),sql);
		preDbUtil.setString(1, instanceId);
		preDbUtil.executePrepared();
		if(preDbUtil!=null && preDbUtil.size()>0){
			result = preDbUtil.getString(0, "wasting_time");
		}
		return result;
	}*/

	/**
	 * 删除办结规则计算
	 * 算法：删除办结的办件是否出具了删除办结原因。
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertDeleteFinishRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,");
			sqlStr.append(" 'true' as rlst,");
			sqlStr.append(" '删除办结没有出具删除办结原因。','");
			sqlStr.append(areaCode);
			sqlStr.append("' from ta_sp_instance i, ta_sp_banjie bj, ta_sp_shouli sl ");
			sqlStr.append(" where i.instance_id = bj.instance_id and i.instance_id = sl.instance_id ");
			sqlStr.append(" and bj.finish_result_no='").append(TransTacheConstants.TACHE_FINISH_DELETE).append("'");
			sqlStr.append(" and bj.untread_cause is null ");
			sqlStr.append(" and exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("sl"));
			sqlStr.append(this.getAcceptTimeEndSQL("sl"));

			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 退回办结规则计算
	 * 算法：退回办结的办件是否出具了退回办结原因。
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertBackFinishRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append(" insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,");
			sqlStr.append(" 'true' as rlst,");
			sqlStr.append(" '退回办结没有出具退回办结原因。','");
			sqlStr.append(areaCode);
			sqlStr.append("' from ta_sp_instance i, ta_sp_banjie bj, ta_sp_shouli sl ");
			sqlStr.append(" where i.instance_id = bj.instance_id and i.instance_id = sl.instance_id ");
			sqlStr.append(" and bj.finish_result_no='").append(TransTacheConstants.TACHE_FINISH_REJECT).append("'");
			sqlStr.append(" and bj.untread_cause is null ");
			sqlStr.append(" and exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("sl"));
			sqlStr.append(this.getAcceptTimeEndSQL("sl"));
			
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 作废办结规则计算
	 * 算法：作废办结的办件是否出具了作废办结原因。
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertNullifyFinishRuleInfo(String elemId,String areaCode) throws Exception {

		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,");
			sqlStr.append(" 'true' as rlst,");
			sqlStr.append(" '作废办结没有出具作废办结原因。','");
			sqlStr.append(areaCode);
			sqlStr.append("' from ta_sp_instance i, ta_sp_banjie bj, ta_sp_shouli sl ");
			sqlStr.append(" where i.instance_id = bj.instance_id and i.instance_id = sl.instance_id ");
			sqlStr.append(" and bj.finish_result_no='").append(TransTacheConstants.TACHE_FINISH_SCRAP).append("'");
			sqlStr.append(" and bj.untread_cause is null ");
			sqlStr.append(" and exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("sl"));
			sqlStr.append(this.getAcceptTimeEndSQL("sl"));

			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 材料完整性规则计算（现不考虑实现）
	 * 算法：将办件材料信息项数目与事项规范材料信息（必交项）比较，如果不相等，表示材料提交不完整。
	 * 只针对正常办结了的办件（一般办结、正常办结、上报办结、转报办结）
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertAttachCompleteRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append(" insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,elem_id,instance_id," );
			sqlStr.append(" case when (attachtj=attachbz) then 'false' else 'true' end,");
			sqlStr.append(" case when (attachtj=attachbz) then '事项规范中材料信息为必交材料的都已提交。'");
			sqlStr.append(" else '材料信息提交不完整，有'||to_char(attachbz-attachtj)||'项必交材料未提交。' end,'");
			sqlStr.append(areaCode);
			sqlStr.append("' from(");
			sqlStr.append(" select '").append(elemId).append("' as elem_id,i.instance_id,");
			sqlStr.append(" (select count(a.id) from ta_sp_attachinstance a where a.instance_id = i.instance_id");
			sqlStr.append(" and a.attach_name in (select b.attach_name from ta_sp_attachinfo b where");
			sqlStr.append(" b.is_integrant = 'Y' and b.approve_id = i.approve_id))  as attachtj,");
			sqlStr.append(" (select count(b.attachinfo_id) from ta_sp_attachinfo b");
			sqlStr.append(" where b.is_integrant = 'Y' and b.approve_id = i.approve_id) as attachbz");
			sqlStr.append(" from ta_count_instance_info i");
			sqlStr.append(" where i.finish_result_no in ");
			sqlStr.append("('").append(TransTacheConstants.TACHE_FINISH_GENERAL).append("',");
			sqlStr.append("'").append(TransTacheConstants.TACHE_FINISH_CERT).append("',");
			sqlStr.append("'").append(TransTacheConstants.TACHE_FINISH_SHIFT).append("',");
			sqlStr.append("'").append(TransTacheConstants.TACHE_FINISH_REPORT).append("')");
			sqlStr.append(" and ").append("SUBSTR(AREA_ORG_CODE,0,12) ='").append(areaCode+"'");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("i"));
			sqlStr.append(this.getAcceptTimeEndSQL("i"));
			sqlStr.append(" )");

			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 预警反馈不及时监察
	 * @param diffTime 
	 * @throws Exception
	 */
	/*public void insertAttachCompleteRuleInfo(String elemId,String businessId,double diffTime,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" values( seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" '").append(businessId).append("',");
			sqlStr.append(" '").append(diffTime).append("',");
			sqlStr.append("'预警反馈超过反馈截止时间。',");
			sqlStr.append("'").append(areaCode).append("')");
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 固定收费监察规则计算（规定不收费收了费）（现不考虑实现）
	 * 算法：
	 * 事项规范为不收费的，结果收了费，计算结果为具体收费值。
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertChargeNToYRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,");
			sqlStr.append(" to_number(nvl(charge_money,0)) as rlst,");
			sqlStr.append(" '事项规范为不收费的结果收了费，收费金额为'||to_number(nvl(charge_money,0))||'元。','");
			sqlStr.append(areaCode);
			sqlStr.append("' from ta_count_instance_info i");
			sqlStr.append(" where is_charge='N'");
			sqlStr.append(" and ").append("SUBSTR(AREA_ORG_CODE,0,12) ='").append(areaCode+"'");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("i"));
			sqlStr.append(this.getAcceptTimeEndSQL("i"));

			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 固定收费监察规则计算（规定收费的没收费）（现不考虑实现）
	 * 算法：
	 * 事项规范为要收费的，办件办结了还没收费，计算结果为true。
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertChargeYToNRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" instance_id,");
			sqlStr.append(" to_number(nvl(charge_money,0)) as rlst,");
			sqlStr.append(" '事项规范为要收费的结果没收费，收费金额为'||to_number(nvl(charge_money,0))||'元。','");
			sqlStr.append(areaCode);
			sqlStr.append("' from ta_count_instance_info i");
			sqlStr.append(" where is_charge='Y'");
			sqlStr.append(" and ").append("SUBSTR(AREA_ORG_CODE,0,12) ='").append(areaCode+"'");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("i"));
			sqlStr.append(this.getAcceptTimeEndSQL("i"));

			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 不予受理信息完整性监察规则计算
	 * 算法：
	 * 如果不予受理办件没有出具不予受理原因，则计算结果为true。
	 * @param elemId 
	 * @throws Exception
	 */
/*	public void insertNotAcceptRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,");
			sqlStr.append(" 'true' as rlst,");
			sqlStr.append(" '不予受理办件没有出具不予受理原因。', '");
			sqlStr.append(areaCode);
			sqlStr.append("' from ta_sp_shouli sl, ta_sp_instance i where sl.instance_id = i.instance_id and sl.is_accept='N'");
			sqlStr.append(" and sl.unaccepted_cause is null and exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%') ");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("sl"));
			sqlStr.append(this.getAcceptTimeEndSQL("sl"));
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 办理时限监察
	 * 算法：
	 * 1、对于在办办件，以系统当前时间减受理时间，计算实际耗时
	 * 2、针对正常办结了的办件（一般办结、正常办结、上报办结、转报办结），以办结时间减去受理时间，计算实际耗时
	 * 3、计算实际耗时时，必须去掉节假日、特别程序时间
	 * @param elemId 
	 * @throws Exception
	 */
/*	public void insertInstanceTimeRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,is_timesupervise,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,'Y',");
			sqlStr.append(" (i.wasting_time-xm.time_limit-nvl(i.tingperiodtime,0)) as rlst,");
			sqlStr.append(" '办理时限超过法定时限，超过'");
			sqlStr.append(" ||to_char((i.wasting_time-xm.time_limit-nvl(i.tingperiodtime,0)))||'天。','");
			sqlStr.append(areaCode);
			sqlStr.append("' from ta_sp_instance i inner join ta_spxm xm on i.approve_id = xm.approve_id ");
			sqlStr.append(" left join ta_sp_banjie bj on i.instance_id = bj.instance_id ");
			sqlStr.append(" left join ta_sp_shouli sl on i.instance_id = sl.instance_id ");
			sqlStr.append(" where (i.processing_state = '1' or (bj.finish_result_no in ('5001','5002','5006','5008') and i.is_supervise = 0)) ");
			sqlStr.append(" and xm.time_limit+nvl(i.tingperiodtime,0) <= i.wasting_time and exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getAcceptTimeBeginSQL("sl"));
			sqlStr.append(this.getAcceptTimeEndSQL("sl"));
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/
	
	/**
	 * 办理时限监察(按承诺时间计算)
	 * 算法：
	 * 1、对于在办办件，以系统当前时间减受理时间，计算实际耗时
	 * 2、针对正常办结了的办件（一般办结、正常办结、上报办结、转报办结），以办结时间减去受理时间，计算实际耗时
	 * 3、计算实际耗时时，必须去掉节假日、特别程序时间
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertProcessTimeRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,is_timesupervise,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,'Y',");
			sqlStr.append(" (i.wasting_time-nvl(i.promises_limit,0)-nvl(i.tingperiodtime,0)) as rlst,");
			sqlStr.append(" '办理时限超过承诺时限，超过'");
			sqlStr.append(" ||to_char((i.wasting_time-nvl(i.promises_limit,0)-nvl(i.tingperiodtime,0)))||'天。','");
			sqlStr.append(areaCode);
			sqlStr.append("' from va_sp_processtime i");
			sqlStr.append(" where i.wasting_time - nvl(i.promises_limit,0) - nvl(i.tingperiodtime,0) >= -2 ");
			sqlStr.append(" and exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("i"));
			sqlStr.append(this.getAcceptTimeEndSQL("i"));
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 特殊程序时限监察规则计算
	 * 算法：
	 * 如果特别程序流程实际办理时长超过了时限，则计算结果为true。
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertSpecialLimitRuleInfo(String elemId,String businessId,double dateInv,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
				sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code) ");
				sqlStr.append("values (seq_rule_elem_result.nextval,'").append(elemId).append("','");
				sqlStr.append(businessId).append("','").append(dateInv).append("','");
				sqlStr.append("特别程序流程实际办理时长超过了时限，超过").append(dateInv).append("天。','");
				sqlStr.append(areaCode).append("')");
				*//**
				sqlStr.append(" select seq_rule_elem_result.nextval, '").append(elemId).append("',");
				sqlStr.append(" i.instance_id||'-'||sq.tscxsq_id as business_id,");
				sqlStr.append(" '").append(dateInv).append("',");
				sqlStr.append("'").append("特别程序流程实际办理时长超过了时限，超过").append(dateInv).append("天。'").append(",");
				sqlStr.append(" '").append(areaCode).append("' ");
				sqlStr.append(" from TA_SP_TSCXSQ sq");
				sqlStr.append(" left join TA_SP_TSCXJG jg on sq.tscxsq_id = jg.tscxsq_id");
				sqlStr.append(" inner join ta_sp_instance i on sq.instance_id = i.instance_id");
				sqlStr.append(" inner join ta_sp_shouli s on i.instance_id=s.instance_id");
				sqlStr.append(" where i.instance_id = '").append(businessId.substring(0, businessId.indexOf("-"))).append("' ");
				sqlStr.append(this.getBJInstanceSQL("i"));
				sqlStr.append(this.getAcceptTimeBeginSQL("s"));
				sqlStr.append(this.getAcceptTimeEndSQL("s"));
				*//*
				preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
				preDbUtil.executePrepared();
			
			*//**
			sqlStr.append(" values( seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" '").append(businessId).append("',");
			sqlStr.append(" '").append(dateInv).append("',");
			if(dateInv>0){
				sqlStr.append("'").append("特别程序流程实际办理时长超过了时限，超过").append(dateInv).append("天。'").append(",");
			}else{
				sqlStr.append("'特别程序流程实际办理时长没有超过时限。',");
			}
			sqlStr.append(" '").append(areaCode).append("')");
			*//*
			
			
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 特别程序结果是否早于特别程序申请逻辑性监察规则计算（现不考虑实现）
	 * 算法：特别程序结果是否早于特别程序申请
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertSpecialLogicRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id||'$$'||sq.tscxsq_id as business_id,");
			sqlStr.append(" case when sq.start_date>=jg.end_date then 'true' else 'false' end as rlst,");
			sqlStr.append(" case when sq.start_date>=jg.end_date then '特别程序结果早于特别程序申请。'");
			sqlStr.append(" else '特别程序结果晚于特别程序申请。' end,'");
			sqlStr.append(areaCode);
			sqlStr.append("' from TA_SP_TSCXSQ sq");
			sqlStr.append(" inner join TA_SP_TSCXJG jg on sq.tscxsq_id = jg.tscxsq_id");
			sqlStr.append(" inner join ta_sp_instance i on sq.instance_id = i.instance_id");
			sqlStr.append(" inner join ta_sp_shouli s on i.instance_id=s.instance_id");
			sqlStr.append(" where exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("s"));
			sqlStr.append(this.getAcceptTimeEndSQL("s"));

			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 补交告知环节完整性监察规则计算（现不考虑实现）
	 * 算法：
	 * 如果补交告知超过n次，则计算结果为true。
	 * n为配置数据
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertSupplyIntegralRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" instance_id,num,");
			sqlStr.append(" '补交告知有'||num||'次。',area_code from(");
			sqlStr.append(" select t.instance_id,count(t.bjgz_id) as num,");
			sqlStr.append("'").append(areaCode).append("' as area_code");
			sqlStr.append(" from ta_sp_bujiaogaozhi t");
			sqlStr.append(" inner join ta_sp_instance i on t.instance_id=i.instance_id");
			sqlStr.append(" inner join ta_sp_shouli s on i.instance_id=s.instance_id");
			sqlStr.append(" where exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("s"));
			sqlStr.append(this.getAcceptTimeEndSQL("s"));
			sqlStr.append(" group by t.instance_id)");
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 补交受理时间逻辑性监察规则计算
	 * 算法：比较补交受理时间与补交告知时间：如果补交受理时间小于补交告知时间，则计算结果为true。
	 * @param elemId 
	 * @throws Exception
	 */
/*	public void insertSupplyTimeLogicRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,");
			sqlStr.append(" 'true' as rlst,");
			sqlStr.append(" '补交受理时间小于补交告知时间。',");
			sqlStr.append("'").append(areaCode).append("'");
			sqlStr.append(" from ta_sp_bujiaoshouli sl");
			sqlStr.append(" inner join ta_sp_bujiaogaozhi gz on sl.instance_id=gz.instance_id");
			sqlStr.append(" inner join ta_sp_instance i on sl.instance_id=i.instance_id");
			sqlStr.append(" inner join ta_sp_shouli s on i.instance_id=s.instance_id");
			sqlStr.append(" where sl.accept_time<gz.apprize_time and exists (select org_id from td_sm_organization o where i.org_id=o.ORG_ID and remark1 like '").append(areaCode+"%')");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("s"));
			sqlStr.append(this.getAcceptTimeEndSQL("s"));
			
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/
	
	/**
	 * 受理办结时间逻辑性监察规则计算
	 * 算法：比较受理时间与办结时间：如果受理时间大于办结时间，则计算结果为true。
	 * @param elemId 
	 * @throws Exception
	 */
/*	public void insertFinishTimeLogicRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result");
			sqlStr.append(" (rlt_id, elem_id, business_id, rlt_value, rlt_memo, area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" i.instance_id,");
			sqlStr.append(" case when s.accept_time > bj.finish_time then 'true' else 'false' end as rlst,");
			sqlStr.append(" case when s.accept_time > bj.finish_time then '受理时间大于办结时间。' else '受理时间小于办结时间。' end,");
			sqlStr.append(" '").append(areaCode).append("'");     
			sqlStr.append(" from ta_sp_banjie bj");
			sqlStr.append(" inner join ta_sp_instance i on bj.instance_id = i.instance_id");
			sqlStr.append(" inner join ta_sp_shouli s on i.instance_id = s.instance_id");
			sqlStr.append(" where exists (select org_id from td_sm_organization o where i.org_id = o.ORG_ID and remark1 like '");
			sqlStr.append(areaCode+"%')");
			sqlStr.append(" and i.is_supervise = '0'");
			sqlStr.append(" and bj.finish_time < s.accept_time ");
			
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 环节完整性监察计算（现不考虑实现）
	 * 算法：
	 * 如果正常办结的办件环节数与事项定义不相符，则计算结果为true。
	 * 只针对正常办结了的办件（一般办结、正常办结、上报办结、转报办结）
	 * @param ruleId 
	 * @throws Exception
	 */
	/*public void insertTacheIntegralRuleInfo(String elemId,String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		//FIXME yin.liu  通过直接取审批的环节信息数据处理
		//经与审批沟通，事项环节定义是有表可以取到数据（ta_sp_wfaction 目前查看数据有点乱，审批必须确保环节存入具体编码）；
		//但实例具体的环节信息（对应的是我们这边的环节历史表）只有到审批的意见表（ta_sp_tasklist）取，可里面对应的环节编码不是其具体编码，是五大环节的基础编码。如审查0801[其五大环节编码是08]，审核08.
		//**具体还要讨论。
		
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',instance_id,");
			sqlStr.append(" case when rlsttj=rlstbz then 'false' else 'true' end ,");
			sqlStr.append(" case when rlsttj=rlstbz then '正常办结的办件环节数与事项定义相符。'");
			sqlStr.append(" else '正常办结的办件环节数与事项定义有'||to_char(rlstbz-rlsttj)||'项不相符。' end, ");
			sqlStr.append("'").append(areaCode).append("'");
			sqlStr.append(" from (select i.instance_id,");
			sqlStr.append(" (select count(h.id) from ta_tache_history h where h.instance_id=i.instance_id");
			sqlStr.append(" and h.tache_no in (select tache_code from ta_dic_trans_tache t ");
			sqlStr.append(" where (parent_tache_code <6000 or tache_code <6000)))as rlsttj , ");
			sqlStr.append(" (select count(t.tacheinfo_id) from ta_sp_tacheinfo t where t.approve_id=i.approve_id) rlstbz, ");
			sqlStr.append("'").append(areaCode).append("' as area_code");
			sqlStr.append(" from ta_count_instance_info i");
			sqlStr.append(" where finish_result_no in('").append(TransTacheConstants.TACHE_FINISH_GENERAL).append("',");
			sqlStr.append("'").append(TransTacheConstants.TACHE_FINISH_CERT).append("',");
			sqlStr.append("'").append(TransTacheConstants.TACHE_FINISH_SHIFT).append("',");
			sqlStr.append("'").append(TransTacheConstants.TACHE_FINISH_REPORT).append("')");
			sqlStr.append(" and ").append("SUBSTR(AREA_ORG_CODE,0,12)  ='").append(areaCode+"'");
			sqlStr.append(this.getBJInstanceSQL("i"));
			sqlStr.append(this.getAcceptTimeBeginSQL("i"));
			sqlStr.append(this.getAcceptTimeEndSQL("i"));
			sqlStr.append(" )");
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/

	/**
	 * 环节时限逻辑监察（现不考虑实现）
	 * 算法：
	 * 1、对于在办办件，以系统当前时间减受理时间，计算实际耗时
	 * 2、针对正常办结了的办件（一般办结、正常办结、上报办结、转报办结），以办结时间减去受理时间，计算实际耗时
	 * 3、计算实际耗时时，必须去掉节假日、特别程序时间
	 * @param elemId 
	 * @throws Exception
	 */
	/*public void insertTacheTimeLogicRuleInfo(String elemId,String nBusinessId,String resBoolean, String areaCode) throws Exception {
		PreparedDBUtil preDbUtil = new PreparedDBUtil();
		StringBuffer sqlStr = new StringBuffer();
		try {
			sqlStr.append("insert into ta_rule_elem_result (rlt_id,elem_id,business_id,rlt_value,rlt_memo,area_code)");
			sqlStr.append(" select seq_rule_elem_result.nextval,'").append(elemId).append("',");
			sqlStr.append(" '").append(nBusinessId).append("',");
			sqlStr.append(" '").append(resBoolean).append("',");
			if("".equals(resBoolean)) {
				sqlStr.append(" '").append("后一环节时限早于前一环节时限。").append("',");
			} else {
				sqlStr.append(" '").append("后一环节时限晚于前一环节时限。").append("',");
			}
			sqlStr.append(" '").append(areaCode).append("'");
			sqlStr.append(" from dual");
			preDbUtil.preparedInsert(DBSourceInfo.getDBProperties().getDzjc(),sqlStr.toString());
			preDbUtil.executePrepared();
		} catch(Exception e) {
			//LOG.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}*/
	
}
