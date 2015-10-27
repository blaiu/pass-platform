package com.gome.autodeploy.dao.app.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.app.AppPackageDao;
import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.domain.app.AppPackage;
import com.gome.autodeploy.exception.PaasDBException;

public class AppPackageDaoImpl extends BaseDao implements AppPackageDao{

	@Override
	public boolean addAppPackage(AppPackage appPackage) {
		Integer v=0;
		try {
			v = getSqlSession().insert(getAppPackageSpaceName() + "addAppPackage", appPackage);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<AppPackage> queryAppPackage(Map<String, Object> map) {
		try {
			return getSqlSession().selectList(getAppPackageSpaceName() + "queryAppPackage", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean removeAppPackage(Map<String, Object> map) {
		int result=0;
		try {
			result = getSqlSession().delete(getAppPackageSpaceName() + "removeAppPackage", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyAppPackage(AppPackage appPackage) {
		int flag=0;
		try {
			flag = getSqlSession().update(getAppPackageSpaceName() + "modifyAppPackage", appPackage);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

}
