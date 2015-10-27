/**
 * 
 */
package com.gome.autodeploy.dao.project;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.project.ProjectPackage;

/**
 * @author bailu-ds
 *
 */
public interface ProjectPackageDao {

	public boolean addProjectPackage(ProjectPackage projectPackage);
	
	public List<ProjectPackage> queryProjectPackage(Map<String, Object> map);
	
	public boolean removeProjectPackage(Map<String, Object> map);
	
	public boolean updateProjectPackageStatus(Map<String, Object> map);
	
	public boolean updateProjectPackage(ProjectPackage projectPackage);
	
	public List<ProjectPackage> queryImage(Map<String, Object> map);
	
	public ProjectPackage queryProjectPackageById(Integer id);
	
	public int queryProjectPackageByVersionNo(String versionNo);
	
}
