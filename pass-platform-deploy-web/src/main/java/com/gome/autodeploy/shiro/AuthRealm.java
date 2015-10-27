/**
 * 
 */
package com.gome.autodeploy.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.gome.autodeploy.domain.auth.Role;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.service.auth.UserService;

/**
 * @author bailu-ds
 *
 */
public class AuthRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;
	
	/** 
     * 为当前登录的Subject授予角色和权限 
     * @see  经测试:本例中该方法的调用时机为需授权资源被访问时 
     * @see  经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache 
     * @see  个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache 
     * @see  比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache 
     */ 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String currentUsername = (String)super.getAvailablePrincipal(principalCollection);
		List<String> permissionList = new ArrayList<String>();  
		//从数据库中获取当前登录用户的详细信息  
		User user = userService.getUserByName(currentUsername);
		if (null != user) {
			List<Role> listRoles = userService.queryUserRoles(user.getUserId());
			Map<Integer, String> map = getRoleMap(listRoles);
			List<com.gome.autodeploy.domain.auth.Permission> listPermission = userService.queryUserPermissions(getRoleIds(map));
			//实体类User中包含有用户角色的实体类信息  
			if (null != listPermission && listPermission.size() > 0){  
				//获取权限  
				for(com.gome.autodeploy.domain.auth.Permission pmss : listPermission){  
					if(!StringUtils.isEmpty(pmss.getPermissionName())){  
						permissionList.add(pmss.getPermissionName());  
					}  
				}  
			}  
			//为当前用户设置角色和权限  
			SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
			simpleAuthorInfo.addRoles(map.values());  
			simpleAuthorInfo.addStringPermissions(permissionList);
			return simpleAuthorInfo;
		} else {  
			throw new AuthorizationException("用户名：" + currentUsername + "不存在");  
		}  
	}

	/**
	 * @see  省份认证登陆
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;  
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
        User user = userService.getUserByName(token.getUsername()); 
        if (null != user) {  
        	AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), null);  
        	putSession("currentUser", user);  
        	return authcInfo;  
        } else {  
          return null;  
        } 
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
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void putSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }

}
