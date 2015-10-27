/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class ListMeta {

	private String selfLink;
	private String resourceVersion;
	public String getSelfLink() {
		return selfLink;
	}
	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}
	public String getResourceVersion() {
		return resourceVersion;
	}
	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}
	
	
	
}
