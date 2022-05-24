package com.shopify.inventorymanagement.enums;

/**
 * <h1>CustomStatusEnum</h1>
 * <p>
 * This CustomStatusEnum will be used to manage custom response status
 * </p>
 *
 */
public enum CustomStatusEnum {

  /**
   * This can be used when sql query is failed
   */
  SQL_QUERY_FAILED(1000, "Sql Query Failed"),
  ;

  private final int value;

  private final String reasonMessage;

  CustomStatusEnum(int value, String reasonMessage) {
    this.value = value;
    this.reasonMessage = reasonMessage;
  }

  public int getValue() {
    return value;
  }

  public String getReasonMessage() {
    return reasonMessage;
  }
}
