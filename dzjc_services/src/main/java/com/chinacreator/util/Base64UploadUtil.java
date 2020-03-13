/**
 * 
 */
package com.chinacreator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.apache.commons.codec.binary.Base64;


/**
 *<p>Title:Base64UploadUtil.java</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2017 </p>
 *<p>Company:湖南科创</p>
 *@author yuting.deng
 *@version 1.0
 *@date 2017年6月11日
 */
public class Base64UploadUtil {

	 // 私有化构造器
    private Base64UploadUtil() {
    }

    // 事先定义一个变量存放该类的实例
    private static Base64UploadUtil fileBase64 = null;

    // 对外暴露一个静态方法获取该类的实例
    public static Base64UploadUtil getFileBase64() {
        if (fileBase64 == null) {
            fileBase64 = new Base64UploadUtil();
        }
        return fileBase64;
    }

    // 将 file 转化为 Base64
    public String fileToBase64(String path) {
        File file = new File(path);
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return new BASE64Encoder().encode(buffer);
        } catch (Exception e) {
            throw new RuntimeException("文件路径无效\n" + e.getMessage());
        }
    }
    
 // 将 file 转化为 Base64
    public String byteToBase64(byte[] content) {
    	if(content != null && content.length > 0){
    		try {
                return new BASE64Encoder().encode(content);
            } catch (Exception e) {
                throw new RuntimeException("参数无效\n" + e.getMessage());
            }
    	}else{
    		return "";
    	}
    }

    // 将 base64 转化为 file
    public boolean base64ToFile(String base64, String path) throws Exception {
        byte[] buffer;
        try {
            buffer = new BASE64Decoder().decodeBuffer(base64);
            FileOutputStream out = new FileOutputStream(path);
            out.write(buffer);
            out.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("base64字符串异常或地址异常\n" + e.getMessage());
        }
    }
    
    // 将 base64 转化为 byte
    public byte[] base64ToByte(String base64) throws Exception {
        byte[] buffer;
        try {
            buffer = new BASE64Decoder().decodeBuffer(base64);
            return buffer;
        } catch (Exception e) {
            throw new RuntimeException("base64字符串异常或地址异常\n" + e.getMessage());
        }
    }
    
    // 将 base64 转化为 file
    public boolean base64ByteToFile(String base64, String path) throws Exception {
    	byte[] buffer;
    	try {
    		buffer = Base64.decodeBase64(base64);
    		FileOutputStream out = new FileOutputStream(path);
    		out.write(buffer);
    		out.close();
    		return true;
    	} catch (Exception e) {
    		throw new RuntimeException("base64字符串异常或地址异常\n" + e.getMessage());
    	}
    }
    public static void main(String[] args) {
    	
    	Base64UploadUtil fileBase64 = Base64UploadUtil.getFileBase64();
    	String base64 = fileBase64.fileToBase64("E:\\复件 cssxndt.rar");
    	try {
			fileBase64.base64ToFile(base64, "E:\\44.rar");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
}
