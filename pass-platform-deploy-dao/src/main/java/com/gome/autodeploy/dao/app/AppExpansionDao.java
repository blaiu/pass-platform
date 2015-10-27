package com.gome.autodeploy.dao.app;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.domain.app.AppExpansion;


public interface AppExpansionDao {
     
	public boolean addAppExpansion(AppExpansion appExpansion);
	public List<AppExpansion> queryAppExpansion(Map<String, Object> map);
	public boolean removeAppExpansion(Map<String, Object> map);
	public boolean modifyAppExpansion(AppExpansion appExpansion);
	
	public boolean updateAppExpansionDelFlag(AppExpansion appExpansion);
	
	public List<AppExpansion> queryAppExpansionByAppId(Integer appId);
	
	public boolean updateAppExpansionPlan(Integer appId);
}
