package com.gome.autodeploy.service.volume.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gome.autodeploy.dao.volume.VolumeDao;
import com.gome.autodeploy.domain.volume.Volume;
import com.gome.autodeploy.service.volume.VolumeService;

public class VolumeServiceImpl implements VolumeService{
	
	@Resource
	private VolumeDao volumeDao;

	@Override
	public boolean addVolume(Volume volume) {
		return volumeDao.addVolume(volume);
	}

	@Override
	public boolean removeVolume(Integer id) {
		return volumeDao.removeVolume(id);
	}

	@Override
	public List<Volume> queryVolume(Map<String, Object> map) {
		return volumeDao.queryVolume(map);
	}

	@Override
	public Volume getVolume(Integer id) {
		return volumeDao.getVolume(id);
	}

	@Override
	public boolean modifyVolume(Volume volume) {
		return volumeDao.modifyVolume(volume);
	}

	@Override
	public List<Volume> queryVolumeByUserId(Integer userId) {
		return volumeDao.queryVolumeByUserId(userId);
	}

	@Override
	public List<Volume> queryVolumeNotUsed(Integer userId) {
		return volumeDao.queryVolumeNotUsed(userId);
	}

	@Override
	public boolean mountVolume(Volume volume) {
		return volumeDao.mountVolume(volume);
	}

	@Override
	public Volume queryVolumeByAppId(Integer appId) {
		return volumeDao.queryVolumeByAppId(appId);
	}

	@Override
	public boolean deleteAppVolume(Integer appId) {
		return volumeDao.deleteAppVolume(appId);
	}

	@Override
	public boolean updateVolumeDelFlag(Volume volume) {
		return volumeDao.updateVolumeDelFlag(volume);
	}

	@Override
	public boolean formatVolume(Integer id) {
		return volumeDao.formatVolume(id);
	}

	@Override
	public boolean checkExistVolumeName(Volume volume) {
		if(volumeDao.getVolumeByName(volume) != null){
			 return true;
		 }else{
			 return false;
		 }
	}

}
