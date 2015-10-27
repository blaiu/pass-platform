/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import java.util.List;

/**
 * @author blaiu
 *
 */
public class NamespaceSpec {

	private List<FinalizerName> finalizers;

	public List<FinalizerName> getFinalizers() {
		return finalizers;
	}

	public void setFinalizers(List<FinalizerName> finalizers) {
		this.finalizers = finalizers;
	}
	
	
	
}
