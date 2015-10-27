/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class ServiceStatus {

	private LoadBalancerStatus loadBalancer;

	public LoadBalancerStatus getLoadBalancer() {
		return loadBalancer;
	}

	public void setLoadBalancer(LoadBalancerStatus loadBalancer) {
		this.loadBalancer = loadBalancer;
	}
	
}
