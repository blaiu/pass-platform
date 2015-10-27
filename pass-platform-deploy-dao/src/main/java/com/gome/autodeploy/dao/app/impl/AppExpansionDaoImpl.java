package com.gome.autodeploy.dao.app.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.app.AppExpansionDao;
import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.domain.app.AppExpansion;
import com.gome.autodeploy.exception.PaasDBException;

public class AppExpansionDaoImpl extends BaseDao  implements AppExpansionDao {

	@Override
	public boolean addAppExpansion(AppExpansion appExpansion) {
		Integer v=0;
		try {
			v = getSqlSession().insert(getAppExpansionSpaceName()+"addAppExpansion", appExpansion);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public List<AppExpansion> queryAppExpansion(Map<String, Object> map) {
		try {
			return getSqlSession().selectList(getAppExpansionSpaceName()+"queryAppExpansion", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean removeAppExpansion(Map<String, Object> map) {
		Integer v=0;
		try {
			v = getSqlSession().update(getAppExpansionSpaceName()+"removeAppExpansion", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean modifyAppExpansion(AppExpansion appExpansion) {
		Integer v=0;
		try {
			v = getSqlSession().update(getAppExpansionSpaceName()+"modifyAppExpansion", appExpansion);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public boolean updateAppExpansionDelFlag(AppExpansion appExpansion) {
		Integer v=0;
		try {
			v = getSqlSession().update(getAppExpansionSpaceName()+"updateAppExpansionDelFlag", appExpansion);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public List<AppExpansion> queryAppExpansionByAppId(Integer appId) {
		try {
			return getSqlSession().selectList(getAppExpansionSpaceName()+"queryAppExpansionByAppId", appId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean updateAppExpansionPlan(Integer appId) {
		Integer v=0;
		try {
			v = getSqlSession().update(getAppExpansionSpaceName()+"updateAppExpansionPlan", appId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		} 
		return false;
	}
	
}
