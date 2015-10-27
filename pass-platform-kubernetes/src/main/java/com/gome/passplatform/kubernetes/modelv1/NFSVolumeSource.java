/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author bailu-ds
 *
 */
public class NFSVolumeSource {

	private String server;
	
	private String path;

	private Boolean readOnly;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
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
