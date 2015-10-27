/**
 * 
 */
package com.gome.pass.platform.log;

/**
 * @author bailu-ds
 *
 */
public enum LogTimeType {
	
	D("d"),
	H("h"),
	M("m"),
	S("s");
	
	private LogTimeType(String value) {
		this.value = value;
	}
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
