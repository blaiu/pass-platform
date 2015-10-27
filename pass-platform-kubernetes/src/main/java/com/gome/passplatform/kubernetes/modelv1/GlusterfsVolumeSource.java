/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author bailu-ds
 *
 */
public class GlusterfsVolumeSource {

	private String endpoints;
	private String path;
	private Boolean readOnly;
	
	public String getEndpoints() {
		return endpoints;
	}
	public void setEndpoints(String endpoints) {
		this.endpoints = endpoints;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Boolean getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	
	
}
