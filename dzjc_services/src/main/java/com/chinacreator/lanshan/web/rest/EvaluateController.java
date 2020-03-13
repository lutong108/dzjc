package com.chinacreator.lanshan.web.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.lanshan.Evaluate;
import com.chinacreator.lanshan.util.ExcelUtil;

@Controller
@Path("lsx_Evaluate")
@Consumes(MediaType.APPLICATION_JSON)
public class EvaluateController extends ExcelUtil {

	/** 系统日志 */
	private static final Logger LOG = LoggerFactory.getLogger(EvaluateController.class);
	public String folderPath = this.getClass().getResource("/").getPath();
	
	@POST
	@Path("/{evaluationId}")
	public Evaluate update(Evaluate entity) {
		DaoFactory.create(Evaluate.class).update(entity);
		return entity;
	}

	@POST
	@Path("")
	public Evaluate insert(Evaluate entity) {
		DaoFactory.create(Evaluate.class).insert(entity);
		return entity;
	}

	@GET
	@Path("/{evaluationId}")
	public Evaluate get(@PathParam(value = "evaluationId") java.lang.String evaluationId) {
		return DaoFactory.create(Evaluate.class).selectByID(evaluationId);
	}

	@DELETE
	@Path("")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(Evaluate.class).deleteBatch(ids);
	}

	@DELETE
	@Path("/{evaluationId}")
	public void delete(@PathParam(value = "evaluationId") java.lang.String evaluationId) {
		Evaluate entity = new Evaluate();
		entity.setEvaluationId(evaluationId);
		DaoFactory.create(Evaluate.class).delete(entity);
	}

	@GET
	@Path("")
	public Page<Evaluate> getListByPage(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		//创建分页对象
		Pageable pageable = new Pageable(page, rows);
		//创建排序对象
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		//自定义分页
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable, null, true);
		Evaluate entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, Evaluate.class) : new Evaluate();
		//获取参数
		Map<String,String> params = new HashMap<String,String>();
		if (entity != null) {
			if (entity.getBeginDate().toString() != null) {
				params.put("beginDate", entity.getBeginDate().toString());
			}
			if (entity.getEndDate().toString() != null) {
				params.put("endDate", entity.getEndDate().toString());
			}
		}
		List<Evaluate> list = DaoFactory.create(Evaluate.class).getSession()
				.selectList("com.chinacreator.lanshan.EvaluateMapper.selectByPage", params, rowbound);
		Page<Evaluate> selectPageByCondition = new Page<Evaluate>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}
	
	public String exportExcelEvaluate(String beginDate,String endDate) {
		LOG.info(">>>>>>>>>>>>  帮你办积分查询生成Excel开始    >>>>>>>>>>>>>");
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
		// 接收查询参数
		Map<String,String> params  = new HashMap<String,String>();
		if (beginDate != null) {
			params.put("beginDate", beginDate);
		}
		if (endDate != null) {
			params.put("endDate", endDate);
		}
		List<Evaluate> list  = DaoFactory.create(Evaluate.class).getSession()
				.selectList("com.chinacreator.lanshan.EvaluateMapper.selectByPage", params);

		// 创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		// 新增样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		HSSFCellStyle timeStyle = wb.createCellStyle();
		HSSFCellStyle headStyle = wb.createCellStyle();
		HSSFCellStyle columnStyle = wb.createCellStyle();
		// 自动换行
		titleStyle.setWrapText(true);
		timeStyle.setWrapText(true);
		headStyle.setWrapText(true);
		columnStyle.setWrapText(true);
		// 生成字体
		HSSFFont titleFont = wb.createFont();
		HSSFFont timeFont = wb.createFont();
		HSSFFont headFont = wb.createFont();
		HSSFFont columnFont = wb.createFont();

		HSSFSheet sheet = wb.createSheet("帮你办积分查询");
		// 设置列宽
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 3500);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 7000);
		sheet.setColumnWidth(7, 6500);

		String titleName = "帮你办积分查询";
		StringBuffer timeTitle = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (sdf.parse(beginDate).getTime() < sdf.parse("2019-04-01").getTime()) {
				beginDate = "2019-04-01";
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (null != beginDate && null != endDate) {
			timeTitle.append("(" + beginDate + " ▬ " + endDate + ")");
		}
		
		int rowIndex = 0;
		// 标题行
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		HSSFRow rowTitle = sheet.createRow(rowIndex++);
		HSSFCell cellTitle = rowTitle.createCell(0);
		setFont(titleFont, 15, true, "黑体");
		setCellStyle(titleStyle, true, false, titleFont);
		rowTitle.setHeight((short) 700);// 行高
		cellTitle.setCellStyle(titleStyle);
		cellTitle.setCellValue("帮你办积分查询");

		// 日期行
		if (timeTitle != null && !"".equals(timeTitle)) {
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
			HSSFRow rowTime = sheet.createRow(rowIndex++);
			HSSFCell cellTime = rowTime.createCell(0);
			rowTime.setHeight((short) 500);
			setFont(timeFont, 10, false, "宋体");
			setTimeTitleStyle(timeStyle, true, true, timeFont);
			cellTime.setCellStyle(timeStyle);
			cellTime.setCellValue(timeTitle.toString());
		}

		rowIndex = exqHeader(headStyle, headFont, sheet, rowIndex);//生成表头

		exqBodyCell(list, columnStyle, columnFont, sheet, rowIndex);//生成工作簿主体

		FileOutputStream os = null;
		try {
			// 文件输出流
			os = new FileOutputStream(folderPath + titleName + ".xls");
			wb.write(os);
			wb.close();
		} catch (IOException e) {
			LOG.error("帮你办积分查询生成Excel出错:" + e);
			e.printStackTrace();
		}
		LOG.info(">>>>>>>>>>>> 帮你办积分查询生成Excel结束  >>>>>>>>>>>>>");
		return titleName;
	}
	
	
	private void exqBodyCell(List<Evaluate> list, HSSFCellStyle columnStyle,
			HSSFFont columnFont, HSSFSheet sheet, int rowIndex) {
		int cellIndex;
		HSSFRow rowColumnValue = null;
		HSSFCell cellColumnValue = null;
		setFont(columnFont, 10, false, "宋体");
		setCellStyle(columnStyle, true, true, columnFont);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Evaluate evaluate = list.get(i);
				rowColumnValue = sheet.createRow(rowIndex + i);
				rowColumnValue.setHeight((short) 400);
				cellIndex = 0;
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(evaluate.getCommunity());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(evaluate.getUsername());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(evaluate.getPhone());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(evaluate.getSystemtype());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(evaluate.getResultStr());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(evaluate.getEvaluationResult());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(evaluate.getEvaluationContent());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(evaluate.getEvaluationTime()));
			}
		}
	}
	
	
	private int exqHeader(HSSFCellStyle headStyle, HSSFFont headFont, HSSFSheet sheet, int rowIndex) {
		// 第三行
		int cellIndex = 0;
		HSSFRow rowColumnName = sheet.createRow(rowIndex++);
		setFont(headFont, 12, true, "宋体");
		setCellStyle(headStyle, true, true, headFont);
		rowColumnName.setHeight((short) 500);
		HSSFCell cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("社区");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("网格员/党员");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("（网格员/党员）电话");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("所在系统");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("评价结果");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("评分");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("评价内容");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("评价时间");
		return rowIndex;
	}
}
