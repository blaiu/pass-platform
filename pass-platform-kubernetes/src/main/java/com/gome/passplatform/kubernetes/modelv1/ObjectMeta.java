/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author bailu-ds
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectMeta {

	private String name;
	private String generateName;
	private String namespace;
	private String selfLink;
	private String uid;
	private String resourceVersion;
	private Integer generation;
	private String creationTimestamp;
	private String deletionTimestamp;
	
	private Map<String, String> labels;
	private Annotation annotations;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenerateName() {
		return generateName;
	}
	public void setGenerateName(String generateName) {
		this.generateName = generateName;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getSelfLink() {
		return selfLink;
	}
	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getResourceVersion() {
		return resourceVersion;
	}
	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}
	public Integer getGeneration() {
		return generation;
	}
	public void setGeneration(Integer generation) {
		this.generation = generation;
	}
	public String getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(String creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	public String getDeletionTimestamp() {
		return deletionTimestamp;
	}
	public void setDeletionTimestamp(String deletionTimestamp) {
		this.deletionTimestamp = deletionTimestamp;
	}
//	public Label getLabels() {
//		return labels;
//	}
//	public void setLabels(Label labels) {
//		this.labels = labels;
//	}
	public Annotation getAnnotations() {
		return annotations;
	}
	public void setAnnotations(Annotation annotations) {
		this.annotations = annotations;
	}
	public Map<String, String> getLabels() {
		return labels;
	}
	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}
	
	
	
	
}
