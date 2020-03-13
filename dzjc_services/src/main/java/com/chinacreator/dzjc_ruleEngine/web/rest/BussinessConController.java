package com.chinacreator.dzjc_ruleEngine.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.BussinessCon;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class BussinessConController {

	@GET
	@Path("bussinessCon/{consultId}")
	public BussinessCon get(
			@PathParam(value = "consultId") java.lang.String consultId) {
		//TODO auto-generated method stub
		return DaoFactory.create(BussinessCon.class).selectByID(consultId);
	}
}
