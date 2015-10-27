/**
 * 
 */
package com.gome.autodeploy.service.deploy.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.deploy.DeployDao;
import com.gome.autodeploy.domain.deploy.DeployResource;
import com.gome.autodeploy.service.deploy.DeployService;

/**
 * @author bailu-ds
 *
 */
public class DeployServiceImpl implements DeployService {

	@Resource
	private DeployDao deployDao;

	@Override
	public List<DeployResource> queryDeployResource(Map<String, Object> map) {
		return deployDao.queryDeployResource(map);
	}
	
}
