package com.seniortest.api.response;

import java.util.List;

public class PaginationResponse<T> {
  private List<T> data;

  private Integer size;

  private Integer page = 0;

  private Integer totalPages = 1;

  private Integer totalElements = 1;

	public List<T> getData() {
		return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPage) {
    this.totalPages = totalPage;
  }

  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }
}
