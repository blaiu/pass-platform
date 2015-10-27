package com.gome.autodeploy.dao.app;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.app.AppPackage;


public interface AppPackageDao {
     
	public boolean addAppPackage(AppPackage appPackage);
	public List<AppPackage> queryAppPackage(Map<String, Object> map);
	public boolean removeAppPackage(Map<String, Object> map);
	public boolean modifyAppPackage(AppPackage appPackage);
}
