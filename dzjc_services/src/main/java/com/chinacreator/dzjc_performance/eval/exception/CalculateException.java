package com.chinacreator.dzjc_performance.eval.exception;

/**
 * 自定义异常，在绩效考核，计算的时候，出了异常传递返回值信息
 * @author Administrator
 *
 */
public class CalculateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6924469572636913447L;

	public CalculateException(String message){
		super(message);
	}
	
	public CalculateException(Throwable throwable){
		super(throwable);
	}
	
	public CalculateException(String message,Throwable throwable){
		super(message,throwable);
	}
}
