/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author bailu-ds
 *
 */
public class Pod extends AbstractK8s {

	private ObjectMeta metadata;
	private PodSpec spec;
	private PodStatus status;
	public ObjectMeta getMetadata() {
		return metadata;
	}
	public void setMetadata(ObjectMeta metadata) {
		this.metadata = metadata;
	}
	public PodSpec getSpec() {
		return spec;
	}
	public void setSpec(PodSpec spec) {
		this.spec = spec;
	}
	public PodStatus getStatus() {
		return status;
	}
	public void setStatus(PodStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Pod [metadata=" + metadata + ", spec=" + spec + ", status="
				+ status + "]";
	}
	
	
	
	
}
