/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class ServiceList {

	private ListMeta metadata;
	private List<Service> items;
	public ListMeta getMetadata() {
		return metadata;
	}
	public void setMetadata(ListMeta metadata) {
		this.metadata = metadata;
	}
	public List<Service> getItems() {
		return items;
	}
	public void setItems(List<Service> items) {
		this.items = items;
	}
	
	
}
