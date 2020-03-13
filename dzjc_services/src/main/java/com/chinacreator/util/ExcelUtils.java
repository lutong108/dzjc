package com.chinacreator.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

/**
 * Excel导出工具类
 * 
 * @author zyz818
 * 
 */
public class ExcelUtils {

	private static final short TITLE_FONT_HEIGHT = 16;
	private static final short COLUMN_FONT_HEIGHT = 12;
	private static final short MAIN_FONT_HEIGHT = 10;

	private ExcelUtils() {
	}

	public static void exportExcel(String fileName,
			List<? extends Object> data, HttpServletRequest request,
			HttpServletResponse response, List<GroupHeaders> groupHeaders) {
		// 获取实体的@Exportable字段
		if (data.size() == 0) {
			return;
		}
		Object object = data.get(0);
		Class<? extends Object> clz = object.getClass();
		Field[] fields = clz.getDeclaredFields();
		List<Field> exportableFields = new ArrayList<>();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getAnnotation(Exportable.class) != null) {
				exportableFields.add(fields[i]);
			}
		}
		if (exportableFields.size() == 0) {
			throw new RuntimeException("需导出的实体文件没有标识Exportable注解的字段，请检查该实体文件！");
		}
		// 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 表格页
		HSSFSheet sheet = workbook.createSheet(fileName);
		// 表格页默认宽度
		sheet.setDefaultColumnWidth((short) 15);

		createTitleStyle(workbook, sheet, exportableFields, fileName);
		createColumnStyle(workbook, sheet, exportableFields, groupHeaders);
		createMainStyle(workbook, sheet, exportableFields, data);
		OutputStream out = null;
		try {
			out = createResponesHeader(request, response, fileName)
					.getOutputStream();
			workbook.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置响应头
	 * 
	 * @param request
	 * 
	 * @param response
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private static HttpServletResponse createResponesHeader(
			HttpServletRequest request, HttpServletResponse response,
			String fileName) throws UnsupportedEncodingException {
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ fileName + ".xls");
		return response;
	}

	/**
	 * 设置单元格样式
	 * 
	 * @return
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 设置字体样式
	 * 
	 * @param workbook
	 * @return
	 */
	private static HSSFFont createFont(HSSFWorkbook workbook, short height,
			short bold) {
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints(height);
		font.setBoldweight(bold);
		return font;
	}

	/**
	 * 设置标题
	 * 
	 */
	private static void createTitleStyle(HSSFWorkbook workbook,
			HSSFSheet sheet, List<Field> exportableFields, String fileName) {
		HSSFCellStyle style = createCellStyle(workbook);
		HSSFFont font = createFont(workbook, TITLE_FONT_HEIGHT,
				HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue(new HSSFRichTextString(fileName));
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, 0,
				exportableFields.size() - 1);
		sheet.addMergedRegion(cellRangeAddress);
		fillBorderStyle(workbook, sheet, cellRangeAddress);
	}

	/**
	 * 设置列
	 * 
	 */
	private static void createColumnStyle(HSSFWorkbook workbook,
			HSSFSheet sheet, List<Field> exportableFields,
			List<GroupHeaders> groupHeaders) {
		Collections.sort(exportableFields, new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				return o1.getAnnotation(Exportable.class).sort()
						- o2.getAnnotation(Exportable.class).sort();
			}
		});
		HSSFCellStyle style = createCellStyle(workbook);
		HSSFFont font = createFont(workbook, COLUMN_FONT_HEIGHT,
				HSSFFont.BOLDWEIGHT_BOLD);
		List<GroupHeaders> ghList = checkGroupHeaders(groupHeaders,
				exportableFields);
		HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
		if (ghList.size() > 0) {
			HSSFRow row0 = sheet.createRow(sheet.getLastRowNum() + 1);
			for (GroupHeaders gh : ghList) {
				if (gh.getNumberOfEnd() - gh.getNumberOfStart() < 1) {
					throw new RuntimeException("表头组" + gh.getHeadName()
							+ "：合并区域必须包含2个或多个单元格");
				}
				CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2,
						gh.getNumberOfStart(), gh.getNumberOfEnd());
				sheet.addMergedRegion(cellRangeAddress);
				fillBorderStyle(workbook, sheet, cellRangeAddress);
			}
			for (int i = 0; i < exportableFields.size(); i++) {
				for (int j = 0; j < ghList.size(); j++) {
					if (i > ghList.get(j).getNumberOfEnd() && ghList.size() > 1) {
						ghList.remove(j);
						j--;
						continue;
					}
					if (i >= ghList.get(j).getNumberOfStart()
							&& i <= ghList.get(j).getNumberOfEnd()) {
						HSSFCell cell = row.createCell(ghList.get(j)
								.getNumberOfStart());
						style.setFont(font);
						cell.setCellStyle(style);
						HSSFRichTextString text = new HSSFRichTextString(ghList
								.get(j).getHeadName());
						cell.setCellValue(text);
						HSSFCell cell0 = row0.createCell(i);
						style.setFont(font);
						cell0.setCellStyle(style);
						String name = exportableFields.get(i)
								.getAnnotation(Exportable.class).name();
						HSSFRichTextString text0 = new HSSFRichTextString(name);
						cell0.setCellValue(text0);
						break;
					} else {
						HSSFCell cell = row.createCell(i);
						style.setFont(font);
						cell.setCellStyle(style);
						String name = exportableFields.get(i)
								.getAnnotation(Exportable.class).name();
						HSSFRichTextString text = new HSSFRichTextString(name);
						cell.setCellValue(text);
						CellRangeAddress cellRangeAddress = new CellRangeAddress(
								2, 3, i, i);
						sheet.addMergedRegion(cellRangeAddress);
						fillBorderStyle(workbook, sheet, cellRangeAddress);
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < exportableFields.size(); i++) {
				HSSFCell cell = row.createCell(i);
				style.setFont(font);
				cell.setCellStyle(style);
				String name = exportableFields.get(i)
						.getAnnotation(Exportable.class).name();
				HSSFRichTextString text = new HSSFRichTextString(name);
				cell.setCellValue(text);
			}
		}
	}

	/**
	 * 检查二级表头
	 * 
	 * @param groupHeaders
	 */
	private static List<GroupHeaders> checkGroupHeaders(
			List<GroupHeaders> groupHeaders, List<Field> exportableFields) {
		if (groupHeaders == null) {
			return new LinkedList<GroupHeaders>();
		}
		for (GroupHeaders groupHeader : groupHeaders) {
			for (Field f : exportableFields) {
				if (f.getName().equals(groupHeader.getStartColumnName())) {
					int sort = f.getAnnotation(Exportable.class).sort();
					int numberOfEnd = sort + groupHeader.getNumberOfColumns();
					groupHeader.setNumberOfStart(sort);
					groupHeader.setNumberOfEnd(numberOfEnd - 1);
				}
			}
		}
		return groupHeaders;
	}

	/**
	 * 设置内容
	 * 
	 */
	private static void createMainStyle(HSSFWorkbook workbook, HSSFSheet sheet,
			List<Field> exportableFields, List<? extends Object> data) {
		Iterator<? extends Object> it = data.iterator();
		int index = sheet.getLastRowNum();
		HSSFCellStyle style = createCellStyle(workbook);
		HSSFFont font = createFont(workbook, MAIN_FONT_HEIGHT,
				HSSFFont.BOLDWEIGHT_NORMAL);
		while (it.hasNext()) {
			index++;
			Object next = it.next();
			HSSFRow row = sheet.createRow(index);
			for (int i = 0; i < exportableFields.size(); i++) {
				Field field = exportableFields.get(i);
				HSSFCell cell = row.createCell(i);
				style.setFont(font);
				cell.setCellStyle(style);
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Method getMethod = next.getClass().getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(next, new Object[] {});
					if (value instanceof String) {
						cell.setCellValue(value.toString());
					}
					if (value instanceof Double) {
						cell.setCellValue((double) value);
					}
					if (value instanceof Integer) {
						cell.setCellValue((int) value);
					}
				} catch (NoSuchMethodException | SecurityException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 填充边框样式
	 * 
	 * @param workbook
	 * @param sheet
	 */
	private static void fillBorderStyle(HSSFWorkbook workbook, HSSFSheet sheet,
			CellRangeAddress cellRangeAddress) {
		RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellRangeAddress,
				sheet, workbook);
		RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellRangeAddress,
				sheet, workbook);
		RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellRangeAddress,
				sheet, workbook);
		RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellRangeAddress,
				sheet, workbook);
	}
}
