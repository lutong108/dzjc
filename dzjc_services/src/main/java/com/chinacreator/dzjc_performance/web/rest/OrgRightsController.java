package com.chinacreator.dzjc_performance.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;



import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.uop.Organization;
import com.chinacreator.c2.web.util.RestUtils;
import com.chinacreator.dzjc_performance.VOrganization;
import com.chinacreator.util.RoleUtil;

import freemarker.template.Configuration;

@Controller
@Path("statistic/v1/")
@Consumes(MediaType.APPLICATION_JSON)
public class OrgRightsController {

	private static String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
	private Configuration configuration = null;

	/**
	 * 页面加载时，获取统计数据
	 * 
	 * @param org_id
	 * @return
	 */
	/*@GET
	@Path("orgrightsinfo")
	public Map<String, Object> getOrgRights(@QueryParam(value = "org_id") String org_id) {

		String code = null;
		try {

			// 查询当前登录用户编号
			code = RoleUtil.getInstance().queryCodeByUserId();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<>();

		// code为空时，只能是admin用户，目前只有admin用户不是挂在机构下的用户，没有系统业务操作权限
		if (StringUtils.isBlank(code)) {
			map.put("dataList", new ArrayList<OrgRightsInfo>());
			return map;
		}

		// 如果机构编号为空
		if (ParamsUtil.isNull(org_id)) {

			// 获取登录用户当前本级机构
			LocalOrganization orgByUser = RoleUtil.getInstance().getOrgByUser();

			if (orgByUser != null) {
				org_id = orgByUser.getOrgId();
			} else {
				org_id = getOrgIdByCode(code).getOrgId();
			}
		}

		OrgRightsInfo entity = new OrgRightsInfo();
		entity.setOrg_id(org_id);

		// 查询数据
		List<OrgRightsInfo> org_list = DaoFactory.create(OrgRightsInfo.class).getSession()
				.selectList("com.chinacreator.statistic.OrgRightsInfoMapper.selectByPage", entity);

		int ml_sum_all = 0;// 目录清单总条数
		int ml_xz_all = 0;// 行政许可
		int ml_gg_all = 0;// 公共服务

		int ss_sum_all = 0;// 已填报实施清单总数
		int ss_xz_all = 0;// 行政许可
		int ss_gg_all = 0;// 公共服务

		int erj_sum = 0;
		int sanj_sum = 0;
		int sij_sum = 0;

		int org_ss_all = 0;// 已填报实施清单的机构总数

		if (org_list.size() > 0) {

			for (OrgRightsInfo orgRightsInfo : org_list) {

				ml_sum_all += orgRightsInfo.getMl_sum();
				ml_xz_all += orgRightsInfo.getMl_xz();
				ml_gg_all += orgRightsInfo.getMl_gg();

				ss_sum_all += orgRightsInfo.getSs_sum();
				ss_xz_all += orgRightsInfo.getSs_xz();
				ss_gg_all += orgRightsInfo.getSs_gg();

				erj_sum += orgRightsInfo.getErj();
				sanj_sum += orgRightsInfo.getSanj();
				sij_sum += orgRightsInfo.getSij();

				// 已填报实施清单大于0，代表有记录
				if (orgRightsInfo.getSs_sum() > 0) {
					org_ss_all++;
				}
			}

			//具有政务服务职能的机构
			int org_num = org_list.size();

			// 集合元素大于一个，才做总计
			if (org_list.size() > 1) {

				OrgRightsInfo org = new OrgRightsInfo();
				org.setOrg_name("总计");
				org.setMl_xz(ml_xz_all);
				org.setMl_gg(ml_gg_all);
				org.setMl_sum(ml_sum_all);
				org.setSs_xz(ss_xz_all);
				org.setSs_gg(ss_gg_all);
				org.setSs_sum(ss_sum_all);
				org.setErj(erj_sum);
				org.setSanj(sanj_sum);
				org.setSij(sij_sum);

				org_list.add(org);
			}

			String data_str = "目录清单库事项总计" + ml_sum_all + "项，其中行政许可" + ml_xz_all + "项，公共服务" + ml_gg_all + "项；共" + org_num + "个单位具有政务服务职能，目前"
					+ org_ss_all + "个部门已开始填报实施清单，截止时间：" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "，已填报实施清单总数" + ss_sum_all
					+ "项，具体情况如下：";

			String titleName = getTitleNameByOrg(org_id).replace("湖南省本级", "省本级");

			map.put("dataList", org_list);
			map.put("dataStr", data_str);
			map.put("titleName", titleName + " （截至 " + new SimpleDateFormat("MM 月 dd 日 HH 时").format(new Date()) + " ）");
		}

		return map;

	}

	*//**
	 * word 导出
	 * 
	 * @param org_id
	 * @param dataStr
	 *//*
	@GET
	@Path("wordexport")
	@SuppressWarnings("deprecation")
	public String wordExport(String org_id, String dataStr) {

		// 根据自定义查询方法，查出数据
		List<OrgRightsInfo> dataList = this.getData(org_id);

		// 获取文档标题
		String titleName = getTitleNameByOrg(org_id);

		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");

		// 创建word导出对象，并导出
		OrgRightsWord word = new OrgRightsWord();
		word.createWord(configuration, dataStr, titleName, dataList);

		return titleName;

	}

	*//**
	 * excel 导出
	 * 
	 * @param org_id
	 * @param dataStr
	 *//*
	public String excelExport(String org_id, String dataStr) {

		// 根据自定义查询方法，查出数据
		List<OrgRightsInfo> dataList = this.getData(org_id);

		// 获取文档标题
		String titleName = getTitleNameByOrg(org_id);

		// 创建Excel，并写入
		OrgRightsExcel excel = new OrgRightsExcel();
		excel.excelExport(dataStr, titleName, dataList);

		return titleName;
	}

	*//**
	 * 自定义条件，统计数据
	 * 
	 * @param org_id
	 * @return
	 *//*
	private List<OrgRightsInfo> getData(String org_id) {

		String code = null;

		try {

			// 查询当前登录用户编号
			code = RoleUtil.getInstance().queryCodeByUserId();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// code为空时，只能是admin用户，目前只有admin用户不是挂在机构下的用户，没有系统业务操作权限
		if (StringUtils.isBlank(code)) {
			return new ArrayList<OrgRightsInfo>();
		}

		// 如果机构编号为空
		if (ParamsUtil.isNull(org_id)) {

			// 获取登录用户当前本级机构
			LocalOrganization orgByUser = RoleUtil.getInstance().getOrgByUser();

			if (orgByUser != null) {
				org_id = orgByUser.getOrgId();
			} else {
				org_id = getOrgIdByCode(code).getOrgId();
			}
		}

		OrgRightsInfo entity = new OrgRightsInfo();
		entity.setOrg_id(org_id);

		List<OrgRightsInfo> contents = DaoFactory.create(OrgRightsInfo.class).getSession()
				.selectList("com.chinacreator.statistic.OrgRightsInfoMapper.selectByPage", entity);

		return contents;

	}*/

	/**
	 * 根据角色编号，获取对应的机构编号
	 * 
	 * @param code
	 * @return
	 */
	public static VOrganization getOrgIdByCode(String code) {

		if (code.length() == 12) {// 普通用户

			code = code + "000000000000";

			List<VOrganization> list = DaoFactory.create(VOrganization.class).getSession()
					.selectList("com.chinacreator.dzjc_performance.VOrganizationMapper.selectorginfobycode", code);

			if (list.size() > 0) {
				return list.get(0);
			}

		} else if (code.length() == 2) {// 省级超级管理员

			code = code + "0000000000000000000000";

			List<VOrganization> list = DaoFactory.create(VOrganization.class).getSession()
					.selectList("com.chinacreator.dzjc_performance.VOrganizationMapper.selectorginfobycode", code);

			if (list.size() > 0) {
				return list.get(0);
			}

		} else if (code.length() == 4) {// 市级超级管理员

			code = code + "00000000000000000000";

			List<VOrganization> list = DaoFactory.create(VOrganization.class).getSession()
					.selectList("com.chinacreator.dzjc_performance.VOrganizationMapper.selectorginfobycode", code);

			if (list.size() > 0) {
				return list.get(0);
			}

		} else if (code.length() == 6) {// 区级超级管理员

			code = code + "000000000000000000";

			List<VOrganization> list = DaoFactory.create(VOrganization.class).getSession()
					.selectList("com.chinacreator.dzjc_performance.VOrganizationMapper.selectorginfobycode", code);

			if (list.size() > 0) {
				return list.get(0);
			}

		}

		String url = ConfigManager.getInstance().getConfig("c2_apigateway_url");
		Organization org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/{id}", Organization.class, code);

		VOrganization entity = new VOrganization();
		entity.setId(org.getId());
		entity.setName(org.getName());
		entity.setCode((String) org.get("xzqm"));

		return entity;

	}

	/**
	 * 获取报表文档标题
	 * 
	 * @param org_id
	 * @return
	 */
	/*private static String getTitleNameByOrg(String org_id) {

		// 标题
		String titleName = null;

		if (ParamsUtil.notNull(org_id)) {// 指定区域设置

			// 父级名称
			String parent_name = DaoFactory.create(OrgRightsInfo.class).getSession()
					.selectOne("com.chinacreator.statistic.OrgRightsInfoMapper.selectParentName", org_id);

			// 该机构名称
			Object org = RestUtils.createRestTemplate().getForObject(url + "/uop/v1/orgs/" + org_id, Object.class);
			String orname = String.valueOf(JSON.parseObject(JSON.toJSONString(org)).get("name"));

			// 如果父级为空时 || 完全相等时 || 本机构包含父机构时 || 父级为省，本机构为市时
			if (ParamsUtil.isNull(parent_name) || parent_name.equals(orname) || orname.indexOf(parent_name) != -1
					|| (parent_name.indexOf("省") != -1 && orname.indexOf("市") != -1)) {

				titleName = orname;

			} else {

				String[] array = { "省", "市", "州", "区", "县", "乡", "镇", "街道" };
				for (int i = 0; i < array.length; i++) {
					if (parent_name.indexOf(array[i]) != -1 && orname.indexOf(array[i]) != -1) {
						parent_name = parent_name.replace(array[i], "");
						if (parent_name.equals("直")) {
							parent_name = "";
						}
						break;
					}
				}

				if (parent_name.indexOf("市") != -1 && orname.indexOf("本级") != -1) {
					orname = "本级";
				}

				// 该机构包含'直'关键字时
				if (orname.indexOf("直") != -1) {
					orname = orname.replace("直", "本级");
				}

				// 本机构包含'湖南'关键字时 || 父级包含各种本级时
				if (orname.indexOf("湖南") != -1 || parent_name.indexOf("本级") != -1) {
					parent_name = "";
				}

				titleName = parent_name + orname;
			}

		} else {// 本区域设置

			// 获取登录用户当前本级机构
			LocalOrganization localOrganization = RoleUtil.getInstance().getOrgByUser();

			String name = null;
			if (localOrganization != null) {
				name = localOrganization.getOrgName();
			} else {
				String code = RoleUtil.getInstance().queryCodeByUserId();
				name = getOrgIdByCode(code).getOrgName();
			}

			if (name.equals("市直")) {

				// 查询父级名称
				String parent_name = DaoFactory.create(OrgRightsInfo.class).getSession()
						.selectOne("com.chinacreator.statistic.OrgRightsInfoMapper.selectParentName", localOrganization.getOrgId());

				name = parent_name + "本级";

			} else if (name.indexOf("直") != -1) {

				name = name.replace("直", "本级");
			}

			titleName = name;
		}

		return titleName += "政务服务事项梳理情况统计表";
	}*/

}
