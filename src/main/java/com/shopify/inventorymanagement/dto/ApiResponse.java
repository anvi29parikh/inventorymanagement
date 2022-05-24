package com.shopify.inventorymanagement.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopify.inventorymanagement.enums.CustomStatusEnum;

/**
 * <h1>ApiResponse</h1>
 * <p>This will be used to manage all api  response</p>
 *
 */
public class ApiResponse {
  private int status;
  private String message;
  private Object data;

  @JsonIgnore
  private HttpStatus httpStatus;

  public ApiResponse() {
  }

  public ApiResponse(HttpStatus httpStatus, String message, Object data) {
    this.httpStatus = httpStatus;
    this.status = httpStatus.value();
    this.message = message;
    this.data = data;
  }

  public ApiResponse(HttpStatus status, String message) {
    this.status = status.value();
    this.message = message;
  }

  public ApiResponse(CustomStatusEnum status, String message, Object data) {
    this.status = status.getValue();
    this.message = message;
    this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }
}