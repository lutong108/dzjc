package com.chinacreator.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	private static PinyinUtil instance;
	private final static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	static{
		//不带声调
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		//绿字的拼音用lv,而不是lu
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		//采用小写展示
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		
	}
	
	private PinyinUtil(){
		
	}
	
	public static PinyinUtil getInstance(){
		if(instance == null){
			instance = new PinyinUtil();
		}
		return instance;
	}
	
	/**
	 * 将中文字符串转换成拼音（该方法不建议使用，因为toHanyuPinyinString方法已经不建议使用，可能存在未知问题）
	 * @param str 传入的中文字符串
	 * @param fill  每个中文之间的分隔符
	 * @return
	 */
	public String str2PinyinOld(String str, String fill){
		String reStr = "";
		//转成拼音
		try {
			reStr = PinyinHelper.toHanyuPinyinString(str, format, fill);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return reStr;
	}
	
	/**
	 * 将中文字符串转换成拼音
	 * @param str  入的中文字符串
	 * @param fill 每个中文之间的分隔符
	 * @return
	 */
	public String str2Pinyin(String str, String fill){
		StringBuffer sb = new StringBuffer();
		boolean isCn = true;
		if(fill==null){
			fill = "";
		} 
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			//判断是否是中文，中文的accii码为\u4e00到\u9fa5
			if(c>='\u4e00' && c<='\u9fa5'){
				isCn = true;
				try {
					//中文字符转换成拼音
					sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}else{
				sb.append(c);
				isCn = false;
			}
			if(isCn && i<str.length()-1){
				sb.append(fill);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将中文字符串转换成拼音的首写字母
	 * @param str  入的中文字符串
	 * @param fill 每个中文之间的分隔符
	 * @return
	 */
	public String strFirst2Pinyin(String str, String fill){
		StringBuffer sb = new StringBuffer();
		if(fill==null){
			fill = "";
		} 
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			//判断是否是中文，中文的accii码为\u4e00到\u9fa5
			if(c>='\u4e00' && c<='\u9fa5'){
				try {
					//中文字符转换成拼音
					sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0].charAt(0)).append(fill);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
}
