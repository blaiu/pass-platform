/**
 * 
 */
package com.gome.passplatform.kubernetes.storage;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
@SuppressWarnings("serial")
public class Storage implements Serializable {

	public String name;
	public Long size;
	public Integer objects;
	public Integer order;
	public Long objects_size;
	public String block_name_prefix;
	public Integer format;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Integer getObjects() {
		return objects;
	}
	public void setObjects(Integer objects) {
		this.objects = objects;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Long getObjects_size() {
		return objects_size;
	}
	public void setObjects_size(Long objects_size) {
		this.objects_size = objects_size;
	}
	public String getBlock_name_prefix() {
		return block_name_prefix;
	}
	public void setBlock_name_prefix(String block_name_prefix) {
		this.block_name_prefix = block_name_prefix;
	}
	public Integer getFormat() {
		return format;
	}
	public void setFormat(Integer format) {
		this.format = format;
	}
	
	
}
