package com.chinacreator.dzjc_ruleEngine.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinacreator.dzjc_complain.web.rest.TaJcComplainSuggestController;
import com.chinacreator.dzjc_ruleEngine.utils.DownLoadUtil;
import com.chinacreator.dzjc_ruleEngine.web.rest.HotApproveController;
import com.chinacreator.dzjc_ruleEngine.web.rest.SuperviseInfoComplainController;
import com.chinacreator.dzjc_ruleEngine.web.rest.SuperviseInfoInstanceController;
import com.chinacreator.dzjc_ruleEngine.web.rest.Superviseinfo_huanjieController;
import com.chinacreator.dzjc_ruleEngine.web.rest.SuperviserInfoConsultController;
import com.chinacreator.dzjc_tongji.web.rest.Ta_Jc_Sum_XxylttjController;
import com.chinacreator.util.FilesUtil;

/**
 * 
 * @author lilang excel导出servlet
 * 
 */
public class ExportExcelServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 存放文件夹所在路径
	private String srcPath = this.getClass().getResource("/").getPath();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 类型判断:办件1、投诉2、咨询3、热点事项4  环节时限监察5
		String type = request.getParameter("type");
		String cond = request.getParameter("cond");
		String path = null;
		try {
			if ("1".equals(type)) {
				String titleName = new SuperviseInfoInstanceController().excelExport(cond);
				path = srcPath + titleName + ".xls";
			}
			else if("2".equals(type)){
				String titleName = new SuperviseInfoComplainController().excelExport(cond);
				path = srcPath + titleName + ".xls";
			}
			else if("3".equals(type)){
				String titleName = new SuperviserInfoConsultController().excelExport(cond);
				path = srcPath + titleName + ".xls";
			}
			else if ("4".equals(type)) {
				String titleName = new HotApproveController().excelExport(cond);
				path = srcPath + titleName + ".xls";
			}
			else if("5".equals(type)){
				String titleName = new Superviseinfo_huanjieController().excelExport(cond);
				path = srcPath + titleName + ".xls";
			}
			else if("6".equals(type)){
				String titleName = new TaJcComplainSuggestController().excelExport(cond);
				path = srcPath + titleName + ".xls";
			}else if("7".equals(type) || "8".equals(type)|| "9".equals(type)|| "10".equals(type)|| "11".equals(type)|| "12".equals(type)){
				String titleName = new Ta_Jc_Sum_XxylttjController().excelExport(cond,type);
				path = srcPath + titleName + ".xls";
			}
			System.err.println("路径"+path);
			if(path!=null && !"".equals(path)){
				// 用户下载
				DownLoadUtil.download(path, response, request);
				// 删除临时文件
				FilesUtil.deleteFile(path);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
