package com.gome.autodeploy.domain.auth;

import java.io.Serializable;

/**
 * 
 */

/**
 * @author bailu-ds
 *
 */
public class UserGroup implements Serializable {

	private static final long serialVersionUID = -1690535455353719146L;
	
	private Integer userId;
	private Integer groupId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	
	
}
