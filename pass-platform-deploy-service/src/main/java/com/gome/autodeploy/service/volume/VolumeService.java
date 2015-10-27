package com.gome.autodeploy.service.volume;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.volume.Volume;

public interface VolumeService {
    
	public boolean addVolume(Volume volume);
	public boolean removeVolume(Integer id);
	public List<Volume> queryVolume(Map<String, Object> map);
	
	public Volume getVolume(Integer id);
	public boolean modifyVolume(Volume volume);
	
	public List<Volume> queryVolumeByUserId(Integer userId);
	public Volume queryVolumeByAppId(Integer appId);
	
	public List<Volume> queryVolumeNotUsed(Integer userId);
	public boolean mountVolume(Volume volume);
	
	public boolean deleteAppVolume(Integer appId);
	
	public boolean updateVolumeDelFlag(Volume volume);
	
	public boolean formatVolume(Integer id);
	
	public boolean checkExistVolumeName(Volume volume);
	
}
