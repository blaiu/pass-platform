/**
 * 
 */
package com.gome.autodeploy.dao.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.auth.UserDao;
import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.domain.auth.Permission;
import com.gome.autodeploy.domain.auth.Resources;
import com.gome.autodeploy.domain.auth.Role;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.exception.PaasDBException;

/**
 * @author bailu-ds
 *
 */
public class UserDaoImpl extends BaseDao implements UserDao {

	private String getSpaceName() {
		return "UserMapper.";
	}
	
	@Override
	public User getUserByName(String userName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		List<User> list;
		try {
			list = getSqlSession().selectList(getSpaceName() + "getUser", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Role> queryUserRoles(Integer userId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", userId);
		try {
			return getSqlSession().selectList(getSpaceName() + "queryUserRoles", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public List<Permission> queryUserPermissions(List<Integer> roleIds) {
		try {
			return getSqlSession().selectList(getSpaceName() + "queryUserPermissions", roleIds);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public List<Resources> queryResources() {
		try {
			return getSqlSession().selectList(getSpaceName() + "queryResources");
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}
	@Override
	public User getUserLoginMessages(String userName, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("password",password);
		List<User> list;
		try {
			list = getSqlSession().selectList(getSpaceName() + "queryUsersLogin", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	
	}
   
	

}
