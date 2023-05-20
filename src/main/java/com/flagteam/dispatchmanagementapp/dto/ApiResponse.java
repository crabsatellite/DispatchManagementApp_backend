package com.flagteam.dispatchmanagementapp.dto;

public class ApiResponse<T> {
  private int status;
  private T data;

  public ApiResponse() {}

  public ApiResponse(int status, T data) {
    this.status = status;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
