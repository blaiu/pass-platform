package com.gome.autodeploy.dao.volume.impl;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.dao.common.BaseDao;
import com.gome.autodeploy.dao.volume.VolumeDao;
import com.gome.autodeploy.domain.volume.Volume;
import com.gome.autodeploy.exception.PaasDBException;

public class VolumeDaoImpl extends BaseDao implements VolumeDao{

	@Override
	public boolean addVolume(Volume volume) {
		
		Integer v=0;
		try {
			v = getSqlSession().insert(getVolumeSpaceName() + "addVolume", volume);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (v > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean removeVolume(Integer id) {
		int flag=0;
		try {
			flag = getSqlSession().update(getVolumeSpaceName() + "deleteVolume", id);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Volume> queryVolume(Map<String, Object> map) {
		try {
			return getSqlSession().selectList(getVolumeSpaceName() + "queryVolume", map);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public Volume getVolume(Integer id) {
		try {
			return getSqlSession().selectOne(getVolumeSpaceName() + "queryVolumeById", id);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean modifyVolume(Volume volume) {
		int flag=0;
		try {
			flag = getSqlSession().update(getVolumeSpaceName() + "modifyVolume",volume);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Volume> queryVolumeByUserId(Integer userId) {
		try {
			return getSqlSession().selectList(getVolumeSpaceName() + "queryVolumeByUserId", userId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public List<Volume> queryVolumeNotUsed(Integer userId) {
		try {
			return getSqlSession().selectList(getVolumeSpaceName() + "queryVolumeNotUsed", userId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean mountVolume(Volume volume) {
		int flag=0;
		try {
			flag = getSqlSession().update(getVolumeSpaceName() + "mountVolume",volume);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Volume queryVolumeByAppId(Integer appId) {
		try {
			return getSqlSession().selectOne(getVolumeSpaceName() + "queryVolumeByAppId", appId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
	}

	@Override
	public boolean deleteAppVolume(Integer appId) {
		int flag=0;
		try {
			flag = getSqlSession().update(getVolumeSpaceName() + "deleteAppVolume", appId);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateVolumeDelFlag(Volume volume) {
		int flag=0;
		try {
			flag = getSqlSession().update(getVolumeSpaceName() + "updateVolumeDelFlag", volume);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean formatVolume(Integer id) {
		int flag=0;
		try {
			flag = getSqlSession().update(getVolumeSpaceName() + "formatVolume", id);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if (flag > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Volume getVolumeByName(Volume volume) {
		List<Volume> list;
		try {
			list = getSqlSession().selectList(getVolumeSpaceName() + "queryVolumeName", volume);
		} catch (Exception e) {
			throw new PaasDBException(e.getMessage());
		}
		if(list != null && list.size() > 0 ){
			return list.get(0);
		}else{
			return null;
		}
	}

}
