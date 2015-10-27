/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class Service extends AbstractK8s {

	private ObjectMeta metadata;
	private ServiceSpec spec;
	private ServiceStatus status;
	public ObjectMeta getMetadata() {
		return metadata;
	}
	public void setMetadata(ObjectMeta metadata) {
		this.metadata = metadata;
	}
	public ServiceSpec getSpec() {
		return spec;
	}
	public void setSpec(ServiceSpec spec) {
		this.spec = spec;
	}
	public ServiceStatus getStatus() {
		return status;
	}
	public void setStatus(ServiceStatus status) {
		this.status = status;
	}
	
	
}
