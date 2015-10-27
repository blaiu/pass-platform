/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class NamespaceList extends AbstractK8s {

	private ListMeta metadata;
	
	private List<Namespace> items;

	public ListMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ListMeta metadata) {
		this.metadata = metadata;
	}

	public List<Namespace> getItems() {
		return items;
	}

	public void setItems(List<Namespace> items) {
		this.items = items;
	}
	
	
	
}
