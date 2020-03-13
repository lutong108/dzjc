package com.chinacreator.dzjc_performance.web.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.fs.DownResult;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.dzjc_performance.EvalEnclosure;
import com.chinacreator.util.S3StorageServices;
import com.chinacreator.util.StringUtil;

@Controller
@Path("dzjc_performance/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class EvalEnclosureController {

	@POST
	@Path("Enclosure/{enclosureId}")
	public EvalEnclosure update(EvalEnclosure entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalEnclosure.class).update(entity);
		return entity;
	}

	@POST
	@Path("Enclosure")
	public EvalEnclosure insert(EvalEnclosure entity) {
		//TODO auto-generated method stub	
		DaoFactory.create(EvalEnclosure.class).insert(entity);
		return entity;
	}

	@GET
	@Path("Enclosure/{enclosureId}")
	public EvalEnclosure get(
			@PathParam(value = "enclosureId") java.lang.String enclosureId) {
		//TODO auto-generated method stub
		return DaoFactory.create(EvalEnclosure.class).selectByID(enclosureId);
	}

	@DELETE
	@Path("Enclosure")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		//TODO auto-generated method stub
		DaoFactory.create(EvalEnclosure.class).deleteBatch(ids);
	}

	@DELETE
	@Path("Enclosure/{enclosureId}")
	public void delete(
			@PathParam(value = "enclosureId") java.lang.String enclosureId) {
		//TODO auto-generated method stub
		EvalEnclosure entity = new EvalEnclosure();
		entity.setEnclosureId(enclosureId);
		DaoFactory.create(EvalEnclosure.class).delete(entity);
	}

	@GET
	@Path("Enclosure")
	public Page<EvalEnclosure> getListByPage(
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
		EvalEnclosure entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(
				cond, EvalEnclosure.class) : new EvalEnclosure();

		return DaoFactory.create(EvalEnclosure.class).selectPageByCondition(
				entity, conditions, pageable, sortable, true);

	}
	
	/**
	 * 取附件路径
	 * @throws Exception 
	 */
	@GET
	@Path("Enclosure/OpenView/{path}")
	public void areaCode(@PathParam(value = "path") java.lang.String path) throws Exception {
		
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		
		HttpServletResponse response = context.getResponse();
		
		path=path.replaceAll("_", "/");
		
		DownResult result = new S3StorageServices().downloadFile(path);
		
		InputStream inputStream = result.getInputStream();
		
		OutputStream	outs = response.getOutputStream();
		
		writeResponse(inputStream,outs);
		
	}
	
	  private void writeResponse(InputStream is, OutputStream os) throws Exception {
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	            bis = new BufferedInputStream(is);
	            byte[] bytes = new byte[1024];
	            bos = new BufferedOutputStream(os);
	            while ((bis.read(bytes)) != -1) {
	                bos.write(bytes);
	            }
	            bos.flush();
	        } catch (Exception e) {
	            throw e;
	        } finally {
	        	StringUtil.closeStream(bis);
	        	StringUtil.closeStream(bos);
	        }
	    }
}
