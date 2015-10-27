/**
 * 
 */
package com.gome.autodeploy.shiro.filter;

import org.apache.shiro.web.filter.authc.LogoutFilter;

/**
 * @author bailu-ds
 *
 */
public class CustomLogoutFilter extends LogoutFilter {

	private String redirectUrl;
	
	@Override
	public void setRedirectUrl(String redirectUrl) {
		super.setRedirectUrl(redirectUrl);
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	
	
}
