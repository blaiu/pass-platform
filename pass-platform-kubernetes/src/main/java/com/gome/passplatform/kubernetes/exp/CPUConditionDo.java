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
public class CPUConditionDo implements Serializable {

	private Integer maxValue;
	private Integer minValue;
	
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public Integer getMinValue() {
		return minValue;
	}
	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}
	
}
