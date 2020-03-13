package com.chinacreator.util;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.aspose.words.Body;
import com.aspose.words.Cell;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Font;
import com.aspose.words.FontSettings;
import com.aspose.words.HorizontalAlignment;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.License;
import com.aspose.words.NodeType;
import com.aspose.words.PaperSize;
import com.aspose.words.Paragraph;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.Run;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;
import com.aspose.words.SectionStart;
import com.aspose.words.Shape;
import com.aspose.words.Table;
import com.aspose.words.Underline;
import com.aspose.words.VerticalAlignment;
import com.aspose.words.WrapType;
import com.chinacreator.c2.config.ConfigManager;

/**
 * 清理word痕迹和转PDF 的工具类
 * @author Administrator
 *
 */
public class AsposeWordsUtil {
	
	 private static InputStream license;
	 private InputStream word;
	 
	 private static AsposeWordsUtil Instance;
	 
	 private AsposeWordsUtil(){
	   String fontsdir = ConfigManager.getInstance().getConfig("custom_fonts_directory");
	   if (StringUtils.isNotBlank(fontsdir)) {
	     FontSettings.setFontsFolder(fontsdir, false);
	   }
	 }
	    
	    /**
	     * 获取AsposeWordsUtil实例
	     * @return
	     */
	    public static AsposeWordsUtil getInstance(){
	        if(Instance == null){
	          Instance = new AsposeWordsUtil();
	        }
	        return Instance; 
	    }
	 
	 /**
     * 获取license
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            license = AsposeWordsUtil.class.getClassLoader().getResourceAsStream("wordlicense.xml");// license路径
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 清理痕迹 和转PDF
     * @param wordFilePath
     * @param pdfFilePath
     */
    public void wordToPdfAnddeleteHJ(String wordFilePath,String pdfFilePath){
    	// 验证License
        if (!getLicense()) {
            return;
        }
        FileOutputStream fileOS = null;
        try {
        	File file = new File(wordFilePath);
        	word = new FileInputStream(file);
        	System.out.println("清理痕迹转PDF 开始 ");
            long old = System.currentTimeMillis();
            Document doc = new Document(word);
            doc.acceptAllRevisions();
            doc.save(wordFilePath);
            File pdffile = new File(pdfFilePath);// 输出路径
            fileOS = new FileOutputStream(pdffile);
            doc.save(fileOS, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            System.out.println("清理痕迹转PDF 共耗时：" + ((now - old) / 1000.0) + "秒\n" + "word文件保存在:" + file.getPath()+"\nPDF文件保存:"+pdffile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(word!=null){
        		try {
					word.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(fileOS!=null){
        		try {
        			fileOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
    }
    
    /**
     * 转PDF
     * @param wordFilePath
     * @param pdfFilePath
     */
    public void wordToPdf(String wordFilePath,String pdfFilePath){
   	   // 验证License
       if (!getLicense()) {
           return;
       }
       FileOutputStream fileOS = null;
       try {
    	   File file = new File(wordFilePath);
    	   word = new FileInputStream(file);
       	   //System.out.println("转PDF 开始 ");
           //long old = System.currentTimeMillis();
           Document doc = new Document(word);
           File pdffile = new File(pdfFilePath);// 输出路径
           fileOS = new FileOutputStream(pdffile);
           doc.save(fileOS, SaveFormat.PDF);
           //long now = System.currentTimeMillis();
           //System.out.println("转PDF 共耗时：" + ((now - old) / 1000.0) + "秒\n" + "word文件保存在:" + file.getPath()+"\nPDF文件保存:"+pdffile.getPath());
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
	       	if(word!=null){
	       		try {
						word.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       	}
	       	if(fileOS!=null){
	       		try {
	       			fileOS.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       	}
       }
   }
    
    /**
     * 向word中写图片意见和签字
     * @param wordFilePath
     * @param pdfFilePath
     */
    public void writeImgViewToWordFile(Map<String,String> writeMap,String userName,File targetFile){
   	   // 验证License
       if (!getLicense()) {
           return ;
       }
       FileOutputStream fileOS = null;
       try {
    	   System.out.println("==============写入图片的word 的路径=======："+targetFile.getPath());
    	   word = new FileInputStream(targetFile);
       	   System.out.println("向word中写图片意见和签字  开始 ");
           long old = System.currentTimeMillis();
           Document doc = new Document(word);
           doc.setTrackRevisions(false);
           DocumentBuilder builder = new DocumentBuilder(doc);
	       Font font = builder.getFont();
	   	   font.setSize(16);
	   	   font.setUnderline(Underline.NONE);
           //会签 意见位置
           for (String loc : writeMap.keySet()) {
        	   String[] locs = loc.split(",");
        	   int a = Integer.parseInt(locs[0]);
        	   int b = Integer.parseInt(locs[1]);
        	   int c = Integer.parseInt(locs[2]);
        	   try{
        		   Table table = (Table) doc.getChild(NodeType.TABLE, a-1, true);
        		   Cell cell = table.getRows().get(b-1).getCells().get(c-1);
        		   //插入图片
        		   Shape shape = builder.insertImage(writeMap.get(loc));
        		   shape.setWrapType(WrapType.INLINE );
        		   shape.setBehindText(true);
           		   shape.setBounds(new Rectangle2D.Float(0, 0, 100, 50));
           		   //段落为空的情况
           		   if(cell.getFirstParagraph().getChildNodes().getCount()==0){
         				 cell.getFirstParagraph().appendChild(shape);
           		  } else {
	           		  //判断 第一个段落  图片
	           		  if(cell.getFirstParagraph().getChildNodes().get(0).getNodeType()==NodeType.SHAPE){
	           			  Paragraph para = new Paragraph(doc);
	          			  para.appendChild(shape);
	          			  cell.appendChild(para);
	          		  //判断 第一个段落 是段落
	           		  } else if(cell.getFirstParagraph().getChildNodes().get(0).getNodeType()==NodeType.PARAGRAPH){
	           				 Paragraph para = new Paragraph(doc);
	             			 para.appendChild(shape);
	             			 cell.appendChild(para);
	           		   //纯文本
	           		  } else if(cell.getFirstParagraph().getChildNodes().get(0).getNodeType()==NodeType.RUN){
	           			 if("".equals(cell.getFirstParagraph().getText().trim())){
	           				 cell.getFirstParagraph().removeAllChildren();
	           				 cell.getFirstParagraph().appendChild(shape);
	           			  } else {
	           				 Paragraph para = new Paragraph(doc);
	             			 para.appendChild(shape);
	             			 cell.appendChild(para);
	           			  }
	           		  }
           		  }
        		  doc.acceptAllRevisions();
        		   //保存word
                  doc.save(targetFile.getPath());
        	   }catch(Exception e){
        		   e.printStackTrace();
        	   }
           }
           long now = System.currentTimeMillis();
           System.out.println("word中写图片意见和签字  共耗时：" + ((now - old) / 1000.0) + "秒\n" + "word文件保存在:" + targetFile.getPath());
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
	       	if(word!=null){
	       		try {
						word.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       	}
	       	if(fileOS!=null){
	       		try {
	       			fileOS.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       	}
       }
   } 
    /**
	 *<b>Summary: </b>
	 * writeWordFile(向word中写意见和签字)
	 * @param writeMap 写的内容
	 * @param userName 修改者名字
	 * @param targetFile 目标word文件
	 * @return
	 */
    public  boolean writeWordFile(Map<String,String> writeMap,String userName,File targetFile){
    	boolean result = true;
    	// 验证License
        if (!getLicense()) {
        	result = false;
            return result;
        }
        FileOutputStream fileOS = null;
        try {
        	File file = new File(targetFile.getPath());
        	word = new FileInputStream(file);
        	System.out.println("\r向word中写意见和签字 开始 ");
            long old = System.currentTimeMillis();
            Document doc = new Document(word);
            //会签 意见位置
			for (String loc : writeMap.keySet()) {
				String[] locs = loc.split(",");
				int a = Integer.parseInt(locs[0]);
				int b = Integer.parseInt(locs[1]);
				int c = Integer.parseInt(locs[2]);
				try{
					Table table = (Table) doc.getChild(NodeType.TABLE, a-1, true);
					Cell cell = table.getRows().get(b-1).getCells().get(c-1);
					
	           		Paragraph para = new Paragraph(doc);

					Run run = new Run(doc);
					run.setText(writeMap.get(loc));
					run.getFont().setSize(15);//文字大小
					run.getFont().setBold(true);//加粗
					run.getFont().setName("楷体_GB2312");
         			para.appendChild(run);
         			
					cell.appendChild(para);
					doc.acceptAllRevisions();
					//保存
					doc.save(targetFile.getPath());
				}catch(Exception e){
					e.printStackTrace();
				}
			}
            long now = System.currentTimeMillis();
            System.out.println("向word中写意见和签字 开始  共耗时：" + ((now - old) / 1000.0) + "秒\n" + "word文件保存在:" + file.getPath());
            return result;
        } catch (Exception e) {
        	result = false;
            e.printStackTrace();
        } finally {
        	if(word!=null){
        		try {
					word.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(fileOS!=null){
        		try {
        			fileOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return result;
    }
    
    /**
     * 向word中写图片
     * @param wordFilePath
     * @param pdfFilePath
     */
    public void writeImgView(Map<String,String> writeMap,File targetFile){
   	   // 验证License
       if (!getLicense()) {
           return ;
       }
       FileOutputStream fileOS = null;
       try {
           long old = System.currentTimeMillis();
    	   word = new FileInputStream(targetFile);
           Document doc = new Document(word);
           DocumentBuilder builder = new DocumentBuilder(doc);
	       Font font = builder.getFont();
	   	   font.setSize(16);
	   	   font.setUnderline(Underline.NONE);
	   	   String imsStrings = writeMap.get("images");
	   	   doc.removeAllChildren();
	   	   for(int i=0;i<imsStrings.split(",").length;i++){
	   		   //部分
	   		   Shape shape = builder.insertImage(imsStrings.split(",")[i]);
	           shape.setWrapType(WrapType.NONE);
	           shape.setBehindText(true);
	           shape.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
	           shape.setHorizontalAlignment(HorizontalAlignment.CENTER);
	           shape.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
	           shape.setVerticalAlignment(VerticalAlignment.CENTER);
	           //段落
	   		   Paragraph para = new Paragraph(doc);
	   		   para.appendChild(shape);
	   		   //页
	   		   Section section = new Section(doc);
	   		   section.getPageSetup().setSectionStart(SectionStart.NEW_PAGE);
	   		   section.getPageSetup().setPaperSize(PaperSize.LETTER);
	   		   //内容
	   		   Body body = new Body(doc);
	   		   section.appendChild(body);
	   		   body.appendChild(para);
	   		   doc.appendChild(section);
	   	   }
		  doc.acceptAllRevisions();
		   //保存word
          doc.save(targetFile.getPath());
          long now = System.currentTimeMillis();
          System.out.println("向word写图片开始  共耗时：" + ((now - old) / 1000.0) + "秒\n" );
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
	       	if(word!=null){
	       		try {
						word.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       	}
	       	if(fileOS!=null){
	       		try {
	       			fileOS.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	       	}
       }
   } 
   
      //word 生成图片
    public boolean wordToPic(File targetFile){
    	boolean result = true;
    	// 验证License
        if (!getLicense()) {
        	result = false;
            return result;
        }
        FileOutputStream fileOS = null;
        try {
        	File file = new File(targetFile.getPath());
        	word = new FileInputStream(file);
            long old = System.currentTimeMillis();
            Document doc = new Document(word);
            ImageSaveOptions iso = new ImageSaveOptions(SaveFormat.JPEG);
            iso.setResolution(220);
            iso.setPrettyFormat(true);
            iso.setUseAntiAliasing(true); 
            for (int i = 0; i < doc.getPageCount(); i++){ 
            	iso.setPageIndex(i); 
            	doc.save("D:\\" + i + ".jpg", iso);
            }
            long now = System.currentTimeMillis();
            System.out.println(((now - old) / 1000.0) + "秒\n" + "word文件保存在:" );
            return result;
        } catch (Exception e) {
        	result = false;
            e.printStackTrace();
        } finally {
        	if(word!=null){
        		try {
					word.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(fileOS!=null){
        		try {
        			fileOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return result;
    }
	
	  /**
	 *<b>Summary: </b>
	 * word2(多个word拼接)
	 * @param targetFile 目标word文件
	 * @return
	 */
    public  boolean word2(Map<String,String> writeMap,String targetFile){
    	boolean result = true;
    	// 验证License
        if (!getLicense()) {
        	result = false;
            return result;
        }
        FileOutputStream fileOS = null;
        try {
            long old = System.currentTimeMillis();
            String path = writeMap.get("paths");
            String[] paths= path.split(",");
            ArrayList<Document> docArrayList = null;
            Document doc;
            docArrayList = new ArrayList<Document>();
            for (int i = 0; i < paths.length; i++)
            {
                doc = new Document(paths[i]);
                docArrayList.add(doc);
            }
            
            Document docAll  = new Document();
            docAll.removeAllChildren();
            for(int i=0;i<docArrayList.size();i++){
            	Document document = docArrayList.get(i);
            	document.getFirstSection().getPageSetup().setSectionStart(SectionStart.CONTINUOUS);
            	docAll.appendDocument(document, ImportFormatMode.KEEP_SOURCE_FORMATTING);
            }
            docAll.save(targetFile);
			
            long now = System.currentTimeMillis();
            System.out.println("合并 共耗时：" + ((now - old) / 1000.0) + "秒\n" + "word文件保存在:" + targetFile);
            return result;
        } catch (Exception e) {
        	result = false;
            e.printStackTrace();
        } finally {
        	if(word!=null){
        		try {
					word.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(fileOS!=null){
        		try {
        			fileOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return result;
    }
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 验证License
      if (!getLicense()) {
          return;
      }
    	
    	AsposeWordsUtil t = new AsposeWordsUtil();
    	Map<String,String> writeMap = new HashMap<String,String>();
    	writeMap.put("images", "D:\\111.png,D:\\222.png,D:\\333.png");
    	/*String userName ="sdfsa";
    	File targetFile = new File("D:\\8166b9d4-41aa-4c29-9602-a6056b6bc6a1.doc");
    	t.writeImgViewToWordFile(writeMap,userName,targetFile);
    	
    	
    	Map<String,String> writeMap1 = new HashMap<String,String>();
    	writeMap1.put("1,3,2", "asdf asd fasd"+ControlChar.LINE_BREAK+"【胡林 2016-12-13 11:02】");
    	String userName1 ="sdfsa";
    	File targetFile1 = new File("D:\\8166b9d4-41aa-4c29-9602-a6056b6bc6a1.doc");
    	t.writeWordFile(writeMap1,userName1,targetFile1);
    	*/
    	
    	t.wordToPdf("D:\\file\\1234564687$$tt1中文.doc","D:\\file\\1234564687$$tt1中文.pdf");
    	 
    }	
}
