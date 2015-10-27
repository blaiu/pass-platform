/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class ReplicationControllerStatus {

	private Integer replicas;
	private Integer observedGeneration;
	public Integer getReplicas() {
		return replicas;
	}
	public void setReplicas(Integer replicas) {
		this.replicas = replicas;
	}
	public Integer getObservedGeneration() {
		return observedGeneration;
	}
	public void setObservedGeneration(Integer observedGeneration) {
		this.observedGeneration = observedGeneration;
	}
	
	
}
