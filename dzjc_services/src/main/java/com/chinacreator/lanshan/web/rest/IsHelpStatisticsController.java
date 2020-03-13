package com.chinacreator.lanshan.web.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.util.ConditionsUtil;
import com.chinacreator.c2.web.util.SortableUtil;
import com.chinacreator.lanshan.BnbTongji;
import com.chinacreator.lanshan.BnbTongjiVo;
import com.chinacreator.lanshan.IsHelpStatistics;
import com.chinacreator.lanshan.util.ExcelUtil;

@Controller
@Path("lanshan/")
@Consumes(MediaType.APPLICATION_JSON)
public class IsHelpStatisticsController extends ExcelUtil {
	/** 系统日志 */
	private static final Logger LOG = LoggerFactory.getLogger(IsHelpStatisticsController.class);
	public String folderPath = this.getClass().getResource("/").getPath();

	@POST
	@Path("isHelpStatistics/{orderId}")
	public IsHelpStatistics update(IsHelpStatistics entity) {
		DaoFactory.create(IsHelpStatistics.class).update(entity);
		return entity;
	}

	@POST
	@Path("isHelpStatistics")
	public IsHelpStatistics insert(IsHelpStatistics entity) {
		DaoFactory.create(IsHelpStatistics.class).insert(entity);
		return entity;
	}

	@GET
	@Path("isHelpStatistics/{orderId}")
	public IsHelpStatistics get(@PathParam(value = "orderId") java.lang.String orderId) {
		return DaoFactory.create(IsHelpStatistics.class).selectByID(orderId);
	}

	@DELETE
	@Path("isHelpStatistics")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(IsHelpStatistics.class).deleteBatch(ids);
	}

	@DELETE
	@Path("isHelpStatistics/{orderId}")
	public void delete(@PathParam(value = "orderId") java.lang.String orderId) {
		IsHelpStatistics entity = new IsHelpStatistics();
		entity.setOrderId(orderId);
		DaoFactory.create(IsHelpStatistics.class).delete(entity);
	}

	@GET
	@Path("isHelpStatistics")
	public Page<IsHelpStatistics> getListByPage(@QueryParam(value = "page") int page,
			@QueryParam(value = "rows") int rows, @QueryParam(value = "sidx") String sidx,
			@QueryParam(value = "sord") String sord, @QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable, null, true);
		List<IsHelpStatistics> list = new ArrayList<IsHelpStatistics>();
		// 接收查询参数
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		if (conditions != null) {
			List<Rule> rules = conditions.getRules();
			// 获取查询参数
			for (Rule rule : rules) {
				// 接单用户名
				String op = rule.getOp();
				if ("username".equals(rule.getField())) {
					if ("cn".equals(op)) {
						params.put("username", rule.getData().toString());
					}
				}
				// 系统类型
				if ("systemtype".equals(rule.getField())) {
					if ("eq".equals(op)) {
						params.put("systemtype", rule.getData().toString());
					}
				}
				// 办件状态
				if ("status".equals(rule.getField())) {
					if ("eq".equals(op)) {
						params.put("status", rule.getData().toString());
					}
				}
				// 事项类型
				if ("sxlx".equals(rule.getField())) {
					if ("eq".equals(op)) {
						params.put("sxlx", rule.getData().toString());
					}
				}
				// 接单开始时间
				if ("beginTime".equals(rule.getField())) {
					// 接单开始时间
					if ("ge".equals(op)) {
						params.put("beginTime", new Date((long) rule.getData()));
					}
				}
				// 接单结束时间
				if ("endTime".equals(rule.getField())) {
					if ("le".equals(op)) {
						params.put("endTime", new Date((long) rule.getData()));
					}
	
				}
				// 办结开始时间
				if ("completed_beginTime".equals(rule.getField())) {
					// 办结开始时间
					if ("ge".equals(op)) {
						params.put("completed_beginTime", new Date((long) rule.getData()));
					}
				}
				// 办结结束时间
				if ("completed_endTime".equals(rule.getField())) {
					if ("le".equals(op)) {
						params.put("completed_endTime", new Date((long) rule.getData()));
					}
	
				}
			}
		}
		list = DaoFactory.create(IsHelpStatistics.class).getSession()
				.selectList("com.chinacreator.lanshan.IsHelpStatisticsMapper.selectByPage", params, rowbound);
		Page<IsHelpStatistics> selectPageByCondition = new Page<IsHelpStatistics>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}

	/**
	 * 蓝山县帮你办办件列表生成Excel
	 * <p>
	 * Title: expExcelIsHelpStatistics
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param list
	 * @return
	 */
	public String expExcelIsHelpStatistics(String cond) {
		LOG.info(">>>>>>>>>>>>  帮你办办件列表生成Excel开始    >>>>>>>>>>>>>");
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String titleName = "帮你办办件列表";
		StringBuffer timeTitle = new StringBuffer();
		String beginDate = "";
		String endDate = "";
		String beginStr = "0";
		String endStr = "0";
		String completed_beginDate = "";
		String completed_endDate = "";
		String completed_beginStr = "0";
		String completed_endStr = "0";
		// 接收查询参数
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		if (conditions != null) {
			List<Rule> rules = conditions.getRules();
			// 获取查询参数
			for (Rule rule : rules) {
				// 接单用户名
				String op = rule.getOp();
				if ("username".equals(rule.getField())) {
					if ("cn".equals(op)) {
						params.put("username", rule.getData().toString());
					}
				}
				// 系统类型
				if ("systemtype".equals(rule.getField())) {
					if ("eq".equals(op)) {
						params.put("systemtype", rule.getData().toString());
					}
				}
				// 办件状态
				if ("status".equals(rule.getField())) {
					if ("eq".equals(op)) {
						params.put("status", rule.getData().toString());
					}
				}
				// 事项类型
				if ("sxlx".equals(rule.getField())) {
					if ("eq".equals(op)) {
						params.put("sxlx", rule.getData().toString());
					}
				}
				// 接单开始时间
				if ("beginTime".equals(rule.getField())) {
					// 接单开始时间
					if ("ge".equals(op)) {
						params.put("beginTime", new Date((long) rule.getData()));
						beginDate = sdf.format(new Date((long) rule.getData()));
						beginStr = "1";
					}
				}
				// 接单结束时间
				if ("endTime".equals(rule.getField())) {
					if ("le".equals(op)) {
						params.put("endTime", new Date((long) rule.getData()));
						endDate = sdf.format(new Date((long) rule.getData()));
						endStr = "1";
					}

				}
				// 办结开始时间
				if ("completed_beginTime".equals(rule.getField())) {
					// 办结开始时间
					if ("ge".equals(op)) {
						params.put("completed_beginTime", new Date((long) rule.getData()));
						completed_beginDate = sdf.format(new Date((long) rule.getData()));
						completed_beginStr = "1";
					}
				}
				// 办结结束时间
				if ("completed_endTime".equals(rule.getField())) {
					if ("le".equals(op)) {
						params.put("completed_endTime", new Date((long) rule.getData()));
						completed_endDate = sdf.format(new Date((long) rule.getData()));
						completed_endStr = "1";
					}

				}
			}
			if ("1".equals(beginStr) && "1".equals(endStr)) {
				timeTitle.append("查询接单时间：" + beginDate + "至" + endDate + "的数据");
			} else if ("1".equals(beginStr) && "0".equals(endStr)) {
				timeTitle.append("查询接单时间在:" + beginDate + "之后的数据");
			} else if ("0".equals(beginStr) && "1".equals(endStr)) {
				timeTitle.append("查询接单时间在：" + endDate + "之前的数据");
			} if ("1".equals(completed_beginStr) && "1".equals(completed_endStr)) {
				timeTitle.append("查询办结时间：" + completed_beginDate + "至" + completed_endDate + "的数据");
			} else if ("1".equals(completed_beginStr) && "0".equals(completed_endStr)) {
				timeTitle.append("查询办结时间在:" + completed_beginDate + "之后的数据");
			} else if ("0".equals(completed_beginStr) && "1".equals(completed_endStr)) {
				timeTitle.append("查询办结时间在：" + completed_endDate + "之前的数据");
			} else if ("0".equals(beginStr) && "".equals(completed_endStr)) {
				timeTitle.append("导出所有数据");
			}
		}
		List<IsHelpStatistics> list = DaoFactory.create(IsHelpStatistics.class).getSession()
				.selectList("com.chinacreator.lanshan.IsHelpStatisticsMapper.selectExpExcel", params);

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

		HSSFSheet sheet = wb.createSheet("帮你办办件列表");
		// 设置列宽
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 12000);
		sheet.setColumnWidth(3, 5500);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 3500);
		sheet.setColumnWidth(6, 3500);
		sheet.setColumnWidth(7, 3500);
		sheet.setColumnWidth(8, 3500);
		sheet.setColumnWidth(9, 3500);
		sheet.setColumnWidth(10, 15000);

		int rowIndex = 0;
		// 标题行
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
		HSSFRow rowTitle = sheet.createRow(rowIndex++);
		HSSFCell cellTitle = rowTitle.createCell(0);
		// CellRangeAddress（int firstRow, int lastRow, int firstCol, int
		// lastCol）参数：起始行号，终止行号， 起始列号，终止列号
		setFont(titleFont, 15, true, "黑体");
		setCellStyle(titleStyle, true, false, titleFont);
		rowTitle.setHeight((short) 700);// 行高
		cellTitle.setCellStyle(titleStyle);
		cellTitle.setCellValue("帮你办办件列表");

		// 日期行
		if (timeTitle != null && !"".equals(timeTitle)) {
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 10));
			HSSFRow rowTime = sheet.createRow(rowIndex++);
			HSSFCell cellTime = rowTime.createCell(0);
			rowTime.setHeight((short) 500);
			setFont(timeFont, 10, false, "宋体");
			setTimeTitleStyle(timeStyle, true, true, timeFont);
			cellTime.setCellStyle(timeStyle);
			cellTime.setCellValue(timeTitle.toString());
		}

		rowIndex = exqHeader(headStyle, headFont, sheet, rowIndex);//生成表头

		exqBodyCell(sdf, list, columnStyle, columnFont, sheet, rowIndex);//生成工作簿主体

		FileOutputStream os = null;
		try {
			// 文件输出流
			os = new FileOutputStream(folderPath + titleName + ".xls");
			wb.write(os);
			wb.close();
		} catch (IOException e) {
			LOG.error("帮你办办件列表生成Excel出错:" + e);
			e.printStackTrace();
		}
		LOG.info(">>>>>>>>>>>>  帮你办办件列表生成Excel结束  >>>>>>>>>>>>>");
		return titleName;
	}

	/**  
	 * <p>Title: exqBodyCell</p>  
	 * <p>Description: 生成工作簿主体</p> 
	 * @param: @param sdf
	 * @param: @param list
	 * @param: @param columnStyle
	 * @param: @param columnFont
	 * @param: @param sheet
	 * @param: @param rowIndex 
	 * @return void
	 * @throws
	 * @author ming.yi
	 * createTime：2019年2月21日 下午6:11:40 
	 */  
	private void exqBodyCell(SimpleDateFormat sdf, List<IsHelpStatistics> list, HSSFCellStyle columnStyle,
			HSSFFont columnFont, HSSFSheet sheet, int rowIndex) {
		int cellIndex;
		HSSFRow rowColumnValue = null;
		HSSFCell cellColumnValue = null;
		setFont(columnFont, 10, false, "宋体");
		setCellStyle(columnStyle, true, true, columnFont);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				IsHelpStatistics isHelpStatistics = list.get(i);
				rowColumnValue = sheet.createRow(rowIndex + i);
				rowColumnValue.setHeight((short) 400);
				cellIndex = 0;
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getApplyName());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getApplyPhone());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getApproveName());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getUsername());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getOrderPhone());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				if (isHelpStatistics.getOrderTime() != null) {
					cellColumnValue.setCellValue(sdf.format(isHelpStatistics.getOrderTime()));
				} else {
					cellColumnValue.setCellValue("");
				}
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				if (isHelpStatistics.getEndTime() != null) {
					cellColumnValue.setCellValue(sdf.format(isHelpStatistics.getEndTime()));
				} else {
					cellColumnValue.setCellValue("");
				}
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getSystemtype());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getStatus());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getSxlx());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(isHelpStatistics.getTdyy());
			}
		}
	}

	/**  
	 * <p>Title: exqHeader</p>  
	 * <p>Description: 生成表头</p> 
	 * @param: @param headStyle
	 * @param: @param headFont
	 * @param: @param sheet
	 * @param: @param rowIndex
	 * @param: @return 
	 * @return int
	 * @throws
	 * @author ming.yi
	 * createTime：2019年2月21日 下午6:10:06 
	 */  
	private int exqHeader(HSSFCellStyle headStyle, HSSFFont headFont, HSSFSheet sheet, int rowIndex) {
		// 第三行
		int cellIndex = 0;
		HSSFRow rowColumnName = sheet.createRow(rowIndex++);
		setFont(headFont, 12, true, "宋体");
		setCellStyle(headStyle, true, true, headFont);
		rowColumnName.setHeight((short) 500);
		HSSFCell cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("申请人姓名");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("申请人电话");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("事项名称(需求)");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("接单人姓名");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("接单人电话");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("接单时间");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("办结时间");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("系统类型");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("办件状态");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("事项类型");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("退单原因");
		return rowIndex;
	}

	/**
	 * 帮你办办件统计
	 * <p>
	 * Title: getPageList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param cond
	 * @return
	 */
	@GET
	@Path("bnbTongJi")
	public Page<BnbTongji> getPageList(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		BnbTongji entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, BnbTongji.class) : new BnbTongji();
		return DaoFactory.create(BnbTongji.class).selectPageByCondition(entity, conditions, pageable, sortable, true);
	}

	/**
	 * 帮你办统计生成Excel
	 * <p>
	 * Title: expBnb_TongJiExcel
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param list
	 * @return
	 */
	public String expBnb_TongJiExcel(String cond) {
		LOG.info(">>>>>>  帮你办统计生成Excel开始  >>>>>>");
		// 创建文件
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
		String titleName = "帮你办统计表";
		String timeTitle = "";
		String orderMonth = "";
		// 接收查询参数
		Conditions conditions = ConditionsUtil.getConditions(cond, "filters");
		BnbTongjiVo bnb_tongji = new BnbTongjiVo();
		if (conditions != null) {
			List<Rule> rules = conditions.getRules();
			// 获取查询参数
			for (Rule rule : rules) {
				// 接单时间
				if ("orderMonth".equals(rule.getField())) {
					// 接单开始时间
					if ("eq".equals(rule.getOp())) {
						orderMonth = rule.getData().toString();
						bnb_tongji.setOrderMonth(orderMonth);
						timeTitle += "查询" + orderMonth.replace("-", "年")+ "月数据";
					}
					if ("le".equals(rule.getOp())) {
						orderMonth = rule.getData().toString();
						bnb_tongji.setEndMonth(orderMonth);
						int len = orderMonth.length();
						timeTitle += "至" + orderMonth.substring(len-2, len)+ "月数据";
					}
					if ("ge".equals(rule.getOp())) {
						orderMonth = rule.getData().toString();
						bnb_tongji.setFromMonth(orderMonth);
						timeTitle += "查询范围从" + orderMonth.replace("-", "年")+ "月开始";
					}
				} 
			}
		} else {
			timeTitle = "查询所有数据";
		}
		// 查询数据
		List<BnbTongji> list = DaoFactory.create(BnbTongji.class).getSession()
				.selectList("com.chinacreator.lanshan.BnbTongjiMapper.selectExcelList", bnb_tongji);

		// 创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		// 新增样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		HSSFCellStyle timeStyle = wb.createCellStyle();
		HSSFCellStyle headStyle = wb.createCellStyle();
		HSSFCellStyle columnStyle = wb.createCellStyle();
		// 自动换行
		headStyle.setWrapText(true);
		timeStyle.setWrapText(true);
		titleStyle.setWrapText(true);
		columnStyle.setWrapText(true);
		// 生成字体
		HSSFFont titleFont = wb.createFont();
		HSSFFont timeFont = wb.createFont();
		HSSFFont headFont = wb.createFont();
		HSSFFont columnFont = wb.createFont();

		HSSFSheet sheet = wb.createSheet("帮你办统计表");
		// 设置列宽
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);

		int rowIndex = 0;
		HSSFRow rowTitle = sheet.createRow(rowIndex++);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
		HSSFCell cellTitle = rowTitle.createCell(0);
		rowTitle.setHeight((short) 700);// 设置标题行行高
		setFont(titleFont, 15, true, "黑体");
		setCellStyle(titleStyle, true, false, titleFont);
		cellTitle.setCellStyle(titleStyle);
		cellTitle.setCellValue("帮你办统计表");

		HSSFRow rowTime = sheet.createRow(rowIndex++);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
		HSSFCell cellTime = rowTime.createCell(0);
		rowTime.setHeight((short) 400);// 设置日期行行高
		setFont(timeFont, 10, false, "宋体");
		setTimeTitleStyle(timeStyle, true, true, timeFont);
		cellTime.setCellStyle(timeStyle);
		cellTime.setCellValue(timeTitle);

		int cellIndex = 0;
		//生成表头
		rowIndex = exqBnbTongjiHeader(headStyle, headFont, sheet, rowIndex, cellIndex);

		// 生成Excel表格主体
		exqBnbExcelBody(list, columnStyle, columnFont, sheet, rowIndex);
		FileOutputStream os = null;
		try {
			// 文件输出流
			os = new FileOutputStream(folderPath + titleName + ".xls");
			wb.write(os);
			wb.close();
		} catch (IOException e) {
			LOG.error("帮你办统计表生成Excel出错:" + e);
			e.printStackTrace();
		}
		LOG.info(">>>>>>  帮你办统计生成Excel结束  >>>>>>");
		return titleName;
	}

	/**  
	 * <p>Title: exqBnbExcelBody</p>  
	 * <p>Description: 生成Excel表格主体</p> 
	 * @param: @param list
	 * @param: @param columnStyle
	 * @param: @param columnFont
	 * @param: @param sheet
	 * @param: @param rowIndex 
	 * @return void
	 * @throws
	 * @author ming.yi
	 * createTime：2019年2月21日 下午7:34:56 
	 */  
	private void exqBnbExcelBody(List<BnbTongji> list, HSSFCellStyle columnStyle, HSSFFont columnFont, HSSFSheet sheet,
			int rowIndex) {
		int cellIndex;
		HSSFRow rowColumnValue = null;
		HSSFCell cellColumnValue = null;
		setFont(columnFont, 10, false, "宋体");
		setCellStyle(columnStyle, true, true, columnFont);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				BnbTongji bean = list.get(i);
				rowColumnValue = sheet.createRow(rowIndex + i);
				rowColumnValue.setHeight((short) 400);
				cellIndex = 0;
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(bean.getOrderMonth());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(bean.getJdzs());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(bean.getBjzs());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(bean.getBjsWgxt());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(bean.getBjsDjxt());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(bean.getBjsYlsx());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(bean.getBjsElsx());
			}
		}
	}

	/**  
	 * <p>Title: exqBnbTongjiHeader</p>  
	 * <p>Description: 生成帮你办统计表头</p> 
	 * @param: @param headStyle
	 * @param: @param headFont
	 * @param: @param sheet
	 * @param: @param rowIndex
	 * @param: @param cellIndex
	 * @param: @return 
	 * @return int
	 * @throws
	 * @author ming.yi
	 * createTime：2019年2月21日 下午7:32:13 
	 */  
	private int exqBnbTongjiHeader(HSSFCellStyle headStyle, HSSFFont headFont, HSSFSheet sheet, int rowIndex,
			int cellIndex) {
		HSSFRow rowColumnName = sheet.createRow(rowIndex++);
		rowColumnName.setHeight((short) 500);
		setFont(headFont, 12, true, "宋体");
		setCellStyle(headStyle, true, true, headFont);
		
		HSSFCell cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("接单时间");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("接单总数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("办结总数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("网格员办件数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("党员办件数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("一类事项办件数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("二类事项办件数");
		return rowIndex;
	}

}
