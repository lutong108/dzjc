package com.chinacreator.dzjc_tongji;

import java.math.BigDecimal;
import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 统计
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.TongJi", table = "TA_JC_SUM_SUPERVISE_INFO", ds = "acceptdata", cache = false)
public class TongJi {
	/**
	 *机构id
	 */
	@Column(id = "org_id", datatype = "string128")
	private java.lang.String orgId;

	/**
	 *机构名
	 */
	@Column(id = "area_org_name", datatype = "string256")
	private java.lang.String areaOrgName;

	/**
	 *机构编码
	 */
	@Column(id = "area_org_code", datatype = "string128")
	private java.lang.String areaOrgCode;

	/**
	 *省级编码
	 */
	@Column(id = "province_code", datatype = "string64")
	private java.lang.String provinceCode;

	/**
	 *市级编码
	 */
	@Column(id = "city_code", datatype = "string64")
	private java.lang.String cityCode;

	/**
	 *县级编码
	 */
	@Column(id = "county_code", datatype = "string64")
	private java.lang.String countyCode;

	/**
	 *街道编码
	 */
	@Column(id = "street_code", datatype = "string64")
	private java.lang.String streetCode;

	/**
	 *社区编码
	 */
	@Column(id = "village_code", datatype = "string20")
	private java.lang.String villageCode;

	/**
	 *--级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
	 */
	@Column(id = "org_level", datatype = "string64")
	private java.lang.String orgLevel;

	/**
	 *本级 1：省本级 2： 市本级 9: 其他
	 */
	@Column(id = "org_this_level", datatype = "string64")
	private java.lang.String orgThisLevel;
	
	/**
	 *是否多规合一（0：否；1：是）
	 */
	@Column(id = "is_investment_supervision", datatype = "string64")
	private java.lang.String isInvestmentSupervision;

	/**
	 *统计数据时间
	 */
	@Column(id = "now_date", datatype = "string20")
	private java.lang.String nowDate;

	/**
	 *统计数据时间
	 */
	@Column(id = "accept_now_date", datatype = "string20")
	private java.lang.String acceptNowDate;

	/**
	 *受理数(存储过程1：只查办件)
	 */
	@Column(id = "accept_num", datatype = "bigdecimal")
	private java.math.BigDecimal acceptNum;

	/**
	 *大集中受理数(存储过程1：只查办件),该统计暂时有问题
	 */
	@Column(id = "djz_accept_num", datatype = "bigdouble")
	private java.lang.Double djzAcceptNum;

	/**
	 *办结数(存储过程2：只查办件)
	 */
	@Column(id = "finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal finishNum;

	/**
	 *大集中办结数(存储过程2：只查办件),该统计暂时有问题
	 */
	@Column(id = "djz_finish_num", datatype = "bigdouble")
	private java.lang.Double djzFinishNum;

	/**
	 *正常办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	@Column(id = "normal_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal normalFinishNum;

	/**
	 *提前办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	@Column(id = "before_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal beforeFinishNum;

	/**
	 *过期办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	@Column(id = "after_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal afterFinishNum;

	/**
	 *退窗办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	@Column(id = "unthreadfinish_num", datatype = "bigdecimal")
	private java.math.BigDecimal unthreadfinishNum;

	/**
	 *作废办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	@Column(id = "throw_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal throwFinishNum;

	/**
	 *日办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	@Column(id = "day_finish_num", datatype = "bigdecimal")
	private java.math.BigDecimal dayFinishNum;

	/**
	 *所用时间总和（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	@Column(id = "sum_wasting_time", datatype = "bigdecimal")
	private java.math.BigDecimal sumWastingTime;

	/**
	 *收费金额（暂无统计）
	 */
	@Column(id = "charge_money", datatype = "bigdouble")
	private java.lang.Double chargeMoney;

	/**
	 *即办件(存储过程1：只查办件)
	 */
	@Column(id = "rightoff_num", datatype = "bigdecimal")
	private java.math.BigDecimal rightoffNum;

	/**
	 *承诺件(存储过程1：只查办件)
	 */
	@Column(id = "respond_num", datatype = "bigdecimal")
	private java.math.BigDecimal respondNum;

	/**
	 *联办件(存储过程1：只查办件)
	 */
	@Column(id = "union_num", datatype = "bigdecimal")
	private java.math.BigDecimal unionNum;

	/**
	 *上报件(存储过程1：只查办件)
	 */
	@Column(id = "up_num", datatype = "bigdecimal")
	private java.math.BigDecimal upNum;

	/**
	 *行政许可实例数(存储过程1：只查办件)--待确认
	 */
	@Column(id = "xzxk_num", datatype = "bigdouble")
	private java.lang.Double xzxkNum;

	/**
	 *非行政许可实例数(存储过程1：只查办件)-待确认
	 */
	@Column(id = "fxzxk_num", datatype = "bigdouble")
	private java.lang.Double fxzxkNum;

	/**
	 *便民服务实例数(存储过程1：只查办件)-待确认
	 */
	@Column(id = "bmfw_num", datatype = "bigdouble")
	private java.lang.Double bmfwNum;

	/**
	 *行政许可办结数（暂无统计）
	 */
	@Column(id = "xzxk_finish_num", datatype = "bigdouble")
	private java.lang.Double xzxkFinishNum;

	/**
	 *非行政许可办结数（暂无统计）
	 */
	@Column(id = "fxzxk_finish_num", datatype = "bigdouble")
	private java.lang.Double fxzxkFinishNum;

	/**
	 *便民服务办结数（暂无统计）
	 */
	@Column(id = "bmfw_finish_num", datatype = "bigdouble")
	private java.lang.Double bmfwFinishNum;

	/**
	 *有效投诉量(存储过程5：只查投诉)
	 */
	@Column(id = "complain_num", datatype = "bigdecimal")
	private java.math.BigDecimal complainNum;

	/**
	 *投诉数(存储过程6：只查投诉)
	 */
	@Column(id = "com_num", datatype = "bigdouble")
	private java.lang.Double comNum;

	/**
	 *投诉回复数(存储过程7：只查投诉)
	 */
	@Column(id = "complain_reply_num", datatype = "bigdouble")
	private java.lang.Double complainReplyNum;

	/**
	 *咨询数(存储过程8：只查咨询)
	 */
	@Column(id = "consult_num", datatype = "bigdouble")
	private java.lang.Double consultNum;

	/**
	 *咨询回复数(存储过程9：只查咨询)
	 */
	@Column(id = "consult_replay_num", datatype = "bigdouble")
	private java.lang.Double consultReplayNum;

	/**
	 *投资额（暂无统计）
	 */
	@Column(id = "money_num", datatype = "bigdouble")
	private java.lang.Double moneyNum;

	/**
	 *发正常牌数(存储过程1：只查办件)
	 */
	@Column(id = "normal_num", datatype = "bigdecimal")
	private java.math.BigDecimal normalNum;

	/**
	 *发红牌数(存储过程1：只查办件)
	 */
	@Column(id = "red_num", datatype = "bigdecimal")
	private java.math.BigDecimal redNum;

	/**
	 *发黄牌数(存储过程1：只查办件)
	 */
	@Column(id = "yello_num", datatype = "bigdecimal")
	private java.math.BigDecimal yelloNum;

	/**
	 *发预警牌数(存储过程1：只查办件)
	 */
	@Column(id = "green_num", datatype = "bigdecimal")
	private java.math.BigDecimal greenNum;

	/**
	 *状态
	 */
	@Column(id = "status", datatype = "string5")
	private java.lang.String status;

	/**
	 *在办正常数(存储过程1：只查办件)
	 */
	@Column(id = "now_normal_num", datatype = "bigdouble")
	private java.lang.Double nowNormalNum;

	/**
	 *在办红牌数(存储过程1：只查办件)
	 */
	@Column(id = "now_red_num", datatype = "bigdouble")
	private java.lang.Double nowRedNum;

	/**
	 *在办黄牌数(存储过程1：只查办件)
	 */
	@Column(id = "now_yello_num", datatype = "bigdouble")
	private java.lang.Double nowYelloNum;

	/**
	 *在办预警数(存储过程1：只查办件)
	 */
	@Column(id = "now_green_num", datatype = "bigdouble")
	private java.lang.Double nowGreenNum;

	/**
	 *办结正常数(存储过程1：只查办件)
	 */
	@Column(id = "finish_normal_num", datatype = "bigdouble")
	private java.lang.Double finishNormalNum;

	/**
	 *办结红牌数(存储过程1：只查办件)
	 */
	@Column(id = "finish_red_num", datatype = "bigdouble")
	private java.lang.Double finishRedNum;

	/**
	 *办结黄牌数(存储过程1：只查办件)
	 */
	@Column(id = "finish_yello_num", datatype = "bigdouble")
	private java.lang.Double finishYelloNum;

	/**
	 *办结预警数(存储过程1：只查办件)
	 */
	@Column(id = "finish_green_num", datatype = "bigdouble")
	private java.lang.Double finishGreenNum;

	/**
	 *按监察时间统计预警数(存储过程3：查所有)
	 */
	@Column(id = "supervise_green_num", datatype = "bigdouble")
	private java.lang.Double superviseGreenNum;

	/**
	 *按监察时间统计黄牌数(存储过程3：查所有)
	 */
	@Column(id = "supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double superviseYelloNum;

	/**
	 *按监察时间统计红牌数(存储过程3：查所有)
	 */
	@Column(id = "supervise_red_num", datatype = "bigdouble")
	private java.lang.Double superviseRedNum;

	/**
	 *按取消/撤销时间统计黄牌数(存储过程4：查所有)
	 */
	@Column(id = "cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double cancelYellowNum;

	/**
	 *按取消/撤销时间统计红牌数(存储过程4：查所有)
	 */
	@Column(id = "cancel_red_num", datatype = "bigdouble")
	private java.lang.Double cancelRedNum;
	
	/**
	 * 撤销的预警数
	 */
	private java.lang.Double cancelGreenNum;

	public java.lang.Double getCancelGreenNum() {
		return cancelGreenNum;
	}

	public void setCancelGreenNum(java.lang.Double cancelGreenNum) {
		this.cancelGreenNum = cancelGreenNum;
	}

	/**
	 *主键ID
	 */
	@Column(id = "record_id", type = ColumnType.uuid, datatype = "string64")
	private java.lang.String recordId;

	/**
	 *按监察时间统计办件预警数（存储过程10：只查办件）
	 */
	@Column(id = "instance_supervise_green_num", datatype = "bigdouble")
	private java.lang.Double instanceSuperviseGreenNum;

	/**
	 *按监察时间统计办件黄牌数（存储过程10：只查办件））
	 */
	@Column(id = "instance_supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double instanceSuperviseYelloNum;

	/**
	 *按监察时间统计办件红牌数（存储过程10：只查办件））
	 */
	@Column(id = "instance_supervise_red_num", datatype = "bigdouble")
	private java.lang.Double instanceSuperviseRedNum;

	/**
	 *按取消/撤销时间统计办件黄牌数(存储过程11：只查办件)
	 */
	@Column(id = "instance_cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double instanceCancelYellowNum;

	/**
	 *按取消/撤销时间统计办件红牌数(存储过程11：只查办件)
	 */
	@Column(id = "instance_cancel_red_num", datatype = "bigdouble")
	private java.lang.Double instanceCancelRedNum;

	/**
	 *按监察时间统计投诉预警数(存储过程12：只查投诉)
	 */
	@Column(id = "complain_supervise_green_num", datatype = "bigdouble")
	private java.lang.Double complainSuperviseGreenNum;

	/**
	 *按监察时间统计投诉黄牌数(存储过程12：只查投诉)
	 */
	@Column(id = "complain_supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double complainSuperviseYelloNum;

	/**
	 *按监察时间统计投诉红牌数(存储过程12：只查投诉)
	 */
	@Column(id = "complain_supervise_red_num", datatype = "bigdouble")
	private java.lang.Double complainSuperviseRedNum;

	/**
	 *按取消/撤销时间统计投诉黄牌数(存储过程13：只查投诉)
	 */
	@Column(id = "complain_cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double complainCancelYellowNum;

	/**
	 *按取消/撤销时间统计投诉红牌数(存储过程13：只查投诉)
	 */
	@Column(id = "complain_cancel_red_num", datatype = "bigdouble")
	private java.lang.Double complainCancelRedNum;

	/**
	 *按监察时间统计咨询预警数(存储过程14：只查咨询)
	 */
	@Column(id = "consult_supervise_green_num", datatype = "bigdouble")
	private java.lang.Double consultSuperviseGreenNum;

	/**
	 *按监察时间统计咨询黄牌数(存储过程14：只查咨询)
	 */
	@Column(id = "consult_supervise_yello_num", datatype = "bigdouble")
	private java.lang.Double consultSuperviseYelloNum;

	/**
	 *按监察时间统计咨询红牌数(存储过程14：只查咨询)
	 */
	@Column(id = "consult_supervise_red_num", datatype = "bigdouble")
	private java.lang.Double consultSuperviseRedNum;

	/**
	 *按取消/撤销时间统计咨询黄牌数(存储过程15：只查咨询)
	 */
	@Column(id = "consult_cancel_yellow_num", datatype = "bigdouble")
	private java.lang.Double consultCancelYellowNum;

	/**
	 *按取消/撤销时间统计咨询红牌数(存储过程15：只查咨询)
	 */
	@Column(id = "consult_cancel_red_num", datatype = "bigdouble")
	private java.lang.Double consultCancelRedNum;

	/**
	 *行政许可收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzxke_num", datatype = "bigdouble")
	private java.lang.Double xzxkeNum;

	/**
	 *行政奖励收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzjl_num", datatype = "bigdouble")
	private java.lang.Double xzjlNum;

	/**
	 *行政裁决收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzcj_num", datatype = "bigdouble")
	private java.lang.Double xzcjNum;

	/**
	 *行政检查收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzjc_num", datatype = "bigdouble")
	private java.lang.Double xzjcNum;

	/**
	 *行政给付收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzgf_num", datatype = "bigdouble")
	private java.lang.Double xzgfNum;

	/**
	 *行政强制收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzqz_num", datatype = "bigdouble")
	private java.lang.Double xzqzNum;

	/**
	 *行政确认收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzqr_num", datatype = "bigdouble")
	private java.lang.Double xzqrNum;

	/**
	 *行政处罚收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzcf_num", datatype = "bigdouble")
	private java.lang.Double xzcfNum;

	/**
	 *行政征收收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "xzzs_num", datatype = "bigdouble")
	private java.lang.Double xzzsNum;

	/**
	 *其他行政权利收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "qtxzql_num", datatype = "bigdouble")
	private java.lang.Double qtxzqlNum;

	/**
	 *公共服务 收件数(存储过程16：查办件和咨询)
	 */
	@Column(id = "ggfw_num", datatype = "bigdouble")
	private java.lang.Double ggfwNum;

	/**
	 *行政许可办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzxke_finish_num", datatype = "bigdouble")
	private java.lang.Double xzxkeFinishNum;

	/**
	 *行政奖励办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzjl_finish_num", datatype = "bigdouble")
	private java.lang.Double xzjlFinishNum;

	/**
	 *行政裁决办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzcj_finish_num", datatype = "bigdouble")
	private java.lang.Double xzcjFinishNum;

	/**
	 *行政检查办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzjc_finish_num", datatype = "bigdouble")
	private java.lang.Double xzjcFinishNum;

	/**
	 *行政给付办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzgf_finish_num", datatype = "bigdouble")
	private java.lang.Double xzgfFinishNum;

	/**
	 *行政强制办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzqz_finish_num", datatype = "bigdouble")
	private java.lang.Double xzqzFinishNum;

	/**
	 *行政确认办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzqr_finish_num", datatype = "bigdouble")
	private java.lang.Double xzqrFinishNum;

	/**
	 *行政处罚办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzcf_finish_num", datatype = "bigdouble")
	private java.lang.Double xzcfFinishNum;

	/**
	 *行政征收办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "xzzs_finish_num", datatype = "bigdouble")
	private java.lang.Double xzzsFinishNum;

	/**
	 *其他行政权利办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "qtxzql_finish_num", datatype = "bigdouble")
	private java.lang.Double qtxzqlFinishNum;

	/**
	 *公共服务办结数(存储过程17：查办件和咨询)
	 */
	@Column(id = "ggfw_finish_num", datatype = "bigdouble")
	private java.lang.Double ggfwFinishNum;

	/**
	 *按监察时间统计特别程序预警数(存储过程18：只查特别程序)
	 */
	@Column(id = "special_supervise_green_num", datatype = "bigdouble")
	private java.lang.Double specialSuperviseGreenNum;

	/**
	 *按监察时间统计特别程序黄牌数(存储过程18：只查特别程序)
	 */
	@Column(id = "special_supervise_yellow_num", datatype = "bigdouble")
	private java.lang.Double specialSuperviseYellowNum;

	/**
	 *按监察时间统计特别程序红牌数(存储过程18：只查特别程序)
	 */
	@Column(id = "special_supervise_red_num", datatype = "bigdouble")
	private java.lang.Double specialSuperviseRedNum;
	
	/**
	 *按监察时间统计网上办事预警数(存储过程19)
	 */
	@Column(id = "WSBS_GREEN_NUM", datatype = "bigdouble")
	private java.lang.Double wsbsGreenNum;
	
	/**
	 *按监察时间统计网上办事黄牌数(存储过程19)
	 */
	@Column(id = "WSBS_YELLOW_NUM", datatype = "bigdouble")
	private java.lang.Double wsbsYellowNum;
	
	/**
	 *按监察时间统计网上办事红牌数(存储过程19)
	 */
	@Column(id = "WSBS_RED_NUM", datatype = "bigdouble")
	private java.lang.Double wsbsRedNum;
	
	/**
	 *按监察时间统计办件出证预警牌数(存储过程20)
	 */
	@Column(id = "BJCZ_GREEN_NUM", datatype = "bigdouble")
	private java.lang.Double bjczGreenNum;
	
	/**
	 *按监察时间统计办件出证黄牌数(存储过程20)
	 */
	@Column(id = "BJCZ_YELLOW_NUM", datatype = "bigdouble")
	private java.lang.Double bjczYellowNum;
	
	/**
	 *按监察时间统计办件出证红牌数(存储过程20)
	 */
	@Column(id = "BJCZ_RED_NUM", datatype = "bigdouble")
	private java.lang.Double bjczRedNum;
	
	/**
	 * 不予受理数
	 */
	@Column(id = "NO_ACCEPT_NUM", datatype = "bigdouble")
	private java.lang.Double noAcceptNum;
	
	
	private String xzxksxs;//       --事项数   行政许可 
	private String xzjlsxs;//       --事项数   行政奖励
	private String xzcjsxs;//       --事项数   行政裁决
	private String xzjcsxs;//       --事项数   行政检查
	private String xzgfsxs;//       --事项数   行政给付
	private String xzqzsxs;//       --事项数   行政强制
	private String xzqrsxs;//       --事项数   行政确认
	private String xzcfsxs;//       --事项数   行政处罚
	private String xzzssxs;//       --事项数   行政征收
	private String qtxzqlsxs;//     --事项数   其他行政权利
	private String ggfwsxs;//       --事项数   公共服务
	private String sxscount;//      --事项数   小计

	private String xzxksjs;//       --收件数   行政许可 
	private String xzjlsjs;//       --收件数   行政奖励
	private String xzcjsjs;//       --收件数   行政裁决
	private String xzjcsjs;//       --收件数   行政检查
	private String xzgfsjs;//       --收件数   行政给付
	private String xzqzsjs;//       --收件数   行政强制
	private String xzqrsjs;//       --收件数   行政确认
	private String xzcfsjs;//       --收件数   行政处罚
	private String xzzssjs;//       --收件数   行政征收
	private String qtxzqlsjs;//     --收件数   其他行政权利
	private String ggfwsjs;//       --收件数   公共服务 
	private String sjscount;//      --收件数   小计

	private String xzxkbjs;//       --办结数   行政许可 
	private String xzjlbjs;//       --办结数   行政奖励
	private String xzcjbjs;//       --办结数   行政裁决
	private String xzjcbjs;//       --办结数   行政检查
	private String xzgfbjs;//       --办结数   行政给付
	private String xzqzbjs;//       --办结数   行政强制
	private String xzqrbjs;//       --办结数   行政确认
	private String xzcfbjs;//       --办结数   行政处罚
	private String xzzsbjs;//       --办结数   行政征收
	private String qtxzqlbjs;//     --办结数   其他行政权利
	private String ggfwbjs;//       --办结数   公共服务
	private String bjscount;//      --办结数   小计
	
	/**
	 * 环节发牌
	 */
	private java.lang.Double superviseSegmentGreenNum;// --按监察时间统计环节预警数(存储过程11：查所有)
	private java.lang.Double superviseSegmentYelloNum;// --按监察时间统计环节黄牌数(存储过程11：查所有)
	private java.lang.Double superviseSegmentRedNum;//   --按监察时间统计环节红牌数(存储过程11：查所有)
	private java.lang.Double cancelSegmentYellowNum;//   --按取消/撤销时间统计环节黄牌数(存储过程12：查所有)
	private java.lang.Double cancelSegmentRedNum;//      --按取消/撤销时间统计环节红牌数(存储过程12：查所有)
	private java.lang.Double naturalPersonNum;//         --自然人受理数（存储过程1）
	private java.lang.Double legalPersonNum;//           --法人受理数（存储过程1）
	
	public java.lang.Double getSuperviseSegmentGreenNum() {
		return superviseSegmentGreenNum;
	}

	public void setSuperviseSegmentGreenNum(
			java.lang.Double superviseSegmentGreenNum) {
		this.superviseSegmentGreenNum = superviseSegmentGreenNum;
	}

	public java.lang.Double getSuperviseSegmentYelloNum() {
		return superviseSegmentYelloNum;
	}

	public void setSuperviseSegmentYelloNum(
			java.lang.Double superviseSegmentYelloNum) {
		this.superviseSegmentYelloNum = superviseSegmentYelloNum;
	}

	public java.lang.Double getSuperviseSegmentRedNum() {
		return superviseSegmentRedNum;
	}

	public void setSuperviseSegmentRedNum(java.lang.Double superviseSegmentRedNum) {
		this.superviseSegmentRedNum = superviseSegmentRedNum;
	}

	public java.lang.Double getCancelSegmentYellowNum() {
		return cancelSegmentYellowNum;
	}

	public void setCancelSegmentYellowNum(java.lang.Double cancelSegmentYellowNum) {
		this.cancelSegmentYellowNum = cancelSegmentYellowNum;
	}

	public java.lang.Double getCancelSegmentRedNum() {
		return cancelSegmentRedNum;
	}

	public void setCancelSegmentRedNum(java.lang.Double cancelSegmentRedNum) {
		this.cancelSegmentRedNum = cancelSegmentRedNum;
	}

	public java.lang.Double getNaturalPersonNum() {
		return naturalPersonNum;
	}

	public void setNaturalPersonNum(java.lang.Double naturalPersonNum) {
		this.naturalPersonNum = naturalPersonNum;
	}

	public java.lang.Double getLegalPersonNum() {
		return legalPersonNum;
	}

	public void setLegalPersonNum(java.lang.Double legalPersonNum) {
		this.legalPersonNum = legalPersonNum;
	}

	public String getXzxksxs() {
		return xzxksxs;
	}

	public void setXzxksxs(String xzxksxs) {
		this.xzxksxs = xzxksxs;
	}

	public String getXzjlsxs() {
		return xzjlsxs;
	}

	public void setXzjlsxs(String xzjlsxs) {
		this.xzjlsxs = xzjlsxs;
	}

	public String getXzcjsxs() {
		return xzcjsxs;
	}

	public void setXzcjsxs(String xzcjsxs) {
		this.xzcjsxs = xzcjsxs;
	}

	public String getXzjcsxs() {
		return xzjcsxs;
	}

	public void setXzjcsxs(String xzjcsxs) {
		this.xzjcsxs = xzjcsxs;
	}

	public String getXzgfsxs() {
		return xzgfsxs;
	}

	public void setXzgfsxs(String xzgfsxs) {
		this.xzgfsxs = xzgfsxs;
	}

	public String getXzqzsxs() {
		return xzqzsxs;
	}

	public void setXzqzsxs(String xzqzsxs) {
		this.xzqzsxs = xzqzsxs;
	}

	public String getXzqrsxs() {
		return xzqrsxs;
	}

	public void setXzqrsxs(String xzqrsxs) {
		this.xzqrsxs = xzqrsxs;
	}

	public String getXzcfsxs() {
		return xzcfsxs;
	}

	public void setXzcfsxs(String xzcfsxs) {
		this.xzcfsxs = xzcfsxs;
	}

	public String getXzzssxs() {
		return xzzssxs;
	}

	public void setXzzssxs(String xzzssxs) {
		this.xzzssxs = xzzssxs;
	}

	public String getQtxzqlsxs() {
		return qtxzqlsxs;
	}

	public void setQtxzqlsxs(String qtxzqlsxs) {
		this.qtxzqlsxs = qtxzqlsxs;
	}

	public String getGgfwsxs() {
		return ggfwsxs;
	}

	public void setGgfwsxs(String ggfwsxs) {
		this.ggfwsxs = ggfwsxs;
	}

	public String getSxscount() {
		return sxscount;
	}

	public void setSxscount(String sxscount) {
		this.sxscount = sxscount;
	}

	public String getXzxksjs() {
		return xzxksjs;
	}

	public void setXzxksjs(String xzxksjs) {
		this.xzxksjs = xzxksjs;
	}

	public String getXzjlsjs() {
		return xzjlsjs;
	}

	public void setXzjlsjs(String xzjlsjs) {
		this.xzjlsjs = xzjlsjs;
	}

	public String getXzcjsjs() {
		return xzcjsjs;
	}

	public void setXzcjsjs(String xzcjsjs) {
		this.xzcjsjs = xzcjsjs;
	}

	public String getXzjcsjs() {
		return xzjcsjs;
	}

	public void setXzjcsjs(String xzjcsjs) {
		this.xzjcsjs = xzjcsjs;
	}

	public String getXzgfsjs() {
		return xzgfsjs;
	}

	public void setXzgfsjs(String xzgfsjs) {
		this.xzgfsjs = xzgfsjs;
	}

	public String getXzqzsjs() {
		return xzqzsjs;
	}

	public void setXzqzsjs(String xzqzsjs) {
		this.xzqzsjs = xzqzsjs;
	}

	public String getXzqrsjs() {
		return xzqrsjs;
	}

	public void setXzqrsjs(String xzqrsjs) {
		this.xzqrsjs = xzqrsjs;
	}

	public String getXzcfsjs() {
		return xzcfsjs;
	}

	public void setXzcfsjs(String xzcfsjs) {
		this.xzcfsjs = xzcfsjs;
	}

	public String getXzzssjs() {
		return xzzssjs;
	}

	public void setXzzssjs(String xzzssjs) {
		this.xzzssjs = xzzssjs;
	}

	public String getQtxzqlsjs() {
		return qtxzqlsjs;
	}

	public void setQtxzqlsjs(String qtxzqlsjs) {
		this.qtxzqlsjs = qtxzqlsjs;
	}

	public String getGgfwsjs() {
		return ggfwsjs;
	}

	public void setGgfwsjs(String ggfwsjs) {
		this.ggfwsjs = ggfwsjs;
	}

	public String getSjscount() {
		return sjscount;
	}

	public void setSjscount(String sjscount) {
		this.sjscount = sjscount;
	}

	public String getXzxkbjs() {
		return xzxkbjs;
	}

	public void setXzxkbjs(String xzxkbjs) {
		this.xzxkbjs = xzxkbjs;
	}

	public String getXzjlbjs() {
		return xzjlbjs;
	}

	public void setXzjlbjs(String xzjlbjs) {
		this.xzjlbjs = xzjlbjs;
	}

	public String getXzcjbjs() {
		return xzcjbjs;
	}

	public void setXzcjbjs(String xzcjbjs) {
		this.xzcjbjs = xzcjbjs;
	}

	public String getXzjcbjs() {
		return xzjcbjs;
	}

	public void setXzjcbjs(String xzjcbjs) {
		this.xzjcbjs = xzjcbjs;
	}

	public String getXzgfbjs() {
		return xzgfbjs;
	}

	public void setXzgfbjs(String xzgfbjs) {
		this.xzgfbjs = xzgfbjs;
	}

	public String getXzqzbjs() {
		return xzqzbjs;
	}

	public void setXzqzbjs(String xzqzbjs) {
		this.xzqzbjs = xzqzbjs;
	}

	public String getXzqrbjs() {
		return xzqrbjs;
	}

	public void setXzqrbjs(String xzqrbjs) {
		this.xzqrbjs = xzqrbjs;
	}

	public String getXzcfbjs() {
		return xzcfbjs;
	}

	public void setXzcfbjs(String xzcfbjs) {
		this.xzcfbjs = xzcfbjs;
	}

	public String getXzzsbjs() {
		return xzzsbjs;
	}

	public void setXzzsbjs(String xzzsbjs) {
		this.xzzsbjs = xzzsbjs;
	}

	public String getQtxzqlbjs() {
		return qtxzqlbjs;
	}

	public void setQtxzqlbjs(String qtxzqlbjs) {
		this.qtxzqlbjs = qtxzqlbjs;
	}

	public String getGgfwbjs() {
		return ggfwbjs;
	}

	public void setGgfwbjs(String ggfwbjs) {
		this.ggfwbjs = ggfwbjs;
	}

	public String getBjscount() {
		return bjscount;
	}

	public void setBjscount(String bjscount) {
		this.bjscount = bjscount;
	}

	/**
	 * 获取不予受理数
	 */
	public java.lang.Double getNoAcceptNum() {
		return noAcceptNum;
	}

	/**
	 * 设置不予受理数
	 */
	public void setNoAcceptNum(java.lang.Double noAcceptNum) {
		this.noAcceptNum = noAcceptNum;
	}


	/**
	 * 设置机构id
	 */
	public void setOrgId(java.lang.String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取机构id
	 */
	public java.lang.String getOrgId() {
		return orgId;
	}

	/**
	 * 设置机构名
	 */
	public void setAreaOrgName(java.lang.String areaOrgName) {
		this.areaOrgName = areaOrgName;
	}

	/**
	 * 获取机构名
	 */
	public java.lang.String getAreaOrgName() {
		return areaOrgName;
	}

	/**
	 * 设置机构编码
	 */
	public void setAreaOrgCode(java.lang.String areaOrgCode) {
		this.areaOrgCode = areaOrgCode;
	}

	/**
	 * 获取机构编码
	 */
	public java.lang.String getAreaOrgCode() {
		return areaOrgCode;
	}

	/**
	 * 设置省级编码
	 */
	public void setProvinceCode(java.lang.String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * 获取省级编码
	 */
	public java.lang.String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * 设置市级编码
	 */
	public void setCityCode(java.lang.String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 获取市级编码
	 */
	public java.lang.String getCityCode() {
		return cityCode;
	}

	/**
	 * 设置县级编码
	 */
	public void setCountyCode(java.lang.String countyCode) {
		this.countyCode = countyCode;
	}

	/**
	 * 获取县级编码
	 */
	public java.lang.String getCountyCode() {
		return countyCode;
	}

	/**
	 * 设置街道编码
	 */
	public void setStreetCode(java.lang.String streetCode) {
		this.streetCode = streetCode;
	}

	/**
	 * 获取街道编码
	 */
	public java.lang.String getStreetCode() {
		return streetCode;
	}

	/**
	 * 设置社区编码
	 */
	public void setVillageCode(java.lang.String villageCode) {
		this.villageCode = villageCode;
	}

	/**
	 * 获取社区编码
	 */
	public java.lang.String getVillageCode() {
		return villageCode;
	}

	/**
	 * 设置--级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
	 */
	public void setOrgLevel(java.lang.String orgLevel) {
		this.orgLevel = orgLevel;
	}

	/**
	 * 获取--级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
	 */
	public java.lang.String getOrgLevel() {
		return orgLevel;
	}

	/**
	 * 设置本级 1：省本级 2： 市本级 9: 其他
	 */
	public void setOrgThisLevel(java.lang.String orgThisLevel) {
		this.orgThisLevel = orgThisLevel;
	}

	/**
	 * 获取本级 1：省本级 2： 市本级 9: 其他
	 */
	public java.lang.String getOrgThisLevel() {
		return orgThisLevel;
	}

	/**
	 * 设置统计数据时间
	 */
	public void setNowDate(java.lang.String nowDate) {
		this.nowDate = nowDate;
	}

	/**
	 * 获取统计数据时间
	 */
	public java.lang.String getNowDate() {
		return nowDate;
	}

	/**
	 * 设置统计数据时间
	 */
	public void setAcceptNowDate(java.lang.String acceptNowDate) {
		this.acceptNowDate = acceptNowDate;
	}

	/**
	 * 获取统计数据时间
	 */
	public java.lang.String getAcceptNowDate() {
		return acceptNowDate;
	}

	/**
	 * 设置受理数(存储过程1：只查办件)
	 */
	public void setAcceptNum(java.math.BigDecimal acceptNum) {
		this.acceptNum = acceptNum;
	}

	/**
	 * 获取受理数(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getAcceptNum() {
		return acceptNum;
	}

	/**
	 * 设置大集中受理数(存储过程1：只查办件),该统计暂时有问题
	 */
	public void setDjzAcceptNum(java.lang.Double djzAcceptNum) {
		this.djzAcceptNum = djzAcceptNum;
	}

	/**
	 * 获取大集中受理数(存储过程1：只查办件),该统计暂时有问题
	 */
	public java.lang.Double getDjzAcceptNum() {
		return djzAcceptNum;
	}

	/**
	 * 设置办结数(存储过程2：只查办件)
	 */
	public void setFinishNum(java.math.BigDecimal finishNum) {
		this.finishNum = finishNum;
	}

	/**
	 * 获取办结数(存储过程2：只查办件)
	 */
	public java.math.BigDecimal getFinishNum() {
		return finishNum;
	}

	/**
	 * 设置大集中办结数(存储过程2：只查办件),该统计暂时有问题
	 */
	public void setDjzFinishNum(java.lang.Double djzFinishNum) {
		this.djzFinishNum = djzFinishNum;
	}

	/**
	 * 获取大集中办结数(存储过程2：只查办件),该统计暂时有问题
	 */
	public java.lang.Double getDjzFinishNum() {
		return djzFinishNum;
	}

	/**
	 * 设置正常办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public void setNormalFinishNum(java.math.BigDecimal normalFinishNum) {
		this.normalFinishNum = normalFinishNum;
	}

	/**
	 * 获取正常办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public java.math.BigDecimal getNormalFinishNum() {
		return normalFinishNum;
	}

	/**
	 * 设置提前办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public void setBeforeFinishNum(java.math.BigDecimal beforeFinishNum) {
		this.beforeFinishNum = beforeFinishNum;
	}

	/**
	 * 获取提前办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public java.math.BigDecimal getBeforeFinishNum() {
		return beforeFinishNum;
	}

	/**
	 * 设置过期办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public void setAfterFinishNum(java.math.BigDecimal afterFinishNum) {
		this.afterFinishNum = afterFinishNum;
	}

	/**
	 * 获取过期办结数（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public java.math.BigDecimal getAfterFinishNum() {
		return afterFinishNum;
	}

	/**
	 * 设置退窗办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public void setUnthreadfinishNum(java.math.BigDecimal unthreadfinishNum) {
		this.unthreadfinishNum = unthreadfinishNum;
	}

	/**
	 * 获取退窗办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public java.math.BigDecimal getUnthreadfinishNum() {
		return unthreadfinishNum;
	}

	/**
	 * 设置作废办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public void setThrowFinishNum(java.math.BigDecimal throwFinishNum) {
		this.throwFinishNum = throwFinishNum;
	}

	/**
	 * 获取作废办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public java.math.BigDecimal getThrowFinishNum() {
		return throwFinishNum;
	}

	/**
	 * 设置日办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public void setDayFinishNum(java.math.BigDecimal dayFinishNum) {
		this.dayFinishNum = dayFinishNum;
	}

	/**
	 * 获取日办结（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public java.math.BigDecimal getDayFinishNum() {
		return dayFinishNum;
	}

	/**
	 * 设置所用时间总和（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public void setSumWastingTime(java.math.BigDecimal sumWastingTime) {
		this.sumWastingTime = sumWastingTime;
	}

	/**
	 * 获取所用时间总和（存储过程2：只查办件,包括一般办结、出证办结、上报办结）
	 */
	public java.math.BigDecimal getSumWastingTime() {
		return sumWastingTime;
	}

	/**
	 * 设置收费金额（暂无统计）
	 */
	public void setChargeMoney(java.lang.Double chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	/**
	 * 获取收费金额（暂无统计）
	 */
	public java.lang.Double getChargeMoney() {
		return chargeMoney;
	}

	/**
	 * 设置即办件(存储过程1：只查办件)
	 */
	public void setRightoffNum(java.math.BigDecimal rightoffNum) {
		this.rightoffNum = rightoffNum;
	}

	/**
	 * 获取即办件(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getRightoffNum() {
		return rightoffNum;
	}

	/**
	 * 设置承诺件(存储过程1：只查办件)
	 */
	public void setRespondNum(java.math.BigDecimal respondNum) {
		this.respondNum = respondNum;
	}

	/**
	 * 获取承诺件(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getRespondNum() {
		return respondNum;
	}

	/**
	 * 设置联办件(存储过程1：只查办件)
	 */
	public void setUnionNum(java.math.BigDecimal unionNum) {
		this.unionNum = unionNum;
	}

	/**
	 * 获取联办件(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getUnionNum() {
		return unionNum;
	}

	/**
	 * 设置上报件(存储过程1：只查办件)
	 */
	public void setUpNum(java.math.BigDecimal upNum) {
		this.upNum = upNum;
	}

	/**
	 * 获取上报件(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getUpNum() {
		return upNum;
	}

	/**
	 * 设置行政许可实例数(存储过程1：只查办件)--待确认
	 */
	public void setXzxkNum(java.lang.Double xzxkNum) {
		this.xzxkNum = xzxkNum;
	}

	/**
	 * 获取行政许可实例数(存储过程1：只查办件)--待确认
	 */
	public java.lang.Double getXzxkNum() {
		return xzxkNum;
	}

	/**
	 * 设置非行政许可实例数(存储过程1：只查办件)-待确认
	 */
	public void setFxzxkNum(java.lang.Double fxzxkNum) {
		this.fxzxkNum = fxzxkNum;
	}

	/**
	 * 获取非行政许可实例数(存储过程1：只查办件)-待确认
	 */
	public java.lang.Double getFxzxkNum() {
		return fxzxkNum;
	}

	/**
	 * 设置便民服务实例数(存储过程1：只查办件)-待确认
	 */
	public void setBmfwNum(java.lang.Double bmfwNum) {
		this.bmfwNum = bmfwNum;
	}

	/**
	 * 获取便民服务实例数(存储过程1：只查办件)-待确认
	 */
	public java.lang.Double getBmfwNum() {
		return bmfwNum;
	}

	/**
	 * 设置行政许可办结数（暂无统计）
	 */
	public void setXzxkFinishNum(java.lang.Double xzxkFinishNum) {
		this.xzxkFinishNum = xzxkFinishNum;
	}

	/**
	 * 获取行政许可办结数（暂无统计）
	 */
	public java.lang.Double getXzxkFinishNum() {
		return xzxkFinishNum;
	}

	/**
	 * 设置非行政许可办结数（暂无统计）
	 */
	public void setFxzxkFinishNum(java.lang.Double fxzxkFinishNum) {
		this.fxzxkFinishNum = fxzxkFinishNum;
	}

	/**
	 * 获取非行政许可办结数（暂无统计）
	 */
	public java.lang.Double getFxzxkFinishNum() {
		return fxzxkFinishNum;
	}

	/**
	 * 设置便民服务办结数（暂无统计）
	 */
	public void setBmfwFinishNum(java.lang.Double bmfwFinishNum) {
		this.bmfwFinishNum = bmfwFinishNum;
	}

	/**
	 * 获取便民服务办结数（暂无统计）
	 */
	public java.lang.Double getBmfwFinishNum() {
		return bmfwFinishNum;
	}

	/**
	 * 设置有效投诉量(存储过程5：只查投诉)
	 */
	public void setComplainNum(java.math.BigDecimal complainNum) {
		this.complainNum = complainNum;
	}

	/**
	 * 获取有效投诉量(存储过程5：只查投诉)
	 */
	public java.math.BigDecimal getComplainNum() {
		return complainNum;
	}

	/**
	 * 设置投诉数(存储过程6：只查投诉)
	 */
	public void setComNum(java.lang.Double comNum) {
		this.comNum = comNum;
	}

	/**
	 * 获取投诉数(存储过程6：只查投诉)
	 */
	public java.lang.Double getComNum() {
		return comNum;
	}

	/**
	 * 设置投诉回复数(存储过程7：只查投诉)
	 */
	public void setComplainReplyNum(java.lang.Double complainReplyNum) {
		this.complainReplyNum = complainReplyNum;
	}

	/**
	 * 获取投诉回复数(存储过程7：只查投诉)
	 */
	public java.lang.Double getComplainReplyNum() {
		return complainReplyNum;
	}

	/**
	 * 设置咨询数(存储过程8：只查咨询)
	 */
	public void setConsultNum(java.lang.Double consultNum) {
		this.consultNum = consultNum;
	}

	/**
	 * 获取咨询数(存储过程8：只查咨询)
	 */
	public java.lang.Double getConsultNum() {
		return consultNum;
	}

	/**
	 * 设置咨询回复数(存储过程9：只查咨询)
	 */
	public void setConsultReplayNum(java.lang.Double consultReplayNum) {
		this.consultReplayNum = consultReplayNum;
	}

	/**
	 * 获取咨询回复数(存储过程9：只查咨询)
	 */
	public java.lang.Double getConsultReplayNum() {
		return consultReplayNum;
	}

	/**
	 * 设置投资额（暂无统计）
	 */
	public void setMoneyNum(java.lang.Double moneyNum) {
		this.moneyNum = moneyNum;
	}

	/**
	 * 获取投资额（暂无统计）
	 */
	public java.lang.Double getMoneyNum() {
		return moneyNum;
	}

	/**
	 * 设置发正常牌数(存储过程1：只查办件)
	 */
	public void setNormalNum(java.math.BigDecimal normalNum) {
		this.normalNum = normalNum;
	}

	/**
	 * 获取发正常牌数(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getNormalNum() {
		return normalNum;
	}

	/**
	 * 设置发红牌数(存储过程1：只查办件)
	 */
	public void setRedNum(java.math.BigDecimal redNum) {
		this.redNum = redNum;
	}

	/**
	 * 获取发红牌数(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getRedNum() {
		return redNum;
	}

	/**
	 * 设置发黄牌数(存储过程1：只查办件)
	 */
	public void setYelloNum(java.math.BigDecimal yelloNum) {
		this.yelloNum = yelloNum;
	}

	/**
	 * 获取发黄牌数(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getYelloNum() {
		return yelloNum;
	}

	/**
	 * 设置发预警牌数(存储过程1：只查办件)
	 */
	public void setGreenNum(java.math.BigDecimal greenNum) {
		this.greenNum = greenNum;
	}

	/**
	 * 获取发预警牌数(存储过程1：只查办件)
	 */
	public java.math.BigDecimal getGreenNum() {
		return greenNum;
	}

	/**
	 * 设置状态
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * 获取状态
	 */
	public java.lang.String getStatus() {
		return status;
	}

	/**
	 * 设置在办正常数(存储过程1：只查办件)
	 */
	public void setNowNormalNum(java.lang.Double nowNormalNum) {
		this.nowNormalNum = nowNormalNum;
	}

	/**
	 * 获取在办正常数(存储过程1：只查办件)
	 */
	public java.lang.Double getNowNormalNum() {
		return nowNormalNum;
	}

	/**
	 * 设置在办红牌数(存储过程1：只查办件)
	 */
	public void setNowRedNum(java.lang.Double nowRedNum) {
		this.nowRedNum = nowRedNum;
	}

	/**
	 * 获取在办红牌数(存储过程1：只查办件)
	 */
	public java.lang.Double getNowRedNum() {
		return nowRedNum;
	}

	/**
	 * 设置在办黄牌数(存储过程1：只查办件)
	 */
	public void setNowYelloNum(java.lang.Double nowYelloNum) {
		this.nowYelloNum = nowYelloNum;
	}

	/**
	 * 获取在办黄牌数(存储过程1：只查办件)
	 */
	public java.lang.Double getNowYelloNum() {
		return nowYelloNum;
	}

	/**
	 * 设置在办预警数(存储过程1：只查办件)
	 */
	public void setNowGreenNum(java.lang.Double nowGreenNum) {
		this.nowGreenNum = nowGreenNum;
	}

	/**
	 * 获取在办预警数(存储过程1：只查办件)
	 */
	public java.lang.Double getNowGreenNum() {
		return nowGreenNum;
	}

	/**
	 * 设置办结正常数(存储过程1：只查办件)
	 */
	public void setFinishNormalNum(java.lang.Double finishNormalNum) {
		this.finishNormalNum = finishNormalNum;
	}

	/**
	 * 获取办结正常数(存储过程1：只查办件)
	 */
	public java.lang.Double getFinishNormalNum() {
		return finishNormalNum;
	}

	/**
	 * 设置办结红牌数(存储过程1：只查办件)
	 */
	public void setFinishRedNum(java.lang.Double finishRedNum) {
		this.finishRedNum = finishRedNum;
	}

	/**
	 * 获取办结红牌数(存储过程1：只查办件)
	 */
	public java.lang.Double getFinishRedNum() {
		return finishRedNum;
	}

	/**
	 * 设置办结黄牌数(存储过程1：只查办件)
	 */
	public void setFinishYelloNum(java.lang.Double finishYelloNum) {
		this.finishYelloNum = finishYelloNum;
	}

	/**
	 * 获取办结黄牌数(存储过程1：只查办件)
	 */
	public java.lang.Double getFinishYelloNum() {
		return finishYelloNum;
	}

	/**
	 * 设置办结预警数(存储过程1：只查办件)
	 */
	public void setFinishGreenNum(java.lang.Double finishGreenNum) {
		this.finishGreenNum = finishGreenNum;
	}

	/**
	 * 获取办结预警数(存储过程1：只查办件)
	 */
	public java.lang.Double getFinishGreenNum() {
		return finishGreenNum;
	}

	/**
	 * 设置按监察时间统计预警数(存储过程3：查所有)
	 */
	public void setSuperviseGreenNum(java.lang.Double superviseGreenNum) {
		this.superviseGreenNum = superviseGreenNum;
	}

	/**
	 * 获取按监察时间统计预警数(存储过程3：查所有)
	 */
	public java.lang.Double getSuperviseGreenNum() {
		return superviseGreenNum;
	}

	/**
	 * 设置按监察时间统计黄牌数(存储过程3：查所有)
	 */
	public void setSuperviseYelloNum(java.lang.Double superviseYelloNum) {
		this.superviseYelloNum = superviseYelloNum;
	}

	/**
	 * 获取按监察时间统计黄牌数(存储过程3：查所有)
	 */
	public java.lang.Double getSuperviseYelloNum() {
		return superviseYelloNum;
	}

	/**
	 * 设置按监察时间统计红牌数(存储过程3：查所有)
	 */
	public void setSuperviseRedNum(java.lang.Double superviseRedNum) {
		this.superviseRedNum = superviseRedNum;
	}

	/**
	 * 获取按监察时间统计红牌数(存储过程3：查所有)
	 */
	public java.lang.Double getSuperviseRedNum() {
		return superviseRedNum;
	}

	/**
	 * 设置按取消/撤销时间统计黄牌数(存储过程4：查所有)
	 */
	public void setCancelYellowNum(java.lang.Double cancelYellowNum) {
		this.cancelYellowNum = cancelYellowNum;
	}

	/**
	 * 获取按取消/撤销时间统计黄牌数(存储过程4：查所有)
	 */
	public java.lang.Double getCancelYellowNum() {
		return cancelYellowNum;
	}

	/**
	 * 设置按取消/撤销时间统计红牌数(存储过程4：查所有)
	 */
	public void setCancelRedNum(java.lang.Double cancelRedNum) {
		this.cancelRedNum = cancelRedNum;
	}

	/**
	 * 获取按取消/撤销时间统计红牌数(存储过程4：查所有)
	 */
	public java.lang.Double getCancelRedNum() {
		return cancelRedNum;
	}

	/**
	 * 设置主键ID
	 */
	public void setRecordId(java.lang.String recordId) {
		this.recordId = recordId;
	}

	/**
	 * 获取主键ID
	 */
	public java.lang.String getRecordId() {
		return recordId;
	}

	/**
	 * 设置按监察时间统计办件预警数（存储过程10：只查办件）
	 */
	public void setInstanceSuperviseGreenNum(
			java.lang.Double instanceSuperviseGreenNum) {
		this.instanceSuperviseGreenNum = instanceSuperviseGreenNum;
	}

	/**
	 * 获取按监察时间统计办件预警数（存储过程10：只查办件）
	 */
	public java.lang.Double getInstanceSuperviseGreenNum() {
		return instanceSuperviseGreenNum;
	}

	/**
	 * 设置按监察时间统计办件黄牌数（存储过程10：只查办件））
	 */
	public void setInstanceSuperviseYelloNum(
			java.lang.Double instanceSuperviseYelloNum) {
		this.instanceSuperviseYelloNum = instanceSuperviseYelloNum;
	}

	/**
	 * 获取按监察时间统计办件黄牌数（存储过程10：只查办件））
	 */
	public java.lang.Double getInstanceSuperviseYelloNum() {
		return instanceSuperviseYelloNum;
	}

	/**
	 * 设置按监察时间统计办件红牌数（存储过程10：只查办件））
	 */
	public void setInstanceSuperviseRedNum(
			java.lang.Double instanceSuperviseRedNum) {
		this.instanceSuperviseRedNum = instanceSuperviseRedNum;
	}

	/**
	 * 获取按监察时间统计办件红牌数（存储过程10：只查办件））
	 */
	public java.lang.Double getInstanceSuperviseRedNum() {
		return instanceSuperviseRedNum;
	}

	/**
	 * 设置按取消/撤销时间统计办件黄牌数(存储过程11：只查办件)
	 */
	public void setInstanceCancelYellowNum(
			java.lang.Double instanceCancelYellowNum) {
		this.instanceCancelYellowNum = instanceCancelYellowNum;
	}

	/**
	 * 获取按取消/撤销时间统计办件黄牌数(存储过程11：只查办件)
	 */
	public java.lang.Double getInstanceCancelYellowNum() {
		return instanceCancelYellowNum;
	}

	/**
	 * 设置按取消/撤销时间统计办件红牌数(存储过程11：只查办件)
	 */
	public void setInstanceCancelRedNum(java.lang.Double instanceCancelRedNum) {
		this.instanceCancelRedNum = instanceCancelRedNum;
	}

	/**
	 * 获取按取消/撤销时间统计办件红牌数(存储过程11：只查办件)
	 */
	public java.lang.Double getInstanceCancelRedNum() {
		return instanceCancelRedNum;
	}

	/**
	 * 设置按监察时间统计投诉预警数(存储过程12：只查投诉)
	 */
	public void setComplainSuperviseGreenNum(
			java.lang.Double complainSuperviseGreenNum) {
		this.complainSuperviseGreenNum = complainSuperviseGreenNum;
	}

	/**
	 * 获取按监察时间统计投诉预警数(存储过程12：只查投诉)
	 */
	public java.lang.Double getComplainSuperviseGreenNum() {
		return complainSuperviseGreenNum;
	}

	/**
	 * 设置按监察时间统计投诉黄牌数(存储过程12：只查投诉)
	 */
	public void setComplainSuperviseYelloNum(
			java.lang.Double complainSuperviseYelloNum) {
		this.complainSuperviseYelloNum = complainSuperviseYelloNum;
	}

	/**
	 * 获取按监察时间统计投诉黄牌数(存储过程12：只查投诉)
	 */
	public java.lang.Double getComplainSuperviseYelloNum() {
		return complainSuperviseYelloNum;
	}

	/**
	 * 设置按监察时间统计投诉红牌数(存储过程12：只查投诉)
	 */
	public void setComplainSuperviseRedNum(
			java.lang.Double complainSuperviseRedNum) {
		this.complainSuperviseRedNum = complainSuperviseRedNum;
	}

	/**
	 * 获取按监察时间统计投诉红牌数(存储过程12：只查投诉)
	 */
	public java.lang.Double getComplainSuperviseRedNum() {
		return complainSuperviseRedNum;
	}

	/**
	 * 设置按取消/撤销时间统计投诉黄牌数(存储过程13：只查投诉)
	 */
	public void setComplainCancelYellowNum(
			java.lang.Double complainCancelYellowNum) {
		this.complainCancelYellowNum = complainCancelYellowNum;
	}

	/**
	 * 获取按取消/撤销时间统计投诉黄牌数(存储过程13：只查投诉)
	 */
	public java.lang.Double getComplainCancelYellowNum() {
		return complainCancelYellowNum;
	}

	/**
	 * 设置按取消/撤销时间统计投诉红牌数(存储过程13：只查投诉)
	 */
	public void setComplainCancelRedNum(java.lang.Double complainCancelRedNum) {
		this.complainCancelRedNum = complainCancelRedNum;
	}

	/**
	 * 获取按取消/撤销时间统计投诉红牌数(存储过程13：只查投诉)
	 */
	public java.lang.Double getComplainCancelRedNum() {
		return complainCancelRedNum;
	}

	/**
	 * 设置按监察时间统计咨询预警数(存储过程14：只查咨询)
	 */
	public void setConsultSuperviseGreenNum(
			java.lang.Double consultSuperviseGreenNum) {
		this.consultSuperviseGreenNum = consultSuperviseGreenNum;
	}

	/**
	 * 获取按监察时间统计咨询预警数(存储过程14：只查咨询)
	 */
	public java.lang.Double getConsultSuperviseGreenNum() {
		return consultSuperviseGreenNum;
	}

	/**
	 * 设置按监察时间统计咨询黄牌数(存储过程14：只查咨询)
	 */
	public void setConsultSuperviseYelloNum(
			java.lang.Double consultSuperviseYelloNum) {
		this.consultSuperviseYelloNum = consultSuperviseYelloNum;
	}

	/**
	 * 获取按监察时间统计咨询黄牌数(存储过程14：只查咨询)
	 */
	public java.lang.Double getConsultSuperviseYelloNum() {
		return consultSuperviseYelloNum;
	}

	/**
	 * 设置按监察时间统计咨询红牌数(存储过程14：只查咨询)
	 */
	public void setConsultSuperviseRedNum(
			java.lang.Double consultSuperviseRedNum) {
		this.consultSuperviseRedNum = consultSuperviseRedNum;
	}

	/**
	 * 获取按监察时间统计咨询红牌数(存储过程14：只查咨询)
	 */
	public java.lang.Double getConsultSuperviseRedNum() {
		return consultSuperviseRedNum;
	}

	/**
	 * 设置按取消/撤销时间统计咨询黄牌数(存储过程15：只查咨询)
	 */
	public void setConsultCancelYellowNum(
			java.lang.Double consultCancelYellowNum) {
		this.consultCancelYellowNum = consultCancelYellowNum;
	}

	/**
	 * 获取按取消/撤销时间统计咨询黄牌数(存储过程15：只查咨询)
	 */
	public java.lang.Double getConsultCancelYellowNum() {
		return consultCancelYellowNum;
	}

	/**
	 * 设置按取消/撤销时间统计咨询红牌数(存储过程15：只查咨询)
	 */
	public void setConsultCancelRedNum(java.lang.Double consultCancelRedNum) {
		this.consultCancelRedNum = consultCancelRedNum;
	}

	/**
	 * 获取按取消/撤销时间统计咨询红牌数(存储过程15：只查咨询)
	 */
	public java.lang.Double getConsultCancelRedNum() {
		return consultCancelRedNum;
	}

	/**
	 * 设置行政许可收件数(存储过程16：查办件和咨询)
	 */
	public void setXzxkeNum(java.lang.Double xzxkeNum) {
		this.xzxkeNum = xzxkeNum;
	}

	/**
	 * 获取行政许可收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzxkeNum() {
		return xzxkeNum;
	}

	/**
	 * 设置行政奖励收件数(存储过程16：查办件和咨询)
	 */
	public void setXzjlNum(java.lang.Double xzjlNum) {
		this.xzjlNum = xzjlNum;
	}

	/**
	 * 获取行政奖励收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzjlNum() {
		return xzjlNum;
	}

	/**
	 * 设置行政裁决收件数(存储过程16：查办件和咨询)
	 */
	public void setXzcjNum(java.lang.Double xzcjNum) {
		this.xzcjNum = xzcjNum;
	}

	/**
	 * 获取行政裁决收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzcjNum() {
		return xzcjNum;
	}

	/**
	 * 设置行政检查收件数(存储过程16：查办件和咨询)
	 */
	public void setXzjcNum(java.lang.Double xzjcNum) {
		this.xzjcNum = xzjcNum;
	}

	/**
	 * 获取行政检查收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzjcNum() {
		return xzjcNum;
	}

	/**
	 * 设置行政给付收件数(存储过程16：查办件和咨询)
	 */
	public void setXzgfNum(java.lang.Double xzgfNum) {
		this.xzgfNum = xzgfNum;
	}

	/**
	 * 获取行政给付收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzgfNum() {
		return xzgfNum;
	}

	/**
	 * 设置行政强制收件数(存储过程16：查办件和咨询)
	 */
	public void setXzqzNum(java.lang.Double xzqzNum) {
		this.xzqzNum = xzqzNum;
	}

	/**
	 * 获取行政强制收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzqzNum() {
		return xzqzNum;
	}

	/**
	 * 设置行政确认收件数(存储过程16：查办件和咨询)
	 */
	public void setXzqrNum(java.lang.Double xzqrNum) {
		this.xzqrNum = xzqrNum;
	}

	/**
	 * 获取行政确认收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzqrNum() {
		return xzqrNum;
	}

	/**
	 * 设置行政处罚收件数(存储过程16：查办件和咨询)
	 */
	public void setXzcfNum(java.lang.Double xzcfNum) {
		this.xzcfNum = xzcfNum;
	}

	/**
	 * 获取行政处罚收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzcfNum() {
		return xzcfNum;
	}

	/**
	 * 设置行政征收收件数(存储过程16：查办件和咨询)
	 */
	public void setXzzsNum(java.lang.Double xzzsNum) {
		this.xzzsNum = xzzsNum;
	}

	/**
	 * 获取行政征收收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getXzzsNum() {
		return xzzsNum;
	}

	/**
	 * 设置其他行政权利收件数(存储过程16：查办件和咨询)
	 */
	public void setQtxzqlNum(java.lang.Double qtxzqlNum) {
		this.qtxzqlNum = qtxzqlNum;
	}

	/**
	 * 获取其他行政权利收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getQtxzqlNum() {
		return qtxzqlNum;
	}

	/**
	 * 设置公共服务 收件数(存储过程16：查办件和咨询)
	 */
	public void setGgfwNum(java.lang.Double ggfwNum) {
		this.ggfwNum = ggfwNum;
	}

	/**
	 * 获取公共服务 收件数(存储过程16：查办件和咨询)
	 */
	public java.lang.Double getGgfwNum() {
		return ggfwNum;
	}

	/**
	 * 设置行政许可办结数(存储过程17：查办件和咨询)
	 */
	public void setXzxkeFinishNum(java.lang.Double xzxkeFinishNum) {
		this.xzxkeFinishNum = xzxkeFinishNum;
	}

	/**
	 * 获取行政许可办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzxkeFinishNum() {
		return xzxkeFinishNum;
	}

	/**
	 * 设置行政奖励办结数(存储过程17：查办件和咨询)
	 */
	public void setXzjlFinishNum(java.lang.Double xzjlFinishNum) {
		this.xzjlFinishNum = xzjlFinishNum;
	}

	/**
	 * 获取行政奖励办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzjlFinishNum() {
		return xzjlFinishNum;
	}

	/**
	 * 设置行政裁决办结数(存储过程17：查办件和咨询)
	 */
	public void setXzcjFinishNum(java.lang.Double xzcjFinishNum) {
		this.xzcjFinishNum = xzcjFinishNum;
	}

	/**
	 * 获取行政裁决办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzcjFinishNum() {
		return xzcjFinishNum;
	}

	/**
	 * 设置行政检查办结数(存储过程17：查办件和咨询)
	 */
	public void setXzjcFinishNum(java.lang.Double xzjcFinishNum) {
		this.xzjcFinishNum = xzjcFinishNum;
	}

	/**
	 * 获取行政检查办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzjcFinishNum() {
		return xzjcFinishNum;
	}

	/**
	 * 设置行政给付办结数(存储过程17：查办件和咨询)
	 */
	public void setXzgfFinishNum(java.lang.Double xzgfFinishNum) {
		this.xzgfFinishNum = xzgfFinishNum;
	}

	/**
	 * 获取行政给付办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzgfFinishNum() {
		return xzgfFinishNum;
	}

	/**
	 * 设置行政强制办结数(存储过程17：查办件和咨询)
	 */
	public void setXzqzFinishNum(java.lang.Double xzqzFinishNum) {
		this.xzqzFinishNum = xzqzFinishNum;
	}

	/**
	 * 获取行政强制办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzqzFinishNum() {
		return xzqzFinishNum;
	}

	/**
	 * 设置行政确认办结数(存储过程17：查办件和咨询)
	 */
	public void setXzqrFinishNum(java.lang.Double xzqrFinishNum) {
		this.xzqrFinishNum = xzqrFinishNum;
	}

	/**
	 * 获取行政确认办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzqrFinishNum() {
		return xzqrFinishNum;
	}

	/**
	 * 设置行政处罚办结数(存储过程17：查办件和咨询)
	 */
	public void setXzcfFinishNum(java.lang.Double xzcfFinishNum) {
		this.xzcfFinishNum = xzcfFinishNum;
	}

	/**
	 * 获取行政处罚办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzcfFinishNum() {
		return xzcfFinishNum;
	}

	/**
	 * 设置行政征收办结数(存储过程17：查办件和咨询)
	 */
	public void setXzzsFinishNum(java.lang.Double xzzsFinishNum) {
		this.xzzsFinishNum = xzzsFinishNum;
	}

	/**
	 * 获取行政征收办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getXzzsFinishNum() {
		return xzzsFinishNum;
	}

	/**
	 * 设置其他行政权利办结数(存储过程17：查办件和咨询)
	 */
	public void setQtxzqlFinishNum(java.lang.Double qtxzqlFinishNum) {
		this.qtxzqlFinishNum = qtxzqlFinishNum;
	}

	/**
	 * 获取其他行政权利办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getQtxzqlFinishNum() {
		return qtxzqlFinishNum;
	}

	/**
	 * 设置公共服务办结数(存储过程17：查办件和咨询)
	 */
	public void setGgfwFinishNum(java.lang.Double ggfwFinishNum) {
		this.ggfwFinishNum = ggfwFinishNum;
	}

	/**
	 * 获取公共服务办结数(存储过程17：查办件和咨询)
	 */
	public java.lang.Double getGgfwFinishNum() {
		return ggfwFinishNum;
	}

	/**
	 * 设置按监察时间统计特别程序预警数(存储过程18：只查特别程序)
	 */
	public void setSpecialSuperviseGreenNum(
			java.lang.Double specialSuperviseGreenNum) {
		this.specialSuperviseGreenNum = specialSuperviseGreenNum;
	}

	/**
	 * 获取按监察时间统计特别程序预警数(存储过程18：只查特别程序)
	 */
	public java.lang.Double getSpecialSuperviseGreenNum() {
		return specialSuperviseGreenNum;
	}

	/**
	 * 设置按监察时间统计特别程序黄牌数(存储过程18：只查特别程序)
	 */
	public void setSpecialSuperviseYellowNum(
			java.lang.Double specialSuperviseYellowNum) {
		this.specialSuperviseYellowNum = specialSuperviseYellowNum;
	}

	/**
	 * 获取按监察时间统计特别程序黄牌数(存储过程18：只查特别程序)
	 */
	public java.lang.Double getSpecialSuperviseYellowNum() {
		return specialSuperviseYellowNum;
	}

	/**
	 * 设置按监察时间统计特别程序红牌数(存储过程18：只查特别程序)
	 */
	public void setSpecialSuperviseRedNum(
			java.lang.Double specialSuperviseRedNum) {
		this.specialSuperviseRedNum = specialSuperviseRedNum;
	}

	/**
	 * 获取按监察时间统计特别程序红牌数(存储过程18：只查特别程序)
	 */
	public java.lang.Double getSpecialSuperviseRedNum() {
		return specialSuperviseRedNum;
	}
	
	/**获取机构级别
	 * 非平台生成
	 * @return
	 */
	public Integer getOrgLevelInt(){
		JcOrgView orgView = new JcOrgView();
		orgView.setOrgCode(areaOrgCode);
		return orgView.getOrgLevel();
	}
	
	/**
	 * 办结率
	 */
	private Float banJieLvFloat;
	/**计算办结率
	 * @return
	 */
	public Float getBanJieLvFloat(){
		if(banJieLvFloat!=null){
			return banJieLvFloat;
		}
		if(acceptNum!=null&&finishNum!=null){
			if(acceptNum.compareTo(BigDecimal.ZERO)!=0&&finishNum.compareTo(BigDecimal.ZERO)!=0){
				try {
					BigDecimal banJieLv = finishNum.divide(acceptNum,6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
					banJieLv = banJieLv.setScale(2, BigDecimal.ROUND_DOWN);//保留小数点后4位，其他的直接舍去
					banJieLvFloat = banJieLv.floatValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(banJieLvFloat==null)banJieLvFloat=0f;
		return banJieLvFloat;
	}
	
	public void setBanJieLvFloat(Float banJieLvFloat) {
		this.banJieLvFloat = banJieLvFloat;
	}

	/**
	 * 提速率
	 */
	private Float tiSuLvFloat;
	/**计算提速率
	 * @return
	 */
	public Float getTiSuLvFloat() {
		if(tiSuLvFloat!=null){
			return tiSuLvFloat;
		}
		if(acceptNum!=null&&beforeFinishNum!=null){
			if(acceptNum.compareTo(BigDecimal.ZERO)!=0&&beforeFinishNum.compareTo(BigDecimal.ZERO)!=0){
				try {
					BigDecimal banJieLv = beforeFinishNum.divide(acceptNum,6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));;
					banJieLv = banJieLv.setScale(2, BigDecimal.ROUND_DOWN);//保留小数点后4位，其他的直接舍去
					tiSuLvFloat = banJieLv.floatValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(tiSuLvFloat==null)tiSuLvFloat=0f;
		return tiSuLvFloat;
	}

	public void setTiSuLvFloat(Float tiSuLvFloat) {
		this.tiSuLvFloat = tiSuLvFloat;
	}
	
	/**
	 * 业务发牌统计情况办结率
	 */
	private Float businessDealHandlingRate;

	public Float getBusinessDealHandlingRate() 
	{
		if (businessDealHandlingRate != null) return businessDealHandlingRate;
		if (acceptNum != null && normalFinishNum != null && unthreadfinishNum != null && throwFinishNum != null)
		{
			// 办结数
			BigDecimal handlingNum = normalFinishNum.add(unthreadfinishNum).add(throwFinishNum);
			if (acceptNum.compareTo(BigDecimal.ZERO) != 0 && handlingNum.compareTo(BigDecimal.ZERO) != 0)
			{
				try
				{
					BigDecimal rate = handlingNum.divide(acceptNum, 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
					rate = rate.setScale(2, BigDecimal.ROUND_DOWN);
					businessDealHandlingRate = rate.floatValue();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		if (businessDealHandlingRate == null) businessDealHandlingRate = 0f;
		return businessDealHandlingRate;
	}

	public void setBusinessDealHandlingRate(Float businessDealHandlingRate) 
	{
		this.businessDealHandlingRate = businessDealHandlingRate;
	}
	
	/**
	 * 法定提速率
	 */
	private Float legalAccelerationRate;
	
	public Float getLegalAccelerationRate() {
		if (legalAccelerationRate != null) return legalAccelerationRate;
		if (legalTimeSum != null && timeUseSumLegal != null)
		{
			if (legalTimeSum.compareTo(BigDecimal.ZERO) != 0)
			{
				try
				{
					BigDecimal rate = legalTimeSum.subtract(timeUseSumLegal).divide(legalTimeSum, 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
					rate = rate.setScale(2, BigDecimal.ROUND_DOWN);
					legalAccelerationRate = rate.floatValue();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		if (legalAccelerationRate == null) legalAccelerationRate = 0f;
		return legalAccelerationRate;
	}
	
	public void setLegalAccelerationRate(Float legalAccelerationRate) {
		this.legalAccelerationRate = legalAccelerationRate;
	}
	
	/**
	 * 承诺提速率
	 */
	private Float promiseAccelerationRate;


	public Float getPromiseAccelerationRate() {
		if (promiseAccelerationRate != null) return promiseAccelerationRate;
		if (promiseTimeSum != null && timeUseSumPromise != null)
		{
			if (promiseTimeSum.compareTo(BigDecimal.ZERO) != 0)
			{
				try
				{
					BigDecimal rate = promiseTimeSum.subtract(timeUseSumPromise).divide(promiseTimeSum, 6, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
					rate = rate.setScale(2, BigDecimal.ROUND_DOWN);
					promiseAccelerationRate = rate.floatValue();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		if (promiseAccelerationRate == null) promiseAccelerationRate = 0f;
		return promiseAccelerationRate;
	}

	public void setPromiseAccelerationRate(Float promiseAccelerationRate) {
		this.promiseAccelerationRate = promiseAccelerationRate;
	}
	
	/**
	 * 法定时限总和
	 */
	@Column(id = "legal_time_sum", datatype = "bigdecimal")
	private java.math.BigDecimal legalTimeSum;
	
	/**
	 * 承诺时限总和
	 */
	@Column(id = "promise_time_sum", datatype = "bigdecimal")
	private java.math.BigDecimal promiseTimeSum;
	
	/**
	 * 法定时限办件所用时长
	 */
	@Column(id = "time_use_sum_legal", datatype = "bigdecimal")
	private java.math.BigDecimal timeUseSumLegal;
	
	/**
	 * 承诺时限办件所用时长
	 */
	@Column(id = "time_use_sum_promise", datatype = "bigdecimal")
	private java.math.BigDecimal timeUseSumPromise;

	public java.math.BigDecimal getLegalTimeSum() {
		return legalTimeSum;
	}

	public void setLegalTimeSum(java.math.BigDecimal legalTimeSum) {
		this.legalTimeSum = legalTimeSum;
	}

	public java.math.BigDecimal getPromiseTimeSum() {
		return promiseTimeSum;
	}

	public void setPromiseTimeSum(java.math.BigDecimal promiseTimeSum) {
		this.promiseTimeSum = promiseTimeSum;
	}

	public java.math.BigDecimal getTimeUseSumLegal() {
		return timeUseSumLegal;
	}

	public void setTimeUseSumLegal(java.math.BigDecimal timeUseSumLegal) {
		this.timeUseSumLegal = timeUseSumLegal;
	}

	public java.math.BigDecimal getTimeUseSumPromise() {
		return timeUseSumPromise;
	}

	public void setTimeUseSumPromise(java.math.BigDecimal timeUseSumPromise) {
		this.timeUseSumPromise = timeUseSumPromise;
	}
	
	/**
	 * 新受理数
	 */
	@Column(id = "accept_num_new", datatype = "bigdecimal")
	private java.math.BigDecimal acceptNumNew;

	public java.math.BigDecimal getAcceptNumNew() {
		return acceptNumNew;
	}

	public void setAcceptNumNew(java.math.BigDecimal acceptNumNew) {
		this.acceptNumNew = acceptNumNew;
	}
	
	public java.lang.String getIsInvestmentSupervision() {
		return isInvestmentSupervision;
	}

	public void setIsInvestmentSupervision(java.lang.String isInvestmentSupervision) {
		this.isInvestmentSupervision = isInvestmentSupervision;
	}
	
	public java.lang.Double getWsbsGreenNum() {
		return wsbsGreenNum;
	}

	public void setWsbsGreenNum(java.lang.Double wsbsGreenNum) {
		this.wsbsGreenNum = wsbsGreenNum;
	}

	public java.lang.Double getWsbsYellowNum() {
		return wsbsYellowNum;
	}

	public void setWsbsYellowNum(java.lang.Double wsbsYellowNum) {
		this.wsbsYellowNum = wsbsYellowNum;
	}

	public java.lang.Double getWsbsRedNum() {
		return wsbsRedNum;
	}

	public void setWsbsRedNum(java.lang.Double wsbsRedNum) {
		this.wsbsRedNum = wsbsRedNum;
	}

	public java.lang.Double getBjczGreenNum() {
		return bjczGreenNum;
	}

	public void setBjczGreenNum(java.lang.Double bjczGreenNum) {
		this.bjczGreenNum = bjczGreenNum;
	}

	public java.lang.Double getBjczYellowNum() {
		return bjczYellowNum;
	}

	public void setBjczYellowNum(java.lang.Double bjczYellowNum) {
		this.bjczYellowNum = bjczYellowNum;
	}

	public java.lang.Double getBjczRedNum() {
		return bjczRedNum;
	}

	public void setBjczRedNum(java.lang.Double bjczRedNum) {
		this.bjczRedNum = bjczRedNum;
	}
	
}
