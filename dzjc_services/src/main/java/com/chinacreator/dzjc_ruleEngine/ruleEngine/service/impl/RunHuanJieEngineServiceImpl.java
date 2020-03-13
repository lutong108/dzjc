package com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ResultOpinion;
import com.chinacreator.dzjc_ruleEngine.RuleElement;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.RuleWithBusinessBean;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.HuanJieEngineCaculatorServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.HuanJieInfoServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RuleEngineRunServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.RunHuanJieEngineServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.utils.RuleEngineInterfaceReflectUtil;

/**
 * @author created by liaolong 2019-1-14 
 */
public class RunHuanJieEngineServiceImpl implements RunHuanJieEngineServiceIfc {


	// 查询监察要素反射执行
	@Override
	public String ruleCaculate(List<ResultOpinion> list, String batchId) throws Exception {
		String exceptions = "";
		try {
			//查询环节的监察要素
			List<RuleElement> elementlist = DaoFactory.create(RuleElement.class)
					.getSession().selectList("com.chinacreator.dzjc_ruleEngine.RuleElementMapper.selectHuanJie");

			if (elementlist != null && elementlist.size() != 0) {

				RuleEngineInterfaceReflectUtil reflectUtil = new RuleEngineInterfaceReflectUtil();
				HuanJieEngineCaculatorServiceIfc huanJieEngineCaculatorServiceIfc = null;

				for (RuleElement ruleElement : elementlist) {
					if(StringUtils.isNotBlank(exceptions)){
						exceptions += "#";
					}
					huanJieEngineCaculatorServiceIfc = reflectUtil
							.getHuanJieEngineCaculatorService(ruleElement
									.getElemCaculator());
					exceptions += huanJieEngineCaculatorServiceIfc.doCaculator(ruleElement
							.getElemId(),batchId,list);
				}
				elementlist = null;
			}
		} catch (Exception e) {
			System.err.println("环节发牌规则要素执行失败："+e.getMessage());
			e.printStackTrace();
			if(StringUtils.isNotBlank(exceptions)){
				exceptions += "#";
			}
			exceptions += e.getMessage();
		}
		return exceptions;
	}

	@Override
	public void ruleRun(List<ResultOpinion> list,ServletContext application) throws Exception {
		
		HuanJieInfoServiceIfc ruleInfoifc = new HuanJieInfoServiceImpl();
		//获取规则运行时的准备参数
		RuleWithBusinessBean ruleWithBusinessBean = null;	
		try{
			ruleWithBusinessBean = ruleInfoifc.getRuleWithBusinessBeanList(list);
		}catch(Exception e){
			//LOG.error(e.getMessage());
			//LOG.info("获取规则运行时的准备参数失败");
			System.err.println("获取环节监察规则运行时的准备参数失败："+e.getMessage());
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
							ruleEngineRunSimpleServiceIfc =  new SimpleHuanJieEngineRunServiceImpl();
							ruleEngineRunSimpleServiceIfc.doRun2(ruleBean,businessIdList,ruleWithBusinessBean,application);
						}
					}
				}catch(Exception e){
					System.err.println("环节监察发牌处理失败："+e.getMessage());
					e.printStackTrace();
				}
				finally {
					ruleBeanAndBusinessIdListMap = null;
				}
			}
			
		}
		
	}

}
