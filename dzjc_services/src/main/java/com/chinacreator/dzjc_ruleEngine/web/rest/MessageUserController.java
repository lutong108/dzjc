package com.chinacreator.dzjc_ruleEngine.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_ruleEngine.MessageUser;
import com.chinacreator.util.BeanUtil;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_ruleEngine/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class MessageUserController {

	private static final String String = null;

	@DELETE
	@Path("messageUser/{messageUserId}")
	public void delete(
			@PathParam(value = "messageUserId") java.lang.String messageUserId) {
		//TODO auto-generated method stub
		MessageUser entity = new MessageUser();
		entity.setMessageUserId(messageUserId);
		DaoFactory.create(MessageUser.class).delete(entity);
	}

	@DELETE
	@Path("messageUser")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(MessageUser.class).deleteBatch(ids);
	}

	@GET
	@Path("messageUser/{messageUserId}")
	public MessageUser get(
			@PathParam(value = "messageUserId") java.lang.String messageUserId) {
		//TODO auto-generated method stub
		return DaoFactory.create(MessageUser.class).selectByID(messageUserId);
	}

	@POST
	@Path("messageUser")
	public MessageUser insert(MessageUser entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MessageUser.class).insert(entity);
		return entity;
	}
	
	@POST
	@Path("saveMessageUsers")
	public List<MessageUser> saveMessageUsers(JSONObject object) {
		List<MessageUser> list= null;
		String modelStr=object.get("model").toString();
		MessageUser entity = StringUtils.isNotBlank(modelStr)? JSON.parseObject(
				modelStr, MessageUser.class) : null;
		JSONArray jsonArray = object.getJSONArray("approveIds");
		if(jsonArray!=null && jsonArray.size()>0 && entity!=null){
			list=new ArrayList<MessageUser>();
			for (Object approveId : jsonArray) {
				MessageUser model= new MessageUser();
				BeanUtil.coverBean2Bean(entity, model);
				model.setApproveId(StringUtil.DeNull(approveId));
				list.add(model);
			}
		}
		int insertBatch = DaoFactory.create(MessageUser.class).insertBatch(list);
		return list;
	}
	
	@GET
	@Path("messageUserAll")
	public List<MessageUser> getAll() {
		Sortable sortable = SortableUtil.getSortable("approveId desc", "asc");
		return DaoFactory.create(MessageUser.class).selectAll(sortable);
	}
	
	public List<MessageUser> getAllByApproveList(List<String> approveList) {
		Sortable sortable = SortableUtil.getSortable("approveId desc", "asc");
		List<MessageUser>  messageList= new ArrayList<MessageUser>();
		for (String string : approveList) {
			MessageUser entity= new MessageUser();
			entity.setApproveId(string);
			messageList.add(entity);
		}
		List<MessageUser> selectUnion = DaoFactory.create(MessageUser.class).selectUnion(messageList, sortable);
		return selectUnion;
	}
	
	@POST
	@Path("messageUser/{messageUserId}")
	public MessageUser update(MessageUser entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(MessageUser.class).update(entity);
		return entity;
	}

	@GET
	@Path("messageUser")
	public Page<MessageUser> getListByPage(
			@QueryParam(value = "page") int page,
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
		MessageUser entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, MessageUser.class) : new MessageUser();

		return DaoFactory.create(MessageUser.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	@GET
	@Path("findByApproveIds")
	public Page<MessageUser> findByApproveIds(
			@QueryParam(value = "page") int page,
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
		MessageUser entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, MessageUser.class) : new MessageUser();
		int page_ = (pageable == null) ? 1 : pageable
						.getPageIndex();
		int size = (pageable == null) ? 15 : pageable
						.getPageSize();
		RowBounds4Page rowBounds = new RowBounds4Page((page_ - 1)* size, size, sortable, conditions, true);
		String statement="com.chinacreator.dzjc_ruleEngine.MessageUserMapper.approveIdBatch";
		Map<String,Object> parameter= (Map<String, Object>) JSON.parse(cond);
		List<MessageUser> resultList = DaoFactory.create(MessageUser.class).getSession().selectList(statement, parameter, rowBounds);
		Page<MessageUser> pagingResult = new Page(page, size, (true)? rowBounds.getTotalSize(): 0, resultList);
		return pagingResult;	
	}
	
	
	
	/**
	 * 自定义getById
	 * @return
	 */
	@POST
	@Path("messageUser/getMessageUser")
	public MessageUser getMessageUser(@RequestBody String messageUserId) {
		return DaoFactory.create(MessageUser.class).selectByID(messageUserId);
	}
	
	/**
	 * 监察短信发送获取信息
	 * @return
	 */
	@GET
	@Path("messageUser/getMessageUser4Supervise")
	public List<MessageUser> getMessageUser4Supervise(String approveId,String superviseResult) {
		MessageUser messageUser= new MessageUser();
		messageUser.setApproveId(approveId);
		messageUser.setWarnLevel(superviseResult);
		messageUser.setState("Y");
		
		//getMessageUser4Supervise
		List<MessageUser> select = DaoFactory.create(MessageUser.class).select(messageUser);
		return select;
	}

	public List<String> getStreetOrgList() {
		List<String> selectList = DaoFactory.create(MessageUser.class).getSession().
			selectList("com.chinacreator.dzjc_ruleEngine.MessageUserMapper.findStreetOrgId");
		return selectList;
	}
	
}
