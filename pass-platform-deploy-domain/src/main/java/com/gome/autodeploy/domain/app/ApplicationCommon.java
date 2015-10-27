/**
 * 
 */
package com.gome.autodeploy.domain.app;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bailu-ds
 *
 */
public class ApplicationCommon implements Serializable {

	private static final long serialVersionUID = -6859729722776091096L;
	
	private Integer id;
	private Integer appId;
	private String fileName;
	private String filePath;
	private Date createTime;
	private String createUser;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	
	
	
}
