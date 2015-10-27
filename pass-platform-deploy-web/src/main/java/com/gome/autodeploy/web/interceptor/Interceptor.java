/**
 * 拦截器
 */
package com.gome.autodeploy.web.interceptor;  
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
/**   
 * @author 
 * @date 
 */  
@Repository  
public class Interceptor extends HandlerInterceptorAdapter{  
    private final Logger log = LoggerFactory.getLogger(Interceptor.class);  
	
  
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {   
    	 if ("GET".equalsIgnoreCase(request.getMethod())) {  
            
         }  
    	 log.info("==============执行顺序: 1、preHandle================");
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());        
        log.info("requestUri:"+requestUri);    
        log.info("contextPath:"+contextPath);    
        log.info("url:"+url);          
        String username =  (String)request.getSession().getAttribute("username");   
        if(username == null){  
            log.info("用户未登录，跳转到login");  
            request.getRequestDispatcher("/views/jsp/login.jsp").forward(request, response);  
            return false;  
        }else  
            return true;     
    }       
   
  
   
    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {       
        if(modelAndView != null){ 
            modelAndView.addObject("var", "postHandle");    
        }    
    }      
  
   
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object obj, Exception err)  
            throws Exception {  
    }  
  
  
}    