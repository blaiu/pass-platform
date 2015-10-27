package com.gome.autodeploy.dao.app.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.app.AppDao;
import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.exception.PaasDBException;

public class AppDaoImpl extends BaseDao implements AppDao {

	@Override
	public boolean addApp(App app) {
		int result = 0;
		try {
			result = getSqlSession().insert("AppMapper.addApp", app);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateAppStatus(Map<String, Object> map) {
		int result = 0;
		try {
			result = getSqlSession().update("AppMapper.updateAppStatus", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateApp(App app) {
		int result = 0;
		try {
			result = getSqlSession().update("AppMapper.modifyAppStatus", app);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public App queryAppById(Integer id) {
		try {
			return getSqlSession().selectOne("AppMapper.queryAppById", id);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public List<App> queryApp(Map<String, Object> map) {
		try {
			return getSqlSession().selectList("AppMapper.queryApp", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean removeApp(Map<String, Object> map) {
		int result = 0;
		try {
			result = getSqlSession().delete("AppMapper.removeApp", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Integer queryAppCount(Map<String, Object> map) {
		Object obj;
		try {
			obj = getSqlSession().selectOne("AppMapper.queryAppCount", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (null != obj) {
			return (Integer) obj;
		}
		return 0;
	}

	@Override
	public List<App> queryAppList(Map<String, Object> map) {
		try {
			return getSqlSession().selectList("AppMapper.queryAppList", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean delApp(App app) {
		try {
			int resullt = getSqlSession().update("AppMapper.deleteApp", app);
			if (resullt > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean upateAppDelFlag(App app) {
		try {
			int resullt = getSqlSession().update("AppMapper.upateAppDelFlag",
					app);
			if (resullt > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public List<App> queryForAppList() {
		try {
			return getSqlSession().selectList("AppMapper.queryForAppList");
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

}
