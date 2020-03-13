package com.chinacreator.dzjc_evaluation.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.chinacreator.dzjc_evaluation.department.DepartmentOrg;
import com.chinacreator.dzjc_evaluation.department.DepartmentScore;
import com.chinacreator.dzjc_evaluation.person.PersonScore;
import com.chinacreator.dzjc_evaluation.person.PersonUser;

/**
 * 测评统计Excel导出工具类
 * 
 * @author 谌欣慰
 */
public class EvaluationExcel {

	private String folderPath = this.getClass().getResource("/").getPath();

	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName, Map<String, Object> map, String dataType) {

		/** 创建相关参数 */
		// 第一行为大标题-->titleName

		// 第二行，上层表头文字，一个表头合并多少行，就重复写多少个
		Object[] coloumItems1 = (Object[]) map.get("parentStr");

		// 第二行，上层表头坐标，顺序对应上层表头文字，重复的表头只存一个
		Object[] number1 = (Object[]) map.get("parentIndex");

		// 第三行，下层表头文字
		Object[] coloumItems2 = (Object[]) map.get("itemStr");

		// 第三行，下层表头坐标，顺序对应下层表头文字
		Object[] number2 = (Object[]) map.get("itemIndex");

		//最左边静态标题占几列
		int toptitle = (int) map.get("toptitle");

		//表格数据
		Object objectList = map.get("orgList");
		if (objectList != null) {

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, coloumItems2, number2, objectList, toptitle, dataType);

		}

	}

	/**
	 * Excel写入数据
	 * 
	 */
	private void writeExcel(String titleName, Object[] coloumItems1, Object[] number1, Object[] coloumItems2,
			Object[] number2, Object dataList, int toptitle, String dataType) {

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

			// 设置第一行大标题
			createTag1(workbook, sheet, titleName, coloumItems1);

			// 设置第二、三组合行的表格表头
			createTag2(workbook, sheet, coloumItems1, number1, coloumItems2, number2, toptitle);

			// 设置表格内容
			createValue(workbook, sheet, dataList, toptitle, dataType);

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
	private static void createTag1(HSSFWorkbook workbook, HSSFSheet sheet, String titleName, Object[] coloumItems) {

		// 第一行，大标题
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, coloumItems.length - 1));// 传入坐标，合并坐标内的单元格
		HSSFRow row = sheet.createRow(0);// 获取第一行，作为总标题文字
		row.setHeightInPoints(40);// 设置的值永远是height属性值的40倍

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
	private void createTag2(HSSFWorkbook workbook, HSSFSheet sheet, Object[] coloumItems1, Object[] number1,
			Object[] coloumItems2, Object[] number2, int toptitle) {

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
		row.setHeightInPoints(15);

		// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

		for (int i = 0; i < coloumItems1.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue((String) coloumItems1[i]);
			cell.setCellStyle(styles);
		}

		// 动态合并单元格
		for (int i = 0; i < number1.length; i++) {
			Object[] temp = ((String) number1[i]).split(",");
			Integer startrow = Integer.parseInt((String) temp[0]);
			Integer overrow = Integer.parseInt((String) temp[1]);
			Integer startcol = Integer.parseInt((String) temp[2]);
			Integer overcol = Integer.parseInt((String) temp[3]);
			CellRangeAddress cellRangeAddress = new CellRangeAddress(startrow, overrow, startcol, overcol);
			if (cellRangeAddress.getNumberOfCells()<2) {
				continue;
			}else{
				sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
			}
		}

		// 第三行，下层表头列名
		row = sheet.createRow(2);// 获取第三行，作为下层的标头
		row.setHeightInPoints(15);

		// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

		for (int i = 0; i < coloumItems1.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(styles);
			for (int j = 0; j < coloumItems2.length; j++) {
				// 下层行，因为是第toptitle列才开始合并，所以 +toptitle
				cell = row.createCell(j + toptitle);
				cell.setCellValue((String) coloumItems2[j]);
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
	@SuppressWarnings("unchecked")
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet, Object objectList, int toptitle, String dataType) {

		// 第四行开始循环写入表格数据，自定义样式同样写在上边
		HSSFCellStyle styles = workbook.createCellStyle();
		styles.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 文本居中
		styles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 并垂直居中
		styles.setWrapText(true);// 自动换行

		// 设置边框
		styles.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		styles.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styles.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styles.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		HSSFRow row = null;
		HSSFCell cell = null;

		int count = 0;

		//部门统计数据
		if (dataType.equals("1")) {
			List<DepartmentOrg> dataList = (List<DepartmentOrg>) objectList;
			count = dataList.size();

			for (int i = 0; i < count; i++) {

				DepartmentOrg entity = dataList.get(i);

				row = sheet.createRow(i + 3);// 从第四行开始

				// 该行样式注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框，不好看
				// row.setRowStyle(styles);

				// 设置行高
				row.setHeightInPoints(15);// 设置的值永远是height属性值的15倍

				cell = row.createCell(0);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getRid());

				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getOrg_name());

				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getOrder_no());

				List<DepartmentScore> list = entity.getList();
				int size = list.size();
				for (int j = 0; j < size; j++) {

					cell = row.createCell(toptitle + j);
					cell.setCellStyle(styles);
					cell.setCellValue(list.get(j).getFinal_item_value());
				}

				cell = row.createCell(toptitle + size);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getBonuspoints());
				
				cell = row.createCell(toptitle + size+1);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getSum_score());
			}

			//个人统计数据
		} else if (dataType.equals("2")) {

			List<PersonUser> dataList = (List<PersonUser>) objectList;
			count = dataList.size();

			for (int i = 0; i < count; i++) {

				PersonUser entity = dataList.get(i);

				row = sheet.createRow(i + 3);// 从第四行开始

				// 该行样式注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框，不好看
				// row.setRowStyle(styles);

				// 设置行高
				row.setHeightInPoints(15);// 设置的值永远是height属性值的15倍

				cell = row.createCell(0);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getRid());

				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getUser_name());

				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getOrg_name());

				cell = row.createCell(3);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getOrder_no());

				List<PersonScore> list = entity.getList();
				int size = list.size();
				for (int j = 0; j < size; j++) {

					cell = row.createCell(toptitle + j);
					cell.setCellStyle(styles);
					cell.setCellValue(list.get(j).getFinal_item_value());
				}

				cell = row.createCell(toptitle + size);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getBonuspoints());
				
				cell = row.createCell(toptitle + size+1);
				cell.setCellStyle(styles);
				cell.setCellValue(entity.getSum_score());
			}
		}

	}
}
