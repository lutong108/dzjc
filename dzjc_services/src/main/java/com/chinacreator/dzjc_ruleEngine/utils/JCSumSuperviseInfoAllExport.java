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

import com.chinacreator.dzjc_ruleEngine.BussinessAllInfo;
import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;


public class JCSumSuperviseInfoAllExport {
	
	
	public String folderPath = this.getClass().getResource("/").getPath();
	
	
	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName, List<List<JCSumSuperviseInfoAll>> dataList) {
		/** 创建相关参数 */
		// 第一行为大标题
		// titleName

		// 第二行，上层表头文字，一个表头合并多少行，就重复写多少个
		String[] coloumItems1 = { "行政区划/组织机构", "预警", "黄牌", "红牌", "撤销", "撤销"};

		// 第二行，上层表头坐标，顺序对应上层表头文字，重复的表头只存一个
		String[] number1 = { "2,3,0,0", "2,3,1,1", "2,3,2,2", "2,3,3,3","2,2,4,5"};
		/*String[] number1 = { "1,2,0,0", "1,2,1,1", "1,2,2,2", "1,2,3,3","1,1,4,5"};*/
		// 第三行，下层表头文字
		String[] coloumItems2 = { "黄牌", "红牌" };

		// 第三行，下层表头坐标，顺序对应下层表头文字
		String[] number2 = { "3,3,4,4", "3,3,5,5"};

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, coloumItems2, number2, dataList);
	}
	
	
	
	/**
	 * Excel写入数据
	 * 
	 */
	private void writeExcel(String titleName, String[] coloumItems1, String[] number1, String[] coloumItems2,
			String[] number2, List<List<JCSumSuperviseInfoAll>> dataList) {

		// 创建文件
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}

		FileOutputStream fileOutputStream = null;
		try {

			// 文件输出流
			fileOutputStream = new FileOutputStream(folderPath + titleName + ".xls");

			// 创建Excel设计样式
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 获取列信息
			HSSFSheet sheet = workbook.createSheet();

			// 调整每列的列宽
			sheet.setColumnWidth((short) 0, (short) 40 * 256);// 行政区划/组织机构
			sheet.setColumnWidth((short) 1, (short) 16 * 256);// 预警
			sheet.setColumnWidth((short) 2, (short) 16 * 256);// 黄牌
			sheet.setColumnWidth((short) 3, (short) 16 * 256);// 红牌
			sheet.setColumnWidth((short) 4, (short) 16 * 256);// 黄牌
			sheet.setColumnWidth((short) 5, (short) 16 * 256);// 红牌
	


			// 设置第一行大标题
			createTag1(workbook, sheet, titleName);

			// 设置第二、三组合行的表格表头
			createTag2(workbook, sheet, coloumItems1, number1, coloumItems2, number2);

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
	private static void createTag1(HSSFWorkbook workbook, HSSFSheet sheet, String titleName) {

/*		// 第一行，大标题
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));// 传入坐标，合并坐标内的单元格
		HSSFRow row = sheet.createRow(0);// 获取第一行，作为总标题文字
		row.setHeightInPoints(60);// 设置的值永远是height属性值的60倍

		// 自定义该行样式
		HSSFCellStyle styles = workbook.createCellStyle();
		styles.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 文本居中
		styles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 并垂直居中

		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		styles.setFont(font);

		// 注意！如果要设置单行样式，必须cell和row都同时设置！
		HSSFCell cell = row.createCell(0);// 创建列
		cell.setCellStyle(styles);// 设置表头样式
		row.setRowStyle(styles);// 设置行样式

		// 写入标题数据
		cell.setCellValue(titleName);*/
		
		
		HSSFRow row;
		// 第一行，标题大小设置
        Font headfonts = workbook.createFont();
        headfonts.setFontHeightInPoints((short) 15);// 字体大小 
        /*headfonts.setFontName("方正舒体");*/
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
		cell.setCellValue("全省发牌统计情况");
		cell.setCellStyle(styles);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));// 传入坐标，合并坐标内的单元格
		
		// 第二行，标题
		row =sheet.createRow(1);
		row.setHeightInPoints(35);
		 cell = row.createCell(0);// 创建列
	    titleName=titleName.substring(8);
	  /*  if(titleName.contains("-")){
	    	titleName=titleName.replaceAll("-", "/");
	    }*/
	    cell.setCellValue(titleName);
	    cell.setCellStyle(style_);
	    sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));


	}
	
	
	/**
	 * 创建第二、三组合行的表格表头
	 * 
	 * @param tags
	 * @param s
	 */
	private void createTag2(HSSFWorkbook workbook, HSSFSheet sheet, String[] coloumItems1, String[] number1,
			String[] coloumItems2, String[] number2) {

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

		// 动态合并单元格
		for (int i = 0; i < number1.length; i++) {

			String[] temp = number1[i].split(",");

			Integer startrow = Integer.parseInt(temp[0]);
			Integer overrow = Integer.parseInt(temp[1]);
			Integer startcol = Integer.parseInt(temp[2]);
			Integer overcol = Integer.parseInt(temp[3]);

			sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
		}

		// 第三行，下层表头列名
		row = sheet.createRow(3);// 获取第三行，作为下层的标头
		row.setHeightInPoints(30);

		// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

		for (int i = 0; i < coloumItems1.length; i++) {

			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(styles);

			for (int j = 0; j < coloumItems2.length; j++) {

				// 下层行，因为是第五列才开始合并，所以 +4
				cell = row.createCell(j + 4);
				cell.setCellValue(coloumItems2[j]);

				cell.setCellStyle(styles);
			}

		}

	}
	
	
	/**
	 * 设置表格内容
	 * 
	 * @param res
	 * @param s
	 */
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet, List<List<JCSumSuperviseInfoAll>> dataList) {

		HSSFRow row = null;
		HSSFCell cell = null;

		int index = 3;// 从第四行开始（下标应为4，但是会在内循环中自增）
		// 设置样式
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
				JCSumSuperviseInfoAll jCSumSuperviseInfoAll = dataList.get(i).get(j);

				// 设置样式
				//HSSFCellStyle styles = workbook.createCellStyle();

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
				
				//行政区划/组织机构
				cell = row.createCell(0);
				cell.setCellStyle(styles);
				cell.setCellValue(jCSumSuperviseInfoAll.getArea_name());
				
				//预警
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(jCSumSuperviseInfoAll.getYjs());
				
				//黄牌
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(jCSumSuperviseInfoAll.getYellow());
				//红牌
				cell = row.createCell(3);
				cell.setCellStyle(styles);
				cell.setCellValue(jCSumSuperviseInfoAll.getRed());
				
				
				//撤销  黄牌
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				cell.setCellValue(jCSumSuperviseInfoAll.getCancel_yellow_num());
				//撤销 红牌
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				cell.setCellValue(jCSumSuperviseInfoAll.getCancel_red_num());
				

			}
		}

	}
	

}
