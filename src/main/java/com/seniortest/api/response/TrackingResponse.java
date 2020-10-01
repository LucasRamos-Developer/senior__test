package com.seniortest.api.response;

import java.util.List;

public class TrackingResponse<T> {
  private List<T> data;
  private List<String> tracking;

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public List<String> getTracking() {
    return tracking;
  }

  public void setTracking(List<String> tracking) {
    this.tracking = tracking;
  }
}
