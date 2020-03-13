package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.MsgRecord;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class MsgRecordController {

	@POST
	@Path("msgRecord/{msgId}")
	public MsgRecord update(MsgRecord entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MsgRecord.class).update(entity);
		return entity;
	}

	@POST
	@Path("msgRecord")
	public MsgRecord insert(MsgRecord entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MsgRecord.class).insert(entity);
		return entity;
	}

	@GET
	@Path("msgRecord/{msgId}")
	public MsgRecord get(@PathParam(value = "msgId") java.lang.String msgId) {
		//TODO auto-generated method stub
		return DaoFactory.create(MsgRecord.class).selectByID(msgId);
	}
	
	@GET
	@Path("msgInterface")
	public String  msgReport(@QueryParam("mobile") String mobile,
			@QueryParam("msgid") String msgid,@QueryParam("status") String status,
			@QueryParam("result") String result) {
		//TODO auto-generated method stub
		MsgRecord entity =new MsgRecord();
		entity.setSendMobile(mobile);
		entity.setSendMsgId(msgid);
		entity = getMsgRecord(entity);
		if(entity!=null){
			if("0".equals(result)){
				entity.setSendFlag("S");
			}else{
				entity.setSendFlag("F");
				try {
					//status = URLEncoder.encode(status, "GBK");
					System.out.println("status000=="+StringUtil.deNull(status));
					status = URLDecoder.decode(StringUtil.deNull(status), "GBK");
					System.out.println("status001=="+status);
					status = new String(StringUtil.getUTF8BytesFromGBKString(status),"UTF-8");
					System.out.println("status002=="+status);
					//status = URLDecoder.decode(URLDecoder.decode(StringUtil.deNull(status), "UTF-8"),"GBK");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				System.out.println(status);
				if(!StringUtil.isBlank(status)){
					if(status.length()>100){
						status=status.substring(0, 99);
					}
				}
				entity.setResultDes(status);
			}
			update(entity);
		}
		return "ok";
	}
	
	@POST
	@Path("getMsgRecord")
	public MsgRecord getMsgRecord(MsgRecord entity ) {
		//TODO auto-generated method stub
		return DaoFactory.create(MsgRecord.class).selectOne(entity);
	}
	

	@GET
	@Path("msgRecord")
	public Page<MsgRecord> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		//TODO auto-generated method stub
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		/*创建高级查询对象*/
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		/*初始化实体对象*/
		MsgRecord entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, MsgRecord.class) : new MsgRecord();

		return DaoFactory.create(MsgRecord.class).selectPageByCondition(entity,
				conditions, pageable, sortable, true);

	}
}
