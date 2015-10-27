/**
 * 
 */
package com.gome.autodeploy.shiro.realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.MessageSource;

import com.gome.autodeploy.domain.auth.Role;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.service.auth.UserService;


/**
 * @author bailu-ds
 *
 */
public class AuthRealm extends AuthorizingRealm {

//	private DefaultPasswordService passwordService = new DefaultPasswordService();
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
//		User user = userService.getUserByName(userName);
		if (null == userName) {
			throw new AuthorizationException(messageSource.getMessage("shiro_msg_001", new String[]{userName}, Locale.CHINA));
		}
		User user = getSession(userName);
		List<String> permissionList = new ArrayList<String>();
		List<Role> listRoles = userService.queryUserRoles(user.getUserId());
		Map<Integer, String> map = getRoleMap(listRoles);
		List<com.gome.autodeploy.domain.auth.Permission> listPermission = userService.queryUserPermissions(getRoleIds(map));
		if (null != listPermission && listPermission.size() > 0) {
			for(com.gome.autodeploy.domain.auth.Permission pmss : listPermission){  
				if(!StringUtils.isEmpty(pmss.getPermissionName())){  
					permissionList.add(pmss.getPermissionName());  
				}  
			}
		}
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
		simpleAuthorInfo.addRoles(map.values());  
		simpleAuthorInfo.addStringPermissions(permissionList);
		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
//		String password = String.copyValueOf((char[]) token.getCredentials());
        User user = userService.getUserByName(userName); 
        if (null == user) {
        	throw new UnknownAccountException(messageSource.getMessage("shiro_msg_001", new String[]{userName}, Locale.CHINA));
        }
        
        if (Boolean.TRUE.equals(user.getLocked())) {
        	throw new LockedAccountException(messageSource.getMessage("shiro_msg_002", null, Locale.CHINA));
        }
        
    	AuthenticationInfo authcInfo;
		try {
			authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getUserName()), user.getUserName());
		} catch (AuthenticationException e) {
			throw new AuthenticationException(messageSource.getMessage("shiro_msg_003", null, Locale.CHINA));
		}  
		
		setSession(user.getUserId(), user);
		
    	return authcInfo;  
	}

	private Map<Integer, String> getRoleMap(List<Role> listRoles) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (Role r : listRoles) {
			if (!map.containsKey(r.getRoleId())) {
				map.put(r.getRoleId(), r.getRoleName());
			}
		}
		return map;
	}
	
	private List<Integer> getRoleIds(Map<Integer, String> map) {
		List<Integer> list = new ArrayList<Integer>();
		Iterator<Integer> it = map.keySet().iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	
	private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    } 
	
	private User getSession(Object key){  
		Subject currentUser = SecurityUtils.getSubject();  
		if(null != currentUser){  
			Session session = currentUser.getSession();  
			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
			if(null != session){  
				return (User) session.getAttribute(key);
			}  
		}  
		return null;
	} 
	
//	public static void main(String[] args) {
//		DefaultPasswordService passwordService = new DefaultPasswordService();
//		System.out.println(passwordService.encryptPassword("123456").length());
//		System.out.println(passwordService.encryptPassword("123456"));
//		System.out.println(passwordService.encryptPassword("asjdhasidasjdiwjdlsajkdiwjlwaijiajs"));
//	}

}
