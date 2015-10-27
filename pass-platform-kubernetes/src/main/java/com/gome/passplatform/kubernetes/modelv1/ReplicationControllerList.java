/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class ReplicationControllerList extends AbstractK8s {

	private ListMeta metadata;
	
	private List<ReplicationController> items;

	public ListMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ListMeta metadata) {
		this.metadata = metadata;
	}

	public List<ReplicationController> getItems() {
		return items;
	}

	public void setItems(List<ReplicationController> items) {
		this.items = items;
	}
	
	
	
}
