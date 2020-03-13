package com.chinacreator.dzjc_tongji.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.dzjc_tongji.StatisticalSumCache;
import com.chinacreator.dzjc_tongji.service.StatisticalSumCacheService;

@Controller
@Path("dzjc_tongji/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class StatisticalSumCacheController {
	@Autowired
	private StatisticalSumCacheService statisticalSumCacheService;

	@POST
	@Path("StatisticalSumCache/{superviseId}")
	public StatisticalSumCache update(StatisticalSumCache entity) {
		//TODO auto-generated method stub	
		return statisticalSumCacheService.update(entity);
	}

	@POST
	@Path("StatisticalSumCache")
	public StatisticalSumCache insert(StatisticalSumCache entity) {
		//TODO auto-generated method stub	
		return statisticalSumCacheService.insert(entity);
	}

	@GET
	@Path("StatisticalSumCache/{superviseId}")
	public StatisticalSumCache get(
			@PathParam(value = "superviseId") java.lang.String superviseId) {
		//TODO auto-generated method stub
		return statisticalSumCacheService.get(superviseId);
	}

	@DELETE
	@Path("StatisticalSumCache")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		statisticalSumCacheService.deleteBatch(ids);
	}

	@DELETE
	@Path("StatisticalSumCache/{superviseId}")
	public void delete(
			@PathParam(value = "superviseId") java.lang.String superviseId) {
		//TODO auto-generated method stub
		statisticalSumCacheService.delete(superviseId);
	}

	@GET
	@Path("StatisticalSumCache")
	public Page<StatisticalSumCache> getListByPage(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		//TODO auto-generated method stub
		return statisticalSumCacheService.getListByPage(page, rows, sidx, sord,
				cond);

	}
	
	@Path("incrSendCard")
	public void incrSendCard(){
		statisticalSumCacheService.incrSendCards();
	}
}
