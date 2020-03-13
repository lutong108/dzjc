package com.chinacreator.dzjc_quartz.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.chinacreator.dzjc_log.util.ConsoleLogUtil;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.Ta_Jc_Sum_XxylttjServiceIfc;
import com.chinacreator.dzjc_ruleEngine.ruleEngine.service.impl.Ta_Jc_Sum_XxylttjServiceImpl;

public class Ta_Jc_Sum_XxylttjServlet extends HttpServlet implements Job{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String exe(){
		Date beginDate = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = true;
		String message = "成功";
		System.out.println("湘西一路通统计");
		System.err.println("servlet处理开始......"+fat.format(beginDate));
		Ta_Jc_Sum_XxylttjServiceIfc ir = new Ta_Jc_Sum_XxylttjServiceImpl();
		try {
			ir.doStartup();
			map.put("beginDate", fat.format(beginDate));
		} catch (Exception e) {
			isSuccess = false;
			message = e.getMessage();
			e.printStackTrace();
		}
		map.put("isSuccess", isSuccess);
		map.put("message", message);
		Date endDate = new Date();
		map.put("endDate", fat.format(endDate));
		if(isSuccess){
			ConsoleLogUtil.insertConsoleLog("湘西一路通统计", "成功==》开始时间："+fat.format(beginDate)+" 结束时间："+fat.format(endDate));
		}else{
			ConsoleLogUtil.insertConsoleLog("湘西一路通统计", message);
		}
		System.err.println("servlet处理结束......开始时间："+fat.format(beginDate)+" 结束时间："+fat.format(endDate));
		return JSONObject.fromObject(map).toString();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String result = exe();
		PrintWriter writer = response.getWriter();
		writer.write(result);
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		exe();
	}

}
