/**
 * 
 */
package com.gome.autodeploy.domain.auth;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class Group implements Serializable {

	private static final long serialVersionUID = -7954205237773864506L;
	private Integer groupId;
	private String groupName;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
