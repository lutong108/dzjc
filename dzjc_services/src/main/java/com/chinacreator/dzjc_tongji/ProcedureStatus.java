package com.chinacreator.dzjc_tongji;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 存储过程执行情况数据实体
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.dzjc_tongji.ProcedureStatus", table = "TA_JC_PROCEDURE_STATUS", ds = "acceptdata", cache = false)
public class ProcedureStatus {
	/**
	 *主键
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string1024")
	private java.lang.String id;

	/**
	 *固化日期
	 */
	@Column(id = "solid_date", datatype = "string64")
	private java.lang.String solidDate;

	/**
	 *固化开始时间
	 */
	@Column(id = "begin_time", datatype = "string64")
	private java.lang.String beginTime;

	/**
	 *固化结束时间
	 */
	@Column(id = "end_time", datatype = "string64")
	private java.lang.String endTime;

	/**
	 *办件量
	 */
	@Column(id = "instance_num", datatype = "bigdouble")
	private java.lang.Double instanceNum;

	/**
	 *发牌表数据量
	 */
	@Column(id = "deal_num", datatype = "bigdouble")
	private java.lang.Double dealNum;

	/**
	 *事项数据量
	 */
	@Column(id = "approve_num", datatype = "bigdouble")
	private java.lang.Double approveNum;

	/**
	 *存储过程1开始时间
	 */
	@Column(id = "procedure_1_start_time", datatype = "string64")
	private java.lang.String procedure1StartTime;

	/**
	 *存储过程1结束时间
	 */
	@Column(id = "procedure_1_end_time", datatype = "string64")
	private java.lang.String procedure1EndTime;

	/**
	 *存储过程2开始时间
	 */
	@Column(id = "procedure_2_start_time", datatype = "string64")
	private java.lang.String procedure2StartTime;

	/**
	 *存储过程2结束时间
	 */
	@Column(id = "procedure_2_end_time", datatype = "string64")
	private java.lang.String procedure2EndTime;

	/**
	 *存储过程3开始时间
	 */
	@Column(id = "procedure_3_start_time", datatype = "string64")
	private java.lang.String procedure3StartTime;

	/**
	 *存储过程3结束时间
	 */
	@Column(id = "procedure_3_end_time", datatype = "string64")
	private java.lang.String procedure3EndTime;

	/**
	 *存储过程4开始时间
	 */
	@Column(id = "procedure_4_start_time", datatype = "string64")
	private java.lang.String procedure4StartTime;

	/**
	 *存储过程4结束时间
	 */
	@Column(id = "procedure_4_end_time", datatype = "string64")
	private java.lang.String procedure4EndTime;

	/**
	 *存储过程5开始时间
	 */
	@Column(id = "procedure_5_start_time", datatype = "string64")
	private java.lang.String procedure5StartTime;

	/**
	 *存储过程5结束时间
	 */
	@Column(id = "procedure_5_end_time", datatype = "string64")
	private java.lang.String procedure5EndTime;

	/**
	 *存储过程6开始时间
	 */
	@Column(id = "procedure_6_start_time", datatype = "string64")
	private java.lang.String procedure6StartTime;

	/**
	 *存储过程6结束时间
	 */
	@Column(id = "procedure_6_end_time", datatype = "string64")
	private java.lang.String procedure6EndTime;

	/**
	 *存储过程7开始时间
	 */
	@Column(id = "procedure_7_start_time", datatype = "string64")
	private java.lang.String procedure7StartTime;

	/**
	 *存储过程7结束时间
	 */
	@Column(id = "procedure_7_end_time", datatype = "string64")
	private java.lang.String procedure7EndTime;

	/**
	 *存储过程8开始时间
	 */
	@Column(id = "procedure_8_start_time", datatype = "string64")
	private java.lang.String procedure8StartTime;

	/**
	 *存储过程8结束时间
	 */
	@Column(id = "procedure_8_end_time", datatype = "string64")
	private java.lang.String procedure8EndTime;

	/**
	 *存储过程9开始时间
	 */
	@Column(id = "procedure_9_start_time", datatype = "string64")
	private java.lang.String procedure9StartTime;

	/**
	 *存储过程9结束时间
	 */
	@Column(id = "procedure_9_end_time", datatype = "string64")
	private java.lang.String procedure9EndTime;

	/**
	 *存储过程10开始时间
	 */
	@Column(id = "procedure_10_start_time", datatype = "string64")
	private java.lang.String procedure10StartTime;

	/**
	 *存储过程10结束时间
	 */
	@Column(id = "procedure_10_end_time", datatype = "string64")
	private java.lang.String procedure10EndTime;

	/**
	 *存储过程11开始时间
	 */
	@Column(id = "procedure_11_start_time", datatype = "string64")
	private java.lang.String procedure11StartTime;

	/**
	 *存储过程11结束时间
	 */
	@Column(id = "procedure_11_end_time", datatype = "string64")
	private java.lang.String procedure11EndTime;

	/**
	 *存储过程12开始时间
	 */
	@Column(id = "procedure_12_start_time", datatype = "string64")
	private java.lang.String procedure12StartTime;

	/**
	 *存储过程12结束时间
	 */
	@Column(id = "procedure_12_end_time", datatype = "string64")
	private java.lang.String procedure12EndTime;

	/**
	 *存储过程13开始时间
	 */
	@Column(id = "procedure_13_start_time", datatype = "string64")
	private java.lang.String procedure13StartTime;

	/**
	 *存储过程13结束时间
	 */
	@Column(id = "procedure_13_end_time", datatype = "string64")
	private java.lang.String procedure13EndTime;

	/**
	 *存储过程14开始时间
	 */
	@Column(id = "procedure_14_start_time", datatype = "string64")
	private java.lang.String procedure14StartTime;

	/**
	 *存储过程14结束时间
	 */
	@Column(id = "procedure_14_end_time", datatype = "string64")
	private java.lang.String procedure14EndTime;

	/**
	 *存储过程15开始时间
	 */
	@Column(id = "procedure_15_start_time", datatype = "string64")
	private java.lang.String procedure15StartTime;

	/**
	 *存储过程15结束时间
	 */
	@Column(id = "procedure_15_end_time", datatype = "string64")
	private java.lang.String procedure15EndTime;

	/**
	 *存储过程16开始时间
	 */
	@Column(id = "procedure_16_start_time", datatype = "string64")
	private java.lang.String procedure16StartTime;

	/**
	 *存储过程16结束时间
	 */
	@Column(id = "procedure_16_end_time", datatype = "string64")
	private java.lang.String procedure16EndTime;

	/**
	 *存储过程17开始时间
	 */
	@Column(id = "procedure_17_start_time", datatype = "string64")
	private java.lang.String procedure17StartTime;

	/**
	 *存储过程17结束时间
	 */
	@Column(id = "procedure_17_end_time", datatype = "string64")
	private java.lang.String procedure17EndTime;

	/**
	 *存储过程18开始时间
	 */
	@Column(id = "procedure_18_start_time", datatype = "string64")
	private java.lang.String procedure18StartTime;

	/**
	 *存储过程18结束时间
	 */
	@Column(id = "procedure_18_end_time", datatype = "string64")
	private java.lang.String procedure18EndTime;

	/**
	 *存储过程19开始时间
	 */
	@Column(id = "procedure_19_start_time", datatype = "string64")
	private java.lang.String procedure19StartTime;

	/**
	 *存储过程19结束时间
	 */
	@Column(id = "procedure_19_end_time", datatype = "string64")
	private java.lang.String procedure19EndTime;

	/**
	 *存储过程20开始时间
	 */
	@Column(id = "procedure_20_start_time", datatype = "string64")
	private java.lang.String procedure20StartTime;

	/**
	 *存储过程20结束时间
	 */
	@Column(id = "procedure_20_end_time", datatype = "string64")
	private java.lang.String procedure20EndTime;

	/**
	 *存储过程21开始时间
	 */
	@Column(id = "procedure_21_start_time", datatype = "string64")
	private java.lang.String procedure21StartTime;

	/**
	 *存储过程21结束时间
	 */
	@Column(id = "procedure_21_end_time", datatype = "string64")
	private java.lang.String procedure21EndTime;

	/**
	 *存储过程22开始时间
	 */
	@Column(id = "procedure_22_start_time", datatype = "string64")
	private java.lang.String procedure22StartTime;

	/**
	 *存储过程22结束时间
	 */
	@Column(id = "procedure_22_end_time", datatype = "string64")
	private java.lang.String procedure22EndTime;

	/**
	 *存储过程1总时长
	 */
	private float procedure1TotalTime;

	/**
	 *存储过程2总时长
	 */
	private float procedure2TotalTime;

	/**
	 *存储过程3总时长
	 */
	private float procedure3TotalTime;

	/**
	 *存储过程4总时长
	 */
	private float procedure4TotalTime;

	/**
	 *存储过程5总时长
	 */
	private float procedure5TotalTime;

	/**
	 *存储过程6总时长
	 */
	private float procedure6TotalTime;

	/**
	 *存储过程7总时长
	 */
	private float procedure7TotalTime;

	/**
	 *存储过程8总时长
	 */
	private float procedure8TotalTime;

	/**
	 *存储过程9总时长
	 */
	private float procedure9TotalTime;

	/**
	 *存储过程10总时长
	 */
	private float procedure10TotalTime;

	/**
	 *存储过程11总时长
	 */
	private float procedure11TotalTime;

	/**
	 *存储过程12总时长
	 */
	private float procedure12TotalTime;

	/**
	 *存储过程13总时长
	 */
	private float procedure13TotalTime;

	/**
	 *存储过程14总时长
	 */
	private float procedure14TotalTime;

	/**
	 *存储过程15总时长
	 */
	private float procedure15TotalTime;

	/**
	 *存储过程16总时长
	 */
	private float procedure16TotalTime;

	/**
	 *存储过程17总时长
	 */
	private float procedure17TotalTime;

	/**
	 *存储过程18总时长
	 */
	private float procedure18TotalTime;

	/**
	 *存储过程19总时长
	 */
	private float procedure19TotalTime;

	/**
	 *存储过程20总时长
	 */
	private float procedure20TotalTime;

	/**
	 *存储过程21总时长
	 */
	private float procedure21TotalTime;

	/**
	 *存储过程22总时长
	 */
	private float procedure22TotalTime;

	/**
	 *总时长
	 */
	private float totalTime;
	
	
	//===========================================================================================
	//======================================== 分割线  =============================================
	//===========================================================================================
	

	/**
	 * 设置主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取主键
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置固化日期
	 */
	public void setSolidDate(java.lang.String solidDate) {
		this.solidDate = solidDate;
	}

	/**
	 * 获取固化日期
	 */
	public java.lang.String getSolidDate() {
		return solidDate;
	}

	/**
	 * 设置固化开始时间
	 */
	public void setBeginTime(java.lang.String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 获取固化开始时间
	 */
	public java.lang.String getBeginTime() {
		return beginTime;
	}

	/**
	 * 设置固化结束时间
	 */
	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取固化结束时间
	 */
	public java.lang.String getEndTime() {
		return endTime;
	}

	/**
	 * 设置办件量
	 */
	public void setInstanceNum(java.lang.Double instanceNum) {
		this.instanceNum = instanceNum;
	}

	/**
	 * 获取办件量
	 */
	public java.lang.Double getInstanceNum() {
		return instanceNum;
	}

	/**
	 * 设置发牌表数据量
	 */
	public void setDealNum(java.lang.Double dealNum) {
		this.dealNum = dealNum;
	}

	/**
	 * 获取发牌表数据量
	 */
	public java.lang.Double getDealNum() {
		return dealNum;
	}

	/**
	 * 设置事项数据量
	 */
	public void setApproveNum(java.lang.Double approveNum) {
		this.approveNum = approveNum;
	}

	/**
	 * 获取事项数据量
	 */
	public java.lang.Double getApproveNum() {
		return approveNum;
	}

	/**
	 * 设置存储过程1开始时间
	 */
	public void setProcedure1StartTime(java.lang.String procedure1StartTime) {
		this.procedure1StartTime = procedure1StartTime;
	}

	/**
	 * 获取存储过程1开始时间
	 */
	public java.lang.String getProcedure1StartTime() {
		return procedure1StartTime;
	}

	/**
	 * 设置存储过程1结束时间
	 */
	public void setProcedure1EndTime(java.lang.String procedure1EndTime) {
		this.procedure1EndTime = procedure1EndTime;
	}

	/**
	 * 获取存储过程1结束时间
	 */
	public java.lang.String getProcedure1EndTime() {
		return procedure1EndTime;
	}

	/**
	 * 设置存储过程2开始时间
	 */
	public void setProcedure2StartTime(java.lang.String procedure2StartTime) {
		this.procedure2StartTime = procedure2StartTime;
	}

	/**
	 * 获取存储过程2开始时间
	 */
	public java.lang.String getProcedure2StartTime() {
		return procedure2StartTime;
	}

	/**
	 * 设置存储过程2结束时间
	 */
	public void setProcedure2EndTime(java.lang.String procedure2EndTime) {
		this.procedure2EndTime = procedure2EndTime;
	}

	/**
	 * 获取存储过程2结束时间
	 */
	public java.lang.String getProcedure2EndTime() {
		return procedure2EndTime;
	}

	/**
	 * 设置存储过程3开始时间
	 */
	public void setProcedure3StartTime(java.lang.String procedure3StartTime) {
		this.procedure3StartTime = procedure3StartTime;
	}

	/**
	 * 获取存储过程3开始时间
	 */
	public java.lang.String getProcedure3StartTime() {
		return procedure3StartTime;
	}

	/**
	 * 设置存储过程3结束时间
	 */
	public void setProcedure3EndTime(java.lang.String procedure3EndTime) {
		this.procedure3EndTime = procedure3EndTime;
	}

	/**
	 * 获取存储过程3结束时间
	 */
	public java.lang.String getProcedure3EndTime() {
		return procedure3EndTime;
	}

	/**
	 * 设置存储过程4开始时间
	 */
	public void setProcedure4StartTime(java.lang.String procedure4StartTime) {
		this.procedure4StartTime = procedure4StartTime;
	}

	/**
	 * 获取存储过程4开始时间
	 */
	public java.lang.String getProcedure4StartTime() {
		return procedure4StartTime;
	}

	/**
	 * 设置存储过程4结束时间
	 */
	public void setProcedure4EndTime(java.lang.String procedure4EndTime) {
		this.procedure4EndTime = procedure4EndTime;
	}

	/**
	 * 获取存储过程4结束时间
	 */
	public java.lang.String getProcedure4EndTime() {
		return procedure4EndTime;
	}

	/**
	 * 设置存储过程5开始时间
	 */
	public void setProcedure5StartTime(java.lang.String procedure5StartTime) {
		this.procedure5StartTime = procedure5StartTime;
	}

	/**
	 * 获取存储过程5开始时间
	 */
	public java.lang.String getProcedure5StartTime() {
		return procedure5StartTime;
	}

	/**
	 * 设置存储过程5结束时间
	 */
	public void setProcedure5EndTime(java.lang.String procedure5EndTime) {
		this.procedure5EndTime = procedure5EndTime;
	}

	/**
	 * 获取存储过程5结束时间
	 */
	public java.lang.String getProcedure5EndTime() {
		return procedure5EndTime;
	}

	/**
	 * 设置存储过程6开始时间
	 */
	public void setProcedure6StartTime(java.lang.String procedure6StartTime) {
		this.procedure6StartTime = procedure6StartTime;
	}

	/**
	 * 获取存储过程6开始时间
	 */
	public java.lang.String getProcedure6StartTime() {
		return procedure6StartTime;
	}

	/**
	 * 设置存储过程6结束时间
	 */
	public void setProcedure6EndTime(java.lang.String procedure6EndTime) {
		this.procedure6EndTime = procedure6EndTime;
	}

	/**
	 * 获取存储过程6结束时间
	 */
	public java.lang.String getProcedure6EndTime() {
		return procedure6EndTime;
	}

	/**
	 * 设置存储过程7开始时间
	 */
	public void setProcedure7StartTime(java.lang.String procedure7StartTime) {
		this.procedure7StartTime = procedure7StartTime;
	}

	/**
	 * 获取存储过程7开始时间
	 */
	public java.lang.String getProcedure7StartTime() {
		return procedure7StartTime;
	}

	/**
	 * 设置存储过程7结束时间
	 */
	public void setProcedure7EndTime(java.lang.String procedure7EndTime) {
		this.procedure7EndTime = procedure7EndTime;
	}

	/**
	 * 获取存储过程7结束时间
	 */
	public java.lang.String getProcedure7EndTime() {
		return procedure7EndTime;
	}

	/**
	 * 设置存储过程8开始时间
	 */
	public void setProcedure8StartTime(java.lang.String procedure8StartTime) {
		this.procedure8StartTime = procedure8StartTime;
	}

	/**
	 * 获取存储过程8开始时间
	 */
	public java.lang.String getProcedure8StartTime() {
		return procedure8StartTime;
	}

	/**
	 * 设置存储过程8结束时间
	 */
	public void setProcedure8EndTime(java.lang.String procedure8EndTime) {
		this.procedure8EndTime = procedure8EndTime;
	}

	/**
	 * 获取存储过程8结束时间
	 */
	public java.lang.String getProcedure8EndTime() {
		return procedure8EndTime;
	}

	/**
	 * 设置存储过程9开始时间
	 */
	public void setProcedure9StartTime(java.lang.String procedure9StartTime) {
		this.procedure9StartTime = procedure9StartTime;
	}

	/**
	 * 获取存储过程9开始时间
	 */
	public java.lang.String getProcedure9StartTime() {
		return procedure9StartTime;
	}

	/**
	 * 设置存储过程9结束时间
	 */
	public void setProcedure9EndTime(java.lang.String procedure9EndTime) {
		this.procedure9EndTime = procedure9EndTime;
	}

	/**
	 * 获取存储过程9结束时间
	 */
	public java.lang.String getProcedure9EndTime() {
		return procedure9EndTime;
	}

	/**
	 * 设置存储过程10开始时间
	 */
	public void setProcedure10StartTime(java.lang.String procedure10StartTime) {
		this.procedure10StartTime = procedure10StartTime;
	}

	/**
	 * 获取存储过程10开始时间
	 */
	public java.lang.String getProcedure10StartTime() {
		return procedure10StartTime;
	}

	/**
	 * 设置存储过程10结束时间
	 */
	public void setProcedure10EndTime(java.lang.String procedure10EndTime) {
		this.procedure10EndTime = procedure10EndTime;
	}

	/**
	 * 获取存储过程10结束时间
	 */
	public java.lang.String getProcedure10EndTime() {
		return procedure10EndTime;
	}

	/**
	 * 设置存储过程11开始时间
	 */
	public void setProcedure11StartTime(java.lang.String procedure11StartTime) {
		this.procedure11StartTime = procedure11StartTime;
	}

	/**
	 * 获取存储过程11开始时间
	 */
	public java.lang.String getProcedure11StartTime() {
		return procedure11StartTime;
	}

	/**
	 * 设置存储过程11结束时间
	 */
	public void setProcedure11EndTime(java.lang.String procedure11EndTime) {
		this.procedure11EndTime = procedure11EndTime;
	}

	/**
	 * 获取存储过程11结束时间
	 */
	public java.lang.String getProcedure11EndTime() {
		return procedure11EndTime;
	}

	/**
	 * 设置存储过程12开始时间
	 */
	public void setProcedure12StartTime(java.lang.String procedure12StartTime) {
		this.procedure12StartTime = procedure12StartTime;
	}

	/**
	 * 获取存储过程12开始时间
	 */
	public java.lang.String getProcedure12StartTime() {
		return procedure12StartTime;
	}

	/**
	 * 设置存储过程12结束时间
	 */
	public void setProcedure12EndTime(java.lang.String procedure12EndTime) {
		this.procedure12EndTime = procedure12EndTime;
	}

	/**
	 * 获取存储过程12结束时间
	 */
	public java.lang.String getProcedure12EndTime() {
		return procedure12EndTime;
	}

	/**
	 * 设置存储过程13开始时间
	 */
	public void setProcedure13StartTime(java.lang.String procedure13StartTime) {
		this.procedure13StartTime = procedure13StartTime;
	}

	/**
	 * 获取存储过程13开始时间
	 */
	public java.lang.String getProcedure13StartTime() {
		return procedure13StartTime;
	}

	/**
	 * 设置存储过程13结束时间
	 */
	public void setProcedure13EndTime(java.lang.String procedure13EndTime) {
		this.procedure13EndTime = procedure13EndTime;
	}

	/**
	 * 获取存储过程13结束时间
	 */
	public java.lang.String getProcedure13EndTime() {
		return procedure13EndTime;
	}

	/**
	 * 设置存储过程14开始时间
	 */
	public void setProcedure14StartTime(java.lang.String procedure14StartTime) {
		this.procedure14StartTime = procedure14StartTime;
	}

	/**
	 * 获取存储过程14开始时间
	 */
	public java.lang.String getProcedure14StartTime() {
		return procedure14StartTime;
	}

	/**
	 * 设置存储过程14结束时间
	 */
	public void setProcedure14EndTime(java.lang.String procedure14EndTime) {
		this.procedure14EndTime = procedure14EndTime;
	}

	/**
	 * 获取存储过程14结束时间
	 */
	public java.lang.String getProcedure14EndTime() {
		return procedure14EndTime;
	}

	/**
	 * 设置存储过程15开始时间
	 */
	public void setProcedure15StartTime(java.lang.String procedure15StartTime) {
		this.procedure15StartTime = procedure15StartTime;
	}

	/**
	 * 获取存储过程15开始时间
	 */
	public java.lang.String getProcedure15StartTime() {
		return procedure15StartTime;
	}

	/**
	 * 设置存储过程15结束时间
	 */
	public void setProcedure15EndTime(java.lang.String procedure15EndTime) {
		this.procedure15EndTime = procedure15EndTime;
	}

	/**
	 * 获取存储过程15结束时间
	 */
	public java.lang.String getProcedure15EndTime() {
		return procedure15EndTime;
	}

	/**
	 * 设置存储过程16开始时间
	 */
	public void setProcedure16StartTime(java.lang.String procedure16StartTime) {
		this.procedure16StartTime = procedure16StartTime;
	}

	/**
	 * 获取存储过程16开始时间
	 */
	public java.lang.String getProcedure16StartTime() {
		return procedure16StartTime;
	}

	/**
	 * 设置存储过程16结束时间
	 */
	public void setProcedure16EndTime(java.lang.String procedure16EndTime) {
		this.procedure16EndTime = procedure16EndTime;
	}

	/**
	 * 获取存储过程16结束时间
	 */
	public java.lang.String getProcedure16EndTime() {
		return procedure16EndTime;
	}

	/**
	 * 设置存储过程17开始时间
	 */
	public void setProcedure17StartTime(java.lang.String procedure17StartTime) {
		this.procedure17StartTime = procedure17StartTime;
	}

	/**
	 * 获取存储过程17开始时间
	 */
	public java.lang.String getProcedure17StartTime() {
		return procedure17StartTime;
	}

	/**
	 * 设置存储过程17结束时间
	 */
	public void setProcedure17EndTime(java.lang.String procedure17EndTime) {
		this.procedure17EndTime = procedure17EndTime;
	}

	/**
	 * 获取存储过程17结束时间
	 */
	public java.lang.String getProcedure17EndTime() {
		return procedure17EndTime;
	}

	/**
	 * 设置存储过程18开始时间
	 */
	public void setProcedure18StartTime(java.lang.String procedure18StartTime) {
		this.procedure18StartTime = procedure18StartTime;
	}

	/**
	 * 获取存储过程18开始时间
	 */
	public java.lang.String getProcedure18StartTime() {
		return procedure18StartTime;
	}

	/**
	 * 设置存储过程18结束时间
	 */
	public void setProcedure18EndTime(java.lang.String procedure18EndTime) {
		this.procedure18EndTime = procedure18EndTime;
	}

	/**
	 * 获取存储过程18结束时间
	 */
	public java.lang.String getProcedure18EndTime() {
		return procedure18EndTime;
	}

	/**
	 * 设置存储过程19开始时间
	 */
	public void setProcedure19StartTime(java.lang.String procedure19StartTime) {
		this.procedure19StartTime = procedure19StartTime;
	}

	/**
	 * 获取存储过程19开始时间
	 */
	public java.lang.String getProcedure19StartTime() {
		return procedure19StartTime;
	}

	/**
	 * 设置存储过程19结束时间
	 */
	public void setProcedure19EndTime(java.lang.String procedure19EndTime) {
		this.procedure19EndTime = procedure19EndTime;
	}

	/**
	 * 获取存储过程19结束时间
	 */
	public java.lang.String getProcedure19EndTime() {
		return procedure19EndTime;
	}

	/**
	 * 设置存储过程20开始时间
	 */
	public void setProcedure20StartTime(java.lang.String procedure20StartTime) {
		this.procedure20StartTime = procedure20StartTime;
	}

	/**
	 * 获取存储过程20开始时间
	 */
	public java.lang.String getProcedure20StartTime() {
		return procedure20StartTime;
	}

	/**
	 * 设置存储过程20结束时间
	 */
	public void setProcedure20EndTime(java.lang.String procedure20EndTime) {
		this.procedure20EndTime = procedure20EndTime;
	}

	/**
	 * 获取存储过程20结束时间
	 */
	public java.lang.String getProcedure20EndTime() {
		return procedure20EndTime;
	}

	/**
	 * 设置存储过程21开始时间
	 */
	public void setProcedure21StartTime(java.lang.String procedure21StartTime) {
		this.procedure21StartTime = procedure21StartTime;
	}

	/**
	 * 获取存储过程21开始时间
	 */
	public java.lang.String getProcedure21StartTime() {
		return procedure21StartTime;
	}

	/**
	 * 设置存储过程21结束时间
	 */
	public void setProcedure21EndTime(java.lang.String procedure21EndTime) {
		this.procedure21EndTime = procedure21EndTime;
	}

	/**
	 * 获取存储过程21结束时间
	 */
	public java.lang.String getProcedure21EndTime() {
		return procedure21EndTime;
	}

	/**
	 * 设置存储过程22开始时间
	 */
	public void setProcedure22StartTime(java.lang.String procedure22StartTime) {
		this.procedure22StartTime = procedure22StartTime;
	}

	/**
	 * 获取存储过程22开始时间
	 */
	public java.lang.String getProcedure22StartTime() {
		return procedure22StartTime;
	}

	/**
	 * 设置存储过程22结束时间
	 */
	public void setProcedure22EndTime(java.lang.String procedure22EndTime) {
		this.procedure22EndTime = procedure22EndTime;
	}

	/**
	 * 获取存储过程22结束时间
	 */
	public java.lang.String getProcedure22EndTime() {
		return procedure22EndTime;
	}

	//===============================================================================================
	//==========================================  分割线  ==============================================
	//===============================================================================================

	/**
	 * 获取存储过程1总时长
	 */
	public float getProcedure1TotalTime() {
		if (procedure1StartTime != null && procedure1EndTime != null) {
			procedure1TotalTime = getTimeCost(procedure1StartTime, procedure1EndTime);
		}
		return procedure1TotalTime;
	}

	/**
	 * 获取存储过程2总时长
	 */
	public float getProcedure2TotalTime() {
		if (procedure2StartTime != null && procedure2EndTime != null) {
			procedure2TotalTime = getTimeCost(procedure2StartTime, procedure2EndTime);
		}
		return procedure2TotalTime;
	}

	/**
	 * 获取存储过程3总时长
	 */
	public float getProcedure3TotalTime() {
		if (procedure3StartTime != null && procedure3EndTime != null) {
			procedure3TotalTime = getTimeCost(procedure3StartTime, procedure3EndTime);
		}
		return procedure3TotalTime;
	}

	/**
	 * 获取存储过程4总时长
	 */
	public float getProcedure4TotalTime() {
		if (procedure4StartTime != null && procedure4EndTime != null) {
			procedure4TotalTime = getTimeCost(procedure4StartTime, procedure4EndTime);
		}
		return procedure4TotalTime;
	}

	/**
	 * 获取存储过程5总时长
	 */
	public float getProcedure5TotalTime() {
		if (procedure5StartTime != null && procedure5EndTime != null) {
			procedure5TotalTime = getTimeCost(procedure5StartTime, procedure5EndTime);
		}
		return procedure5TotalTime;
	}

	/**
	 * 获取存储过程6总时长
	 */
	public float getProcedure6TotalTime() {
		if (procedure6StartTime != null && procedure6EndTime != null) {
			procedure6TotalTime = getTimeCost(procedure6StartTime, procedure6EndTime);
		}
		return procedure6TotalTime;
	}

	/**
	 * 获取存储过程7总时长
	 */
	public float getProcedure7TotalTime() {
		if (procedure7StartTime != null && procedure7EndTime != null) {
			procedure7TotalTime = getTimeCost(procedure7StartTime, procedure7EndTime);
		}
		return procedure7TotalTime;
	}

	/**
	 * 获取存储过程8总时长
	 */
	public float getProcedure8TotalTime() {
		if (procedure8StartTime != null && procedure8EndTime != null) {
			procedure8TotalTime = getTimeCost(procedure8StartTime, procedure8EndTime);
		}
		return procedure8TotalTime;
	}

	/**
	 * 获取存储过程9总时长
	 */
	public float getProcedure9TotalTime() {
		if (procedure9StartTime != null && procedure9EndTime != null) {
			procedure9TotalTime = getTimeCost(procedure9StartTime, procedure9EndTime);
		}
		return procedure9TotalTime;
	}

	/**
	 * 获取存储过程10总时长
	 */
	public float getProcedure10TotalTime() {
		if (procedure10StartTime != null && procedure10EndTime != null) {
			procedure10TotalTime = getTimeCost(procedure10StartTime, procedure10EndTime);
		}
		return procedure10TotalTime;
	}

	/**
	 * 获取存储过程11总时长
	 */
	public float getProcedure11TotalTime() {
		if (procedure11StartTime != null && procedure11EndTime != null) {
			procedure11TotalTime = getTimeCost(procedure11StartTime, procedure11EndTime);
		}
		return procedure11TotalTime;
	}

	/**
	 * 获取存储过程12总时长
	 */
	public float getProcedure12TotalTime() {
		if (procedure12StartTime != null && procedure12EndTime != null) {
			procedure12TotalTime = getTimeCost(procedure12StartTime, procedure12EndTime);
		}
		return procedure12TotalTime;
	}

	/**
	 * 获取存储过程13总时长
	 */
	public float getProcedure13TotalTime() {
		if (procedure13StartTime != null && procedure13EndTime != null) {
			procedure13TotalTime = getTimeCost(procedure13StartTime, procedure13EndTime);
		}
		return procedure13TotalTime;
	}

	/**
	 * 获取存储过程14总时长
	 */
	public float getProcedure14TotalTime() {
		if (procedure14StartTime != null && procedure14EndTime != null) {
			procedure14TotalTime = getTimeCost(procedure14StartTime, procedure14EndTime);
		}
		return procedure14TotalTime;
	}

	/**
	 * 获取存储过程15总时长
	 */
	public float getProcedure15TotalTime() {
		if (procedure15StartTime != null && procedure15EndTime != null) {
			procedure15TotalTime = getTimeCost(procedure15StartTime, procedure15EndTime);
		}
		return procedure15TotalTime;
	}

	/**
	 * 获取存储过程16总时长
	 */
	public float getProcedure16TotalTime() {
		if (procedure16StartTime != null && procedure16EndTime != null) {
			procedure16TotalTime = getTimeCost(procedure16StartTime, procedure16EndTime);
		}
		return procedure16TotalTime;
	}

	/**
	 * 获取存储过程17总时长
	 */
	public float getProcedure17TotalTime() {
		if (procedure17StartTime != null && procedure17EndTime != null) {
			procedure17TotalTime = getTimeCost(procedure17StartTime, procedure17EndTime);
		}
		return procedure17TotalTime;
	}

	/**
	 * 获取存储过程18总时长
	 */
	public float getProcedure18TotalTime() {
		if (procedure18StartTime != null && procedure18EndTime != null) {
			procedure18TotalTime = getTimeCost(procedure18StartTime, procedure18EndTime);
		}
		return procedure18TotalTime;
	}

	/**
	 * 获取存储过程19总时长
	 */
	public float getProcedure19TotalTime() {
		if (procedure19StartTime != null && procedure19EndTime != null) {
			procedure19TotalTime = getTimeCost(procedure19StartTime, procedure19EndTime);
		}
		return procedure19TotalTime;
	}

	/**
	 * 获取存储过程20总时长
	 */
	public float getProcedure20TotalTime() {
		if (procedure20StartTime != null && procedure20EndTime != null) {
			procedure20TotalTime = getTimeCost(procedure20StartTime, procedure20EndTime);
		}
		return procedure20TotalTime;
	}

	/**
	 * 获取存储过程21总时长
	 */
	public float getProcedure21TotalTime() {
		if (procedure21StartTime != null && procedure21EndTime != null) {
			procedure21TotalTime = getTimeCost(procedure21StartTime, procedure21EndTime);
		}
		return procedure21TotalTime;
	}

	/**
	 * 获取存储过程22总时长
	 */
	public float getProcedure22TotalTime() {
		if (procedure22StartTime != null && procedure22EndTime != null) {
			procedure22TotalTime = getTimeCost(procedure22StartTime, procedure22EndTime);
		}
		return procedure22TotalTime;
	}

	/**
	 * 获取总时长
	 */
	public float getTotalTime() {
		if (beginTime != null && endTime != null) {
			totalTime = getTimeCost(beginTime, endTime);
		}
		return totalTime;
	}
	
	/**
	 * 计算时间差
	 * @param start
	 * @param end
	 * @return 秒
	 */
	private float getTimeCost(String start, String end) {
		float duration = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		try {
			Date d1 = sdf.parse(start);
			Date d2 = sdf.parse(end);
			duration = ((float)(d2.getTime() - d1.getTime())) / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return duration;
	}
	
}
