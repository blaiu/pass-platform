/**
 * 
 */
package com.gome.pass.platform.log.bean;

import java.io.Serializable;


/**
 * @author bailu-ds
 *
 */
@SuppressWarnings("serial")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable {

	private String containers_id;
	
	private String scrollId;
	
	private String stream;
	
	private String time;
	
	private String log;


	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getLog() {
		return log;
	}
	
	public void setLog(String log) {
		this.log = log;
	}

	public String getContainers_id() {
		return containers_id;
	}

	public void setContainers_id(String containers_id) {
		this.containers_id = containers_id;
	}

	public String getScrollId() {
		return scrollId;
	}

	public void setScrollId(String scrollId) {
		this.scrollId = scrollId;
	}
	
	

}
