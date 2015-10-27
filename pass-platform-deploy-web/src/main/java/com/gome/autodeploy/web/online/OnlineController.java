/**
 * 
 */
package com.gome.autodeploy.web.online;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.common.CosUploadUtil;
import com.gome.autodeploy.common.DateUtil;
import com.gome.autodeploy.common.DictionaryType;
import com.gome.autodeploy.common.utils.page.PageParam;
import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.domain.auth.User;
import com.gome.autodeploy.domain.online.OnlineTask;
import com.gome.autodeploy.domain.online.OnlineTaskPackage;
import com.gome.autodeploy.domain.online.OnlineTaskResume;
import com.gome.autodeploy.service.app.ApplicationService;
import com.gome.autodeploy.service.online.OnlineService;
import com.gome.autodeploy.web.BaseController;


/**
 * @author bailu-ds
 *
 */
@Controller
@RequestMapping(value="/online")
public class OnlineController extends BaseController {

	@Resource
	private OnlineService onlineService;
	
	@Resource
	private ApplicationService applicationService;
	
	/**
	 * 待办任务
	 * @return
	 */
	@RequestMapping(value="/todo", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toDoTask() {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCode", getUser().getUserName());
		List<Integer> listStatus = new ArrayList<Integer>();
		listStatus.add(0);
		listStatus.add(1);
		map.put("listStatus", listStatus);
		List<OnlineTask> list = onlineService.queryOnlineTask(map);
		Map<String, Object> appIdMap = new HashMap<String, Object>();
		for (OnlineTask task : list) {
			appIdMap.put(task.getAppId()+"", task.getAppId());
		}
		Map<Integer, Application> appMap = new HashMap<Integer, Application>();
		if (appIdMap.size() > 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("listIds", new ArrayList<Object>(appIdMap.values()));
			List<Application> listApp = applicationService.queryApplication(paramMap);
			for (Application app : listApp) {
				appMap.put(app.getId(), app);
			}
		}
		for (OnlineTask task : list) {
			task.setApplication(appMap.get(task.getAppId()));
		}
		modelAndView.addObject("listTasks", list);
		modelAndView.setViewName("/online/to-do.jsp");
		return modelAndView;
	}
	
	/**
	 * 跳转至任务申请
	 * @return
	 */
	@RequestMapping(value="/to/task", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toAdd() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCode", getUser().getUserName());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appTypes", getDictionarys(DictionaryType.IS_NEWAPP.getValue()));
		modelAndView.addObject("sqlChanges", getDictionarys(DictionaryType.IS_SQLCHANGE.getValue()));
		modelAndView.addObject("onlineTypes", getDictionarys(DictionaryType.ONLINE_TYPE.getValue()));
		modelAndView.addObject("onlineTimes", getDictionarys(DictionaryType.ONLINE_TIME.getValue()));
		modelAndView.addObject("listApps", applicationService.queryApplicationByMember(map));
		modelAndView.addObject("listWeeks", DateUtil.getWeekList());
		modelAndView.setViewName("/online/task_apply.jsp");
		return modelAndView;
	} 
	
	/**
	 * 添加上线任务
	 * @param task
	 * @return
	 */
	@RequestMapping(value="/task/add", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addTask(OnlineTask task) {
		ModelAndView modelAndView = new ModelAndView();
		OnlineTaskResume taskResume = new OnlineTaskResume();
		try {
			task.setCreateUser(getUser().getUserName());
			task.setUpdateUser(getUser().getUserName());
			taskResume.setCreateUser(getUser().getUserName());
			onlineService.addOnlineTaskAndResume(task, taskResume);
		} catch(Exception ex) {
			ex.printStackTrace();
			modelAndView.setViewName("/online/task_apply.jsp");
		} 
		modelAndView.addObject("onlineTaskId", task.getId());
		modelAndView.setViewName("/online/task_package.jsp");
		return modelAndView;
	} 
	
	/**
	 * 上传包
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/package/upload", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView uploadPackage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			String packageType = request.getParameter("packageType");
			String taskId = request.getParameter("taskId");		//任务ID
			String domain = request.getParameter("domain");		//域名
			String file = request.getParameter("filePath");		//文件名
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
			User user = getUser();
			String packagePath = "/Upload/package/" + domain + "/" + taskId + "/";
			String packageName = user.getUserName() + "_" + format.format(new Date()) + "tar.gz";
			OnlineTaskPackage taskPackage = new OnlineTaskPackage();
			taskPackage.setTaskId(Integer.valueOf(taskId));
			taskPackage.setType(Integer.valueOf(packageType));
			taskPackage.setPackageName(packageName);
			taskPackage.setPackagePath(packagePath);
			
			onlineService.addOnlineTaskPackage(taskPackage);
			String srcPath = "/export/Upload/" + domain + "/" + user.getUserName() + "/";	// 存相对路径
			String descPath = "/export/Upload/package/" + domain + "/" + taskId + "/";
			if(!file.endsWith(".tar.gz")) {
				modelAndView.addObject("error", "只支持 .tar.gz后缀的文件");
				return modelAndView;
			}
			CosUploadUtil.upload(packageName, domain, user.getUserName(), srcPath, descPath, request);
		} catch(Exception ex) {
			modelAndView.setViewName("/upload.jsp");
		}
		
        return modelAndView;
	}
	
	/**
	 * 显示上线任务列表
	 */
	
	@RequestMapping(value="/tasklist", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView taskList(PageParam pageParam) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		int pageNo = 1;
		if (null != pageParam) {
			map.put("userCode", getUser().getUserName());
			map.put("status", pageParam.getStatus());
			map.put("path", "/list");
			map.put("limit", "limit");
			pageNo = (null != pageParam.getPageNo()) ? pageParam.getPageNo() : 1;
		}
		modelAndView.addObject("pagination",onlineService.queryApplicationPage(map, pageNo, 50));
		modelAndView.addObject("pageParam", pageParam);
        modelAndView.setViewName("/online/task-list.jsp");
		return modelAndView;
	}
	
	/**
	 * 显示上线审批列表
	 */
	@RequestMapping(value="/task/approval", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView taskApproval(PageParam pageParam) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		int pageNo = 1;
		if (null != pageParam) {
			map.put("userCode", getUser().getUserName());
			map.put("status", 0);
			map.put("path", "/list");
			map.put("limit", "limit");
			pageNo = (null != pageParam.getPageNo()) ? pageParam.getPageNo() : 1;
		}
		modelAndView.addObject("pagination", onlineService.queryApplicationPage(map, pageNo, 50));
		modelAndView.addObject("pageParam", pageParam);
        modelAndView.setViewName("/online/task-approval.jsp");
		return modelAndView;
	}
	
	
}
