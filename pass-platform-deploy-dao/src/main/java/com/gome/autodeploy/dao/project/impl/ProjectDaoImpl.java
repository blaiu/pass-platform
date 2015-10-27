package com.gome.autodeploy.dao.project.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.project.ProjectDao;
import com.gome.autodeploy.domain.project.Project;
import com.gome.autodeploy.exception.PaasDBException;

public class ProjectDaoImpl extends BaseDao implements ProjectDao {

	@Override
	public int addProject(Project project) {
		try {
			return getSqlSession().update(getProjectSpaceName() + "addProject", project);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public int modifyProject(Project project) {
		try {
			return getSqlSession().update(getProjectSpaceName() + "updateProject", project);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public Project getProject(Integer projectId) {
		
		try {
			return getSqlSession().selectOne(getProjectSpaceName() + "queryProjectById", projectId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public List<Project> queryProject(Project project) {
		try {
			return getSqlSession().selectList(getProjectSpaceName() + "queryProject", project);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public Integer queryProjectCount(Map<String, Object> map) {
		Object obj;
		try {
			obj = getSqlSession().selectOne(getProjectSpaceName() + "queryProjectCount", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (null != obj) {
			return (Integer) obj;
		}
		return 0;
	}

	@Override
	public boolean removeProject(Integer projId) {
		int returnValue;
		try {
			returnValue = getSqlSession().update(getProjectSpaceName() + "removeProject", projId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if(returnValue > 0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public  List<Project> queryProjectByUser(Integer userId) {
		
		try {
			return getSqlSession().selectList(getProjectSpaceName() + "queryProjectByUser", userId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}
	@Override
	public Project getProjectByNameOrSpell(Project project) {
		List<Project> list;
		try {
			list = getSqlSession().selectList(getProjectSpaceName() + "queryProjectNameOrSpell", project);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if(list != null && list.size() > 0 ){
			return list.get(0);
		}else{
			return null;
		}
	}
}
