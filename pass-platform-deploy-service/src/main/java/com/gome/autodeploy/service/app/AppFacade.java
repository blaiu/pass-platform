package com.gome.autodeploy.service.app;

import java.util.List;

import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.domain.app.AppExpansion;

public interface AppFacade {

	/** 
	* @Title: addApp 
	* @Description: app新增方法，新增app及扩容计划
	* @param app
	* @param expansions
	* @return boolean   
	* @throws 
	*/
	public boolean addApp(App app,List<AppExpansion> expansions);
	
	
	
	/** 
	* @Title: delApp 
	* @Description: 根据AppId 修改 app及扩展计划标识信息  isDel = 1
	* @param appId void   
	* @throws 
	*/
	public boolean delApp(int appId);
	
	
	/** 
	* @Title: recoverApp 
	* @Description: 根据AppId 恢复app及扩容计划标识信息 isDel = 0
	* @param appId
	* @return boolean   
	* @throws 
	*/
	public boolean recoverApp(int appId);
}
