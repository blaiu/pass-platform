/**
 * 
 */
package com.gome.passplatform.kubernetes.exp;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
@SuppressWarnings("serial")
public class MemConditionDo implements Serializable {

	private Double maxvalue;
	private Double minvalue;
	
	public Double getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(Double maxvalue) {
		this.maxvalue = maxvalue;
	}
	public Double getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(Double minvalue) {
		this.minvalue = minvalue;
	}
	
}
