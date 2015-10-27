package com.gome.autodeploy.dao.deploy.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.deploy.TestDeployDao;
import com.gome.autodeploy.domain.deploy.TestDeployResources;
import com.gome.autodeploy.exception.PaasDBException;

public class TestDeployDaoImpl extends BaseDao implements TestDeployDao{
	
	@Override
	public boolean addTestDeployResources(List<TestDeployResources> resources) {
		Integer v=0;
		try {
			v = getSqlSession().insert(getTestDeployResourcesSpaceName() + "addTestDeployResources", resources);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeTestDeployResources(Map<String, Object> map) {
		int result=0;
		try {
			result = getSqlSession().delete(getTestDeployResourcesSpaceName() + "removeTestDeployResources", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TestDeployResources> queryTestDeployResources(Map<String, Object> map) {
		try {
			return getSqlSession().selectList(getTestDeployResourcesSpaceName() + "queryTestDeployResources", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean modifyTestDeployResources(TestDeployResources testDeployResources) {
		int flag=0;
		try {
			flag = getSqlSession().update(getTestDeployResourcesSpaceName() + "modifyTestDeployResources", testDeployResources);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}


}
