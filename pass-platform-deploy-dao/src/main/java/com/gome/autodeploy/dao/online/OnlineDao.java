/**
 * 
 */
package com.gome.autodeploy.dao.online;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.online.OnlineTask;
import com.gome.autodeploy.domain.online.OnlineTaskPackage;
import com.gome.autodeploy.domain.online.OnlineTaskResume;

/**
 * @author bailu-ds
 *
 */
public interface OnlineDao {

	public Integer addOnlineTask(OnlineTask onlineTask);
	
	public Integer addOnlineTaskPackage(OnlineTaskPackage taskPackage);
	
	public Integer addOnlineTaskResume(OnlineTaskResume taskResume);
	
	public Integer queryOnlineTaskCount(Map<String, Object> map);
	
	public List<OnlineTask> queryOnlineTask(Map<String, Object> map);
	
	/**
	 * 查询发布包总数
	 * @param map
	 * @return
	 */
	public Integer queryOnlineTaskPackageCount (Map<String, Object> map);
	
	/**
	 * 查询发布包
	 * @param map
	 * @return
	 */
	public List<OnlineTaskPackage> queryOnlineTaskPackage(Map<String, Object> map);
	
}
