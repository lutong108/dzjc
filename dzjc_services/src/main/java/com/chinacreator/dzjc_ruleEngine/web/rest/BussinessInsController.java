package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.BussinessIns;
import com.chinacreator.dzjc_ruleEngine.SuperviserInfo;
import com.chinacreator.dzjc_tongji.DevSupervise;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class BussinessInsController {

	@GET
	@Path("bussinessIns/{instanceId}")
	public BussinessIns get(
			@PathParam(value = "instanceId") java.lang.String instanceId) {
		BussinessIns bussinessIns=DaoFactory.create(BussinessIns.class).selectByID(instanceId);
		
		/*2019-08-31 zhl修改
		 * 特殊处理，法定时限和承诺时限显示发牌表中的，如果未发牌则显示事项中的法定时限和承诺时限
		 * */
		DevSupervise devSupervise = new DevSupervise();
		devSupervise.setBusinessId(bussinessIns.getInstanceId());
		devSupervise.setBusinessType("1");//只查询办件发牌
		List<DevSupervise> devSuperviseList = DaoFactory.create(DevSupervise.class).select(devSupervise);
		if(devSuperviseList!=null){
			for (DevSupervise temp : devSuperviseList) {
				if(temp.getPromiseLimit()!=null&&temp.getProcessLimit()!=null){
					bussinessIns.setPromisesLimit(temp.getPromiseLimit().toString());//承诺时限
					bussinessIns.setTimeLimit(temp.getProcessLimit().toString());//法定时限
					break;
				}
			}
		}
		return bussinessIns;
	}
}
