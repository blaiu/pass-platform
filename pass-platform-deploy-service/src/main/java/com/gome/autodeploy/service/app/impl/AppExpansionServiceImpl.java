package com.gome.autodeploy.service.app.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.app.AppExpansionDao;
import com.gome.autodeploy.domain.app.AppExpansion;
import com.gome.autodeploy.service.app.AppExpansionService;

public class AppExpansionServiceImpl implements AppExpansionService{

	@Resource
	private AppExpansionDao appExpansionDao;
	
	@Override
	public boolean addAppExpansion(AppExpansion appExpansion) {
		return appExpansionDao.addAppExpansion(appExpansion);
	}

	@Override
	public List<AppExpansion> queryAppExpansion(Map<String, Object> map) {
		return appExpansionDao.queryAppExpansion(map);
	}

	@Override
	public boolean removeAppExpansion(Map<String, Object> map) {
		return appExpansionDao.removeAppExpansion(map);
	}

	@Override
	public boolean modifyAppExpansion(AppExpansion appExpansion) {
		return appExpansionDao.modifyAppExpansion(appExpansion);
	}

	@Override
	public boolean updateAppExpansionDelFlag(AppExpansion appExpansion) {
		return appExpansionDao.updateAppExpansionDelFlag(appExpansion);
	}

	@Override
	public List<AppExpansion> queryAppExpansionByAppId(Integer appId) {
		return appExpansionDao.queryAppExpansionByAppId(appId);
	}

	@Override
	public boolean updateAppExpansionPlan(Integer appId) {
		return appExpansionDao.updateAppExpansionPlan(appId);
	}

}
