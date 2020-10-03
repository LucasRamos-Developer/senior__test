package com.seniortest.api.response;

import java.util.List;

public class DefaultResponse<T> {
  private T data;

  private String message;
  
  private List<String> errors;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public void addError(String error){
    this.errors.add(error);
  }
}