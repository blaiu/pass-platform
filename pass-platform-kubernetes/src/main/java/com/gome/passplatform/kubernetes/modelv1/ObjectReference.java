/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author blaiu
 *
 */
public class ObjectReference extends AbstractK8s {

	private String namespace;
	private String name;
	private String uid;
	
	private String resourceVersion;
	private String fieldPath;
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getFieldPath() {
		return fieldPath;
	}
	public void setFieldPath(String fieldPath) {
		this.fieldPath = fieldPath;
	}
	
	
	
}
