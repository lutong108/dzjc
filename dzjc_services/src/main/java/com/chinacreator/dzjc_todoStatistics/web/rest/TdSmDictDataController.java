package com.chinacreator.dzjc_todoStatistics.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.chinacreator.dzjc_todoStatistics.TdSmDictData;
import com.chinacreator.dzjc_todoStatistics.TdSmDictVo;
import com.chinacreator.dzjc_todoStatistics.service.TdSmDictDataService;

@Controller
@Path("dzjc_todoStatistics/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class TdSmDictDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(TdSmDictDataController.class);
	
	@Autowired
	private TdSmDictDataService tdSmDictDataService;

	@GET
	@Path("tdSmDictData/{dicttypeName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getTdSmDictDataById(
			@PathParam(value = "dicttypeName") java.lang.String dicttypeName) {
		Map<String,Object> result=new HashMap<>();
		try{
			if(StringUtils.isEmpty(dicttypeName)){
				result.put("message", "数据参数失败");
				result.put("success",false);
			}else{
				List<TdSmDictVo> tdSmDictDatas=tdSmDictDataService.getTdSmDictDataById(dicttypeName);
				result.put("data", tdSmDictDatas);
				result.put("success",true);
			}
		}catch(Exception e){
			logger.error("数据查询失败"+e.getMessage(), e);
			result.put("message", "数据查询失败");
			result.put("success", false);
		}
		return result;
	}
}
