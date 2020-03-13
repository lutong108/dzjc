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

import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JCSumSuperviseInfoWordExport {
	public void createWord(Configuration configuration, String titleName, List<List<JCSumSuperviseInfoAll>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();

		// 汇总导出数据
		getData(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("jcfp_data.ftl"); // 获取模板文件
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
	
	
	private void getData(Map<String, Object> wordMap, String titleName, List<List<JCSumSuperviseInfoAll>> dataList) {

		wordMap.put("titleName", titleName);

		// 保存每一行的数据
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		for (List<JCSumSuperviseInfoAll> citylist : dataList) {

			for (JCSumSuperviseInfoAll entity : citylist) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("area_name", entity.getArea_name());
				map.put("sls", entity.getSls());
				map.put("zcbjs", entity.getZcbjs());
				map.put("thbjs", entity.getThbjs());
				map.put("zfbjs", entity.getZfbjs());
				map.put("scbjs", entity.getScbjs());
				map.put("bjl", entity.getBjl());
				map.put("ljsls", entity.getLjsls());
				map.put("ljbjs", entity.getLjbjs());
				map.put("bysls", entity.getBysls());
				map.put("instance_yjs", entity.getInstance_yjs());
				map.put("instance_yellow", entity.getInstance_yellow());
				map.put("instance_red", entity.getInstance_red());
				map.put("instance_cancel_yellow_num", entity.getInstance_cancel_yellow_num());
				map.put("instance_cancel_red_num", entity.getInstance_cancel_red_num());
				map.put("complain_num", entity.getComplain_num());
				map.put("complain_reply_num", entity.getComplain_reply_num());
				map.put("complain_yjs", entity.getComplain_yjs());
				map.put("complain_yellow", entity.getComplain_yellow());
				map.put("complain_red", entity.getComplain_red());
				map.put("complain_cancel_yellow_num", entity.getComplain_cancel_yellow_num());
				map.put("complain_cancel_red_num", entity.getComplain_cancel_red_num());
				map.put("consult_num", entity.getConsult_num());
				map.put("consult_replay_num", entity.getConsult_replay_num());
				map.put("consult_yjs", entity.getConsult_yjs());
				map.put("consult_yellow", entity.getConsult_yellow());
				map.put("consult_red", entity.getConsult_red());
				map.put("consult_cancel_yellow_num", entity.getConsult_cancel_yellow_num());
				map.put("consult_cancel_red_num", entity.getConsult_cancel_red_num());
				map.put("special_supervise_green_num", entity.getSpecial_supervise_green_num());
				map.put("special_supervise_yellow_num", entity.getSpecial_supervise_yellow_num());
				map.put("special_supervise_red_num", entity.getSpecial_supervise_red_num());
				mapList.add(map);
			}

		}

		wordMap.put("mapList", mapList);

	}
}
