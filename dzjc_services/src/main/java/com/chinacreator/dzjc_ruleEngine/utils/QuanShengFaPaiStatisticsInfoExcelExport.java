package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.chinacreator.dzjc_tongji.TongJi;

public class QuanShengFaPaiStatisticsInfoExcelExport {
	
	public String folderPath = this.getClass().getResource("/").getPath();
	
	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName, List<List<TongJi>> dataList) {
		/** 创建相关参数 */

		// 第一行，上层表头文字，一个表头合并多少行，就重复写多少个
		String[] coloumItems1 = { "行政区划/组织机构", "预警牌数(张)","黄牌数(张)","红牌数(张)","撤销","撤销"};

		// 第二行，上层表头坐标，顺序对应上层表头文字，重复的表头只存一个
		String[] number1 = { "1,2,0,0", "1,2,1,1", "1,2,2,2","1,2,3,3","1,1,4,5"};

		// 第三行，下层表头文字
		String[] coloumItems2 = { "黄牌数(张)","红牌数(张)"};

		// 第四行，下层表头坐标，顺序对应下层表头文字（暂时没用）
		String[] number2 = { };

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, coloumItems2, number2, dataList);
	}
	
	/**
	 * Excel写入数据
	 * 
	 */
	private void writeExcel(String titleName, String[] coloumItems1, String[] number1, String[] coloumItems2,
			String[] number2, List<List<TongJi>> dataList) {

		// 创建文件
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}

		FileOutputStream fileOutputStream = null;
		try {

			//2, 文件输出流
			fileOutputStream = new FileOutputStream(folderPath + titleName + ".xls");

			//3, 创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();

			//4, 在workbook中添加一个sheet,对应Excel文件中的sheet 
			HSSFSheet sheet = workbook.createSheet();

			//5, 在sheet中设置某一列列宽（列序号，宽度的值)  
			sheet.setColumnWidth((short) 0, (short) 40 * 256);// 行政区划/组织机构
			
			sheet.setColumnWidth((short) 1, (short) 20 * 256);
			sheet.setColumnWidth((short) 2, (short) 20 * 256);
			sheet.setColumnWidth((short) 3, (short) 20 * 256);
			sheet.setColumnWidth((short) 4, (short) 20 * 256);
			sheet.setColumnWidth((short) 5, (short) 20 * 256);
			
			//6, 设置第一行大标题
			createTag1(workbook, sheet, titleName);

			//7, 设置第二、三组合行的表格表头
			createTag2(workbook, sheet, coloumItems1, number1, coloumItems2, number2);

			//8, 设置表格内容
			createValue(workbook, sheet, dataList);

			//9, 开始写入数据
			workbook.write(fileOutputStream);

			//10,关闭流
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

		// 第一行，大标题
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
		cell.setCellValue(titleName);

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
		HSSFRow row = sheet.createRow(1);// 获取第二行，作为上层的标头
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
		row = sheet.createRow(2);// 获取第三行，作为下层的标头
		row.setHeightInPoints(30);

		// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

		for (int i = 0; i < coloumItems1.length; i++) {

			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(styles);

			for (int j = 0; j < coloumItems2.length; j++) {

				// 下层行，因为是第一列才开始合并，所以 +1
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
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet, List<List<TongJi>> dataList) {

		HSSFRow row = null;
		HSSFCell cell = null;

		int index = 2;// 从第四行开始（下标应为3，但是会在内循环中自增）
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
				TongJi tongJi = dataList.get(i).get(j);

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
				cell.setCellValue(tongJi.getAreaOrgName());
				
				//预警牌数(张)
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(tongJi.getSuperviseGreenNum());
				//黄牌数(张)
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(tongJi.getSuperviseYelloNum());
				//红牌数(张)
				cell = row.createCell(3);
				cell.setCellStyle(styles);
				cell.setCellValue(tongJi.getSuperviseRedNum());
				//撤销-黄牌数(张)
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				cell.setCellValue(tongJi.getCancelYellowNum());
				//撤销-红牌数(张)
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				cell.setCellValue(tongJi.getCancelRedNum());
				
			}
		}

	}
	
	
	
	

}
