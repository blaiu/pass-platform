/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author bailu-ds
 *
 */
public class RBDVolumeSource {

	private List<String> monitors;
	private String image;
	private String fsType;
	private String pool;
	private String user;
	private String keyring;
	private LocalObjectReference secretRef;
	private Boolean readOnly;
	public List<String> getMonitors() {
		return monitors;
	}
	public void setMonitors(List<String> monitors) {
		this.monitors = monitors;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFsType() {
		return fsType;
	}
	public void setFsType(String fsType) {
		this.fsType = fsType;
	}
	public String getPool() {
		return pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getKeyring() {
		return keyring;
	}
	public void setKeyring(String keyring) {
		this.keyring = keyring;
	}
	public LocalObjectReference getSecretRef() {
		return secretRef;
	}
	public void setSecretRef(LocalObjectReference secretRef) {
		this.secretRef = secretRef;
	}
	public Boolean getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	
}
