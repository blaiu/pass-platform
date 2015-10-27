/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;


/**
 * @author bailu-ds
 *
 */
public class PodSpec {

	private List<Volume> volumes;
	private List<Container> containers;
	
	private String restartPolicy;
	private Integer terminationGracePeriodSeconds;
	private Integer activeDeadlineSeconds;
	private String dnsPolicy;
	
	private String nodeSelector;
	private String serviceAccountName;
	private String serviceAccount;
	private String nodeName;
	private Boolean hostNetwork;
	
	private LocalObjectReference imagePullSecrets;

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public String getRestartPolicy() {
		return restartPolicy;
	}

	public void setRestartPolicy(String restartPolicy) {
		this.restartPolicy = restartPolicy;
	}

	public Integer getTerminationGracePeriodSeconds() {
		return terminationGracePeriodSeconds;
	}

	public void setTerminationGracePeriodSeconds(
			Integer terminationGracePeriodSeconds) {
		this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
	}

	public Integer getActiveDeadlineSeconds() {
		return activeDeadlineSeconds;
	}

	public void setActiveDeadlineSeconds(Integer activeDeadlineSeconds) {
		this.activeDeadlineSeconds = activeDeadlineSeconds;
	}

	public String getDnsPolicy() {
		return dnsPolicy;
	}

	public void setDnsPolicy(String dnsPolicy) {
		this.dnsPolicy = dnsPolicy;
	}

	public String getNodeSelector() {
		return nodeSelector;
	}

	public void setNodeSelector(String nodeSelector) {
		this.nodeSelector = nodeSelector;
	}

	public String getServiceAccountName() {
		return serviceAccountName;
	}

	public void setServiceAccountName(String serviceAccountName) {
		this.serviceAccountName = serviceAccountName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Boolean getHostNetwork() {
		return hostNetwork;
	}

	public void setHostNetwork(Boolean hostNetwork) {
		this.hostNetwork = hostNetwork;
	}

	public LocalObjectReference getImagePullSecrets() {
		return imagePullSecrets;
	}

	public void setImagePullSecrets(LocalObjectReference imagePullSecrets) {
		this.imagePullSecrets = imagePullSecrets;
	}

	public String getServiceAccount() {
		return serviceAccount;
	}

	public void setServiceAccount(String serviceAccount) {
		this.serviceAccount = serviceAccount;
	}

	
	
}
