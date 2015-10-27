package com.gome.autodeploy.web.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.gome.autodeploy.exception.PaasException;

public class ExceptionHandler extends DefaultHandlerExceptionResolver{
	private static final Logger logger = Logger.getLogger(ExceptionHandler.class);  

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error("执行出错，堆栈信息如下：", ex);
		try {
			if(ex instanceof PaasException){
				if(((PaasException) ex).getErrorCode().equals("404")){
					
				}
				System.out.println(ex.toString());
			}
			 
			String errorMsg = ex.getMessage();
			//设置repsonse输出编码格式
			response.setContentType("text/html;charset=UTF-8"); 
			response.getOutputStream().write(errorMsg.getBytes());
		} catch (IOException e) {
			//忽略
		}catch(Exception e){
			
		}
		return new ModelAndView();
	}
}
