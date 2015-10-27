/**
 * 
 */
package com.gome.autodeploy.dao.app;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.domain.app.ApplicationCommon;
import com.gome.autodeploy.domain.app.ApplicationMember;
import com.gome.autodeploy.domain.app.ApplicationPackageVersion;
import com.gome.autodeploy.domain.app.ApplicationResource;

/**
 * @author bailu-ds
 *
 */
public interface ApplicationDao {

	public boolean addApplication(Application application);
	public boolean modifyApplication(Application app);
	public Application getApplication(Integer appId);
	public Integer queryApplicationCount(Map<String, Object> map);
	public List<Application> queryApplication(Map<String, Object> map);
	
	public boolean addApplicationResource(List<ApplicationResource> resources);
	public boolean removeApplicationResource(Map<String, Object> map);
	public List<ApplicationResource> queryApplicationResource(Map<String, Object> map);
	
	public boolean addApplicationMember(List<ApplicationMember> members);
	public boolean removeApplicationMember(Map<String, Object> map);
	public List<ApplicationMember> queryApplicationMember(Map<String, Object> map);
	
//	public boolean addApplicationSvn(ApplicationSvn applicationSvn);
//	public boolean modifyApplicationSvn(ApplicationSvn applicationSvn);
//	public ApplicationSvn getApplicationSvn(Integer appId);
	
	public boolean addApplicationPackage(ApplicationPackageVersion packageVersion);
	public List<ApplicationPackageVersion> queryApplicationPackage(Map<String, Object> map);
	
	public boolean addApplicationCommon(List<ApplicationCommon> commons);
	public boolean removeApplicationCommon(Integer id);
	public List<ApplicationCommon> queryApplicationCommon(Map<String, Object> map);
	
	
	
	
	
	
	
}
