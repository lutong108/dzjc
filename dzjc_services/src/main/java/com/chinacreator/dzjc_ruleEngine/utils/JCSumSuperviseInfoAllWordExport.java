package com.chinacreator.dzjc_ruleEngine.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.dzjc_ruleEngine.JCSumSuperviseInfoAll;
import com.chinacreator.dzjc_todoStatistics.EgovernmentListBean;
import com.chinacreator.dzjc_tongji.TongJi;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class JCSumSuperviseInfoAllWordExport {

	public void createWord(Configuration configuration, String titleName, List<List<JCSumSuperviseInfoAll>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();

		// 汇总导出数据
		getData(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("jcsp_data.ftl"); // 获取模板文件
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
	
	public String createWordbjty(Configuration configuration, String titleName, List<List<EgovernmentListBean>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();

		// 汇总导出数据
		getDatabjty(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("bjtj_data.ftl"); // 获取模板文件
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
		return u;
	}
	
	
	public String createWordbjtynew(Configuration configuration, String titleName, List<List<TongJi>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();

		// 汇总导出数据
		getDatabjtynew(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("bjtj_data.ftl"); // 获取模板文件
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
		return u;
	}
	
	public String wordExportForProvince(Configuration configuration, String titleName, List<List<TongJi>> dataList) {

		Map<String, Object> wordMap = new HashMap<String, Object>();

		// 汇总导出数据
		getDataForProvince(wordMap, titleName, dataList);

		// 模板文件所在路径
		configuration.setClassForTemplateLoading(this.getClass(), "");
		Template t = null;
		try {
			t = configuration.getTemplate("bjtj_data_province.ftl"); // 获取模板文件
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
		return u;
	}
	
	private void getData(Map<String, Object> wordMap, String titleName, List<List<JCSumSuperviseInfoAll>> dataList) {
		titleName=titleName.substring(8);
		wordMap.put("titleName", titleName);

		// 保存每一行的数据
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		for (List<JCSumSuperviseInfoAll> citylist : dataList) {

			for (JCSumSuperviseInfoAll citys : citylist) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("area_name", citys.getArea_name());
				map.put("yjs", citys.getYjs());
				map.put("yellow", citys.getYellow());
				map.put("red", citys.getRed());
				map.put("cancel_yellow_num", citys.getCancel_yellow_num());
				map.put("cancel_red_num", citys.getCancel_red_num());
				mapList.add(map);
			}

		}

		wordMap.put("mapList", mapList);

	}
	
	private void getDatabjty(Map<String, Object> wordMap, String titleName, List<List<EgovernmentListBean>> dataList) {
			titleName=titleName.substring(11);
			if(titleName.contains("-")){
				titleName=titleName.replaceAll("-", "/");
				//titleName=titleName.replace((char) 0, '/');
			}
		wordMap.put("titleName", titleName);

		// 保存每一行的数据
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		for (List<EgovernmentListBean> citylist : dataList) {
			
			for (EgovernmentListBean citys : citylist) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("areaName", citys.getAreaName());
				map.put("sj", String.valueOf(citys.getSj()));
				map.put("bj", String.valueOf(citys.getBj()));
				map.put("bjPercent", citys.getBjPercent());
				map.put("tsPercent", citys.getTsPercent());
				map.put("bjyellow", String.valueOf(citys.getBjyellow()));
				map.put("bjred", String.valueOf(citys.getBjred()));
				map.put("cnyellow", String.valueOf(citys.getCnyellow()));
				map.put("cnred", String.valueOf(citys.getCnred()));
				map.put("csred", String.valueOf(citys.getCsred()));
				map.put("csyellow", String.valueOf(citys.getCsyellow()));
				map.put("cn", String.valueOf(citys.getCn()));
				map.put("crn", String.valueOf(citys.getCrn()));
				map.put("csn", String.valueOf(citys.getCsn()));
				map.put("csrn", String.valueOf(citys.getCsrn()));
				mapList.add(map);
			}

		}

		wordMap.put("mapList", mapList);

	}
	
	
	private void getDatabjtynew(Map<String, Object> wordMap, String titleName, List<List<TongJi>> dataList) {
		titleName=titleName.substring(9);
		if(titleName.contains("-")){
			titleName=titleName.replaceAll("-", "/");
			//titleName=titleName.replace((char) 0, '/');
		}
	wordMap.put("titleName", titleName);

	// 保存每一行的数据
	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

	for (List<TongJi> citylist : dataList) {
		for (TongJi citys : citylist) {
			DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaName", citys.getAreaOrgName()); 
			map.put("sj", decimalFormat.format(citys.getAcceptNum())); //acceptNum
			map.put("bj", decimalFormat.format(citys.getFinishNum())); //finishNum
			map.put("bjPercent", citys.getBanJieLvFloat()); //banJieLvFloat
			//map.put("tsPercent", citys.getTiSuLvFloat());//tiSuLvFloat
			map.put("legalAccelerationRate", citys.getLegalAccelerationRate());
			map.put("promiseAccelerationRate", citys.getPromiseAccelerationRate());
			map.put("bjyellow", decimalFormat.format(citys.getInstanceSuperviseYelloNum()));//instanceSuperviseYelloNum
			map.put("bjred", decimalFormat.format(citys.getInstanceSuperviseRedNum())); //instanceSuperviseRedNum办件红牌数(张)
			map.put("cnyellow", decimalFormat.format(citys.getComplainSuperviseYelloNum())); //complainSuperviseYelloNum
			map.put("cnred", decimalFormat.format(citys.getComplainSuperviseRedNum()));//complainSuperviseRedNum
			map.put("csred", decimalFormat.format(citys.getConsultSuperviseRedNum()));//consultSuperviseRedNum
			map.put("csyellow", decimalFormat.format(citys.getConsultSuperviseYelloNum()));//consultSuperviseYelloNum
			map.put("cn", decimalFormat.format(citys.getComNum()));//comNum
			map.put("crn", decimalFormat.format(citys.getComplainReplyNum()));//complainReplyNum
			map.put("csn", decimalFormat.format(citys.getConsultNum()));//consultNum
			map.put("csrn", decimalFormat.format(citys.getConsultReplayNum()));//consultReplayNum
			mapList.add(map);
		}

	}
	wordMap.put("mapList", mapList);
}
	
	private void getDataForProvince(Map<String, Object> wordMap, String titleName, List<List<TongJi>> dataList) {
		titleName=titleName.substring(7);
		if(titleName.contains("-")){
			titleName=titleName.replaceAll("-", "/");
			//titleName=titleName.replace((char) 0, '/');
		}
	wordMap.put("titleName", titleName);

	// 保存每一行的数据
	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

	for (List<TongJi> citylist : dataList) {
		for (TongJi citys : citylist) {
			DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaName", citys.getAreaOrgName()); 
			map.put("sj", decimalFormat.format(citys.getAcceptNum())); //acceptNum
			map.put("acceptNumNew", decimalFormat.format(citys.getAcceptNumNew()));
			map.put("bj", decimalFormat.format(citys.getFinishNum())); //finishNum
			map.put("bjPercent", citys.getBanJieLvFloat()); //banJieLvFloat
			map.put("legalAccelerationRate", citys.getLegalAccelerationRate());
			map.put("promiseAccelerationRate", citys.getPromiseAccelerationRate());
			map.put("bjyellow", decimalFormat.format(citys.getInstanceSuperviseYelloNum()));//instanceSuperviseYelloNum
			map.put("bjred", decimalFormat.format(citys.getInstanceSuperviseRedNum())); //instanceSuperviseRedNum办件红牌数(张)
			map.put("cnyellow", decimalFormat.format(citys.getComplainSuperviseYelloNum())); //complainSuperviseYelloNum
			map.put("cnred", decimalFormat.format(citys.getComplainSuperviseRedNum()));//complainSuperviseRedNum
			map.put("csred", decimalFormat.format(citys.getConsultSuperviseRedNum()));//consultSuperviseRedNum
			map.put("csyellow", decimalFormat.format(citys.getConsultSuperviseYelloNum()));//consultSuperviseYelloNum
			map.put("cn", decimalFormat.format(citys.getComNum()));//comNum
			map.put("crn", decimalFormat.format(citys.getComplainReplyNum()));//complainReplyNum
			map.put("csn", decimalFormat.format(citys.getConsultNum()));//consultNum
			map.put("csrn", decimalFormat.format(citys.getConsultReplayNum()));//consultReplayNum
			mapList.add(map);
		}

	}
	wordMap.put("mapList", mapList);
}
	
}
