/**
 * 
 */
package com.gome.autodeploy.shiro.filter;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.context.MessageSource;

/**
 * @author bailu-ds
 *
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	@Resource
	private MessageSource messageSource;
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;
			HttpServletResponse httpServletResponse = (HttpServletResponse)response;
         	if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With")) || request.getParameter("ajax") == null) {// 不是ajax请求
         		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login?error=error");
         	} else {
         		httpServletRequest.setAttribute("error", messageSource.getMessage("shiro_msg_003", null, Locale.CHINA));
	            httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
	        }
		} catch (IOException | ServletException e1) {
			e1.printStackTrace();
		}
         return true;
	}
}
