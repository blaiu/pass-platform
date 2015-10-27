/**
 * 
 */
package com.gome.autodeploy.service.app;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.common.utils.page.Pagination;
import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.domain.app.ApplicationMember;
import com.gome.autodeploy.domain.app.ApplicationPackageVersion;
import com.gome.autodeploy.domain.app.ApplicationResource;

/**
 * @author bailu-ds
 *
 */
public interface ApplicationService {

	public boolean addApplication(Application application, List<ApplicationResource> resources, List<ApplicationMember> members);
	public Application getApplication(Integer appId);
	public int queryApplicationCount(Map<String, Object> map);
	public List<Application> queryApplication(Map<String, Object> map);
	public Pagination queryApplicationPage(Map<String, Object> map, int pageNo, int pageSize);
	public boolean though(Integer appId);
	public boolean notThough(Integer appId, String reason);
	
	public List<Application> queryApplicationByMember(Map<String, Object> map);
	
	public String queryApplicationMember(Integer appId);
	
	public List<ApplicationResource> queryApplicationResource(Map<String, Object> map);
	public List<ApplicationPackageVersion> queryApplicationPackage(Map<String, Object> map);
	
}
