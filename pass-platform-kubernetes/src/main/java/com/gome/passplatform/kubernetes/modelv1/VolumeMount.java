/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author blaiu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeMount {

	private String name;
	private String mountPath;
	
	private Boolean readOnly;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMountPath() {
		return mountPath;
	}

	public void setMountPath(String mountPath) {
		this.mountPath = mountPath;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	
	
}
