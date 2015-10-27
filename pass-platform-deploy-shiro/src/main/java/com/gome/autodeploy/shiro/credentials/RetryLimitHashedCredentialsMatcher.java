/**
 * 
 */
package com.gome.autodeploy.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;



import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 凭证匹配器
 * @author bailu-ds
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache;
	
	@Resource
	private CacheManager shiroCacheManager;

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		String userName = (String) token.getPrincipal();
		if (null != shiroCacheManager && null != shiroCacheManager.getCache("passwordRetryCache")) {
			passwordRetryCache = shiroCacheManager.getCache("passwordRetryCache");
			AtomicInteger retryCount = passwordRetryCache.get(userName);
			if (null == retryCount) {
				retryCount = new AtomicInteger(0);
				passwordRetryCache.put(userName, retryCount);
			}
			if (retryCount.incrementAndGet() > 5) {
				throw new ExcessiveAttemptsException();
			}
		} 
		boolean macthes = super.doCredentialsMatch(token, info);
		if (macthes) {
			if (null != passwordRetryCache) {
				passwordRetryCache.remove(userName);
			}
		}
		
		return macthes;
	}

}
