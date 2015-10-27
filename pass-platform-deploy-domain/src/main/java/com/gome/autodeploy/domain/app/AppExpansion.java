/**
 * 
 */
package com.gome.autodeploy.domain.app;

import java.io.Serializable;

/**
 * @author bailu-ds
 *
 */
public class AppExpansion implements Serializable {

	private static final long serialVersionUID = 3976548337322840886L;
	/** 应用ID */
	private Integer id;
	/** 应用标识 */
	private Integer appId;
	/** 包ID */
	private Integer packageId;
	/** 删除标识 */
	private Integer isDel;
	/** 扩容标识 */
	private Integer expansionIndex;
	/** 取值 最大，最小，平均 */
	private Integer getValue;
	/** 最大值 */
	private Double maxValue;
	/** 最小值 */
	private Double minValue;

	/** 最大实例数 */
	private Integer maxInstance;
	/** 最小实例数 */
	private Integer minInstance;

	/** 减小量 */
	private Integer reduceCount;
	/** 增加量 */
	private Integer addCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getExpansionIndex() {
		return expansionIndex;
	}
	public void setExpansionIndex(Integer expansionIndex) {
		this.expansionIndex = expansionIndex;
	}
	public Integer getGetValue() {
		return getValue;
	}
	public void setGetValue(Integer getValue) {
		this.getValue = getValue;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
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
