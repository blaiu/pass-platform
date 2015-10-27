/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class ServiceSpec {

	private List<ServicePort> ports;
	
	private Selector selector;
	
	private String clusterIP;
	
	private String type;
	
	private List<String> deprecatedPublicIPs;
	
	private String sessionAffinity;

	public List<ServicePort> getPorts() {
		return ports;
	}

	public void setPorts(List<ServicePort> ports) {
		this.ports = ports;
	}

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector selector) {
		this.selector = selector;
	}

	public String getClusterIP() {
		return clusterIP;
	}

	public void setClusterIP(String clusterIP) {
		this.clusterIP = clusterIP;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getDeprecatedPublicIPs() {
		return deprecatedPublicIPs;
	}

	public void setDeprecatedPublicIPs(List<String> deprecatedPublicIPs) {
		this.deprecatedPublicIPs = deprecatedPublicIPs;
	}

	public String getSessionAffinity() {
		return sessionAffinity;
	}

	public void setSessionAffinity(String sessionAffinity) {
		this.sessionAffinity = sessionAffinity;
	}
	
	
	
}
