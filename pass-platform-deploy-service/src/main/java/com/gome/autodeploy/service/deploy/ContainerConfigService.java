package com.gome.autodeploy.service.deploy;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.deploy.ContainerConfig;

public interface ContainerConfigService {
    
	public boolean addContainerConfig(ContainerConfig containerConfig);
	public boolean removeContainerConfig(Integer id);
	public List<ContainerConfig> queryContainerConfig(Map<String, Object> map);
	
	public ContainerConfig getContainerConfig(Integer id);
	public boolean modifyContainerConfig(ContainerConfig containerConfig);
	
}
