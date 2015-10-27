/**
 * 
 */
package com.gome.autodeploy.dao.deploy.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.deploy.DeployDao;
import com.gome.autodeploy.domain.deploy.DeployResource;
import com.gome.autodeploy.exception.PaasDBException;

/**
 * @author bailu-ds
 *
 */
public class DeployDaoImpl extends BaseDao implements DeployDao {

	@Override
	public List<DeployResource> queryDeployResource(Map<String, Object> map) {
		try {
			return getSqlSession().selectList(getDeployResourceSpaceName() + "queryDeployResource", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	
}
