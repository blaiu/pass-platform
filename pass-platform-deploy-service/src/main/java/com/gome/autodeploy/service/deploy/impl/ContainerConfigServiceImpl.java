package com.gome.autodeploy.service.deploy.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.deploy.ContainerConfigDao;
import com.gome.autodeploy.domain.deploy.ContainerConfig;
import com.gome.autodeploy.service.deploy.ContainerConfigService;

public class ContainerConfigServiceImpl implements ContainerConfigService{

	@Resource
	private ContainerConfigDao containerConfigDao;


	@Override
	public List<ContainerConfig> queryContainerConfig(Map<String, Object> map) {
		return containerConfigDao.queryContainerConfig(map);
	}

	@Override
	public boolean addContainerConfig(ContainerConfig containerConfig) {
		return containerConfigDao.addContainerConfig(containerConfig);
	}


	@Override
	public boolean removeContainerConfig(Integer id) {
		return containerConfigDao.removeContainerConfig(id);
	}

	@Override
	public ContainerConfig getContainerConfig(Integer id) {
		return containerConfigDao.getContainerConfig(id);
	}

	@Override
	public boolean modifyContainerConfig(ContainerConfig containerConfig) {
		return containerConfigDao.modifyContainerConfig(containerConfig);
		
	}



}
