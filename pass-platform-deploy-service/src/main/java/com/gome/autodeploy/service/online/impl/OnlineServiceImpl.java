/**
 * 
 */
package com.gome.autodeploy.service.online.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.common.utils.page.Pagination;
import com.gome.autodeploy.dao.online.OnlineDao;
import com.gome.autodeploy.domain.online.OnlineTask;
import com.gome.autodeploy.domain.online.OnlineTaskPackage;
import com.gome.autodeploy.domain.online.OnlineTaskResume;
import com.gome.autodeploy.service.online.OnlineService;

/**
 * @author bailu-ds
 *
 */
public class OnlineServiceImpl implements OnlineService {

	@Resource
	private OnlineDao onlineDao;

	@Override
	public Integer addOnlineTask(OnlineTask onlineTask) {
		return onlineDao.addOnlineTask(onlineTask);
	}

	@Override
	public Integer addOnlineTaskPackage(OnlineTaskPackage taskPackage) {
		return onlineDao.addOnlineTaskPackage(taskPackage);
	}
	
	@Override
	public Integer addOnlineTaskResume(OnlineTaskResume taskResume) {
		return onlineDao.addOnlineTaskResume(taskResume);
	}

	@Override
	public Integer addOnlineTaskAndResume(OnlineTask onlineTask, OnlineTaskResume taskResume) {
		Integer taskId = onlineDao.addOnlineTask(onlineTask);
		taskResume.setTaskId(onlineTask.getId());
		taskResume.setStatus(onlineTask.getStatus());
		onlineDao.addOnlineTaskResume(taskResume);
		return taskId;
	}

	@Override
	public Integer queryOnlineTaskCount(Map<String, Object> map) {
		return onlineDao.queryOnlineTaskCount(map);
	}


	@Override
	public List<OnlineTask> queryOnlineTask(Map<String, Object> map) {
		return onlineDao.queryOnlineTask(map);
	}

	@Override
	public Pagination queryApplicationPage(Map<String, Object> map, int pageNo, int pageSize) {
		int total = queryOnlineTaskCount(map);
		int endIndex = pageNo * pageSize;
		if (endIndex > total) {
			endIndex = total;
		}
		
		map.put("startRow", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("limit", "limit");
		List<OnlineTask> list = queryOnlineTask(map);
		Pagination p = new Pagination(pageNo, pageSize, total, list);
		p.setUrl(map.get("path").toString());
		return p;
	}

	@Override
	public Integer queryOnlineTaskPackageCount(Map<String, Object> map) {
		return onlineDao.queryOnlineTaskPackageCount(map);
	}

	@Override
	public Pagination queryOnlineTaskPackagePage(Map<String, Object> map, int pageNo, int pageSize) {
		int total = queryOnlineTaskCount(map);
		int endIndex = pageNo * pageSize;
		if (endIndex > total) {
			endIndex = total;
		}
		
		map.put("startRow", (pageNo - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("limit", "limit");
		List<OnlineTaskPackage> list = queryOnlineTaskPackage(map);
		Pagination p = new Pagination(pageNo, pageSize, total, list);
		p.setUrl(map.get("path").toString());
		return p;
	}

	@Override
	public List<OnlineTaskPackage> queryOnlineTaskPackage(Map<String, Object> map) {
		return onlineDao.queryOnlineTaskPackage(map);
	}
	
}
