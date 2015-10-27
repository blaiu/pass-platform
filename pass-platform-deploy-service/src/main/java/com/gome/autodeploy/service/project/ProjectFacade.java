package com.gome.autodeploy.service.project;

import com.gome.autodeploy.domain.app.App;

/**
 * @author yangshuangjun
 *
 */
public interface ProjectFacade {
	
	/** 
	* @Title: addApp 
	* @Description: 新增应用
	* 	1、校验是否存在同名应用
	* 	2、新增
	* @param app
	* @return App   
	* @throws 
	*/
	public App addApp(App app);
	
	
	/** 
	* @Title: delAppById 
	* @Description: 删除应用
	* 	1、校验是否
	* @param appId
	* @return int   
	* @throws 
	*/
	public int delAppById(int appId);
}
