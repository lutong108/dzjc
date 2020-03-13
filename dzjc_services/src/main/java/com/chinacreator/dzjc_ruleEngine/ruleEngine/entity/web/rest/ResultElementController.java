package com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.entity.ResultElement;

@Controller
@Path("ResultElement")
@Consumes(MediaType.APPLICATION_JSON)
public class ResultElementController {

	@DELETE
	@Path("/{rltId}")
	public void delete(@PathParam(value = "rltId") java.lang.String rltId) {
		//TODO auto-generated method stub
		ResultElement entity = new ResultElement();
		entity.setRltId(rltId);
		DaoFactory.create(ResultElement.class).delete(entity);
	}

	@DELETE
	@Path("")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(ResultElement.class).deleteBatch(ids);
	}
}
