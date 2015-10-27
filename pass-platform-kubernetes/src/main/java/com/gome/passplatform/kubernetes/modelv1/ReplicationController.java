/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class ReplicationController extends AbstractK8s {

	private ObjectMeta metadata;
	
	private ReplicationControllerSpec spec;
	private ReplicationControllerStatus status;
	public ObjectMeta getMetadata() {
		return metadata;
	}
	public void setMetadata(ObjectMeta metadata) {
		this.metadata = metadata;
	}
	public ReplicationControllerSpec getSpec() {
		return spec;
	}
	public void setSpec(ReplicationControllerSpec spec) {
		this.spec = spec;
	}
	public ReplicationControllerStatus getStatus() {
		return status;
	}
	public void setStatus(ReplicationControllerStatus status) {
		this.status = status;
	}
	
	
	
}
