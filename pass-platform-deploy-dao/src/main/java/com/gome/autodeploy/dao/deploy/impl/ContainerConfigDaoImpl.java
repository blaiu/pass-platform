package com.gome.autodeploy.dao.deploy.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.deploy.ContainerConfigDao;
import com.gome.autodeploy.domain.deploy.ContainerConfig;
import com.gome.autodeploy.exception.PaasDBException;

public class ContainerConfigDaoImpl extends BaseDao implements ContainerConfigDao{


	@Override
	public List<ContainerConfig> queryContainerConfig(Map<String, Object> map) {
		try {
			return getSqlSession().selectList(getContainerConfigSpaceName() + "queryContainerConfig", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean addContainerConfig(ContainerConfig containerConfig) {
		Integer v=0;
		try {
			v = getSqlSession().insert(getContainerConfigSpaceName() + "addContainerConfig", containerConfig);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		}
		return false;
	}
	

	@Override
	public boolean modifyContainerConfig(ContainerConfig containerConfig) {
		int flag=0;
		try {
			flag = getSqlSession().update(getContainerConfigSpaceName() + "modifyContainerConfig",containerConfig);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeContainerConfig(Integer id) {
		int flag=0;
		try {
			flag = getSqlSession().update(getContainerConfigSpaceName() + "deleteContainerConfig", id);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ContainerConfig getContainerConfig(Integer id) {
		try {
			return getSqlSession().selectOne(getContainerConfigSpaceName() + "queryContainerConfigById", id);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}


}
