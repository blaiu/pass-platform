/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class Capabilities {

	private List<Capability> add;
	private List<Capability> drop;
	public List<Capability> getAdd() {
		return add;
	}
	public void setAdd(List<Capability> add) {
		this.add = add;
	}
	public List<Capability> getDrop() {
		return drop;
	}
	public void setDrop(List<Capability> drop) {
		this.drop = drop;
	}
	
	
	
}
