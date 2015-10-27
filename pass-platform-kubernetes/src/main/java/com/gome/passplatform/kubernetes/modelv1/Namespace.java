/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class Namespace extends AbstractK8s {

	private ObjectMeta metadata;
	
	private NamespaceSpec spec;
	
	private NamespaceStatus status;

	public ObjectMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ObjectMeta metadata) {
		this.metadata = metadata;
	}

	public NamespaceSpec getSpec() {
		return spec;
	}

	public void setSpec(NamespaceSpec spec) {
		this.spec = spec;
	}

	public NamespaceStatus getStatus() {
		return status;
	}

	public void setStatus(NamespaceStatus status) {
		this.status = status;
	}
	
	
	
}
