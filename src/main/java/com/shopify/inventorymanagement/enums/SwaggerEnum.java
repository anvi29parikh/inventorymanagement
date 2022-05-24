package com.shopify.inventorymanagement.enums;

/**
 * <h1>SwaggerEnum</h1>
 * <p>
 * This SwaggerEnum will be used to configure swagger ui values
 * </p>
 */
public enum SwaggerEnum {
  API_TITLE("Inventory Management Service"),
  API_DESCRIPTION("Inventory Management Services"),
  CONTROLLER_BASE_PACKAGE("com.shopify.inventorymanagement.controllers"),
  SCOPE_READ("read"),
  SCOPE_READ_DESCRIPTION("read"),
  HEADER("header"),
  ;
  private String value;

  SwaggerEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
