/**
 * 
 */
package com.gome.autodeploy.service.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.auth.UserDao;
import com.gome.autodeploy.domain.auth.Permission;
import com.gome.autodeploy.domain.auth.Resources;
import com.gome.autodeploy.domain.auth.Role;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.service.auth.UserService;

/**
 * @author bailu-ds
 *
 */
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	@Override
	public List<Role> queryUserRoles(Integer userId) {
		return userDao.queryUserRoles(userId);
	}

	@Override
	public List<Permission> queryUserPermissions(List<Integer> roleIds) {
		return userDao.queryUserPermissions(roleIds);
	}

	@Override
	public List<Resources> queryResources() {
		return userDao.queryResources();
	}
    public User getUserLoginMessages(String userName, String password) {
		return userDao.getUserLoginMessages(userName, password);
    }
}
