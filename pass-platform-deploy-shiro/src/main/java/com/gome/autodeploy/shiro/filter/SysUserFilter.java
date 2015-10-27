/**
 * 
 */
package com.gome.autodeploy.shiro.filter;


import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.gome.autodeploy.service.auth.UserService;
import com.gome.autodeploy.shiro.common.Constants;


/**
 * @author bailu-ds
 *
 */
public class SysUserFilter extends PathMatchingFilter {

	@Resource
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String userName = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, SecurityUtils.getSubject().getSession().getAttribute(userName));
        return false;
    }
	
}
