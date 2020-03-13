package com.chinacreator.dzjc_todoStatistics;

/**
 *<p>Title:EgovernmentBean.java</p>
 *<p>Description:统计基本类</p>
 *<p>Copyright:Copyright (c) 2011</p>
 *<p>Company:湖南科创</p>
 *@author 刘健
 *@version 1.0
 *2011-3-28
 */
public class EgovernmentBean {
	  //累计收件
	  private long sj;
	  
	  //累计办结数
	  private long bj;
	  
	  //累计提前办结数
	  private long tsbj;

	  //累计红牌
	  private long red;
	  
	  //累计黄牌
	  private long yellow;
	  
	  //累计办件红牌
	  private long bjred;
	  
	  //累计办件黄牌
	  private long bjyellow;
	  
	  //累计投诉红牌
	  private long cnred;
	  
	  //累计投诉黄牌
	  private long cnyellow;
	  
	  //累计咨询红牌
	  private long csred;
	  
	  //累计咨询黄牌
	  private long csyellow;
	  
	  //办结率
	  private float bjPercent;

	  //提速率
	  private float tsPercent;
	  
	  //有效投诉
	  private long ts;
	  	  
	  //投诉数
	  private long cn;
	  
	  //投诉回复数
	  private long crn;
	  
	  //咨询数
	  private long csn;
	  
	  //咨询回复数
	  private long csrn;

	public long getSj() {
		return sj;
	}

	public void setSj(long sj) {
		this.sj = sj;
	}

	public long getBj() {
		return bj;
	}

	public void setBj(long bj) {
		this.bj = bj;
	}

	public long getTsbj() {
		return tsbj;
	}

	public void setTsbj(long tsbj) {
		this.tsbj = tsbj;
	}

	public float getBjPercent() {
		return bjPercent;
	}

	public void setBjPercent(float bjPercent) {
		this.bjPercent = bjPercent;
	}

	public float getTsPercent() {
		return tsPercent;
	}

	public void setTsPercent(float tsPercent) {
		this.tsPercent = tsPercent;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public long getCn() {
		return cn;
	}

	public void setCn(long cn) {
		this.cn = cn;
	}

	public long getCrn() {
		return crn;
	}

	public void setCrn(long crn) {
		this.crn = crn;
	}

	public long getCsn() {
		return csn;
	}

	public void setCsn(long csn) {
		this.csn = csn;
	}

	public long getCsrn() {
		return csrn;
	}

	public void setCsrn(long csrn) {
		this.csrn = csrn;
	}

	public long getCnred() {
		return cnred;
	}

	public void setCnred(long cnred) {
		this.cnred = cnred;
	}

	public long getCnyellow() {
		return cnyellow;
	}

	public void setCnyellow(long cnyellow) {
		this.cnyellow = cnyellow;
	}

	public long getCsred() {
		return csred;
	}

	public void setCsred(long csred) {
		this.csred = csred;
	}

	public long getCsyellow() {
		return csyellow;
	}

	public void setCsyellow(long csyellow) {
		this.csyellow = csyellow;
	}

	public long getBjred() {
		return bjred;
	}

	public void setBjred(long bjred) {
		this.bjred = bjred;
	}

	public long getBjyellow() {
		return bjyellow;
	}

	public void setBjyellow(long bjyellow) {
		this.bjyellow = bjyellow;
	}

	public long getRed() {
		return red;
	}

	public void setRed(long red) {
		this.red = red;
	}

	public long getYellow() {
		return yellow;
	}

	public void setYellow(long yellow) {
		this.yellow = yellow;
	}
	  
	  

}
