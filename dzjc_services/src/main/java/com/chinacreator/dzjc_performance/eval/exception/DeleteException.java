package com.chinacreator.dzjc_performance.eval.exception;

/**
 * 自定义删除异常
 * @author Administrator
 *
 */
public class DeleteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4953553545189513690L;

	public DeleteException(String message){
		super(message);
	}
	
	public DeleteException(Throwable throwable){
		super(throwable);
	}
	
	public DeleteException(String message, Throwable throwable){
		super(message, throwable);
	}
}
