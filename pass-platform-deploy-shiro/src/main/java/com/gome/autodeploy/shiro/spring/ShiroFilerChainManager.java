/**
 * 
 */
package com.gome.autodeploy.shiro.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.util.StringUtils;

import com.gome.autodeploy.domain.auth.Resources;
import com.gome.autodeploy.service.auth.UserService;

/**
 * @author bailu-ds
 *
 */
public class ShiroFilerChainManager {

	@Resource
	private DefaultFilterChainManager filterChainManager;
	
	@Resource
	private UserService userService;
	
	private Map<String, NamedFilterList> defaultFilterChains;
	
	public void init() {
		defaultFilterChains = new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
		initFilterChains(userService.queryResources());
	}
	
	public void initFilterChains(List<Resources> listResources) {
		filterChainManager.getFilterChains().clear();
		if (null != defaultFilterChains) {
			filterChainManager.getFilterChains().putAll(defaultFilterChains);
		}
		
		for (Resources resources : listResources) {
			String url = resources.getUrl();
			
			if (StringUtils.isEmpty(resources.getPermission())) {
				filterChainManager.addToChain(url, "perms", resources.getPermission());
			}
		}
		
	}
	
}
