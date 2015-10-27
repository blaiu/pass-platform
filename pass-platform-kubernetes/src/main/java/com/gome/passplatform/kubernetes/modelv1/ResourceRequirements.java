/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class ResourceRequirements {

	private Limit limits;
	private String requests;
	
	public Limit getLimits() {
		return limits;
	}
	public void setLimits(Limit limits) {
		this.limits = limits;
	}
	public String getRequests() {
		return requests;
	}
	public void setRequests(String requests) {
		this.requests = requests;
	}
	
	
	
}
