/**
 * 
 */
package com.gome.autodeploy.dao.common;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @author bailu-ds
 *
 */
public class BaseDao extends SqlSessionDaoSupport {

	
	
//	@Resource
//	protected SqlSessionTemplate sqlSession;
	// --- app
	protected String getAppExpansionSpaceName() {
		return "AppExpansionMapper.";
	}
	protected String getProjectSpaceName() {
		return "ProjectMapper.";
	}
	protected String getAppSpaceName() {
		return "ApplicationMapper.";
	}
	
	protected String getAppResourceSpaceName() {
		return "ApplicationResourceMapper.";
	}
	
	protected String getAppMemberSpaceName() {
		return "ApplicationMemberMapper.";
	}
	
	protected String getProjectSvnSpaceName() {
		return "ProjectSvnMapper.";
	}
	
	protected String getAppCommonSpaceName() {
		return "ApplicationCommonMapper.";
	}
	
	protected String getAppPackageSpaceName() {
		return "AppPackageMapper.";
	}
	
	// --- online
	protected String getOnlineTaskSpaceName() {
		return "OnlineTaskMapper.";
	}
	
	protected String getOnlineTaskPackageSpaceName() {
		return "OnlineTaskPackageMapper.";
	}
	
	protected String getOnlineTaskResumeSpaceName() {
		return "OnlineTaskResumeMapper.";
	}
	
	// --- deploy
	protected String getDeployTaskSpaceName() {
		return "OnlineTaskMapper.";
	}
	
	protected String getDeployResourceSpaceName() {
		return "DeployResourceMapper.";
	}
	
	protected String getTestDeployResourcesSpaceName() {
		return "TestDeployResourcesMapper.";
	}
	
	protected String getContainerConfigSpaceName() {
		return "ContainerConfigMapper.";
	}
	
	//---volume
	protected String getVolumeSpaceName() {
		return "VolumeMapper.";
	}
	
	
	// --- common
	protected String getDictionarySpaceName() {
		return "DictionaryMapper.";
	}

}
