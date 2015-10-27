/**
 * 
 */
package com.gome.autodeploy.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.gome.autodeploy.domain.app.App;
import com.gome.autodeploy.domain.app.AppExpansion;
import com.gome.autodeploy.service.app.AppExpansionService;
import com.gome.autodeploy.service.app.AppFacade;
import com.gome.autodeploy.service.app.AppService;

/**
 * @author yangshuangjun
 *
 */
public class AppFacadeImpl implements AppFacade {
	private static Logger logger = Logger.getLogger(AppFacadeImpl.class);
	@Resource
	private AppService appService;
	@Resource
	private AppExpansionService appExpansionService;
	 
	@Override
	public boolean addApp(App app, List<AppExpansion> expansions) {
		if(app == null || expansions == null){
			logger.error(" 参数 不能 null");
			return false;
		}
		boolean addFlag = appService.addApp(app);
		if(addFlag){
			for(AppExpansion appExpansion : expansions){
				appExpansion.setAppId(app.getId());
				appExpansion.setIsDel(0);
				appExpansion.setPackageId(app.getPackageId());
				addFlag = addFlag && appExpansionService.addAppExpansion(appExpansion);
			}			
		}
		return addFlag;
	}
	@Override
	public boolean delApp(int appId) {
		App app = new App();
		app.setIsDel(1);
		app.setId(appId);
		AppExpansion appExpansion = new AppExpansion();
		appExpansion.setIsDel(1);
		appExpansion.setAppId(appId);
		boolean delFlag = appExpansionService.updateAppExpansionDelFlag(appExpansion);
		delFlag = delFlag && appService.upateAppDelFlag(app);
		return delFlag;
	}
	@Override
	public boolean recoverApp(int appId) {
		App app = new App();
		app.setIsDel(0);
		app.setId(appId);
		AppExpansion appExpansion = new AppExpansion();
		appExpansion.setIsDel(0);
		appExpansion.setAppId(appId);
		boolean recFlag = appExpansionService.updateAppExpansionDelFlag(appExpansion);
		recFlag = recFlag && appService.upateAppDelFlag(app);
		return recFlag;
	}

}
