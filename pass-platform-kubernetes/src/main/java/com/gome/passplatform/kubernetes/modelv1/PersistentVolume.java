/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class PersistentVolume extends AbstractK8s {

	private ObjectMeta metadata;
	
	private PersistentVolumeSpec spec;

	public ObjectMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ObjectMeta metadata) {
		this.metadata = metadata;
	}

	public PersistentVolumeSpec getSpec() {
		return spec;
	}

	public void setSpec(PersistentVolumeSpec spec) {
		this.spec = spec;
	}
	
//	private PersistentVolumeStatus status;
	
	
}
