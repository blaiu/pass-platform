/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class PodTemplateList extends AbstractK8s {
	
	private ListMeta metadata;
	
	private List<PodTemplate> items;

	public ListMeta getMetadata() {
		return metadata;
	}

	public void setMetadata(ListMeta metadata) {
		this.metadata = metadata;
	}

	public List<PodTemplate> getItems() {
		return items;
	}

	public void setItems(List<PodTemplate> items) {
		this.items = items;
	}
	
	

}
