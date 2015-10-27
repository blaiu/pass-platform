package com.gome.autodeploy.common.utils.page;

/**
 * 
 * @author bailu
 *
 */
public class SimplePage implements Paginable {
	
	public static final int DEFALUT_COUNT = 50;
	
	protected int totalCount = 0;		//总记录数
	protected int pageSize = 50;		//页面显示大小
	protected int pageNo = 1;			//当前页号
	protected int totalPage = 0;		//总页数
	
	public static int cpn (Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}
	
	public SimplePage() {
	}
	
	public SimplePage (int pageNo, int pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
	}
	
	public void adjustPageNo () {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if(pageNo > tp) {
			pageNo = tp;
		}
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPage() {
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}
	
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}
	
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	public void setTotalCount(int totalCount) {
		if(totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	public void setPageSize(int pageSize) {
		if(pageSize < 1) {
			this.pageSize = DEFALUT_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	

}
