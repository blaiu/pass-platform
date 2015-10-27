package com.gome.autodeploy.domain.app;

import java.io.Serializable;

public class ExpansionPlan implements Serializable{

	private static final long serialVersionUID = 3976548337322840886L;
	
	/** 扩容标识 */
	private Integer memoryExpansionIndex;
	/** 取值 最大，最小，平均 */
	private Integer memoryGetValue;
	/** 最大值 */
	private Double memoryMaxValue;
	/** 最小值 */
	private Double memoryMinValue;
	
	/** 扩容标识 */
	private Integer cpuExpansionIndex;
	/** 取值 最大，最小，平均 */
	private Integer cpuGetValue;
	/** 最大值 */
	private Double cpuMaxValue;
	/** 最小值 */
	private Double cpuMinValue;

	/** 最大实例数 */
	private Integer maxInstance;
	/** 最小实例数 */
	private Integer minInstance;

	/** 减小量 */
	private Integer reduceCount;
	/** 增加量 */
	private Integer addCount;
	
	public Integer getMemoryExpansionIndex() {
		return memoryExpansionIndex;
	}
	public void setMemoryExpansionIndex(Integer memoryExpansionIndex) {
		this.memoryExpansionIndex = memoryExpansionIndex;
	}
	public Integer getMemoryGetValue() {
		return memoryGetValue;
	}
	public void setMemoryGetValue(Integer memoryGetValue) {
		this.memoryGetValue = memoryGetValue;
	}
	public Double getMemoryMaxValue() {
		return memoryMaxValue;
	}
	public void setMemoryMaxValue(Double memoryMaxValue) {
		this.memoryMaxValue = memoryMaxValue;
	}
	public Double getMemoryMinValue() {
		return memoryMinValue;
	}
	public void setMemoryMinValue(Double memoryMinValue) {
		this.memoryMinValue = memoryMinValue;
	}
	public Integer getCpuExpansionIndex() {
		return cpuExpansionIndex;
	}
	public void setCpuExpansionIndex(Integer cpuExpansionIndex) {
		this.cpuExpansionIndex = cpuExpansionIndex;
	}
	public Integer getCpuGetValue() {
		return cpuGetValue;
	}
	public void setCpuGetValue(Integer cpuGetValue) {
		this.cpuGetValue = cpuGetValue;
	}
	public Double getCpuMaxValue() {
		return cpuMaxValue;
	}
	public void setCpuMaxValue(Double cpuMaxValue) {
		this.cpuMaxValue = cpuMaxValue;
	}
	public Double getCpuMinValue() {
		return cpuMinValue;
	}
	public void setCpuMinValue(Double cpuMinValue) {
		this.cpuMinValue = cpuMinValue;
	}
	public Integer getMaxInstance() {
		return maxInstance;
	}
	public void setMaxInstance(Integer maxInstance) {
		this.maxInstance = maxInstance;
	}
	public Integer getMinInstance() {
		return minInstance;
	}
	public void setMinInstance(Integer minInstance) {
		this.minInstance = minInstance;
	}
	public Integer getReduceCount() {
		return reduceCount;
	}
	public void setReduceCount(Integer reduceCount) {
		this.reduceCount = reduceCount;
	}
	public Integer getAddCount() {
		return addCount;
	}
	public void setAddCount(Integer addCount) {
		this.addCount = addCount;
	}
	
	
}
