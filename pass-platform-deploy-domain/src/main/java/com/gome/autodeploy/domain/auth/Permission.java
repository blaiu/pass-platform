/**
 * 
 */
package com.gome.autodeploy.domain.auth;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = 6418378678861187397L;
	
	private Integer permissionId;
	private String permissionName;
	
	private Integer roleId;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	
}
