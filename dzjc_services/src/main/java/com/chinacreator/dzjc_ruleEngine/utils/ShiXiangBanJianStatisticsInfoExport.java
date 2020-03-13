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

import com.chinacreator.dzjc_ruleEngine.ShiXiangBanJianStatistics;

/**
 * 完成办件和事项统计情况数据以Excel表格导出的功能
 * @author Administrator
 *
 */
public class ShiXiangBanJianStatisticsInfoExport {
	
	
	public String folderPath = this.getClass().getResource("/").getPath();
	
	
	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName, List<List<ShiXiangBanJianStatistics>> dataList) {
		/** 创建相关参数 */
		// 第一行为大标题
		// titleName

		// 第二行，上层表头文字，一个表头合并多少行，就重复写多少个
		String[] coloumItems1 = { "行政区划/组织机构",
				"事项数目", "事项数目", "事项数目",
				"事项数目", "事项数目", "事项数目",
				"事项数目", "事项数目", "事项数目",
				"事项数目", "事项数目", "事项数目",
				"收件数", "收件数", "收件数",
				"收件数", "收件数", "收件数",
				"收件数", "收件数", "收件数",
				"收件数", "收件数", "收件数",
				"办结数", "办结数", "办结数",
				"办结数", "办结数", "办结数",
				"办结数", "办结数", "办结数",
				"办结数", "办结数", "办结数"};

		// 第二行，上层表头坐标，顺序对应上层表头文字，重复的表头只存一个
		String[] number1 = { "1,2,0,0", "1,1,1,12", "1,1,13,24", "1,1,25,36"};

		// 第三行，下层表头文字
		String[] coloumItems2 = { 
			"行政许可", "行政奖励","行政裁决","行政检查","行政给付","行政强制","行政确认","行政处罚","行政征收","其他权利","公共服务","小计",
			"行政许可", "行政奖励","行政裁决","行政检查","行政给付","行政强制","行政确认","行政处罚","行政征收","其他权利","公共服务","小计",
			"行政许可", "行政奖励","行政裁决","行政检查","行政给付","行政强制","行政确认","行政处罚","行政征收","其他权利","公共服务","小计"};

		// 第三行，下层表头坐标，顺序对应下层表头文字（暂时没用）
		String[] number2 = {};

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, coloumItems2, number2, dataList);
	}
	
	
	
	/**
	 * Excel写入数据
	 * 
	 */
	private void writeExcel(String titleName, String[] coloumItems1, String[] number1, String[] coloumItems2,
			String[] number2, List<List<ShiXiangBanJianStatistics>> dataList) {

		//1, 创建文件,面向对象便于操作
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
			sheet.setColumnWidth((short) 0, (short) 20 * 256);// 行政区划/组织机构
			
			sheet.setColumnWidth((short) 1, (short) 8 * 256);
			sheet.setColumnWidth((short) 2, (short) 8 * 256);
			sheet.setColumnWidth((short) 3, (short) 8 * 256);
			sheet.setColumnWidth((short) 4, (short) 8 * 256);
			sheet.setColumnWidth((short) 5, (short) 8 * 256);
			sheet.setColumnWidth((short) 6, (short) 8 * 256);
			sheet.setColumnWidth((short) 7, (short) 8 * 256);
			sheet.setColumnWidth((short) 8, (short) 8 * 256);
			sheet.setColumnWidth((short) 9, (short) 8 * 256);
			sheet.setColumnWidth((short) 10, (short) 8 * 256);
			sheet.setColumnWidth((short) 11, (short) 8 * 256);
			sheet.setColumnWidth((short) 12, (short) 4 * 256); // 小计
			
			sheet.setColumnWidth((short) 13, (short) 8 * 256);
			sheet.setColumnWidth((short) 14, (short) 8 * 256);
			sheet.setColumnWidth((short) 15, (short) 8 * 256);
			sheet.setColumnWidth((short) 16, (short) 8 * 256);
			sheet.setColumnWidth((short) 17, (short) 8 * 256);
			sheet.setColumnWidth((short) 18, (short) 8 * 256);
			sheet.setColumnWidth((short) 19, (short) 8 * 256);
			sheet.setColumnWidth((short) 20, (short) 8 * 256);
			sheet.setColumnWidth((short) 21, (short) 8 * 256);
			sheet.setColumnWidth((short) 22, (short) 8 * 256);
			sheet.setColumnWidth((short) 23, (short) 8 * 256);
			sheet.setColumnWidth((short) 24, (short) 4 * 256); // 小计
			
			sheet.setColumnWidth((short) 25, (short) 8 * 256);
			sheet.setColumnWidth((short) 26, (short) 8 * 256);
			sheet.setColumnWidth((short) 27, (short) 8 * 256);
			sheet.setColumnWidth((short) 28, (short) 8 * 256);
			sheet.setColumnWidth((short) 29, (short) 8 * 256);
			sheet.setColumnWidth((short) 30, (short) 8 * 256);
			sheet.setColumnWidth((short) 31, (short) 8 * 256);
			sheet.setColumnWidth((short) 32, (short) 8 * 256);
			sheet.setColumnWidth((short) 33, (short) 8 * 256);
			sheet.setColumnWidth((short) 34, (short) 8 * 256);
			sheet.setColumnWidth((short) 35, (short) 8 * 256);
			sheet.setColumnWidth((short) 36, (short) 4 * 256); // 小计
			
			/*sheet.setColumnWidth((short) 4, (short) 16 * 256);// 行政许可类
			sheet.setColumnWidth((short) 5, (short) 16 * 256);// 公共服务类
			sheet.setColumnWidth((short) 6, (short) 8 * 256); // 小计
			sheet.setColumnWidth((short) 7, (short) 16 * 256);// 行政许可类
			sheet.setColumnWidth((short) 8, (short) 16 * 256);// 公共服务类
			sheet.setColumnWidth((short) 9, (short) 8 * 256); // 小计
*/

			//6, 设置第一行大标题
			createTag1(workbook, sheet, titleName);

			//7, 设置第二、三组合行的表格表头
			createTag2(workbook, sheet, coloumItems1, number1, coloumItems2, number2);

			//8, 设置表格内容
			createValue(workbook, sheet, dataList);

			//9, 开始写入数据
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

		// 第一行，大标题
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 36));// 传入坐标，合并坐标内的单元格
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
				cell = row.createCell(j + 1);
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
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet, List<List<ShiXiangBanJianStatistics>> dataList) {

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
				ShiXiangBanJianStatistics shiXiangBanJianStatistics = dataList.get(i).get(j);

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
				cell.setCellValue(shiXiangBanJianStatistics.getArea_name());
				
				//事项数--行政许可
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzxksxs());
				//事项数--行政处罚
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzcfsxs());
				//事项数--行政强制
				cell = row.createCell(3);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzqzsxs());
				//事项数--行政征收
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzzssxs());
				//事项数--行政给付
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzgfsxs());
				//事项数--行政检查
				cell = row.createCell(6);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzjcsxs());
				//事项数--行政确认
				cell = row.createCell(7);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzqrsxs());
				//事项数--行政奖励
				cell = row.createCell(8);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzjlsxs());
				//事项数--行政裁决
				cell = row.createCell(9);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzcjsxs());
				//事项数--其他行政权利
				cell = row.createCell(10);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getQtxzqlsxs());
				//事项数--公共服务
				cell = row.createCell(11);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getGgfwsxs());
				//事项数--小计
				cell = row.createCell(12);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getSxscount());
				
				
				//收件数--行政许可
				cell = row.createCell(13);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzxksjs());
				//收件数--行政处罚
				cell = row.createCell(14);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzcfsjs());
				//收件数--行政强制
				cell = row.createCell(15);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzqzsjs());
				//收件数--行政征收
				cell = row.createCell(16);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzzssjs());
				//收件数--行政给付
				cell = row.createCell(17);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzgfsjs());
				//收件数--行政检查
				cell = row.createCell(18);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzjcsjs());
				//收件数--行政确认
				cell = row.createCell(19);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzqrsjs());
				//收件数--行政奖励
				cell = row.createCell(20);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzjlsjs());
				//收件数--行政裁决
				cell = row.createCell(21);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzcjsjs());
				//收件数--其他行政权利
				cell = row.createCell(22);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getQtxzqlsjs());
				//收件数--公共服务
				cell = row.createCell(23);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getGgfwsjs());
				//收件数--小计
				cell = row.createCell(24);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getSjscount());

				
				//办结数--行政许可
				cell = row.createCell(25);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzxkbjs());
				//办结数--行政处罚
				cell = row.createCell(26);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzcfbjs());
				//办结数--行政强制
				cell = row.createCell(27);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzqzbjs());
				//办结数--行政征收
				cell = row.createCell(28);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzzsbjs());
				//办结数--行政给付
				cell = row.createCell(29);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzgfbjs());
				//办结数--行政检查
				cell = row.createCell(30);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzjcbjs());
				//办结数--行政确认
				cell = row.createCell(31);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzqrbjs());
				//办结数--行政奖励
				cell = row.createCell(32);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzjlbjs());
				//办结数--行政裁决
				cell = row.createCell(33);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getXzcjbjs());
				//办结数--其他行政权利
				cell = row.createCell(34);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getQtxzqlbjs());
				//办结数--公共服务
				cell = row.createCell(35);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getGgfwbjs());
				//办结数--小计
				cell = row.createCell(36);
				cell.setCellStyle(styles);
				cell.setCellValue(shiXiangBanJianStatistics.getBjscount());

			}
		}

	}
	

}
