/**
 * 
 */
package com.gome.autodeploy.service.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.common.utils.page.Pagination;
import com.gome.autodeploy.dao.app.ApplicationDao;
import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.domain.app.ApplicationMember;
import com.gome.autodeploy.domain.app.ApplicationPackageVersion;
import com.gome.autodeploy.domain.app.ApplicationResource;
import com.gome.autodeploy.service.app.ApplicationService;

/**
 * @author bailu-ds
 *
 */
public class ApplicationServiceImpl implements ApplicationService {
	
	@Resource
	private ApplicationDao applicationDao;

	@Override
	public boolean addApplication(Application application, List<ApplicationResource> resources, List<ApplicationMember> members) {
		if (applicationDao.addApplication(application)) {
			if (null != resources) {
				for (ApplicationResource resource : resources) {
					resource.setAppId(application.getId());
				}
				applicationDao.addApplicationResource(resources);
			}
			
			if (null != members) {
				for (ApplicationMember member : members) {
					member.setAppId(application.getId());
				}
				applicationDao.addApplicationMember(members);
			}
			return true;
		} 
		return false;
	}
	
	@Override
	public Application getApplication(Integer appId) {
		return applicationDao.getApplication(appId);
	}
	
	@Override
	public int queryApplicationCount(Map<String, Object> map) {
		return applicationDao.queryApplicationCount(map);
	}
	
	@Override
	public List<Application> queryApplication(Map<String, Object> map) {
		return applicationDao.queryApplication(map);
	}

	@Override
	public Pagination queryApplicationPage(Map<String, Object> map, int pageNo, int pageSize) {
		map.put("listIds", queryAppIds(map));
		int total = queryApplicationCount(map);
		int endIndex = pageNo * pageSize;
		if (endIndex > total) {
			endIndex = total;
		}
		map.put("startRow", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("limit", "limit");
		List<Application> list = queryApplication(map);
		Pagination p  = new Pagination(pageNo, pageSize, total, list);
		p.setUrl(map.get("path").toString());
		return p;
	}
	
	private List<Integer> queryAppIds(Map<String, Object> map) {
		List<ApplicationMember> listAM = applicationDao.queryApplicationMember(map);
		List<Integer> list = new ArrayList<Integer>();
		for (ApplicationMember am : listAM) {
			list.add(am.getAppId());
		}
		return list;
	}

	@Override
	public boolean though(Integer appId) {
		Application app = new Application();
		app.setStatus(1);
		app.setId(appId);
		return applicationDao.modifyApplication(app);
	}

	@Override
	public boolean notThough(Integer appId, String reason) {
		Application app = new Application();
		app.setStatus(-1);
		app.setId(appId);
		app.setReason(reason);
		return applicationDao.modifyApplication(app);
	}
	
	@Override
	public String queryApplicationMember(Integer appId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", appId);
		List<ApplicationMember> listAM = applicationDao.queryApplicationMember(map);
		String member = null;
		for (ApplicationMember am : listAM) {
			if(null == member) {
				member = am.getUserName() + "("+am.getUserCode()+")";
			} else {
				member = member + "," + am.getUserName() + "("+am.getUserCode()+")";
			}
		}
		return member;
	}

	@Override
	public List<Application> queryApplicationByMember(Map<String, Object> map) {
		map.put("listIds", queryAppIds(map));
		return queryApplication(map);
	}

	@Override
	public List<ApplicationResource> queryApplicationResource(Map<String, Object> map) {
		return applicationDao.queryApplicationResource(map);
	}
    
	@Override
	public List<ApplicationPackageVersion> queryApplicationPackage(Map<String, Object> map) {
		return applicationDao.queryApplicationPackage(map);
	}

	

}
