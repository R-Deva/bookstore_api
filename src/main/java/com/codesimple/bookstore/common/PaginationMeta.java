package com.codesimple.bookstore.common;

import org.springframework.data.domain.Page;

public class PaginationMeta {

	private long totalCount;
	private Integer pageSize;
	private Integer totalPage;
	private Integer pageNumber;
	private boolean isLast;
	private boolean isFirst;
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public boolean getIsLast() {
		return isLast;
	}
	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}
	public boolean getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	
	public static <T> PaginationMeta createPagination(Page<T> page)
	{
		PaginationMeta paginationMeta = new PaginationMeta();
		paginationMeta.setIsFirst(page.isFirst());
		paginationMeta.setIsLast(page.isLast());
		paginationMeta.setPageNumber(page.getNumber()+1);
		paginationMeta.setPageSize(page.getSize());
		paginationMeta.setTotalCount(page.getTotalElements());
		paginationMeta.setTotalPage(page.getTotalPages());
		
		return paginationMeta;
		
	}
	
	
}
