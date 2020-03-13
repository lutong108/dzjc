package com.chinacreator.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSONObject;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;

@SuppressWarnings({"deprecation","unchecked"})
public class ExpUtil {
	public static final String ENCODING = "UTF-8";
	public static final String EXCEL_META = "application/ms-excel";
	
	/**
	 * @param order   排序参数
	 * @param map	    查询参数
	 * @param headers 表格头部
	 * @param dao     查询dao
	 * @param response
	 * @param fileName 导出名称
	 * @param List<expBean>  对应字段对应值对应变化值
	 * @param pkColumnName   主键列ID（合并单元格时使用）
     * @param mergeColumnIndexArray   列下标数组（合并单元格列下标集合）
	 * @return
	 * */
	public static Object doExpFile(Order order,Map<String,Object> map,String[] headers,Dao<?> dao,HttpServletResponse response,String fileName,String[] list,String pkColumnName,String[] mergeColumnIndexArray){
		try {
			Sortable sortable = setSortable(order);
			
			Pageable pageable = new Pageable();
			pageable.setPageIndex(1);
			pageable.setPageSize(99999); //最大能导出数 excl 存在最大条数
			
			OutputStream outStream = setOutFileName(response, fileName);
			
			Page<?>  dataList = dao.selectByPage(map, pageable,sortable, false);
			
			String fileDownName=fileName.substring(0,fileName.indexOf(".xls"));
			exportExcel(fileDownName,headers,dataList.getContents(),outStream,"yyyy-MM-dd",list,pkColumnName,mergeColumnIndexArray);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Sortable setSortable(Order order) {
		//配置参数
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		Sortable sortable = new Sortable();
		sortable.setOrders(orders);
		return sortable;
	}
	
	
	public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null)  
            return null;      
   
        Map<String, Object> map = new HashMap<String, Object>();   
   
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {    
            String key = property.getName();    
            if (key.compareToIgnoreCase("class") == 0) {   
                continue;  
            }  
            Method getter = property.getReadMethod();  
            Object value = getter!=null ? getter.invoke(obj) : null;  
            map.put(key, value);  
        }    
   
        return map;  
    }    

	private static OutputStream setOutFileName(HttpServletResponse response,
			String fileName) throws UnsupportedEncodingException, IOException {
		byte[] b = fileName.getBytes("gb2312");
		String formatName = new String(b, "ISO-8859-1");
			response.setContentType(EXCEL_META);
			response.setCharacterEncoding(ENCODING);
			response.setHeader("Content-Disposition",
					"attachment;filename=" + formatName);
			OutputStream outStream = response.getOutputStream();
		return outStream;
	}
	
	 /** 
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上 
     *  
     * @param title 
     *            表格标题名 
     * @param headers 
     *            表格属性列名数组 
     * @param dataset 
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据) 
     * @param out 
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中 
     * @param pattern 
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd" 
     * @param pkColumnName   主键列ID（合并单元格时使用）
     * @param mergeColumnIndexArray   列下标数组（合并单元格列下标集合）
     */  
	@SuppressWarnings("rawtypes")
	public static void exportExcel(String title, String[] headers,  
    		 List<?> dataset, OutputStream out,String pattern,String[] list,String pkColumnName,String[] mergeColumnIndexArray)  
    {  
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
      
        
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 15);  
        HSSFCellStyle style = serSytle(workbook);  
        HSSFCellStyle style2 = setStyle2(workbook);  
        // 声明一个画图的顶级管理器  
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch(); 
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);
        
        Iterator<?> it = dataset.iterator();  
        
        for (short i = 0; i < headers.length; i++)  
        {  
            createCell(headers, style, row, i); 
        }  
        // 遍历集合数据，产生数据行  
        int index = 0;  
        //合并单元格开始行号
        int startRowIndex = 0;
        //合并单元格主键
        String curPkId ="";
        //合并单元格结束行号
        int endRowIndex = 0;
        //合并单元格开始标志
        boolean isMerge =false;
        //合并单元格结束标志
        boolean isMergeEnd = false;
        
        while (it.hasNext())  
        {  
            index++;  
            row = sheet.createRow(index);  
            Object t=(Object) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
          //  Field[] fields = t.getClass().getFields();  
            Method[] methods = t.getClass().getMethods();
            
            if(StringUtils.isNotEmpty(pkColumnName)){
		    String getMethodName =pkColumnName;
			Class tCls = t.getClass();  
			Method getMethod = null;
				try {
					getMethod = tCls.getMethod(getMethodName, new Class[]{}); 
					String value = (String) getMethod.invoke(t, new Object[]{});
					if(curPkId.equals(value)){
						isMerge =true;
					}
					else{
						if(isMerge){
							endRowIndex =index-1;
							isMerge = false;
							isMergeEnd = true;
						}else{
						   startRowIndex =index;
						}
					}
					curPkId = value;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            }
            setCellValue(headers, pattern, workbook, sheet, style2, patriarch,
            		row, index, t, methods,list);  
            
            if(!StringUtils.isNotEmpty(pkColumnName)){
            	boolean isEnd = !it.hasNext();
            	//isMergeEnd为判断到了主键相同的结束行号   （isEnd&&isMerge）判断到了最后一行
            	if(isMergeEnd||(isEnd&&isMerge)){
	            	 if(mergeColumnIndexArray!=null&&mergeColumnIndexArray.length>0){
	            		for (int j = 0; j < mergeColumnIndexArray.length; j++) {
							 int columnIndex = Integer.parseInt(mergeColumnIndexArray[j]);
							 //合并单元格 （开始行号，结束行号，开始列号，结束列号）
							 sheet.addMergedRegion(new CellRangeAddress(startRowIndex, endRowIndex,columnIndex,columnIndex)); 
						}
	            	 }
	            	 isMergeEnd = false;
	            	 isMerge =false;
            	 }
            }
        } 
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }
    //行赋值
	private static void setCellValue(String[] headers, String pattern,
			HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle style2,
			HSSFPatriarch patriarch, HSSFRow row, int index, Object t,
			Method[] fields,String[] list) {
		HSSFRow rowValue = sheet.createRow(index);   //增加下一行
		for (short i = 0; i < fields.length; i++)  
		{  
			Method field = fields[i];
//		    String fieldName = field.getName();  
//		    String getMethodName = "get"  
//		            + fieldName.substring(0, 1).toUpperCase()  
//		            + fieldName.substring(1);  
			String getMethodName = field.getName();
		    try  
		    {  
		    	
		        @SuppressWarnings("rawtypes")
				Class tCls = t.getClass();  
		        Method getMethod = null;
		        Object value=null;
		        for (int k = 0; k < headers.length; k++)  {
		        	if(getMethodName.equals(headers[k].split(":")[0])){
		        		 HSSFCell cell = rowValue.createCell(k);    //增加列做列赋值
		     		    cell.setCellStyle(style2);  
		     		    //获取对应值
		        		getMethod = tCls.getMethod(getMethodName, new Class[]{}); 
		        		 if(getMethod!=null){
				        	 value = getMethod.invoke(t, new Object[]{}); 
				        	 String getValue = setValuesDoTableChanges(getMethod,String.valueOf(value),list);  //值处理
				        	 if(!"".equals(getValue)){
				        		 value=getValue;
				        	 }
				        }
				        // 判断值的类型后进行强制类型转换  
				        String textValue = null;  
				        if(value instanceof Double){
		        			 cell.setCellValue((Double)value);  
		        		}else if (value instanceof Date)  
				        {  
				            Date date = (Date) value;  
				            SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
				            textValue = sdf.format(date);  
				        }  
				        else if (value instanceof byte[])  
				        {  
				            // 有图片时，设置行高为60px;  
				        	rowValue.setHeightInPoints(60);  
				            // 设置图片所在列宽度为80px,注意这里单位的一个换算  
				            sheet.setColumnWidth(i, (short) (35.7 * 80));  
				            // sheet.autoSizeColumn(i);  
				            byte[] bsValue = (byte[]) value;  
				            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,  
				                    1023, 255, (short) 6, index, (short) 6, index);  
//				            anchor.setsetAnchorType(2);  
				            patriarch.createPicture(anchor, workbook.addPicture(  
				                    bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));  
				        }  
				        else  
				        {  
				            // 其它数据类型都当作字符串简单处理  
				        	if(!"".equals(value)&&value!=null){
				        		 textValue = value.toString();  
				        	}
				           
				        }  
				        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
				        if (textValue != null)  
				        {  
				            Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
				            Matcher matcher = p.matcher(textValue);  
				            if (matcher.matches())  
				            {  
				                // 是数字当作double处理  
				                cell.setCellValue(Double.parseDouble(textValue));  
				            }  
				            else  
				            {  
				                HSSFRichTextString richString = new HSSFRichTextString(  
				                        textValue);  
				                HSSFFont font3 = workbook.createFont();  
				                font3.setColor(HSSFColor.BLUE.index);  
				                richString.applyFont(font3);  
				                cell.setCellValue(richString);  
				            }  
				        }  
		        	}
		        }
		       
		    }  
		    catch (SecurityException e)  
		    {  
		        e.printStackTrace();  
		    }  
		    catch (NoSuchMethodException e)  
		    {  
		        e.printStackTrace();  
		    }  
		    catch (IllegalArgumentException e)  
		    {  
		        e.printStackTrace();  
		    }  
		    catch (IllegalAccessException e)  
		    {  
		        e.printStackTrace();  
		    }  
		    catch (InvocationTargetException e)  
		    {  
		        e.printStackTrace();  
		    }  
		    finally  
		    {  
		        // 清理资源  
		    }  
		}
	}

	private static void createCell(String[] headers, HSSFCellStyle style, HSSFRow row,
			short i) {
		HSSFCell cell = row.createCell(i);  
		cell.setCellStyle(style);  
		HSSFRichTextString text = new HSSFRichTextString(headers[i].split(":")[1]);  
		cell.setCellValue(text);
	}

	private static HSSFCellStyle serSytle(HSSFWorkbook workbook) {
		// 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);
		return style;
	}

	private static HSSFCellStyle setStyle2(HSSFWorkbook workbook) {
		// 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);
		return style2;
	}  
	
	private static String setValuesDoTableChanges(Method getMethod,String value,String[] list) {
		 String reValue="";
		 //字段不同内容值处理
		 try {
			if(list!=null){
				for(int i=0;i<list.length;i++){
					 String[] setValues=list[i].split(":");
					 for(int j=0;j<setValues.length;j++){
						 String setName=setValues[0];
						 String[] setValuesStr=setValues[1].split(",");
						 for(int k=0;k<setValuesStr.length;k++){
							 String setValue=setValuesStr[k].substring(0,setValuesStr[k].indexOf("-"));
							 String getValue=setValuesStr[k].substring(setValuesStr[k].indexOf("-")+1,setValuesStr[k].length());
							 if(setName.equals(getMethod.getName())){
						   		 if(setValue.equals(value)){reValue=getValue;}
						   		 if("$^".equals(setValue)){reValue=getValue+value;}   //开头加说明
								 if("$$".equals(setValue)){
									 if(!"".equals(reValue)){    //开头结尾都需要加说明
										 reValue=reValue+getValue;
									 }else{
										 reValue=value+getValue;  //结尾加说明
									 }
								 }
						   	 }
						 }
					 }
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  	 return reValue;
	} 
	
	public static Map<String, Object> getMap(String queryCondition) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"undefined".equals(queryCondition)){
			JSONObject jsonobject = JSONObject.parseObject(queryCondition);
			map = (Map<String, Object>) jsonobject;
		}
		return map;
	}
	
	public static Object doExpFileByList(Order order,Map<String,Object> map,String[] headers,List<?> listDao,HttpServletResponse response,String fileName,String[] list,String pkColumnName,String[] mergeColumnIndexArray){
		try {
			OutputStream outStream = setOutFileName(response, fileName);
			String fileDownName=fileName.substring(0,fileName.indexOf(".xls"));
			exportExcel(fileDownName,headers,listDao,outStream,"yyyy-MM-dd HH:mm:ss",list,pkColumnName,mergeColumnIndexArray);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /**
     * 根据静态配置,和转的方法名构建要转换的设置数据
     * @param dictTypeName
     * @param function
     * @return
     */
	public static String getValues(String dictTypeName,String function){
//        DictDataService dictService = (DictDataService)ApplicationContextManager.getContext().getBean(DictDataService.class);
//
//    	 List dicts = dictService.queryByDictTypeName(dictTypeName);
//         StringBuffer sb = new StringBuffer();
//         sb.append(function).append(":");
//         Iterator map = dicts.iterator();
//
//         while(map.hasNext()) {
//             DictDataDTO setValues = (DictDataDTO)map.next();
//             sb.append(setValues.getDictdataName()).append("-");
//             sb.append(setValues.getDictdataValue()).append(",");
//         }
//
//         if(sb.length() > 0) {
//             sb.deleteCharAt(sb.length() - 1);
//         }
//         
//         return sb.toString();
		return "";
    }	
}