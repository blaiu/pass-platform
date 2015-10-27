/**
 * 
 */
package com.gome.autodeploy.web.deploy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.common.utils.page.PageParam;
import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.domain.app.ApplicationPackageVersion;
import com.gome.autodeploy.domain.app.ApplicationResource;
import com.gome.autodeploy.domain.deploy.DeployResource;
import com.gome.autodeploy.domain.online.OnlineTask;
import com.gome.autodeploy.service.app.ApplicationService;
import com.gome.autodeploy.service.deploy.DeployService;
import com.gome.autodeploy.service.online.OnlineService;
import com.gome.autodeploy.web.BaseController;


/**
 * @author bailu-ds
 *
 */
@Controller
@RequestMapping(value = "/deploy")
public class DeployController extends BaseController {

	@Resource
	private DeployService deployService;
	
	@Resource
	private OnlineService onlineService;
	
	@Resource
	private ApplicationService applicationService;
	
	/**
	 * 我的发包记录
	 * @return
	 */
	@RequestMapping(value = "/package", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView myPackage (PageParam pageParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("userCode", getUser().getUserName());
		map.put("path", "/deploy/package");
		int pageNo = 1;
		if (null != pageParam) {
			pageNo = (null != pageParam.getPageNo()) ? pageParam.getPageNo() : 1;
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagination", onlineService.queryOnlineTaskPackagePage(map, pageNo, 50));
		modelAndView.addObject("pageParam", pageParam);
        modelAndView.setViewName("/deploy/packet-record.jsp");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/step/1")
	public ModelAndView step1(String taskId) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", Integer.valueOf(taskId));
		map.put("userCode", getUser().getUserName());
		
		List<OnlineTask> list = onlineService.queryOnlineTask(map);
		if (list.size() > 0) {
			List<Integer> listIds = new ArrayList<Integer>();
			listIds.add(list.get(0).getAppId());
			map.put("listIds", listIds);
			List<Application> listApp = applicationService.queryApplication(map);
			modelAndView.addObject("app", listApp.get(0));
		}
		modelAndView.addObject("task", list.get(0));
        modelAndView.setViewName("/deploy/operation.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/step/2")
	public ModelAndView step2(String appId, String taskId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", Integer.valueOf(taskId));
		map.put("userCode", getUser().getUserName());
		List<DeployResource> listDeployRes = deployService.queryDeployResource(map);
		
		map.put("appId", Integer.valueOf(appId));
		List<ApplicationResource> listAppRes = applicationService.queryApplicationResource(map);
		Map<String, ApplicationResource> appResMap = new HashMap<String, ApplicationResource>();
		for (ApplicationResource ar : listAppRes) {
			appResMap.put(ar.getIp(), ar);
		}
		
		for (DeployResource dr : listDeployRes) {
			appResMap.remove(dr.getDeployIp());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listDeployRes", listDeployRes);
		modelAndView.addObject("listAppRes", new ArrayList<ApplicationResource>(appResMap.values()));
        modelAndView.setViewName("/deploy/step2.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/step/3")
	public ModelAndView step3(String appId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", Integer.valueOf(appId));
		List<ApplicationPackageVersion> list = applicationService.queryApplicationPackage(map);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listPackages", list);
        modelAndView.setViewName("/deploy/operation.jsp");
		return modelAndView;
	}
	
	
	
	
}