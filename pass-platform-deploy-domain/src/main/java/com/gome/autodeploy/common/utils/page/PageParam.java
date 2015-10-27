/**
 * 
 */
package com.gome.autodeploy.common.utils.page;

import java.io.Serializable;

/**
 * @author bailu
 *
 */
public class PageParam implements Serializable  {

	private static final long serialVersionUID = -6117301687370704493L;

	private Integer pageNo;
	
	private String userCode;
	
	private Integer status;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
	
	
	
}
