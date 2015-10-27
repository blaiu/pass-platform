/**
 * 
 */
package com.gome.autodeploy.service.deploy;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.deploy.DeployResource;

/**
 * @author bailu-ds
 *
 */
public interface DeployService {

	/**
	 * 查询部署资源
	 * @param map
	 * @return
	 */
	public List<DeployResource> queryDeployResource(Map<String, Object> map);
	
}
