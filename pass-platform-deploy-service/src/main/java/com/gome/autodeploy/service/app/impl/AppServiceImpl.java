package com.gome.autodeploy.service.app.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.common.utils.page.Pagination;
import com.gome.autodeploy.dao.app.AppDao;
import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.service.app.AppService;

public class AppServiceImpl implements AppService{

	@Resource
	private AppDao appDao;
	
	@Override
	public boolean addApp(App app) {
		return appDao.addApp(app);
	}

	@Override
	public List<App> queryApp(Map<String, Object> map) {
		return appDao.queryApp(map);
	}

	@Override
	public App queryAppById(Integer id) {
		return appDao.queryAppById(id);
	}

	@Override
	public boolean removeApp(Map<String, Object> map) {
		return appDao.removeApp(map);
	}

	@Override
	public boolean updateAppStatus(Map<String, Object> map) {
		return appDao.updateAppStatus(map);
	}

	@Override
	public boolean updateApp(App app) {
		return appDao.updateApp(app);
	}

	@Override
	public List<App> queryAppList(Map<String, Object> map) {
		return appDao.queryAppList(map);
	}

	@Override
	public boolean delApp(App app) {
		return appDao.delApp(app);
	}

	@Override
	public boolean upateAppDelFlag(App app) {
		return appDao.upateAppDelFlag(app);
	}
    
	
	
	@Override
	public Integer queryAppCount(Map<String, Object> map) {
		return appDao.queryAppCount(map);
	}
	
	@Override
	public Pagination queryAppPage(Map<String, Object> map, int pageNo,int pageSize) {
		int total = queryAppCount(map);
		int endIndex = pageNo * pageSize;
		if (endIndex > total) {
			endIndex = total;
		}
		map.put("startRow", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("limit", "limit");
		List<App> list = queryAppList(map);
		Pagination p  = new Pagination(pageNo, pageSize, total, list);
		p.setUrl(map.get("path").toString());
		return p;
	}

	@Override
	public List<App> queryForAppList() {
		return appDao.queryForAppList();
	}

	

}
