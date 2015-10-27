/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class SecurityContext {

	private Capabilities capabilities;
	
	private Boolean privileged;
	
	private SELinuxOptions seLinuxOptions;
	
	private Integer runAsUser;

	public Capabilities getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(Capabilities capabilities) {
		this.capabilities = capabilities;
	}

	public Boolean getPrivileged() {
		return privileged;
	}

	public void setPrivileged(Boolean privileged) {
		this.privileged = privileged;
	}

	public SELinuxOptions getSeLinuxOptions() {
		return seLinuxOptions;
	}

	public void setSeLinuxOptions(SELinuxOptions seLinuxOptions) {
		this.seLinuxOptions = seLinuxOptions;
	}

	public Integer getRunAsUser() {
		return runAsUser;
	}

	public void setRunAsUser(Integer runAsUser) {
		this.runAsUser = runAsUser;
	}
	
	
	
}
