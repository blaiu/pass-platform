/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class LoadBalancerStatus {

	private List<LoadBalancerIngress> ingress;

	public List<LoadBalancerIngress> getIngress() {
		return ingress;
	}

	public void setIngress(List<LoadBalancerIngress> ingress) {
		this.ingress = ingress;
	}
	
	
	
}
