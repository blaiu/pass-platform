package com.gome.autodeploy.service.project.impl;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.project.ProjectSvnDao;
import com.gome.autodeploy.domain.project.ProjectSvn;
import com.gome.autodeploy.service.project.ProjectSvnService;

public class ProjectSvnServiceImpl implements ProjectSvnService {
	@Resource
	private ProjectSvnDao projectSvnDao;
	
	
	@Override
	public int addProjectSvn(ProjectSvn projectSvn) {
		return projectSvnDao.addProjectSvn(projectSvn);
	}

	@Override
	public int modifyProjectSvn(ProjectSvn projectSvn) {
		return projectSvnDao.modifyProjectSvn(projectSvn);
	}

	@Override
	public ProjectSvn getProjectSvn(Integer projectId) {
		return projectSvnDao.getProjectSvn(projectId);
	}

}
