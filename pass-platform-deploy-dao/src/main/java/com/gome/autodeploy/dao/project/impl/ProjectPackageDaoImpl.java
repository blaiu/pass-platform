/**
 * 
 */
package com.gome.autodeploy.dao.project.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.project.ProjectPackageDao;
import com.gome.autodeploy.domain.project.ProjectPackage;
import com.gome.autodeploy.exception.PaasDBException;

/**
 * @author bailu-ds
 *
 */
public class ProjectPackageDaoImpl extends BaseDao implements ProjectPackageDao {

	@Override
	public boolean addProjectPackage(ProjectPackage projectPackage) {
		int result=0;
		try {
			result = getSqlSession().insert("ProjectPackageMapper.addProjectPackage", projectPackage);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ProjectPackage> queryProjectPackage(Map<String, Object> map) {
		try {
			return getSqlSession().selectList("ProjectPackageMapper.queryProjectPackage", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean removeProjectPackage(Map<String, Object> map) {
		int resullt=0;
		try {
			resullt = getSqlSession().delete("ProjectPackageMapper.removeProjectPackage", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (resullt > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProjectPackageStatus(Map<String, Object> map) {
		int resullt=0;
		try {
			resullt = getSqlSession().update("ProjectPackageMapper.updateProjectPackageStatus", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (resullt > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProjectPackage(ProjectPackage projectPackage) {
		int resullt=0;
		try {
			resullt = getSqlSession().update("ProjectPackageMapper.updateProjectPackage", projectPackage);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (resullt > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ProjectPackage> queryImage(Map<String, Object> map) {
		try {
			return getSqlSession().selectList("ProjectPackageMapper.queryImage", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public ProjectPackage queryProjectPackageById(Integer id) {
		List<ProjectPackage> packages;
		try {
			packages = getSqlSession().selectList("ProjectPackageMapper.queryAppPackageById", id);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if(packages.size() > 0){
			return packages.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int queryProjectPackageByVersionNo(String versionNo) {
		
		List<ProjectPackage> packages;
		try {
			packages = getSqlSession().selectList("ProjectPackageMapper.queryAppPackageByVersionNo", versionNo);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if(packages.size() > 0){
			return packages.get(0).getPackageId();
		}else{
			return 0;
		}
	}

	
}
