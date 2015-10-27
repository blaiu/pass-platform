/**
 * 
 */
package com.gome.passplatform.kubernetes.modelv1;

/**
 * @author bailu-ds
 *
 */
public class LimitRangeItem {

	private String type;
	private String max;//sss
	private String min;//sss
	//private String default;//sss
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	
	
}
