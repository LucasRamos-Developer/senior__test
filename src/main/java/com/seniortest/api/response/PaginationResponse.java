package com.seniortest.api.response;

import java.util.List;

public class PaginationResponse<T> {
  private List<T> data;

  private Integer size;

  private Integer page = 0;

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

}
