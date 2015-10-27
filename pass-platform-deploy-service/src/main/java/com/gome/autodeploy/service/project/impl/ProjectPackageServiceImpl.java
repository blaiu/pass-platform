/**
 * 
 */
package com.gome.autodeploy.service.project.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.project.ProjectPackageDao;
import com.gome.autodeploy.domain.project.ProjectPackage;
import com.gome.autodeploy.service.project.ProjectPackageService;

/**
 * @author bailu-ds
 *
 */
public class ProjectPackageServiceImpl implements ProjectPackageService {

	@Resource
	private ProjectPackageDao projectPackageDao;
	
	@Override
	public boolean addProjectPackage(ProjectPackage projectPackage) {
		return projectPackageDao.addProjectPackage(projectPackage);
	}

	@Override
	public List<ProjectPackage> queryProjectPackage(Map<String, Object> map) {
		return projectPackageDao.queryProjectPackage(map);
	}

	@Override
	public boolean removeProjectPackage(Map<String, Object> map) {
		return projectPackageDao.removeProjectPackage(map);
	}

	@Override
	public boolean updateProjectPackageStatus(Map<String, Object> map) {
		return projectPackageDao.updateProjectPackageStatus(map);
	}

	@Override
	public boolean updateProjectPackage(ProjectPackage projectPackage) {
		return projectPackageDao.updateProjectPackage(projectPackage);
	}

	@Override
	public List<ProjectPackage> queryImage(Map<String, Object> map) {
		return projectPackageDao.queryImage(map);
	}

	@Override
	public ProjectPackage queryProjectPackageById(Integer id) {
		return projectPackageDao.queryProjectPackageById(id);
	}

	@Override
	public int queryProjectPackageByVersionNo(String versionNo) {
		return projectPackageDao.queryProjectPackageByVersionNo(versionNo);
	}

}
