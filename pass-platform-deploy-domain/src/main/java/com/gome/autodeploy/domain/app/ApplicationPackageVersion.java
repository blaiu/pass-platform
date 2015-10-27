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
public class ApplicationPackageVersion implements Serializable {

	private static final long serialVersionUID = 680431999435186134L;
	private Integer id;
	private Integer appId;
	private String packageVersionNo;
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
	public String getPackageVersionNo() {
		return packageVersionNo;
	}
	public void setPackageVersionNo(String packageVersionNo) {
		this.packageVersionNo = packageVersionNo;
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
