/**
 * 
 */
package com.gome.autodeploy.common;

/**
 * @author bailu-ds
 *
 */
public enum DictionaryType {

	/** 应用类型*/
	APPTYPE(1000),
	/** 部署类型*/
	DEPLOYTYPE(1001),
	/** 域名类型*/
	DOMAINTYPE(1008),
	/** 编译类型*/
	COMPILETYPE(1007),
	/** 应用级别*/
	APPTLEVEL(1006),
	
	/** 是否新应用*/
	IS_NEWAPP(1002),
	/** 上线类型*/
	ONLINE_TYPE(1003),
	/** 是否SQL变动*/
	IS_SQLCHANGE(1004),
	/** 上线时间段*/
	ONLINE_TIME(1005),
	
	/** 上线时间段*/
	MIRROR_TYPE(1010);
	private Integer value;
	private DictionaryType(Integer value) {
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	
	
	
}
