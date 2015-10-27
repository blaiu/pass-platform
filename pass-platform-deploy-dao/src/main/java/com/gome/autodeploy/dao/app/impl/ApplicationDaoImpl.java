/**
 * 
 */
package com.gome.autodeploy.dao.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.app.ApplicationDao;
import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.domain.app.ApplicationCommon;
import com.gome.autodeploy.domain.app.ApplicationMember;
import com.gome.autodeploy.domain.app.ApplicationPackageVersion;
import com.gome.autodeploy.domain.app.ApplicationResource;

/**
 * @author bailu-ds
 *
 */
public class ApplicationDaoImpl extends BaseDao implements ApplicationDao {

	//---app
	@Override
	public boolean addApplication(Application app) {
		Integer v = getSqlSession().insert(getAppSpaceName() + "addApplication", app);
		if (null != v && v > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean modifyApplication(Application app) {
		int flag = getSqlSession().update(getAppSpaceName() + "modifyApplication", app);
		if (flag > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Application getApplication(Integer appId) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(appId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listIds", list);
		map.put("startRow", 0);
		map.put("pageSize", 1);
		List<Application> list1 = getSqlSession().selectList(getAppSpaceName() + "queryApplication", map);
		if (list1.size() > 0) {
			return list1.get(0);
		}
		return null;
	}
	
	@Override
	public Integer queryApplicationCount(Map<String, Object> map) {
		Object obj = getSqlSession().selectOne(getAppSpaceName() + "queryApplicationCount", map);
		if (null != obj) {
			return (Integer) obj;
		}
		return 0;
	}

	@Override
	public List<Application> queryApplication(Map<String, Object> map) {
		return getSqlSession().selectList(getAppSpaceName() + "queryApplication", map);
	}
	
	
	//---member
	@Override
	public boolean addApplicationMember(List<ApplicationMember> members) {
		int result = getSqlSession().insert(getAppMemberSpaceName() + "addApplicationMember", members);
		if (result > 0) {
			return true;
		} 
		return false;
	}
	
	@Override
	public boolean removeApplicationMember(Map<String, Object> map) {
		int result = getSqlSession().delete(getAppMemberSpaceName() + "removeApplicationMember", map);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<ApplicationMember> queryApplicationMember(Map<String, Object> map) {
		return getSqlSession().selectList(getAppMemberSpaceName() + "queryApplicationMember", map);
	}

	
	//---resource
	@Override
	public boolean addApplicationResource(List<ApplicationResource> resources) {
		Integer v = getSqlSession().insert(getAppResourceSpaceName() + "addApplicationResource", resources);
		if (null != v && v > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeApplicationResource(Map<String, Object> map) {
		int result = getSqlSession().delete(getAppResourceSpaceName() + "removeApplicationResource", map);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ApplicationResource> queryApplicationResource(Map<String, Object> map) {
		return getSqlSession().selectList(getAppResourceSpaceName() + "queryApplicationResource", map);
	}

	
	//---svn
//	@Override
//	public boolean addApplicationSvn(ApplicationSvn applicationSvn) {
//		Integer v = getSqlSession().insert(getAppSvnSpaceName() + "addApplicationSvn", applicationSvn);
//		if (null != v && v > 0) {
//			return true;
//		}
//		return false;
//	}

//	@Override
//	public boolean modifyApplicationSvn(ApplicationSvn applicationSvn) {
//		int result = getSqlSession().update(getAppSvnSpaceName() + "modifyApplicationSvn", applicationSvn);
//		if (result > 0) {
//			return true;
//		}
//		return false;
//	}

//	@Override
//	public ApplicationSvn getApplicationSvn(Integer appId) {
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("appId", appId);
//		List<ApplicationSvn> list = getSqlSession().selectList(getAppSvnSpaceName() + "getApplicationSvn", map);
//		if (list.size() > 0) {
//			return list.get(0);
//		}
//		return null;
//	}

	//--- package
	@Override
	public boolean addApplicationPackage(ApplicationPackageVersion packageVersion) {
		int result = getSqlSession().insert(getAppPackageSpaceName() + "addApplicationPackageVersion", packageVersion);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ApplicationPackageVersion> queryApplicationPackage(Map<String, Object> map) {
		return getSqlSession().selectList(getAppPackageSpaceName() + "queryApplicationPackageVersion", map);
	}

	
	//-- common
	@Override
	public boolean addApplicationCommon(List<ApplicationCommon> commons) {
		int result = getSqlSession().insert(getAppCommonSpaceName() + "addApplicationCommon", commons);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeApplicationCommon(Integer id) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		int result = getSqlSession().delete(getAppCommonSpaceName() + "", map);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ApplicationCommon> queryApplicationCommon(Map<String, Object> map) {
		return getSqlSession().selectList(getAppCommonSpaceName() + "queryApplicationMember", map);
	}

}
