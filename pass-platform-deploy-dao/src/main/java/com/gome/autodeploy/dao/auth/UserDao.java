/**
 * 
 */
package com.gome.autodeploy.dao.auth;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.auth.Permission;
import com.gome.autodeploy.domain.auth.Resources;
import com.gome.autodeploy.domain.auth.Role;
import com.gome.autodeploy.domain.auth.User;

/**
 * @author bailu-ds
 *
 */
public interface UserDao {
/**
 * 
 * @param userName
 * @return
 */
	public User getUserByName(String userName);
	
	public List<Role> queryUserRoles(Integer userId);
	
	public List<Permission> queryUserPermissions(List<Integer> roleIds);
	
	public List<Resources> queryResources();
	/**
	 * 
	 * @param 校验用户名和密码
	 * @param user和password
	 * @return
	 */
    public User getUserLoginMessages(String userName , String password);
    
    
}

