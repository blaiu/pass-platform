/**
 * 
 */
package com.gome.autodeploy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.gome.autodeploy.domain.online.OnlineTask;
import com.gome.autodeploy.domain.online.OnlineTaskResume;
import com.gome.autodeploy.service.online.OnlineService;

/**
 * @author bailu-ds
 *
 */
public class OnlineTest extends BaseSpringTestCase {

	@Resource
	private OnlineService onlineService;
	
	@Test
	public void queryApplicationTest () {
		
		OnlineTask onlineTask = new OnlineTask();
		onlineTask.setAppId(1);
		onlineTask.setTitle("自动部署二期上线");
		onlineTask.setIsNew(1);
		onlineTask.setIsChangeSql(1);
		onlineTask.setType(1);
		onlineTask.setStatus(0);
		onlineTask.setDeployTime("09-17");
		onlineTask.setDeployDate("18");
		onlineTask.setCreateUser("admin");
		onlineTask.setCreateTime(new Date());
		onlineTask.setUpdateUser("admin");
		onlineTask.setUpdateTime(new Date());
		
		//Integer taskId = onlineService.addOnlineTask(onlineTask);
		OnlineTaskResume taskResume = new OnlineTaskResume();
		taskResume.setCreateTime(new Date());
		taskResume.setCreateUser("admin");
		taskResume.setStatus(1);
		
//		Integer taskId = onlineService.addOnlineTaskAndResume(onlineTask, taskResume);
//		System.out.println(taskId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCode", "admin");
		List<Integer> listStatus = new ArrayList<Integer>();
		listStatus.add(0);
		listStatus.add(1);
		map.put("listStatus", listStatus);
		map.put("startRow", 0);
		map.put("pageSize", 50);
		onlineService.queryOnlineTask(map);
	}
	
}
