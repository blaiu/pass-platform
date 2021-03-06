/**
 * 
 */
package com.gome.autodeploy.shiro.spring;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;

import com.gome.autodeploy.domain.auth.Resources;
import com.gome.autodeploy.service.auth.UserService;
import com.gome.autodeploy.shiro.common.Constants;

/**
 * @author bailu-ds
 *
 */
public class CustomDefaultFilterChainManager extends DefaultFilterChainManager {

	private Map<String, String> filterChainDefinitionMap = null;
	
	private String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;
	
	@Resource
	private CacheManager shiroCacheManager;
	
	@Resource
	private UserService userService;
	
	private Section initChain(Section section) {
		Section s = (Section) Constants.cMap.get("chains_url");
		if (null == s || s.size() == 0) {
			List<Resources> list = userService.queryResources();
			for (Resources r : list) {
				section.put(r.getUrl(), r.getFilters()); 
			}
			Constants.cMap.put("chains_url", section);
		} else {
			section = s;
		}
		return section;
	}
	
	public CustomDefaultFilterChainManager() {
		//过滤器
		setFilters(new LinkedHashMap<String, Filter>());
		//过滤器链
		setFilterChains(new LinkedHashMap<String, NamedFilterList>());
		addDefaultFilters(false);
	}
	
	public void setCustomFilters(Map<String, Filter> customFilters) {
		for (Map.Entry<String, Filter> entry : customFilters.entrySet()) {
			System.out.println("key:" + entry.getKey() + "value:" + entry.getValue());
			addFilter(entry.getKey(), entry.getValue(), false);
		}
	}
	
	public void setDefaultFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
            section = initChain(section);
        }
        setFilterChainDefinitionMap(section);
    }
	
	@PostConstruct
    public void init() {
        Map<String, Filter> filters = getFilters();
        if (!CollectionUtils.isEmpty(filters)) {
            for (Map.Entry<String, Filter> entry : filters.entrySet()) {
                String name = entry.getKey();
                Filter filter = entry.getValue();
                applyGlobalPropertiesIfNecessary(filter);
                if (filter instanceof Nameable) {
                    ((Nameable) filter).setName(name);
                }
            }
        }

        //build up the chains:
        Map<String, String> chains = getFilterChainDefinitionMap();
        if (!CollectionUtils.isEmpty(chains)) {
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue();
                createChain(url, chainDefinition);
            }
        }
    }
	
	@Override
	protected void initFilter(Filter filter) {
		// TODO Auto-generated method stub
		//super.initFilter(filter);
	}

	public FilterChain proxy(FilterChain original, List<String> chainNames) {
		NamedFilterList configured = new SimpleNamedFilterList(chainNames.toString());
        for(String chainName : chainNames) {
        	configured.addAll(getChain(chainName));
        }
        return configured.proxy(original);
	}
	
	private void applyLoginUrlIfNecessary(Filter filter) {
        String loginUrl = getLoginUrl();
        if (StringUtils.hasText(loginUrl) && (filter instanceof AccessControlFilter)) {
            AccessControlFilter acFilter = (AccessControlFilter) filter;
            String existingLoginUrl = acFilter.getLoginUrl();
            if (AccessControlFilter.DEFAULT_LOGIN_URL.equals(existingLoginUrl)) {
                acFilter.setLoginUrl(loginUrl);
            }
        }
    }

    private void applySuccessUrlIfNecessary(Filter filter) {
        String successUrl = getSuccessUrl();
        if (StringUtils.hasText(successUrl) && (filter instanceof AuthenticationFilter)) {
            AuthenticationFilter authcFilter = (AuthenticationFilter) filter;
            //only apply the successUrl if they haven't explicitly configured one already:
            String existingSuccessUrl = authcFilter.getSuccessUrl();
            if (AuthenticationFilter.DEFAULT_SUCCESS_URL.equals(existingSuccessUrl)) {
                authcFilter.setSuccessUrl(successUrl);
            }
        }
    }

    private void applyUnauthorizedUrlIfNecessary(Filter filter) {
        String unauthorizedUrl = getUnauthorizedUrl();
        if (StringUtils.hasText(unauthorizedUrl) && (filter instanceof AuthorizationFilter)) {
            AuthorizationFilter authzFilter = (AuthorizationFilter) filter;
            //only apply the unauthorizedUrl if they haven't explicitly configured one already:
            String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
            if (existingUnauthorizedUrl == null) {
                authzFilter.setUnauthorizedUrl(unauthorizedUrl);
            }
        }
    }
    
    private void applyGlobalPropertiesIfNecessary(Filter filter) {
        applyLoginUrlIfNecessary(filter);
        applySuccessUrlIfNecessary(filter);
        applyUnauthorizedUrlIfNecessary(filter);
    }

	
	public Map<String, String> getFilterChainDefinitionMap() {
		return filterChainDefinitionMap;
	}
	
	public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
		this.filterChainDefinitionMap = filterChainDefinitionMap;
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}
	
	
	
	
	
	
}
