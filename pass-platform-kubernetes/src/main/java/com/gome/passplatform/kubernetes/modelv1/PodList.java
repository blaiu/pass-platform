/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class PodList extends AbstractK8s {

	private ListMeta metadata;
	
	private List<Pod> items;

	public ListMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ListMeta metadata) {
		this.metadata = metadata;
	}

	public List<Pod> getItems() {
		return items;
	}

	public void setItems(List<Pod> items) {
		this.items = items;
	}
	
	
	
}
