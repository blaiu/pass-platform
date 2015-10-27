/**
 * 
 */
package com.gome.autodeploy.service.project;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.project.Project;

/**
 * @author bailu-ds
 *
 */
public interface ProjectService {

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
	
	public Integer queryProjectCount(Map<String, Object> map);
	
	public boolean removeProject(Integer projId);
	public List<Project> queryProjectByUser(Integer userId);
	
	/** 
	* @Title: checkExist 
	* @Description: 校验是否存在相同的项目   
	* 	用于新增项目时，校验是否存在同名或同简拼的项目存在
	* @param project
	* @return boolean   
	* @throws 
	*/
	public boolean checkExistProjectNameOrSpell(Project project);
	
}
