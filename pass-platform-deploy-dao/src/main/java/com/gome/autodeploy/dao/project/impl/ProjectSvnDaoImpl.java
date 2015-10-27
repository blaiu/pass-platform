package com.gome.autodeploy.dao.project.impl;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.project.ProjectSvnDao;
import com.gome.autodeploy.domain.project.ProjectSvn;
import com.gome.autodeploy.exception.PaasDBException;

public class ProjectSvnDaoImpl extends BaseDao implements ProjectSvnDao {

	@Override
	public int addProjectSvn(ProjectSvn projectSvn) {
		try {
			return getSqlSession().update(getProjectSvnSpaceName() + "addProjectSvn", projectSvn);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public int modifyProjectSvn(ProjectSvn projectSvn) {
		try {
			return getSqlSession().update(getProjectSvnSpaceName() + "modifyProjectSvn", projectSvn);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public ProjectSvn getProjectSvn(Integer projectId) {
		try {
			return getSqlSession().selectOne(getProjectSvnSpaceName() + "queryProjectSvn", projectId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

}
