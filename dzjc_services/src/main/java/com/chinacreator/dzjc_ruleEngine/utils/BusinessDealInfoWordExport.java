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

public class BusinessDealInfoWordExport {
	
	public void createWord(Configuration configuration, String titleName, List<List<TongJi>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();

		// 汇总导出数据
		getData(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("business_deal_info.ftl"); // 获取模板文件
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

		for (List<TongJi> citylist : dataList) {

			for (TongJi entity : citylist) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("area_name", entity.getAreaOrgName());
				map.put("sls", entity.getAcceptNum());
				map.put("zcbjs", entity.getNormalFinishNum());
				map.put("thbjs", entity.getUnthreadfinishNum());
				map.put("zfbjs", entity.getThrowFinishNum());
				map.put("bjl", entity.getBusinessDealHandlingRate());
				map.put("bysls", entity.getNoAcceptNum());
				map.put("zrrsls", entity.getNaturalPersonNum());
				map.put("frsls", entity.getLegalPersonNum());
				map.put("instance_yjs", entity.getInstanceSuperviseGreenNum());
				map.put("instance_yellow", entity.getInstanceSuperviseYelloNum());
				map.put("instance_red", entity.getInstanceSuperviseRedNum());
				map.put("instance_cancel_yellow_num", entity.getInstanceCancelYellowNum());
				map.put("instance_cancel_red_num", entity.getInstanceCancelRedNum());
				map.put("complain_num", entity.getComNum());
				map.put("complain_reply_num", entity.getComplainReplyNum());
				map.put("complain_yjs", entity.getComplainSuperviseGreenNum());
				map.put("complain_yellow", entity.getComplainSuperviseYelloNum());
				map.put("complain_red", entity.getComplainSuperviseRedNum());
				map.put("complain_cancel_yellow_num", entity.getComplainCancelYellowNum());
				map.put("complain_cancel_red_num", entity.getComplainCancelRedNum());
				map.put("consult_num", entity.getConsultNum());
				map.put("consult_replay_num", entity.getConsultReplayNum());
				map.put("consult_yjs", entity.getConsultSuperviseGreenNum());
				map.put("consult_yellow", entity.getConsultSuperviseYelloNum());
				map.put("consult_red", entity.getConsultSuperviseRedNum());
				map.put("consult_cancel_yellow_num", entity.getConsultCancelYellowNum());
				map.put("consult_cancel_red_num", entity.getConsultCancelRedNum());
				mapList.add(map);
			}

		}
		wordMap.put("mapList", mapList);

	}
}
