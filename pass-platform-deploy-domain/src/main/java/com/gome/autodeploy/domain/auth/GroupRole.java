/**
 * 
 */
package com.gome.autodeploy.domain.auth;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class GroupRole implements Serializable {

	private static final long serialVersionUID = 930288853039295579L;
	
	private Integer groupId;
	private Integer roleId;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	
}
