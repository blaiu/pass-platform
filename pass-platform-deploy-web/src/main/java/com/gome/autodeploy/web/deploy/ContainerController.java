package com.gome.autodeploy.web.deploy;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.domain.deploy.ContainerConfig;
import com.gome.autodeploy.service.deploy.ContainerConfigService;
import com.gome.autodeploy.web.BaseController;

@Controller
@RequestMapping(value = "/deploy")
public class ContainerController extends BaseController{
     
	@Resource
	private ContainerConfigService containerConfigService;
	
	@RequestMapping(value="/config", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addContainerConfig(ContainerConfig containerConfig) {
		containerConfigService.addContainerConfig(containerConfig);
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/deploy/list");
		return modelAndView;
	}
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView list( ) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		modelAndView.addObject("list",containerConfigService.queryContainerConfig(map));
        modelAndView.setViewName("/deploy/config-list.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView removeContainerConfig(@PathVariable Integer id) {
		containerConfigService.removeContainerConfig(id);
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/deploy/list");
		return modelAndView;
	}
	
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewContainerConfig(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("view",containerConfigService.getContainerConfig(id));
		modelAndView.setViewName("/deploy/config-detail.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modifyContainerConfig(ContainerConfig containerConfig) {
		containerConfigService.modifyContainerConfig(containerConfig);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/deploy/list");
		return modelAndView;
	}
}
