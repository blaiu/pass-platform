/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class ServiceAccount extends AbstractK8s {

	private ObjectMeta metadata;
	
	private List<ObjectReference> secrets;
	
	private List<LocalObjectReference> imagePullSecrets;

	public ObjectMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ObjectMeta metadata) {
		this.metadata = metadata;
	}

	public List<ObjectReference> getSecrets() {
		return secrets;
	}

	public void setSecrets(List<ObjectReference> secrets) {
		this.secrets = secrets;
	}

	public List<LocalObjectReference> getImagePullSecrets() {
		return imagePullSecrets;
	}

	public void setImagePullSecrets(List<LocalObjectReference> imagePullSecrets) {
		this.imagePullSecrets = imagePullSecrets;
	}
	
	
	
}
