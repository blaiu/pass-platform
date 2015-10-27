package com.gome.autodeploy.common.utils.page;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author bailu
 *
 */
public class Pagination extends SimplePage implements Paginable, Serializable{

	private static final long serialVersionUID = 2038987936767319237L;
	
	private String url;
	
	private List<?> list;
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}
	
	public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}
	
	public int getFirstResult () {
		return (pageNo -1) * pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
