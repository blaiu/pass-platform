package com.gome.autodeploy.dao.app;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.app.App;


 
public interface AppDao {
     
    public boolean addApp(App app);
	
	public List<App> queryApp(Map<String, Object> map);
	
	public App queryAppById(Integer id);
	
	public boolean removeApp(Map<String, Object> map);
	
	public boolean updateAppStatus(Map<String, Object> map);
	
	public boolean updateApp(App app);
	
	public Integer queryAppCount(Map<String, Object> map);
	
	public List<App> queryAppList(Map<String, Object> map);
	
	public boolean delApp(App app);
	
	
	/** 
	* @Title: delAppById 
	* @Description: 根据AppId 修改App
	* @param appId
	* @return boolean   
	* @throws 
	*/
	public boolean upateAppDelFlag(App app);
	
	public List<App> queryForAppList();
	
}
