/**
 * 
 */
package com.gome.autodeploy.exception;


/**
 * @author yangshuangjun
 *
 */
public class PaasException extends RuntimeException {
	static final long serialVersionUID = 1L;

	private String errorCode;      //错误码
	private String message ;	   //异常信息
	
	public PaasException(String message) {
		super(message);
	}
	
	public PaasException(String errorCode,String message){
		super(String.format("%s:%s",errorCode,message));
		this.errorCode = errorCode;
		this.message = message;
	}
	
	 

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static final String PARAM_ERROR="param_error";   //参数不合法
//	public static final String NO_RESULT="no_result";		//没有相关数据
	public static final String INTERNAL_ERROR="internal_error";	//内部异常
	public static final String INTERFACE_ERROR="interface_error";	//接口异常
	
	
}
