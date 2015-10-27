/**
 * 
 */
package com.gome.autodeploy.domain.auth;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -7740488591694777056L;
	
	private Integer roleId;
	private String roleName;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
