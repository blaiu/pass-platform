/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author bailu-ds
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Annotation {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
