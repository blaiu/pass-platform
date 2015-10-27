/**
 * 
 */
package com.gome.autodeploy.domain.online;

import java.io.Serializable;
import java.util.Date;

import com.gome.autodeploy.domain.app.Application;

/**
 * @author bailu-ds
 *
 */
public class OnlineTask implements Serializable {

	private static final long serialVersionUID = 8163289806045863205L;
	private Integer id;
	private String title;
	private Integer appId;
	private Integer status;
	private Integer isNew;
	private String deployDate;
	private String deployTime;
	private Integer type;
	private Integer isChangeSql;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	public String getDeployDate() {
		return deployDate;
	}
	public void setDeployDate(String deployDate) {
		this.deployDate = deployDate;
	}
	public String getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(String deployTime) {
		this.deployTime = deployTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsChangeSql() {
		return isChangeSql;
	}
	public void setIsChangeSql(Integer isChangeSql) {
		this.isChangeSql = isChangeSql;
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
	
	private Application application;


	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
	
	
	
}
