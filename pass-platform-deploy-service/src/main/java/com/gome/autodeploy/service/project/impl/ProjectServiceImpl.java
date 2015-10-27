package com.gome.autodeploy.service.project.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.project.ProjectDao;
import com.gome.autodeploy.dao.project.ProjectPackageDao;
import com.gome.autodeploy.domain.project.Project;
import com.gome.autodeploy.service.project.ProjectService;

public class ProjectServiceImpl implements ProjectService {
	@Resource
	private ProjectDao projectDao;
	@Resource
	private ProjectPackageDao projectPackageDao;
	
	@Override
	public int addProject(Project project) {
		return projectDao.addProject(project);
	}

	@Override
	public int modifyProject(Project project) {
		return projectDao.modifyProject(project);
	}

	@Override
	public Project getProject(Integer projectId) {
		return projectDao.getProject(projectId);
	}

	@Override
	public List<Project> queryProject(Project project) {
		return projectDao.queryProject(project);
	}

	@Override
	public List<Project> queryProjectByUser(Integer userId) {
		return projectDao.queryProjectByUser(userId);
	}
	@Override
	public Integer queryProjectCount(Map<String, Object> map) {
		return projectDao.queryProjectCount(map);
	}

	@Override
	public boolean removeProject(Integer projId) {
		return projectDao.removeProject(projId);
	}

	@Override
	public boolean checkExistProjectNameOrSpell(Project project) {
		 if(projectDao.getProjectByNameOrSpell(project) != null){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	
}
