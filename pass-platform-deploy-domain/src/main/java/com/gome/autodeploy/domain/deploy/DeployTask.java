/**
 * 
 */
package com.gome.autodeploy.domain.deploy;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bailu-ds
 *
 */
public class DeployTask implements Serializable {

	private static final long serialVersionUID = -7141596972640408762L;
	private Integer id;
	private Integer type;
	private String approvalUser;
	private Date approvalTime;
	private String title;
	private Integer appId;
	private String planDeployTime;
	private String remark;
	private String versionSrc;
	private String versionNo;
	private String createUser;
	private Date createTime;
	private Integer status;
	private Date finishTime;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getApprovalUser() {
		return approvalUser;
	}
	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getPlanDeployTime() {
		return planDeployTime;
	}
	public void setPlanDeployTime(String planDeployTime) {
		this.planDeployTime = planDeployTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getVersionSrc() {
		return versionSrc;
	}
	public void setVersionSrc(String versionSrc) {
		this.versionSrc = versionSrc;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
