package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.chinacreator.dzjc_tongji.TongJi;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ManualDealInfoExport {
	
	public String folderPath = this.getClass().getResource("/").getPath();

	public void wordExport(Configuration configuration, String titleName, List<List<TongJi>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();
		
		// 汇总导出数据
		getData(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		System.out.println(this.getClass());
		Template t = null;
		try {
			t = configuration.getTemplate("jc_manual_data.ftl"); // 获取模板文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		String url = this.getClass().getResource("/").getPath();
		String u = url + titleName + ".doc";

		File outFile = new File(u); // 导出文件
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			t.process(wordMap, out); // 将填充数据填入模板文件并输出到目标文件
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void excelExport(String titleName, List<List<TongJi>> dataList) {
		// 第一行为大标题
		// titleName

		// 第二行，上层表头文字，一个表头合并多少行，就重复写多少个
		String[] coloumItems1 = { "行政区划/组织机构", "预警", "黄牌", "红牌", "撤销", "撤销","撤销"};

		// 第二行，上层表头坐标，顺序对应上层表头文字，重复的表头只存一个
		String[] number1 = { "1,2,0,0", "1,2,1,1", "1,2,2,2", "1,2,3,3","1,1,4,6"};

		// 第三行，下层表头文字
		String[] coloumItems2 = { "预警",  "黄牌", "红牌" };

		// 第三行，下层表头坐标，顺序对应下层表头文字
		String[] number2 = { "2,2,4,4", "2,2,5,5", "2,2,6,6"};

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, coloumItems2, number2, dataList);
	}
	
	/**
	 * Excel写入数据
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
			sheet.setColumnWidth((short) 4, (short) 16 * 256);// 撤销预警
			sheet.setColumnWidth((short) 5, (short) 16 * 256);// 撤销黄牌
			sheet.setColumnWidth((short) 6, (short) 16 * 256);// 撤销红牌
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
	 */
	private static void createTag1(HSSFWorkbook workbook, HSSFSheet sheet, String titleName) {
		// 第一行，大标题
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));// 传入坐标，合并坐标内的单元格
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
				// 实体对象
				TongJi item = dataList.get(i).get(j);
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
				cell.setCellValue(item.getAreaOrgName());
				//预警
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getSuperviseGreenNum());
				//黄牌
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getSuperviseYelloNum());
				//红牌
				cell = row.createCell(3);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getSuperviseRedNum());
				//撤销  预警
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getCancelGreenNum());
				//撤销  黄牌
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getCancelYellowNum());
				//撤销 红牌
				cell = row.createCell(6);
				cell.setCellStyle(styles);
				cell.setCellValue(item.getCancelRedNum());
			}
		}
	}
	
	private void getData(Map<String, Object> wordMap, String titleName, List<List<TongJi>> dataList) {

		wordMap.put("titleName", titleName);
		// 保存每一行的数据
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		for (List<TongJi> citylist : dataList) {

			for (TongJi citys : citylist) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("area_name", citys.getAreaOrgName());
				map.put("yjs", citys.getSuperviseGreenNum());
				map.put("yellow", citys.getSuperviseYelloNum());
				map.put("red", citys.getSuperviseRedNum());
				map.put("cancel_yj_num", citys.getCancelGreenNum());
				map.put("cancel_yellow_num", citys.getCancelYellowNum());
				map.put("cancel_red_num", citys.getCancelRedNum());
				mapList.add(map);
			}
		}
		wordMap.put("mapList", mapList);
	}
	
}
