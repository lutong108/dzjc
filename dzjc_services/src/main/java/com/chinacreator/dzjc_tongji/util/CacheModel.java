package com.chinacreator.dzjc_tongji.util;

import java.util.List;

/**
 * 缓存模型
 *
 */
public class CacheModel{

	private List<? extends Object> value;
		
	private Long createTime;
		
	private Long maxAge;

	public List<? extends Object> getValue() {
		return value;
	}

	public void setValue(List<? extends Object> value) {
		this.value = value;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Long maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * 是否有效
	 * @return
	 */
	public boolean isValid() {
		if (System.currentTimeMillis() - this.getMaxAge() > this.getCreateTime()) {
			return true;
		}
		return false;
	}
	
	public CacheModel(List<? extends Object> value,Long createTime,Long maxAge) {
		this.value = value;
		this.createTime = createTime;
		this.maxAge = maxAge;
	}
}
