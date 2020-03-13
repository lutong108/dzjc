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

import com.chinacreator.dzjc_complain.TaJcComplainSuggest;
import com.chinacreator.dzjc_tongji.Ta_Jc_Sum_Xxylttj;

/**
 * 湘西州村级办件统计
 */
public class VillageLevelStatisticsExport {

	public String folderPath = this.getClass().getResource("/").getPath();

	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName, List<Ta_Jc_Sum_Xxylttj> busList) {
		// 下层表头文字
		String[] coloumItems1 = { "受理单位", "受理数", "办结数","乡镇审核量","县级审核量"};
		// 下层表头坐标，顺序对应下层表头文字
		String[] number1 = { "1,1,1,1", "1,1,2,2", "1,1,3,3", "1,1,4,4","1,1,5,5"};
		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, busList);
	}

	/**
	 * Excel写入数据
	 */
	private void writeExcel(String titleName, String[] coloumItems1,String[] number1, List<Ta_Jc_Sum_Xxylttj> busList) {
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
			sheet.setColumnWidth((short) 0, (short) 30 * 256);// 标题
			sheet.setColumnWidth((short) 1, (short) 20 * 256);// 所在地区
			sheet.setColumnWidth((short) 2, (short) 20 * 256);// 姓名
			sheet.setColumnWidth((short) 3, (short) 20 * 256);// 联系方式
			sheet.setColumnWidth((short) 4, (short) 20 * 256);// 建议1
			
			// 设置第一行大标题
			createTag1(workbook, sheet, titleName);
			// 设置第二的表格表头
			createTag2(workbook, sheet, coloumItems1, number1);
			// 设置表格内容
			createValue(workbook, sheet, busList);
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

		// 第一行，大标题
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));// 传入坐标，合并坐标内的单元格
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
		HSSFRow row = sheet.createRow(1);// 获取第二行，作为上层的标头
		row.setHeightInPoints(60);

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
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet,List<Ta_Jc_Sum_Xxylttj> busList) {

		HSSFRow row = null;
		HSSFCell cell = null;
		int index = 1;// 从第四行开始（下标应为3，但是会在内循环中自增）
		// 设置样式-- 放外面，修复数据量大报错
		HSSFCellStyle styles = workbook.createCellStyle();
		int n_size = busList.size();
		for (int j = 0; j < n_size; j++) {
			index++;// 每次自增，插入一行数据
			row = sheet.createRow(index);
			row.setHeightInPoints(60);// 设置行高，值永远是height属性值的30倍
			// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框
			// 实体对象
			Ta_Jc_Sum_Xxylttj obj = busList.get(j);
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
			
			// 标题
			cell = row.createCell(0);
			cell.setCellStyle(styles);
			cell.setCellValue(obj.getShareName());

			// 所在地区
			cell = row.createCell(1);
			cell.setCellStyle(styles);
			cell.setCellValue(obj.getSlsNum());
			
			// 姓名
			cell = row.createCell(2);
			cell.setCellStyle(styles);
			cell.setCellValue(obj.getBjsNum());
			
			// 联系方式
			cell = row.createCell(3);
			cell.setCellStyle(styles);
			cell.setCellValue(obj.getXzshlNum());
			
			// 建议1
			cell = row.createCell(4);
			cell.setCellStyle(styles);
			cell.setCellValue(obj.getXjshlNum());
		
		}
	}
}
