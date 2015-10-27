/**
 * 
 */
package com.gome.autodeploy.web.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.autodeploy.common.DictionaryType;
import com.gome.autodeploy.common.utils.page.PageParam;
import com.gome.autodeploy.domain.app.Application;
import com.gome.autodeploy.service.app.ApplicationService;
import com.gome.autodeploy.web.BaseController;

/**
 * @author bailu-ds
 *
 */
@Controller
@RequestMapping(value="/app")
public class ApplicationController extends BaseController {
	
	@Resource
	private ApplicationService applicationService;

	@RequestMapping(value="/to/new", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toApp() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appTypes", getDictionarys(DictionaryType.APPTYPE.getValue()));
		modelAndView.addObject("deployTypes", getDictionarys(DictionaryType.DEPLOYTYPE.getValue()));
		modelAndView.addObject("appLevels", getDictionarys(DictionaryType.APPTLEVEL.getValue()));
		modelAndView.addObject("compileTypes", getDictionarys(DictionaryType.COMPILETYPE.getValue()));
		modelAndView.addObject("domainTypes", getDictionarys(DictionaryType.DOMAINTYPE.getValue()));
		modelAndView.setViewName("/app/app_new.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/new", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addApp(Application app) {
		app.setDeployPath("/export/App/"+app.getDomain());
		app.setSystemChief(app.getAppChief());
		app.setStatus(1);
		applicationService.addApplication(app, null, null);
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView list(PageParam pageParam) {
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
		modelAndView.addObject("pagination",applicationService.queryApplicationPage(map, pageNo, 50));
		modelAndView.addObject("pageParam", pageParam);
        modelAndView.setViewName("/app/app_list.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/approval/list", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView approvalList(PageParam pageParam) {
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
		modelAndView.addObject("pagination", applicationService.queryApplicationPage(map, pageNo, 50));
		modelAndView.addObject("pageParam", pageParam);
        modelAndView.setViewName("/app/app_approval.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/approval/{userId}/{appId}", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public boolean approval(@PathVariable Integer userId, @PathVariable Integer appId, Integer flag, String reason) {
		if (null == flag) {
			return false;
		}
		
		if (1 == flag) {
			return applicationService.though(appId);
		} else {
			return applicationService.notThough(appId, reason);
		}
	}
	
	@RequestMapping(value="/view/{appId}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView view(@PathVariable String appId) {
		ModelAndView modelAndView = new ModelAndView();
		Application app = null;
		if (StringUtils.isNotEmpty(appId)) {
			app = applicationService.getApplication(Integer.valueOf(appId.trim()));
			if (null != app) {
				modelAndView.addObject("domainType", getDictionary(null, app.getDomainType().toString(), 1008).getKey());
				modelAndView.addObject("compileType", getDictionary(null, app.getCompileType().toString(), 1007).getKey());
				modelAndView.addObject("deployType", getDictionary(null, app.getDeployType().toString(), 1001).getKey());
				modelAndView.addObject("appLevel", getDictionary(null, app.getAppLevel().toString(), 1006).getKey());
				modelAndView.addObject("appType", getDictionary(null, app.getAppType().toString(), 1000).getKey());
				modelAndView.addObject("members", applicationService.queryApplicationMember(Integer.valueOf(appId.trim())));
			}
		}
		modelAndView.addObject("app", app);
		modelAndView.setViewName("/app/app_view.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/get/{appId}", method={RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getApp(@PathVariable Integer appId) {
		try {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> listIds = new ArrayList<Integer>();
		listIds.add(appId);
		map.put("listIds", listIds);
		List<Application> list = applicationService.queryApplication(map);
		if (list.size() > 0) {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(list.get(0));
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//	public static void main(String[] args) {
//		Application application = new Application();
//		application.setId(1);
//		List<Application> list = new ArrayList<Application>();
//		list.add(application);
//		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
//		try {
//			String s = mapper.writeValueAsString(list);
//			System.out.println(s);
//			Application[] list2 = mapper.readValue(s, Application[].class);
//			for (Application a : list2) {  
//	             System.out.println(a.getId());
//	              
//	        }  
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//			Calendar cl = Calendar.getInstance(); 
////			cl.setTime(sdf.parse("2012-12-31"));
//			System.out.println(cl.WEEK_OF_YEAR);
//			int week = cl.get(Calendar.WEEK_OF_YEAR); 
//			System.out.println(week); 
//			cl.add(Calendar.DAY_OF_MONTH, -7);
//			int year = cl.get(Calendar.YEAR);
//			if(week<cl.get(Calendar.WEEK_OF_YEAR)){
//				year+=1;
//			}
////			System.out.println(year+"年第"+week+"周" + "("++")"); 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 

//	}
	
	
}
