package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_complain.Orgview;
import com.chinacreator.dzjc_ruleEngine.BussinessCom;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class BussinessComController {

	@GET
	@Path("bussinessCom/{complainId}")
	public BussinessCom get(
			@PathParam(value = "complainId") java.lang.String complainId) {
		BussinessCom entity=DaoFactory.create(BussinessCom.class).selectByID(complainId);
		
		if (entity != null) {
			String org_id = entity.getBycomplainedUserAddress();
			List<Orgview> orgList = DaoFactory.create(Orgview.class).getSession()
					.selectList("com.chinacreator.dzjc_complain.OrgviewMapper.selectOrgAndPi", org_id);

			if (orgList.size() > 0) {

				String orgLongName = "";
				for (Orgview orgView : orgList) {
					if (orgView.getName().indexOf("本级") == -1) {
						orgLongName += orgView.getName();
					}
				}
				entity.setBycomplainedUserAddress(orgLongName);
			}

			/*// 获取是否满意（Y：满意；M：比较满意 ；N：不满意）
			String isSatisfied = entity.getIsSatisfied();
			if ("Y".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("满意");
			} else if ("M".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("比较满意");
			} else if ("N".equalsIgnoreCase(isSatisfied)) {
				entity.setIsSatisfied("不满意");
			}

			// 获取投诉方式
			String problemMode = entity.getProblemMode();
			if ("1".equals(problemMode)) {
				entity.setProblemMode("网络");
			} else if ("2".equals(problemMode)) {
				entity.setProblemMode("电话");
			} else if ("3".equals(problemMode)) {
				entity.setProblemMode("来访");
			} else if ("4".equals(problemMode)) {
				entity.setProblemMode("函件");
			} else if ("5".equals(problemMode)) {
				entity.setProblemMode("批办");
			}*/

		}
		return entity;
	}
}
