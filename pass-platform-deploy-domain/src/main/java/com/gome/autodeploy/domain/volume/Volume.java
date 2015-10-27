package com.gome.autodeploy.domain.volume;

import java.io.Serializable;

public class Volume implements Serializable{
	
	private static final long serialVersionUID = 1574353462031679744L;
	
	private Integer id;
	private String volumeName;
	private Integer volumeSize;
	private Integer objectes;
	private Integer volumeOrder;
	private Integer objectsSize;
	private String blockNamePrefix;
	private Integer volumeFormat;
	private Integer isDel;
	private Integer isUsed;
	private Integer userId;
	private Integer appId;
	private Integer readOnlyFlag;
	private String customPath;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVolumeName() {
		return volumeName;
	}
	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}
	public Integer getVolumeSize() {
		return volumeSize;
	}
	public void setVolumeSize(Integer volumeSize) {
		this.volumeSize = volumeSize;
	}
	public Integer getObjectes() {
		return objectes;
	}
	public void setObjectes(Integer objectes) {
		this.objectes = objectes;
	}
	public Integer getVolumeOrder() {
		return volumeOrder;
	}
	public void setVolumeOrder(Integer volumeOrder) {
		this.volumeOrder = volumeOrder;
	}
	public Integer getObjectsSize() {
		return objectsSize;
	}
	public void setObjectsSize(Integer objectsSize) {
		this.objectsSize = objectsSize;
	}
	public String getBlockNamePrefix() {
		return blockNamePrefix;
	}
	public void setBlockNamePrefix(String blockNamePrefix) {
		this.blockNamePrefix = blockNamePrefix;
	}
	public Integer getVolumeFormat() {
		return volumeFormat;
	}
	public void setVolumeFormat(Integer volumeFormat) {
		this.volumeFormat = volumeFormat;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public Integer getReadOnlyFlag() {
		return readOnlyFlag;
	}
	public void setReadOnlyFlag(Integer readOnlyFlag) {
		this.readOnlyFlag = readOnlyFlag;
	}
	public String getCustomPath() {
		return customPath;
	}
	public void setCustomPath(String customPath) {
		this.customPath = customPath;
	}
	
	
	
}
