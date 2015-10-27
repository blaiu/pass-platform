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
public class Project implements Serializable {

	private static final long serialVersionUID = 3976548337322840886L;

	private Integer projectId;
	private String projectName;
	private String projectComment;
	private Date updateTime;
	private Integer isDel;
	private String projectSpell;
	private Integer userId;
	private Integer codeSource;
	
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectComment() {
		return projectComment;
	}
	public void setProjectComment(String projectComment) {
		this.projectComment = projectComment;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getProjectSpell() {
		return projectSpell;
	}
	public void setProjectSpell(String projectSpell) {
		this.projectSpell = projectSpell;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCodeSource() {
		return codeSource;
	}
	public void setCodeSource(Integer codeSource) {
		this.codeSource = codeSource;
	}
	
}
