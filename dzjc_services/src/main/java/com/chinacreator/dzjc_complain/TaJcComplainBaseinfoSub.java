package com.chinacreator.dzjc_complain;

/**
 * 投诉信息子类
 * 
 * @author xinwei.chen
 * 
 */
public class TaJcComplainBaseinfoSub extends TaJcComplainBaseinfo {

	public TaJcComplainBaseinfoSub() {
		super();
	}

	private String beginDate;
	private String endDate;
	
	private String[] statusArr;
	/**
	 * 延期申请查询条件最后期限
	 */
	private String deadline;
	
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String[] getStatusArr() {
		return statusArr;
	}

	public void setStatusArr(String[] statusArr) {
		this.statusArr = statusArr;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

}
