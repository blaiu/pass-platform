/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class SecretList extends AbstractK8s {

	private ObjectMeta metadata;
	
	private List<Secret> items;

	public ObjectMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ObjectMeta metadata) {
		this.metadata = metadata;
	}

	public List<Secret> getItems() {
		return items;
	}

	public void setItems(List<Secret> items) {
		this.items = items;
	}
	
	
	
}
