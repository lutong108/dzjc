package com.chinacreator.dzjc_ruleEngine.utils;

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

import com.chinacreator.dzjc_ruleEngine.SuperviserInfoConsult;

public class ConsultExport {
	public String folderPath = this.getClass().getResource("/").getPath();
	
	
	
	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName,
			List<List<SuperviserInfoConsult>> dataList) {

		/** 创建相关参数 */

		// 下层表头文字
		String[] coloumItems1 = { "监管结果", "监管类型","咨询标题","咨询时间", "咨询单位", "申请者名称","回复时间","回复单位名称","回复人","监管时间","是否公开","咨询状态"};

		// 下层表头坐标，顺序对应下层表头文字
		String[] number1 = { "1,1,1,1", "1,1,2,2", "1,1,3,3", "1,1,4,4",
				"1,1,5,5", "1,1,6,6", "1,1,7,7" , "1,1,8,8", "1,1,9,9", "1,1,10,10","1,1,11,11"};

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, dataList);

	}
	
	/**
	 * Excel写入数据
	 * 
	 */
	private void writeExcel(String titleName, String[] coloumItems1,
			String[] number1, List<List<SuperviserInfoConsult>> dataList) {

		// 创建文件
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}

		FileOutputStream fileOutputStream = null;
		try {

			// 文件输出流
			fileOutputStream = new FileOutputStream(folderPath + titleName
					+ ".xls");

			// 创建Excel设计样式
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 获取列信息
			HSSFSheet sheet = workbook.createSheet();
			sheet.setColumnWidth((short) 0, (short) 12 * 256);// 监察结果
			sheet.setColumnWidth((short) 1, (short) 16 * 256);// 监察类型
			sheet.setColumnWidth((short) 2, (short) 40 * 256);// 咨询标题
			sheet.setColumnWidth((short) 3, (short) 16 * 256);// 咨询单位名称
			sheet.setColumnWidth((short) 4, (short) 16 * 256);// 申请者名称
			sheet.setColumnWidth((short) 5, (short) 16 * 256);// 回复单位名称
			sheet.setColumnWidth((short) 6, (short) 16 * 256);// 回复人
			sheet.setColumnWidth((short) 7, (short) 16 * 256);// 咨询时间
			sheet.setColumnWidth((short) 8, (short) 16 * 256);// 到期时间
			sheet.setColumnWidth((short) 9, (short) 12 * 256);// 是否公开
			sheet.setColumnWidth((short) 10, (short) 12 * 256);// 咨询状态
			sheet.setColumnWidth((short) 11, (short) 16 * 256);// 咨询状态
			// 设置第一行大标题
			createTag1(workbook, sheet, titleName);

			// 设置第二的表格表头
			createTag2(workbook, sheet, coloumItems1, number1);

			// 设置表格内容
			createValue(workbook, sheet, dataList);

			// 开始写入数据
			workbook.write(fileOutputStream);

			// 关闭流
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	/**
	 * 创建第一行的大标题
	 * 
	 * @param workbook
	 * @param sheet
	 * @param style
	 */
	private void createTag1(HSSFWorkbook workbook, HSSFSheet sheet,
			String titleName) {

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
		cell.setCellValue("咨询监管信息情况表");
		cell.setCellStyle(styles);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));// 传入坐标，合并坐标内的单元格
		
		// 第二行，标题
		row =sheet.createRow(1);
		row.setHeightInPoints(35);
		 cell = row.createCell(0);// 创建列
	    titleName=titleName.substring(9);
	    if(titleName.contains("-")){
	    	titleName=titleName.replaceAll("-", "/");
	    }
	    cell.setCellValue(titleName);
	    cell.setCellStyle(style_);
	    sheet.addMergedRegion(new CellRangeAddress(1,1,0,11));

	}
	
	/**
	 * 创建第二的表格表头
	 * 
	 * @param tags
	 * @param s
	 */
	private void createTag2(HSSFWorkbook workbook, HSSFSheet sheet,
			String[] coloumItems1, String[] number1) {

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
	 * 
	 * @param res
	 * @param s
	 */
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet,
			List<List<SuperviserInfoConsult>> dataList) {

		HSSFRow row = null;
		HSSFCell cell = null;

		int index = 2;// 从第四行开始（下标应为3，但是会在内循环中自增）
		
		
		// 设置样式-- 放外面，修复数据量大报错
		HSSFCellStyle styles = workbook.createCellStyle();
		
		
		

		// //外循环，里面每个元素都是一个集合
		int w_size = dataList.size();
		for (int i = 0; i < w_size; i++) {

			// 内循环，里面每个元素都是一个对象
			int n_size = dataList.get(i).size();
			for (int j = 0; j < n_size; j++) {

				index++;// 每次自增，插入一行数据
				row = sheet.createRow(index);
				row.setHeightInPoints(30);// 设置行高，值永远是height属性值的30倍

				// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

				// 实体对象
				SuperviserInfoConsult consult = dataList.get(i).get(j);

				/*// 设置样式 --移到循环外，修复数据量大4000错误
				HSSFCellStyle styles = workbook.createCellStyle();*/

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

				// 监察结果
				cell = row.createCell(0);
				cell.setCellStyle(styles);
				cell.setCellValue(consult.getSuperviseResultName());

				// 监察类型
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(consult.getSuperviseTypeName());

				// 咨询标题
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(consult.getConsultTitle());
				
				// 咨询时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cell = row.createCell(3);
				cell.setCellStyle(styles);

				if (consult.getConsultTime() != null
						&& consult.getConsultTime().toString() != "") {
					cell.setCellValue(sdf.format(consult.getConsultTime()));
				} else {
					cell.setCellValue("");
				}
				// 咨询单位名称
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				cell.setCellValue(consult.getOrgName());
				// 申请者名称
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				cell.setCellValue(consult.getApplyName());
				//回复时间
				cell = row.createCell(6);
				cell.setCellStyle(styles);

				if (consult.getReplyTime() != null
						&& consult.getReplyTime().toString() != "") {
					cell.setCellValue(sdf.format(consult.getReplyTime()));
				} else {
					cell.setCellValue("");
				}
				// 回复单位名称
				cell = row.createCell(7);
				cell.setCellStyle(styles);
				cell.setCellValue(consult.getReplyOrg());
				
				// 回复人
				cell = row.createCell(8);
				cell.setCellStyle(styles);
				cell.setCellValue(consult.getReplyName());
				
				
				

				// 监察时间
				cell = row.createCell(9);
				cell.setCellStyle(styles);

				if (consult.getSuperviseTime() != null
						&& consult.getSuperviseTime().toString() != "") {
					cell.setCellValue(sdf.format(consult.getSuperviseTime()));
				} else {
					cell.setCellValue("");
				}
				// 是否公开
				cell = row.createCell(10);
				cell.setCellStyle(styles);
				if(consult.getIsOpen()!=null &&  !"".equals(consult.getIsOpen())){
					if("Y".equals(consult.getIsOpen())){
						consult.setIsOpen("是");
					}else{
						consult.setIsOpen("否");
					}
					cell.setCellValue(consult.getIsOpen());
				}else{
					cell.setCellValue("");
				}
				
				// 咨询状态
				cell = row.createCell(11);
				cell.setCellStyle(styles);
				if(consult.getIsValid()!=null && !"".equals(consult.getIsValid())){
					cell.setCellValue(consult.getIsValid());
				}else{
					cell.setCellValue("");
				}
				

			}
		}

	}
	
	
}
