/**
 * 
 */
package com.gome.autodeploy.domain.deploy;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class DeployResource implements Serializable {

	private static final long serialVersionUID = 1574353462031679744L;
	private Integer id;
	private Integer deployTaskId;
	private String deployIp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeployTaskId() {
		return deployTaskId;
	}
	public void setDeployTaskId(Integer deployTaskId) {
		this.deployTaskId = deployTaskId;
	}
	public String getDeployIp() {
		return deployIp;
	}
	public void setDeployIp(String deployIp) {
		this.deployIp = deployIp;
	}
	
}
