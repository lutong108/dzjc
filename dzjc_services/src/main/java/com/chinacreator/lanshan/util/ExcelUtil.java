package com.chinacreator.lanshan.util;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Font;

public class ExcelUtil {

	/**
	 * 设置字体样式
	 * 
	 * @param font
	 * @param fontHeightInPoints
	 *            字体大小
	 * @param isBoldweight
	 *            是否字体加粗
	 * @param fontName
	 *            字体 ["宋体","楷体"]
	 */
	public void setFont(HSSFFont font, Integer fontHeightInPoints, boolean isBoldweight, String fontName) {
		if (font == null)
			return;
		if (fontHeightInPoints != null) {
			font.setFontHeightInPoints(fontHeightInPoints.shortValue());
		}
		if (isBoldweight) {
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		}
		if (StringUtils.isNotBlank(fontName)) {
			font.setFontName(fontName);
		}
	}

	/**
	 * 填充样式
	 * 
	 * @param style
	 * @param isAlign
	 *            是否水平垂直居中
	 * @param isBorder
	 *            是否添加边框
	 */
	public void setCellStyle(HSSFCellStyle style, boolean isAlign, boolean isBorder, HSSFFont font) {
		if (style == null)
			return;
		// 自动换行
		style.setWrapText(true);
		if (isAlign) {
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		}
		if (isBorder) {
			// 设置边框
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		}
		if (font != null) {
			style.setFont(font);
		}
	}

	/**
	 * 填充样式
	 * 
	 * @param style
	 * @param isAlign
	 *            是否水平垂直居中
	 * @param isBorder
	 *            是否添加边框
	 */
	public void setTimeTitleStyle(HSSFCellStyle style, boolean isAlign, boolean isBorder, HSSFFont font) {
		if (style == null)
			return;
		// 自动换行
		style.setWrapText(true);
		if (isAlign) {
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		}
		if (isBorder) {
			// 设置边框
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		}
		if (font != null) {
			style.setFont(font);
		}
	}

}