/**
 * 
 */
package com.gome.autodeploy.service.online;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.common.utils.page.Pagination;
import com.gome.autodeploy.domain.online.OnlineTask;
import com.gome.autodeploy.domain.online.OnlineTaskPackage;
import com.gome.autodeploy.domain.online.OnlineTaskResume;

/**
 * @author bailu-ds
 *
 */
public interface OnlineService {

	public Integer addOnlineTask(OnlineTask onlineTask);
	
	public Integer addOnlineTaskResume(OnlineTaskResume taskResume);
	
	public Integer addOnlineTaskAndResume(OnlineTask onlineTask, OnlineTaskResume taskResume);
	
	public Integer addOnlineTaskPackage(OnlineTaskPackage taskPackage);
	
	public List<OnlineTask> queryOnlineTask(Map<String, Object> map);
	
	public Integer queryOnlineTaskCount(Map<String, Object> map);
	
	public Pagination queryApplicationPage(Map<String, Object> map, int pageNo, int pageSize);
	
	public List<OnlineTaskPackage> queryOnlineTaskPackage(Map<String, Object> map);
	
	public Integer queryOnlineTaskPackageCount(Map<String, Object> map);
	
	public Pagination queryOnlineTaskPackagePage(Map<String, Object> map, int pageNo, int pageSize);
	
	
}
