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

import com.chinacreator.dzjc_ruleEngine.SuperviseInfoComplain;

/**
 * 投诉导出
 * 
 * @author lilang
 *
 */
public class ComplainExport {

	public String folderPath = this.getClass().getResource("/").getPath();

	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName,
			List<List<SuperviseInfoComplain>> dataList) {

		/** 创建相关参数 */

		// 下层表头文字
		String[] coloumItems1 = {"监管结果", "监管类型", "投诉标题", "被投诉人姓名","投诉人姓名", "投诉时间",
				"受理时间","受理单位","完成时间","监管时间","投诉状态"};

		// 下层表头坐标，顺序对应下层表头文字
		String[] number1 = { "1,1,1,1", "1,1,2,2", "1,1,3,3", "1,1,4,4",
				"1,1,5,5", "1,1,6,6", "1,1,7,7","1,1,8,8","1,1,9,9","1,1,10,10","1,1,11,11", "1,1,12,12" };

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, dataList);

	}

	/**
	 * Excel写入数据
	 * 
	 */
	private void writeExcel(String titleName, String[] coloumItems1,
			String[] number1, List<List<SuperviseInfoComplain>> dataList) {

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

			//String[] coloumItems1 = { "投诉状态", "监察结果", "监察类型", "投诉标题", "被投诉人姓名","投诉人姓名", "投诉时间",
			//		"受理时间","受理单位","到期时间" };
			
			// 获取列信息
			HSSFSheet sheet = workbook.createSheet();
			sheet.setColumnWidth((short) 0, (short) 12 * 256);// 监察结果
			sheet.setColumnWidth((short) 1, (short) 12 * 256);//  监察类型
			sheet.setColumnWidth((short) 2, (short) 40 * 256);//  投诉标题
			sheet.setColumnWidth((short) 3, (short) 12 * 256);//  被投诉人姓名
			sheet.setColumnWidth((short) 4, (short) 16 * 256);//  投诉人姓名
			sheet.setColumnWidth((short) 5, (short) 16 * 256);// 投诉时间
			sheet.setColumnWidth((short) 6, (short) 12 * 256);// 受理时间
			sheet.setColumnWidth((short) 7, (short) 12 * 256);//  受理单位
			sheet.setColumnWidth((short) 8, (short) 12 * 256);//  到期时间
			sheet.setColumnWidth((short) 9, (short) 12 * 256);//  投诉状态
			sheet.setColumnWidth((short) 9, (short) 12 * 256);//  投诉状态

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
		cell.setCellValue("投诉监管信息情况表");
		cell.setCellStyle(styles);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));// 传入坐标，合并坐标内的单元格
		
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
	    sheet.addMergedRegion(new CellRangeAddress(1,1,0,10));


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

		// 第三行，上层表头列名
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
			List<List<SuperviseInfoComplain>> dataList) {

		HSSFRow row = null;
		HSSFCell cell = null;

		int index = 2;// 从第四行开始（下标应为3，但是会在内循环中自增）
		
		
		// 设置样式-- 放外面，修复数据量大报错
		HSSFCellStyle styles = workbook.createCellStyle();
		
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
				SuperviseInfoComplain complain = dataList.get(i).get(j);

				// 监察结果
				cell = row.createCell(0);
				cell.setCellStyle(styles);
				cell.setCellValue(complain.getSuperviseResultName());

				// 监察类型
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(complain.getSuperviseTypeName());
				// 投诉标题
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(complain.getComplainTitle());
				// 被投诉人姓名
				cell = row.createCell(3);
				cell.setCellStyle(styles);
				cell.setCellValue(complain.getBycomplainedUserOrg());
				// 投诉人姓名
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				cell.setCellValue(complain.getComplainUserName());

				// 投诉时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				
				
				if (complain.getEnregisterTime() != null
						&& complain.getEnregisterTime().toString() != "") {
					cell.setCellValue(sdf.format(complain.getEnregisterTime()));
				} else {
					cell.setCellValue("");
				}
				//cell.setCellValue(sdf.format(complain.getEnregisterTime()));
				
				// 受理时间
				cell = row.createCell(6);
				cell.setCellStyle(styles);
				if (complain.getAcceptTime() != null
						&& complain.getAcceptTime().toString() != "") {
					cell.setCellValue(sdf.format(complain.getAcceptTime()));
				} else {
					cell.setCellValue("");
				}
				//cell.setCellValue(complain.getAcceptTime());
				
				// 受理单位
				cell = row.createCell(7);
				cell.setCellStyle(styles);
				cell.setCellValue(complain.getReplyOrgname());
				
				// 完成时间
				cell = row.createCell(8);
				cell.setCellStyle(styles);
				if (complain.getEndTime() != null
						&& complain.getEndTime().toString() != "") {
					cell.setCellValue(complain.getEndTime().toString().substring(0, 10));
				} else {
					cell.setCellValue("");
				}
				
				// 监察时间
				cell = row.createCell(9);
				cell.setCellStyle(styles);
				if (complain.getSuperviseTime() != null
						&& complain.getSuperviseTime().toString() != "") {
					cell.setCellValue(complain.getSuperviseTime().toString().substring(0, 10));
				} else {
					cell.setCellValue("");
				}
				//cell.setCellValue(complain.getExpireDate());
				
				// 投诉状态
				cell = row.createCell(10);
				cell.setCellStyle(styles);
				if(complain.getState().equals("2")){
					complain.setState("正在办理");
				}else if(complain.getState().equals("3")){
					complain.setState("不予受理");
				}else if(complain.getState().equals("9")){
					complain.setState("办理完成");
				}else if(complain.getState().equals("10")){
					complain.setState("已提交");
				}else{
					complain.setState("");
				}
				cell.setCellValue(complain.getState());

			}
		}

	}

}
