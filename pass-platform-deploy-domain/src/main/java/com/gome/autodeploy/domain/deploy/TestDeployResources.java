package com.gome.autodeploy.domain.deploy;

import java.io.Serializable;


public class TestDeployResources implements Serializable {
   
	private static final long serialVersionUID = 1574353462031679744L;
	
	/** ID */
	private Integer id;
	/** 应用ID */
	private Integer appId;
	/** 包ID */
	private Integer packageId;
	/** 部署IP */
	private String deployIp;
	/** pod ID */
	private String podLabel;
	/** 删除标识 */
	private Integer isDel;
	/** 状态 */
	private Integer status;
	/** 删除标识 */
	private String imageName;
	/** 删除标识 */
	private String podName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public String getDeployIp() {
		return deployIp;
	}
	public void setDeployIp(String deployIp) {
		this.deployIp = deployIp;
	}
	public String getPodLabel() {
		return podLabel;
	}
	public void setPodLabel(String podLabel) {
		this.podLabel = podLabel;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getPodName() {
		return podName;
	}
	public void setPodName(String podName) {
		this.podName = podName;
	}
	
	
	
}
