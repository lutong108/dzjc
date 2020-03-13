package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import com.chinacreator.dzjc_ruleEngine.ZwfwInstanceInfo;


public class ZwfwInstanceExcelExport {
	
	public String folderPath = this.getClass().getResource("/").getPath();
	
	/**
	 * Excel导入准备工作
	 */
	public byte[] excelExport(String titleName, List<List<ZwfwInstanceInfo>> dataList) {

		// 第三行，下层表头文字
		String[] coloumItems1 = { "项目名称", "业务类型", "发牌状态", "受理时间", "发牌结果", "发牌类型", "机构名称","办理状态","办结时间" };

		// 第三行，下层表头坐标，顺序对应下层表头文字
		String[] number1 = { "1,1,1,1", "1,1,2,2", "1,1,3,3","1,1,4,4", "1,1,5,5", "1,1,6,6", "1,1,7,7","1,1,8,8","1,1,9,9"};

		// 开始写表格
		return writeExcel(titleName, coloumItems1, number1, dataList);
	}
	
	/**
	 * Excel写入数据
	 */
	private byte[] writeExcel(String titleName, String[] coloumItems1, String[] number1,  List<List<ZwfwInstanceInfo>> dataList) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {

			// 文件输出流

			// 创建Excel设计样式
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 获取列信息
			HSSFSheet sheet = workbook.createSheet();
			sheet.setColumnWidth((short) 0, (short) 40 * 256);// 办件名称
			sheet.setColumnWidth((short) 1, (short) 12 * 256);// 业务类型
			sheet.setColumnWidth((short) 2, (short) 12 * 256);// 发牌状态
			sheet.setColumnWidth((short) 3, (short) 16 * 256);// 受理时间
			sheet.setColumnWidth((short) 4, (short) 12 * 256);// 发牌结果
			sheet.setColumnWidth((short) 5, (short) 16 * 256);// 发牌类型
			sheet.setColumnWidth((short) 6, (short) 16 * 256);// 机构名称
			sheet.setColumnWidth((short) 7, (short) 16 * 256);// 办理状态
			sheet.setColumnWidth((short) 8, (short) 22 * 256);// 办结时间
			// 设置第一行大标题
			createTag1(workbook, sheet, titleName);

			// 设置第二的表格表头
			createTag2(workbook, sheet, coloumItems1, number1);

			// 设置表格内容
			createValue(workbook, sheet, dataList);

			// 开始写入数据
			workbook.write(os);

			// 关闭流
			os.close();
			
			return os.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 创建第一行的大标题
	 * 
	 * @param workbook
	 * @param sheet
	 * @param style
	 */
	private static void createTag1(HSSFWorkbook workbook, HSSFSheet sheet, String titleName) {
		HSSFRow row;
		// 第一行，标题大小设置
        Font headfonts = workbook.createFont();
        headfonts.setFontHeightInPoints((short) 15);// 字体大小 
        headfonts.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
        // 第二行，标题大小设置
        Font headfont_ = workbook.createFont();
        headfont_.setFontHeightInPoints((short) 12);// 字体大小
        
        //第一行，标题样式设置
        CellStyle styles = workbook.createCellStyle();
        styles.setFont(headfonts);
        styles.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中
        styles.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中
        styles.setBorderRight(CellStyle.BORDER_THIN);
        styles.setBorderTop(CellStyle.BORDER_THIN);
        styles.setBorderLeft(CellStyle.BORDER_THIN);//左边框    
        styles.setBorderRight(CellStyle.BORDER_THIN);//右边框    
        
        //第二行，标题样式设置
        CellStyle style_ = workbook.createCellStyle();
        style_.setFont(headfont_);
        style_.setAlignment(CellStyle.ALIGN_RIGHT);// 右
        style_.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        
		// 第一行，标题
		row = sheet.createRow(0);// 获取第一行，作为总标题文字
		row.setHeightInPoints(60);// 设置的值永远是height属性值的60倍
		HSSFCell cell = row.createCell(0);// 创建列
		cell.setCellValue("全部办件查询");
		cell.setCellStyle(styles);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));// 传入坐标，合并坐标内的单元格
		
		// 第二行，标题
		row =sheet.createRow(1);
		row.setHeightInPoints(35);
		 cell = row.createCell(0);// 创建列
	    titleName=titleName.substring(6);
	    if(titleName.contains("-")){
	    	titleName=titleName.replaceAll("-", "/");
	    }
	    cell.setCellValue(titleName);
	    cell.setCellStyle(style_);
	    sheet.addMergedRegion(new CellRangeAddress(1,1,0,8));
	}
	
	/**
	 * 创建第二的表格表头
	 */
	private void createTag2(HSSFWorkbook workbook, HSSFSheet sheet, String[] coloumItems1, String[] number1) {

		// 因为第二行为复杂合并行，自定义样式写在最上边
		HSSFCellStyle styles = workbook.createCellStyle();
		styles.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 文本居中
		styles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 并垂直居中
		styles.setWrapText(true);// 自动换行

		// 设置边框
		styles.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		styles.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styles.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styles.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		styles.setFont(font);

		// 第二行，上层表头列名
		HSSFRow row = sheet.createRow(2);// 获取第二行，作为上层的标头
		row.setHeightInPoints(30);

		// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

		for (int i = 0; i < coloumItems1.length; i++) {

			HSSFCell cell = row.createCell(i);
			cell.setCellValue(coloumItems1[i]);

			cell.setCellStyle(styles);
		}	
	}
	
	/**
	 * 设置表格内容
	 */
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet, List<List<ZwfwInstanceInfo>> dataList) {

		HSSFRow row = null;
		HSSFCell cell = null;

		int index = 2;// 从第四行开始（下标应为3，但是会在内循环中自增）
		// 设置样式
		HSSFCellStyle styles = workbook.createCellStyle();
		// //外循环，里面每个元素都是一个集合
		int w_size = dataList.size();
		// 设置字体颜色

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);// 黑色
		styles.setFont(font);
		
		styles.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 文本居中
		styles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 并垂直居中
		styles.setWrapText(true);// 自动换行

		// 设置边框
		styles.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		styles.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styles.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styles.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		for (int i = 0; i < w_size; i++) {

			// 内循环，里面每个元素都是一个对象
			int n_size = dataList.get(i).size();
			for (int j = 0; j < n_size; j++) {

				index++;// 每次自增，插入一行数据
				row = sheet.createRow(index);
				row.setHeightInPoints(30);// 设置行高，值永远是height属性值的30倍

				// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

				// 实体对象
				ZwfwInstanceInfo item = dataList.get(i).get(j);
				
				//办件名称
				cell = row.createCell(0);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getInstanceName());
				
				//业务类型
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				if(item.getApproveTypeCode()!=null && !item.getApproveTypeCode().equals("")){
					cell.setCellValue(item.getApproveTypeCodeName());
				} else {
					cell.setCellValue("");
				}
				//发牌状态
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				if(item.getState()!=null && !"".equals(item.getState()) ){
					cell.setCellValue(item.getState());
				} else {
					cell.setCellValue("");
				}
				//受理时间
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				cell = row.createCell(3);
				cell.setCellStyle(styles);
				if(item.getAcceptTime()!=null){
					cell.setCellValue(sdf.format(item.getAcceptTime()));
				}else{
					cell.setCellValue("");
				}
				//发牌结果
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getSuperviseResultName());
				//发牌类型
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getSuperviseTypeName());
				//机构名称
				cell = row.createCell(6);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getAreaOrgName());
				//办理状态
				cell = row.createCell(7);
				cell.setCellStyle(styles);
				if(item.getProcessingState()!=null && !item.getProcessingState().equals("")){
					cell.setCellValue(item.getProcessingState());
				}else{
					cell.setCellValue("");	
				}
				//办结时间
				cell = row.createCell(8);
				cell.setCellStyle(styles);
				if(item.getFinishTime()!=null && !"".equals(item.getFinishTime())){
					cell.setCellValue(sdf.format(item.getFinishTime()));	
				}else{
					cell.setCellValue("");
				}
			}
		}

	}
}
