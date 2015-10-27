package com.gome.autodeploy.common;

import java.io.Serializable;

public class ExpansionBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer expansionCount;
	private Integer instanceCount;
	private String projectSpell;
	private String appSpell;
	
	public Integer getExpansionCount() {
		return expansionCount;
	}
	public void setExpansionCount(Integer expansionCount) {
		this.expansionCount = expansionCount;
	}
	public Integer getInstanceCount() {
		return instanceCount;
	}
	public void setInstanceCount(Integer instanceCount) {
		this.instanceCount = instanceCount;
	}
	public String getProjectSpell() {
		return projectSpell;
	}
	public void setProjectSpell(String projectSpell) {
		this.projectSpell = projectSpell;
	}
	public String getAppSpell() {
		return appSpell;
	}
	public void setAppSpell(String appSpell) {
		this.appSpell = appSpell;
	}
	
}
