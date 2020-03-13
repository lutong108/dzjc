package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.dzjc_ruleEngine.RuleElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.RuleElementResultDao;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.dao.impl.RuleElementResultDaoImpl;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.EnginecfgBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.JcAllbussinesSum;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleEngineInitAndTearDownBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineCaculatorServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineInitAndTearDownServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineRunServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleInfoServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RunRuleEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.support.RuleEngineConstants;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.RuleEngineInterfaceReflectUtil;

/**
 * @author created by 李浪 2018-3-30 上午10:36:52
 */
public class RunRuleEngineServiceImpl implements RunRuleEngineServiceIfc {

	@Override
	public Map<String, List<EnginecfgBean>> loadConfigInfo() {

		RuleInfoServiceIfc ruleinfoic = new RuleInfoServiceImpl();

		Map<String, List<EnginecfgBean>> map = null;
		try {
			List<EnginecfgBean> cfgBeanList = ruleinfoic.getCfgValueList();
			if (cfgBeanList != null && cfgBeanList.size() != 0) {

				map = new HashMap<String, List<EnginecfgBean>>();
				map = new HashMap<String, List<EnginecfgBean>>();
				List<EnginecfgBean> initCfgBeanList = new ArrayList<EnginecfgBean>();
				List<EnginecfgBean> endCfgBeanList = new ArrayList<EnginecfgBean>();
				for (EnginecfgBean bean : cfgBeanList) {
					if (RuleEngineConstants.INIT.equalsIgnoreCase(bean
							.getCfgType())) {// 启动时初始化数据
						initCfgBeanList.add(bean);
					} else if (RuleEngineConstants.END.equalsIgnoreCase(bean
							.getCfgType())) {// 完成监察发牌后要处理数据
						endCfgBeanList.add(bean);
					}
				}
				map.put(RuleEngineConstants.INIT, initCfgBeanList);
				map.put(RuleEngineConstants.END, endCfgBeanList);

			}
		} catch (Exception e) {
			System.err.println("初始化配置数据失败");
		}
		return map;
	}

	@Override
	public String ruleInit(List<JcAllbussinesSum> list1,List<EnginecfgBean> list) throws Exception {
		String exceptions = "";
		if (list != null && list.size() != 0) {
			RuleEngineInterfaceReflectUtil reflectUtil = new RuleEngineInterfaceReflectUtil();
			RuleEngineInitAndTearDownBean ruleEngineInitAndTearDownBean = null;
			RuleEngineInitAndTearDownServiceIfc ruleEngineInitAndTearDownServiceIfc = null;
			for (EnginecfgBean enginecfgBean : list) {
				ruleEngineInitAndTearDownBean = new RuleEngineInitAndTearDownBean();
				ruleEngineInitAndTearDownBean.setIsUseTimeLimit(enginecfgBean
						.getIsUsetimelimit());
				try {
					if(StringUtils.isNotBlank(exceptions)){
						exceptions += "#";
					}
					ruleEngineInitAndTearDownServiceIfc = reflectUtil
							.getRuleEngineInitAndTearDownService(enginecfgBean
									.getCfgValue());
					exceptions += ruleEngineInitAndTearDownServiceIfc
							.start(ruleEngineInitAndTearDownBean,list1);

				} catch (Exception e) {
					System.err.println("发牌规则引擎初始化失败," + e.getMessage());
					e.printStackTrace();
					if(StringUtils.isNotBlank(exceptions)){
						exceptions += "#";
					}
					exceptions += e.getMessage();
				}
			}
		}
		return exceptions;
	}

	// 查询监察要素反射执行
	@Override
	public String ruleCaculate(List<JcAllbussinesSum> list, String batchId) throws Exception {
		String exceptions = "";
		RuleElementResultDao elementResultDao = new RuleElementResultDaoImpl();
		try {

			List<RuleElement> elementlist = null;

			//elementResultDao.deleteResultElement(list);// 删除监察要素结果

			elementlist = elementResultDao.findElementInfo();// 加载所有监察要素规则；

			if (elementlist != null && elementlist.size() != 0) {

				RuleEngineInterfaceReflectUtil reflectUtil = new RuleEngineInterfaceReflectUtil();
				RuleEngineCaculatorServiceIfc ruleEngineCaculatorServiceIfc = null;

				for (RuleElement ruleElement : elementlist) {
					if(StringUtils.isNotBlank(exceptions)){
						exceptions += "#";
					}
					ruleEngineCaculatorServiceIfc = reflectUtil
							.getRuleEngineCaculatorService(ruleElement
									.getElemCaculator());
					exceptions += ruleEngineCaculatorServiceIfc.doCaculator(ruleElement
							.getElemId(),batchId,list);
				}
				elementlist = null;
			}
		} catch (Exception e) {
			System.err.println("发牌规则要素执行失败："+e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions)){
				exceptions += "#";
			}
			exceptions += e.getMessage();
		}
		return exceptions;
	}

	// 查询监察要素反射执行
	@Override
	public String ruleCaculateConsult(List<JcAllbussinesSum> list, String batchId) throws Exception {
		String exceptions = "";
		RuleElementResultDao elementResultDao = new RuleElementResultDaoImpl();
		try {
			List<RuleElement> elementlist = null;
			elementlist = elementResultDao.findElementConsult();// 加载咨询监察要素规则；
			if (elementlist != null && elementlist.size() != 0) {
				RuleEngineInterfaceReflectUtil reflectUtil = new RuleEngineInterfaceReflectUtil();
				RuleEngineCaculatorServiceIfc ruleEngineCaculatorServiceIfc = null;
				for (RuleElement ruleElement : elementlist) {
					if(StringUtils.isNotBlank(exceptions)){
						exceptions += "#";
					}
					ruleEngineCaculatorServiceIfc = reflectUtil
							.getRuleEngineCaculatorService(ruleElement
									.getElemCaculator());
					exceptions += ruleEngineCaculatorServiceIfc.doCaculator(ruleElement
							.getElemId(),batchId,list);
				}
				elementlist = null;
			}
		} catch (Exception e) {
			System.err.println("咨询发牌规则要素执行失败："+e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions)){
				exceptions += "#";
			}
			exceptions += e.getMessage();
		}
		return exceptions;
	}
	
	@Override
	public void ruleRun(List<JcAllbussinesSum> list, String feedbackDate) throws Exception {
		
		RuleInfoServiceIfc ruleInfoifc = new RuleInfoServiceImpl();
		//获取规则运行时的准备参数
		RuleWithBusinessBean ruleWithBusinessBean = null;	
		try{
			ruleWithBusinessBean = ruleInfoifc.getRuleWithBusinessBeanList(list);
		}catch(Exception e){
			System.err.println("获取监察规则运行时的准备参数失败,"+e.getMessage());
			e.printStackTrace();
		}
				
		if(ruleWithBusinessBean != null){//判断准备数据是否为空，必须有数据才有意义继续执行
			//获取规则及其对应的业务ID集合
			Map<String,List<String>> ruleBeanAndBusinessIdListMap = ruleWithBusinessBean.getRuleBeanAndBusinessIdList();
			if(ruleBeanAndBusinessIdListMap != null && ruleBeanAndBusinessIdListMap.size() !=0 ){
				try{
					Set<String> ruleidSet =  (Set<String>) ruleBeanAndBusinessIdListMap.keySet();
					Iterator<String> ruleidIt = ruleidSet.iterator();
					RuleBean ruleBean = null;
					RuleEngineRunServiceIfc ruleEngineRunSimpleServiceIfc = null;
					while(ruleidIt.hasNext()){
						String ruleidv = ruleidIt.next();
						ruleBean =  ruleWithBusinessBean.getRuleIdAndRuleBeanMap().get(ruleidv);
						List<String> businessIdList = ruleBeanAndBusinessIdListMap.get(ruleidv);
						if(businessIdList != null && businessIdList.size() != 0){
							//if (RuleEngineConstants.RULE_TYPE_COMPLEX.equalsIgnoreCase(ruleBean.getRuleType())) {// 复杂规则
								//RuleEngineRunServiceIfc ruleEngineRunComplexServiceIfc = new ComplexRuleEngineRunServiceImpl();
								//ruleEngineRunComplexServiceIfc.doRun(ruleBean,businessIdList,ruleWithBusinessBean);
							//} else {//简单规则
								ruleEngineRunSimpleServiceIfc =  new SimpleRuleEngineRunServiceImpl();
								ruleEngineRunSimpleServiceIfc.doRun(ruleBean,businessIdList,ruleWithBusinessBean,feedbackDate);
							//}
						}
					}
				}catch(Exception e){
					System.err.println("监察规则运行失败："+e.getMessage());
					e.printStackTrace();
				}
				finally {
					ruleBeanAndBusinessIdListMap = null;
				}
			}
		}
	}

	@Override
	public void ruleRunConsult(List<JcAllbussinesSum> list, String feedbackDate) throws Exception {
		RuleInfoServiceIfc ruleInfoifc = new RuleInfoServiceImpl();
		//获取规则运行时的准备参数
		RuleWithBusinessBean ruleWithBusinessBean = null;	
		try{
			ruleWithBusinessBean = ruleInfoifc.getRuleWithBusinessConsultList(list);
		}catch(Exception e){
			System.err.println("获取咨询监察规则运行时的准备参数失败,"+e.getMessage());
			e.printStackTrace();
		}
		if(ruleWithBusinessBean != null){//判断准备数据是否为空，必须有数据才有意义继续执行
			//获取规则及其对应的业务ID集合
			Map<String,List<String>> ruleBeanAndBusinessIdListMap = ruleWithBusinessBean.getRuleBeanAndBusinessIdList();
			if(ruleBeanAndBusinessIdListMap != null && ruleBeanAndBusinessIdListMap.size() !=0 ){
				try{
					Set<String> ruleidSet =  (Set<String>) ruleBeanAndBusinessIdListMap.keySet();
					Iterator<String> ruleidIt = ruleidSet.iterator();
					RuleBean ruleBean = null;
					RuleEngineRunServiceIfc ruleEngineRunSimpleServiceIfc = null;
					while(ruleidIt.hasNext()){
						String ruleidv = ruleidIt.next();
						ruleBean =  ruleWithBusinessBean.getRuleIdAndRuleBeanMap().get(ruleidv);
						List<String> businessIdList = ruleBeanAndBusinessIdListMap.get(ruleidv);
						if(businessIdList != null && businessIdList.size() != 0){
							ruleEngineRunSimpleServiceIfc =  new SimpleRuleEngineRunServiceImpl();
							ruleEngineRunSimpleServiceIfc.doRunConsult(ruleBean,businessIdList,ruleWithBusinessBean,feedbackDate);
						}
					}
				}catch(Exception e){
					System.err.println("咨询监察规则运行失败："+e.getMessage());
					e.printStackTrace();
				}
				finally {
					ruleBeanAndBusinessIdListMap = null;
				}
			}
		}
	}
	
	@Override
	public void ruleTearDown(String currentTime, List<EnginecfgBean> list)
			throws Exception {
		if(list != null && list.size() !=0){
			RuleEngineInterfaceReflectUtil reflectUtil = new RuleEngineInterfaceReflectUtil(); 
			for (EnginecfgBean enginecfgBean : list) {
				//1.完成 RuleEngineInitAndTearDownBean 对象的封装 
				RuleEngineInitAndTearDownBean ruleEngineInitAndTearDownBean = new RuleEngineInitAndTearDownBean();
				//ruleEngineInitAndTearDownBean.setAreaCode(areaCode);
				ruleEngineInitAndTearDownBean.setCurrentTime(currentTime);
				ruleEngineInitAndTearDownBean.setIsUseTimeLimit(enginecfgBean.getIsUsetimelimit());
				try{
					//反射出要运行的类然后执行
					RuleEngineInitAndTearDownServiceIfc ruleEngineInitAndTearDownServiceIfc = reflectUtil.getRuleEngineInitAndTearDownService(enginecfgBean.getCfgValue());
					ruleEngineInitAndTearDownServiceIfc.clear(ruleEngineInitAndTearDownBean);
				}catch (Exception e) {
					//LOG.error(e.getMessage());
					//LOG.info("规则引擎后续数据处理过程失败");
					throw e;
				}
			
			}
		}
	}

}
