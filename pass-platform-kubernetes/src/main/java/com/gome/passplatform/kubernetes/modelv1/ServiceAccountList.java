/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class ServiceAccountList extends AbstractK8s {

	private ListMeta metadata;
	
	private List<ServiceAccount> items;

	public ListMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ListMeta metadata) {
		this.metadata = metadata;
	}

	public List<ServiceAccount> getItems() {
		return items;
	}

	public void setItems(List<ServiceAccount> items) {
		this.items = items;
	}
	
	
	
}
