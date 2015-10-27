package com.gome.autodeploy.domain.app;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class App implements Serializable{
    
	private static final long serialVersionUID = 1574353462031679744L;
	
	private Integer id;
	private String appName;
	private Integer packageId;
	private Integer projectId;
	private String imageName;
	private Integer serverPort;
	private Integer hostPort;
	private String podLabel;
	private Integer appStatus;
	private String serverIp;
	private String hostIp;
	private String appSpell;
	private String projectSpell;
	private Integer configId;
	private Integer containerCount;
	private Integer isDel;
	private Integer expansionFlag;
	
	private Integer volumeId;
	private String customPath; 
	
	private Integer mountVolume;
	
	private String memo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getPodLabel() {
		return podLabel;
	}
	public void setPodLabel(String podLabel) {
		this.podLabel = podLabel;
	}
	public Integer getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	public Integer getHostPort() {
		return hostPort;
	}
	public void setHostPort(Integer hostPort) {
		this.hostPort = hostPort;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getAppSpell() {
		return appSpell;
	}
	public void setAppSpell(String appSpell) {
		this.appSpell = appSpell;
	}
	public String getProjectSpell() {
		return projectSpell;
	}
	public void setProjectSpell(String projectSpell) {
		this.projectSpell = projectSpell;
	}
	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public Integer getContainerCount() {
		return containerCount;
	}
	public void setContainerCount(Integer containerCount) {
		this.containerCount = containerCount;
	}
	
	
 
	public Integer getVolumeId() {
		return volumeId;
	}
	public void setVolumeId(Integer volumeId) {
		this.volumeId = volumeId;
	}
	
	public String getCustomPath() {
		return customPath;
	}
	public void setCustomPath(String customPath) {
		this.customPath = customPath;
	}
	
	public Integer getMountVolume() {
		return mountVolume;
	}
	public void setMountVolume(Integer mountVolume) {
		this.mountVolume = mountVolume;
	}
	
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	@Override
	public String toString() {
		return "App [id=" + id + ", appName=" + appName + ", packageId="
				+ packageId + ", projectId=" + projectId + ", imageName="
				+ imageName + ", serverPort=" + serverPort + ", hostPort="
				+ hostPort + ", podLabel=" + podLabel + ", appStatus="
				+ appStatus + ", serverIp=" + serverIp + ", hostIp=" + hostIp
				+ ", appSpell=" + appSpell + ", projectSpell=" + projectSpell
				+ ", configId=" + configId + ", containerCount="
				+ containerCount + ", isDel=" + isDel + ", expansionFlag="
				+ expansionFlag + "]";
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public boolean checkNull(){
		if(StringUtils.isNotEmpty(this.serverIp) &&  this.serverPort != null && this.serverPort != 0 && StringUtils.isNotEmpty(this.hostIp) && this.hostPort != null && this.hostPort != 0 ){
			return true;
		}else{
			return false;
		}
	}
	public Integer getExpansionFlag() {
		return expansionFlag;
	}
	public void setExpansionFlag(Integer expansionFlag) {
		this.expansionFlag = expansionFlag;
	}
	
}
