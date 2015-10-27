/**
 * 
 */
package com.gome.autodeploy.domain.app;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class ApplicationMember implements Serializable {

	private static final long serialVersionUID = 7277956700940334697L;
	private Integer id;
	private Integer appId;
	private String userCode;
	private String userName;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
