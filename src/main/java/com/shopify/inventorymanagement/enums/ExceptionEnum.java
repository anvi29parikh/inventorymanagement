package com.shopify.inventorymanagement.enums;

/**
 * <h1>ExceptionEnum</h1>
 * <p>This enum will be used to return error api response messages</p>
 *
 * @author Softvan Nester
 * @version 1.0
 * @since 13-05-2020
 */
public enum ExceptionEnum {

  ACCESS_DENIED("Access denied"),
  SOMETHING_WENT_WRONG("Something went wrong"),
  PRODUCT_ID_INVALID("Product Id is not present in database"),
  PRODUCT_NOT_IN_INVENTORY("Product Id is not present in inventory"),
  ;

  private String value;

  private String message;

  ExceptionEnum(String value) {
    this.value = value;
  }

  ExceptionEnum(String message, String value) {
    this.message = message;
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String getMessage() {
    return message;
  }
}
