package com.chinacreator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

import com.aspose.cells.CellsHelper;
import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.words.FontSettings;
import com.chinacreator.c2.config.ConfigManager;

//引入aspose-cells-8.5.2.jar包
public class Excel2Pdf {
  private static InputStream license;
  private InputStream inexcel;

  private static Excel2Pdf Instance;
  
  private Excel2Pdf(){
     String fontsdir = ConfigManager.getInstance().getConfig("custom_fonts_directory");
     if (StringUtils.isNotBlank(fontsdir)) {
        FontSettings.setFontsFolder(fontsdir, false);
     }
  }
         
  /**
   * 获取Excel2Pdf实例
   * @return
  */
  public static Excel2Pdf getInstance(){
     if(Instance == null){
       Instance = new Excel2Pdf();
     }
     return Instance; 
  }
  
	public static boolean getLicense() {
		boolean result = false;
		try {
		    license = Excel2Pdf.class.getClassLoader().getResourceAsStream(
					"xlsxlicense.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
			License aposeLic = new License();
			aposeLic.setLicense(license);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void excel2pdf(String inputFile, String outputFile) {
		if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
			return;
		}
		FileOutputStream fileOS = null;
		
		try {
			File pdfFile = new File(outputFile);// 输出路径
			File file = new File(inputFile);
			inexcel = new FileInputStream(file);
			String fontsdir = ConfigManager.getInstance().getConfig("custom_fonts_directory");
			
			if (StringUtils.isNotBlank(fontsdir)) {
			  CellsHelper.setFontDir(fontsdir);
			}
			
			Workbook wb = new Workbook(inexcel);// 原始excel路径
			fileOS = new FileOutputStream(pdfFile);
			
			if (null != fileOS) {
			  wb.save(fileOS, SaveFormat.PDF);
			}
			
			//fileOS.close();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
          if( null != inexcel ){
            try {
              inexcel.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
        }
        if(null != fileOS ){
            try {
                fileOS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }
	}
	
    /**
     * 支持DOC, DOCX, OOXML, RTF, HTML, OpenDocument, PDF, EPUB, XPS, SWF等相互转换<br>
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 验证License
        if (!getLicense()) {
            return;
        }

        try {
        	Excel2Pdf.getInstance().excel2pdf("D:\\file\\aaa.xlsx", "D:\\file\\aaa.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
}