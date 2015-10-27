package com.gome.autodeploy.dao.volume;

import java.util.List;
import java.util.Map;

import com.gome.autodeploy.domain.volume.Volume;

public interface VolumeDao {
    
	public boolean addVolume(Volume volume);
	public boolean removeVolume(Integer id);
	public List<Volume> queryVolume(Map<String, Object> map);
	
	public Volume getVolume(Integer id);
	public boolean modifyVolume(Volume volume);
	
	public List<Volume> queryVolumeByUserId(Integer userId);
	
	public List<Volume> queryVolumeNotUsed(Integer userId);
	public Volume queryVolumeByAppId(Integer appId);
	
	public boolean mountVolume(Volume volume);
	
	public boolean deleteAppVolume(Integer appId);
	
	public boolean updateVolumeDelFlag(Volume volume);
	
	public boolean formatVolume(Integer id);
	
	/**
	 * 检测是否有相同共享磁盘名称存在
	 * @param volume
	 * @return
	 */
	public Volume getVolumeByName(Volume volume);
	
}
