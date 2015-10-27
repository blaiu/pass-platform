/**
 * 
 */
package com.gome.autodeploy.dao.project;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.project.Project;

/**
 * @author bailu-ds
 *
 */
 
public interface ProjectDao {

	/**
	 * 增加项目
	 * @param project
	 * @return
	 */
	public int addProject(Project project);
	/**
	 * 修改项目
	 * @param project
	 * @return
	 */
	public int modifyProject(Project project);
	/**
	 * 获取项目
	 * @param projectId
	 * @return
	 */
	public Project getProject(Integer projectId);
	/**
	 * 获取项目列表
	 * @param project
	 * @return
	 */
	public List<Project> queryProject(Project project);
	
	public List<Project> queryProjectByUser(Integer userId);
	public Integer queryProjectCount(Map<String, Object> map);
	
	/**
	 * 删除项目
	 * */
	public boolean removeProject(Integer projId);
	
	 
	
	/** 
	* @Title: getProjectNameOrSpell 
	* @Description: 检查是否存在相同项目名称或简拼的项目存在
	* @param project
	* @return Project   
	* @throws 
	*/
	public Project getProjectByNameOrSpell(Project project);
	
}
