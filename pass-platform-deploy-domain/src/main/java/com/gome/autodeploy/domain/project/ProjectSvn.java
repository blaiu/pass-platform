/**
 * 
 */
package com.gome.autodeploy.domain.project;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bailu-ds
 *
 */
public class ProjectSvn implements Serializable {

	private static final long serialVersionUID = -2711857437009053486L;
	private Integer id;
	private Integer projectId;
	private String svnAddressTrunk;
	private String svnUserTrunk;
	private String svnPasswordTrunk;
	private String svnAddressBranch;
	private String svnUserBranch;
	private String svnPasswordBranch;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getSvnAddressTrunk() {
		return svnAddressTrunk;
	}
	public void setSvnAddressTrunk(String svnAddressTrunk) {
		this.svnAddressTrunk = svnAddressTrunk;
	}
	public String getSvnUserTrunk() {
		return svnUserTrunk;
	}
	public void setSvnUserTrunk(String svnUserTrunk) {
		this.svnUserTrunk = svnUserTrunk;
	}
	public String getSvnPasswordTrunk() {
		return svnPasswordTrunk;
	}
	public void setSvnPasswordTrunk(String svnPasswordTrunk) {
		this.svnPasswordTrunk = svnPasswordTrunk;
	}
	public String getSvnAddressBranch() {
		return svnAddressBranch;
	}
	public void setSvnAddressBranch(String svnAddressBranch) {
		this.svnAddressBranch = svnAddressBranch;
	}
	public String getSvnUserBranch() {
		return svnUserBranch;
	}
	public void setSvnUserBranch(String svnUserBranch) {
		this.svnUserBranch = svnUserBranch;
	}
	public String getSvnPasswordBranch() {
		return svnPasswordBranch;
	}
	public void setSvnPasswordBranch(String svnPasswordBranch) {
		this.svnPasswordBranch = svnPasswordBranch;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	@Override
	public String toString() {
		return "ProjectSvn [id=" + id + ", projectId=" + projectId
				+ ", svnAddressTrunk=" + svnAddressTrunk + ", svnUserTrunk="
				+ svnUserTrunk + ", svnPasswordTrunk=" + svnPasswordTrunk
				+ ", svnAddressBranch=" + svnAddressBranch + ", svnUserBranch="
				+ svnUserBranch + ", svnPasswordBranch=" + svnPasswordBranch
				+ ", createTime=" + createTime + ", createUser=" + createUser
				+ ", updateTime=" + updateTime + ", updateUser=" + updateUser
				+ "]";
	}
	
	
	
	
}
