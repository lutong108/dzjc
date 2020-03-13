package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class QuanShengYingYongQingKuangStatisticsInfoWordExport {

	public void createWord(Configuration configuration, String titleName,Map<String, Object> map) {

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("qsyyqks_data.ftl"); // 获取模板文件
		} catch (IOException e) {
			e.printStackTrace();
		}

		String url = this.getClass().getResource("/").getPath();
		String u = url + titleName + ".doc";

		File outFile = new File(u); // 导出文件
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			t.process(map, out); // 将填充数据填入模板文件并输出到目标文件
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
