/**
 * 
 */
package com.gome.autodeploy.service.auth;

import java.util.List;

import com.gome.autodeploy.domain.auth.Permission;
import com.gome.autodeploy.domain.auth.Resources;
import com.gome.autodeploy.domain.auth.Role;
import com.gome.autodeploy.domain.auth.User;

/**
 * 
 *
 */
public interface UserService {

	public User getUserByName(String userName);
	
	public List<Role> queryUserRoles(Integer userId);
	
	public List<Permission> queryUserPermissions(List<Integer> roleIds);
	
	public List<Resources> queryResources();
	
	public User getUserLoginMessages(String userName, String password);
	
}
