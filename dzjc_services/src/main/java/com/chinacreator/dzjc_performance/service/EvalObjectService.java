package com.chinacreator.dzjc_performance.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.uop.User;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_performance.EvalObject;

/**
 * 考核对象的service
 * @author Administrator
 *
 */
@Service
public class EvalObjectService {
	
	private String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");

	/**
	 * 批量插入考核对象的方法
	 */
	public List<EvalObject> insertBatch(EvalObject entity){
		String objectIds = entity.getObjectId();
		if(StringUtils.isNotBlank(objectIds)){
			
			String[] ids = objectIds.split(",");
			List<EvalObject> list = new ArrayList<>();
			String objectName = "";
			for (String objectId : ids) {
				EvalObject object = new EvalObject();
				//根据考核对象的类型来判断查询哪个表
				if("1".equals(entity.getObjectType())){//部门
					Orgview obj = DaoFactory.create(Orgview.class).selectByID(objectId);
					objectName = obj.getName();
				}else if("2".equals(entity.getObjectType())){//窗口
					//窗口暂时没有
				}else if("3".equals(entity.getObjectType())){//个人
					User user = RestUtils.createRestTemplate().getForObject(url +"/uop/v1/users/{id}",User.class,objectId);
					objectName = user.getName();
				}
				object.setObjectId(objectId);
				object.setObjectName(objectName);
				object.setObjectType(entity.getObjectType());
				object.setEvalInstanceId(entity.getEvalInstanceId());
				object.setState("N");
				list.add(object);
			}
			DaoFactory.create(EvalObject.class).insertBatch(list);
			return list;
			
		}else{
			return null;
		}
	}
	
	/**
	 * 根据测评实例id批量删除考核对象信息的方法
	 */
	public Integer deleteBatch(String instanceId){
		//根据实例id查询对应的对象信息
		int rows = DaoFactory.create(EvalObject.class).getSession()
			.delete("com.chinacreator.dzjc_performance.EvalObjectMapper.deleteByInstanceId", instanceId);
		return rows;
	}
	
}
