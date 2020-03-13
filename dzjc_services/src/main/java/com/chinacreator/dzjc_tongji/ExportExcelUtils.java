package com.chinacreator.dzjc_tongji;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcelUtils {
	/**单元格赋值
	 * @param cell
	 * @param value
	 *
	private static void setCellValue(HSSFCell cell,Object value){
		setCellValue(cell, value, null);
	}
	/**单元格赋值
	 * @param cell
	 * @param value
	 * @param style
	 */
	private static void setCellValue(HSSFCell cell,Object value,HSSFCellStyle style){
		if(cell==null)return;
		if(style!=null)cell.setCellStyle(style);
		if(value!=null){
			if(value instanceof java.math.BigDecimal){
				java.math.BigDecimal temp = (java.math.BigDecimal)value;
				cell.setCellValue(temp.doubleValue());
			}else if(value instanceof String){
				String temp = (String)value;
				cell.setCellValue(temp);
			}else if(value instanceof Integer){
				Integer temp = (Integer)value;
				cell.setCellValue(temp);
			}else if(value instanceof Double){
				Double temp = (Double)value;
				cell.setCellValue(temp);
			}else if(value instanceof Float){
				Float temp = (Float)value;
				cell.setCellValue(temp);
			}
		}
	}
	
	/**填充样式
	 * @param style
	 * @param isAlign 是否水平垂直居中
	 * @param isBorder 是否添加边框
	 */
	private static void setCellStyle(HSSFCellStyle style,boolean isAlign,boolean isBorder,HSSFFont font){
		if(style==null)return;
		// 自动换行  
		style.setWrapText(true); 
		if(isAlign){
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		}
		if(isBorder){
			//设置边框
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		}
		if(font!=null){
			style.setFont(font);
		}
	}
	/**设置字体样式
	 * @param font
	 * @param fontHeightInPoints 字体大小
	 * @param isBoldweight 是否字体加粗
	 * @param fontName 字体 ["宋体","楷体"]
	 */
	private static void setFont(HSSFFont font,Integer fontHeightInPoints,boolean isBoldweight,String fontName){
		if(font==null)return;
		if(fontHeightInPoints!=null){
			font.setFontHeightInPoints(fontHeightInPoints.shortValue());
		}
		if(isBoldweight){
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		}
		if(StringUtils.isNotBlank(fontName)){
			font.setFontName(fontName);
		}
	}
	/**办件统计Excel导出
	 * @param list List<TongJi> list
	 * @return byte[]
	 */
	public static byte[] expExcelBanJianTongJi(List<TongJi> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle columnStyle = wb.createCellStyle();
		// 自动换行  
		columnStyle.setWrapText(true); 
		// 生成一个字体
		HSSFFont fontColumn = wb.createFont();
		
		setFont(fontColumn, 10, true, "宋体");
		setCellStyle(columnStyle, true, false,fontColumn);

		
		HSSFSheet sheet = wb.createSheet("全部办件查询");
		int rowIndex = 0;
		HSSFRow rowTitle = sheet.createRow(rowIndex++);
		HSSFRow rowTime = sheet.createRow(rowIndex++);
		HSSFRow rowColumnName = sheet.createRow(rowIndex++);
		HSSFCell cellTitle = rowTitle.createCell(0);
		HSSFCell cellTime = rowTime.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 14));
		int cellIndex=0;
		HSSFCell cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("行政区划");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("收件数(件)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("办结数(件)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("办结率(%)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("提速率(%)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("办件黄牌数(张)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("办件红牌数(张)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("投诉黄牌数(张)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("投诉红牌数(张)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("咨询黄牌数(张)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("咨询红牌数(张)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("投诉数(件)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("投诉回复数(件)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("咨询数(件)");
		cellColumnName = rowColumnName.createCell(cellIndex++);cellColumnName.setCellValue("咨询回复数(件)");

		HSSFRow rowColumnValue = null;
		HSSFCell cellColumnValue = null;
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				TongJi tongji = list.get(i);
				rowColumnValue = sheet.createRow(rowIndex+i);
				cellIndex = 0;
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getAreaOrgName());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getAcceptNum().toString());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getFinishNum().toString());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getBanJieLvFloat());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getTiSuLvFloat());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getInstanceSuperviseYelloNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getInstanceSuperviseRedNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getComplainSuperviseYelloNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getComplainSuperviseRedNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getConsultSuperviseYelloNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getConsultSuperviseRedNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getComNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getComplainReplyNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getConsultNum());
				cellColumnValue = rowColumnValue.createCell(cellIndex++);cellColumnValue.setCellValue(tongji.getConsultReplayNum());
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return os.toByteArray();
	}
	
	/**文件下载
	 * @param response	HttpServletResponse
	 * @param os	文件流
	 * @param filename 文件名
	 */
	public static void download(HttpServletResponse response,byte[] os,String filename) {
		try {
			if(os!=null&&os.length>0&&StringUtils.isNotBlank(filename)){
				// 取得文件名。
				filename = URLEncoder.encode(filename, "UTF-8");
				// 以流的形式下载文件。
				response.reset();// 清空response
				response.addHeader("Content-Disposition", "attachment;filename=" + filename);
				response.addHeader("Content-Length", "" + os.length);
				response.setContentType("application/vnd.ms-excel;charset=UTF-8");
				OutputStream outs;
				outs = response.getOutputStream();
				outs.write(os);
				outs.flush();
				outs.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**文件下载
	 * @param response	HttpServletResponse
	 * @param os	文件流
	 * @param filename 文件名
	 */
	public static void download(HttpServletRequest request,HttpServletResponse response,byte[] os,String filename) {
		try {
			if(os!=null&&os.length>0&&StringUtils.isNotBlank(filename)){
				// 取得文件名。
				filename = processFileName(request,filename);
				// 以流的形式下载文件。
				response.reset();// 清空response
				response.addHeader("Content-Disposition", "attachment;filename=" + filename);
				response.addHeader("Content-Length", "" + os.length);
				response.setContentType("application/vnd.ms-excel;charset=UTF-8");
				OutputStream outs;
				outs = response.getOutputStream();
				outs.write(os);
				outs.flush();
				outs.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 解决设置名称时的乱码
	 * @param request
	 * @param fileNames
	 * @return
	 */
	public static String processFileName(HttpServletRequest request, String filename) {
		String codedfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("Firefox")) {
				//浏览器为火狐
				codedfilename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			}else{
				codedfilename = URLEncoder.encode(filename, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codedfilename;
	}
}
