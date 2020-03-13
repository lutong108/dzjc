package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import com.chinacreator.dzjc_tongji.TongJi;

public class QuanShengFaPaiStatisticsInfoWordExport {
	
	public void createWord(Configuration configuration, String titleName, List<List<TongJi>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();

		// 汇总导出数据
		getData(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("qsfptj_data.ftl"); // 获取模板文件
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
			t.process(wordMap, out); // 将填充数据填入模板文件并输出到目标文件
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void getData(Map<String, Object> wordMap, String titleName, List<List<TongJi>> dataList) {

		wordMap.put("titleName", titleName);

		// 保存每一行的数据
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		for (List<TongJi> list : dataList) {

			for (TongJi entity : list) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("area_name", entity.getAreaOrgName());
				map.put("yjs", entity.getSuperviseGreenNum());
				map.put("yellow", entity.getSuperviseYelloNum());
				map.put("red", entity.getSuperviseRedNum());
				map.put("cancel_yellow_num", entity.getCancelYellowNum());
				map.put("cancel_red_num", entity.getCancelRedNum());
				
				mapList.add(map);
			}

		}
		wordMap.put("mapList", mapList);

	}
}
