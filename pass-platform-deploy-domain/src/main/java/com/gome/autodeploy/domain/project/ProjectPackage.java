/**
 * 
 */
package com.gome.autodeploy.domain.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gome.autodeploy.domain.app.App;

/**
 * @author bailu-ds
 *
 */
public class ProjectPackage implements Serializable {

	private static final long serialVersionUID = 7143529528494666509L;
	
	private Integer packageId;
	private Integer projectId;
	private String packageName;
	private String packagePath;
	private String createUser;
	private Date createTime;
	private Integer type;
	private String versionNo;
	private Integer status;
	private String imageName;
	private Integer projectType;
	private Integer deployType;
	private Integer osType;
	private String basicImage;
	private Integer isDel;
	private String memo;
	
	
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
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackagePath() {
		return packagePath;
	}
	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
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
	public Integer getProjectType() {
		return projectType;
	}
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	public Integer getDeployType() {
		return deployType;
	}
	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}
	public Integer getOsType() {
		return osType;
	}
	public void setOsType(Integer osType) {
		this.osType = osType;
	}
	public String getBasicImage() {
		return basicImage;
	}
	public void setBasicImage(String basicImage) {
		this.basicImage = basicImage;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	
}
