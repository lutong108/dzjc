package com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.RuleInfoDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAll;

/**
 * @author created by 李浪 2018-3-30 上午10:22:59
 */
public class RuleInfoDaoImpl implements RuleInfoDao {

	/**
	 * 查询所有初始化配设
	 * 
	 * @return
	 */
	public List<EnginecfgBean> getCfgValueList() {

		return DaoFactory
				.create(EnginecfgBean.class)
				.getSession()
				.selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBeanMapper.selectAllEnginecfg");
	}

	@Override
	public List<RuleBaseinfoBeanAll> getRuleBaseinfoBeanAllList(List<JcAllbussinesSum> list1) {
		List<RuleBaseinfoBeanAll> list = new ArrayList<RuleBaseinfoBeanAll>();
		//办件的instanceIdList
		List<String> instanceIdList = new ArrayList<String>();
		//投诉的complainIdList
		List<String> complainIdList = new ArrayList<String>();
		//咨询的consultIdList
		List<String> consultIdList = new ArrayList<String>();
		//特别程序的suspendIdList
		List<String> suspendIdList = new ArrayList<String>();
		//网办的internetIdList
		List<String> internetIdList = new ArrayList<String>();
		//出证的dzzzIdList
		List<String> dzzzIdList = new ArrayList<String>();
		for (JcAllbussinesSum jcAllbussinesSum : list1) {
			String bussinessType = jcAllbussinesSum.getBussinessType();
			String bussinessId = "";
			if("1".equals(bussinessType)){
				bussinessId = jcAllbussinesSum.getBussinessId();
				if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
					instanceIdList.add(bussinessId);
				}
			}else if("2".equals(bussinessType)){
				bussinessId = jcAllbussinesSum.getBussinessId();
				if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
					complainIdList.add(bussinessId);
				}
			}else if("3".equals(bussinessType)){
				bussinessId = jcAllbussinesSum.getBussinessId();
				if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
					consultIdList.add(bussinessId);
				}
			}else if("4".equals(bussinessType)){
				bussinessId = jcAllbussinesSum.getBussinessId();
				if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
					suspendIdList.add(bussinessId);
				}
			}else if("5".equals(bussinessType)){
				bussinessId = jcAllbussinesSum.getBussinessId();
				if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
					internetIdList.add(bussinessId);
				}
			}else if("6".equals(bussinessType)){
				bussinessId = jcAllbussinesSum.getBussinessId();
				if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
					dzzzIdList.add(bussinessId);
				}
			}
		}

		List<RuleBaseinfoBeanAll> beanList = new ArrayList<RuleBaseinfoBeanAll>();
		//查询办件监察信息
		if(instanceIdList.size() > 0 ){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectByBussinessID", instanceIdList);
			list.addAll(beanList);
		}

		//查询咨询监察信息
		if(consultIdList.size() >0 ){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectAllconsultById", consultIdList);
			list.addAll(beanList);
		}

		//查询投诉监察信息
		if(complainIdList.size() > 0){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectAllcomplainById", complainIdList);
			list.addAll(beanList);
		}
		
		//查询特别程序信息
		if(suspendIdList.size() > 0){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectBySuspendID", suspendIdList);
			list.addAll(beanList);
		}
		
		//查询网办信息
		if(internetIdList.size() > 0){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectByInternetID", internetIdList);
			list.addAll(beanList);
		}
		
		//查询出证信息
		if(dzzzIdList.size() > 0){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectByDzzzID", dzzzIdList);
			list.addAll(beanList);
		}
		
		return list;
	}

	@Override
	public List<RuleBaseinfoBeanAll> getRuleBaseinfoBeanConsultList(List<JcAllbussinesSum> list1) {
		List<RuleBaseinfoBeanAll> list = new ArrayList<RuleBaseinfoBeanAll>();
		//咨询的consultIdList
		List<String> consultIdList = new ArrayList<String>();
		for (JcAllbussinesSum jcAllbussinesSum : list1) {
			String bussinessType = jcAllbussinesSum.getBussinessType();
			String bussinessId = "";
			if("3".equals(bussinessType)){
				bussinessId = jcAllbussinesSum.getBussinessId();
				if(bussinessId!=null && !"".equals(bussinessId) && !"null".equals(bussinessId)){
					consultIdList.add(bussinessId);
				}
			}
		}
		List<RuleBaseinfoBeanAll> beanList = new ArrayList<RuleBaseinfoBeanAll>();

		//查询咨询监察信息
		if(consultIdList.size() >0 ){
			beanList = DaoFactory.create(RuleBaseinfoBeanAll.class).getSession().selectList("com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBaseinfoBeanAllMapper.selectAllconsultByIdNew", consultIdList);
			list.addAll(beanList);
		}
		return list;
	}
}
