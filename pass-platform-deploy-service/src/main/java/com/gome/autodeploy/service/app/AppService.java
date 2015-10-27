package com.gome.autodeploy.service.app;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.common.utils.page.Pagination;
import com.gome.autodeploy.domain.app.App;

public interface AppService {
     
    public boolean addApp(App app);
	
	public List<App> queryApp(Map<String, Object> map);
	
	public App queryAppById(Integer id);
	
	public boolean removeApp(Map<String, Object> map);
	
	public boolean updateAppStatus(Map<String, Object> map);
	
	public boolean updateApp(App app);
	
	public List<App> queryAppList(Map<String, Object> map);
	
	public boolean delApp(App app);
	public boolean upateAppDelFlag(App app);
	
	public List<App> queryForAppList();
	
	public Integer queryAppCount(Map<String, Object> map);
	public Pagination queryAppPage(Map<String, Object> map, int pageNo, int pageSize);
}

