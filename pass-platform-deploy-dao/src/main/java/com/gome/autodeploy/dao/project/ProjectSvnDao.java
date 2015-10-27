/**
 * 
 */
package com.gome.autodeploy.dao.project;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.project.Project;
import com.gome.autodeploy.domain.project.ProjectSvn;

/**
 * @author bailu-ds
 *
 */
 
public interface ProjectSvnDao {

	/**
	 * 增加项目svn信息
	 * @param project
	 * @return
	 */
	public int addProjectSvn(ProjectSvn projectSvn);
	/**
	 * 修改项目Svn
	 * @param project
	 * @return
	 */
	public int modifyProjectSvn(ProjectSvn projectSvn);
	/**
	 * 获取项目Svn
	 * @param projectId
	 * @return
	 */
	public ProjectSvn getProjectSvn(Integer projectId);
 
}
