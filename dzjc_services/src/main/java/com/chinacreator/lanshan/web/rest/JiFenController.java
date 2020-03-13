package com.chinacreator.lanshan.web.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
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
import com.chinacreator.lanshan.JiFen;
import com.chinacreator.lanshan.util.ExcelUtil;

@Controller
@Path("lsx_bnb_JiFen")
@Consumes(MediaType.APPLICATION_JSON)
public class JiFenController extends ExcelUtil {

	/** 系统日志 */
	private static final Logger LOG = LoggerFactory.getLogger(JiFenController.class);
	public String folderPath = this.getClass().getResource("/").getPath();
	
	@POST
	@Path("/{redeemId}")
	public JiFen update(JiFen entity) {
		DaoFactory.create(JiFen.class).update(entity);
		return entity;
	}

	@POST
	@Path("")
	public JiFen insert(JiFen entity) {
		DaoFactory.create(JiFen.class).insert(entity);
		return entity;
	}

	@GET
	@Path("/{redeemId}")
	public JiFen get(@PathParam(value = "redeemId") java.lang.String redeemId) {
		return DaoFactory.create(JiFen.class).selectByID(redeemId);
	}

	@DELETE
	@Path("")
	public void deleteBatch(@QueryParam(value = "id") List<java.lang.String> ids) {
		DaoFactory.create(JiFen.class).deleteBatch(ids);
	}

	@DELETE
	@Path("/{redeemId}")
	public void delete(@PathParam(value = "redeemId") java.lang.String redeemId) {
		JiFen entity = new JiFen();
		entity.setRedeemId(redeemId);
		DaoFactory.create(JiFen.class).delete(entity);
	}

	@GET
	@Path("score")
	public Page<JiFen> getPageList(@QueryParam(value = "page") int page, @QueryParam(value = "rows") int rows,
			@QueryParam(value = "sidx") String sidx, @QueryParam(value = "sord") String sord,
			@QueryParam(value = "cond") String cond) {
		Pageable pageable = new Pageable(page, rows);
		Sortable sortable = SortableUtil.getSortable(sidx, sord);
		RowBounds4Page rowbound = new RowBounds4Page(pageable, sortable, null, true);
		JiFen entity = StringUtils.isNotBlank(cond) ? JSON.parseObject(cond, JiFen.class) : new JiFen();
		Map<String,String> params = new HashMap<String,String>();
		if (entity != null) {
			if (entity.getBeginDate().toString() != null) {
				params.put("beginDate", entity.getBeginDate().toString());
			}
			if (entity.getEndDate().toString() != null) {
				params.put("endDate", entity.getEndDate().toString());
			}
		}
		List<JiFen> list = DaoFactory.create(JiFen.class).getSession()
				.selectList("com.chinacreator.lanshan.JiFenMapper.selectByPage", params, rowbound);
		Page<JiFen> selectPageByCondition = new Page<JiFen>(page, rows, rowbound.getTotalSize(), list);
		return selectPageByCondition;
	}
	
	/**
	 * 生成帮你办积分兑换导出Excel工作簿
	 * <p>Title: exportExcelJiFen</p>  
	 * <p>Description: TODO</p> 
	 * @param: @param beginDate
	 * @param: @param endDate
	 * @param: @return 
	 * @return String
	 * @throws
	 * @author ming.yi
	 * createTime：2019年3月29日 上午11:05:30
	 */
	public String exportExcelJiFen(String beginDate,String endDate) {
		LOG.info(">>>>>>>>>>>>  帮你办积分兑换统计生成Excel开始    >>>>>>>>>>>>>");
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
		List<JiFen> list  = DaoFactory.create(JiFen.class).getSession()
				.selectList("com.chinacreator.lanshan.JiFenMapper.selectByPage", params);

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

		HSSFSheet sheet = wb.createSheet("帮你办积分兑换统计");
		// 设置列宽
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 3500);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 3500);
		sheet.setColumnWidth(8, 3500);
		sheet.setColumnWidth(9, 3500);

		String titleName = "帮你办积分兑换统计";
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
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		HSSFRow rowTitle = sheet.createRow(rowIndex++);
		HSSFCell cellTitle = rowTitle.createCell(0);
		setFont(titleFont, 15, true, "黑体");
		setCellStyle(titleStyle, true, false, titleFont);
		rowTitle.setHeight((short) 700);// 行高
		cellTitle.setCellStyle(titleStyle);
		cellTitle.setCellValue("帮你办积分兑换统计");

		// 日期行
		if (timeTitle != null && !"".equals(timeTitle)) {
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));
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
			LOG.error("帮你办积分兑换统计生成Excel出错:" + e);
			e.printStackTrace();
		}
		LOG.info(">>>>>>>>>>>>  帮你办积分兑换统计生成Excel结束  >>>>>>>>>>>>>");
		return titleName;
	}
	
	/**
	 * 生成帮你办积分兑换统计表格内容
	 * <p>Title: exqBodyCell</p>  
	 * <p>Description: TODO</p> 
	 * @param: @param sdf
	 * @param: @param list
	 * @param: @param columnStyle
	 * @param: @param columnFont
	 * @param: @param sheet
	 * @param: @param rowIndex 
	 * @return void
	 * @throws
	 * @author ming.yi
	 * createTime：2019年3月28日 下午4:27:02
	 */
	private void exqBodyCell(List<JiFen> list, HSSFCellStyle columnStyle,
			HSSFFont columnFont, HSSFSheet sheet, int rowIndex) {
		int cellIndex;
		HSSFRow rowColumnValue = null;
		HSSFCell cellColumnValue = null;
		setFont(columnFont, 10, false, "宋体");
		setCellStyle(columnStyle, true, true, columnFont);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				JiFen jf = list.get(i);
				rowColumnValue = sheet.createRow(rowIndex + i);
				rowColumnValue.setHeight((short) 400);
				cellIndex = 0;
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getCommunity());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getGridName());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getBlsxs());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getYlsx());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getElsx());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getYlsxScore());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getElsxScore());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getGoodPoints());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getTotalScore());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);
				cellColumnValue.setCellStyle(columnStyle);
				cellColumnValue.setCellValue(jf.getTotalMoney());
			}
		}
	}
	
	/**
	 * 生成帮你办积分兑换统计Excel表表头
	 * <p>Title: exqHeader</p>  
	 * <p>Description: TODO</p> 
	 * @param: @param headStyle
	 * @param: @param headFont
	 * @param: @param sheet
	 * @param: @param rowIndex
	 * @param: @return 
	 * @return int
	 * @throws
	 * @author ming.yi
	 * createTime：2019年3月28日 下午4:13:07
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
		cellColumnName.setCellValue("社区");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("网格员/党员");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("办理事项总数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("一类事项数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("二类事项数");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("一类事项积分");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("二类事项积分");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("好评积分");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("总积分");
		cellColumnName = rowColumnName.createCell(cellIndex++);
		cellColumnName.setCellStyle(headStyle);
		cellColumnName.setCellValue("总金额");
		return rowIndex;
	}
}
