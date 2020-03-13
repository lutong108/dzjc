package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import com.chinacreator.dzjc_ruleEngine.BussinessAllInfo;
import com.chinacreator.dzjc_ruleEngine.Dictdata;
import com.chinacreator.dzjc_ruleEngine.web.rest.DicttypeController;


public class BussinessAllImport {
	public String folderPath = this.getClass().getResource("/").getPath();
	
	/**
	 * Excel导入准备工作
	 */
	public void excelExport(String titleName, List<List<BussinessAllInfo>> dataList) {

		/** 创建相关参数 */
		// 第一行为大标题
		// titleName

		/*// 第二行，上层表头文字，一个表头合并多少行，就重复写多少个
		String[] coloumItems1 = { "区域名称", "区域名称", "目录清单", "目录清单", "目录清单", "已填报实施清单", "已填报实施清单", "已填报实施清单", "已发布实施清单",
				"已发布实施清单", "已发布实施清单" };

		// 第二行，上层表头坐标，顺序对应上层表头文字，重复的表头只存一个
		String[] number1 = { "1,2,0,1", "1,1,2,4", "1,1,5,7", "1,1,8,10" };*/

		// 第三行，下层表头文字
		String[] coloumItems1 = { "业务名称","实例编号","申请者名称", "业务类型", "发牌状态", "受理时间", "办结时间", "发牌结果", "发牌类型", "机构名称","办理状态","监管时间","承诺时限","承诺所剩天数","法定时限","法定所剩天数"};

		// 第三行，下层表头坐标，顺序对应下层表头文字
		String[] number1 = { "1,1,1,1", "1,1,2,2", "1,1,3,3","1,1,4,4", "1,1,5,5", "1,1,6,6", "1,1,7,7","1,1,8,8","1,1,9,9","1,1,10,10","1,1,11,11","1,1,12,12"};

		// 开始写表格
		writeExcel(titleName, coloumItems1, number1, dataList);

	}
	
	/**
	 * Excel写入数据
	 * 
	 */
	private void writeExcel(String titleName, String[] coloumItems1, String[] number1,  List<List<BussinessAllInfo>> dataList) {

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
			int x=14;
			sheet.setColumnWidth((short) 0, (short) 40 * 256);// 业务名称
			for (int i = 1; i <= x; i++) {
				if(i==1){
					sheet.setColumnWidth((short) i, (short) 40 * 256);	
				}else{
					sheet.setColumnWidth((short) i, (short) 13 * 256);
				}
				
			}
			
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
	private static void createTag1(HSSFWorkbook workbook, HSSFSheet sheet, String titleName) {

	/*	// 第一行，大标题
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));// 传入坐标，合并坐标内的单元格
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
		cell.setCellValue("全部发牌信息管理");
		cell.setCellStyle(styles);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));// 传入坐标，合并坐标内的单元格
		
		// 第二行，标题
		row =sheet.createRow(1);
		row.setHeightInPoints(35);
		 cell = row.createCell(0);// 创建列
	    titleName=titleName.substring(8);
	    if(titleName.contains("-")){
	    	titleName=titleName.replaceAll("-", "/");
	    }
	    cell.setCellValue(titleName);
	    cell.setCellStyle(style_);
	    sheet.addMergedRegion(new CellRangeAddress(1,1,0,15));


	}
	
	/**
	 * 创建第二的表格表头
	 * 
	 * @param tags
	 * @param s
	 */
	private void createTag2(HSSFWorkbook workbook, HSSFSheet sheet, String[] coloumItems1, String[] number1) {

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
		/*for (int i = 0; i < number1.length; i++) {

			String[] temp = number1[i].split(",");

			Integer startrow = Integer.parseInt(temp[0]);
			Integer overrow = Integer.parseInt(temp[1]);
			Integer startcol = Integer.parseInt(temp[2]);
			Integer overcol = Integer.parseInt(temp[3]);

			sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
		}*/

		// 第三行，下层表头列名
		//row = sheet.createRow(1);// 获取第三行，作为下层的标头
		//row.setHeightInPoints(30);

		// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

		/*for (int i = 0; i < coloumItems1.length; i++) {

			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(styles);


			cell = row.createCell(i);
			cell.setCellValue(coloumItems1[i]);

			cell.setCellStyle(styles);
		}*/
		

	}
	
	
	
	/**
	 * 设置表格内容
	 * 
	 * @param res
	 * @param s
	 */
	private void createValue(HSSFWorkbook workbook, HSSFSheet sheet, List<List<BussinessAllInfo>> dataList) {

		HSSFRow row = null;
		HSSFCell cell = null;

		int index = 2;// 从第四行开始（下标应为3，但是会在内循环中自增）
		// 设置样式
		HSSFCellStyle styles = workbook.createCellStyle();
		// //外循环，里面每个元素都是一个集合
		int w_size = dataList.size();
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
		
		//字典数据
		/*Map<String,String[]> map=new  HashMap<String, String[]>();
		String[] values={"业务类型","发牌状态","办件办理状态"};
		map.put("dictTypeNames", values);
		Map<String, Map> dictDataMap = new DicttypeController().getDictDataMap(map);
		Map map2 = dictDataMap.get("result");
		List<Dictdata> ywlx = (List<Dictdata>) map2.get("业务类型");
		List<Dictdata> fpzt = (List<Dictdata>) map2.get("发牌状态");
		List<Dictdata> bjblzt = (List<Dictdata>) map2.get("办件办理状态");
		for (Dictdata dictdata : bjblzt) {
			
		}*/

		
		
		
		for (int i = 0; i < w_size; i++) {

			// 内循环，里面每个元素都是一个对象
			int n_size = dataList.get(i).size();
			for (int j = 0; j < n_size; j++) {

				index++;// 每次自增，插入一行数据
				row = sheet.createRow(index);
				row.setHeightInPoints(30);// 设置行高，值永远是height属性值的30倍

				// row.setRowStyle(styles);注释的原因：因为要显示边框，如果行样式也设置，会导致Excel右边无关的表格也全显示边框

				// 实体对象
				BussinessAllInfo bussinessAllInfo = dataList.get(i).get(j);
				
				// 设置样式
				//HSSFCellStyle styles = workbook.createCellStyle();
				//业务名称
				cell = row.createCell(0);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getBussinessName());
				
				cell = row.createCell(1);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getInstanceCode());	
				
				cell = row.createCell(2);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getApplyName());	

				
				//业务类型
				cell = row.createCell(3);
				cell.setCellStyle(styles);
				if(bussinessAllInfo.getBussinessType()!=null && !bussinessAllInfo.getBussinessType().equals("")){
					if("1".equals(bussinessAllInfo.getBussinessType())){
						bussinessAllInfo.setBussinessType("办件");
					}else if("2".equals(bussinessAllInfo.getBussinessType())){
						bussinessAllInfo.setBussinessType("投诉");
					}else if("3".equals(bussinessAllInfo.getBussinessType())){
						bussinessAllInfo.setBussinessType("咨询");
					}else{
						bussinessAllInfo.setBussinessType("");
					}
					cell.setCellValue(bussinessAllInfo.getBussinessType());
				}else{
					cell.setCellValue("");
				}
				
				
				//发牌状态
				cell = row.createCell(4);
				cell.setCellStyle(styles);
				if(bussinessAllInfo.getState()!=null && !"".equals(bussinessAllInfo.getState()) ){
					if("Y".equals(bussinessAllInfo.getState())){
						bussinessAllInfo.setState("已发");
					}else if("D".equals(bussinessAllInfo.getState())){
						bussinessAllInfo.setState("待发");
					}else if("C".equals(bussinessAllInfo.getState())){
						bussinessAllInfo.setState("撤销");
					}else if("Q".equals(bussinessAllInfo.getState())){
						bussinessAllInfo.setState("取消");
					}else if("B".equals(bussinessAllInfo.getState())){
						bussinessAllInfo.setState("督办");
					}else{
						bussinessAllInfo.setState("");
					}
					cell.setCellValue(bussinessAllInfo.getState());
					
				}else{
					cell.setCellValue("");
				}
				
				//时间
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				cell = row.createCell(5);
				cell.setCellStyle(styles);
				if(bussinessAllInfo.getBussinessTime()!=null){
					cell.setCellValue(sdf.format(bussinessAllInfo.getBussinessTime()));
				}else{
					cell.setCellValue("");
				}
				
				//办结时间
				cell = row.createCell(6);
				cell.setCellStyle(styles);
				if(bussinessAllInfo.getEndTime()!=null){
					cell.setCellValue(sdf.format(bussinessAllInfo.getEndTime()));
				}else{
					cell.setCellValue("");
				}
				
				//发牌结果
				cell = row.createCell(7);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getSuperviseResultName());
				//发牌类型
				cell = row.createCell(8);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getSuperviseTypeName());
				//机构名称
				cell = row.createCell(9);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getOrgName());
				
				//办理状态
				cell = row.createCell(10);
				cell.setCellStyle(styles);
					if(bussinessAllInfo.getBussinessType().equals("咨询")){
						if(bussinessAllInfo.getIsValid().equals("Y")){
							
							if(bussinessAllInfo.getReplyTime()!=null){
								bussinessAllInfo.setBjState("已回复");
								
							}else{
								bussinessAllInfo.setBjState("待回复");
							}
							
							
							}else{
								bussinessAllInfo.setBjState("已撤回");
							}
						}
				cell.setCellValue(bussinessAllInfo.getBjState());
					
				
				
				//机构名称
				cell = row.createCell(11);
				cell.setCellStyle(styles);
				if(bussinessAllInfo.getSuperviseTime()!=null && !"".equals(bussinessAllInfo.getSuperviseTime())){
					cell.setCellValue(sdf.format(bussinessAllInfo.getSuperviseTime()));	
				}else{
					cell.setCellValue("");
				}
				
				cell = row.createCell(12);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getPromiseLimit());	
				
				cell = row.createCell(13);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getCommitment());	
				
				cell = row.createCell(14);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getProcessLimit());	
				
				cell = row.createCell(15);
				cell.setCellStyle(styles);
				cell.setCellValue(bussinessAllInfo.getLegal());	
				
				
			}
		}

	}
	
	
	
	
}
