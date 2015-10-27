/**
 * 
 */
package com.gome.autodeploy.domain.common;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class WeekDto implements Serializable {

	private static final long serialVersionUID = 8137762361463133921L;
	private Integer week;
	private String value;
	private String name;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	
	
}
