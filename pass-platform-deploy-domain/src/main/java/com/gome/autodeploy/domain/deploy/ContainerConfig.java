package com.gome.autodeploy.domain.deploy;

import java.io.Serializable;

public class ContainerConfig implements Serializable{
    
    private static final long serialVersionUID = 1574353462031679744L;
	
	/** ID */
	private Integer id;
	/** 配置名称 */
	private String configName;
	/** memory */
	private Integer memory;
	/** cpu */
	private Float cpu;
	/** 删除标识 */
	private Integer isDel;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public Integer getMemory() {
		return memory;
	}
	public void setMemory(Integer memory) {
		this.memory = memory;
	}
	public Float getCpu() {
		return cpu;
	}
	public void setCpu(Float cpu) {
		this.cpu = cpu;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
}
