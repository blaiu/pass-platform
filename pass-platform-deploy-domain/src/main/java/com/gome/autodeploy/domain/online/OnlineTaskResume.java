/**
 * 
 */
package com.gome.autodeploy.domain.online;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bailu-ds
 *
 */
public class OnlineTaskResume implements Serializable {

	private static final long serialVersionUID = -1161545508940802253L;
	private Integer id;
	private Integer taskId;
	private Integer status;
	private String createUser;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
	
	
}
