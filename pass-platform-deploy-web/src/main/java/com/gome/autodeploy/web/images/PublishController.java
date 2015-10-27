/**
 * 
 */
package com.gome.autodeploy.web.images;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.autodeploy.common.KuberUtil;
import com.gome.autodeploy.service.project.ProjectPackageService;
import com.gome.autodeploy.service.project.ProjectService;
import com.gome.autodeploy.web.BaseController;
import com.gome.passplatform.kubernetes.exceptions.KubernetesClientException;
import com.gome.passplatform.kubernetes.interfaces.KubernetesAPIClientInterface;

/**
 * @author bailu-ds
 *
 */
@Controller
@RequestMapping(value="/publish")
public class PublishController extends BaseController {
	
	@Resource
	private ProjectService projectService;
	
	@Resource
	private ProjectPackageService projectPackageService;
	
	@Resource
	private KubernetesAPIClientInterface kubernetesApiClient;

	@RequestMapping(value="/{projectId}/{packageId}", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String publish(@PathVariable String projectId, @PathVariable String packageId) {
		if (StringUtils.isEmpty(packageId) || StringUtils.isEmpty(projectId)) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("packageId", packageId);
		map.put("createUser", getUser().getUserName());
//		List<ProjectPackage> list = projectPackageService.queryProjectPackage(map);
//		if (list.size() == 0) {
//			return null;
//		}
		
//		Project project = new Project();
//		project.setProjectId(Integer.valueOf(projectId));
//		List<Project> list2 = projectService.queryProject(project);
//		if (list2.size() == 0) {
//			return null;
//		}
		
		String s = "";
		
		try {
//			kubernetesApiClient.createReplicationController("namespaces/gomeyun/", KuberUtil.createReplicationController("cctv6", "namespaces/gomeyun/", "httpd", null, "cctv6", "500m", "64Mi", 3,8080));
			kubernetesApiClient.createService("namespaces/gomeyun/", KuberUtil.createService("cctv6", "cctv6", 80,-1, "cctv6"));
		} catch (KubernetesClientException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	
	
	

	
}
