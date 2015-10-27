package com.gome.autodeploy.dao.deploy;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.deploy.TestDeployResources;

public interface TestDeployDao {
	
	public boolean addTestDeployResources(List<TestDeployResources> resources);
	public boolean removeTestDeployResources(Map<String, Object> map);
	public boolean modifyTestDeployResources(TestDeployResources testDeployResources);
	public List<TestDeployResources> queryTestDeployResources(Map<String, Object> map);

}
