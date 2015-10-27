/**
 * 
 */
package com.gome.autodeploy.dao.online.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.online.OnlineDao;
import com.gome.autodeploy.domain.online.OnlineTask;
import com.gome.autodeploy.domain.online.OnlineTaskPackage;
import com.gome.autodeploy.domain.online.OnlineTaskResume;

/**
 * @author bailu-ds
 *
 */
public class OnlineDaoImpl extends BaseDao implements OnlineDao {
	
	@Override
	public Integer addOnlineTask(OnlineTask onlineTask) {
		return getSqlSession().insert(getOnlineTaskSpaceName() + "addOnlineTask", onlineTask);
	}

	@Override
	public Integer addOnlineTaskPackage(OnlineTaskPackage taskPackage) {
		return getSqlSession().insert(getOnlineTaskPackageSpaceName() + "addOnlineTaskPackage", taskPackage);
	}

	@Override
	public Integer addOnlineTaskResume(OnlineTaskResume taskResume) {
		return getSqlSession().insert(getOnlineTaskResumeSpaceName() + "addOnlineTaskResume", taskResume);
	}

	@Override
	public Integer queryOnlineTaskCount(Map<String, Object> map) {
		Object obj = getSqlSession().selectOne(getOnlineTaskSpaceName() + "queryOnlineTaskCount", map);
		if (null != obj) {
			return (Integer) obj;
		}
		return 0;
	}

	@Override
	public List<OnlineTask> queryOnlineTask(Map<String, Object> map) {
		return getSqlSession().selectList(getOnlineTaskSpaceName() + "queryOnlineTask", map);
	}

	@Override
	public Integer queryOnlineTaskPackageCount(Map<String, Object> map) {
		Object obj = getSqlSession().selectOne(getOnlineTaskPackageSpaceName() + "queryOnlineTaskPackageCount", map);
		if (null != obj) {
			return (Integer) obj;
		}
		return 0;
	}

	@Override
	public List<OnlineTaskPackage> queryOnlineTaskPackage(Map<String, Object> map) {
		return getSqlSession().selectList(getOnlineTaskPackageSpaceName() + "queryOnlineTaskPackage", map);
	}

}
